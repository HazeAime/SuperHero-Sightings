/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.controllers;

import com.sg.supersightings.models.Super;
import com.sg.supersightings.repositories.OrganizationRepository;
import com.sg.supersightings.repositories.PowerRepository;
import com.sg.supersightings.repositories.SuperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    SuperRepository superBeingRepo;
    
    @Autowired
    PowerRepository powerRepo;
    
    @Autowired
    OrganizationRepository organizationRepo;
    
    @GetMapping("createsuper")
    public String createNewSuper(Model model) {
        model.addAttribute("allPowers", powerRepo.findAll());
        model.addAttribute("allOrganizations", organizationRepo.findAll());
        return "createsuper";
    }
    
    @PostMapping("createsuper")
    public String createNewSuper(Model model, Super toAdd) {
        try {
            superBeingRepo.save(toAdd);
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("isValid", false);
                model.addAttribute("errorMessage", "Super already exists.");
                return "createsuper";
        }
        return "redirect:/allsupers";
    }
    
    @GetMapping("allsupers")
    public String displayAllSupers(Model model) {
        model.addAttribute("allSupers", superBeingRepo.findAll());
        return "allsupers";
    }
    
    @GetMapping("deletesuper")
    public String deleteLocation(Super toRemove) {
        superBeingRepo.delete(toRemove);
        return "alllocations";
    }
    
}
