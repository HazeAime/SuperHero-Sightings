/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.repositories;

import com.sg.supersightings.models.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author siessejordan
 */
@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
    
}
