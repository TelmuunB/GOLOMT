package org.golomt.arp.db.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MST_DATABASE_TABLES database table.
 * 
 */
@Entity
@Table(name="MST_DATABASE_TABLES")
@NamedQuery(name="MstDatabaseTable.findAll", query="SELECT m FROM MstDatabaseTable m")
public class MstDatabaseTable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TABLE_ID")
	private long tableId;

	@Column(name="TABLE_NAME")
	private String tableName;

	@Column(name="TABLE_NAME1")
	private String tableName1;

	public MstDatabaseTable() {
	}

	public long getTableId() {
		return this.tableId;
	}

	public void setTableId(long tableId) {
		this.tableId = tableId;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableName1() {
		return this.tableName1;
	}

	public void setTableName1(String tableName1) {
		this.tableName1 = tableName1;
	}

}