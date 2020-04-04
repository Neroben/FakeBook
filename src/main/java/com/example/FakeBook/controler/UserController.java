package com.example.FakeBook.controler;

import com.example.FakeBook.domain.User;
import com.example.FakeBook.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;


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

    @PostMapping("/getUser")
    public String getUser(
        String id,
        Model model
    ){ ;
        Long num = Long.parseLong(id);
        Optional<User> user = userRepo.findById(num);
        if(user.isPresent()) {
            model.addAttribute("name", user.get().getName());
            model.addAttribute("email", user.get().getEmail());
            model.addAttribute("urlImage", user.get().getUrlImage());
        }
        else
            model.addAttribute("name", "Не существует");
        return "user";
    }

}
