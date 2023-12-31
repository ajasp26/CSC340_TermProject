package com.csc340.scamguard.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Kenny Banks
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping({"", "/", "/menu"})
    public String userMenu(Model model) {
        String title = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("currentName", title);
        return "user/menu";
    }

    @GetMapping("/all")
    public String getAllUsers(Model model,
            @RequestParam(name = "continue",required = false) String cont) {
        model.addAttribute("userList", service.getAllUsers());
        return "user/list-users";
    }

    @GetMapping("/id={id}")
    public String getUser(@PathVariable long id, Model model) {
        model.addAttribute("user", service.getUser(id));
        return "user/user-detail";
    }

    @GetMapping("/delete/id={id}")
    public String deleteUser(@PathVariable long id, Model model) {
        service.deleteUser(id);
        return "redirect:/user/all";
    }

    @PostMapping("/create")
    public String createUser(User user, Model model) {
        if (service.getUserByUserName(user.getUserName()).isPresent()) {
            model.addAttribute("error", "User with that username already exists");
            return "user/new-user";
        }
        if (service.getUserByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("error", "User with that email already exists");
            return "user/new-user";
        }
        service.saveUser(user);
        return "redirect:/login";
    }

    @PostMapping("/update")
    public String updateUser(User user) {
        service.updateUser(user);
        return "redirect:/user/all";
    }

    @GetMapping("/new-user")
    public String newUserForm(Model model) {
        return "user/new-user";
    }

    @GetMapping("/update/id={id}")
    public String updateUserForm(@PathVariable long id, Model model) {
        model.addAttribute("user", service.getUser(id));
        return "user/update-user";
    }

    @GetMapping("/register")
    public String registerUserForm() {
        return "user/new-user";
    }
}
