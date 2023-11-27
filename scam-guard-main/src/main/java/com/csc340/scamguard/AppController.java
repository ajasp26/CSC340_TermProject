package com.csc340.scamguard;

import com.csc340.scamguard.admin.AdminService;
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

    @Autowired
    AdminService adminService;

    @GetMapping(value = {"", "/", "/dashboard", "/home"})
    public String dashboard(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("currentName", name);

        if (businessService.getBusinessByTitle(name) != null) {
            return "business/menu";
        } else if (userService.getUserByUserName(name) != null) {
            return "user/menu";
        } else if (adminService.getAdminByName(name) != null){
            return "admin/menu";
        }

        throw new IllegalStateException("user has no role");
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
