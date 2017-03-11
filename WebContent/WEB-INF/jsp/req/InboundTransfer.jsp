﻿
<!--
* Моделийн нэр            InboundTransfer.jsp
*
* Функцын нэр          【Дуудлагын төрөл_шилжүүлгийг хийсэн
* Тvvх
* Хувилбарын №  Огноо    Хариуцагч    Агуулга
* 01.00.00                 Огноо    Хариуцагч    Шинээр хийсэн/Зассан
* 01.00.01 2013.02.28 Б.Тэмүүлэн Шинээр үүсгэх
* 02.00.01 2013.03.04 Б.Оргил Зассан
*
*-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<s:form action="InboundTransfer" cssClass="form">
<script language="javascript">
		$(document).ready(function(){

			$("#txtArea4").bind("keyup", function(){
					$("#txtArea4").css({"border-color":""});
					maxlength = $(this).attr("cols");
					if($(this).val().length > maxlength){
						value = $(this).val().slice(0,maxlength); 
						$(this).val(value);						
						$("#txtArea4").css({"border-color":"red"});
					}
			});

			digits();
			texts();
			});
</script>
	<fieldset>
		<legend>
			<s:property value="getText('InboundTransfer.grpCall_transfer')" />
		</legend>
		<table width="100%" cellpadding="2">
			<tr>
				<td width=30%>
					<s:label key="InboundTransfer.lblStaticCall_transfer_number" />:
				</td>
				<td width=70%>
					<s:textfield name="transfer_phonenumber" cssClass="idfield digits" cssStyle="width: 150;" maxlength="15">
       	    			<s:param name="disabled" value="'true'.equals(status)"/>
       	    			<s:param name="onchange">saveOnChange('InboundTransfer_saveOnChange', {callID: '<s:property value="callID"/>', transfer_phonenumber:this.value})</s:param>
       	    		</s:textfield>
       	    		<s:if test="finishCall != 'true'">
						<!--<s:submit type="image" src='Images/phone.png' name="btnCall" key="InboundTransfer.btnCall" cssStyle="width:30;height:30px;margin:0 5px 0 15px;"  onclick="alert('call transfer');return false;" />
					--></s:if>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<s:label key="InboundTransfer.lblStaticCall_transfer_comment" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<s:textarea cols="500" id="txtArea4" accesskey="register" cssStyle="resize: none; overflow: auto; width:425px; height:100px;" name="call_comment" cssClass="idfield txtArea4">
						<s:param name="disabled" value="'true'.equals(status)"/>
						<s:param name="onkeyup">saveOnChange('InboundTransfer_saveOnChange', {callID: '<s:property value="callID"/>', call_comment:this.value})</s:param>
					</s:textarea>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;" width=50%>
					<s:label key="InboundTransfer.lblStaticUnit_id" />:
				</td>
           		<td>
           			<s:hidden name="unit_id"/>

           			<s:textfield accesskey="register" name="unit_name" cssClass="idfield unit_auto">
						<s:param name="disabled" value="'true'.equals(status)"/>
						<s:param name="onchange">saveOnChange('InboundTransfer_saveOnChange', {callID: '<s:property value="callID"/>', unit_id:this.form.unit_id.value})</s:param>
					</s:textfield>
           		</td>
			</tr>
			<tr>
				<td style="text-align: right; width: 50%;">
					<s:label key="InboundTransfer.lblStaticCall_name" />:
				</td>
				<td>
       	    		<s:textfield accesskey="register" name="call_name" maxlength = "100" cssClass="idfield">
       	    			<s:param name="disabled" value="'true'.equals(status)"/>
       	    			<s:param name="onchange">saveOnChange('InboundTransfer_saveOnChange', {callID: '<s:property value="callID"/>', call_name:this.value})</s:param>
       	    		</s:textfield>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;" width=50%>
					<s:label key="InboundTransfer.lblStaticTransfer_result_id" />:
				</td>
				<td>
					<s:select accesskey="register" name="call_result_id" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbResultList">
						<s:param name="disabled" value="'true'.equals(status)"/>
						<s:param name="onchange">var self = this; saveOnChange('InboundTransfer_saveOnChange', {callID: '<s:property value="callID"/>', call_result_id:this.value}, function(result) { changeDeadline(self.form.deadline, result); })</s:param>
					</s:select>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;" width=50%>
					<s:label key="InboundTransfer.lblStaticTransfer_rate_id" />:
				</td>
				<td>
					<s:select accesskey="register" name="call_rate_id" cssClass="idselect" headerKey="" headerValue="%{getText('system.rateselect')}" list="cmbRateList" >
						<s:param name="disabled" value="'true'.equals(status)"/>
						<s:param name="onchange">saveOnChange('InboundTransfer_saveOnChange', {callID: '<s:property value="callID"/>', call_rate_id:this.value})</s:param>
					</s:select>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;" width=50%>
					<s:label key="InboundTransfer.lblStaticDeadline" />:
				</td>
				<td>
					<s:textfield accesskey="register" name="deadline" cssClass="idfield date" maxlength="10">
						<s:param name="disabled" value="'true'.equals(status) || 1 != fresult"/>
						<s:param name="onchange">saveOnChange('InboundTransfer_saveOnChange', {callID: '<s:property value="callID"/>', deadline:this.value})</s:param>
					</s:textfield>
				</td>
			</tr>
		</table>
	</fieldset>
</s:form>
