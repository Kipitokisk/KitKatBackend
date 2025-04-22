package com.pentalog.KitKat.Repository;

import com.pentalog.KitKat.Entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PositionRepository extends JpaRepository<Position, Integer> {

    Optional<Position> findByName(String name);
}
