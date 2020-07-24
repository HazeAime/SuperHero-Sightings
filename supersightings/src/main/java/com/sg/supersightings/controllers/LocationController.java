/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.controllers;

import com.sg.supersightings.exceptions.InvalidEntityException;
import com.sg.supersightings.models.Location;
import com.sg.supersightings.repositories.LocationRepository;
import com.sg.supersightings.services.LocationService;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
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
public class LocationController {

    @Autowired
    LocationService locationService;

    @GetMapping("/createlocation")
    public String createNewLocation(Model model) {
        model.addAttribute("isValid", true);
        return "createlocation";
    }

    @PostMapping("/createlocation")
    public String createNewLocation(Location toAdd, Model model) {
        try {
            locationService.saveNewLocation(toAdd);
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("isValid", false);
            model.addAttribute("errorMessage", "Location already exists.");
            return "createlocation";
        } catch (NumberFormatException ex) {
            model.addAttribute("isValid", false);
            model.addAttribute("errorMessage", "Location must have a valid Latitude and Longitude.");
            return "createlocation";
        } catch (InvalidEntityException ex) {
            model.addAttribute("isValid", false);
            model.addAttribute("errorMessage", ex.getMessage());
            return "createlocation";
        }
        return "redirect:/alllocations";
    }

    @GetMapping("/alllocations")
    public String displayAllLocations(Model model) {
        model.addAttribute("allLocations", locationService.findAll());
        return "alllocations";
    }

    @GetMapping("/deletelocation/{id}")
    public String deleteLocation(@PathVariable Integer id) {
        locationService.delete(id);
        return "redirect:/alllocations";
    }

    @GetMapping("/updatelocation/{id}")
    public String updateLocation(Model model, @PathVariable Integer id) {
        model.addAttribute("location", locationService.getOne(id));
        model.addAttribute("isValid", true);
        return "updatelocation";
    }

    @PostMapping("/updatelocation/{id}")
    public String updateLocations(Location toEdit, Model model) {
        model.addAttribute("location", locationService.getOne(toEdit.getId()));
        try {
            locationService.updateLocation(toEdit);
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("isValid", false);
            model.addAttribute("errorMessage", "Location already exists.");
            return "updatelocation";
        } catch (NumberFormatException ex) {
            model.addAttribute("isValid", false);
            model.addAttribute("errorMessage", "Location must have a valid Latitude and Longitude.");
            return "updatelocation";
        } catch (InvalidEntityException ex) {
            model.addAttribute("isValid", false);
            model.addAttribute("errorMessage", ex.getMessage());
            return "updatelocation";
        }

        return "redirect:/alllocations";

    }

    @GetMapping("/locationdetails/{id}")
    public String displayLocationDetails() {
        return "locationdetails";
    }
}
