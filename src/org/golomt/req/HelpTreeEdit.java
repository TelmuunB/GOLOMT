package org.golomt.req;


import infox.org.db.DBAccess;
import infox.org.sql.SQLListReader;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.iaac.common.BForm;
import org.iaac.consts.Consts;

import com.lowagie.text.pdf.codec.Base64.OutputStream;



public class HelpTreeEdit extends BForm {
	
	   private static final String select_success = "system.select_success";

	   private static final String select_failed = "system.select_failed"; 

	   private static final String delete_success = "system.delete_success";

	   private static final String delete_failed = "system.delete_failed";
	   private String tree_content_id;
	   private String tree_id;
	   private String Content;
	   private String name;
	 

	   private String txtId;
	   private LinkedHashMap<String, String> editData;
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private LinkedHashMap<Integer,Object> hsts;
	private LinkedHashMap<Integer,Object> hsts1;
	private LinkedHashMap<Integer,Object> hsts2;

	private String parent_id1;
	private String content_num;
	private String tree_name;

	public String execute(){

		return SUCCESS;
	    }
	public String Select(){
		  String[] Querys = new String[1];
		  Querys[0] = Consts.HELP_TREE_PS_214;
		  hsts=fromDatabase(Querys,1,2,"success", "error");
		  hsts1=fromDatabase(Querys, 1, 3, "success", "error");

		return "tree";
	}	
	
	public String ContentRead(){
		  String[] Querys1 = new String[1];
		  Querys1[0] = Consts.HELP_TREE_CONTENT_PS_214;
		  HashMap<String,Object> hftf=new HashMap<String, Object>();
		  hftf.put("ID", escStrQNull(tree_content_id));
		  hsts2=fromDatabase1(Querys1,hftf,"success", "error");	 
		return "tree";
	}
	public String DeleteRow(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String id=request.getParameter("tree_id");
    	String[] Querys = new String[1];
    	String key="";
    	String date="DELETE_DATE";
    	Querys[0] = Consts.DELETEROW_HELP_TREE_SORT_PS_214;
		key="ID";
    	HashMap<String,Object> hsts=new HashMap<String,Object>();
    	hsts.put(key, id);
    	hsts.put(date, now());
    	toDatabase(Querys, hsts, delete_success, delete_failed);
    	return "tree";
    }
	
	public String Save(){	
				if(content_num==null||content_num==""){
				content_num="0";
				}
    	    	SQLException ex = new SQLException();
    	    	DBAccess db = getDBAccess();
    	    	db.connect();
    	    	try {
    	    		ByteArrayInputStream bais = new ByteArrayInputStream(content_num.getBytes("UTF-8"));
    	    		PreparedStatement stmt = db.getConnection().prepareStatement("INSERT INTO HELP_TREE(PARENT_ID,NAME,CONTENT)VALUES(?,?,?)");
    	    		stmt.setString(1, parent_id1);
    	    		stmt.setString(2, tree_name);
    	    		stmt.setBinaryStream(3, bais, content_num.getBytes("UTF-8").length);
    	    		stmt.execute();
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
    						log(getText("errorMsg"), db.getLastException());
    						addActionMessage(getText("errorMsg"));
    					}
    					return "tree";

    				} else {
    					db.commit();
    					log(getText("successMsg"));
    					addActionMessage(getText("successMsg"));
    					return "tree";
    				}
    				
    	    	}catch (Exception e) {
    				// Log bichih?
    				log("Баазтай холбогдох алдаа", e);

    				return "tree";
    			} finally {
    				db.disconnect();
    			}	
    		
    }

	public String EditRow(){
    	    	SQLException ex = new SQLException();
    	    	DBAccess db = getDBAccess();
    	    	db.connect();
    	    	try {
    	    		ByteArrayInputStream bais = new ByteArrayInputStream(Content.getBytes("UTF-8"));
    	    		PreparedStatement stmt = db.getConnection().prepareStatement("UPDATE HELP_TREE SET NAME=?,CONTENT=? WHERE ID=?");
    	    		stmt.setString(3, tree_id);
    	    		stmt.setString(1, name);
    	    		stmt.setBinaryStream(2, bais, Content.getBytes("UTF-8").length);
    	    		stmt.execute();
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
    						log(getText("errorMsg"), db.getLastException());
    						addActionMessage(getText("errorMsg"));
    					}
    					return "tree";

    				} else {
    					db.commit();
    					log(getText("successMsg"));
    					addActionMessage(getText("successMsg"));
    					return "tree";
    				}
    				
    	    	}catch (Exception e) {
    				// Log bichih?
    				log("Баазтай холбогдох алдаа", e);

    				return "tree";
    			} finally {
    				db.disconnect();
    			}
		
    }
	public String Edit(){
    	String[] Querys = new String[1];
    	String key="";
		Querys[0] = Consts.EDIT_HELP_TREE_214;
		key="ID";
		HashMap<String, Object> hsts=new HashMap<String, Object>();
    	hsts.put(key, tree_id);
    	editData=fromDatabaseEdit(Querys, hsts, select_success, select_failed);
    	return "tree";
    	
    }

    public LinkedHashMap<String,String> fromDatabaseEdit(String[] baseQuerys,
		HashMap<String, Object> hsts, String successMsg, String errorMsg) {
    	LinkedHashMap<String,String> result=new LinkedHashMap<String, String>();
		String sql;
		SQLException ex = new SQLException();
		DBAccess db = getDBAccess();
		db.connect();
		// Connection conn = db.getConnection();
		Blob blob;
		InputStream in;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte b[] = new byte[4096];
		try {
			SQLListReader reader = getSQLListReader();
			// conn.setAutoCommit(false);
			int i = 0;
			while (i < baseQuerys.length && db.getLastException() == null) {
				if (baseQuerys[i] != null) {
					sql = reader.findSQLByCommand(baseQuerys[i],hsts);
					log(sql);
					ResultSet rs=db.select(sql);
					while(rs.next()){
						if(rs.getBlob(3)==null){
							 result.put("CONTENT", "");
						}else{
							 blob= rs.getBlob(3);
							 in = blob.getBinaryStream();
							 int bytes;
							 while ((bytes = in.read(b)) != -1){
								baos.write(b, 0, bytes);
							 }
							 result.put("CONTENT", new String(blob.getBytes(3, (int)blob.length()), "UTF-8"));
						}
						result.put("ID", rs.getString(1));
						result.put("NAME", rs.getString(2));
						
					}
				}
				i++;
			}
			baos.close();
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

	public LinkedHashMap<Integer,Object> fromDatabase(String[] baseQuerys,Integer m,Integer k, String successMsg, String errorMsg) {
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
					addActionError(getText("ERR010"));
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
	 * Баазаас контент уншихал ашиглана.
	 */
	
	public LinkedHashMap<Integer,Object> fromDatabase1(String[] baseQuerys,HashMap<String,Object> hsts, String successMsg, String errorMsg) {
    	LinkedHashMap<Integer,Object> result=new LinkedHashMap<Integer,Object>();
		String sql;
		SQLException ex = new SQLException();
		DBAccess db = getDBAccess();
		db.connect();
		// Connection conn = db.getConnection();
		Blob blob;
		InputStream in;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte b[] = new byte[4096];
		try {
			SQLListReader reader = getSQLListReader();
			// conn.setAutoCommit(false);
			int i = 0;
			while (i < baseQuerys.length && db.getLastException() == null) {
				if (baseQuerys[i] != null) {
					sql = reader.findSQLByCommand(baseQuerys[i],hsts,true);
					log(sql);
					ResultSet rs=db.select(sql);
					if(rs.next()){
						if(rs.getBlob(2)==null){
							result.put(rs.getInt(1),"<p>No data</p>");
						}else{
						 blob= rs.getBlob(2);
						 in = blob.getBinaryStream();
						 int bytes;
						 while ((bytes = in.read(b)) != -1){
							baos.write(b, 0, bytes);
						 }
						//
						result.put(rs.getInt(1), new String(blob.getBytes(1, (int)blob.length()), "UTF-8"));
						}
					}
				}
				i++;
			}
			baos.close();
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
	public LinkedHashMap<String,Object> fromDatabaseAll(String[] baseQuerys, String successMsg, String errorMsg) {
    	LinkedHashMap<String,Object> result=new LinkedHashMap<String,Object>();
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
					int r = 0;
					while(rs.next()){
					result.put("ID_"+r, rs.getString("ID"));
					result.put("PARENT_"+r, rs.getString("PARENT_ID"));
					result.put("NAME_"+r, rs.getString("NAME"));
					result.put("TYPE_"+r, rs.getString("TYPEID"));
					r+=1;
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
					sql = reader.findSQLByCommand(baseQuerys[i], hsts);
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


  


	/**
	 * @param con
	 * @param id
	 * @param data
	 * @throws SQLException
	 */
/*	public static void insertBlob(Connection con, String id, String data) throws SQLException {

		ByteArrayInputStream bais = new ByteArrayInputStream(data.getBytes());
		PreparedStatement stmt = con.prepareStatement("INSERT INTO TREE_HELP (id, content) VALUES(?, ?)");
		
		stmt.setString(0, id) ;
		stmt.setBinaryStream(1, bais, data.getBytes().length);
		stmt.executeUpdate();
	}*/

	/**
	 * @param con
	 * @param id
	 * @param data
	 * @throws SQLException
	 */
	public static void updateBlob(Connection con, String id, String data) throws SQLException {

		ByteArrayInputStream bais = new ByteArrayInputStream(data.getBytes());
		PreparedStatement stmt = con.prepareStatement("UPDATE HELP_TREE SET Content=? WHERE ID= ?");

		stmt.setString(0, id) ;
		stmt.setBinaryStream(1, bais, data.getBytes().length);
		stmt.executeUpdate();
	}


	public HashMap<Integer, Object> getHsts() {
		return hsts;
	}

	public void setHsts(LinkedHashMap<Integer, Object> hsts) {
		this.hsts = hsts;
	}

	public HashMap<Integer, Object> getHsts1() {
		return hsts1;
	}

	public void setHsts1(LinkedHashMap<Integer, Object> hsts1) {
		this.hsts1 = hsts1;
	}

	public String getTxtId() {
		return txtId;
	}

	public void setTxtId(String txtId) {
		this.txtId = txtId;
	}

	public HashMap<String, String> getEditData() {
		return editData;
	}

	public void setEditData(LinkedHashMap<String, String> editData) {
		this.editData = editData;
	}

	public LinkedHashMap<Integer, Object> getHsts2() {
		return hsts2;
	}

	public void setHsts2(LinkedHashMap<Integer, Object> hsts2) {
		this.hsts2 = hsts2;
	}

	public String getTree_content_id() {
		return tree_content_id;
	}

	public void setTree_content_id(String tree_content_id) {
		this.tree_content_id = tree_content_id;
	}

	public String getContent_num() {
		return content_num;
	}

	public void setContent_num(String content_num) {
		this.content_num = content_num;
	}

	public String getParent_id1() {
		return parent_id1;
	}

	public void setParent_id1(String parent_id) {
		this.parent_id1 = parent_id;
	}

	public String getTree_name() {
		return tree_name;
	}

	public void setTree_name(String tree_name) {
		this.tree_name = tree_name;
	}

	public String getTree_id() {
		return tree_id;
	}

	public void setTree_id(String tree_id) {
		this.tree_id = tree_id;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}













}
