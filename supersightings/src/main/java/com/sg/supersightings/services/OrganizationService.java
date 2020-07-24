/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.services;

import com.sg.supersightings.exceptions.InvalidEntityException;
import com.sg.supersightings.models.Organization;
import com.sg.supersightings.models.OrganizationVM;
import com.sg.supersightings.models.Super;
import com.sg.supersightings.models.SuperVM;
import com.sg.supersightings.repositories.OrganizationRepository;
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
public class OrganizationService {

    @Autowired
    OrganizationRepository organizationRepo;

    @Autowired
    SuperRepository superBeingRepo;

    public Organization getOne(Integer id) {
        return organizationRepo.getOne(id);
    }
    
    public List<Organization> findAll() {
        return organizationRepo.findAll();
    }

    public void saveNewOrg(OrganizationVM vm) throws InvalidEntityException {
        Organization toAdd = new Organization(vm);
        List<Super> orgSupers = new ArrayList<>();

        for (Integer superId : vm.getSuperIds()) {
            orgSupers.add(superBeingRepo.getOne(superId));
        }

        toAdd.setAllSupers(orgSupers);

        validateOrg(toAdd);
        organizationRepo.save(toAdd);
    }
    
    public void updateOrg(Organization org, OrganizationVM vm) throws InvalidEntityException {        
        org.setOrgName(vm.getOrgName());
        org.setOrgDescription(vm.getOrgDescription());
        org.setAddress(vm.getAddress());
        org.setPhone(vm.getPhone());
        
        List<Super> orgSupers = new ArrayList();
        for(Integer superId : vm.getSuperIds()) {
            orgSupers.add(superBeingRepo.getOne(superId));
        }
        org.setAllSupers(orgSupers);
        
        validateOrg(org);
        organizationRepo.save(org);
    }

    public List<SuperVM> getAllSuperVMsForOrg(Organization org) {
        List<Super> allSupers = superBeingRepo.findAll();
        List<SuperVM> toReturn = new ArrayList<>();

        for (Super superBeing : allSupers) {
            SuperVM vm = new SuperVM(superBeing);
            if (org.getAllSupers().contains(superBeing)) {
                vm.setIsChecked(true);
            }
            toReturn.add(vm);
        }

        return toReturn;
    }

    private void validateOrg(Organization org) throws InvalidEntityException {
        if (org.getOrgName().isEmpty()) {
            throw new InvalidEntityException("An Organization must have a name.");
        }
    }

    public void delete(Integer id) {
        organizationRepo.delete(organizationRepo.getOne(id));
    }
}
