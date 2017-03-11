package org.golomt.arp.db.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the CALL_HISTORY database table.
 * 
 */
@Entity
@Table(name="CALL_HISTORY")
@NamedQuery(name="CallHistory.findAll", query="SELECT c FROM CallHistory c")
public class CallHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CALL_HISTORY_ID")
	private long callHistoryId;

	@Column(name="AGENT_ID")
	private String agentId;

	private String calltype;

	@Temporal(TemporalType.DATE)
	@Column(name="DELETE_DATE")
	private Date deleteDate;

	@Column(name="DELETE_USER")
	private BigDecimal deleteUser;

	@Column(name="FINISHED_TIME")
	private Timestamp finishedTime;

	@Temporal(TemporalType.DATE)
	@Column(name="INSERT_DATE")
	private Date insertDate;

	@Column(name="INSERT_USER")
	private BigDecimal insertUser;

	private BigDecimal phonenumber2;

	@Column(name="START_TIME")
	private Timestamp startTime;

	private BigDecimal transfer;

	@Temporal(TemporalType.DATE)
	@Column(name="UPDATE_DATE")
	private Date updateDate;

	@Column(name="UPDATE_USER")
	private BigDecimal updateUser;

	//bi-directional many-to-one association to MstUser
	@ManyToOne
	@JoinColumn(name="PHONENUMBER")
	private MstUser mstUser;

	//bi-directional many-to-one association to Complaint
	@OneToMany(mappedBy="callHistory")
	private List<Complaint> complaints;

	//bi-directional many-to-one association to Task
	@OneToMany(mappedBy="callHistory")
	private List<Task> tasks;

	public CallHistory() {
	}

	public long getCallHistoryId() {
		return this.callHistoryId;
	}

	public void setCallHistoryId(long callHistoryId) {
		this.callHistoryId = callHistoryId;
	}

	public String getAgentId() {
		return this.agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getCalltype() {
		return this.calltype;
	}

	public void setCalltype(String calltype) {
		this.calltype = calltype;
	}

	public Date getDeleteDate() {
		return this.deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	public BigDecimal getDeleteUser() {
		return this.deleteUser;
	}

	public void setDeleteUser(BigDecimal deleteUser) {
		this.deleteUser = deleteUser;
	}

	public Timestamp getFinishedTime() {
		return this.finishedTime;
	}

	public void setFinishedTime(Timestamp finishedTime) {
		this.finishedTime = finishedTime;
	}

	public Date getInsertDate() {
		return this.insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public BigDecimal getInsertUser() {
		return this.insertUser;
	}

	public void setInsertUser(BigDecimal insertUser) {
		this.insertUser = insertUser;
	}

	public BigDecimal getPhonenumber2() {
		return this.phonenumber2;
	}

	public void setPhonenumber2(BigDecimal phonenumber2) {
		this.phonenumber2 = phonenumber2;
	}

	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public BigDecimal getTransfer() {
		return this.transfer;
	}

	public void setTransfer(BigDecimal transfer) {
		this.transfer = transfer;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public BigDecimal getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(BigDecimal updateUser) {
		this.updateUser = updateUser;
	}

	public MstUser getMstUser() {
		return this.mstUser;
	}

	public void setMstUser(MstUser mstUser) {
		this.mstUser = mstUser;
	}

	public List<Complaint> getComplaints() {
		return this.complaints;
	}

	public void setComplaints(List<Complaint> complaints) {
		this.complaints = complaints;
	}

	public Complaint addComplaint(Complaint complaint) {
		getComplaints().add(complaint);
		complaint.setCallHistory(this);

		return complaint;
	}

	public Complaint removeComplaint(Complaint complaint) {
		getComplaints().remove(complaint);
		complaint.setCallHistory(null);

		return complaint;
	}

	public List<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Task addTask(Task task) {
		getTasks().add(task);
		task.setCallHistory(this);

		return task;
	}

	public Task removeTask(Task task) {
		getTasks().remove(task);
		task.setCallHistory(null);

		return task;
	}

}