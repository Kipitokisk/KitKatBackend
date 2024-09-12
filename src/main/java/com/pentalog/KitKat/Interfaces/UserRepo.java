package com.pentalog.KitKat.Interfaces;

import com.pentalog.KitKat.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}
