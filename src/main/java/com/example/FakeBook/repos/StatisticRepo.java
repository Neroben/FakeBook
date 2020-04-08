package com.example.FakeBook.repos;

import com.example.FakeBook.domain.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface StatisticRepo extends JpaRepository<Statistics, Long> {
    List<Statistics> findByActiveAndTimestampGreaterThanEqual(boolean active, Timestamp now);
}
