<!--
* „M„Ђ„t„u„|„y„z„~ „~„Џ„‚             UploadExcel.jsp
*
* „U„…„~„{„€„Ќ„~ „~„Џ„‚          ЃyДэлгэцийн зохиомжЃz
* „Svv„‡
* „V„…„r„y„|„q„p„‚„Ќ„~ ‡‚  „O„s„~„Ђ„Ђ    „V„p„‚„y„…„€„p„s„‰    „@„s„…„…„|„s„p
* 01.00.00                 „O„s„~„Ђ„Ђ    „V„p„‚„y„…„€„p„s„‰    „Y„y„~„Џ„Џ„‚ „‡„y„z„ѓ„Џ„~/„H„p„ѓ„ѓ„p„~
*-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
   <jsp:include page="/WEB-INF/jsp/common/header.jsp">
       <jsp:param name="form_name" value="UploadExcel.form_name"/>
   </jsp:include>
   <body>
   <s:component template="window.ftl" cssStyle="width:500;height:300px;">
       <s:param name="animate" value="true"/>
       <s:param name="center" value="true"/>
       <s:param name="title"><s:property value="getText('UploadExcel.form_name')"/></s:param>
       <s:param name="body">
           <s:form action="UploadExcel" cssClass="form">
             <table width=100% border="1" cellpadding="4">
                  <tr>
                      <td rowspan="2" width=20%>
                      <img src="C:\Users\ini\Desktop\ms-excel.jpg" width="100" height="70">
                      </td>
                      <td style="text-align: center;" width=80%>
                          <s:label name="lblMessage" value="%{getText('UploadExcel.lblMessage')}"/>
                      </td>
                  </tr>
                  <tr>
                      <td>                      
                          <!-- 
                          <s:property value="getText('UploadExcel.txtSelect_file')"/>
                          -->                          
                          <s:textfield name="txtSelect_file" cssClass="idfield" cssStyle="width:230;height:20px;"/>
                          <s:submit name="btnBrowse" key="UploadExcel.btnBrowse" cssClass="button" method="onBtnBrowseClick"/>
                          <s:submit name="btnPage_load" key="UploadExcel.btnPage_load" cssClass="button" method="onBtnPage_loadClick"/>
                      </td>
                  </tr>
              </table>           
              <table id="grdUpload"></table><div id="pager_grdUpload" ></div>
               <script type="text/javascript">
                    jQuery("#grdUpload").jqGrid({
                    url:            'UploadExcel_getXML.action',
                    datatype: "xml",
                    height: 150,
                    width: 600,
                    colNames:['№','', 'Хуулсан ажилтан'],
                    colModel:[
                             {width:70},
                             {width:100},
                             {width:400} ],
                    rowNum:100,
                    rowTotal: 2000,
                    mtype: "GET",
                    rownumbers: true,
                    rownumWidth: 40,
                    gridview: true,
                    pager:  '#pager_grdUpload',
                    viewrecords: true,
                    sortorder: "asc"
                    });
                    </script>                    
              <table width=100% border="1" cellpadding="4">
                     <tr>
                         <td style="text-align: right;">
                             <s:submit name="btnReturn" key="UploadExcel.btnReturn" cssClass="button" method="onBtnReturnClick"/>
                         </td>
                     </tr>
              </table>
           </s:form>
         </s:param>
   </s:component>
   </body>
</html>
