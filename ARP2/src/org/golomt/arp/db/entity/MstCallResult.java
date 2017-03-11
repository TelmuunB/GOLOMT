package org.golomt.arp.db.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the MST_CALL_RESULT database table.
 * 
 */
@Entity
@Table(name="MST_CALL_RESULT")
@NamedQuery(name="MstCallResult.findAll", query="SELECT m FROM MstCallResult m")
public class MstCallResult implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CALL_RESULT_ID")
	private long callResultId;

	@Column(name="CALL_RESULT_NAME")
	private String callResultName;

	@Column(name="DELETE_DATE")
	private Timestamp deleteDate;

	@Column(name="DELETE_USER")
	private BigDecimal deleteUser;

	@Column(name="INSERT_DATE")
	private Timestamp insertDate;

	@Column(name="INSERT_DATE_AGENT")
	private Timestamp insertDateAgent;

	@Column(name="INSERT_USER")
	private BigDecimal insertUser;

	@Column(name="MIGRATION_NUM")
	private BigDecimal migrationNum;

	@Column(name="ORDER_NUM")
	private BigDecimal orderNum;

	@Column(name="PARENT_CALL_RESULT_ID")
	private BigDecimal parentCallResultId;

	@Column(name="PERMITTED_AGENT")
	private BigDecimal permittedAgent;

	private BigDecimal status;

	@Column(name="UPDATE_AGENT")
	private BigDecimal updateAgent;

	@Column(name="UPDATE_DATE")
	private Timestamp updateDate;

	@Column(name="UPDATE_DATE_AGENT")
	private Timestamp updateDateAgent;

	@Column(name="UPDATE_USER")
	private BigDecimal updateUser;

	//bi-directional many-to-one association to Complaint
	@OneToMany(mappedBy="mstCallResult")
	private List<Complaint> complaints;

	public MstCallResult() {
	}

	public long getCallResultId() {
		return this.callResultId;
	}

	public void setCallResultId(long callResultId) {
		this.callResultId = callResultId;
	}

	public String getCallResultName() {
		return this.callResultName;
	}

	public void setCallResultName(String callResultName) {
		this.callResultName = callResultName;
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

	public Timestamp getInsertDate() {
		return this.insertDate;
	}

	public void setInsertDate(Timestamp insertDate) {
		this.insertDate = insertDate;
	}

	public Timestamp getInsertDateAgent() {
		return this.insertDateAgent;
	}

	public void setInsertDateAgent(Timestamp insertDateAgent) {
		this.insertDateAgent = insertDateAgent;
	}

	public BigDecimal getInsertUser() {
		return this.insertUser;
	}

	public void setInsertUser(BigDecimal insertUser) {
		this.insertUser = insertUser;
	}

	public BigDecimal getMigrationNum() {
		return this.migrationNum;
	}

	public void setMigrationNum(BigDecimal migrationNum) {
		this.migrationNum = migrationNum;
	}

	public BigDecimal getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(BigDecimal orderNum) {
		this.orderNum = orderNum;
	}

	public BigDecimal getParentCallResultId() {
		return this.parentCallResultId;
	}

	public void setParentCallResultId(BigDecimal parentCallResultId) {
		this.parentCallResultId = parentCallResultId;
	}

	public BigDecimal getPermittedAgent() {
		return this.permittedAgent;
	}

	public void setPermittedAgent(BigDecimal permittedAgent) {
		this.permittedAgent = permittedAgent;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getUpdateAgent() {
		return this.updateAgent;
	}

	public void setUpdateAgent(BigDecimal updateAgent) {
		this.updateAgent = updateAgent;
	}

	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public Timestamp getUpdateDateAgent() {
		return this.updateDateAgent;
	}

	public void setUpdateDateAgent(Timestamp updateDateAgent) {
		this.updateDateAgent = updateDateAgent;
	}

	public BigDecimal getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(BigDecimal updateUser) {
		this.updateUser = updateUser;
	}

	public List<Complaint> getComplaints() {
		return this.complaints;
	}

	public void setComplaints(List<Complaint> complaints) {
		this.complaints = complaints;
	}

	public Complaint addComplaint(Complaint complaint) {
		getComplaints().add(complaint);
		complaint.setMstCallResult(this);

		return complaint;
	}

	public Complaint removeComplaint(Complaint complaint) {
		getComplaints().remove(complaint);
		complaint.setMstCallResult(null);

		return complaint;
	}

}