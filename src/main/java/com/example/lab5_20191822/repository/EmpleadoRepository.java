package com.example.lab5_20191822.repository;

import com.example.lab5_20191822.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Employees, Integer> {

    @Query(nativeQuery = true,value = "select * from employees where first_name = ?1")
    List<Employees> buscarPorNombre(String nombre);
}
