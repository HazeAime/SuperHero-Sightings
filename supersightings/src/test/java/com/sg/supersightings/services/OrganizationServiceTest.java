/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.services;

import com.sg.supersightings.models.Organization;
import com.sg.supersightings.models.OrganizationVM;
import com.sg.supersightings.models.SuperVM;
import com.sg.supersightings.repositories.InMemOrgDao;
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

/**
 *
 * @author siessejordan
 */
@SpringBootTest
public class OrganizationServiceTest {
    
    OrganizationService service;
    
    public OrganizationServiceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        service = new OrganizationService(new InMemSupDao(), new InMemOrgDao());
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getOne method, of class OrganizationService.
     */
    @Test
    public void testGetOne() {
        Organization toTest = service.getOne(3);
        
        assertTrue(toTest.getId() == 3);
        assertTrue(toTest.getOrgName().equals("gud"));
        assertTrue(toTest.getOrgDescription().equals("git gud"));
        assertTrue(toTest.getAddress().equals("9000 winners circle"));
        assertTrue(toTest.getPhone().equals("(612)444-3333"));
        assertTrue(toTest.getAllSupers().get(0).getId() == (5));
        assertTrue(toTest.getAllSupers().get(0).getName().equals("Avdol"));
    }

    /**
     * Test of findAll method, of class OrganizationService.
     */
    @Test
    public void testFindAll() {
        List<Organization> allOrgs = service.findAll();
        
        assertTrue(allOrgs.size() == 3);
        
        assertTrue(allOrgs.get(0).getId() == 1);
        assertTrue(allOrgs.get(0).getOrgName().equals("E.V.I.L."));
        assertTrue(allOrgs.get(0).getOrgDescription().equals("evil"));
        assertTrue(allOrgs.get(0).getAddress().equals("404 no address"));
        assertTrue(allOrgs.get(0).getPhone().equals("(555)444-3333"));
        assertTrue(allOrgs.get(0).getAllSupers().get(0).getId() == (4));
        assertTrue(allOrgs.get(0).getAllSupers().get(0).getName().equals("Joseph Joestar"));
        
        assertTrue(allOrgs.get(2).getId() == 3);
        assertTrue(allOrgs.get(2).getOrgName().equals("gud"));
        assertTrue(allOrgs.get(2).getOrgDescription().equals("git gud"));
        assertTrue(allOrgs.get(2).getAddress().equals("9000 winners circle"));
        assertTrue(allOrgs.get(2).getPhone().equals("(612)444-3333"));
        assertTrue(allOrgs.get(2).getAllSupers().get(0).getId() == (5));
        assertTrue(allOrgs.get(2).getAllSupers().get(0).getName().equals("Avdol"));
        
    }

    /**
     * Test of saveNewOrg method, of class OrganizationService.
     */
    @Test
    public void testSaveNewOrg() throws Exception {
        OrganizationVM vm = new OrganizationVM();
        vm.setId(4);
        vm.setOrgName("Moto");
        vm.setOrgDescription("Hello Moto");
        vm.setAddress("no address here");
        vm.setPhone("(123)456-7890");
        
        List<Integer> vmSupers = new ArrayList();
        vmSupers.add(1);
        vm.setSuperIds(vmSupers);

        service.saveNewOrg(vm);

        List<Organization> allOrgs = service.findAll();
        
        assertTrue(allOrgs.size() == 4);
        
        assertTrue(allOrgs.get(3).getId() == 4);
        assertTrue(allOrgs.get(3).getOrgName().equals("Moto"));
        assertTrue(allOrgs.get(3).getOrgDescription().equals("Hello Moto"));
        assertTrue(allOrgs.get(3).getAddress().equals("no address here"));
        assertTrue(allOrgs.get(3).getPhone().equals("(123)456-7890"));
        assertTrue(allOrgs.get(3).getAllSupers().get(0).getId() == 1);
        assertTrue(allOrgs.get(3).getAllSupers().get(0).getName().equals("Star Platinum"));
    }

    /**
     * Test of updateOrg method, of class OrganizationService.
     */
    @Test
    public void testUpdateOrg() throws Exception {
        OrganizationVM vm = new OrganizationVM();
        vm.setId(1);
        vm.setOrgName("Moto");
        vm.setOrgDescription("Hello Moto");
        vm.setAddress("no address here");
        vm.setPhone("(123)456-7890");
        
        List<Integer> vmSupers = new ArrayList();
        vmSupers.add(3);
        vm.setSuperIds(vmSupers);

        service.updateOrg(service.getOne(vm.getId()), vm);

        List<Organization> allOrgs = service.findAll();
        
        assertTrue(allOrgs.size() == 3);
        
        assertTrue(allOrgs.get(0).getId() == 1);
        assertTrue(allOrgs.get(0).getOrgName().equals("Moto"));
        assertTrue(allOrgs.get(0).getOrgDescription().equals("Hello Moto"));
        assertTrue(allOrgs.get(0).getAddress().equals("no address here"));
        assertTrue(allOrgs.get(0).getPhone().equals("(123)456-7890"));
        assertTrue(allOrgs.get(0).getAllSupers().get(0).getId() == 3);
        assertTrue(allOrgs.get(0).getAllSupers().get(0).getName().equals("Dat Boi"));
    }

    /**
     * Test of getAllSuperVMsForOrg method, of class OrganizationService.
     */
    @Test
    public void testGetAllSuperVMsForOrg() {
        Organization toTest = service.getOne(1);
        
        List<SuperVM> allSuperVMs = service.getAllSuperVMsForOrg(toTest);
        
        assertEquals(3, allSuperVMs.size());
        assertTrue(allSuperVMs.get(0).getSuperId() == 1);
        assertTrue(allSuperVMs.get(0).getName().equals("Star Platinum"));
        assertTrue(allSuperVMs.get(0).getDescription().equals("*Opens can of whoop ass.*"));
        
        assertTrue(allSuperVMs.get(2).getSuperId() == 3);
        assertTrue(allSuperVMs.get(2).getName().equals("Dat Boi"));
        assertTrue(allSuperVMs.get(2).getDescription().equals("It Me"));

    }

    /**
     * Test of delete method, of class OrganizationService.
     */
    @Test
    public void testDelete() {
        service.delete(2);
        
        List<Organization> allOrgs = service.findAll();
        
        assertTrue(allOrgs.size() == 2);
        
        assertTrue(allOrgs.get(0).getId() == 1);
        assertTrue(allOrgs.get(0).getOrgName().equals("E.V.I.L."));
        assertTrue(allOrgs.get(0).getOrgDescription().equals("evil"));
        assertTrue(allOrgs.get(0).getAddress().equals("404 no address"));
        assertTrue(allOrgs.get(0).getPhone().equals("(555)444-3333"));
        assertTrue(allOrgs.get(0).getAllSupers().get(0).getId() == (4));
        assertTrue(allOrgs.get(0).getAllSupers().get(0).getName().equals("Joseph Joestar"));
        
        assertTrue(allOrgs.get(1).getId() == 3);
        assertTrue(allOrgs.get(1).getOrgName().equals("gud"));
        assertTrue(allOrgs.get(1).getOrgDescription().equals("git gud"));
        assertTrue(allOrgs.get(1).getAddress().equals("9000 winners circle"));
        assertTrue(allOrgs.get(1).getPhone().equals("(612)444-3333"));
        assertTrue(allOrgs.get(1).getAllSupers().get(0).getId() == (5));
        assertTrue(allOrgs.get(1).getAllSupers().get(0).getName().equals("Avdol"));
    }
    
}
