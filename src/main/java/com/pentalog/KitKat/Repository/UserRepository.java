package com.pentalog.KitKat.Repository;

import com.pentalog.KitKat.Entities.User.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    List<User> findAllByFirstNameContainingAndLastNameContaining(String firstName, String lastName);
    User findUserByEmail(String email);
    Integer countByProjectIsNull();
    Optional<User> findUserByOauthToken(String oauthToken);
}
