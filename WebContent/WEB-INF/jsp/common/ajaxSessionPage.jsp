<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<jsp:include page="/WEB-INF/jsp/common/header.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function ()
	{
		//IE window.opener support хийхгүй байна
		try{
			if (window.opener)
			{
				if (window.opener.location.href != null)
				{
					if (!(window.opener.location.href.indexOf("AjaxSession")>0))
					{
						window.opener.location.reload();
						closeWindow();
						return;
					}
				}
			}
			window.location = "Login"; 
			
		}
		catch(ex){
			window.location = "Login";
		}	
	});
</script>