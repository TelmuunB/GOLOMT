<!--
* Моделийн нэр            InsertTask.jsp
*
* Функцын нэр          【Даалгавар оруулах】
* Тvvх
* Хувилбарын №  Огноо    Хариуцагч    Агуулга
* 01.00.00                 Огноо    Хариуцагч    Шинээр хийсэн/Зассан
* 01.00.01				2013.03.11	Б.Номин-эрдэнэ		Шинээр үүсгэх
*-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	$(document).ready(function(){
		$("#InsertTask1").bind("keyup", function(){
					$("#InsertTask1").css({"border-color":""});
					maxlength = $(this).attr("cols");
					if($(this).val().length > maxlength){
						value = $(this).val().slice(0,maxlength); 
						$(this).val(value);
						
						$("#InsertTask1").css({"border-color":"red"});
			}
	});
		$.ajax(
	          { 
		       		type: "POST",
					url: "InsertTask_getData.action",
					dataType:'json',
					data:get_data("InsertTask"), 
					success: function(result)
					    {   
						    var array=result.returnData;
						    
						    for(key in array){
						    	switch (key){
						    		case "PHONE_NUMBER_ONE":
						    		$("input[name=txtPhone_number]").val(array[key]);
						    		break;
						    		case "CALLER_NAME":
						    		$("[name=txtCaller_name]").val(array[key]);
						    		break;
						    		case "TASK_NAME":
						    		$("[name=txtTask_name]").val(array[key]);
						    		break;
						    		case "AGENT_ID":
						    		$("[name=cmbAgent_id]").val(array[key]);
						    		break;
						    		case "DEADLINE":
						    		$("[name=txtDeadline]").val(array[key]);
						    		break;
						    		case "TASK_COMMENT":
						    		$("[name=txtTask_comment]").val(array[key]);	
						    		break;
						    	}
						    }
						}
						
	           });
		});
	$("input[name=txtPhone_number]").focus();
		digits();
		texts();

	function insertTask() {	
		if(!checkNullInsertTaskData()){
			return false;
		}
		 $.ajax(
	          { 
		       		type: "POST",
					url: "InsertTask_onBtnCreateTask",
					data: get_data("InsertTask"),			
					success: function(result)
					    {
						    jQuery("#grdTask_list").trigger("reloadGrid");
						    document.getElementById("InsertTask").reset();
						    $("label[id=system.success]").html('<s:text name="system.insert_success"/>');
					    }					  		
			  });

	}
	
	function edit() {
		if(!checkNullInsertTaskData()){
			return false;
		}
		$.ajax(
			{	
				type: "POST",
				url: "InsertTask_EditTask.action",
				data: get_data("InsertTask"),
				success: function(result)
				{
					    jQuery("#grdTask_list").trigger("reloadGrid");
					    document.getElementById("InsertTask").reset() ;
					    $("label[id=system.success]").html('<s:text name="system.insert_success"/>');
				
				}
	
		});
	}
	function checkNullInsertTaskData(){
					var validatorFlag="true";
					var datas=new Array();
					var tmp=new Array();
					datas=$("#InsertTask").find("input[accesskey=register]");
					tmp=$("#InsertTask").find("select[accesskey=register]");
					for(var i=0;i<tmp.length;i++){
						datas.push(tmp[i]);
					}
					
					for(var z=0;z<datas.length;z++){
						
						if($(datas[z]).val()==""){
							if($(datas[z]).attr("tagName")!="SELECT"){
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
		$(cad).css({"border-color":"red"});
	}
	function greenGlow(cad){
		$(cad).css({"border-color":"lightgreen"});
	}
	function normalGlow(cad){
		$(cad).css({"border-color":""});
		$(cad).attr("title","");
	}
		
</script>
           <s:form action="InsertTask" cssClass="form">
           	   <table width="100%" cellpadding="4" id=InboundTransfer>
               			<tr>
               				<td>
               					<s:label name="lblStaticPhone_number" value="%{getText('InsertTask.lblStaticPhone_number')}"/>:
               				</td>
               				<td>
               					<s:label name="lblStaticCaller_name" value="%{getText('InsertTask.lblStaticCaller_name')}"/>:
               				</td>
               				<td>
               					<s:label name="lblStaticTask_name" value="%{getText('InsertTask.lblStaticTask_name')}"/>:
               				</td>
               			</tr>
               			<tr>
               				<td>
               					<s:textfield accesskey="register" name="txtPhone_number" maxlength = "15" cssClass="idfield digits"/>
               				</td>
               				<td>
               					<s:textfield accesskey="register" name="txtCaller_name" maxlength = "50" cssClass="idfield texts"/>
               				</td>
               				<td>
               					<s:textfield accesskey="register" name="txtTask_name" maxlength="100" cssClass="idfield"/>
               				</td>
               			</tr>
               			<tr>
               				<td>
               					<s:label name="lblStaticAgent_id" value="%{getText('InsertTask.lblStaticAgent_id')}"/>:
               				</td>               				
               				<td>
               					<s:label name="lblStaticDeadline" value="%{getText('InsertTask.lblStaticDeadline')}"/>:
               				</td>
               				<td>
               					<s:hidden name="Task_id"/>
               				</td>               				
               			</tr>               			
               			<tr>
               				<td>
               					<s:select accesskey="register" name="cmbAgent_id" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbAgent_idList"/>
               				</td>
               				<td>
    	           				<s:textfield accesskey="register" name="txtDeadline" id="txtDeadline" cssClass="idfield date" maxlength="10"/>             		
               				</td>
               				<td>
               				</td>
               			</tr>               			
               			<tr>               				
               				<td>
               					<s:label name="lblStaticTask_comment" value="%{getText('InsertTask.lblStaticTask_comment')}"/>
               				</td>
             			<td colspan="2" align="center">
					    	<font color="red"><s:label id="system.success" name="system.success"/></font>
					    	<label id="message_label"></label>
					    </td>	
               			</tr>               			
               			<tr>
               				<td colspan="3">
               					<s:textarea cols="500" accesskey="register" id="InsertTask1" cssStyle="width: 100%; height:50px;" name="txtTask_comment" cssClass="idfield InsertTask">		
               						<s:param name="disabled" value="'true'.equals(status)"/>
									<s:param name="onchange">saveOnChange('InsertTask_saveOnChange', {callID: '<s:property value="callID"/>', call_comment:this.value})</s:param>
								</s:textarea>
               				</td>
               			</tr>
               			<tr>               			
               				<td colspan="3" style="text-align: right;"> 

	               				<button class="button" style="width:120" onclick="insertTask()" ><s:label key="InsertTask.btnCreate_task"  /></button>
	               				<button class="button" style="width:120" onclick="edit(); return false;"><s:label key="InsertTask.btnEdit_task" /></button>
                   				<s:reset  key="InsertTask.btnClear" cssClass="button" />               					
	               				<s:submit name="btnReturn" key="InsertTask.btnReturn" cssClass="button" onclick="$('#demoDialog').dialog('close');return false;"/>	
               				</td>               				
               			</tr>
               		</table>     
           </s:form>
        