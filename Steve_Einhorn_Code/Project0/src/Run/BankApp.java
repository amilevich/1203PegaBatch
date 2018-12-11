package Run;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import Source.Account;
import Source.Customer;
import Source.Employee;
import Source.OpenApplication;

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
		
		if ( isApplicationAlreadyExists(openApplication) ) {
			System.out.println("Application Already Exists!");
		} else {
			System.out.println("Please enter an Initial Deposit amount");
			openApplication.setInitDeposit(scanner.nextDouble());
			makeAccount(openApplication);
		} 
		
	}
	
	private static boolean isApplicationAlreadyExists(OpenApplication openAppl) throws FileNotFoundException, IOException {
		
		FileInputStream fis = new FileInputStream("OpenApplications.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		
		String stringLine;
		String [] splitStrings = new String [3];
		
		while ((stringLine = br.readLine()) != null)   {
			splitStrings = stringLine.split(":");
			if ( splitStrings[0].equals(openAppl.getUsername()) && splitStrings[1].equals(openAppl.getPassword()) ) {
				return true;
			}
		}
		
		return false;
		
	}
	
	private static void makeAccount(OpenApplication openAppl) throws IOException {
		
		FileWriter fw = new FileWriter("OpenApplications.txt", true); 
		BufferedWriter bwr = new BufferedWriter(fw);
		
		bwr.write(openAppl.getUsername() + ":" + openAppl.getPassword() + ":" + openAppl.getInitDeposit());
		bwr.newLine();
		bwr.close();
		
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
			System.out.println("3. Exit");
			
			int choice = scanner.nextInt();
			
			switch (choice) {
			
				case 1:	makeWithdrawal(scanner, customer);
						break;
				case 2: makeDeposit(scanner, customer);
						break;
				case 3: return;
				
			}
			
		} else {
			System.out.println("Customer Not Found");

		} 
		
	}
	
	private static boolean isCustomerFound(Customer customer) throws FileNotFoundException, IOException {
		
		FileInputStream fis = new FileInputStream("Customers.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		
		String stringLine;
		String [] splitStrings = new String [3];
		
		while ((stringLine = br.readLine()) != null)   {
			splitStrings = stringLine.split(":");
			if ( splitStrings[0].equals(customer.getUsername()) && splitStrings[1].equals(customer.getPassword()) ) {
				return true;
			}
		}
		
		return false;
		
	}
	
	private static void makeWithdrawal(Scanner scanner, Customer customer) throws IOException {
		
		double newBal = 0;
		double withdrawalAmt = 0;
		
		System.out.println("Please enter Withdrawal Amount");
		withdrawalAmt = scanner.nextDouble();
		
		Account account = getAccount(customer);
		
		if ( account != null ) {
			if ( withdrawalAmt <= account.getAccountBal() ) {
				account.setAccountBal(account.getAccountBal() - withdrawalAmt);
				updateAccountBalance(customer, account);
			}
		}
		
	}
	
	private static void makeDeposit(Scanner scanner, Customer customer) throws IOException {
		
		double newBal = 0;
		double depositAmt = 0;
		
		System.out.println("Please enter Deposit Amount");
		depositAmt = scanner.nextDouble();
		
		Account account = getAccount(customer);
		
		if ( account != null ) {
			account.setAccountBal(account.getAccountBal() + depositAmt);
			updateAccountBalance(customer, account);
		}
		
	}
	
	private static void updateAccountBalance(Customer customer, Account account) {

	    try {

	        BufferedReader file = new BufferedReader(new FileReader("Accounts.txt"));
	        String line;
	        String [] splitStrings = new String [3];
	        StringBuffer inputBuffer = new StringBuffer();

	        while ((line = file.readLine()) != null) {
	        	splitStrings = line.split(":");
	        	if ( splitStrings[1].equals(customer.getUsername()) ) {
	        		inputBuffer.append(splitStrings[0] + ":" + splitStrings[1] + ":" + account.getAccountBal());
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

	    } catch (Exception e) {
	        System.out.println("Problem reading file.");
	    }
	    
	}
	
	private static Account getAccount(Customer customer) throws FileNotFoundException, IOException {
		
		FileInputStream fis = new FileInputStream("Accounts.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		
		String stringLine;
		String [] splitStrings = new String [3];
		Account account = null;
		
		while ((stringLine = br.readLine()) != null)   {
			splitStrings = stringLine.split(":");
			if ( splitStrings[1].equals(customer.getUsername()) ) {
				account = new Account();
				account.setAccountNbr(splitStrings[0]);
				account.setUserName(splitStrings[1]);
				account.setAccountBal(Double.parseDouble(splitStrings[2]));
			}
		}
		
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
					System.out.println("9. Exit");
					
					choice = scanner. nextInt();
					
					switch ( choice ) {
					
						case 1:	doApproveDeny(scanner, employee);
								break;
								
						case 2:	doWithdrawDeposit(scanner, employee);
								break;
								
						case 3:	doCancelAccount(scanner, employee);
								break;
								
						default:break;
						
					}
				
				} else {
					
				}
				
			} else {
				System.out.println("Employee does not exist");
			}
			
		}
		
	}
	
	private static boolean isEmployeeExists(Employee employee) throws FileNotFoundException, IOException {
		
		FileInputStream fis = new FileInputStream("Employees.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		
		String stringLine;
		String [] splitStrings = new String [3];
		
		while ((stringLine = br.readLine()) != null)   {
			splitStrings = stringLine.split(":");
			if ( splitStrings[0].equals(employee.getUsername()) && splitStrings[1].equals(employee.getPassword()) ) {
				return true;
			}
		}
		
		return false;
		
	}
	
	private static boolean isBusinessAnalyst(Employee employee) throws FileNotFoundException, IOException {
		
		FileInputStream fis = new FileInputStream("Employees.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		
		String stringLine;
		String [] splitStrings = new String [3];
		
		while ((stringLine = br.readLine()) != null)   {
			splitStrings = stringLine.split(":");
			if ( splitStrings[0].equals(employee.getUsername()) && 
				 splitStrings[1].equals(employee.getPassword()) &&
				 splitStrings[2].equals("BA") ) {
				return true;
			}
		}
		
		return false;
		
	}
	
	private static void doApproveDeny(Scanner scanner, Employee employee) throws FileNotFoundException, IOException {
		
		FileInputStream fis = new FileInputStream("OpenApplications.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		
		scanner = new Scanner(System.in);
		
		String stringLine;
		String [] splitStrings = new String [3];
		String choice = "";
		
		System.out.println(choice);

		while ( !choice.equals("9") ) {
		
			while ((stringLine = br.readLine()) != null)   {
				
				splitStrings = stringLine.split(":");
				
				OpenApplication openAppl = new OpenApplication();
				
				openAppl.setUsername(splitStrings[0]);
				openAppl.setPassword(splitStrings[1]);
				openAppl.setInitDeposit(Double.valueOf(splitStrings[2]));
				
				System.out.println(	"User Name: " + openAppl.getUsername() + "\t"
								+	"Password: " + openAppl.getPassword() + "\t"
								+	"Initial Deposit: " + openAppl.getInitDeposit() );
				
				System.out.println(	"\nApprove (A), Deny (D), Skip (S), or Return (9) ?" );
				
				choice = scanner.nextLine();
				
				if ( choice.equals("9") ) {
					break;
				}
				
				if ( choice.equals("A") || choice.equals("a") ) {
					doApprove(openAppl);
				} else if ( choice.equals("D") || choice.equals("d") ) {
					removeOpenApplication(openAppl);
				}
				
			}
			
		}
		
	}
	
	private static void doApprove(OpenApplication openAppl) throws IOException {
		
		FileWriter fwCust = new FileWriter("Customers.txt", true); 
		BufferedWriter bwrCust = new BufferedWriter(fwCust);
		
		bwrCust.write(openAppl.getUsername() + ":" + openAppl.getPassword());
		bwrCust.newLine();
		bwrCust.close();
		
		int acctId = getNewAccountId();
		
		FileWriter fwAcct = new FileWriter("Accounts.txt", true); 
		BufferedWriter bwrAcct = new BufferedWriter(fwAcct);
		
		bwrAcct.write(acctId + ":" + openAppl.getUsername() + ":" + openAppl.getInitDeposit());
		bwrAcct.newLine();
		bwrAcct.close();
		
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

        BufferedReader file = new BufferedReader(new FileReader("OpenApplications.txt"));
        String line;
        String [] splitStrings = new String [3];
        StringBuffer inputBuffer = new StringBuffer();

        while ((line = file.readLine()) != null) {
        	splitStrings = line.split(":");
        	if ( splitStrings[0].equals(openAppl.getUsername()) &&
        		 splitStrings[1].equals(openAppl.getPassword()) ) {
        		continue;
        	} else {
        		inputBuffer.append(line);
        	}
            inputBuffer.append('\n');
        }
        String inputStr = inputBuffer.toString();

        file.close();

        // write the new String with the replaced line OVER the same file
        FileOutputStream fileOut = new FileOutputStream("OpenApplications.txt");
        fileOut.write(inputStr.getBytes());
        fileOut.close();
        
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
		
		System.out.println("Please enter the Customer's Username:");
		customer.setUsername(scanner.nextLine());
		System.out.println("Please enter the Customer's Password:");
		customer.setPassword(scanner.nextLine());
		
		if ( isCustomerFound(customer) ) {
			
			System.out.println("\nPlease enter the Withdrawal amount");
			withdrawalAmt = scanner.nextDouble();
			
			Account account = getAccount(customer);
			
			if ( account != null ) {
				if ( withdrawalAmt <= account.getAccountBal() ) {
					account.setAccountBal(account.getAccountBal() - withdrawalAmt);
					updateAccountBalance(customer, account);
					System.out.println("Account updated");
				}
			}
			
		} else {
			System.out.println("Customer Not Found!!");
		}

	}
	
	private static void doDeposit(Scanner scanner) throws IOException{
		
		scanner = new Scanner(System.in);
		Customer customer = new Customer();
		double depositAmt;
		
		System.out.println("Please enter the Customer's Username:");
		customer.setUsername(scanner.nextLine());
		System.out.println("Please enter the Customer's Password:");
		customer.setPassword(scanner.nextLine());
		
		if ( isCustomerFound(customer) ) {
			
			System.out.println("\nPlease enter the Deposit amount");
			depositAmt = scanner.nextDouble();
			
			Account account = getAccount(customer);
			
			if ( account != null ) {
				account.setAccountBal(account.getAccountBal() + depositAmt);
				updateAccountBalance(customer, account);
			}
			
		}

	}
	
	private static void doCancelAccount(Scanner scanner, Employee employee) throws IOException {
		
		Customer customer = new Customer();
		String choice = "";
		
		scanner = new Scanner(System.in);
		
		System.out.println("Please enter User Name");
		customer.setUsername(scanner.nextLine());
		
		System.out.println("Please enter a Password");
		customer.setPassword(scanner.nextLine());
		
		if ( isCustomerFound(customer) ) {
			
			if ( isCustomerAccountFound(customer) ) {
				
				System.out.println("Do you want to delete this customer's account?");
				System.out.println("Enter 'Y' or 'N'");
				choice = scanner.nextLine();
				
			}

			if ( choice.equals("Y") || choice.equals("y") ) {
				removeCustomerAccount(customer);
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
	
	private static void removeCustomerAccount(Customer customer) throws FileNotFoundException, IOException {

        BufferedReader file = new BufferedReader(new FileReader("Accounts.txt"));
        String line;
        String [] splitStrings = new String [3];
        StringBuffer inputBuffer = new StringBuffer();

        while ((line = file.readLine()) != null) {
        	splitStrings = line.split(":");
        	if ( splitStrings[1].equals(customer.getUsername()) ) {
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

}
