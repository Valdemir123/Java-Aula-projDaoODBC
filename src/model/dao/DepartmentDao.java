package model.dao;

import java.util.List;

import model.entities.Department;

public interface DepartmentDao {

	void Insert(Department obj);
	void Update(Department obj);
	void DeleteById(Integer Id);
	
	Department FindById(Integer Id);
	List<Department> FindAll();
}
