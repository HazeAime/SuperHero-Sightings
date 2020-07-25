/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.services;

import com.sg.supersightings.models.Power;
import com.sg.supersightings.repositories.InMemPowDao;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author siessejordan
 */
@SpringBootTest
public class PowerServiceTest {

    PowerService service;

    public PowerServiceTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        service = new PowerService(new InMemPowDao());
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of saveNewPower method, of class PowerService.
     */
    @Test
    public void testSaveNewPower() throws Exception {
        Power newPower = new Power();
        newPower.setId(4);
        newPower.setName("Money");

        service.saveNewPower(newPower);

        Power toTest = service.getOne(4);

//        ASSERTIONS
        assertTrue(toTest.getId() == 4);
        assertTrue(toTest.getName().equals("Money"));
    }

    /**
     * Test of findAll method, of class PowerService.
     */
    @Test
    public void testFindAll() {
        List<Power> allPowers = service.findAll();

//        ASSERTIONS
        assertTrue(allPowers.get(0).getId() == 1);
        assertTrue(allPowers.get(0).getName().equals("ORAORAORAH"));

        assertTrue(allPowers.get(1).getId() == 2);
        assertTrue(allPowers.get(1).getName().equals("ZA-WORLD-OHH"));

        assertTrue(allPowers.get(2).getId() == 3);
        assertTrue(allPowers.get(2).getName().equals("Flight"));
    }

    /**
     * Test of getOne method, of class PowerService.
     */
    @Test
    public void testGetOne() {
        Power toTest = service.getOne(1);

//        ASSERTIONS
        assertTrue(toTest.getId() == 1);
        assertTrue(toTest.getName().equals("ORAORAORAH"));
    }

    /**
     * Test of updatePower method, of class PowerService.
     */
    @Test
    public void testUpdatePower() throws Exception {
        Power toUpdate = service.getOne(1);
        toUpdate.setName("Super Strength");

        Power toTest = service.getOne(1);

//        ASSERTIONS
        assertTrue(toTest.getId() == 1);
        assertTrue(toTest.getName().equals("Super Strength"));

    }

    /**
     * Test of delete method, of class PowerService.
     */
    @Test
    public void testDelete() {
        Power toDelete = service.getOne(1);

        service.delete(toDelete);

        List<Power> allPowers = service.findAll();

        for (Power power : allPowers) {
            if (power.getId() == 1) {
                fail("Should not find deleted power");
            }
        }
    }

}
