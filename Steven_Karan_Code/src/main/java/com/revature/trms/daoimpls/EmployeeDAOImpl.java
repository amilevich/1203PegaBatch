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

	@Override
	public boolean insertEmployee(Employee empl) {
		try (Connection conn = cf.getConnection();) {
			String sql = "INSERT INTO employee VALUES(null,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, empl.getSupervisor_id());
			ps.setInt(2, empl.getTitle_id());
			ps.setInt(3, empl.getDepartment_id());
			ps.setString(4, empl.getEmail());
			ps.setString(5, empl.getFirstname());
			ps.setString(6, empl.getLastname());
			ps.setString(7, empl.getUsername());
			ps.setString(8, empl.getPassword());
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

				empl.setDepartment_id(rs.getInt("dept_id"));
				empl.setDepartment_name(rs.getString("dept_name"));

				empl.setTitle_id(rs.getInt("int_id"));
				empl.setTitle_name(rs.getString("title_name"));

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

				empl.setDepartment_id(rs.getInt("dept_id"));
				empl.setDepartment_name(rs.getString("dept_name"));

				empl.setTitle_id(rs.getInt("title_id"));
				empl.setTitle_name(rs.getString("title_name"));

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
			String sql = "UPDATE employee SET firstname=?, lastname=?, super_id=?, email=?, username=?, password=?, dept_id=?, title_id=? WHERE emp_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, empl.getFirstname());
			ps.setString(2, empl.getLastname());
			ps.setInt(3, empl.getSupervisor_id());
			ps.setString(4, empl.getUsername());
			ps.setInt(5, empl.getDepartment_id());
			ps.setInt(6, empl.getTitle_id());
			
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

	public static void main(String[] args) {
		EmployeeDAOImpl edi = new EmployeeDAOImpl();

		Employee empl = new Employee("Karan", "Boodwa", "kboodwa@gmail.com", "kboodwa", "8^,dN]\"*48,nPY-@", 1, 1, 1);
		edi.insertEmployee(empl);
		Employee empl2 = edi.getEmployeeByUsername("kboodwa");
		System.out.println(empl2);
		// add check to db that emp_id != super_id? OR leave as option for CEO

	}
}
