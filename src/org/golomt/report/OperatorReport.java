/*
* „M„Ђ„t„u„|„y„z„~ „~„Џ„‚             OperatorReport.java
*
* „U„…„~„{„€„Ќ„~ „~„Џ„‚          ЃyОператорын ажлын үр дүнгийн тайланЃz
* „Svv„‡
* „V„…„r„y„|„q„p„‚„Ќ„~ ‡‚  „O„s„~„Ђ„Ђ    „V„p„‚„y„…„€„p„s„‰    „@„s„…„…„|„s„p
* 01.00.00                 „O„s„~„Ђ„Ђ    „V„p„‚„y„…„€„p„s„‰    „Y„y„~„Џ„Џ„‚ „‡„y„z„ѓ„Џ„~/„H„p„ѓ„ѓ„p„~
*/


package org.golomt.report;


import org.iaac.common.BForm;


/**
* @author 
*
*/
public class OperatorReport extends BForm 
{
   /** „†„Ђ„‚„}„Ќ„~ „~„Џ„‚ */
   private static final String FORM_NAME = "OperatorReport.form_name" ;
    /** Ачаалал */
    private String grpReport_agent;
    /** Ачаалал */
    private String grdReport_agent;
    /** Утасны жагсаалт */
    private String grpPhone_list;
    /** Утасны жагсаалт */
    private String grdPhone_list;
    /** Утасны дугаар */
    private String txtPhone_number;

    /**
     * „A„p„z„s„…„…„|„p„s„‰ „†„…„~„{„€
     */
    public OperatorReport()
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
    public String getGrpReport_agent() 
    {
       return grpReport_agent;
    }

    /**
     * @param grpReport_agent     */
    public void setGrpReport_agent(String grpReport_agent)
    {
       this.grpReport_agent= grpReport_agent;
    }

    /**
     * @return
     */
    public String getGrdReport_agent() 
    {
       return grdReport_agent;
    }

    /**
     * @param grdReport_agent     */
    public void setGrdReport_agent(String grdReport_agent)
    {
       this.grdReport_agent= grdReport_agent;
    }

    /**
     * @return
     */
    public String getGrpPhone_list() 
    {
       return grpPhone_list;
    }

    /**
     * @param grpPhone_list     */
    public void setGrpPhone_list(String grpPhone_list)
    {
       this.grpPhone_list= grpPhone_list;
    }

    /**
     * @return
     */
    public String getGrdPhone_list() 
    {
       return grdPhone_list;
    }

    /**
     * @param grdPhone_list     */
    public void setGrdPhone_list(String grdPhone_list)
    {
       this.grdPhone_list= grdPhone_list;
    }

    /**
     * @return
     */
    public String getTxtPhone_number() 
    {
       return txtPhone_number;
    }

    /**
     * @param txtPhone_number     */
    public void setTxtPhone_number(String txtPhone_number)
    {
       this.txtPhone_number= txtPhone_number;
    }

    /**
     * @param btnSearch
     */
    public String onBtnSearchClick()
    {
       //CALL EVENT
       return SUCCESS; 
    }

}
