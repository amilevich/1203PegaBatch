package bank;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class CustomerDaoImpl implements CustomerDao {

	private static Connection getConnection() {
		Properties prop = new Properties();
		// Way more secure as enviornment variables
		try {
			prop.load(new FileReader("database.properties"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String user = prop.getProperty("usr"); // System.getenv("jdbc_user")
		String password = prop.getProperty("password");
		String url = prop.getProperty("url");

		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean removeCustomer(Customer customer) {
		Connection conn = getConnection();

		String query = "DELETE FROM customer WHERE uname = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, customer.getCustomerUsername());
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Boolean addCustomer(Customer customer) {
		Connection conn = getConnection();

		String query = "INSERT INTO customer (id, fname, lname, uname, pword, abalance, jaccount, jauname ) "
				+ "VALUES (customer_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, customer.getCustomerFirstName());
			ps.setString(2, customer.getCustomerLastName());
			ps.setString(3, customer.getCustomerUsername());
			ps.setString(4, customer.getCustomerPassword());
			ps.setDouble(5, customer.getAccountBalance());
			ps.setString(6, customer.getJointAccount());
			ps.setString(7, customer.getJoinCustomerUsername());
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public void getCustomers() {
		Customer customer;
		Connection conn = getConnection();

		String query = "SELECT * FROM customer";
		// String query = "SELECT
		// cinfo.cid,cinfo.cfname,cinfo.clname,cacc.cuser,cacc.cpass,cacc.cbal,cacc.cjacc,cacc.cjauser
		// "
		// + "FROM cInfo,cAcc "
		// + "WHERE cinfo.cid = cacc.cid";
		try {
			PreparedStatement ps = conn.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				customer = new Customer();

				customer.setCustomerFirstName(rs.getString(2));
				customer.setCustomerLastName(rs.getString(3));
				customer.setCustomerUsername(rs.getString(4));
				customer.setCustomerPassword(rs.getString(5));
				customer.setAccountBalance(rs.getDouble(6));
				customer.setJoinCustomerUsername(rs.getString(8));
				customer.setJointAccount(rs.getString(7));
				Main.customerList.add(customer);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Boolean addNewCustomer(Customer customer) {
		Connection conn = getConnection();

		String query = "INSERT INTO newcustomer (id, fname, lname, uname, pword, abalance, jaccount, jauname ) "
				+ "VALUES (customernew_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, customer.getCustomerFirstName());
			ps.setString(2, customer.getCustomerLastName());
			ps.setString(3, customer.getCustomerUsername());
			ps.setString(4, customer.getCustomerPassword());
			ps.setDouble(5, customer.getAccountBalance());
			ps.setString(6, customer.getJointAccount());
			ps.setString(7, customer.getJoinCustomerUsername());
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Boolean removeNewCustomer(Customer customer) {
		Connection conn = getConnection();

		String query = "DELETE FROM newcustomer WHERE uname = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, customer.getCustomerUsername());
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public void getNewCustomers() {
		Customer customer;
		Connection conn = getConnection();

		String query = "SELECT * FROM newcustomer";
		// String query = "SELECT
		// cinfo.cid,cinfo.cfname,cinfo.clname,cacc.cuser,cacc.cpass,cacc.cbal,cacc.cjacc,cacc.cjauser
		// "
		// + "FROM cInfo,cAcc "
		// + "WHERE cinfo.cid = cacc.cid";
		try {
			PreparedStatement ps = conn.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				customer = new Customer();

				customer.setCustomerFirstName(rs.getString(2));
				customer.setCustomerLastName(rs.getString(3));
				customer.setCustomerUsername(rs.getString(4));
				customer.setCustomerPassword(rs.getString(5));
				customer.setAccountBalance(rs.getDouble(6));
				customer.setJoinCustomerUsername(rs.getString(8));
				customer.setJointAccount(rs.getString(7));
				Main.newCustomers.add(customer);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateInDb() {
		Connection conn = getConnection();
		String query = "TRUNCATE TABLE customer";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		query = "DROP SEQUENCE customer_seq";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		query = "CREATE SEQUENCE customer_seq" + " START WITH     1" + " INCREMENT BY   1";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (Customer c : Main.customerList) {
			query = "INSERT INTO customer VALUES (customer_seq.nextval, ?,?,?,?,?,?,?)";
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, c.getCustomerFirstName());
				ps.setString(2, c.getCustomerLastName());
				ps.setString(3, c.getCustomerUsername());
				ps.setString(4, c.getCustomerPassword());
				ps.setDouble(5, c.getAccountBalance());
				ps.setString(6, c.getJointAccount());
				ps.setString(7, c.getJoinCustomerUsername());
				ps.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void updateAmountCustomer(Customer customer) {
		Connection conn = getConnection();

		String query = "UPDATE customer SET aBalance = ? WHERE uname = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setDouble(1, customer.getAccountBalance());
			ps.setString(2, customer.getCustomerUsername());
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateJoin(Customer c1) {
		Connection conn = getConnection();

		String query = "UPDATE newcustomer SET jaccount = ?, jauname = ? WHERE uname = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, c1.getJointAccount());
			ps.setString(2, c1.getJoinCustomerUsername());
			ps.setString(3, c1.getCustomerUsername());
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
