package com.pentalog.KitKat.Service;

import com.pentalog.KitKat.Entities.Status;
import com.pentalog.KitKat.Repository.StatusRepository;
import org.springframework.stereotype.Service;

@Service
public class StatusService {
    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public Status saveStatus(Status status) {
        return statusRepository.save(status);
    }
}
