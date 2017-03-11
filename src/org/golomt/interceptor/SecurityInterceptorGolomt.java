/*
* Моделийн нэр        SecurityInterceptor.java
*
* Функцын нэр         【Session-ний шалгалт хийх】
* Тvvх
* Хувилбарын №  Огноо         		Хариуцагч    		Агуулга
* 01.00.00      2013/03/24    Ж.Мөнгөнсүх    	Шинээр хийсэн
*/
package org.golomt.interceptor;

import infox.org.db.DBAccess;
import infox.org.prop.RDProperties;
import infox.org.sql.SQLListReader;

import java.sql.ResultSet;
import java.util.HashMap;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.ApplicationMap;
import org.golomt.bean.UserInfo;
import org.golomt.common.Constants;
import org.golomt.common.LoginForm;
import org.golomt.common.QueryConstants;
import org.golomt.common.Status;
import org.iaac.common.Logout;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @author Мөнгөнсүх
 *
 */
public class SecurityInterceptorGolomt extends AbstractInterceptor {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public static final String LOGIN_RESULT = "login";
	/**
	 * Intercept method
	 */
	public String intercept(ActionInvocation invocation) throws Exception {

		Object user = invocation.getInvocationContext().getSession().get(Constants.SESSION_USER_INFO);
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();


        Object action = invocation.getAction();

        /*
         * Нэвтрэх формд бүх хэрэглэгч нэвтэрнэ.
         */

        if(action instanceof LoginForm)
        {
        	return invocation.invoke();
        }
        else if(action instanceof Logout)
        {
        	return invocation.invoke();
        }
        else if (action instanceof Status)
        {
        	return invocation.invoke() ;
        }
        /*
         * Бусад формд нэвтрэхэд үед session шалгана.
         */
        if( user == null )
        {
        	if (cookies != null)
        	{
        		boolean found = false ;
        		for(int i=0;i<cookies.length;i++)
        		{
        			if ("JSESSIONID".equals(cookies[i].getName()))
        			{
        				DBAccess db = getDBAccess() ;

    					SQLListReader reader = getSQLListReader();
    					HashMap<String, String> hsts = new HashMap<String, String>();
    					hsts.put(Constants.FIELD_LAST_SESSION, cookies[i].getValue());

    					String sql = reader.findSQLByCommand(QueryConstants.SLCT_AGENT_BY_LAST_SESSION, hsts);

    					db.connect() ;
    					try
    					{
	    					ResultSet rs = db.select(sql);

	    					if (rs.next()) {
	    						user = new UserInfo(rs.getString(Constants.FIELD_AGENT_ID), rs.getString(Constants.FIELD_FIRSTNAME), rs.getString(Constants.FIELD_LASTNAME),
	    								rs.getString(Constants.FIELD_EMAIL), rs.getInt(Constants.FIELD_PERMISSION));
	    						invocation.getInvocationContext().getSession().put(Constants.SESSION_USER_INFO, user);
	    						found = true ;

	        					hsts.put(Constants.FIELD_CURRENT_SESSION, (String)invocation.getInvocationContext().getSession().get("JSESSIONID"));

	        					sql = reader.findSQLByCommand(QueryConstants.UPDATE_AGENT_LAST_SESSION, hsts);

	        					db.executeUpdate(sql) ;

	        					db.commit() ;

	    					}
	    					else
	    					{
	    						return LOGIN_RESULT;
	    					}
    					}
    					catch(Throwable e)
    					{
    						return LOGIN_RESULT;
    					}
    					finally
    					{
    						db.disconnect() ;
    					}

        				break ;
        			}
        		}

        		if (!found)
        		{
        			return LOGIN_RESULT;
        		}
        	}
        	else
        	{
        		return LOGIN_RESULT;
        	}
        }

        synchronized(user)
		{
        	return invokeLocal(invocation) ;
	    }
	}

	/**
	 * @param invocation
	 * @return
	 * @throws Exception
	 */
	protected String invokeLocal(ActionInvocation invocation) throws Exception
	{
    	return invocation.invoke();
	}

	/**
	 *
	 * @return
	 */
	protected RDProperties getRDProperties() {
		ApplicationMap map = (ApplicationMap) ActionContext.getContext().getApplication();
		return (RDProperties) map.get(org.iaac.common.Constants.PROPERTIES);
	}

	/**
	 *
	 * @return
	 */
	protected String getPropText(String id) {
		return getRDProperties().getString(id) ;
	}

	/**
	 * @return
	 */
	protected SQLListReader getSQLListReader() {
		ApplicationMap map = (ApplicationMap) ActionContext.getContext().getApplication();
		return (SQLListReader) map.get(org.iaac.common.Constants.SQLLIST);
	}

	/**
	 * DBAccess
	 *
	 * @return
	 */
	protected DBAccess getDBAccess() {
		return new DBAccess(getRDProperties());
	}

}
