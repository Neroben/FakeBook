package com.example.FakeBook.controler;

import com.example.FakeBook.domain.User;
import com.example.FakeBook.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/addUser")
    public String addUser(Model model){
        model.addAttribute("idbool", false);
        return "addUser";
    }

    @PostMapping("/addUser")
    public String regUser(
            User user,
            Model model
    ){
        User userFromDb = userRepo.findByEmail(user.getEmail());

        if(userFromDb != null){
            model.addAttribute("message", "Email exists!");
            model.addAttribute("idbool", false);
            return "/addUser";
        }

        user.setActive(false);
        userRepo.save(user);
        model.addAttribute("message", "ID = " + user.getId());
        return "/addUser";
    }

    @GetMapping("/getUser")
    public String getInf(){
        return "getUser";
    }

}
