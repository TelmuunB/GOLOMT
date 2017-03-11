<!--
* Моделийн нэр             InboundOther.jsp
*
* Функцын нэр          【Дуудлагын төрөл Бусад】
* Тvvх
* Хувилбарын №  Огноо    Хариуцагч    Агуулга
* 01.00.00                 Огноо    Хариуцагч    Шинээр хийсэн/Зассан
* 01.00.01				2013.02.28	Б.Тэлмүүн		Шинээр үүсгэх
* 02.00.01				2013.03.04	Э.Бат-Эрдэнэ 		Зассан
*
*-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@  taglib  prefix="sj"  uri="/struts-jquery-tags"%>
<html>
	<script>
		$(document).ready(function(){
   			$("#btnSave").click(function(){
   						$.ajax({
						      type: "POST",
						      url: "InboundOther_saveEditAll.action",
						      data: get_data_register("InboundOther"),
						      success: function() {
						      	alert("Амжилттай нэмэгдлээ.");
						      }
						    });
							return false;
   			});
   			
   				$("#txtArea5").bind("keyup", function(){
					$("#txtArea5").css({"border-color":""});
					maxlength = $(this).attr("cols");
					if($(this).val().length > maxlength){
						value = $(this).val().slice(0,maxlength); 
						$(this).val(value);						
						$("#txtArea5").css({"border-color":"red"});
					}
				});	
   			
   		});
	</script>
    <link rel="stylesheet" type="text/css" href="css/box.css">

    <s:form action="InboundOther" cssClass="form">
             <fieldset><legend><s:property value="getText('InboundOther.grpOther_Call_Name')"/></legend>
               <table style="width:100%" cellpadding="4">
               		<tr>
               			<td colspan="2">
               			<s:label key="InboundOther.lblStaticOthers_comment"/>:
               			</td>
               		</tr>
               		<tr>
               			<td colspan="2">
               				<s:textarea cols="500" id="txtArea5" accesskey="register" name="call_comment" cssStyle="resize:none;width:425px;height:85px;overflow: auto;" cssClass="idfield txtArea5">
								<s:param name="disabled" value="'true'.equals(status)"/>
								<s:param name="onkeyup">saveOnChange('InboundOther_saveOnChange', {callID: '<s:property value="callID"/>', call_comment:this.value})</s:param>
							</s:textarea>

               			</td>
               		</tr>
               		<tr>
               			<td style="text-align: right;">
            	   			<s:label key="InboundOther.lblStaticUnit_id"/>:
               			</td>
		           		<td>
		           			<s:hidden name="unit_id"/>

		           			<s:textfield accesskey="register" name="unit_name" cssClass="idfield unit_auto">
								<s:param name="disabled" value="'true'.equals(status)"/>
								<s:param name="onchange">saveOnChange('InboundOther_saveOnChange', {callID: '<s:property value="callID"/>', unit_id:this.form.unit_id.value})</s:param>
							</s:textfield>
		           		</td>
               		</tr>
               		<tr>
               			<td style="text-align: right;">
    	           			<s:label key="InboundOther.lblStaticCall_name"/>:
               			</td>
               			<td>
               	    		<s:textfield accesskey="register" name="call_name" cssClass="idfield" maxlength="100">
               	    			<s:param name="disabled" value="'true'.equals(status)"/>
               	    			<s:param name="onchange">saveOnChange('InboundOther_saveOnChange', {callID: '<s:property value="callID"/>', call_name:this.value})</s:param>
               	    		</s:textfield>
               			</td>
               		</tr>
               		<tr>
               			<td style="text-align: right;">
               				<s:label key="InboundOther.lblStaticOthers_result_id"/>:
               			</td>
               			<td>
							<s:select accesskey="register" name="call_result_id" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbResultList">
								<s:param name="disabled" value="'true'.equals(status)"/>
								<s:param name="onchange">var self = this; saveOnChange('InboundOther_saveOnChange', {callID: '<s:property value="callID"/>', call_result_id:this.value}, function(result) { changeDeadline(self.form.deadline, result); })</s:param>
							</s:select>
               			</td>
               		</tr>
               		<tr>
               			<td style="text-align: right;">
	               			<s:label key="InboundOther.lblStaticOthers_rate_id"/>:
               			</td>
               			<td>
							<s:select accesskey="register" name="call_rate_id" cssClass="idselect" headerKey="" headerValue="%{getText('system.rateselect')}" list="cmbRateList" >
								<s:param name="disabled" value="'true'.equals(status)"/>
								<s:param name="onchange">saveOnChange('InboundOther_saveOnChange', {callID: '<s:property value="callID"/>', call_rate_id:this.value})</s:param>
							</s:select>
               			</td>
               		</tr>
               		<tr>
               			<td style="text-align: right;">
	               			<s:label key="InboundOther.lblStaticDeadline"/>:
               			</td>
               			<td>
							<s:textfield accesskey="register" name="deadline" cssClass="idfield date" maxlength="10">
								<s:param name="disabled" value="'true'.equals(status) || 1 != fresult"/>
								<s:param name="onchange">saveOnChange('InboundOther_saveOnChange', {callID: '<s:property value="callID"/>', deadline:this.value})</s:param>
							</s:textfield>
               			</td>
               		</tr>
               </table>
			</fieldset>
</s:form>
