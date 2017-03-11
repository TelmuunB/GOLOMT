package org.golomt.arp.db.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the MST_HELP_PER database table.
 * 
 */
@Entity
@Table(name="MST_HELP_PER")
@NamedQuery(name="MstHelpPer.findAll", query="SELECT m FROM MstHelpPer m")
public class MstHelpPer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="HELP_PER_ID")
	private String helpPerId;

	@Column(name="DELETE_DATE")
	private String deleteDate;

	@Column(name="DELETE_USER")
	private String deleteUser;

	@Column(name="HELP_PER_NAME")
	private String helpPerName;

	@Column(name="INSERT_DATE")
	private String insertDate;

	@Column(name="INSERT_DATE_AGENT")
	private String insertDateAgent;

	@Column(name="INSERT_USER")
	private String insertUser;

	@Column(name="MIGRATION_NUM")
	private BigDecimal migrationNum;

	@Column(name="ORDER_NUM")
	private String orderNum;

	@Column(name="PARENT_HELP_PER_ID")
	private BigDecimal parentHelpPerId;

	@Column(name="PERMITTED_AGENT")
	private String permittedAgent;

	private BigDecimal status;

	@Column(name="UPDATE_AGENT")
	private String updateAgent;

	@Column(name="UPDATE_DATE")
	private String updateDate;

	@Column(name="UPDATE_DATE_AGENT")
	private String updateDateAgent;

	@Column(name="UPDATE_USER")
	private String updateUser;

	public MstHelpPer() {
	}

	public String getHelpPerId() {
		return this.helpPerId;
	}

	public void setHelpPerId(String helpPerId) {
		this.helpPerId = helpPerId;
	}

	public String getDeleteDate() {
		return this.deleteDate;
	}

	public void setDeleteDate(String deleteDate) {
		this.deleteDate = deleteDate;
	}

	public String getDeleteUser() {
		return this.deleteUser;
	}

	public void setDeleteUser(String deleteUser) {
		this.deleteUser = deleteUser;
	}

	public String getHelpPerName() {
		return this.helpPerName;
	}

	public void setHelpPerName(String helpPerName) {
		this.helpPerName = helpPerName;
	}

	public String getInsertDate() {
		return this.insertDate;
	}

	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}

	public String getInsertDateAgent() {
		return this.insertDateAgent;
	}

	public void setInsertDateAgent(String insertDateAgent) {
		this.insertDateAgent = insertDateAgent;
	}

	public String getInsertUser() {
		return this.insertUser;
	}

	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}

	public BigDecimal getMigrationNum() {
		return this.migrationNum;
	}

	public void setMigrationNum(BigDecimal migrationNum) {
		this.migrationNum = migrationNum;
	}

	public String getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public BigDecimal getParentHelpPerId() {
		return this.parentHelpPerId;
	}

	public void setParentHelpPerId(BigDecimal parentHelpPerId) {
		this.parentHelpPerId = parentHelpPerId;
	}

	public String getPermittedAgent() {
		return this.permittedAgent;
	}

	public void setPermittedAgent(String permittedAgent) {
		this.permittedAgent = permittedAgent;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getUpdateAgent() {
		return this.updateAgent;
	}

	public void setUpdateAgent(String updateAgent) {
		this.updateAgent = updateAgent;
	}

	public String getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateDateAgent() {
		return this.updateDateAgent;
	}

	public void setUpdateDateAgent(String updateDateAgent) {
		this.updateDateAgent = updateDateAgent;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

}