package com.csc340.scamguard.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Derek Fox
 */
@Controller
@RequestMapping("/business")
public class BusinessController {

    @Autowired
    private BusinessService service;

    @GetMapping({"", "/", "/menu"})
    public String menu(Model model) {
        String title = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("currentName", title);
        return "business/menu";
    }

    @GetMapping("/all")
    public String getAllBusinesses(Model model,
            @RequestParam(name = "continue", required = false) String cont) {
        model.addAttribute("businessList", service.getAllBusinesses());
        return "business/list-businesses";
    }

    @GetMapping("/id={id}")
    public String getBusiness(@PathVariable long id, Model model) {
        model.addAttribute("business", service.getBusiness(id));
        return "business/business-detail";
    }

    @GetMapping("/delete/id={id}")
    public String deleteBusiness(@PathVariable long id, Model model) {
        service.deleteBusiness(id);
        return "redirect:/business/all";
    }

    @PostMapping("/create")
    public String createBusiness(Business business) {

        service.saveBusiness(business);
        return "redirect:/login";
    }

    @PostMapping("/update")
    public String updateBusiness(Business business) {
        service.updateBusiness(business);
        return "redirect:/business/menu";
    }

    @GetMapping("/new-business")
    public String newBusinessForm(Model model) {
        return "business/new-business";
    }

    @GetMapping("/update/id={id}")
    public String updateBusinessForm(@PathVariable long id, Model model) {
        model.addAttribute("business", service.getBusiness(id));
        return "business/update-business";
    }

    @GetMapping("/register")
    public String registerBusinessForm() {
        return "business/new-business";
    }

}
