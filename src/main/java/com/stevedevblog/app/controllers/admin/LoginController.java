package com.stevedevblog.app.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(LoginController.PATH)
public class LoginController {

    public static final String PATH = "/login";
    public static final String PAGE_TITLE = "Login";
    public static final String LOGIN_ERROR_MESSAGE = "Incorrect Credentials";

    @GetMapping
    public String getLoginPage(@RequestParam(required = false) String authError, Model model) {
        System.out.println(authError);
        if (authError != null) {
            model.addAttribute("errorMessage", LOGIN_ERROR_MESSAGE);
        }
        model.addAttribute("pageTitle", PAGE_TITLE);
        return "admin/login";
    }



}