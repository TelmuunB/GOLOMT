<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<html>

<jsp:include page="header.jsp">
	<jsp:param name="form_name" value="system.form_name"/>
	<jsp:param name="checklogin" value="false"/>
</jsp:include>
<script>

</script>
<body>
	<s:component template="window.ftl" cssStyle="width:400;height:200px;">
	  <s:param name="animate" value="true"/>
	  <s:param name="center" value="true"/>
	  <s:param name="title"><s:property value="getText('system.message_form')"/></s:param>
	  <s:param name="body">
		<s:form action="%{#parameters.return_form}" cssClass="form">
			<CENTER>
					<b>
						<font style="color:navy;">
							<s:property value="getText(#parameters.message, {#attr.param})"/>
						</font>
					</b>
					<br>
					<br>
					<br>
					<s:submit name="btn" value="%{getText(#parameters.button)}" onclick="%{getText(#parameters.function)}" align="center" cssClass="button"/>
			</CENTER>
		</s:form>
	  </s:param>
	</s:component>
</body>
</html>
