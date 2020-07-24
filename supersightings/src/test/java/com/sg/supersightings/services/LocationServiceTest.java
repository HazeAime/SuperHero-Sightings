/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.services;

import com.sg.supersightings.models.Location;
import com.sg.supersightings.repositories.InMemLocDao;
import java.math.BigDecimal;
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
public class LocationServiceTest {
    
    
    LocationService service;
    
    public LocationServiceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        service = new LocationService(new InMemLocDao());
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of findAll method, of class LocationService.
     */
    @Test
    public void testFindAll() {
        List<Location> allLocations = service.findAll();
        
        assertTrue(allLocations.size() == 3);
        
        assertTrue(allLocations.get(0).getId() == 1);
        assertTrue(allLocations.get(0).getLocationName().equals("Flavor Town"));
        assertTrue(allLocations.get(0).getDescription().equals("Your next destination."));
        assertTrue(allLocations.get(0).getAddress().equals("9800 Umami Way"));
        assertTrue(allLocations.get(0).getLatitude().compareTo(new BigDecimal("80.1234")) == 0);
        assertTrue(allLocations.get(0).getLongitude().compareTo(new BigDecimal("60.9876")) == 0);
        
        assertTrue(allLocations.get(2).getId() == 3);
        assertTrue(allLocations.get(2).getLocationName().equals("Hyrule"));
        assertTrue(allLocations.get(2).getDescription().equals("Your princess is in another castle lol."));
        assertTrue(allLocations.get(2).getAddress().equals("404 maybe in the hills?"));
        assertTrue(allLocations.get(2).getLatitude().compareTo(new BigDecimal("30.404")) == 0);
        assertTrue(allLocations.get(2).getLongitude().compareTo(new BigDecimal("30.202")) == 0);
    }

    /**
     * Test of saveNewLocation method, of class LocationService.
     */
    @Test
    public void testSaveNewLocation() throws Exception {
        
        Location toAdd = new Location();
        toAdd.setId(4);
        toAdd.setLocationName("Luigi's Mansion");
        toAdd.setDescription("It's a spooky house full of ghosts!");
        toAdd.setAddress("2 spoopyforme avenue");
        toAdd.setLatitude(new BigDecimal("10.10101"));
        toAdd.setLongitude(new BigDecimal("50.6879"));
        
        service.saveNewLocation(toAdd);
        
        List<Location> allLocations = service.findAll();
        
        assertTrue(allLocations.size() == 4);
        
        assertTrue(allLocations.get(0).getId() == 1);
        assertTrue(allLocations.get(0).getLocationName().equals("Flavor Town"));
        assertTrue(allLocations.get(0).getDescription().equals("Your next destination."));
        assertTrue(allLocations.get(0).getAddress().equals("9800 Umami Way"));
        assertTrue(allLocations.get(0).getLatitude().compareTo(new BigDecimal("80.1234")) == 0);
        assertTrue(allLocations.get(0).getLongitude().compareTo(new BigDecimal("60.9876")) == 0);
        
        assertTrue(allLocations.get(3).getId() == 4);
        assertTrue(allLocations.get(3).getLocationName().equals("Luigi's Mansion"));
        assertTrue(allLocations.get(3).getDescription().equals("It's a spooky house full of ghosts!"));
        assertTrue(allLocations.get(3).getAddress().equals("2 spoopyforme avenue"));
        assertTrue(allLocations.get(3).getLatitude().compareTo(new BigDecimal("10.10101")) == 0);
        assertTrue(allLocations.get(3).getLongitude().compareTo(new BigDecimal("50.6879")) == 0);
        
    }

    /**
     * Test of getOne method, of class LocationService.
     */
    @Test
    public void testGetOne() {
        List<Location> allLocations = service.findAll();
        
        Location test = service.getOne(1);
                
        assertTrue(test.getId() == 1);
        assertTrue(test.getLocationName().equals("Flavor Town"));
        assertTrue(test.getDescription().equals("Your next destination."));
        assertTrue(test.getAddress().equals("9800 Umami Way"));
        assertTrue(test.getLatitude().compareTo(new BigDecimal("80.1234")) == 0);
        assertTrue(test.getLongitude().compareTo(new BigDecimal("60.9876")) == 0);
    }

    /**
     * Test of delete method, of class LocationService.
     */
    @Test
    public void testDelete() {
        
        service.delete(3);
        
        List<Location> allLocations = service.findAll();
        
        assertTrue(allLocations.size() == 2);
        
        assertTrue(allLocations.get(0).getId() == 1);
        assertTrue(allLocations.get(0).getLocationName().equals("Flavor Town"));
        assertTrue(allLocations.get(0).getDescription().equals("Your next destination."));
        assertTrue(allLocations.get(0).getAddress().equals("9800 Umami Way"));
        assertTrue(allLocations.get(0).getLatitude().compareTo(new BigDecimal("80.1234")) == 0);
        assertTrue(allLocations.get(0).getLongitude().compareTo(new BigDecimal("60.9876")) == 0);
        
        assertTrue(allLocations.get(1).getId() == 2);
        assertTrue(allLocations.get(1).getLocationName().equals("The Krusty Krab"));
        assertTrue(allLocations.get(1).getDescription().equals("My name is Mr. Krabs and I like money."));
        assertTrue(allLocations.get(1).getAddress().equals("1000 Rock Bottom Heights"));
        assertTrue(allLocations.get(1).getLatitude().compareTo(new BigDecimal("-60.0101")) == 0);
        assertTrue(allLocations.get(1).getLongitude().compareTo(new BigDecimal("-160.2222")) == 0);
    }

    /**
     * Test of updateLocation method, of class LocationService.
     */
    @Test
    public void testUpdateLocation() throws Exception {
        
        Location toEdit = new Location();
        toEdit.setId(2);
        toEdit.setLocationName("Jimmy Johns");
        toEdit.setDescription("Your princess is in another castle lol.");
        toEdit.setAddress("404 maybe in the hills?");
        toEdit.setLatitude(new BigDecimal("30.404"));
        toEdit.setLongitude(new BigDecimal("30.202"));
        
        service.saveNewLocation(toEdit);
        
        List<Location> allLocations = service.findAll();
        
        assertTrue(allLocations.size() == 3);
        
        assertTrue(allLocations.get(1).getId() == 2);
        assertTrue(allLocations.get(1).getLocationName().equals("Jimmy Johns"));
        assertTrue(allLocations.get(1).getDescription().equals("Your princess is in another castle lol."));
        assertTrue(allLocations.get(1).getAddress().equals("404 maybe in the hills?"));
        assertTrue(allLocations.get(1).getLatitude().compareTo(new BigDecimal("30.404")) == 0);
        assertTrue(allLocations.get(1).getLongitude().compareTo(new BigDecimal("30.202")) == 0);
        
        
    }
    
}
