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
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

/**
 *
 * @author siessejordan
 */
public class InMemSupDao implements SuperRepository {

    List<Super> allSupers = new ArrayList();

    public InMemSupDao() {

        Super test = new Super();
        test.setId(1);
        test.setName("Star Platinum");
        test.setDescription("*Opens can of whoop ass.*");
        
        List<Power> superPowers1 = new ArrayList();
        
        Power power1 = new Power();
        power1.setId(1);
        power1.setName("ORAORAORAH");
        
        Power power2 = new Power();
        power2.setId(2);
        power2.setName("ZA-WORLD-OHH");
        
        superPowers1.add(power1);
        superPowers1.add(power2);
        test.setAllPowers(superPowers1);
        
        List<Organization> superOrgs1 = new ArrayList();
        
        Organization org1 = new Organization();
        org1.setId(1);
        org1.setOrgName("Jojo's Crew");
        org1.setOrgDescription("The first crew.");
        org1.setAddress("404 No address way");
        org1.setPhone("(612)222-2222");
        
        Organization org2 = new Organization();
        org2.setId(2);
        org2.setOrgName("Crew2");
        org2.setOrgDescription("The second crew.");
        org2.setAddress("808 SUB PATH");
        org2.setPhone("(190)888-0000");
        
        superOrgs1.add(org1);
        superOrgs1.add(org2);
        test.setAllOrganizations(superOrgs1);
        

        Super test2 = new Super();
        test2.setId(2);
        test2.setName("Kirito");
        test2.setDescription("Protagonist of SAO");
        
        List<Power> superPowers2 = new ArrayList();
        superPowers2.add(power1);
        superPowers2.add(power2);
        test2.setAllPowers(superPowers2);
        
        List<Organization> superOrgs2 = new ArrayList();
        superOrgs2.add(org2);
        test2.setAllOrganizations(superOrgs2);

        Super test3 = new Super();
        test3.setId(3);
        test3.setName("Dat Boi");
        test3.setDescription("It Me");
        
        List<Power> superPowers3 = new ArrayList();
        superPowers3.add(power1);
        test3.setAllPowers(superPowers3);
        
        List<Organization> superOrgs3 = new ArrayList();
        superOrgs3.add(org1);
        test3.setAllOrganizations(superOrgs3);

        allSupers.add(test);
        allSupers.add(test2);
        allSupers.add(test3);
    }

    @Override
    public List<Super> findAll() {
        return allSupers;
    }

    @Override
    public Super getOne(Integer id) {
        Super toReturn = null;

        for (Super superBeing : allSupers) {
            if (superBeing.getId() == id) {
                toReturn = superBeing;
            }
        }
        return toReturn;
    }

    @Override
    public <S extends Super> S save(S s) {
        boolean isCreate = true;
        Optional<Super> originalSup = findById(s.getId());

        if (!originalSup.isEmpty()) {
            isCreate = false;
        }

        if (isCreate) {
            s.setId(allSupers.size() + 1);
            allSupers.add(s);

        }

        if (!isCreate) {
            Super toEdit = originalSup.get();

            toEdit.setId(s.getId());
            toEdit.setName(s.getName());
            toEdit.setDescription(s.getDescription());
            toEdit.setAllOrganizations(s.getAllOrganizations());
            toEdit.setAllPowers(s.getAllPowers());

        }
        return s;
//        check if object exists in list (findById)
//        run create or update depending on whether or not it can be found
    }

    @Override
    public Optional<Super> findById(Integer id) {
        Optional<Super> toReturn = allSupers.stream()
                .filter(l -> l.getId() == id)
                .findAny();

        return toReturn;
    }

    @Override
    public void delete(Super t) {
        if (allSupers.contains(t)) {
            allSupers.remove(t);
        }
    }

//    UNUSED METHODS - NEEDED TO COMPLY WITH INTERFACE
    @Override
    public void deleteAll(Iterable<? extends Super> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <S extends Super> Optional<S> findOne(Example<S> exmpl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <S extends Super> Page<S> findAll(Example<S> exmpl, Pageable pgbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <S extends Super> long count(Example<S> exmpl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <S extends Super> boolean exists(Example<S> exmpl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Super> findAll(Sort sort) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Super> findAllById(Iterable<Integer> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <S extends Super> List<S> saveAll(Iterable<S> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void flush() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <S extends Super> S saveAndFlush(S s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteInBatch(Iterable<Super> itrbl) {
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
    public <S extends Super> List<S> findAll(Example<S> exmpl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <S extends Super> List<S> findAll(Example<S> exmpl, Sort sort) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Page<Super> findAll(Pageable pgbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAllInBatch() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
