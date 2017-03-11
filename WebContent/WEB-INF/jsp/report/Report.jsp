<!--
* Моделийн нэр             InboundComplaint.jsp
*
* Функцын нэр          【Тайлан】
* Тvvх
* Хувилбарын №  Огноо    Хариуцагч    Агуулга
* 01.00.00                 Огноо    Хариуцагч    Шинээр хийсэн/Зассан
* 01.00.01				2013.02.28	Б.Тэлмүүн		Шинээр үүсгэх
* 02.00.01				2013.03.04	Э.Бат-Эрдэнэ 		Зассан
*
*-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	$(document).ready(function(){
				if($("#permission").val()=="0"){
					$("[name=agentId]").attr("disabled","disabled");
				}

			});
</script>
<s:form action="Report" cssClass="form" target="report">
	<fieldset><legend><s:property value="getText('Report.grpReport_agent')" /></legend>
	<table cellpadding="4"  cellspacing="4">
		<tr>
			<td><s:label key='Report.Date' />:</td>
			<td><s:textfield name="dateFrom" cssClass="idfield date" />~</td>
			<td><s:textfield name="dateTo" cssClass="idfield date" /></td>
		</tr>
		<tr>
			<td><s:label key='Report.Agent' /></td>
			<td>
				<s:hidden name="permission" id="permission" />
				<s:select accesskey="A" name="agentId" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbAgentList"  />
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<s:submit name="btnSearch" key="Report.btnDownload" cssClass="button" method="onDownload" accesskey="R"/>
			</td>
		</tr>
	</table>
	</fieldset>
</s:form>

	<iframe name="report" frameborder="0" width="0px" height="0px" scrolling="no" style="display:none">
    </iframe>
