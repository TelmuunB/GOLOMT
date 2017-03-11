<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>

<jsp:include page="header.jsp">
	<jsp:param name="form_name" value="system.form_name"/>
	<jsp:param name="checklogin" value="false"/>
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
	<s:component template="window.ftl" cssStyle="width:400;height:200px;">
	  <s:param name="animate" value="true"/>
	  <s:param name="center" value="true"/>
	  <s:param name="title"><s:property value="getText('system.error')"/></s:param>
	  <s:param name="body">

		<s:form action="%{#parameters.return_form}" cssClass="form">
				<CENTER>
					<img src="struts/images/warning.png" width="90"><br/>
					<div style="margin: 5px 5px 5px 5px;">
						<font style="color:navy;"><s:fielderror fieldName="messageErr"/></font>
					</div>
					<br/>
					<s:submit key="system.ok_btn" cssClass="button" onclick="goLink(); return false;" />
				</CENTER>
		</s:form>
	  </s:param>
	</s:component>
</body>
</html>
