package com.example.Restaurant.Repositories;

import com.example.Restaurant.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
