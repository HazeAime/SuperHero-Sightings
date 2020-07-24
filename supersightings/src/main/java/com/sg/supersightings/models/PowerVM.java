/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.models;

/**
 *
 * @author siessejordan
 */
public class PowerVM {
    private int id;
    private String name;
    private boolean isChecked;
    
    public PowerVM(){}
    
    public PowerVM(Power other){
        this.id = other.getId();
        this.name = other.getName();
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
