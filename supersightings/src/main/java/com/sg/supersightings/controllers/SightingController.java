/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.controllers;

import com.sg.supersightings.exceptions.InvalidEntityException;
import com.sg.supersightings.models.Sighting;
import com.sg.supersightings.models.SightingVM;
import com.sg.supersightings.services.LocationService;
import com.sg.supersightings.services.SightingService;
import com.sg.supersightings.services.SuperService;
import java.time.LocalDate;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author siessejordan
 */
@Controller
public class SightingController {

    @Autowired
    SuperService superService;

    @Autowired
    SightingService sightingService;

    @Autowired
    LocationService locationService;

    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    //ManyToOne ?
    @GetMapping("/createsighting")
    public String createNewSighting(Model model) {
        model.addAttribute("allSupers", superService.findAll());
        model.addAttribute("allLocations", locationService.findAll());
        model.addAttribute("isValid", true);
        return "createsighting";
    }

    //ManyToOne ?
    @PostMapping("/createsighting")
    public String createNewSighting(SightingVM toAdd, Model model) {
        model.addAttribute("allLocations", locationService.findAll());
        model.addAttribute("allSupers", superService.findAll());

        try {
            sightingService.saveNewSighting(toAdd);
        } catch (InvalidEntityException ex) {
            model.addAttribute("isValid", false);
            model.addAttribute("errorMessage", ex.getMessage());
            return "createsighting";
        }
        return "redirect:/allsightings";
    }

    @GetMapping("/updatesighting/{id}")
    public String updateSighting(Model model, @PathVariable Integer id) {
        model.addAttribute("sighting", sightingService.getOne(id));
        model.addAttribute("allSupers", superService.findAll());
        model.addAttribute("allLocations", locationService.findAll());
        model.addAttribute("isValid", true);
        return "updatesighting";
    }

    @PostMapping("/updatesighting/{id}")
    public String updateSighting(Model model, SightingVM toAdd, @PathVariable int id) {
        model.addAttribute("allLocations", locationService.findAll());
        model.addAttribute("allSupers", superService.findAll());
        
        try {
            sightingService.updateSighting(id, toAdd);
        } catch (InvalidEntityException ex) {
            model.addAttribute("isValid", false);
            model.addAttribute("errorMessage", ex.getMessage());
            return "updatesighting";
        }
        return "redirect:/allsightings";
    }

    @GetMapping("/allsightings")
    public String displayAllSightings(Model model) {
        model.addAttribute("allSightings", sightingService.findAll());
        return "allsightings";
    }

    @GetMapping("/deletesighting/{id}")
    public String deleteSighting(@PathVariable Integer id) {
        sightingService.delete(id);
        return "redirect:/allsightings";
    }
}
