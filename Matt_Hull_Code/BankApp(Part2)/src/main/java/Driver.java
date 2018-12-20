import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
//		final String USERNAME = "bank_db";
//		final String PASSWORD = "p4ssw0rd";
//		final String URL = "jdbc:oracle:thin:@octocatdb.cquzk1ryvr4n.us-east-2.rds.amazonaws.com:1521:ORCL";
		Properties prop = new Properties();
		Connection conn;
		try {
			prop.load(new FileReader("database.properties"));
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(prop.getProperty("url"),
											   prop.getProperty("username"),
											   prop.getProperty("password") );
			conn.setAutoCommit(false);
			ServerCommunicator serv = new ServerCommunicator(conn);
			Terminal term = new Terminal(scan,serv);
			term.interact();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
