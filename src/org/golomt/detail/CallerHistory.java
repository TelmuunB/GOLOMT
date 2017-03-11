/*
* „M„Ђ„t„u„|„y„z„~ „~„Џ„‚             CallerHistory.java
*
* „U„…„~„{„€„Ќ„~ „~„Џ„‚          ЃyОператорын ажлын үр дүнгийн тайланЃz
* „Svv„‡
* „V„…„r„y„|„q„p„‚„Ќ„~ ‡‚  „O„s„~„Ђ„Ђ    „V„p„‚„y„…„€„p„s„‰    „@„s„…„…„|„s„p
* 01.00.00                 „O„s„~„Ђ„Ђ    „V„p„‚„y„…„€„p„s„‰    „Y„y„~„Џ„Џ„‚ „‡„y„z„ѓ„Џ„~/„H„p„ѓ„ѓ„p„~
*/


package org.golomt.detail;


import org.iaac.common.BForm;


/**
* @author 
*
*/
public class CallerHistory extends BForm 
{
   /** „†„Ђ„‚„}„Ќ„~ „~„Џ„‚ */
   private static final String FORM_NAME = "CallerHistory.form_name" ;
    /** Үйлчлүүлэгчийн түүх */
    private String grpCaller_history;
    /** Үйлчлүүлэгчийн түүх */
    private String grdCaller_history;

    /**
     * „A„p„z„s„…„…„|„p„s„‰ „†„…„~„{„€
     */
    public CallerHistory()
    {
    }

    /* (non-Javadoc)
     * @see com.opensymphony.xwork2.ActionSupport#execute()
     */
    public String execute()
    {
       return SUCCESS; 
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
    public String getGrpCaller_history() 
    {
       return grpCaller_history;
    }

    /**
     * @param grpCaller_history     */
    public void setGrpCaller_history(String grpCaller_history)
    {
       this.grpCaller_history= grpCaller_history;
    }

    /**
     * @return
     */
    public String getGrdCaller_history() 
    {
       return grdCaller_history;
    }

    /**
     * @param grdCaller_history     */
    public void setGrdCaller_history(String grdCaller_history)
    {
       this.grdCaller_history= grdCaller_history;
    }

}
