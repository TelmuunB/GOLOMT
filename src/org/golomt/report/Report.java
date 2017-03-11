/*
 * Моделийн нэр             Report.java
 *
 * Функцын нэр          【Тайлан форм】
 * Тvvх
 * Хувилбарын №     			Огноо        Хариуцагч         Агуулга
 * 					    01.00.00       2013/3/20   			Ж.Мөнгөнсүх    Шинээр хийсэн
 */
package org.golomt.report;

import infox.org.db.DBAccess;
import infox.org.sql.SQLListReader;
import infox.org.util.RDDate;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;


import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.golomt.common.Constants;
import org.golomt.common.XMLForm;
import org.iaac.consts.Consts;

/**
 * @author
 *
 */
public class Report extends XMLForm {
	/** serialVersionUID */
    private static final long serialVersionUID = 0L;

	/** application/xls  */
	private static final String CONTENT_TYPE_XLS = "application/xlsx";

	/** EXCEL character encoding */
	private static final String CHARACTER_ENCODING = "UTF-8";

	private static final String Complaint = "Complaint" ;

	private static final String Appeal = "Appeal";

	private static final String Reference = "Reference";

	private static final String Info = "Info";

	private static final String OthersReport = "OthersReport" ;

	private static final String SolvedComplaintReport = "SolvedComplaintReport" ;

	private static final String UnsolvedComplaintReport = "UnsolvedComplaintReport" ;

	private static final String Calltype = "Calltype" ;
	
	private static final String Callsum = "Callsum" ;

	private static final String CallRate = "CallRate" ;

	private static final String CallResult = "CallResult" ;

	private static final String TransferReport = "TransferReport" ;

	private static final String HandledCalls = "HandledCalls" ;

	private static final String Abandonedcalls = "Abandonedcalls" ;

	private static final String INBOUND_REPORT = "INBOUND_REPORT" ;

	private static final String OUTBOUND_REPORT = "OUTBOUND_REPORT" ;

	private static final String BY_AGENT = "BY_AGENT" ;
	
	private static final String BYDAYSUM = "BYDAYSUM" ;
	
	private static final String BYDAYNAMES = "BYDAYNAMES" ;

	/** Утасны дугаар */
	private String phone_number;

	private String dateFrom ;

	private String dateTo ;

	private String agentId ;

	private XSSFCellStyle groupstyle ;

	private XSSFFont font ;
	
	private String permission;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() {
		return SUCCESS;
	}

	/**
	 * @return
	 */
	public String onDownload()
	{
		try
		{
			File fname = new File(ServletActionContext.getServletContext().getRealPath(File.separator) + "/WEB-INF/xls/reportAll.xlsx");
			InputStream fileInputStream = new FileInputStream(fname);
			XSSFWorkbook wb = new XSSFWorkbook(fileInputStream) ;
			XSSFSheet complaint = wb.getSheet("Complaint") ;
			XSSFSheet appeal = wb.getSheet("Appeal") ;
			XSSFSheet reference = wb.getSheet("Reference") ;
			XSSFSheet info = wb.getSheet("Info") ;
			XSSFSheet others = wb.getSheet("Others") ;
			XSSFSheet solvedcomplaint = wb.getSheet("SolvedComplaint") ;
			XSSFSheet unsolvedcomplaint = wb.getSheet("NotSolvedComplaint") ;
			XSSFSheet calltype = wb.getSheet("Calltype") ;
			XSSFSheet callrate = wb.getSheet("CallRate") ;
			XSSFSheet callresult = wb.getSheet("CallResult") ;
			XSSFSheet transfer = wb.getSheet("transfer") ;
			XSSFSheet by_hour = wb.getSheet("By hour") ;
			XSSFSheet inbound = wb.getSheet("Inbound") ;
			XSSFSheet outbound = wb.getSheet("Outbound") ;
			XSSFSheet byagent = wb.getSheet("ByAgent") ;
			XSSFSheet byday = wb.getSheet("By day") ;
			

			font = wb.createFont();
			font.setFontName("Times New Roman");

			groupstyle = wb.createCellStyle();

			groupstyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER) ;
			groupstyle.setAlignment(XSSFCellStyle.ALIGN_CENTER) ;
			groupstyle.setWrapText(true) ;
			groupstyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
			groupstyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
			groupstyle.setFont(font) ;
			DBAccess db=null;
			try {
				db = getDBAccess();
				db.connect();
				report(Complaint, db, wb, complaint, 5, 1, 11, true, true) ;
				report(Appeal, db, wb, appeal, 5, 1, 11, true, true) ;
				report(Reference, db, wb, reference, 5, 1, 9, true, true) ;
				report(Info, db, wb, info, 5, 1, 9, true, true) ;
				report(OthersReport, db, wb, others, 5, 1, 8, true, true) ;
				report(SolvedComplaintReport, db, wb, solvedcomplaint, 5, 1, 14, true, true) ;
				report(UnsolvedComplaintReport, db, wb, unsolvedcomplaint, 5, 1, 10, true, true) ;
				report(Calltype, db, wb, calltype, 5, 3, 1, false, false) ;
				report(Callsum, db, wb, calltype, 12, 3, 1, false, false );
				report(CallRate, db, wb, callrate, 5, 1, 7, true, true) ;
				report(CallResult, db, wb, callresult, 5, 1, 7, true, true) ;
				report(TransferReport, db, wb, transfer, 5, 1, 9, true, true) ;
				report(HandledCalls, db, wb, by_hour, 6, 3, 1, false, false) ;
				report(Abandonedcalls, db, wb, by_hour, 6, 4, 1, false, false) ;
				report(INBOUND_REPORT, db, wb, inbound, 6, 1, 10, false, true) ;
				report(OUTBOUND_REPORT, db, wb, outbound, 6, 1, 7, false, true) ;
				report(BY_AGENT, db, wb, byagent, 7, 1, 21, true, true) ;
				report(BYDAYSUM, db, wb, byday, 7, 1, 15, true, false) ; 
				report(BYDAYNAMES, db, wb, byday, 11, 1, 10, true, true) ; 


				String content = CONTENT_ATTACHMENT + "report" + RDDate.getSystemDateAsString(RDDate.yyyyMMddHH24mmSS) + ".xlsx";
				HttpServletResponse response = getServletResponse();
				response.setCharacterEncoding(CHARACTER_ENCODING);
				response.setContentType(CONTENT_TYPE_XLS);
				response.setBufferSize(5120);
				response.setHeader(CONTENT_DISPOSITION, content);
				evaluatePOI(wb) ;
				wb.write(response.getOutputStream()) ;
				log("FINISHED");
				return DOWNLOAD ;
			}
			catch (Exception e) {
				log("Баазтай холбогдох алдаа", e);
				return ERROR ;
			} finally {
				db.disconnect();
			}
		}
		catch(Throwable fnf)
		{
			log("FILE DOWNLOAD", fnf) ;
		}

		return ERROR ;
	}

	/**
	 * @param sheet
	 */
	private void report(String reportSQL, DBAccess db, XSSFWorkbook wb, XSSFSheet sheet, int row, int col, int maxcol, boolean hasNo, boolean format) throws SQLException,Exception
	{
		int frow = row ;
		int fcol = col ;

		HashMap<String, Object> hsts = new HashMap<String, Object>();

		hsts.put(Constants.FIELD_DATE_FROM, getDateFrom()) ;
		hsts.put(Constants.FIELD_DATE_TO, getDateTo()) ;
		hsts.put(Constants.FIELD_AGENT_ID, getAgentId()) ;
		hsts.put("SYSTEMRATE", escStrQNull(getText("system.rateselect")));	
		
		SQLListReader reader = getSQLListReader();

		String sql = reader.findSQLByCommand(reportSQL, hsts, false);

		log(sql) ;
		ResultSet rs = db.select(sql);
		int len = rs.getMetaData().getColumnCount() ;

		if (hasNo)
		{
			col ++ ;
		}

		int rindex = 1 ;
		String last = null ;

		while(rs.next())
		{
			int addrow = 0 ;

			if (sheet.getRow(row) == null)
			{
				sheet.createRow(row) ;
			}

			int st = 1 ;

			if (rs.getMetaData().getColumnName(1).startsWith("G"))
			{
				if (!isEqual(last, rs.getString(1)))
				{
					if (sheet.getRow(row).getCell(col) == null)
					{
						sheet.getRow(row).createCell(col) ;
					}

					for(int k=col;k<fcol+maxcol-1;k++)
					{
						sheet.getRow(row).createCell(k) ;
						sheet.getRow(row).getCell(k).setCellStyle(groupstyle) ;
					}

					sheet.addMergedRegion(new CellRangeAddress(row, row, col, fcol + maxcol - 1)) ;
					sheet.getRow(row).getCell(col).setCellValue(rs.getString(1)) ;

					sheet.getRow(row).getCell(col).setCellStyle(groupstyle);

					last = rs.getString(1) ;
					row ++ ;
					if (sheet.getRow(row) == null)
					{
						sheet.createRow(row) ;
					}
					rindex = 1 ;
				}
				else
				{
					st ++ ;
				}
			}

			if (hasNo)
			{
				sheet.getRow(row).createCell(col - 1);
				sheet.getRow(row).getCell(col - 1).setCellValue(rindex) ;
			}
			for(int i=st;i<=len;i++)
			{
				if (rs.getString(i) != null)
				{
					String name = rs.getMetaData().getColumnName(i).substring(1) ;
					int cellindex = 0 ;
					int rowindex = 0 ;

					if (name.indexOf("_") > 0)
					{
						String[] names = name.split("_") ;
						cellindex = Integer.parseInt(names[0]) ;
						rowindex = Integer.parseInt(names[1]) - 1 ;

						if (sheet.getRow(row + rowindex) == null)
						{
							sheet.createRow(row + rowindex) ;
						}

						if (rowindex > addrow)
						{
							addrow = rowindex ;
						}
					}
					else
					{
						cellindex = Integer.parseInt(name) ;
					}

					if (sheet.getRow(row + rowindex).getCell(col + cellindex - 1) == null)
					{
						sheet.getRow(row + rowindex).createCell(col + cellindex - 1);
					}

					if ((rs.getMetaData().getColumnType(i) == Types.INTEGER || rs.getMetaData().getColumnType(i) == Types.BIGINT || rs.getMetaData().getColumnType(i) == Types.DECIMAL || rs.getMetaData().getColumnType(i) == Types.NUMERIC) && rs.getString(i) != null && rs.getString(i).length() < 10)
					{
						sheet.getRow(row + rowindex).getCell(col + cellindex - 1).setCellValue(rs.getInt(i)) ;
					}
					else
					{
						sheet.getRow(row + rowindex).getCell(col + cellindex - 1).setCellValue(rs.getString(i)) ;
					}
				}
			}
			row = row + addrow + 1 ;
			rindex ++ ;
		}

		if (format)
		{
			createCellBorders(wb, sheet, frow, fcol, sheet.getLastRowNum(), maxcol ) ;
		}


	}

	protected void evaluatePOI(XSSFWorkbook wb)
	  {
	    FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
	    for (int sheetNum = 0; sheetNum < wb.getNumberOfSheets(); sheetNum++) {
	      Sheet sheet = wb.getSheetAt(sheetNum);
	      Iterator localIterator2;
	      for (Iterator localIterator1 = sheet.iterator(); localIterator1.hasNext(); 
	        localIterator2.hasNext())
	      {
	    	  Row r = (Row)localIterator1.next();
	          localIterator2 = r.iterator(); 
	          continue;/* Cell c = (Cell)localIterator2.next();
	          evaluator.evaluateFormulaCell(c);*/
	      }
	    }
	  }
	
	/**
	 * @param wb
	 * @param sheet
	 * @return
	 */
	private void createCellBorders(XSSFWorkbook wb, XSSFSheet sheet, int frow, int scol, int lrow, int lcol)
	{
		XSSFCellStyle style = wb.createCellStyle();

		style.setBorderBottom(XSSFCellStyle.BORDER_DOTTED);
		style.setBorderLeft(XSSFCellStyle.BORDER_DOTTED);
		style.setBorderRight(XSSFCellStyle.BORDER_DOTTED);
		style.setBorderTop(XSSFCellStyle.BORDER_DOTTED);
		style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER) ;
		style.setWrapText(true) ;
		style.setFont(font) ;

		XSSFCellStyle styleLeft = wb.createCellStyle();

		styleLeft.setBorderBottom(XSSFCellStyle.BORDER_DOTTED);
		styleLeft.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		styleLeft.setBorderRight(XSSFCellStyle.BORDER_DOTTED);
		styleLeft.setBorderTop(XSSFCellStyle.BORDER_DOTTED);
		styleLeft.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER) ;
		styleLeft.setWrapText(true) ;
		styleLeft.setFont(font) ;

		XSSFCellStyle styleRight = wb.createCellStyle();

		styleRight.setBorderBottom(XSSFCellStyle.BORDER_DOTTED);
		styleRight.setBorderLeft(XSSFCellStyle.BORDER_DOTTED);
		styleRight.setBorderRight(XSSFCellStyle.BORDER_THIN);
		styleRight.setBorderTop(XSSFCellStyle.BORDER_DOTTED);
		styleRight.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER) ;
		styleRight.setWrapText(true) ;
		styleRight.setFont(font) ;

		XSSFCellStyle styleLeftBottom = wb.createCellStyle();

		styleLeftBottom.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		styleLeftBottom.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		styleLeftBottom.setBorderRight(XSSFCellStyle.BORDER_DOTTED);
		styleLeftBottom.setBorderTop(XSSFCellStyle.BORDER_DOTTED);
		styleLeftBottom.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER) ;
		styleLeftBottom.setWrapText(true) ;
		styleLeftBottom.setFont(font) ;

		XSSFCellStyle styleRightBottom = wb.createCellStyle();

		styleRightBottom.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		styleRightBottom.setBorderLeft(XSSFCellStyle.BORDER_DOTTED);
		styleRightBottom.setBorderRight(XSSFCellStyle.BORDER_THIN);
		styleRightBottom.setBorderTop(XSSFCellStyle.BORDER_DOTTED);
		styleRightBottom.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER) ;
		styleRightBottom.setWrapText(true) ;
		styleRightBottom.setFont(font) ;

		XSSFCellStyle styleBottom = wb.createCellStyle();

		styleBottom.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		styleBottom.setBorderLeft(XSSFCellStyle.BORDER_DOTTED);
		styleBottom.setBorderRight(XSSFCellStyle.BORDER_DOTTED);
		styleBottom.setBorderTop(XSSFCellStyle.BORDER_DOTTED);
		styleBottom.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER) ;
		styleBottom.setWrapText(true) ;
		styleBottom.setFont(font) ;

		for (int i=frow;i<=lrow;i++)
		{
			if (sheet.getRow(i) == null)
			{
				sheet.createRow(i) ;
			}
			for(int j=scol;j<=lcol;j++)
			{

				if (sheet.getRow(i).getCell(j) == null)
				{
					sheet.getRow(i).createCell(j) ;
				}

				XSSFCellStyle cur = sheet.getRow(i).getCell(j).getCellStyle() ;

				if (!groupstyle.equals(cur))
				{
					if (j == scol)
					{
						if (i == lrow)
						{
							sheet.getRow(i).getCell(j).setCellStyle(styleLeftBottom) ;
						}
						else
						{
							sheet.getRow(i).getCell(j).setCellStyle(styleLeft) ;
						}
					}
					else if (j == lcol)
					{
						if (i == lrow)
						{
							sheet.getRow(i).getCell(j).setCellStyle(styleRightBottom) ;
						}
						else
						{
							sheet.getRow(i).getCell(j).setCellStyle(styleRight) ;
						}
					}
					else if (i == lrow)
					{
						sheet.getRow(i).getCell(j).setCellStyle(styleBottom) ;
					}
					else
					{
						sheet.getRow(i).getCell(j).setCellStyle(style) ;
					}
				}
				else
				{
					//break ;
				}			}
		}
	}

	private boolean isEqual(String val1, String val2)
	{
		return val1 == null && val2 == null || val1 != null && val1.equals(val2) ;
	}

	/*	 * (non-Javadoc)
	 *
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	public void validate() {
	}

	/**
	 * @return
	 */
	public HashMap<String, String> getCmbAgentList() {
		try {
			return getCombo(Consts.SLCT_AGENT_LIST, Constants.FIELD_AGENT_ID, Constants.FIELD_AGENT_NAME, null);
		} catch (Exception e) {
			log("Өгөгдлийн сан руу хандах үед алдаа гарлаа", e);
			return new HashMap<String, String>();
		}
	}

	/**
	 * @return phone_number
	 */
	public String getPhone_number() {
		return phone_number;
	}

	/**
	 * @param phone_number セットする phone_number
	 */
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
    public String getDateFrom() {
    	return dateFrom == null ? "" : dateFrom ;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo == null ? "" : dateTo ;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public String getAgentId() {
		return agentId == null ? super.getAgentID() : agentId ;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	/**
	 * @return
	 */
	public String getAgentPermission()
	{	permission = Integer.toString(getPermission());
		return permission;
	}

}
