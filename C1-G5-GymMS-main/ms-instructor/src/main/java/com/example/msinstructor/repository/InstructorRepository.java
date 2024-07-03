package com.example.msinstructor.repository;

import com.example.msinstructor.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository <Instructor, Integer> {
}
