package com.training.ifcs;

import java.util.List;

import com.training.error.NotFoundException;

public interface Repository<T,ID> {
    T findById(ID id) throws NotFoundException;
	void save(T entity);
	List<T> findAll();
	void update(T entity,String [] params) throws NotFoundException;
	void delete(T entity);
	void deleteBYId(ID id) throws NotFoundException;
	void updateByID(ID id,String [] params) throws NotFoundException;
	
}
