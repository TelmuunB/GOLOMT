<!--
* Моделийн нэр             CallDetail.jsp
*
* Функцын нэр          【Дуудлагын дэлгэрэнгүй】
* Тvvх
* Хувилбарын №  Огноо    Хариуцагч    Агуулга
* 01.00.00                 Огноо    Хариуцагч    Шинээр хийсэн/Зассан
* 01.00.01				2013.03.01	Б.Тэлмүүн		Шинээр үүсгэх
* 02.00.01				2013.03.04	Э.Бат-Эрдэнэ 		Зассан
*
*-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
	<html lang="en">	
		<jsp:include page="/WEB-INF/jsp/common/header.jsp">
       	<jsp:param name="form_name" value="CallDetail.form_name"/>
        	</jsp:include>
        	
       <body>
   <s:component template="window.ftl" cssStyle="width:950px;height:320px;">
       <s:param name="animate" value="true"/>
       <s:param name="center" value="true"/>
       <s:param name="title"><s:property value="getText('CallDetail.form_name')"/></s:param>
       <s:param name="body">
    <s:form action="CallDetail" cssClass="form">

               <fieldset><legend class="CallDetail"><s:property value="getText('CallDetail.grpCall_detail')"/></legend>
               <table cellpadding="8" class="full-no-wrap" width="100%" id="CallDetailTable">
               		<tr>
               			<td>
               			<s:label name="lblStaticCall_id" value="%{getText('CallDetail.lblStaticCall_id')}"/>:
               			</td>
               			<td colspan="5">
               			<b><s:label name="lblCall_id" value="%{getText('CallDetail.lblCall_id')}"/></b>
               			</td>
               		</tr>
               		
               		<tr>
               			<td>
               			<s:label name="lblStaticPhone_number" value="%{getText('CallDetail.lblStaticPhone_number')}"/>:
               			</td>
               			<td>
               			<b><s:label name="lblPhone_number" value="%{getText('CallDetail.lblPhone_number')}"/></b>
               			</td>
               			<td>
               			<s:label name="lblStaticCall_req_type_id" value="%{getText('CallDetail.lblStaticCall_req_type_id')}"/>:
               			</td>
               			<td>
               			<s:select name="cmbCall_req_type_id" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="%{#{}}"/>               			
               			</td>
               			<td>
               			<s:label name="lblStaticUnit_id" value="%{getText('CallDetail.lblStaticUnit_id')}"/>:
               			</td>
               			<td>
               			<b><s:label name="lblUnit_id" value="%{getText('CallDetail.lblUnit_id')}"/></b>
               			</td>
               		</tr>
               		<tr>
               			<td>
               			<s:label name="lblStaticSecond_phone" value="%{getText('CallDetail.lblStaticSecond_phone')}"/>:
               			</td>
               			<td>
               			<b><s:label name="lblSecond_phone" value="%{getText('CallDetail.lblSecond_phone')}"/></b>
               			</td>
               			<td>
               			<s:label name="lblStaticCaller_name" value="%{getText('CallDetail.lblStaticCaller_name')}"/>:
               			</td>
               			<td>
               			<b><s:label name="lblCaller_name" value="%{getText('CallDetail.lblCaller_name')}"/></b>
               			</td>
               			<td>
               			<s:label name="lblStaticType_id" value="%{getText('CallDetail.lblStaticType_id')}"/>:
               			</td>
               			<td>
               			<b><s:label name="lblType_id" value="%{getText('CallDetail.lblType_id')}"/></b>
               			</td>
               		</tr>
               		<tr>
               			<td>
               			<s:label name="lblStaticCall_sort_id" value="%{getText('CallDetail.lblStaticCall_sort_id')}"/>:
               			</td>
               			<td>
               			<b><s:label name="lblCall_sort_id" value="%{getText('CallDetail.lblCall_sort_id')}"/></b>
               			</td>
               			<td>
               			<s:label name="lblStaticClose_type_Id" value="%{getText('CallDetail.lblStaticClose_type_Id')}"/>:
               			</td>
               			<td>
               			<b><s:label name="lblClose_type_id" value="%{getText('CallDetail.lblClose_type_id')}"/></b>
               			</td>
               			<td>
               			<s:label name="lblStaticCall_result_id" value="%{getText('CallDetail.lblStaticCall_result_id')}"/>:
               			</td>
               			<td>
               			<b><s:label name="lblCall_result_id" value="%{getText('CallDetail.lblCall_result_id')}"/></b>
               			</td>
               		</tr>
               		<tr>
               			<td>
               			<s:label name="lblStaticAgent_id" value="%{getText('CallDetail.lblStaticAgent_id')}"/>:
               			</td>
               			<td>
               			<b><s:label name="lblAgent_id" value="%{getText('CallDetail.lblAgent_id')}"/></b>
               			</td>
               			<td>
               			<s:label name="lblStaticCall_name" value="%{getText('CallDetail.lblStaticCall_name')}"/>:
               			</td>
               			<td>
               			<b><s:label name="lblCall_name" value="%{getText('CallDetail.lblCall_name')}"/></b>
               			</td>
               			<td>
               			<s:label name="lblStaticCall_rate_id" value="%{getText('CallDetail.lblStaticCall_rate_id')}"/>:
               			</td>
               			<td>
               			<b><s:label name="lblCall_rate_id" value="%{getText('CallDetail.lblCall_rate_id')}"/></b>
               			</td>
               		</tr>
               		<tr>
               			<td>
               			<s:label name="lblStaticCall_start_time" value="%{getText('CallDetail.lblStaticCall_start_time')}"/>:
               			</td>
               			<td>
               			<b><s:label name="lblCall_start_time" value="%{getText('CallDetail.lblCall_start_time')}"/></b>
               			</td>
               			<td>
               			<s:label name="lblStaticDuration_time" value="%{getText('CallDetail.lblStaticDuration_time')}"/>:
               			</td>
               			<td>
               			<b><s:label name="lblDuration_time" value="%{getText('CallDetail.lblDuration_time')}"/></b>
               			</td>
               			<td>
               			<s:label name="lblStaticCall_type" value="%{getText('CallDetail.lblStaticCall_type')}"/>:
               			</td>
               			<td>
               			<b><s:label name="lblCall_type" value="%{getText('CallDetail.lblCall_type')}"/></b>
               			</td>
               		</tr>
               		<tr>
               			<td class="last">
               			<s:label name="lblStaticCall_comment" value="%{getText('CallDetail.lblStaticCall_comment')}"/>:
               			</td>
               			<td colspan="5" class="last">
               			<b><s:label name="lblCall_comment" value="%{getText('CallDetail.lblCall_comment')}"/></b>
               			</td>
               		</tr>
               		<tr>
               			<td colspan="6" align="right" class="last">
               			<s:submit name="btnListen" key="CallDetail.btnListen" cssClass="button" method="onBtnListenClick"/>
               			</td>
               		</tr>
               </table>
		</fieldset>
		</s:form>
      </s:param>
   </s:component>
   </body>
</html>
