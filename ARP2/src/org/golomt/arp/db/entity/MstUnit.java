package org.golomt.arp.db.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the MST_UNIT database table.
 * 
 */
@Entity
@Table(name="MST_UNIT")
@NamedQuery(name="MstUnit.findAll", query="SELECT m FROM MstUnit m")
public class MstUnit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="UNIT_ID")
	private long unitId;

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

	@Column(name="PARENT_UNIT_ID")
	private BigDecimal parentUnitId;

	@Column(name="PERMITTED_AGENT")
	private BigDecimal permittedAgent;

	private BigDecimal status;

	@Column(name="UNIT_NAME")
	private String unitName;

	@Column(name="UPDATE_AGENT")
	private BigDecimal updateAgent;

	@Column(name="UPDATE_DATE")
	private Timestamp updateDate;

	@Column(name="\"UPDATE_DATE_AGENT \"")
	private Timestamp updateDateAgent_;

	@Column(name="UPDATE_USER")
	private BigDecimal updateUser;

	public MstUnit() {
	}

	public long getUnitId() {
		return this.unitId;
	}

	public void setUnitId(long unitId) {
		this.unitId = unitId;
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

	public BigDecimal getParentUnitId() {
		return this.parentUnitId;
	}

	public void setParentUnitId(BigDecimal parentUnitId) {
		this.parentUnitId = parentUnitId;
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

	public String getUnitName() {
		return this.unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
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

	public Timestamp getUpdateDateAgent_() {
		return this.updateDateAgent_;
	}

	public void setUpdateDateAgent_(Timestamp updateDateAgent_) {
		this.updateDateAgent_ = updateDateAgent_;
	}

	public BigDecimal getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(BigDecimal updateUser) {
		this.updateUser = updateUser;
	}

}