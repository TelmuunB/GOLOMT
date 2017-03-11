/*
 * Моделийн нэр             InboundReference.java
 *
 * Функцын нэр          【Лавлагаа форм】
 * Тvvх
 * Хувилбарын №     			Огноо        Хариуцагч         Агуулга
 * 					    01.00.00       2013/3/20   			Ж.Мөнгөнсүх    Шинээр хийсэн
 */
package org.golomt.req;

/**
 * @author
 *
 */
public class InboundTransfer extends InboundTab
{
    /** serialVersionUID */
    private static final long serialVersionUID = 0L;

    private String finishCall ;

	/**
	 * @return
	 */
	protected String getReqType()
	{
		return "5" ;
	}

	/**
	 * @return finishCall
	 */
	public String getFinishCall() {
		return finishCall;
	}

	/**
	 * @param finishCall セットする finishCall
	 */
	public void setFinishCall(String finishCall) {
		this.finishCall = finishCall;
	}

}
