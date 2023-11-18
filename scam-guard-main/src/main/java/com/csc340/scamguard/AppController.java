package com.csc340.scamguard;

import com.csc340.scamguard.business.BusinessService;
import com.csc340.scamguard.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Derek Fox
 */
@Controller
public class AppController {

    @Autowired
    BusinessService businessService;

    @Autowired
    UserService userService;

    @GetMapping(value = {"", "/", "/dashboard", "/home"})
    public String dashboard(Model model) {
        //who is logged in?
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("currentName", name);

        //if business, redirect to business dashboard
        if (businessService.getBusinessByTitle(name) != null) {
            return "business/menu";
        }

        //otherwise, redirect to default dashboard
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/403")
    public String _403() {
        return "403";
    }
}
