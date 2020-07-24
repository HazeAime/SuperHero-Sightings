/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.controllers;

import com.sg.supersightings.exceptions.InvalidEntityException;
import com.sg.supersightings.models.Sighting;
import com.sg.supersightings.models.SightingVM;
import com.sg.supersightings.repositories.LocationRepository;
import com.sg.supersightings.repositories.SightingRepository;
import com.sg.supersightings.repositories.SuperRepository;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import javax.persistence.Entity;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author siessejordan
 */
@Controller
public class SightingController {

    @Autowired
    SightingRepository sightingRepo;

    @Autowired
    SuperRepository superBeingRepo;

    @Autowired
    LocationRepository locationRepo;

    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    //ManyToOne ?
    @GetMapping("/createsighting")
    public String createNewSighting(Model model) {
        model.addAttribute("allSupers", superBeingRepo.findAll());
        model.addAttribute("allLocations", locationRepo.findAll());
        model.addAttribute("isValid", true);
        return "createsighting";
    }

    //ManyToOne ?
    @PostMapping("/createsighting")
    public String createNewSighting(SightingVM toAdd, Model model) {
        model.addAttribute("allLocations", locationRepo.findAll());
        model.addAttribute("allSupers", superBeingRepo.findAll());

        try {
            if (toAdd.getDate().isEmpty()) {
                model.addAttribute("isValid", false);
                model.addAttribute("errorMessage", "A Sighting Date must not be blank.");
                return "createsighting";
            }
            if (toAdd.getSuperId() == 0) {
                model.addAttribute("isValid", false);
                model.addAttribute("errorMessage", "A Super must be selected.");
                return "createsighting";
            }
            if (toAdd.getLocationId() == 0) {
                model.addAttribute("isValid", false);
                model.addAttribute("errorMessage", "A Location must be selected.");
                return "createsighting";
            }
            LocalDate date = LocalDate.parse(toAdd.getDate());
            Sighting sighting = new Sighting();
            sighting.setLocation(locationRepo.getOne(toAdd.getLocationId()));
            sighting.setSuperBeing(superBeingRepo.getOne(toAdd.getSuperId()));
            sighting.setDate(date);
            validateEntity(sighting);

            sightingRepo.save(sighting);
        } catch (InvalidEntityException ex) {
            model.addAttribute("isValid", false);
            model.addAttribute("errorMessage", ex.getMessage());
            return "createsighting";
        }
        return "redirect:/allsightings";
    }

    @GetMapping("/updatesighting/{id}")
    public String updateSighting(Model model, @PathVariable Integer id) {
        model.addAttribute("sighting", sightingRepo.getOne(id));
        model.addAttribute("allSupers", superBeingRepo.findAll());
        model.addAttribute("allLocations", locationRepo.findAll());
        model.addAttribute("isValid", true);
        return "updatesighting";
    }

    @PostMapping("/updatesighting/{id}")
    public String updateSighting(Model model, SightingVM toAdd, @PathVariable int id) {
        model.addAttribute("allLocations", locationRepo.findAll());
        model.addAttribute("allSupers", superBeingRepo.findAll());

        try {
            if (toAdd.getDate().isEmpty()) {
                model.addAttribute("isValid", false);
                model.addAttribute("errorMessage", "A Sighting Date must not be blank.");
                return "updatesighting";
            }
            if (toAdd.getSuperId() == 0) {
                model.addAttribute("isValid", false);
                model.addAttribute("errorMessage", "A Super must be selected.");
                return "updatesighting";
            }
            if (toAdd.getLocationId() == 0) {
                model.addAttribute("isValid", false);
                model.addAttribute("errorMessage", "A Location must be selected.");
                return "updatesighting";
            }

            LocalDate date = LocalDate.parse(toAdd.getDate());
            Sighting sighting = sightingRepo.findById(id).orElse(new Sighting());
            sighting.setLocation(locationRepo.getOne(toAdd.getLocationId()));
            sighting.setSuperBeing(superBeingRepo.getOne(toAdd.getSuperId()));
            sighting.setDate(date);
            model.addAttribute("sighting", sighting);
            validateEntity(sighting);

            sightingRepo.save(sighting);
        } catch (InvalidEntityException ex) {
            model.addAttribute("isValid", false);
            model.addAttribute("errorMessage", ex.getMessage());
            return "updatesighting";
        }
        return "redirect:/allsightings";
    }

    @GetMapping("/allsightings")
    public String displayAllSightings(Model model) {
        model.addAttribute("allSightings", sightingRepo.findAll());
        return "allsightings";
    }

    @GetMapping("/deletesighting/{id}")
    public String deleteSighting(Sighting toRemove, @PathVariable Integer id) {
        sightingRepo.delete(toRemove);
        return "redirect:/allsightings";
    }

    public void validateEntity(Sighting sighting) throws InvalidEntityException {
        Set<ConstraintViolation<Sighting>> errors = validator.validate(sighting);

        if (errors.size() > 0) {
            throw new InvalidEntityException("A Sighting must have a valid date.");
        }

//         if(errors.contains(DateTimeParseException))
    }

}
