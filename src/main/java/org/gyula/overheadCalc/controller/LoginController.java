package org.gyula.overheadCalc.controller;

import lombok.extern.slf4j.Slf4j;
import org.gyula.overheadCalc.entity.A_tenant;
import org.gyula.overheadCalc.entity.Users;
import org.gyula.overheadCalc.service.UsersService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Slf4j
@Controller
public class LoginController {

    UsersService usersService;

    public LoginController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/")
    public String start(HttpServletRequest request) {
//        Locale currentLocale = request.getLocale();
//        String countryCode = currentLocale.getCountry();
//        String countryName = currentLocale.getDisplayCountry();
//        String langCode = currentLocale.getDisplayLanguage();
//        System.out.println("Country code: " + countryCode);
//        System.out.println("Country name: " + countryName);
//        System.out.println("Language code: " + langCode);
        log.info("Start page mapped");
        return "start";
    }

    @GetMapping("/login")
    public String login() {
        log.info("The login mapping was called");
        return "fancy-login";
    }

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal User user, Model model) {
        Users loggedUser = usersService.findByUserName(getAuthUserName());
        model.addAttribute("loggedTenant", loggedUser.getTenant());
        log.info("home page called");
        model.addAttribute("myUser", user);
        return "home";
    }

    //add mapping for /access-denied
    @GetMapping("/accessDenied")
    public String accessDenied(Model model) {
        model.addAttribute("userName", getAuthUserName());
        log.info("accessDenied called");
        return "accessDenied";
    }

//    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @GetMapping("/username")
    @ResponseBody
    public Object currentUserName(Authentication authentication) {
        return authentication.getPrincipal();
    }

    private String getAuthUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

}


