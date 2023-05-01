package com.example.lab5_20191822.repository;

import com.example.lab5_20191822.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobsRepository extends JpaRepository<Jobs, String> {
}
