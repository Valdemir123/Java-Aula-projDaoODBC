package model.dao;

import java.util.List;

import model.entities.Seller;

public interface SellerDao {

	void Insert(Seller obj);
	void Update(Seller obj);
	void DeleteById(Integer Id);
	
	Seller FindById(Integer Id);
	List<Seller> FindAll();
}
