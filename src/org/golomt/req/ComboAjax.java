/*
 * Моделийн нэр             ComboAjax.java
 *
 * Функцын нэр          【Лавлагаа форм】
 * Тvvх
 * Хувилбарын №     			Огноо        Хариуцагч         Агуулга
 * 					    01.00.00       2013/3/20   			Ж.Мөнгөнсүх    Шинээр хийсэн
 */
package org.golomt.req;

import java.util.HashMap;
import org.golomt.common.Constants;
import org.golomt.common.QueryConstants;
import org.golomt.common.XMLForm;

public class ComboAjax extends XMLForm {
	/** */
	private static final long serialVersionUID = 1L;

	/** */
	private String comboName;

	/** */
	private String callReq;

	/*
	 * (非 Javadoc)
	 *
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() {
		return SUCCESS;
	}

	// ////////////////////////////////////////////////////////////////////////////////
	private String getComboName() {
		return comboName;
	}

	public void setComboName(String comboName) {
		this.comboName = comboName;
	}

	/**
	 * @return
	 */
	private String getCallReq() {
		return callReq;
	}

	/**
	 * @param callReq
	 */
	public void setCallReq(String callReq) {
		this.callReq = callReq;
	}

	/**
	 * @return
	 */
	public HashMap<String, String> getFinalReturn() {
		HashMap<String, Object> hsts = new HashMap<String, Object>();
		hsts.put(Constants.FIELD_AGENT_ID, getAgentID());
		hsts.put(Constants.FIELD_CALL_REQ_TYPE_ID, getCallReq());

		String pid = getCallReq();

		if (pid != null) {
			hsts.put(Constants.FIELD_PARENT_SERVICE_ID, getComboName());

			try {
				return getCombo(QueryConstants.SLCT_MST_SERVICE_DETAIL, Constants.FIELD_SERVICE_ID, Constants.FIELD_SERVICE_NAME, hsts);
			} catch (Exception e) {
				log("Өгөгдлийн сан руу хандах үед алдаа гарлаа", e);
				return new HashMap<String, String>();
			}
		} else {
			return new HashMap<String, String>();
		}

	}
}
