package com.pentalog.KitKat.Repository;

import com.pentalog.KitKat.Entities.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByFirstNameContaining(String firstName);
    User findByEmail(String email);
}
