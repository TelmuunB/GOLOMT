/*
* „M„Ђ„t„u„|„y„z„~ „~„Џ„‚             TaskList.java
*
* „U„…„~„{„€„Ќ„~ „~„Џ„‚          ЃyДаалгаврын жагсаалтЃz
* „Svv„‡
* „V„…„r„y„|„q„p„‚„Ќ„~ ‡‚  „O„s„~„Ђ„Ђ    „V„p„‚„y„…„€„p„s„‰    „@„s„…„…„|„s„p
* 01.00.00                 „O„s„~„Ђ„Ђ    „V„p„‚„y„…„€„p„s„‰    „Y„y„~„Џ„Џ„‚ „‡„y„z„ѓ„Џ„~/„H„p„ѓ„ѓ„p„~
*/


package org.golomt.detail;


import infox.org.db.DBAccess;
import infox.org.sql.SQLListReader;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.golomt.common.Constants;
import org.golomt.common.XMLForm;
import org.iaac.consts.Consts;

import com.opensymphony.xwork2.ActionContext;


/**
* @author 
*
*/
public class TaskList extends XMLForm 
{
   /** „†„Ђ„‚„}„Ќ„~ „~„Џ„‚ */
   private static final String FORM_NAME = "TaskList.form_name" ;
    /** Хайлт */
    private String grpSearch;
    /** Утасны дугаар */
    private String lblStaticPhone_number;
    /** Утасны дугаар */
    private String txtPhone_number;
    /** Үйлчлүүлэгчийн нэр */
    private String lblStaticCaller_name;
    /** Үйлчлүүлэгчийн нэр */
    private String txtCaller_name;
    /** Даалгаврын нэр */
    private String lblStaticTask_name;
    /** Даалгаврын нэр */
    private String txtTask_name;
    /** Агентын № */
    private String lblStaticAgent_id;
   /** Агентын № */
    private String cmbAgent_id;
    /** Дуудлагын төрөл */
    private String lblStaticCall_req_type_id;
   /** Дуудлагын төрөл */
    private String cmbCall_req_type_id;
    /** Даалгавар оруулсан */
    private String lblStaticInsert_agent;
   /** Даалгавар оруулсан */
    private String cmbInsert_agent;
    /** Дуудлагын үр дүн */
    private String lblStaticCall_result_id;
   /** Дуудлагын үр дүн */
    private String cmbCall_result_id;
    /** Даалгавар дуусах хугацаа */
    private String lblStaticDeadline;
    /** Даалгавар дуусах хугацаа */
    private String txtDeadline;
    /** Өөрийн даалгавар */
    private String chkMine_task;
    /** Дундын даалгавар */
    private String chkAverage_task;
    /** Даалгаврын жагсаалт */
    private String grpTask_list;
    /** Даалгаврын жагсаалт */
    private String grdTask_list;
    /** Даалгаврын дэлгэрэнгүй */
    private String grpTask_detail;
    /** Утасны дугаар */
    /** Утасны дугаар */
    private String lblPhone_number;
    /** 2дахь дугаар */
    private String lblStaticSecond_phone;
    /** 2 дахь дугаар */
    private String lblSecond_phone;
    /** Үйлчлүүлэгчийн нэр */
    /** Үйлчлүүлэгчийн нэр */
    private String lblCaller_name;
    /** Агентын № */
    /** Агентын № */
    private String lblAgent_id;
    /** Эхлэсэн цаг */
    private String lblStaticCall_start_time;
    /** Эхлэсэн цаг */
    private String lblCall_start_time;
    /** Даалгавар дуусах хугацаа */
    /** Даалгавар дуусах хугацаа */
    private String lblDeadline;
    /** Дуудлагын төрөл */
    /** Дуудлагын төрөл */
    private String lblCall_req_type_id;
    /** Төрөл */
    private String lblStaticInbound_service_id;
    /** Төрөл */
    private String lblInbound_service_id;
    /** Нарийвчилсан төрөл */
    private String lblStaticDetailed_service_id;
    /** Нарийвчилсан төрөл */
    private String lblDetailed_service_id;
    /** Даалгаврын нэр */
    /** Даалгаврын нэр */
    private String lblTask_name;
    /** Үргэлжилсэн хугацаа */
    private String lblStaticDuration_time;
    /** Үргэлжилсэн хугацаа */
    private String lblDuration_time;
    /** Холбоотой нэгж */
    private String lblStaticUnit_id;
    /** Холбоотой нэгж */
    private String lblUnit_id;
    /** Дуудлагын ангилал */
    private String lblStaticCall_sort_id;
    /** Дуудлагын ангилал */
    private String lblCall_sort_id;
    /** Дуудлагын үр дүн */
    /** Дуудлагын үр дүн */
    private String lblCall_result_id;
    /** Дуудлагын үнэлгээ */
    private String lblStaticCall_rate_id;
    /** Дуудлагын үнэлгээ */
    private String lblCall_rate_id;
    /** Дуудлагын оролт гаралт */
    /** Дуудлагын оролт гаралт */
    private String lblCall_type;
    /** Даалгаврын товч тэмдэглэл */
    private String lblStaticCall_type;
    /** Даалгаврын товч тэмдэглэл */
    private String lblTask_comment;
    private String txtDeadlineStart;
    private String txtDeadlineEnd;
    private boolean searchClick;
	private String qString;
	
//=================================================================================================
	
	/*
	'TASK_ID', index : 'TASK_ID', hidden: true },
		                             {name : 'PHONE_NUMBER_ONE', index : 'PHONE_NUMBER_ONE' , width:80 },
		                             {name : 'CALLER_NAME', index : 'CALLER_NAME' , width: 80 },
		                             {name : 'TASK_NAME', index : 'TASK_NAME' , width:90 },
		                             {name : 'AGENT_ID', index : 'AGENT_ID' , width:100 },
		                             {name : 'CALL_REQ_TYPE_NAME', index : 'CALL_REQ_TYPE_NAME' , width:100 },
		                             {name : 'DEADLINE', index : 'DEADLINE' , width:100 },
		                             {name : 'INSERT_AGENT', index : 'INSERT_AGENT' , width:100 },
		                             {name : 'CALL_RESULT_NAME', index : 'CALL_RESULT_NAME' , width:100 },
		                             {name : 'CALL_REQ_TYPE_ID', index : 'CALL_REQ_TYPE_ID' , hidden:true },
		                             {name : 'CALLTYPE'
	*/
	
    private static final String[] FIELD_LIST = { "TASK_ID", "PHONE_NUMBER_ONE", 
		"CALLER_NAME", "TASK_NAME", "AGENT_ID", "CALL_REQ_TYPE_NAME", "DEADLINE", 
		"INSERT_AGENT", "CALL_RESULT_NAME","T_INSERT_DATE" , "CALL_REQ_TYPE_ID", "CALLTYPE","TASK_COMMENT", 
		"SERVICE_NAME1", "DETAILED_SERVICE_NAME1", "SERVICE_NAME2" , "DETAILED_SERVICE_NAME2", "SERVICE_NAME3" , "DETAILED_SERVICE_NAME3" , "SERVICE_NAME4" , "DETAILED_SERVICE_NAME4" , "SERVICE_NAME5" , "DETAILED_SERVICE_NAME5"
		, "INSERT_DATE", "CALL_RATE_NAME", "CALL_SORT_NAME", "UNIT_NAME", "START_TIME", "DURAT"};
    
    /** Хайлтын нөхцөлөө задлах */
	private static final String[] NAME_LIST = { "txtPhone_number",
			"txtCaller_name", "txtTask_name", "cmbAgent_id", "cmbCall_req_type_id", "chkMine_task", "chkAverage_task", "txtDeadlineStart", "txtDeadlineEnd"
			, "cmbInsert_agent", "cmbCall_result_id" };
	
    private int page;
    private String sord;
	private String sidx;
	private static final String SPACE = " ";
	private static final String ASC = "asc";
	private static final String DESC = "desc";
	private static final String ORDER_BY = " ORDER BY ";
	/** Хайлтын нөхцөл query "where" */
	public static final String QUERY_WHERE = "WHERE";
	private static final String ROWNUM = " AND ROWNUM BETWEEN ";
	private boolean pageLoaded;
	
    
    
    private static final String QUERY_BEGIN = " 1 = 1 ";
    private static final String PHONE_NUMBER = " AND T.PHONE_NUMBER_ONE LIKE '%?%'";
    private static final String AGENT_ID = " AND T.AGENT_ID = '?'";
    private static final String CALLER_NAME = " AND UPPER(T.CALLER_NAME) LIKE UPPER('%?%')";    
    private static final String TASK_NAME = " AND UPPER(T.TASK_NAME) LIKE UPPER('%?%')";
    private static final String CALL_REQ_TYPE_ID = " AND T.CALL_REQ_TYPE_ID = '?'";
    private static final String CALL_RESULT_ID = " AND CLL.CALL_RESULT_ID = '?'";
    private static final String INSERT_AGENT = " AND T.INSERT_AGENT LIKE '%?%'";
    private static final String DATE_WHERE = " AND ((T.DEADLINE BETWEEN TO_TIMESTAMP('?', 'YYYY-MM-DD') AND TO_TIMESTAMP('?', 'YYYY-MM-DD')) OR T.DEADLINE IS NULL)";
    
    private static final String DELETE_DATE_NULL = " AND T.DELETE_DATE IS NULL";
    ///private static final String ACTIVE_TASK = " AND T.ACTIVE_TASK IS NULL";
    private static final String START_DATE = "1900-01-01";
    private static final String END_DATE = "2100-12-31";
    private static final String GROUP_BY_USER_NUM = " GROUP BY T.TASK_ID ";
    
//=================================================================================================
    /**
     * „A„p„z„s„…„…„|„p„s„‰ „†„…„~„{„€
     */
    public TaskList()
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
    	pageLoaded = true;
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

    /**
     * @param lblStaticTask_name     */

    /**
     * @return
     */
    public String getTxtTask_name() 
    {
       return txtTask_name;
    }

    /**
     * @param txtTask_name     */
    public void setTxtTask_name(String txtTask_name)
    {
       this.txtTask_name= txtTask_name;
    }

    /**
     * @return
     */

    /**
     * @param lblStaticAgent_id     */

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
     * @param lblStaticCall_req_type_id     */

    /**
     * @return
     */
    public String getCmbCall_req_type_id() 
    {
       return cmbCall_req_type_id ;
    }

    /**
     * @param cmbCall_req_type_id     */
    public void setCmbCall_req_type_id(String cmbCall_req_type_id)
    {
       this.cmbCall_req_type_id= cmbCall_req_type_id;
    }

    /**
     * @return
     */
    public String getLblStaticInsert_agent() 
    {
       return lblStaticInsert_agent;
    }

    /**
     * @param lblStaticInsert_agent     */
    public void setLblStaticInsert_agent(String lblStaticInsert_agent)
    {
       this.lblStaticInsert_agent= lblStaticInsert_agent;
    }

    /**
     * @return
     */
    public String getCmbInsert_agent() 
    {
       return cmbInsert_agent ;
    }

    /**
     * @param cmbInsert_agent     */
    public void setCmbInsert_agent(String cmbInsert_agent)
    {
       this.cmbInsert_agent= cmbInsert_agent;
    }

    /**
     * @return
     */

    /**
     * @param lblStaticCall_result_id     */

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

    /**
     * @param lblStaticDeadline     */

    /**
     * @return
     */
    public String getTxtDeadline() 
    {
       return txtDeadline;
    }

    /**
     * @param txtDeadline     */
    public void setTxtDeadline(String txtDeadline)
    {
       this.txtDeadline= txtDeadline;
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
	 * @return LIMIT_GRID
	 */
	public int getLimitGrid() {
		return LIMIT_GRID;
	}

    /**
     * @param btnSearch
     */
    public String getXML() {
    	log("      >>>>>>>>>>>>>     tasklist =  "+getTaskID());
    	if(isEmpty(getChkMine_task())) setChkMine_task("false");
    	if(isEmpty(getChkAverage_task())) setChkAverage_task("false");
		StringBuffer where = new StringBuffer(QUERY_BEGIN);
		buildWQuery(where, PHONE_NUMBER, new String[] { getTxtPhone_number() });
		buildWQuery(where, CALLER_NAME, new String[] { getTxtCaller_name() });
		buildWQuery(where, TASK_NAME, new String[] { getTxtTask_name() });
		buildWQuery(where, CALL_REQ_TYPE_ID, new String[] { getCmbCall_req_type_id() });
		buildWQuery(where, CALL_RESULT_ID, new String[] { getCmbCall_result_id() });
		buildWQuery(where, INSERT_AGENT, new String[] { getCmbInsert_agent() });
		
		if(getPermission()==0){
			if(getChkMine_task().equals("true"))
				buildWQuery(where, AGENT_ID, new String[] { getAgentID() });
			else { if(getChkAverage_task().equals("true"))
					{ where.append(" AND T.AGENT_ID is null "); }
				  else { where.append(" AND (T.AGENT_ID = "+getAgentID()+" OR T.AGENT_ID is null)"); }
			}
			if(getChkMine_task().equals("true") && getChkAverage_task().equals("true"))
				where.append(" AND  (T.AGENT_ID = "+getAgentID()+" OR T.AGENT_ID is null)");
		}
		else if(getPermission()==1){ 
			if(isEmpty(getCmbAgent_id())){
				if(getChkMine_task().equals("true") && getChkAverage_task().equals("true")){
					where.append(" AND  (T.AGENT_ID = "+getAgentID()+" OR T.AGENT_ID is null)");
				}
				else
				{
					if(getChkMine_task().equals("true"))
						buildWQuery(where, AGENT_ID, new String[] { getAgentID() });
					else if(getChkAverage_task().equals("true"))
					     	where.append(" AND T.AGENT_ID is null ");
					else buildWQuery(where, AGENT_ID, new String[] { getCmbAgent_id() }); 
				}
			}else buildWQuery(where, AGENT_ID, new String[] { getCmbAgent_id() });
		}
		
		where.append(DELETE_DATE_NULL);

		if (isEmpty(getTxtDeadlineStart()))
		{
			setTxtDeadlineStart(START_DATE);
		}
		if (isEmpty(getTxtDeadlineEnd()))
		{
			setTxtDeadlineEnd(END_DATE);
		}
		
		
		buildWQuery(where, DATE_WHERE, new String[] { getTxtDeadlineStart(), getTxtDeadlineEnd() });

		//where.append(GROUP_BY_USER_NUM);
		if (page < 1) {
			page = 1;
		}
		if (!isEmpty(sidx) && !isEmpty(sord)) {
			
			int i = 0;
			while (i < FIELD_LIST.length && !FIELD_LIST[i].equals(sidx))
				i++;
			if (i < FIELD_LIST.length && (ASC.equals(sord) || DESC.equals(sord))) {
				where.append(ORDER_BY);
				where.append(sidx);
				where.append(SPACE);
				where.append(sord);
			}
		}
		
		if (isSearchClick()) {

			log(FORM_NAME);
		
			HashMap<String, Object> hsts = new HashMap<String, Object>();
			hsts.put(QUERY_WHERE, where);
			DBAccess db = getDBAccess();
			db.connect();
			int totalResult = 0;
			try {

				SQLListReader reader = getSQLListReader();
				String sql = reader.findSQLByCommand(Consts.SLCT_Task_List, hsts, true);
				log(sql);
				ResultSet rs = db.select(sql);

				while (rs.next()){
					totalResult++;
				}
				
				rs = db.select(sql);
				xmlOutput(rs, page, ((totalResult - 1) / LIMIT_GRID + 1), totalResult, FIELD_LIST);

				return XML;
			} catch (Exception e) {
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
     * @param btnClear
     */
    public String onBtnClearClick()
    {
       //CALL EVENT
       return SUCCESS; 
    }

    /**
     * @return
     */
    public String getGrpTask_list() 
    {
       return grpTask_list;
    }

    /**
     * @param grpTask_list     */
    public void setGrpTask_list(String grpTask_list)
    {
       this.grpTask_list= grpTask_list;
    }

    /**
     * @return
     */
    public String getGrdTask_list() 
    {
       return grdTask_list;
    }

    /**
     * @param grdTask_list     */
    public void setGrdTask_list(String grdTask_list)
    {
       this.grdTask_list= grdTask_list;
    }

    /**
     * @return
     */

    /**
     * @param grp Task_detail     */

    /**
     * @return
     */

    /**
     * @param lblStaticPhone_number     */

    /**
     * @return
     */
    public String getLblPhone_number() 
    {
       return lblPhone_number;
    }

    /**
     * @param lblPhone_number     */
    public void setLblPhone_number(String lblPhone_number)
    {
       this.lblPhone_number= lblPhone_number;
    }

    /**
     * @return
     */
    public String getLblStaticSecond_phone() 
    {
       return lblStaticSecond_phone;
    }

    /**
     * @param lblStaticSecond_phone     */
    public void setLblStaticSecond_phone(String lblStaticSecond_phone)
    {
       this.lblStaticSecond_phone= lblStaticSecond_phone;
    }

    /**
     * @return
     */
    public String getLblSecond_phone() 
    {
       return lblSecond_phone;
    }

    /**
     * @param lblSecond_phone     */
    public void setLblSecond_phone(String lblSecond_phone)
    {
       this.lblSecond_phone= lblSecond_phone;
    }

    /**
     * @return
     */
    public String getLblCaller_name() 
    {
       return lblCaller_name;
    }

    /**
     * @param lblCaller_name     */
    public void setLblCaller_name(String lblCaller_name)
    {
       this.lblCaller_name= lblCaller_name;
    }

    /**
     * @return
     */

    /**
     * @param lblStaticAgent_id     */

    /**
     * @return
     */
    public String getLblAgent_id() 
    {
       return lblAgent_id;
    }

    /**
     * @param lblAgent_id     */
    public void setLblAgent_id(String lblAgent_id)
    {
       this.lblAgent_id= lblAgent_id;
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
    public String getLblCall_start_time() 
    {
       return lblCall_start_time;
    }

    /**
     * @param lblCall_start_time     */
    public void setLblCall_start_time(String lblCall_start_time)
    {
       this.lblCall_start_time= lblCall_start_time;
    }

    /**
     * @return
     */

    /**
     * @param lblStaticDeadline     */

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

    /**
     * @param lblStaticCall_req_type_id     */

    /**
     * @return
     */
    public String getLblCall_req_type_id() 
    {
       return lblCall_req_type_id;
    }

    /**
     * @param lblCall_req_type_id     */
    public void setLblCall_req_type_id(String lblCall_req_type_id)
    {
       this.lblCall_req_type_id= lblCall_req_type_id;
    }

    /**
     * @return
     */
    public String getLblStaticInbound_service_id() 
    {
       return lblStaticInbound_service_id;
    }

    /**
     * @param lblStaticInbound_service_id     */
    public void setLblStaticInbound_service_id(String lblStaticInbound_service_id)
    {
       this.lblStaticInbound_service_id= lblStaticInbound_service_id;
    }

    /**
     * @return
     */
    public String getLblInbound_service_id() 
    {
       return lblInbound_service_id;
    }

    /**
     * @param lblInbound_service_id     */
    public void setLblInbound_service_id(String lblInbound_service_id)
    {
       this.lblInbound_service_id= lblInbound_service_id;
    }

    /**
     * @return
     */
    public String getLblStaticDetailed_service_id() 
    {
       return lblStaticDetailed_service_id;
    }

    /**
     * @param lblStaticDetailed_service_id     */
    public void setLblStaticDetailed_service_id(String lblStaticDetailed_service_id)
    {
       this.lblStaticDetailed_service_id= lblStaticDetailed_service_id;
    }

    /**
     * @return
     */
    public String getLblDetailed_service_id() 
    {
       return lblDetailed_service_id;
    }

    /**
     * @param lblDetailed_service_id     */
    public void setLblDetailed_service_id(String lblDetailed_service_id)
    {
       this.lblDetailed_service_id= lblDetailed_service_id;
    }

    /**
     * @return
     */

    /**
     * @param lblStaticTask_name     */

    /**
     * @return
     */
    public String getLblTask_name() 
    {
       return lblTask_name;
    }

    /**
     * @param lblTask_name     */
    public void setLblTask_name(String lblTask_name)
    {
       this.lblTask_name= lblTask_name;
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
    public String getLblDuration_time() 
    {
       return lblDuration_time;
    }

    /**
     * @param lblDuration_time     */
    public void setLblDuration_time(String lblDuration_time)
    {
       this.lblDuration_time= lblDuration_time;
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

    /**
     * @param lblStaticCall_result_id     */

    /**
     * @return
     */
    public String getLblCall_result_id() 
    {
       return lblCall_result_id;
    }

    /**
     * @param lblCall_result_id     */
    public void setLblCall_result_id(String lblCall_result_id)
    {
       this.lblCall_result_id= lblCall_result_id;
    }

    /**
     * @return
     */

    /**
     * @param lblStaticCall_rate_id     */

    /**
     * @return
     */
    public String getLblCall_rate_id() 
    {
       return lblCall_rate_id;
    }

    /**
     * @param lblCall_rate_id     */
    public void setLblCall_rate_id(String lblCall_rate_id)
    {
       this.lblCall_rate_id= lblCall_rate_id;
    }

    /**
     * @return
     */

    /**
     * @param lblStaticCall_rate_id     */

    /**
     * @return
     */
    public String getLblCall_type() 
    {
       return lblCall_type;
    }

    /**
     * @param lblCall_type     */
    public void setLblCall_type(String lblCall_type)
    {
       this.lblCall_type= lblCall_type;
    }

    /**
     * @return
     */
    public String getLblStaticCall_type() 
    {
       return lblStaticCall_type;
    }

    /**
     * @param lblStaticCall_type     */
    public void setLblStaticCall_type(String lblStaticCall_type)
    {
       this.lblStaticCall_type= lblStaticCall_type;
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
     * @param btnChoose_task
     */
    public String onBtnChoose_taskClick()
    {
       //CALL EVENT
       return INPUT; 
    }

	public String getLblStaticCall_rate_id() {
		return lblStaticCall_rate_id;
	}

	public void setLblStaticCall_rate_id(String lblStaticCall_rate_id) {
		this.lblStaticCall_rate_id = lblStaticCall_rate_id;
	}

	public String getTxtDeadlineEnd() {
		return txtDeadlineEnd;
	}

	public void setTxtDeadlineEnd(String txtDeadlineEnd) {
		this.txtDeadlineEnd = txtDeadlineEnd;
	}

	public String getTxtDeadlineStart() {
		return txtDeadlineStart;
	}

	public void setTxtDeadlineStart(String txtDeadlineStart) {
		this.txtDeadlineStart = txtDeadlineStart;
	}

	public boolean isSearchClick() {
		return searchClick;
	}

	public void setSearchClick(boolean searchClick) {
		this.searchClick = searchClick;
	}

	public String getQString() {
		return qString;
	}

	public void setQString(String string) {
		qString = string;
	}

	public boolean isPageLoaded() {
		return pageLoaded;
	}

	public void setPageLoaded(boolean pageLoaded) {
		this.pageLoaded = pageLoaded;
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

}
