package org.golomt.lit;


import infox.org.db.DBAccess;
import infox.org.sql.SQLListReader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.golomt.common.XMLForm;
import org.iaac.consts.Consts;
/**
* @author 
*
*/
public class InsertTask extends XMLForm
{	
	
   /** „†„Ђ„‚„}„Ќ„~ „~„Џ„‚ */
   private static final String FORM_NAME = "InsertTask.form_name" ;
   /** Утасны дугаар */
   private String txtPhone_number;
  /** Агентын № */
   private String cmbAgent_id;
   /** Үйлчлүүлэгчийн нэр */
   private String txtCaller_name;
   /** Даалгаврын нэр */
   private String txtTask_name;  
  /** Даалгаврын төрөл */
   private String cmbTask_type_id;
   /** Даалгавар дуусах хугацаа */
   private String txtDeadline;
  /** Дуудлагын төрөл */
   private String cmbCall_req_type_id;
  /** Холбоотой нэгж */
   private String cmbUnit_id;
  /** Даалгаврын нарийвчилсан төрөл */
   private String cmbTask_detailed_type_id;
   /** Даалгаврын тэмдэглэл */
   private String txtTask_comment;
   
   private String Task_id;
   
   private static final String PHONE_NUMBER_ONE="PHONE_NUMBER_ONE";
   
   private static final String CALLER_NAME="CALLER_NAME";
   
   private static final String TASK_NAME="TASK_NAME";
   
   private static final String AGENT_ID="AGENT_ID";
   
   private static final String DEADLINE="DEADLINE";
   
   private static final String TASK_COMMENT="TASK_COMMENT";
   
   private static final String insert_success = "system.insert_success";
   
   private static final String insert_failed = "system.insert_failed";
   
   private static final String ERR010 = "ERR010";
   
   private LinkedHashMap<String,Object> returnData;
   
  

       public InsertTask()
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
/*          public String saveTask()
          {	
           	  	HashMap<String,Object> hsts=new HashMap<String,Object>();
	      	  	hsts.put(PHONE_NUMBER_ONE, escStrQNull(getTxtPhone_number()));
	      	  	hsts.put(CALLER_NAME, escStrQNull(getTxtCaller_name()));
	      	  	hsts.put(TASK_NAME, escStrQNull(getTxtTask_name()));
	      	  	hsts.put(DEADLINE, escStrQNull(getTxtDeadline()));
	      	  	hsts.put(TASK_COMMENT, escStrQNull(getTxtTask_comment()));
	      	  	String[] Querys = new String[1];
	      	  	Querys[0] = Consts.INSERT_TASK;      
      	    
	      	  	return SUCCESS; //toDatabase(Querys,hsts,insert_success,insert_failed)?SUCCESS:ERROR; 
        }*/
          public String EditTask()
          {	  HashMap<String,Object> hsts=new HashMap<String,Object>();
          		hsts.put(TASK_ID, escStrQNull(getTask_id()));
	  	  		hsts.put(PHONE_NUMBER_ONE, escStrQNull(getTxtPhone_number()));
	  	  		hsts.put(CALLER_NAME, escStrQNull(getTxtCaller_name()));
	  	  		hsts.put(TASK_NAME, escStrQNull(getTxtTask_name()));
	  	  		hsts.put(AGENT_ID, escStrQNull(getCmbAgent_id()));
	  	  		hsts.put(DEADLINE, escStrQNull(getTxtDeadline()));
	  	  		hsts.put(TASK_COMMENT, escStrQNull(getTxtTask_comment()));   
	  	  		String[] Querys = new String[1];
  	  		    Querys[0] = Consts.UPDATE_TASK;
        	  return toDatabase(Querys,hsts,insert_success,insert_failed)?SUCCESS:ERROR; 
          }
          public String getData()
          {		HashMap<String,Object> hsts=new HashMap<String,Object>();
          		hsts.put(TASK_ID,escStrQNull(getTask_id()));
            	String[] Querys = new String[1];
            	Querys[0] = Consts.GET_TASK;
            	returnData=fromDatabase(Querys, hsts, "successMsg", "errorMsg");
        	return "json";  
          }
          public String onBtnCreateTask()
          { 
        	  	HashMap<String,Object> hsts=new HashMap<String,Object>();
        	  	hsts.put(PHONE_NUMBER_ONE, escStrQNull(getTxtPhone_number()));
        	  	hsts.put(CALLER_NAME, escStrQNull(getTxtCaller_name()));
        	  	hsts.put(TASK_NAME, escStrQNull(getTxtTask_name()));
        	  	hsts.put(AGENT_ID, escStrQNull(getCmbAgent_id()));
        	  	hsts.put(DEADLINE, escStrQNull(getTxtDeadline()));
        	  	hsts.put(TASK_COMMENT, escStrQNull(getTxtTask_comment()));
        	  	String[] Querys = new String[1];
        	  	Querys[0] = Consts.INSERT_TASK;
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
          
         public LinkedHashMap<String,Object> fromDatabase(String[] baseQuerys,
        		HashMap<String, Object> hsts, String successMsg, String errorMsg) {
        		LinkedHashMap<String,Object> result=new LinkedHashMap<String, Object>();
        	  	String sql;
        		SQLException ex = new SQLException();
        		DBAccess db = getDBAccess();
        		db.connect();
        		// Connection conn = db.getConnection();
        		try {
        			SQLListReader reader = getSQLListReader();
        			// conn.setAutoCommit(false);
        			ResultSet rs;
        			int i = 0;
        			while (i < baseQuerys.length && db.getLastException() == null) {
        				if (baseQuerys[i] != null) {
        					sql = reader.findSQLByCommand(baseQuerys[i], hsts,true);
        					log(sql);
        					rs=db.select(sql);
        					while(rs.next()){
        						result.put("PHONE_NUMBER_ONE", rs.getString("PHONE_NUMBER_ONE"));
        						result.put("CALLER_NAME", rs.getString("CALLER_NAME"));
        						result.put("TASK_NAME", rs.getString("TASK_NAME"));
        						result.put("AGENT_ID", rs.getString("AGENT_ID"));
        						result.put("DEADLINE", rs.getString("DEADLINE"));
        						result.put("TASK_COMMENT", rs.getString("TASK_COMMENT"));
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
        					addActionError(getText(ERR010));
        					break;
        				// case 1451: addActionError(getText(ERR035));
        				default:
        					log(getText(errorMsg), db.getLastException());
        					addActionMessage(getText(errorMsg));
        				}
        				return result;

        			} else {
        				db.commit();
        				log(getText(successMsg));
        				addActionMessage(getText(successMsg));
        				return result;
        			}

        		} catch (Exception e) {
        			// Log bichih?
        			log("Баазтай холбогдох алдаа", e);

        			return result;
        		} finally {
        			db.disconnect();
        		}

        	}


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
          public String getCmbTask_type_id() 
          {
             return cmbTask_type_id ;
          }

          /**
           * @param cmbTask_type_id     */
          public void setCmbTask_type_id(String cmbTask_type_id)
          {
             this.cmbTask_type_id= cmbTask_type_id;
          }

          /**
           * @return
           */
          public String getCmbTask_detailed_type_id() 
          {
             return cmbTask_detailed_type_id ;
          }

          /**
           * @param cmbTask_detailed_type_id     */
          public void setCmbTask_detailed_type_id(String cmbTask_detailed_type_id)
          {
             this.cmbTask_detailed_type_id= cmbTask_detailed_type_id;
          }

          /**
           * @return
           */
          public String getTxtTask_comment() 
          {
             return txtTask_comment;
          }

          /**
           * @param txtTask_comment     */
          public void setTxtTask_comment(String txtTask_comment)
          {
             this.txtTask_comment= txtTask_comment;
          }

          /**
           * @param btnDelete
           */
          public String onBtnDeleteClick()
          {
             //CALL EVENT
             return SUCCESS; 
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
           * @param btnCreate_task
           */
         
          /**
           * @return
           */
          /**
           * @param btnReturn
           */
          public String onBtnReturnClick()
          {
             //CALL EVENT
             return SUCCESS; 
          }
		public String getTask_id() {
			return Task_id;
		}
		public void setTask_id(String task_id) {
			Task_id = task_id;
		}
		public LinkedHashMap<String, Object> getReturnData() {
			return returnData;
		}
		public void setReturnData(LinkedHashMap<String, Object> returnData) {
			this.returnData = returnData;
		}
		public String getTxtDeadline() {
			return txtDeadline;
		}
		public void setTxtDeadline(String txtDeadline) {
			this.txtDeadline = txtDeadline;
		}
}