/*
 * Моделийн нэр             CallReqType.java
 *
 * Функцын нэр          【Дуудлагын форм】
 * Тvvх
 * Хувилбарын №     			Огноо        Хариуцагч         Агуулга
 * 					    01.00.00       2013/3/20   			Ж.Мөнгөнсүх    Шинээр хийсэн
 */
package org.golomt.req;


import infox.org.db.DBAccess;
import infox.org.sql.SQLListReader;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.golomt.bean.UserInfo;
import org.golomt.common.Constants;
import org.golomt.common.QueryConstants;
import org.golomt.common.XMLForm;

import com.opensymphony.xwork2.ActionContext;


/**
* @author
*
*/
/**
 * @author infox
 *
 */
public class CallReqType extends XMLForm {
    /** serialVersionUID */
    private static final long serialVersionUID = 0L;

	/** Хайлтын үр дүнг харуулах талбарууд */
	private static final String[] FIELD_LIST = { "CALL_HISTORY_ID", "PHONENUMBER", "CALLERNAME", "START_TIME", "FINISHED_TIME", "DURATION", "CALLTYPE", "AGENTNAME", "PHONENUMBER2", "VIEW_STATUS" };

	/** Хайлтын үр дүнг харуулах талбарууд */
	private static final String[] FIELD_LIST_SUB = { "CALL_REQ_TYPE_NAME", "SERVICE_NAME1", "DETAILED_SERVICE_NAME1", "CALL_COMMENT", "CALL_NAME",
		"CALL_RESULT_NAME", "CALL_RATE_NAME", "CALL_STAT_NAME", "CALL_SORT_NAME", "DEADLINE", "UNIT_NAME",
		"SERVICE_NAME2", "DETAILED_SERVICE_NAME2", "SERVICE_NAME3", "DETAILED_SERVICE_NAME3",
		"SERVICE_NAME4", "DETAILED_SERVICE_NAME4", "SERVICE_NAME5", "DETAILED_SERVICE_NAME5", "TRANSFER_PHONENUMBER" };

	/** Хоосон зай */
	private static final String SPACE = " ";

	/** хоосон хайлт */
	private static final String QUERY_BEGIN = " 1 = 1 ";

	/** Эрэмбэлэх талбарын */
	private static final String ORDER_BY = " ORDER BY ";

	/** Өгсөх эрэмбэлэлт */
	private static final String ASC = "asc";

	/** Өгсөх эрэмбэлэлт */
	private static final String DESC = "desc";

    /** Дуудлагын төрөл */
    private String lblStaticCall_req_type_id;

    /** Дуудлагын төрөл */
    private String lblCall_req_type_id;

	/** Дуудлагын дугаар */
	private String callID ;

	/** Агентийн дугаар */
	private String agentID ;

	/** */
	private String phone_number;

	/** */
	private String phone_number2;

	/** */
	private String start_time ;

	private String row_Id;

	/** */
	private String finished_time ;

	/** */
	private String duration ;

	/** */
	private String has_ref ;

	/** */
	private String has_info ;

	/** */
	private String has_app ;

	/** */
	private String has_comp ;

	/** */
	private String has_tra ;

	/** */
	private String has_other ;

	/** */
	private String caller_name ;

	/** */
	private String type ;

	/** */
	private boolean isExistingCall ;

	/** */
	private String view_status ;


	/** Эрэмбэлэлтийн өсөх/буурах эсэхийг заагч талбарууд */
	private String sord;

	/** Эрэмбэлэлтийг заагч талбар */
	private String sidx;

	/** Хайлтыг харуулах хуудасны дугаар */
	private int page;

	private String searchClick;

	private String fields ;

	private LinkedHashMap<String,Object> callInfoList;
	
	private String call_result_idg;
	
	private String a_Result_mig;
	
    /* (non-Javadoc)
     * @see com.opensymphony.xwork2.ActionSupport#execute()
     */
    public String execute()
    {
		DBAccess db = getDBAccess();
		SQLListReader reader = getSQLListReader();

		StringBuffer where = new StringBuffer(QUERY_BEGIN);
		HashMap<String, Object> hsts = new HashMap<String, Object>();

    	if (!isEmpty(getPhone_number()))
    	{
    		
    		buildWQuery(where, " AND " + Constants.FIELD_PHONENUMBER + " = '?'", new String[] { getPhone_number() });
    		hsts.put(Constants.QUERY_WHERE, where);

    		db.connect();

    		try {

    			String sql = reader.findSQLByCommand(QueryConstants.SLCT_CALLER_DETAIL, hsts, true);
    			log(sql);
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

    	}

    	boolean isCreated = false ;

    	if (getCallID() == null || getCallID().equals(""))
    	{
    		db.connect();

    		try {
    				hsts.put(Constants.FIELD_PHONENUMBER, escStrQ(getPhone_number()));
    				hsts.put(Constants.FIELD_AGENT_ID, escStrQ(getAgentID()));
    				String sql = reader.findSQLByCommand(QueryConstants.SLCT_CALL_HISTORY_INSERT, hsts, true);
    				if (db.executeInsert(sql))
    				{
    					sql = reader.findSQLByCommand(QueryConstants.SLCT_CALL_HISTORY_MAX, hsts, true);
    					ResultSet rs = db.select(sql);

    					if (rs.next())
    					{
    						setCallID(rs.getString(Constants.FIELD_CALL_HISTORY_ID)) ;
    						isCreated = true ;
    						db.commit() ;
    					}
    					else
    					{
    						db.rollback() ;
    						return ERROR ;
    					}
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
    	}

		where = new StringBuffer(QUERY_BEGIN);

		buildWQuery(where, " AND " + Constants.FIELD_CALL_HISTORY_ID + " = '?'", new String[] { getCallID() });

		hsts.put(Constants.QUERY_WHERE, where);

		db.connect();

		try {
			reader = getSQLListReader();

			String sql = reader.findSQLByCommand(QueryConstants.SLCT_CALL_HISTORY, hsts, true);
			log(sql);
			ResultSet rs = db.select(sql);

			if (rs.next())
			{
				setPhone_number(rs.getString(Constants.FIELD_PHONENUMBER)) ;
				setPhone_number2(rs.getString(Constants.FIELD_PHONENUMBER2)) ;
				setCaller_name(rs.getString(Constants.FIELD_CALLERNAME)) ;
				setAgentID(rs.getString(Constants.FIELD_AGENT_ID)) ;
				setStart_time(rs.getString(Constants.FIELD_START_TIME)) ;
				setFinished_time(rs.getString(Constants.FIELD_FINISHED_TIME)) ;
				setDuration(rs.getString(Constants.FIELD_DURATION)) ;
				setHas_ref(rs.getString(Constants.FIELD_HAS_REF)) ;
				setHas_info(rs.getString(Constants.FIELD_HAS_INFO)) ;
				setHas_app(rs.getString(Constants.FIELD_HAS_APP)) ;
				setHas_comp(rs.getString(Constants.FIELD_HAS_COMP)) ;
				setHas_tra(rs.getString(Constants.FIELD_HAS_TRA)) ;
				setHas_other(rs.getString(Constants.FIELD_HAS_OTHER)) ;
				
				setView_status(rs.getString(Constants.FIELD_VIEW_STATUS)) ;

				this.isExistingCall = !isCreated ;
			}
			else
			{
				where = new StringBuffer(QUERY_BEGIN);
			
				buildWQuery(where, " AND " + Constants.FIELD_PHONENUMBER + " = '?'", new String[] { getPhone_number() });
				
				

				hsts = new HashMap<String, Object>();
				hsts.put(Constants.QUERY_WHERE, where);

				sql = reader.findSQLByCommand(QueryConstants.SLCT_CALLER_DETAIL, hsts, true);
				log("test"+sql);
				rs = db.select(sql);

				if (rs.next())
				{
					setPhone_number(rs.getString(Constants.FIELD_PHONENUMBER)) ;
					setPhone_number2(rs.getString(Constants.FIELD_PHONENUMBER2)) ;
					setCaller_name(rs.getString(Constants.FIELD_FIRSTNAME)) ;
				}


				if (getStart_time() == null || getStart_time().equals(""))
				{
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					setStart_time(sdf1.format(new Date())) ;
				}
			}
		}

		catch (Exception e) {
			log("Баазтай холбогдох алдаа", e);
			return ERROR;
		} finally {
			db.disconnect();
		}

		return SUCCESS;
    }

    /* (non-Javadoc)
     * @see com.opensymphony.xwork2.ActionSupport#validate()
     */
    public void validate()
    {
    }

    /**
     * Хэрэглэгчийн 2 дахь утас болон Хэрэглэгчийн нэр өөрчилөгдсөн үед нэмэх<br>
     */
    public String saveUserOnChange()
    {
		DBAccess db = getDBAccess();
		HashMap<String, Object> hsts = new HashMap<String, Object>();
		StringBuffer sets = new StringBuffer();

    	db.connect();
		try {
			SQLListReader reader = getSQLListReader();

			String fields = getFields() ;

			boolean phone2 = false ;

			if (!isEmpty(fields))
			{
				String[] fieldsar = fields.split(",") ;

				for(int i=0;i<fieldsar.length;i++)
				{
					if (fieldsar[i].equals("phone_number2"))
					{
						buildWQuery(sets, Constants.FIELD_PHONENUMBER2 + " = '?'", new String[] { getPhone_number2() }) ;
						phone2 = true ;
					}
					else if (fieldsar[i].equals("caller_name"))
					{
						buildWQuery(sets, Constants.FIELD_FIRSTNAME + " = '?'", new String[] { getCaller_name() });
					}
				}

				hsts.put("SET", sets);
				hsts.put(Constants.FIELD_PHONENUMBER, "'" + getPhone_number() + "'");
				String sql = reader.findSQLByCommand(QueryConstants.SLCT_CALLER_DETAIL_UPDATE, hsts, true);
				log(sql);
				if (db.executeUpdate(sql))
				{
					db.commit() ;
				}

				//2Дахь дугаарыг хайж олно
				StringBuffer where = new StringBuffer(QUERY_BEGIN);
				hsts.clear() ;

				if (phone2)
				{
					
					
					hsts.put("PHONENUMBER2", escStrQNull(getPhone_number2()));
					hsts.put("CALL_HISTORY_ID", escStrQNull(getCallID()));
					sql = reader.findSQLByCommand(QueryConstants.UPDATE_CALL_HISTORY_PHONE2, hsts, true);
					log(sql);
					if (db.executeUpdate(sql))
					{
						db.commit() ;
					}
					hsts.clear();
					buildWQuery(where, " AND " + Constants.FIELD_PHONENUMBER + " = '?'", new String[] { getPhone_number2() });
		    		hsts.put(Constants.QUERY_WHERE, where);

		    		sql = reader.findSQLByCommand(QueryConstants.SLCT_CALLER_DETAIL, hsts, true);
		    		log("MMMMMMMM AAAAAAA _>>>_>_>_>_>"+sql);
		    		ResultSet rs = db.select(sql);

	    			if (!rs.next())
	    			{
	    				hsts.put(Constants.FIELD_PHONENUMBER, getPhone_number2());
	    				hsts.put(Constants.FIELD_PHONENUMBER2, getPhone_number());
	    				hsts.put(Constants.FIELD_AGENT_ID, getAgentID());
	    				
	    				sql = reader.findSQLByCommand(QueryConstants.SLCT_CALLER_DETAIL_INSERT2, hsts, true);
	    				log("GGGGGG NNNNNN _>>>_>_>_>_>"+sql);
	    				if (db.executeInsert(sql))
	    				{
	    					db.commit() ;
	    				}
	    				setCaller_name("") ;
	    			}
	    			else
	    			{
	    				setCaller_name(rs.getString(Constants.FIELD_FIRSTNAME)) ;
	    			}
    				return "change_user" ;
				}
				else
				{
					buildWQuery(where, " AND " + Constants.FIELD_PHONENUMBER + " = '?'", new String[] { getPhone_number() });
		    		hsts.put(Constants.QUERY_WHERE, where);

		    		sql = reader.findSQLByCommand(QueryConstants.SLCT_CALLER_DETAIL, hsts, true);

		    		ResultSet rs = db.select(sql);

	    			if (rs.next() && !isEmpty(rs.getString(Constants.FIELD_PHONENUMBER2)))
	    			{
	    				sets.setLength(0) ;

	    				buildWQuery(sets, Constants.FIELD_FIRSTNAME + " = '?'", new String[] { getCaller_name() });
	    				hsts.put("SET", sets);
	    				hsts.put(Constants.FIELD_PHONENUMBER, escStrQ(rs.getString(Constants.FIELD_PHONENUMBER2)));

	    				sql = reader.findSQLByCommand(QueryConstants.SLCT_CALLER_DETAIL_UPDATE, hsts, true);
	    				if (db.executeUpdate(sql))
	    				{
	    					db.commit() ;
	    				}
	    			}
				}
			}
		}

		catch (Exception e) {
			log("Баазтай холбогдох алдаа", e);
			return ERROR ;
		} finally {
			db.disconnect();
		}

		return SUCCESS ;
    }


	/**
	 * Тухайн хайлтын хүснэгтийн XML бүтцийг бэлдэх
	 *
	 * @param sqlQuery
	 *            XML болгож харуулах квери
	 * @param hsts
	 *            квери руу дамжигдах параметрууд
	 */

	public String getXML() {
		StringBuffer where = new StringBuffer(QUERY_BEGIN);
		StringBuffer paging = new StringBuffer(QUERY_BEGIN);

		HashMap<String, Object> hsts = new HashMap<String, Object>();
		DBAccess db = getDBAccess();

		buildWQuery(where, " AND " + Constants.FIELD_CALL_HISTORY_ID + " = '?'", new String[] { getCallID() });

		hsts.put(Constants.QUERY_WHERE, where);

		db.connect();

		try {
			SQLListReader reader = getSQLListReader();

			String sql = reader.findSQLByCommand(QueryConstants.SLCT_CALL_HISTORY, hsts, true);

			ResultSet rs = db.select(sql);

			if (rs.next())
			{
				setPhone_number(rs.getString(Constants.FIELD_PHONENUMBER)) ;
			}

			StringBuffer ord = new StringBuffer();

			where = new StringBuffer(QUERY_BEGIN);
			buildWQuery(where, " AND " + Constants.FIELD_PHONENUMBER + " = '?'", new String[] { getPhone_number() });
			if (page < 1) {
				page = 1;
			}

			/**
			 * Хайлтын үр дүнг эрэмбэлэх талбар,(өсөхөөр/буурахаар) хэсгийг хайлтын
			 * нөхцөлд залгах хэсэг
			 */
			if (!isEmpty(sidx) && !isEmpty(sord)) {
				int i = 0;
				while (i < FIELD_LIST.length && !FIELD_LIST[i].equals(sidx))
					i++;
				if (i < FIELD_LIST.length && (ASC.equals(sord) || DESC.equals(sord))) {
					ord.append(ORDER_BY);
					ord.append(sidx);
					ord.append(SPACE);
					ord.append(sord);
				}
			}

			hsts.put(Constants.QUERY_WHERE, where);
			hsts.put(Constants.QUERY_ORDER, ord);


			sql = reader.findSQLByCommand(QueryConstants.SLCT_CALL_HISTORY_CNT, hsts, true);

			rs = db.select(sql);
			log(sql);
			int totalResult = 0;
			if (rs.next()) {
				totalResult = rs.getInt("CNT");
			}

			paging.append(" AND RN >");
			paging.append((page - 1) * 12);
			paging.append(" AND RN <=");
			paging.append(page * 12);

			hsts.put(Constants.QUERY_WHERE, where);
			hsts.put(Constants.QUERY_PAGING, paging);

			sql = reader.findSQLByCommand(QueryConstants.SLCT_CALL_HISTORY_PAGING, hsts, true);
			log(sql);
			rs = db.select(sql);
			xmlOutput(rs, page, ((totalResult - 1) / 12 + 1), totalResult, FIELD_LIST);

			return XML;
		}

		catch (Exception e) {
			log("Баазтай холбогдох алдаа", e);
			return ERROR;
		} finally {
			db.disconnect();
		}
	}

	/**
	 * Тухайн хайлтын хүснэгтийн XML бүтцийг бэлдэх
	 *
	 * @param sqlQuery
	 *            XML болгож харуулах квери
	 * @param hsts
	 *            квери руу дамжигдах параметрууд
	 */

	public String getXMLSub() {
		StringBuffer where = new StringBuffer(QUERY_BEGIN);
		StringBuffer ord = new StringBuffer();

		buildWQuery(where, " AND " + Constants.FIELD_CALL_HISTORY_ID + " = '?'", new String[] { getCallID() });

		/**
		 * Хайлтын үр дүнг эрэмбэлэх талбар,(өсөхөөр/буурахаар) хэсгийг хайлтын
		 * нөхцөлд залгах хэсэг
		 */
		if (!isEmpty(sidx) && !isEmpty(sord)) {
			int i = 0;
			while (i < FIELD_LIST_SUB.length && !FIELD_LIST_SUB[i].equals(sidx))
				i++;
			if (i < FIELD_LIST_SUB.length && (ASC.equals(sord) || DESC.equals(sord))) {
				ord.append(ORDER_BY);
				ord.append(sidx);
				ord.append(SPACE);
				ord.append(sord);
			}
		}

		if (!isEmpty(searchClick)) {
			HashMap<String, Object> hsts = new HashMap<String, Object>();
			hsts.put(Constants.QUERY_WHERE, where);
			hsts.put(Constants.QUERY_ORDER, ord);
			hsts.put("SYSTEMRATE", escStrQNull(getText("system.rateselect")));
			DBAccess db = getDBAccess();
			db.connect();

			try {
				SQLListReader reader = getSQLListReader();

				String sql = reader.findSQLByCommand(QueryConstants.SLCT_CALL_HISTORY_SERVICE_CNT, hsts, true);

				ResultSet rs = db.select(sql);
				log(sql);
				int totalResult = 0;
				if (rs.next()) {
					totalResult = rs.getInt("CNT");
				}

				sql = reader.findSQLByCommand(QueryConstants.SLCT_CALL_HISTORY_SERVICE, hsts, true);

				log(sql) ;
				rs = db.select(sql);
				xmlOutput(rs, 0, totalResult, totalResult, FIELD_LIST_SUB);

				return XML;
			}

			catch (Exception e) {
				log("Баазтай холбогдох алдаа", e);
				return ERROR;
			} finally {
				db.disconnect();
			}
		} else {
			return XML;
		}
	}
	
	public String getCallerDetails(){
		log(getPhone_number2());
		StringBuffer where = new StringBuffer(QUERY_BEGIN);
		buildWQuery(where, "AND " + Constants.CALL_PHONENUMBER + " LIKE '?%'", getPhone_number2());
		buildWQuery(where, "AND " + "FIRSTNAME IS NOT NULL " , getPhone_number());
		callInfoList=new LinkedHashMap<String, Object>();
		HashMap<String, Object> hsts=new HashMap<String, Object>();
		DBAccess db = getDBAccess();
		try{
			db.connect();
			hsts.put(Constants.QUERY_WHERE, where);
			SQLListReader reader = getSQLListReader();
			String sql = reader.findSQLByCommand(QueryConstants.SLCT_CALLER_DETAILS_ON_CHANGE, hsts, true);
			log(sql);
			ResultSet rs=db.select(sql);
			int s=0;
			while(rs.next()){
				callInfoList.put(Integer.toString(s), rs.getString("FIRSTNAME"));
				s+=1;
			}
			return "json";
		}catch(Exception e){
			log("Баазтай холбогдох алдаа", e);
			return ERROR;
		}finally{
			db.disconnect();
		}
	}
	

    /**
     * @return
     */
    public String getLblStaticCall_req_type_id()
    {
       return lblStaticCall_req_type_id;
    }

    /**
     * @param lblStaticCall_req_type_id     */
    public void setLblStaticCall_req_type_id(String lblStaticCall_req_type_id)
    {
       this.lblStaticCall_req_type_id= lblStaticCall_req_type_id;
    }

    /**
     * @return
     */
    public String getLblCall_req_type_id()
    {
       return lblCall_req_type_id;
    }

    /**
     * @param lblCall_req_type_id     */
    public void setLblCall_req_type_id(String lblCall_req_type_id)
    {
       this.lblCall_req_type_id= lblCall_req_type_id;
    }


	/**
	 * @return
	 */
	private boolean isExistingCall()
	{
		return isExistingCall ;
	}

	/**
	 * @return
	 */
	public String getFinishCall()
	{
		return String.valueOf(isExistingCall) ;
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
	 * @return phone_number2
	 */
	public String getPhone_number2() {
		return phone_number2;
	}

	/**
	 * @param phone_number2 セットする phone_number2
	 */
	public void setPhone_number2(String phone_number2) {
		this.phone_number2 = phone_number2;
	}

	/**
	 * @return finished_time
	 */
	public String getFinished_time() {
		return finished_time;
	}

	/**
	 * @param finished_time セットする finished_time
	 */
	public void setFinished_time(String finished_time) {
		this.finished_time = finished_time;
	}

	/**
	 * @return
	 */
	public String getDuration_time() {
		return duration;
	}

	/**
	 * @return
	 */
	private void setDuration(String duration) {
		this.duration = duration ;
	}



	/**
	 * @return caller_name
	 */
	public String getCaller_name() {
		return caller_name;
	}

	/**
	 * @param caller_name セットする caller_name
	 */
	public void setCaller_name(String caller_name) {
		this.caller_name = caller_name;
	}

	/**
	 * @param searchClick
	 */
	public void setSearchClick(String searchClick) {
		this.searchClick = searchClick;
	}

	/**
	 * @return
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return
	 */
	public String getSidx() {
		return sidx;
	}

	/**
	 * @param sidx
	 */
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	/**
	 * @return
	 */
	public String getSord() {
		return sord;
	}

	/**
	 * @param sord
	 */
	public void setSord(String sord) {
		this.sord = sord;
	}

	/**
	 * @return status
	 */
	public String getStatus() {
		/*System.out.println("isExistingCall() = >  "+isExistingCall()+"  getView_status()  = >  "+getView_status());*/
		log("result return   = >  "+ (isExistingCall() && "0".equals(getView_status()) ? "true" : null));
		return isExistingCall() && "0".equals(getView_status()) ? "true" : null ;
	}

	public String getRow_Id() {
		return row_Id;
	}

	public void setRow_Id(String row_Id) {
		this.row_Id = row_Id;
	}

	/**
	 * @return has_ref
	 */
	public String getHas_ref() {
		return !isExistingCall() || "1".equals(getView_status())?"1": has_ref;
	}

	/**
	 * @return has_info
	 */
	public String getHas_info() {
		return !isExistingCall() || "1".equals(getView_status()) ?"1": has_info;
	}

	/**
	 * @return has_app
	 */
	public String getHas_app() {
		return !isExistingCall() || "1".equals(getView_status())?"1": has_app;
	}

	/**
	 * @return has_comp
	 */
	public String getHas_comp() {
		return !isExistingCall() || "1".equals(getView_status())?"1": has_comp;
	}

	/**
	 * @return has_tra
	 */
	public String getHas_tra() {
		return !isExistingCall() || "1".equals(getView_status())?"1": has_tra;
	}

	/**
	 * @return has_other
	 */
	public String getHas_other() {
		return !isExistingCall() || "1".equals(getView_status())?"1": has_other;
	}

	/**
	 * @param has_ref セットする has_ref
	 */
	private void setHas_ref(String has_ref) {
		this.has_ref = has_ref;
	}

	/**
	 * @param has_info セットする has_info
	 */
	private void setHas_info(String has_info) {
		this.has_info = has_info;
	}

	/**
	 * @param has_app セットする has_app
	 */
	private void setHas_app(String has_app) {
		this.has_app = has_app;
	}

	/**
	 * @param has_comp セットする has_comp
	 */
	private void setHas_comp(String has_comp) {
		this.has_comp = has_comp;
	}

	/**
	 * @param has_tra セットする has_tra
	 */
	private void setHas_tra(String has_tra) {
		this.has_tra = has_tra;
	}

	/**
	 * @param has_other セットする has_other
	 */
	private void setHas_other(String has_other) {
		this.has_other = has_other;
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
	 * @return fields
	 */
	public String getFields() {
		return fields;
	}

	/**
	 * @param fields セットする fields
	 */
	public void setFields(String fields) {
		this.fields = fields;
	}

	public LinkedHashMap<String, Object> getCallInfoList() {
		return callInfoList;
	}

	public void setCallInfoList(LinkedHashMap<String, Object> callInfoList) {
		this.callInfoList = callInfoList;
	}
	public String findMig(){
		if (!isEmpty(getCall_result_idg()))
		{
			HashMap<String, Object> hsts = new HashMap<String, Object>();

			hsts.put(Constants.FIELD_CALL_RESULT_ID, getCall_result_idg()) ;

			DBAccess db = getDBAccess();
			db.connect();

			try {
				SQLListReader reader = getSQLListReader();

				String sql = reader.findSQLByCommand(QueryConstants.SLCT_CALL_RESULT_INFO, hsts, true);

				log(sql) ;
				ResultSet rs = db.select(sql) ;

				if(rs.next())
				{
					setA_Result_mig(rs.getString("MIGRATION_NUM"));
					return "json" ;
				}
				else
				{
					return ERROR ;
				}

			}
			catch (Exception e)
			{
				log("Өгөгдлийн сан руу хандах үед алдаа гарлаа", e);
				return ERROR ;
			}
			finally
			{
				db.disconnect() ;
			}
		}
		else
		{
			return ERROR ;
		}
	}

	public String getCall_result_idg() {
		return call_result_idg;
	}

	public void setCall_result_idg(String call_result_idg) {
		this.call_result_idg = call_result_idg;
	}

	public String getA_Result_mig() {
		return a_Result_mig;
	}

	public void setA_Result_mig(String result_mig) {
		a_Result_mig = result_mig;
	}
	
}
