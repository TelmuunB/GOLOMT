<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html"%>
      <s:if test="#session.login != 'admin'">
      	<jsp:forward page="login.jsp" />
      </s:if>
