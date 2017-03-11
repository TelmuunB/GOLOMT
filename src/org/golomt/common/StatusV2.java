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

import com.opensymphony.xwork2.ActionContext;

public class StatusV2 extends XMLForm {
	/** serialVersionUID */
	private static final long serialVersionUID = 0L;

	private String status;

	/** Хэрэглэгчийн мэдээлэл татах квери */
	private static final String SELECT_AGENT = "SELECT_AGENT";

	/** Нэвтрэх нэр */
	private String user_id;
	/** Нууц үг */
	private String password;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() {
		if(!isEmpty(user_id)){
			user_id = user_id.replaceAll("\\D+","");
		}
		log(user_id);
		if (!isEmpty(getStatus())) {
			Map<String, Object> session = (Map<String, Object>) ActionContext.getContext().getSession();

			DBAccess db = getDBAccess();

			db.connect();
			try {

				if ((getStatus().equals(Constants.LOG_IDLE) || getStatus().equals(Constants.LOG_LOGOUT))) {
					if (isLoggedIn())
					{
						SQLListReader reader = getSQLListReader();
						HashMap<String, String> hsts = new HashMap<String, String>();
						hsts.put(Constants.FIELD_AGENT_ID, getAgentID());
						hsts.put(Constants.FIELD_LOG_STATUS, getStatus());

						String sql = reader.findSQLByCommand(QueryConstants.INSERT_LOG, hsts);

						db.executeInsert(sql);
						db.commit();
					}
					return SUCCESS;
				} else if (getStatus().equals(Constants.LOG_ACTIVE) || getStatus().equals(Constants.LOG_LOGIN)) {
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

						reader = getSQLListReader();
						hsts = new HashMap<String, String>();
						hsts.put(Constants.FIELD_AGENT_ID, getAgentID());
						hsts.put(Constants.FIELD_LOG_STATUS, getStatus());
						hsts.put(Constants.FIELD_LAST_SESSION, (String)session.get("JSESSIONID"));

						sql = reader.findSQLByCommand(QueryConstants.INSERT_LOG, hsts);

						db.executeInsert(sql);
						db.commit();

					} else {
						log(getText(Constants.LOGIN_ERR001));
						return "login_error";
					}
					return SUCCESS;
				} else {
					return ERROR;
				}
			} catch (SQLException e) {
				log(getText(Constants.SYS_ERR001));
				return ERROR;

			} finally {
				db.disconnect();
			}
		}
		return ERROR;
	}

	/**
	 * @return
	 */
	
	private boolean isLoggedIn() {
		Map<String, Object> session = (Map<String, Object>) ActionContext.getContext().getSession();
		if (session.get(Constants.SESSION_USER_INFO) != null) {
			return true;
		}
		return false;
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

	/**
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
