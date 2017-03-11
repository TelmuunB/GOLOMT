package org.golomt.arp.db.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the TASK database table.
 * 
 */
@Entity
@NamedQuery(name="Task.findAll", query="SELECT t FROM Task t")
public class Task implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TASK_ID")
	private long taskId;

	@Column(name="ACTIVE_TASK")
	private BigDecimal activeTask;

	@Column(name="AGENT_ID")
	private BigDecimal agentId;

	@Column(name="CALLER_NAME")
	private String callerName;

	private String calltype;

	private Timestamp deadline;

	@Column(name="DELETE_DATE")
	private Timestamp deleteDate;

	@Column(name="DELETE_USER")
	private BigDecimal deleteUser;

	@Column(name="EXCEL_TASK")
	private BigDecimal excelTask;

	@Column(name="INSERT_AGENT")
	private BigDecimal insertAgent;

	@Column(name="INSERT_DATE")
	private Timestamp insertDate;

	@Column(name="INSERT_USER")
	private BigDecimal insertUser;

	@Column(name="PHONE_NUMBER_ONE")
	private BigDecimal phoneNumberOne;

	@Column(name="PHONE_NUMBER_TWO")
	private BigDecimal phoneNumberTwo;

	@Column(name="TASK_COMMENT")
	private String taskComment;

	@Column(name="TASK_NAME")
	private String taskName;

	@Column(name="UPDATE_DATE")
	private Timestamp updateDate;

	@Column(name="UPDATE_USER")
	private BigDecimal updateUser;

	//bi-directional many-to-one association to CallHistory
	@ManyToOne
	@JoinColumn(name="CALL_HISTORY_ID")
	private CallHistory callHistory;

	//bi-directional many-to-one association to CallHistoryService
	@ManyToOne
	@JoinColumn(name="CALL_HISTORY_SERVICE_ID")
	private CallHistoryService callHistoryService;

	//bi-directional many-to-one association to MstCallReqType
	@ManyToOne
	@JoinColumn(name="CALL_REQ_TYPE_ID")
	private MstCallReqType mstCallReqType;

	public Task() {
	}

	public long getTaskId() {
		return this.taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public BigDecimal getActiveTask() {
		return this.activeTask;
	}

	public void setActiveTask(BigDecimal activeTask) {
		this.activeTask = activeTask;
	}

	public BigDecimal getAgentId() {
		return this.agentId;
	}

	public void setAgentId(BigDecimal agentId) {
		this.agentId = agentId;
	}

	public String getCallerName() {
		return this.callerName;
	}

	public void setCallerName(String callerName) {
		this.callerName = callerName;
	}

	public String getCalltype() {
		return this.calltype;
	}

	public void setCalltype(String calltype) {
		this.calltype = calltype;
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

	public BigDecimal getExcelTask() {
		return this.excelTask;
	}

	public void setExcelTask(BigDecimal excelTask) {
		this.excelTask = excelTask;
	}

	public BigDecimal getInsertAgent() {
		return this.insertAgent;
	}

	public void setInsertAgent(BigDecimal insertAgent) {
		this.insertAgent = insertAgent;
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

	public BigDecimal getPhoneNumberTwo() {
		return this.phoneNumberTwo;
	}

	public void setPhoneNumberTwo(BigDecimal phoneNumberTwo) {
		this.phoneNumberTwo = phoneNumberTwo;
	}

	public String getTaskComment() {
		return this.taskComment;
	}

	public void setTaskComment(String taskComment) {
		this.taskComment = taskComment;
	}

	public String getTaskName() {
		return this.taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
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

	public CallHistoryService getCallHistoryService() {
		return this.callHistoryService;
	}

	public void setCallHistoryService(CallHistoryService callHistoryService) {
		this.callHistoryService = callHistoryService;
	}

	public MstCallReqType getMstCallReqType() {
		return this.mstCallReqType;
	}

	public void setMstCallReqType(MstCallReqType mstCallReqType) {
		this.mstCallReqType = mstCallReqType;
	}

}