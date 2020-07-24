/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.repositories;

import com.sg.supersightings.models.Location;
import com.sg.supersightings.models.Sighting;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author siessejordan
 */
@Repository
public interface SightingRepository extends JpaRepository<Sighting, Integer>{

    @Query(value = "SELECT s FROM Sighting s where s.superBeing.id = :superId")
    public List<Sighting> getAllSightingsForSuperId(int superId);
    
    @Query(value = "SELECT s.* from sighting s order by s.date desc limit 10", nativeQuery = true)
    public List<Sighting> getTenMostRecentSightings();
}
