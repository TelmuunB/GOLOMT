/*
 * Моделийн нэр             LoginForm.java
 *
 * Функцын нэр          【Системд нэвтрэх форм】
 * Тvvх
 * Хувилбарын №     			Огноо        Хариуцагч         Агуулга
 * 					    01.00.00       2013/3/20   			Ж.Мөнгөнсүх    Шинээр хийсэн
 */
package org.golomt.common;


public class CallingHandler extends XMLForm {
	/** serialVersionUID */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String Calling;
	
	private String Called;
	

	
	/*
	 * (non-Javadoc)
	 *
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() {
		
		if(!isEmpty(Calling)){
			if(getAgentID().equals(Calling)&&Called.length()==4){
				return SUCCESS;
			}
			if(getAgentID().equals(Called)&&Calling.length()==4){
				return SUCCESS;
			}
			if(getAgentID().equals(Calling)){
				raiseFlag(Integer.parseInt(getAgentID()), 2);
			}else{
				raiseFlag(Integer.parseInt(getAgentID()), 1);
			}
		}
		return SUCCESS;
	}

	public String getCalled() {
		return Called;
	}

	public void setCalled(String called) {
		Called = called;
	}

	public String getCalling() {
		return Calling;
	}

	public void setCalling(String calling) {
		Calling = calling;
	}

	/**
	 * @return
	 */
	

	/**
	 * @return
	 */
	

}
