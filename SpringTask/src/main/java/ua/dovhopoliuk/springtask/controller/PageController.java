package ua.dovhopoliuk.springtask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
    @RequestMapping("/api")
    public String mainPage() {
        return "index.html";
    }

    @RequestMapping("/registration")
    public String registrationPage() {
        return "registration";
    }

    @RequestMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);

        return "login";
    }

    @RequestMapping("/all_users")
    public String userPage() {
        return "all_users";
    }

    @RequestMapping("/conferences")
    public String conferencesPage() {
        return "conferences";
    }

    @RequestMapping("/reports")
    public String reportsPage() {
        return "reports";
    }
}
