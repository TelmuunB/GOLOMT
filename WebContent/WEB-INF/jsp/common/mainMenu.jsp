<!--
* Моделийн нэр             CallerHistory.jsp
*
* Функцын нэр          【Үйлчлүүлэгчийн түүх】
* Тvvх
* Хувилбарын №  Огноо    Хариуцагч    Агуулга
* 01.00.00                 Огноо    Хариуцагч    Шинээр хийсэн/Зассан
* 01.00.01				2013.03.01	Б.Тэлмүүн		Шинээр үүсгэх
* 02.00.01				2013.03.01	Э.Бат-Эрдэнэ 		Зассан
*
*-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<jsp:include page="/WEB-INF/jsp/common/header.jsp">
	<jsp:param name="form_name" value="mainMenu.form_name" />
</jsp:include>

<script>
	/* tab */
	$(function() {
	$( "#maintab" ).tabs({
	selected:<s:property value="defaultTab"/>,
	disabled:[<s:property value="disabledTabs"/>],
	load: function()
	{
		formatter($("#maintab div.ui-tabs-panel:not(.ui-tabs-hide)")) ;
	},
	beforeLoad: function( event, ui ) {
	ui.jqXHR.error(function() {
	ui.panel.html(
	"Couldn't load this tab." );
	});
	},cache:true
	});
	});

	/* subtab */
	$(function() {
	$( "#subtab" ).tabs({
	load: function()
	{
		formatter($("#subtab div.ui-tabs-panel:not(.ui-tabs-hide)")) ;
	},
	beforeLoad: function( event, ui ) {
	ui.jqXHR.error(function() {
	ui.panel.html(
	"Couldn't load this tab. We'll try to fix this as soon as possible. " +
	"If this wouldn't be a demo." );
	});
	}
	});
	});
</script>
<script type="text/javascript">
	
	function changeLang(e){
		if(e.value!=""){
			document.location.href = e.value;
		}
	}
	
</script>
<body onload="checkCalling();">
<s:component template="window.ftl" cssStyle="width:1200px;height:1000px;">
	<s:param name="animate" value="true" />
	<s:param name="center" value="true" />
	<s:param name="title">
		<s:property value="getText('mainMenu.form_name')" />
	</s:param>
	<s:param name="body">
		<div id="maintab">
			<ul>
				<li><a href="CallReqType.action?phone_number=<s:property value="%{phone_number}"/>&dnis=<s:property value="%{dnis}"/>&callID=<s:property value="%{callID}"/>&type=<s:property value="%{type}"/>&calltype=<s:property value="%{calltype}"/>"><s:text name="Inbound"/></a></li>
				<li><a href="Outbound.action?type=<s:property value="%{type}"/>&phone_number=<s:property value="%{phone_number}"/>&dnis=<s:property value="%{dnis}"/>&callID=<s:property value="%{callID}"/>&callEnd=<s:property value="%{callEnd}"/>&calltype=<s:property value="%{calltype}"/>"><s:text name="Outbound" /></a></li>
				<li><a href="Complaint.action"><s:text name="Complaint" /></a></li>
				<li><a href="DistributeTask.action"><s:text name="DistributeTask" /></a></li>
				<li><a href="CallHistory.action"><s:text name="CallHistory" /></a></li>
				<li><a href="Report.action"><s:text name="Report" /></a></li>
				<li><a href="Tohiruulga.action"><s:text name="InsertData" /></a></li>
				<li><a href="HelpEdit.action"><s:text name="HelpTreeEdit" /></a></li>
			</ul>
		</div>
		<div>
		</div>
		
		<s:form action="mainMenu" cssClass="form" id="mainMenu_idid">
		</s:form>
		<div style="right:0px;bottom:0px" style="float:right;margin-bottom:10px; margin-right:10px; position:absolute">
		<select id="language_combo" onchange="changeLang(this);">
			<option value="">
			<s:property value="getText('system.select')"/>	
			</option>
			<option value="mainMenu?request_locale=mn">
			Монгол
			</option>
			<option value="mainMenu?request_locale=en">
			English
			</option>
		</select>
		<a href="Status?status=4"><s:property value="getText('mainMenu.logout')" /></a>
		</div>
	</s:param>
</s:component>
	<div id="bottom_message" style="position:absolute;right:0px;top:0px;display:none;width:180px;height:0px;padding:15px;background:white;border-radius:10px;border-left:1px solid;border-bottom:1px solid;">
		<s:hidden name="ownAgentId"></s:hidden>
		<label id="calling_status"></label>
		<s:hidden name="err_message" id="err_message"/>
		<script type="text/javascript">
		if($("#err_message").val()=="1"){
			$("#bottom_message").css({"display":"block"});
			$("#calling_status").html("<s:text name='mainMenu.errWrong_number'/>");
		}	
		</script>
	</div>
	
</body>
</html>
