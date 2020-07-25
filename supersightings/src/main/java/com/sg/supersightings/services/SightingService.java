/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.services;

import com.sg.supersightings.exceptions.InvalidEntityException;
import com.sg.supersightings.models.Sighting;
import com.sg.supersightings.models.SightingVM;
import com.sg.supersightings.repositories.LocationRepository;
import com.sg.supersightings.repositories.SightingRepository;
import com.sg.supersightings.repositories.SuperRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author siessejordan
 */
@Service
public class SightingService {

    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Autowired
    SightingRepository sightingRepo;

    @Autowired
    SuperRepository superBeingRepo;

    @Autowired
    LocationRepository locationRepo;

    public SightingService() {}
    
    public SightingService(SightingRepository sightingRepo, SuperRepository superRepo, LocationRepository locationRepo) {
        this.locationRepo = locationRepo;
        this.sightingRepo = sightingRepo;
        this.superBeingRepo = superRepo;
    }
    
    public Sighting getOne(Integer id) {
        return sightingRepo.getOne(id);
    }
    
    public List<Sighting> findAll() {
        return sightingRepo.findAll();
    }

    public void saveNewSighting(SightingVM vm) throws InvalidEntityException {
        validateSightingVM(vm);

        LocalDate date = LocalDate.parse(vm.getDate());
        Sighting sighting = new Sighting();
        sighting.setLocation(locationRepo.getOne(vm.getLocationId()));
        sighting.setSuperBeing(superBeingRepo.getOne(vm.getSuperId()));
        sighting.setDate(date);

        validateSighting(sighting);
        sightingRepo.save(sighting);
    }

    public void updateSighting(Integer id, SightingVM vm) throws InvalidEntityException {
        validateSightingVM(vm);

        LocalDate date = LocalDate.parse(vm.getDate());
        Sighting sighting = sightingRepo.findById(id).orElse(new Sighting());
        sighting.setLocation(locationRepo.getOne(vm.getLocationId()));
        sighting.setSuperBeing(superBeingRepo.getOne(vm.getSuperId()));
        sighting.setDate(date);
        
        validateSighting(sighting);
        sightingRepo.save(sighting);
    }

    private void validateSighting(Sighting sighting) throws InvalidEntityException {
        Set<ConstraintViolation<Sighting>> errors = validator.validate(sighting);

        if (errors.size() > 0) {
            throw new InvalidEntityException("A Sighting must have a valid date.");
        }
    }

    private void validateSightingVM(SightingVM sighting) throws InvalidEntityException {
        if (sighting.getSuperId() == 0) {
            throw new InvalidEntityException("A Super must be selected.");
        }
        if (sighting.getLocationId() == 0) {
            throw new InvalidEntityException("A Location must be selected.");
        }
        if (sighting.getDate().isEmpty()) {
            throw new InvalidEntityException("A Sighting Date must not be blank.");
        }
    }

    public void delete(Integer id) {
        sightingRepo.delete(sightingRepo.getOne(id));
    }
    
    public List<Sighting> getAllSightingsForSuperId(Integer id) {
        return sightingRepo.getAllSightingsForSuperId(id);
    }
    
    public List<Sighting> getTenMostRecentSightings() {
        return sightingRepo.getTenMostRecentSightings();
    }

}
