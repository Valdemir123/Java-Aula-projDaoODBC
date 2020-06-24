package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.dbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {
	
	private Connection _cn;
	public SellerDaoJDBC(Connection _conn) {
		_cn = _conn;
	}
	

	@Override
	public void Insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DeleteById(Integer Id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller FindById(Integer Id) {
		PreparedStatement _st = null;
		ResultSet _rs = null;
		
		try {
			_st = _cn.prepareStatement("Select sel.*, dep.Name as DepName"
					+" From Seller as sel"
					+" Inner Join Department as dep on dep.Id = sel.DepartmentId"
					+" Where sel.Id = ?");
			_st.setInt(1, Id);
			
			_rs = _st.executeQuery();
			if(_rs.next()) {
				Department _dep = InstantiateDepartment(_rs);
				Seller _sel = InstantiateSeller(_rs, _dep);
				
				return _sel;
			}
		}
		catch (SQLException e) {
			throw new dbException(e.getMessage());
		}
		finally {
			DB.closeStatement(_st);
			DB.closeResultSet(_rs);
		}
		return null;
	}

	private Seller InstantiateSeller(ResultSet _rs, Department _dep) throws SQLException {
		Seller _sel = new Seller();
		_sel.setId(_rs.getInt("Id"));
		_sel.setName(_rs.getString("Name"));
		_sel.setEmail(_rs.getString("Email"));
		_sel.setBirthDate(_rs.getDate("BirthDate"));
		_sel.setBaseSalary(_rs.getDouble("BaseSalary"));
		_sel.setDepartament(_dep);
		
		return _sel;
	}


	private Department InstantiateDepartment(ResultSet _rs) throws SQLException {
		Department _dep = new Department();
		_dep.setId(_rs.getInt("DepartmentId"));
		_dep.setName(_rs.getString("DepName"));
		
		return _dep;
	}


	@Override
	public List<Seller> FindAll() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
