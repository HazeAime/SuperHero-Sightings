/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.repositories;

import com.sg.supersightings.models.Super;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author siessejordan
 */
@Repository
public interface SuperRepository extends JpaRepository<Super, Integer> {
    
//    public static final class SuperMapper implements RowMapper<Super> {}
}
