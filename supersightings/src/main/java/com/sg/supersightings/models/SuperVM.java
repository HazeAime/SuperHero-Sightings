/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.models;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author siessejordan
 */
public class SuperVM {
    //super object?
    //array of supers
    //array of orgs
    private int superId;
    private String name;
    private String description;
    private List<Integer> powerIds;
    private List<Integer> orgIds;
    private boolean isChecked;
    
    public SuperVM(Super other) {
        this.superId = other.getId();
        this.name = other.getName();
        this.description = other.getDescription();
    }
    
    public SuperVM() {}

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.superId;
        hash = 13 * hash + Objects.hashCode(this.name);
        hash = 13 * hash + Objects.hashCode(this.description);
        hash = 13 * hash + Objects.hashCode(this.powerIds);
        hash = 13 * hash + Objects.hashCode(this.orgIds);
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
        final SuperVM other = (SuperVM) obj;
        if (this.superId != other.superId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.powerIds, other.powerIds)) {
            return false;
        }
        if (!Objects.equals(this.orgIds, other.orgIds)) {
            return false;
        }
        return true;
    }

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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @return the powerIds
     */
    public List<Integer> getPowerIds() {
        return powerIds;
    }

    /**
     * @param powerIds the powerIds to set
     */
    public void setPowerIds(List<Integer> powerIds) {
        this.powerIds = powerIds;
    }

    /**
     * @return the orgIds
     */
    public List<Integer> getOrgIds() {
        return orgIds;
    }

    /**
     * @param orgIds the orgIds to set
     */
    public void setOrgIds(List<Integer> orgIds) {
        this.orgIds = orgIds;
    }

    /**
     * @return the isChecked
     */
    public boolean isIsChecked() {
        return isChecked;
    }

    /**
     * @param isChecked the isChecked to set
     */
    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

}
