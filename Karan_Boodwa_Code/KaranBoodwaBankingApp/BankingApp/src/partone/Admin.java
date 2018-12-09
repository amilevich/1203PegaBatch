package partone;

public class Admin extends Employee {
	
	private UserAuthorizer userAuth = UserAuthorizer.getUserAuthSingleton();
	
	public Admin() {
		super();
	}
	
	public Admin(String username, String password) {
		super(username, password);
	}
}
