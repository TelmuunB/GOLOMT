<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	$(document).ready(function(){
	$("input[name=txtPhone_number]").focus();
		function get_data_search1()
			{
				var controls = new Array();
	   			var tmp = new Array();
	   			var uri_data = '?';
	   			var uri_dataNoEncode = '?';
	   			tmp = $("select");
		   		controls = $("input[type=text]");
		   		for (i=0;i<tmp.length;i++)
		   			controls.push(tmp[i]);
		   		for (i=0;i<controls.length;i++) {
		   			if($(controls[i]).attr("accesskey") == "search" && $(controls[i]).attr('name') != "qString"){
		   				uri_data += $(controls[i]).attr('name')+'='+ encodeURIComponent($(controls[i]).val())+'&';
			   			if($(controls[i]).val() !="" || $(controls[i]).attr('name') == "txtLastNameS")
			   					{
									uri_dataNoEncode += $(controls[i]).attr('name')+'='+ $(controls[i]).val()+'&';
			   					}
			   				}
		   			}
		   		$('[name=qString]').val(uri_dataNoEncode);
		   		return uri_data;
	   		}

		$("button[name=btnSearch1]").click(function(){
			$("#grpComplaint_list").setGridParam({
					                	url:'RegisterComplaint_getXML.action'+get_data_search1()+'searchClick=true',
					                	postData: {qString : $('#qString').val()},
					                	page:1
					              }).trigger("reloadGrid");
			});

		$("input[id=btnClearSearch]").click(function(){
				var controls = new Array();
	   			var tmp = new Array();
	   			tmp = $("select");
		   		controls = $("input[type=text]");
		   		for (i=0;i<tmp.length;i++)
		   			controls.push(tmp[i]);
		   		for (i=0;i<controls.length;i++) {
		   			if($(controls[i]).attr("accesskey") == "search"){
		   				$(controls[i]).val("");
			   		}
		   		}

			return false;
			});
					digits();
					texts();
					
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

		});
		function clearMessage(){
			$("label[id=system.success]").html("");
		}
		
		$(document).ready(function(){
			comboAdder('incomplaint_tbl', 'ir','service_type','detailed_service_type', 6, 4);
			
					$("#txtSolved_comment").bind("keyup", function(){
					$("#txtSolved_comment").css({"border-color":""});
					maxlength = $(this).attr("cols");
					if($(this).val().length > maxlength){
						value = $(this).val().slice(0,maxlength); 
						$(this).val(value);
						$("#txtSolved_comment").css({"border-color":"red"});
					}
			});	
  	});
</script>

<script type="text/javascript">
	var dflag="false";
	var tsCnt=0;
	function saveComplaint() {
		if($("[name=hddComplaint_id]").val()==""){
			$("label[id=system.success]").html('<span style="color:red"><s:text name="RegisterComplaint.errNull_insert"/></span>');
			setTimeout(function(){clearMessage()},2000);
			return false;
		}else if($("[name=hddComplaint_id]").val()==" "){
			$("label[id=system.success]").html('<span style="color:red"><s:text name="RegisterComplaint.errNull_insert"/></span>');
			setTimeout(function(){clearMessage()},2000);
			return false;
		}else if($("[name=hddComplaint_id]").val()=="null"){
			$("label[id=system.success]").html('<span style="color:red"><s:text name="RegisterComplaint.errNull_insert"/></span>');
			setTimeout(function(){clearMessage()},2000);
			return false;
		}else if($("[name=hddComplaint_id]").val()==null){
			$("label[id=system.success]").html('<span style="color:red"><s:text name="RegisterComplaint.errNull_insert"/></span>');
			setTimeout(function(){clearMessage()},2000);
			return false;
		}
		if(!checkNullComplaintData()){
			return false;
		}
		   $.ajax({
						      type: "POST",
						      url: "RegisterComplaint_onBtnSaveClick.action",
						      data: get_data("RegisterComplaint"),

						      success: function(result) {
						      	clearData();
						      	$("label[id=system.success]").html('<span style="color:green"><s:text name="RegisterComplaint.infUpdate_success"/></span>');
						      	setTimeout(function(){clearMessage()},2000);
						      	$("#grpComplaint_list").trigger("reloadGrid");
						      }
		   });
		 return false;
	}
	function deleteComplaint() {
		if($("[name=hddComplaint_id]").val()==""){
			$("label[id=system.success]").html('<span style="color:red"><s:text name="RegisterComplaint.errNull_insert"/></span>');
			setTimeout(function(){clearMessage()},2000);
			return false;
		}else if($("[name=hddComplaint_id]").val()==" "){
			$("label[id=system.success]").html('<span style="color:red"><s:text name="RegisterComplaint.errNull_insert"/></span>');
			setTimeout(function(){clearMessage()},2000);
			return false;
		}else if($("[name=hddComplaint_id]").val()=="null"){
			$("label[id=system.success]").html('<span style="color:red"><s:text name="RegisterComplaint.errNull_insert"/></span>');
			setTimeout(function(){clearMessage()},2000);
			return false;
		}else if($("[name=hddComplaint_id]").val()==null){
			$("label[id=system.success]").html('<span style="color:red"><s:text name="RegisterComplaint.errNull_insert"/></span>');
			setTimeout(function(){clearMessage()},2000);
			return false;
		}
		 $("#delete_confirm_dialog").css({"left":"30%","top":"50%","display":"block"});
		 dflag="true";
		 tsCnt=0;
		 return false;
	}
	function deleteData(){
		$.ajax({
						      type: "POST",
						      url: "RegisterComplaint_onBtnDeleteClick.action",
						      data: get_data("RegisterComplaint"),
							  success: function(result) {
						      	clearData();
						      	$("label[id=system.success]").html('<span style="color:green"><s:text name="system.delete_success"/></span>');
						      	setTimeout(function(){clearMessage()},2000);
						      	$("#grpComplaint_list").trigger("reloadGrid");
						      }
		   });
		$("#delete_confirm_dialog").css({"display":"none"});
	}
	function dialogCancel(){
		   $("#delete_confirm_dialog").css({"display":"none"});
	}
	function clearData(){
				var tmp = new Array();
	   			tmp = $("#RegisterComplaint").find("label");
	   			for (i=0;i<tmp.length;i++) {
		   			if($(tmp[i]).attr("accesskey") == "edit"){
		   				$(tmp[i]).html("");
		   			}
		   		}
		   		tmp = $("#RegisterComplaint").find("select");
	   			for (i=0;i<tmp.length;i++) {
		   			if($(tmp[i]).attr("accesskey") == "edit"){
		   				$(tmp[i]).val("");
		   			}
		   		}
		   		
		   		tmp = $("#RegisterComplaint").find("input");
		   		for (i=0;i<tmp.length;i++) {
		   			if($(tmp[i]).attr("accesskey") == "edit"){
		   				$(tmp[i]).val("");
		   			}
		   		}
		   		tmp = $("#RegisterComplaint").find("textarea");
		   		for (i=0;i<tmp.length;i++) {
		   			if($(tmp[i]).attr("accesskey") == "edit"){
		   				$(tmp[i]).html("");
		   			}
		   		}
		   		$("input[name=hddComplaint_id]").val("");
		   		clearMessage();
		return false;
	}
	$(document).click(function(){
		if(dflag=="true"){
			if(tsCnt==1){
			dialogCancel();
			dflag="false";
			}else{
			tsCnt+=1;
			}
		}
	});
	function checkNullComplaintData(){
					var validatorFlag="true";
					var datas=new Array();
					var tmp=new Array();
					datas=$("#RegisterComplaint").find("input[accesskey=edit]");
					tmp=$("#RegisterComplaint").find("select[accesskey=edit]");
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
						if($(datas[z]).attr("name")=="txtComplaint_expense"){
							normalGlow(datas[z]);
						}else if($(datas[z]).attr("name")=="txtEmail"){
							normalGlow(datas[z]);
							if($(datas[z]).val()!=""){
								if(validateEmail($(datas[z]).val())){
								normalGlow(datas[z]);
								}else{
									validatorFlag="false";
									setTitle(datas[z],'<s:text name="RegisterOtherComplaint.errWrong_email"/>');
									redGlow(datas[z]);
								}	
							}
						}else if($(datas[z]).val()==""){
							validatorFlag="false";
							setMessage('<span style="color:red"><s:text name="RegisterOtherComplaint.errData_null"/></span>');
							setTitle(datas[z],'<s:text name="RegisterOtherComplaint.errData_null"/>');
							redGlow(datas[z]);
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
<s:form id="RegisterComplaint" action="RegisterComplaint" cssClass="form">
<fieldset><legend class="RegisterComplaint"><s:property value="getText('RegisterComplaint.grpSearch')" /></legend> <s:hidden id="qString" name="qString" />
<table width="100%" cellpadding="2">
	<tr>
		<td style="text-align: right;"><s:label key="RegisterComplaint.lblStaticPhone_number"/>:</td>
		<td>
			<s:textfield accesskey="search" name="txtPhone_number1" maxlength = "15" cssClass="idfield digits" />
		</td>
		
		<td style="text-align: right;"><s:label key="RegisterComplaint.lblStaticComplaint_id"/>:</td>
		<td>
			<s:select accesskey="search" name="cmbComplaint_id" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbServiceList" />
		</td>
		
		<td style="text-align: right;"><s:label key="RegisterComplaint.lblStaticComplaint_rate_id"/>:</td>
		<td>
			<s:select accesskey="search" name="cmbComplaint_rate_id" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbInboundComplaintRateList" />
		</td>
	</tr>
	<tr>
		<td style="text-align: right;"><s:label key="RegisterComplaint.lblStaticAgent_id"/>:</td>
		<td>
			<s:select accesskey="search" name="cmbAgent_id1" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbAgent_idList" />
		</td>
		
		<td style="text-align: right;"><s:label key="RegisterComplaint.lblStaticComplaint_type_id"/>:</td>
		<td>
			<s:select accesskey="search" name="cmbComplaint_type_id" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="%{#{}}" />
		</td>
		
		<td style="text-align: right;"><s:label key="RegisterComplaint.lblStaticComplaint_sort_id"/>:</td>
		<td>
			<s:select accesskey="search" name="cmbComplaint_sort_id1" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbInboundComplaintSortList" />
		</td>
	</tr>
	<tr>
		<td colspan="6" align="center">
		<s:submit name="btnSearch1" key="RegisterComplaint.btnSearch" cssClass="button"
			onclick="searchGridData('grpComplaint_list', 'RegisterComplaint_getXML.action', 'RegisterComplaint');return false" />
		<s:submit name="btnClearSearch" id="btnClearSearch" key="RegisterComplaint.btnClear" cssClass="button" method="onBtnClearClick" /></td>
	</tr>
</table>
</fieldset>
<div style="text-align: center;height: 30px;">
			        <s:label name="lblResult" id="lblResult"  cssClass="error_message"/>
</div>
<s:hidden name="agent_permission" id="agent_permission"/>
<fieldset><legend><s:property value="getText('RegisterComplaint.grpComplaint_list')" /></legend>
<table id="grpComplaint_list"></table>
<div id="pager_grpComplaint_list"></div>
<script type="text/javascript">
                    jQuery("#grpComplaint_list").jqGrid({
                    url:'RegisterComplaint_getXML.action?searchClick=true',
                    datatype: "xml",
                    height: 200,
                    width: 1090,
                    colNames:[
                     		'<s:text name="RegisterComplaint.grdPHONE_NUMBER_ONE"/>',
                     		'<s:text name="RegisterComplaint.grdAGENT_ID"/>' ,
		                    '<s:text name="RegisterComplaint.grdCOMPLAINT_ID"/>' ,
		                    '<s:text name="RegisterComplaint.grdPARENT_COMPLAINT_ID"/>' ,
		                    '<s:text name="RegisterComplaint.grdCOMPLAINT_RATE_ID"/>' ,
		                    '<s:text name="RegisterComplaint.grdCOMPLAINT_SORT_ID"/>',
		                    '<s:text name="RegisterComplaint.grdCOMPLAINT_DEADLINE"/>',
		                    'none',
		                    'none',
		                    'none',
		                    'none',
		                    'none',
		                    'none',
		                    'none',
		                    'none',
		                    'none',
		                    'none',
		                    'none',
		                    'none',
		                    'none',
		                    '<s:text name="RegisterComplaint.grdSOURCE"/>'
							],
                    colModel:[
                    		 {name :'PHONE_NUMBER', index : 'PHONE_NUMBER', width: 100, hidden: false},
                             {name :'AGENT_NAME', index : 'AGENT_NAME', width:150, hidden: false},
                             {name :'SERVICE_NAME', index : 'SERVICE_NAME', width:200, hidden: false},
                             {name :'DETAILED_SERVICE_NAME', index : 'DETAILED_SERVICE_NAME', width:180, hidden: false},
                             {name :'CALL_RATE', index : 'CALL_RATE', width:200, hidden: false},
                             {name :'CALL_SORT', index : 'CALL_SORT', width:180,hidden: false},
							 {name :'DEADLINE', index : 'DEADLINE', width:120, hidden: false},
							 {name :'AGENT_ID', index : 'AGENT_ID', hidden: true},
                             {name :'COMPLAINT_LIST_ID', index : 'COMPLAINT_LIST_ID', hidden: true},
                             {name :'CALLER_EMAIL', index : 'CALLER_EMAIL', hidden: true},
                             {name :'CALLER_NAME', index : 'CALLER_NAME', hidden: true},
                             {name :'SOLVED_DATE', index : 'SOLVED_DATE', hidden: true},
                             {name :'EXPENSE', index : 'EXPENSE', hidden: true},
                             {name :'CALL_SORT_ID', index : 'CALL_SORT_ID', hidden: true},
                             {name :'CALL_RESULT_ID', index : 'CALL_RESULT_ID', hidden: true},
                             {name :'COMPLAINT_COMMENT', index : 'COMPLAINT_COMMENT', hidden: true},
                             {name :'SOLVED_COMMENT', index : 'SOLVED_COMMENT', hidden: true},
                             {name :'UNIT_ID', index : 'UNIT_ID',  hidden: true},
                             {name :'INSERT_DATE', index : 'INSERT_DATE', hidden: true},
                             {name :'SOLVED_STATUS', index:'SOLVED_STATUS',hidden: true},
                             {name :'SOURCE_NAME', index:'SOURCE_NAME',width:80,hidden: false}
                             ],
                    rowNum:100,
                    rowTotal: 2000,
                    mtype: "GET",
                    rownumbers: true,
                    rownumWidth: 30,
                    gridview: true,
                    pager:  '#pager_grpComplaint_list',
                    viewrecords: true,
                    sortorder: "asc",
                    loadComplete: function(){
                 
			                    	 $("tr.jqgrow:odd").css("background", "#EAF6F1");
			                    
                    				 document.getElementById("lblResult").innerHTML = "" ;

                          		     if (jQuery("#grpComplaint_list").getGridParam("records") == 0)
									 {
										document.getElementById("lblResult").innerHTML = "<s:text name="system.WRN007"/>";
									 }
									 var ids = jQuery("#grpComplaint_list").getDataIDs();
							         var data;
							         for(var i=0;i<ids.length;i++)
							         {
							        	data = jQuery("#grpComplaint_list").getRowData(ids[i]);

							        	if(data['SOLVED_STATUS'] == 2)
							        	{
							        		$("#grpComplaint_list").jqGrid('setRowData',ids[i],false, {  weightfont:'bold',background:'#F5D0A9'});
							        	}
							        	
							        }
                    },
                 	onSelectRow: function(id){
                 	  dialogCancel();
				      var rowData = $('#grpComplaint_list').jqGrid('getRowData', id);
				      if(rowData['SOLVED_STATUS']=='2'){
			      		$("input[name=hddComplaint_id]").attr("disabled","disabled");
					    $("input[name=txtEmail]").attr("disabled","disabled");
					    $("input[name=txtSolved_date]").attr("disabled","disabled");
					    $("input[name=txtComplaint_expense]").attr("disabled","disabled");
					    $("select[name=cmbComplaint_sort_id]").attr("disabled","disabled");
					    if($("#agent_permission").val()=="1"){
					    $("select[name=cmbComplaint_result_id]").attr("disabled","");
					  	$("[name=btnSave]").attr("disabled","");
					  	$("[name=btnDelete]").attr("disabled","");
					    }else{
					    $("select[name=cmbComplaint_result_id]").attr("disabled","disabled");
					  	$("[name=btnSave]").attr("disabled","disabled");
					  	$("[name=btnDelete]").attr("disabled","disabled");
					    }
					    $("textarea[name=txtSolved_comment]").attr("disabled","disabled");
					  	$("select[name=cmbAgent_id]").attr("disabled","disabled");
					  	
					  }else{
					  	$("input[name=hddComplaint_id]").attr("disabled","");
					    $("input[name=txtEmail]").attr("disabled","");
					    $("input[name=txtSolved_date]").attr("disabled","");
					    $("input[name=txtComplaint_expense]").attr("disabled","");
					    $("select[name=cmbComplaint_sort_id]").attr("disabled","");
					    $("select[name=cmbComplaint_result_id]").attr("disabled","");
					  	$("textarea[name=txtSolved_comment]").attr("disabled","");
					  	$("select[name=cmbAgent_id]").attr("disabled","");
					  	$("[name=btnSave]").attr("disabled","");
					  	$("[name=btnDelete]").attr("disabled","");
					  }
				      $("label[id=lblPhone_number]").html(rowData['PHONE_NUMBER']);
					  $("select[name=cmbAgent_id]").val(rowData['AGENT_ID']);
					  $("label[id=lblComplaint_id]").html(rowData['SERVICE_NAME']);
					  $("label[id=lblInsert_date]").html(rowData['INSERT_DATE']);
					  $("label[id=lblComplaint_rate_id]").html(rowData['CALL_RATE']);
					  $("label[id=lblCall_sort_id]").html(rowData['CALL_SORT']);
					  $("input[name=hddComplaint_id]").val(rowData['COMPLAINT_LIST_ID']);
					  $("input[name=txtEmail]").val(rowData['CALLER_EMAIL']);
					  $("input[name=txtSolved_date]").val(rowData['SOLVED_DATE']);
					  $("input[name=txtComplaint_expense]").val(rowData['EXPENSE']);
					  $("select[name=cmbComplaint_sort_id]").val(rowData['CALL_SORT_ID']);
					  $("select[name=cmbComplaint_result_id]").val(rowData['CALL_RESULT_ID']);
					  $("label[id=lblComplaint_comment]").html(rowData['COMPLAINT_COMMENT']);
					  $("textarea[name=txtSolved_comment]").html(rowData['SOLVED_COMMENT']);
					  $("label[id=lblUnit_id]").html(rowData['UNIT_ID']);
					  $("label[id=lblCaller_name]").html(rowData['CALLER_NAME']);
						 }
                    });
                    </script></fieldset>
<fieldset style="height:100pxword-wrap: break-word;">
	<legend>
		<s:property value="getText('RegisterComplaint.grpComplaint_comment')" />
	</legend>
	<s:label accesskey="edit" name="lblComplaint_comment" id="lblComplaint_comment" value="%{getText('RegisterComplaint.lblComplaint_comment')}" />
</fieldset>

<fieldset> 
	<legend> 
		<s:property value="getText('RegisterComplaint.grdComplaint_register')" /> 
	</legend>
	<label id="message_label"></label>
	<table width="100%" border='0' cellpadding='4'>
		<tr>
			<td align="center" colspan="6">
				<s:label id="system.success" name="system.success"/>
			</td>
		</tr>
		<tr >
			<td width="120px" style="background-color:#EFEFEF;"><s:label key="RegisterComplaint.lblStaticPhone_number"/>:</td>
			<td width="190px">
				<b><s:label accesskey="edit" name="lblPhone_number" id="lblPhone_number" /></b>
				<s:hidden  accesskey="edit" name="hddComplaint_id" value=""/>
			</td>
			<td style="background-color:#EFEFEF;"><s:label key="RegisterComplaint.lblStaticCaller_name"/>:</td>
			<td width="190px"><b><s:label  accesskey="edit" id="lblCaller_name" name="lblCaller_name" /></b></td>
			<td style="background-color:#EFEFEF;" ><s:label key="RegisterComplaint.lblStaticComplaint_id"/>:</td>
			<td width="190px"><b><s:label  accesskey="edit" id="lblComplaint_id" name="lblComplaint_id"/></b></td>
		</tr>
		
		<tr >
			<td style="background-color:#EFEFEF;"><s:label key="RegisterComplaint.lblStaticAgent_id"/>:</td>
			<td><s:select id="cmbAgent_id" accesskey="edit" name="cmbAgent_id" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbAgent_idList" /></td>
			<td style="background-color:#EFEFEF;"><s:label key="RegisterComplaint.lblStaticUnit_id"/>:</td>
			<td><b><s:label id="lblUnit_id" accesskey="edit" name="lblUnit_id" /></b></td>
			<td style="background-color:#EFEFEF;"><s:label key="RegisterComplaint.lblStaticInsert_date"/>:</td>
			<td><b><s:label  accesskey="edit" id="lblInsert_date" name="lblInsert_date"/></b></td>
		</tr>
		<tr >
			<td style="background-color:#EFEFEF;"><s:label key="RegisterComplaint.lblStaticComplaint_rate_id"/>:</td>
			<td><b><s:label id="lblComplaint_rate_id" accesskey="edit" name="lblComplaint_rate_id" /></b></td>
			<td style="background-color:#EFEFEF;"><s:label key="RegisterComplaint.lblStaticComplaint_result_id"/>:</td>
			<td><s:select id="cmbComplaint_result_id" accesskey="edit" name="cmbComplaint_result_id" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbInboundComplaintResultList" /></td>
			<td style="background-color:#EFEFEF;"><s:label key="RegisterComplaint.lblStaticCall_sort_id"/>:</td>
			<td><s:select id="cmbComplaint_sort_id" accesskey="edit" name="cmbComplaint_sort_id" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbInboundComplaintSortList" /></td>
		</tr>
		<tr>
			<td style="background-color:#EFEFEF;"><s:label key="RegisterComplaint.lblStaticEmail"/>:</td>
			<td><s:textfield accesskey="edit" name="txtEmail" maxlength = "100" cssClass="idfield" /></td>
			<td style="background-color:#EFEFEF;"><s:label key="RegisterComplaint.lblStaticSolved_date"/>:</td>
			<td><s:textfield accesskey="edit" name="txtSolved_date" cssClass="idfield date" maxLength="10" /></td>
			<td style="background-color:#EFEFEF;"><s:label key="RegisterComplaint.lblStaticComplaint_expense"/>:</td>
			<td><s:textfield accesskey="edit" name="txtComplaint_expense" cssClass="idfield digits" /></td>
		</tr>
		<tr>
			<td colspan="3"><s:label name="lblStaticSolved_comment" value="%{getText('RegisterComplaint.lblStaticSolved_comment')}" /></td>
			
		</tr>
		<tr>
			<td colspan="6"><s:textarea cols="500" id = "txtSolved_comment" accesskey="edit" cssStyle="width: 100%; height:50px;" name="txtSolved_comment" cssClass="idfield" /></td>
		</tr>
		<tr>
			<td colspan="6" style="text-align: center;" class="no-wrap">
				<s:submit name="btnSave" key="RegisterComplaint.btnSave" cssClass="button" onclick="saveComplaint(); return false;"/>
				<s:submit name="btnReset" key="RegisterComplaint.btnReset" cssClass="button" onclick="clearData(); return false;"/>
				<s:submit name="btnDelete" key="RegisterComplaint.btnDelete" cssClass="button" onclick="deleteComplaint(); return false;"/>
			</td>
		</tr>
	</table>
	</fieldset>
	<div id="delete_confirm_dialog" style="background:#E6E6E6;display:none;position:absolute;border: 1px solid;">
			<table cellpadding="10">
				<tr>
					<td colspan="2" align="center">
						<s:property value="getText('RegisterComplaint.delete_confirm')"/>
					</td>
				</tr>
				<tr>
					<td>
						<button name="delete_confirm" class="button" onclick="deleteData();"><s:property value="getText('RegisterComplaint.btnDelete_confirm')"/></button>
					</td>
					<td>
						<button name="delete_cancel" class="button" onclick="dialogCancel();"><s:property value="getText('RegisterComplaint.btnDelete_cancel')"/></button>
					</td>
				</tr>
			</table>
		</div>
</s:form>
