package projectPartZero;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File; 
import java.io.FileOutputStream;
import java.io.OutputStream;


public class Employee implements User {

	private String name;
	private String userName;
	
	Employee(String userName){
		this.userName = userName;
	}
	
	@Override
	public String getName() {
		return this.name;
		
	}
	
	@Override
	public String getUserName() {
		return this.userName;
		
	}
	
	public void displayAllInfo() {
		ArrayList<Customer> customers = FileManipulation.getAllCustomer();
		for (int i = 0; i < customers.size(); i++) {
			ArrayList<Account> accounts = FileManipulation.getUserAccounts(Integer.parseInt(customers.get(i).getAccountNumber()));
			for (int j = 0; j < accounts.size(); j++) {
				customers.get(i).getAccounts().add(accounts.get(j));
			}
			System.out.println("Customer Information");
			System.out.println(customers.get(i));
			customers.get(i).displayAccounts();
		}
		
		
	}
	
	
	public void getApplications() {
		int response = 0;
		System.out.println("Here are the following applications");
		try(FileInputStream file = new FileInputStream("applications.txt")){
			BufferedReader br = new BufferedReader(new InputStreamReader(file));
			String line = "";
			String name = "";
			String userName = "";
			String ss = "";
			String password = "";
			while((line = br.readLine()) != null) {
				String[] data = line.split(":");
				name = data[0];
				ss = data[1];
				userName = data[2];
				password = data[3];
				System.out.println("Application: " + name + " " + userName);
				System.out.println("1.) Approve");
				System.out.println("2.) Deny");
				System.out.print("Enter response here: ");
				response = Main.input.nextInt();
				switch(response) {
				case 1:
					this.approveOrDenyApplication(name, ss, userName, password);
					break;
				case 2:
					System.out.println("In case 2");
					System.out.println("Application denied");
					break;
				}
			}
			br.close();
			file.close();
			File aFile = new File("applications.txt");
			if (aFile.exists()) {
				aFile.delete();     
			}
		} catch (FileNotFoundException e) {
			System.out.println("No new applications to be seen");
			System.out.println("Going back to menu");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void approveOrDenyApplication(String name, String ss, String userName, String password) {
		String fileName = "customers.txt";
		File file = new File(fileName);
		boolean exists = file.exists();
		int id = (int)(Math.random() * 50 + 1);
		String aId = Integer.toString(id);
		if (exists) {
			try (OutputStream os = new FileOutputStream(file, true)) {
				String line = aId + ":" + name + ":" + ss + ":" + userName + ":" + password;
				os.write(line.getBytes());
				String lineSeparator = System.getProperty("line.separator");
				os.write(lineSeparator.getBytes());
				os.flush();
				os.close();
				this.makeAccount(aId);
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			try (OutputStream os = new FileOutputStream(file)) {
				String line = aId + ":" + name + ":" + ss + ":" + userName + ":" + password;
				os.write(line.getBytes());
				String lineSeparator = System.getProperty("line.separator");
				os.write(lineSeparator.getBytes());
				os.flush();
				os.close();
				this.makeAccount(aId);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	private void makeAccount(String id) {
		
		String fileName = "accounts.txt";
		File file = new File(fileName);
		boolean exists = file.exists();
		if (exists) {
			try (OutputStream os = new FileOutputStream(file, true)) {
				String line = id + ":" + "0";
				os.write(line.getBytes());
				String lineSeparator = System.getProperty("line.separator");
				os.write(lineSeparator.getBytes());
				os.flush();
				os.close();
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}

		}else {
			try (OutputStream os = new FileOutputStream(file)) {
				String line = id + ":" + "0";
				os.write(line.getBytes());
				String lineSeparator = System.getProperty("line.separator");
				os.write(lineSeparator.getBytes());
				os.flush();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
//under construction
//	public void getJointApplications() {
//		int response = 0;
//		ArrayList<Customer> temps = new ArrayList<Customer>();
//		Customer temp = null;
//		System.out.println("Here are the following applications");
//		try(FileInputStream file = new FileInputStream("jointApplications.txt")){
//			BufferedReader br = new BufferedReader(new InputStreamReader(file));
//			String line = "";
//			String name = "";
//			String userName = "";
//			String ss = "";
//			String password = "";
//			while((line = br.readLine()) != null) {
//				String[] data = line.split(":");
//				name = data[0];
//				ss = data[1];
//				userName = data[2];
//				password = data[3];
//				temp = new Customer(name, ss, userName, password);
//				temps.add(temp);
//				}
//			System.out.println("Application: " + name + " " + userName);
//			System.out.println("1.) Approve");
//			System.out.println("2.) Deny");
//			System.out.print("Enter response here: ");
//			response = Main.input.nextInt();
//			switch(response) {
//			case 1:
//				this.approveOrDenyApplication(name, ss, userName, password);
//				break;
//			case 2:
//				System.out.println("In case 2");
//				System.out.println("Application denied");
//				break;
//			}
//			br.close();
//			file.close();
//			File aFile = new File("jointApplications.txt");
//			if (aFile.exists()) {
//				aFile.delete();     
//			}
//		} catch (FileNotFoundException e) {
//			System.out.println("No new applications to be seen");
//			System.out.println("Going back to menu");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		
//	}
//	}
//	public void approveOrDenyJointApplications(String name, String ss, String userName, String password) {
//		String fileName = "customers.txt";
//		File file = new File(fileName);
//		boolean exists = file.exists();
//		int id = (int)(Math.random() * 50 + 1);
//		String aId = Integer.toString(id);
//		if (exists) {
//			try (OutputStream os = new FileOutputStream(file, true)) {
//				String line = aId + ":" + name + ":" + ss + ":" + userName + ":" + password;
//				os.write(line.getBytes());
//				String lineSeparator = System.getProperty("line.separator");
//				os.write(lineSeparator.getBytes());
//				os.flush();
//				os.close();
//				this.makeAccount(aId);
//			}catch (FileNotFoundException e) {
//				e.printStackTrace();
//			}catch (IOException e) {
//				e.printStackTrace();
//			}
//		}else {
//			try (OutputStream os = new FileOutputStream(file)) {
//				String line = aId + ":" + name + ":" + ss + ":" + userName + ":" + password;
//				os.write(line.getBytes());
//				String lineSeparator = System.getProperty("line.separator");
//				os.write(lineSeparator.getBytes());
//				os.flush();
//				os.close();
//				this.makeAccount(aId);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//
//	}

}
