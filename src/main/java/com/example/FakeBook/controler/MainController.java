package com.example.FakeBook.controler;

import com.example.FakeBook.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String home(@RequestParam(name="name", required=false, defaultValue="World") String name , Model model){
        model.addAttribute("name", name);
        return "home";
    }

}
