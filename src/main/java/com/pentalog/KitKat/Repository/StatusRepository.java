package com.pentalog.KitKat.Repository;

import com.pentalog.KitKat.Entities.Status;
import com.pentalog.KitKat.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {

}
