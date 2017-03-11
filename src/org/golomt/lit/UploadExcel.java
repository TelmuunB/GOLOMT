/*
* „M„Ђ„t„u„|„y„z„~ „~„Џ„‚             UploadExcel.java
*
* „U„…„~„{„€„Ќ„~ „~„Џ„‚          ЃyДэлгэцийн зохиомжЃz
* „Svv„‡
* „V„…„r„y„|„q„p„‚„Ќ„~ ‡‚  „O„s„~„Ђ„Ђ    „V„p„‚„y„…„€„p„s„‰    „@„s„…„…„|„s„p
* 01.00.00                 „O„s„~„Ђ„Ђ    „V„p„‚„y„…„€„p„s„‰    „Y„y„~„Џ„Џ„‚ „‡„y„z„ѓ„Џ„~/„H„p„ѓ„ѓ„p„~
*/


package org.golomt.lit;


import org.iaac.common.BForm;


/**
* @author 
*
*/
public class UploadExcel extends BForm 
{
   /** „†„Ђ„‚„}„Ќ„~ „~„Џ„‚ */
   private static final String FORM_NAME = "UploadExcel.form_name" ;
   /**  */
   private static final String INFO_MESSAGE="UploadExcel.Та бичлэгээ сонгоно уу";
   /**  */
   private static final String INFO_MESSAGE1="UploadExcel.Амжилттай хадгалагдлаа";
    /** Мессеж */
    private String lblMessage;
    /** Сонгох файлын замыг оруулах талбар */
    private String txtSelect_file;
    /** Хуулсан файл */
    private String grdUpload;

    /**
     * „A„p„z„s„…„…„|„p„s„‰ „†„…„~„{„€
     */
    public UploadExcel()
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
    public String getLblMessage() 
    {
       return lblMessage;
    }

    /**
     * @param lblMessage     */
    public void setLblMessage(String lblMessage)
    {
       this.lblMessage= lblMessage;
    }

    /**
     * @return
     */
    public String getTxtSelect_file() 
    {
       return txtSelect_file;
    }

    /**
     * @param txtSelect_file     */
    public void setTxtSelect_file(String txtSelect_file)
    {
       this.txtSelect_file= txtSelect_file;
    }

    /**
     * @param btnBrowse
     */
    public String onBtnBrowseClick()
    {
       //CALL EVENT
       return SUCCESS; 
    }

    /**
     * @param btnPage_load
     */
    public String onBtnPage_loadClick()
    {
       //CALL EVENT
       return SUCCESS; 
    }

    /**
     * @return
     */
    public String getGrdUpload() 
    {
       return grdUpload;
    }

    /**
     * @param grdUpload     */
    public void setGrdUpload(String grdUpload)
    {
       this.grdUpload= grdUpload;
    }

    /**
     * @param btnReturn
     */
    public String onBtnReturnClick()
    {
       //CALL EVENT
       return SUCCESS; 
    }

}
