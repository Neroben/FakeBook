package com.example.FakeBook.controller;

import com.example.FakeBook.services.UserService;
import com.example.FakeBook.domain.Statistics;
import com.example.FakeBook.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/addUser")
    public String addUser(Model model){
        return "addUser";
    }

    @PostMapping("/addUser") //регистрация пользователя
    public String regUser(
            User user,
            Statistics statistic,
            Model model
    ) throws InterruptedException {
        model.addAttribute("message", userService.addUser(user, statistic));
        return "/addUser";
    }

    @GetMapping("/getUser")
    public String getInf(){
        return "getUser";
    }

    @PostMapping("/getUser") //получение персональных данных пользователя
    public String getUser(
        String id,
        Model model
    ) throws InterruptedException { ;
        userService.getUser(id, model);
        return "User";
    }

    @GetMapping("/editUser")
    public String editUser(){
        return "editUser";
    }

    @PostMapping("/editUser") //изменить активность пользователя
    public String edit(
            @RequestParam String id,
            @RequestParam(required = false) boolean active,
            Model model
    ) throws InterruptedException {
        model.addAttribute("message", userService.editUser(id, active));
        return "/editUser";
    }
}
