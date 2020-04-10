package com.example.FakeBook.Services;

import com.example.FakeBook.domain.Statistics;
import com.example.FakeBook.domain.User;
import com.example.FakeBook.repos.StatisticRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Thread.sleep;

@Service
public class StatisticService {

    @Autowired
    private StatisticRepo statisticRepo;

    public List<Statistics> getStatistic(String timestamp, boolean active) throws InterruptedException {
        Timestamp now = new Timestamp(Long.parseLong(timestamp));
        sleep(5000);
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

        return res;
    }

}
