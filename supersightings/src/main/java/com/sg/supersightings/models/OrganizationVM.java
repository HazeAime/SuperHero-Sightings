/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.models;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author siessejordan
 */
public class OrganizationVM {
    
    private int id;
    private String orgName;
    private String orgDescription;
    private String address;
    private String phone;
    private boolean isChecked;
    private List<Integer> superIds;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.id;
        hash = 47 * hash + Objects.hashCode(this.orgName);
        hash = 47 * hash + Objects.hashCode(this.orgDescription);
        hash = 47 * hash + Objects.hashCode(this.address);
        hash = 47 * hash + Objects.hashCode(this.phone);
        hash = 47 * hash + (this.isChecked ? 1 : 0);
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
        final OrganizationVM other = (OrganizationVM) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.isChecked != other.isChecked) {
            return false;
        }
        if (!Objects.equals(this.orgName, other.orgName)) {
            return false;
        }
        if (!Objects.equals(this.orgDescription, other.orgDescription)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        return true;
    }
    
    public OrganizationVM() {
    
    }
    
    public OrganizationVM(Organization other) {
        this.id = other.getId();
        this.orgName = other.getOrgName();
        this.address = other.getAddress();
        this.orgDescription = other.getOrgDescription();
        this.phone = other.getPhone();
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
     * @return the orgName
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * @param orgName the orgName to set
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * @return the orgDescription
     */
    public String getOrgDescription() {
        return orgDescription;
    }

    /**
     * @param orgDescription the orgDescription to set
     */
    public void setOrgDescription(String orgDescription) {
        this.orgDescription = orgDescription;
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
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
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

    /**
     * @return the superIds
     */
    public List<Integer> getSuperIds() {
        return superIds;
    }

    /**
     * @param superIds the superIds to set
     */
    public void setSuperIds(List<Integer> superIds) {
        this.superIds = superIds;
    }

}