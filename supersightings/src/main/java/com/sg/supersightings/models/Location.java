/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.models;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author siessejordan
 */
@Entity
public class Location {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private int id;
    
    @Column(nullable = false)
    private String locationName;
    
    @Column(nullable = false)
    private String description;
    
    @Column(nullable = false)
    private String address;
    
    @Column(nullable = false)
    private int latitude;
    
    @Column(nullable = false)
    private int longitude;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.locationName);
        hash = 89 * hash + Objects.hashCode(this.description);
        hash = 89 * hash + Objects.hashCode(this.address);
        hash = 89 * hash + this.latitude;
        hash = 89 * hash + this.longitude;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Location other = (Location) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.latitude != other.latitude) {
            return false;
        }
        if (this.longitude != other.longitude) {
            return false;
        }
        if (!Objects.equals(this.locationName, other.locationName)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        return true;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the locationName
     */
    public String getLocationName() {
        return locationName;
    }

    /**
     * @param locationName the locationName to set
     */
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the latitude
     */
    public int getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public int getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }
}
