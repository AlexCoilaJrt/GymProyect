package com.example.mstrainer.repository;

import com.example.mstrainer.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer, Integer> {
}
