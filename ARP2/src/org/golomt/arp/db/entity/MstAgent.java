package org.golomt.arp.db.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the MST_AGENT database table.
 * 
 */
@Entity
@Table(name="MST_AGENT")
@NamedQuery(name="MstAgent.findAll", query="SELECT m FROM MstAgent m")
public class MstAgent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="AGENT_ID")
	private String agentId;

	@Column(name="AGENT_PERMISSION")
	private BigDecimal agentPermission;

	@Column(name="AGENT_REGISTER")
	private String agentRegister;

	@Column(name="DELETE_DATE")
	private Timestamp deleteDate;

	@Column(name="DELETE_USER")
	private BigDecimal deleteUser;

	private String email;

	private String firstname;

	@Column(name="INSERT_DATE")
	private Timestamp insertDate;

	@Column(name="INSERT_USER")
	private BigDecimal insertUser;

	private String lastname;

	private String password;

	@Column(name="UPDATE_DATE")
	private Timestamp updateDate;

	@Column(name="UPDATE_USER")
	private BigDecimal updateUser;

	public MstAgent() {
	}

	public String getAgentId() {
		return this.agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public BigDecimal getAgentPermission() {
		return this.agentPermission;
	}

	public void setAgentPermission(BigDecimal agentPermission) {
		this.agentPermission = agentPermission;
	}

	public String getAgentRegister() {
		return this.agentRegister;
	}

	public void setAgentRegister(String agentRegister) {
		this.agentRegister = agentRegister;
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
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

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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

}