/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.services;

import com.sg.supersightings.exceptions.InvalidEntityException;
import com.sg.supersightings.models.Power;
import com.sg.supersightings.repositories.PowerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author siessejordan
 */
@Service
public class PowerService {

    @Autowired
    PowerRepository powerRepo;

    public void saveNewPower(Power power) throws InvalidEntityException {
        validatePower(power);
        powerRepo.save(power);
    }
    
    public List<Power> findAll(){
        return powerRepo.findAll();
    }
    
    public Power getOne(Integer id) {
        return powerRepo.getOne(id);
    }
    
    public void updatePower(Power power) throws InvalidEntityException {
        validatePower(power);
        powerRepo.save(power);
    }
    
    public void delete(Power power) {
        powerRepo.delete(power);
    }

    private void validatePower(Power power) throws InvalidEntityException {
        if (power.getName().isEmpty()) {
            throw new InvalidEntityException("Power must not be blank.");
        }
    }

}
