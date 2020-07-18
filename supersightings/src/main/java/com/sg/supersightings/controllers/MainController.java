/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.controllers;

import com.sg.supersightings.models.Location;
import com.sg.supersightings.models.Organization;
import com.sg.supersightings.models.Power;
import com.sg.supersightings.models.Sighting;
import com.sg.supersightings.models.Super;
import com.sg.supersightings.repositories.LocationRepository;
import com.sg.supersightings.repositories.OrganizationRepository;
import com.sg.supersightings.repositories.PowerRepository;
import com.sg.supersightings.repositories.SightingRepository;
import com.sg.supersightings.repositories.SuperRepository;
import java.util.List;
import java.util.stream.Collectors;
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
public class MainController {
    
    @Autowired
    LocationRepository location;
    
    @Autowired
    OrganizationRepository organization;
    
    @Autowired
    PowerRepository power;
    
    @Autowired
    SightingRepository sighting;
    
    @Autowired
    SuperRepository superBeing;
    
    @GetMapping("/")
    public String displayHomepage() {   
        return "home";
    }
    
    @GetMapping("createlocation")
    public String createNewLocation() {
        return "createlocation";
    }
    
    @PostMapping("createlocation")
    public String createNewLocation(Location toAdd){
        location.save(toAdd);
        return "alllocations";
    }
    
    @GetMapping("createorganization")
    public String createNewOrg() {
        return "createorganization";
    }
    
    @PostMapping("createorganization")
    public String createNewOrg(Organization toAdd) {
        organization.save(toAdd);
        return "allorganizations";
    }
    
    @GetMapping("createpower")
    public String createNewPower(){
        return "allpowers";
    }
    
    @PostMapping("createpower")
    public String createNewPower(Power toAdd) {
        power.save(toAdd);
        return "allpowers";
    }
    //ManyToOne ?
    @GetMapping("createsighting")
    public String createNewSighting() {
        return "createsighting";
    }
    //ManyToOne ?
    @PostMapping("createsighting")
    public String createNewSighting(Sighting toAdd) {
        sighting.save(toAdd);
        return "allsightings";
    }
        
    @GetMapping("createsuper")
    public String createNewSuper(Model model) {
        model.addAttribute("power", power.findAll());
        return "createsuper";
    }
    
    @PostMapping("createsuper")
    public String createNewSuper(Super toAdd) {
        superBeing.save(toAdd);
        return "allsupers";
    }
    
    @GetMapping("alllocations")
    public String displayAllLocations(Model model) {
        model.addAttribute("location", location.findAll());
        return "alllocations";
    }
    
    @GetMapping("allorganizations")
    public String displayAllOrganizations(Model model) {
        model.addAttribute("organization", organization.findAll());
        return "allorganizations";
    }
    
    @GetMapping("allpowers")
    public String displayAllPowers(Model model) {
        model.addAttribute("power", power.findAll());
        return "allpowers";
    }
    
    @GetMapping("allsightings")
    public String displayAllSightings(Model model) {
        model.addAttribute("sighting", sighting.findAll());
        return "allsightings";
    }
    
    @GetMapping("allsupers")
    public String displayAllSupers(Model model) {
        model.addAttribute("superBeing", superBeing.findAll());
        return "allsupers";
    }
    
    @GetMapping("deletelocation")
    public String deleteLocation(Location toRemove) {
        location.delete(toRemove);
        return "alllocations";
    }
    
    @GetMapping("deleteorganization")
    public String deleteLocation(Organization toRemove) {
        organization.delete(toRemove);
        return "allorganizations";
    }
    
    @GetMapping("deletepower")
    public String deleteLocation(Power toRemove) {
        power.delete(toRemove);
        return "allpowers";
    }
    
    @GetMapping("deletesighting")
    public String deleteLocation(Sighting toRemove) {
        sighting.delete(toRemove);
        return "alllocations";
    }
    
    @GetMapping("deletesuper")
    public String deleteLocation(Super toRemove) {
        superBeing.delete(toRemove);
        return "alllocations";
    }
}
