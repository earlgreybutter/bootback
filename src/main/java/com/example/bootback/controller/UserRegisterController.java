package com.example.bootback.controller;

import com.example.bootback.entity.Account;
import com.example.bootback.repository.AccountRepository;
import com.example.bootback.util.Hashing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserRegisterController {
    
    @Autowired
    AccountRepository accountRepository; 

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/register")
    @ResponseBody
    public String registerUser(@RequestBody Account newAccount){
        String username = newAccount.getUsername();
        String password = Hashing.hashingPassword(newAccount.getPassword());
        String email = newAccount.getEmail();

        if(username.equals("")||password.equals("")||email.equals("")){
            return "failed";
        }

        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setEmail(email);

        // 가입하려는 username 이 이미 존재하는 경우 
        if(accountRepository.findByUsername(username) != null){
            return "failed"; 
        }

        accountRepository.save(account);

        return "success";
    }
}