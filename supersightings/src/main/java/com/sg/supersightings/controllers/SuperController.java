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
import com.sg.supersightings.services.OrganizationService;
import com.sg.supersightings.services.PowerService;
import com.sg.supersightings.services.SightingService;
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
public class SuperController {

    @Autowired
    SuperService superService;
    
    @Autowired
    OrganizationService orgService;
    
    @Autowired
    PowerService powerService;
    
    @Autowired
    SightingService sightingService;

    @GetMapping("/createsuper")
    public String createNewSuper(Model model) {
        model.addAttribute("allPowers", powerService.findAll());
        model.addAttribute("allOrganizations", orgService.findAll());
        model.addAttribute("isValid", true);
        return "createsuper";
    }

    @PostMapping("/createsuper")
    public String createNewSuper(Model model, SuperVM vm) {
        model.addAttribute("allPowers", powerService.findAll());

        try {
            superService.saveNewSuper(vm);
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("isValid", false);
            model.addAttribute("errorMessage", "Super already exists.");
            return "createsuper";
        } catch (NullPointerException ex) {
            model.addAttribute("isValid", false);
            model.addAttribute("errorMessage", "A Super must have at least one power.");
            return "createsuper";
        } catch (InvalidEntityException ex) {
            model.addAttribute("isValid", false);
            model.addAttribute("errorMessage", ex.getMessage());
            return "createsuper";
        }
        return "redirect:/allsupers";
    }

    @GetMapping("/allsupers")
    public String displayAllSupers(Model model) {
        model.addAttribute("allSupers", superService.findAll());
        return "allsupers";
    }

    @GetMapping("/superdetails/{id}")
    public String displayOneSuper(Model model, @PathVariable Integer id) {
        Super superBeing = superService.getOne(id);
        model.addAttribute("super", superBeing);
        model.addAttribute("allSightings", sightingService.getAllSightingsForSuperId(id));
        model.addAttribute("allPowers", superBeing.getAllPowers());
        model.addAttribute("allOrgs", superBeing.getAllOrganizations());
        return "superdetails";
    }

    @GetMapping("/updatesuper/{id}")
    public String updateSuper(Model model, @PathVariable Integer id) {
        Super superBeing = superService.getOne(id);

        List<Power> allPowers = powerService.findAll();
        List<Organization> allOrgs = orgService.findAll();

        model.addAttribute("allPowers", superService.getAllPowerVMsForSuper(superBeing));
        model.addAttribute("allOrgs", superService.getAllOrgVMsForSuper(superBeing));
        model.addAttribute("super", superBeing);
        model.addAttribute("isValid", true);

        return "updatesuper";
    }

    @PostMapping("/updatesuper")
    public String updateSuper(Model model, SuperVM vm) {
        Super superBeing = superService.getOne(vm.getSuperId());

        model.addAttribute("allPowers", superService.getAllPowerVMsForSuper(superBeing));
        model.addAttribute("allOrgs", superService.getAllOrgVMsForSuper(superBeing));
        model.addAttribute("super", superService.getOne(vm.getSuperId()));

        try {
            superService.updateSuper(superBeing, vm);
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("isValid", false);
            model.addAttribute("errorMessage", "Super already exists.");
            return "updatesuper";
        } catch (NullPointerException ex) {
            model.addAttribute("isValid", false);
            model.addAttribute("errorMessage", "A Super must have at least one power.");
            return "updatesuper";
        } catch (InvalidEntityException ex) {
            model.addAttribute("isValid", false);
            model.addAttribute("errorMessage", "Super Name must not be blank.");
            return "updatesuper";
        }
        return "redirect:/allsupers";
    }

    @GetMapping("/deletesuper/{id}")
    public String deleteSuper(@PathVariable Integer id, Model model) {
        superService.delete(id);
        return "redirect:/allsupers";
    }

}
