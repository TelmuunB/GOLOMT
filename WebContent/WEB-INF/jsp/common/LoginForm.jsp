<!--
* Моделийн нэр             LoginForm.jsp
*
* Функцын нэр          【Нэвтрэх форм】
* Тvvх
* Хувилбарын №  Огноо    Хариуцагч    Агуулга
* 01.00.00                 Огноо    Хариуцагч    Шинээр хийсэн/Зассан
* 01.00.01				2013.03.01	Б.Тэмүүлэн		Шинээр үүсгэх
* 02.00.01				2013.03.04	А.Инкар 		Зассан
*
*-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<html>
<script type="text/javascript">
	function error(){
		alert($("#user_id").val());
		if($("#user_id").val()=="" || $("#password").val()=="" ){
			$("#error_message").html("<s:text name='error_messageLogin'/>");
			return false;
		}else{
			return true;		
		}
	}
	function login_Test(){
		if(document.getElementById("login_Message").value=="1"){
			$("#error_message").html("<s:text name='LoginForm.ERR01'/>");
		}else if(document.getElementById("login_Message").value=="2"){
			$("#error_message").html("<s:text name='LoginForm.err_WrongDb'/>");
		}
	}
</script>

   <jsp:include page="/WEB-INF/jsp/common/header.jsp">
       <jsp:param name="form_name" value="LoginForm.form_name"/>
   </jsp:include>
   <body onload="login_Test()">
   <s:component template="window.ftl" cssStyle="width:400px;height:250px;">
       <s:param name="animate" value="true"/>
       <s:param name="center" value="true"/>
       <s:param name="title"><s:property value="getText('LoginForm.form_name')"/></s:param>
       <s:param name="body">
           <s:form action="LoginForm" cssClass="form">
	           <table width="90%" height="150px" style="margin:10px;">
		           <tr>
		           		<td align="center" colspan="2">
		          	   		<h1 style="color:navy;font-weight:bold;"><s:text name="LoginForm.lblMsg"/></h1>
		          	 	</td>
		          	</tr>
					<tr>
						<td colspan="2">
							<b><span id="error_message" style="color:red"></span></b>
							<s:hidden name="login_Message" id="login_Message"/>
						</td>
					</tr>
		          	 <tr>
		              	<td>
		              		 <b><s:property value="getText('LoginForm.txtUser_id')"/></b>
		              	</td>
		              	<td>
		             		 <s:textfield id="user_id" name="user_id" maxlength = "25" cssClass="idfield"/>
		             	</td>
		             </tr>
		             <tr>
		             	<td>
		              		 <b><s:property value="getText('LoginForm.lblStaticPassword')"/></b>
		              	</td>
		              	<td>
		             		 <s:password   id="password" name="password" cssClass="idfield"/></td>
		             </tr>
		             <tr>
		                <td colspan="2" align="center">
		               		 <s:submit name="btnLogin" key="LoginForm.btnLogin" cssClass="button"  onClick="error();" method="onLoginClick"/>
		               		 <s:reset name="btnClear" key="LoginForm.btnClear" cssClass="button"/>
		               </td>
		             </tr>
	             </table>
           </s:form>
         </s:param>
   </s:component>
   </body>
</html>
