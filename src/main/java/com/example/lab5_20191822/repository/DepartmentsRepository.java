package com.example.lab5_20191822.repository;

import com.example.lab5_20191822.entity.Departments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentsRepository extends JpaRepository<Departments,Integer> {
}
