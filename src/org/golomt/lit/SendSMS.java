/*
* „M„Ђ„t„u„|„y„z„~ „~„Џ„‚             SendSMS.java
*
* „U„…„~„{„€„Ќ„~ „~„Џ„‚          ЃyДэлгэцийн зохиомжЃz
* „Svv„‡
* „V„…„r„y„|„q„p„‚„Ќ„~ ‡‚  „O„s„~„Ђ„Ђ    „V„p„‚„y„…„€„p„s„‰    „@„s„…„…„|„s„p
* 01.00.00                 „O„s„~„Ђ„Ђ    „V„p„‚„y„…„€„p„s„‰    „Y„y„~„Џ„Џ„‚ „‡„y„z„ѓ„Џ„~/„H„p„ѓ„ѓ„p„~
*/


package org.golomt.lit;

import infox.org.db.DBAccess;
import infox.org.sql.SQLListReader;

import java.sql.SQLException;
import java.util.HashMap;

import org.golomt.common.XMLForm;
import org.iaac.consts.Consts;

/**
* @author 
*/
public class SendSMS extends XMLForm 
{
   private static final String insert_success = "system.insert_success";
   
   private static final String insert_failed = "system.insert_failed";
   
   private static final String MESSAGE_SENDER = "MESSAGE_SENDER";
   
   private static final String MESSAGE_RECEIVER = "MESSAGE_RECEIVER";
   
   private static final String TEXT_MESSAGE = "TEXT_MESSAGE";
   
   private static final String INSERT_USER="INSERT_USER";
   
   private static final String INSERT_DATE="INSERT_DATE";
   
    /** Утасны дугаар */
    private String lblStaticPhone_number;
    /** Утасны дугаар */
    private String phone_number;
    /** Текст мессеж */
    private String lblStaticSMS;
    /** Текст мессеж */
    private String txtSMS;
    /**Утасны дугаар*/
    private String txtPhone_number;
    /***/
    private String txtSend_name;
    /**Textarea*/
    private String txt_name;
    
     
    
    

    /**
     * „A„p„z„s„…„…„|„p„s„‰ „†„…„~„{„€
     */
    public String saveTask()
    {	
  	  	HashMap<String,Object> hsts=new HashMap<String,Object>();
  	  	hsts.put(MESSAGE_SENDER, escStrQNull(getTxtPhone_number()));
	  	hsts.put(MESSAGE_RECEIVER, escStrQNull(getTxtSend_name()));
	  	hsts.put(TEXT_MESSAGE, escStrQNull(getTxt_name()));
    	  	String[] Querys = new String[1];
    	  	Querys[0] = Consts.SAVE_SMS_SEND;      
	    
    	  	return SUCCESS; //toDatabase(Querys,hsts,insert_success,insert_failed)?SUCCESS:ERROR; 
  }
    
    public String SendMessage()
    {
    	HashMap<String,Object> hsts=new HashMap<String,Object>();
  	  	hsts.put(MESSAGE_SENDER, escStrQNull(getTxtPhone_number()));
  	  	hsts.put(MESSAGE_RECEIVER, escStrQNull(getTxtSend_name()));
  	  	hsts.put(TEXT_MESSAGE, escStrQNull(getTxt_name()));
  	  	hsts.put(AGENT_ID, escStrQNull(getAgentID()));
  	  	hsts.put(INSERT_USER, escStrQNull(getAgentID()));
  	  	hsts.put(INSERT_DATE, escStrQNull(now()));
  	  	String[] Querys = new String[1];
  	  	Querys[0] = Consts.SAVE_SMS_SEND;
  	  	return toDatabase(Querys,hsts,insert_success,insert_failed)?SUCCESS:ERROR; 
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
    
    

    /* (non-Javadoc)
     * @see com.opensymphony.xwork2.ActionSupport#execute()
     */
    public String execute()
    {
    	//setTxtSend_name(getPhone_number());
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
    public String getLblStaticSMS() 
    {
       return lblStaticSMS;
    }

    /**
     * @param lblStaticSMS     */
    public void setLblStaticSMS(String lblStaticSMS)
    {
       this.lblStaticSMS= lblStaticSMS;
    }

    /**
     * @return
     */
    public String getTxtSMS() 
    {
       return txtSMS;
    }

    /**
     * @param txtSMS     */
    public void setTxtSMS(String txtSMS)
    {
       this.txtSMS= txtSMS;
    }

    /**
     * @param btnSend
     */
    public String onBtnSendClick()
    {
       //CALL EVENT
       return SUCCESS; 
    }

    
    /**
     * @param btnReturn
     */
    public String onBtnReturnClick()
    {
       //CALL EVENT
       return SUCCESS; 
    }

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getTxtPhone_number() {
		return txtPhone_number;
	}


	public void setTxtPhone_number(String txtPhone_number) {
		this.txtPhone_number = txtPhone_number;
	}

	public String getTxtSend_name() {
		return txtSend_name;
	}

	public void setTxtSend_name(String txtSend_name) {
		this.txtSend_name = txtSend_name;
	}

	public String getTxt_name() {
		return txt_name;
	}

	public void setTxt_name(String txt_name) {
		this.txt_name = txt_name;
	}

}
