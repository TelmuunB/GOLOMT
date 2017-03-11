/*
 * Моделийн нэр             MainMenu.java
 *
 * Функцын нэр          【Үндсэн форм】
 * Тvvх
 * Хувилбарын №     			Огноо        Хариуцагч         Агуулга
 * 					    01.00.00       2013/3/20   			Ж.Мөнгөнсүх    Шинээр хийсэн
 */
package org.golomt.common;

import infox.org.db.DBAccess;
import infox.org.sql.SQLListReader;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.golomt.bean.UserInfo;

import com.opensymphony.xwork2.ActionContext;

/**
 * @author
 *
 */
public class mainMenu extends XMLForm {
    /** serialVersionUID */
    private static final long serialVersionUID = 0L;

	/** Дуудлагын дугаар */
	private String callID ;

	/** Агентийн дугаар */
	private String agentID ;

	/** */
	private String phone_number;
	
	private String dnis;

	/** */
	private String start_time ;

	/** */
	private String type ;

	/** */
	private boolean isExistingCall ;

	/** */
	private String view_status ;

	/** */
	private String callEnd ;
	
	private String calltype;
	
	private String ownAgentId;

	private String err_message;
	
	private String Calling;
	
	private String Called;
	
	private String Answered;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() {
		setOwnAgentId(getAgentID());
		HashMap<String, Object> hsts = new HashMap<String, Object>();
		DBAccess db = getDBAccess();
		SQLListReader reader = getSQLListReader();
		if(!isEmpty(dnis)){
			if(dnis.length()==4 && phone_number.equals(getAgentID())){
				type=null;
				return SUCCESS;
			}if(phone_number.length()==4 && dnis.equals(getAgentID())){
				type=null;
				return SUCCESS;
			}
		}
		
		if(!isEmpty(getPhone_number())){
			if("<Unavailable>".equals(getPhone_number())){
				setPhone_number("000000");
			}
			if("11".equals(getPhone_number().substring(0, 2))){
				setPhone_number(getPhone_number().substring(2));
			}
		}
		
		if(!isEmpty(getDnis())){
		    if("<Unavailable>".equals(getDnis())){
		    	setErr_message("1");
		    	setPhone_number(null);
		    	setType(null);
		    	return SUCCESS;
		    }
		}
	    if(typeOfCall()==0){
        	setType("inbound");
        }else if(typeOfCall()==1){
        	if(!"outview".equals(getType())){
        		setType("out");
        	}
        	String tmp1;
        	String tmp2;
        	tmp1=getPhone_number();
        	tmp2=getDnis();
        	setPhone_number(tmp2);
        	setDnis(tmp1);
        }
	    //Дуудлага дууссан
		//boolean g=true;
	    
	    if("true".equals(getAnswered())){
	    	raiseFlag(Integer.parseInt(getAgentID()), 0);
		}
		
		if (!isEmpty(getCallEnd()))
		{
			/*if(!isEmpty(getType())){
				if(getType().equals("out"))
					g=false;
				else g=true;
			}else g=true;*/
			setCallEnding(getAgentID(), "true");
			if(getFlag(Integer.parseInt(getAgentID()))==1){
				//db.connect();
				StringBuffer where = new StringBuffer(QUERY_BEGIN);

		    		
		    		buildWQuery(where, " AND " + Constants.FIELD_PHONENUMBER + " = '?'", new String[] { getPhone_number() });
		    		hsts.put(Constants.QUERY_WHERE, where);

		    		db.connect();

		    		try {

		    			String sql = reader.findSQLByCommand(QueryConstants.SLCT_CALLER_DETAIL, hsts, true);

		    			ResultSet rs = db.select(sql);

		    			if (!rs.next())
		    			{
		    				hsts.put(Constants.FIELD_PHONENUMBER, getPhone_number());
		    				hsts.put(Constants.FIELD_AGENT_ID, getAgentID());
		    				sql = reader.findSQLByCommand(QueryConstants.SLCT_CALLER_DETAIL_INSERT, hsts, true);
		    				if (db.executeInsert(sql))
		    				{
		    					db.commit() ;
		    				}
		    				else
		    				{
		    					return ERROR;
		    				}
		    			}
		    		}

		    		catch (Exception e) {
		    			log("Баазтай холбогдох алдаа", e);
		    			return ERROR;
		    		} finally {
		    			db.disconnect();
		    		}

		    		db.connect();

		    		try {
		    				hsts.put(Constants.FIELD_PHONENUMBER, escStrQ(getPhone_number()));
		    				hsts.put(Constants.FIELD_AGENT_ID, escStrQ(getAgentID()));
		    				String sql = reader.findSQLByCommand(QueryConstants.SLCT_CALL_HISTORY_INSERT_MISS, hsts, true);
		    				log(sql);
		    				if (db.executeInsert(sql))
		    				{
		    						db.commit() ;
		    				}
		    				else
		    				{
		    					return ERROR;
		    				}
		   			}
		    		catch (Exception e) {
		    			log("Баазтай холбогдох алдаа", e);
		    			return ERROR;
		    		} finally {
		    			db.disconnect();
		    		}
				setType(null);
				setCallEnd(null);
				setCallEnding(getAgentID(), null);
				return SUCCESS;
			}
			if(getFlag(Integer.parseInt(getAgentID()))==2){
				setType("outview");
				return SUCCESS;
			}
			if(!"out".equals(getType())){
	    		db.connect();

	    		try {
	    				hsts.put(Constants.FIELD_PHONENUMBER, escStrQ(getPhone_number()));
	    				hsts.put(Constants.FIELD_AGENT_ID, escStrQ(getAgentID()));
						String sql = reader.findSQLByCommand(QueryConstants.SLCT_CALL_HISTORY_MAX, hsts, true);
						ResultSet rs = db.select(sql);

						if (rs.next())
						{
							setCallID(rs.getString(Constants.FIELD_CALL_HISTORY_ID)) ;
							hsts.put(Constants.FIELD_CALL_HISTORY_ID, escStrQ(getCallID()));

		    				sql = reader.findSQLByCommand(QueryConstants.SLCT_CALL_HISTORY_UPDATE, hsts, true);
		    				if (db.executeUpdate(sql))
		    				{
		    					db.commit() ;
		    				}
		    				else
		    				{
		    					return ERROR;
		    				}
						}
						else
						{
							return ERROR ;
						}
	   			}
	    		catch (Exception e) {
	    			log("Баазтай холбогдох алдаа", e);
	    			return ERROR;
	    		} finally {
	    			db.disconnect();
	    		}
			}
		}

		if (getCallID() != null)
		{
			StringBuffer where = new StringBuffer(QUERY_BEGIN);

			buildWQuery(where, " AND " + Constants.FIELD_CALL_HISTORY_ID + " = '?'", new String[] { getCallID() });

			hsts.put(Constants.QUERY_WHERE, where);

			db.connect();

			try {

				String sql = reader.findSQLByCommand(QueryConstants.SLCT_CALL_HISTORY, hsts, true);
				ResultSet rs = db.select(sql);

				if (rs.next())
				{
					setPhone_number(rs.getString(Constants.FIELD_PHONENUMBER)) ;
					setAgentID(rs.getString(Constants.FIELD_AGENT_ID)) ;
					setStart_time(rs.getString(Constants.FIELD_START_TIME)) ;
					setView_status(rs.getString(Constants.FIELD_VIEW_STATUS)) ;
					if(isEmpty(getType()))							
						setType("inbound");

					this.isExistingCall = true ;
				}
			}

			catch (Exception e) {
				log("Баазтай холбогдох алдаа", e);
				return ERROR;
			} finally {
				db.disconnect();
			}
		}
		return SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	public void validate() {
	}
	
	/**
	 * @return disabledTabs
	 */
	public String getDisabledTabs() {
 		String disabledTabs = "" ;
		String defaultDisabledTabs="";
		int permission = getPermission() ;
		log("permission  =  "+permission + "  isExistingCall =  "+isExistingCall()+"  getType =  "+getType());
		switch(permission)
		{
			//Агент
			case 0: disabledTabs = "2,3,6,7" ; break ;
			//Ахлах
			case 1: disabledTabs = "" ; break ;
			//Гомдол хариуцсан
			case 2: disabledTabs = "0,1,3,4,5,6,7" ; break ;
			//Тусламж хариуцсан
			case 3: disabledTabs = "0,1,2,3,4,5,6" ; break ;
		}
		defaultDisabledTabs=disabledTabs;
		//New Call Without Call END
		if(getPhone_number()!=null&&!isExistingCall()&&getCallEnd()==null){
			if("inbound".equals(getType())){
				disabledTabs = "1,2,3,4,5,6,7";
			}else if("out".equals(getType())){
				disabledTabs = "0,2,3,4,5,6,7";
			}else{
				disabledTabs = defaultDisabledTabs+",0";
			}
		}
		if(getPhone_number()!=null&&"true".equals(getCallEnd())){
			if(getFlag(Integer.parseInt(getAgentID()))==1){
				disabledTabs=defaultDisabledTabs+",0";
			}else if("inbound".equals(getType())){
				disabledTabs=defaultDisabledTabs+"";
			}else if("out".equals(getType())){
				disabledTabs=defaultDisabledTabs+",0";
			}else{
				disabledTabs=defaultDisabledTabs+",0";
			}
		}
		if(!isEmpty(getCallID())){
			if("outview".equals(getType())){
				disabledTabs=defaultDisabledTabs+",0";
			}else{
				disabledTabs=defaultDisabledTabs;
			}
		}
		if(getPhone_number()==null&&getType()==null){
			disabledTabs=defaultDisabledTabs+",0";
		}	
	//	System.out.println(" ^^^^ getType = "+getType()+"  getPhone_number = "+getPhone_number()+"  getCalltype = "+getCalltype()+"   getCallEnd = "+getCallEnd());
	//	System.out.println("disabledTabs  =  "+disabledTabs);
		return disabledTabs;
	}

	/**
	 * @return
	 */
	public String getDefaultTab(){
		String defaultTab = "" ;
		int permission = getPermission();
		switch(permission)
		{
			//Агент
			//Ахлах
			case 0:
				if(!isEmpty(getType())&&!isEmpty(getPhone_number())){
					if(getFlag(Integer.parseInt(getAgentID()))!=10){
						if(getFlag(Integer.parseInt(getAgentID()))==1){
							defaultTab="4";
						}else if("inbound".equals(getType())){
							defaultTab="0";
						}else if("out".equals(getType())||"outview".equals(getType())){
							defaultTab="1";
						}
					}else{
						if("inbound".equals(getType())){
							defaultTab="0";
						}else if("out".equals(getType())||"outview".equals(getType())){
							defaultTab="1";
						}
					}
					
				}else{
					defaultTab="4";
				}
				break;			
			case 1:
				if(!isEmpty(getType())&&!isEmpty(getPhone_number())){
					if(getFlag(Integer.parseInt(getAgentID()))!=10){
						if(getFlag(Integer.parseInt(getAgentID()))==1){
							defaultTab="4";
						}else if("inbound".equals(getType())){
							defaultTab="0";
						}else if("out".equals(getType())||"outview".equals(getType())){
							defaultTab="1";
						}
					}else{
						if("inbound".equals(getType())){
							defaultTab="0";
						}else if("out".equals(getType())||"outview".equals(getType())){
							defaultTab="1";
						}
					}
				}else{
					defaultTab="4";
				}
				break ;
			//Гомдол хариуцсан
			case 2: defaultTab = "2" ; break ;
			//Тусламж хариуцсан
			case 3: defaultTab = "7" ; break ;
			
		}
		
	//	System.out.println("defaultTab = "+defaultTab);
		return defaultTab;
	}

	/**
	 * @return
	 */
	private boolean isExistingCall()
	{
		return isExistingCall ;
	}

	/**
	 * @return agentID
	 */
	public String getAgentID() {
		if (agentID == null)
		{
			Map<String, Object> session = (Map<String, Object>)ActionContext.getContext().getSession();
			UserInfo info = (UserInfo)session.get(Constants.SESSION_USER_INFO) ;

			return  info.getAgentID() ;
		}
		else
		{
			return this.agentID ;
		}
	}

	/**
	 * @param agentID セットする agentID
	 */
	private void setAgentID(String agentID) {
		this.agentID = agentID;
	}

	/**
	 * @return phone_number
	 */
	public String getPhone_number() {
		return phone_number;
	}

	/**
	 * @param phone_number セットする phone_number
	 */
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	/**
	 * @return callID
	 */
	public String getCallID() {
		return callID;
	}

	/**
	 * @param callID セットする callID
	 */
	public void setCallID(String callID) {
		this.callID = callID;
	}

	/**
	 * @return start_time
	 */
	public String getStart_time() {
		return start_time;
	}

	/**
	 * @param start_time セットする start_time
	 */
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	/**
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type セットする type
	 */
	public void setType(String type) {
		this.type = type;
	}


	/**
	 * @return view_status
	 */
	public String getView_status() {
		return view_status;
	}

	/**
	 * @param view_status セットする view_status
	 */
	private void setView_status(String view_status) {
		this.view_status = view_status;
	}

	/**
	 * @return callEnd
	 */
	public String getCallEnd() {
		return callEnd;
	}

	/**
	 * @param callEnd セットする callEnd
	 */
	public void setCallEnd(String callEnd) {
		this.callEnd = callEnd;
	}

	public String getCalltype() {
		return calltype;
	}

	public void setCalltype(String calltype) {
		this.calltype = calltype;
	}

	public String getDnis() {
		return dnis;
	}

	public void setDnis(String dnis) {
		this.dnis = dnis;
	}

	public Integer typeOfCall(){
		if(!isEmpty(dnis)){
			if(dnis.equals(getAgentID())){
				//Inbound
				return 0;
			}
		}
		if(!isEmpty(phone_number)){
			if(phone_number.equals(getAgentID())){
				//Outbound
				return 1;
			}
		}
		//Other
		return 2;
	}

	public String getOwnAgentId() {
		return ownAgentId;
	}

	public void setOwnAgentId(String ownAgentId) {
		this.ownAgentId = ownAgentId;
	}
	public String getErr_message() {
		return err_message;
	}

	public void setErr_message(String err_message) {
		this.err_message = err_message;
	}

	public String getCalling() {
		return Calling;
	}

	public void setCalling(String calling) {
		Calling = calling;
	}

	public String getAnswered() {
		return Answered;
	}

	public void setAnswered(String answered) {
		Answered = answered;
	}

	public String getCalled() {
		return Called;
	}

	public void setCalled(String called) {
		Called = called;
	}



	

	
}
