/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.services;

import com.sg.supersightings.models.Sighting;
import com.sg.supersightings.models.SightingVM;
import com.sg.supersightings.repositories.InMemLocDao;
import com.sg.supersightings.repositories.InMemSightDao;
import com.sg.supersightings.repositories.InMemSupDao;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author siessejordan
 */
@SpringBootTest
public class SightingServiceTest {

    SightingService service;

    public SightingServiceTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        service = new SightingService(new InMemSightDao(), new InMemSupDao(), new InMemLocDao());
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getOne method, of class SightingService.
     */
    @Test
    public void testGetOne() {
        Sighting toTest = service.getOne(1);

        assertTrue(toTest.getDate().equals(LocalDate.parse("2020-01-01")));
        assertTrue(toTest.getId() == 1);
        assertTrue(toTest.getSuperBeing().getName().equals("Star Platinum"));
        assertTrue(toTest.getLocation().getLocationName().equals("Chicago"));
    }

    /**
     * Test of findAll method, of class SightingService.
     */
    @Test
    public void testFindAll() {
        List<Sighting> allSightings = service.findAll();

        Sighting first = allSightings.get(0);

        Sighting last = allSightings.get(allSightings.size() - 1);

        assertTrue(first.getDate().equals(LocalDate.parse("2020-01-01")));
        assertTrue(first.getId() == 1);
        assertTrue(first.getSuperBeing().getName().equals("Star Platinum"));
        assertTrue(first.getLocation().getLocationName().equals("Chicago"));

        assertTrue(last.getDate().equals(LocalDate.parse("2020-01-12")));
        assertTrue(last.getId() == 12);
        assertTrue(last.getSuperBeing().getName().equals("Star Platinum"));
        assertTrue(last.getLocation().getLocationName().equals("New York"));
    }

    /**
     * Test of saveNewSighting method, of class SightingService.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSaveNewSighting() throws Exception {

        Integer expectedId = 4;
        SightingVM toSave = new SightingVM();
        toSave.setDate("2020-01-04");
        toSave.setLocationId(1);
        toSave.setSuperId(1);

        service.saveNewSighting(toSave);

        Sighting toTest = service.getOne(expectedId);

        assertTrue(toTest.getId() == expectedId);
        assertTrue(toTest.getDate().equals(LocalDate.parse("2020-01-04")));
        assertTrue(toTest.getSuperBeing().getName().equals("Star Platinum"));
        assertTrue(toTest.getLocation().getLocationName().equals("New York"));

    }

    /**
     * Test of updateSighting method, of class SightingService.
     */
    @Test
    public void testUpdateSighting() throws Exception {
        Sighting toEdit = service.getOne(1);
        SightingVM vm = new SightingVM();
        vm.setDate(toEdit.getDate().toString());
        vm.setLocationId(1);
        vm.setSuperId(2);
        service.updateSighting(toEdit.getId(), vm);
        
        Sighting toTest = service.getOne(1);
        
        assertTrue(toTest.getDate().equals(LocalDate.parse("2020-01-01")));
        assertTrue(toTest.getLocation().getLocationName().equals("Flavor Town"));
        assertTrue(toTest.getSuperBeing().getName().equals("Kirito"));
    }

    /**
     * Test of delete method, of class SightingService.
     */
    @Test
    public void testDelete() {
        Sighting toDelete = service.getOne(1);
        service.delete(toDelete.getId());

        List<Sighting> allSightings = service.findAll();

        for (Sighting sighting : allSightings) {
            if (sighting.getId() == toDelete.getId()) {
                fail("Should not find deleted id");
            }
        }
    }

    /**
     * Test of getAllSightingsForSuperId method, of class SightingService.
     */
    @Test
    public void testGetAllSightingsForSuperId() {
        List<Sighting> toTest = service.getAllSightingsForSuperId(1);

        Sighting first = toTest.get(0);
        Sighting last = toTest.get(toTest.size() - 1);

        assertTrue(first.getLocation().getLocationName().equals("Chicago"));
        assertTrue(first.getDate().equals(LocalDate.parse("2020-01-01")));
        assertTrue(first.getSuperBeing().getName().equals("Star Platinum"));

        assertTrue(last.getLocation().getLocationName().equals("New York"));
        assertTrue(last.getDate().equals(LocalDate.parse("2020-01-12")));
        assertTrue(last.getSuperBeing().getName().equals("Star Platinum"));
    }

    /**
     * Test of getTenMostRecentSightings method, of class SightingService.
     */
    @Test
    public void testGetTenMostRecentSightings() {
//        TEST to see if the first element is the latest date
        List<Sighting> toTest = service.getTenMostRecentSightings();
        
        Sighting first = toTest.get(0);
        Sighting second = toTest.get(1);
        Sighting last = toTest.get(toTest.size() - 1);
        
        assertTrue(first.getDate().equals(LocalDate.parse("2020-01-12")));
        assertTrue(second.getDate().equals(LocalDate.parse("2020-01-11")));
        assertTrue(last.getDate().equals(LocalDate.parse("2020-01-03")));
    }

}
