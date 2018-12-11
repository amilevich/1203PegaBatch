package projectPartZero;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

public class FileManipulation {
	
	public static void updateAccount(Customer customer) {
		String inputFileName = "accounts.txt";
		String outputFileName = "temp.txt";
		int accountID;
		String balance = "";
		try {
		    File inputFile = new File(inputFileName);
		    File outputFile = new File(outputFileName);
		    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
		        // Read each line from the reader and compare it with
		        // with the line to remove and write if required
		        String line = null;
		        String aLine = null;
		        for(int i = 0; i < customer.getAccounts().size(); i++) {
		        	aLine = customer.getAccountNumber() + ":" + customer.getAccount(i).getBalance();
		        	writer.write(aLine);
		        	writer.newLine();
		        }
		        while ((line = reader.readLine()) != null) {
		        	String[] accountData = line.split(":");
		        	accountID = Integer.parseInt(accountData[0]);
		        	balance = accountData[1];
		            if (!(Integer.parseInt(customer.getAccountNumber()) == accountID)) {
		                writer.write(line);
		                writer.newLine();
		            }
		        }
		    }
		    if (inputFile.delete()) {
		        // Rename the output file to the input file
		        if (!outputFile.renameTo(inputFile)) {
		            throw new IOException("Could not rename " + outputFileName + " to " + inputFileName);
		        }
		    } else {
		        throw new IOException("Could not delete original input file " + inputFileName);
		    }
		} catch (IOException ex) {
		    // Handle any exceptions
		    ex.printStackTrace();
		}
	}
	
	public static void createApplication(String name, String ss, String alias, String passWord) {
		
		String fileName = "applications.txt";
		File file = new File(fileName);
		boolean exists = file.exists();
		
		if(exists) {
			try(OutputStream os = new FileOutputStream(file, true)){
				String line = name + ":" + ss + ":" + alias + ":" + passWord;
				os.write(line.getBytes());
				String lineSeparator = System.getProperty("line.separator");
				os.write(lineSeparator.getBytes());
				os.flush();
				os.close();
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		else {
			try(OutputStream os = new FileOutputStream(file)){
				String line = name + ":" + ss + ":" + alias + ":" + passWord;
				os.write(line.getBytes());
				String lineSeparator = System.getProperty("line.separator");
				os.write(lineSeparator.getBytes());
				os.flush();
				os.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		
	
		
	}
	
	public static ArrayList<Account> getUserAccounts(int id) {
		Account anAccount = null;
		ArrayList<Account> accounts = new ArrayList<Account>();
		try(FileInputStream accFile = new FileInputStream("accounts.txt")){
			BufferedReader brr = new BufferedReader(new InputStreamReader(accFile));
			String lines = "";
			int accountID;
			String balance = "";
			while((lines = brr.readLine()) != null) {
					String[] accountData = lines.split(":");
					accountID = Integer.parseInt(accountData[0]);
					balance = accountData[1];
					if(id == accountID) {
						anAccount = new Account(id, Integer.parseInt(balance));
						accounts.add(anAccount);
					}
				}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accounts;
	}
	
	public static Customer getACustomer(String givenUserName, String givenPassword) {
		Customer aCustomer = null;
		try(FileInputStream file = new FileInputStream("customers.txt")){
			BufferedReader br = new BufferedReader(new InputStreamReader(file));
				String line = "";
				String customerID = "";
				String name = "";
				String social = "";
				String userName = "";
				String password = "";
				while((line = br.readLine()) != null) {
					String[] data = line.split(":");
					customerID = data[0];
					name = data[1];
					social = data[2];
					userName = data[3];
					password = data[4];
					if(givenUserName.equals(userName) && givenPassword.equals(password)) {
						System.out.println("Welcome " + "" + name + " " + userName);
						aCustomer = new Customer(name, userName, social, password, customerID);
						}		
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return aCustomer;
	}
	
	public static ArrayList<Customer> getAllCustomer() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		try(FileInputStream file = new FileInputStream("customers.txt")){
			BufferedReader br = new BufferedReader(new InputStreamReader(file));
				String line = "";
				String customerID = "";
				String name = "";
				String social = "";
				String userName = "";
				String password = "";
				while((line = br.readLine()) != null) {
					String[] data = line.split(":");
					customerID = data[0];
					name = data[1];
					social = data[2];
					userName = data[3];
					password = data[4];
					Customer aCustomer = new Customer(name, userName, social, password, customerID);
					customers.add(aCustomer);
				}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return customers;
	}
	
	public static void removeCustomer(Customer customer) {
			String inputFileName = "customers.txt";
			String outputFileName = "temp.txt";
			int accountID;
			String balance = "";
			String name = "";
			String ss = "";
			String username = "";
			String password = "";
			try {
			    File inputFile = new File(inputFileName);
			    File outputFile = new File(outputFileName);
			    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
			        // Read each line from the reader and compare it with
			        // with the line to remove and write if required
			        String line = null;
			        while ((line = reader.readLine()) != null) {
			        	String[] accountData = line.split(":");
			        	accountID = Integer.parseInt(accountData[0]);
			        	name = accountData[1];
			        	ss = accountData[2];
			        	username = accountData[3];
			        	password = accountData[4];
			            if (!(Integer.parseInt(customer.getAccountNumber()) == accountID)) {
			                writer.write(line);
			                writer.newLine();
			            }
			        }
			    }
			    if (inputFile.delete()) {
			        // Rename the output file to the input file
			        if (!outputFile.renameTo(inputFile)) {
			            throw new IOException("Could not rename " + outputFileName + " to " + inputFileName);
			        }
			    } else {
			        throw new IOException("Could not delete original input file " + inputFileName);
			    }
			} catch (IOException ex) {
			    // Handle any exceptions
			    ex.printStackTrace();
			}
		
	}

	public static void removeAccount(Customer customer) {
		if (customer.getAccounts().size() == 0) {
			String inputFileName = "accounts.txt";
			String outputFileName = "temp.txt";
			int accountID;
			String balance = "";
			try {
			    File inputFile = new File(inputFileName);
			    File outputFile = new File(outputFileName);
			    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
			        // Read each line from the reader and compare it with
			        // with the line to remove and write if required
			        String line = null;
			        while ((line = reader.readLine()) != null) {
			        	String[] accountData = line.split(":");
			        	accountID = Integer.parseInt(accountData[0]);
			        	balance = accountData[1];
			            if (!(Integer.parseInt(customer.getAccountNumber()) == accountID)) {
			                writer.write(line);
			                writer.newLine();
			            }
			        }
			    }
			    if (inputFile.delete()) {
			        // Rename the output file to the input file
			        if (!outputFile.renameTo(inputFile)) {
			            throw new IOException("Could not rename " + outputFileName + " to " + inputFileName);
			        }
			    } else {
			        throw new IOException("Could not delete original input file " + inputFileName);
			    }
			} catch (IOException ex) {
			    // Handle any exceptions
			    ex.printStackTrace();
			}
			removeCustomer(customer);
		}
		else {
			String inputFileName = "accounts.txt";
			String outputFileName = "temp.txt";
			int accountID;
			String balance = "";
			try {
			    File inputFile = new File(inputFileName);
			    File outputFile = new File(outputFileName);
			    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
			        // Read each line from the reader and compare it with
			        // with the line to remove and write if required
			        String line = null;
			        String aLine = null;
			        for(int i = 0; i < customer.getAccounts().size(); i++) {
			        	aLine = customer.getAccountNumber() + ":" + customer.getAccount(i).getBalance();
			        	writer.write(aLine);
			        	writer.newLine();
			        }
			        while ((line = reader.readLine()) != null) {
			        	String[] accountData = line.split(":");
			        	accountID = Integer.parseInt(accountData[0]);
			        	balance = accountData[1];
			            if (!(Integer.parseInt(customer.getAccountNumber()) == accountID)) {
			                writer.write(line);
			                writer.newLine();
			            }
			        }
			    }
			    if (inputFile.delete()) {
			        // Rename the output file to the input file
			        if (!outputFile.renameTo(inputFile)) {
			            throw new IOException("Could not rename " + outputFileName + " to " + inputFileName);
			        }
			    } else {
			        throw new IOException("Could not delete original input file " + inputFileName);
			    }
			} catch (IOException ex) {
			    // Handle any exceptions
			    ex.printStackTrace();
			}
		}
	
		
	}

	public static void createJointApplication(String aName, String social, String userName, String passWord,
			String anName, String socialSecurity, String userName2, String passWord2) {
		
		String fileName = "jointApplications.txt";
		File file = new File(fileName);
		boolean exists = file.exists();
		
		if(exists) {
			try(OutputStream os = new FileOutputStream(file, true)){
				String line = aName + ":" + social + ":" + userName + ":" + passWord;
				String line2 = aName + ":" + socialSecurity + ":" + userName2 + ":" + passWord2;
				os.write(line.getBytes());
				String lineSeparator = System.getProperty("line.separator");
				os.write(lineSeparator.getBytes());
				os.write(line2.getBytes());
				os.flush();
				os.close();
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		else {
			try(OutputStream os = new FileOutputStream(file)){
				String line = aName + ":" + social + ":" + userName + ":" + passWord;
				String line2 = aName + ":" + socialSecurity + ":" + userName2 + ":" + passWord2;
				os.write(line.getBytes());
				String lineSeparator = System.getProperty("line.separator");
				os.write(lineSeparator.getBytes());
				os.write(line2.getBytes());
				os.flush();
				os.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}

}
