package com.pentalog.KitKat.Repository;

import com.pentalog.KitKat.Entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {
}
