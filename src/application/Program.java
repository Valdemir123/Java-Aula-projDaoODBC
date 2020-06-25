package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		SellerDao sellerDao = DaoFactory.CreateSellerDao();
		
		System.out.println("==Teste 1== Seller FindById");
		Seller _seller = sellerDao.FindById(3);		
		System.out.println(_seller);
		
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
		
		System.out.println("\n==Teste 4== Seller Insert");
		Seller _NewSeller = new Seller(null, "Greg", "greg@email.com", new Date(), 4000.0, _dep);
		sellerDao.Insert(_NewSeller);
		System.out.println("Inserted! new id: "+_NewSeller.getId());
		
		System.out.println("\n==Teste 5== Seller Update");
		_seller = sellerDao.FindById(1);
		System.out.println("Actual! "+_seller);
		_seller.setName("Martha Waine");
		sellerDao.Update(_seller);
		System.out.println("Update! "+_seller);
	}

}
