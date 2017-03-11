<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<jsp:include page="/WEB-INF/jsp/common/header.jsp">
     <jsp:param name="form_name" value="PrepareExcel.form_name"/>
</jsp:include>
<script type="text/javascript">
	function goLink()
	{
		if ( window.opener == null)
		{
	 		document.location = "<%=request.getContextPath()%>" + "/MainMenu";
	 	}
	 	else
	 	{
	 		closeWindow();
	 	}
	}
</script>
<body>
	<s:component template="window.ftl" cssStyle="width:500;height:300px;">
	  <s:param name="animate" value="true"/>
	  <s:param name="center" value="true"/>
	  <s:param name="title"><s:text name="PrepareExcel.form_name" /></s:param>
	  <s:param name="body">

		<s:form action="PrepareExcel" cssClass="form">
				<CENTER>
					<img src="images/ms-excel.png" ><br/>
					<div style="margin: 5px 5px 5px 5px;">
						<font style="color:navy;"><s:fielderror fieldName="message"/></font><br/>
						<s:text name="PrepareExcel.comment" />
					</div>
					<br/>
					<s:submit key="PrepareExcel.btnCreate" cssClass="button" method="createTempate"/>
				</CENTER>
		</s:form>
	  </s:param>
	</s:component>
</body>
</html>
