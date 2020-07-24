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
import com.sg.supersightings.repositories.LocationRepository;
import com.sg.supersightings.repositories.OrganizationRepository;
import com.sg.supersightings.repositories.PowerRepository;
import com.sg.supersightings.repositories.SightingRepository;
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
public class SuperController {

    @Autowired
    SuperRepository superBeingRepo;

    @Autowired
    PowerRepository powerRepo;

    @Autowired
    OrganizationRepository organizationRepo;

    @Autowired
    SightingRepository sightingRepo;

    @Autowired
    LocationRepository locationRepo;

    @GetMapping("/createsuper")
    public String createNewSuper(Model model) {
        model.addAttribute("allPowers", powerRepo.findAll());
        model.addAttribute("allOrganizations", organizationRepo.findAll());
        model.addAttribute("isValid", true);
        return "createsuper";
    }

    @PostMapping("/createsuper")
    public String createNewSuper(Model model, SuperVM vm) {
        Super toAdd = new Super(vm);
        List<Power> allPowers = new ArrayList<>();
        model.addAttribute("allPowers", powerRepo.findAll());

        try {
            for (Integer powerId : vm.getPowerIds()) {
                Power power = powerRepo.findById(powerId).orElse(null);
                if (power != null) {
                    allPowers.add(power);
                }
            }
            toAdd.setAllPowers(allPowers);
            if (toAdd.getName().isEmpty()) {
                model.addAttribute("isValid", false);
                model.addAttribute("errorMessage", "Super Name must not be blank.");
                return "createsuper";
            }
            superBeingRepo.save(toAdd);
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("isValid", false);
            model.addAttribute("errorMessage", "Super already exists.");
            return "createsuper";
        } catch (NullPointerException ex) {
            model.addAttribute("isValid", false);
            model.addAttribute("errorMessage", "A Super must have at least one power.");
            return "createsuper";
        }
        return "redirect:/allsupers";
    }

    @GetMapping("/allsupers")
    public String displayAllSupers(Model model) {
        model.addAttribute("allSupers", superBeingRepo.findAll());
        return "allsupers";
    }

    @GetMapping("/superdetails/{id}")
    public String displayOneSuper(Model model, @PathVariable Integer id) {
        Super superBeing = superBeingRepo.getOne(id);
        model.addAttribute("super", superBeing);
        model.addAttribute("allSightings", sightingRepo.getAllSightingsForSuperId(id));
        model.addAttribute("allPowers", superBeing.getAllPowers());
        model.addAttribute("allOrgs", superBeing.getAllOrganizations());
        return "superdetails";
    }

    @GetMapping("/updatesuper/{id}")
    public String updateSuper(Model model, @PathVariable Integer id) {
        Super superBeing = superBeingRepo.getOne(id);
        
        List<Power> allPowers = powerRepo.findAll();
        List<Organization> allOrgs = organizationRepo.findAll();
        
        model.addAttribute("allPowers", getAllPowerVMsForSuper(superBeing));
        model.addAttribute("allOrgs", getAllOrgVMsForSuper(superBeing));
        model.addAttribute("super", superBeing);
        model.addAttribute("isValid", true);

        return "updatesuper";
    }

    @PostMapping("/updatesuper")
    public String updateSuper(Model model, SuperVM vm) {
        Super toEdit = superBeingRepo.getOne(vm.getSuperId());
        
        List<Power> allPowers = new ArrayList<>();
        List<Organization> allOrgs = new ArrayList<>();
        
        model.addAttribute("allPowers", getAllPowerVMsForSuper(toEdit));
        model.addAttribute("allOrgs", getAllOrgVMsForSuper(toEdit));
        model.addAttribute("super", superBeingRepo.getOne(vm.getSuperId()));

        try {
            for (Integer powerId : vm.getPowerIds()) {
                Power power = powerRepo.findById(powerId).orElse(null);
                if (power != null) {
                    allPowers.add(power);
                }
                
            }
            for(Integer orgId : vm.getOrgIds()) {
                Organization org = organizationRepo.findById(orgId).orElse(null);
                if(org != null) {
                    allOrgs.add(org);
                }
            }
            
            toEdit.setAllPowers(allPowers);
            toEdit.setAllOrganizations(allOrgs);
            
            if (vm.getName().isEmpty()) {
                model.addAttribute("isValid", false);
                model.addAttribute("errorMessage", "Super Name must not be blank.");
                return "updatesuper";
            }
            
            toEdit.setName(vm.getName());
            toEdit.setDescription(vm.getDescription());
            toEdit.setAllPowers(allPowers);
            toEdit.setAllOrganizations(allOrgs);
            superBeingRepo.save(toEdit);
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("isValid", false);
            model.addAttribute("errorMessage", "Super already exists.");
            return "updatesuper";
        } catch (NullPointerException ex) {
            model.addAttribute("isValid", false);
            model.addAttribute("errorMessage", "A Super must have at least one power.");
            return "updatesuper";
        }
        return "redirect:/allsupers";
    }

    @GetMapping("/deletesuper/{id}")
    public String deleteSuper(Super toRemove, Model model) {
        superBeingRepo.delete(toRemove);
        return "redirect:/allsupers";
    }
    
    private List<PowerVM> getAllPowerVMsForSuper(Super superBeing) {
        List<Power> allPowers = powerRepo.findAll();
        List<PowerVM> toReturn = new ArrayList<>();
        
        for(Power power : allPowers) {
            PowerVM vm = new PowerVM(power);
            if (superBeing.getAllPowers().contains(power)) {
                vm.setIsChecked(true);
            }
            toReturn.add(vm);
        }
        
        return toReturn;
    }
    
    private List<OrganizationVM> getAllOrgVMsForSuper(Super superBeing) {
        List<Organization> allOrgs = organizationRepo.findAll();
        List<OrganizationVM> toReturn = new ArrayList<>();
        
        for(Organization org : allOrgs) {
            OrganizationVM vm = new OrganizationVM(org);
            if (superBeing.getAllOrganizations().contains(org)) {
                vm.setIsChecked(true);
            }
            toReturn.add(vm);
        }
        
        return toReturn;
    }
}
