/*
* „M„Ђ„t„u„|„y„z„~ „~„Џ„‚             InsertData.java
*
* „U„…„~„{„€„Ќ„~ „~„Џ„‚          ЃyӨгөгдөл оруулахЃz
* „Svv„‡
* „V„…„r„y„|„q„p„‚„Ќ„~ ‡‚  „O„s„~„Ђ„Ђ    „V„p„‚„y„…„€„p„s„‰    „@„s„…„…„|„s„p
* 01.00.00                 „O„s„~„Ђ„Ђ    „V„p„‚„y„…„€„p„s„‰    „Y„y„~„Џ„Џ„‚ „‡„y„z„ѓ„Џ„~/„H„p„ѓ„ѓ„p„~
*/


package org.golomt.req;


import infox.org.db.DBAccess;
import infox.org.sql.SQLListReader;

import java.net.URLDecoder;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;


import org.apache.struts2.ServletActionContext;
import org.golomt.common.XMLForm;
import org.iaac.consts.Consts;



/**
* @author 
*
*/
public class InsertData extends XMLForm 
{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/** „†„Ђ„‚„}„Ќ„~ „~„Џ„‚ */
   private static final String FORM_NAME = "InsertData.form_name" ;
   /**  */
   private static final String INFO_MESSAGE="InsertData.Мэдээлэл амжилттай хадгалагдлаа";
   /**  */
   private static final String INFO_MESSAGE1="InsertData.Мэдээлэл амжилттай устлаа";
   /**  */
   private static final String INFO_MESSAGE2="InsertData.Устгах мөрөө идэвхижүүлнэ үү";
   
   private static final String select_success = "system.select_success";
   
   private static final String select_failed = "system.select_failed";
   
   private static final String insert_success = "system.insert_success";
   
   private static final String insert_failed = "system.insert_failed";
   
   private static final String delete_success = "system.delete_success";
   
   private static final String delete_failed = "system.delete_failed";
   
   private static final String update_success = "system.update_success";
   
   private static final String update_failed = "system.update_failed";
   
   private static final String TABLE_ID="TABLE_ID";
   
   private static final String TABLE_NAME="TABLE_NAME";
   
   private static final String DISPLAY_ID="DISPLAY_ID";
   
   private static final String INSERT_USER="INSERT_USER";
   
   private static final String INSERT_DATE="INSERT_DATE";
   
   private static final String UPDATE_USER="UPDATE_USER";
   
   private static final String UPDATE_DATE="UPDATE_DATE";
   
   private static final String DELETE_USER="DELETE_USER";
   
   private static final String DELETE_DATE="DELETE_DATE";
   
   private static final String ORDER_NUM="ORDER_NUM";
   
   
	private static final String ERR010 = "ERR010";
    /** Оруулах өгөгдлийн төрөл */
    private String grpData_type;
   /** Өгөгдөл оруулах талбар */
    private String cmbInsert_data_type;
    /** Оруулах өгөгдөл */
    private String grpData_tree;
    /** Өгөгдөл нэмэх */
    private String grpInsert_data;
    /** Өгөгдөл харуулах */
    private String grdShow_data;
    /** Өгөгдлийн ID */
    private String lblStaticData_id;
    /** Өгөгдлийн ID */
    private String txtData_id;
    /** Өгөгдлийн дараалал */
    private String lblStaticData_order;
    /** Өгөгдлийн дараалал */
    private String txtData_order;
    /** Өгөгдлийн нэр */
    private String lblStaticData_name;
    /** Өгөгдлийн нэр */
    private String txtData_name;
    /** Шилжих талбар */
    private String lblStaticMigration;
   /** Шилжих талбар */
    private String cmbMigration_attrib;
    
    private HashMap<String, String> editData;
    
    private static HashMap<String, String> cmbInsertDataCallReqTypeList;

    /**
     * „A„p„z„s„…„…„|„p„s„‰ „†„…„~„{„€
     */
    private String id;
    
    private String parent_id;
    
    private String order_num_id;
    
    private String migration_num_id;
    
    private String disp_id;
    
    private String tree_name;
     	
    private String parent_key;
    
    private String name_key;
    
    private String migration_key;
    
    public InsertData()
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
    public String Save(){
    	String[] Querys = new String[1];
    	switch(Integer.parseInt(parent_id)){
    		case 1:
    			Querys[0] = Consts.SAVEROW_APPEAL_PS_214;
    			parent_key="PARENT_SERVICE_ID";
    			name_key="SERVICE_NAME";
    			migration_key="none";
    			break;
    		case 3:
    			Querys[0]=Consts.SAVEROW_CALL_SORT_PS_214;
    			parent_key="PARENT_CALL_SORT_ID";
    			name_key="CALL_SORT_NAME";
    			migration_key="MIGRATION_NUM";
    			break;
    		case 4:
    			Querys[0] = Consts.SAVEROW_CALL_RATE_PS_214;
    			parent_key="PARENT_CALL_RATE_ID";
    			name_key="CALL_RATE_NAME";
    			migration_key="MIGRATION_NUM";
    			break;
    		case 5:
    			Querys[0] = Consts.SAVEROW_CALL_RESULT_PS_214;
    			parent_key="PARENT_CALL_RESULT_ID";
    			name_key="CALL_RESULT_NAME";
    			migration_key="MIGRATION_NUM";
    			break;
    		case 6:
    			Querys[0] = Consts.SAVEROW_CALL_STAT_PS_214;
    			parent_key="PARENT_CALL_STAT_ID";
    			name_key="CALL_STAT_NAME";
    			migration_key="MIGRATION_NUM";
    			break;
    		case 7:
    			Querys[0] = Consts.SAVEROW_COMPLAINT_PS_214;
    			parent_key="PARENT_SERVICE_ID";
    			name_key="SERVICE_NAME";
    			migration_key="none";
    			break;
    		case 11:
    			Querys[0] = Consts.SAVEROW_HELP_PER_PS_214;
    			parent_key="PARENT_HELP_PER_ID";
    			name_key="HELP_PER_NAME";
    			migration_key="MIGRATION_NUM";
    			break;
    		case 12:
    			Querys[0] = Consts.SAVEROW_INFOREQ_PS_214;
    			parent_key="PARENT_SERVICE_ID";
    			name_key="SERVICE_NAME";
    			migration_key="none";
    			break;
    		case 13:
    			Querys[0] = Consts.SAVEROW_REFERENCE_PS_214;
    			parent_key="PARENT_SERVICE_ID";
    			name_key="SERVICE_NAME";
    			migration_key="none";
    			break;
    		case 14:
    			Querys[0] = Consts.SAVEROW_SOURCE_PS_214;
    			parent_key="PARENT_SOURCE_ID";
    			name_key="SOURCE_NAME";
    			migration_key="MIGRATION_NUM";
    			break;
    		case 15:
    			Querys[0] = Consts.SAVEROW_UNIT_PS_214;
    			parent_key="PARENT_UNIT_ID";
    			name_key="UNIT_NAME";
    			migration_key="MIGRATION_NUM";
    			break;
    		}
    		HashMap<String,Object> hsts=new HashMap<String, Object>();
    		hsts.put(parent_key, escStrQNull(id));
    		hsts.put(ORDER_NUM, escStrQNull(order_num_id));
    		hsts.put(migration_key, escStrQNull(migration_num_id));
    		hsts.put(DISPLAY_ID, escStrQNull(disp_id));	
    		hsts.put(name_key, escStrQNull(tree_name));
    		hsts.put(INSERT_USER, escStrQNull(getAgentID()));
    		hsts.put(INSERT_DATE, escStrQNull(now()));
    		setDisp_id("");
    	return toDatabase(Querys, hsts, insert_success, insert_failed)?"save":ERROR;
    }
    public String DeleteRow(){
    	String[] Querys = new String[1];
    	String key="";
    	switch(Integer.parseInt(parent_id)){
		case 1:
			Querys[0] = Consts.DELETEROW_APPEAL_PS_214;
			key="SERVICE_ID";
			break;
		case 3:
			Querys[0]=Consts.DELETEROW_CALL_SORT_PS_214;
			key="CALL_SORT_ID";
			break;
		case 4:
			Querys[0] = Consts.DELETEROW_CALL_RATE_PS_214;
			key="CALL_RATE_ID";
			break;
		case 5:
			Querys[0] = Consts.DELETEROW_CALL_RESULT_PS_214;
			key="CALL_RESULT_ID";
			break;
		case 6:
			Querys[0] = Consts.DELETEROW_CALL_STAT_PS_214;
			key="CALL_STAT_ID";
			break;
		case 7:
			Querys[0] = Consts.DELETEROW_COMPLAINT_PS_214;
			key="SERVICE_ID";
			break;
		case 11:
			Querys[0] = Consts.DELETEROW_HELP_PER_PS_214;
			key="HELP_PER_ID";
			break;
		case 12:
			Querys[0] = Consts.DELETEROW_INFOREQ_PS_214;
			key="SERVICE_ID";
			break;
		case 13:
			Querys[0] = Consts.DELETEROW_REFERENCE_PS_214;
			key="SERVICE_ID";
			break;
		case 14:
			Querys[0] = Consts.DELETEROW_SOURCE_PS_214;
			key="SOURCE_ID";
			break;
		case 15:
			Querys[0] = Consts.DELETEROW_UNIT_PS_214;
			key="UNIT_ID";
			break;
		}
    	HashMap<String,Object> hsts=new HashMap<String,Object>();
    	hsts.put(key, escStrQNull(id));
    	hsts.put(DELETE_DATE, escStrQNull(now()));
    	hsts.put(DELETE_USER, escStrQNull(getAgentID()));
    	setDisp_id("");
    	return toDatabase(Querys, hsts, delete_success, delete_failed)?"deleted":ERROR;
    }
    public String EditRow(){
    	if(migration_num_id=="null"||migration_num_id==null||migration_num_id==""){
    		migration_num_id="0";
    	}
    	String[] Querys = new String[1];
    	String key="";
    		switch(Integer.parseInt(parent_id)){
    		case 1:
    			Querys[0] = Consts.EDITROW_APPEAL_PS_214;
    			key="SERVICE_ID";
    			name_key="SERVICE_NAME";
    			migration_key="none";
    			break;
    		case 3:
    			Querys[0]=Consts.EDITROW_CALL_SORT_PS_214;
    			key="CALL_SORT_ID";
    			name_key="CALL_SORT_NAME";
    			migration_key="MIGRATION_NUM";
    			break;
    		case 4:
    			Querys[0] = Consts.EDITROW_CALL_RATE_PS_214;
    			key="CALL_RATE_ID";
    			name_key="CALL_RATE_NAME";
    			migration_key="MIGRATION_NUM";
    			break;
    		case 5:
    			Querys[0] = Consts.EDITROW_CALL_RESULT_PS_214;
    			key="CALL_RESULT_ID";
    			name_key="CALL_RESULT_NAME";
    			migration_key="MIGRATION_NUM";
    			break;
    		case 6:
    			Querys[0] = Consts.EDITROW_CALL_STAT_PS_214;
    			key="CALL_STAT_ID";
    			name_key="CALL_STAT_NAME";
    			migration_key="MIGRATION_NUM";
    			break;
    		case 7:
    			Querys[0] = Consts.EDITROW_COMPLAINT_PS_214;
    			key="SERVICE_ID";
    			name_key="SERVICE_NAME";
    			migration_key="none";
    			break;
    		case 11:
    			Querys[0] = Consts.EDITROW_HELP_PER_PS_214;
    			key="HELP_PER_ID";
    			name_key="HELP_PER_NAME";
    			migration_key="MIGRATION_NUM";
    			break;
    		case 12:
    			Querys[0] = Consts.EDITROW_INFOREQ_PS_214;
    			key="SERVICE_ID";
    			name_key="SERVICE_NAME";
    			migration_key="none";
    			break;
    		case 13:
    			Querys[0] = Consts.EDITROW_REFERENCE_PS_214;
    			key="SERVICE_ID";
    			name_key="SERVICE_NAME";
    			migration_key="none";
    			break;
    		case 14:
    			Querys[0] = Consts.EDITROW_SOURCE_PS_214;
    			key="SOURCE_ID";
    			name_key="SOURCE_NAME";
    			migration_key="MIGRATION_NUM";
    			break;
    		case 15:
    			Querys[0] = Consts.EDITROW_UNIT_PS_214;
    			key="UNIT_ID";
    			name_key="UNIT_NAME";
    			migration_key="MIGRATION_NUM";
    			break;
    		}
    		HashMap<String, Object> hsts=new HashMap<String, Object>();
    		hsts.put(key, escStrQNull(id));
    		hsts.put(ORDER_NUM, escStrQNull(order_num_id));
    		hsts.put(migration_key, escStrQNull(migration_num_id));
    		hsts.put(name_key,escStrQNull(tree_name));
    		hsts.put("UPDATE_USER", escStrQNull(getAgentID()));
    		hsts.put("UPDATE_DATE", escStrQNull(now()));
    		setDisp_id("");
    	return toDatabase(Querys, hsts, update_success, update_failed)?"save":ERROR;
    }
    public String Edit(){
    	String[] Querys = new String[1];
    	String key="";
    	switch(Integer.parseInt(parent_id)){
		case 1:
			Querys[0] = Consts.EDIT_APPEAL_PS_214;
			key="SERVICE_ID";
			break;
		case 3:
			Querys[0]=Consts.EDIT_CALL_SORT_PS_214;
			key="CALL_SORT_ID";
			break;
		case 4:
			Querys[0] = Consts.EDIT_CALL_RATE_PS_214;
			key="CALL_RATE_ID";
			break;
		case 5:
			Querys[0] = Consts.EDIT_CALL_RESULT_PS_214;
			key="CALL_RESULT_ID";
			break;
		case 6:
			Querys[0] = Consts.EDIT_CALL_STAT_PS_214;
			key="CALL_STAT_ID";
			break;
		case 7:
			Querys[0] = Consts.EDIT_COMPLAINT_PS_214;
			key="SERVICE_ID";
			break;
		case 11:
			Querys[0] = Consts.EDIT_HELP_PER_PS_214;
			key="HELP_PER_ID";
			break;
		case 12:
			Querys[0] = Consts.EDIT_INFOREQ_PS_214;
			key="SERVICE_ID";
			break;
		case 13:
			Querys[0] = Consts.EDIT_REFERENCE_PS_214;
			key="SERVICE_ID";
			break;
		case 14:
			Querys[0] = Consts.EDIT_SOURCE_PS_214;
			key="SOURCE_ID";
			break;
		case 15:
			Querys[0] = Consts.EDIT_UNIT_PS_214;
			key="UNIT_ID";
			break;
		}
    	HashMap<String, Object> hsts=new HashMap<String, Object>();
    	hsts.put(key, escStrQNull(id));
    	editData=fromDatabaseEdit(Querys, hsts, select_success, select_failed);
    	return "edit";
    }
    
    public HashMap<String, String> getCmbInsertDataCallReqTypeList() {
		if(cmbInsertDataCallReqTypeList == null)
		{				
			cmbInsertDataCallReqTypeList = new HashMap<String, String>();
			try
			{
				cmbInsertDataCallReqTypeList = getCombo(Consts.SLCT001_PS_214, TABLE_ID, TABLE_NAME, null);
			}
			catch (Exception e)
			{
				log("Өгөгдлийн сан руу хандах үед алдаа гарлаа", e);
				
			}
		}
		return cmbInsertDataCallReqTypeList;
	}
   
    public HashMap<String,String> fromDatabaseEdit(String[] baseQuerys,
			HashMap<String, Object> hsts, String successMsg, String errorMsg) {
    	HashMap<String,String> result=new HashMap<String, String>();
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
					sql = reader.findSQLByCommand(baseQuerys[i], hsts, true);
					log(sql);
					ResultSet rs=db.select(sql);
					ResultSetMetaData rsMetaData = rs.getMetaData();
					int numberOfColumns = rsMetaData.getColumnCount();
					while(rs.next()){
						result.put("id", rs.getString(1));
						result.put("name", rs.getString(2));
						result.put("order", rs.getString(3));
						for (int f = 1; f < numberOfColumns + 1; f++) {
						    if ("MIGRATION_NUM".equals(rsMetaData.getColumnName(f))) {
						    	result.put("migration", rs.getString(4));
						    }
						}	
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
    
    /**
     * @return
     */
   
    public String getGrpData_type() 
    {
       return grpData_type;
    }

    /**
     * @param grpData_type     */
    public void setGrpData_type(String grpData_type)
    {
       this.grpData_type= grpData_type;
    }

    /**
     * @return
     */
    public String getCmbInsert_data_type() 
    {
       return cmbInsert_data_type ;
    }

    /**
     * @param cmbInsert_data_type     */
    public void setCmbInsert_data_type(String cmbInsert_data_type)
    {
       this.cmbInsert_data_type= cmbInsert_data_type;
    }

    /**
     * @return
     */
    public String getGrpData_tree() 
    {
       return grpData_tree;
    }

    /**
     * @param grpData_tree     */
    public void setGrpData_tree(String grpData_tree)
    {
       this.grpData_tree= grpData_tree;
    }

    /**
     * @return
     */
    public String getGrpInsert_data() 
    {
       return grpInsert_data;
    }

    /**
     * @param grpInsert_data     */
    public void setGrpInsert_data(String grpInsert_data)
    {
       this.grpInsert_data= grpInsert_data;
    }

    /**
     * @return
     */
    public String getGrdShow_data() 
    {
       return grdShow_data;
    }

    /**
     * @param grdShow_data     */
    public void setGrdShow_data(String grdShow_data)
    {
       this.grdShow_data= grdShow_data;
    }

    /**
     * @return
     */
    public String getLblStaticData_id() 
    {
       return lblStaticData_id;
    }

    /**
     * @param lblStaticData_id     */
    public void setLblStaticData_id(String lblStaticData_id)
    {
       this.lblStaticData_id= lblStaticData_id;
    }

    /**
     * @return
     */
    public String getTxtData_id() 
    {
       return txtData_id;
    }

    /**
     * @param txtData_id     */
    public void setTxtData_id(String txtData_id)
    {
       this.txtData_id= txtData_id;
    }

    /**
     * @return
     */
    public String getLblStaticData_order() 
    {
       return lblStaticData_order;
    }

    /**
     * @param lblStaticData_order     */
    public void setLblStaticData_order(String lblStaticData_order)
    {
       this.lblStaticData_order= lblStaticData_order;
    }

    /**
     * @return
     */
    public String getTxtData_order() 
    {
       return txtData_order;
    }

    /**
     * @param txtData_order     */
    public void setTxtData_order(String txtData_order)
    {
       this.txtData_order= txtData_order;
    }

    /**
     * @return
     */
    public String getLblStaticData_name() 
    {
       return lblStaticData_name;
    }

    /**
     * @param lblStaticData_name     */
    public void setLblStaticData_name(String lblStaticData_name)
    {
       this.lblStaticData_name= lblStaticData_name;
    }

    /**
     * @return
     */
    public String getTxtData_name() 
    {
       return txtData_name;
    }

    /**
     * @param txtData_name     */
    public void setTxtData_name(String txtData_name)
    {
       this.txtData_name= txtData_name;
    }

    /**
     * @return
     */
    public String getLblStaticMigration() 
    {
       return lblStaticMigration;
    }

    /**
     * @param lblStaticMigration     */
    public void setLblStaticMigration(String lblStaticMigration)
    {
       this.lblStaticMigration= lblStaticMigration;
    }

    /**
     * @return
     */
    public String getCmbMigration_attrib() 
    {
       return cmbMigration_attrib ;
    }

    /**
     * @param cmbMigration_attrib     */
    public void setCmbMigration_attrib(String cmbMigration_attrib)
    {
       this.cmbMigration_attrib= cmbMigration_attrib;
    }

    /**
     * @param btnSave
     */
    public String onBtnSaveClick()
    {
       //CALL EVENT
       return SUCCESS; 
    }

    /**
     * @param btnDelete
     */
    public String onBtnDeleteClick()
    {
       //CALL EVENT
       return SUCCESS; 
    }

	public HashMap<String, String> getEditData() {
		return editData;
	}

	public void setEditData(HashMap<String, String> editData) {
		this.editData = editData;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMigration_key() {
		return migration_key;
	}

	public void setMigration_key(String migration_key) {
		this.migration_key = migration_key;
	}

	public String getMigration_num_id() {
		return migration_num_id;
	}

	public void setMigration_num_id(String migration_num_id) {
		this.migration_num_id = migration_num_id;
	}

	public String getName_key() {
		return name_key;
	}

	public void setName_key(String name_key) {
		this.name_key = name_key;
	}

	public String getOrder_num_id() {
		return order_num_id;
	}

	public void setOrder_num_id(String order_num_id) {
		this.order_num_id = order_num_id;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getParent_key() {
		return parent_key;
	}

	public void setParent_key(String parent_key) {
		this.parent_key = parent_key;
	}

	public String getTree_name() {
		return tree_name;
	}

	public void setTree_name(String tree_name) {
		this.tree_name = tree_name;
	}

	public String getDisp_id() {
		return disp_id;
	}

	public void setDisp_id(String disp_id) {
		this.disp_id = disp_id;
	}

}