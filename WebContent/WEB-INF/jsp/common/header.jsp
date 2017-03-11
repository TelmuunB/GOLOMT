<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", -1);
%>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="Expires" content="0">
		<meta http-equiv="Cache-Control" content="no-cache">
		<meta http-equiv="Pragma" content="no-cache">

		<base target="_self" >
		<link href="css/images/golomt.png" rel="shortcut icon"/>

		<title><s:property value="getText(#parameters.form_name)"/></title>

		<link rel="stylesheet" href="struts/css/combined.css" type="text/css" />
		<link rel="stylesheet" href="css/box.css" type="text/css"/>
		<link rel="stylesheet" href="css/insert-form.css" type="text/css"/>
		<link rel="stylesheet" href="css/golomt.css" type="text/css"/>
		<link rel="stylesheet" href="css/jquery-ui.css" type="text/css"/>

  		<script type="text/javascript" src="dl.js"></script>
		<script type="text/javascript" src="struts/js/combined20130203.js"></script>
  		<script type="text/javascript" src="js/gapi.js"></script>
		<script type="text/javascript" src="js/golomt.js"></script>
	</head>
