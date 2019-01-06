package com.revature.trms.models;

import java.sql.Blob;

public class Attachment {

	private int attach_id;
	private int reimb_id;
	private String attach_name;
	private Blob attachment;
	
	public Attachment() {
		super();
	}

	public Attachment(int attach_id, int reimb_id, String attach_name, Blob attachment) {
		super();
		this.attach_id = attach_id;
		this.reimb_id = reimb_id;
		this.attach_name = attach_name;
		this.attachment = attachment;
	}

	public int getAttach_id() {
		return attach_id;
	}

	public void setAttach_id(int attach_id) {
		this.attach_id = attach_id;
	}

	public int getReimb_id() {
		return reimb_id;
	}

	public void setReimb_id(int reimb_id) {
		this.reimb_id = reimb_id;
	}

	public String getAttach_name() {
		return attach_name;
	}

	public void setAttach_name(String attach_name) {
		this.attach_name = attach_name;
	}

	public Blob getAttachment() {
		return attachment;
	}

	public void setAttachment(Blob attachment) {
		this.attachment = attachment;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + attach_id;
		result = prime * result + ((attach_name == null) ? 0 : attach_name.hashCode());
		result = prime * result + ((attachment == null) ? 0 : attachment.hashCode());
		result = prime * result + reimb_id;
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
		Attachment other = (Attachment) obj;
		if (attach_id != other.attach_id)
			return false;
		if (attach_name == null) {
			if (other.attach_name != null)
				return false;
		} else if (!attach_name.equals(other.attach_name))
			return false;
		if (attachment == null) {
			if (other.attachment != null)
				return false;
		} else if (!attachment.equals(other.attachment))
			return false;
		if (reimb_id != other.reimb_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Attachment [attach_id=" + attach_id + ", reimb_id=" + reimb_id + ", attach_name=" + attach_name
				+ ", attachment=" + attachment + "]";
	}
	
}
