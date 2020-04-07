package com.example.FakeBook.controler;

import com.example.FakeBook.domain.Statistics;
import com.example.FakeBook.repos.StatisticRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
            String timestamp,
            boolean active,
            Model model
    ){

        List<Statistics> statistic = statisticRepo.findByActive(active);

        model.addAttribute("statistic", statistic);
        return "statistic";
    }

}
