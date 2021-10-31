package com.stevedevblog.app.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(AdminController.PATH)
public class AdminController {

    public static final String PATH = "/admin";
    public static final String PAGE_TITLE = "Admin";

    @GetMapping
    public String getAdminPage(Model model) {
        model.addAttribute("pageTitle", PAGE_TITLE);
        return "admin/admin";
    }

}
