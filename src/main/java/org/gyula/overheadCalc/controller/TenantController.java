package org.gyula.overheadCalc.controller;

import lombok.extern.slf4j.Slf4j;
import org.gyula.overheadCalc.entity.A_tenant;
import org.gyula.overheadCalc.entity.Users;
import org.gyula.overheadCalc.service.FlatService;
import org.gyula.overheadCalc.service.TenantService;
import org.gyula.overheadCalc.service.UsersService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("userName", getUserName());
        model.addAttribute("tenantList", tenantService.findAll());
        log.info("Listed all the tenants in the database");
        return "tenantTemplate/tenantList";
    }

    @GetMapping("/addTenant")
    public String addTenant(Model model) {
        A_tenant newTenant = new A_tenant();
        model.addAttribute("userName", getUserName());
        model.addAttribute("tenant", newTenant);
        log.info("Adding new tenant data, calling the form page");
        return "tenantTemplate/tenant-form";
    }

    @PostMapping("/saveTenant")
    public String saveTenant(@ModelAttribute("tenant") A_tenant newTenant, Model model) {
        tenantService.save(newTenant);
        model.addAttribute("userName", getUserName());
        log.info("Saved the new tenant: " + newTenant.getFirstName() + " " + newTenant.getLastName());
        //use redirect to prevent duplicate submissions
        return "redirect:/tenants/tenantList";
    }

    @GetMapping("/tenantUpdate")
    public String updateTenant(@RequestParam("tenantId") int theId, Model model) {
        A_tenant tenantToUpdate = tenantService.findById(theId);
        model.addAttribute("userName", getUserName());
        model.addAttribute("tenant", tenantToUpdate);
        log.info("Going to update form with data of: " + tenantToUpdate.getFirstName() + " " + tenantToUpdate.getLastName());
        return "tenantTemplate/tenant-form";
    }

    @GetMapping("/tenantDelete")
    public String deleteTenant(@RequestParam("tenantId") int theId, Model model) {
        model.addAttribute("userName", getUserName());
        tenantService.deleteById(theId);
        log.info("Deleted the tenant with the id " + theId);
        return "redirect:/tenants/tenantList";
    }

    private String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Users myUser = usersService.findByUserName(currentPrincipalName);
        return myUser.getUsername();
    }

}
