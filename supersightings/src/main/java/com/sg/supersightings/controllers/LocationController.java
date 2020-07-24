/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.controllers;

import com.sg.supersightings.exceptions.InvalidEntityException;
import com.sg.supersightings.models.Location;
import com.sg.supersightings.repositories.LocationRepository;
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
    LocationRepository locationRepo;
     
     @GetMapping("/createlocation")
    public String createNewLocation(Model model) {
        model.addAttribute("isValid", true);
        return "createlocation";
    }
    
    @PostMapping("/createlocation")
    public String createNewLocation(Location toAdd, Model model){
        if (toAdd.getLocationName().isEmpty()) {
                model.addAttribute("isValid", false);
                model.addAttribute("errorMessage", "Location Name must not be blank.");
                return "createlocation";
            }
        
        if (toAdd.getLatitude().isEmpty()) {
                model.addAttribute("isValid", false);
                model.addAttribute("errorMessage", "Location Latitude must not be blank.");
                return "createlocation";
            }
        if (toAdd.getLongitude().isEmpty()) {
                model.addAttribute("isValid", false);
                model.addAttribute("errorMessage", "Location Longitude must not be blank.");
                return "createlocation";
            }
        
        
        try {
            BigDecimal latitude = new BigDecimal(toAdd.getLatitude());
            BigDecimal longitude = new BigDecimal(toAdd.getLongitude());
            
            if(latitude.compareTo(new BigDecimal("-90")) == -1|| latitude.compareTo(new BigDecimal("90")) == 1) {
                throw new InvalidEntityException("Latitude must be between -90 and 90.");
            }
            if(longitude.compareTo(new BigDecimal("-180")) == -1|| longitude.compareTo(new BigDecimal("180")) == 1) {
                throw new InvalidEntityException("Longitude must be between -180 and 180.");
            }
            locationRepo.save(toAdd);
        }  catch (DataIntegrityViolationException ex) {
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
        locationRepo.save(toAdd);
        return "redirect:/alllocations";
    }
    
     @GetMapping("/alllocations")
    public String displayAllLocations(Model model) {
        model.addAttribute("allLocations", locationRepo.findAll());
        return "alllocations";
    }
    
     @GetMapping("/deletelocation/{id}")
    public String deleteLocation(@PathVariable Integer id) {
        Location toRemove = locationRepo.getOne(id);
        locationRepo.delete(toRemove);
        return "redirect:/alllocations";
    }
    
    @GetMapping("/updatelocation/{id}")
    public String updateLocation(Model model, @PathVariable Integer id) {
        model.addAttribute("location", locationRepo.getOne(id));
        model.addAttribute("isValid", true);
        return "updatelocation";
    }
     
    @PostMapping("/updatelocation/{id}")
    public String updateLocations(Location toEdit, Model model) {
        model.addAttribute("location", locationRepo.getOne(toEdit.getId()));
        
        
        
        if (toEdit.getLocationName().isEmpty()) {
                model.addAttribute("isValid", false);
                model.addAttribute("errorMessage", "Location Name must not be blank.");
                return "updatelocation";
            }
        
        if (toEdit.getLatitude().isEmpty()) {
                model.addAttribute("isValid", false);
                model.addAttribute("errorMessage", "Location Latitude must not be blank.");
                return "updatelocation";
            }
        if (toEdit.getLongitude().isEmpty()) {
                model.addAttribute("isValid", false);
                model.addAttribute("errorMessage", "Location Longitude must not be blank.");
                return "updatelocation";
            }
        
        
        try {
            BigDecimal latitude = new BigDecimal(toEdit.getLatitude());
            BigDecimal longitude = new BigDecimal(toEdit.getLongitude());
            
            if(latitude.compareTo(new BigDecimal("-90")) == -1|| latitude.compareTo(new BigDecimal("90")) == 1) {
                throw new InvalidEntityException("Latitude must be between -90 and 90.");
            }
            if(longitude.compareTo(new BigDecimal("-180")) == -1|| longitude.compareTo(new BigDecimal("180")) == 1) {
                throw new InvalidEntityException("Longitude must be between -180 and 180.");
            }
            locationRepo.save(toEdit);
        }  catch (DataIntegrityViolationException ex) {
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
