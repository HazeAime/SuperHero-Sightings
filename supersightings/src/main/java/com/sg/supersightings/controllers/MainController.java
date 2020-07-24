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
import com.sg.supersightings.services.SightingService;
import java.util.List;
import java.util.Set;
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
    SightingService sightingService;
    
    @GetMapping("/")
    public String displayHomepage(Model model) {   
        List<Sighting> recentSightings = sightingService.getTenMostRecentSightings();
        model.addAttribute("recentSightings", recentSightings);
        return "home";
    }
    
}
