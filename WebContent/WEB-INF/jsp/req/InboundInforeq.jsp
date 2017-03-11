<!--
* Моделийн нэр             InboundInforeq.jsp
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
<script>
		$(document).ready(function(){
			comboAdder('inforeq_tbl', 'ir','service_type','detailed_service_type', 6, 2);
			
					$("#txtArea1").bind("keyup", function(){
					$("#txtArea1").css({"border-color":""});
					maxlength = $(this).attr("cols");
					if($(this).val().length > maxlength){
						value = $(this).val().slice(0,maxlength); 
						$(this).val(value);
						
						$("#txtArea1").css({"border-color":"red"});
					}
			});	
});
</script>

 <s:form action="InboundInforeq" cssClass="form">
		<fieldset><legend><s:property value="getText('InboundInforeq.grpCall_Information')" /></legend>
		<table width="100%" cellpadding="2">
			<tr>
				<td style="color:blue; text-align: center;" width=50%>
					<s:label key="InboundInforeq.lblStaticInformation_id" />
				</td>
				<td style="color:blue; text-align: center;" width=50%>
					<s:label key="InboundInforeq.lblStaticInformation_type_id" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<table width="100%" id="inforeq_tbl">
						<tr class="ir1" >
              					<td style="text-align: center;" width=50%>
              						<s:select accesskey="register" cssClass="idselect" headerKey=""  headerValue="%{getText('system.select')}" list="cmbServiceList" name="service_type1">
              							<s:param name="onchange">saveOnChange('InboundInforeq_saveOnChange', {callID: '<s:property value="callID"/>', service_type1:this.value, detailed_service_type1:''})</s:param>
              							<s:param name="disabled" value="'true'.equals(status)"/>
              						</s:select>
		               	    </td>
		               		<td style="text-align: right;" width=50%>
								<s:select accesskey="register" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbServiceDetailList1" name="detailed_service_type1">
									<s:param name="onchange">saveOnChange('InboundInforeq_saveOnChange', {callID: '<s:property value="callID"/>', detailed_service_type1:this.value})</s:param>
									<s:param name="disabled" value="'true'.equals(status)"/>
								</s:select>
							</td>
              			</tr>

						<tr class="ir2<s:if test="service_type2 == null"> ui-select-hidden</s:if>" >
              					<td style="text-align: center;" width=50%>
              						<s:select accesskey="register" cssClass="idselect" headerKey=""  headerValue="%{getText('system.select')}" list="cmbServiceList" name="service_type2">
              							<s:param name="onchange">saveOnChange('InboundInforeq_saveOnChange', {callID: '<s:property value="callID"/>', service_type2:this.value, detailed_service_type2:''})</s:param>
              							<s:param name="disabled" value="'true'.equals(status)"/>
              						</s:select>
		               	    </td>
		               		<td style="text-align: right;" width=50%>
								<s:select accesskey="register" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbServiceDetailList2" name="detailed_service_type2">
									<s:param name="onchange">saveOnChange('InboundInforeq_saveOnChange', {callID: '<s:property value="callID"/>', detailed_service_type2:this.value})</s:param>
									<s:param name="disabled" value="'true'.equals(status)"/>
								</s:select>
							</td>
              			</tr>

						<tr class="ir3<s:if test="service_type3 == null"> ui-select-hidden</s:if>" >
              					<td style="text-align: center;" width=50%>
              						<s:select accesskey="register" cssClass="idselect" headerKey=""  headerValue="%{getText('system.select')}" list="cmbServiceList" name="service_type3">
              							<s:param name="onchange">saveOnChange('InboundInforeq_saveOnChange', {callID: '<s:property value="callID"/>', service_type3:this.value, detailed_service_type3:''})</s:param>
              							<s:param name="disabled" value="'true'.equals(status)"/>
              						</s:select>
		               	    </td>
		               		<td style="text-align: right;" width=50%>
								<s:select accesskey="register" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbServiceDetailList3" name="detailed_service_type3">
									<s:param name="onchange">saveOnChange('InboundInforeq_saveOnChange', {callID: '<s:property value="callID"/>', detailed_service_type3:this.value})</s:param>
									<s:param name="disabled" value="'true'.equals(status)"/>
								</s:select>
							</td>
              			</tr>

						<tr class="ir4<s:if test="service_type4 == null"> ui-select-hidden</s:if>" >
              					<td style="text-align: center;" width=50%>
              						<s:select accesskey="register" cssClass="idselect" headerKey=""  headerValue="%{getText('system.select')}" list="cmbServiceList" name="service_type4">
              							<s:param name="onchange">saveOnChange('InboundInforeq_saveOnChange', {callID: '<s:property value="callID"/>', service_type4:this.value, detailed_service_type4:''})</s:param>
              							<s:param name="disabled" value="'true'.equals(status)"/>
              						</s:select>
		               	    </td>
		               		<td style="text-align: right;" width=50%>
								<s:select accesskey="register" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbServiceDetailList4" name="detailed_service_type4">
									<s:param name="onchange">saveOnChange('InboundInforeq_saveOnChange', {callID: '<s:property value="callID"/>', detailed_service_type4:this.value})</s:param>
									<s:param name="disabled" value="'true'.equals(status)"/>
								</s:select>
							</td>
              			</tr>

						<tr class="ir5<s:if test="service_type5 == null"> ui-select-hidden</s:if>" >
              					<td style="text-align: center;" width=50%>
              						<s:select accesskey="register" cssClass="idselect" headerKey=""  headerValue="%{getText('system.select')}" list="cmbServiceList" name="service_type5">
              							<s:param name="onchange">saveOnChange('InboundInforeq_saveOnChange', {callID: '<s:property value="callID"/>', service_type5:this.value, detailed_service_type5:''})</s:param>
              							<s:param name="disabled" value="'true'.equals(status)"/>
              						</s:select>
		               	    </td>
		               		<td style="text-align: right;" width=50%>
								<s:select accesskey="register" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbServiceDetailList5" name="detailed_service_type5">
									<s:param name="onchange">saveOnChange('InboundInforeq_saveOnChange', {callID: '<s:property value="callID"/>', detailed_service_type5:this.value})</s:param>
									<s:param name="disabled" value="'true'.equals(status)"/>
								</s:select>
							</td>
              			</tr>
					</table>
				</td>
			</tr>
			<s:if test="status != 'true'">
			 <tr>
				<td colspan="2" align="right">
					<img src='Images/plus.png' name="btnAdd1" onclick="addComboSet('inforeq_tbl');return false;" style="cursor:pointer"/>
					<img src='Images/minus.png' onclick="removeComboSet('inforeq_tbl'); return false;" style="cursor:pointer"/>
				</td>
			</tr>
			</s:if>
			<tr>
				<td><s:label key="InboundInforeq.lblStaticInformation_comment" /></td>
			</tr>
			<tr>
				<td colspan="2">		
					<s:textarea cols="500" id="txtArea1" accesskey="register" cssStyle="resize: none; overflow: auto; width:425px; height:100px;" name="call_comment" cssClass="idfield txtArea1">
						<s:param name="disabled" value="'true'.equals(status)"/>
						<s:param name="onkeyup">saveOnChange('InboundInforeq_saveOnChange', {callID: '<s:property value="callID"/>', call_comment:this.value})</s:param>
					</s:textarea>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">
					<s:label key="InboundInforeq.lblStaticCall_name" />:
				</td>
				<td>
     	    		<s:textfield accesskey="register" name="call_name" cssClass="idfield" maxlength="100">
     	    			<s:param name="disabled" value="'true'.equals(status)"/>
     	    			<s:param name="onchange">saveOnChange('InboundInforeq_saveOnChange', {callID: '<s:property value="callID"/>', call_name:this.value})</s:param>
     	    		</s:textfield>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">
					<s:label key="InboundInforeq.lblStaticInformation_result_id" />:
				</td>
				<td>
					<s:select accesskey="register" name="call_result_id" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbResultList">
						<s:param name="disabled" value="'true'.equals(status)"/>
						<s:param name="onchange">var self = this; saveOnChange('InboundInforeq_saveOnChange', {callID: '<s:property value="callID"/>', call_result_id:this.value}, function(result) { changeDeadline(self.form.deadline, result); })</s:param>
					</s:select>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">
					<s:label key="InboundInforeq.lblStaticInformation_rate_id" />:
				</td>
				<td>
					<s:select accesskey="register" name="call_rate_id" cssClass="idselect" headerKey="" headerValue="%{getText('system.rateselect')}" list="cmbRateList" >
						<s:param name="disabled" value="'true'.equals(status)"/>
						<s:param name="onchange">saveOnChange('InboundInforeq_saveOnChange', {callID: '<s:property value="callID"/>', call_rate_id:this.value})</s:param>
					</s:select>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">
					<s:label key="InboundInforeq.lblStaticDeadline" />:
				</td>
				<td>
					<s:textfield accesskey="register" name="deadline" cssClass="idfield date" maxlength="10">
						<s:param name="disabled" value="'true'.equals(status) || 1 != fresult"/>
						<s:param name="onchange">saveOnChange('InboundInforeq_saveOnChange', {callID: '<s:property value="callID"/>', deadline:this.value})</s:param>
					</s:textfield>
				</td>
			</tr>
		</table>
		</fieldset>
</s:form>