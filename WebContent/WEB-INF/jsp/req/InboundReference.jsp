<!--
* Моделийн нэр             InboundReference.jsp
*
* Функцын нэр          【Дуудлагын төрөл Лавлагаа хүссэн】
* Тvvх
* Хувилбарын №  Огноо    Хариуцагч    Агуулга
* 01.00.00                 Огноо    Хариуцагч    Шинээр хийсэн/Зассан
* 01.00.01				2013.03.01	Б.Тэмүүлэн		Шинээр үүсгэх
* 02.00.01				2013.03.01	А.Инкар 		Зассан
*
*-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@  taglib  prefix="sj"  uri="/struts-jquery-tags"%>
		<!--<link rel="stylesheet" href="struts/css/combined.css" type="text/css" />-->
		<!--<link rel="stylesheet" href="css/box.css" type="text/css"/>-->
		<!--<link rel="stylesheet" href="css/insert-form.css" type="text/css"/>-->
		<style>
			.ui-state-highlight .ui-icon {background-image: url(images/ui-icons_2e83ff_256x240.png); }
			.ui-icon { width: 16px; height: 16px; background-image: url(images/ui-icons_222222_256x240.png); }
			.ui-icon-mail-closed { background-position: -80px -96px; }
		</style>

		<script>

$(document).ready(function(){
			comboAdder('reference_tbl', 'ir','service_type','detailed_service_type', 6, 1);
			
			
						$("#txtArea").bind("keyup", function(){
						$("#txtArea").css({"border-color":""});
						maxlength = $(this).attr("cols");
						if($(this).val().length > maxlength){
						value = $(this).val().slice(0,maxlength); 
						$(this).val(value);
						
						$("#txtArea").css({"border-color":"red"});
							}
						});
});

</script>
		<s:form action="InboundReference" cssClass="form">

			<fieldset>
				<legend class="InboundReferenceLegend">
					<s:property	value="getText('InboundReference.grpCall_reference')" />
				</legend>
				<table width="100%" cellpadding="2">
					<tr>
						<td style="color:blue; text-align: center;" width=50%>
						    <s:label key="InboundReference.lblStaticReference_id"/>
						</td>
						<td style="color:blue; text-align: center;" width=50%>
						    <s:label key="InboundReference.lblStaticReference_type_id"/>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<table width="100%" id="reference_tbl">
								<tr class="ir1" >
	               					<td style="text-align: center;" width=50%>
	               						<s:select accesskey="register" cssClass="idselect" headerKey=""  headerValue="%{getText('system.select')}" list="cmbServiceList" name="service_type1">
	               							<s:param name="onchange">saveOnChange('InboundReference_saveOnChange', {callID: '<s:property value="callID"/>', service_type1:this.value, detailed_service_type1:''})</s:param>
	               							<s:param name="disabled" value="'true'.equals(status)"/>
	               						</s:select>
				               	    </td>
				               		<td style="text-align: right;" width=50%>
										<s:select accesskey="register" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbServiceDetailList1" name="detailed_service_type1">
											<s:param name="onchange">saveOnChange('InboundReference_saveOnChange', {callID: '<s:property value="callID"/>', detailed_service_type1:this.value})</s:param>
											<s:param name="disabled" value="'true'.equals(status)"/>
										</s:select>
									</td>
	               				</tr>

								<tr class="ir2<s:if test="service_type2 == null"> ui-select-hidden</s:if>" >
	               					<td style="text-align: center;" width=50%>
	               						<s:select accesskey="register" cssClass="idselect" headerKey=""  headerValue="%{getText('system.select')}" list="cmbServiceList" name="service_type2">
	               							<s:param name="onchange">saveOnChange('InboundReference_saveOnChange', {callID: '<s:property value="callID"/>', service_type2:this.value, detailed_service_type2:''})</s:param>
	               							<s:param name="disabled" value="'true'.equals(status)"/>
	               						</s:select>
				               	    </td>
				               		<td style="text-align: right;" width=50%>
										<s:select accesskey="register" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbServiceDetailList2" name="detailed_service_type2">
											<s:param name="onchange">saveOnChange('InboundReference_saveOnChange', {callID: '<s:property value="callID"/>', detailed_service_type2:this.value})</s:param>
											<s:param name="disabled" value="'true'.equals(status)"/>
										</s:select>
									</td>
	               				</tr>

								<tr class="ir3<s:if test="service_type3 == null"> ui-select-hidden</s:if>" >
	               					<td style="text-align: center;" width=50%>
	               						<s:select accesskey="register" cssClass="idselect" headerKey=""  headerValue="%{getText('system.select')}" list="cmbServiceList" name="service_type3">
	               							<s:param name="onchange">saveOnChange('InboundReference_saveOnChange', {callID: '<s:property value="callID"/>', service_type3:this.value, detailed_service_type3:''})</s:param>
	               							<s:param name="disabled" value="'true'.equals(status)"/>
	               						</s:select>
				               	    </td>
				               		<td style="text-align: right;" width=50%>
										<s:select accesskey="register" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbServiceDetailList3" name="detailed_service_type3">
											<s:param name="onchange">saveOnChange('InboundReference_saveOnChange', {callID: '<s:property value="callID"/>', detailed_service_type3:this.value})</s:param>
											<s:param name="disabled" value="'true'.equals(status)"/>
										</s:select>
									</td>
	               				</tr>

								<tr class="ir4<s:if test="service_type4 == null"> ui-select-hidden</s:if>" >
	               					<td style="text-align: center;" width=50%>
	               						<s:select accesskey="register" cssClass="idselect" headerKey=""  headerValue="%{getText('system.select')}" list="cmbServiceList" name="service_type4">
	               							<s:param name="onchange">saveOnChange('InboundReference_saveOnChange', {callID: '<s:property value="callID"/>', service_type4:this.value, detailed_service_type4:''})</s:param>
	               							<s:param name="disabled" value="'true'.equals(status)"/>
	               						</s:select>
				               	    </td>
				               		<td style="text-align: right;" width=50%>
										<s:select accesskey="register" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbServiceDetailList4" name="detailed_service_type4">
											<s:param name="onchange">saveOnChange('InboundReference_saveOnChange', {callID: '<s:property value="callID"/>', detailed_service_type4:this.value})</s:param>
											<s:param name="disabled" value="'true'.equals(status)"/>
										</s:select>
									</td>
	               				</tr>

								<tr class="ir5<s:if test="service_type5 == null"> ui-select-hidden</s:if>" >
	               					<td style="text-align: center;" width=50%>
	               						<s:select accesskey="register" cssClass="idselect" headerKey=""  headerValue="%{getText('system.select')}" list="cmbServiceList" name="service_type5">
	               							<s:param name="onchange">saveOnChange('InboundReference_saveOnChange', {callID: '<s:property value="callID"/>', service_type5:this.value, detailed_service_type5:''})</s:param>
	               							<s:param name="disabled" value="'true'.equals(status)"/>
	               						</s:select>
				               	    </td>
				               		<td style="text-align: right;" width=50%>
										<s:select accesskey="register" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbServiceDetailList5" name="detailed_service_type5">
											<s:param name="onchange">saveOnChange('InboundReference_saveOnChange', {callID: '<s:property value="callID"/>', detailed_service_type5:this.value})</s:param>
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
							<img src='Images/plus.png' name="btnAdd1" onclick="addComboSet('reference_tbl');return false;" style="cursor:pointer"/>
							<img src='Images/minus.png' onclick="removeComboSet('reference_tbl'); return false;" style="cursor:pointer"/>
						</td>
					</tr>
					</s:if>
					<tr>
						<td>
							<s:property value="getText('InboundReference.txtReference_comment')"/>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<s:textarea accesskey="register" cols="500" id="txtArea" cssStyle="resize: none; overflow: auto; width:425px; height:100px;" name="call_comment" cssClass="idfield" maxlength="500">
								<s:param name="disabled" value="'true'.equals(status)"/>
								<s:param name="onkeyup">saveOnChange('InboundReference_saveOnChange', {callID: '<s:property value="callID"/>', call_comment:this.value})</s:param>
							</s:textarea>
						</td>
					</tr>
					<tr>
                      	<td style="text-align: right;">
                      		<s:label key="InboundReference.lblStaticCall_name"/>:
                        </td>
                      	<td>
               	    		<s:textfield accesskey="register" name="call_name" cssClass="idfield" maxlength="100">
               	    			<s:param name="disabled" value="'true'.equals(status)"/>
               	    			<s:param name="onchange">saveOnChange('InboundReference_saveOnChange', {callID: '<s:property value="callID"/>', call_name:this.value})</s:param>
               	    		</s:textfield>
                      	</td>
                    </tr>
					<tr>
						<td style="text-align: right;">
							<s:property value="getText('InboundReference.lblStaticReference_result_id')"/>:
						</td>
						<td>
							<s:select accesskey="register" name="call_result_id" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbResultList">
								<s:param name="disabled" value="'true'.equals(status)"/>
								<s:param name="onchange">var self = this; saveOnChange('InboundReference_saveOnChange', {callID: '<s:property value="callID"/>', call_result_id:this.value}, function(result) { changeDeadline(self.form.deadline, result); })</s:param>
							</s:select>
						</td>
					</tr>
					<tr>
						 <td style="text-align: right;">
						 	<s:property value="getText('InboundReference.cmbReference_rate_id')" />:</td>
						<td>
							<s:select accesskey="register" name="call_rate_id" cssClass="idselect" headerKey="" headerValue="%{getText('system.rateselect')}" list="cmbRateList" >
								<s:param name="disabled" value="'true'.equals(status)"/>
								<s:param name="onchange">saveOnChange('InboundReference_saveOnChange', {callID: '<s:property value="callID"/>', call_rate_id:this.value})</s:param>
							</s:select>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;">
							<s:property value="getText('InboundReference.txtDeadline')" />:</td>
						<td>
							<s:textfield accesskey="register" name="deadline" cssClass="idfield date" maxlength="10">
								<s:param name="disabled" value="'true'.equals(status) || 1 != fresult"/>
								<s:param name="onchange">saveOnChange('InboundReference_saveOnChange', {callID: '<s:property value="callID"/>', deadline:this.value})</s:param>
							</s:textfield>
						</td>
					</tr>
				</table>
			</fieldset>
			 </s:form>
