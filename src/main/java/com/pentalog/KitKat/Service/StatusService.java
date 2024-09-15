package com.pentalog.KitKat.Service;

import com.pentalog.KitKat.Entities.Status;
import com.pentalog.KitKat.Repository.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatusService {
    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public Status saveStatus(Status status) {
        return statusRepository.save(status);
    }
    public Optional<Status> findByName(String name) {return  statusRepository.findByName(name);}
}
