package org.gyula.overheadCalc.controller;

import lombok.extern.slf4j.Slf4j;
import org.gyula.overheadCalc.entity.A_flat;
import org.gyula.overheadCalc.service.FlatService;
import org.gyula.overheadCalc.service.TenantService;
import org.gyula.overheadCalc.service.UsersService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/flats")
public class FlatController {

    FlatService flatService;
    TenantService tenantService;
    UsersService usersService;

    public FlatController(FlatService flatService, TenantService tenantService, UsersService usersService) {
        this.flatService = flatService;
        this.tenantService = tenantService;
        this.usersService = usersService;
    }

    @GetMapping("/flatList")
    public String allFlats(Model model) {
        model.addAttribute("userName", getAuthUserName());
        model.addAttribute("flatList", flatService.findAll());
        log.info("Listed all the flats in the database");
        return "flatTemplate/flatList";
    }

    @GetMapping("/addFlat")
    public String addFlat(Model model) {
        A_flat newFlat = new A_flat();
        model.addAttribute("userName", getAuthUserName());
        model.addAttribute("flat", newFlat);
        model.addAttribute("tenantList", tenantService.findAll());
        log.info("Adding new flat data, calling the form page");
        return "flatTemplate/flat-form";
    }

    @GetMapping("flat-error")
    public String flatError(@RequestParam("errorList") BindingResult bindingResult, Model model) {
        model.addAttribute("errorList", bindingResult.getAllErrors());
        model.addAttribute("username", getAuthUserName());
        return "flatTemplate/flat-error";
    }

    @PostMapping("/saveFlat")
    public String saveFlat(@Valid @ModelAttribute("newFlat") A_flat newFlat, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            flatError(bindingResult, model);
            return "flatTemplate/flat-error";
        }
        flatService.save(newFlat);
        model.addAttribute("userName", getAuthUserName());
        log.info("Saved the new flat: " + newFlat.getAddress());
        //use redirect to prevent duplicate submissions
        return "redirect:/flats/flatList";
    }

    @GetMapping("/flatUpdate")
    public String updateFlat(@RequestParam("flatId") int theId, Model model, Authentication authentication) {
        A_flat updatedFlat = flatService.findById(theId);
        model.addAttribute("userName", getAuthUserName());
        model.addAttribute("flat", updatedFlat);
        model.addAttribute("tenantList", tenantService.findAll());
        model.addAttribute("authentication", authentication);
        log.info("Going to update form with data of: " + updatedFlat.getAddress());
        return "flatTemplate/flat-form";
    }

    @GetMapping("/flatDelete")
    public String deleteFlat(@RequestParam("flatId") int theId, Model model) {
        model.addAttribute("userName", getAuthUserName());
        flatService.deleteById(theId);
        log.info("Deleted the flat with the id " + theId);
        return "redirect:/flats/flatList";
    }

    private String getAuthUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

}
