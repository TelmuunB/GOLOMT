<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<html>

	<jsp:include page="header.jsp">
		<jsp:param name="form_name" value="system.form_name"/>
		<jsp:param name="checklogin" value="false"/>
	</jsp:include>

	<body>

		<s:component template="window.ftl" cssStyle="width:400;height:200px;">
		  <s:param name="animate" value="true"/>
		  <s:param name="center" value="true"/>
		  <s:param name="title"><s:property value="getText('passwordForgot.form_name')"/></s:param>
		  <s:param name="body">
			<s:form action="PasswordForgot" cssClass="form">
					<s:hidden name="message" value="passwordForgot.success_message" />
					<s:hidden name="button" value="system.login_btn" />
					<s:hidden name="return_form" value="Login_input" />
		
					<TABLE border="0" width="100%" height="80%">
						<TR>
							<TD colspan="2" align="center"><font style="font-size:16px;font-weight:bold"><s:property value="getText('passwordForgot.warning')" /></font></TD>
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
							<TD></TD>
							<TD align="center"><s:property value="getText('passwordForgot.email')" />: &nbsp;
								<s:textfield name="email" cssClass="idfield" cssStyle="width:180px;"/>
							</TD>
						</TR>
						<TR>
							<TD></TD>
							<TD align="center">
								<s:submit value="%{getText('passwordForgot.send_btn')}" align="center" cssClass="button"  method="send"/>&nbsp;
								<s:submit value="%{getText('system.return_btn')}" align="center" cssClass="button" onclick="this.form.action='Login.action'"/>&nbsp;
							</TD>
						</TR>
					</TABLE>
			</s:form>
		  </s:param>
		</s:component>
	</body>

</html>
