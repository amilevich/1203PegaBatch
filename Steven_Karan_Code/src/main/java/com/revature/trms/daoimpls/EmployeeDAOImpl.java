package com.revature.trms.daoimpls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.trms.dao.EmployeeDAO;
import com.revature.trms.models.Employee;
import com.revature.trms.util.ConnFactory;

public class EmployeeDAOImpl implements EmployeeDAO {

	private static ConnFactory cf = ConnFactory.getInstance();

	/**
	 * Inserts a given employee into the database
	 * @param empl Employee to be inserted
	 * @return true if inserted successfully, false otherwise
	 */
	@Override
	public boolean insertEmployee(Employee empl) {
		try (Connection conn = cf.getConnection();) {
			String sql = "INSERT INTO employee VALUES(null,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			int super_id = empl.getSupervisor_id();
			if(super_id > 0) {
				ps.setInt(1, empl.getSupervisor_id());
			}
			else {
				ps.setNull(1, 1);
			}
			ps.setString(2, empl.getPosition());
			ps.setString(3, empl.getDepartment());
			ps.setString(4, empl.getEmail());
			ps.setString(5, empl.getFirstname());
			ps.setString(6, empl.getLastname());
			ps.setString(7, empl.getUsername());
			ps.setString(8, empl.getPassword());
			ps.setDouble(9, empl.getAvailable_funds());
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	
	@Override
	public Employee getEmployeeByID(int id) {
		try (Connection conn = cf.getConnection();) {
			String sql = "SELECT * FROM empl_view WHERE emp_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Employee empl = null;
			if (rs.next()) {
				empl = new Employee();
				empl.setEmp_id(rs.getInt("emp_id"));
				empl.setFirstname(rs.getString("firstname"));
				empl.setLastname(rs.getString("lastname"));
				empl.setEmail(rs.getString("email"));
				empl.setUsername(rs.getString("username"));
				empl.setPassword(rs.getString("password"));
				empl.setAvailable_funds(rs.getDouble("available_funds"));

				empl.setDepartment(rs.getString("dept"));
				empl.setPosition(rs.getString("pos"));
				empl.setSupervisor_id(rs.getInt("super_id"));
				
				
				if(!rs.wasNull()) {
					empl.setSupervisor_firstname(rs.getString("super_firstname"));
					empl.setSupervisor_lastname(rs.getString("super_lastname"));
				}	
			}
			
			return empl;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Employee getEmployeeByUsername(String username) {
		try (Connection conn = cf.getConnection();) {
			String sql = "SELECT * FROM empl_view WHERE username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			Employee empl = null;
			if (rs.next()) {
				empl = new Employee();
				empl.setEmp_id(rs.getInt("emp_id"));
				empl.setFirstname(rs.getString("firstname"));
				empl.setLastname(rs.getString("lastname"));
				empl.setEmail(rs.getString("email"));
				empl.setUsername(rs.getString("username"));
				empl.setPassword(rs.getString("password"));
				empl.setAvailable_funds(rs.getDouble("available_funds"));
				empl.setDepartment(rs.getString("dept"));
				empl.setPosition(rs.getString("pos"));
				
				empl.setSupervisor_id(rs.getInt("super_id"));
				empl.setSupervisor_firstname(rs.getString("super_firstname"));
				empl.setSupervisor_lastname(rs.getString("super_lastname"));
			}
			return empl;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Employee getDepartmentHead(String department_name) {
		try (Connection conn = cf.getConnection();) {
			String sql = "SELECT * FROM employee WHERE dept = ? AND pos ='Department Head'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, department_name);
			ResultSet rs = ps.executeQuery();

			Employee empl = null;
			if (rs.next()) {
				empl = new Employee();
				empl.setEmp_id(rs.getInt("emp_id"));
				empl.setFirstname(rs.getString("firstname"));
				empl.setLastname(rs.getString("lastname"));
				empl.setEmail(rs.getString("email"));
				empl.setUsername(rs.getString("username"));
				empl.setPassword(rs.getString("password"));
				empl.setAvailable_funds(rs.getDouble("available_funds"));
				empl.setDepartment(rs.getString("dept"));
				empl.setPosition(rs.getString("pos"));
			}
			return empl;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

	/**
	 * update employee based on employee passed as parameter, using the id of the
	 * employee passed as the id to update
	 * 
	 * @param empl
	 * @return
	 */
	@Override
	public boolean updateEmployee(Employee empl) {
		try (Connection conn = cf.getConnection();) {
			// TODO: NOTE: check that not null values are not null from any calling method
			String sql = "UPDATE employee SET firstname=?, lastname=?, email=?, username=?, dept=?, pos=?, available_funds=? WHERE emp_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, empl.getFirstname());
			ps.setString(2, empl.getLastname());
			ps.setString(3, empl.getEmail());
			ps.setString(4, empl.getUsername());
			ps.setString(5, empl.getDepartment());
			ps.setString(6, empl.getPosition());
			ps.setDouble(7, empl.getAvailable_funds());
			ps.setInt(8, empl.getEmp_id());
			
			// Note: at most 1 row can be updated at a time given that the where clause selects an id
			if ( ps.executeUpdate() >= 1) {
				return true;
			}else {
				return false;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
