/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.models;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author siessejordan
 */
public class SightingVM {
    private int superId;
    private int locationId;
    private String date;

    /**
     * @return the superId
     */
    public int getSuperId() {
        return superId;
    }

    /**
     * @param superId the superId to set
     */
    public void setSuperId(int superId) {
        this.superId = superId;
    }

    /**
     * @return the locationId
     */
    public int getLocationId() {
        return locationId;
    }

    /**
     * @param locationId the locationId to set
     */
    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }
    
    
}
