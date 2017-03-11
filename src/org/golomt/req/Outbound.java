/*
* „M„Ђ„t„u„|„y„z„~ „~„Џ„‚             CallReqType.java
*
* „U„…„~„{„€„Ќ„~ „~„Џ„‚          ЃyОператорын ажлын үр дүнгийн тайланЃz
* „Svv„‡
* „V„…„r„y„|„q„p„‚„Ќ„~ ‡‚  „O„s„~„Ђ„Ђ    „V„p„‚„y„…„€„p„s„‰    „@„s„…„…„|„s„p
* 01.00.00                 „O„s„~„Ђ„Ђ    „V„p„‚„y„…„€„p„s„‰    „Y„y„~„Џ„Џ„‚ „‡„y„z„ѓ„Џ„~/„H„p„ѓ„ѓ„p„~
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
public class Outbound extends XMLForm 
{
	
	/** Даалгаврын дугаар */
	private String taskID ;
	
	private String id;
	
	private String calltype;

	private boolean isExistingTask ;
	String disabledTabs="1";
	String defaultTab="0";
   /** „†„Ђ„‚„}„Ќ„~ „~„Џ„‚ */
   private static final String FORM_NAME = "Outbound.form_name" ;
       public Outbound()
    {
    }

    /* (non-Javadoc)
     * @see com.opensymphony.xwork2.ActionSupport#execute()
     */
    public String execute()
    {
    	if(!getType().equals("") && !getPhone_number().equals(""))
    	{
    		
			setInbound_Call(getPhone_number());
			if(getType()!=null || getCalltype()!=null){
				if("out".equals(getType()) && getPhone_number() != null){
					disabledTabs="";
					defaultTab="1";
				}else if("1".equals(getCalltype()))
				{
					disabledTabs="";
					defaultTab="1";
				}else if("outview".equals(getType()) && getPhone_number() != null ){
					disabledTabs="";
					defaultTab="1";
				}
			}
			/*if(getType() != null && getType().equals("inbound")){
				disabledTabs="1,2,3,4,5,6";
				defaultTab="0";
			}*/
			
    	}else setInbound_Call("");
    	
    	/*System.out.println("outbound ==> disabledTabs = "+disabledTabs+" ,,,,  defaultTab = "+defaultTab +" ,,,, getCallEnd = "+getCallEnd());*/
       return SUCCESS; 
    }

    /* (non-Javadoc)
     * @see com.opensymphony.xwork2.ActionSupport#validate()
     */

	/**
	 * @return disabledTabs
	 */
	public String getDisabledTabs() {
		
	
		/*if (taskId )
		{
			disabledTabs = "" ;
		}*/
		return disabledTabs;
	}

	/**
	 * @return
	 */
	public String getDefaultTab(){
		
		return defaultTab;

	}

	public boolean isExistingTask() {
		return isExistingTask;
	}

	public void setExistingTask(boolean isExistingTask) {
		this.isExistingTask = isExistingTask;
	}

	public String getTaskID() {
		return taskID;
	}

	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCalltype() {
		return calltype;
	}

	public void setCalltype(String calltype) {
		this.calltype = calltype;
	}   
}
