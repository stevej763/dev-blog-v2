package com.stevedevblog.mvp.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(AdminLoginController.PATH)
public class AdminLoginController {

    public static final String PATH = "/admin-login";

    @GetMapping
    public String getLoginPage(Model model) {
        model.addAttribute("pageTitle", "Login");
        return "admin/login";
    }

}
