package org.gyula.overheadCalc.controller;

import lombok.extern.slf4j.Slf4j;
import org.gyula.overheadCalc.entity.A_tenant;
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
@RequestMapping("/tenants")
public class TenantController {

    FlatService flatService;
    TenantService tenantService;
    UsersService usersService;

    public TenantController(FlatService flatService, TenantService tenantService, UsersService usersService) {
        this.flatService = flatService;
        this.tenantService = tenantService;
        this.usersService = usersService;
    }

    @GetMapping("/tenantList")
    public String allTenants(Model model) {
        model.addAttribute("userName", getAuthUserName());
        model.addAttribute("tenantList", tenantService.findAll());
        log.info("Listed all the tenants in the database");
        return "tenantTemplate/tenantList";
    }

    @GetMapping("/addTenant")
    public String addTenant(Model model) {
        A_tenant newTenant = new A_tenant();
        model.addAttribute("userName", getAuthUserName());
        model.addAttribute("tenant", newTenant);
        log.info("Adding new tenant data, calling the form page");
        return "tenantTemplate/tenant-form";
    }

    @GetMapping("tenant-error")
    public String tenantError(@RequestParam("errorList") BindingResult bindingResult, Model model) {
//        model.addAttribute("tenant", model.getAttribute("tenant"));
        model.addAttribute("errorList", bindingResult.getAllErrors());
        model.addAttribute("username", getAuthUserName());
        addTenant(model);
        return "tenantTemplate/tenant-error";
    }

    @PostMapping("/saveTenant")
    public String saveTenant(@Valid @ModelAttribute("newTenant") A_tenant newTenant, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
//            model.addAttribute("tenant", newTenant);
            tenantError(bindingResult, model);
            return "tenantTemplate/tenant-error";
        }
        tenantService.save(newTenant);
        model.addAttribute("userName", getAuthUserName());
        log.info("Saved the new tenant: " + newTenant.getFirstName() + " " + newTenant.getLastName());
        //use redirect to prevent duplicate submissions
        return "redirect:/tenants/tenantList";
    }

    @GetMapping("/tenantUpdate")
    public String updateTenant(@RequestParam("tenantId") int theId, Model model) {
        A_tenant tenantToUpdate = tenantService.findById(theId);
        model.addAttribute("userName", getAuthUserName());
        model.addAttribute("tenant", tenantToUpdate);
        log.info("Going to update form with data of: " + tenantToUpdate.getFirstName() + " " + tenantToUpdate.getLastName());
        return "tenantTemplate/tenant-form";
    }

    @GetMapping("/tenantDelete")
    public String deleteTenant(@RequestParam("tenantId") int theId, Model model) {
        model.addAttribute("userName", getAuthUserName());
        tenantService.deleteById(theId);
        log.info("Deleted the tenant with the id " + theId);
        return "redirect:/tenants/tenantList";
    }

    private String getAuthUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

}
