package org.gyula.overheadCalc.controller;

import org.gyula.overheadCalc.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        System.out.println(tenantService.findAll());
        theModel.addAttribute("tenantList", tenantService.findAll());
        return "tenantList";
    }
}
