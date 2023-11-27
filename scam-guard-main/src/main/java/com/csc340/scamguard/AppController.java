package com.csc340.scamguard;

import com.csc340.scamguard.admin.AdminService;
import com.csc340.scamguard.business.BusinessService;
import com.csc340.scamguard.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

/**
 * Controller for the main application.
 * @author Derek Fox
 */
@Controller
public class AppController {

    @Autowired
    BusinessService businessService;

    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;

    /**
     * Returns the dashboard page for the current user.
     */
    @GetMapping(value = {"", "/", "/dashboard", "/home"})
    public String dashboard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String name = auth.getName();
        model.addAttribute("currentName", name);

        /*
         * Get the current user's authority and return the appropriate dashboard.
         */
        Collection<? extends GrantedAuthority> authList = auth.getAuthorities();
        if (authList.contains(new SimpleGrantedAuthority("BUSINESS"))) {
            return "business/menu";
        } else if (authList.contains(new SimpleGrantedAuthority("USER"))) {
            return "user/menu";
        } else if (authList.contains(new SimpleGrantedAuthority("ADMIN"))){
            return "admin/menu";
        }

        throw new IllegalStateException("user has no authority");
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
