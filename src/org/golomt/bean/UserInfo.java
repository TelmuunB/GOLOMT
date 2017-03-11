/*
 * Моделийн нэр             UserInfo.java
 *
 * Функцын нэр          【Хэрэглэгчийн мэдээлэл】
 * Тvvх
 * Хувилбарын №     			Огноо        Хариуцагч         Агуулга
 * 					    01.00.00       2013/3/20   			Ж.Мөнгөнсүх    Шинээр хийсэн
 */
package org.golomt.bean;

public class UserInfo {

	/** Агентийн код */
	private String agentID ;

	/** Агентийн овог */
	private String firstName ;

	/** Агентийн нэр */
	private String lastName ;

	/** Агентийн имейл */
	private String email ;

	/** Агентийн эрх */
	private int permission ;

	/**
	 * Хэрэглэгчийн мэдээлэл үүсгэх<br>
	 * @param agentID
	 * @param firstName
	 * @param lastName
	 * @param email
	 */
	public UserInfo(String agentID, String firstName, String lastName, String email, int permission){
		this.agentID = agentID ;
		this.firstName = firstName ;
		this.lastName = lastName ;
		this.email = email ;
		this.permission = permission ;
	}

	/**
	 * @return agentID
	 */
	public String getAgentID() {
		return agentID;
	}

	/**
	 * @param agentID ийг тохируулах agentID
	 */
	public void setAgentID(String agentID) {
		this.agentID = agentID;
	}

	/**
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName ийг тохируулах firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName ийг тохируулах lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email ийг тохируулах email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return permission
	 */
	public int getPermission() {
		return permission;
	}

	/**
	 * @param permission セットする permission
	 */
	public void setPermission(int permission) {
		this.permission = permission;
	}
}
