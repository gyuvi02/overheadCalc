package org.gyula.overheadCalc.controller;

import lombok.extern.slf4j.Slf4j;
import org.gyula.overheadCalc.entity.A_flat;
import org.gyula.overheadCalc.entity.A_gas_meter;
import org.gyula.overheadCalc.entity.A_tenant;
import org.gyula.overheadCalc.entity.Users;
import org.gyula.overheadCalc.service.GasService;
import org.gyula.overheadCalc.service.UsersService;
import org.gyula.overheadCalc.util.CurrentData;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("me")
public class OverheadsController {

    UsersService usersService;
//    GasService gasService;
//    GasService gasService;

    public OverheadsController(UsersService usersService, GasService gasService) {
        this.usersService = usersService;
//        this.gasService = gasService;
    }

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal User user, Model model) {
        Users loggedUser = usersService.findByUserName(getAuthUserName());
        model.addAttribute("loggedTenant", loggedUser.getTenant());
        log.info("home page called");
//        A_gas_meter newGas = new A_gas_meter(110000);
//        gasService.save(newGas);
        model.addAttribute("myUser", user);
        return "me/home";
    }

    @GetMapping("/addOverheadsData")
    public String addOverheadsData(Model model) {
        Users myUser = usersService.findByUserName(getAuthUserName());
        CurrentData currentData = new CurrentData();
        currentData.createData(myUser.getTenant().getFlats().get(0));
        model.addAttribute("userName", getAuthUserName());
//        model.addAttribute("myUser", myUser);
        model.addAttribute("overheadsData", currentData);
//        model.addAttribute("tenantList", tenantService.findAll());
        log.info("Adding new overheads data, calling the form page");
        return "me/addOverheads-form";
    }


//    @PostMapping("/saveOverheads")
//    public String saveUser(@ModelAttribute("username") Users newUser, Model model) {
//        usersService.save(newUser);
//        model.addAttribute("userName", getAuthUserName());
//        log.info("Saved overheads data for the flat: " + newUser.getTenant().getFlats().get(0).getAddress());
//        //use redirect to prevent duplicate submissions
//        return "redirect:/me/home";
//    }


    private String getAuthUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }


}
