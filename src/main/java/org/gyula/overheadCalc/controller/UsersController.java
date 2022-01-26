package org.gyula.overheadCalc.controller;

import lombok.extern.slf4j.Slf4j;
import org.gyula.overheadCalc.entity.A_tenant;
import org.gyula.overheadCalc.entity.Authorities;
import org.gyula.overheadCalc.entity.Users;
import org.gyula.overheadCalc.service.AuthoritiesService;
import org.gyula.overheadCalc.service.TenantService;
import org.gyula.overheadCalc.service.UsersService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/users")
public class UsersController {

//    FlatService flatService;
    TenantService tenantService;
    UsersService usersService;
    AuthoritiesService authoritiesService;
    List<String> roleList = Arrays.asList("ROLE_USER", "ROLE_ADMIN");


    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UsersController(TenantService tenantService, UsersService usersService, AuthoritiesService authoritiesService) {
        this.tenantService = tenantService;
        this.usersService = usersService;
        this.authoritiesService = authoritiesService;
    }

    @GetMapping("/userList")
    public String allUsers(Model model) {
        model.addAttribute("userName", getAuthUserName());
        model.addAttribute("userList", usersService.findAll());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Listed all the users in the database");
        return "userTemplate/userList";
    }

    @GetMapping("/addUser")
    public String addUser(Model model) {
        Users newUser = new Users();
        Authorities newAuthorities = null;
        newUser.setAuthorities(newAuthorities);
        model.addAttribute("roleList", roleList);
        model.addAttribute("userName", getAuthUserName());
        model.addAttribute("newUser", newUser);
        model.addAttribute("tenantList", tenantService.findAll());
        log.info("Adding new user data, calling the form page");
        return "userTemplate/user-form";
    }

    @GetMapping("user-error")
    public String userError(@RequestParam("errorList") BindingResult bindingResult, Model model) {
        model.addAttribute("errorList", bindingResult.getAllErrors());
        model.addAttribute("username", getAuthUserName());
        return "userTemplate/user-error";
    }

    @PostMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute("newUser") Users newUser, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            userError(bindingResult, model);
            return "userTemplate/user-error";
        }
        if (usersService.findByUserName(newUser.getUsername()) != null ) {
            bindingResult.rejectValue("username", "error.username", "This username has been taken already!");
            userError(bindingResult, model);
            return "userTemplate/user-error";
        }
        A_tenant newTenant = null;
        newUser.setPassword("{bcrypt}" + passwordEncoder.encode(newUser.getPassword()));
        newUser.getAuthorities().setUsername(newUser.getUsername());
        // if I do not add tenant (actually I add a dummy tenant anyway), I want to keep the tenant field empty
        // otherwise I would have several 1 ids in the tenant_id column, but it's a OneToOne connection
        if (newUser.getTenant().getId() == 1) {
            newTenant= null;
        }else {
            newTenant = tenantService.findById(newUser.getTenant().getId());
        }
        newUser.setTenant(newTenant);
        usersService.save(newUser);
        model.addAttribute("userName", getAuthUserName());
        model.addAttribute("userList", usersService.findAll());
        log.info("Saved the new user with username: " + newUser.getUsername());
        //should use redirect to prevent duplicate submissions?
        return "userTemplate/userList";
    }

    @GetMapping("/userUpdate")
    public String updateFlat(@RequestParam("userName") String userName, Model model) {
        Users updatedUser = usersService.findByUserName(userName);
        model.addAttribute("roleList", roleList);
        model.addAttribute("user", updatedUser);
        model.addAttribute("userName", getAuthUserName());
        log.info("Going to update form with data of: " + userName);
        return "userTemplate/user-form";
    }

    @GetMapping("/userDelete")
    public String deletUser(@RequestParam("userName") String userName, Model model) {
        model.addAttribute("userName", getAuthUserName());
        usersService.deleteByUserName(userName);
        model.addAttribute("userList", usersService.findAll());
        log.info("Deleted the user with the name " + userName);
        return "userTemplate/userList";
    }

    private String getAuthUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }


}
