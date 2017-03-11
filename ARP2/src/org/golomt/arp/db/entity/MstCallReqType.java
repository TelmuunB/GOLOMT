package org.golomt.arp.db.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the MST_CALL_REQ_TYPE database table.
 * 
 */
@Entity
@Table(name="MST_CALL_REQ_TYPE")
@NamedQuery(name="MstCallReqType.findAll", query="SELECT m FROM MstCallReqType m")
public class MstCallReqType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CALL_REQ_TYPE_ID")
	private long callReqTypeId;

	@Column(name="CALL_REQ_TYPE_NAME")
	private String callReqTypeName;

	@Column(name="DELETE_DATE")
	private Timestamp deleteDate;

	@Column(name="DELETE_USER")
	private BigDecimal deleteUser;

	@Column(name="INSERT_DATE")
	private Timestamp insertDate;

	@Column(name="INSERT_USER")
	private BigDecimal insertUser;

	@Column(name="ORDER_NUM")
	private BigDecimal orderNum;

	@Column(name="UPDATE_DATE")
	private Timestamp updateDate;

	@Column(name="UPDATE_USER")
	private BigDecimal updateUser;

	//bi-directional many-to-one association to Task
	@OneToMany(mappedBy="mstCallReqType")
	private List<Task> tasks;

	public MstCallReqType() {
	}

	public long getCallReqTypeId() {
		return this.callReqTypeId;
	}

	public void setCallReqTypeId(long callReqTypeId) {
		this.callReqTypeId = callReqTypeId;
	}

	public String getCallReqTypeName() {
		return this.callReqTypeName;
	}

	public void setCallReqTypeName(String callReqTypeName) {
		this.callReqTypeName = callReqTypeName;
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

	public BigDecimal getInsertUser() {
		return this.insertUser;
	}

	public void setInsertUser(BigDecimal insertUser) {
		this.insertUser = insertUser;
	}

	public BigDecimal getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(BigDecimal orderNum) {
		this.orderNum = orderNum;
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
		task.setMstCallReqType(this);

		return task;
	}

	public Task removeTask(Task task) {
		getTasks().remove(task);
		task.setMstCallReqType(null);

		return task;
	}

}