package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Prog_Department {

	public static void main(String[] args) {
		
		Scanner _sc = new Scanner(System.in);
		
		DepartmentDao departmentDao = DaoFactory.CreateDepartmentDao();
		
		System.out.println("==Teste 1== Department FindById");
		Department _seller = departmentDao.FindById(3);		
		System.out.println(_seller);
		
		System.out.println("\n==Teste 2== Department FindAll");
		List<Department> _list = departmentDao.FindAll();
		for (Department _loop : _list) {
			System.out.println(_loop);
		}
		
		System.out.println("\n==Teste 3== Department Insert");
		Department _NewDepartment = new Department(null, "Machine");
		departmentDao.Insert(_NewDepartment);
		System.out.println("Inserted! new id: "+_NewDepartment.getId());
		
		System.out.println("\n==Teste 4== Department Update");
		Department _dep = departmentDao.FindById(1);
		System.out.println("Actual! "+_dep);
		_dep.setName("Big Data");
		departmentDao.Update(_dep);
		System.out.println("Update! "+_dep);
		
		System.out.println("\n==Teste 5== Department Delete");
		_list = departmentDao.FindAll();
		for (Department _loop : _list) {
			System.out.println(_loop);
		}
		System.out.println("Enter Id for Delete:");
		int _id = _sc.nextInt();
		departmentDao.DeleteById(_id);
		System.out.println("Delete Afected!");
		_list = departmentDao.FindAll();
		for (Department _loop : _list) {
			System.out.println(_loop);
		}
		
		_sc.close();
	}

}
