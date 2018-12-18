package com.revature.beans;

public class FlashCard {
	private int fcId;
	private String fcQuestion;
	private String fcAnswer;
	
	public FlashCard() {
		
	}
	

	public FlashCard(int fcId, String fcQuestion, String fcAnswer) {
		super();
		this.fcId = fcId;
		this.fcQuestion = fcQuestion;
		this.fcAnswer = fcAnswer;
	}


	public int getFcId() {
		return fcId;
	}

	public void setFcId(int fcId) {
		this.fcId = fcId;
	}

	public String getFcQuestion() {
		return fcQuestion;
	}

	public void setFcQuestion(String fcQuestion) {
		this.fcQuestion = fcQuestion;
	}

	public String getFcAnswer() {
		return fcAnswer;
	}

	public void setFcAnswer(String fcAnswer) {
		this.fcAnswer = fcAnswer;
	}

	@Override
	public String toString() {
		return "FlashCard [fcId=" + fcId + ", fcQuestion=" + fcQuestion + ", fcAnswer=" + fcAnswer + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fcAnswer == null) ? 0 : fcAnswer.hashCode());
		result = prime * result + fcId;
		result = prime * result + ((fcQuestion == null) ? 0 : fcQuestion.hashCode());
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
		FlashCard other = (FlashCard) obj;
		if (fcAnswer == null) {
			if (other.fcAnswer != null)
				return false;
		} else if (!fcAnswer.equals(other.fcAnswer))
			return false;
		if (fcId != other.fcId)
			return false;
		if (fcQuestion == null) {
			if (other.fcQuestion != null)
				return false;
		} else if (!fcQuestion.equals(other.fcQuestion))
			return false;
		return true;
	}

}
