/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.controllers;

import com.sg.supersightings.exceptions.InvalidEntityException;
import com.sg.supersightings.models.Power;
import com.sg.supersightings.services.PowerService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author siessejordan
 */
@Controller
public class PowerController {

    @Autowired
    PowerService powerService;

    @GetMapping("/createpower")
    public String createNewPower(Model model) {
        model.addAttribute("isValid", true);
        return "createpower";
    }

    @PostMapping("/createpower")
    public String createNewPower(Model model, Power toAdd) {
        try {
            powerService.saveNewPower(toAdd);
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("isValid", false);
            model.addAttribute("errorMessage", "Power already exists.");
            return "createpower";
        } catch (InvalidEntityException ex) {
            model.addAttribute("isValid", false);
            model.addAttribute("errorMessage", ex.getMessage());
            return "createpower";
        }
        return "redirect:/allpowers";
    }

    @GetMapping("/allpowers")
    public String displayAllPowers(Model model) {
        model.addAttribute("allPowers", powerService.findAll());
        return "allpowers";
    }

    @GetMapping("/updatepower/{id}")
    public String updatePower(Model model, @PathVariable Integer id) {
        model.addAttribute("power", powerService.getOne(id));
        model.addAttribute("isValid", true);
        return "updatepower";
    }

    @PostMapping("/updatepower")
    public String updatePower(Power toEdit, Model model) {
        model.addAttribute("power", toEdit);
        try {
            powerService.updatePower(toEdit);
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("isValid", false);
            model.addAttribute("errorMessage", "Power already exists.");
            return "updatepower";
        } catch (InvalidEntityException ex) {
            model.addAttribute("isValid", false);
            model.addAttribute("errorMessage", ex.getMessage());
            return "updatepower";
        }
        return "redirect:/allpowers";
    }

    @GetMapping("/deletepower/{id}")
    public String deletePower(Power toRemove, Model model) {
        powerService.delete(toRemove);
        return "redirect:/allpowers";
    }

}
