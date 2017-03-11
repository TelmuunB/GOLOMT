/*
 * Моделийн нэр             UnitList.java
 *
 * Функцын нэр          【Хэлтсийн жагсаалт харуулах】
 * Тvvх
 * Хувилбарын №  Огноо    Хариуцагч    Агуулга
 * 01.00.01   				2013/03/22   	Мөнгөнсүх    	Хянасан, засварласан
 */

package org.golomt.ajax ;

import infox.org.db.DBAccess;
import java.sql.ResultSet;
import java.util.HashMap;

import org.golomt.common.Constants;
import org.golomt.common.QueryConstants;
import org.iaac.common.BForm;

/**
 * @author Мөнгөнсүх
 *
 */
public class UnitList extends BForm
{
	/** */
	private static final long serialVersionUID = 0L;

	/** */
	private HashMap<String, String> unitList;

	/** */
	private String term ;

	/**
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 * Хэлтсийн жагсаалтыг өгөгдлийн баазаас татаж XML хэлбэрээр буцаах хэсэг
	 */
	public String execute()
	{
		unitList = new HashMap<String, String>();
		DBAccess db = getDBAccess();
		db.connect();
		try
		{
			HashMap<String, Object> hsts = new HashMap<String, Object>();
			hsts.put(Constants.FIELD_UNIT_NAME, getTerm() + "%");

			String sql = getSQLListReader().findSQLByCommand(QueryConstants.SLCT_UNIT_LIST, hsts);

			ResultSet rs = db.select(sql);

			while (rs.next())
			{
				unitList.put(rs.getString(1), rs.getString(2));
			}
		}
		catch (Exception e)
		{
			log("Хэлтсийн жагсаалтыг Ө/С аас татаж авах үед алдаа гарлаа", e);
		}
		finally
		{
			db.disconnect();
		}
		return SUCCESS;
	}

	/**
	 * @return
	 */
	public HashMap<String, String> getUnitList()
	{
		return unitList;
	}

	/**
	 * @return
	 */
	public String getTerm() {
		return term;
	}

	/**
	 * @param term
	 */
	public void setTerm(String term) {
		this.term = term;
	}


}
