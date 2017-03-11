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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;


import org.apache.struts2.ServletActionContext;
import org.iaac.common.BForm;
import org.iaac.consts.Consts;



/**
* @author 
*
*/
public class InsertDataTree extends BForm
{
   
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String TABLE_ID="TABLE_ID";
   
   private static final String TABLE_NAME="TABLE_NAME";
   
	private static final String ERR010 = "ERR010";
    /** Оруулах өгөгдлийн төрөл */
    
    private static HashMap<String, String> cmbInsertDataCallReqTypeList;

    private LinkedHashMap<Integer,Object> fatherList;
    
    private LinkedHashMap<Integer,Object> parentList;
    
    private LinkedHashMap<Integer,Object> displayList;
    
    private static final String select_success = "system.select_success";
    
    private static final String select_failed = "system.select_failed";
    /**
     * „A„p„z„s„…„…„|„p„s„‰ „†„…„~„{„€
     */
    public InsertDataTree()
    {
    }

    /* (non-Javadoc)
     * @see com.opensymphony.xwork2.ActionSupport#execute()
     */
    public String execute()
    {
    	HttpServletRequest request = ServletActionContext.getRequest();    	
    	Integer parent_value=Integer.parseInt(request.getParameter("parent"));
    	String[] Querys = new String[1];
    	switch(parent_value){
    			case 1:
    				Querys[0] = Consts.TREE_APPEAL_PS_214;
    				break;
    			case 3:
    				Querys[0] = Consts.TREE_CALL_SORT_PS_214;
    				break;
    			case 4:
    				Querys[0] = Consts.TREE_CALL_RATE_PS_214;
    				break;
    			case 5:
    				Querys[0] = Consts.TREE_CALL_RESULT_PS_214;
    				break;
    			case 6:
    				Querys[0] = Consts.TREE_CALL_STAT_PS_214;
    				break;
    			case 7:
    				Querys[0] = Consts.TREE_COMPLAINT_PS_214;
    				break;
    			case 11:
    				Querys[0] = Consts.TREE_HELP_PER_PS_214;
    				break;
    			case 12:
    				Querys[0] = Consts.TREE_INFOREQ_PS_214;
    				break;
    			case 13:
    				Querys[0] = Consts.TREE_REFERENCE_PS_214;
    				break;
    			case 14:
    				Querys[0] = Consts.TREE_SOURCE_PS_214;
    				break;
    			case 15:
    				Querys[0] = Consts.TREE_UNIT_PS_214;
    				break;
    			
    	}
    			  parentList=fromDatabaseFull(Querys,1,2,select_success,select_failed);
    			  fatherList=fromDatabaseFull(Querys,1,3,select_success,select_failed);
    		return "tree";
    
    }

    /* (non-Javadoc)
     * @see com.opensymphony.xwork2.ActionSupport#validate()
     */
    public void validate()
    {
    }
    public LinkedHashMap<Integer,Object> fromDatabaseFull(String[] baseQuerys,Integer m,Integer k, String successMsg, String errorMsg) {
    	LinkedHashMap<Integer,Object> result=new LinkedHashMap<Integer,Object>();
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
					sql = reader.findSQLByCommand(baseQuerys[i]);
					log(sql);
					ResultSet rs=db.select(sql);
					while(rs.next()){
					result.put(rs.getInt(m),rs.getString(k));
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
    public HashMap<String,Object> fromDatabase(String[] baseQuerys,
			HashMap<String, Object> hsts, String successMsg, String errorMsg) {
		HashMap<String,Object> result=new HashMap<String,Object>();
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
					while(rs.next()){
					result.put(rs.getString(1),rs.getString(3));
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
    /**
     * @return
     */
 
	public HashMap<Integer, Object> getFatherList() {
		return fatherList;
	}

	public void setFatherList(LinkedHashMap<Integer, Object> fatherList) {
		this.fatherList = fatherList;
	}

	public HashMap<Integer, Object> getParentList() {
		return parentList;
	}

	public void setParentList(LinkedHashMap<Integer, Object> parentList) {
		this.parentList = parentList;
	}

	public LinkedHashMap<Integer, Object> getDisplayList() {
		return displayList;
	}

	public void setDisplayList(LinkedHashMap<Integer, Object> displayList) {
		this.displayList = displayList;
	}

}
