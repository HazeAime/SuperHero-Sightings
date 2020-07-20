/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.controllers;

import com.sg.supersightings.models.Super;
import com.sg.supersightings.repositories.PowerRepository;
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
public class SuperController {
    
    @Autowired
    SuperRepository superBeing;
    
    @Autowired
    PowerRepository power;
    
    @GetMapping("createsuper")
    public String createNewSuper(Model model) {
        model.addAttribute("allpowers", power.findAll());
        return "createsuper";
    }
    
    @PostMapping("createsuper")
    public String createNewSuper(Super toAdd) {
        superBeing.save(toAdd);
        return "allsupers";
    }
    
    @GetMapping("allsupers")
    public String displayAllSupers(Model model) {
        model.addAttribute("allsupers", superBeing.findAll());
        return "allsupers";
    }
    
    @GetMapping("deletesuper")
    public String deleteLocation(Super toRemove) {
        superBeing.delete(toRemove);
        return "alllocations";
    }
    
}
