/*
* „M„Ђ„t„u„|„y„z„~ „~„Џ„‚             DistributeTaskAgent.java
*
* „U„…„~„{„€„Ќ„~ „~„Џ„‚          ЃyДаалгавар хуваарилахЃz
* „Svv„‡
* „V„…„r„y„|„q„p„‚„Ќ„~ ‡‚  „O„s„~„Ђ„Ђ    „V„p„‚„y„…„€„p„s„‰    „@„s„…„…„|„s„p
* 01.00.00                 „O„s„~„Ђ„Ђ    „V„p„‚„y„…„€„p„s„‰    „Y„y„~„Џ„Џ„‚ „‡„y„z„ѓ„Џ„~/„H„p„ѓ„ѓ„p„~
*/


package org.golomt.lit;


import org.iaac.common.BForm;
import infox.org.db.DBAccess;
import infox.org.sql.SQLListReader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.poi.hslf.model.Freeform;
import org.iaac.common.Login;
import org.iaac.consts.Consts;

import com.opensymphony.xwork2.ActionContext;

/**
* @author 
*/
public class DistributeTaskAgent extends BForm
{
	
	private static final String[] FIELD_LIST = {"AGENT_ID","FIRSTNAME"};
	
	public static final String QUERY_WHERE = "WHERE";
   /** „†„Ђ„‚„}„Ќ„~ „~„Џ„‚ */
   private static final String FORM_NAME = "DistributeTaskAgent.form_name" ;
   /**  */
   private static final String INFO_MESSAGE="DistributeTaskAgent.Хуваарилалтаа хийнэ үү";
   /**  */
   private static final String INFO_MESSAGE1="DistributeTaskAgent.Амжилттай хуваарилагдлаа";
   
   private static final String FIRST_NAME="FIRSTNAME";
   
   private static final String AGENT_REGISTER = "AGENT_REGISTER";
   
   private static final String AGENT_ID="AGENT_ID";
   
   private static final String TASK_ID="TASK_ID";
   
   private static final String ERR010 = "ERR010";
    
   private int page;
   private String sidx;
   private String sord;
   private boolean searchClick;
   
   private String selected_Agent_Id;
   
   private String distrubute_Task_id;
   
   private String distribute_message;


/**
     * „A„p„z„s„…„…„|„p„s„‰ „†„…„~„{„€
     */
    public DistributeTaskAgent()
    {
    }
    public String getXML()
	{
		StringBuffer where = new StringBuffer();
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
				where.append(" ORDER_BY ");
				where.append(sidx);
				where.append(" ");
				where.append(sord);
			}
		}
		
			
			log(FORM_NAME);

			HashMap<String, Object> hsts = new HashMap<String, Object>();
			hsts.put("WHERE", where);
			DBAccess db = getDBAccess();
			db.connect();
			int totalResult = 0;
			try
			{
				SQLListReader reader = getSQLListReader();
				String sql = reader.findSQLByCommand("SLCT_DISTRIBUTE_AGENT", hsts, true);
				ResultSet rs = db.select(sql);
				/*while (rs.next())
					totalResult++;*/
				//rs = db.select(sql + 100 + (page - 1) * LIMIT_GRID + "," + LIMIT_GRID);
				xmlOutput(rs, page, 2, totalResult, FIELD_LIST);
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
  

    /**
     * @param btnProrate
     */
    public String onBtnProrateClick()
    {
       if(selected_Agent_Id==""||selected_Agent_Id==null){
    	   distribute_message="0";
    	   return "tree";
       }
       if(distrubute_Task_id==""||distrubute_Task_id==null){
    	   distribute_message="1";
    	   return "tree";
       }
       String[] Querys = new String[1];
	   Querys[0] = Consts.DIST_TASK_AGENT;
       HashMap<String,Object> tek=new HashMap<String,Object>();
       String[] distribute_Task_List=distrubute_Task_id.split(",");
       String[] selected_Agent_List=selected_Agent_Id.split(",");
       Integer taskCount=distribute_Task_List.length;
       Integer agentCount=selected_Agent_List.length;
       Integer taskPerAgent=taskCount/agentCount;
       Integer evenTasks=agentCount*taskPerAgent;
       if(taskCount-(agentCount*taskPerAgent)!=0){
       	   int s=0;
       	   for(int z=0;z<agentCount;z+=0){
       		   for(int i=s;i<=evenTasks;i++){
        		   if(i==s+taskPerAgent){
        			   z+=1;
        			   s=i;
        			   break;
        		   }else{
        			   tek.put(AGENT_ID, escStrQNull(selected_Agent_List[z]));
        			   tek.put(TASK_ID, escStrQNull(distribute_Task_List[i]));
        			   toDatabase(Querys, tek, "successMsg", "errorMsg");
        			   tek.clear();
        		   }
        	   }
       		   
       		   
       	   }
       	   Random r = new Random();
       	   for(int i=s;i<taskCount;i++){
       		   tek.put(AGENT_ID, escStrQNull(selected_Agent_List[r.nextInt(agentCount)]));
			   tek.put(TASK_ID, escStrQNull(distribute_Task_List[i]));
			   toDatabase(Querys, tek, "successMsg", "errorMsg");
			   tek.clear();
       	   }
       	distribute_message="2";
       }else{
    	   int s=0;
       	   for(int z=0;z<agentCount;z+=0){
       		   for(int i=s;i<=taskCount;i++){
        		   if(i==s+taskPerAgent){
        			   z+=1;
        			   s=i;
        			   break;
        		   }else{
        			   tek.put(AGENT_ID, selected_Agent_List[z]);
        			   tek.put(TASK_ID, distribute_Task_List[i]);
        			   toDatabase(Querys, tek, "successMsg", "errorMsg");
        			   tek.clear();
        		   }
        	   }
       		   
       		   
       	   }
       	distribute_message="2";
       }
       
       return "tree";
    }

    /**
     * @param btnReturn
     */
    public String onBtnReturnClick()
    {
       //CALL EVENT
       return SUCCESS; 
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
	
	
	public String getDistrubute_Task_id() {
		return distrubute_Task_id;
	}
	public void setDistrubute_Task_id(String distrubute_Task_id) {
		this.distrubute_Task_id = distrubute_Task_id;
	}
	public String getSelected_Agent_Id() {
		return selected_Agent_Id;
	}
	public void setSelected_Agent_Id(String selected_Agent_Id) {
		this.selected_Agent_Id = selected_Agent_Id;
	}
	public String getDistribute_message() {
		return distribute_message;
	}
	public void setDistribute_message(String distribute_message) {
		this.distribute_message = distribute_message;
	}
	

}
