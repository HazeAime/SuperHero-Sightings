/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.services;

import com.sg.supersightings.models.Organization;
import com.sg.supersightings.models.Power;
import com.sg.supersightings.models.Super;
import com.sg.supersightings.models.SuperVM;
import com.sg.supersightings.repositories.InMemSupDao;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

/**
 *
 * @author siessejordan
 */
@SpringBootTest
public class SuperServiceTest {
    
    SuperService service;
    
    public SuperServiceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        service = new SuperService(new InMemSupDao());
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of findAll method, of class SuperService.
     */
    @Test
    public void testFindAll() {
        List<Super> allSupers = service.findAll();
        
        assertTrue(allSupers.size() == 3);
        
        assertTrue(allSupers.get(0).getId() == 1);
        assertTrue(allSupers.get(0).getName().equals("Star Platinum"));
        assertTrue(allSupers.get(0).getDescription().equals("*Opens can of whoop ass.*"));
        
        assertEquals(2, allSupers.get(0).getAllPowers().size());
        assertTrue(allSupers.get(0).getAllPowers().get(0).getId() == 1);
        assertTrue(allSupers.get(0).getAllPowers().get(1).getId() == 2);
        
        assertEquals(2, allSupers.get(0).getAllOrganizations().size());
        assertTrue(allSupers.get(0).getAllOrganizations().get(0).getId() == 1);
        assertTrue(allSupers.get(0).getAllOrganizations().get(1).getId() == 2);
        
        assertTrue(allSupers.get(2).getId() == 3);
        assertTrue(allSupers.get(2).getName().equals("Dat Boi"));
        assertTrue(allSupers.get(2).getDescription().equals("It Me"));
        
        assertEquals(1, allSupers.get(2).getAllPowers().size());
        assertTrue(allSupers.get(2).getAllPowers().get(0).getId() == 1);
        
        assertEquals(1, allSupers.get(2).getAllOrganizations().size());
        assertTrue(allSupers.get(2).getAllOrganizations().get(0).getId() == 1);
        
        
    }

    /**
     * Test of getOne method, of class SuperService.
     */
    @Test
    public void testGetOne() {
        Super toTest = service.getOne(0);
        
        assertTrue(toTest.getId() == 1);
        assertTrue(toTest.getName().equals("Star Platinum"));
        assertTrue(toTest.getDescription().equals("*Opens can of whoop ass.*"));
        
        assertEquals(2, toTest.getAllPowers().size());
        assertTrue(toTest.getAllPowers().get(0).getId() == 1);
        assertTrue(toTest.getAllPowers().get(1).getId() == 2);
        
        assertEquals(2, toTest.getAllOrganizations().size());
        assertTrue(toTest.getAllOrganizations().get(0).getId() == 1);
        assertTrue(toTest.getAllOrganizations().get(1).getId() == 2);
        
    }

    /**
     * Test of saveNewSuper method, of class SuperService.
     */
    @Test
    public void testSaveNewSuper() throws Exception {
        
        SuperVM vm = new SuperVM();
        vm.setSuperId(4);
        vm.setName("Jotaro Kujo");
        vm.setDescription("Yare, yare");
        
        List<Integer> vmPowers = new ArrayList();
        
        vmPowers.add(1);
        vm.setPowerIds(vmPowers);
                
        List<Integer> vmOrgs = new ArrayList();
        
        vmOrgs.add(1);
        vm.setOrgIds(vmOrgs);
        
//        Organization org = new Organization();
//        org.setId(3);
//        org.setOrgName("All Stars");
//        org.setOrgDescription("Too Cool For School.");
//        org.setAddress("9070 No address Path");
//        org.setPhone("(612)999-9999");
        
        service.saveNewSuper(vm);
        
        List<Super> allSupers = service.findAll();
        
        assertTrue(allSupers.size() == 4);
        
        assertTrue(allSupers.get(0).getId() == 1);
        assertTrue(allSupers.get(0).getName().equals("Star Platinum"));
        assertTrue(allSupers.get(0).getDescription().equals("*Opens can of whoop ass.*"));
        
        assertEquals(2, allSupers.get(0).getAllPowers().size());
        assertTrue(allSupers.get(0).getAllPowers().get(0).getId() == 1);
        assertTrue(allSupers.get(0).getAllPowers().get(1).getId() == 2);
        
        assertEquals(2, allSupers.get(0).getAllOrganizations().size());
        assertTrue(allSupers.get(0).getAllOrganizations().get(0).getId() == 1);
        assertTrue(allSupers.get(0).getAllOrganizations().get(1).getId() == 2);
        
        assertTrue(allSupers.get(3).getId() == 4);
        assertTrue(allSupers.get(3).getName().equals("Jotaro Kujo"));
        assertTrue(allSupers.get(3).getDescription().equals("Yare, yare"));
        
        assertEquals(1, allSupers.get(3).getAllPowers().size());
        assertTrue(allSupers.get(3).getAllPowers().get(0).getId() == 3);
        
        assertEquals(1, allSupers.get(2).getAllOrganizations().size());
        assertTrue(allSupers.get(2).getAllOrganizations().get(0).getId() == 3);
    }

    /**
     * Test of updateSuper method, of class SuperService.
     */
    @Test
    public void testUpdateSuper() throws Exception {
    }

    /**
     * Test of delete method, of class SuperService.
     */
    @Test
    public void testDelete() {
    }

    /**
     * Test of getAllPowerVMsForSuper method, of class SuperService.
     */
    @Test
    public void testGetAllPowerVMsForSuper() {
    }

    /**
     * Test of getAllOrgVMsForSuper method, of class SuperService.
     */
    @Test
    public void testGetAllOrgVMsForSuper() {
    }
    
}
