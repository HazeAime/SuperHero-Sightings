/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.controllers;

import com.sg.supersightings.models.Organization;
import com.sg.supersightings.models.Super;
import com.sg.supersightings.repositories.OrganizationRepository;
import com.sg.supersightings.repositories.SuperRepository;
import java.util.List;
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
public class OrganizationController {

    @Autowired
    OrganizationRepository organizationRepo;

    @Autowired
    SuperRepository superBeingRepo;

    @GetMapping("createorganization")
    public String createNewOrg(Model model) {
        model.addAttribute("allSupers", superBeingRepo.findAll());
        return "createorganization";
    }

    @PostMapping("createorganization")
    public String createNewOrg(Model model, Organization toAdd) {
        model.addAttribute("allSupers", superBeingRepo.findAll());
        try {
            organizationRepo.save(toAdd);
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("isValid", false);
                model.addAttribute("errorMessage", "Organization already exists.");
                return "createorganization";
        }
        return "redirect:/allorganizations";
    }

    @GetMapping("allorganizations")
    public String displayAllOrganizations(Model model) {
        model.addAttribute("allOrganizations", organizationRepo.findAll());
        return "allorganizations";
    }

    @GetMapping("deleteorganization")
    public String deleteLocation(Organization toRemove) {
        organizationRepo.delete(toRemove);
        return "allorganizations";
    }

}
