package com.revature.driver;

import java.sql.SQLException;

import com.revature.beans.Accounts;
import com.revature.beans.Login;
import com.revature.daoimpl.AccountsDAOImpl;
import com.revature.daoimpl.LoginDAOImpl;
import com.revature.util.OnlyScan;

public abstract class Driver {

	public static void main(String[] args) {

		Login logIn = new Login();
		Accounts account = new Accounts();
		Accounts transferAccID = new Accounts();
		LoginDAOImpl lndi = new LoginDAOImpl();
		AccountsDAOImpl adi = new AccountsDAOImpl();
		int select = 0;
		double transferAmt = 0;

		do {
//			Starting Menu
			System.out.println(" ***********");
			System.out.println("**Ban(K)ing**");
			System.out.println(" ***********");
			System.out.println("1 - For Customer");
			System.out.println("2 - For Employee");
			System.out.println("3 - For Bank Administrator");
			System.out.println("0 - Exit");
			System.out.print("Select an option: ");
			select = OnlyScan.getScanner().nextInt();

			switch (select) {
			
		
//			Customer Options	 
			case 1:
				System.out.println("\n1 - Register to be an account user");
				System.out.println("2 - Login into account");
				System.out.print("Select an option: ");
				select = OnlyScan.getScanner().nextInt();

				switch (select) {
				
//				Account Registration
				case 1:
					OnlyScan.getScanner().nextLine();
					System.out.print("\nUsername must be at least 8 characters ");
					System.out.print("and must begin with a alphabet.");
					System.out.print("\nEnter username: ");
					logIn.setUserName(OnlyScan.getScanner().nextLine());

					while (logIn.getUserName().length() < 8 || !Character.isAlphabetic(logIn.getUserName().charAt(0))) {
						System.out.print("\nUsername must be at least 8 characters ");
						System.out.print("and must begin with a alphabet.");
						System.out.print("\nEnter username: ");
						logIn.setUserName(OnlyScan.getScanner().nextLine());
					}

					System.out.print("\nPassword must be at least 7 characters ");
					System.out.print("and it can't begin with a number.");
					System.out.print("\nEnter password: ");
					logIn.setPassWord(OnlyScan.getScanner().nextLine());

					while (logIn.getPassWord().length() < 7 || Character.isDigit(logIn.getPassWord().charAt(0))) {
						System.out.print("\nPassword must be at least 7 characters ");
						System.out.print("and it can't begin with a number.");
						System.out.print("\nEnter password: ");
						logIn.setPassWord(OnlyScan.getScanner().nextLine());
					}

					try {
						lndi.createLoginProc(logIn);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;

//				Registered Account  
				case 2:
					OnlyScan.getScanner().nextLine();
					System.out.print("\nEnter your username: ");
					logIn.setUserName(OnlyScan.getScanner().nextLine());

					System.out.print("Enter your password: ");
					logIn.setPassWord(OnlyScan.getScanner().nextLine());

					try {
						if (!lndi.isInLogin(logIn)) {
							System.out.println("\nThe username or password is incorrect or account doesn't exists");
							System.out.println("Returning to main menu.");
							System.out.println();
							break;

						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					System.out.println("Now logged-in");
					try {
						logIn.setUserID(lndi.getUserId(logIn));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
//					Menu for Logged-in User
					do {
						System.out.println("1 - View your account/s");
						System.out.println("2 - Create a new account");
						System.out.println("3 - Make a Deposit");
						System.out.println("4 - Make a Withdrawal");
						System.out.println("5 - Transfer Out");
						System.out.println("6 - Delete an account");
						System.out.println("7 - Logout");
						System.out.print("Enter: ");
						select = OnlyScan.getScanner().nextInt();

						switch (select) {
						
//						View your account
						case 1:
							try {
								adi.getSpecificAccount(logIn, account);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							break;

//						Create bank account type
						case 2:
							System.out.println("1 - Checking Acc");
							System.out.println("2 - Joint Acc");
							select = OnlyScan.getScanner().nextInt();

							switch (select) {
							
//							For Checking Account
							case 1:
								try {
									account.setUserID(lndi.getUserId(logIn));
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								account.setAccounType("Checking");
								account.setBalance(0.00);
								account.setStatus("Open");
								try {
									adi.createAccountProc(account);
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								break;
								
//							For Joint Account
							case 2:
								try {
									account.setUserID(lndi.getUserId(logIn));
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								account.setAccounType("Joint");
								account.setBalance(0.00);
								account.setStatus("Open");
								try {
									adi.createAccountProc(account);
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								break;

							default:
								break;
							}

							break;
							
//						For Deposit
						case 3:
							System.out.print("Enter your bank account ID: ");
							account.setAccountID(OnlyScan.getScanner().nextInt());

							try {
								adi.getAccountStatus(account, logIn.getUserID());
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							if (account.getStatus().equals("Open")) {
								System.out.println("Your account has not yet been approved.");
								System.out.println();
							} else if (account.getStatus().equals("Denied")) {
								System.out.println("Your account has been denied.");
								System.out.println();
							} else if (account.getStatus().equals("Approved")) {
								System.out.println("Current account balance is $" + account.getBalance());
								System.out.print("Enter deposit amount $");
								double deposit = OnlyScan.getScanner().nextDouble();

								if (deposit < 0) {
									System.out.println("Invalid amount entered: $" + deposit);
									System.out.println();
								}

								else {
									account.setBalance(account.getBalance() + deposit);
									System.out.println("New balance $" + account.getBalance());

									try {
										adi.updateAccount(account);
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
							break;
							
//						For Withdrawal
						case 4:
							System.out.print("Enter bank account ID: ");
							account.setAccountID(OnlyScan.getScanner().nextInt());

							try {
								adi.getAccountStatus(account, logIn.getUserID());
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							if (account.getStatus().equals("Open")) {
								System.out.println("Your account has not yet been approved.");
							} else if (account.getStatus().equals("Denied")) {
								System.out.println("Your account has been denied.");
							} else if (account.getStatus().equals("Approved")) {
								System.out.println("Current account balance is $" + account.getBalance());
								System.out.print("Enter withdrawal amount $");
								double withdrawal = OnlyScan.getScanner().nextDouble();

								if (withdrawal > account.getBalance()) {
									System.out.println("Overdrafting not supported.");
									System.out.println();
								}

								else {
									account.setBalance(account.getBalance() - withdrawal);
									System.out.println("New balance $" + account.getBalance());

									try {
										adi.updateAccount(account);
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
							break;
							
//						For Transferring
						case 5:
							System.out.print("Enter your bank account ID: ");
							account.setAccountID(OnlyScan.getScanner().nextInt());

							try {
								adi.getAccountStatus(account, logIn.getUserID());
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							if (account.getStatus().equals("Open")) {
								System.out.println("Your account has not yet been approved.");
							} else if (account.getStatus().equals("Denied")) {
								System.out.println("Your account has been denied.");
							} else if (account.getStatus().equals("Approved")) {
								System.out.println("Enter the receiving account Id: ");
								transferAccID.setAccountID(OnlyScan.getScanner().nextInt());

								try {
									adi.editAccount(transferAccID);
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

								if (transferAccID.getStatus().equals("Open")) {
									System.out.println("This account has not yet been approved.");
								} else if (transferAccID.getStatus().equals("Denied")) {
									System.out.println("This account has been denied.");
								} else if (transferAccID.getStatus().equals("Approved")) {
									System.out.print("Enter transfer amount $");
									transferAmt = OnlyScan.getScanner().nextInt();

									if (account.getBalance() < transferAmt) {
										System.out.println("Account Id: " + account.getAccountID() + " balance of $"
												+ account.getBalance() + " is less than transfer amount $"
												+ transferAmt);
										System.out.println("Transfer can't be done.");
									}
									account.setBalance(account.getBalance() - transferAmt);
									transferAccID.setBalance(transferAccID.getBalance() + transferAmt);

									try {
										adi.updateAccount(account);
										adi.updateAccount(transferAccID);
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

								}

								System.out.println("New balance $" + account.getBalance() + " for account Id: "
										+ account.getAccountID());
								System.out.println("New balance $" + transferAccID.getBalance()
										+ " for transfer account Id: " + transferAccID.getAccountID());
							}
							break;
							
//						For Deleting
						case 6:
							System.out.print("Enter bank account ID to be deleted: ");
							account.setAccountID(OnlyScan.getScanner().nextInt());

							try {
								adi.getAccountStatus(account, logIn.getUserID());
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							if (account.getBalance() == 0) {
								try {
									adi.deleteAccount(account.getAccountID(), logIn.getUserID());
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								System.out.println("Account ID " + account.getAccountID() + " has been deleted.");

							} else if (account.getBalance() > 0) {
								System.out.println("Account ID " + account.getAccountID() + " has a balance of $"
										+ account.getBalance());
								System.out.println("Account ID " + account.getAccountID() + " can not be deleted.");
							}
							break;

						default:
							System.out.println("Now being logged-out.");
							break;
						}

						// break;
					} while (select != 7);
				default:
					break;

				}

				break;

//			Employee Menu
			case 2:
				System.out.println("\nBank Employee Menu: ");
				System.out.println("1 - View all accounts");
				System.out.println("2 - Edit status of accounts");
				System.out.println("0 - Exit");
				System.out.print("Enter: ");
				select = OnlyScan.getScanner().nextInt();

				switch (select) {
				
//				View Accounts
				case 1:
					try {
						adi.viewAccounts(account, logIn);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
					
//				Changing Account Status
				case 2:
					System.out.print("Enter bank account ID to be edited: ");
					account.setAccountID(OnlyScan.getScanner().nextInt());

					try {
						adi.editAccount(account);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(account.toString());

					if (account.getStatus().equals("Open")) {
						System.out.println("\nThis account needs approving.");
						System.out.println("1 - To Approve");
						System.out.println("2 - To Deny");
						System.out.println("3 - Do nothing");
						System.out.print("Enter: ");
						select = OnlyScan.getScanner().nextInt();

						switch (select) {
						case 1:
							account.setStatus("Approved");
							try {
								adi.updateAccount(account);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break;
						case 2:
							account.setStatus("Denied");
							try {
								adi.updateAccount(account);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break;

						default:
							//System.out.println("Continuing on");
							break;
						}
					}

					break;

				default:
					break;
				}

				break;

//			Administration Menu
			case 3:
				System.out.println("Bank Admin Menu: ");
				System.out.println("1 - View all accounts");
				System.out.println("2 - Edit accounts");
				System.out.println("0 - Exit");
				select = OnlyScan.getScanner().nextInt();

				switch (select) {
				case 1:
					try {
						adi.viewAccounts(account, logIn);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}

				break; // Case 3
			}

		} while (select != 0);

		OnlyScan.getScanner().close();

	}

}
