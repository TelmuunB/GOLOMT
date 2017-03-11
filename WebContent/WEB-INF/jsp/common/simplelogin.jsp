<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<form action="Login" cssClass="form" onsubmit="simpleLogin(this.txtUserName.value, this.txtPassword.value);return false;">
	<TABLE border="0" width="100%" height="80%">
		<TR>
			<TD colspan="2"><s:property value="getText('Login.txtRelogin')"/></TD>
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
			<TD><s:submit key="Login.btnLogIn" align="center" cssClass="submit_button button ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"/></TD>
		</TR>
	</TABLE>
</form>			  
