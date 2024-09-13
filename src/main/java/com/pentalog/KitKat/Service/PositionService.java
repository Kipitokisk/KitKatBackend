package com.pentalog.KitKat.Service;

import com.pentalog.KitKat.Entities.Position;
import com.pentalog.KitKat.Repository.PositionRepository;
import org.springframework.stereotype.Service;

@Service
public class PositionService {
    private final PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public Position savePosition(Position position) {
        return positionRepository.save(position);
    }
}
