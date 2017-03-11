package org.golomt.arp.db.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the TASK_TEMP database table.
 * 
 */
@Entity
@Table(name="TASK_TEMP")
@NamedQuery(name="TaskTemp.findAll", query="SELECT t FROM TaskTemp t")
public class TaskTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TASK_ID")
	private long taskId;

	@Column(name="AGENT_ID")
	private BigDecimal agentId;

	@Column(name="CALLER_NAME")
	private String callerName;

	private Timestamp deadline;

	@Column(name="INSERT_USER")
	private String insertUser;

	@Column(name="PHONE_NUMBER")
	private BigDecimal phoneNumber;

	@Column(name="\"RESULT\"")
	private String result;

	@Column(name="TASK_NAME")
	private String taskName;

	@Column(name="TASK_TYPE")
	private String taskType;

	public TaskTemp() {
	}

	public long getTaskId() {
		return this.taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
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

	public Timestamp getDeadline() {
		return this.deadline;
	}

	public void setDeadline(Timestamp deadline) {
		this.deadline = deadline;
	}

	public String getInsertUser() {
		return this.insertUser;
	}

	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}

	public BigDecimal getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(BigDecimal phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getTaskName() {
		return this.taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskType() {
		return this.taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

}