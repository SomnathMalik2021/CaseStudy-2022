package com.training.ifcs;

import com.training.entity.Employee;
import com.training.error.NotFoundException;

public interface EmployeesRepository extends Repository<Employee, String> {

	void deleteByFirstName(String firstName) throws NotFoundException;
	
}
