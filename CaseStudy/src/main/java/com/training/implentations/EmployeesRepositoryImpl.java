package com.training.implentations;

import java.sql.*;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.entity.Employee;
import com.training.error.NotFoundException;
import com.training.ifcs.EmployeesRepository;

public class EmployeesRepositoryImpl implements EmployeesRepository {

	private List<Employee> employees =new ArrayList<>();
	private Connection con=null;
	private boolean ismodified=true;
	private static final Logger logger = LogManager.getRootLogger() ; 
	public EmployeesRepositoryImpl(Connection con) {
		super();
		this.con = con;
	}



	@Override
	public void save(Employee entity) {
		String sql = "insert into lumen_employees values(?,?,?,?,?,?,?)" ; 
		int rowAdded = 0; 
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,entity.getFirstName());
			pstmt.setString(2, entity.getLastName());
			pstmt.setString(3, entity.getAddress());
			pstmt.setString(4, entity.getEmail());
			pstmt.setString(5, entity.getPhoneNo());
			pstmt.setDate(6, java.sql.Date.valueOf(entity.getDateOfBirth()));
			pstmt.setDate(7, java.sql.Date.valueOf(entity.getWeddingDate()));
			
			rowAdded = pstmt.executeUpdate();
			ismodified=true;
			
		} catch (SQLException e) {
			logger.error(e.getClass()+" : "+e.getMessage());
		}
		finally {
			if(rowAdded==0) {
				logger.warn("No records Added");
			}
			logger.info(rowAdded + " rows added");
		}
		
	}
	@Override
	public List<Employee> findAll() {
		if(ismodified) {
		employees.clear();
		String sql = "select * from lumen_employees" ; 
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			ResultSet results = pstmt.executeQuery();
			while(results.next()) {
				Employee employee = new Employee(results.getString(1), results.getString(2), results.getString(3),
						results.getString(4), results.getString(5),results.getDate(6).toLocalDate(),
						results.getDate(7).toLocalDate());
			    employees.add(employee);
			}
			ismodified=false;
			
		} catch (SQLException e) {
			logger.error(e.getClass()+" : "+e.getMessage());
		}   
		}
		
		return employees;
	}

	@Override
	public Employee findById(String email) throws NotFoundException {
		Employee employee=null;
		String sql = "select * from lumen_employees where email=?" ; 
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,email);
			ResultSet results = pstmt.executeQuery();
			while(results.next()) {
			employee = new Employee(results.getString(0), results.getString(1), results.getString(2),
					results.getString(3), results.getString(4),results.getDate(5).toLocalDate(),
					results.getDate(6).toLocalDate());
			}
			
		} catch (SQLException e) {
			logger.error(e.getClass()+" : "+e.getMessage());
		}
		finally {
			if(employee.equals(null)) {
				throw new NotFoundException("No employee Found by id " + email);
			}
		}
		
		
		return employee;
	}



	@Override
	public void update(Employee entity, String[] params) throws NotFoundException {
		String sql = "UPDATE lumen_employees set email=?,phoneNo=? where email=?" ; 
		int rowUpDated = 0; 
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
		
			pstmt.setString(1,params[0]);
			pstmt.setString(2,params[1]);
			
			rowUpDated = pstmt.executeUpdate();
			ismodified=true;
			
		} catch (SQLException e) {
			logger.error(e.getClass()+" : "+e.getMessage());
		}
		finally {
			if(rowUpDated==0) {
				throw new NotFoundException("No Element Found to Update");
			}
			logger.info(rowUpDated + " rows updated");
		}
	}



	@Override
	public void delete(Employee entity) {
		try {
			this.deleteBYId(entity.getEmail());
		}
		catch(Exception e){
			logger.error(e.getClass()+" : "+e.getMessage());
		}
		
	}



	@Override
	public void deleteBYId(String id) throws NotFoundException {
		String sql = "delete from lumen_employees where email=?" ; 
		int rowUpDated = 0; 
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
			rowUpDated = pstmt.executeUpdate();
			ismodified=true;
		} catch (SQLException e) {
			logger.error(e.getClass()+" : "+e.getMessage());
		}
		finally {
            if(rowUpDated==0) {
            	throw new NotFoundException("No Element Found to Delete");
            }
			logger.info(rowUpDated + " rows updated");
		}
		
	}



	@Override
	public void deleteByFirstName(String firstName) throws NotFoundException {
		String sql = "delete from lumen_employees where firstName=?" ; 
		int rowDeleted = 0; 
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,firstName);
			rowDeleted = pstmt.executeUpdate();
			ismodified=true;
		} catch (SQLException e) {
			logger.error(e.getClass()+" : "+e.getMessage());
		}
		finally {
			if(rowDeleted==0) {
            	throw new NotFoundException("No Element Found to Delete");
            }
			logger.info(rowDeleted + " rows Deleted");
		}
		
	}



	@Override
	public void updateByID(String email, String[] params) throws NotFoundException {
		String sql = "UPDATE lumen_employees set email=?, phoneNo=? where email=?" ; 
		int rowUpDated = 0; 
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
		    
			pstmt.setString(1,params[0]);
			pstmt.setString(2,params[1]);
			pstmt.setString(3,email);
			
			rowUpDated = pstmt.executeUpdate();
			ismodified=true;
			
		} catch (SQLException e) {
			logger.error(e.getClass()+" : "+e.getMessage());
		}
		finally {
			if(rowUpDated==0) {
            	throw new NotFoundException("No Element Found to Delete");
            }
			logger.info(rowUpDated + " rows updated");
		}
	}

	









	

}
