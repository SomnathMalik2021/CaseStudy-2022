package com.training.utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.entity.Employee;
import com.training.error.NotFoundException;
import com.training.services.EmployeeService;

public class MenuManager {

	private Scanner scanner;
	private EmployeeService employeeService;
	private static final Logger logger = LogManager.getRootLogger() ; 
	public MenuManager() {
		super();
		this.scanner=new Scanner(System.in);
		this.employeeService=new EmployeeService();
	}
	
	public void closeScanner() {
		this.scanner.close();
	}
	
	public void getManagedOutPut(int input) {
		
		switch(input) {
		case 1:
		{
			System.out.println("Enter details of employee");
			System.out.print("Enter First Name: ");
			String firstName=scanner.nextLine();
			System.out.println();
			System.out.print("Enter Last Name: ");
			String lastName=scanner.nextLine();
			System.out.println();
			System.out.print("Enter Address: ");
			String address=scanner.nextLine();
			System.out.println();
			System.out.print("Enter Email: ");
			String email=scanner.nextLine();
			System.out.println();
			System.out.print("Enter PhoneNo: ");
			String phoneNo=scanner.nextLine();
			System.out.println();
			System.out.println("Enter Date Of Birth(YYYY-MM-DD): ");
			LocalDate dateOfBirth=DateChecker.getCorrectDate(scanner);
			System.out.println();
			System.out.println("Enter Wedding Date(YYYY-MM-DD): ");
			LocalDate weddingDate=DateChecker.getCorrectDate(scanner);
		
			System.out.println();
			Employee employee = new Employee(firstName, lastName, address, email, phoneNo, dateOfBirth, weddingDate);
			this.employeeService.addDetails(employee);
		}
		break;
		case 2:
		{
			System.out.print("Enter First Name: ");
			String firstName=scanner.nextLine();
			try {
				this.employeeService.getEmployeesByFirstName(firstName);
			} catch (NotFoundException e) {
				logger.error(e.getClass()+" : "+e.getMessage());
			}
		}
		break;
		case 3:
		{
			try {
				this.employeeService.getEmployeesWithFirstNameAndPhoneNo();
			} catch (NotFoundException e) {
				logger.error(e.getCause()+""+e.getMessage());
			}
		}
		break;
		case 4:{
			System.out.print("Enter Old Email: ");
			String oldEmail=scanner.nextLine();
			System.out.println();
			System.out.print("Enter New Email: ");
			String newEmail=scanner.nextLine();
			System.out.println();
			System.out.print("Enter phoneNo: ");
			String phoneNo=scanner.nextLine();
			System.out.println();
			this.employeeService.updateEmailAndPhoneNumber(oldEmail, newEmail, phoneNo);
		}
		break;
		case 5:
		{
			System.out.print("Enter First Name: ");
			String firstName=scanner.nextLine();
			this.employeeService.deleteByFirstName(firstName);
		}
		break;
		case 6:
		{
			System.out.println("Enter Date Of Birth(YYYY-MM-DD): ");
			LocalDate dateOfBirth=DateChecker.getCorrectDate(scanner);
			System.out.println();
			try {
				this.employeeService.getEmployeesWithFirstNameAndEmailByBirthDay(dateOfBirth);
			} catch (NotFoundException e) {
				logger.error(e.getCause()+""+e.getMessage());
			}
		}
		break;
		case 7:
		{
			System.out.println("Enter Wedding Date(YYYY-MM-DD): ");
			LocalDate weddingDate=DateChecker.getCorrectDate(scanner);
			System.out.println();
			try {
				this.employeeService.getEmployeesWithFirstNameAndPhoneNoByweedingDate(weddingDate);
			} catch (NotFoundException e) {
				logger.error(e.getCause()+""+e.getMessage());
			}
		}
		break;
		default:
			System.out.println("oops!,wrong input");
		}
	}
	
}
