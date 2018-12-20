import java.util.ArrayList;

public interface TransactionDao {
	public ArrayList<Transaction> getUserTransactions(BankUser user);
	public ArrayList<Transaction> getAccountTransactions(BankAccount account);
	public ArrayList<Transaction> getAccountTransactions(int accountNumber);
	public Transaction insertTransaction(Transaction trans, boolean commit);
	public boolean deleteTransaction(Transaction trans, boolean commit);
	public boolean deleteTransactions(BankAccount acc, boolean commit);
	public boolean deleteTransactions(BankUser user, boolean commit);
	public boolean deleteTransactions(int id, boolean commit);
	public boolean deleteOrphanAccountTransactions(boolean commit);
	
	
}
