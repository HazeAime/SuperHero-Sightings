/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.repositories;

import com.sg.supersightings.models.Location;
import com.sg.supersightings.models.Power;
import com.sg.supersightings.models.Sighting;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author siessejordan
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    
    
    
}
