package com.pentalog.KitKat.Repository;

import com.pentalog.KitKat.Entities.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByFirstNameContaining(String firstName);
    User findUserByEmail(String email);
    Optional<User> findUserByOauthToken(String oauthToken);
}
