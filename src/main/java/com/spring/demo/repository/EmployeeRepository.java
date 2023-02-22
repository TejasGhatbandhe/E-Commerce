package com.spring.demo.repository;

import com.spring.demo.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "Select emp from Employee emp")
    Optional<List<Employee>> getAllEmployees(Sort sort);
    Optional<List<Employee>> findByFirstNameOrLastName(String firstName , String lastName);

    @Query(value = "Select * from emplyoees where first_name=?1 and last_name=?2", nativeQuery = true)
    Optional<List<Employee>> findByName(String firstName, String lastname);

    @Query(value = "Select emp from emplyoee emp where emp,first_name=?1 and last_name=?2")
    Optional<List<Employee>> findByEmpName(String firstName, String lastname);

    @Query(value ="SELECT u FROM Employee U ORDER BY firstName")
    Page<Employee> findEmployeeswithPagination(Pageable pageable);
}
