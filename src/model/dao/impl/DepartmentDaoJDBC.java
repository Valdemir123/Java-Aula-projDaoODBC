package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.dbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {
	
	private Connection _cn;
	public DepartmentDaoJDBC(Connection _conn) {
		_cn = _conn;
	}

	@Override
	public void Insert(Department obj) {
		PreparedStatement _st = null;
		
		try {
			_st = _cn.prepareStatement(""
					+ "Insert Into Department"
					+ " (Name)"
					+ " Values "
					+ " (?)", Statement.RETURN_GENERATED_KEYS);
			_st.setString(1, obj.getName());
			
			int _RowsAffected = _st.executeUpdate();
			if (_RowsAffected>0) {
				ResultSet _rs = _st.getGeneratedKeys();
				if (_rs.next()) {
					int _NewId = _rs.getInt(1);
					obj.setId(_NewId);
				} 
				DB.closeResultSet(_rs);
				
			} else {
				throw new dbException("Erro inesperado, nenhum linha afetada !");
			}
			
		} catch(SQLException e) {
			throw new dbException(e.getMessage());
		} finally {
			DB.closeStatement(_st);
		}
	}

	@Override
	public void Update(Department obj) {
		PreparedStatement _st = null;
		
		try {
			_st = _cn.prepareStatement(""
					+ "Update Department"
					+ " Set Name=?"
					+ " Where Id=?");
			_st.setString(1, obj.getName());
			_st.setInt(2, obj.getId());
			
			int _RowsAffected = _st.executeUpdate();
			if (_RowsAffected==0) {
				throw new dbException("Erro inesperado, nenhum linha afetada !");
			}
			
		} catch(SQLException e) {
			throw new dbException(e.getMessage());
		} finally {
			DB.closeStatement(_st);
		}
	}

	@Override
	public void DeleteById(Integer Id) {
		PreparedStatement _st =null;
		
		try {
			_st = _cn.prepareStatement(""
					+ "Delete From Department"
					+ " Where Id=?");
			_st.setInt(1, Id);
						
			int _RowsAffected = _st.executeUpdate();
			if (_RowsAffected==0) {
				throw new dbException("Erro inesperado, nenhum linha afetada !");
			}
			
		} catch(SQLException e) {
			throw new dbException(e.getMessage());
		} finally {
			DB.closeStatement(_st);
		}
	}

	@Override
	public Department FindById(Integer Id) {
		PreparedStatement _st =null;
		ResultSet _rs =null;
		
		try {
			_st=_cn.prepareStatement(""
					+ "Select Id,Name"
					+ " From Department"
					+ " Where Id=?");
			_st.setInt(1, Id);
			
			_rs = _st.executeQuery();
			if (_rs.next()) {
				Department _dep = InstantiateDepartment(_rs);
				return _dep;
			}
			
		} catch (SQLException e) {
			throw new dbException(e.getMessage());
		} finally {
			DB.closeStatement(_st);
			DB.closeResultSet(_rs);
		}
		return null;
	}

	private Department InstantiateDepartment(ResultSet _rs) throws SQLException {
		Department _dep = new Department();
		_dep.setId(_rs.getInt("Id"));
		_dep.setName(_rs.getString("Name"));
		
		return _dep;
	}

	@Override
	public List<Department> FindAll() {
		PreparedStatement _st =null;
		ResultSet _rs =null;
		
		try {
			_st=_cn.prepareStatement(""
					+ "Select Id,Name"
					+ " From Department"
					+ " Order by Name");
						
			List<Department> _List = new ArrayList<>();
						
			_rs = _st.executeQuery();
			while(_rs.next()) {
				Department _dep = InstantiateDepartment(_rs);
				_List.add(_dep);
			}
			return _List;
			
		} catch (SQLException e) {
			throw new dbException(e.getMessage());
		} finally {
			DB.closeStatement(_st);
			DB.closeResultSet(_rs);
		}
	}

}
