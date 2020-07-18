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
public class Super {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private int id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String description;
    
    @ManyToMany
    @JoinTable(name = "superPower",
            joinColumns = {@JoinColumn(name = "superId")},
            inverseJoinColumns = {@JoinColumn(name = "powerId")})
    private List<Power> powers;

    @ManyToMany
    @JoinTable(name = "superOrganization",
            joinColumns = {@JoinColumn(name = "superId")},
            inverseJoinColumns = {@JoinColumn(name = "orgId")})
    private List<Organization> allOrganizations;
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.description);
        hash = 67 * hash + Objects.hashCode(this.powers);
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
        final Super other = (Super) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.powers, other.powers)) {
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
     * @return the power
     */
    public List<Power> getPower() {
        return powers;
    }

    /**
     * @param power the power to set
     */
    public void setPowers(List<Power> powers) {
        this.powers = powers;
    }
}
