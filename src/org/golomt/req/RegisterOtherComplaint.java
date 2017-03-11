/*
* „M„Ђ„t„u„|„y„z„~ „~„Џ„‚             RegisterOtherComplaint.java
*
* „U„…„~„{„€„Ќ„~ „~„Џ„‚          ЃyБусад гомдлын бүртгэлЃz
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
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.golomt.common.Constants;
import org.golomt.common.QueryConstants;
import org.golomt.common.XMLForm;
import org.iaac.consts.Consts;

/**
* @author
*
*/
public class RegisterOtherComplaint extends XMLForm
{	/** Бусад гомдол бүртгэх COLUMN нэрнүүд */


	/*private static final String CALL_REQ_TYPE_ID = "CALL_REQ_TYPE_ID";
	private static final String SERVICE_TYPE = "SERVICE_TYPE";
	private static final String CALL_HISTORY_ID = "CALL_HISTORY_ID";
	private static final String DETAILED_SERVICE_TYPE = "DETAILED_SERVICE_TYPE";*/
	private static final String PHONE_NUMBER_ONE = "PHONE_NUMBER_ONE";
	private static final String CALLER_NAME="CALLER_NAME";
	private static final String SERVICE_ID="SERVICE_ID";
	private static final String DETAILED_SERVICE_ID="DETAILED_SERVICE_ID";
	private static final String UNIT_ID="UNIT_ID";
	private static final String CALL_SORT_ID="CALL_SORT_ID";
	private static final String CALL_RATE_ID="CALL_RATE_ID";
	private static final String CALL_RESULT_ID="CALL_RESULT_ID";
	private static final String CALLER_EMAIL="CALLER_EMAIL";
	private static final String SOURCE_ID="SOURCE_ID";
	private static final String COMPLAINT_COMMENT="COMPLAINT_COMMENT";
	private static final String INSERT_USER="INSERT_USER";
	private static final String INSERT_DATE="INSERT_DATE";
	private static final String AGENT_ID="AGENT_ID";
	private static final String COMPLAINT_LIST_ID="COMPLAINT_LIST_ID";

	/** Бичлэг амжилттай нэмэгдлээ */
	private static final String INSERT_SUCCESS = "system.insert_success";

	/** Бичлэг нэмэх үед алдаа гарлаа */
	private static final String INSERT_FAILED = "system.insert_failed";

	private static final String ERR010 = "ERR010";
   /**
	 *
	 */
	private static final long serialVersionUID = 1L;
/** „†„Ђ„‚„}„Ќ„~ „~„Џ„‚ */
   private static final String FORM_NAME = "RegisterOtherComplaint.form_name" ;
   /**  */
   private static final String INFO_MESSAGE="RegisterOtherComplaint.Амжилттай бүртгэгдлээ";
   /**  */
   private static final String ERR_MESSAGE="RegisterOtherComplaint.Өгөгдлөө зөв оруулна уу!";
   /**  */
   private static final String INFO_MESSAGE1 ="RegisterOtherComplaint.Талбарын утга хоосон байж болохгүй! ";
    /** Бүртгэл */
    private String grpOther_complaint_registration;
    /** Утасны дугаар */
    private String lblStaticPhone_number;
    /** Утасны дугаар */
    private String txtPhone_number;
    /** Үйлчлүүлэгчийн нэр */
    private String lblStaticCaller_name;
    /** Үйлчлүүлэгчийн нэр */
    private String txtCaller_name;
    /** Эх үүсвэр */
    private String lblStaticSource;
   /** Эх үүсвэр */
    private String cmbSource;
    /** Гомдол гаргасан асуудал */
    private String lblStaticComplaint_id;
   /** Гомдол гаргасан асуудал */
    private String cmbComplaint_id;
    /** Гомдлын нарийвчилсан төрөл */
    private String lblStaticComplaint_type_id;
   /** Гомдлын нарийвчилсан төрөл */
    private String cmbComplaint_type_id;
    /** Холбоотой нэгж */
    private String lblStaticUnit_id;
   /** Холбоотой нэгж */
    private String cmbUnit_id;
    /** Дуудлагын ангилал */
    private String lblStaticComplaint_sort_id;
   /** Дуудлагын ангилал */
    private String cmbComplaint_sort_id;
    /** Дуудлагын үнэлгээ */
    private String lblStaticComplaint_rate_id;
   /** Дуудлагын үнэлгээ */
    private String cmbComplaint_rate_id;
    /** Дуудлагын үр дүн */
    private String lblStaticComplaint_result_id;
   /** Дуудлагын үр дүн */
    private String cmbComplaint_result_id;
    /** Имэйл хаяг */
    private String lblStaticEmail;
    /** Имэйл хаяг */
    private String txtEmail;

    private String unit_id;
    /** Ирүүлсэн гомдлын тэмдэглэл */
    private String txtComplaint_comment;

    private String insert_message;
    /**
     * „A„p„z„s„…„…„|„p„s„‰ „†„…„~„{„€
     */
    public RegisterOtherComplaint()
    {
    }

    /* (non-Javadoc)
     * @see com.opensymphony.xwork2.ActionSupport#execute()
     */
    public String onBtnAddClick()
    {	
    	String tmpId="";
    	String ctmpId="";
    	if(getCmbComplaint_id()==""||getCmbComplaint_id()==null){
    		cmbComplaint_id="8";
        }
    	if(getCmbComplaint_type_id()==""||getCmbComplaint_type_id()==null){
        	cmbComplaint_type_id="9";
        }
    	DBAccess db=getDBAccess();
    	String sql;
        SQLListReader reader=getSQLListReader();
    	db.connect();
    	try{
    		HashMap<String, Object> inst = new HashMap<String, Object>();
    		if(!isEmpty(getTxtPhone_number())){
    			inst.put("WHERE", "1=1 AND PHONENUMBER="+escStrQNull(getTxtPhone_number()));
    			sql = reader.findSQLByCommand(QueryConstants.SLCT_CALLER_DETAIL,inst,true);
    			ResultSet rs=db.select(sql);
    			if(!rs.next()){
    				inst.clear();
    				inst.put("PHONENUMBER", escStrQNull(getTxtPhone_number()));
    				inst.put("AGENT_ID", escStrQNull(getAgentID()));
    				inst.put("FIRSTNAME", escStrQNull(getTxtCaller_name()));
    				sql = reader.findSQLByCommand(QueryConstants.SLCT_CALLER_DETAIL_INSERT3,inst,true);
    				if(db.executeInsert(sql)){
    					db.commit();
    				}else{
    					return ERROR;
    				}
    			}
        	}
    		inst.put(PHONE_NUMBER_ONE, escStrQNull(getTxtPhone_number()));
            inst.put(CALLER_NAME, escStrQNull(getTxtCaller_name()));
            inst.put(CALLER_EMAIL, escStrQNull(getTxtEmail()));
            inst.put(SERVICE_ID,  escStrQNull(getCmbComplaint_id()));
            inst.put(UNIT_ID, escStrQNull(getUnit_id()));
            inst.put(CALL_RATE_ID, escStrQNull(getCmbComplaint_rate_id()));
            inst.put(DETAILED_SERVICE_ID, escStrQNull(getCmbComplaint_type_id()));
            inst.put(CALL_SORT_ID, escStrQNull(getCmbComplaint_sort_id()));
            inst.put(CALL_RESULT_ID, escStrQNull(getCmbComplaint_result_id()));
            inst.put(SOURCE_ID, escStrQNull(getCmbSource()));
            inst.put(COMPLAINT_COMMENT, escStrQNull(getTxtComplaint_comment()));
            inst.put("SOLVED_STATUS", '0');
            inst.put(AGENT_ID, escStrQNull(getAgentID()));
            inst.put(INSERT_USER, escStrQNull(getAgentID()));
            inst.put(INSERT_DATE, escStrQNull(now()));
            sql = reader.findSQLByCommand(Consts.INSRT010_COMPLAINT,inst,true);
    		if(db.executeInsert(sql)){
    			
    			inst.clear();
    			inst.put(Constants.FIELD_PHONENUMBER, escStrQ(getTxtPhone_number()));
				inst.put(Constants.FIELD_AGENT_ID, escStrQ(getAgentID()));
				sql = reader.findSQLByCommand(QueryConstants.SLCT_CALL_HISTORY_INSERT_COMPLAINT, inst, true);
				if (db.executeInsert(sql))
				{
					sql = reader.findSQLByCommand(QueryConstants.SLCT_CALL_HISTORY_MAX, inst, true);
					ResultSet rs = db.select(sql);
					if (rs.next())
					{
						tmpId=rs.getString(Constants.FIELD_CALL_HISTORY_ID);
						db.commit() ;
						
						inst.clear();
						inst.put("INSERT_USER", escStrQNull(getAgentID()));
						sql = reader.findSQLByCommand(QueryConstants.SLCT_COMPLAINT_MAX, inst, true);
						rs = db.select(sql);
						if(rs.next()){
							ctmpId=rs.getString("COMPLAINT_ID");
						}else{
							return ERROR;
						}
						inst.clear();
						inst.put("CALL_HISTORY_ID", tmpId);
						inst.put("COMPLAINT_ID", ctmpId);
						sql = reader.findSQLByCommand(Consts.ASSIGN_CALL_HISTORY_ID_TO_COMPLAINT,inst,true);
						db.select(sql);
						db.commit();
						sql = reader.findSQLByCommand(Consts.COMPLAINT_TO_CALL_HISTORY_SERVICE,inst,true);
						db.select(sql);
						db.commit();
					}
					else
					{
						db.rollback() ;
						return ERROR ;
					}
					
				}else
				{
					return ERROR;
				}
    		}else{
    			return ERROR;
    		}
    	}catch(Exception e){
    		log("ERROR",e);
			return ERROR;
    	}finally{
    		db.disconnect();
    	}
    	return "saved";
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
    public String getGrpOther_complaint_registration()
    {
       return grpOther_complaint_registration;
    }

    /**
     * @param grpOther_complaint_registration     */
    public void setGrpOther_complaint_registration(String grpOther_complaint_registration)
    {
       this.grpOther_complaint_registration= grpOther_complaint_registration;
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
    public String getLblStaticCaller_name()
    {
       return lblStaticCaller_name;
    }

    /**
     * @param lblStaticCaller_name     */
    public void setLblStaticCaller_name(String lblStaticCaller_name)
    {
       this.lblStaticCaller_name= lblStaticCaller_name;
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
    public String getLblStaticSource()
    {
       return lblStaticSource;
    }

    /**
     * @param lblStaticSource     */
    public void setLblStaticSource(String lblStaticSource)
    {
       this.lblStaticSource= lblStaticSource;
    }

    /**
     * @return
     */
    public String getCmbSource()
    {
       return cmbSource ;
    }

    /**
     * @param cmbSource     */
    public void setCmbSource(String cmbSource)
    {
       this.cmbSource= cmbSource;
    }

    /**
     * @return
     */
    public String getLblStaticComplaint_id()
    {
       return lblStaticComplaint_id;
    }

    /**
     * @param lblStaticComplaint_id     */
    public void setLblStaticComplaint_id(String lblStaticComplaint_id)
    {
       this.lblStaticComplaint_id= lblStaticComplaint_id;
    }

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
    public String getTxtComplaint_comment()
    {
       return txtComplaint_comment;
    }

    /**
     * @param txtComplaint_comment     */
    public void setTxtComplaint_comment(String txtComplaint_comment)
    {
       this.txtComplaint_comment= txtComplaint_comment;
    }

    /**
     * @param btnComplaint_register
     */

    /**
     * @param btnClear
     */
    public String onBtnClearClick()
    {
       //CALL EVENT
       return SUCCESS;
    }

	public String getInsert_message() {
		return insert_message;
	}

	public void setInsert_message(String insert_message) {
		this.insert_message = insert_message;
	}

	public String getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}

}
