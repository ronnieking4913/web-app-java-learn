package com.web_app.repositories;

import com.web_app.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // all crud database methods

}
