package model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		PreparedStatement _st = null;
		
		try {
			_st = _cn.prepareStatement(
					"Insert Into Seller"
					+" (Name, Email, BirthDate, BaseSalary, DepartmentId)"
					+" Values "
					+" (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			_st.setString(1, obj.getName());
			_st.setString(2, obj.getEmail());
			_st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			_st.setDouble(4,  obj.getBaseSalary());
			_st.setInt(5, obj.getDepartament().getId());
			
			int _rowsAffected = _st.executeUpdate();
			if (_rowsAffected > 0) {
				ResultSet _rs = _st.getGeneratedKeys();
				if(_rs.next()) {
					int _id = _rs.getInt(1);
					obj.setId(_id);
				} else {
					DB.closeResultSet(_rs);
				}
			} else {
				throw new dbException("Erro inexperado, nenhum linha afetada !");
			}
			
		} catch(SQLException e) {
			throw new dbException(e.getMessage());
		} finally {
			DB.closeStatement(_st);
		}
		
	}

	@Override
	public void Update(Seller obj) {
		PreparedStatement _st = null;
		
		try {
			_st = _cn.prepareStatement(
					"Update Seller"
					+" SET Name=?, Email=?, BirthDate=?, BaseSalary=?, DepartmentId=?"
					+" WHERE Id=? ");
			_st.setString(1, obj.getName());
			_st.setString(2, obj.getEmail());
			_st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			_st.setDouble(4,  obj.getBaseSalary());
			_st.setInt(5, obj.getDepartament().getId());
			_st.setInt(6, obj.getId());
			
			_st.executeUpdate();
			
		} catch(SQLException e) {
			throw new dbException(e.getMessage());
		} finally {
			DB.closeStatement(_st);
		}
		
	}

	@Override
	public void DeleteById(Integer Id) {
		PreparedStatement _st = null;
		
		try {
			_st = _cn.prepareStatement(
					"Delete From Seller"
					+" Where Id = ?");
			_st.setInt(1, Id);
			
			_st.executeUpdate();
		} catch (SQLException e) {
			throw new dbException(e.getMessage());
		} finally {
			DB.closeStatement(_st);
		}
		
	}

	@Override
	public Seller FindById(Integer Id) {
		PreparedStatement _st = null;
		ResultSet _rs = null;
		
		try {
			_st = _cn.prepareStatement(
					"Select sel.*, dep.Name as DepName"
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
		PreparedStatement _st = null;
		ResultSet _rs = null;
		
		try {
			_st = _cn.prepareStatement(
					"Select sel.*, dep.Name as DepName"
					+" From Seller as sel"
					+" Inner Join Department as dep on dep.Id = sel.DepartmentId"
					+" Order by sel.Name");
						
			List<Seller> _List = new ArrayList<>();
			Map<Integer, Department> _map = new HashMap<>();
			
			_rs = _st.executeQuery();
			while(_rs.next()) {
				Department _dep = _map.get(_rs.getInt("DepartmentId"));
				if (_dep == null) {
					_dep = InstantiateDepartment(_rs);
					_map.put(_rs.getInt("DepartmentId"), _dep);
				}
				
				Seller _sel = InstantiateSeller(_rs, _dep);
				_List.add(_sel);
			}
			return _List;
		}
		catch (SQLException e) {
			throw new dbException(e.getMessage());
		}
		finally {
			DB.closeStatement(_st);
			DB.closeResultSet(_rs);
		}
	}


	@Override
	public List<Seller> FindByDepartment(Department _department) {
		PreparedStatement _st = null;
		ResultSet _rs = null;
		
		try {
			_st = _cn.prepareStatement(
					"Select sel.*, dep.Name as DepName"
					+" From Seller as sel"
					+" Inner Join Department as dep on dep.Id = sel.DepartmentId"
					+" Where sel.DepartmentId = ?"
					+" Order by sel.Name");
			_st.setInt(1, _department.getId());
			
			List<Seller> _List = new ArrayList<>();
			Map<Integer, Department> _map = new HashMap<>();
			
			_rs = _st.executeQuery();
			while(_rs.next()) {
				Department _dep = _map.get(_rs.getInt("DepartmentId"));
				if (_dep == null) {
					_dep = InstantiateDepartment(_rs);
					_map.put(_rs.getInt("DepartmentId"), _dep);
				}
				
				Seller _sel = InstantiateSeller(_rs, _dep);
				_List.add(_sel);
			}
			return _List;
		}
		catch (SQLException e) {
			throw new dbException(e.getMessage());
		}
		finally {
			DB.closeStatement(_st);
			DB.closeResultSet(_rs);
		}
	}
	

}
