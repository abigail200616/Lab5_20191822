package com.example.lab5_20191822.repository;

import com.example.lab5_20191822.entity.Locations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationsRepository extends JpaRepository<Locations, Integer> {
}
