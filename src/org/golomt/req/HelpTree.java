package org.golomt.req;


import infox.org.db.DBAccess;
import infox.org.sql.SQLListReader;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.iaac.common.BForm;
import org.iaac.consts.Consts;



public class HelpTree extends BForm {
	
	   private static final String FORM_NAME = "HelpTree.form_name" ;

	   
	   private static final String QUERY_BEGIN = "  ";	 	  
	 
	   public static final String CONTENT = " AND CONTENT LIKE '%?%'";
	   
	   private String tree_content_id;
	   private String txtId; 
	   private LinkedHashMap<String, String> editData;

	private static final long serialVersionUID = 1L;
	
	private LinkedHashMap<Integer,Object> hsts;
	private LinkedHashMap<Integer,Object> hsts1;
	private LinkedHashMap<Integer,Object> hsts2;

	private LinkedHashMap<String,Object> searchResult;
	private HashMap<String,Object> searchResult1;
	
	
	String txtContent;
	
	private boolean searchClick;
	
	private String parent_id1;
	private String content_num;
	private String tree_name;	
	private String search;
	
	
    public HelpTree()
    {
    }
  
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
	
	  public String getSearch()
		{		  	
			DBAccess db = getDBAccess();
			db.connect();
			Blob blob;
			InputStream in;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte b[] = new byte[4096];
			try {
				searchResult=new LinkedHashMap<String, Object>();
				PreparedStatement stmt = db.getConnection().prepareStatement("SELECT ID,NAME,CONTENT FROM HELP_TREE WHERE dbms_lob.instr(CONTENT,utl_raw.CAST_TO_RAW(?), 1, 1) > 0");
				stmt.setString(1, search);
				ResultSet rs=stmt.executeQuery();
				int i=0;
				while(rs.next()){
					searchResult.put("ID"+i, rs.getString("ID"));
					searchResult.put("NAME"+i, rs.getString("NAME"));
					blob= rs.getBlob("CONTENT");
					in = blob.getBinaryStream();
					int bytes;
					while ((bytes = in.read(b)) != -1){
							baos.write(b, 0, bytes);
					}
					searchResult.put("CONTENT"+i, new String(blob.getBytes(1, (int)blob.length()), "UTF-8"));
					i+=1;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			finally{
				db.disconnect();
			}
			return "tree";
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
					while(rs.next()){
						if(rs.getBlob(2)==null){
							return result;
						}
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

	public String getTxtContent() {
		return txtContent;
	}

	public void setTxtContent(String txtContent) {
		this.txtContent = txtContent;
	}

	public boolean isSearchClick() {
		return searchClick;
	}

	public void setSearchClick(boolean searchClick) {
		this.searchClick = searchClick;
	}

	public String getSearcher() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public LinkedHashMap<String, Object> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(LinkedHashMap<String, Object> searchResult) {
		this.searchResult = searchResult;
	}

	public HashMap<String, Object> getSearchResult1() {
		return searchResult1;
	}

	public void setSearchResult1(HashMap<String, Object> searchResult1) {
		this.searchResult1 = searchResult1;
	}

	
	
	




  
	


} 
