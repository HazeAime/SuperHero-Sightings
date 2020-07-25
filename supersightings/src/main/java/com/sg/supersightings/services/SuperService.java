/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.services;

import com.sg.supersightings.exceptions.InvalidEntityException;
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
import org.springframework.stereotype.Service;

/**
 *
 * @author siessejordan
 */
@Service
public class SuperService {

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
    
    public SuperService() {
    
    }
    
    public SuperService(
            SuperRepository superBeingRepo, 
            PowerRepository powerRepo, 
            OrganizationRepository organizationRepo,
            SightingRepository sightingRepo,
            LocationRepository locationRepo) {
        this.superBeingRepo = superBeingRepo;
        this.powerRepo = powerRepo;
        this.locationRepo = locationRepo;
        this.organizationRepo = organizationRepo;
        this.sightingRepo = sightingRepo;
    }

    public List<Super> findAll() {
        return superBeingRepo.findAll();
    }

    public Super getOne(Integer id) {
        return superBeingRepo.getOne(id);
    }

    public void saveNewSuper(SuperVM vm) throws InvalidEntityException {
        Super toSave = new Super(vm);
        List<Power> allPowers = new ArrayList<>();
        List<Organization> allOrgs = new ArrayList<>();

        for (Integer powerId : vm.getPowerIds()) {
            Power power = powerRepo.findById(powerId).orElse(null);
            if (power != null) {
                allPowers.add(power);
            }
        }
        for (Integer orgId : vm.getOrgIds()) {
            Organization org = organizationRepo.findById(orgId).orElse(null);
            if (org != null) {
                allOrgs.add(org);
            }
        }
        toSave.setId(vm.getSuperId());
        toSave.setAllPowers(allPowers);
        toSave.setAllOrganizations(allOrgs);
        validateSuper(toSave);
        superBeingRepo.save(toSave);
    }

    public void updateSuper(Super toEdit, SuperVM vm) throws InvalidEntityException {
        List<Power> allPowers = new ArrayList<>();
        List<Organization> allOrgs = new ArrayList<>();

        for (Integer powerId : vm.getPowerIds()) {
            Power power = powerRepo.findById(powerId).orElse(null);
            if (power != null) {
                allPowers.add(power);
            }

        }
        for (Integer orgId : vm.getOrgIds()) {
            Organization org = organizationRepo.findById(orgId).orElse(null);
            if (org != null) {
                allOrgs.add(org);
            }
        }

        toEdit.setAllPowers(allPowers);
        toEdit.setAllOrganizations(allOrgs);
        toEdit.setName(vm.getName());
        toEdit.setDescription(vm.getDescription());
        toEdit.setAllPowers(allPowers);
        toEdit.setAllOrganizations(allOrgs);
        
        validateSuper(toEdit);
        superBeingRepo.save(toEdit);
    }

    public void delete(Integer id) {
        superBeingRepo.delete(superBeingRepo.getOne(id));
    }

    public List<PowerVM> getAllPowerVMsForSuper(Super superBeing) {
        List<Power> allPowers = powerRepo.findAll();
        List<PowerVM> toReturn = new ArrayList<>();

        for (Power power : allPowers) {
            PowerVM vm = new PowerVM(power);
            if (superBeing.getAllPowers().contains(power)) {
                vm.setIsChecked(true);
                toReturn.add(vm);
            }
            
        }

        return toReturn;
    }

    public List<OrganizationVM> getAllOrgVMsForSuper(Super superBeing) {
        List<Organization> allOrgs = organizationRepo.findAll();
        List<OrganizationVM> toReturn = new ArrayList<>();

        for (Organization org : allOrgs) {
            OrganizationVM vm = new OrganizationVM(org);
            if (superBeing.getAllOrganizations().contains(org)) {
                vm.setIsChecked(true);
            }
            toReturn.add(vm);
        }

        return toReturn;
    }
    
        private void validateSuper(Super s) throws InvalidEntityException {
        if (s.getName().isEmpty()) {
            throw new InvalidEntityException("Super Name must not be blank");
        }
    }

}
