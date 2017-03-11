package org.golomt.arp.db.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the "MESSAGE" database table.
 * 
 */
@Entity
@Table(name="\"MESSAGE\"")
@NamedQuery(name="Message.findAll", query="SELECT m FROM Message m")
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MESSAGE_ID")
	private long messageId;

	@Column(name="AGENT_ID")
	private BigDecimal agentId;

	@Column(name="DELETE_DATE")
	private Timestamp deleteDate;

	@Column(name="DELETE_USER")
	private BigDecimal deleteUser;

	@Column(name="INSERT_DATE")
	private Timestamp insertDate;

	@Column(name="INSERT_USER")
	private BigDecimal insertUser;

	@Column(name="MESSAGE_DATE")
	private Timestamp messageDate;

	@Column(name="MESSAGE_RECEIVER")
	private BigDecimal messageReceiver;

	@Column(name="MESSAGE_SENDER")
	private BigDecimal messageSender;

	@Column(name="TEXT_MESSAGE")
	private String textMessage;

	@Column(name="UPDATE_DATE")
	private Timestamp updateDate;

	@Column(name="UPDATE_USER")
	private BigDecimal updateUser;

	public Message() {
	}

	public long getMessageId() {
		return this.messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	public BigDecimal getAgentId() {
		return this.agentId;
	}

	public void setAgentId(BigDecimal agentId) {
		this.agentId = agentId;
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

	public Timestamp getMessageDate() {
		return this.messageDate;
	}

	public void setMessageDate(Timestamp messageDate) {
		this.messageDate = messageDate;
	}

	public BigDecimal getMessageReceiver() {
		return this.messageReceiver;
	}

	public void setMessageReceiver(BigDecimal messageReceiver) {
		this.messageReceiver = messageReceiver;
	}

	public BigDecimal getMessageSender() {
		return this.messageSender;
	}

	public void setMessageSender(BigDecimal messageSender) {
		this.messageSender = messageSender;
	}

	public String getTextMessage() {
		return this.textMessage;
	}

	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
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