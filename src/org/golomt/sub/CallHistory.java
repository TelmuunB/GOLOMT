/*
 * Моделийн нэр             CallHistory.java
 *
 * Функцын нэр          【Дуудлагын түүх】
 * Тvvх
 * Хувилбарын №     			Огноо        Хариуцагч         Агуулга
 * 					    01.00.00       2013/3/20   			Ж.Мөнгөнсүх    Шинээр хийсэн
 */
package org.golomt.sub;

import infox.org.db.DBAccess;
import infox.org.sql.SQLListReader;

import java.sql.ResultSet;
import java.util.HashMap;

import org.golomt.common.Constants;
import org.golomt.common.QueryConstants;
import org.iaac.consts.Consts;
import org.golomt.common.XMLForm;

/**
 * @author
 *
 */
public class CallHistory extends XMLForm {

	/** serialVersionUID */
	private static final long serialVersionUID = 0L;

	/** Хайлтын үр дүнг харуулах талбарууд */
	private static final String[] FIELD_LIST = { "CALL_HISTORY_ID", "PHONENUMBER", "CALLERNAME", "START_TIME", "FINISHED_TIME", "DURATION", "CALLTYPE", "AGENTNAME", "PHONENUMBER2", "VIEW_STATUS", "CALL_TYPE" };

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

	/** буурах эрэмбэлэлт */
	private static final String DESC = "desc";

	/** Агентын № */
	private String cmbAgent_id;

	/** Inbound */
	private String chkInbound;

	/** Outbound */
	private String chkOutbound;

	/** Утасны дугаар */
	private String txtPhone_number;

	/** 2 дахь дугаар */
	private String txtSecond_phone;

	/** Үйлчлүүлэгчийн нэр */
	private String txtCaller_name;

	/** Дуудлагын төрөл */
	private String cmbCall_req_type_id;

	/** Дуудлагын нэр */
	private String txtCall_name;

	/** */
	private String txtCallHistoryId;

	protected HashMap<String, String> cmbAgent_idList;
	
	/** Эрэмбэлэлтийн өсөх/буурах эсэхийг заагч талбарууд */
	private String sord;

	/** Эрэмбэлэлтийг заагч талбар */
	private String sidx;

	/** Хайлтыг харуулах хуудасны дугаар */
	private int page;

	/** */
	private String searchClick;
	
	private String permission;
	


	/**
	 * @return
	 */
	public HashMap<String, String> getCmbAgentList() {
		try {
			return getCombo(Consts.SLCT_AGENT_LIST, Constants.FIELD_AGENT_ID, Constants.FIELD_AGENT_NAME, null);
		} catch (Exception e) {
			log("Өгөгдлийн сан руу хандах үед алдаа гарлаа", e);
			return new HashMap<String, String>();
		}
	}

	/**
	 * @return
	 */
	public HashMap<String, String> getCmbCallTypeList() {
		try {
			return getCombo(Consts.SLCT_CALL_TYPE_LIST, Constants.FIELD_CALL_REQ_TYPE_ID, Constants.FIELD_CALL_REQ_TYPE_NAME, null);
		} catch (Exception e) {
			log("Өгөгдлийн сан руу хандах үед алдаа гарлаа", e);
			return new HashMap<String, String>();
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

	public String getXML() {
		StringBuffer where = new StringBuffer(QUERY_BEGIN);
		StringBuffer wheresub = new StringBuffer(QUERY_BEGIN);
		StringBuffer paging = new StringBuffer(QUERY_BEGIN);
		

		StringBuffer ord = new StringBuffer();

		buildWQuery(where, " AND " + Constants.FIELD_PHONENUMBER + " LIKE '%?%'", new String[] { getTxtPhone_number() });

		buildWQuery(where, " AND UPPER(" + Constants.FIELD_CALLERNAME + ") LIKE UPPER('%?%')", new String[] { getTxtCaller_name() });

		buildWQuery(wheresub, " AND UPPER(" + Constants.FIELD_CALL_NAME + ") LIKE UPPER('%?%')", new String[] { getTxtCall_name() });

		buildWQuery(wheresub, " AND " + Constants.FIELD_CALL_REQ_TYPE + " LIKE '%?%'", new String[] { getCmbCall_req_type_id() });

		buildWQuery(where, " AND " + Constants.FIELD_AGENT_ID + " LIKE '%?%'", new String[] { getCmbAgent_id() });
		
		
		
		if("true".equals(getChkInbound()) && "true".equals(getChkOutbound())){
			buildWQuery(where, " AND (" + Constants.FIELD_CALLTYPE + " = '?'", "0");
			buildWQuery(where, " OR " + Constants.FIELD_CALLTYPE + " = '?')", "1");
		}else{
			if ("true".equals(getChkInbound())) {
				buildWQuery(where, " AND " + Constants.FIELD_CALLTYPE + " = '?'", "0");
			}
	
			if ("true".equals(getChkOutbound())) {
				buildWQuery(where, " AND " + Constants.FIELD_CALLTYPE + " = '?'", "1");
			}
		}

		if (!isEmpty(getTxtCall_name()) || !isEmpty(getCmbCall_req_type_id())) {
			where.append(" AND (SELECT COUNT(*) FROM CALL_HISTORY_SERVICE CHS WHERE CHS.CALL_HISTORY_ID = VCH.CALL_HISTORY_ID AND ");
			where.append(wheresub);
			where.append(") > 0");
		}

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
		if (!isEmpty(searchClick)) {
			HashMap<String, Object> hsts = new HashMap<String, Object>();
			hsts.put(Constants.QUERY_WHERE, where);
			hsts.put(Constants.QUERY_ORDER, ord);
			DBAccess db = getDBAccess();
			db.connect();

			try {
				SQLListReader reader = getSQLListReader();

				String sql = reader.findSQLByCommand(QueryConstants.SLCT_CALL_HISTORY_CNT, hsts, true);

				ResultSet rs = db.select(sql);
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
		} else {
			return XML;
		}
	}

	/**
	 * Тухайн хайлтын хүснэгтийн XML бүтцийг бэлдэх
	 *
	 * @param sqlQuery XML болгож харуулах квери
	 * @param hsts квери руу дамжигдах параметрууд
	 */
	public String getXMLSub() {
		
		StringBuffer where = new StringBuffer(QUERY_BEGIN);
		StringBuffer ord = new StringBuffer();
		
		buildWQuery(where, " AND " + Constants.FIELD_CALL_HISTORY_ID + " = '?'", new String[] { getTxtCallHistoryId() });
		buildWQuery(where, " AND " + Constants.FIELD_CALL_NAME + " LIKE '%?%'", new String[] { getTxtCall_name() });
		buildWQuery(where, " AND " + Constants.FIELD_CALL_REQ_TYPE + " LIKE '%?%'", new String[] { getCmbCall_req_type_id() });

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

				rs = db.select(sql);
				log(sql) ;
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
	 * @return
	 */
	public String getCmbAgent_id() {
		return cmbAgent_id == null ? super.getAgentID() : cmbAgent_id ;
	}

	/**
	 * @param cmbAgent_id
	 */
	public void setCmbAgent_id(String cmbAgent_id) {
		this.cmbAgent_id = cmbAgent_id;
	}

	/**
	 * @return
	 */
	public String getCmbCall_req_type_id() {
		return cmbCall_req_type_id;
	}

	/**
	 * @param cmbCall_req_type_id
	 */
	public void setCmbCall_req_type_id(String cmbCall_req_type_id) {
		this.cmbCall_req_type_id = cmbCall_req_type_id;
	}

	/**
	 * @return
	 */
	public String getChkInbound() {
		return chkInbound;
	}

	/**
	 * @param chkInbound
	 */
	public void setChkInbound(String chkInbound) {
		this.chkInbound = chkInbound;
	}

	/**
	 * @return
	 */
	public String getChkOutbound() {
		return chkOutbound;
	}

	/**
	 * @param chkOutbound
	 */
	public void setChkOutbound(String chkOutbound) {
		this.chkOutbound = chkOutbound;
	}

	/**
	 * @return
	 */
	public String getTxtPhone_number() {
		return txtPhone_number;
	}

	/**
	 * @param txtPhone_number
	 */
	public void setTxtPhone_number(String txtPhone_number) {
		this.txtPhone_number = txtPhone_number;
	}

	/**
	 * @return
	 */
	public String getTxtSecond_phone() {
		return txtSecond_phone;
	}

	/**
	 * @param txtSecond_phone
	 */
	public void setTxtSecond_phone(String txtSecond_phone) {
		this.txtSecond_phone = txtSecond_phone;
	}

	/**
	 * @return
	 */
	public String getTxtCaller_name() {
		return txtCaller_name;
	}

	/**
	 * @param txtCaller_name
	 */
	public void setTxtCaller_name(String txtCaller_name) {
		this.txtCaller_name = txtCaller_name;
	}

	/**
	 * @return
	 */
	public String getTxtCall_name() {
		return txtCall_name;
	}

	/**
	 * @param txtCall_name
	 */
	public void setTxtCall_name(String txtCall_name) {
		this.txtCall_name = txtCall_name;
	}

	/**
	 * @param searchClick
	 */
	public void setSearchClick(String searchClick) {
		this.searchClick = searchClick;
	}

	public HashMap<String, String> getCmbAgent_idList() {
		if (cmbAgent_idList == null)
		{
			try
			{
				cmbAgent_idList = getCombo(Consts.SLCT_AGENT_ID, "AGENT_ID", "AGENT_NAME", null);
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
	 * @return txtCallHistoryId
	 */
	public String getTxtCallHistoryId() {
		return txtCallHistoryId;
	}

	/**
	 * @param txtCallHistoryId
	 *            セットする txtCallHistoryId
	 */
	public void setTxtCallHistoryId(String txtCallHistoryId) {
		this.txtCallHistoryId = txtCallHistoryId;
	}
	

	public String getAgentPermission()
	{	permission = Integer.toString(getPermission());
		return permission;
	}
}
