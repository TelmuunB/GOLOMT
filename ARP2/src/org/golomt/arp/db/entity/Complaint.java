package org.golomt.arp.db.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the COMPLAINT database table.
 * 
 */
@Entity
@NamedQuery(name="Complaint.findAll", query="SELECT c FROM Complaint c")
public class Complaint implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COMPLAINT_LIST_ID")
	private long complaintListId;

	@Column(name="AGENT_ID")
	private BigDecimal agentId;

	@Column(name="CALLER_EMAIL")
	private String callerEmail;

	@Column(name="CALLER_NAME")
	private String callerName;

	@Column(name="COMPLAINT_COMMENT")
	private String complaintComment;

	private Timestamp deadline;

	@Column(name="DELETE_DATE")
	private Timestamp deleteDate;

	@Column(name="DELETE_USER")
	private BigDecimal deleteUser;

	@Column(name="DETAILED_SERVICE_ID")
	private BigDecimal detailedServiceId;

	private BigDecimal expense;

	@Column(name="INSERT_DATE")
	private Timestamp insertDate;

	@Column(name="INSERT_USER")
	private BigDecimal insertUser;

	@Column(name="PHONE_NUMBER_ONE")
	private BigDecimal phoneNumberOne;

	@Column(name="SERVICE_ID")
	private BigDecimal serviceId;

	@Column(name="SOLVED_COMMENT")
	private String solvedComment;

	@Column(name="SOLVED_DATE")
	private Timestamp solvedDate;

	@Column(name="SOLVED_STATUS")
	private BigDecimal solvedStatus;

	@Column(name="SOURCE_ID")
	private BigDecimal sourceId;

	@Column(name="UNIT_ID")
	private BigDecimal unitId;

	@Column(name="UPDATE_DATE")
	private Timestamp updateDate;

	@Column(name="UPDATE_USER")
	private BigDecimal updateUser;

	//bi-directional many-to-one association to CallHistory
	@ManyToOne
	@JoinColumn(name="CALL_HISTORY_ID")
	private CallHistory callHistory;

	//bi-directional many-to-one association to MstCallRate
	@ManyToOne
	@JoinColumn(name="CALL_RATE_ID")
	private MstCallRate mstCallRate;

	//bi-directional many-to-one association to MstCallResult
	@ManyToOne
	@JoinColumn(name="CALL_RESULT_ID")
	private MstCallResult mstCallResult;

	//bi-directional many-to-one association to MstCallSort
	@ManyToOne
	@JoinColumn(name="CALL_SORT_ID")
	private MstCallSort mstCallSort;

	public Complaint() {
	}

	public long getComplaintListId() {
		return this.complaintListId;
	}

	public void setComplaintListId(long complaintListId) {
		this.complaintListId = complaintListId;
	}

	public BigDecimal getAgentId() {
		return this.agentId;
	}

	public void setAgentId(BigDecimal agentId) {
		this.agentId = agentId;
	}

	public String getCallerEmail() {
		return this.callerEmail;
	}

	public void setCallerEmail(String callerEmail) {
		this.callerEmail = callerEmail;
	}

	public String getCallerName() {
		return this.callerName;
	}

	public void setCallerName(String callerName) {
		this.callerName = callerName;
	}

	public String getComplaintComment() {
		return this.complaintComment;
	}

	public void setComplaintComment(String complaintComment) {
		this.complaintComment = complaintComment;
	}

	public Timestamp getDeadline() {
		return this.deadline;
	}

	public void setDeadline(Timestamp deadline) {
		this.deadline = deadline;
	}

	public Timestamp getDeleteDate() {
		return this.deleteDate;
	}

	public void setDeleteDate(Timestamp deleteDate) {
		this.deleteDate = deleteDate;
	}

	public BigDecimal getDeleteUser() {
		return this.deleteUser;
	}

	public void setDeleteUser(BigDecimal deleteUser) {
		this.deleteUser = deleteUser;
	}

	public BigDecimal getDetailedServiceId() {
		return this.detailedServiceId;
	}

	public void setDetailedServiceId(BigDecimal detailedServiceId) {
		this.detailedServiceId = detailedServiceId;
	}

	public BigDecimal getExpense() {
		return this.expense;
	}

	public void setExpense(BigDecimal expense) {
		this.expense = expense;
	}

	public Timestamp getInsertDate() {
		return this.insertDate;
	}

	public void setInsertDate(Timestamp insertDate) {
		this.insertDate = insertDate;
	}

	public BigDecimal getInsertUser() {
		return this.insertUser;
	}

	public void setInsertUser(BigDecimal insertUser) {
		this.insertUser = insertUser;
	}

	public BigDecimal getPhoneNumberOne() {
		return this.phoneNumberOne;
	}

	public void setPhoneNumberOne(BigDecimal phoneNumberOne) {
		this.phoneNumberOne = phoneNumberOne;
	}

	public BigDecimal getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(BigDecimal serviceId) {
		this.serviceId = serviceId;
	}

	public String getSolvedComment() {
		return this.solvedComment;
	}

	public void setSolvedComment(String solvedComment) {
		this.solvedComment = solvedComment;
	}

	public Timestamp getSolvedDate() {
		return this.solvedDate;
	}

	public void setSolvedDate(Timestamp solvedDate) {
		this.solvedDate = solvedDate;
	}

	public BigDecimal getSolvedStatus() {
		return this.solvedStatus;
	}

	public void setSolvedStatus(BigDecimal solvedStatus) {
		this.solvedStatus = solvedStatus;
	}

	public BigDecimal getSourceId() {
		return this.sourceId;
	}

	public void setSourceId(BigDecimal sourceId) {
		this.sourceId = sourceId;
	}

	public BigDecimal getUnitId() {
		return this.unitId;
	}

	public void setUnitId(BigDecimal unitId) {
		this.unitId = unitId;
	}

	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public BigDecimal getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(BigDecimal updateUser) {
		this.updateUser = updateUser;
	}

	public CallHistory getCallHistory() {
		return this.callHistory;
	}

	public void setCallHistory(CallHistory callHistory) {
		this.callHistory = callHistory;
	}

	public MstCallRate getMstCallRate() {
		return this.mstCallRate;
	}

	public void setMstCallRate(MstCallRate mstCallRate) {
		this.mstCallRate = mstCallRate;
	}

	public MstCallResult getMstCallResult() {
		return this.mstCallResult;
	}

	public void setMstCallResult(MstCallResult mstCallResult) {
		this.mstCallResult = mstCallResult;
	}

	public MstCallSort getMstCallSort() {
		return this.mstCallSort;
	}

	public void setMstCallSort(MstCallSort mstCallSort) {
		this.mstCallSort = mstCallSort;
	}

}