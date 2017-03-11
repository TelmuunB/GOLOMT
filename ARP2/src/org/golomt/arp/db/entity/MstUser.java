package org.golomt.arp.db.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the MST_USERS database table.
 * 
 */
@Entity
@Table(name="MST_USERS")
@NamedQuery(name="MstUser.findAll", query="SELECT m FROM MstUser m")
public class MstUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long phonenumber;

	@Temporal(TemporalType.DATE)
	@Column(name="DELETE_DATE")
	private Date deleteDate;

	@Column(name="DELETE_USER")
	private BigDecimal deleteUser;

	private String firstname;

	@Temporal(TemporalType.DATE)
	@Column(name="INSERT_DATE")
	private Date insertDate;

	@Column(name="INSERT_USER")
	private BigDecimal insertUser;

	private String lastname;

	private BigDecimal phonenumber2;

	private BigDecimal status;

	@Temporal(TemporalType.DATE)
	@Column(name="UPDATE_DATE")
	private Date updateDate;

	@Column(name="UPDATE_USER")
	private BigDecimal updateUser;

	//bi-directional many-to-one association to CallHistory
	@OneToMany(mappedBy="mstUser")
	private List<CallHistory> callHistories;

	public MstUser() {
	}

	public long getPhonenumber() {
		return this.phonenumber;
	}

	public void setPhonenumber(long phonenumber) {
		this.phonenumber = phonenumber;
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

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
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

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public BigDecimal getPhonenumber2() {
		return this.phonenumber2;
	}

	public void setPhonenumber2(BigDecimal phonenumber2) {
		this.phonenumber2 = phonenumber2;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
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

	public List<CallHistory> getCallHistories() {
		return this.callHistories;
	}

	public void setCallHistories(List<CallHistory> callHistories) {
		this.callHistories = callHistories;
	}

	public CallHistory addCallHistory(CallHistory callHistory) {
		getCallHistories().add(callHistory);
		callHistory.setMstUser(this);

		return callHistory;
	}

	public CallHistory removeCallHistory(CallHistory callHistory) {
		getCallHistories().remove(callHistory);
		callHistory.setMstUser(null);

		return callHistory;
	}

}