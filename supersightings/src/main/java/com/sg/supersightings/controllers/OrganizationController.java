/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.controllers;

import com.sg.supersightings.models.Organization;
import com.sg.supersightings.models.OrganizationVM;
import com.sg.supersightings.models.Power;
import com.sg.supersightings.models.PowerVM;
import com.sg.supersightings.models.Super;
import com.sg.supersightings.models.SuperVM;
import com.sg.supersightings.repositories.OrganizationRepository;
import com.sg.supersightings.repositories.SuperRepository;
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
    OrganizationRepository organizationRepo;

    @Autowired
    SuperRepository superBeingRepo;

    @GetMapping("/createorganization")
    public String createNewOrg(Model model) {
        model.addAttribute("allSupers", superBeingRepo.findAll());
        model.addAttribute("isValid", true);
        return "createorganization";
    }

    @PostMapping("/createorganization")
    public String createNewOrg(Model model, OrganizationVM vm) {
        model.addAttribute("allSupers", superBeingRepo.findAll());
        
        Organization toAdd = new Organization(vm);
        List<Super> orgSupers = new ArrayList<>();
        for(Integer superId : vm.getSuperIds()) {
            orgSupers.add(superBeingRepo.getOne(superId));
        }
        toAdd.setAllSupers(orgSupers);
        
        if (toAdd.getOrgName().isEmpty()) {
            model.addAttribute("isValid", false);
            model.addAttribute("errorMessage", "An Organization must have a name.");
            return "createorganization";
        }
        
        try {
            organizationRepo.save(toAdd);
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("isValid", false);
                model.addAttribute("errorMessage", "Organization already exists.");
                return "createorganization";
        }
        return "redirect:/allorganizations";
    }
    
    @GetMapping("/updateorganization/{id}")
    public String updateOrganization(Model model, @PathVariable Integer id) {
        Organization org = organizationRepo.getOne(id);
        model.addAttribute("org", org);
        model.addAttribute("allSupers", getAllSuperVMsForOrg(org));
        model.addAttribute("isValid", true);
        return "/updateorganization";
    }
    
    @PostMapping("/updateorganization/{id}")
    public String updateOrganization(Model model, OrganizationVM vm, @PathVariable Integer id) {
        Organization org = organizationRepo.getOne(id);
        model.addAttribute("org", org);
        model.addAttribute("allSupers", getAllSuperVMsForOrg(org));
        
        if (vm.getOrgName().isEmpty()) {
                model.addAttribute("isValid", false);
                model.addAttribute("errorMessage", "An Organization must have a name.");
                return "updateorganization";
            }
        org.setOrgName(vm.getOrgName());
        org.setOrgDescription(vm.getOrgDescription());
        org.setAddress(vm.getAddress());
        org.setPhone(vm.getPhone());
        
        List<Super> orgSupers = new ArrayList();
        for(Integer superId : vm.getSuperIds()) {
            orgSupers.add(superBeingRepo.getOne(superId));
        }
        org.setAllSupers(orgSupers);
        try {
            organizationRepo.save(org);
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("isValid", false);
            model.addAttribute("errorMessage", "Organization already exists.");
            return "updateorganization";
        }
        
        return "redirect:/allorganizations";
    }

    @GetMapping("/allorganizations")
    public String displayAllOrganizations(Model model) {
        model.addAttribute("allOrganizations", organizationRepo.findAll());
        return "allorganizations";
    }

    @GetMapping("/deleteorganization/{id}")
    public String deleteOrganization(@PathVariable Integer id) {
        Organization toDelete = organizationRepo.getOne(id);
        organizationRepo.delete(toDelete);
        return "redirect:/allorganizations";
    }

    private Object getAllSuperVMsForOrg(Organization org) {
        List<Super> allSupers = superBeingRepo.findAll();
        List<SuperVM> toReturn = new ArrayList<>();
        
        for(Super superBeing : allSupers) {
            SuperVM vm = new SuperVM(superBeing);
            if (org.getAllSupers().contains(superBeing)) {
                vm.setIsChecked(true);
            }
            toReturn.add(vm);
        }
        
        return toReturn;
    }

}
