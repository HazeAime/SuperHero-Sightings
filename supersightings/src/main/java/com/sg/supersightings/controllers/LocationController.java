/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.controllers;

import com.sg.supersightings.models.Location;
import com.sg.supersightings.repositories.LocationRepository;
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
public class LocationController {
    
     @Autowired
    LocationRepository location;
     
     @GetMapping("createlocation")
    public String createNewLocation() {
        return "createlocation";
    }
    
    @PostMapping("createlocation")
    public String createNewLocation(Location toAdd){
        location.save(toAdd);
        return "alllocations";
    }
    
     @GetMapping("alllocations")
    public String displayAllLocations(Model model) {
        model.addAttribute("alllocations", location.findAll());
        return "alllocations";
    }
    
     @GetMapping("deletelocation")
    public String deleteLocation(Location toRemove) {
        location.delete(toRemove);
        return "alllocations";
    }
    
    @GetMapping("updatelocation/{id}")
    public String updateLocation(Model model, @PathVariable Integer id) {
        model.addAttribute("location", location.findById(id).orElse(null));
        return "redirect:/locationdetails/{id}";
    }
     
    @PostMapping("updatelocation/{id}")
    public String updateLocations(Location toEdit) {
        location.save(toEdit);
        return "redirect:/locationdetails/{id}";
    }
    
    @GetMapping("locationdetails/{id}")
    public String displayLocationDetails() {
        return "locationdetails/{id}";
    }
}
