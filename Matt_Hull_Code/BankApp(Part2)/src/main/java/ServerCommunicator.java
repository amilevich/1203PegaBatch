import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServerCommunicator {
	public static BankAccountDao accountDao;// = new BankAccountDaoImpl();
	public static BankUserDao userDao;// = new BankUserDaoImpl();
	public static TransactionDao transDao;// = new TransactionDaoImpl();
	public static OwnershipDao ownershipDao;// = new OwnershipDaoImpl();
	public static Connection conn;

	/*
	 * This class is what the front end(Terminal) uses to interact with the back end
	 * It has the 4 dao implementations used to interact with the jdbc driver to interact with
	 * the database backend
	 * 
	 * In the constructor, it receives the connection that communicates with the database back end
	 * it instantiates the dao implementations, passing them the connection that it receives
	 * and stores them into static class variables
	 *
	 */
	public ServerCommunicator(Connection conn) {
		this.conn = conn;
		accountDao = new BankAccountDaoImpl(conn);
		userDao = new BankUserDaoImpl(conn);
		transDao = new TransactionDaoImpl(conn);
		ownershipDao = new OwnershipDaoImpl(conn);
	}
//checks if user exists
	public boolean userExists(String username) {
		return userDao.userExists(username);
	}
//makes user
	public BankUser createUser(BankUser user) {
		return userDao.insertUser(user, true);
	}
//returns user specified by username
	public BankUser getUser(String username) {
		return userDao.getUser(username);
	}
//This method recieves a user object which it then sends to the userDao
	/*
	 * this object contains the id of the user, and the dao updates every other attribute of the user 
	 * in the database to match what is stored in this object
	 */
	public BankUser updateUser(BankUser user) {
		return userDao.updateUser(user, true);
	}

	/*
	 * This method deletes records from the junction table that contains
	 * the relationships between users and account (i.e., who can access which accounts)
	 * 
	 * After that it deletes all transactions pertaining to the accounts that are going to be deleted
	 * that belong solely to the user to be deleted
	 * 
	 * then it looks to find accounts that do not have any users listed as having access
	 * in the junction table that lists the account-user relationships
	 * 
	 * then it deletes the user
	 * 
	 */
	public boolean deleteUser(BankUser user) {
		boolean success = ownershipDao.deleteRelationships(user, false); 
		if (success) {
			success = transDao.deleteTransactions(user,false);
			if (success) {
				success = accountDao.deleteOrphanAccounts(false); // DELETE ORPHAN ACCOUNT 
				if (success) {
					success = userDao.deleteUser(user, true);
				}
			}
		}
		return success;
	}
/*
 * This is for creating an account. First an account is inserted into the database, but it does not commit the
 * insert - this is specified by the 2nd arguement of the insertAccount method for the accountDao, the false
 * it means don't commit
 * 
 * then an Ownership object is created which stores an account and user so that it identicates that this account is owned
 * by this user
 * 
 * When this connection is created, it is indicated that it should commit by the "true" arguement passed
 */
	public BankAccount createAccount(BankAccount account, BankUser user) {
		BankAccount acc = accountDao.insertAccount(account, false);
		if (acc != null) {

			Ownership relationship = ownershipDao.insertConnection(new Ownership(user, account), true);
			if (relationship != null) {
				return acc;
			}
		}
		return null;
	}
/*
 * This receives an account and an arraylist of users that are to be associated with that account
 * it creates one account, then it creates a relationship between each of the users and the account
 */
	public BankAccount createJointAccount(BankAccount account, ArrayList<BankUser> users) {
		BankAccount acc = accountDao.insertAccount(account, true); //false
		if (acc == null) {
			return null;
		}
		boolean commit = false;
		for (BankUser user : users) {
			if (users.indexOf(user) == users.size() - 1) { 
				commit = true;
			}
			Ownership relationship = ownershipDao.insertConnection(new Ownership(user, acc), commit);
			if (relationship == null) {
				return null;
			}
		}

		return acc;
	}
/*
 * This is the method for deleting an account,
 * it deletes the transactions associated with the acount
 * then it deletes the records from the junction table that indicate the relationships that account has with people
 * then it deletes the account
 */
	public boolean deleteAccount(BankAccount account) {
		if (account.getBalance() != 0) {
			return false;
		}
		boolean success = transDao.deleteTransactions(account, false); //false
		if (success) {
			success = ownershipDao.deleteRelationships(account, false); //false
			if (success) {
				return accountDao.deleteAccount(account, true);
			}
		}
		try {
			conn.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
//gets all the accounts associated with this user
	public ArrayList<BankAccount> getUserAccounts(BankUser user) {
		return accountDao.getUserAccounts(user);
	}
//This method is not used - put there for future functionality, it returns all accounts in the database
	public ArrayList<BankAccount> getAllAccounts() {
		return accountDao.getAllAccounts();
	}
//this gets an arraylist of every user in the database
	public ArrayList<BankUser> getAllUsers() {
		return userDao.getAllUsers();
	}
//used when logging it, checks if password and username match existing account in database
	public BankUser login(BankUser user) {
		return userDao.validateUser(user);
	}
//gets account by id
	public BankAccount getAccount(int id) {
		return accountDao.getAccount(id);
	}
//gets account by account number, only if that account has an association with the user specified
	public BankAccount getAccount(int accNumber, int userID) {
		return accountDao.getAccount(accNumber, userID);
	}
//gets acc if associated with user specified
	public BankAccount getAccount(BankAccount acc, BankUser user) {
		return accountDao.getAccount(acc, user);
	}
//used when withdrawing or depositing into an account, updates balance, etc
	public BankAccount updateAccount(BankAccount acc) {
		return accountDao.updateAccount(acc, true);
	}
	//used when withdrawing or depositing into an account, updates balance, etc
	//also stores transaction
	public BankAccount updateAccount(BankAccount acc,Transaction trans) {
		acc =  accountDao.updateAccount(acc, false);
		if(acc == null) {
			return acc;
		}
		trans = transDao.insertTransaction(trans, true);
		if(trans==null) {
			return null;
		}
		return acc;
	}
	//Gets all owners belonging to an account
	public ArrayList<BankUser> getAccountOwners(BankAccount acc){
		ArrayList<BankUser> accs = this.userDao.getAccountUsers(acc);
		return accs;
	}
	//is used when making a transfer, basically updates 2 accounts and inserts 2 transactions
	//1 transaction for withdraw from init account
	//1 transaction for deposit into recip account
	public boolean makeTransfer(BankAccount init, BankAccount recip,Transaction tran1, Transaction tran2) {
		if(init.getAccountNumber()==recip.getAccountNumber()) {
			return false;
		}
		init =  accountDao.updateAccount(init, false);
		if(init == null) {
			return false;
		}
		recip =  accountDao.updateAccount(recip, false);
		if(recip == null) {
			return false;
		}
		tran1 = transDao.insertTransaction(tran1, false);
		if(tran1==null) {
			return false;
		}
		tran2 = transDao.insertTransaction(tran2, true);
		if(tran2==null) {
			return false;
		}
		return true;
	}
//gets all transaction associated with the user given
	public ArrayList<Transaction> getUserTransactions(BankUser user) {
		return transDao.getUserTransactions(user);
	}
//gets transactions associated with the account given
	public ArrayList<Transaction> getAccountTransactions(BankAccount acc) {
		return transDao.getAccountTransactions(acc);
	}
//deletes transactions associated with the account given
	public boolean deleteTransactions(BankAccount acc) {
		return transDao.deleteTransactions(acc, true);
	}
//deletes transactions associated with the user given
	public boolean deleteTransactions(BankUser user) {
		return transDao.deleteTransactions(user, true);
	}
//insert transaction given
	public Transaction insertTransaction(Transaction trans) {
		return transDao.insertTransaction(trans, true);
	}
}
