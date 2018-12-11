package project0.bank;

import java.util.Scanner;

public class Applications {

	private Users applicant;
	
	
	Applications(Users Applicant){
		applicant=Applicant;
	}

	public Applications(Customer c) {
		// TODO Auto-generated constructor stub
	}

	public boolean approveAccount() {
		Scanner keyboard = new Scanner(System.in);
		int input;
		boolean accountApproved = false;
		do {
			System.out.println("if you would like to approve the Application, type 1");
			System.out.println("if you would like to deny the Application, type 2");
			input = keyboard.nextInt();
			if (input == 1) {
				accountApproved = true;
				applicant.approved=true;
				applicant.myAccount=new Accounts(25, applicant.Username, applicant);
				
			} else if (input == 2) {
				accountApproved = false;
			} else {
				System.out.println("invalid input. Try again");
			}
		} while (input != 1 && input != 2);
		return accountApproved;
	}

	public Users getApplicant() {
		return applicant;
	}

	public void setApplicant(Users applicant) {
		this.applicant = applicant;
	}

}
