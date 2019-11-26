package com.societegenerale.katatest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.societegenerale.katatest.model.Employee;


/**
 * This is repository class.
 * @author Kalpesh Kotkar
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	

}
