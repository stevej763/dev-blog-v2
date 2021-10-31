package com.stevedevblog.app.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(LoginController.PATH)
public class LoginController {

    public static final String PATH = "/login";
    public static final String PAGE_TITLE = "Login";

    @GetMapping
    public String getLoginPage(Model model) {
        model.addAttribute("pageTitle", PAGE_TITLE);
        return "admin/login";
    }

}