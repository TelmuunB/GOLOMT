/*
* „M„Ђ„t„u„|„y„z„~ „~„Џ„‚             OutboundTask.java
*
* „U„…„~„{„€„Ќ„~ „~„Џ„‚          ЃyДаалгавар, хадгалахЃz
* „Svv„‡
* „V„…„r„y„|„q„p„‚„Ќ„~ ‡‚  „O„s„~„Ђ„Ђ    „V„p„‚„y„…„€„p„s„‰    „@„s„…„…„|„s„p
* 01.00.00                 „O„s„~„Ђ„Ђ    „V„p„‚„y„…„€„p„s„‰    „Y„y„~„Џ„Џ„‚ „‡„y„z„ѓ„Џ„~/„H„p„ѓ„ѓ„p„~
*/


package org.golomt.req;


import infox.org.db.DBAccess;
import infox.org.sql.SQLListReader;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.golomt.common.Constants;
import org.golomt.common.QueryConstants;
import org.golomt.common.XMLForm;
import org.iaac.consts.Consts;


/**
* @author 
*
*/
public class OutboundTask extends XMLForm 
{
   /** „†„Ђ„‚„}„Ќ„~ „~„Џ„‚ */
   private static final String FORM_NAME = "OutboundTask.form_name" ;
   /**  */
   private static final String INFO_MESSAGE="OutboundTask.Амжилттай хадгалагдлаа";
   /**  */
   private static final String ERR_MESSAGE="OutboundTask.Өгөгдлөө зөв оруулна уу!";
   /**  */
   private static final String INFO_MESSAGE1="OutboundTask.Өгөгдлөө бөглөнө үү!";
   
   private static final String update_success = "system.update_success";
   
   private static final String update_failed = "system.update_failed";
   
 /*  static String outboundTask_ID;
   */
    /** Дуудлага мэдээллийн хэсэг */
    private String grpCall_information;
    /** Өөрийн даалгавар */
    private String lblStaticMine_task;
    /** Өөрийн даалгавар */
    private String chkMine_task;
    /** Дундын даалгавар */
    private String lblStaticAverage_task;
    /** Дундын даалгавар */
    private String chkAverage_task;
   /** Даалгаврын жагсаалт */
    private String cmbTask_list;
    private boolean isExistingCall ;
    /** Агентын № */
    private String lblStaticAgent_id;
        
    /** 2 дахь дугаар */
    private String txtSecond_phone;
    
    /** Үйлчлүүлэгчийн нэр */
    private String txtCaller_name;
    /** Огноо, цаг */
    private String lblStaticCall_start_time;
    /** Үргэлжилсэн хугацаа */
    private String lblStaticDuration_time;
    /** Үргэлжилсэн хугацаа */
    private String Duration_time;
    /** Дуудлагын бүртгэл */
    private String grpCall_registration;
    /** Дуудлагын төрөл */
    private String lblStaticCall_req_type_id;
    
    /** Дуудлагын статус */
    private String lblStaticCall_status_id;
   /** Дуудлагын статус */
    private String cmbCall_status_id;
    /** Дуудлагын ангилал */
    private String lblStaticCall_sort_id;
   /** Дуудлагын ангилал  */
    private String cmbCall_sort_id;
    /** Дуудлагын үр дүн */
    private String lblStaticCall_result_id;
   /** Дуудлагын үр дүн */
    private String cmbCall_result_id;
    /** Дуудлагын үр дүн */
    private String lblCall_name;
    /** Дуудлагын үнэлгээ */
    private String lblStaticCall_rate_id;
   /** Дуудлагын үнэлгээ */
    private String cmbCall_rate_id;
    /** Даалгавар дуусах хугацаа */
    private String lblStaticDeadline;
    /** Даалгавар дуусах хугацаа */
    private String lblDeadline;
    /** Даалгаврын товч тэмдэглэл */
    private String lblStaticTask_comment;
    /** Даалгаврын товч тэмдэглэл */
    private String lblTask_comment;
    /** Товч тэмдэглэл */
    private String lblStaticOutbound_comment;
    /** Товч тэмдэглэл */
    private String call_comment;
    
    private String open;
    
    private String ag_agent_permission;
    
    protected static final String QUERY_BEGIN = " 1 = 1 ";
    
    private String taskID;
    
    
    /** Дуудлагын ID */
    private String callID;

    /** Үйлчлүүлэгчийн нэр */
    private String caller_name;
	private String Call_name;
	private String Deadline;
	private String Task_comment;
	/** Дуудлагын төрөл */
    private String Call_req_type_id;
    
    boolean for_req_type_id;
    
    private String Call_req_type_name;
    /** Агентын № */
    private String Agent_id;
    private String call_start_time;
    private String call_finished_time;
    private String view_status ;
	
	/** Эрэмбэлэлтийн өсөх/буурах эсэхийг заагч талбарууд */
	private String sord;

	/** Эрэмбэлэлтийг заагч талбар */
	private String sidx;

	/** Хайлтыг харуулах хуудасны дугаар */
	private int page;

	private String searchClick;
	private String call_history_id;
	
	private String status;
	
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
	
	private String fields ;
	
    /** Утасны дугаар */
    private String phone_number;

    private String dnis;
	/** */
	private String phone_number2;
	
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
	
	/** Дуудлагын тэмдэглэл */
	
	/** Дуудлагын үр дүн */
	private String call_result_id ;
	
	/** Дуудлагын нэр */
	private String call_rate_id ;
	
	/** Дуудлагын Үнэлгээ */
	private String call_sort_id ;
	
	/** Холбоотой нэгж */
	private String unit_id;
	/** */
	private String transfer_check ;
	/** */
	private String calltype ;
	
	/** Хайлтын үр дүнг харуулах талбарууд */
	private static final String[] FIELD_LIST = { "CALL_HISTORY_ID", "PHONENUMBER", "CALLERNAME", "START_TIME", "FINISHED_TIME", "DURATION", "CALLTYPE", "AGENTNAME", "PHONENUMBER2", "VIEW_STATUS" };

	/** Хайлтын үр дүнг харуулах талбарууд */
	private static final String[] FIELD_LIST_SUB = { "CALL_REQ_TYPE_NAME", "SERVICE_NAME1", "DETAILED_SERVICE_NAME1", "CALL_COMMENT", "CALL_NAME",
		"CALL_RESULT_NAME", "CALL_RATE_NAME", "CALL_STAT_NAME", "CALL_SORT_NAME", "DEADLINE", "UNIT_NAME",
		"SERVICE_NAME2", "DETAILED_SERVICE_NAME2", "SERVICE_NAME3", "DETAILED_SERVICE_NAME3",
		"SERVICE_NAME4", "DETAILED_SERVICE_NAME4", "SERVICE_NAME5", "DETAILED_SERVICE_NAME5", "TRANSFER_PHONENUMBER" };
	
	/** Дуудлага шилжүүлэх дугаар */
	private String transfer_phonenumber ;
	/** үйлчилгээ */
	private String service_type1;
	/** үйлчилгээний нарийвчилсан төрөл */
	private String detailed_service_type1;
	/** Эрэмбэлэх талбарын */
	private static final String ORDER_BY = " ORDER BY ";
	
	/** Хоосон зай */
	private static final String SPACE = " ";
	
	/** Өгсөх эрэмбэлэлт */
	private static final String ASC = "asc";

	/** Өгсөх эрэмбэлэлт */
	private static final String DESC = "desc";
	
	private LinkedHashMap<String,Object> outboundResultList;
	
	/** ЛХүсэлт гаргасан Ангилал COLUMN нэрнүүд */
	protected static final String CALL_SORT_ID = "CALL_SORT_ID";
	protected static final String CALL_SORT_NAME = "CALL_SORT_NAME";
	protected static final String TASK_ID = "TASK_ID";
	
	public static final String PARENT_CALL_SORT_ID = "PARENT_CALL_SORT_ID" ;
	
	protected HashMap<String, String> cmbTask_listList;

	
    /**
     * „A„p„z„s„…„…„|„p„s„‰ „†„…„~„{„€
     */
    public OutboundTask()
    {
    }

    /* (non-Javadoc)
     * @see com.opensymphony.xwork2.ActionSupport#execute()
     */
    public String execute()
    {
    	log("    <><> <><>  taskID = "+getTaskID());
    	DBAccess db = getDBAccess();
    	SQLListReader reader = getSQLListReader();
    	
    	HashMap<String, Object> hsts = new HashMap<String, Object>();
		StringBuffer where = new StringBuffer(QUERY_BEGIN);
		
		if(!isEmpty(getPhone_number()) && getType().equals("outview"))
    	{	
    		
    		buildWQuery(where, " AND ( " + Constants.FIELD_PHONE_NUMBER_ONE + " = '?'", new String[] { getPhone_number() });
    		buildWQuery(where, " OR " + Constants.FIELD_PHONE_NUMBER_TWO + " = '?' )", new String[] { getPhone_number() });
    		where.append(" AND TA.DELETE_USER IS NULL AND TA.DELETE_DATE IS NULL");
			hsts.put(Constants.QUERY_WHERE, where);

			
			db.connect();

			try {

				String sql = reader.findSQLByCommand(Constants.SLCT_TASK, hsts, true);
				log(">> "+sql);
				ResultSet rs = db.select(sql);

				if (rs.next())
				{
					setTaskID(rs.getString("TASK_ID"));
					setCall_req_type_id(rs.getString("CALL_REQ_TYPE_ID"));
					setCall_req_type_name(rs.getString("CALL_REQ_TYPE_NAME"));
					setCallID(rs.getString("CALL_HISTORY_ID")) ;
					setPhone_number(rs.getString("PHONE_NUMBER_ONE")) ;
					setPhone_number2(rs.getString("PHONE_NUMBER_TWO")) ;
					setCaller_name(rs.getString("CALLER_NAME")) ;
					setCall_name(rs.getString("TASK_NAME")) ;
					setDeadline(rs.getString("DEADLINE")) ;
					setTask_comment(rs.getString("TASK_COMMENT"));
					log(" setCallID  =   "+getCallID());
				}
			}

			catch (Exception e) {
				log("Баазтай холбогдох алдаа", e);
				return ERROR;
			} finally {
				db.disconnect();
			}
    	}
		
		if("out".equals(getType()) && !"true".equals(getCallEnd()))
		{
			log("<><><><><><><><>okokokokokok     "+getPhone_number() );
	    	if(!isEmpty(getPhone_number()))
	    	{	

	    		log("okokokokokokokokokokok    ");
	    		
	    		buildWQuery(where, " AND ( " + Constants.FIELD_PHONENUMBER + " = '?'", new String[] { getPhone_number() });
	    		buildWQuery(where, " OR " + Constants.FIELD_PHONENUMBER2+ " = '?' )", new String[] { getPhone_number() });
	    		hsts.put(Constants.QUERY_WHERE, where);
	    		log("=======================         "+hsts);
	    		db.connect();

	    		try {

	    			String sql = reader.findSQLByCommand(QueryConstants.SLCT_CALLER_DETAIL, hsts, true);

	    			ResultSet rs = db.select(sql);
	    			log(">>>>>>>>>>>>>    "+sql);
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
	    	log("+++++++   "+getCallID());
	    	if (getCallID() == null || getCallID().equals(""))
	    	{
	    		db.connect();
	    		
	    		try {
	    				hsts.put(Constants.FIELD_PHONENUMBER, escStrQ(getPhone_number()));
	    				hsts.put(Constants.FIELD_AGENT_ID, escStrQ(getAgentID()));
	    				
	    				String sql = reader.findSQLByCommand(QueryConstants.OUT_CALL_HISTORY_INSERT, hsts, true);
	    				if (db.executeInsert(sql))
	    				{
	    					sql = reader.findSQLByCommand(QueryConstants.SLCT_CALL_HISTORY_MAX, hsts, true);
	    					ResultSet rs = db.select(sql);
	    					log("<><><><><><>   "+sql);
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
	    				
	    				HashMap<String, Object> hsts2 = new HashMap<String, Object>();
	    				StringBuffer where2 = new StringBuffer(QUERY_BEGIN);
	    				log(getTaskID()+"<<---------<<<<<<<<<____________---------TASK ID");
	    				buildWQuery(where2, " AND TASK_ID = '?'", new String[] { getTaskID() });
	    				
	    				hsts2.put(Constants.QUERY_WHERE, where2);
	    				
	    				sql = reader.findSQLByCommand(Constants.SLCT_TASK_FOR_ID, hsts2, true);
	    				log("   SLCT_TASK_FOR_ID   --->>    "+sql);
	    				ResultSet rs2 = db.select(sql);
	    				
	    				if(rs2.next()){
	    				
		    				HashMap<String, Object> hsts1 = new HashMap<String, Object>();
		    						    				
		    				if(!isEmpty(rs2.getString("CALL_REQ_TYPE_ID")))
		    				{
		    					hsts1.put("CALL_REQ_TYPE", rs2.getString("CALL_REQ_TYPE_ID")); 
		    				}else{
		    					hsts1.put("CALL_REQ_TYPE", "''"); 
		    				}
		    				
		    				log("    =CALL_REQ_TYPE_ID =  "+ hsts1);
		    				hsts1.put("CALL_NAME", "'"+rs2.getString("TASK_NAME")+"'");
		    				if(!isEmpty(rs2.getString("DEADLINE"))){
		    					hsts1.put("DEADLINE", "'"+rs2.getString("DEADLINE")+"'");
		    				}else{
		    					hsts1.put("DEADLINE", "'"+now()+"'");
		    				}
		    				hsts1.put("TASK_COMMENT", "'"+rs2.getString("TASK_COMMENT")+"'");
		    				hsts1.put("CALL_HISTORY_ID", "'"+getCallID()+"'");
		    				
		    				sql = reader.findSQLByCommand(Constants.INSRT_TASK_HIS_SERVICE, hsts1, true);
		    				log(">> "+sql);
		    				ResultSet rs1 = db.select(sql);
		    				if(rs1.next()){  log("insertedTask");  }
		    				else{ log("No insertedTask"); }
		    				db.commit() ;
		    				//ResultSet rs = db.select(sql);
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
	
			log(">>>>>>>>>>>>>    "+hsts);
			db.connect();

			try {
				reader = getSQLListReader();
	
				String sql = reader.findSQLByCommand(QueryConstants.SLCT_CALL_HISTORY, hsts, true);
				log(">>>>>>>>> ::::::::::::    "+sql);
				ResultSet rs = db.select(sql);
				if (rs.next())
				{
					/*setPhone_number(rs.getString(Constants.FIELD_PHONENUMBER)) ;
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
					setView_status(rs.getString(Constants.FIELD_VIEW_STATUS)) ;*/
	
					//setTask_ID(rs.getString("TASK_ID"));
					///setCall_history_id(rs.getString("CALL_HISTORY_ID"));
					//setCall_req_type_id(rs.getString("CALL_REQ_TYPE_ID"));
					//setCall_req_type_name(rs.getString("CALL_REQ_TYPE_NAME"));
					//setAgent_id(rs.getString(Constants.FIELD_AGENT_ID));
					setHas_ref(rs.getString(Constants.FIELD_HAS_REF)) ;
					setHas_info(rs.getString(Constants.FIELD_HAS_INFO)) ;
					setHas_app(rs.getString(Constants.FIELD_HAS_APP)) ;
					setHas_comp(rs.getString(Constants.FIELD_HAS_COMP)) ;
					setHas_tra(rs.getString(Constants.FIELD_HAS_TRA)) ;
					setHas_other(rs.getString(Constants.FIELD_HAS_OTHER)) ;
					//setCallID(rs.getString("CALL_HISTORY_ID")) ;
					setPhone_number(rs.getString(Constants.FIELD_PHONENUMBER)) ;
					setPhone_number2(rs.getString(Constants.FIELD_PHONENUMBER2)) ;
					setCaller_name(rs.getString(Constants.FIELD_CALLERNAME)) ;
					//setCall_name(rs.getString("TASK_NAME")) ;
					setDeadline(rs.getString(Constants.FIELD_DURATION)) ;
					setView_status(rs.getString(Constants.FIELD_VIEW_STATUS)) ;
					setCall_start_time(rs.getString(Constants.FIELD_START_TIME));
					setCall_finished_time(rs.getString(Constants.FIELD_FINISHED_TIME));
					/*System.out.println("orson >>> setView_status "+rs.getString(Constants.FIELD_VIEW_STATUS));*/
					//setTask_comment(rs.getString("TASK_COMMENT"));
					log("IDIDIDID   =    "+getCallID());
					this.isExistingCall = !isCreated ;
					log("isExistingCall   =    "+this.isExistingCall);
				}
				else
				{
					log("<><>*&&&&&&&&&&&&&&&&& orson >>>>>>>>>>>>>>>>");
					where = new StringBuffer(QUERY_BEGIN);
	
					buildWQuery(where, " AND " + Constants.FIELD_PHONENUMBER + " = '?'", new String[] { getPhone_number() });
	
					hsts = new HashMap<String, Object>();
					hsts.put(Constants.QUERY_WHERE, where);
	
					sql = reader.findSQLByCommand(QueryConstants.SLCT_CALLER_DETAIL, hsts, true);
	
					rs = db.select(sql);
	
					if (rs.next())
					{
						setPhone_number(rs.getString(Constants.FIELD_PHONENUMBER)) ;
						setPhone_number2(rs.getString(Constants.FIELD_PHONENUMBER2)) ;
						setCaller_name(rs.getString(Constants.FIELD_CALLERNAME)) ;
					}
				}
				
				if (getCall_start_time() == null || getCall_start_time().equals(""))
				{
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					setCall_start_time(sdf1.format(new Date())) ;
				}
				
				if(!isEmpty(getTaskID())){
				where=new StringBuffer(QUERY_BEGIN);
				buildWQuery(where, " AND " + Constants.FIELD_TASK_ID + " = '?'", new String[] { getTaskID() });
	    		where.append(" AND TA.DELETE_USER IS NULL AND TA.DELETE_DATE IS NULL");
				hsts.put(Constants.QUERY_WHERE, where);

				
				sql = reader.findSQLByCommand(Constants.SLCT_TASK, hsts, true);
				log(">> "+sql);
				rs = db.select(sql);
					if (rs.next())
					{
						setCall_req_type_id(rs.getString("CALL_REQ_TYPE_ID"));
						
						setCall_req_type_name(rs.getString("CALL_REQ_TYPE_NAME"));
						setCall_name(rs.getString("TASK_NAME")) ;
						setTask_comment(rs.getString("TASK_COMMENT"));
						log(" setCallID  =   "+getCallID());
					}
					
				}

			}catch (Exception e) {
				log("Баазтай холбогдох алдаа", e);
				return ERROR;
			} finally {
				db.disconnect();
			}
			
		}
		log("getCallEnd().equals(true)>>>>12>>>>>     "+getCallEnd());
		
		if("true".equals(getCallEnd())){
			
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
			
			log("                            )>>>>>>>123>>>>>>>>>>>>>>   getCallID =  "+getCallID()+"         >>    "+getCalltype());
			where = new StringBuffer(QUERY_BEGIN);

			buildWQuery(where, " AND " + Constants.FIELD_CALL_HISTORY_ID + " = '?'", new String[] { getCallID() });

			hsts.put(Constants.QUERY_WHERE, where);
			
			db.connect();

			try {
				String sql = reader.findSQLByCommand(QueryConstants.SLCT_CALL_HISTORY, hsts, true);

				ResultSet rs = db.select(sql);

				if (rs.next())
				{
					setPhone_number(rs.getString(Constants.FIELD_PHONENUMBER)) ;
					setPhone_number2(rs.getString(Constants.FIELD_PHONENUMBER2));
					setCaller_name(rs.getString(Constants.FIELD_CALLERNAME));
					setCall_start_time(rs.getString(Constants.FIELD_START_TIME)) ;
					setCall_finished_time(rs.getString(Constants.FIELD_FINISHED_TIME));
					setDuration_time(rs.getString(Constants.FIELD_DURATION));
					setView_status(rs.getString(Constants.FIELD_VIEW_STATUS)) ;
					
					log(">>>>>mainMenu>>>>>>    "+rs.getString(Constants.FIELD_START_TIME)+" .,,,,   "+rs.getString(Constants.FIELD_VIEW_STATUS));
					/*if(!getType().equals("out"))
						setType("inbound");*/
					this.isExistingCall = true ;
				}
				
				
			}
			catch (Exception e) {
				log("Баазтай холбогдох алдаа", e);
				return ERROR;
			} finally {
				db.disconnect();
			}
			
			
////////////--------------------------------------------
			where = new StringBuffer(QUERY_BEGIN);
			log("      >>>>    getCallID    ===    "+getCallID());
			buildWQuery(where, " AND HIS.CALL_HISTORY_ID = '?'", new String[] { getCallID() });

			hsts.put(Constants.QUERY_WHERE, where);
			
			db.connect();
			try{
			
				String sql = reader.findSQLByCommand(QueryConstants.SLCT_OUT_CALL_HISTORY, hsts, true);
		
				ResultSet rs = db.select(sql);
				log(" sql = "+sql);
				if (rs.next())
				{
					log("   <><>< orson ");
					setCall_req_type_name(rs.getString("CALL_REQ_TYPE_NAME"));
					setCmbCall_status_id(rs.getString("CALL_STAT_ID"));
					setCmbCall_sort_id(rs.getString("CALL_SORT_ID"));
					setCall_name(rs.getString("CALL_NAME"));
					setCmbCall_result_id(rs.getString("CALL_RESULT_ID"));
					setCmbCall_rate_id(rs.getString("CALL_RATE_ID"));
					setDeadline(rs.getString("DEADLINE"));
					setCall_comment(rs.getString("CALL_COMMENT"));
					setTask_comment(rs.getString("TASK_COMMENT"));
					setCallID(rs.getString("CALL_HISTORY_ID"));
				}
			}catch (Exception e) {
				log("Баазтай холбогдох алдаа", e);
				return ERROR;
			} finally {
				db.disconnect();
			}
////		-----------------------------------------------------
		}
		
		if("1".equals(calltype)){
			log("                            )>>>>>>>123>>>>>>>>>>>>>>   getCallID =  "+getCallID()+"         >>    "+getCalltype());
			where = new StringBuffer(QUERY_BEGIN);

			buildWQuery(where, " AND " + Constants.FIELD_CALL_HISTORY_ID + " = '?'", new String[] { getCallID() });

			hsts.put(Constants.QUERY_WHERE, where);
			
			db.connect();

			try {
				String sql = reader.findSQLByCommand(QueryConstants.SLCT_CALL_HISTORY, hsts, true);
				log("find Me here>>>>>>>>>>>>>>>>>>>>>"+sql);
				ResultSet rs = db.select(sql);

				if (rs.next())
				{
					setPhone_number(rs.getString(Constants.FIELD_PHONENUMBER)) ;
					setPhone_number2(rs.getString(Constants.FIELD_PHONENUMBER2));
					setCaller_name(rs.getString(Constants.FIELD_CALLERNAME));
					setCall_start_time(rs.getString(Constants.FIELD_START_TIME)) ;
					setCall_finished_time(rs.getString(Constants.FIELD_FINISHED_TIME));
					setDuration_time(rs.getString(Constants.FIELD_DURATION));
					setView_status(rs.getString(Constants.FIELD_VIEW_STATUS)) ;
					
					log(">>>>>mainMenu>>>>>>    "+rs.getString(Constants.FIELD_START_TIME)+" .,,,,   "+rs.getString(Constants.FIELD_VIEW_STATUS));
					if(!getType().equals("out"))
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
			
			
////////////--------------------------------------------
			where = new StringBuffer(QUERY_BEGIN);
			log("      >>>>    getCallID    ===    "+getCallID());
			buildWQuery(where, " AND HIS.CALL_HISTORY_ID = '?'", new String[] { getCallID() });

			hsts.put(Constants.QUERY_WHERE, where);
			
			db.connect();
			try{
			
				String sql = reader.findSQLByCommand(QueryConstants.SLCT_OUT_CALL_HISTORY, hsts, true);
		
				ResultSet rs = db.select(sql);
				log(" sql = "+sql);
				if (rs.next())
				{
					log("   <><>< orson ");
					setCall_req_type_name(rs.getString("CALL_REQ_TYPE_NAME"));
					setCmbCall_status_id(rs.getString("CALL_STAT_ID"));
					setCmbCall_sort_id(rs.getString("CALL_SORT_ID"));
					setCall_name(rs.getString("CALL_NAME"));
					setCmbCall_result_id(rs.getString("CALL_RESULT_ID"));
					setCmbCall_rate_id(rs.getString("CALL_RATE_ID"));
					setDeadline(rs.getString("DEADLINE"));
					setCall_comment(rs.getString("CALL_COMMENT"));
					setTask_comment(rs.getString("TASK_COMMENT"));
					setCallID(rs.getString("CALL_HISTORY_ID"));
				}
			}catch (Exception e) {
				log("Баазтай холбогдох алдаа", e);
				return ERROR;
			} finally {
				db.disconnect();
			}
////		-----------------------------------------------------
		}
        return SUCCESS; 
    }

    
    /* (non-Javadoc)
     * @see com.opensymphony.xwork2.ActionSupport#validate()
     */
    public void validate()
    {
    }
   

    public String OutboundData()
    {
    	if(!getTaskID().equals(""))
    	{
    		StringBuffer where = new StringBuffer(QUERY_BEGIN);
    		
    		buildWQuery(where, " AND " + Constants.FIELD_TASK_ID + " = '?'", new String[] { getTaskID() });
    		
    		HashMap<String, Object> hsts = new HashMap<String, Object>();
			hsts.put(Constants.QUERY_WHERE, where);

			DBAccess db = getDBAccess();
			db.connect();

			try {
				SQLListReader reader = getSQLListReader();

				String sql = reader.findSQLByCommand(Constants.SLCT_TASK, hsts, true);
				log("json >>> "+sql);
				ResultSet rs = db.select(sql);
				outboundResultList=new LinkedHashMap<String, Object>();
				if (rs.next())
				{
					setCall_req_type_id(rs.getString("CALL_REQ_TYPE_ID"));
					setCallID(rs.getString("CALL_HISTORY_ID"));
					outboundResultList.put("CALL_REQ_TYPE_ID", rs.getString("CALL_REQ_TYPE_ID"));
					outboundResultList.put("CALL_REQ_TYPE_NAME", rs.getString("CALL_REQ_TYPE_NAME"));
					outboundResultList.put("CALL_HISTORY_ID", rs.getString("CALL_HISTORY_ID"));
					outboundResultList.put("PHONE_NUMBER_ONE", rs.getString("PHONE_NUMBER_ONE"));
					outboundResultList.put("PHONE_NUMBER_TWO", rs.getString("PHONE_NUMBER_TWO"));
					outboundResultList.put("CALLER_NAME", rs.getString("CALLER_NAME"));
					outboundResultList.put("TASK_NAME", rs.getString("TASK_NAME"));
					outboundResultList.put("DEADLINE", rs.getString("DEADLINE"));
					outboundResultList.put("TASK_COMMENT", rs.getString("TASK_COMMENT"));
				}else{
					setCall_history_id(null);
				}
			}

			catch (Exception e) {
				log("Баазтай холбогдох алдаа", e);
				return ERROR;
			} finally {
				db.disconnect();
			}
    	}
    	
        return "json"; 
    }
    
    
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
			log("            >>>>>>>    TaskList  ==  "+sql);
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
    
	
	
	 /**
     * Хэрэглэгчийн 2 дахь утас болон Хэрэглэгчийн нэр өөрчилөгдсөн үед нэмэх<br>
     */
    public String saveUserOnChange()
    {
    	log("   >>>id  1  "+getCallID());
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

				if (db.executeUpdate(sql))
				{
					db.commit() ;
				}

				//2Дахь дугаарыг хайж олно
				StringBuffer where = new StringBuffer(QUERY_BEGIN);
				hsts.clear() ;

				if (phone2)
				{
					buildWQuery(where, " AND " + Constants.FIELD_PHONENUMBER + " = '?'", new String[] { getPhone_number2() });
		    		hsts.put(Constants.QUERY_WHERE, where);

		    		sql = reader.findSQLByCommand(QueryConstants.SLCT_CALLER_DETAIL, hsts, true);
		    		log("save user = "+sql);
		    		ResultSet rs = db.select(sql);

	    			if (!rs.next())
	    			{
	    				hsts.put(Constants.FIELD_PHONENUMBER, getPhone_number2());
	    				hsts.put(Constants.FIELD_PHONENUMBER2, getPhone_number());
	    				hsts.put(Constants.FIELD_AGENT_ID, getAgentID());

	    				sql = reader.findSQLByCommand(QueryConstants.SLCT_CALLER_DETAIL_INSERT2, hsts, true);
	    				log("save user change  =  "+sql);
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
     * Лавлагаа мэдээлэл өөрчилөгдсөн үед нэмэх<br>
     */
    public String saveOnChange()
    {
    	log("CALL_REQ_TYPE_ID:"+getCall_req_type_id());
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
				if(!isEmpty(getCall_req_type_id()))
					hsts.put(Constants.FIELD_CALL_REQ_TYPE, "'"+getCall_req_type_id()+"'");
				else hsts.put(Constants.FIELD_CALL_REQ_TYPE, "''");


				sql = reader.findSQLByCommand(QueryConstants.SLCT_CALL_SERVICE_INSERT, hsts, true);
				
				log(sql);
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
					log(" i = "+i+"  field = "+fieldsar[i]);
				
					
					
					
					
					if(fieldsar[i].equals("cmbCall_status_id"))
					{
						buildWQuery(subset, Constants.FIELD_CALL_STAT_ID + " = '?'", new String[] { getCmbCall_status_id() }, true);
					}
					else if(fieldsar[i].equals("cmbCall_sort_id"))
					{
						buildWQuery(subset, Constants.FIELD_CALL_SORT_ID + " = '?'", new String[] { getCmbCall_sort_id() }, true);
					}
					else if(fieldsar[i].equals("cmbCall_result_id"))
					{
						
						buildWQuery(subset, Constants.FIELD_CALL_RESULT_ID + " = '?'", new String[] { getCmbCall_result_id() }, true);
						
						HashMap<String, Object> hsts1 = new HashMap<String, Object>();
						StringBuffer subset1 = new StringBuffer();
						buildWQuery(subset1, Constants.FIELD_CALL_RESULT_ID + " = '?'", new String[] { getCmbCall_result_id() }, true);
						
						hsts1.put(Constants.QUERY_WHERE, subset1);

						String sql1 = reader.findSQLByCommand(QueryConstants.SLCT_TASK_LIST_ACT, hsts1, true);
						
						ResultSet rs1 = db.select(sql1);
						if(rs1.next())
						{
							if(rs1.getString("MIGRATION_NUM").equals("0")){
								StringBuffer where1 = new StringBuffer();
								buildWQuery(where1, Constants.FIELD_OUT_TASK_ID + " = '?'", new String[] { getTaskID() }, true);
								hsts1.put(Constants.QUERY_WHERE, where1);
								String sqlout = reader.findSQLByCommand(QueryConstants.UPDT_OUT_TASK_LIST_ACT, hsts1, true);
								log(sqlout);
								db.select(sqlout);
								db.commit();
								hsts1.clear();
								hsts1.put(TASK_ID, getTaskID());
								hsts1.put("SOLVED_STATUS", '1');
								sqlout = reader.findSQLByCommand(QueryConstants.UPDT_TASK_COMPLAINT_SOLVED,hsts1,true);
								log(sqlout);
								db.select(sqlout);
								db.commit();
							}else{
								StringBuffer where1 = new StringBuffer();
								buildWQuery(where1, Constants.FIELD_OUT_TASK_ID + " = '?'", new String[] { getTaskID() }, true);
								hsts1.put(Constants.QUERY_WHERE, where1);
								String sqlout = reader.findSQLByCommand(QueryConstants.UPDT_OUT_TASK_LIST_REV, hsts1, true);
								log(sqlout);
								db.select(sqlout);
								db.commit();
								hsts1.clear();
								hsts1.put(TASK_ID, getTaskID());
								hsts1.put("SOLVED_STATUS", '2');
								sqlout = reader.findSQLByCommand(QueryConstants.UPDT_TASK_COMPLAINT_SOLVED,hsts1,true);
								log(sqlout);
								db.select(sqlout);
								db.commit();
							}
						}
					}
					else if(fieldsar[i].equals("cmbCall_rate_id"))
					{
						buildWQuery(subset, Constants.FIELD_CALL_RATE_ID + " = '?'", new String[] { getCmbCall_rate_id() }, true);
					}
					else if(fieldsar[i].equals("call_comment"))
					{
						buildWQuery(subset, Constants.FIELD_CALL_COMMENT + " = '?'", new String[] { getCall_comment() }, true);
					}

					/*if (sets.length() != 0)
					{
						sets.append(",") ;
					}*/
					sets.append(subset) ;
				}

				hsts.clear() ;
				hsts.put("SET", sets);
				hsts.put(Constants.FIELD_CALL_HISTORY_ID, "'" + getCallID() + "'");
				if(!isEmpty(getCall_req_type_id())){
					hsts.put(Constants.FIELD_CALL_REQ_TYPE, "CALL_REQ_TYPE");
					hsts.put(Constants.FIELD_CALL_REQ_TYPE_ID, escStrQNull(getCall_req_type_id()));
				}else{
					hsts.put(Constants.FIELD_CALL_REQ_TYPE, "1");
					hsts.put(Constants.FIELD_CALL_REQ_TYPE_ID, "1");
				}
				sql = reader.findSQLByCommand(QueryConstants.SLCT_OUT_CALL_SERVICE_UPDATE, hsts, true);
				log("         >>>>>>>>>>>  sql  ==  "+sql);
				if (db.executeUpdate(sql))
				{
					saveTaskAndComplaint(false) ;
					db.commit() ;
				}else{
					db.commit();
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
    
    private void saveTaskAndComplaint(boolean commit)
    {
    	DBAccess db = getDBAccess() ;
		SQLListReader reader = getSQLListReader();

		HashMap<String, Object> hsts = new HashMap<String, Object>();
		hsts.put(Constants.FIELD_CALL_HISTORY_ID, getCallID()) ;

		String sql = reader.findSQLByCommand(QueryConstants.DELETE_FROM_TASK, hsts, true);

		db.select(sql) ;

		sql = reader.findSQLByCommand(QueryConstants.DELETE_FROM_COMPLAINT, hsts, true);

		db.select(sql) ;

		for (int i=1;i<6;i++)
		{
			hsts.put("ID", i) ;

			sql = reader.findSQLByCommand(QueryConstants.INS_COMPLAINT_FROM_CALL, hsts, true);
			db.select(sql) ;
		}

		sql = reader.findSQLByCommand(QueryConstants.INS_TASK_FROM_CALL, hsts, true);

		db.select(sql) ;

		if (commit)
		{
			db.commit() ;
		}
    }
    
    /**
	 * @return
	 */
	protected String getReqType()
	{
		return null ;
	}
    
    
    /**
     * @return
     */
    public String getGrpCall_information() 
    {
       return grpCall_information;
    }

    /**
     * @param grpCall_information     */
    public void setGrpCall_information(String grpCall_information)
    {
       this.grpCall_information= grpCall_information;
    }

    /**
     * @return
     */
    public String getLblStaticMine_task() 
    {
       return lblStaticMine_task;
    }

    /**
     * @param lblStaticMine_task     */
    public void setLblStaticMine_task(String lblStaticMine_task)
    {
       this.lblStaticMine_task= lblStaticMine_task;
    }

    /**
     * @return
     */
    public String getChkMine_task() 
    {
       return chkMine_task;
    }

    /**
     * @param chkMine_task     */
    public void setChkMine_task(String chkMine_task)
    {
       this.chkMine_task= chkMine_task;
    }

    /**
     * @return
     */
    public String getLblStaticAverage_task() 
    {
       return lblStaticAverage_task;
    }

    /**
     * @param lblStaticAverage_task     */
    public void setLblStaticAverage_task(String lblStaticAverage_task)
    {
       this.lblStaticAverage_task= lblStaticAverage_task;
    }

    /**
     * @return
     */
    public String getChkAverage_task() 
    {
       return chkAverage_task;
    }

    /**
     * @param chkAverage_task     */
    public void setChkAverage_task(String chkAverage_task)
    {
       this.chkAverage_task= chkAverage_task;
    }

    /**
     * @return
     */
    public String getCmbTask_list() 
    {
       return cmbTask_list ;
    }

    /**
     * @param cmbTask_list     */
    public void setCmbTask_list(String cmbTask_list)
    {
       this.cmbTask_list= cmbTask_list;
    }

    /**
     * @param btnCall
     */
    public String onBtnCallClick()
    {
       //CALL EVENT
       return SUCCESS; 
    }

    /**
     * @param btnMessage
     */
    public String onBtnMessageClick()
    {
       //CALL EVENT
       return SUCCESS; 
    }
    
    /**
     * @return
     */
    public String getLblStaticAgent_id() 
    {
       return lblStaticAgent_id;
    }

    /**
     * @param lblStaticAgent_id     */
    public void setLblStaticAgent_id(String lblStaticAgent_id)
    {
       this.lblStaticAgent_id= lblStaticAgent_id;
    }

    /**
     * @return
     */
    public String getTxtSecond_phone() 
    {
       return txtSecond_phone;
    }

    /**
     * @param txtSecond_phone     */
    public void setTxtSecond_phone(String txtSecond_phone)
    {
       this.txtSecond_phone= txtSecond_phone;
    }

    /**
     * @return
     */
    public String getTxtCaller_name() 
    {
       return txtCaller_name;
    }

    /**
     * @param txtCaller_name     */
    public void setTxtCaller_name(String txtCaller_name)
    {
       this.txtCaller_name= txtCaller_name;
    }

    /**
     * @return
     */
    public String getLblStaticCall_start_time() 
    {
       return lblStaticCall_start_time;
    }

    /**
     * @param lblStaticCall_start_time     */
    public void setLblStaticCall_start_time(String lblStaticCall_start_time)
    {
       this.lblStaticCall_start_time= lblStaticCall_start_time;
    }

    /**
     * @return
     */
    public String getLblStaticDuration_time() 
    {
       return lblStaticDuration_time;
    }

    /**
     * @param lblStaticDuration_time     */
    public void setLblStaticDuration_time(String lblStaticDuration_time)
    {
       this.lblStaticDuration_time= lblStaticDuration_time;
    }

    /**
     * @return
     */
    public String getGrpCall_registration() 
    {
       return grpCall_registration;
    }

    /**
     * @param grpCall_registration     */
    public void setGrpCall_registration(String grpCall_registration)
    {
       this.grpCall_registration= grpCall_registration;
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
    public String getLblStaticCall_status_id() 
    {
       return lblStaticCall_status_id;
    }

    /**
     * @param lblStaticCall_status_id     */
    public void setLblStaticCall_status_id(String lblStaticCall_status_id)
    {
       this.lblStaticCall_status_id= lblStaticCall_status_id;
    }

    /**
     * @return
     */
    public String getCmbCall_status_id() 
    {
       return cmbCall_status_id ;
    }

    /**
     * @param cmbCall_status_id     */
    public void setCmbCall_status_id(String cmbCall_status_id)
    {
       this.cmbCall_status_id= cmbCall_status_id;
    }

    /**
     * @return
     */
    public String getLblStaticCall_sort_id() 
    {
       return lblStaticCall_sort_id;
    }

    /**
     * @param lblStaticCall_sort_id     */
    public void setLblStaticCall_sort_id(String lblStaticCall_sort_id)
    {
       this.lblStaticCall_sort_id= lblStaticCall_sort_id;
    }

    /**
     * @return
     */
    public String getCmbCall_sort_id() 
    {
       return cmbCall_sort_id ;
    }

    /**
     * @param cmbCall_sort_id     */
    public void setCmbCall_sort_id(String cmbCall_sort_id)
    {
       this.cmbCall_sort_id= cmbCall_sort_id;
    }

    /**
     * @return
     */
    public String getLblStaticCall_result_id() 
    {
       return lblStaticCall_result_id;
    }

    /**
     * @param lblStaticCall_result_id     */
    public void setLblStaticCall_result_id(String lblStaticCall_result_id)
    {
       this.lblStaticCall_result_id= lblStaticCall_result_id;
    }

    /**
     * @return
     */
    public String getCmbCall_result_id() 
    {
       return cmbCall_result_id ;
    }

    /**
     * @param cmbCall_result_id     */
    public void setCmbCall_result_id(String cmbCall_result_id)
    {
       this.cmbCall_result_id= cmbCall_result_id;
    }

    /**
     * @return
     */
    public String getLblCall_name() 
    {
       return lblCall_name;
    }

    /**
     * @param lblCall_name     */
    public void setLblCall_name(String lblCall_name)
    {
       this.lblCall_name= lblCall_name;
    }

    /**
     * @return
     */
    public String getLblStaticCall_rate_id() 
    {
       return lblStaticCall_rate_id;
    }

    /**
     * @param lblStaticCall_rate_id     */
    public void setLblStaticCall_rate_id(String lblStaticCall_rate_id)
    {
       this.lblStaticCall_rate_id= lblStaticCall_rate_id;
    }

    /**
     * @return
     */
    public String getCmbCall_rate_id() 
    {
       return cmbCall_rate_id ;
    }

    /**
     * @param cmbCall_rate_id     */
    public void setCmbCall_rate_id(String cmbCall_rate_id)
    {
       this.cmbCall_rate_id= cmbCall_rate_id;
    }

    /**
     * @return
     */
    public String getLblStaticDeadline() 
    {
       return lblStaticDeadline;
    }

    /**
     * @param lblStaticDeadline     */
    public void setLblStaticDeadline(String lblStaticDeadline)
    {
       this.lblStaticDeadline= lblStaticDeadline;
    }

    /**
     * @return
     */
    public String getLblDeadline() 
    {
       return lblDeadline;
    }

    /**
     * @param lblDeadline     */
    public void setLblDeadline(String lblDeadline)
    {
       this.lblDeadline= lblDeadline;
    }

    /**
     * @return
     */
    public String getLblStaticTask_comment() 
    {
       return lblStaticTask_comment;
    }

    /**
     * @param lblStaticTask_comment     */
    public void setLblStaticTask_comment(String lblStaticTask_comment)
    {
       this.lblStaticTask_comment= lblStaticTask_comment;
    }

    /**
     * @return
     */
    public String getLblTask_comment() 
    {
       return lblTask_comment;
    }

    /**
     * @param lblTask_comment     */
    public void setLblTask_comment(String lblTask_comment)
    {
       this.lblTask_comment= lblTask_comment;
    }

    /**
     * @return
     */
    public String getLblStaticOutbound_comment() 
    {
       return lblStaticOutbound_comment;
    }

    /**
     * @param lblStaticOutbound_comment     */
    public void setLblStaticOutbound_comment(String lblStaticOutbound_comment)
    {
       this.lblStaticOutbound_comment= lblStaticOutbound_comment;
    }

    /**
     * @return
     */
    

    /**
     * @param btnSave
     */
    public String onBtnSaveClick()
    {
    	return SUCCESS;
    }

	

	public String getCall_name() {
		return Call_name;
	}

	public void setCall_name(String call_name) {
		Call_name = call_name;
	}

	public String getDeadline() {
		return Deadline;
	}

	public void setDeadline(String deadline) {
		Deadline = deadline;
	}


	public String getTask_comment() {
		return Task_comment;
	}

	public void setTask_comment(String task_comment) {
		Task_comment = task_comment;
	}

	public String getDuration_time() {
		return Duration_time;
	}

	public void setDuration_time(String duration_time) {
		Duration_time = duration_time;
	}

	

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	

	public String getTaskID() {
		return taskID;
	}

	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getCall_req_type_name() {
		return Call_req_type_name;
	}

	public void setCall_req_type_name(String call_req_type_name) {
		Call_req_type_name = call_req_type_name;
	}

	public String getAgent_id() {
		return Agent_id;
	}

	public void setAgent_id(String agent_id) {
		Agent_id = agent_id;
	}

	public String getCall_start_time() {
		return call_start_time;
	}

	public void setCall_start_time(String call_start_time) {
		this.call_start_time = call_start_time;
	}

	public LinkedHashMap<String, Object> getOutboundResultList() {
		return outboundResultList;
	}

	public void setOutboundResultList(
			LinkedHashMap<String, Object> outboundResultList) {
		this.outboundResultList = outboundResultList;
	}

	
	public String getSearchClick() {
		return searchClick;
	}

	public void setSearchClick(String searchClick) {
		this.searchClick = searchClick;
	}

	public String getCall_history_id() {
		return call_history_id;
	}

	public void setCall_history_id(String call_history_id) {
		this.call_history_id = call_history_id;
	}

	public String getCallID() {
		return callID;
	}

	public void setCallID(String callID) {
		this.callID = callID;
	}

	public String getView_status() {
		return view_status;
	}

	public void setView_status(String view_status) {
		this.view_status = view_status;
	}

	public String getCall_finished_time() {
		return call_finished_time;
	}

	public void setCall_finished_time(String call_finished_time) {
		this.call_finished_time = call_finished_time;
	}

	public boolean isExistingCall() {
		return isExistingCall;
	}

	public void setExistingCall(boolean isExistingCall) {
		this.isExistingCall = isExistingCall;
	}
	
	public String getStatus() {
		log("Outbound   >>> isExistingCall() = >  "+isExistingCall()+"    Outbound   >>>  getView_status()  = >  "+getView_status());
		return isExistingCall() && "0".equals(getView_status()) ? "true" : null ;
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

	public void setHas_ref(String has_ref) {
		this.has_ref = has_ref;
	}

	public void setHas_info(String has_info) {
		this.has_info = has_info;
	}

	public void setHas_app(String has_app) {
		this.has_app = has_app;
	}

	public void setHas_comp(String has_comp) {
		this.has_comp = has_comp;
	}

	public void setHas_tra(String has_tra) {
		this.has_tra = has_tra;
	}

	public void setHas_other(String has_other) {
		this.has_other = has_other;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public String getPhone_number2() {
		return phone_number2;
	}

	public void setPhone_number2(String phone_number2) {
		this.phone_number2 = phone_number2;
	}

	public String getCaller_name() {
		return caller_name;
	}

	public void setCaller_name(String caller_name) {
		this.caller_name = caller_name;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getTransfer_phonenumber() {
		return transfer_phonenumber;
	}

	public void setTransfer_phonenumber(String transfer_phonenumber) {
		this.transfer_phonenumber = transfer_phonenumber;
	}

	public String getService_type1() {
		return service_type1;
	}

	public void setService_type1(String service_type1) {
		this.service_type1 = service_type1;
	}

	public String getDetailed_service_type1() {
		return detailed_service_type1;
	}

	public void setDetailed_service_type1(String detailed_service_type1) {
		this.detailed_service_type1 = detailed_service_type1;
	}

	public String getService_type2() {
		return service_type2;
	}

	public void setService_type2(String service_type2) {
		this.service_type2 = service_type2;
	}

	public String getDetailed_service_type2() {
		return detailed_service_type2;
	}

	public void setDetailed_service_type2(String detailed_service_type2) {
		this.detailed_service_type2 = detailed_service_type2;
	}

	public String getService_type3() {
		return service_type3;
	}

	public void setService_type3(String service_type3) {
		this.service_type3 = service_type3;
	}

	public String getDetailed_service_type3() {
		return detailed_service_type3;
	}

	public void setDetailed_service_type3(String detailed_service_type3) {
		this.detailed_service_type3 = detailed_service_type3;
	}

	public String getService_type4() {
		return service_type4;
	}

	public void setService_type4(String service_type4) {
		this.service_type4 = service_type4;
	}

	public String getDetailed_service_type4() {
		return detailed_service_type4;
	}

	public void setDetailed_service_type4(String detailed_service_type4) {
		this.detailed_service_type4 = detailed_service_type4;
	}

	public String getService_type5() {
		return service_type5;
	}

	public void setService_type5(String service_type5) {
		this.service_type5 = service_type5;
	}

	public String getDetailed_service_type5() {
		return detailed_service_type5;
	}

	public void setDetailed_service_type5(String detailed_service_type5) {
		this.detailed_service_type5 = detailed_service_type5;
	}

	public String getCall_comment() {
		return call_comment;
	}

	public void setCall_comment(String call_comment) {
		this.call_comment = call_comment;
	}

	public String getCall_result_id() {
		return call_result_id;
	}

	public void setCall_result_id(String call_result_id) {
		this.call_result_id = call_result_id;
	}

	public String getCall_rate_id() {
		return call_rate_id;
	}

	public void setCall_rate_id(String call_rate_id) {
		this.call_rate_id = call_rate_id;
	}

	public String getCall_sort_id() {
		return call_sort_id;
	}

	public void setCall_sort_id(String call_sort_id) {
		this.call_sort_id = call_sort_id;
	}

	public String getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}

	public String getTransfer_check() {
		return transfer_check;
	}

	public void setTransfer_check(String transfer_check) {
		this.transfer_check = transfer_check;
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
	
	public HashMap<String, String> getCmbTask_listList() {
		
		if (cmbTask_listList == null)
		{
			try
			{
	    		HashMap<String, Object> hsts = new HashMap<String, Object>();
				hsts.put(PHONE_NUMBER_ONE, getPhone_number());
				hsts.put(PHONE_NUMBER_TWO, getPhone_number());
				cmbTask_listList = getCombo(Constants.SLCT_OUTBOUND_TASK_LIST, TASK_ID, TASKS, hsts);
			}
			catch (Exception e)
			{
				log("Өгөгдлийн сан руу хандах үед алдаа гарлаа", e);
				cmbTask_listList = new HashMap<String, String>();
			}
		}
		return cmbTask_listList;
	}
	public void setCmbTask_listList(HashMap<String, String> cmbTask_listList) {
		this.cmbTask_listList = cmbTask_listList;
	}
	
	
	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public String getCall_req_type_id() {
		return Call_req_type_id;
	}

	public void setCall_req_type_id(String call_req_type_id) {
		Call_req_type_id = call_req_type_id;
	}

	public String getAg_agent_permission() {
		ag_agent_permission=Integer.toString(getPermission());
		return ag_agent_permission;
	}

	public void setAg_agent_permission(String ag_agent_permission) {
		this.ag_agent_permission = ag_agent_permission;
	}

	/*public static String getOutboundTask_ID() {
		return outboundTask_ID;
	}

	public static void setOutboundTask_ID(String outboundTask_ID) {
		OutboundTask.outboundTask_ID = outboundTask_ID;
	}*/

	

	

	
}
