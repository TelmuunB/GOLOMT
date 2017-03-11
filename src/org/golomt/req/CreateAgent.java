/*
* „M„Ђ„t„u„|„y„z„~ „~„Џ„‚             CreateAgent.java
*
* „U„…„~„{„€„Ќ„~ „~„Џ„‚          ЃyДэлгэцийн зохиомжЃz
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

import org.golomt.common.Constants;
import org.golomt.common.QueryConstants;
import org.golomt.common.XMLForm;
import org.iaac.common.BForm;
import org.iaac.common.Login;
import org.iaac.consts.Consts;

import com.opensymphony.xwork2.ActionContext;


/**
* @author 
*
*/
public class CreateAgent extends XMLForm 
{
	/** Хайлтын үр дүнг харуулах талбарууд */
	private static final String[] FIELD_LIST = { "LASTNAME", "FIRSTNAME","AGENT_REGISTER","AGENT_ID","AGENT_PERMISSION","AGENT_PERMISSION_NAME","EMAIL","A_PASSWORD","DELETE_USER"};
	public static final String QUERY_WHERE = "WHERE";
	
	/** хоосон хайлт */
	private static final String QUERY_BEGIN = " 1 = 1 ";
   /** „†„Ђ„‚„}„Ќ„~ „~„Џ„‚ */
   private static final String FORM_NAME = "CreateAgent.form_name" ;
   /**  */
   private static final String INFO_MESSAGE="CreateAgent.Хэрэглэгч амжилттай нэмэгдлээ";
   /**  */
   private static final String ERR_MESSAGE="CreateAgent.Өгөгдлөө зөв оруулна уу!";
   /**  */
   private static final String INFO_MESSAGE1="CreateAgent.Өгөгдлөө бөглөнө үү!";
   /**  */
   private static final String INFO_MESSAGE2="CreateAgent.Хэрэглэгч амжилттай устгагдлаа";
   
   private static final String LASTNAME="LASTNAME";
   
   private static final String FIRSTNAME="FIRSTNAME";
   
   private static final String AGENT_REGISTER = "AGENT_REGISTER";
   
   private static final String AGENT_ID = "AGENT_ID";
   
   private static final String AGENT_PERMISSION = "AGENT_PERMISSION";
   
   private static final String ORIGINAL_AGENT_ID="ORIGINAL_AGENT_ID";
   
   private static final String DELETE_DATE = "DELETE_DATE";
   
   private static final String DELETE_USER = "DELETE_USER";
   
   private static final String UPDATE_USER = "UPDATE_USER";
   
   private static final String UPDATE_DATE = "UPDATE_DATE";
   
   private static final String INSERT_USER = "INSERT_USER";
   
   private static final String INSERT_DATE = "INSERT_DATE";
   
   private static final String PASSWORD = "PASSWORD"; 
     
   private static final String EMAIL = "EMAIL";
   
   private static final String insert_success = "system.insert_success";
   
   private static final String insert_failed = "system.insert_failed";   
   
   private static final String ERR010 = "ERR010";
   
   private static final String ORDER_BY = " ORDER BY ";
   
   protected HashMap<String, String> cmbAgent_permissionList;
   
    /** Овог */
    private String lblStaticLast_name;
    /** Овог */
    private String txtLast_name;
    /** Нэр */
    private String lblStaticFirst_name;
    /** Нэр */
    private String txtFirst_name;
    /** Регистрийн дугаар */
    private String lblStaticRegister;
    /** Регистрийн дугаар */
    private String txtRegister;
    /** Агентын № */
    private String lblStaticAgent_id;
    /** Агентын № */
    private String txtAgent_id;
    
    private String hddOriginal_Id;
    /** Нууц үг */
    private String lblStaticPassword;
    /** Нууц үг */
    private String txtPassword;
    /** Хэрэглэгчийн эрх */
    private String lblStaticAgent_permission;
   /** Хэрэглэгчийн эрх */
    private String cmbAgent_permission;
    /** Email хаяг */
    private String lblStaticEmail;
    /** Email хаяг */
    private String txtEmail;
    /** Агентын Жагсаалт */
    private String grpAgent_list;
    /** Агентын Жагсаалт Хүснэгт */
    private String grdAgent_list;
    
    private int page;
    private String sidx;
    private String sord;
    private boolean searchClick;
    
    
 
    /**
     * „A„p„z„s„…„…„|„p„s„‰ „†„…„~„{„€
     */
    public CreateAgent()
    {
    }

    /* (non-Javadoc)
     * @see com.opensymphony.xwork2.ActionSupport#execute()
     */
    
    
   /**Continue*/ 
   /* public String execute()
    {
    	setRegisterQuery("SELECT LAST_NAME, FIRST_NAME, AGENT_REGISTER, AGENT_ID, PASSWORD, AGENT_PERMISSION_ID, EMAIL FROM AGENT");
       return SUCCESS; 
    }*/

    /* (non-Javadoc)
     * @see com.opensymphony.xwork2.ActionSupport#validate()
     */
    
    /**
	 * @return LIMIT_GRID
	 */
    
    /**
	 * Шүүлт хийж гарч ирсэн өгөгдлийг grid руу гаргана
	 */
    public String getXML()
	{
		StringBuffer where = new StringBuffer(QUERY_BEGIN);
		StringBuffer ord = new StringBuffer();
		//buildWQuery(where, "AGENT_ID", new String[] { getTxtAgent_id() });
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
		
		
			
			log(FORM_NAME);

			HashMap<String, Object> hsts = new HashMap<String, Object>();
			hsts.put(Constants.QUERY_WHERE, where);
			hsts.put(Constants.QUERY_ORDER, ord);
			DBAccess db = getDBAccess();
			db.connect();
			int totalResult = 0;
			try
			{
				SQLListReader reader = getSQLListReader();
				String sql = reader.findSQLByCommand(Consts.SLCT_CREATEAGENT, hsts, true);
				log(sql);
				ResultSet rs = db.select(sql);
				while (rs.next()){
					totalResult++;
				}
				rs=db.select(sql);
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

    
    /**
     * @param btnAddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd
     */
    public String onBtnAddClick()
    {
       HashMap<String,Object> tek=new HashMap<String,Object>();
       tek.put(AGENT_ID, escStrQNull(getTxtAgent_id()));
       tek.put(LASTNAME, escStrQNull(getTxtLast_name()));
       tek.put(FIRSTNAME, escStrQNull(getTxtFirst_name()));
       tek.put(AGENT_REGISTER, escStrQNull(getTxtRegister()));
       tek.put(PASSWORD, escStrQNull(getTxtPassword()));
       tek.put(EMAIL, escStrQNull(getTxtEmail()));
	   tek.put(AGENT_PERMISSION, escStrQNull(getCmbAgent_permission()));
       tek.put(INSERT_USER, escStrQNull(getAgentID()));
       tek.put(INSERT_DATE, escStrQNull(now()));
       String[] Querys = new String[1];
	   Querys[0] = Consts.INSRT_AGENT;
	   return toDatabase(Querys,tek,insert_success,insert_failed)?SUCCESS:ERROR; 
    }
    
    /**
     * @param btnEdit
     */
    public String onBtnEditClick()
    {   
    	HashMap<String,Object> hsts=new HashMap<String,Object>();
		hsts.put(AGENT_ID, escStrQNull(getTxtAgent_id()));
		hsts.put(FIRSTNAME, escStrQNull(getTxtFirst_name()));
		hsts.put(LASTNAME, escStrQNull(getTxtLast_name()));
		hsts.put(PASSWORD, escStrQNull(getTxtPassword()));
		hsts.put(AGENT_REGISTER, escStrQNull(getTxtRegister()));
		hsts.put(AGENT_PERMISSION, escStrQNull(getCmbAgent_permission()));
		hsts.put(EMAIL, escStrQNull(getTxtEmail()));
		hsts.put(ORIGINAL_AGENT_ID, escStrQNull(getHddOriginal_Id()));
		hsts.put(UPDATE_USER, escStrQNull(getAgentID()));
		hsts.put(UPDATE_DATE, escStrQNull(now()));
		String[] Querys = new String[1];
		Querys[0] = Consts.UPDT_AGENT;
    	return toDatabase(Querys,hsts,insert_success,insert_failed)?SUCCESS:ERROR; 
    }
    
    public boolean toDatabase(String[] baseQuerys,
		HashMap<String, Object> tek, String successMsg, String errorMsg) {

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
					sql = reader.findSQLByCommand(baseQuerys[i], tek, true);
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

    /**
     * @param btnClear
     */
    public String onBtnClearClick()
    { 	
       //CALL EVENT
       return SUCCESS; 
    }
   
    /**
     * @param btnDelete
     */
    public String onBtnDeleteClick()
    {
    	HashMap<String,Object> tek=new HashMap<String,Object>();
        tek.put(AGENT_ID, escStrQNull(getTxtAgent_id()));
        String[] Querys = new String[1];
 	   	Querys[0] = Consts.DELETE_AGENT;
 	   	return toDatabase(Querys,tek,insert_success,insert_failed)?SUCCESS:ERROR; 
    }

    /**
     * @return
     */
    
    
	public int getLimitGrid() {
		return LIMIT_GRID;
	}
    
    public void validate()
    {
    }

 
    /**
     * @return
     */
    
    public String getLblStaticLast_name() 
    {
       return lblStaticLast_name;
    }

    /**
     * @param lblStaticLast_name     */
    public void setLblStaticLast_name(String lblStaticLast_name)
    {
       this.lblStaticLast_name= lblStaticLast_name;
    }

    /**
     * @return
     */
    public String getTxtLast_name() 
    {
       return txtLast_name;
    }

    /**
     * @param txtLast_name     */
    public void setTxtLast_name(String txtLast_name)
    {
       this.txtLast_name= txtLast_name;
    }

    /**
     * @return
     */
    public String getLblStaticFirst_name() 
    {
       return lblStaticFirst_name;
    }

    /**
     * @param lblStaticFirst_name     */
    public void setLblStaticFirst_name(String lblStaticFirst_name)
    {
       this.lblStaticFirst_name= lblStaticFirst_name;
    }

    /**
     * @return
     */
    public String getTxtFirst_name() 
    {
       return txtFirst_name;
    }

    /**
     * @param txtFirst_name     */
    public void setTxtFirst_name(String txtFirst_name)
    {
       this.txtFirst_name= txtFirst_name;
    }

    /**
     * @return
     */
    public String getLblStaticRegister() 
    {
       return lblStaticRegister;
    }

    /**
     * @param lblStaticRegister     */
    public void setLblStaticRegister(String lblStaticRegister)
    {
       this.lblStaticRegister= lblStaticRegister;
    }

    /**
     * @return
     */
    public String getTxtRegister() 
    {
       return txtRegister;
    }

    /**
     * @param txtRegister     */
    public void setTxtRegister(String txtRegister)
    {
       this.txtRegister= txtRegister;
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
    public String getTxtAgent_id() 
    {
       return txtAgent_id;
    }

    /**
     * @param txtAgent_id     */
    public void setTxtAgent_id(String txtAgent_id)
    {
       this.txtAgent_id= txtAgent_id;
    }

    /**
     * @return
     */
    public String getLblStaticPassword() 
    {
       return lblStaticPassword;
    }

    /**
     * @param lblStaticPassword     */
    public void setLblStaticPassword(String lblStaticPassword)
    {
       this.lblStaticPassword= lblStaticPassword;
    }

    /**
     * @return
     */
    public String getTxtPassword() 
    {
       return txtPassword;
    }

    /**
     * @param txtPassword     */
    public void setTxtPassword(String txtPassword)
    {
       this.txtPassword= txtPassword;
    }

    /**
     * @return
     */
    public String getLblStaticAgent_permission() 
    {
       return lblStaticAgent_permission;
    }

    /**
     * @param lblStaticAgent_permission     */
    public void setLblStaticAgent_permission(String lblStaticAgent_permission)
    {
       this.lblStaticAgent_permission= lblStaticAgent_permission;
    }

    /**
     * @return
     */
    public String getCmbAgent_permission() 
    {
       return cmbAgent_permission ;
    }

    /**
     * @param cmbAgent_permission     */
    public void setCmbAgent_permission(String cmbAgent_permission)
    {
       this.cmbAgent_permission= cmbAgent_permission;
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
    
    public String getGrpAgent_list() 
    {
       return grpAgent_list;
    }

    /**
     * @param grpAgent_list     */
    public void setGrpAgent_list(String grpAgent_list)
    {
       this.grpAgent_list= grpAgent_list;
    }

    /**
     * @return
     */
    public String getGrdAgent_list() 
    {
       return grdAgent_list;
    }

    /**
     * @param grdAgent_list     */
    public void setGrdAgent_list(String grdAgent_list)
    {
       this.grdAgent_list= grdAgent_list;
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
	
	public boolean isSearchClick()
	{
		return searchClick;
	}

	/**
	 * @param searchClick the searchClick to set
	 */
	public void setSearchClick(boolean searchClick)
	{
		this.searchClick = searchClick;
	}

	public String getHddOriginal_Id() {
		return hddOriginal_Id;
	}

	public void setHddOriginal_Id(String hddOriginal_Id) {
		this.hddOriginal_Id = hddOriginal_Id;
	}

	public HashMap<String, String> getCmbAgent_permissionList() {
		
		if (cmbAgent_permissionList == null)
		{
			try
			{
				cmbAgent_permissionList = getCombo(Consts.AGENT_PERMISSION_CMB, "AGENT_PERMISSION_ID", "AGENT_PERMISSION_NAME", null);
			}
			catch (Exception e)
			{
				log("Өгөгдлийн сан руу хандах үед алдаа гарлаа", e);
				cmbAgent_permissionList = new HashMap<String, String>();
			}
		}
		return cmbAgent_permissionList;
	}

	public void setCmbAgent_permissionList(
			HashMap<String, String> cmbAgent_permissionList) {
		this.cmbAgent_permissionList = cmbAgent_permissionList;
	}

}
