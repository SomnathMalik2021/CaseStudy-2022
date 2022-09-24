package com.training.services;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.entity.Employee;
import com.training.error.NotFoundException;
import com.training.factory.ConnectionFactory;
import com.training.implentations.EmployeesRepositoryImpl;
import com.training.utils.OutputFormatter;

public class EmployeeService {

	@SuppressWarnings("unused")
	private EmployeesRepositoryImpl repo;
	private Connection con;
	private OutputFormatter outputFormatter;
	private static final Logger logger = LogManager.getRootLogger(); 
	public EmployeeService() {
		super();
		this.con = ConnectionFactory.getMySqlConnection();
		this.repo = new EmployeesRepositoryImpl(con);
		this.outputFormatter=new OutputFormatter();
		
	}
	
	public void addDetails(Employee entity) {
		if(entity.equals(null)) {
			logger.error("A null object cant be added");
		}
		else {
			this.repo.save(entity);
			logger.info(outputFormatter.getJsonFormat(entity));
		}
		
	}
	
	public List<Employee> getEmployeesByFirstName(String firstName) throws NotFoundException{
		List<Employee>result=this.repo.findAll().stream()
				.filter((emp)->emp.getFirstName().equals(firstName))
				.collect(Collectors.toList());
		if(result.isEmpty()) {
			throw new NotFoundException("No Element Found");
		}
		logger.info(outputFormatter.getJsonFormat(result.toArray()));
		return result;
		
	}
	
	public List<List<String>> getEmployeesWithFirstNameAndPhoneNo() throws NotFoundException{
		
		List<List<String>> result=this.repo.findAll().stream()
				.map((emp)->Arrays.asList(emp.getFirstName(),emp.getPhoneNo()))
				.collect(Collectors.toList());
		logger.info(outputFormatter.getJsonFormat(result.toArray()));
		if(result.isEmpty()) {
			throw new NotFoundException("No Element Found");
		}
		return result;
		
	}
	public void updateEmailAndPhoneNumber(String oldEmail,String newEmail,String phoneNo) {
		String [] params = {newEmail,phoneNo};
		try {
			this.repo.updateByID(oldEmail, params);
		} catch (NotFoundException e) {
			logger.error(e.getClass()+" : "+e.getMessage());
		}
	}
	public void deleteByFirstName(String firstName) {
		try {
			this.repo.deleteByFirstName(firstName);
		} catch (NotFoundException e) {
			logger.error(e.getClass()+" : "+e.getMessage());
		}
	}
	public List<List<String>> getEmployeesWithFirstNameAndEmailByBirthDay(LocalDate dateOfBirth) throws NotFoundException{
		List<List<String>> result= this.repo.findAll().stream()
				.filter((emp)->emp.getDateOfBirth().getMonth().equals(dateOfBirth.getMonth())&&emp.getDateOfBirth().getDayOfMonth()==dateOfBirth.getDayOfMonth())
				.map((emp)->Arrays.asList(emp.getFirstName(),emp.getEmail()))
				.collect(Collectors.toList());
		if(result.isEmpty()) {
			throw new NotFoundException("No Element Found");
		}
		logger.info(outputFormatter.getJsonFormat(result.toArray()));
		return result;
		
	}
	public List<List<String>> getEmployeesWithFirstNameAndPhoneNoByweedingDate(LocalDate weddingDate) throws NotFoundException{
		List<List<String>> result=this.repo.findAll().stream()
				.filter((emp)->emp.getWeddingDate().getMonth().equals(weddingDate.getMonth())&&emp.getWeddingDate().getDayOfMonth()==weddingDate.getDayOfMonth())
				.map((emp)->Arrays.asList(emp.getFirstName(),emp.getEmail()))
				.collect(Collectors.toList());
		if(result.isEmpty()) {
			throw new NotFoundException("No Element Found");
		}
		logger.info(outputFormatter.getJsonFormat(result.toArray()));
		return result;
		
	}
	
	
	
}
