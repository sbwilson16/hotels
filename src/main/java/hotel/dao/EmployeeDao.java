package hotel.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import hotel.entity.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long> {

}