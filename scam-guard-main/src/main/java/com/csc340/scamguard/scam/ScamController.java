package com.csc340.scamguard.scam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Kenny Banks
 */
@Controller
@RequestMapping("/scam")
public class ScamController {

    @Autowired
    ScamService scamService;

    @GetMapping("/all")
    public String getAllScams(Model model) {
        model.addAttribute("scamList",
                scamService.getAllScams());
        return "scam/list-scams";
    }

    @GetMapping("/search")
    public String getScams(Model model, @Param("keyword") String keyword) {
        model.addAttribute("scamList",
                scamService.getAllScams());
        model.addAttribute("keyword", keyword);
        return "scam/list-scams";
    }

    @GetMapping("/id={scamId}")
    public String getScam(@PathVariable long scamId, Model model) {
        model.addAttribute("scam",
                scamService.getScam(scamId));
        return "scam/scam-detail";
    }

    @GetMapping("/delete/id={scamId}")
    public String deleteScam(@PathVariable long scamId, Model model) {
        scamService.deleteScam(scamId);
        return "redirect:/scam/all";
    }

    @PostMapping("/create")
    public String createScam(Scam scam) {

        scamService.saveScam(scam);
        return "redirect:/scam/all";
    }

    @PostMapping("/update")
    public String updateScam(Scam scam) {
        scamService.saveScam(scam);
        return "redirect:/scam/all";
    }

    @GetMapping("/new-scam")
    public String newScamForm(Model model) {
        return "scam/new-scam";
    }

    @GetMapping("/update/id={scamId}")
    public String updateScamForm(@PathVariable long scamId, Model model) {
        model.addAttribute("scam",
                scamService.getScam(scamId));
        return "scam/update-scam";
    }
}
