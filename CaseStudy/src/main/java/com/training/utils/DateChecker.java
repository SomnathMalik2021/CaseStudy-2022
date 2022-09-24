package com.training.utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DateChecker {
	private static final Logger logger = LogManager.getRootLogger() ;
	public static LocalDate getCorrectDate(Scanner sc) {
		LocalDate date=null;
		boolean isCorrect=false;
		while(!isCorrect) {
			try {
				System.out.print("Enter: ");
				date=LocalDate.parse(sc.nextLine());
				isCorrect=true;
			}
			catch(DateTimeParseException dte) {
				logger.error("Please provide date in YYYY-MM-DD format" );
				
			}
		}
		return date;
	}
}
