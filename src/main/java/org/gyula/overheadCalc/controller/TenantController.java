package org.gyula.overheadCalc.controller;

import lombok.extern.slf4j.Slf4j;
import org.gyula.overheadCalc.entity.A_tenant;
import org.gyula.overheadCalc.service.TenantService;
import org.gyula.overheadCalc.service.TenantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/tenants")
public class TenantController {

    TenantService tenantService;

    @Autowired
    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @GetMapping("/tenantList")
    public String allTenants(Model theModel) {
        theModel.addAttribute("tenantList", tenantService.findAll());
        log.info("Listed all the tenants in the database");
        return "tenantTemplate/tenantList";
    }

    @GetMapping("/addTenant")
    public String addTenant(Model model) {
        A_tenant newTenant = new A_tenant();
        model.addAttribute("tenant", newTenant);
        log.info("Adding new tenant data, calling the form page");
        return "tenantTemplate/tenant-form";
    }

    @PostMapping("/saveTenant")
    public String saveTenant(@ModelAttribute("tenant") A_tenant newTenant) {
        tenantService.save(newTenant);
        log.info("Saved the new tenant: " + newTenant.getFirstName() + " " + newTenant.getLastName());
        //use redirect to prevent duplicate submissions
        return "redirect:/tenants/tenantList";
    }

    @GetMapping("/tenantUpdate")
    public String updateTenant(@RequestParam("tenantId") int theId, Model model) {
        A_tenant updatedTenant = tenantService.findById(theId);
        model.addAttribute("tenant", updatedTenant);
        log.info("Going to update form with data of: " + updatedTenant.getFirstName() + " " + updatedTenant.getLastName());
        return "tenantTemplate/tenant-form";
    }

    @GetMapping("/tenantDelete")
    public String deleteTenant(@RequestParam("tenantId") int theId) {
        tenantService.deleteById(theId);
        log.info("Deleted the tenant with the id " + theId);
        return "redirect:/tenants/tenantList";
    }
}
