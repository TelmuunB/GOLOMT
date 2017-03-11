<%@page contentType="text/html" pageEncoding="UTF-8" %><%@ taglib prefix="s" uri="/struts-tags" %>
<%
response.setHeader("Cache-Control", "no-cache");
response.setHeader("Pragma", "no-cache");
response.setDateHeader ("Expires", -1);
%>[
<s:iterator value="UnitList" status="status" var="row" >
	<s:if test="#status.first != true">,</s:if>
	{"id":"<s:property value='%{key}'/>","value":"<s:property value='%{value}' escapeHtml="false"/>"}
</s:iterator>
]