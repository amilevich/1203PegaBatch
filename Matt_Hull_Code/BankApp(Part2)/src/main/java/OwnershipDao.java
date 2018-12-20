import java.util.ArrayList;

public interface OwnershipDao {
	public Ownership insertConnection(BankAccount acc, BankUser user, boolean commit);
	public Ownership insertConnection(Ownership link, boolean commit);
	
	public ArrayList<Ownership> getAccountOwners(int accountNumber);
	public ArrayList<Ownership> getAccountOwners(BankAccount acc);
	public ArrayList<Ownership> getOwnersAccounts(int userID);
	public ArrayList<Ownership> getOwnersAccounts(BankUser user);
	
	public boolean deleteRelationship(Ownership relationship, boolean commit);
	public boolean deleteRelationships(BankAccount acc, boolean commit);
	public boolean deleteRelationships(BankUser user, boolean commit);
	

}
