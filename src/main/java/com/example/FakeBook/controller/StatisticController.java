package com.example.FakeBook.controller;

import com.example.FakeBook.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @GetMapping("/statistics")
    public String statistics(Model model){
        return "statistics";
    }

    @PostMapping("/statistics")
    public String viewStatistic(
            @RequestParam(required = false, defaultValue = "0") String timestamp,
            @RequestParam(required = false) boolean active,
            Model model
    ) throws InterruptedException {
        model.addAttribute("statistic", statisticService.getStatistic(timestamp, active));
        return "statistic";
    }

}
