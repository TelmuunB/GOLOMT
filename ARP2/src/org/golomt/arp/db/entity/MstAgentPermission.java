package org.golomt.arp.db.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MST_AGENT_PERMISSION database table.
 * 
 */
@Entity
@Table(name="MST_AGENT_PERMISSION")
@NamedQuery(name="MstAgentPermission.findAll", query="SELECT m FROM MstAgentPermission m")
public class MstAgentPermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="AGENT_PERMISSION_ID")
	private long agentPermissionId;

	@Column(name="AGENT_PERMISSION_NAME")
	private String agentPermissionName;

	public MstAgentPermission() {
	}

	public long getAgentPermissionId() {
		return this.agentPermissionId;
	}

	public void setAgentPermissionId(long agentPermissionId) {
		this.agentPermissionId = agentPermissionId;
	}

	public String getAgentPermissionName() {
		return this.agentPermissionName;
	}

	public void setAgentPermissionName(String agentPermissionName) {
		this.agentPermissionName = agentPermissionName;
	}

}