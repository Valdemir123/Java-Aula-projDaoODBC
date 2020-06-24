package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		SellerDao sellerDao = DaoFactory.CreateSellerDao();
		
		System.out.println("==Teste 1== Seller FindById");
		Seller seller = sellerDao.FindById(3);		
		System.out.println(seller);
		
		System.out.println("\n==Teste 2== Seller FindByDepartment");
		Department _dep = new Department(2,null);
		List<Seller> _list = sellerDao.FindByDepartment(_dep);
		for (Seller _loop : _list) {
			System.out.println(_loop);
		}

		System.out.println("\n==Teste 3== Seller FindAll");
		_list = sellerDao.FindAll();
		for (Seller _loop : _list) {
			System.out.println(_loop);
		}
	}

}
