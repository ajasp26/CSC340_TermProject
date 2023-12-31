package com.csc340.scamguard.scam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

/**
 * @author Kenny Banks
 */
@Controller
@RequestMapping("/scam")
public class ScamController {

    // TODO: USER VIEW ALERTS

    @Autowired
    ScamService scamService;

    @GetMapping("/all")
    public String getAllScams(Model model) {
        //only allow user or admin to create scam
        Collection<? extends GrantedAuthority> authorityList = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        boolean authority = authorityList.contains(new SimpleGrantedAuthority("USER")) || authorityList.contains(new SimpleGrantedAuthority("ADMIN"));
        model.addAttribute("authority", authority);

        model.addAttribute("scamList",
                scamService.getAllScams());
        return "scam/list-scams";
    }

    @GetMapping("/your-scams")
    public String getYourScams(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("scamList",
                scamService.getScamsFrom(username));
        return "scam/list-scams";
    }

    @GetMapping("/search")
    public String getScams(Model model, @Param("keyword") String keyword) {
        model.addAttribute("scamList",
                scamService.getAllScams(keyword));
        model.addAttribute("keyword", keyword);
        return "scam/list-scams";
    }

    @GetMapping("/id={scamId}")
    public String getScam(@PathVariable long scamId, Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Scam scam = scamService.getScam(scamId);
        model.addAttribute("scam",
                scam);

        scoreAttributes(model, scam);

        // condition determining if current user created scam or not
        boolean isCreator = username.equals(scam.getPosted_by());
        model.addAttribute("creator", isCreator);

        return "scam/scam-detail";
    }

    @GetMapping("/delete/id={scamId}")
    public String deleteScam(@PathVariable long scamId, Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Scam scam = scamService.getScam(scamId);

        if (username.equals(scam.getPosted_by())) {
            scamService.deleteScam(scamId);
        }

        return "redirect:/scam/all";
    }

    @PostMapping("/create")
    public String createScam(Scam scam) {
        // get name of user who posted scam and add it to scam
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        scam.setPosted_by(username);
        scam.setFlags(0);

        // set date to current date
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);

        scam.setPosted_on(formattedDate);

        scamService.saveScam(scam);
        return "redirect:/scam/all";
    }

    @PostMapping("/update")
    public String updateScam(Scam scam) {
        scam.setFlags(scam.getFlags());
        scam.setPosted_by(scam.getPosted_by());

        scamService.saveScam(scam);

        return "redirect:/scam/all";
    }

    @GetMapping("/new-scam")
    public String newScamForm(Model model) {
        return "scam/new-scam";
    }

    @GetMapping("/update/id={scamId}")
    public String updateScamForm(@PathVariable long scamId, Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Scam scam = scamService.getScam(scamId);

        if (username.equals(scam.getPosted_by())) {
            model.addAttribute("scam",
                    scam);
            return "scam/update-scam";
        } else {
            return "redirect:/scam/all";
        }
    }

    @GetMapping("/upvote/id={scamId}")
    public String upvoteScam(@PathVariable long scamId, Model model) {
        Scam scam = scamService.getScam(scamId);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if (scam.getUpvotes().contains(username)) {
            scam.getUpvotes().remove(username);
        } else {
            scam.getUpvotes().add(username);
        }

        if (scam.getDownvotes().contains(username)) {
            scam.getDownvotes().remove(username);
        }

        setScore(scam);

        scamService.saveScam(scam);

        return "redirect:/scam/id={scamId}";
    }

    @GetMapping("/downvote/id={scamId}")
    public String downvoteScam(@PathVariable long scamId, Model model) {
        Scam scam = scamService.getScam(scamId);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if (scam.getDownvotes().contains(username)) {
            scam.getDownvotes().remove(username);
        } else {
            scam.getDownvotes().add(username);
        }

        if (scam.getUpvotes().contains(username)) {
            scam.getUpvotes().remove(username);
        }

        setScore(scam);

        scamService.saveScam(scam);

        return "redirect:/scam/id={scamId}";
    }

    private void scoreAttributes(Model model, Scam scam) {
        int score = scam.getUpvotes().size() - scam.getDownvotes().size();
        model.addAttribute("score",
                score);

        model.addAttribute("green",
                score > 0);

        model.addAttribute("red",
                score < 0);
    }

    private void setScore(Scam scam) {
        scam.setScore(scam.getUpvotes().size() - scam.getDownvotes().size());
    }
}
