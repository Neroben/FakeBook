package com.example.FakeBook.repositories;

import com.example.FakeBook.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String Name);
}
