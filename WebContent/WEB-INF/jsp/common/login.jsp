<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<html>

<jsp:include page="header.jsp">
	<jsp:param name="form_name" value="system.form_name"/>
</jsp:include>

<body>
	<s:component name="window" template="window.ftl" cssStyle="width:400;height:300px;">
	  <s:param name="animate" value="true"/>
  	  <s:param name="center" value="true"/>
	  <s:param name="title"><s:property value="getText('system.form_name')"/></s:param>
	  <s:param name="body">
		<s:form action="Login" cssClass="form">
			<TABLE border="0" width="100%" height="80%">
				<TR>
					<TD colspan="2" align="center">
					<H2><s:property value="getText('system.name')" /></H2>
					</TD>
				</TR>
				<TR>
					<TD colspan="2" align="center">
					<HR>
					</TD>
				</TR>
				<TR>
					<TD></TD>
					<TD><s:actionerror name="error" cssClass="error_message" /></TD>
				</TR>
				<TR>
					<TD><s:property value="getText('Login.txtUserName')"/></TD>
					<TD><s:textfield name="txtUserName" cssClass="idfield" cssStyle="width:200px;"/></TD>
				</TR>
				<TR>
					<TD><s:property value="getText('Login.txtPassword')"/></TD>
					<TD><s:password name="txtPassword" cssClass="passwordfield" cssStyle="width:200px;" /></TD>
				</TR>
				<TR>
					<TD></TD>
					<TD><s:a action="PasswordForgot_input" key="Login.lnkForgotPassword"></s:a>
					</TD>
				</TR>
				<TR>
					<TD></TD>
					<TD><s:submit key="Login.btnLogIn" align="center" cssClass="submit_button button" method="onBtnLogInClick" />&nbsp; 
					    <s:reset key="Login.btnCancel" align="center" cssClass="button" /></TD>
				</TR>
				
				<tr>
					<td colspan="2" align="right"><a href="http://www.youtube.com/watch?v=xdAYDEj-1So" target="ini">Тусламж</a></td>
				</tr>
			</TABLE>
			<img style="position:absolute;top:10px;left:360px" border="true" oncontextmenu="return false;" src="https://seal.thawte.com/getthawteseal?at=0&sealid=0&dn=meduuleg.iaac.mn&lang=en&gmtoff=-480" name="seal">
		</s:form>			  
	  </s:param>
	</s:component>
</body>
</html>
