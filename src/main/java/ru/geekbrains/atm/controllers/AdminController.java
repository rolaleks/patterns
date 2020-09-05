package ru.geekbrains.atm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.atm.policy.LogPolicy;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @Autowired
    private LogPolicy logPolicy;

    @GetMapping("logs")
    public String login(Model model) {

        model.addAttribute("logs", logPolicy.getLogs());
        return "admin/logs";
    }
}
