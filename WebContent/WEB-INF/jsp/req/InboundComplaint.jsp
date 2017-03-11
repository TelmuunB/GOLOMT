<!--
* Моделийн нэр             InboundComplaint.jsp
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
			comboAdder('incomplaint_tbl', 'ir','service_type','detailed_service_type', 6, 4);
			
					$("#txtArea3").bind("keyup", function(){
					$("#txtArea3").css({"border-color":""});
					maxlength = $(this).attr("cols");
					if($(this).val().length > maxlength){
						value = $(this).val().slice(0,maxlength); 
						$(this).val(value);
						
						$("#txtArea3").css({"border-color":"red"});
					}
					});
			$("[name=call_result_id]").change(function(){
				$.ajax({
			        	type: "POST",
			      		url: "CallReqType_findMig.action",
			         	data: {"call_result_idg":$(this).val()},
			        	dataType: 'json',
			         	success: function(result)
			            	    {
			            	       if(result.a_Result_mig=="3"){
			            	       		$("[name=transfer_check_btn]").removeAttr("disabled");
			            	       		$("[name=transfer_check_btn]").css({"display":"block"});
			            	       }else{
			            	       		$("[name=transfer_check_btn]").attr("disabled","disabled");
			            	       		$("[name=transfer_check_btn]").css({"display":"none"});
			            	       }
			        		    }
			    });
			});
			
  	});  	
  	
</script>
 <s:form action="InboundComplaint" cssClass="form">
     <fieldset><legend><s:property value="getText('InboundComplaint.grpCall_complaint')"/></legend>
               <table width=100% cellpadding="2">
	              	 <tr>
	              		 <td style="color:blue; text-align: center;" width=50%>
	               			<s:label key="InboundComplaint.lblStaticComplaint_id"/>
	               		 </td>
	               		 <td style="color:blue; text-align: center;">
	               			<s:label key="InboundComplaint.lblStaticComplaint_type_id"/>
	  					 </td>
	        		</tr>
					<tr>
						<td colspan="2">
							<table width="100%" id="incomplaint_tbl">
								<tr class="ir1" >
		              					<td style="text-align: center;" width=50%>
		              						<s:select accesskey="register" cssClass="idselect" headerKey=""  headerValue="%{getText('system.select')}" list="cmbServiceList" name="service_type1">
		              							<s:param name="onchange">saveOnChange('InboundComplaint_saveOnChange', {callID: '<s:property value="callID"/>', service_type1:this.value, detailed_service_type1:''})</s:param>
		              							<s:param name="disabled" value="'true'.equals(status)"/>
		              						</s:select>
				               	    </td>
				               		<td style="text-align: right;" width=50%>
										<s:select accesskey="register" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbServiceDetailList1" name="detailed_service_type1">
											<s:param name="onchange">saveOnChange('InboundComplaint_saveOnChange', {callID: '<s:property value="callID"/>', detailed_service_type1:this.value})</s:param>
											<s:param name="disabled" value="'true'.equals(status)"/>
										</s:select>
									</td>
		              			</tr>

								<tr class="ir2<s:if test="service_type2 == null"> ui-select-hidden</s:if>" >
		              					<td style="text-align: center;" width=50%>
		              						<s:select accesskey="register" cssClass="idselect" headerKey=""  headerValue="%{getText('system.select')}" list="cmbServiceList" name="service_type2">
		              							<s:param name="onchange">saveOnChange('InboundComplaint_saveOnChange', {callID: '<s:property value="callID"/>', service_type2:this.value, detailed_service_type2:''})</s:param>
		              							<s:param name="disabled" value="'true'.equals(status)"/>
		              						</s:select>
				               	    </td>
				               		<td style="text-align: right;" width=50%>
										<s:select accesskey="register" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbServiceDetailList2" name="detailed_service_type2">
											<s:param name="onchange">saveOnChange('InboundComplaint_saveOnChange', {callID: '<s:property value="callID"/>', detailed_service_type2:this.value})</s:param>
											<s:param name="disabled" value="'true'.equals(status)"/>
										</s:select>
									</td>
		              			</tr>

								<tr class="ir3<s:if test="service_type3 == null"> ui-select-hidden</s:if>" >
		              					<td style="text-align: center;" width=50%>
		              						<s:select accesskey="register" cssClass="idselect" headerKey=""  headerValue="%{getText('system.select')}" list="cmbServiceList" name="service_type3">
		              							<s:param name="onchange">saveOnChange('InboundComplaint_saveOnChange', {callID: '<s:property value="callID"/>', service_type3:this.value, detailed_service_type3:''})</s:param>
		              							<s:param name="disabled" value="'true'.equals(status)"/>
		              						</s:select>
				               	    </td>
				               		<td style="text-align: right;" width=50%>
										<s:select accesskey="register" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbServiceDetailList3" name="detailed_service_type3">
											<s:param name="onchange">saveOnChange('InboundComplaint_saveOnChange', {callID: '<s:property value="callID"/>', detailed_service_type3:this.value})</s:param>
											<s:param name="disabled" value="'true'.equals(status)"/>
										</s:select>
									</td>
		              			</tr>
								<tr class="ir4<s:if test="service_type4 == null"> ui-select-hidden</s:if>" >
		              					<td style="text-align: center;" width=50%>
		              						<s:select accesskey="register" cssClass="idselect" headerKey=""  headerValue="%{getText('system.select')}" list="cmbServiceList" name="service_type4">
		              							<s:param name="onchange">saveOnChange('InboundComplaint_saveOnChange', {callID: '<s:property value="callID"/>', service_type4:this.value, detailed_service_type4:''})</s:param>
		              							<s:param name="disabled" value="'true'.equals(status)"/>
		              						</s:select>
				               	    </td>
				               		<td style="text-align: right;" width=50%>
										<s:select accesskey="register" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbServiceDetailList4" name="detailed_service_type4">
											<s:param name="onchange">saveOnChange('InboundComplaint_saveOnChange', {callID: '<s:property value="callID"/>', detailed_service_type4:this.value})</s:param>
											<s:param name="disabled" value="'true'.equals(status)"/>
										</s:select>
									</td>
		              			</tr>

								<tr class="ir5<s:if test="service_type5 == null"> ui-select-hidden</s:if>" >
		              					<td style="text-align: center;" width=50%>
		              						<s:select accesskey="register" cssClass="idselect" headerKey=""  headerValue="%{getText('system.select')}" list="cmbServiceList" name="service_type5">
		              							<s:param name="onchange">saveOnChange('InboundComplaint_saveOnChange', {callID: '<s:property value="callID"/>', service_type5:this.value, detailed_service_type5:''})</s:param>
		              							<s:param name="disabled" value="'true'.equals(status)"/>
		              						</s:select>
				               	    </td>
				               		<td style="text-align: right;" width=50%>
										<s:select accesskey="register" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbServiceDetailList5" name="detailed_service_type5">
											<s:param name="onchange">saveOnChange('InboundComplaint_saveOnChange', {callID: '<s:property value="callID"/>', detailed_service_type5:this.value})</s:param>
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
							<img src='Images/plus.png' name="btnAdd1" onclick="addComboSet('incomplaint_tbl');return false;" style="pointer:default"/>
							<img src='Images/minus.png' onclick="removeComboSet('incomplaint_tbl'); return false;" style="pointer:default"/>
						</td>
					</tr>
					</s:if>
	              	<tr>
	            	 	  <td>
	              	 			<s:label key="InboundComplaint.lblStaticComplaint_comment"/>
	               	      </td>
	             	 </tr>
	              	<tr>
	              			<td colspan="2">
								<s:textarea cols="500" id="txtArea3" accesskey="register" cssStyle="resize: none; overflow: auto; width: 425px; height:100px;" name="call_comment" cssClass="idfield txtArea3">
									<s:param name="disabled" value="'true'.equals(status)"/>
									<s:param name="onkeyup">saveOnChange('InboundComplaint_saveOnChange', {callID: '<s:property value="callID"/>', call_comment:this.value})</s:param>
								</s:textarea>
	             	 		</td>
	             	</tr>
	                <tr>
	                		<td style="text-align:right">
	                		 	<s:label key="InboundComplaint.lblStaticUnit_id"/>:
	                		</td>
	                		<td>
	                			<s:hidden name="unit_id"/>

	                			<s:textfield accesskey="register" name="unit_name" cssClass="idfield unit_auto">
									<s:param name="disabled" value="'true'.equals(status)"/>
									<s:param name="onchange">saveOnChange('InboundComplaint_saveOnChange', {callID: '<s:property value="callID"/>', unit_id:this.form.unit_id.value})</s:param>
								</s:textfield>
	                		</td>
	                </tr>

	       			<tr>
	       					<td style="text-align:right">
	       						<s:label key="InboundComplaint.lblStaticComplaints_sort_id"/>:
	       					</td>
	       					<td>
	       						<s:select accesskey="register" name="call_sort_id" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbInboundComplaintSortList">
									<s:param name="disabled" value="'true'.equals(status)"/>
									<s:param name="onchange">saveOnChange('InboundComplaint_saveOnChange', {callID: '<s:property value="callID"/>', call_sort_id:this.value})</s:param>
								</s:select>
	       					</td>
	       			</tr>
	       			<tr>
	       					<td style="text-align:right">
	       						<s:label key="InboundComplaint.lblStaticCall_name"/>:
	       					</td>
	       					<td>
	               	    		<s:textfield accesskey="register" name="call_name" cssClass="idfield">
	               	    			<s:param name="disabled" value="'true'.equals(status)"/>
	               	    			<s:param name="onchange">saveOnChange('InboundComplaint_saveOnChange', {callID: '<s:property value="callID"/>', call_name:this.value})</s:param>
	               	    		</s:textfield>
	       					</td>
	       			</tr>
	       			<tr>
	       					<td style="text-align:right">
	       						 <s:label key="InboundComplaint.lblStaticComplaint_result_id"/>:
	       					</td>
	       					<td>
								<s:select accesskey="register" name="call_result_id" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbInboundComplaintResultList">
									<s:param name="disabled" value="'true'.equals(status) || 'true'.equals(transfer_check)"/>
									<s:param name="onchange">var self = this; saveOnChange('InboundComplaint_saveOnChange', {callID: '<s:property value="callID"/>', call_result_id:this.value})</s:param>
								</s:select>
	                		</td>
	       			</tr>
	       			<tr>
	       					<td style="text-align:right">
	       						<s:label key="InboundComplaint.lblStaticComplaint_rate_id"/>:
	       					</td>
	       					<td>
								<s:select accesskey="register" name="call_rate_id" cssClass="idselect" headerKey="" headerValue="%{getText('system.rateselect')}" list="cmbInboundComplaintRateList" >
									<s:param name="disabled" value="'true'.equals(status)"/>
									<s:param name="onchange">saveOnChange('InboundComplaint_saveOnChange', {callID: '<s:property value="callID"/>', call_rate_id:this.value})</s:param>
								</s:select>
	       					</td>
	       			</tr>
	       			<tr>
	       					<td style="text-align:right">
	       						<s:label key="InboundComplaint.lblStaticDeadline"/>:
	       					</td>
	       					<td>
								<s:textfield accesskey="register" name="deadline" cssClass="idfield date" maxlength="10">
									<s:param name="disabled" value="'true'.equals(status)"/>
									<s:param name="onchange">saveOnChange('InboundComplaint_saveOnChange', {callID: '<s:property value="callID"/>', deadline:this.value})</s:param>
								</s:textfield>
	       					</td>
	       			</tr>
	       			<tr>
	       					<td colspan="2" style="text-align:right">
	       					      <s:if test="'true'.equals(transfer_check)">
	       					      		<s:label key="InboundComplaint.complaint_transferred" cssStyle="color:red;font-weight:bold"></s:label>
	       					      </s:if>

       							  <s:if test="status != 'true'">
       							  <s:hidden name="callID"/>
	       					      <s:submit name="transfer_check_btn" key="InboundComplaint.chkComplaint_transfer" cssClass="button" cssStyle="width:350px;" disabled="3 != fresult" onclick="var self = this; saveOnChange('InboundComplaint_saveOnChange', {callID: self.form.callID.value, transfer_check:true}, function(){ complaintCheck(self.form.call_result_id);  });return false;">
	       					      </s:submit>
	       					      </s:if>				            					      
	       					</td>
	       			</tr>
					
               </table>

			</fieldset>
</s:form>