import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server server = new Server();
		Scanner scan = new Scanner(System.in);
		Terminal terminal = new Terminal(server, scan);
		terminal.interact();
		scan.close();
	}

}
