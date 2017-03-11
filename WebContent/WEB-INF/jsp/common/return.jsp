<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<html>
   <jsp:include page="header.jsp">
       <jsp:param name="form_name" value="return_form"/>
   </jsp:include>

   <script>
		function refer() {
			if ($.browser.msie)
			{
				window.returnValue = "success" ;
			}
			else
			{	
				window.opener.document.getElementById('lblResult').value = "success";
			}
			window.close();
		};
	</script>

<body onload="refer()">
</body>
</html>
