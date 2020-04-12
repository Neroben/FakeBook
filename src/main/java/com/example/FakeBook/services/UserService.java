package com.example.FakeBook.services;

import com.example.FakeBook.domain.Statistics;
import com.example.FakeBook.domain.User;
import com.example.FakeBook.repositories.StatisticRepo;
import com.example.FakeBook.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import static java.lang.Thread.sleep;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private StatisticRepo statisticRepo;

    public String addUser(User user, Statistics statistic) throws InterruptedException { //добавление пользователя
        User userFromDb = userRepo.findByEmail(user.getEmail());

        if(userFromDb != null){
            return "message, Email exists!";
        }

        user.setActive(false);

        sleep(5000);
        userRepo.save(user);
        statistic.setUser(user);
        statistic.setActive(false);
        statistic.setTimestamp(new Timestamp(new Date().getTime()));
        statisticRepo.save(statistic);

        return "ID = " + user.getId();
    }

    public String editUser(String id, boolean active) throws InterruptedException { //изменение активности пользователя
        Optional<User> userFromDb = userRepo.findById(Long.parseLong(id));

        if(!userFromDb.isPresent()){
            return "Пользователя с таким ID не существует";
        }

        if(userFromDb.get().isActive() == active){
            return "Пользователя с таким ID имеет данную активность: " + boolToActive(active);
        }
        userFromDb.get().setActive(active);

        sleep(5000);
        userRepo.save(userFromDb.get());
        statisticRepo.save(new Statistics(userFromDb.get(), active, new Timestamp(new Date().getTime())));
        return "Активность изменена: " + boolToActive(!active) + " -> " + boolToActive(active);
    }

    public void getUser(String id, Model model) throws InterruptedException {
        Long num = Long.parseLong(id);
        sleep(5000);
        Optional<User> user = userRepo.findById(num);
        if(user.isPresent()) {
            model.addAttribute("name", user.get().getName());
            model.addAttribute("email", user.get().getEmail());
            model.addAttribute("urlImage", user.get().getUrlImage());
        }
        else
            model.addAttribute("name", "Не существует");
    }

    private String boolToActive(boolean active){
        return active?"Online":"Offline";
    }
}
