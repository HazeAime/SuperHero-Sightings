/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.models;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author siessejordan
 */
@Entity
public class Organization {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private int id;
    
    @Column(nullable = false)
    private String orgName;
    
    @Column(nullable = false)
    private String orgDescription;
    
    @Column(nullable = false)
    private String address;
    
    @Column(nullable = false)
    private String phone;
    
    @ManyToMany
    @JoinTable(name = "superOrganization",
            joinColumns = {@JoinColumn(name = "orgId")},
            inverseJoinColumns = {@JoinColumn(name = "superId")})
    private List<Super> allSupers;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.orgName);
        hash = 79 * hash + Objects.hashCode(this.orgDescription);
        hash = 79 * hash + Objects.hashCode(this.address);
        hash = 79 * hash + Objects.hashCode(this.phone);
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
        final Organization other = (Organization) obj;
        if (this.id != other.id) {
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
  
}
