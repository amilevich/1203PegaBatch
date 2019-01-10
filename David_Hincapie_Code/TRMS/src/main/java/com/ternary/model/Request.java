package com.ternary.model;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.time.LocalDate;

public class Request {

	public Request() {
	}

	public Request(int requestId, int employeeId, LocalDate requestCompleted, String status, boolean moreInfo,
			String justification, int directMgrApproval, int deptHeadApproval, int bencoApproval, boolean denied,
			String deniedReason, int preApproved, InputStream approvalAttachment, double projectedReimbursement,
			boolean awardChanged, boolean exceedAvailable, String passingGrade, String finalGrade, boolean presentation,
			InputStream presentationAttachment, String eventDescription, double eventCost, LocalDate eventStart,
			LocalDate eventEnd, String eventType, int reimbCoverage, String streetAddress, String city, String state,
			String country, String zipCode, String gradeType, InputStream eventFile) {
		super();
		this.requestId = requestId;
		this.employeeId = employeeId;
		this.reimbursementDate = requestCompleted;
		this.status = status;
		this.moreInfo = moreInfo;
		this.justification = justification;
		this.directMgrApprovalId = directMgrApproval;
		this.deptHeadApprovalId = deptHeadApproval;
		this.bencoApprovalId = bencoApproval;
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
		this.eventFile = eventFile;
	}

	public Request(int requestId, int employeeId, LocalDate requestCompleted, String status, boolean moreInfo,
			String justification, int directMgrApproval, int deptHeadApproval, int bencoApproval, boolean denied,
			String deniedReason, int preApproved, InputStream approvalAttachment, double projectedReimbursement,
			boolean awardChanged, boolean exceedAvailable, String passingGrade, String finalGrade, boolean presentation,
			InputStream presentationAttachment, String eventDescription, double eventCost, LocalDate eventStart,
			LocalDate eventEnd, String eventType, int reimbCoverage, String streetAddress, String city, String state,
			String country, String zipCode, String gradeType, InputStream eventFile, String presentationAttachmentName,
			String approvalAttachmentname, String eventFilename) {
		super();
		this.requestId = requestId;
		this.employeeId = employeeId;
		this.reimbursementDate = requestCompleted;
		this.status = status;
		this.moreInfo = moreInfo;
		this.justification = justification;
		this.directMgrApprovalId = directMgrApproval;
		this.deptHeadApprovalId = deptHeadApproval;
		this.bencoApprovalId = bencoApproval;
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
		this.eventFile = eventFile;
		this.presentationAttachmentName = presentationAttachmentName;
		this.approvalAttachmentname = approvalAttachmentname;
		this.eventFilename = eventFilename;
	}

	private int requestId;
	private int employeeId;

	private String status;
	private boolean moreInfo;
	private String justification;

	private int directMgrApprovalId = 0;
	private boolean directMgrApproval = false;
	private int deptHeadApprovalId = 0;
	private boolean deptHeadApproval = false;
	private int bencoApprovalId = 0;
	private boolean bencoApproval = false;
	private boolean denied;

	private String deniedReason;
	private int preApprovedSupervisorId;
	private double projectedReimbursement;
	private boolean awardChanged;
	private boolean exceedAvailable;

	private boolean uploadedPresentation;
	private String eventDescription;

	private FileOutputStream approvalAttachmentOut;

	private InputStream approvalAttachment;
	private Blob approvalAttachmentBlob;
	private String approvalAttachmentname;
	private InputStream eventFile;
	private Blob eventFileBlob;
	private String eventFilename;
	private InputStream presentationAttachment;
	private Blob presentationAttachmentBlob;
	private String presentationAttachmentName;

	private String eventTime;
	private LocalDate reimbursementDate;
	private LocalDate dateCompleted;
	private LocalDate eventStart;
	private LocalDate eventEnd;
	private String eventType;

	private int gradeTypeId;
	private String gradeType;
	private int gradeId;
	
	private String passingGrade;
	private String finalGrade = "n/a";
	private String exceedAvailibleComment;

	private double eventCost;
	private int reimbCoverage;

	private String streetAddress;
	private String city;
	private String state;
	private String country;
	private String zipCode;

	private String employeeFirstName;
	private String employeeLastName;

	// private ArrayList<MoreInfo> moreInfos;
	// private ArrayList<Blob> attachments;

	public int getRequestId() {
		return requestId;
	}

	public int getGradeTypeId() {
		return gradeTypeId;
	}

	public void setGradeTypeId(int gradeTypeId) {
		this.gradeTypeId = gradeTypeId;
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

	public int getBencoApprovalId() {
		return bencoApprovalId;
	}

	public void setBencoApprovalId(int bencoApproval) {
		this.bencoApprovalId = bencoApproval;
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

	public InputStream getApprovalAttachment() {
		return approvalAttachment;
	}

	public void setApprovalAttachment(InputStream approvalAttachment) {
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

	public InputStream getPresentationAttachment() {
		return presentationAttachment;
	}

	public void setPresentationAttachment(InputStream presentationAttachment) {
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

	public LocalDate getEventStart() {
		return eventStart;
	}

	public void setEventStart(LocalDate eventStart) {
		this.eventStart = eventStart;
	}

	public LocalDate getEventEnd() {
		return eventEnd;
	}

	public void setEventEnd(LocalDate eventEnd) {
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

	public String getExceedAvailibleComment() {
		return exceedAvailibleComment;
	}

	public void setExceedAvailibleComment(String exceedAvailibleComment) {
		this.exceedAvailibleComment = exceedAvailibleComment;
	}

	public LocalDate getReimbursementDate() {
		return reimbursementDate;
	}

	public void setReimbursementDate(LocalDate reimbursementDate) {
		this.reimbursementDate = reimbursementDate;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public boolean isDirectMgrApproval() {
		return directMgrApproval;
	}

	public void setDirectMgrApproval(boolean directMgrApproval) {
		this.directMgrApproval = directMgrApproval;
	}

	public boolean isDeptHeadApproval() {
		return deptHeadApproval;
	}

	public void setDeptHeadApproval(boolean deptHeadApproval) {
		this.deptHeadApproval = deptHeadApproval;
	}

	public boolean isBencoApproval() {
		return bencoApproval;
	}

	public void setBencoApproval(boolean bencoApproval) {
		this.bencoApproval = bencoApproval;
	}

	public InputStream getEventFile() {
		return eventFile;
	}

	public void setEventFile(InputStream eventFile) {
		this.eventFile = eventFile;
	}

	public String getPresentationAttachmentName() {
		return presentationAttachmentName;
	}

	public void setPresentationAttachmentName(String presentationAttachmentName) {
		this.presentationAttachmentName = presentationAttachmentName;
	}

	public String getApprovalAttachmentname() {
		return approvalAttachmentname;
	}

	public void setApprovalAttachmentname(String approvalAttachmentname) {
		this.approvalAttachmentname = approvalAttachmentname;
	}

	public String getEventFilename() {
		return eventFilename;
	}

	public void setEventFilename(String eventFilename) {
		this.eventFilename = eventFilename;
	}

	public Blob getPresentationAttachmentBlob() {
		return presentationAttachmentBlob;
	}

	public void setPresentationAttachmentBlob(Blob presentationAttachmentBlob) {
		this.presentationAttachmentBlob = presentationAttachmentBlob;
	}

	public Blob getApprovalAttachmentBlob() {
		return approvalAttachmentBlob;
	}

	public void setApprovalAttachmentBlob(Blob approvalAttachmentBlob) {
		this.approvalAttachmentBlob = approvalAttachmentBlob;
	}

	public Blob getEventFileBlob() {
		return eventFileBlob;
	}

	public void setEventFileBlob(Blob eventFileBlob) {
		this.eventFileBlob = eventFileBlob;
	}

	public FileOutputStream getApprovalAttachmentOut() {
		return approvalAttachmentOut;
	}

	public void setApprovalAttachmentOut(FileOutputStream approvalAttachmentOut) {
		this.approvalAttachmentOut = approvalAttachmentOut;
	}

	public LocalDate getDateCompleted() {
		return dateCompleted;
	}

	public void setDateCompleted(LocalDate dateCompleted) {
		this.dateCompleted = dateCompleted;
	}

	public String getEmployeeFirstName() {
		return employeeFirstName;
	}

	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}

	public String getEmployeeLastName() {
		return employeeLastName;
	}

	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}
	

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	@Override
	public String toString() {
		return "Request [requestId=" + requestId + ", employeeId=" + employeeId + ", status=" + status + ", moreInfo="
				+ moreInfo + ", justification=" + justification + ", directMgrApprovalId=" + directMgrApprovalId
				+ ", directMgrApproval=" + directMgrApproval + ", deptHeadApprovalId=" + deptHeadApprovalId
				+ ", deptHeadApproval=" + deptHeadApproval + ", bencoApprovalId=" + bencoApprovalId + ", bencoApproval="
				+ bencoApproval + ", denied=" + denied + ", deniedReason=" + deniedReason + ", preApprovedSupervisorId="
				+ preApprovedSupervisorId + ", projectedReimbursement=" + projectedReimbursement + ", awardChanged="
				+ awardChanged + ", exceedAvailable=" + exceedAvailable + ", uploadedPresentation="
				+ uploadedPresentation + ", eventDescription=" + eventDescription + ", approvalAttachmentOut="
				+ approvalAttachmentOut + ", approvalAttachment=" + approvalAttachment + ", approvalAttachmentBlob="
				+ approvalAttachmentBlob + ", approvalAttachmentname=" + approvalAttachmentname + ", eventFile="
				+ eventFile + ", eventFileBlob=" + eventFileBlob + ", eventFilename=" + eventFilename
				+ ", presentationAttachment=" + presentationAttachment + ", presentationAttachmentBlob="
				+ presentationAttachmentBlob + ", presentationAttachmentName=" + presentationAttachmentName
				+ ", eventTime=" + eventTime + ", reimbursementDate=" + reimbursementDate + ", dateCompleted="
				+ dateCompleted + ", eventStart=" + eventStart + ", eventEnd=" + eventEnd + ", eventType=" + eventType
				+ ", gradeTypeId=" + gradeTypeId + ", gradeType=" + gradeType + ", gradeId=" + gradeId
				+ ", passingGrade=" + passingGrade + ", finalGrade=" + finalGrade + ", exceedAvailibleComment="
				+ exceedAvailibleComment + ", eventCost=" + eventCost + ", reimbCoverage=" + reimbCoverage
				+ ", streetAddress=" + streetAddress + ", city=" + city + ", state=" + state + ", country=" + country
				+ ", zipCode=" + zipCode + ", employeeFirstName=" + employeeFirstName + ", employeeLastName="
				+ employeeLastName + "]";
	}



}