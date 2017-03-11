/*
* „M„Ђ„t„u„|„y„z„~ „~„Џ„‚             RegisterComplaint.java
*
* „U„…„~„{„€„Ќ„~ „~„Џ„‚          ЃyГомдлын бүртгэлЃz
* „Svv„‡
* „V„…„r„y„|„q„p„‚„Ќ„~ ‡‚  „O„s„~„Ђ„Ђ    „V„p„‚„y„…„€„p„s„‰    „@„s„…„…„|„s„p
* 01.00.00                 „O„s„~„Ђ„Ђ    „V„p„‚„y„…„€„p„s„‰    „Y„y„~„Џ„Џ„‚ „‡„y„z„ѓ„Џ„~/„H„p„ѓ„ѓ„p„~
*/


package org.golomt.req;


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
public class RegisterComplaint extends XMLForm
{
   /** „†„Ђ„‚„}„Ќ„~ „~„Џ„‚ */
   private static final String FORM_NAME = "RegisterComplaint.form_name" ;
   /**  */
   private static final String INFO_MESSAGE="RegisterComplaint.Амжилттай хадгаллаа";
   /**  */
   private static final String ERR_MESSAGE="RegisterComplaint.Өгөгдлөө зөв оруулна уу!";
   /**  */
   private static final String INFO_MESSAGE1="RegisterComplaint.Амжилттай устгагдлаа";
   /**  */
   private static final String INFO_MESSAGE2="RegisterComplaint.Устгах мөрөө хүснэгтээс сонгоно уу!";
    /** Ирүүлсэн гомдлын жагсаалт */

    private String grpComplaint_list;
    /** Ирүүлсэн гомдлын тэмдэглэл */
    private String grpComplaint_comment;
    /** Гомдлын тэмдэглэлийн дэлгэрэнгүй текст */
    private String lblComplaint_comment;
    /** Холбоотой нэгж */
    private String lblStaticUnit_id;
    /** Холбоотой нэгж */
    private String lblUnit_id;
    /** Дуудлагын ангилал */
    private String lblStaticCall_sort_id;
    /** Дуудлагын ангилал */
    private String lblCall_sort_id;
    /** Имэйл хаяг */
    private String lblStaticEmail;
    /** Имэйл хаяг */
    private String txtEmail;
    /** Дуудлагын үр дүн */
    private String lblStaticComplaint_result_id;
   /** Дуудлагын үр дүн */
    private String cmbComplaint_result_id;
    /** Шийдвэрлэсэн огноо, цаг */
    private String lblStaticSolved_date;
    /** Шийдвэрлэсэн огноо, цаг */
    private String txtSolved_date;
    /** Гомдол барагдуулахад гарсан зардал */
    private String lblStaticComplaint_expense;
    /** Гомдол барагдуулахад гарсан зардал */
    private String txtComplaint_expense;
    /** Дуудлагын ангилал */
    private String lblStaticComplaint_sort_id;
   /** Дуудлагын ангилал */
    private String cmbComplaint_sort_id;

    private String cmbComplaint_sort_id1;
    /** Ирүүлсэн гомдлыг шийдвэрлэсэн тэмдэглэл */
    private String lblStaticSolved_comment;
    /** Ирүүлсэн гомдлыг шийдвэрлэсэн тэмдэглэл */
    private String txtSolved_comment;

    private String txtPhone_number1;

    private String cmbComplaint_id;

    private String cmbComplaint_rate_id;

    private String cmbAgent_id;
    
    private String cmbAgent_id1;
    
    private String cmbComplaint_type_id;

    private String hddComplaint_id;

    private String qString;

    private static final String COMPLAINT_LIST_ID="COMPLAINT_LIST_ID";
    
    private static final String SOLVED_STATUS="SOLVED_STATUS";

    private static final String CALLER_EMAIL="CALLER_EMAIL";

    private static final String COMPLAINT_COMMENT="COMPLAINT_COMMENT";

    private static final String CALL_RESULT_ID="CALL_RESULT_ID";

    private static final String SOLVED_DATE="SOLVED_DATE";

    private static final String SOLVED_COMMENT="SOLVED_COMMENT";

    private static final String EXPENSE="EXPENSE";

    private static final String CALL_SORT_ID="CALL_SORT_ID";

    private static final String UPDATE_USER="UPDATE_USER";

    private static final String UPDATE_DATE="UPDATE_DATE";

    private static final String DELETE_USER="DELETE_USER";

    private static final String DELETE_DATE="DELETE_DATE";

	public static final String QUERY_WHERE = "WHERE";

	/** Хоосон зай */
	private static final String SPACE = " ";

		/** хоосон хайлт */
	private static final String QUERY_BEGIN = "1 = 1";

	/** Эрэмбэлэх талбарын */
	private static final String ORDER_BY = " ORDER BY ";

	/** Өгсөх эрэмбэлэлт */
	private static final String ASC = "asc";

	/** буурах эрэмбэлэлт */
	private static final String DESC = "desc";


    public static final String PHONE_NUMBER_S = " AND C.PHONE_NUMBER_ONE LIKE '%?%'";

    public static final String SERVICE_ID_S = " AND C.SERVICE_ID LIKE '%?%'";

    public static final String DETAILED_SERVICE_ID_S = " AND C.DETAILED_SERVICE_ID LIKE '%?%'";

    public static final String CALL_RATE_S = " AND C.CALL_RATE_ID LIKE '%?%'";

    public static final String CALL_SORT_S = " AND C.CALL_SORT_ID LIKE '%?%'";

    public static final String AGENT_ID_S = " AND C.AGENT_ID LIKE '%?%'";

    private static final String[] FIELD_LIST = { "PHONE_NUMBER","AGENT_NAME",
    	"SERVICE_NAME", "DETAILED_SERVICE_NAME", "CALL_RATE","CALL_SORT","DEADLINE", "AGENT_ID","COMPLAINT_LIST_ID",
    	"CALLER_EMAIL","CALLER_NAME","SOLVED_DATE","EXPENSE","CALL_SORT_ID","CALL_RESULT_ID"
    	,"COMPLAINT_COMMENT","SOLVED_COMMENT","UNIT_NAME","INSERT_DATE","SOLVED_STATUS","SOURCE_NAME"};

    private int page;
    private String sidx;
    private String sord;
    private boolean searchClick;


    private String chosenId;
    HashMap<String,Object> getDataList;
    
    private String agent_permission;

    

	/**
     * „A„p„z„s„…„…„|„p„s„‰ „†„…„~„{„€
     */
    public RegisterComplaint()
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



    public String getXML()
	{
		StringBuffer where = new StringBuffer(QUERY_BEGIN);
		//buildWQuery(where, "AGENT_ID", new String[] { getTxtAgent_id() });
		buildWQuery(where, PHONE_NUMBER_S, new String[] { getTxtPhone_number1() });
		buildWQuery(where, AGENT_ID_S, new String[] { getCmbAgent_id1() });
		buildWQuery(where, SERVICE_ID_S, new String[] { getCmbComplaint_id() });
		buildWQuery(where, DETAILED_SERVICE_ID_S, new String[] { getCmbComplaint_type_id() });
		buildWQuery(where, CALL_RATE_S, new String[] { getCmbComplaint_rate_id() });
		buildWQuery(where, CALL_SORT_S, new String[] { getCmbComplaint_sort_id1() });

		StringBuffer ord = new StringBuffer();
		if (page < 1) {
			page = 1;
		}

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

				String sql = reader.findSQLByCommand(Consts.SEARCH_COMPLAINT_CNT_PS_301, hsts, true);

				ResultSet rs = db.select(sql);
				log(sql);
				int totalResult = 0;
				if (rs.next()) {
					totalResult = rs.getInt("CNT");
				}

				sql = reader.findSQLByCommand(Consts.SEARCH_COMPLAINT_PS_301, hsts, true);
				log(sql);
				rs = db.select(sql);
				xmlOutput(rs, page, ((totalResult - 1) / LIMIT_GRID + 1), totalResult, FIELD_LIST);
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

    /**
     * @param btnSave
     */
    public String onBtnSaveClick()
    {
       HashMap<String, Object> hsts=new HashMap<String, Object>();
       hsts.put(COMPLAINT_LIST_ID, escStrQNull(getHddComplaint_id()));
       hsts.put(CALLER_EMAIL, escStrQNull(getTxtEmail()));
       hsts.put(AGENT_ID, escStrQNull(getCmbAgent_id()));
       hsts.put(CALL_RESULT_ID, escStrQNull(getCmbComplaint_result_id()));
       hsts.put(SOLVED_DATE, escStrQNull(getTxtSolved_date()));
       hsts.put(EXPENSE, escStrQNull(getTxtComplaint_expense()));
       hsts.put(CALL_SORT_ID, escStrQNull(getCmbComplaint_sort_id()));
       hsts.put(SOLVED_COMMENT, escStrQNull(getTxtSolved_comment()));
       hsts.put(UPDATE_USER, escStrQNull(getAgentID()));
       hsts.put(UPDATE_DATE, escStrQNull(now()));
       String[] Querys = new String[1];
       Querys[0] = Consts.UPDT_COMPLAINT_DETAILS_PS_301;
       if(chkMigration()==2){
    	   setComplaintAsSolved();
       }else if(chkMigration()==1){
    	   sendComplaintToTask();
       }
       return toDatabase(Querys, hsts, "successMsg", "errorMsg")?SUCCESS:ERROR;
    }
    public Integer chkMigration(){
    	HashMap<String, Object> migrationMap=new HashMap<String, Object>();
        String[] Querys1 = new String[1];
        Querys1[0] = Consts.CHECK_MIGRATION_PS_301;
        migrationMap.put("CALL_RESULT_ID", escStrQNull(getCmbComplaint_result_id()));
	    return checkMigration(Querys1, migrationMap, "successMsg", "errorMsg");
    }
    public boolean setComplaintAsSolved(){
       String[] Querys1 = new String[1];
  	   Querys1[0]=Consts.SOLVE_COMPLAINT_PS_301;
  	   HashMap<String, Object> historyMap=new HashMap<String, Object>();
  	   historyMap.put(SOLVED_STATUS, '1');
  	   historyMap.put(COMPLAINT_LIST_ID, escStrQNull(getHddComplaint_id()));
  	   return toDatabase(Querys1, historyMap," successMsg", "errorMsg");
    }
    public boolean sendComplaintToTask(){
    	   String[] Querys1 = new String[1];
    	   Querys1[0]=Consts.SOLVE_COMPLAINT_PS_301;
    	   HashMap<String, Object> moveMap=new HashMap<String, Object>();
    	   moveMap.put(SOLVED_STATUS, '2');
    	   moveMap.put(COMPLAINT_LIST_ID, escStrQNull(getHddComplaint_id()));
    	   toDatabase(Querys1, moveMap," successMsg", "errorMsg");
    	   Querys1[0]=Consts.SEND_COMPLAINT_TO_TASK_PS_301;
	 	   moveMap.clear();
	 	   moveMap.put(COMPLAINT_LIST_ID,escStrQNull(getHddComplaint_id()));
	 	   return toDatabase(Querys1,moveMap,"successMsg","errorMsg");
     }
     
     
    /**
     * @param btnDelete
     */
    public String onBtnDeleteClick()
    {
       HashMap<String,Object> hsts=new HashMap<String, Object>();
       hsts.put(DELETE_USER, escStrQNull(getAgentID()));
       hsts.put(DELETE_DATE, escStrQNull(now()));
       hsts.put(COMPLAINT_LIST_ID, escStrQNull(getHddComplaint_id()));
       String[] Querys = new String[1];
       Querys[0] = Consts.DLT_COMPLAINT_PS_301;
       return toDatabase(Querys, hsts, "SUCCESS", "ERROR")?SUCCESS:ERROR;
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
     * @return
     */

    /**
     * @param grpComplaint_comment     */

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
    public String getLblStaticUnit_id()
    {
       return lblStaticUnit_id;
    }

    /**
     * @param lblStaticUnit_id     */
    public void setLblStaticUnit_id(String lblStaticUnit_id)
    {
       this.lblStaticUnit_id= lblStaticUnit_id;
    }

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
    public String getLblCall_sort_id()
    {
       return lblCall_sort_id;
    }

    /**
     * @param lblCall_sort_id     */
    public void setLblCall_sort_id(String lblCall_sort_id)
    {
       this.lblCall_sort_id= lblCall_sort_id;
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
    public String getTxtEmail()
    {
       return txtEmail;
    }

    /**
     * @param txtEmail     */
    public void setTxtEmail(String txtEmail)
    {
       this.txtEmail= txtEmail;
    }

    /**
     * @return
     */
    public String getLblStaticComplaint_result_id()
    {
       return lblStaticComplaint_result_id;
    }

    /**
     * @param lblStaticComplaint_result_id     */
    public void setLblStaticComplaint_result_id(String lblStaticComplaint_result_id)
    {
       this.lblStaticComplaint_result_id= lblStaticComplaint_result_id;
    }

    /**
     * @return
     */
    public String getCmbComplaint_result_id()
    {
       return cmbComplaint_result_id ;
    }

    /**
     * @param cmbComplaint_result_id     */
    public void setCmbComplaint_result_id(String cmbComplaint_result_id)
    {
       this.cmbComplaint_result_id= cmbComplaint_result_id;
    }

    /**
     * @return
     */
    public String getLblStaticSolved_date()
    {
       return lblStaticSolved_date;
    }

    /**
     * @param lblStaticSolved_date     */
    public void setLblStaticSolved_date(String lblStaticSolved_date)
    {
       this.lblStaticSolved_date= lblStaticSolved_date;
    }

    /**
     * @return
     */
    public String getTxtSolved_date()
    {
       return txtSolved_date;
    }

    /**
     * @param txtSolved_date     */
    public void setTxtSolved_date(String txtSolved_date)
    {
       this.txtSolved_date= txtSolved_date;
    }

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
    public String getTxtComplaint_expense()
    {
       return txtComplaint_expense;
    }

    /**
     * @param txtComplaint_expense     */
    public void setTxtComplaint_expense(String txtComplaint_expense)
    {
       this.txtComplaint_expense= txtComplaint_expense;
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

    public String getTxtSolved_comment()
    {
       return txtSolved_comment;
    }

    /**
     * @param txtSolved_comment     */
    public void setTxtSolved_comment(String txtSolved_comment)
    {
       this.txtSolved_comment= txtSolved_comment;
    }


    /**
     * @param btnVoice
     */
    public String onBtnVoiceClick()
    {
       //CALL EVENT
       return SUCCESS;
    }

	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}

	public boolean isSearchClick() {
		return searchClick;
	}


	public void setSearchClick(boolean searchClick) {
		this.searchClick = searchClick;
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

	public String getQString() {
		return qString;
	}

	public void setQString(String string) {
		qString = string;
	}

	public String getCmbAgent_id() {
		return cmbAgent_id;
	}

	public void setCmbAgent_id(String cmbAgent_id) {
		this.cmbAgent_id = cmbAgent_id;
	}

	public String getCmbComplaint_id() {
		return cmbComplaint_id;
	}

	public void setCmbComplaint_id(String cmbComplaint_id) {
		this.cmbComplaint_id = cmbComplaint_id;
	}

	public String getCmbComplaint_rate_id() {
		return cmbComplaint_rate_id;
	}

	public void setCmbComplaint_rate_id(String cmbComplaint_rate_id) {
		this.cmbComplaint_rate_id = cmbComplaint_rate_id;
	}

	public String getCmbComplaint_type_id() {
		return cmbComplaint_type_id;
	}

	public void setCmbComplaint_type_id(String cmbComplaint_type_id) {
		this.cmbComplaint_type_id = cmbComplaint_type_id;
	}

	public String getTxtPhone_number1() {
		return txtPhone_number1;
	}

	public void setTxtPhone_number1(String txtPhone_number) {
		this.txtPhone_number1 = txtPhone_number;
	}

	public HashMap<String, Object> getGetDataList() {
		return getDataList;
	}

	public void setGetDataList(HashMap<String, Object> getDataList) {
		this.getDataList = getDataList;
	}

	public String getHddComplaint_id() {
		return hddComplaint_id;
	}

	public void setHddComplaint_id(String hddComplaint_id) {
		this.hddComplaint_id = hddComplaint_id;
	}

	public String getChosenId() {
		return chosenId;
	}

	public void setChosenId(String chosenId) {
		this.chosenId = chosenId;
	}

	public String getCmbComplaint_sort_id1() {
		return cmbComplaint_sort_id1;
	}

	public void setCmbComplaint_sort_id1(String cmbComplaint_sort_id1) {
		this.cmbComplaint_sort_id1 = cmbComplaint_sort_id1;
	}

	public String getCmbAgent_id1() {
		return cmbAgent_id1;
	}

	public void setCmbAgent_id1(String cmbAgent_id1) {
		this.cmbAgent_id1 = cmbAgent_id1;
	}
	
	public String getAgent_permission() {
		agent_permission=Integer.toString(getPermission());
		return agent_permission;
	}

	public void setAgent_permission(String agent_permission) {
		this.agent_permission = agent_permission;
	}
}
