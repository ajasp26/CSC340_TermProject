package com.csc340.scamguard.alert;

import com.csc340.scamguard.business.Business;
import com.csc340.scamguard.business.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Derek Fox
 */
@Controller
@RequestMapping("/alert")
public class AlertController {

    @Autowired
    AlertService alertService;

    @Autowired
    BusinessService businessService;

    @GetMapping("/all")
    public String getAllAlerts(Model model) {
        model.addAttribute("alertList",
                alertService.getAllAlerts());
        return "alert/list-alerts";
    }

    @GetMapping("/search")
    public String getAlerts(Model model, @Param("keyword") String keyword) {
        model.addAttribute("alertList",
                alertService.getAllAlerts(keyword));
        model.addAttribute("keyword", keyword);
        return "alert/list-alerts";
    }

    @GetMapping("/id={alertId}")
    public String getAlert(@PathVariable long alertId, Model model) {
        model.addAttribute("alert",
                alertService.getAlert(alertId));
        return "alert/alert-detail";
    }

    @GetMapping("/delete/id={alertId}")
    public String deleteAlert(@PathVariable long alertId, Model model) {
        alertService.deleteAlert(alertId);
        return "redirect:/alert/all";
    }

    @PostMapping("/create")
    public String createAlert(Alert alert) {
        //Get the id of the business which just posted this alert and save to posted_by
        String title = SecurityContextHolder.getContext().getAuthentication().getName();
        Business b = businessService.getBusinessByTitle(title);
        long id = b.getId();
        alert.setPosted_by(id);

        alertService.saveAlert(alert);
        return "redirect:/alert/all";
    }

    @PostMapping("/update")
    public String updateAlert(Alert alert) {
        alert.setPosted_by(alertService.getAlert(alert.getId()).getPosted_by()); //still posted by same business

        alertService.saveAlert(alert);
        return "redirect:/alert/all";
    }

    @GetMapping("/new-alert")
    public String newAlertForm(Model model) {
        return "alert/new-alert";
    }

    @GetMapping("/update/id={alertId}")
    public String updateAlertForm(@PathVariable long alertId, Model model) {
        model.addAttribute("alert",
                alertService.getAlert(alertId));
        return "alert/update-alert";
    }
}
