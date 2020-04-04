package com.example.FakeBook.repos;

import com.example.FakeBook.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String Name);
}
