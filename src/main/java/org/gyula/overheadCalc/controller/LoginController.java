package org.gyula.overheadCalc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class LoginController {

//    @GetMapping("/")
//    public String start() {
//        log.info("Start page mapped");
//        return "greetings";
//    }

    @GetMapping("/login")
    public String login() {
        log.info("The login mapping was called");
        return "fancy-login";
    }

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal User user, Model model) {
        log.info("start page called");
        String username = user.getUsername();
        model.addAttribute("username", username);
        model.addAttribute("role", user.getAuthorities());
        return "home";
    }

    //add mapping for /access-denied
    @GetMapping("/accessDenied")
    public String accessDenied() {
        log.info("accessDenied called");
        return "accessDenied";
    }

//    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @GetMapping("/username")
    @ResponseBody
    public Object currentUserName(Authentication authentication) {
        return authentication.getPrincipal();
    }
}


