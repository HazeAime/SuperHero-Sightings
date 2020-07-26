/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.repositories;

import com.sg.supersightings.models.Location;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

/**
 *
 * @author siessejordan
 */
public class InMemLocDao implements LocationRepository {

    List<Location> allLocations = new ArrayList();

    public InMemLocDao() {

        Location test = new Location();
        test.setId(1);
        test.setLocationName("Flavor Town");
        test.setDescription("Your next destination.");
        test.setAddress("9800 Umami Way");
        test.setLatitude(new BigDecimal("80.1234"));
        test.setLongitude(new BigDecimal("60.9876"));

        Location test2 = new Location();
        test2.setId(2);
        test2.setLocationName("The Krusty Krab");
        test2.setDescription("My name is Mr. Krabs and I like money.");
        test2.setAddress("1000 Rock Bottom Heights");
        test2.setLatitude(new BigDecimal("-60.0101"));
        test2.setLongitude(new BigDecimal("-160.2222"));

        Location test3 = new Location();
        test3.setId(3);
        test3.setLocationName("Hyrule");
        test3.setDescription("Your princess is in another castle lol.");
        test3.setAddress("404 maybe in the hills?");
        test3.setLatitude(new BigDecimal("30.404"));
        test3.setLongitude(new BigDecimal("30.202"));

        allLocations.add(test);
        allLocations.add(test2);
        allLocations.add(test3);
    }

    @Override
    public List<Location> findAll() {
        return allLocations;
    }

    @Override
    public Location getOne(Integer id) {
        Location toReturn = null;

        for (Location location : allLocations) {
            if (location.getId() == id) {
                toReturn = location;
            }
        }
        return toReturn;
    }

    @Override
    public <S extends Location> S save(S s) {
        boolean isCreate = true;
        Optional<Location> originalLoc = findById(s.getId());

        if (!originalLoc.isEmpty()) {
            isCreate = false;
        }

        if (isCreate) {
            s.setId(allLocations.size() + 1);
            allLocations.add(s);

        }

        if (!isCreate) {
            Location toEdit = originalLoc.get();


                toEdit.setId(s.getId());
                toEdit.setLocationName(s.getLocationName());
                toEdit.setDescription(s.getDescription());
                toEdit.setAddress(s.getAddress());
                toEdit.setLatitude(s.getLatitude());
                toEdit.setLongitude(s.getLongitude());
            
        }
        return s;
//        check if object exists in list (findById)
//        run create or update depending on whether or not it can be found
    }

    @Override
    public Optional<Location> findById(Integer id) {
        Optional<Location> toReturn = allLocations.stream()
                .filter(l -> l.getId() == id)
                .findAny();

        return toReturn;
    }

    @Override
    public void delete(Location t) {
        if (allLocations.contains(t)) {
            allLocations.remove(t);
        }
    }

//    UNUSED METHODS - NEEDED TO COMPLY WITH INTERFACE
    @Override
    public void deleteAll(Iterable<? extends Location> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <S extends Location> Optional<S> findOne(Example<S> exmpl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <S extends Location> Page<S> findAll(Example<S> exmpl, Pageable pgbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <S extends Location> long count(Example<S> exmpl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <S extends Location> boolean exists(Example<S> exmpl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Location> findAll(Sort sort) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Location> findAllById(Iterable<Integer> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <S extends Location> List<S> saveAll(Iterable<S> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void flush() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <S extends Location> S saveAndFlush(S s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteInBatch(Iterable<Location> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existsById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <S extends Location> List<S> findAll(Example<S> exmpl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <S extends Location> List<S> findAll(Example<S> exmpl, Sort sort) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Page<Location> findAll(Pageable pgbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAllInBatch() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
