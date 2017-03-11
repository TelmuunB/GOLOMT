<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<html>

	<jsp:include page="WEB-INF/jsp/common/header.jsp">
		<jsp:param name="form_name" value="system.form_name"/>
		<jsp:param name="checklogin" value="false"/>
	</jsp:include>
	
	
	<body  style="overflow:hidden;margin-top:0px;" onload="setTimeout(function() { window.location.href = 'mainMenu.action'}, 5)">
		<table class="splash" align="center" height=100% width=100%>
			<tr>
				<td align="center">
					<CENTER>
					<DIV class="">
					<table height="100%" width="100%">
						<TR valign="bottom">
							<TD align="center">
								<IMG SRC="Images/LOGO.jpg"/>
								<BR>
							
								<SPAN class="leco-copyright">
								&nbsp;Copyright &copy;2013. Голомт банк. 
								<BR>
								&nbsp;All Rights Reserved. 
								</SPAN>
							</TD>
						</TR>
					</table>
					</DIV>
					</CENTER>
				</td>
			</tr>
		</table>
	</body>
</html>
