package org.golomt.arp.db.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the CALL_HISTORY_SERVICE database table.
 * 
 */
@Entity
@Table(name="CALL_HISTORY_SERVICE")
@NamedQuery(name="CallHistoryService.findAll", query="SELECT c FROM CallHistoryService c")
public class CallHistoryService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CALL_HISTORY_SERVICE_ID")
	private long callHistoryServiceId;

	@Column(name="CALL_COMMENT")
	private String callComment;

	@Column(name="CALL_HISTORY_ID")
	private BigDecimal callHistoryId;

	@Column(name="CALL_NAME")
	private String callName;

	@Column(name="CALL_RATE_ID")
	private BigDecimal callRateId;

	@Column(name="CALL_REQ_TYPE")
	private BigDecimal callReqType;

	@Column(name="CALL_RESULT_ID")
	private BigDecimal callResultId;

	@Column(name="CALL_SORT_ID")
	private BigDecimal callSortId;

	@Column(name="CALL_STAT_ID")
	private BigDecimal callStatId;

	@Temporal(TemporalType.DATE)
	private Date deadline;

	@Column(name="DELETE_DATE")
	private Timestamp deleteDate;

	@Column(name="DELETE_USER")
	private BigDecimal deleteUser;

	@Column(name="DETAILED_SERVICE_TYPE1")
	private BigDecimal detailedServiceType1;

	@Column(name="DETAILED_SERVICE_TYPE2")
	private BigDecimal detailedServiceType2;

	@Column(name="DETAILED_SERVICE_TYPE3")
	private BigDecimal detailedServiceType3;

	@Column(name="DETAILED_SERVICE_TYPE4")
	private BigDecimal detailedServiceType4;

	@Column(name="DETAILED_SERVICE_TYPE5")
	private BigDecimal detailedServiceType5;

	@Column(name="INSERT_DATE")
	private Timestamp insertDate;

	@Column(name="INSERT_USER")
	private BigDecimal insertUser;

	@Column(name="SEND_STATUS")
	private String sendStatus;

	@Column(name="SERVICE_TYPE1")
	private BigDecimal serviceType1;

	@Column(name="SERVICE_TYPE2")
	private BigDecimal serviceType2;

	@Column(name="SERVICE_TYPE3")
	private BigDecimal serviceType3;

	@Column(name="SERVICE_TYPE4")
	private BigDecimal serviceType4;

	@Column(name="SERVICE_TYPE5")
	private BigDecimal serviceType5;

	@Column(name="TASK_COMMENT")
	private String taskComment;

	@Column(name="TRANSFER_CHECK")
	private BigDecimal transferCheck;

	@Column(name="TRANSFER_PHONENUMBER")
	private BigDecimal transferPhonenumber;

	@Column(name="UNIT_ID")
	private BigDecimal unitId;

	@Column(name="UPDATE_DATE")
	private Timestamp updateDate;

	@Column(name="UPDATE_USER")
	private BigDecimal updateUser;

	//bi-directional many-to-one association to Task
	@OneToMany(mappedBy="callHistoryService")
	private List<Task> tasks;

	public CallHistoryService() {
	}

	public long getCallHistoryServiceId() {
		return this.callHistoryServiceId;
	}

	public void setCallHistoryServiceId(long callHistoryServiceId) {
		this.callHistoryServiceId = callHistoryServiceId;
	}

	public String getCallComment() {
		return this.callComment;
	}

	public void setCallComment(String callComment) {
		this.callComment = callComment;
	}

	public BigDecimal getCallHistoryId() {
		return this.callHistoryId;
	}

	public void setCallHistoryId(BigDecimal callHistoryId) {
		this.callHistoryId = callHistoryId;
	}

	public String getCallName() {
		return this.callName;
	}

	public void setCallName(String callName) {
		this.callName = callName;
	}

	public BigDecimal getCallRateId() {
		return this.callRateId;
	}

	public void setCallRateId(BigDecimal callRateId) {
		this.callRateId = callRateId;
	}

	public BigDecimal getCallReqType() {
		return this.callReqType;
	}

	public void setCallReqType(BigDecimal callReqType) {
		this.callReqType = callReqType;
	}

	public BigDecimal getCallResultId() {
		return this.callResultId;
	}

	public void setCallResultId(BigDecimal callResultId) {
		this.callResultId = callResultId;
	}

	public BigDecimal getCallSortId() {
		return this.callSortId;
	}

	public void setCallSortId(BigDecimal callSortId) {
		this.callSortId = callSortId;
	}

	public BigDecimal getCallStatId() {
		return this.callStatId;
	}

	public void setCallStatId(BigDecimal callStatId) {
		this.callStatId = callStatId;
	}

	public Date getDeadline() {
		return this.deadline;
	}

	public void setDeadline(Date deadline) {
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

	public BigDecimal getDetailedServiceType1() {
		return this.detailedServiceType1;
	}

	public void setDetailedServiceType1(BigDecimal detailedServiceType1) {
		this.detailedServiceType1 = detailedServiceType1;
	}

	public BigDecimal getDetailedServiceType2() {
		return this.detailedServiceType2;
	}

	public void setDetailedServiceType2(BigDecimal detailedServiceType2) {
		this.detailedServiceType2 = detailedServiceType2;
	}

	public BigDecimal getDetailedServiceType3() {
		return this.detailedServiceType3;
	}

	public void setDetailedServiceType3(BigDecimal detailedServiceType3) {
		this.detailedServiceType3 = detailedServiceType3;
	}

	public BigDecimal getDetailedServiceType4() {
		return this.detailedServiceType4;
	}

	public void setDetailedServiceType4(BigDecimal detailedServiceType4) {
		this.detailedServiceType4 = detailedServiceType4;
	}

	public BigDecimal getDetailedServiceType5() {
		return this.detailedServiceType5;
	}

	public void setDetailedServiceType5(BigDecimal detailedServiceType5) {
		this.detailedServiceType5 = detailedServiceType5;
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

	public String getSendStatus() {
		return this.sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	public BigDecimal getServiceType1() {
		return this.serviceType1;
	}

	public void setServiceType1(BigDecimal serviceType1) {
		this.serviceType1 = serviceType1;
	}

	public BigDecimal getServiceType2() {
		return this.serviceType2;
	}

	public void setServiceType2(BigDecimal serviceType2) {
		this.serviceType2 = serviceType2;
	}

	public BigDecimal getServiceType3() {
		return this.serviceType3;
	}

	public void setServiceType3(BigDecimal serviceType3) {
		this.serviceType3 = serviceType3;
	}

	public BigDecimal getServiceType4() {
		return this.serviceType4;
	}

	public void setServiceType4(BigDecimal serviceType4) {
		this.serviceType4 = serviceType4;
	}

	public BigDecimal getServiceType5() {
		return this.serviceType5;
	}

	public void setServiceType5(BigDecimal serviceType5) {
		this.serviceType5 = serviceType5;
	}

	public String getTaskComment() {
		return this.taskComment;
	}

	public void setTaskComment(String taskComment) {
		this.taskComment = taskComment;
	}

	public BigDecimal getTransferCheck() {
		return this.transferCheck;
	}

	public void setTransferCheck(BigDecimal transferCheck) {
		this.transferCheck = transferCheck;
	}

	public BigDecimal getTransferPhonenumber() {
		return this.transferPhonenumber;
	}

	public void setTransferPhonenumber(BigDecimal transferPhonenumber) {
		this.transferPhonenumber = transferPhonenumber;
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

	public List<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Task addTask(Task task) {
		getTasks().add(task);
		task.setCallHistoryService(this);

		return task;
	}

	public Task removeTask(Task task) {
		getTasks().remove(task);
		task.setCallHistoryService(null);

		return task;
	}

}