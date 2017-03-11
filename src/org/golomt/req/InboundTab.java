/*
 * Моделийн нэр             InboundReference.java
 *
 * Функцын нэр          【Лавлагаа форм】
 * Тvvх
 * Хувилбарын №     			Огноо        Хариуцагч         Агуулга
 * 					    01.00.00       2013/3/20   			Ж.Мөнгөнсүх    Шинээр хийсэн
 */
package org.golomt.req;

import infox.org.db.DBAccess;
import infox.org.sql.SQLListReader;

import java.sql.ResultSet;
import java.util.HashMap;

import org.golomt.common.Constants;
import org.golomt.common.QueryConstants;
import org.golomt.common.XMLForm;

/**
 * @author
 *
 */
public class InboundTab extends XMLForm
{
    public String getUnit_name() {
		return unit_name;
	}

	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}

	/** serialVersionUID */
    private static final long serialVersionUID = 0L;

	/** Дуудлага шилжүүлэх дугаар */
	private String transfer_phonenumber ;
	/** Холбоотой нэгж */
	private String unit_id;
	/** Холбоотой нэгж */
	private String unit_name;
	/** Дуудлагын үр дүн */
	private String call_result_id ;
	/** Дуудлагын тэмдэглэл */
	private String call_comment;
	/** Дуудлагын нэр */
	private String call_name ;

	/** Дуудлагын нэр */
	private String call_rate_id ;
	/** Дуудлагын Үнэлгээ */
	private String call_sort_id ;
	/** Дуудлагын Үнэлгээ */

	/** Дуусах хугацаа */
	private String deadline;

	/** */
	private String transfer_check ;

	/** үйлчилгээ */
	private String service_type1;

	/** үйлчилгээний нарийвчилсан төрөл */
	private String detailed_service_type1;

	/** үйлчилгээ */
	private String service_type2;

	/** үйлчилгээний нарийвчилсан төрөл */
	private String detailed_service_type2;

	/** үйлчилгээ */
	private String service_type3;

	/** үйлчилгээний нарийвчилсан төрөл */
	private String detailed_service_type3;

	/** үйлчилгээ */
	private String service_type4;

	/** үйлчилгээний нарийвчилсан төрөл */
	private String detailed_service_type4;

	/** үйлчилгээ */
	private String service_type5;

	/** үйлчилгээний нарийвчилсан төрөл */
	private String detailed_service_type5;

	private boolean isExistingCall ;

	private String fields ;
	
	
	/*
	 * (non-Javadoc)
	 *
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() {
		StringBuffer where = new StringBuffer(QUERY_BEGIN);

		buildWQuery(where, " AND " + Constants.FIELD_CALL_HISTORY_ID + " = '?'", new String[] { getCallID() });
		buildWQuery(where, " AND " + Constants.FIELD_CALL_REQ_TYPE + " = '?'", new String[] { getReqType() });

		HashMap<String, Object> hsts = new HashMap<String, Object>();
		hsts.put(Constants.QUERY_WHERE, where);
		hsts.put("SYSTEMRATE", escStrQNull(getText("system.rateselect")));
		DBAccess db = getDBAccess();
		db.connect();
		
		try {
			SQLListReader reader = getSQLListReader();
			
			String sql = reader.findSQLByCommand(QueryConstants.SLCT_CALL_HISTORY_SERVICE, hsts, true);
			log(sql);
			ResultSet rs = db.select(sql);
			
			if (rs.next())
			{
				this.setCall_name(rs.getString(Constants.FIELD_CALL_NAME)) ;
				this.setCall_result_id(rs.getString(Constants.FIELD_CALL_RESULT_ID)) ;
				this.setCall_rate_id(rs.getString(Constants.FIELD_CALL_RATE_ID)) ;
				this.setCall_sort_id(rs.getString(Constants.FIELD_CALL_SORT_ID)) ;
				this.setUnit_id(rs.getString(Constants.FIELD_UNIT_ID)) ;
				this.setUnit_name(rs.getString(Constants.FIELD_UNIT_NAME)) ;
				this.setTransfer_check(String.valueOf(rs.getInt(Constants.FIELD_TRANSFER_CHECK) == 1)) ;
				this.setTransfer_phonenumber(rs.getString(Constants.FIELD_TRANSFER_PHONENUMBER)) ;

				this.setDeadline(rs.getString(Constants.FIELD_DEADLINE)) ;
				this.setCall_comment(rs.getString(Constants.FIELD_CALL_COMMENT)) ;

				this.setService_type1(rs.getString(Constants.FIELD_SERVICE_TYPE1)) ;
				this.setDetailed_service_type1(rs.getString(Constants.FIELD_DETAILED_SERVICE_TYPE1)) ;

				this.setService_type2(rs.getString(Constants.FIELD_SERVICE_TYPE2)) ;
				this.setDetailed_service_type2(rs.getString(Constants.FIELD_DETAILED_SERVICE_TYPE2)) ;

				this.setService_type3(rs.getString(Constants.FIELD_SERVICE_TYPE3)) ;
				this.setDetailed_service_type3(rs.getString(Constants.FIELD_DETAILED_SERVICE_TYPE3)) ;

				this.setService_type4(rs.getString(Constants.FIELD_SERVICE_TYPE4)) ;
				this.setDetailed_service_type4(rs.getString(Constants.FIELD_DETAILED_SERVICE_TYPE4)) ;

				this.setService_type5(rs.getString(Constants.FIELD_SERVICE_TYPE5)) ;
				this.setDetailed_service_type5(rs.getString(Constants.FIELD_DETAILED_SERVICE_TYPE5)) ;

				this.setExistingCall(rs.getInt(Constants.FIELD_VIEW_STATUS) == 0) ;
				
			}
			saveTaskAndComplaint(true);

		}

		catch (Exception e) {
			log("Баазтай холбогдох алдаа", e);
			return ERROR;
		} finally {
			db.disconnect();
		}
		/*if(!isEmpty(getCallEnding(getAgentID()))){
			log("Call End is nigh:!=-------------=>"+getCallEnding(getAgentID()));
			if("true".equals(getCallEnding(getAgentID()))){
				if("1".equals(mig)){
					saveTaskAndComplaint1(true);
				}else if("2".equals(mig)){
					saveToComplaintCallEnd();
				}else if("3".equals(mig)){
					saveComplaintEnding(true);
				}
				
				
				setCallEnding(getAgentID(), null);
			}
		}*/
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
	 * @return
	 */
	protected String getReqType()
	{
		return null;
	}

    /**
     * Лавлагаа мэдээлэл өөрчилөгдсөн үед нэмэх<br>
     */
    public String saveOnChange()
    {
    	String result = SUCCESS ;
		DBAccess db = getDBAccess();
		SQLListReader reader = getSQLListReader();

		HashMap<String, Object> hsts = new HashMap<String, Object>();
		StringBuffer sets = new StringBuffer();

    	db.connect();

		try {

			StringBuffer where = new StringBuffer(QUERY_BEGIN);

			buildWQuery(where, " AND " + Constants.FIELD_CALL_HISTORY_ID + " = '?'", new String[] { getCallID() });
			buildWQuery(where, " AND " + Constants.FIELD_CALL_REQ_TYPE + " = '?'", new String[] { getReqType() });

	    	hsts.put(Constants.QUERY_WHERE, where);

			String sql = reader.findSQLByCommand(QueryConstants.SLCT_CALL_HISTORY_SERVICE_CNT, hsts, true);

			ResultSet rs = db.select(sql);

			if (!rs.next() || rs.getInt(1) == 0)
			{
				hsts.put(Constants.FIELD_CALL_HISTORY_ID, "'" + getCallID() + "'");
				hsts.put(Constants.FIELD_AGENT_ID, "'" + getAgentID() + "'");
				hsts.put(Constants.FIELD_CALL_REQ_TYPE, "'" + getReqType() + "'");


				sql = reader.findSQLByCommand(QueryConstants.SLCT_CALL_SERVICE_INSERT, hsts, true);

				if (!db.executeInsert(sql))
				{
					log("error", db.getLastException()) ;
					return ERROR ;
				}
				else
				{
					db.commit() ;
				}
			}


			String fields = getFields() ;

			if (!isEmpty(fields))
			{
				String[] fieldsar = fields.split(",") ;

				for(int i=0;i<fieldsar.length;i++)
				{
					StringBuffer subset = new StringBuffer();

					if (fieldsar[i].equals("service_type1"))
					{
						buildWQuery(subset, Constants.FIELD_SERVICE_TYPE1 + " = '?'", new String[] { getService_type1() }, true) ;
					}
					else if (fieldsar[i].equals("detailed_service_type1"))
					{
						buildWQuery(subset, Constants.FIELD_DETAILED_SERVICE_TYPE1 + " = '?'", new String[] { getDetailed_service_type1() }, true);
					}
					else if (fieldsar[i].equals("service_type2"))
					{
						buildWQuery(subset, Constants.FIELD_SERVICE_TYPE2 + " = '?'", new String[] { getService_type2() }, true) ;
					}
					else if (fieldsar[i].equals("detailed_service_type2"))
					{
						buildWQuery(subset, Constants.FIELD_DETAILED_SERVICE_TYPE2 + " = '?'", new String[] { getDetailed_service_type2() }, true);
					}
					else if (fieldsar[i].equals("service_type3"))
					{
						buildWQuery(subset, Constants.FIELD_SERVICE_TYPE3 + " = '?'", new String[] { getService_type3() }, true) ;
					}
					else if (fieldsar[i].equals("detailed_service_type3"))
					{
						buildWQuery(subset, Constants.FIELD_DETAILED_SERVICE_TYPE3 + " = '?'", new String[] { getDetailed_service_type3() }, true);
					}
					else if (fieldsar[i].equals("service_type4"))
					{
						buildWQuery(subset, Constants.FIELD_SERVICE_TYPE4 + " = '?'", new String[] { getService_type4() }, true) ;
					}
					else if (fieldsar[i].equals("detailed_service_type4"))
					{
						buildWQuery(subset, Constants.FIELD_DETAILED_SERVICE_TYPE4 + " = '?'", new String[] { getDetailed_service_type4() }, true);
					}
					else if (fieldsar[i].equals("service_type5"))
					{
						buildWQuery(subset, Constants.FIELD_SERVICE_TYPE5 + " = '?'", new String[] { getService_type5() }, true) ;
					}
					else if (fieldsar[i].equals("detailed_service_type5"))
					{
						buildWQuery(subset, Constants.FIELD_DETAILED_SERVICE_TYPE5 + " = '?'", new String[] { getDetailed_service_type5() }, true);
					}
					else if (fieldsar[i].equals("call_comment"))
					{
						buildWQuery(subset, Constants.FIELD_CALL_COMMENT + " = '?'", new String[] { getCall_comment() }, true);
					}
					else if (fieldsar[i].equals("call_name"))
					{
						buildWQuery(subset, Constants.FIELD_CALL_NAME + " = '?'", new String[] { getCall_name() }, true);
					}
					else if (fieldsar[i].equals("call_result_id"))
					{
						buildWQuery(subset, Constants.FIELD_CALL_RESULT_ID + " = '?'", new String[] { getCall_result_id()}, true);
						result = "result" ;
					}
					else if (fieldsar[i].equals("call_rate_id"))
					{
						buildWQuery(subset, Constants.FIELD_CALL_RATE_ID+ " = '?'", new String[] { getCall_rate_id() }, true);
					}
					else if (fieldsar[i].equals("call_sort_id"))
					{
						buildWQuery(subset, Constants.FIELD_CALL_SORT_ID+ " = '?'", new String[] { getCall_sort_id() }, true);
					}
					else if (fieldsar[i].equals("unit_id"))
					{
						buildWQuery(subset, Constants.FIELD_UNIT_ID + " = '?'", new String[] { getUnit_id() }, true);
					}
					else if (fieldsar[i].equals("transfer_check"))
					{
						buildWQuery(subset, Constants.FIELD_TRANSFER_CHECK  + " = '?'", new String[] { "true".equals(getTransfer_check()) ? "1" : "0" }, true);
						sendEmailComplaint();
						result = "result" ;
					}
					else if (fieldsar[i].equals("deadline"))
					{
						buildWQuery(subset,  Constants.FIELD_DEADLINE + " =  TO_TIMESTAMP('?','YYYY-MM-DD')", new String[] { getDeadline() }, true);
					}
					else if (fieldsar[i].equals("transfer_phonenumber"))
					{
						buildWQuery(subset, Constants.FIELD_TRANSFER_PHONENUMBER + " = '?'", new String[] { getTransfer_phonenumber() }, true);
					}

					if (sets.length() != 0)
					{
						sets.append(",") ;
					}
					sets.append(subset) ;
				}

				hsts.clear() ;
				hsts.put("SET", sets);
				hsts.put(Constants.FIELD_CALL_HISTORY_ID, "'" + getCallID() + "'");
				hsts.put(Constants.FIELD_CALL_REQ_TYPE, "'" + getReqType() + "'");

				sql = reader.findSQLByCommand(QueryConstants.SLCT_CALL_SERVICE_UPDATE, hsts, true);
				log("very important ---------------------------------------->"+sql);
				if (db.executeUpdate(sql))
				{
					saveTaskAndComplaint(false);
					/*String mig=getMigration();
					if("1".equals(mig)){
						saveTaskAndComplaint(false);
					}else if("2".equals(mig)){
						saveComplaintHistory(false);
					}else if("3".equals(mig)){
						saveComplaint(false);
					}*/
					
					db.commit() ;
				}
			}
		}

		catch (Exception e) {
			log("Баазтай холбогдох алдаа", e);
		} finally {
			db.disconnect();
		}
		return result ;
    }

    /**
     * @param email
     */
    private void sendEmailComplaint()
    {
		StringBuffer where = new StringBuffer(QUERY_BEGIN);

		buildWQuery(where, " AND " + Constants.FIELD_CALL_HISTORY_ID + " = '?'", new String[] { getCallID() });
		buildWQuery(where, " AND " + Constants.FIELD_CALL_REQ_TYPE + " = '?'", new String[] { getReqType() });

		HashMap<String, Object> hsts = new HashMap<String, Object>();

    	DBAccess db = getDBAccess();

		try {
			SQLListReader reader = getSQLListReader();


			hsts.put(Constants.QUERY_WHERE, where);
			hsts.put("SYSTEMRATE", escStrQNull(getText("system.rateselect")));
			String sql = reader.findSQLByCommand(QueryConstants.SLCT_CALL_HISTORY_SERVICE, hsts, true);
			ResultSet rs = db.select(sql);

			if (rs.next())
			{
				StringBuffer buf = new StringBuffer();
				buf.append(convertIfEmpty(getText("InboundComplaint.txtCall_name"), rs.getString(Constants.FIELD_CALL_NAME))) ;
				buf.append(convertIfEmpty(getText("InboundComplaint.lblStaticComplaint_result_id"), rs.getString(Constants.FIELD_CALL_RESULT_NAME))) ;
				buf.append(convertIfEmpty(getText("InboundComplaint.lblStaticComplaint_rate_id"), rs.getString(Constants.FIELD_CALL_RATE_NAME))) ;
				buf.append(convertIfEmpty(getText("InboundComplaint.lblStaticComplaints_sort_id"), rs.getString(Constants.FIELD_CALL_SORT_NAME))) ;
				buf.append(convertIfEmpty(getText("InboundComplaint.lblStaticUnit_id"), rs.getString(Constants.FIELD_UNIT_NAME))) ;

				buf.append(convertIfEmpty(getText("InboundComplaint.lblStaticDeadline"), rs.getString(Constants.FIELD_DEADLINE))) ;
				buf.append(convertIfEmpty(getText("InboundComplaint.lblStaticComplaint_comment"), rs.getString(Constants.FIELD_CALL_COMMENT))) ;

				buf.append(convertIfEmpty(getText("InboundComplaint.lblStaticComplaint_id"), rs.getString(Constants.FIELD_SERVICE_NAME1))) ;
				buf.append(convertIfEmpty(getText("InboundComplaint.lblStaticComplaint_type_id"), rs.getString(Constants.FIELD_DETAILED_SERVICE_NAME1))) ;

				buf.append(convertIfEmpty(getText("InboundComplaint.lblStaticComplaint_id"), rs.getString(Constants.FIELD_SERVICE_NAME2))) ;
				buf.append(convertIfEmpty(getText("InboundComplaint.lblStaticComplaint_type_id"), rs.getString(Constants.FIELD_DETAILED_SERVICE_NAME2))) ;

				buf.append(convertIfEmpty(getText("InboundComplaint.lblStaticComplaint_id"), rs.getString(Constants.FIELD_SERVICE_NAME3))) ;
				buf.append(convertIfEmpty(getText("InboundComplaint.lblStaticComplaint_type_id"), rs.getString(Constants.FIELD_DETAILED_SERVICE_NAME3))) ;

				buf.append(convertIfEmpty(getText("InboundComplaint.lblStaticComplaint_id"), rs.getString(Constants.FIELD_SERVICE_NAME4))) ;
				buf.append(convertIfEmpty(getText("InboundComplaint.lblStaticComplaint_type_id"), rs.getString(Constants.FIELD_DETAILED_SERVICE_NAME4))) ;

				buf.append(convertIfEmpty(getText("InboundComplaint.lblStaticComplaint_id"), rs.getString(Constants.FIELD_SERVICE_NAME5))) ;
				buf.append(convertIfEmpty(getText("InboundComplaint.lblStaticComplaint_type_id"), rs.getString(Constants.FIELD_DETAILED_SERVICE_NAME5))) ;

				sql = reader.findSQLByCommand(QueryConstants.SELECT_AGENT_COMPLAINT, hsts, true);
				rs = db.select(sql);

				while(rs.next())
				{
					sendEmail(rs.getString(Constants.FIELD_AGENT_EMAIL), buf.toString()) ;
				}
			}
		}

		catch (Exception e) {
			log("Баазтай холбогдох алдаа", e);
		}    
	}
    /**
     * @param value
     * @return
     */
    private String convertIfEmpty(String message, String value)
    {
    	return isEmpty(value) ? "" : message + ":" + value + "\n";
    }

    /**
     *
     */
    private void saveTaskAndComplaint(boolean commit)
    {
    	try{
    		DBAccess db = getDBAccess() ;
    		SQLListReader reader = getSQLListReader();
    		HashMap<String, Object> hsts = new HashMap<String, Object>();
    		String sql;
    		hsts.put(Constants.FIELD_CALL_HISTORY_ID, getCallID()) ;
    		sql = reader.findSQLByCommand(QueryConstants.DELETE_FROM_TASK, hsts, true);
			log(sql);
			db.select(sql);
			sql = reader.findSQLByCommand(QueryConstants.DELETE_FROM_COMPLAINT, hsts, true);
			db.select(sql);
			for (int i=1;i<6;i++)
			{
				hsts.put("ID", i) ;
				sql = reader.findSQLByCommand(QueryConstants.INS2_COMPLAINT_FROM_CALL, hsts, true);
				db.select(sql) ;
			}
			sql = reader.findSQLByCommand(QueryConstants.INS_TASK_FROM_CALL, hsts, true);
			log(sql);
			db.select(sql) ;
			if (commit)
			{
				db.commit() ;
			}
    	}
    	catch(Exception e){
    		log("Баазтай холбогдох алдаа", e);
    	}
    }
    /*private void saveTaskAndComplaint1(boolean commit)
    {
    	DBAccess db3 = getDBAccess();
    	try{
    		db3.connect();
    		SQLListReader reader = getSQLListReader();
    		HashMap<String, Object> hsts = new HashMap<String, Object>();
    		String sql;
    		hsts.put(Constants.FIELD_CALL_HISTORY_ID, getCallID()) ;
			sql = reader.findSQLByCommand(QueryConstants.DELETE_FROM_TASK, hsts, true);
			log(sql);
			db3.select(sql);
			sql = reader.findSQLByCommand(QueryConstants.DELETE_FROM_COMPLAINT, hsts, true);
			log(sql);
			db3.select(sql);
			for (int i=1;i<6;i++)
			{
				hsts.put("ID", i) ;
				sql = reader.findSQLByCommand(QueryConstants.INS2_COMPLAINT_FROM_CALL, hsts, true);
				log(sql);
				db3.select(sql) ;
			}
			sql = reader.findSQLByCommand(QueryConstants.INS_TASK_FROM_CALL, hsts, true);
			log(sql);
			db3.select(sql) ;
			if (commit)
			{
				db3.commit() ;
			}
    	}
    	catch(Exception e){
    		log("Баазтай холбогдох алдаа", e);
    	}finally{
    		db3.disconnect();
    	}
    }*/
    /*private void saveComplaint(boolean commit)
    {
    	try{
    		DBAccess db = getDBAccess();
    		SQLListReader reader = getSQLListReader();
    		HashMap<String, Object> hsts = new HashMap<String, Object>();
    		String sql;
    		hsts.put(Constants.FIELD_CALL_HISTORY_ID, getCallID()) ;
			sql = reader.findSQLByCommand(QueryConstants.DELETE_FROM_COMPLAINT, hsts, true);
			log(sql);
			db.select(sql);
			for (int i=1;i<6;i++)
			{
				hsts.put("ID", i) ;
				sql = reader.findSQLByCommand(QueryConstants.INS3_COMPLAINT_FROM_CALL, hsts, true);
				log(sql);
				db.select(sql) ;
			}
			if (commit)
			{
				db.commit() ;
			}
    	}
    	catch(Exception e){
    		log("Баазтай холбогдох алдаа", e);
    	}
    }
    private void saveComplaintEnding(boolean commit)
    {
    	DBAccess db=null;
    	try{
    		db = getDBAccess();
    		db.connect();
    		SQLListReader reader = getSQLListReader();
    		HashMap<String, Object> hsts = new HashMap<String, Object>();
    		String sql;
    		hsts.put(Constants.FIELD_CALL_HISTORY_ID, getCallID()) ;
			sql = reader.findSQLByCommand(QueryConstants.DELETE_FROM_COMPLAINT, hsts, true);
			log(sql);
			db.select(sql);
			for (int i=1;i<6;i++)
			{
				hsts.put("ID", i) ;
				sql = reader.findSQLByCommand(QueryConstants.INS3_COMPLAINT_FROM_CALL, hsts, true);
				log(sql);
				db.select(sql) ;
			}
			if (commit)
			{
				db.commit() ;
			}
    	}
    	catch(Exception e){
    		log("Баазтай холбогдох алдаа", e);
    	}finally{
    		db.disconnect();
    	}
    }
    private void saveComplaintHistory(boolean commit)
    {
    	try{
    		DBAccess db = getDBAccess();
    		SQLListReader reader = getSQLListReader();
    		HashMap<String, Object> hsts = new HashMap<String, Object>();
    		String sql;
    		hsts.put(Constants.FIELD_CALL_HISTORY_ID, getCallID()) ;
			sql = reader.findSQLByCommand(QueryConstants.DELETE_FROM_COMPLAINT, hsts, true);
			log(sql);
			db.select(sql);
			for (int i=1;i<6;i++)
			{
				hsts.put("ID", i) ;
				sql = reader.findSQLByCommand(QueryConstants.INS1_COMPLAINT_FROM_CALL, hsts, true);
				log(sql);
				db.select(sql) ;
			}
			if (commit)
			{
				db.commit() ;
			}
    	}
    	catch(Exception e){
    		log("Баазтай холбогдох алдаа", e);
    	}
    }
    private void saveToComplaintCallEnd(){
    		log("saving to complaint");
    		DBAccess db1 = getDBAccess();
    		try{
    		db1.connect();
			SQLListReader reader = getSQLListReader();
			HashMap<String, Object> hsts = new HashMap<String, Object>();
			String sql;
			hsts.put(Constants.FIELD_CALL_HISTORY_ID, getCallID()) ;
			for (int i=1;i<6;i++)
			{
				hsts.put("ID", i) ;
				sql = reader.findSQLByCommand(QueryConstants.INS1_COMPLAINT_FROM_CALL, hsts, true);
				db1.select(sql) ;
			}
			db1.commit();
			}catch(Exception e){
				log("Баазтай холбогдох алдаа", e);
			}
			finally{
				db1.disconnect();
			}
    }*/
    
    /**
	 * @param id
	 * @return
	 */
	public HashMap<String, String> getCmbServiceList() {
		HashMap<String, Object> hsts = new HashMap<String, Object>();

		hsts.put(Constants.FIELD_AGENT_ID, getAgentID()) ;
		hsts.put(Constants.FIELD_CALL_REQ_TYPE_ID, getReqType()) ;

		try
		{
			return getCombo(QueryConstants.SLCT_MST_SERVICE, Constants.FIELD_SERVICE_ID, Constants.FIELD_SERVICE_NAME, hsts);
		}
		catch (Exception e)
		{
			log("Өгөгдлийн сан руу хандах үед алдаа гарлаа", e);
			return new HashMap<String, String>();
		}
	}

	/**
	 * @param id
	 * @return
	 */
	private HashMap<String, String> getCmbServiceDetailList(int id) {

		HashMap<String, Object> hsts = new HashMap<String, Object>();
		hsts.put(Constants.FIELD_AGENT_ID, getAgentID()) ;
		hsts.put(Constants.FIELD_CALL_REQ_TYPE_ID, getReqType()) ;

		String pid = null ;

		switch(id)
		{
			case 1: if (!isEmpty(getService_type1())) {pid = getService_type1() ;} break ;
			case 2: if (!isEmpty(getService_type2())) {pid = getService_type2() ;} break ;
			case 3: if (!isEmpty(getService_type3())) {pid = getService_type3() ;} break ;
			case 4: if (!isEmpty(getService_type4())) {pid = getService_type4() ;} break ;
			case 5: if (!isEmpty(getService_type5())) {pid = getService_type5() ;} break ;
		}
		if (pid != null)
		{
			hsts.put(Constants.FIELD_PARENT_SERVICE_ID, String.valueOf(pid)) ;

			try
			{
				return getCombo(QueryConstants.SLCT_MST_SERVICE_DETAIL, Constants.FIELD_SERVICE_ID, Constants.FIELD_SERVICE_NAME, hsts);
			}
			catch (Exception e)
			{
				log("Өгөгдлийн сан руу хандах үед алдаа гарлаа", e);
				return new HashMap<String, String>();
			}
		}
		else
		{
			return new HashMap<String, String>();
		}
	}

	/**
	 * @return
	 */
	public HashMap<String, String> getCmbServiceDetailList1() {
		return getCmbServiceDetailList(1) ;
	}

	/**
	 * @return
	 */
	public HashMap<String, String> getCmbServiceDetailList2() {
		return getCmbServiceDetailList(2) ;
	}

	/**
	 * @return
	 */
	public HashMap<String, String> getCmbServiceDetailList3() {
		return getCmbServiceDetailList(3) ;
	}

	/**
	 * @return
	 */
	public HashMap<String, String> getCmbServiceDetailList4() {
		return getCmbServiceDetailList(4) ;
	}

	/**
	 * @return
	 */
	public HashMap<String, String> getCmbServiceDetailList5() {
		return getCmbServiceDetailList(5) ;
	}

	/**
	 * @return isExistingCall
	 */
	public boolean isExistingCall() {
		return isExistingCall;
	}

	/**
	 * @param isExistingCall セットする isExistingCall
	 */
	private void setExistingCall(boolean isExistingCall) {
		this.isExistingCall = isExistingCall;
	}

	/**
	 * @return status
	 */
	public String getStatus() {
		return isExistingCall() ? "true" : null ;
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

	/**
	 * @return transfer_phonenumber
	 */
	public String getTransfer_phonenumber() {
		return transfer_phonenumber;
	}

	/**
	 * @param transfer_phonenumber セットする transfer_phonenumber
	 */
	public void setTransfer_phonenumber(String transfer_phonenumber) {
		this.transfer_phonenumber = transfer_phonenumber;
	}

	/**
	 * @return unit_id
	 */
	public String getUnit_id() {
		return unit_id;
	}

	/**
	 * @param unit_id セットする unit_id
	 */
	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}

	/**
	 * @return call_result_id
	 */
	public String getCall_result_id() {
		return call_result_id;
	}

	/**
	 * @param call_result_id セットする call_result_id
	 */
	public void setCall_result_id(String call_result_id) {
		this.call_result_id = call_result_id;
	}

	/**
	 * @return call_name
	 */
	public String getCall_name() {
		return call_name;
	}

	/**
	 * @param call_name セットする call_name
	 */
	public void setCall_name(String call_name) {
		this.call_name = call_name;
	}

	/**
	 * @return deadline
	 */
	public String getDeadline() {
		return deadline;
	}

	/**
	 * @param deadline セットする deadline
	 */
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	/**
	 * @return service_type1
	 */
	public String getService_type1() {
		return service_type1;
	}

	/**
	 * @param service_type1 セットする service_type1
	 */
	public void setService_type1(String service_type1) {
		this.service_type1 = service_type1;
	}

	/**
	 * @return detailed_service_type1
	 */
	public String getDetailed_service_type1() {
		return detailed_service_type1;
	}

	/**
	 * @param detailed_service_type1 セットする detailed_service_type1
	 */
	public void setDetailed_service_type1(String detailed_service_type1) {
		this.detailed_service_type1 = detailed_service_type1;
	}

	/**
	 * @return service_type2
	 */
	public String getService_type2() {
		return service_type2;
	}

	/**
	 * @param service_type2 セットする service_type2
	 */
	public void setService_type2(String service_type2) {
		this.service_type2 = service_type2;
	}

	/**
	 * @return detailed_service_type2
	 */
	public String getDetailed_service_type2() {
		return detailed_service_type2;
	}

	/**
	 * @param detailed_service_type2 セットする detailed_service_type2
	 */
	public void setDetailed_service_type2(String detailed_service_type2) {
		this.detailed_service_type2 = detailed_service_type2;
	}

	/**
	 * @return service_type3
	 */
	public String getService_type3() {
		return service_type3;
	}

	/**
	 * @param service_type3 セットする service_type3
	 */
	public void setService_type3(String service_type3) {
		this.service_type3 = service_type3;
	}

	/**
	 * @return detailed_service_type3
	 */
	public String getDetailed_service_type3() {
		return detailed_service_type3;
	}

	/**
	 * @param detailed_service_type3 セットする detailed_service_type3
	 */
	public void setDetailed_service_type3(String detailed_service_type3) {
		this.detailed_service_type3 = detailed_service_type3;
	}

	/**
	 * @return service_type4
	 */
	public String getService_type4() {
		return service_type4;
	}

	/**
	 * @param service_type4 セットする service_type4
	 */
	public void setService_type4(String service_type4) {
		this.service_type4 = service_type4;
	}

	/**
	 * @return detailed_service_type4
	 */
	public String getDetailed_service_type4() {
		return detailed_service_type4;
	}

	/**
	 * @param detailed_service_type4 セットする detailed_service_type4
	 */
	public void setDetailed_service_type4(String detailed_service_type4) {
		this.detailed_service_type4 = detailed_service_type4;
	}

	/**
	 * @return service_type5
	 */
	public String getService_type5() {
		return service_type5;
	}

	/**
	 * @param service_type5 セットする service_type5
	 */
	public void setService_type5(String service_type5) {
		this.service_type5 = service_type5;
	}

	/**
	 * @return detailed_service_type5
	 */
	public String getDetailed_service_type5() {
		return detailed_service_type5;
	}

	/**
	 * @param detailed_service_type5 セットする detailed_service_type5
	 */
	public void setDetailed_service_type5(String detailed_service_type5) {
		this.detailed_service_type5 = detailed_service_type5;
	}

	/**
	 * @return call_comment
	 */
	public String getCall_comment() {
		return call_comment;
	}

	/**
	 * @param call_comment セットする call_comment
	 */
	public void setCall_comment(String call_comment) {
		this.call_comment = call_comment;
	}

	/**
	 * @return call_rate_id
	 */
	public String getCall_rate_id() {
		return call_rate_id;
	}

	/**
	 * @param call_rate_id セットする call_rate_id
	 */
	public void setCall_rate_id(String call_rate_id) {
		this.call_rate_id = call_rate_id;
	}

	/**
	 * @return call_sort_id
	 */
	public String getCall_sort_id() {
		return call_sort_id;
	}

	/**
	 * @param call_sort_id セットする call_sort_id
	 */
	public void setCall_sort_id(String call_sort_id) {
		this.call_sort_id = call_sort_id;
	}

	/**
	 * @return transfer_check
	 */
	public String getTransfer_check() {
		return transfer_check;
	}

	/**
	 * @param transfer_check セットする transfer_check
	 */
	public void setTransfer_check(String transfer_check) {
		this.transfer_check = transfer_check;
	}

	/**
	 * @return fresult
	 */
	public String getFresult() {
		if (!isEmpty(getCall_result_id()))
		{
			HashMap<String, Object> hsts = new HashMap<String, Object>();

			hsts.put(Constants.FIELD_CALL_RESULT_ID, getCall_result_id()) ;

			DBAccess db = getDBAccess();
			db.connect();

			try {
				SQLListReader reader = getSQLListReader();

				String sql = reader.findSQLByCommand(QueryConstants.SLCT_CALL_RESULT_INFO, hsts, true);

				log(sql) ;
				ResultSet rs = db.select(sql) ;

				if(rs.next())
				{
					return rs.getString(Constants.FIELD_MIGRATION_NUM) ;
				}
				else
				{
					return null ;
				}

			}
			catch (Exception e)
			{
				log("Өгөгдлийн сан руу хандах үед алдаа гарлаа", e);
				return null ;
			}
			finally
			{
				db.disconnect() ;
			}
		}
		else
		{
			return null ;
		}
	}
	public String getMigration() {
		if (!isEmpty(getCall_result_id()))
		{
			HashMap<String, Object> hsts = new HashMap<String, Object>();

			hsts.put(Constants.FIELD_CALL_RESULT_ID, getCall_result_id()) ;

			DBAccess db = getDBAccess();
			try {
				SQLListReader reader = getSQLListReader();

				String sql = reader.findSQLByCommand(QueryConstants.SLCT_CALL_RESULT_INFO, hsts, true);

				log(sql) ;
				ResultSet rs = db.select(sql) ;

				if(rs.next())
				{
					return rs.getString(Constants.FIELD_MIGRATION_NUM) ;
				}
				else
				{
					return null ;
				}

			}
			catch (Exception e)
			{
				log("Өгөгдлийн сан руу хандах үед алдаа гарлаа", e);
				return null ;
			}
		}
		else
		{
			return null ;
		}
	}

}
