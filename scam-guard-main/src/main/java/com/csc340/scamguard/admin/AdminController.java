package com.csc340.scamguard.admin;

import com.csc340.scamguard.business.Business;
import com.csc340.scamguard.business.BusinessService;
import com.csc340.scamguard.scam.Scam;
import com.csc340.scamguard.scam.ScamService;
import com.csc340.scamguard.user.User;
import com.csc340.scamguard.user.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository; // Add the UserRepository

    @Autowired
    private ScamService scamService;

    @Autowired
    private BusinessService businessService;

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

    /**
     * Method to list all users in the system.
     */
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> users = userRepository.findAll(); // Fetch users from the database
        model.addAttribute("users", users);
        return "admin/userList";
    }

    /**
     * Method to delete a user by their ID.
     */
    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam Long id) {
        userRepository.deleteById(id); // Delete the user with the given ID
        return "redirect:/admin/users"; // Redirect back to the user list page
    }

    // Admin view for listing all scams
    @GetMapping("/scams")
    public String listScams(Model model) {
        List<Scam> scams = scamService.getAllScams(); // Fetches the list of scams
        model.addAttribute("scamList", scams); // Adds the list to the model
        return "admin/scamList"; // Returns the admin scam listing view
    }

// Admin action for deleting a scam
    @PostMapping("/scams/delete/{id}")
    public String deleteScam(@PathVariable Long id) {
        scamService.deleteScam(id); // Deletes the scam
        return "redirect:/admin/scams"; // Redirects back to the scam list
    }

// Admin form for editing a scam
    @GetMapping("/scams/edit/{id}")
    public String editScamForm(@PathVariable Long id, Model model) {
        Scam scam = scamService.getScam(id); // And assuming this method exists
        model.addAttribute("scam", scam);
        return "admin/scams/edit"; // Path to the Thymeleaf template for editing scams
    }

// Admin action for updating a scam
    @PostMapping("/scams/update")
    public String updateScam(@ModelAttribute Scam scam) {
        scamService.saveScam(scam); // And assuming this method exists
        return "redirect:/admin/scams";
    }

    // Admin view for listing all businesses
    @GetMapping("/business")
    public String listBusinesses(Model model) {
        List<Business> businesses = businessService.getAllBusinesses();
        model.addAttribute("businessList", businesses);
        return "admin/list-business"; // Path to the Thymeleaf template for listing businesses
    }

    // Admin view for business details
    @GetMapping("/business/id/{id}")
    public String viewBusiness(@PathVariable Long id, Model model) {
        Business business = businessService.getBusiness(id);
        model.addAttribute("business", business);
        return "admin/business-detail"; // Path to the Thymeleaf template for business details
    }

    // Admin action for deleting a business
    @GetMapping("/business/delete/id/{id}")
    public String deleteBusiness(@PathVariable Long id) {
        businessService.deleteBusiness(id);
        return "redirect:/admin/business";
    }

    // Admin form for creating a new business
    @GetMapping("/business/new")
    public String newBusinessForm() {
        return "admin/new-business"; // Path to the Thymeleaf template for creating a new business
    }

    // Admin action for saving a new business
    @PostMapping("/business/create")
    public String createBusiness(@ModelAttribute Business business) {
        businessService.saveBusiness(business);
        return "redirect:/admin/business";
    }

    // Admin form for editing an existing business
    @GetMapping("/business/edit/id/{id}")
    public String editBusinessForm(@PathVariable Long id, Model model) {
        Business business = businessService.getBusiness(id);
        model.addAttribute("business", business);
        return "admin/edit-business"; // Path to the Thymeleaf template for editing a business
    }

    // Admin action for updating a business
    @PostMapping("/business/update")
    public String updateBusiness(@ModelAttribute Business business) {
        businessService.updateBusiness(business);
        return "redirect:/admin/business";
    }

}
