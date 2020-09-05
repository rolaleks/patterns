package ru.geekbrains.atm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.atm.entity.User;
import ru.geekbrains.atm.policy.UserTransactionPolicy;

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserTransactionPolicy userTransactionPolicy;

    @GetMapping("login")
    public String login() {
        return "user/login";
    }


    @GetMapping("withdraw")
    public String withdraw()
    {

        return "user/withdraw";
    }


    @PostMapping("withdraw")
    public String createWithdraw(@AuthenticationPrincipal User user, @RequestParam("amount") float amount)
    {

        userTransactionPolicy.createWithdrawTransaction(user.getId(), amount);
        return "user/withdraw";
    }

    @GetMapping("deposit")
    public String deposit() {

        return "user/deposit";
    }


    @PostMapping("deposit")
    public String createDeposit(@AuthenticationPrincipal User user, @RequestParam("amount") float amount)
    {

        userTransactionPolicy.createDepositTransaction(user.getId(), amount);
        return "user/withdraw";
    }
}
