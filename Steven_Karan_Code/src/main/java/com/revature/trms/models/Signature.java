package com.revature.trms.models;

import java.time.LocalDate;

public class Signature {
	private int id;
	private int reimb_id;
	private int sign_by_id;
	private LocalDate sign_date;
	private boolean approved_by_email;
	public Signature() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Signature(int id, int reimb_id, int sign_by_id, LocalDate sign_date, boolean approved_by_email) {
		super();
		this.id = id;
		this.reimb_id = reimb_id;
		this.sign_by_id = sign_by_id;
		this.sign_date = sign_date;
		this.approved_by_email = approved_by_email;
	}
	@Override
	public String toString() {
		return "Signature [id=" + id + ", reimb_id=" + reimb_id + ", sign_by_id=" + sign_by_id + ", sign_date="
				+ sign_date + ", approved_by_email=" + approved_by_email + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (approved_by_email ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + reimb_id;
		result = prime * result + sign_by_id;
		result = prime * result + ((sign_date == null) ? 0 : sign_date.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Signature other = (Signature) obj;
		if (approved_by_email != other.approved_by_email)
			return false;
		if (id != other.id)
			return false;
		if (reimb_id != other.reimb_id)
			return false;
		if (sign_by_id != other.sign_by_id)
			return false;
		if (sign_date == null) {
			if (other.sign_date != null)
				return false;
		} else if (!sign_date.equals(other.sign_date))
			return false;
		return true;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getReimb_id() {
		return reimb_id;
	}
	public void setReimb_id(int reimb_id) {
		this.reimb_id = reimb_id;
	}
	public int getSign_by_id() {
		return sign_by_id;
	}
	public void setSign_by_id(int sign_by_id) {
		this.sign_by_id = sign_by_id;
	}
	public LocalDate getSign_date() {
		return sign_date;
	}
	public void setSign_date(LocalDate sign_date) {
		this.sign_date = sign_date;
	}
	public boolean isApproved_by_email() {
		return approved_by_email;
	}
	public void setApproved_by_email(boolean approved_by_email) {
		this.approved_by_email = approved_by_email;
	}
	
	
}
