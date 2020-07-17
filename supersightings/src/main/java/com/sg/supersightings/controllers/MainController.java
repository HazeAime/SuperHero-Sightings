/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.controllers;

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

/**
 *
 * @author siessejordan
 */
@Controller
public class MainController {
    
    @Autowired
    LocationRepository location;
    
    @Autowired
    OrganizationRepository org;
    
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
    
    @GetMapping("createnewsuper")
    public String createNewSuper(Model model) {
        model.addAttribute("power", power.findAll());
        return "createnewuser";
    }
    
}
