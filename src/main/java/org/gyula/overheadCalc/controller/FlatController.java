package org.gyula.overheadCalc.controller;

import lombok.extern.slf4j.Slf4j;
import org.gyula.overheadCalc.entity.A_flat;
import org.gyula.overheadCalc.service.FlatService;
import org.gyula.overheadCalc.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/flats")
public class FlatController {

    FlatService flatService;
    TenantService tenantService;

    @Autowired
    public FlatController(FlatService flatService, TenantService tenantService) {
        this.flatService = flatService;
        this.tenantService = tenantService;
    }

    @GetMapping("/flatList")
    public String allFlats(Model model) {
        model.addAttribute("flatList", flatService.findAll());
        log.info("Listed all the flats in the database");
        return "flatTemplate/flatList";
    }

    @GetMapping("/addFlat")
    public String addFlat(Model model) {
        A_flat newFlat = new A_flat();
        model.addAttribute("flat", newFlat);
        model.addAttribute("tenantList", tenantService.findAll());
        log.info("Adding new flat data, calling the form page");
        return "flatTemplate/flat-form";
    }

    @PostMapping("/saveFlat")
    public String saveFlat(@ModelAttribute("flat") A_flat newFlat) {
        flatService.save(newFlat);
        log.info("Saved the new flat: " + newFlat.getAddress());
        //use redirect to prevent duplicate submissions
        return "redirect:/flats/flatList";
    }

    @GetMapping("/flatUpdate")
    public String updateFlat(@RequestParam("flatId") int theId, Model model, Authentication authentication) {
        A_flat updatedFlat = flatService.findById(theId);
        model.addAttribute("flat", updatedFlat);
        model.addAttribute("tenantList", tenantService.findAll());
        model.addAttribute("authentication", authentication);
        log.info("Going to update form with data of: " + updatedFlat.getAddress());
        return "flatTemplate/flat-form";
    }

    @GetMapping("/flatDelete")
    public String deleteFlat(@RequestParam("flatId") int theId) {
        flatService.deleteById(theId);
        log.info("Deleted the flat with the id " + theId);
        return "redirect:/flats/flatList";
    }



}
