package com.pentalog.KitKat.Repository;

import com.pentalog.KitKat.Entities.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByFirstNameContainingAndLastNameContaining(String firstName, String lastName);
    User findUserByEmail(String email);
    Integer countByProjectIsNull();
    Optional<User> findUserByOauthToken(String oauthToken);
}
