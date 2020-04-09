package com.example.FakeBook.controler;

import com.example.FakeBook.domain.Statistics;
import com.example.FakeBook.domain.User;
import com.example.FakeBook.repos.StatisticRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Controller
public class StatisticController {

    @Autowired
    private StatisticRepo statisticRepo;

    @GetMapping("/statistics")
    public String statistics(Model model){
        return "statistics";
    }

    @PostMapping("/statistics")
    public String viewStatistic(
            @RequestParam String timestamp,
            @RequestParam(required = false) boolean active,
            Model model
    ){
        Timestamp now = new Timestamp(Long.parseLong(timestamp));
        List<Statistics> statistic = statisticRepo.findByTimestampGreaterThanEqualOrderByTimestamp(now);

        HashMap<User, Statistics> arr = new HashMap<>();
        for (Statistics a:statistic) {
            arr.put(a.getUser(), a);
        }

        List<Statistics> path = new ArrayList<Statistics>(arr.values());
        List<Statistics> res = new ArrayList<Statistics>();

        for(int i = 0; i < path.size(); i++){
            if(path.get(i).isActive() == active)
                res.add(path.get(i));
        }

        model.addAttribute("statistic", res);
        return "statistic";
    }

}
