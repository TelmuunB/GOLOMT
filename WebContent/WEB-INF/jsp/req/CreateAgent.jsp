<!--
* „M„Ђ„t„u„|„y„z„~ „~„Џ„‚             CreateAgent.jsp
*
* „U„…„~„{„€„Ќ„~ „~„Џ„‚          ЃyДэлгэцийн зохиомжЃz
* „Svv„‡
* „V„…„r„y„|„q„p„‚„Ќ„~ ‡‚  „O„s„~„Ђ„Ђ    „V„p„‚„y„…„€„p„s„‰    „@„s„…„…„|„s„p
* 01.00.00                 „O„s„~„Ђ„Ђ    „V„p„‚„y„…„€„p„s„‰    „Y„y„~„Џ„Џ„‚ „‡„y„z„ѓ„Џ„~/„H„p„ѓ„ѓ„p„~
*-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script>
	$(document).ready(function(){
		 $("button[name=btnClear]").click(function() {
			  $("input[name=txtLast_name]").val("");
			  $("input[name=txtFirst_name]").val("");
			  $("input[name=txtRegister]").val("");
			  $("input[name=txtAgent_id]").val("");
			  $("input[name=txtPassword]").val("");
			  $("select[name=cmbAgent_permission]").val('');
			  $("input[name=txtCronEmail]").val("");
			  normalGlow();
		
		});		
			  
			  digits();
			  texts();	
			
	  });
	 
	  
	  
</script>
<script type="text/javascript">
	var sflag="false";
	var counter=0;
	function clearData(param)
		{
		 var controls1 = new Array();
		 var controls3 = new Array();
   		 var tmp1 = new Array();
   		 var tmp = new Array();
	   	 controls1 = $('input[type=text]');
	   		if(param == "register"){
				tmp = $("input[type=password]")
		   		tmp1 = $("input[type=hidden]");
		   		for (i=0;i<tmp.length;i++) {
							tmp1.push(tmp[i]);
		   			}
		   		for (i=0;i<tmp1.length;i++) {
							$(tmp1[i]).val("");
		   			}
	   		}
	   		for (i=0;i<controls1.length;i++){
		 			if($(controls1[i]).attr("accesskey") == param){
		 				$(controls1[i]).val("");
		 			}
		 			}
		  	controls3 = $("select");
		  	for (i=0;i<controls3.length;i++)
		  			if($(controls3[i]).attr("accesskey") == param){
		 				$(controls3[i]).val("");
		 			}
		}
		
		function collectData(){
		var row = {};
		row.LAST_NAME = $("[name=txtLast_name]").val();
		}
		
		function showData(row){
		$("[name=txtLast_name]").val(row.LAST_NAME);
		}
		function replaceEscape(param){
		return param.replace(/(&nbsp);/g," ");
		}
function addAgent(){
		 $("#btnAdd").click(function() {
  		if(!checkNullCreateAgentData()){
  			return false;
  		}
  		var allIds=$("#userIds").val().split(",");
  		var correct="1";
  		for(var j=0;j<allIds.length;j++){
  			if(allIds[j]==$("input[name=txtAgent_id]").val()){
  			correct="0";		
			break;
  			}
  		}
  		if(correct=="1"){
  			var dataString = 'txtAgent_id='+$("input[name=txtAgent_id]").val()+
				'&txtLast_name='+$("input[name=txtLast_name]").val()+
				'&txtFirst_name='+$("input[name=txtFirst_name]").val()+
				'&txtRegister='+$("input[name=txtRegister]").val()+
				'&txtEmail='+$("input[name=txtCronEmail]").val()+
				'&txtPassword='+$("input[name=txtPassword]").val()+
				'&cmbAgent_permission='+$("select[name=cmbAgent_permission]").val();
				$.ajax({
			      type: "POST",
			      async: false,
			      cache: false,
			      url: "CreateAgent_onBtnAddClick.action",
			      data: dataString,
			      success: function() {
			      	$("#message_label").html('<span style="color:green"><s:text name="CreateAgent.msgSave_success"/></span>');
			      	sflag="true";
			      	
			      }
			    });
  		}else{
  			$("#message_label").html('<span style="color:red"><s:text name="CreateAgent.errId_match"/></span>');
  			redGlow("input[name=txtAgent_id]");
  				sflag="true";
  		}
  	$("#grdAgent_list").trigger("reloadGrid");
    return false;
	});
}

function btnEditClick() {
		if($("[name=hddOriginal_Id]").val()==null||$("[name=hddOriginal_Id]").val()==""||$("[name=hddOriginal_Id]").val()==" "){
		setMessage('<span style="color:red"><s:text name="CreateAgent.errNo_data"/></span>');
		return false;
		}
		if(!checkNullCreateAgentData()){
  			return false;
  		}
		var dataString = 'txtLast_name='+$("input[name=txtLast_name]").val()+
		'&txtFirst_name='+$("input[name=txtFirst_name]").val()+
		'&txtRegister='+$("input[name=txtRegister]").val()+
		'&txtEmail='+$("input[name=txtCronEmail]").val()+
		'&txtAgent_id='+$("input[name=txtAgent_id]").val()+
		'&hddOriginal_Id='+$("input[name=hddOriginal_Id]").val()+
		'&txtPassword='+$("input[name=txtPassword]").val()+
		'&cmbAgent_permission='+$("select[name=cmbAgent_permission]").val();
		$.ajax({
	      type: "POST",
	      url: "CreateAgent_onBtnEditClick.action",
	      data: dataString,
	      async: false,
		  cache: false,
	      success: function() {
	      	$("#message_label").html('<span style="color:green"><s:text name="CreateAgent.msgEdit_success"/></span>');
	      	sflag="true";
	      }
	    });
	$("#grdAgent_list").trigger("reloadGrid");
    return false;
}
function deleteAgent(){
	if($("[name=hddOriginal_Id]").val()==null||$("[name=hddOriginal_Id]").val()==""||$("[name=hddOriginal_Id]").val()==" "){
		setMessage('<span style="color:red"><s:text name="CreateAgent.errNo_data"/></span>');
		return false;
	}
	if(!checkNullCreateAgentData()){
  			return false;
  	}
	$("#delete_confirm_dialog").css({"left":"40%","top":"40%","display":"block"});
}

$(document).click(function(){
	if(sflag=="true"){
		if(counter==1){
			$("#message_label").html("");
			sflag="false";
			counter=0;
		}
		else{
			counter+=1;
		}
	}
});	
function deleteConfirmed(){
	if($("[name=txtAgent_id]").val()!=""){
			var dataString = '&txtAgent_id='+$("input[name=txtAgent_id]").val();
			$.ajax({
		      type: "POST",
			  url: "CreateAgent_onBtnDeleteClick.action",
		      data: dataString,
		      async: false,
			  cache: false,
		      success: function() {
		      	$("#message_label").html('<span style="color:green"><s:text name="CreateAgent.msgDelete_success"/></span>');
		      	sflag="true";
		      	clearData("register");
		      }
		    });
			$("#grdAgent_list").trigger("reloadGrid");
			$("#delete_confirm_dialog").css({"display":"none"});
    	return false;
		}else{
			$("#message_label").html('<span style="color:red"><s:text name="CreateAgent.errData_null"/></span>');
		    sflag="true";
		    $("#delete_confirm_dialog").css({"display":"none"});
		    return false;
		}
}
function deleteCancel(){
	$("#delete_confirm_dialog").css({"display":"none"});
}

function checkNullCreateAgentData(){
	var validatorFlag="true";
	var datas=new Array();
	datas=$("#CreateAgent").find("input[accesskey=register]");
	for(var z=0;z<datas.length;z++){
		if($(datas[z]).val()==""){
			validatorFlag="false";
			setMessage('<span style="color:red"><s:text name="CreateAgent.errData_null"/></span>');
			setTitle(datas[z],'<s:text name="CreateAgent.errData_null"/>');
			redGlow(datas[z]);
		}else if($(datas[z]).val()==" "){
			validatorFlag="false";
			setMessage('<span style="color:red"><s:text name="CreateAgent.errData_null"/></span>');
			setTitle(datas[z],'<s:text name="CreateAgent.errData_null"/>');
			redGlow(datas[z]);
		}else if($(datas[z]).attr("name")=="txtRegister"){
			var tmp=$(datas[z]).val();
			if(!isNaN(tmp.substr(0,2))){
				validatorFlag="false";
				setTitle(datas[z],'<s:text name="CreateAgent.errWrong_register"/>');
				redGlow(datas[z]);
			}else{
				setTitle(datas[z],'<s:text name="CreateAgent.infData_correct"/>');
				normalGlow(datas[z]);
			}
			if(tmp.length!=10){
				validatorFlag="false";
				setTitle(datas[z],'<s:text name="CreateAgent.errWrong_register"/>');
				redGlow(datas[z]);
			}
		}else if($(datas[z]).attr("name")=="txtCronEmail"){
			if(validateEmail($(datas[z]).val())){
				setTitle(datas[z],'<s:text name="CreateAgent.infData_correct"/>');
				normalGlow(datas[z]);
			}else{
				validatorFlag="false";
				setTitle(datas[z],'<s:text name="CreateAgent.errWrong_email"/>');
				redGlow(datas[z]);
			}
		}
		else{
			normalGlow(datas[z]);
		}
	}
	if(validatorFlag=="true"){
		return true;
	}else{
		setMessage('<span style="color:red"><s:text name="CreateAgent.errData_null"/></span>');
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
function validateEmail(email) { 
    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	return filter.test(email);
}
</script>
		<s:form action="CreateAgent" cssClass="form">
           <table cellpadding="4">
           		<tr>
           			<td>
           				<s:label name="lblStaticLast_name" value="%{getText('CreateAgent.lblStaticLast_name')}"/>:
           			</td>
           			<td>
           				<s:textfield accesskey="register" name="txtLast_name" cssClass="idfield texts"/>
           			</td>
           			<td>
           				<s:label name="lblStaticFirst_name" value="%{getText('CreateAgent.lblStaticFirst_name')}"/>:
           			</td>
           			<td>
           				<s:textfield accesskey="register" name="txtFirst_name" cssClass="idfield texts"/>
           			</td>
           			<td>
           				<s:label name="lblStaticRegister" value="%{getText('CreateAgent.lblStaticRegister')}"/>:
           			</td>
           			<td>
           				<s:textfield accesskey="register" name="txtRegister" cssClass="idfield"/>
           			</td>
           		</tr>
           		<tr>
           			<td>
           				<s:label name="lblStaticAgent_id" value="%{getText('CreateAgent.lblStaticAgent_id')}"/>:
           			</td>
           			<td>
           				<s:textfield accesskey="register" name="txtAgent_id" maxlength = "4" cssClass="idfield digits"/>
           				<s:hidden name="hddOriginal_Id" accesskey="register"/>
           			</td>
           			<td>
           				<s:label name="lblStaticPassword" value="%{getText('CreateAgent.lblStaticPassword')}"/>:
           			</td>
           			<td>
           				 <s:password accesskey="register" name="txtPassword" maxlength = "8" cssClass="idfield"/>
           			</td>
           			<td>
           				<s:label name="lblStaticAgent_permission" value="%{getText('CreateAgent.lblStaticAgent_permission')}"/>:
           			</td>
           			<td>
           				<s:select accesskey="register" name="cmbAgent_permission" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="%{cmbAgent_permissionList}"/>
           			</td>
           		</tr>
           		<tr>
           				<td>
           					<s:label name="lblStaticEmail" value="%{getText('CreateAgent.lblStaticEmail')}"/>:
           				</td>
           				<td>
           					<s:textfield accesskey="register" name="txtCronEmail" cssClass="idfield"/>
           					<s:hidden id="userIds"/>
           				</td>
           		</tr>
           		<tr>
           				<td align="center" colspan="5">
           					<s:submit name="btnAdd" key="CreateAgent.btnAdd" cssClass="button" onclick="addAgent();return false;" id="btnAdd"/>
           					<s:submit name="btnEdit" key="CreateAgent.btnEdit" cssClass="button" onclick="btnEditClick(); return false;" id="btnEdit"/>
          					<s:submit name="btnDelete" key="CreateAgent.btnDelete" cssClass="button" onclick="deleteAgent();return false;" id="btnDelete"/>
           					<button name="btnClear" class="button" onclick="return false;"><s:label name="btnClear" key='CreateAgent.btnClear'/> </button>
           				</td>
           		</tr>
           		<tr>
           				<td align="center" colspan="5">
           					<label id="message_label"></label>
           				</td>
           		</tr>
           </table>
           
     
               <fieldset><legend><s:property value="getText('CreateAgent.grpAgent_list')"/></legend>

               <table id="grdAgent_list"></table><div id="pager_grdAgent_list" ></div>
               <script type="text/javascript">
              		var demo;
                    jQuery("#grdAgent_list").jqGrid({
                    url: 'CreateAgent_getXML.action',
                    datatype: "xml",
                    height: 200,
                    width: 700,
                    colNames:[
                    '<s:text name="CreateAgent.grdLAST_NAME"/>',
                    '<s:text name="CreateAgent.grdFIRST_NAME"/>',
                    '<s:text name="CreateAgent.grdAGENT_REGISTER"/>',
                    '<s:text name="CreateAgent.grdAGENT_ID"/>',
                    '',
                    '<s:text name="CreateAgent.grdAGENT_PERMISSION_ID"/>',
                    '<s:text name="CreateAgent.grdEMAIL"/>',
                    'none',
                    'none'
                    ],                                       
                    colModel:[
                             {name : 'LASTNAME', index : 'LASTNAME' , width:30 },
                             {name : 'FIRSTNAME', index : 'FIRSTNAME' , width:30 },
                             {name : 'AGENT_REGISTER', index : 'AGENT_REGISTER' , width:30 },
                             {name : 'AGENT_ID', index : 'AGENT_ID' , width:30 },
                             {name : 'AGENT_PERMISSION', index : 'AGENT_PERMISSION' , hidden:true },
                             {name : 'AGENT_PERMISSION_NAME', index : 'AGENT_PERMISSION_NAME' , width:30 },
                             {name : 'EMAIL', index : 'EMAIL' , width:30 },
                             {name : 'A_PASSWORD', index : 'A_PASSWORD', hidden:true},
                             {name : 'DELETE_USER', index : 'DELETE_USER', hidden:true}
                             ],
                    rowNum:100,
                    rowTotal: 2000,
                    mtype: "GET",
                    rownumbers: true,
                    rownumWidth: 40,
                    gridview: true,
                    pager:  '#pager_grdAgent_list',
                    viewrecords: true,
                    sortorder: "asc",
                    loadComplete: function(){
      
			                    	 $("tr.jqgrow:odd").css("background", "#EAF6F1");
			                    
                    },
                    onSelectRow: function(selectedRowId) {
								var data = $(this).jqGrid('getRowData', selectedRowId);
								$('[name=txtLast_name]').val(replaceEscape(data['LASTNAME']));
		                     	$('[name=txtFirst_name]').val(replaceEscape(data['FIRSTNAME']));
		                     	$('[name=txtRegister]').val(replaceEscape(data['AGENT_REGISTER']));
		                     	$('[name=txtAgent_id]').val(replaceEscape(data['AGENT_ID']));
		                     	$('[name=hddOriginal_Id]').val(replaceEscape(data['AGENT_ID']));
		                     	$('[name=txtCronEmail]').val(replaceEscape(data['EMAIL']));
		                     	$('[name=cmbAgent_permission]').val(replaceEscape(data['AGENT_PERMISSION']));
		                     	$('[name=txtPassword]').val(data['A_PASSWORD']);
		                     	 						}							 
                    });
                    </script>
               </fieldset>
               
               <div id="delete_confirm_dialog" style="background:#E6E6E6;display:none;position:absolute;border: 1px solid;">
			<table cellpadding=10>
				<tr>
					<td colspan="2" align="center">
						<s:property value="getText('InsertData.delete_confirm')"/>
					</td>
				</tr>
				<tr>
					<td>
						<button name="delete_confirm" onClick="deleteConfirmed();" class="button"><s:property value="getText('InsertData.btnDelete_confirm')"/></button>
					</td>
					<td>
						<button name="delete_cancel" onClick="deleteCancel();" class="button"><s:property value="getText('InsertData.btnDelete_cancel')"/></button>
					</td>
				</tr>
			</table>
		</div>
               
     </s:form>

