package com.example.FakeBook.controler;

import com.example.FakeBook.domain.Statistics;
import com.example.FakeBook.domain.User;
import com.example.FakeBook.repos.StatisticRepo;
import com.example.FakeBook.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;


@Controller
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private StatisticRepo statisticRepo;

    @GetMapping("/addUser")
    public String addUser(Model model){
        return "addUser";
    }

    @PostMapping("/addUser")
    public String regUser(
            User user,
            Statistics statistic,
            Model model
    ){
        User userFromDb = userRepo.findByEmail(user.getEmail());

        if(userFromDb != null){
            model.addAttribute("message", "Email exists!");
            return "/addUser";
        }

        user.setActive(false);


        userRepo.save(user);
        statistic.setUser(user);
        statistic.setActive(false);
        statistic.setTimestamp(new Timestamp(new Date().getTime()));
        statisticRepo.save(statistic);

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
        return "User";
    }

    @GetMapping("/editUser")
    public String editUser(){
        return "editUser";
    }

    @PostMapping("/editUser")
    public String edit(
            @RequestParam String id,
            @RequestParam boolean active,
            Model model
    ){
        Optional<User> userFromDb = userRepo.findById(Long.parseLong(id));

        if(!userFromDb.isPresent()){
            model.addAttribute("message", "Пользователя с таким ID не существует");
            return "/editUser";
        }

        if(userFromDb.get().isActive() == active){
            model.addAttribute("message", "Пользователя с таким ID имеет данную активность");
            return "/editUser";
        }

        userFromDb.get().setActive(active);


        userRepo.save(userFromDb.get());
        statisticRepo.save(new Statistics(userFromDb.get(), active, new Timestamp(new Date().getTime())));

        model.addAttribute("message", "Активность изменена");
        return "/editUser";
    }
}
