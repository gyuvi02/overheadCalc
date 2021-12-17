package org.gyula.overheadCalc.rest;

import org.gyula.overheadCalc.dao.TenantDAO;
import org.gyula.overheadCalc.dao.TenantDAOImpl;
import org.gyula.overheadCalc.entity.A_tenant;
import org.gyula.overheadCalc.service.TenantService;
import org.gyula.overheadCalc.service.TenantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class TenantRestController {
    //
//    // fast but bad - injecet TenantDAO (constructor injection)
//    private TenantDAO tenantDAO;
    TenantService tenantService;

    @Autowired
    public TenantRestController(TenantService theTenantService) {
        tenantService = theTenantService;
    }

    // expose "/tenants" and return list of tenants
    @GetMapping("/tenants")
    public List<A_tenant> findAll() {
        return tenantService.findAll();
    }

    // mappings for tenants/tenantId template
    @GetMapping("tenants/{tenantId}")
    public A_tenant getTenant(@PathVariable int tenantId) {
        A_tenant theTenant = tenantService.findById(tenantId);
        if (theTenant == null) {
            throw new RuntimeException("Tenant id not found: " + tenantId);
        }
        return theTenant;
    }

    // mappings for adding new tenant
    @PostMapping("/tenants")
    public A_tenant addTenant(@RequestBody A_tenant theTenant) {
        // setting id to 0 - maybe we receive an id in the POST
        // and in that case the method would just modify an existing tenant
        theTenant.setId(0);

        tenantService.save(theTenant);

        return theTenant;
    }

    // mappings for updating existing tenant
    @PutMapping("/tenants")
    public A_tenant updateTenant(@RequestBody A_tenant theTenant) {
        tenantService.save(theTenant);
        return theTenant;
    }

    // mappings for deleting existing tenant
    @DeleteMapping("/tenants/{tenantId}")
    public String deleteTenant(@PathVariable int tenantId) {
        if (tenantService.findById(tenantId) == null) {
            throw new RuntimeException("Tenant id not found: " + tenantId);
        }
        tenantService.deleteById(tenantId);
        return ("Deleted tenant id: " + tenantId);
    }
}
