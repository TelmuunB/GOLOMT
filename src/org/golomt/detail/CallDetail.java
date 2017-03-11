/*
* „M„Ђ„t„u„|„y„z„~ „~„Џ„‚             CallDetail.java
*
* „U„…„~„{„€„Ќ„~ „~„Џ„‚          ЃyДэлгэцийн зохиомжЃz
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
public class CallDetail extends BForm 
{
   /** „†„Ђ„‚„}„Ќ„~ „~„Џ„‚ */
   private static final String FORM_NAME = "CallDetail.form_name" ;
    /** Дуудлагын дэлгэрэнгүй */
    private String grpCall_detail;
    /** Дуудлагын ID */
    private String lblStaticCall_id;
    /** Дуудлагын ID */
    private String lblCall_id;
    /** Утасны дугаар */
    private String lblStaticPhone_number;
    /** Утасны дугаар */
    private String lblPhone_number;
    /** Дуудлагын төрөл */
    private String lblStaticCall_req_type_id;
    /** Дуудлагын төрөл */
    private String lblCall_req_type_id;
    /** Холбоотой нэгж */
    private String lblStaticUnit_id;
    /** Холбоотой нэгж */
    private String lblUnit_id;
    /** 2дахь дугаар */
    private String lblStaticSecond_phone;
    /** 2дахь дугаар */
    private String lblSecond_phone;
    /** Үйлчлүүлэгчийн нэр */
    private String lblStaticCaller_name;
    /** Үйлчлүүлэгчийн нэр */
    private String lblCaller_name;
    /** Төрөл */
    private String lblStaticType_id;
    /** Төрөл */
    private String lblType_id;
    /** Дуудлагын ангилал */
    private String lblStaticCall_sort_id;
    /** Дуудлагын ангилал */
    private String lblCall_sort_id;
    /** Нарийвчилсан төрөл */
    private String lblStaticClose_type_Id;
    /** Нарийвчилсан төрөл */
    private String lblClose_type_id;
    /** Дуудлагын үр дүн */
    private String lblStaticCall_result_id;
    /** Дуудлагын үр дүн */
    private String lblCall_result_id;
    /** Агентын № */
    private String lblStaticAgent_id;
    /** Агентын № */
    private String lblAgent_id;
    /** Дуудлагын нэр */
    private String lblStaticCall_name;
    /** Дуудлагын нэр */
    private String lblCall_name;
    /** Дуудлагын үнэлгээ */
    private String lblStaticCall_rate_id;
    /** Дуудлагын үнэлгээ */
    private String lblCall_rate_id;
    /** Эхлэсэн цаг */
    private String lblStaticCall_start_time;
    /** Эхлэсэн цаг */
    private String lblCall_start_time;
    /** Үргэлжилсэн хугацаа */
    private String lblStaticDuration_time;
    /** Үргэлжилсэн хугацаа */
    private String lblDuration_time;
    /** Дуудлагын оролт гаралт */
    private String lblStaticCall_type;
    /** Дуудлагын оролт гаралт */
    private String lblCall_type;
    /** Дуудлагын товч тэмдэглэл */
    private String lblStaticCall_comment;
    /** Дуудлагын товч тэмдэглэл */
    private String lblCall_comment;

    /**
     * „A„p„z„s„…„…„|„p„s„‰ „†„…„~„{„€
     */
    public CallDetail()
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
    public String getGrpCall_detail() 
    {
       return grpCall_detail;
    }

    /**
     * @param grpCall_detail     */
    public void setGrpCall_detail(String grpCall_detail)
    {
       this.grpCall_detail= grpCall_detail;
    }

    /**
     * @return
     */
    public String getLblStaticCall_id() 
    {
       return lblStaticCall_id;
    }

    /**
     * @param lblStaticCall_id     */
    public void setLblStaticCall_id(String lblStaticCall_id)
    {
       this.lblStaticCall_id= lblStaticCall_id;
    }

    /**
     * @return
     */
    public String getLblCall_id() 
    {
       return lblCall_id;
    }

    /**
     * @param lblCall_id     */
    public void setLblCall_id(String lblCall_id)
    {
       this.lblCall_id= lblCall_id;
    }

    /**
     * @return
     */
    public String getLblStaticPhone_number() 
    {
       return lblStaticPhone_number;
    }

    /**
     * @param lblStaticPhone_number     */
    public void setLblStaticPhone_number(String lblStaticPhone_number)
    {
       this.lblStaticPhone_number= lblStaticPhone_number;
    }

    /**
     * @return
     */
    public String getLblPhone_number() 
    {
       return lblPhone_number;
    }

    /**
     * @param lblPhone_number     */
    public void setLblPhone_number(String lblPhone_number)
    {
       this.lblPhone_number= lblPhone_number;
    }

    /**
     * @return
     */
    public String getLblStaticCall_req_type_id() 
    {
       return lblStaticCall_req_type_id;
    }

    /**
     * @param lblStaticCall_req_type_id     */
    public void setLblStaticCall_req_type_id(String lblStaticCall_req_type_id)
    {
       this.lblStaticCall_req_type_id= lblStaticCall_req_type_id;
    }

    /**
     * @return
     */
    public String getLblCall_req_type_id() 
    {
       return lblCall_req_type_id;
    }

    /**
     * @param lblCall_req_type_id     */
    public void setLblCall_req_type_id(String lblCall_req_type_id)
    {
       this.lblCall_req_type_id= lblCall_req_type_id;
    }

    /**
     * @return
     */
    public String getLblStaticUnit_id() 
    {
       return lblStaticUnit_id;
    }

    /**
     * @param lblStaticUnit_id     */
    public void setLblStaticUnit_id(String lblStaticUnit_id)
    {
       this.lblStaticUnit_id= lblStaticUnit_id;
    }

    /**
     * @return
     */
    public String getLblUnit_id() 
    {
       return lblUnit_id;
    }

    /**
     * @param lblUnit_id     */
    public void setLblUnit_id(String lblUnit_id)
    {
       this.lblUnit_id= lblUnit_id;
    }

    /**
     * @return
     */
    public String getLblStaticSecond_phone() 
    {
       return lblStaticSecond_phone;
    }

    /**
     * @param lblStaticSecond_phone     */
    public void setLblStaticSecond_phone(String lblStaticSecond_phone)
    {
       this.lblStaticSecond_phone= lblStaticSecond_phone;
    }

    /**
     * @return
     */
    public String getLblSecond_phone() 
    {
       return lblSecond_phone;
    }

    /**
     * @param lblSecond_phone     */
    public void setLblSecond_phone(String lblSecond_phone)
    {
       this.lblSecond_phone= lblSecond_phone;
    }

    /**
     * @return
     */
    public String getLblStaticCaller_name() 
    {
       return lblStaticCaller_name;
    }

    /**
     * @param lblStaticCaller_name     */
    public void setLblStaticCaller_name(String lblStaticCaller_name)
    {
       this.lblStaticCaller_name= lblStaticCaller_name;
    }

    /**
     * @return
     */
    public String getLblCaller_name() 
    {
       return lblCaller_name;
    }

    /**
     * @param lblCaller_name     */
    public void setLblCaller_name(String lblCaller_name)
    {
       this.lblCaller_name= lblCaller_name;
    }

    /**
     * @return
     */
    public String getLblStaticType_id() 
    {
       return lblStaticType_id;
    }

    /**
     * @param lblStaticType_id     */
    public void setLblStaticType_id(String lblStaticType_id)
    {
       this.lblStaticType_id= lblStaticType_id;
    }

    /**
     * @return
     */
    public String getLblType_id() 
    {
       return lblType_id;
    }

    /**
     * @param lblType_id     */
    public void setLblType_id(String lblType_id)
    {
       this.lblType_id= lblType_id;
    }

    /**
     * @return
     */
    public String getLblStaticCall_sort_id() 
    {
       return lblStaticCall_sort_id;
    }

    /**
     * @param lblStaticCall_sort_id     */
    public void setLblStaticCall_sort_id(String lblStaticCall_sort_id)
    {
       this.lblStaticCall_sort_id= lblStaticCall_sort_id;
    }

    /**
     * @return
     */
    public String getLblCall_sort_id() 
    {
       return lblCall_sort_id;
    }

    /**
     * @param lblCall_sort_id     */
    public void setLblCall_sort_id(String lblCall_sort_id)
    {
       this.lblCall_sort_id= lblCall_sort_id;
    }

    /**
     * @return
     */
    public String getLblStaticClose_type_Id() 
    {
       return lblStaticClose_type_Id;
    }

    /**
     * @param lblStaticClose_type_Id     */
    public void setLblStaticClose_type_Id(String lblStaticClose_type_Id)
    {
       this.lblStaticClose_type_Id= lblStaticClose_type_Id;
    }

    /**
     * @return
     */
    public String getLblClose_type_id() 
    {
       return lblClose_type_id;
    }

    /**
     * @param lblClose_type_id     */
    public void setLblClose_type_id(String lblClose_type_id)
    {
       this.lblClose_type_id= lblClose_type_id;
    }

    /**
     * @return
     */
    public String getLblStaticCall_result_id() 
    {
       return lblStaticCall_result_id;
    }

    /**
     * @param lblStaticCall_result_id     */
    public void setLblStaticCall_result_id(String lblStaticCall_result_id)
    {
       this.lblStaticCall_result_id= lblStaticCall_result_id;
    }

    /**
     * @return
     */
    public String getLblCall_result_id() 
    {
       return lblCall_result_id;
    }

    /**
     * @param lblCall_result_id     */
    public void setLblCall_result_id(String lblCall_result_id)
    {
       this.lblCall_result_id= lblCall_result_id;
    }

    /**
     * @return
     */
    public String getLblStaticAgent_id() 
    {
       return lblStaticAgent_id;
    }

    /**
     * @param lblStaticAgent_id     */
    public void setLblStaticAgent_id(String lblStaticAgent_id)
    {
       this.lblStaticAgent_id= lblStaticAgent_id;
    }

    /**
     * @return
     */
    public String getLblAgent_id() 
    {
       return lblAgent_id;
    }

    /**
     * @param lblAgent_id     */
    public void setLblAgent_id(String lblAgent_id)
    {
       this.lblAgent_id= lblAgent_id;
    }

    /**
     * @return
     */
    public String getLblStaticCall_name() 
    {
       return lblStaticCall_name;
    }

    /**
     * @param lblStaticCall_name     */
    public void setLblStaticCall_name(String lblStaticCall_name)
    {
       this.lblStaticCall_name= lblStaticCall_name;
    }

    /**
     * @return
     */
    public String getLblCall_name() 
    {
       return lblCall_name;
    }

    /**
     * @param lblCall_name     */
    public void setLblCall_name(String lblCall_name)
    {
       this.lblCall_name= lblCall_name;
    }

    /**
     * @return
     */
    public String getLblStaticCall_rate_id() 
    {
       return lblStaticCall_rate_id;
    }

    /**
     * @param lblStaticCall_rate_id     */
    public void setLblStaticCall_rate_id(String lblStaticCall_rate_id)
    {
       this.lblStaticCall_rate_id= lblStaticCall_rate_id;
    }

    /**
     * @return
     */
    public String getLblCall_rate_id() 
    {
       return lblCall_rate_id;
    }

    /**
     * @param lblCall_rate_id     */
    public void setLblCall_rate_id(String lblCall_rate_id)
    {
       this.lblCall_rate_id= lblCall_rate_id;
    }

    /**
     * @return
     */
    public String getLblStaticCall_start_time() 
    {
       return lblStaticCall_start_time;
    }

    /**
     * @param lblStaticCall_start_time     */
    public void setLblStaticCall_start_time(String lblStaticCall_start_time)
    {
       this.lblStaticCall_start_time= lblStaticCall_start_time;
    }

    /**
     * @return
     */
    public String getLblCall_start_time() 
    {
       return lblCall_start_time;
    }

    /**
     * @param lblCall_start_time     */
    public void setLblCall_start_time(String lblCall_start_time)
    {
       this.lblCall_start_time= lblCall_start_time;
    }

    /**
     * @return
     */
    public String getLblStaticDuration_time() 
    {
       return lblStaticDuration_time;
    }

    /**
     * @param lblStaticDuration_time     */
    public void setLblStaticDuration_time(String lblStaticDuration_time)
    {
       this.lblStaticDuration_time= lblStaticDuration_time;
    }

    /**
     * @return
     */
    public String getLblDuration_time() 
    {
       return lblDuration_time;
    }

    /**
     * @param lblDuration_time     */
    public void setLblDuration_time(String lblDuration_time)
    {
       this.lblDuration_time= lblDuration_time;
    }

    /**
     * @return
     */
    public String getLblStaticCall_type() 
    {
       return lblStaticCall_type;
    }

    /**
     * @param lblStaticCall_type     */
    public void setLblStaticCall_type(String lblStaticCall_type)
    {
       this.lblStaticCall_type= lblStaticCall_type;
    }

    /**
     * @return
     */
    public String getLblCall_type() 
    {
       return lblCall_type;
    }

    /**
     * @param lblCall_type     */
    public void setLblCall_type(String lblCall_type)
    {
       this.lblCall_type= lblCall_type;
    }

    /**
     * @return
     */
    public String getLblStaticCall_comment() 
    {
       return lblStaticCall_comment;
    }

    /**
     * @param lblStaticCall_comment     */
    public void setLblStaticCall_comment(String lblStaticCall_comment)
    {
       this.lblStaticCall_comment= lblStaticCall_comment;
    }

    /**
     * @return
     */
    public String getLblCall_comment() 
    {
       return lblCall_comment;
    }

    /**
     * @param lblCall_comment     */
    public void setLblCall_comment(String lblCall_comment)
    {
       this.lblCall_comment= lblCall_comment;
    }

    /**
     * @param btnListen
     */
    public String onBtnListenClick()
    {
       //CALL EVENT
       return SUCCESS; 
    }

}
