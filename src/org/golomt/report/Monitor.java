package org.golomt.report;
import infox.org.db.DBAccess;
import infox.org.sql.SQLListReader;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.iaac.common.BForm;
import org.iaac.consts.Consts;

public class Monitor extends BForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private LinkedHashMap<String,Object> agentList;
	private LinkedHashMap<String,Object> notReadyList;
	private LinkedHashMap<String,Object> talkingList;
	private LinkedHashMap<String,Object> inboundList;
	private LinkedHashMap<String,Object> outboundList;
	private LinkedHashMap<String,Object> missedList;
	
	public String execute()
    {
       return SUCCESS; 
    }
	public void validate()
    {
    }
	public String getTableData(){
		agentList=new LinkedHashMap<String, Object>();
		notReadyList=new LinkedHashMap<String, Object>();
		talkingList=new LinkedHashMap<String, Object>();
		inboundList=new LinkedHashMap<String, Object>();
		outboundList=new LinkedHashMap<String, Object>();
		missedList=new LinkedHashMap<String, Object>();
		DBAccess db=getDBAccess();
		db.connect();
		try{
			SQLListReader reader=getSQLListReader();
			String sql="";
			sql=reader.findSQLByCommand(Consts.MONITOR_AGENT_LOG);
			ResultSet rs;
			log(sql);
			rs=db.select(sql);
			while(rs.next()){
				agentList.put(rs.getString("AGENT_ID"), rs.getString("AGENT"));
				notReadyList.put(rs.getString("AGENT_ID"), rs.getString("STATUS"));
				talkingList.put(rs.getString("AGENT_ID"), rs.getString("TALKING"));
				inboundList.put(rs.getString("AGENT_ID"), rs.getString("INBOUND_CALL"));
				outboundList.put(rs.getString("AGENT_ID"), rs.getString("OUTBOUND_CALL"));
				missedList.put(rs.getString("AGENT_ID"), rs.getString("MISSED_CALL"));
			}
		}catch(Exception e){
			log("Exception",e);
			return ERROR;
		}finally{
			db.disconnect();
		}
		return "json";
	
	}
	
	
	public LinkedHashMap<String,Object> fromDatabase(String[] baseQuerys,String colId1,String colId2, String successMsg, String errorMsg) {
    	LinkedHashMap<String,Object> result=new LinkedHashMap<String,Object>();
		String sql;
		SQLException ex = new SQLException();
		DBAccess db = getDBAccess();
		db.connect();
		// Connection conn = db.getConnection();
		try {
			SQLListReader reader = getSQLListReader();
			// conn.setAutoCommit(false);
			int i = 0;
			while (i < baseQuerys.length && db.getLastException() == null) {
				if (baseQuerys[i] != null) {
					sql = reader.findSQLByCommand(baseQuerys[i]);
					log(sql);
					ResultSet rs=db.select(sql);
					while(rs.next()){
						result.put(rs.getString(colId1), rs.getString(colId2));
					}
				}
				i++;
			}
			if (db.getLastException() != null) {
				log("db.getLastException()" + db.getLastException());
				log("ex" + ex.getErrorCode());
				db.rollback();
				ex = db.getLastException();
				switch (ex.getErrorCode()) {
				case 1062:
					addActionError(getText("ERR010"));
					break;
				// case 1451: addActionError(getText(ERR035));
				default:
					log(getText(errorMsg), db.getLastException());
					addActionMessage(getText(errorMsg));
				}
				return result;

			} else {
				db.commit();
				log(getText(successMsg));
				addActionMessage(getText(successMsg));
				return result;
			}

		} catch (Exception e) {
			// Log bichih?
			log("Баазтай холбогдох алдаа", e);

			return result;
		} finally {
			db.disconnect();
		}

	}
	
	
	
	public LinkedHashMap<String, Object> getAgentList() {
		return agentList;
	}
	public void setAgentList(LinkedHashMap<String, Object> agentList) {
		this.agentList = agentList;
	}
	public LinkedHashMap<String, Object> getInboundList() {
		return inboundList;
	}
	public void setInboundList(LinkedHashMap<String, Object> inboundList) {
		this.inboundList = inboundList;
	}
	public LinkedHashMap<String, Object> getMissedList() {
		return missedList;
	}
	public void setMissedList(LinkedHashMap<String, Object> missedList) {
		this.missedList = missedList;
	}
	public LinkedHashMap<String, Object> getNotReadyList() {
		return notReadyList;
	}
	public void setNotReadyList(LinkedHashMap<String, Object> notReadyList) {
		this.notReadyList = notReadyList;
	}
	public LinkedHashMap<String, Object> getOutboundList() {
		return outboundList;
	}
	public void setOutboundList(LinkedHashMap<String, Object> outboundList) {
		this.outboundList = outboundList;
	}
	public LinkedHashMap<String, Object> getTalkingList() {
		return talkingList;
	}
	public void setTalkingList(LinkedHashMap<String, Object> talkingList) {
		this.talkingList = talkingList;
	}
}
