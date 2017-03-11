package org.golomt.arp.db.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the MST_SERVICE database table.
 * 
 */
@Entity
@Table(name="MST_SERVICE")
@NamedQuery(name="MstService.findAll", query="SELECT m FROM MstService m")
public class MstService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SERVICE_ID")
	private long serviceId;

	@Column(name="CALL_REQ_TYPE_ID")
	private BigDecimal callReqTypeId;

	@Column(name="DELETE_DATE")
	private Timestamp deleteDate;

	@Column(name="DELETE_USER")
	private BigDecimal deleteUser;

	@Column(name="DISPLAY_ID")
	private BigDecimal displayId;

	@Column(name="INSERT_DATE")
	private Timestamp insertDate;

	@Column(name="INSERT_USER")
	private BigDecimal insertUser;

	@Column(name="ORDER_NUM")
	private BigDecimal orderNum;

	@Column(name="PARENT_SERVICE_ID")
	private BigDecimal parentServiceId;

	@Column(name="PERMITTED_AGENT")
	private String permittedAgent;

	@Column(name="SERVICE_NAME")
	private String serviceName;

	@Column(name="UPDATE_DATE")
	private Timestamp updateDate;

	@Column(name="UPDATE_USER")
	private BigDecimal updateUser;

	public MstService() {
	}

	public long getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(long serviceId) {
		this.serviceId = serviceId;
	}

	public BigDecimal getCallReqTypeId() {
		return this.callReqTypeId;
	}

	public void setCallReqTypeId(BigDecimal callReqTypeId) {
		this.callReqTypeId = callReqTypeId;
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

	public BigDecimal getDisplayId() {
		return this.displayId;
	}

	public void setDisplayId(BigDecimal displayId) {
		this.displayId = displayId;
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

	public BigDecimal getParentServiceId() {
		return this.parentServiceId;
	}

	public void setParentServiceId(BigDecimal parentServiceId) {
		this.parentServiceId = parentServiceId;
	}

	public String getPermittedAgent() {
		return this.permittedAgent;
	}

	public void setPermittedAgent(String permittedAgent) {
		this.permittedAgent = permittedAgent;
	}

	public String getServiceName() {
		return this.serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
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