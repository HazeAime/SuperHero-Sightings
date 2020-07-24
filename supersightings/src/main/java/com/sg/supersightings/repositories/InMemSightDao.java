/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.repositories;

import com.sg.supersightings.models.Sighting;
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
@Component
public class InMemSightDao implements SightingRepository {

    List<Sighting> allSightings = new ArrayList();

    public InMemSightDao() {

        Sighting test = new Sighting();
        test.setId(1);

        Sighting test2 = new Sighting();
        test2.setId(2);

        Sighting test3 = new Sighting();
        test3.setId(3);

        allSightings.add(test);
        allSightings.add(test2);
        allSightings.add(test3);
    }

    @Override
    public List<Sighting> findAll() {
        return allSightings;
    }

    @Override
    public Sighting getOne(Integer id) {
        Sighting toReturn = null;

        for (Sighting Sighting : allSightings) {
            if (Sighting.getId() == id) {
                toReturn = Sighting;
            }
        }
        return toReturn;
    }

    @Override
    public <S extends Sighting> S save(S s) {
        boolean isCreate = true;
        Optional<Sighting> originalLoc = findById(s.getId());

        if (!originalLoc.isEmpty()) {
            isCreate = false;
        }

        if (isCreate) {
            s.setId(allSightings.size() + 1);
            allSightings.add(s);

        }

        if (!isCreate) {
            Sighting toEdit = originalLoc.get();

            toEdit.setId(s.getId());
            toEdit.setSuperBeing(s.getSuperBeing());
            toEdit.setDate(s.getDate());
            toEdit.setLocation(s.getLocation());

        }
        return s;
//        check if object exists in list (findById)
//        run create or update depending on whether or not it can be found
    }

    @Override
    public Optional<Sighting> findById(Integer id) {
        Optional<Sighting> toReturn = allSightings.stream()
                .filter(l -> l.getId() == id)
                .findAny();

        return toReturn;
    }

    @Override
    public void delete(Sighting t) {
        if (allSightings.contains(t)) {
            allSightings.remove(t);
        }
    }

    @Override
    public List<Sighting> getTenMostRecentSightings() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    @Override
    public List<Sighting> getAllSightingsForSuperId(int superId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    UNUSED METHODS - NEEDED TO COMPLY WITH INTERFACE
    @Override
    public void deleteAll(Iterable<? extends Sighting> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <S extends Sighting> Optional<S> findOne(Example<S> exmpl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <S extends Sighting> Page<S> findAll(Example<S> exmpl, Pageable pgbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <S extends Sighting> long count(Example<S> exmpl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <S extends Sighting> boolean exists(Example<S> exmpl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sighting> findAll(Sort sort) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sighting> findAllById(Iterable<Integer> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <S extends Sighting> List<S> saveAll(Iterable<S> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void flush() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <S extends Sighting> S saveAndFlush(S s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteInBatch(Iterable<Sighting> itrbl) {
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
    public <S extends Sighting> List<S> findAll(Example<S> exmpl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <S extends Sighting> List<S> findAll(Example<S> exmpl, Sort sort) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Page<Sighting> findAll(Pageable pgbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAllInBatch() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
