package com.csc340.scamguard.admin;

import com.csc340.scamguard.business.Business;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService service;

    @GetMapping({"", "/", "/menu"})
    public String menu(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("currentName", name);
        return "admin/menu";
    }

    @GetMapping("/all")
    public String getAllAdmins(Model model, @RequestParam(name = "continue", required = false) String cont) {
        model.addAttribute("adminList", service.getAllAdmins());
        return "admin/list-admins";
    }

    @GetMapping("/id={id}")
    public String getAdmin(@PathVariable Long id, Model model) {
        model.addAttribute("admin", service.getAdmin(id));
        return "admin/admin-detail";
    }

    @GetMapping("/delete/id={id}")
    public String deleteAdmin(@PathVariable Long id) {
        service.deleteAdmin(id);
        return "redirect:/admin/all";
    }

    @PostMapping("/create")
    public String createAdmin(Admin admin) {

        service.saveAdmin(admin);
        return "redirect:/admin/all";
    }

    @PostMapping("/update")
    public String updateAdmin(Admin admin) {
        service.updateAdmin(admin);
        return "redirect:/admin/all";
    }

    @GetMapping("/new-admin")
    public String newAdminForm(Model model) {
        return "admin/new-admin";
    }

    @GetMapping("/update/id={id}")
    public String updateAdminForm(@PathVariable long id, Model model) {
        model.addAttribute("admin", service.getAdmin(id));
        return "admin/update-admin";
    }
}
