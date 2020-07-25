/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.repositories;

import com.sg.supersightings.models.Location;
import com.sg.supersightings.models.Sighting;
import com.sg.supersightings.models.Super;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

        Super superOne = new Super();
        superOne.setId(1);
        superOne.setName("Star Platinum");
        Location locationOne = new Location();
        locationOne.setId(1);
        locationOne.setLocationName("Chicago");

        Super superTwo = new Super();
        superTwo.setId(2);
        superTwo.setName("Joseph Joestar");
        Location locationTwo = new Location();
        locationOne.setId(2);
        locationOne.setLocationName("New York");

        Sighting test = new Sighting();
        test.setId(1);
        test.setDate(LocalDate.parse("2020-01-01"));
        test.setSuperBeing(superOne);
        test.setLocation(locationOne);

        Sighting test2 = new Sighting();
        test2.setId(2);
        test2.setDate(LocalDate.parse("2020-01-02"));
        test2.setSuperBeing(superTwo);
        test2.setLocation(locationTwo);

        Sighting test3 = new Sighting();
        test3.setId(3);
        test3.setDate(LocalDate.parse("2020-01-03"));
        test3.setSuperBeing(superOne);
        test3.setLocation(locationTwo);

        Sighting test4 = new Sighting();
        test3.setId(4);
        test3.setDate(LocalDate.parse("2020-01-04"));
        test3.setSuperBeing(superOne);
        test3.setLocation(locationTwo);

        Sighting test5 = new Sighting();
        test3.setId(5);
        test3.setDate(LocalDate.parse("2020-01-05"));
        test3.setSuperBeing(superOne);
        test3.setLocation(locationTwo);

        Sighting test6 = new Sighting();
        test3.setId(6);
        test3.setDate(LocalDate.parse("2020-01-06"));
        test3.setSuperBeing(superOne);
        test3.setLocation(locationTwo);

        Sighting test7 = new Sighting();
        test3.setId(7);
        test3.setDate(LocalDate.parse("2020-01-07"));
        test3.setSuperBeing(superOne);
        test3.setLocation(locationTwo);

        Sighting test8 = new Sighting();
        test3.setId(8);
        test3.setDate(LocalDate.parse("2020-01-08"));
        test3.setSuperBeing(superOne);
        test3.setLocation(locationTwo);

        Sighting test9 = new Sighting();
        test3.setId(9);
        test3.setDate(LocalDate.parse("2020-01-09"));
        test3.setSuperBeing(superOne);
        test3.setLocation(locationTwo);

        Sighting test10 = new Sighting();
        test3.setId(10);
        test3.setDate(LocalDate.parse("2020-01-10"));
        test3.setSuperBeing(superOne);
        test3.setLocation(locationTwo);

        Sighting test11 = new Sighting();
        test3.setId(11);
        test3.setDate(LocalDate.parse("2020-01-11"));
        test3.setSuperBeing(superOne);
        test3.setLocation(locationTwo);

        Sighting test12 = new Sighting();
        test3.setId(12);
        test3.setDate(LocalDate.parse("2020-01-12"));
        test3.setSuperBeing(superOne);
        test3.setLocation(locationTwo);

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
        List<Sighting> toReturn = new ArrayList(); // Make empty list

        Collections.copy(toReturn, allSightings); // Copy values from allSightings into toReturn

        Collections.sort(toReturn, (Sighting s1, Sighting s2) -> s1.getDate().compareTo(s2.getDate())); // Sort toReturn

        Integer maxIndex = toReturn.size() - 1;
        
        if(maxIndex > 10){
            maxIndex = 10;
        }
        
        return toReturn.subList(0, maxIndex);
    }

    @Override
    public List<Sighting> getAllSightingsForSuperId(int superId) {
        return allSightings.stream().filter(s -> s.getSuperBeing().getId() == superId).collect(Collectors.toList());
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
