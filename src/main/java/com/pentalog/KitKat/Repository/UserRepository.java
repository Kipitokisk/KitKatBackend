package com.pentalog.KitKat.Repository;

import com.pentalog.KitKat.Entities.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByFirstNameContainingAAndLastNameContaining(String firstName, String lastName);
    User findUserByEmail(String email);
    Optional<User> findUserByOauthToken(String oauthToken);
    @Query(value = "SELECT u FROM User u " +
            "WHERE (:positionIds IS NULL OR u.position.positionId IN :positionIds) " +
            "AND (:seniorityIds IS NULL OR u.seniority.seniortyId IN :seniorityIds) " +
            "AND (:cityIds IS NULL OR u.city.cityId IN :cityIds) " +
            "AND (:roleIds IS NULL OR u.role.roleId IN :roleIds) " +
            "AND (:languageIds IS NULL OR " +
            "      (CONCAT(',', u.languages, ',') LIKE CONCAT('%,', :languageIds, ',%')))")
    List<User> filterUsers(
            @Param("positionIds") List<Integer> positionIds,
            @Param("seniorityIds") List<Integer> seniorityIds,
            @Param("cityIds") List<Integer> cityIds,
            @Param("roleIds") List<Integer> roleIds,
            @Param("languageIds") String languageIds ); // Expecting a comma-separated string of language IDs
}
