package com.ternary.model;

import java.sql.Blob;
import java.sql.Date;
import java.util.ArrayList;

public class Request {

	public Request() {
	}

	public Request(int requestId, int employeeId, Date requestCompleted, String status, boolean moreInfo,
			String justification, int directMgrApproval, int deptHeadApproval, int bencoApproval, boolean denied,
			String deniedReason, int preApproved, Blob approvalAttachment, double projectedReimbursement,
			boolean awardChanged, boolean exceedAvailable, String passingGrade, String finalGrade, boolean presentation,
			Blob presentationAttachment, String eventDescription, double eventCost, Date eventStart, Date eventEnd,
			String eventType, int reimbCoverage, String streetAddress, String city, String state, String country,
			String zipCode, String gradeType) {
		super();
		this.requestId = requestId;
		this.employeeId = employeeId;
		this.requestCompleted = requestCompleted;
		this.status = status;
		this.moreInfo = moreInfo;
		this.justification = justification;
		this.directMgrApprovalId = directMgrApproval;
		this.deptHeadApprovalId = deptHeadApproval;
		this.bencoApproval = bencoApproval;
		this.denied = denied;
		this.deniedReason = deniedReason;
		this.preApprovedSupervisorId = preApproved;
		this.approvalAttachment = approvalAttachment;
		this.projectedReimbursement = projectedReimbursement;
		this.awardChanged = awardChanged;
		this.exceedAvailable = exceedAvailable;
		this.passingGrade = passingGrade;
		this.finalGrade = finalGrade;
		this.uploadedPresentation = presentation;
		this.presentationAttachment = presentationAttachment;
		this.eventDescription = eventDescription;
		this.eventCost = eventCost;
		this.eventStart = eventStart;
		this.eventEnd = eventEnd;
		this.eventType = eventType;
		this.reimbCoverage = reimbCoverage;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipCode = zipCode;
		this.gradeType = gradeType;
	}

	private int requestId;
	private int employeeId;
	private Date requestCompleted;
	private String status;
	private boolean moreInfo;
	private String justification;
	private int directMgrApprovalId;
	private int deptHeadApprovalId;
	private int bencoApproval;
	private boolean denied;
	private String deniedReason;
	private int preApprovedSupervisorId;
	private Blob approvalAttachment;
	private double projectedReimbursement;
	private boolean awardChanged;
	private boolean exceedAvailable;
	private String passingGrade;
	private String finalGrade;
	private boolean uploadedPresentation;
	private Blob presentationAttachment;
	private String eventDescription;
	private double eventCost;
	private Date eventStart;
	private Date eventEnd;
	private String eventType;
	private int reimbCoverage;
	private String streetAddress;
	private String city;
	private String state;
	private String country;
	private String zipCode;
	private String gradeType;
	// private ArrayList<MoreInfo> moreInfos;
	// private ArrayList<Blob> attachments;

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public Date getRequestCompleted() {
		return requestCompleted;
	}

	public void setRequestCompleted(Date requestCompleted) {
		this.requestCompleted = requestCompleted;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isMoreInfo() {
		return moreInfo;
	}

	public void setMoreInfo(boolean moreInfo) {
		this.moreInfo = moreInfo;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public int getDirectMgrApprovalId() {
		return directMgrApprovalId;
	}

	public void setDirectMgrApprovalId(int directMgrApprovalId) {
		this.directMgrApprovalId = directMgrApprovalId;
	}

	public int getDeptHeadApprovalId() {
		return deptHeadApprovalId;
	}

	public void setDeptHeadApprovalId(int deptHeadApprovalId) {
		this.deptHeadApprovalId = deptHeadApprovalId;
	}

	public int getBencoApproval() {
		return bencoApproval;
	}

	public void setBencoApproval(int bencoApproval) {
		this.bencoApproval = bencoApproval;
	}

	public boolean isDenied() {
		return denied;
	}

	public void setDenied(boolean denied) {
		this.denied = denied;
	}

	public String getDeniedReason() {
		return deniedReason;
	}

	public void setDeniedReason(String deniedReason) {
		this.deniedReason = deniedReason;
	}

	public int getPreApprovedSupervisorId() {
		return preApprovedSupervisorId;
	}

	public void setPreApprovedSupervisorId(int preApprovedSupervisorId) {
		this.preApprovedSupervisorId = preApprovedSupervisorId;
	}

	public Blob getApprovalAttachment() {
		return approvalAttachment;
	}

	public void setApprovalAttachment(Blob approvalAttachment) {
		this.approvalAttachment = approvalAttachment;
	}

	public double getProjectedReimbursement() {
		return projectedReimbursement;
	}

	public void setProjectedReimbursement(double projectedReimbursement) {
		this.projectedReimbursement = projectedReimbursement;
	}

	public boolean isAwardChanged() {
		return awardChanged;
	}

	public void setAwardChanged(boolean awardChanged) {
		this.awardChanged = awardChanged;
	}

	public boolean isExceedAvailable() {
		return exceedAvailable;
	}

	public void setExceedAvailable(boolean exceedAvailable) {
		this.exceedAvailable = exceedAvailable;
	}

	public String getPassingGrade() {
		return passingGrade;
	}

	public void setPassingGrade(String passingGrade) {
		this.passingGrade = passingGrade;
	}

	public String getFinalGrade() {
		return finalGrade;
	}

	public void setFinalGrade(String finalGrade) {
		this.finalGrade = finalGrade;
	}

	public boolean isUploadedPresentation() {
		return uploadedPresentation;
	}

	public void setUploadedPresentation(boolean uploadedPresentation) {
		this.uploadedPresentation = uploadedPresentation;
	}

	public Blob getPresentationAttachment() {
		return presentationAttachment;
	}

	public void setPresentationAttachment(Blob presentationAttachment) {
		this.presentationAttachment = presentationAttachment;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public double getEventCost() {
		return eventCost;
	}

	public void setEventCost(double eventCost) {
		this.eventCost = eventCost;
	}

	public Date getEventStart() {
		return eventStart;
	}

	public void setEventStart(Date eventStart) {
		this.eventStart = eventStart;
	}

	public Date getEventEnd() {
		return eventEnd;
	}

	public void setEventEnd(Date eventEnd) {
		this.eventEnd = eventEnd;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public int getReimbCoverage() {
		return reimbCoverage;
	}

	public void setReimbCoverage(int reimbCoverage) {
		this.reimbCoverage = reimbCoverage;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getGradeType() {
		return gradeType;
	}

	public void setGradeType(String gradeType) {
		this.gradeType = gradeType;
	}

	@Override
	public String toString() {
		return "Request [requestId=" + requestId + ", employeeId=" + employeeId + ", requestCompleted="
				+ requestCompleted + ", status=" + status + ", moreInfo=" + moreInfo + ", justification="
				+ justification + ", directMgrApprovalId=" + directMgrApprovalId + ", deptHeadApprovalId="
				+ deptHeadApprovalId + ", bencoApproval=" + bencoApproval + ", denied=" + denied + ", deniedReason="
				+ deniedReason + ", preApprovedSupervisorId=" + preApprovedSupervisorId + ", approvalAttachment="
				+ approvalAttachment + ", projectedReimbursement=" + projectedReimbursement + ", awardChanged="
				+ awardChanged + ", exceedAvailable=" + exceedAvailable + ", passingGrade=" + passingGrade
				+ ", finalGrade=" + finalGrade + ", uploadedPresentation=" + uploadedPresentation
				+ ", presentationAttachment=" + presentationAttachment + ", eventDescription=" + eventDescription
				+ ", eventCost=" + eventCost + ", eventStart=" + eventStart + ", eventEnd=" + eventEnd + ", eventType="
				+ eventType + ", reimbCoverage=" + reimbCoverage + ", streetAddress=" + streetAddress + ", city=" + city
				+ ", state=" + state + ", country=" + country + ", zipCode=" + zipCode + ", gradeType=" + gradeType
				+ "]";
	}

}