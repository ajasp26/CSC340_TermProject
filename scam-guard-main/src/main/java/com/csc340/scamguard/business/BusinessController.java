package com.csc340.scamguard.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author csc340-f23
 */
@Controller
@RequestMapping("/user")
public class BusinessController {

    @Autowired
    private BusinessService service;

    @GetMapping({"", "/"})
    public String userMenu(@RequestParam(name = "continue", required = false) String cont) {
        return "user/menu";
    }

    @GetMapping("/all")
    public String getAllUsers(Model model,
            @RequestParam(name = "continue",required = false) String cont) {
        model.addAttribute("userList", service.getAllBusinesses());
        return "user/list-users";
    }

    @GetMapping("/id={id}")
    public String getUser(@PathVariable long id, Model model) {
        model.addAttribute("user", service.getBusiness(id));
        return "user/user-detail";
    }

    @GetMapping("/delete/id={id}")
    public String deleteUser(@PathVariable long id, Model model) {
        service.deleteBusiness(id);
        return "redirect:/user/all";
    }

    @PostMapping("/create")
    public String createUser(Business business) {

        service.saveBusiness(business);
        return "redirect:/user/all";
    }

    @PostMapping("/update")
    public String updateUser(Business business) {
        service.updateBusiness(business);
        return "redirect:/user/all";
    }

    @GetMapping("/new-user")
    public String newUserForm(Model model) {
        return "user/new-user";
    }

    @GetMapping("/update/id={id}")
    public String updateUserForm(@PathVariable long id, Model model) {
        model.addAttribute("user", service.getBusiness(id));
        return "user/update-user";
    }

}
