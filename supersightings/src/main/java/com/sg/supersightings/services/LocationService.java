/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.services;

import com.sg.supersightings.exceptions.InvalidEntityException;
import com.sg.supersightings.models.Location;
import com.sg.supersightings.repositories.LocationRepository;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author siessejordan
 */
@Service
public class LocationService {

    @Autowired
    LocationRepository locationRepo;

    public List<Location> findAll() {
        return locationRepo.findAll();
    }

    public void saveNewLocation(Location toAdd) throws InvalidEntityException {
        validateLocation(toAdd);
        locationRepo.save(toAdd);
    }

    public Location getOne(Integer id) {
        return locationRepo.getOne(id);
    }

    public void delete(Integer id) {
        locationRepo.delete(locationRepo.getOne(id));
    }

    public void updateLocation(Location toEdit) throws InvalidEntityException {
        validateLocation(toEdit);
        locationRepo.save(toEdit);
    }
    
    private void validateLocation(Location location) throws InvalidEntityException {
        if (location.getLocationName().isEmpty()) {
            throw new InvalidEntityException("Location Name must not be blank.");
        }
        if (location.getLatitude().isEmpty()) {
            throw new InvalidEntityException("Location Latitude must not be blank.");
        }
        if (location.getLongitude().isEmpty()) {
            throw new InvalidEntityException("Location Longitude must not be blank.");
        }
        BigDecimal latitude = new BigDecimal(location.getLatitude());
        BigDecimal longitude = new BigDecimal(location.getLongitude());

        if (latitude.compareTo(new BigDecimal("-90")) == -1 || latitude.compareTo(new BigDecimal("90")) == 1) {
            throw new InvalidEntityException("Latitude must be between -90 and 90.");
        }
        if (longitude.compareTo(new BigDecimal("-180")) == -1 || longitude.compareTo(new BigDecimal("180")) == 1) {
            throw new InvalidEntityException("Longitude must be between -180 and 180.");
        }
    }
}
