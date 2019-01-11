package com.revature.trms.models;

import java.time.LocalDate;

public class Note {

	private int note_id;
	private int reimb_id;
	private int commentor_id;
	private String text;
	private LocalDate note_date;
	public Note() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Note(int note_id, int reimb_id, int commentor_id, String text, LocalDate note_date) {
		super();
		this.note_id = note_id;
		this.reimb_id = reimb_id;
		this.commentor_id = commentor_id;
		this.text = text;
		this.note_date = note_date;
	}
	public int getNote_id() {
		return note_id;
	}
	public void setNote_id(int note_id) {
		this.note_id = note_id;
	}
	public int getReimb_id() {
		return reimb_id;
	}
	public void setReimb_id(int reimb_id) {
		this.reimb_id = reimb_id;
	}
	public int getCommentor_id() {
		return commentor_id;
	}
	public void setCommentor_id(int commentor_id) {
		this.commentor_id = commentor_id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public LocalDate getNote_date() {
		return note_date;
	}
	public void setNote_date(LocalDate note_date) {
		this.note_date = note_date;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + commentor_id;
		result = prime * result + ((note_date == null) ? 0 : note_date.hashCode());
		result = prime * result + note_id;
		result = prime * result + reimb_id;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
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
		Note other = (Note) obj;
		if (commentor_id != other.commentor_id)
			return false;
		if (note_date == null) {
			if (other.note_date != null)
				return false;
		} else if (!note_date.equals(other.note_date))
			return false;
		if (note_id != other.note_id)
			return false;
		if (reimb_id != other.reimb_id)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Note [note_id=" + note_id + ", reimb_id=" + reimb_id + ", commentor_id=" + commentor_id + ", text="
				+ text + ", note_date=" + note_date + "]";
	}
	
}
