package com.example.lab5_20191822.repository;

import com.example.lab5_20191822.dto.EmployeesDto;
import com.example.lab5_20191822.entity.Employees;
import com.example.lab5_20191822.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Employees, Integer> {


    @Query(nativeQuery = true,value = "select e.first_name as firstName, e.last_name as lastName, j.job_title as jobTitle, \n" +
            "d.department_name as departmentName, l.city as city, e.employee_id as employeeId from locations l \n" +
            "inner join  departments d on (l.location_id = d.location_id)\n" +
            "inner join  employees e on (e.department_id = d.department_id)\n" +
            "inner join jobs j on (j.job_id = e.job_id)" +
            "order by employeeId")
    List<EmployeesDto> listarEmpleados();

    @Query(nativeQuery = true,value = "select  e.first_name as firstName, e.last_name as lastName, " +
            "j.job_title as jobTitle ,d.department_name as departmentName, l.city as city, e.employee_id as employeeId" +
            " from locations l \n" +
            "inner join  departments d on (l.location_id = d.location_id)\n" +
            "inner join  employees e on (e.department_id = d.department_id)\n" +
            "inner join jobs j on (j.job_id = e.job_id)\n" +
            "where e.first_name like %?1% or e.last_name like %?1% \n" +
            " or j.job_title like %?1%  or d.department_name like %?1% or l.city like %?1% ")
    List<EmployeesDto> buscarTexto(String name);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "update employees e , departments d set e.first_name =?1, e.last_name =?2 ,\n" +
            "e.job_id =?3, e.department_id =?4,d.location_id =?5 \n" +
            "where e.employee_id =?6  and  (e.department_id = d.department_id) ")
    void guardar(String firstName, String lastName,String job, Integer department,
                         Integer city, Integer employeeId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "insert into employees (first_name , last_name, email, password,\n" +
            "hire_date, job_id, salary, manager_id, department_id, enabled)\n" +
            "values (?1, ?2, ?3, sha2(?4, 256), current_timestamp(), ?5, ?6, ?7, ?8,1)")
    void guardarNuevo(String firstName, String lastName,String email,String password, String job, Double salary,
                      Integer manager, Integer department);








}
