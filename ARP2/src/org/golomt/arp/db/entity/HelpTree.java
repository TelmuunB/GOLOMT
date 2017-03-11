package org.golomt.arp.db.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the HELP_TREE database table.
 * 
 */
@Entity
@Table(name="HELP_TREE")
@NamedQuery(name="HelpTree.findAll", query="SELECT h FROM HelpTree h")
public class HelpTree implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Lob
	private byte[] content;

	@Column(name="DELETE_DATE")
	private Timestamp deleteDate;

	@Column(name="DELETE_USER")
	private String deleteUser;

	@Column(name="INSERT_DATE")
	private Timestamp insertDate;

	@Column(name="INSERT_USER")
	private BigDecimal insertUser;

	private String name;

	@Column(name="PARENT_ID")
	private BigDecimal parentId;

	private String premission;

	@Column(name="UPDATE_DATE")
	private Timestamp updateDate;

	@Column(name="UPDATE_USER")
	private String updateUser;

	public HelpTree() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public byte[] getContent() {
		return this.content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public Timestamp getDeleteDate() {
		return this.deleteDate;
	}

	public void setDeleteDate(Timestamp deleteDate) {
		this.deleteDate = deleteDate;
	}

	public String getDeleteUser() {
		return this.deleteUser;
	}

	public void setDeleteUser(String deleteUser) {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getParentId() {
		return this.parentId;
	}

	public void setParentId(BigDecimal parentId) {
		this.parentId = parentId;
	}

	public String getPremission() {
		return this.premission;
	}

	public void setPremission(String premission) {
		this.premission = premission;
	}

	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

}