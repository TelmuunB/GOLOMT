<!--
* „M„Ђ„t„u„|„y„z„~ „~„Џ„‚             RegisterOtherComplaint.jsp
*
* „U„…„~„{„€„Ќ„~ „~„Џ„‚          ЃyБусад гомдлын бүртгэлЃz
* „Svv„‡
* „V„…„r„y„|„q„p„‚„Ќ„~ ‡‚  „O„s„~„Ђ„Ђ    „V„p„‚„y„…„€„p„s„‰    „@„s„…„…„|„s„p
* 01.00.00                 „O„s„~„Ђ„Ђ    „V„p„‚„y„…„€„p„s„‰    „Y„y„~„Џ„Џ„‚ „‡„y„z„ѓ„Џ„~/„H„p„ѓ„ѓ„p„~
*-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script>
	 $(document).ready(function(){  
	 $("input[name=txtPhone_number]").focus();				
							
				digits();
				texts();
			
		 $("button[name=btnClear]").click(function() {
			  $("input[name=txtPhone_number]").val("");
			  $("input[name=txtCaller_name]").val("");
			  $("select[name=cmbComplaint_id]").val('');
			  $("select[name=cmbComplaint_type_id]").val('');
			  $("select[name=cmbUnit_id]").val('');
			  $("select[name=cmbComplaint_sort_id]").val('');
			  $("select[name=cmbComplaint_rate_id]").val('');
			  $("input[name=txtEmail]").val("");
			  $("select[name=cmbSource]").val('');
			  $("textarea[name=txtComplaint_comment]").html("");
			  return false;
			});
			
				$("select[name=cmbComplaint_id]").change(function (){
	   				$.ajax(
			          {
			        	type: "POST",
			      		url: "ComboAjax.action",
			         	data: {"comboName":$(this).val(),"callReq":'4'},
			        	dataType: 'json',
			         	success: function(result)
			            	    {
			            	       var html ="";
			            	       array = result["finalReturn"];
								   html+="<option value=''><s:text name='system.select'/></option>";
						           for(var key in array){
										html+='<option value="'+ key +'">'+ array[key] +'</option>';
						           }
						           $("select[name=cmbComplaint_type_id]").html(html);
			        		    }
			           });
			   	});

					$("#txtComplaint_comment").bind("keyup", function(){
					$("#txtComplaint_comment").css({"border-color":""});
					maxlength = $(this).attr("cols");
					if($(this).val().length > maxlength){
						value = $(this).val().slice(0,maxlength); 
						$(this).val(value);
						
						$("#txtComplaint_comment").css({"border-color":"red"});
							}
						});
			
				

			});
			function checkNullOtherComplaintData(){
					var validatorFlag="true";
					var datas=new Array();
					var tmp=new Array();
					datas=$("#RegisterOtherComplaint").find("input[accesskey=register]");
					tmp=$("#RegisterOtherComplaint").find("select[accesskey=register]");
					for(var i=0;i<tmp.length;i++){
						datas.push(tmp[i]);
					}
					
					for(var z=0;z<datas.length;z++){
						
						if($(datas[z]).attr("tagName")=="SELECT"){
							if($(datas[z]).val()==""){
								if($(datas[z]).next("span").attr("tagName")!="SPAN"){
								$(datas[z]).parent().append("<span style='color:red'>*</span>");
								}
							}else{
							$(datas[z]).next("span").remove();
							}
						} 
						if($(datas[z]).attr("name")=='cmbUnit_id'){
						normalGlow(datas[z]);
						}
						else if($(datas[z]).val()==""){
							if($(datas[z]).attr("name")=='txtPhone_number'){
								if($('#RegisterOtherComplaint').find('[name=txtEmail]').val()!=""){
									normalGlow(datas[z]);
								}else{
									redGlow(datas[z]);
									validatorFlag="false";	
								}
							}else if($(datas[z]).attr("name")=='txtEmail'){
								if($('#RegisterOtherComplaint').find('[name=txtPhone_number]').val()!=""){
									normalGlow(datas[z]);
								}else{
									redGlow(datas[z]);
									validatorFlag="false";	
								}
							}else{
							validatorFlag="false";
							setMessage('<span style="color:red"><s:text name="RegisterOtherComplaint.errData_null"/></span>');
							setTitle(datas[z],'<s:text name="RegisterOtherComplaint.errData_null"/>');
							redGlow(datas[z]);
							}
						}else if($(datas[z]).val()==" "){
							validatorFlag="false";
							setMessage('<span style="color:red"><s:text name="RegisterOtherComplaint.errData_null"/></span>');
							setTitle(datas[z],'<s:text name="RegisterOtherComplaint.errData_null"/>');
							redGlow(datas[z]);
						}else if($(datas[z]).attr("name")=="txtEmail"){
							if(validateEmail($(datas[z]).val())){
								normalGlow(datas[z]);
							}else{
								validatorFlag="false";
								setTitle(datas[z],'<s:text name="RegisterOtherComplaint.errWrong_email"/>');
								redGlow(datas[z]);
							}
						}
						else if($(datas[z]).attr("name")=="txtPhone_number"){
							if($(datas[z]).val().length<6){
								validatorFlag="false";
								setTitle(datas[z],'<s:text name="RegisterOtherComplaint.errWrong_phone"/>');
								redGlow(datas[z]);
							}else{
								setTitle(datas[z],'');
								normalGlow(datas[z]);
							}
						}
						else{
							normalGlow(datas[z]);
						}
					}
					if(validatorFlag=="true"){
						return true;
					}else{
						setMessage('<span style="color:red"><s:text name="RegisterOtherComplaint.errData_null"/></span>');
						return false;
					}
				}
	function setMessage(str){
		$("#message_label").html(str);
		setTimeout(function(){$("#message_label").html("")},3000);
	}
	function setTitle(cad,msg){
		$(cad).css({"border-color":"red"});
		$(cad).attr("title",msg);
	}
	function redGlow(cad){
		$(cad).css({"border":"1px solid red"});
	}
	function greenGlow(cad){
		$(cad).css({"border-color":"lightgreen"});
	}
	function normalGlow(cad){
		$(cad).css({"border-color":""});
		$(cad).attr("title","");
	}
	function validateEmail(email) { 
	    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		return filter.test(email);
	}
</script>

<script type="text/javascript">
	function saveOtherComplaint() {
		   if(!checkNullOtherComplaintData()){
		   		return false;
		   }
		   $.ajax({
						      type: "POST",
						      url: "RegisterOtherComplaint_onBtnAddClick.action",
						      data: get_data("RegisterOtherComplaint"),

						      success: function(result) {
								//alert(result.insert_message);
						      	document.getElementById("RegisterOtherComplaint").reset() ;
						      	$("#message_label").html('<span style="color:green"><s:text name="system.insert_success"/></span>');
						      }
			  });
			
			
	  }
</script>
<s:form action="RegisterOtherComplaint" id="RegisterOtherComplaint" >
            <fieldset>
               		<legend><s:property value="getText('RegisterOtherComplaint.grpOther_complaint_registration')"/></legend>
				<table cellpadding="2" width="100%">
					<tr>
						<td style="text-align: right;">
						    <s:label key="RegisterOtherComplaint.lblStaticPhone_number"/>:
						</td>
						<td>
						    <s:textfield accesskey="register" name="txtPhone_number" maxlength = "15" cssClass="idfield digits"/>
						</td>
						<td style="text-align: right;">
						    <s:label key="RegisterOtherComplaint.lblStaticCaller_name"/>:
						</td>
						<td>
						    <s:textfield accesskey="register" name="txtCaller_name" maxlength = "100" cssClass="idfield texts"/>
						</td>
					</tr>

					<tr>
					    <td style="text-align: right;">
					        <s:label key="RegisterOtherComplaint.lblStaticComplaint_id"/>:
					    </td>
					    <td>
					        <s:select accesskey="register" name="cmbComplaint_id" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbServiceList"/>
					    </td>
					    <td style="text-align: right;">
					        <s:label key="RegisterOtherComplaint.lblStaticComplaint_type_id"/>:
					    </td>
					    <td>
					        <s:select accesskey="register" name="cmbComplaint_type_id" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="%{#{}}"/>
					    </td>
					</tr>
					<tr>
					    <td style="text-align: right;">
					        <s:label key="RegisterOtherComplaint.lblStaticUnit_id"/>:
					    </td>
					    <td>
					    	<s:hidden name="unit_id"/>
					    	
					        <s:textfield accesskey="register" name="cmbUnit_id" cssClass="idfield unit_auto"/>
					    </td>
					    <td style="text-align: right;">
					        <s:label key="RegisterOtherComplaint.lblStaticComplaint_sort_id"/>:
					    </td>
					    <td>
					        <s:select accesskey="register" name="cmbComplaint_sort_id" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbInboundComplaintSortList"/>
					    </td>
					</tr>
					<tr>
					    <td style="text-align: right;">
					        <s:label key="RegisterOtherComplaint.lblStaticComplaint_rate_id"/>:
					    </td>
					    <td>
					        <s:select accesskey="register" name="cmbComplaint_rate_id" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbInboundComplaintRateList"/>
					    </td>
					    <td style="text-align: right;">
						    <s:label key="RegisterOtherComplaint.lblStaticSource"/>:
						</td>
						<td>
						    <s:select accesskey="register" name="cmbSource" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbSourceList"/>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;">
							<s:label key="RegisterOtherComplaint.lblStaticEmail"/>:
						</td>
						<td>
							<s:textfield accesskey="register" name="txtEmail" maxlength = "100" cssClass="idfield"/>
						</td>
						
					</tr>
					<tr>
						<td>
							<s:label key="RegisterOtherComplaint.lblStaticComplaint_comment"/><br/>
					    </td>
					    
					</tr>
					<tr>
						<td colspan="4">
							<s:textarea cols="500" id="txtComplaint_comment" accesskey="register" cssStyle="width: 100%; height:100px;" name="txtComplaint_comment" cssClass="idfield"/>
						</td>
					</tr>
				</table>
				<div align="center">
					<s:submit name="btnSave" key="RegisterOtherComplaint.btnComplaint_register" cssClass="button" onclick="saveOtherComplaint();return false;"/>
               		<s:reset name="btnClear" cssClass="button" key="RegisterOtherComplaint.btnClear"/>
				</div>
				<div align="center">
					<label id="message_label"></label>
				</div>
				</fieldset>
</s:form>