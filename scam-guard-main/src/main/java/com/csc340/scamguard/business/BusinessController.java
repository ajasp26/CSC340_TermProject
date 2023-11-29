package com.csc340.scamguard.business;

import com.csc340.scamguard.scam.Scam;
import com.csc340.scamguard.scam.ScamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Derek Fox
 */
@Controller
@RequestMapping("/business")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @Autowired
    private ScamService scamService;

    @GetMapping({"", "/", "/menu"})
    public String menu(Model model) {
        String title = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("currentName", title);
        return "business/menu";
    }

    @GetMapping("/all")
    public String getAllBusinesses(Model model,
            @RequestParam(name = "continue",required = false) String cont) {
        model.addAttribute("businessList", businessService.getAllBusinesses());
        return "business/list-businesses";
    }

    @GetMapping("/id={id}")
    public String getBusiness(@PathVariable long id, Model model) {
        model.addAttribute("business", businessService.getBusiness(id));
        return "business/business-detail";
    }

    @GetMapping("/delete/id={id}")
    public String deleteBusiness(@PathVariable long id, Model model) {
        businessService.deleteBusiness(id);
        return "redirect:/business/all";
    }

    @PostMapping("/create")
    public String createBusiness(Business business, Model model) {
        if (businessService.getBusinessByTitle(business.getTitle()).isPresent()) {
            model.addAttribute("error", "Business with that title already exists");
            return "business/new-business";
        }
        if (businessService.getBusinessByEmail(business.getEmail()).isPresent()) {
            model.addAttribute("error", "Business with that email already exists");
            return "business/new-business";
        }

        businessService.saveBusiness(business);
        return "redirect:/login";
    }

    @PostMapping("/update")
    public String updateBusiness(Business business) {
        businessService.updateBusiness(business);
        return "redirect:/business/menu";
    }

    @GetMapping("/new-business")
    public String newBusinessForm(Model model) {
        return "business/new-business";
    }

    @GetMapping("/update/id={id}")
    public String updateBusinessForm(@PathVariable long id, Model model) {
        model.addAttribute("business", businessService.getBusiness(id));
        return "business/update-business";
    }

    @GetMapping("/register")
    public String registerBusinessForm() {
        return "business/new-business";
    }

    @GetMapping("/analytics")
    public String analytics(Model model) {
        String currentTitle = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("currentName", currentTitle);
        List<Scam> scamsOfBusiness =
                scamService.getAllScamsByBusinessTitle(
                        currentTitle);
        Map<String, Integer> scamDateCount = new HashMap<>();
        for (Scam scam : scamsOfBusiness) {
           scamDateCount.put(scam.getPosted_on(),
                   scamDateCount.getOrDefault(scam.getPosted_on(), 0) + 1);
        }
        model.addAttribute("scamDateCount", scamDateCount);
        return "business/analytics";
    }

}
