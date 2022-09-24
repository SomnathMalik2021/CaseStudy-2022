package com.training.CaseStudy;

import java.util.InputMismatchException;
import java.util.Scanner;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.utils.MenuManager;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final Logger logger = LogManager.getRootLogger() ;
    public static void main( String[] args )
    { 
        Scanner sc = new Scanner(System.in);
        MenuManager menuHandler = new MenuManager();
        logger.info("Application started......");
        while(true) {
        	System.out.println("-----------------------------------------------------------------------");
        	System.out.println("Enter 1. to Add employee details");
        	System.out.println("Enter 2. to Get the List of employees by their firstName.");
        	System.out.println("Enter 3. to Get the List of employees with FirstName and Phone Number.");
        	System.out.println("Enter 4. to Update the email and phoneNumber of a particular employee.");
        	System.out.println("Enter 5. to Delete Details of a Particular employee by firstName.");
        	System.out.println("Enter 6. to Get a list of employees with their firstName and emailAddress  whose Birthday falls on the given date.");
        	System.out.println("Enter 7. to Get the list of employees with their firstName and phone Number whose Wedding Anniversary falls on the given date.");
        	System.out.println("Enter 0. to Quit Application");
        	System.out.println("--------------------------------------------------------------------------------------------------------");
        	System.out.print("Enter: ");
        	int input;
        	while(!sc.hasNextInt()) {
        		logger.error("Please provide number(0-7) as input");
        		System.out.print("Enter: ");
        		sc.next();
        	}
        	input=sc.nextInt();
        	if(input==0) {
                logger.warn("Exiting Aplication.......");
        		break;
        	}
        	menuHandler.getManagedOutPut(input);
        	
        	
        }
        menuHandler.closeScanner();
        sc.close();
        
        
    }
    
}
