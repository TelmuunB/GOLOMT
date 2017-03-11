/*
* „M„Ђ„t„u„|„y„z„~ „~„Џ„‚             DistributeTask.java
*
* „U„…„~„{„€„Ќ„~ „~„Џ„‚          ЃyДэлгэцийн зохиомжЃz
* „Svv„‡
* „V„…„r„y„|„q„p„‚„Ќ„~ ‡‚  „O„s„~„Ђ„Ђ    „V„p„‚„y„…„€„p„s„‰    „@„s„…„…„|„s„p
* 01.00.00                 „O„s„~„Ђ„Ђ    „V„p„‚„y„…„€„p„s„‰    „Y„y„~„Џ„Џ„‚ „‡„y„z„ѓ„Џ„~/„H„p„ѓ„ѓ„p„~
*/


package org.golomt.detail;


import infox.org.components.FileUpload;
import infox.org.db.DBAccess;
import infox.org.sql.SQLListReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;



import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.contrib.view.SVTableModel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.hssf.usermodel.HSSFBorderFormatting;

import org.iaac.consts.Consts;
import org.golomt.common.Constants;
//import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.struts2.ServletActionContext;
import org.golomt.common.XMLForm;


import sun.net.www.protocol.http.HttpAuthenticator;

import antlr.collections.List;

import com.lowagie.text.pdf.hyphenation.TernaryTree.Iterator;
import com.opensymphony.xwork2.ActionContext;


/**
* @author 
*
*/
public class DistributeTask extends XMLForm 
{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/** „†„Ђ„‚„}„Ќ„~ „~„Џ„‚ */
   private static final String FORM_NAME = "DistributeTask.form_name" ;
    /** Хайлт */
    private String grpSearch;
    /** Утасны дугаар */
    private String txtPhone_number;
    /** Агентын № */
    private String cmbAgent_id;
    /** Үйлчлүүлэгчийн нэр */
    private String txtCaller_name;
    /** Даалгаврын нэр */
   private String txtTask_name;
    /** Даалгаврын төрөл */
    private String cmbCall_type_id;
    /** Даалгавар оруулсан */
   private String cmbInsert_agent;
    /** Даалгавар дуусах хугацаа */
    private String txtDeadlineStart;
    private String txtDeadlineOver;
    /** Дуудлагын үр дүн */
   private String cmbCall_result_id;
    /** Даалгаврын жагсаалт */
    private String grdTask_list;
    /** Даалгаврын жагсаалт */
    private String grpTask_list;
    /** Даалгавар оруулах */
    private String grpInsert_task;
   /** Дуудлагын төрөл */
    private String cmbCall_req_type_id;
   /** Холбоотой нэгж */
    private String cmbUnit_id;
   /** Даалгаврын төрөл */
    private String cmbTask_type_id;
   /** Даалгаврын нарийвчилсан төрөл */
    private String cmbTask_detailed_type_id;
    /** Дуудлагын ангилал */
    private String lblCall_sort_id;
    /** Дуудлагын үр дүн */

    /** Дуудлагын үр дүн */
    private String lblCall_result_id;
    /** Дуудлагын үнэлгээ */
    private String lblCall_rate_id;
    /** Даалгаврын тэмдэглэл */
    private String txtTask_comment;
    /** Excel-ээс оруулж ирсэн жагсаалт */
    private String grpExcel_import;
    /** Excel-ээс оруулж ирсэн жагсаалт */
    private String grdExcel_import;
    /**     Excel file  				*/
    private String chkExcel;
    
    private File ExcelFile; 
    
    private String UploadFile;
    protected HSSFWorkbook wbAID = null;
    
    private String uploadExcelContentType; //The content type of the file
    
    private String uploadExcelFileName; //The uploaded file name
    
	private static final String update_success = "system.update_success";
	
	private static final String DATE_FORMAT = "yyyy-MM-dd-hh-mm-ss";
	
	
	private static final String insert_success = "system.insert_success";
	   
	private static final String insert_failed = "system.insert_failed";
	/** application/xls  */
	
	protected static final String FILE_NAME = "FILE_NAME";
	protected static final String UPLOAD_STATUS = "UPLOAD_STATUS";
	/** ХОМ мэдүүлэгийн файл биш эсвэл баталгаажуулах товч дарагдаагүй байна. */
    protected static final String FILEERR = "CommonErrors.FILEERR";
    /** Нэмэх үйлдэл амжилттай ажиллаа. */
    protected static final String INSERTSUCCESS = "CommonErrors.INSERTSUCCESS";
    /** Ексэл файлаа дахин татаж авч бөглөнө үү */
    protected static final String MASTERERR = "CommonErrors.MASTERERR";
    /** ZIP болон EXCEL - с өөр файл байна. Заавал дээрх өргөтгөлтөй файлуудыг шалгана. */
    protected static final String UPLOADERR = "CommonErrors.UPLOADERR";
    /** Та хуулах файлаа сонгоно уу. */
    protected static final String UPLOADFILE = "CommonErrors.UPLOADFILE";
    
    private static final String update_failed = "system.update_failed";
	
	private File uploadExcel;//The actual file
	
	
    protected static final String AID_NUM = "AID_NUM";
    
	private static final String ERR010 = "ERR010";
    /** ХОМ мэдүүлэгчийн хүснэгтэндээр гарах мэдээлэл */
	private ArrayList<HashMap<String, Object>> lst = new ArrayList<HashMap<String,Object>>();

	private static final String[] FIELD_LIST = {"TASK_ID","PHONE_NUMBER","CALLER_NAME","TASK_NAME","AGENT_ID","DEADLINE","RESULT"};
	private static final String[] FIELD_LIST_ = {"TASK_ID","PHONE_NUMBER_ONE","CALLER_NAME","TASK_NAME","AGENT_ID","TASK_COMMENT","DEADLINE","INSERT_USER","CALL_RESULT_NAME","CALL_RESULT_ID","CALL_REQ_TYPE_NAME","CALL_REQ_TYPE_ID"};
	public static final String QUERY_WHERE = " WHERE ";
	/** хоосон хайлт */
	private static final String QUERY_BEGIN = " ";	
	public static final String WHERE_PHONE_NUMBER = " AND PHONE_NUMBER_ONE LIKE '%?%'";
	public static final String WHERE_CALLER_NAME  = " AND UPPER(CALLER_NAME) LIKE UPPER('%?%')";
	public static final String WHERE_TASK_NAME    = " AND UPPER(TASK_NAME) LIKE UPPER('%?%')";
	public static final String WHERE_AGENT_ID     = " AND AGENT_ID LIKE '%?%'";	
	public static final String WHERE_DEADLINE     = " AND T.DEADLINE BETWEEN to_timestamp('?', 'YYYY-MM-DD') AND to_timestamp('?', 'YYYY-MM-DD')";
	public static final String WHERE_INSERT_USER  = " AND T.INSERT_USER  LIKE '%?%'";
	public static final String WHERE_CALLTYPE  = " AND T.CALL_REQ_TYPE_ID LIKE '%?%'";
	public static final String WHERE_CALLRESULT  = " AND CHS.CALL_RESULT_ID LIKE '%?%'";
	
	
	/** Эрэмбэлэх талбарын */
	private static final String ORDER_BY = " ORDER BY ";
	
	private int page;	
	private String sidx;
    private String sord;
    private boolean searchClick;
	private HashMap<String, String> excelID;
	public String getID;
	private String test;
	private String test1;
	HashMap<String,Object> getDataList1;
	HashMap<String,Object> result=new HashMap<String, Object>();
	
	protected HashMap<String, String> cmbCallType;
	protected HashMap<String, String> cmbCallResult;
	protected HashMap<String, String> cmbCallResultList;
	
	public String deteleFromGrid()
	{
		String testD[] = test.split(",");	
		for(int i=0;i<testD.length;i++)
		{
			if(testD[i].matches("[+-]?\\d*(\\.\\d+)?"))
			{
				String[] Querys = new String[1];
				Querys[0] = Consts.DLT_TASK;
		 	    HashMap<String,Object> tek=new HashMap<String,Object>();
		        tek.put("TASK_ID", testD[i]);
		        tek.put("DELETE_DATE", now());
		        tek.put("DELETE_USER", getAgentID());
		 	    toDatabase(Querys,tek,insert_success,insert_failed);
			}
			
		}
		return SUCCESS;
	}
	public String deleteFromGridTemp(){
		String test1D[] = test1.split(",");
		for(int i=0;i<test1D.length;i++){
			if(test1D[i].matches("[+-]?\\d*(\\.\\d+)?")){
				String[] Querys=new String[1];
				Querys[0] = Consts.DLT_TASK_TEMP;
				HashMap<String,Object> tek=new HashMap<String, Object>();
				tek.put("TASK_ID",test1D[i]);
				toDatabase(Querys,tek,insert_success,insert_failed);
			}
		}
		return SUCCESS;
	}

	public String excelToData() throws SQLException
	{
		String[] testD = test.split(",");	
		for(int i=0;i<testD.length;i++)
		{			
			if(testD[i].matches("[+-]?\\d*(\\.\\d+)?"))
			{
				HashMap<String, Object> hsts = new HashMap<String, Object>();
		    	hsts.put("TASK_ID", testD[i]);
		 	   	String[] Querys = new String[1];
		 	   	
		 	   	Querys[0] = Consts.SLCT_TASK_TEMP_PS_211;	 
		 	   	getDataList1=fromDatabase(Querys,hsts,"succes","failed");	
		 	   	
		 	    Querys[0] = Consts.INS_TASK;
		 	    toDatabase(Querys, result,update_success,update_failed);
		 	    
		 	    Querys[0] = Consts.DLT_TASK_TEMP;
		 	    HashMap<String,Object> tek=new HashMap<String,Object>();
		        tek.put("TASK_ID", testD[i]);
		 	    toDatabase(Querys,tek,insert_success,insert_failed);
			}
			
		}		
		return SUCCESS;
	}	
	
	public HashMap<String,Object> fromDatabase(String[] baseQuerys,
				HashMap<String, Object> hsts, String successMsg, String errorMsg) {
	    	
			String sql;
			SQLException ex = new SQLException();
			DBAccess db = getDBAccess();
			db.connect();
			// Connection conn = db.getConnection();
			try {
				SQLListReader reader = getSQLListReader();
				
				int i = 0;
				while (i < baseQuerys.length && db.getLastException() == null) {
					if (baseQuerys[i] != null) {
						sql = reader.findSQLByCommand(baseQuerys[i], hsts, true);
						log(sql);
						ResultSet rs=db.select(sql);
						
						while(rs.next()){
							result.put("PHONE_NUMBER_ONE", rs.getString("PHONE_NUMBER"));
							result.put("CALLER_NAME"	, rs.getString("CALLER_NAME"));
							result.put("AGENT_ID"		, rs.getString("AGENT_ID"));
							result.put("TASK_NAME"		, rs.getString("TASK_NAME"));							
							result.put("TASK_COMMENT"	, rs.getString("RESULT"));
							result.put("DEADLINE"		, rs.getString("DEADLINE").replace(". 0",""));
							/*
							String[] Querys = new String[1];
		    				Querys[0] = Consts.INS_TASK;
		    				if(toDatabase(Querys, result,update_success,update_failed))
		    				{
		    					System.out.println("id is " + rs.getString("TASK_ID"));
		    					HashMap<String,Object> tek=new HashMap<String,Object>();
		    			        tek.put("TASK_ID", rs.getString("TASK_ID"));
		    			        String[] Quer = new String[1];
		    			 	   	Quer[0] = Consts.DLT_TASK_TEMP;		    			 	   	
		    			 	   	if(toDatabase(Quer,tek,insert_success,insert_failed))
		    			 	   	{
		    			 	   		System.out.println(" the data is deleted shdeeeeee ");
		    			 	   	}
		    				} */
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
	
	
	public HashMap<String, String> getExcelID() {
		return excelID;
	}

	public void setExcelID(HashMap<String, String> excelID) {
		this.excelID = excelID;
	}

	/**
     * „A„p„z„s„…„…„|„p„s„‰ „†„…„~„{„€
     */
    public DistributeTask()
    {
    }
     
    public String getID()
    {	
    	return SUCCESS;
    }
    /**
	 * Серверлуу хуулж байгаа файлууд хуулах хавтасны нэрийг үүсгэх
	 * @return
	 */
	public String createFolderName()
	{
		String name;
		name = now(DATE_FORMAT);
		return name;
	}

	/* (non-Javadoc)
     * @see com.opensymphony.xwork2.ActionSupport#execute()
     */
    public String execute() 
    {
    
    	return SUCCESS; 
    }
    
    
    public String getFields() throws FileNotFoundException 
    {	
        //File f = new File(UploadFile);
       /*
        if(f.exists())
        {
        	System.out.println("bnaaaaaaaaaaaaaa bna");
        }else System.out.println("alga  " + UploadFile);
        */
    	
    		try {
    	 			
    			FileInputStream file = new FileInputStream(ExcelFile);
	
    			wbAID = new HSSFWorkbook(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	HashMap<String, Object> hsts = new HashMap<String, Object>();
    		String PHONE_NUMBER = "PHONE_NUMBER";
    		String CALLER_NAME = "CALLER_NAME";
    		String TASK_NAME= "TASK_NAME";
    		String AGENT_ID = "AGENT_ID";
    		String DEADLINE="DEADLINE";
    		String RESULT= "RESULT";
    		
        	//InputStream myxls = new FileInputStream(file);		
    		HSSFSheet sheet = wbAID.getSheetAt(0);
    		
    		HSSFCell phone 	   = null;
    		HSSFCell caller	   = null;
    		HSSFCell task_name = null;
    		HSSFCell agent     = null;
    		HSSFCell deadline  = null;
    		HSSFCell comment   = null;
    		
    		SVTableModel model = new SVTableModel(sheet);
    		
    		for(int j=1;j<=model.getRowCount();j++)
    			{	
    				phone 	  = sheet.getRow(j).getCell(0);
    				caller    = sheet.getRow(j).getCell(1); 
    				task_name = sheet.getRow(j).getCell(2);
    				agent 	  = sheet.getRow(j).getCell(3); 
    				deadline  = sheet.getRow(j).getCell(4); 
    				comment    = sheet.getRow(j).getCell(5); 
    				
    				hsts.clear();
    				hsts.put(PHONE_NUMBER , phone);	  
    				hsts.put(CALLER_NAME  , caller);
    				hsts.put(TASK_NAME 	  , task_name);
    				hsts.put(AGENT_ID 	  , agent);
    				hsts.put(DEADLINE 	  , deadline); 
    				hsts.put(RESULT 	  ,	comment); 
    				
    				String[] Querys = new String[1];
    				Querys[0] = Consts.INSRT_TASK_TEMP;
    				if(toDatabase(Querys, hsts,update_success,update_failed))
    				{
    			
    				}    			  			
    			
    		}    
    	    return SUCCESS;
    }
    
    
    ///****
    public String getGridField() throws ParseException
    {
    	StringBuffer where = new StringBuffer(QUERY_BEGIN);	
    	buildWQuery(where, WHERE_PHONE_NUMBER, new String[] { getTxtPhone_number()});
    	buildWQuery(where, WHERE_CALLER_NAME, new String[] { getTxtCaller_name()});
    	buildWQuery(where, WHERE_TASK_NAME, new String[] { getTxtTask_name()});    	
    	buildWQuery(where, WHERE_AGENT_ID, new String[] { getCmbAgent_id()});
    	buildWQuery(where, WHERE_INSERT_USER, new String[] { getCmbInsert_agent()});
    	buildWQuery(where, WHERE_CALLTYPE, new String[] { getCmbCall_type_id()});
    	buildWQuery(where, WHERE_CALLRESULT, new String[] { getCmbCall_result_id()});
    	if(getTxtDeadlineStart()!=null)
    	{
    		if(escStrQNull(getTxtDeadlineStart())!="null"){
    		buildWQuery(where, WHERE_DEADLINE, new String[] { getTxtDeadlineStart(),getTxtDeadlineOver()});
    		}
    		
    	}
    	if(getChkExcel().equals("true"))
    	{
    		where.append("AND EXCEL_TASK IS NOT NULL");
    	}
    	StringBuffer ord = new StringBuffer();

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
			while (i < FIELD_LIST_.length && !FIELD_LIST_[i].equals(sidx))
				i++;
			if (i < FIELD_LIST_.length && ("asc".equals(sord) || "desc".equals(sord)))
			{
				ord.append(ORDER_BY);
				ord.append(sidx);
				ord.append(" ");
				ord.append(sord);
				}
		}
		if (isSearchClick())
		{			
			log(FORM_NAME);
			HashMap<String, Object> hsts = new HashMap<String, Object>();
			hsts.put(Constants.QUERY_WHERE, where);
			hsts.put(Constants.QUERY_ORDER, ord);
			DBAccess db = getDBAccess();
			db.connect();
			int totalResult = 0;
			try
			{
			
				SQLListReader reader = getSQLListReader();
				String sql = reader.findSQLByCommand(Consts.SLCT002_PS_211, hsts, true);
				ResultSet rs = db.select(sql);
				while(rs.next()){
					totalResult++;
				}
				rs = db.select(sql);
				xmlOutput(rs, page, ((totalResult - 1) / LIMIT_GRID + 1), totalResult, FIELD_LIST_);				
			    log(sql);
				return XML;
			}
			catch (Exception e)
			{
				log("DATABASE CONNECT ERROR", e);
				return ERROR;
			}
			finally
			{
				db.disconnect();
			}
		}
		else
		{

			return XML;
		}
    }
    /**
	 * Шүүлт хийж гарч ирсэн өгөгдлийг grid руу гаргана
	 */
    public String getXML()
	{    	
		StringBuffer where = new StringBuffer(QUERY_BEGIN);
		StringBuffer ord = new StringBuffer();
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
				ord.append(ORDER_BY);
				ord.append(sidx);
				ord.append(" ");
				ord.append(sord);
				}
		}
		
		if (isSearchClick())
		{			
			log(FORM_NAME);

			HashMap<String, Object> hsts = new HashMap<String, Object>();
			hsts.put(Constants.QUERY_WHERE, where);
			hsts.put(Constants.QUERY_ORDER, ord);
			DBAccess db = getDBAccess();
			db.connect();
			int totalResult = 0;
			try
			{
				SQLListReader reader = getSQLListReader();
				String sql = reader.findSQLByCommand("SLCT_TASK_TEMP", hsts, true);
				ResultSet rs = db.select(sql);
				while(rs.next()){
					totalResult+=1;
				}
				rs=db.select(sql);
				xmlOutput(rs, page, ((totalResult - 1) / LIMIT_GRID + 1), totalResult, FIELD_LIST);
			    log(sql);
				return XML;
			}
			catch (Exception e)
			{
				log("DATABASE CONNECT ERROR", e);
				return ERROR;
			}
			finally
			{
				db.disconnect();
			}
		}
		else
		{
			return XML;
		}
	}

    ///////////////////////////////////////////////////////////////////////////
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
					sql = reader.findSQLByCommand(baseQuerys[i], hsts, false);
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
    /////////////////////////////////////////////////////////////////////////////////////////////////
    
    public File getExcelFile() {
		return ExcelFile;
	}



	public void setExcelFile(File ExcelFile) {
	
		this.ExcelFile = ExcelFile;
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
  
    public String getCmbCall_type_id() 
    {
       return cmbCall_type_id ;
    }

    /**
     * @param cmbCall_type_id     */
    public void setCmbCall_type_id(String cmbCall_type_id)
    {
       this.cmbCall_type_id= cmbCall_type_id;
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
     * @param btnSearch
     * @throws FileNotFoundException 
     */
    public String onBtnSearchClick() throws FileNotFoundException 
    {
       //CALL EVENT   	
       return INPUT; 
    }

    /**
     * @param btnClear
     */
    public String onBtnClearClick()
    {
       setTxtPhone_number("");
       setTxtCaller_name("");
       setTxtTask_name("");
       setTxtDeadlineOver("");
       setTxtDeadlineStart("");
       return SUCCESS; 
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
    public String getGrpExcel_import() 
    {
       return grpExcel_import;
    }

    /**
     * @param grpExcel_import     */
    public void setGrpExcel_import(String grpExcel_import)
    {
       this.grpExcel_import= grpExcel_import;
    }

    /**
     * @return
     */
    public String getGrdExcel_import() 
    {
       return grdExcel_import;
    }

    /**
     * @param grdExcel_import     */
    public void setGrdExcel_import(String grdExcel_import)
    {
       this.grdExcel_import= grdExcel_import;
    }

    /**
     * @param btnExcel_file_copy *********************************************************
     * @throws IOException 
     */
    public String onBtnExcel_file_copyClick() throws IOException
    {
		getFields();				
		return SUCCESS;				
    }
   
    public String onBtnAddClick()
    {
       //CALL EVENT
       return INPUT; 
    }

    /**
     * @param btnDelete
     */
  /**
     * @param btnAgent_prorate
     */
    public String onBtnAgent_prorateClick()
    {
       //CALL EVENT
       return INPUT; 
    }

	public String getTxtPhone_number() {
		return txtPhone_number;
	}
	
	
	
	public void setTxtPhone_number(String txtPhone_number) {
		this.txtPhone_number = txtPhone_number;
	}
		/**
		 * @return the uploadExcelContentType
		 */
		public String getUploadExcelContentType()
		{
			return uploadExcelContentType;
		}
		
		/**
		 * @param uploadExcelContentType the uploadExcelContentType to set
		 */
		public void setUploadExcelContentType(String uploadExcelContentType)
		{
			this.uploadExcelContentType = uploadExcelContentType;
		}
		
		/**
		 * @return the uploadExcelFileName
		 */
		public String getUploadExcelFileName()
		{
			return uploadExcelFileName;
		}

		/**
		 * @param uploadExcelFileName the uploadExcelFileName to set
		 */
		public void setUploadExcelFileName(String uploadExcelFileName)
		{
			this.uploadExcelFileName = uploadExcelFileName;
		}
		
		/**
		 * @return the uploadExcel
		 */
		public File getUploadExcel()
		{
			//System.out.println("getting excel file");
			return uploadExcel;
		}

		/**
		 * @param uploadExcel the uploadExcel to set
		 */
		public void setUploadExcel(File uploadExcel)
		{
			this.uploadExcel = uploadExcel;
		}

		public String getUploadFile() {
			return UploadFile;
		}

		public void setUploadFile(String uploadFile) {
			UploadFile = uploadFile;
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
		public boolean isSearchClick() {
			return searchClick;
		}

		public void setSearchClick(boolean searchClick) {
			//System.out.println(" setting up click button!!");
			this.searchClick = searchClick;
		}

		public int getPage() {
			return page;
		}

		public void setPage(int page) {
			this.page = page;
		}
		public String getTest() {
			return test;
		}
		public void setTest(String test) {
			this.test = test;
		}

		public HashMap<String, String> getCmbCallResult() {
			if(cmbCallResult==null)
			{
				try
				{					
					cmbCallResult = getCombo(Consts.SLCT001_PS_211, CALL_RESULT_ID , CALL_RESULT_NAME, null);
				}
				catch(Exception e)
				{
					log("Өгөгдлийн сан руу хандах үед алдаа гарлаа",e);
					cmbCallResult = new HashMap<String, String>();
				}
			}
			return cmbCallResult;
		}
		

		
		public String getTxtCaller_name() {
			return txtCaller_name;
		}
		public void setTxtCaller_name(String txtCaller_name) {
			this.txtCaller_name = txtCaller_name;
		}
		public String getTxtTask_name() {
			return txtTask_name;
		}
		public void setTxtTask_name(String txtTask_name) {
			this.txtTask_name = txtTask_name;
		}
		public String getTxtDeadlineOver() {
			return txtDeadlineOver;
		}
		public void setTxtDeadlineOver(String txtDeadlineOver) {
			this.txtDeadlineOver = txtDeadlineOver;
		}
		public String getTxtDeadlineStart() {
			return txtDeadlineStart;
		}
		public void setTxtDeadlineStart(String txtDeadlineStart) {
			this.txtDeadlineStart = txtDeadlineStart;
		}
		public String getCmbAgent_id() {
			return cmbAgent_id;
		}
		public void setCmbAgent_id(String cmbAgent_id) {
			this.cmbAgent_id = cmbAgent_id;
		}
		public String getTest1() {
			return test1;
		}
		public void setTest1(String test1) {
			this.test1 = test1;
		}
		public String getChkExcel() {
			return chkExcel;
		}
		public void setChkExcel(String chkExcel) {
			this.chkExcel = chkExcel;
		}
			
}
