/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.controllers;

import com.sg.supersightings.models.Sighting;
import com.sg.supersightings.repositories.LocationRepository;
import com.sg.supersightings.repositories.SightingRepository;
import com.sg.supersightings.repositories.SuperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    
    //ManyToOne ?
    @GetMapping("createsighting")
    public String createNewSighting(Model model) {
        model.addAttribute("allSupers", superBeingRepo.findAll());
        model.addAttribute("allLocations", locationRepo.findAll());
        return "createsighting";
    }
    //ManyToOne ?
    @PostMapping("createsighting")
    public String createNewSighting(Sighting toAdd) {
        sightingRepo.save(toAdd);
        return "allsightings";
    }
    
     @GetMapping("allsightings")
    public String displayAllSightings(Model model) {
        model.addAttribute("allSightings", sightingRepo.findAll());
        return "allsightings";
    }
    
    @GetMapping("deletesighting")
    public String deleteLocation(Sighting toRemove) {
        sightingRepo.delete(toRemove);
        return "alllocations";
    }
}
