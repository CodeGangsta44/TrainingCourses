package ua.dovhopoliuk.springtask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    @RequestMapping("/header")
    public String header() {
        System.out.println("Header");
        return "./fragments/header";
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

    @RequestMapping("/users")
    public String userPage() {
        return "users";
    }

    @RequestMapping("/conferences")
    public String conferencesPage() {
        return "conferences";
    }

    @RequestMapping("/reports")
    public String reportsPage() {
        return "reports";
    }

    @RequestMapping("/")
    public String mainPage() {
        return "main";
    }

    @RequestMapping("/reportRequests")
    public String reportRequestsPage() {
        return "report_requests";
    }

    @RequestMapping("/notifications")
    public String notifications() {
        return "notifications";
    }

    @RequestMapping("/fragments/{directory}/{fileName}")
    public String getFragment(@PathVariable String directory, @PathVariable String fileName) {
        System.out.println("./fragments/" + directory + "/" + fileName);
        return "./fragments/" + directory + "/" + fileName;
    }
}
