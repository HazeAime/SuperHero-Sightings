/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.controllers;

import com.sg.supersightings.exceptions.InvalidEntityException;
import com.sg.supersightings.models.Organization;
import com.sg.supersightings.models.OrganizationVM;
import com.sg.supersightings.models.Power;
import com.sg.supersightings.models.PowerVM;
import com.sg.supersightings.models.Super;
import com.sg.supersightings.models.SuperVM;
import com.sg.supersightings.repositories.OrganizationRepository;
import com.sg.supersightings.repositories.SuperRepository;
import com.sg.supersightings.services.OrganizationService;
import com.sg.supersightings.services.SuperService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
public class OrganizationController {

    @Autowired
    SuperService superService;

    @Autowired
    OrganizationService orgService;

    @GetMapping("/createorganization")
    public String createNewOrg(Model model) {
        model.addAttribute("allSupers", superService.findAll());
        model.addAttribute("isValid", true);
        return "createorganization";
    }

    @PostMapping("/createorganization")
    public String createNewOrg(Model model, OrganizationVM vm) {
        model.addAttribute("allSupers", superService.findAll());
        try {
            orgService.saveNewOrg(vm);
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("isValid", false);
            model.addAttribute("errorMessage", "Organization already exists.");
            return "createorganization";
        } catch (InvalidEntityException ex) {
            model.addAttribute("isValid", false);
            model.addAttribute("errorMessage", ex.getMessage());
            return "createorganization";
        }
        return "redirect:/allorganizations";
    }

    @GetMapping("/updateorganization/{id}")
    public String updateOrganization(Model model, @PathVariable Integer id) {
        Organization org = orgService.getOne(id);
        model.addAttribute("org", org);
        model.addAttribute("allSupers", orgService.getAllSuperVMsForOrg(org));
        model.addAttribute("isValid", true);
        return "/updateorganization";
    }

    @PostMapping("/updateorganization/{id}")
    public String updateOrganization(Model model, OrganizationVM vm, @PathVariable Integer id) {
        Organization org = orgService.getOne(id);
        model.addAttribute("org", org);
        model.addAttribute("allSupers", orgService.getAllSuperVMsForOrg(org));

        try {
            orgService.updateOrg(org, vm);
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("isValid", false);
            model.addAttribute("errorMessage", "Organization already exists.");
            return "updateorganization";
        } catch (InvalidEntityException ex) {
            model.addAttribute("isValid", false);
            model.addAttribute("errorMessage", ex.getMessage());
            return "updateorganization";
        }

        return "redirect:/allorganizations";
    }

    @GetMapping("/allorganizations")
    public String displayAllOrganizations(Model model) {
        model.addAttribute("allOrganizations", orgService.findAll());
        return "allorganizations";
    }

    @GetMapping("/deleteorganization/{id}")
    public String deleteOrganization(@PathVariable Integer id) {
        orgService.delete(id);
        return "redirect:/allorganizations";
    }

}
