import java.util.ArrayList;

public interface BankAccountDao {
	public BankAccount insertAccount(BankAccount acc, boolean commit);
	public BankAccount updateAccount(BankAccount acc, boolean commit);
	public boolean deleteAccount(int accountNum, boolean commit);
	public boolean deleteAccount(BankAccount account, boolean commit);

	public ArrayList<BankAccount> getUserAccounts(BankUser user);
	public ArrayList<BankAccount> getUserAccounts(int userID);
	public ArrayList<BankAccount> getAllAccounts();
	public BankAccount getAccount(int accountNum);
	public BankAccount getAccount(BankAccount account);
	public BankAccount getAccount(int accountNum, int userID);
	public BankAccount getAccount(BankAccount account,BankUser user);
	public boolean deleteOrphanAccounts(boolean commit);

}
