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
            if(user.get().isActive())
                model.addAttribute("active", "Активен");
            else
                model.addAttribute("active", "Неактивен");
        }
        else
            model.addAttribute("name", "Не существует");
        return "user";
    }

    @GetMapping("/editUser")
    public String editUser(){
        return "editUser";
    }

    @PostMapping("/editUser")
    public String edit(
            String id,
            boolean active,
            Model model
    ) {
        Optional<User> user = userRepo.findById(Long.parseLong(id));
        if (user.isPresent()) {
            if (user.get().isActive() == active) {
                model.addAttribute("message", "Активность установлена");
                return "editUser";
            }
            user.get().setActive(active);
            userRepo.save(user.get());
            model.addAttribute("message", "Активность изменена");
            return "editUser";
        }
        else{
            model.addAttribute("message", "Пользователя не существует");
            return "editUser";
        }
    }

}
