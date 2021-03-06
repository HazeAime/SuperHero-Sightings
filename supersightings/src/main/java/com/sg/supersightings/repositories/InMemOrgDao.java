/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.repositories;

import com.sg.supersightings.models.Organization;
import com.sg.supersightings.models.Power;
import com.sg.supersightings.models.Super;
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
public class InMemOrgDao implements OrganizationRepository {

    List<Organization> allOrganizations = new ArrayList();

    public InMemOrgDao() {

        Organization test = new Organization();
        test.setId(1);
        test.setOrgName("E.V.I.L.");
        test.setOrgDescription("evil");
        test.setAddress("404 no address");
        test.setPhone("(555)444-3333");
        
        List<Super> org1Supers = new ArrayList();
        
//        E.V.I.L.
        Super testSuper = new Super();
        testSuper.setId(4);
        testSuper.setName("Joseph Joestar");
        testSuper.setDescription("...");
        Super testSuper2 = new Super();
        testSuper2.setId(5);
        testSuper2.setName("Avdol");
        testSuper2.setDescription("Just the hands left");
        
        List<Power> testPowers = new ArrayList();
        Power testPower = new Power();
        testPower.setId(4);
        testPower.setName("Smack");
        
        testPowers.add(testPower);
        testSuper.setAllPowers(testPowers);
        testSuper2.setAllPowers(testPowers);
        
        org1Supers.add(testSuper);
        org1Supers.add(testSuper2);
        test.setAllSupers(org1Supers);

        List<Super> org2Supers = new ArrayList();
        Organization test2 = new Organization();
        test2.setId(2);
        test2.setOrgName("more evil");
        test2.setOrgDescription("more evil");
        test2.setAddress("900 no address");
        test2.setPhone("(111)444-3333");
        testSuper.setAllPowers(testPowers);
        org2Supers.add(testSuper);
        test2.setAllSupers(org2Supers);

        List<Super> org3Supers = new ArrayList();
        Organization test3 = new Organization();
        test3.setId(3);
        test3.setOrgName("gud");
        test3.setOrgDescription("git gud");
        test3.setAddress("9000 winners circle");
        test3.setPhone("(612)444-3333");
        testSuper2.setAllPowers(testPowers);
        org3Supers.add(testSuper2);
        test3.setAllSupers(org3Supers);
        
        allOrganizations.add(test);
        allOrganizations.add(test2);
        allOrganizations.add(test3);
    }

    @Override
    public List<Organization> findAll() {
        return allOrganizations;
    }

    @Override
    public Organization getOne(Integer id) {
        Organization toReturn = null;

        for (Organization Organization : allOrganizations) {
            if (Organization.getId() == id) {
                toReturn = Organization;
            }
        }
        return toReturn;
    }

    @Override
    public <S extends Organization> S save(S s) {
        boolean isCreate = true;
        Optional<Organization> originalLoc = findById(s.getId());

        if (!originalLoc.isEmpty()) {
            isCreate = false;
        }

        if (isCreate) {
            s.setId(allOrganizations.size() + 1);
            allOrganizations.add(s);

        }

        if (!isCreate) {
            Organization toEdit = originalLoc.get();


                toEdit.setId(s.getId());
                toEdit.setOrgName(s.getOrgName());
                toEdit.setOrgDescription(s.getOrgDescription());
                toEdit.setAddress(s.getAddress());
                toEdit.setPhone(s.getPhone());
                toEdit.setAllSupers(s.getAllSupers());
            
        }
        return s;
//        check if object exists in list (findById)
//        run create or update depending on whether or not it can be found
    }

    @Override
    public Optional<Organization> findById(Integer id) {
        Optional<Organization> toReturn = allOrganizations.stream()
                .filter(l -> l.getId() == id)
                .findAny();

        return toReturn;
    }

    @Override
    public void delete(Organization t) {
        if (allOrganizations.contains(t)) {
            allOrganizations.remove(t);
        }
    }

//    UNUSED METHODS - NEEDED TO COMPLY WITH INTERFACE
    @Override
    public void deleteAll(Iterable<? extends Organization> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <S extends Organization> Optional<S> findOne(Example<S> exmpl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <S extends Organization> Page<S> findAll(Example<S> exmpl, Pageable pgbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <S extends Organization> long count(Example<S> exmpl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <S extends Organization> boolean exists(Example<S> exmpl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Organization> findAll(Sort sort) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Organization> findAllById(Iterable<Integer> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <S extends Organization> List<S> saveAll(Iterable<S> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void flush() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <S extends Organization> S saveAndFlush(S s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteInBatch(Iterable<Organization> itrbl) {
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
    public <S extends Organization> List<S> findAll(Example<S> exmpl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <S extends Organization> List<S> findAll(Example<S> exmpl, Sort sort) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Page<Organization> findAll(Pageable pgbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAllInBatch() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
