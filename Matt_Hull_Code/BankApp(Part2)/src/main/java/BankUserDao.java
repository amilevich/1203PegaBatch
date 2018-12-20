import java.util.ArrayList;

public interface BankUserDao {
	
	public BankUser insertUser(BankUser user, boolean commit);
	public boolean deleteUser(BankUser user, boolean commit);
	public boolean deleteUser(String username, boolean commit);
	public int getUserID(String username);
	public BankUser updateUser(BankUser user, boolean commit);
	public BankUser getUser(String username);
	public BankUser getUser(int id);
	public boolean userExists(String username);
	public ArrayList<BankUser> getAllUsers();
	public BankUser validateUser(BankUser user);
	public ArrayList<BankUser> getAccountUsers(BankAccount acc);
	
	
}
