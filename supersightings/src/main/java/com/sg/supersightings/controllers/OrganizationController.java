/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.controllers;

import com.sg.supersightings.models.Organization;
import com.sg.supersightings.repositories.OrganizationRepository;
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
public class OrganizationController {
    
    @Autowired
    OrganizationRepository organization;
    
    @GetMapping("createorganization")
    public String createNewOrg() {
        return "createorganization";
    }
    
    @PostMapping("createorganization")
    public String createNewOrg(Organization toAdd) {
        System.out.println(toAdd.toString());
        organization.save(toAdd);
        return "redirect:/allorganizations";
    }
    
    @GetMapping("allorganizations")
    public String displayAllOrganizations(Model model) {
        model.addAttribute("allorganizations", organization.findAll());
        return "allorganizations";
    }
    
    @GetMapping("deleteorganization")
    public String deleteLocation(Organization toRemove) {
        organization.delete(toRemove);
        return "allorganizations";
    }
    
}
