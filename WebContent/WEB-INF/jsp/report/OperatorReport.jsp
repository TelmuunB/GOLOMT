<!--
* „M„Ђ„t„u„|„y„z„~ „~„Џ„‚             OperatorReport.jsp
*
* „U„…„~„{„€„Ќ„~ „~„Џ„‚          ЃyОператорын ажлын үр дүнгийн тайланЃz
* „Svv„‡
* „V„…„r„y„|„q„p„‚„Ќ„~ ‡‚  „O„s„~„Ђ„Ђ    „V„p„‚„y„…„€„p„s„‰    „@„s„…„…„|„s„p
* 01.00.00                 „O„s„~„Ђ„Ђ    „V„p„‚„y„…„€„p„s„‰    „Y„y„~„Џ„Џ„‚ „‡„y„z„ѓ„Џ„~/„H„p„ѓ„ѓ„p„~
*-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
   <jsp:include page="/WEB-INF/jsp/common/header.jsp">
       <jsp:param name="form_name" value="OperatorReport.form_name"/>
   </jsp:include>
   <body>
   <s:component template="window.ftl" cssStyle="width:500;height:300px;">
       <s:param name="animate" value="true"/>
       <s:param name="center" value="true"/>
       <s:param name="title"><s:property value="getText('OperatorReport.form_name')"/></s:param>
       <s:param name="body">
           <s:form action="OperatorReport" cssClass="form">
               <fieldset><legend><s:property value="getText('OperatorReport.grpReport_agent')"/></legend>
</fieldset>
               <table id="grdReport_agent"></table><div id="pager_grdReport_agent" ></div>
               <script type="text/javascript">
                    jQuery("#grdReport_agent").jqGrid({
                    url:            'OperatorReport_getXML.action',
                    datatype: "xml",
                    height: 255,
                    width: 600,
                    colNames:['Header1','Header2', 'Header3'],
                    colModel:[
                             {width:65},
                             {width:150},
                             {width:100} ],
                    rowNum:100,
                    rowTotal: 2000,
                    mtype: "GET",
                    rownumbers: true,
                    rownumWidth: 40,
                    gridview: true,
                    pager:  '#pager_grdReport_agent',
                    viewrecords: true,
                    sortorder: "asc"
                    });
                    </script>
               <fieldset><legend><s:property value="getText('OperatorReport.grpPhone_list')"/></legend>
</fieldset>
               <table id="grdPhone_list"></table><div id="pager_grdPhone_list" ></div>
               <script type="text/javascript">
                    jQuery("#grdPhone_list").jqGrid({
                    url:            'OperatorReport_getXML.action',
                    datatype: "xml",
                    height: 255,
                    width: 600,
                    colNames:['Header1','Header2', 'Header3'],
                    colModel:[
                             {width:65},
                             {width:150},
                             {width:100} ],
                    rowNum:100,
                    rowTotal: 2000,
                    mtype: "GET",
                    rownumbers: true,
                    rownumWidth: 40,
                    gridview: true,
                    pager:  '#pager_grdPhone_list',
                    viewrecords: true,
                    sortorder: "asc"
                    });
                    </script>
               <s:property value="getText('OperatorReport.txtPhone_number')"/>
               <s:textfield name="txtPhone_number" cssClass="idfield"/>
               <s:submit name="btnSearch" key="OperatorReport.btnSearch" cssClass="button" method="onBtnSearchClick"/>
           </s:form>
         </s:param>
   </s:component>
   </body>
</html>
