/**
 * Моделийн нэр             XMLForm.java
 *
 * Функцын нэр          【Хийцийн төрөл хайх】
 * Тvvх
 * Хувилбарын №  Огноо    Хариуцагч    Агуулга

 */

package org.golomt.common;

import infox.org.db.DBAccess;
import infox.org.sql.SQLListReader;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.golomt.bean.UserInfo;
import org.iaac.common.BForm;
import org.iaac.consts.Consts;

import com.opensymphony.xwork2.ActionContext;

/**
 * Бүртгэлтэй хамаатай бусад фүнкцүүдийг агуулсан класс

 */
public class XMLForm extends BForm
{

	/** хоосон хайлт */
	protected static final String QUERY_BEGIN = " 1 = 1 ";

	/** Дуудлагын төрөл CallReqType*/
	protected static final String CALL_REQ_TYPE_ID = "CALL_REQ_TYPE_ID";
	protected static final String CALL_REQ_TYPE_NAME = "CALL_REQ_TYPE_NAME";

	/** Даалгаврын жагсаалтын Дуудлагын төрөл COLUMN нэрнүүд*/
	protected static final String SERVICE_ID = "SERVICE_ID";
	protected static final String SERVICE_NAME = "SERVICE_NAME";
	
	/** Даалгаврын жагсаалтын Даалгавар оруулсан COLUMN нэрнүүд*/
	protected static final String AGENT_REGISTER = "AGENT_REGISTER";
	protected static final String FIRSTNAME = "FIRSTNAME";
	protected static final String AGENT_ID = "AGENT_ID";
	
	/**-------------------------------------------------------------------------------------------------------------------------------------------*/
	/**-------------------------------------------------------------------------------------------------------------------------------------------*/

	/** Гомдол гаргасан асуудал нарийвчилсан COLUMN нэрнүүд*/
	protected static final String COMPLAINT_SORT_ID = "COMPLAINT_SORT_ID";
	protected static final String COMPLAINT_SORT_NAME = "COMPLAINT_SORT_NAME";

	/** Гомдол гаргасан Дуудлагын үр дүн COLUMN нэрнүүд*/
	protected static final String COMPLAINT_RESULT_ID = "COMPLAINT_RESULT_ID";
	protected static final String COMPLAINT_RESULT_NAME = "COMPLAINT_RESULT_NAME";

	/** Гомдол гаргасан Дуудлагын үнэлгээ COLUMN нэрнүүд*/
	protected static final String COMPLAINT_RATE_ID = "COMPLAINT_RATE_ID";
	protected static final String COMPLAINT_RATE_NAME = "COMPLAINT_RATE_NAME";

	/** Гомдол гаргасан ЭХ ҮҮСВЭР*/
	protected static final String SOURCE_ID = "SOURCE_ID";
	protected static final String SOURCE_NAME = "SOURCE_NAME";

	/**-------------------------------------------------------------------------------------------------------------------------------------------*/
	/**-------------------------------------------------------------------------------------------------------------------------------------------*/

	/** Хүсэлт гаргасан Үр дүн COLUMN нэрнүүд */
	protected static final String APPEAL_RESULT_ID = "APPEAL_RESULT_ID";
	protected static final String APPEAL_RESULT_NAME = "APPEAL_RESULT_NAME";

	/** ЛХүсэлт гаргасан Ангилал COLUMN нэрнүүд */
	protected static final String CALL_SORT_ID = "CALL_SORT_ID";
	protected static final String CALL_SORT_NAME = "CALL_SORT_NAME";


	/**-------------------------------------------------------------------------------------------------------------------------------------------*/
	/**-------------------------------------------------------------------------------------------------------------------------------------------*/

	/** Лавлагаа төрөл COLUMN нэрнүүд */

	/**-------------------------------------------------------------------------------------------------------------------------------------------*/
	/**-------------------------------------------------------------------------------------------------------------------------------------------*/

	/**Холбоотой нэгж COLUMN нэрнүүд*/
	protected static final String UNIT_ID = "UNIT_ID";
	protected static final String UNIT_NAME = "UNIT_NAME";

	/**Дуудлагын статус COLUMN нэрнүүд*/
	protected static final String CALL_STAT_ID = "CALL_STAT_ID";
	protected static final String CALL_STAT_NAME = "CALL_STAT_NAME";

	/**Дуудлагын үр дүн COLUMN нэрнүүд*/
	protected static final String CALL_RESULT_ID = "CALL_RESULT_ID";
	protected static final String CALL_RESULT_NAME = "CALL_RESULT_NAME";
	
	/**Агентын дугаар COLUMN нэрнүүд*/
	protected static final String AGENT_REGISTER_ID = "AGENT_REGISTER";
	protected static final String AGENT_NAME = "AGENT_NAME";

	protected static final String PHONE_NUMBER_ONE = "PHONE_NUMBER_ONE";
	protected static final String PHONE_NUMBER_TWO = "PHONE_NUMBER_TWO";
	protected static final String TASK_ID = "TASK_ID";
	protected static final String TASKS = "TASKS";
	
	/**Дуудлагын үнэлгээ COLUMN нэрнүүд*/
	protected static final String CALL_RATE_ID = "CALL_RATE_ID";
	protected static final String CALL_RATE_NAME = "CALL_RATE_NAME";


	/**===========================================================================================================================================*/

	/** Мэдээлэл хүссэн дугаарын жагсаалт */
	protected HashMap<String, String> cmbInboundInfoReqList;

	/** Мэдээлэл хүссэн дугаарын жагсаалт нарийвчлал */
	protected HashMap<String, String> cmbInboundInfoReqTypeList;

	/** Мэдээлэл хүссэн дугаарын жагсаалт үр дүн */
	//protected HashMap<String, String> cmbInboundInfoReqResultList;

	/** Мэдээлэл хүссэн дугаарын жагсаалт үнэлгээ */
	//protected HashMap<String, String> cmbInboundInfoReqRateList;

	/**--------------------------------------------------------------------------------------------------------------------------------------------*/

	/** Дуудлагын төрөл Хүсэлт гаргасан Хүсэлт гаргасан асуудал*/
	protected HashMap<String, String> cmbInboundAppealList;

	/** Дуудлагын төрөл Хүсэлт гаргасан Хүсэлт гаргасан асуудалын нарийвчилсан төрлийг харуулна*/
	protected HashMap<String, String> cmbInboundAppealTypeList;

	/** Дуудлагын төрөл Хүсэлт гаргасан Дуудлагын үр дүн*/
	protected HashMap<String, String> cmbInboundAppealResultList;

	protected HashMap<String, String> cmbInboundAppealRateCompList;

	protected HashMap<String, String> cmbInboundAppealUnitApeList;
	
	protected HashMap<String, String> cmbInsert_agentList;
	
	protected HashMap<String, String> cmbCall_result_idList;
	
	protected HashMap<String, String> cmbCall_Out_result_idList;
	
	protected HashMap<String, String> cmbAgent_idList;
	
	
	/** Дуудлагын төрөл Хүсэлт гаргасан Дуудлагын үнэлгээ*/
	protected HashMap<String, String> cmbInboundAppealSortList;

	/** Холбоотой нэгж */
	protected HashMap<String, String> cmbUnitList;
	protected HashMap<String, String> cmbResultList;
	protected HashMap<String, String> cmbRateList;
	//protected HashMap<String, String> cmbSortList;

	/** Дуудлагын төрөл*/
	protected HashMap<String, String> cmbCallReqType;

	protected HashMap<String, String> cmbCallStatList;
	/**--------------------------------------------------------------------------------------------------------------------------------------------*/

	/** Мэдээлэл хүссэн дугаарын жагсаалт */
	protected HashMap<String, String> cmbInboundReferenceList;

	/** Мэдээлэл хүссэн дугаарын жагсаалт */
	protected HashMap<String, String> cmbInboundReferenceTypeList;
	
	protected HashMap<String, String> cmbCall_req_type_idList;

	/** Мэдээлэл хүссэн дугаарын жагсаалт */
	//protected HashMap<String, String> cmbInboundReferenceResultList;

	/** Мэдээлэл хүссэн дугаарын жагсаалт */
	//protected HashMap<String, String> cmbInboundReferenceRateList;

	/**--------------------------------------------------------------------------------------------------------------------------------------------*/

	/** Дуудлагын төрөл Гомдол үүсгэх Гомдол гаргасан асуудал */
	protected HashMap<String, String> cmbInboundComplaintList;

	/** Дуудлагын төрөл Гомдол үүсгэх Гомдол гаргасан асуудалын нарийвчилсан төрлийг харуулна */
	protected HashMap<String, String> cmbInboundComplaintTypeList;

	/** Дуудлагын төрөл Гомдол үүсгэх Гомдол гаргасан холбоотой нэгж */
	//protected HashMap<String, String> cmbInboundComplaintUnitList;

	/** Дуудлагын төрөл Гомдол үүсгэх Гомдол гаргасан дуудлагын ангилал */
	protected HashMap<String, String> cmbInboundComplaintSortList;

/*	*//** Дуудлагын төрөл Гомдол үүсгэх Гомдол гаргасан дуудлагын ангилал *//*
	protected HashMap<String, String> cmbOutboundComplaintSortList;
	protected HashMap<String, String> cmbOutboundComplaintSortList2;*/

	/** Дуудлагын төрөл Гомдол үүсгэх Гомдол гаргасан дуудлагын үр дүн */
	protected HashMap<String, String> cmbInboundComplaintResultList;

	/** Дуудлагын төрөл Гомдол үүсгэх Гомдол гаргасан дуудлагын үнэлгээ */
	protected HashMap<String, String> cmbInboundComplaintRateList;
	/** Гомдол гаргасан эх үүсвэрийн тайлбар */
	protected HashMap<String, String> cmbSourceList;

	 private static final String ERR010 = "ERR010";
		/**--------------------------------------------------------------------------------------------------------------------------------------------*/

	/** Дуудлагын төрөл Шилжүүлэг холбоотой нэгж*/
	//protected HashMap<String, String> cmbInboundTransferUnitList;

	/** Дуудлагын төрөл Шилжүүлэг Үр дүн*/
	//protected HashMap<String, String> cmbInboundTransferResultList;

	/** Дуудлагын төрөл Шилжүүлэг Үнэлгээ */
	//protected HashMap<String, String> cmbInboundTransferRateList;

	/**--------------------------------------------------------------------------------------------------------------------------------------------*/

	/** Дуудлагын төрөл Бусад холбоотой нэгж*/
	//protected HashMap<String, String> cmbInboundOtherUnitList;

	/** Дуудлагын төрөл Бусад Үр дүн*/
	//protected HashMap<String, String> cmbInboundOtherResultList;

	/** Дуудлагын төрөл Бусад Үнэлгээ */
	//protected HashMap<String, String> cmbInboundOtherRateList;

	/**===========================================================================================================================================*/
	/*inbound test*/

	/** Агентийн дугаар */
	protected String agentID ;

	protected String phone_number;

	protected String phone_number2;

	protected String callID;

    protected String date ;

    protected String time ;

    protected String type ;
    
    protected String callEnd ;
    
    /** */
	private String callreqtype ;

    protected String taskID;

    
	public String getCallreqtype() {
		return callreqtype;
	}

	public void setCallreqtype(String callreqtype) {
		this.callreqtype = callreqtype;
	}

	public String getTaskID() {
		return taskID;
	}

	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCallID() {
		return callID;
	}

	public void setCallID(String callID) {
		this.callID = callID;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPhone_number() {
		return this.phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}


	/////


	static String Inbound_ID;

	static String Inbound_Call;

	static String Inbound_date;

	static String Inbound_time;

	static String Inbound_type;
	
	static HashMap<Integer,Integer> call_Flag;
	
	static HashMap<String,String> call_Ending;
 	/**
 	 * Агентийн дугаар буцаана.<br>
	 * @return agentID
	 */
	public String getAgentID() {

		Map<String, Object> session = (Map<String, Object>)ActionContext.getContext().getSession();
		UserInfo info = (UserInfo)session.get(Constants.SESSION_USER_INFO) ;

		return  info.getAgentID() ;
	}

 	/**
 	 * Агентийн дугаар буцаана.<br>
	 * @return agentID
	 */
	public String getAgentEmail() {

		Map<String, Object> session = (Map<String, Object>)ActionContext.getContext().getSession();
		UserInfo info = (UserInfo)session.get(Constants.SESSION_USER_INFO) ;

		return  info.getEmail() ;
	}

	/**
 	 * Агентийн эрхийг буцаана.<br>
	 * @return permission
	 */
	public int getPermission() {

		Map<String, Object> session = (Map<String, Object>)ActionContext.getContext().getSession();
		UserInfo info = (UserInfo)session.get(Constants.SESSION_USER_INFO) ;

		return  info.getPermission() ;
	}

	public static String getInbound_ID() {
		return Inbound_ID;
	}

	public static void setInbound_ID(String inbound_ID) {
		Inbound_ID = inbound_ID;
	}

	public static String getInbound_date() {
		return Inbound_date;
	}

	public static void setInbound_date(String inbound_date) {
		Inbound_date = inbound_date;
	}

	public static String getInbound_time() {
		return Inbound_time;
	}

	public static void setInbound_time(String inbound_time) {
		Inbound_time = inbound_time;
	}

	public static String getInbound_type() {
		return Inbound_type;
	}

	public static void setInbound_type(String inbound_type) {
		Inbound_type = inbound_type;
	}

public XMLForm()
	{
	}
	/**=====================================================INBOUND INFOREQ======================================================================*/
		public HashMap<String, String> getCmbInboundInfoReqList() {
			if (cmbInboundInfoReqList == null)
			{
				try
				{
					cmbInboundInfoReqList = getCombo(Consts.SLCT001_PS_203, SERVICE_ID, SERVICE_NAME, null);
				}
				catch (Exception e)
				{
					log("Өгөгдлийн сан руу хандах үед алдаа гарлаа", e);
					cmbInboundInfoReqList = new HashMap<String, String>();
				}
			}
			return cmbInboundInfoReqList;
		}


	/**-----------------------------------------------------------------------------------------------------------------------------------------*/
		public HashMap<String, String> getCmbInboundInfoReqTypeList() {
			if (cmbInboundInfoReqTypeList == null)
			{
				try
				{
					cmbInboundInfoReqTypeList = getCombo(Consts.SLCT002_PS_203, SERVICE_ID, SERVICE_NAME, null);
				}
				catch (Exception e)
				{
					log("Өгөгдлийн сан руу хандах үед алдаа гарлаа", e);
					cmbInboundInfoReqTypeList = new HashMap<String, String>();
				}
			}
			return cmbInboundInfoReqTypeList;
		}

	/**-----------------------------------------------------------------------------------------------------------------------------------------*/


	/**-----------------------------------------------------------------------------------------------------------------------------------------*/

	/**=========================================================================================================================================*/

	/**=====================================================INBOUND REFERENCE===================================================================*/

		public HashMap<String, String> getCmbInboundReferenceList() {
			if (cmbInboundReferenceList == null)
			{
				try
				{
					cmbInboundReferenceList = getCombo(Consts.SLCT001_PS_202, SERVICE_ID, SERVICE_NAME, null);
				}
				catch (Exception e)
				{
					log("Өгөгдлийн сан руу хандах үед алдаа гарлаа", e);
					cmbInboundReferenceList = new HashMap<String, String>();
				}
			}
			return cmbInboundReferenceList;
		}
		
		public HashMap<String, String> getCmbInboundReferenceTypeList() {
			if (cmbInboundReferenceTypeList == null)
			{
				try
				{
					cmbInboundReferenceTypeList = getCombo(Consts.SLCT002_PS_202, SERVICE_ID, SERVICE_NAME, null);
				}
				catch (Exception e)
				{
					log("Өгөгдлийн сан руу хандах үед алдаа гарлаа", e);
					cmbInboundReferenceTypeList = new HashMap<String, String>();
				}
			}
			return cmbInboundReferenceTypeList;
		}




		/**-----------------------------------------------------------------------------------------------------------------------------------------*/

/*
		public HashMap<String, String> getCmbInboundReferenceResultList() {
			{
				try
				{
					cmbInboundReferenceResultList = getCombo(Consts.SLCT003_PS_202, CALL_RESULT_ID, CALL_RESULT_NAME, null);
				}
				catch (Exception e)
				{
					log("Өгөгдлийн сан руу хандах үед алдаа гарлаа", e);
					cmbInboundReferenceResultList = new HashMap<String, String>();
				}
			}
			return cmbInboundReferenceResultList;
		}
*/
		/**-----------------------------------------------------------------------------------------------------------------------------------------*/

		/**=========================================================================================================================================*/

		/**=====================================================INBOUND APPEAL===================================================================*/
		public HashMap<String, String> getCmbInboundAppealList()
		{
			if(cmbInboundAppealList == null)
			{
				try
				{
					cmbInboundAppealList = getCombo(Consts.SLCT001_PS_204, SERVICE_ID , SERVICE_NAME, null);
				}
				catch(Exception e)
				{
					log("Өгөгдлийн сан руу хандах үед алдаа гарлаа",e);
					cmbInboundAppealList = new HashMap<String, String>();
				}
			}
			return cmbInboundAppealList;
		}

		/**-----------------------------------------------------------------------------------------------------------------------------------------*/
		public HashMap<String, String> getCmbInboundAppealTypeList()
		{
			if(cmbInboundAppealTypeList == null)
			{
				try
				{
					cmbInboundAppealTypeList = getCombo(Consts.SLCT002_PS_204, SERVICE_ID , SERVICE_NAME, null);
				}
				catch(Exception e)
				{
					log("Өгөгдлийн сан руу хандах үед алдаа гарлаа",e);
					cmbInboundAppealTypeList = new HashMap<String, String>();
				}
			}
			return cmbInboundAppealTypeList;
		}

		/**-----------------------------------------------------------------------------------------------------------------------------------------*/
		public HashMap<String, String> getCmbInboundAppealResultList()
		{
			if(cmbInboundAppealResultList == null)
			{
				try
				{
					cmbInboundAppealResultList = getCombo(Consts.SLCT007_PS_204 , CALL_RESULT_ID, CALL_RESULT_NAME , null);
				}
				catch(Exception e)
				{
					log("Өгөгдлийн сан руу хандах үед алдаа гарлаа",e);
					cmbInboundAppealResultList = new HashMap<String, String>();
				}
			}
			return cmbInboundAppealResultList;
		}

		/**-----------------------------------------------------------------------------------------------------------------------------------------*/
		public HashMap<String, String> getCmbInboundAppealSortList()
		{
			if(cmbInboundAppealSortList == null)
			{
				cmbInboundAppealSortList = new HashMap<String, String>();
			}
			try
			{
				cmbInboundAppealSortList = getCombo(Consts.SLCT004_PS_204, CALL_SORT_ID, CALL_SORT_NAME, null);
			}
			catch(Exception e)
			{
				log("Өгөгдлийн сан руу хандах үед алдаа гарлаа",e);
			}
			return cmbInboundAppealSortList;
		}


		/**-----------------------------------------------------------------------------------------------------------------------------------------*/

		public HashMap<String, String> getCmbRateList() {
			if(cmbRateList == null)
			{
				try
				{
					cmbRateList = getCombo(Consts.SLCT006_PS_204, CALL_RATE_ID, CALL_RATE_NAME, null);
				}
				catch(Exception e)
				{
					log("Өгөгдлийн сан руу хандах үед алдаа гарлаа",e);
				}
			}
			return cmbRateList;
		}


		/**-----------------------------------------------------------------------------------------------------------------------------------------*/


	/**=========================================================================================================================================*/

	/**=====================================================INBOUND TRANSFER====================================================================*/

	/**-----------------------------------------------------------------------------------------------------------------------------------------*/

		public HashMap<String, String> getCmbResultList() {
			if (cmbResultList == null)
			{
				try
				{
					cmbResultList = getCombo(Consts.SLCT002_PS_205, CALL_RESULT_ID, CALL_RESULT_NAME, null);
				}
				catch (Exception e)
				{
					log("Өгөгдлийн сан руу хандах үед алдаа гарлаа", e);
					cmbResultList = new HashMap<String, String>();
				}
			}
			return cmbResultList;
		}

	/**-----------------------------------------------------------------------------------------------------------------------------------------*/

	/**=========================================================================================================================================*/

	/**=====================================================INBOUND COMPLAINT===================================================================*/

		public HashMap<String, String> getCmbInboundComplaintList() {
			if (cmbInboundComplaintList == null)
			{
				try
				{
					cmbInboundComplaintList = getCombo(Consts.SLCT001_PS_201, SERVICE_ID, SERVICE_NAME, null);
				}
				catch (Exception e)
				{
					log("Өгөгдлийн сан руу хандах үед алдаа гарлаа", e);
					cmbInboundComplaintList = new HashMap<String, String>();
				}
			}
			return cmbInboundComplaintList;
		}

	/**-----------------------------------------------------------------------------------------------------------------------------------------*/
		public HashMap<String, String> getCmbInboundComplaintTypeList() {
			if (cmbInboundComplaintTypeList == null)
			{
				try
				{
					cmbInboundComplaintTypeList = getCombo(Consts.SLCT002_PS_201, SERVICE_ID, SERVICE_NAME, null);
				}
				catch (Exception e)
				{
					log("Өгөгдлийн сан руу хандах үед алдаа гарлаа", e);
					cmbInboundComplaintTypeList = new HashMap<String, String>();
				}
			}
			return cmbInboundComplaintTypeList;
		}

		/*public void setCmbInboundComplaintTypeList(
				HashMap<String, String> cmbInboundComplaintTypeList) {
			this.cmbInboundComplaintTypeList = cmbInboundComplaintTypeList;
		}*/
	/**-----------------------------------------------------------------------------------------------------------------------------------------*/
		public HashMap<String, String> getCmbInboundComplaintRateList() {
			if (cmbInboundComplaintRateList == null)
			{
				try
				{
					cmbInboundComplaintRateList = getCombo(Consts.SLCT006_PS_201, CALL_RATE_ID, CALL_RATE_NAME, null);
				}
				catch (Exception e)
				{
					log("Өгөгдлийн сан руу хандах үед алдаа гарлаа", e);
					cmbInboundComplaintRateList = new HashMap<String, String>();
				}
			}
			return cmbInboundComplaintRateList;
		}

	/**-----------------------------------------------------------------------------------------------------------------------------------------*/
		public HashMap<String, String> getCmbInboundComplaintResultList() {
			if (cmbInboundComplaintResultList == null)
			{
				try
				{
					cmbInboundComplaintResultList = getCombo(Consts.SLCT005_PS_201, CALL_RESULT_ID, CALL_RESULT_NAME, null);
				}
				catch (Exception e)
				{
					log("Өгөгдлийн сан руу хандах үед алдаа гарлаа", e);
					cmbInboundComplaintResultList = new HashMap<String, String>();
				}
			}
			return cmbInboundComplaintResultList;
		}

	/**-----------------------------------------------------------------------------------------------------------------------------------------*/
		public HashMap<String, String> getCmbInboundComplaintSortList() {
			if (cmbInboundComplaintSortList == null)
			{
				try
				{
					cmbInboundComplaintSortList = getCombo(Consts.SLCT003_PS_201, CALL_SORT_ID, CALL_SORT_NAME, null);
				}
				catch (Exception e)
				{
					log("Өгөгдлийн сан руу хандах үед алдаа гарлаа", e);
					cmbInboundComplaintSortList = new HashMap<String, String>();
				}
			}
			return cmbInboundComplaintSortList;
		}
	/**-----------------------------------------------------------------------------------------------------------------------------------------*/
		public HashMap<String, String> getCmbSourceList() {
			if (cmbSourceList == null)
			{
				try
				{
					cmbSourceList = getCombo(Consts.SLCT008_PS_201, SOURCE_ID, SOURCE_NAME, null);
				}
				catch (Exception e)
				{
					log("Өгөгдлийн сан руу хандах үед алдаа гарлаа", e);
					cmbSourceList = new HashMap<String, String>();
				}
			}

			return cmbSourceList;
		}
	/**-----------------------------------------------------------------------------------------------------------------------------------------*/

	/**=========================================================================================================================================*/

	/**=========================================================INBOUND OTHER===================================================================*/

	/**-----------------------------------------------------------------------------------------------------------------------------------------*/

	/**-----------------------------------------------------------------------------------------------------------------------------------------*/

		/**-----------------------------------------------------------------------------------------------------------------------------------------*/
		public HashMap<String, String> getCmbUnitList()
		{
			if(cmbUnitList == null)
			{
				try
				{
					cmbUnitList = getCombo(Consts.SLCT003_PS_204, UNIT_ID, UNIT_NAME, null);
				}
				catch (Exception e)
				{
					log("Өгөгдлийн сан руу хандах үед алдаа гарлаа", e);
					cmbUnitList = new HashMap<String, String>();
				}
			}
			return cmbUnitList;
		}

	/**=========================================================================================================================================*/
		/**======================Дуудлагын төрөл===================================================================================================================*/
		public HashMap<String, String> getCmbCallReqType()
		{
			if(cmbCallReqType == null)
			{
				try
				{
					cmbCallReqType = getCombo(Consts.SLCT_CALLREQTYPE, CALL_REQ_TYPE_ID, CALL_REQ_TYPE_NAME, null);
				}
				catch (Exception e)
				{
					log("Өгөгдлийн сан руу хандах үед алдаа гарлаа", e);
					cmbCallReqType = new HashMap<String, String>();
				}
			}
			return cmbCallReqType;
		}
		/**=========================================================================================================================================*/

        public boolean toDatabase(String[] baseQuerys,
    			HashMap<String, Object> hsts, String successMsg, String errorMsg) {

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
    						sql = reader.findSQLByCommand(baseQuerys[i], hsts, true);
    						log(sql);
    						db.executeUpdate(sql);

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
    						addActionError(getText(ERR010));
    						break;
    					// case 1451: addActionError(getText(ERR035));
    					default:
    						log(getText(errorMsg), db.getLastException());
    						addActionMessage(getText(errorMsg));
    					}
    					return false;

    				} else {
    					db.commit();
    					log(getText(successMsg));
    					addActionMessage(getText(successMsg));
    					return true;
    				}

    			} catch (Exception e) {
    				// Log bichih?
    				log("Баазтай холбогдох алдаа", e);

    				return false;
    			} finally {
    				db.disconnect();
    			}

    		}

		public HashMap<String, String> getCmbInboundAppealRateCompList() {
			return cmbInboundAppealRateCompList;
		}

		public void setCmbInboundAppealRateCompList(
				HashMap<String, String> cmbInboundAppealRateCompList) {
			this.cmbInboundAppealRateCompList = cmbInboundAppealRateCompList;
		}

		public HashMap<String, String> getCmbInboundAppealUnitApeList() {
			return cmbInboundAppealUnitApeList;
		}

		public void setCmbInboundAppealUnitApeList(
				HashMap<String, String> cmbInboundAppealUnitApeList) {
			this.cmbInboundAppealUnitApeList = cmbInboundAppealUnitApeList;
		}

		public HashMap<String, String> getCmbCallStatList() {
			if(cmbCallStatList == null)
			{
				try
				{
					cmbCallStatList = getCombo(Consts.SLCT_CALL_STAT, CALL_STAT_ID, CALL_STAT_NAME, null);
				}
				catch (Exception e)
				{
					log("Өгөгдлийн сан руу хандах үед алдаа гарлаа", e);
					cmbCallStatList = new HashMap<String, String>();
				}
			}
			return cmbCallStatList;
		}

		public void setCmbCallStatList(HashMap<String, String> cmbCallStatList) {
			this.cmbCallStatList = cmbCallStatList;
		}

		/**
		 *
		 * @param sql
		 * @param pattern
		 * @param params
		 */
		public void buildWQuery(StringBuffer sql, String pattern, String[] params, boolean canEmpty)
		{
			if (!canEmpty)
			{
				for (String param : params)
				{
					if (isEmpty(param))
					{
						return;
					}
				}
			}

			int p = 0;
			for(int i=0; i<pattern.length(); i++)
			{
				char c = pattern.charAt(i);

				if (c == '?' && p < params.length)
				{
					sql.append(escStr(params[p]));
					p++;
				}
				else
				{
					sql.append(c);
				}
			}
		}

		public HashMap<String, String> getCmbCall_req_type_idList() {
			if (cmbCall_req_type_idList == null)
			{
				try
				{
					cmbCall_req_type_idList = getCombo(Consts.SLCT_CALL_TYPE_ID, SERVICE_ID, SERVICE_NAME, null);
				}
				catch (Exception e)
				{
					log("Өгөгдлийн сан руу хандах үед алдаа гарлаа", e);
					cmbCall_req_type_idList = new HashMap<String, String>();
				}
			}
			return cmbCall_req_type_idList;
		}

		public void setCmbCall_req_type_idList(HashMap<String, String> cmbCall_req_type_idList) {
			this.cmbCall_req_type_idList = cmbCall_req_type_idList;
		}

		public HashMap<String, String> getCmbInsert_agentList() {
			if (cmbInsert_agentList == null)
			{
				try
				{
					cmbInsert_agentList = getCombo(Consts.SLCT_INSERT_AGENT, AGENT_REGISTER, FIRSTNAME, null);
				}
				catch (Exception e)
				{
					log("Өгөгдлийн сан руу хандах үед алдаа гарлаа", e);
					cmbInsert_agentList = new HashMap<String, String>();
				}
			}
			return cmbInsert_agentList;
		}

		public void setCmbInsert_agentList(HashMap<String, String> cmbInsert_agentList) {
			this.cmbInsert_agentList = cmbInsert_agentList;
		}

		public HashMap<String, String> getCmbCall_result_idList() {
			if (cmbCall_result_idList == null)
			{
				try
				{
					cmbCall_result_idList = getCombo(Consts.SLCT_CALL_RES_ID, CALL_RESULT_ID, CALL_RESULT_NAME, null);
				}
				catch (Exception e)
				{
					log("Өгөгдлийн сан руу хандах үед алдаа гарлаа", e);
					cmbCall_result_idList = new HashMap<String, String>();
				}
			}
			return cmbCall_result_idList;
		}

		public void setCmbCall_result_idList(
				HashMap<String, String> cmbCall_result_idList) {
			this.cmbCall_result_idList = cmbCall_result_idList;
		}

		public HashMap<String, String> getCmbAgent_idList() {
			if (cmbAgent_idList == null)
			{
				try
				{
					cmbAgent_idList = getCombo(Consts.SLCT_AGENT_ID, AGENT_ID, AGENT_NAME, null);
				}
				catch (Exception e)
				{
					log("Өгөгдлийн сан руу хандах үед алдаа гарлаа", e);
					cmbAgent_idList = new HashMap<String, String>();
				}
			}
			return cmbAgent_idList;
		}

		public void setCmbAgent_idList(HashMap<String, String> cmbAgent_idList) {
			this.cmbAgent_idList = cmbAgent_idList;
		}

		/**
		 * @return
		 */


		public static String getInbound_Call() {
			return Inbound_Call;
		}

		public static void setInbound_Call(String inbound_Call) {
			Inbound_Call = inbound_Call;
		}

		public String getCallEnd() {
			return callEnd;
		}

		public void setCallEnd(String callEnd) {
			this.callEnd = callEnd;
		}

		public HashMap<String, String> getCmbCall_Out_result_idList() {
			if (cmbCall_Out_result_idList == null)
			{
				try
				{
		    		HashMap<String, Object> hsts = new HashMap<String, Object>();
					hsts.put(PHONE_NUMBER_ONE, getInbound_Call());
					cmbCall_Out_result_idList = getCombo(Constants.SLCT_OUTBOUND_CALL_RESULT_LIST, CALL_RESULT_ID, CALL_RESULT_NAME, hsts);
				}
				catch (Exception e)
				{
					log("Өгөгдлийн сан руу хандах үед алдаа гарлаа", e);
					cmbCall_Out_result_idList = new HashMap<String, String>();
				}
			}
			return cmbCall_Out_result_idList;
		}

		public void setCmbCall_Out_result_idList(
				HashMap<String, String> cmbCall_Out_result_idList) {
			this.cmbCall_Out_result_idList = cmbCall_Out_result_idList;
		}

		public static HashMap getCall_Flag() {
			return call_Flag;
		}
		public static void setCall_Flag(HashMap<Integer,Integer> call_Flag) {
			XMLForm.call_Flag = call_Flag;
		}

		public static void raiseFlag(Integer ownID,Integer flag){
			if(call_Flag==null){
				call_Flag=new HashMap<Integer, Integer>();
			}
			call_Flag.put(ownID, flag);
		}
		public static Integer getFlag(Integer ownID){
			if(call_Flag!=null){
				if(call_Flag.get(ownID)!=null){
				return call_Flag.get(ownID);
				}
			}
			return 10;
		}

		public static HashMap<String, String> getCall_Ending() {
			return call_Ending;
		}

		public static void setCall_Ending(HashMap<String, String> call_Ending) {
			XMLForm.call_Ending = call_Ending;
		}
		
		public static void setCallEnding(String ownID,String task_id){
			if(call_Ending==null){
				call_Ending=new HashMap<String, String>();
			}
			call_Ending.put(ownID, task_id);
		}
		public static String getCallEnding(String ownID){
			if(call_Ending!=null){
				if(call_Ending.get(ownID)!=null){
				return call_Ending.get(ownID);
				}
			}
			return "0";
		}
		
}
