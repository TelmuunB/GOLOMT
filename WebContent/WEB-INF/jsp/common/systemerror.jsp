<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<html>

<jsp:include page="header.jsp">
	<jsp:param name="form_name" value="system.form_name"/>
	<jsp:param name="checklogin" value="false"/>
</jsp:include>

<body>
	<s:component template="window.ftl" cssStyle="width:300;height:200px;">
	  <s:param name="animate" value="true"/>
	  <s:param name="center" value="true"/>
	  <s:param name="title"><s:property value="getText('system.message_form')"/></s:param>
	  <s:param name="body">
		<s:form cssClass="form">
			<CENTER>
					<h2><b><font style="color:navy;"><s:property value="getText('system.ERR022')" escape="false"/></font></b></h2>
					<s:submit name="btn" key="system.retry_btn" align="center" cssClass="button" onclick="history.back();return false;"/>
			</CENTER>
		</s:form>
	  </s:param>
	</s:component>
</body>
</html>
