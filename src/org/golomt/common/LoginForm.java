/*
 * Моделийн нэр             LoginForm.java
 *
 * Функцын нэр          【Системд нэвтрэх форм】
 * Тvvх
 * Хувилбарын №     			Огноо        Хариуцагч         Агуулга
 * 					    01.00.00       2013/3/20   			Ж.Мөнгөнсүх    Шинээр хийсэн
 */
package org.golomt.common;

import infox.org.db.DBAccess;
import infox.org.sql.SQLListReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.golomt.bean.UserInfo;
import org.iaac.common.BForm;
import com.opensymphony.xwork2.ActionContext;

public class LoginForm extends BForm {
	/** serialVersionUID */
	private static final long serialVersionUID = 0L;

	/** Хэрэглэгчийн мэдээлэл татах квери */
	private static final String SELECT_AGENT = "SELECT_AGENT";

	/** Нэвтрэх нэр */
	private String user_id;
	/** Нууц үг */
	private String password;
	
	private String login_Message;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() {
		if (isLoggedIn()) {
			return SUCCESS;
		} else {
			validate();
			if (isLoggedIn()) {
				return SUCCESS;
			}
		}

		return INPUT;
	}

	/**
	 * @return
	 */
	public boolean isLoggedIn() {
		Map<String, Object> session = (Map<String, Object>) ActionContext.getContext().getSession();
		if (session.get(Constants.SESSION_USER_INFO) != null) {
			return true;
		}
		return false;
	}

	/**
	 *
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	public void validate() {
		Map<String, Object> session = (Map<String, Object>) ActionContext.getContext().getSession();

		DBAccess db = getDBAccess();
		db.connect();
		try {
			SQLListReader reader = getSQLListReader();
			HashMap<String, String> hsts = new HashMap<String, String>();
			hsts.put(Constants.FIELD_AGENT_ID, getUser_id());
			hsts.put(Constants.FIELD_PASSWORD, getPassword());
			String sql = reader.findSQLByCommand(SELECT_AGENT, hsts);

			ResultSet rs = db.select(sql);

			if (rs.next()) {
				UserInfo info = new UserInfo(rs.getString(Constants.FIELD_AGENT_ID), rs.getString(Constants.FIELD_FIRSTNAME), rs.getString(Constants.FIELD_LASTNAME),
						rs.getString(Constants.FIELD_EMAIL), rs.getInt(Constants.FIELD_PERMISSION));
				session.put(Constants.SESSION_USER_INFO, info);

				hsts.put(Constants.FIELD_AGENT_ID, getUser_id());
				hsts.put(Constants.FIELD_LOG_STATUS, Constants.LOG_LOGIN);
				hsts.put(Constants.FIELD_LAST_SESSION, (String)session.get("JSESSIONID"));

				sql = reader.findSQLByCommand(QueryConstants.INSERT_LOG, hsts);

				db.executeInsert(sql) ;

				db.commit() ;

			} else {
				log(getText(Constants.LOGIN_ERR001));
				addActionError(getText(Constants.LOGIN_ERR001));
				if(!isEmpty(getUser_id())&&!isEmpty(getPassword())){
					if(!"".equals(getUser_id())){
						setLogin_Message("1");
					}
				}      
				
			}
		} catch (SQLException e) {
			log(getText(Constants.SYS_ERR001));
			addActionError(getText(Constants.SYS_ERR001));
			if(!isEmpty(getUser_id())&&!isEmpty(getPassword())){
				if(!"".equals(getUser_id())){
					setLogin_Message("2");
				}
			}
			log("databaseerror",e);
		} finally {
			db.disconnect();
		}

	}

	/**
	 * @return
	 */
	public String onLoginClick() {
		Map<String, Object> session = (Map<String, Object>) ActionContext.getContext().getSession();

		if (session.get(Constants.SESSION_USER_INFO) != null) {
			return SUCCESS;
		} else {
			return INPUT;
		}
	}

	/**
	 * @return
	 */
	public String getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin_Message() {
		return login_Message;
	}

	public void setLogin_Message(String login_Message) {
		this.login_Message = login_Message;
	}
}
