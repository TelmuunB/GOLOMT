package org.golomt.arp.db.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the MST_AGENT_LOG database table.
 * 
 */
@Entity
@Table(name="MST_AGENT_LOG")
@NamedQuery(name="MstAgentLog.findAll", query="SELECT m FROM MstAgentLog m")
public class MstAgentLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="AGENT_ID")
	private String agentId;

	@Column(name="LAST_SESSION")
	private String lastSession;

	private BigDecimal status;

	@Column(name="UPDATE_DATE")
	private Timestamp updateDate;

	public MstAgentLog() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAgentId() {
		return this.agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getLastSession() {
		return this.lastSession;
	}

	public void setLastSession(String lastSession) {
		this.lastSession = lastSession;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

}