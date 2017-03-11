<!--
* Моделийн нэр            SendSMS.jsp
*
* Функцын нэр          【Мессеж явуулах】
* Тvvх
* Хувилбарын №  Огноо    Хариуцагч    Агуулга
* 01.00.00                 Огноо    Хариуцагч    Шинээр хийсэн/Зассан
* 01.00.01				2013.02.28	Б.Тэлмүүн		Шинээр үүсгэх
* 02.00.01				2013.03.04	Э.Бат-Эрдэнэ 		Зассан
*
*-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<html>
<head>
<script>
		$(document).ready(function(){
		$("[name=txtSend_name]").val($("#phone_number").val());
			digits();
			texts();
		$("input[name=txtPhone_number]").focus();
			comboAdder('incomplaint_tbl', 'ir','service_type','detailed_service_type', 6, 4);
			
			$(".txtArea").bind("keyup", function(){
					$(".txtArea").css({"border-color":""});
					maxlength = $(this).attr("cols");
					if($(this).val().length > maxlength){
						value = $(this).val().slice(0,maxlength); 
						$(this).val(value);
						$(".txtArea").css({"border-color":"red"});
					}
			});	
  	});
			
		function send_Message() {
			if(!checkNullSendSMSData()){
  			$("#sms_message").html('<span style="color:red"><s:text name="SendSMS.nullData"/></span>');  			
  			setTimeout(function(){$("#sms_message").html("");},2000);  			
  			return false;
  			} 
  			if($("[name=txtPhone_number]").val().length<8){
  			$("#sms_message").html('<s:text name="SendSMS.errSHORTTEST"/>');  			 
  			setTimeout(function(){$("#sms_message").html("");},3000); 		
  			return false;
  			}
  			if($("[name=txt_name]").html()==""||$("[name=txt_name]").html()==""){
				$("#sms_message").html('<s:text name="SendSMS.errMessage"/>');  			 
  				setTimeout(function(){$("#sms_message").html("");},3000); 		
  				return false;
			}
  			
		 $.ajax(
	          { 
		       		type: "POST",
					url: "SendSMS_SendMessage",
					data: "txtPhone_number="+$("[name=txtPhone_number]").val()+
							"&txtSend_name="+$("[name=txtSend_name]").val()+
							"&txt_name="+$("[name=txt_name]").html(),	
					success: function(result)
					    {
					    	    $("#sms_message").html('<s:text name="SendSMS.lblSuccess"/>');
					    	    setTimeout(function(){$("#sms_message").html("SendSMS.lblSuccess");$('#smsDemoDialog').dialog('close');},1000);
					    }					  		
			  });
		}
		
		function checkNullSendSMSData(){
			var validatorFlag="true";
			var datas=new Array();
			datas=$("#SendSMS").find("[accesskey=sms]");
			for(var z=0;z<datas.length;z++){
				if($(datas[z]).val()==""){
					validatorFlag="false";
				}else if($(datas[z]).val()==" "){
					validatorFlag="false";
				}else if($(datas[z]).val()=="null"){
					validatorFlag="false";
				}else if($(datas[z]).val()==null){
					validatorFlag="false";
				}
				
			}
			if(validatorFlag=="true"){
				return true;
			}else{
				return false;
			}
		}
</script>
</head>

<body>   
         <s:form action="SendSMS" cssClass="form">
           	   <s:hidden name="phone_number" id="phone_number"/>
           	   <table width="100%" cellpadding="4" id=InboundTransfer overflow="hidden"> 
						<tr>
               				<td>               				
               					<s:label name="lblStaticPhone_number" value="%{getText('SendSMS.txtPhone_number')}"/>:
               					<s:textfield accesskey='sms' cssStyle="width:170px; margin-top:5px;" name="txtPhone_number" maxlength = "15" cssClass="idfield digits"/>
               				</td>    
               				<td>
               					<s:label name="SendSMS.lblStaticPhone_number_send" value="%{getText('SendSMS.lblStaticPhone_number_send')}"/>:
               					<s:textfield accesskey='sms' cssStyle="width:170px; margin-top:5px;" name="txtSend_name" maxlength = "15" cssClass="idfield digits"/>
               				</td>           				
               			</tr>    
               			<tr>
               				<td>
								<s:label name="SendSMS.operator" value="%{getText('SendSMS.operator')}"/>:
               					<select name="operator" style="width:170px">
               						<option value="mobicom">Mobicom</option>
               						<option value="skytel">Skytel</option>
               						<option value="unitel">Unitel</option>
               						<option value="g-mobile">G-Mobile</option>
               					</select>
               				</td>
               				<td>
               				<s:label name="SendSMS.result" value="%{getText('SendSMS.result')}"/>
               					<s:select name="cmbCall_result_id1" id="cmbCall_result_id1" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbCall_Out_result_idList" />
               				</td>
               			</tr>                    			            			
               			<tr>               				
               				<td>
               					<s:label name="lblStaticTask_comment" value="%{getText('SendSMS.txtSMS')}"/>
               				</td>
	             			<td colspan="2" align="center">
						    	<font color="red"><s:label id="sms_message" name="sms_message"/></font>
						    </td>	
               			</tr>               			
               			<tr>
               				<td colspan="3">
               					<s:textarea cols="500" cssStyle="width: 362px; height:80px;" name="txt_name" cssClass="idfield txtArea"/>		
               				</td>
               				
               			</tr>
               			<tr>               			
               				<td colspan="3" style="text-align: right;"> 
	               				<button class="button" onclick="send_Message(); return false;"><s:label key="SendSMS.btnSend" /></button>
	               				<s:submit name="btnReturn" key="SendSMS.btnReturn" cssClass="button" onclick="$('#smsDemoDialog').dialog('close');return false;"/>               			
               				</td>               				
               			</tr>
               		</table>     
           </s:form>        
   </body>
</html>
