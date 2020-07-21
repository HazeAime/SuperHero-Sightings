/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.controllers;

import com.sg.supersightings.models.Power;
import com.sg.supersightings.repositories.PowerRepository;
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
public class PowerController {
    
    @Autowired
    PowerRepository powerRepo;
    
    @GetMapping("createpower")
    public String createNewPower(){
        return "createpower";
    }
    
    @PostMapping("createpower")
    public String createNewPower(Power toAdd) {
        powerRepo.save(toAdd);
        return "allpowers";
    }
    
    @GetMapping("allpowers")
    public String displayAllPowers(Model model) {
        model.addAttribute("allPowers", powerRepo.findAll());
        return "allpowers";
    }
    
    @GetMapping("deletepower")
    public String deleteLocation(Power toRemove) {
        powerRepo.delete(toRemove);
        return "allpowers";
    }
}
