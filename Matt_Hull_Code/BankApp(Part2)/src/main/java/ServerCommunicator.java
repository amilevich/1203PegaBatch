import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServerCommunicator {
	public static BankAccountDao accountDao;// = new BankAccountDaoImpl();
	public static BankUserDao userDao;// = new BankUserDaoImpl();
	public static TransactionDao transDao;// = new TransactionDaoImpl();
	public static OwnershipDao ownershipDao;// = new OwnershipDaoImpl();
	public static Connection conn;

	public ServerCommunicator(Connection conn) {
		this.conn = conn;
		accountDao = new BankAccountDaoImpl(conn);
		userDao = new BankUserDaoImpl(conn);
		transDao = new TransactionDaoImpl(conn);
		ownershipDao = new OwnershipDaoImpl(conn);
	}

	public boolean userExists(String username) {
		return userDao.userExists(username);
	}

	public BankUser createUser(BankUser user) {
		// BankUser user = new BankUser(username,password,firstname,lastname);
		return userDao.insertUser(user, true);
	}

	public BankUser getUser(String username) {
		return userDao.getUser(username);
	}

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

	public ArrayList<BankAccount> getUserAccounts(BankUser user) {
		return accountDao.getUserAccounts(user);
	}

	public ArrayList<BankAccount> getAllAccounts() {
		return accountDao.getAllAccounts();
	}

	public ArrayList<BankUser> getAllUsers() {
		return userDao.getAllUsers();
	}

	public BankUser login(BankUser user) {
		return userDao.validateUser(user);
	}

	public BankAccount getAccount(int id) {
		return accountDao.getAccount(id);
	}

	public BankAccount getAccount(int accNumber, int userID) {
		return accountDao.getAccount(accNumber, userID);
	}

	public BankAccount getAccount(BankAccount acc, BankUser user) {
		return accountDao.getAccount(acc, user);
	}

	public BankAccount updateAccount(BankAccount acc) {
		return accountDao.updateAccount(acc, true);
	}
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
	public ArrayList<BankUser> getAccountOwners(BankAccount acc){
		ArrayList<BankUser> accs = this.userDao.getAccountUsers(acc);
		return accs;
	}
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

	public ArrayList<Transaction> getUserTransactions(BankUser user) {
		return transDao.getUserTransactions(user);
	}

	public ArrayList<Transaction> getAccountTransactions(BankAccount acc) {
		return transDao.getAccountTransactions(acc);
	}

	public boolean deleteTransactions(BankAccount acc) {
		return transDao.deleteTransactions(acc, true);
	}

	public boolean deleteTransactions(BankUser user) {
		return transDao.deleteTransactions(user, true);
	}

	public Transaction insertTransaction(Transaction trans) {
		return transDao.insertTransaction(trans, true);
	}
}
