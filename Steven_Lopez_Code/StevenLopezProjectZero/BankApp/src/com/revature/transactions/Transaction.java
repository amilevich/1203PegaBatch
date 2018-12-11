package com.revature.transactions;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import com.revature.account.Account;
//Class that handles deposits, withdrawals  and transfers
public class Transaction implements Serializable {

	/**
	 * 
	 */
	protected static final long serialVersionUID = -7766208872390328606L;
	public static final String FILENAME = "./transactions.txt";
	static AtomicInteger nextId = new AtomicInteger();
	private int id;
	private double amount;
	private LocalDate date;
	private TransactionType type;
	private Account account;

	//Constructor used for Transfer transaction has an Account object "sender" and another the "receiver".
	public Transaction(TransactionType type, double amount, Account sender, Account receiver) {
		this.id = nextId.getAndIncrement();
		if (type == TransactionType.TRANSFER) {
			this.setType(type);
			this.setDate(LocalDate.now());
			this.setAccount(sender);
			if (this.transferSend(amount, receiver) == 0.00) {
				return;
			}
		}
	}
	//Constructor that handles the Deposit and WITHDRAWAL transaction
	public Transaction(TransactionType type, double amount, Account account) {
		this.id = nextId.getAndIncrement();

		if (type == TransactionType.DEPOSIT) {
			this.setType(type);
			this.setDate(LocalDate.now());
			this.setAccount(account);
			if (this.deposit(amount) == 0.00) {
				return;
			}
		} else if (type == TransactionType.WITHDRAWL) {
			this.setType(type);
			this.setDate(LocalDate.now());
			this.setAccount(account);
			if (this.withdrawl(amount) == 0.00) {
				return;
			}
		}
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", amount=" + amount + ", date=" + date + ", type=" + type +"]";
	}
	//WHere the deposit takes place
	public double deposit(Double amount) {
		if (amount > 0.00) {
			this.account.setCurrentBalance(this.getAccount().getCurrentBalance() + amount);
			System.out.println("Deposit done succesfully!");
			this.getAccount().getTransactions().add(this);
			this.setAmount(amount);
			return amount;
		} else {
			System.out.println("Please input an amount greater than 0.00!");
			return 0.00;
		}
	}
	//Where the withdrawal takes place
	public double withdrawl(Double amount) {
		if (amount > 0.00)
			if (this.getAccount().getCurrentBalance() >= amount) {
				this.getAccount().setCurrentBalance(this.getAccount().getCurrentBalance() - amount);
				System.out.println("Withdrawl done succesfully!");
				this.getAccount().getTransactions().add(this);
				this.setAmount(amount);
				return amount;
			} else {
				System.out.println("Insuficient Funds!");
				return 0.0;
			}
		else {
			System.out.println("Please input an amount greater than 0.00!");
			return 0.00;
		}

	}
	//Where the transfers takes place
	public double transferSend(Double amount, Account account) {

		if (amount > 0) {
			if (this.account.getCurrentBalance() > amount) {
				this.account.setCurrentBalance(this.account.getCurrentBalance() - amount);
				account.setCurrentBalance(account.getCurrentBalance() + amount);
				System.out.println("Tranfer to " + account.getAccountNumber() + " done successfully");
				this.getAccount().getTransactions().add(this);
				account.getTransactions().add(this);
				this.setAmount(amount);
				return amount;
			} else {
				return 0.00;
			}
		} else {
			System.out.println("Please input an amount greater than 0.00!");
			return 0.00;
		}
	}
//	public boolean createTransaction() {
//		ArrayList<Transaction> trans = getTransactions();
//		Transaction transaction2 = this;
//		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
//
//			if (trans != null)
//				for (Transaction c : trans) {
//					oos.writeObject(c);
//				}
//			oos.writeObject(transaction2);
//			oos.writeObject(null);
//			oos.flush();
//			oos.close();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//	}
//
//	private ArrayList<Transaction> getTransactions() {
//		Object obj;
//		ArrayList<Transaction> trans = new ArrayList<>();
//		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
//			while ((obj = (Transaction) ois.readObject()) != null) {
//				trans.add((Transaction) obj);
//			}
//			return trans;
//
//		} catch (EOFException e) {
//			return null;
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//			return null;
//		} catch (IOException e) {
//			e.printStackTrace();
//			return null;
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//
//	public static void saveTransaction(ArrayList<Transaction> trans) {
//
//		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
//
//			if (trans != null)
//				for (Transaction t : trans) {
//					oos.writeObject(t);
//				}
//			oos.writeObject(null);
//			oos.flush();
//			oos.close();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
