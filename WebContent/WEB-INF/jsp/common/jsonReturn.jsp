<%@page contentType="application/json"  pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
{
	"error_code" : "<s:property value="%{jsonReturnValue}"/>",
    "error_msg" : "<s:property value="getText(jsonReturnValue)"/>"
}
