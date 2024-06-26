package com.pluralsight.conference.controller;

import com.pluralsight.conference.model.Account;
import com.pluralsight.conference.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("account")
    public String getRegistration(@ModelAttribute("account") Account account){
        return "account";
    }
    @PostMapping("account")
        public String addRegistration(@Valid  @ModelAttribute("account") Account account, BindingResult result){
             //check for errors
            //should verify that account and the user don't already exist
            //should verify valid email address
            //encrypt password
        account.setPassword(new BCryptPasswordEncoder().encode(account.getPassword()));
            //create account
        account = accountService.create(account);
            //fire off an event on creation
        return "redirect:account";
        }
}