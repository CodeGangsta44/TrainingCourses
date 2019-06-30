package ua.dovhopoliuk.springtask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/api")
    public String mainPage() {
        return "index.html";
    }

    @RequestMapping("/registration")
    public String registrationPage() {
        return "reg_form.html";
    }

    @RequestMapping("/login")
    public String loginPage() {return "login_form.html"; }

    @RequestMapping("/all_user")
    public String userPage() {
        return "all_users.html";
    }
}
