package bank;

public interface CustomerDao {
	public Boolean addCustomer(Customer customer);
	public void updateAmountCustomer(Customer customer);
	public void getCustomers();
	public void updateInDb();
	public Boolean addNewCustomer(Customer customer);
	public void getNewCustomers();
	public Boolean removeNewCustomer(Customer customer);
	public Boolean removeCustomer(Customer customer);
	public void updateJoin(Customer c1);
}
