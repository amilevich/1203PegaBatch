package com.revature;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import com.revature.daos.AccountDaoImpl;
import com.revature.daos.CustomerDao;
import com.revature.daos.CustomerDaoImpl;
import com.revature.daos.EmployeeDaoImpl;
import com.revature.daos.OpenApplicationDao;
import com.revature.daos.OpenApplicationDaoImpl;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.CustomerAccount;
import com.revature.models.Employee;
import com.revature.models.OpenApplication;

public class BankApp {

	public static void main(String[] args) {
		
		try {
			
			int choice = 0;
				
			while ( choice != 9 ) {
			
				Scanner scanner = new Scanner(System.in);
		
				System.out.println("Welcome to The Bank!");
				System.out.println("1. Open Account");
				System.out.println("2. Login to Existing Account");
				System.out.println("3. Employee");
				System.out.println("9. Exit");
				
				choice = scanner.nextInt();
				
				switch (choice) {
				
					case 1:	doOpenAccount(scanner);
							break;
					case 2: doExistingAccount(scanner);
							break;
					case 3: doEmployee(scanner);
							break;
					default:break;
					
				}
				
			}
			
		} catch (FileNotFoundException fnfe) {
			
		} catch (IOException ioe) {
			
		}
		
		
	}
	
	private static void doOpenAccount(Scanner scanner) throws FileNotFoundException, IOException {
		
		OpenApplication openApplication = new OpenApplication();
		
		scanner = new Scanner(System.in);
		
		System.out.println("Please enter User Name");
		openApplication.setUsername(scanner.nextLine());
		
		System.out.println("Please enter a Password");
		openApplication.setPassword(scanner.nextLine());
		
		System.out.println("Please enter an Account Type");
		System.out.println("S (single) or J (joint)");
		openApplication.setAcctType(scanner.nextLine());

		if ( isApplicationAlreadyExists(openApplication) ) {
//			System.out.println("Application Already Exists!");
			openApplication.setPassword("X");
		}
//		} else {
			System.out.println("Please enter an Initial Deposit amount");
			openApplication.setInitDeposit(scanner.nextDouble());
			makeAccount(openApplication);
//		} 

	}
	
	private static boolean isApplicationAlreadyExists(OpenApplication openAppl) throws FileNotFoundException, IOException {
		
		OpenApplicationDao openApplDao = new OpenApplicationDaoImpl();
		boolean openApplFound = openApplDao.findOpenApplication(openAppl);
		
		return openApplFound;
		
	}
	
	private static void makeAccount(OpenApplication openAppl) throws IOException {
		
		OpenApplicationDao openApplDao = new OpenApplicationDaoImpl();
		openApplDao.saveOpenApplication(openAppl);
		
		System.out.println("Open Application created !");
		
	}	
	
	private static void doExistingAccount(Scanner scanner) throws FileNotFoundException, IOException {
		
		Customer customer = new Customer();
		
		scanner = new Scanner(System.in);
		
		System.out.println("Please enter User Name");
		customer.setUsername(scanner.nextLine());
		
		System.out.println("Please enter a Password");
		customer.setPassword(scanner.nextLine());
		
		if ( isCustomerFound(customer) ) {
			
			System.out.println("\nWhat would you like to do?");
			System.out.println("1. Withdrawal");
			System.out.println("2. Deposit");
			System.out.println("3. Transfer Funds");
			System.out.println("9. Exit");
			
			int choice = scanner.nextInt();
			
			switch (choice) {
			
				case 1:	makeWithdrawal(scanner, customer);
						break;
				case 2: makeDeposit(scanner, customer);
						break;
				case 3: xferFunds(scanner, customer);
						break;
				default: return;
				
			}
			
		} else {
			System.out.println("Customer Not Found");

		} 
		
	}
	
	private static boolean isCustomerFound(Customer customer) throws FileNotFoundException, IOException {
		
		CustomerDaoImpl customerDao = new CustomerDaoImpl();
		boolean customerFound = customerDao.findCustomer(customer);
		
		return customerFound;
		
	}
	
	private static void makeWithdrawal(Scanner scanner, Customer customer) throws IOException {
		
		scanner = new Scanner(System.in);
		double withdrawalAmt;
		int accountNbr;
		
		System.out.println("Please enter the Account Number");
		accountNbr = scanner.nextInt();
		
		Account account = getCustomerAccount(accountNbr, customer.getUsername());
		
		if ( account != null ) {
		
			System.out.println("\nPlease enter the Withdrawal amount");
			withdrawalAmt = scanner.nextDouble();

			if ( withdrawalAmt <= account.getAccountBal() &&
				 withdrawalAmt >= 0 ) {
				account.setAccountBal(account.getAccountBal() - withdrawalAmt);
				updateAccountBalance(account);
				System.out.println("Account updated");
			} else {
				System.out.println("Invalid Withdrawal Amount!");
			}
		
		} else {
			System.out.println("This account does not exist!");
		}
		
	}
	
	private static void xferFunds(Scanner scanner, Customer customer) throws IOException {
		
		scanner = new Scanner(System.in);

		int fromAccountNbr;
		int toAccountNbr;
		double xferAmount;
		
		System.out.println("Please enter the from Account Number");
		fromAccountNbr = scanner.nextInt();
		
		Account fromAccount = getCustomerAccount(fromAccountNbr, customer.getUsername());
		
		if ( fromAccount != null ) {
			
			System.out.println("Please enter the transfer amount.");
			xferAmount = scanner.nextInt();
		
			System.out.println("\nPlease enter the to Account Number");
			toAccountNbr = scanner.nextInt();
			
			Account toAccount = getCustomerAccount(toAccountNbr, customer.getUsername());
			
			if ( toAccount != null ) {
				
				if ( fromAccountNbr != toAccountNbr ) {
					
					if ( xferAmount <= fromAccount.getAccountBal() ) {
						
						toAccount.setAccountBal(toAccount.getAccountBal() + xferAmount);
						updateAccountBalance(toAccount);
						fromAccount.setAccountBal(fromAccount.getAccountBal() - xferAmount);
						updateAccountBalance(fromAccount);
						System.out.println("Funds Transferred.");
						
					} else {
						System.out.println("Transfer amount exceeds funds available!");
					}
					
				} else {
					System.out.println("To Account Must Be Different than From Account!");
				}
				
			} else {
				System.out.println("This account does not exist!");
			}
		
		} else {
			System.out.println("This account does not exist!");
		}
		
	}
	
	private static void makeDeposit(Scanner scanner, Customer customer) throws IOException {
		
		scanner = new Scanner(System.in);
		double depositAmt;
		int accountNbr;
		
		System.out.println("Please enter the Account Number");
		accountNbr = scanner.nextInt();
		
		Account account = getCustomerAccount(accountNbr, customer.getUsername());
		
		if ( account != null ) {
		
			System.out.println("\nPlease enter the Deposit amount");
			depositAmt = scanner.nextDouble();

			account.setAccountBal(account.getAccountBal() + depositAmt);
			updateAccountBalance(account);
			System.out.println("Account updated");
		
		}
		
	}
	
	private static void updateAccountBalance(Account account) {
		
		AccountDaoImpl accountDao = new AccountDaoImpl();
		accountDao.updateAccountBalance(account);
	    
	}
	
	private static Account getCustomerAccount(int accountNbr, String userName) throws FileNotFoundException, IOException {
		
		AccountDaoImpl accountDao = new AccountDaoImpl();
		Account account = accountDao.getCustomerAccount(accountNbr, userName);
		
		return account;
		
	}
	
	private static Account getAccount(int accountNbr) throws FileNotFoundException, IOException {
		
		AccountDaoImpl accountDao = new AccountDaoImpl();
		Account account = new Account();
		account = accountDao.getAccount(accountNbr);
		
		return account;
		
	}
	
	private static void doEmployee(Scanner scanner) throws FileNotFoundException, IOException {
		
		Employee employee = new Employee();
		int choice = 0;
		
		scanner = new Scanner(System.in);
		
		System.out.println("Please enter User Name");
		employee.setUsername(scanner.nextLine());
		
		System.out.println("Please enter a Password");
		employee.setPassword(scanner.nextLine());
		
		while ( choice != 9 ) {
			
			if ( isEmployeeExists(employee) ) {
				
				if ( isBusinessAnalyst(employee) ) {
					
					System.out.println("What would you like to do?");
					System.out.println("1. Approve/Deny Apps");
					System.out.println("2. Withdraw/Deposit");
					System.out.println("3. Cancel Account");
					System.out.println("4. Transfer Funds");
					System.out.println("9. Exit");
					
					choice = scanner. nextInt();
					
					switch ( choice ) {
					
						case 1:	doApproveDeny(scanner, employee);
								break;
								
						case 2:	doWithdrawDeposit(scanner, employee);
								break;
								
						case 3:	doCancelAccount(scanner, employee);
								break;
								
						case 4: xferFundsEmp(scanner);
								break;
								
						default:break;
						
					}
				
				} else {
					
					System.out.println("What would you like to do?");
					System.out.println("1. Approve/Deny Apps");
					System.out.println("2. View Customer Accounts");
					System.out.println("9. Exit");
					
					choice = scanner. nextInt();
					
					switch ( choice ) {
					
						case 1:	doApproveDeny(scanner, employee);
								break;
								
						case 2:	viewCustomerAccounts();
								break;
								
						default:break;
						
					}
					
				}
				
			} else {
				System.out.println("Employee does not exist");
				choice = 9;
			}
			
		}
		
	}
	
	private static boolean isEmployeeExists(Employee employee) throws FileNotFoundException, IOException {
		
		EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
		boolean employeeFound = employeeDao.findEmployee(employee);
		
		return employeeFound;
		
	}
	
	private static boolean isBusinessAnalyst(Employee employee) throws FileNotFoundException, IOException {
		
		EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
		boolean busAdminFound = employeeDao.findBusinessAnalyst(employee);
		
		return busAdminFound;
		
	}
	
	private static void doApproveDeny(Scanner scanner, Employee employee) throws FileNotFoundException, IOException {
		
		OpenApplicationDaoImpl openApplDao = new OpenApplicationDaoImpl();
		List<OpenApplication> openAppls = openApplDao.getOpenApplications();
		
		scanner = new Scanner(System.in);
		
		String stringLine;
		String [] splitStrings = new String [3];
		String choice = "";

		while ( !choice.equals("9") && !choice.equals("S") && !choice.equals("s") ) {
			
			if ( openAppls.size() == 0 ) {
				System.out.println("There are no Open Applications at this time.");
				choice = "9";
			}
		
			for ( OpenApplication openAppl : openAppls)   {
				
				System.out.println(	"Id: " + openAppl.getId() + "\t"
								+	"User Name: " + openAppl.getUsername() + "\t"
								+	"Password: " + openAppl.getPassword() + "\t"
								+	"Account Type:" + openAppl.getAcctType() + "\t"
								+	"Initial Deposit: " + openAppl.getInitDeposit() );
				
				System.out.println(	"\nApprove (A), Deny (D), Skip (S), or Return (9) ?" );
				
				choice = scanner.nextLine();
				
				if ( choice.equals("9") ) {
					break;
				}
				
				if ( choice.equals("A") || choice.equals("a") ) {
					doApprove(openAppl);
					System.out.println("Application approved");
				} else if ( choice.equals("D") || choice.equals("d") ) {
					removeOpenApplication(openAppl);
					System.out.println("Application denied");
				}
				
			}
			
		}
		
	}
	
	private static void doApprove(OpenApplication openAppl) throws IOException {
		
		CustomerDaoImpl customerDao = new CustomerDaoImpl();
		Customer customer = new Customer();
		customer.setUsername(openAppl.getUsername());
		customer.setPassword(openAppl.getPassword());
		
		if ( !customerDao.findCustomer(customer) &&
			 !openAppl.getPassword().equals("X") ) {
			customerDao.saveCustomer(customer);
		}

		AccountDaoImpl accountDao = new AccountDaoImpl();
		accountDao.saveAccount(openAppl);
		
		removeOpenApplication(openAppl);

	}
	
	private static int getNewAccountId() throws IOException {
		
		int acctId = 0;

        BufferedReader file = new BufferedReader(new FileReader("Accounts.txt"));
        String line;
        String [] splitStrings = new String [3];
        int i = 0;

        while ((line = file.readLine()) != null) {
        	splitStrings = line.split(":");
        	if ( i == 0 ) {
        		acctId = Integer.parseInt(splitStrings[0]);
        	} else {
        		if ( Integer.parseInt(splitStrings[0]) > acctId ) {
        			acctId = Integer.parseInt(splitStrings[0]);
        		} 
        	}
        }

        file.close();
        
        return acctId + 1;
		
	}
	
	private static void removeOpenApplication(OpenApplication openAppl) throws FileNotFoundException, IOException {

		OpenApplicationDaoImpl openApplDao = new OpenApplicationDaoImpl();
		openApplDao.deleteOpenApplication(openAppl);
        
	}
	
	private static void doWithdrawDeposit(Scanner scanner, Employee employee) throws IOException {
		
		int choice = 0;
		scanner = new Scanner(System.in);
		
		while ( choice != 9 ) {
			
			System.out.println("Please enter the following:");
			System.out.println("1. Withdrawal");
			System.out.println("2. Deposit");
			System.out.println("9. Return");
			
			choice = scanner.nextInt();
			
			switch (choice) {
				case 1:	doWithdrawal(scanner);
						break;
				case 2: doDeposit(scanner);
						break;
				default:break;
			}
			
		}
		
	}
	
	private static void doWithdrawal(Scanner scanner) throws IOException{
		
		scanner = new Scanner(System.in);
		Customer customer = new Customer();
		double withdrawalAmt;
		int accountNbr;
		
		System.out.println("Please enter the Account Number");
		accountNbr = scanner.nextInt();
		
		Account account = getAccount(accountNbr);
		
		if ( account != null ) {
		
			System.out.println("\nPlease enter the Withdrawal amount");
			withdrawalAmt = scanner.nextDouble();

			if ( withdrawalAmt <= account.getAccountBal() ) {
				account.setAccountBal(account.getAccountBal() - withdrawalAmt);
				updateAccountBalance(account);
				System.out.println("Account updated");
			}
		
		}

	}
	
	private static void doDeposit(Scanner scanner) throws IOException{
		
		scanner = new Scanner(System.in);
		double depositAmt;
		int accountNbr;
		
		System.out.println("Please enter the Account Number");
		accountNbr = scanner.nextInt();
		
		Account account = getAccount(accountNbr);
		
		if ( account != null ) {
		
			System.out.println("\nPlease enter the Deposit amount");
			depositAmt = scanner.nextDouble();

			account.setAccountBal(account.getAccountBal() + depositAmt);
			updateAccountBalance(account);
			System.out.println("Account updated");
		
		}

	}
	
	private static void doCancelAccount(Scanner scanner, Employee employee) throws IOException {
		
		Customer customer = new Customer();
		String choice = "";
		String acctType = "";
		
		scanner = new Scanner(System.in);
		
		System.out.println("Please enter User Name");
		customer.setUsername(scanner.nextLine());
		
		System.out.println("Please enter a Password");
		customer.setPassword(scanner.nextLine());
		
		if ( isCustomerFound(customer) ) {
			
			if ( isCustomerAccountFound(customer) ) {
				
				System.out.println("Do you want to delete this customer's account?");
				System.out.println("Enter 'Y' or 'N' or '9' (to return)");
				choice = scanner.nextLine();
				
				System.out.println("Please enter the Account Type");
				System.out.println("S (single) or J (joint)");
				acctType = scanner.nextLine();
				
			}

			if ( choice.equals("Y") || choice.equals("y") ) {
				removeCustomerAccount(customer, acctType);
			}

			
		} else {
			System.out.println("Customer Not Found");
		} 
		
	}
	
	private static boolean isCustomerAccountFound(Customer customer) throws FileNotFoundException, IOException {
		
		FileInputStream fis = new FileInputStream("Accounts.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		
		String stringLine;
		String [] splitStrings = new String [3];
		
		while ((stringLine = br.readLine()) != null)   {
			splitStrings = stringLine.split(":");
			if ( splitStrings[1].equals(customer.getUsername()) ) {
				return true;
			}
		}
		
		return false;
	}
	
	private static void removeCustomerAccount(Customer customer, String acctType) throws FileNotFoundException, IOException {

        BufferedReader file = new BufferedReader(new FileReader("Accounts.txt"));
        String line;
        String [] splitStrings = new String [4];
        StringBuffer inputBuffer = new StringBuffer();

        while ((line = file.readLine()) != null) {
        	splitStrings = line.split(":");
        	if ( splitStrings[1].equals(customer.getUsername()) &&
        		 splitStrings[2].equals(acctType) ) {
        		continue;
        	} else {
        		inputBuffer.append(line);
        	}
            inputBuffer.append('\n');
        }
        String inputStr = inputBuffer.toString();

        file.close();

        // write the new String with the replaced line OVER the same file
        FileOutputStream fileOut = new FileOutputStream("Accounts.txt");
        fileOut.write(inputStr.getBytes());
        fileOut.close();
	}
	
	private static void xferFundsEmp(Scanner scanner) throws IOException {
		
		scanner = new Scanner(System.in);

		String custUsername;
		int fromAccountNbr;
		int toAccountNbr;
		double xferAmount;
		
		System.out.println("Please enter the Customer's Username.");
		custUsername = scanner.nextLine();
		
		System.out.println("Please enter the from Account Number");
		fromAccountNbr = scanner.nextInt();
		
		Account fromAccount = getCustomerAccount(fromAccountNbr, custUsername);
		
		if ( fromAccount != null ) {
			
			System.out.println("\nPlease enter the transfer amount.");
			xferAmount = scanner.nextDouble();
		
			System.out.println("\nPlease enter the to Account Number");
			toAccountNbr = scanner.nextInt();
			
			Account toAccount = getCustomerAccount(toAccountNbr, custUsername);
			
			if ( toAccount != null ) {
				
				if ( fromAccountNbr != toAccountNbr ) {
					
					if ( xferAmount <= fromAccount.getAccountBal() ) {
						
						toAccount.setAccountBal(toAccount.getAccountBal() + xferAmount);
						updateAccountBalance(toAccount);
						fromAccount.setAccountBal(fromAccount.getAccountBal() - xferAmount);
						updateAccountBalance(fromAccount);
						System.out.println("Funds Transferred.");
						
					} else {
						System.out.println("Transfer amount exceeds funds available!");
					}
					
				} else {
					System.out.println("To Account Must Be Different than From Account!");
				}
				
			} else {
				System.out.println("This account does not exist for this customer!");
			}
		
		} else {
			System.out.println("This account does not exist for this customer!");
		}
		
	}
	
	public static void viewCustomerAccounts() throws FileNotFoundException, IOException {
		
		AccountDaoImpl accountDao = new AccountDaoImpl();
		List<CustomerAccount> customerAccounts = accountDao.getCustomerAccounts();
		
		System.out.println("ACCOUNTS REPORT");
		
		for (CustomerAccount ca : customerAccounts) {
			
			System.out.println("User Name: " + ca.getUserName() + "\t"
							+  "Password: " + ca.getPassword() + "\t"
							+  "Account Number: " + ca.getAccountNbr() + "\t"
							+  "Account Type: " + ca.getAcctType() + "\t"
							+  "Account Balance: " + ca.getAccountBal() );
			
		}

	}
	
	public static void viewCustomers(Scanner scanner, Employee employee) throws FileNotFoundException, IOException {
		
		FileInputStream fis = new FileInputStream("Customers.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		
		String stringLine;
		String [] splitStrings = new String [3];
		Customer customer = new Customer();
		
		System.out.println("CUSTOMERS REPORT");
		
		while ((stringLine = br.readLine()) != null)   {
			
			splitStrings = stringLine.split(":");
			
			customer.setUsername(splitStrings[0]);
			customer.setPassword(splitStrings[1]);
			
			System.out.println("Customer Number: " + customer.getUsername() + "\t"
							+  "Password: " + customer.getPassword() );

		}
	}

}
