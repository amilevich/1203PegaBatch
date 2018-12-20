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
		/*
		 * This initiates the scanner that is passed to the terminal for use and then passes it
		 * it instantiates the terminal which acts as the front end, the  ServerCommunicater which
		 * is what the terminal interacts with in order to send the data to the back end
		 * and the connection, which is passed to the servercommunicator, which is then passed to the
		 * dao implementations in the servercommunicator
		 */
		Scanner scan = new Scanner(System.in);
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
			scan.close();
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
