/*
* „M„Ђ„t„u„|„y„z„~ „~„Џ„‚             ComplaintHistory.java
*
* „U„…„~„{„€„Ќ„~ „~„Џ„‚          ЃyДэлгэцийн зохиомжЃz
* „Svv„‡
* „V„…„r„y„|„q„p„‚„Ќ„~ ‡‚  „O„s„~„Ђ„Ђ    „V„p„‚„y„…„€„p„s„‰    „@„s„…„…„|„s„p
* 01.00.00                 „O„s„~„Ђ„Ђ    „V„p„‚„y„…„€„p„s„‰    „Y„y„~„Џ„Џ„‚ „‡„y„z„ѓ„Џ„~/„H„p„ѓ„ѓ„p„~
*/


package org.golomt.detail;


import infox.org.db.DBAccess;
import infox.org.sql.SQLListReader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.golomt.common.Constants;
import org.golomt.common.QueryConstants;
import org.golomt.common.XMLForm;
import org.iaac.consts.Consts;

import com.opensymphony.xwork2.ActionContext;


/**
* @author
*
*/
public class ComplaintHistory extends XMLForm
{
   /** „†„Ђ„‚„}„Ќ„~ „~„Џ„‚ */
   private static final String FORM_NAME = "ComplaintHistory.form_name" ;
   /**  */
   private static final String INFO_MESSAGE="ComplaintHistory.Бичлэг амжилттай засварлагдлаа";
    /** Хайлт */
    private String grpSearch;
    /** Утасны дугаар */
    private String lblStaticPhone_number;
    /** Утасны дугаар */
    private String txtPhone_number;
    /** Агентын № */
    private String lblStaticAgent_id;
   /** Агентын № */
    private String cmbAgent_id;
    /** Гомдол гаргасан асуудал */
    private String lblStaticComplaint_id;
   /** Гомдол гаргасан асуудал */
    private String cmbComplaint_id;
    /** Гомдлын нарийвчилсан төрөл */
    private String lblStaticComplaint_type_id;
   /** Гомдлын нарийвчилсан төрөл */
    private String cmbComplaint_type_id;
    /** Дуудлагын үнэлгээ */
    private String lblStaticComplaint_rate_id;
   /** Дуудлагын үнэлгээ */
    private String cmbComplaint_rate_id;
    /** Шийдвэрлэсэн огноо, цаг */
    private String lblStaticSolved_date;
    /** Шийдвэрлэсэн огноо */
    private String txtSolved_dateStart;   
    /** Шийдвэрлэсэн огноо */
    private String txtSolved_dateOver;
    /** Шийдвэрлэсэн огноо */
    private String txtSolved_date1;
    /** Холбоотой нэгж */
    private String lblStaticUnit_id;
   /** Холбоотой нэгж */
    private String cmbUnit_id;
    /** Дуудлагын үр дүн */
    private String lblStaticComplaint_result_id;
   /** Дуудлагын үр дүн */
    private String cmbComplaint_result_id;

    private String cmbComplaint_result_id_search;
    /** Шийдвэрлэгдсэн гомдлын жагсаалт */
    private String grpComplaint_list;
    /** Ирүүлсэн гомдлын жагсаалт */
    private String grdComing_complaint_list;
    /** Ирүүлсэн гомдлын тэмдэглэл */
    private String grpComplaint_comment;
    /** Гомдлын тэмдэглэлийн дэлгэрэнгүй текст */
    private String lblComplaint_comment;
    /** Холбоотой нэгж */
    private String lblUnit_id;
    /** Дуудлагын үнэлгээ */
    private String lblComplaint_sort_id;
    /** Имэйл хаяг */
    private String lblStaticEmail;
    /** Имейл хаяг */
    private String lblEmail;

    private String qString;

    private static final String COMPLAINT_LIST_ID="COMPLAINT_LIST_ID";

    private static final String CALLER_EMAIL="CALLER_EMAIL";

    private static final String SOLVED_COMMENT="SOLVED_COMMENT";

    private static final String SOLVED_DATE="SOLVED_DATE";

    private static final String UPDATE_USER="UPDATE_USER";

    private static final String UPDATE_DATE="UPDATE_DATE";

    private static final String DELETE_USER="DELETE_USER";
    
    private static final String DELETE_DATE="DELETE_DATE";
    
    private static final String EXPENSE="EXPENSE";

    private static final String COMPLAINT_RESULT_ID="COMPLAINT_RESULT_ID";

    private static final String COMPLAINT_SORT_ID="COMPLAINT_SORT_ID";

	public static final String QUERY_WHERE = "WHERE";

		/** хоосон хайлт */
	private static final String QUERY_BEGIN = "1 = 1";

    public static final String PHONE_NUMBER_S = " AND C.PHONE_NUMBER_ONE LIKE '%?%'";

    public static final String SERVICE_ID_S = " AND C.SERVICE_ID LIKE '%?%'";

    public static final String DETAILED_SERVICE_ID_S = " AND C.DETAILED_SERVICE_ID LIKE '%?%'";

    public static final String CALL_RATE_S = " AND C.CALL_RATE_ID LIKE '%?%'";

    public static final String UNIT_S = " AND C.UNIT_ID LIKE '%?%'";

    public static final String CALL_RESULT_S = " AND C.CALL_RESULT_ID LIKE '%?%'";

    public static final String AGENT_ID_S = " AND C.AGENT_ID LIKE '%?%'";

    public static final String SOLVED_DATE_S = " AND C.SOLVED_DATE = TO_TIMESTAMP('?','YYYY-MM-DD')";

    private static final String[] FIELD_LIST = { "PHONE_NUMBER","AGENT_NAME","SERVICE_NAME"
    	, "DETAILED_SERVICE_NAME", "CALL_RATE", "SOLVED_DATE", "UNIT_NAME", "CALL_RESULT","COMPLAINT_LIST_ID",
    	"CALLER_NAME","CALLER_EMAIL","SOLVED_COMMENT","COMPLAINT_COMMENT","EXPENSE","CALL_SORT_ID","CALL_RESULT_ID","SOURCE_NAME"};
    
    private static final String DATE_WHERE = " AND (C.SOLVED_DATE BETWEEN TO_TIMESTAMP('?', 'YYYY-MM-DD') AND TO_TIMESTAMP('?', 'YYYY-MM-DD'))";
    private static final String START_DATE = "1900-01-01";
    private static final String END_DATE = "2100-12-31";    
    private static final String ORDER_BY = " ORDER BY ";
    private int page;
    private String sidx;
    private String sord;
    private boolean searchClick;

    /** Гомдол барагдуулахад гарсан зардал */
    private String lblStaticComplaint_expense;
    /** Гомдол барагдуулахад гарсан зардал */
    private String txtComplaint_expense1;
    /** Дуудлагын ангилал */
    private String lblStaticComplaint_sort_id;
   /** Дуудлагын ангилал */
    private String cmbComplaint_sort_id;
    /** Ирүүлсэн гомдлыг шийдвэрлэсэн тэмдэглэл */
    private String lblStaticComplain_solved;
    /** Ирүүлсэн гомдлыг шийдвэрлэсэн тэмдэглэл */
    private String txtComplain_solved_note;

    HashMap<String,Object> getDataList1;

    String hddComplaint_id1;

    String txtEmail1;

    String txtSolved_comment;

    /**
     * „A„p„z„s„…„…„|„p„s„‰ „†„…„~„{„€
     */
    public ComplaintHistory()
    {
    }

    /* (non-Javadoc)
     * @see com.opensymphony.xwork2.ActionSupport#execute()
     */
    public String execute()
    {
       return SUCCESS;
    }

    /* (non-Javadoc)
     * @see com.opensymphony.xwork2.ActionSupport#validate()
     */
    public void validate()
    {
    }

    /**
     * @return
     */
    public String getGrpSearch()
    {
       return grpSearch;
    }

    /**
     * @param grpSearch     */
    public void setGrpSearch(String grpSearch)
    {
       this.grpSearch= grpSearch;
    }

    /**
     * @return
     */
    public String getLblStaticPhone_number()
    {
       return lblStaticPhone_number;
    }

    /**
     * @param lblStaticPhone_number     */
    public void setLblStaticPhone_number(String lblStaticPhone_number)
    {
       this.lblStaticPhone_number= lblStaticPhone_number;
    }

    /**
     * @return
     */
    public String getTxtPhone_number()
    {
       return txtPhone_number;
    }

    /**
     * @param txtPhone_number     */
    public void setTxtPhone_number(String txtPhone_number)
    {
       this.txtPhone_number= txtPhone_number;
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
    public String getCmbAgent_id()
    {
       return cmbAgent_id ;
    }

    /**
     * @param cmbAgent_id     */
    public void setCmbAgent_id(String cmbAgent_id)
    {
       this.cmbAgent_id= cmbAgent_id;
    }

    /**
     * @return
     */

    /**
     * @param lblStaticComplaint_id     */

    /**
     * @return
     */
    public String getCmbComplaint_id()
    {
       return cmbComplaint_id ;
    }

    /**
     * @param cmbComplaint_id     */
    public void setCmbComplaint_id(String cmbComplaint_id)
    {
       this.cmbComplaint_id= cmbComplaint_id;
    }

    /**
     * @return
     */
    public String getLblStaticComplaint_type_id()
    {
       return lblStaticComplaint_type_id;
    }

    /**
     * @param lblStaticComplaint_type_id     */
    public void setLblStaticComplaint_type_id(String lblStaticComplaint_type_id)
    {
       this.lblStaticComplaint_type_id= lblStaticComplaint_type_id;
    }

    /**
     * @return
     */
    public String getCmbComplaint_type_id()
    {
       return cmbComplaint_type_id ;
    }

    /**
     * @param cmbComplaint_type_id     */
    public void setCmbComplaint_type_id(String cmbComplaint_type_id)
    {
       this.cmbComplaint_type_id= cmbComplaint_type_id;
    }

    /**
     * @return
     */
    public String getLblStaticComplaint_rate_id()
    {
       return lblStaticComplaint_rate_id;
    }

    /**
     * @param lblStaticComplaint_rate_id     */
    public void setLblStaticComplaint_rate_id(String lblStaticComplaint_rate_id)
    {
       this.lblStaticComplaint_rate_id= lblStaticComplaint_rate_id;
    }

    /**
     * @return
     */
    public String getCmbComplaint_rate_id()
    {
       return cmbComplaint_rate_id ;
    }

    /**
     * @param cmbComplaint_rate_id     */
    public void setCmbComplaint_rate_id(String cmbComplaint_rate_id)
    {
       this.cmbComplaint_rate_id= cmbComplaint_rate_id;
    }

    /**
     * @return
     */

    /**
     * @param lblStaticSolved_date     */

    /**
     * @return
     */

    /**
     * @param txtSolved_date     */

    /**
     * @return
     */

    /**
     * @param lblStaticUnit_id     */

    /**
     * @return
     */
    public String getCmbUnit_id()
    {
       return cmbUnit_id ;
    }

    /**
     * @param cmbUnit_id     */
    public void setCmbUnit_id(String cmbUnit_id)
    {
       this.cmbUnit_id= cmbUnit_id;
    }

    /**
     * @return
     */

    /**
     * @param lblStaticComplaint_result_id     */

    /**
     * @return
     */

    /**
     * @param cmbComplaint_result_id     */

    /**
     * @param btnSearch
     */

    public String onBtnSearchClick()
    {
    	return INPUT;
    }

    public String getXML()
	{
		StringBuffer where = new StringBuffer(QUERY_BEGIN);
		//buildWQuery(where, "AGENT_ID", new String[] { getTxtAgent_id() });
		buildWQuery(where, PHONE_NUMBER_S, new String[] { getTxtPhone_number() });
		buildWQuery(where, AGENT_ID_S, new String[] { getCmbAgent_id() });
		buildWQuery(where, SERVICE_ID_S, new String[] { getCmbComplaint_id() });
		buildWQuery(where, DETAILED_SERVICE_ID_S, new String[] { getCmbComplaint_type_id() });
		buildWQuery(where, CALL_RATE_S, new String[] { getCmbComplaint_rate_id() });
		buildWQuery(where, UNIT_S, new String[] { getCmbUnit_id() });
		buildWQuery(where, CALL_RESULT_S, new String[] { getCmbComplaint_result_id_search() });
		
		if(getTxtSolved_dateStart()!=null){
			
			if(escStrQNull(getTxtSolved_dateOver())!="null"){
				buildWQuery(where, DATE_WHERE, new String[] { getTxtSolved_dateStart(), getTxtSolved_dateOver() });
	    		}
		}
	
		StringBuffer ord = new StringBuffer();
		if (page < 1)
		{
			page = 1;
		}
		/**
		 * Хайлтын үр дүнг эрэмбэлэх талбар,(өсөхөөр/буурахаар) хэсгийг хайлтын нөхцөлд залгах хэсэг
		 */
		if (!isEmpty(sidx) && !isEmpty(sord))
		{
			int i = 0;
			while (i < FIELD_LIST.length && !FIELD_LIST[i].equals(sidx))
				i++;
			if (i < FIELD_LIST.length && ("asc".equals(sord) || "desc".equals(sord)))
			{	
				ord.append(ORDER_BY);
				ord.append(sidx);
				ord.append(" ");
				ord.append(sord);
			}
		}

		if (isSearchClick())
		{
			log(FORM_NAME);

			HashMap<String, Object> hsts = new HashMap<String, Object>();
			hsts.put(Constants.QUERY_WHERE, where);
			hsts.put(Constants.QUERY_ORDER, ord);
			
			DBAccess db = getDBAccess();
			db.connect();
			try
			{
				SQLListReader reader = getSQLListReader();

				String sql = reader.findSQLByCommand(Consts.SEARCH_COMPLAINT_HISTORY_CNT_PS_301, hsts, true);

				ResultSet rs = db.select(sql);
				log(sql);
				int totalResult = 0;
				if (rs.next()) {
					totalResult = rs.getInt("CNT");
				}

				sql = reader.findSQLByCommand(Consts.SEARCH_COMPLAINT_HISTORY_PS_301, hsts, true);
				log(sql);
				rs = db.select(sql);
				xmlOutput(rs, page, ((totalResult - 1) / LIMIT_GRID + 1), totalResult, FIELD_LIST);
			    log(sql);
				return XML;
			}
			catch (Exception e)
			{
				log("Баазтай холбогдох алдаа", e);
				return ERROR;
			}
			finally
			{
				db.disconnect();
			}
		}
		else
		{

			return XML;
		}
	}

    public HashMap<String, String> getCmbServiceList() {
		HashMap<String, Object> hsts = new HashMap<String, Object>();

		hsts.put(Constants.FIELD_AGENT_ID, getAgentID()) ;
		hsts.put(Constants.FIELD_CALL_REQ_TYPE_ID, '4') ;

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
    public HashMap<String, String> getCmbAgentList() {
		try {
			return getCombo(Consts.SLCT_AGENT_LIST, Constants.FIELD_AGENT_ID, Constants.FIELD_AGENT_NAME, null);
		} catch (Exception e) {
			log("Өгөгдлийн сан руу хандах үед алдаа гарлаа", e);
			return new HashMap<String, String>();
		}
	}

    /**
     * @param btnClear
     */
    public String onBtnDeleteClick()
    {
    	HashMap<String,Object> hsts=new HashMap<String, Object>();
        hsts.put(DELETE_USER, escStrQNull(getAgentID()));
        hsts.put(DELETE_DATE, escStrQNull(now()));
        hsts.put(COMPLAINT_LIST_ID, escStrQNull(getHddComplaint_id1()));
        String[] Querys = new String[1];
        Querys[0] = Consts.DLT_COMPLAINT_PS_301;
        return toDatabase(Querys, hsts, "SUCCESS", "ERROR")?SUCCESS:ERROR;
    }

    /**
     * @return
     */
    public String getGrpComplaint_list()
    {
       return grpComplaint_list;
    }

    /**
     * @param grpComplaint_list     */
    public void setGrpComplaint_list(String grpComplaint_list)
    {
       this.grpComplaint_list= grpComplaint_list;
    }

    /**
     * @return
     */
    public String getGrdComing_complaint_list()
    {
       return grdComing_complaint_list;
    }

    /**
     * @param grdComing_complaint_list     */
    public void setGrdComing_complaint_list(String grdComing_complaint_list)
    {
       this.grdComing_complaint_list= grdComing_complaint_list;
    }

    /**
     * @return
     */
    public String getGrpComplaint_comment()
    {
       return grpComplaint_comment;
    }

    /**
     * @param grpComplaint_comment     */
    public void setGrpComplaint_comment(String grpComplaint_comment)
    {
       this.grpComplaint_comment= grpComplaint_comment;
    }

    /**
     * @return
     */
    public String getLblComplaint_comment()
    {
       return lblComplaint_comment;
    }

    /**
     * @param lblComplaint_comment     */
    public void setLblComplaint_comment(String lblComplaint_comment)
    {
       this.lblComplaint_comment= lblComplaint_comment;
    }

    /**
     * @return
     */

    /**
     * @param lblStaticUnit_id     */

    /**
     * @return
     */
    public String getLblUnit_id()
    {
       return lblUnit_id;
    }

    /**
     * @param lblUnit_id     */
    public void setLblUnit_id(String lblUnit_id)
    {
       this.lblUnit_id= lblUnit_id;
    }

    /**
     * @return
     */

    /**
     * @param lblStaticComplaint_id     */

    /**
     * @return
     */
    public String getLblComplaint_sort_id()
    {
       return lblComplaint_sort_id;
    }

    /**
     * @param lblComplaint_sort_id     */
    public void setLblComplaint_sort_id(String lblComplaint_sort_id)
    {
       this.lblComplaint_sort_id= lblComplaint_sort_id;
    }

    /**
     * @return
     */
    public String getLblStaticEmail()
    {
       return lblStaticEmail;
    }

    /**
     * @param lblStaticEmail     */
    public void setLblStaticEmail(String lblStaticEmail)
    {
       this.lblStaticEmail= lblStaticEmail;
    }

    /**
     * @return
     */
    public String getLblEmail()
    {
       return lblEmail;
    }

    /**
     * @param lblEmail     */
    public void setLblEmail(String lblEmail)
    {
       this.lblEmail= lblEmail;
    }

    /**
     * @return
     */

    /**
     * @param lblStaticComplaint_result_id     */

    /**
     * @return
     */

    /**
     * @param cmbComplaint_result_id     */

    /**
     * @return
     */

    /**
     * @param lblStaticSolved_date     */

    /**
     * @return
     */

    /**
     * @param txtSolved_date     */

    /**
     * @return
     */
    public String getLblStaticComplaint_expense()
    {
       return lblStaticComplaint_expense;
    }

    /**
     * @param lblStaticComplaint_expense     */
    public void setLblStaticComplaint_expense(String lblStaticComplaint_expense)
    {
       this.lblStaticComplaint_expense= lblStaticComplaint_expense;
    }

    /**
     * @return
     */
    public String getTxtComplaint_expense1()
    {
       return txtComplaint_expense1;
    }

    /**
     * @param txtComplaint_expense     */
    public void setTxtComplaint_expense1(String txtComplaint_expense1)
    {
       this.txtComplaint_expense1= txtComplaint_expense1;
    }

    /**
     * @return
     */
    public String getLblStaticComplaint_sort_id()
    {
       return lblStaticComplaint_sort_id;
    }

    /**
     * @param lblStaticComplaint_sort_id     */
    public void setLblStaticComplaint_sort_id(String lblStaticComplaint_sort_id)
    {
       this.lblStaticComplaint_sort_id= lblStaticComplaint_sort_id;
    }

    /**
     * @return
     */
    public String getCmbComplaint_sort_id()
    {
       return cmbComplaint_sort_id ;
    }

    /**
     * @param cmbComplaint_sort_id     */
    public void setCmbComplaint_sort_id(String cmbComplaint_sort_id)
    {
       this.cmbComplaint_sort_id= cmbComplaint_sort_id;
    }

    /**
     * @return
     */
    public String getLblStaticComplain_solved()
    {
       return lblStaticComplain_solved;
    }

    /**
     * @param lblStaticComplain_solved     */
    public void setLblStaticComplain_solved(String lblStaticComplain_solved)
    {
       this.lblStaticComplain_solved= lblStaticComplain_solved;
    }

    /**
     * @return
     */
    public String getTxtComplain_solved_note()
    {
       return txtComplain_solved_note;
    }

    /**
     * @param txtComplain_solved_note     */
    public void setTxtComplain_solved_note(String txtComplain_solved_note)
    {
       this.txtComplain_solved_note= txtComplain_solved_note;
    }

    /**
     * @param btnEdit
     */
    public String onBtnEditClick()
    {
    	HashMap<String, Object> hsts=new HashMap<String, Object>();
    	log(">>>>>>"+getTxtSolved_date1());
        hsts.put(COMPLAINT_LIST_ID, escStrQNull(getHddComplaint_id1()));
        hsts.put(CALLER_EMAIL,escStrQNull(getTxtEmail1()));
        hsts.put(CALL_RESULT_ID, escStrQNull(getCmbComplaint_result_id()));
        hsts.put(SOLVED_DATE, escStrQNull(getTxtSolved_date1()));
        hsts.put(EXPENSE, escStrQNull(getTxtComplaint_expense1()));
        hsts.put(CALL_SORT_ID, escStrQNull(getCmbComplaint_sort_id()));
        hsts.put(SOLVED_COMMENT, escStrQNull(getTxtSolved_comment()));
        hsts.put(UPDATE_USER, escStrQNull(getAgentID()));
        hsts.put(UPDATE_DATE, escStrQNull(now()));
        String[] Querys = new String[1];
        Querys[0] = Consts.UPDT_COMPLAINT_DETAILS_PS_301;
        if(chkMigrationReverse()==0){
     	   setComplaintAsUnSolved();
        }else if(chkMigrationReverse()==1){
        	sendComplaintToTask();
        }
        return toDatabase(Querys, hsts, "successMsg", "errorMsg")?SUCCESS:ERROR;
    }
    public Integer chkMigrationReverse(){
    	HashMap<String, Object> migrationMap=new HashMap<String, Object>();
        String[] Querys1 = new String[1];
        Querys1[0] = Consts.CHECK_MIGRATION_PS_301;
        migrationMap.put("CALL_RESULT_ID", escStrQNull(getCmbComplaint_result_id()));
	    return checkMigration(Querys1, migrationMap, "successMsg", "errorMsg");
    }
    public boolean sendComplaintToTask(){
        String[] Querys1 = new String[1];
    	   Querys1[0]=Consts.DLT_COMPLAINT_PS_301;
    	   HashMap<String, Object> moveMap=new HashMap<String, Object>();
    	   moveMap.put(DELETE_USER,escStrQNull(getAgentID()));
    	   moveMap.put(DELETE_DATE, escStrQNull(now()));
    	   moveMap.put(COMPLAINT_LIST_ID, escStrQNull(getHddComplaint_id1()));
    	   toDatabase(Querys1, moveMap," successMsg", "errorMsg");
    	   Querys1[0]=Consts.SEND_COMPLAINT_TO_TASK_PS_301;
    	   moveMap.clear();
    	   moveMap.put(COMPLAINT_LIST_ID,escStrQNull(getHddComplaint_id1()));
    	   return toDatabase(Querys1,moveMap,"successMsg","errorMsg");
    }
    public boolean setComplaintAsUnSolved(){
        String[] Querys1 = new String[1];
   	   Querys1[0]=Consts.SOLVE_COMPLAINT_PS_301;
   	   HashMap<String, Object> historyMap=new HashMap<String, Object>();
   	   historyMap.put("SOLVED_STATUS", '0');
   	   historyMap.put(COMPLAINT_LIST_ID, escStrQNull(getHddComplaint_id1()));
   	   return toDatabase(Querys1, historyMap," successMsg", "errorMsg");
     }
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
					sql = reader.findSQLByCommand(baseQuerys[i], hsts,true);
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
					addActionError(getText("ERR010"));
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
    public Integer checkMigration(String[] baseQuerys,
			HashMap<String, Object> hsts, String successMsg, String errorMsg) {
		Integer returnInt=0;
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
					sql = reader.findSQLByCommand(baseQuerys[i], hsts,true);
					log(sql);
					ResultSet rs=db.select(sql);
					while(rs.next()){
						returnInt=rs.getInt("MIGRATION_NUM");
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
				return returnInt;

			} else {
				db.commit();
				log(getText(successMsg));
				addActionMessage(getText(successMsg));
				return returnInt;
			}

		} catch (Exception e) {
			// Log bichih?
			log("Баазтай холбогдох алдаа", e);

			return 0;
		} finally {
			db.disconnect();
		}

	}
    public boolean isSearchClick()
	{
		return searchClick;
	}

	public String getCmbComplaint_result_id() {
		return cmbComplaint_result_id;
	}

	public void setCmbComplaint_result_id(String cmbComplaint_result_id) {
		this.cmbComplaint_result_id = cmbComplaint_result_id;
	}

	public String getQString() {
		return qString;
	}

	public void setQString(String string) {
		qString = string;
	}

	public void setSearchClick(boolean searchClick) {
		this.searchClick = searchClick;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
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

	public HashMap<String, Object> getGetDataList1() {
		return getDataList1;
	}

	public void setGetDataList1(HashMap<String, Object> getDataList1) {
		this.getDataList1 = getDataList1;
	}

	public String getHddComplaint_id1() {
		return hddComplaint_id1;
	}

	public void setHddComplaint_id1(String hddComplaint_id1) {
		this.hddComplaint_id1 = hddComplaint_id1;
	}

	public String getTxtEmail1() {
		return txtEmail1;
	}

	public void setTxtEmail1(String txtEmail1) {
		this.txtEmail1 = txtEmail1;
	}

	public String getTxtSolved_comment() {
		return txtSolved_comment;
	}

	public void setTxtSolved_comment(String txtSolved_comment) {
		this.txtSolved_comment = txtSolved_comment;
	}

	public String getTxtSolved_date1() {
		return txtSolved_date1;
	}

	public void setTxtSolved_date1(String txtSolved_date1) {
		this.txtSolved_date1 = txtSolved_date1;
	}

	public String getCmbComplaint_result_id_search() {
		return cmbComplaint_result_id_search;
	}

	public void setCmbComplaint_result_id_search(
			String cmbComplaint_result_id_search) {
		this.cmbComplaint_result_id_search = cmbComplaint_result_id_search;
	}

	public String getTxtSolved_dateStart() {
		return txtSolved_dateStart;
	}

	public void setTxtSolved_dateStart(String txtSolved_dateStart) {
		this.txtSolved_dateStart = txtSolved_dateStart;
	}

	public String getTxtSolved_dateOver() {
		return txtSolved_dateOver;
	}

	public void setTxtSolved_dateOver(String txtSolved_dateOver) {
		this.txtSolved_dateOver = txtSolved_dateOver;
	}



}
