<!--
* Моделийн нэр             ComplaintHistory.jsp
*
* Функцын нэр          【Гомдлын түүх】
* Тvvх
* Хувилбарын №  Огноо    Хариуцагч    Агуулга
* 01.00.00                 Огноо    Хариуцагч    Шинээр хийсэн/Зассан
* 01.00.01				2013.02.28	Б.Тэлмүүн		Шинээр үүсгэх
* 02.00.01				2013.03.04	Э.Бат-Эрдэнэ 		Зассан
*
*-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	$(document).ready(function(){
		$("input[name=txtPhone_number]").focus();   
		function get_data_search()
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
		   			if($(controls[i]).attr("accesskey") == "search1" && $(controls[i]).attr('name') != "qString"){
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
	   
		$("button[name=btnSearch]").click(function(){
			$("#grdComplaint_history").setGridParam({
					                	url:'ComplaintHistory_getXML.action'+get_data_search()+'searchClick=true',
					                	postData: {qString : $('#qString').val()},
					                	page:1
					              }).trigger("reloadGrid");
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

<script type="text/javascript">
	var dflag1="false";
	var tsCnt1=0;
	function clearMessage(){
			setTimeout(function(){$("label[id=system.success]").html("");},1000);
	}
	function saveComplaint() {
		if(!checkNullComplaintHistoryData()){
			return false;
		}
	  	if($("[name=hddComplaint_id1]").val()!=""){
	   		$.ajax({
		      	type: "POST",
		      	url: "ComplaintHistory_onBtnEditClick.action",
		      	data: get_data("ComplaintHistory"),
		      	success: function(result) {
		      	clearData();
		      	$("label[id=system.success]").html('<span style="color:green"><s:text name="ComplaintHistory.infUpdate_success"/></span>');
		      	clearMessage();
		      	$("#grdComplaint_history").trigger("reloadGrid");
		      	}
		  	});
	  		return false;
	  	}else{
	  		$("label[id=system.success]").html('<span style="color:red"><s:text name="ComplaintHistory.errNull_edit"/></span>');
	  		clearMessage();
	  		return false;
	  	}
	  	return false;
	}
    function deleteComplaint(){
    	if($("[name=hddComplaint_id1]").val()!=""){
	    	$("#delete_confirm_dialog1").css({"left":"30%","top":"50%","display":"block"});
			dflag1="true";
			tsCnt1=0;
		  	return false;
	  	}else{
	  		$("label[id=system.success]").html('<span style="color:red"><s:text name="ComplaintHistory.errNull_edit"/></span>');
	  		clearMessage();
	  		return false;
	  	}
	  	return false;
    }
    function clearComplaint(){
    	clearData();
    	return false;
    }
    function clearData(){
    			$("[id=lblComplaint_comment]").html("");
				var tmp = new Array();
	   			tmp = $("#ComplaintHistory").find("label");
	   			for (i=0;i<tmp.length;i++) {
		   			if($(tmp[i]).attr("accesskey") == "edit"){
		   				$(tmp[i]).html("");
		   			}
		   		}
		   		tmp = $("#ComplaintHistory").find("select");
	   			for (i=0;i<tmp.length;i++) {
		   			if($(tmp[i]).attr("accesskey") == "edit"){
		   				$(tmp[i]).val("");
		   			}
		   		}
		   		
		   		tmp = $("#ComplaintHistory").find("input");
		   		for (i=0;i<tmp.length;i++) {
		   			if($(tmp[i]).attr("accesskey") == "edit"){
		   				$(tmp[i]).val("");
		   			}
		   		}
		   		tmp = $("#ComplaintHistory").find("textarea");
		   		for (i=0;i<tmp.length;i++) {
		   			if($(tmp[i]).attr("accesskey") == "edit"){
		   				$(tmp[i]).html("");
		   			}
		   		}
		   		$("input[name=hddComplaint_id1]").val("");
		   		clearMessage();
				return false;
	}
	function clearSearchData(){
		clear1Data("search1");
	}
	function deleteData(){
		$.ajax({
		      	type: "POST",
		      	url: "ComplaintHistory_onBtnDeleteClick.action",
		      	data: get_data("ComplaintHistory"),
		      	success: function(result) {
			      	clearData();
			      	$("label[id=system.success]").html('<span style="color:green"><s:text name="ComplaintHistory.infDelete_success"/></span>');
			      	clearMessage();
			      	$("#grdComplaint_history").trigger("reloadGrid");
		      	}
		  	});
		$("#delete_confirm_dialog1").css({"display":"none"});
		return false;
	}
	function dialogCancel(){
		   $("#delete_confirm_dialog1").css({"display":"none"});
		   return false;
	}
	function checkNullComplaintHistoryData(){
					var validatorFlag="true";
					var datas=new Array();
					var tmp=new Array();
					datas=$("#ComplaintHistory").find("input[accesskey=edit]");
					tmp=$("#ComplaintHistory").find("select[accesskey=edit]");
					for(var i=0;i<tmp.length;i++){
						datas.push(tmp[i]);
					}
					for(var z=0;z<datas.length;z++){
						
						if($(datas[z]).attr("tagName")=="SELECT"){
							if($(datas[z]).val()==""){
								validatorFlag="false";   
								if($(datas[z]).next("span").attr("tagName")!="SPAN"){
								$(datas[z]).parent().append("<span style='color:red'>*</span>");
								}
							}else{
							$(datas[z]).next("span").remove();
							}
						}
						if($(datas[z]).val()==""){
							validatorFlag="false";
							setMessage('<span style="color:red"><s:text name="ComplaintHistory.errData_null"/></span>');
							setTitle(datas[z],'<s:text name="ComplaintHistory.errData_null"/>');
							redGlow(datas[z]);
						}else if($(datas[z]).val()==" "){
							validatorFlag="false";
							setMessage('<span style="color:red"><s:text name="ComplaintHistory.errNull_edit"/></span>');
							setTitle(datas[z],'<s:text name="ComplaintHistory.errNull_edit"/>');
							redGlow(datas[z]);
						}else if($(datas[z]).attr("name")=="txtEmail1"){
							if(validateEmail($(datas[z]).val())){
								normalGlow(datas[z]);
							}else{
								validatorFlag="false";
								setTitle(datas[z],'<s:text name="ComplaintHistory.errWrong_email"/>');
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
						setMessage('<span style="color:red"><s:text name="ComplaintHistory.errNull_edit"/></span>');
						return false;
					}
				}
	function setMessage(str){
		$("#message_label1").html(str);
		setTimeout(function(){$("#message_label1").html("")},3000);
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
	$(document).click(function(){
		if(dflag1=="true"){
			if(tsCnt1==1){
			dialogCancel();
			tsCnt1=0;
			dflag1="false";
			}else{
			tsCnt+=1;
			}
		}
	});
	function clear1Data(param)
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
		
</script>
<s:form id="ComplaintHistory" action="ComplaintHistory" cssClass="form">
<fieldset><legend class="ComplaintHistory"><s:property value="getText('ComplaintHistory.grpSearch')" /></legend>
           <s:hidden id="qString" name="qString"/>
<table cellpadding="2">
	<tr>
		<td style="text-align: right;"><s:label key="ComplaintHistory.lblStaticPhone_number"/>:</td>
		<td><s:textfield accesskey="search1" name="txtPhone_number" cssClass="idfield digits" maxlength = "15" cssStyle="width:120px;" /></td>
		<td style="text-align: right;"><s:label key="ComplaintHistory.lblStaticComplaint_id"/>:</td>
		<td><s:select accesskey="search1" name="cmbComplaint_id" cssClass="idselect" headerKey="" cssStyle="width:120px;" headerValue="%{getText('system.select')}" list="cmbServiceList" /></td>

		<td style="text-align: right;"><s:label key="ComplaintHistory.lblStaticComplaint_rate_id"/>:</td>
		<td><s:select accesskey="search1" name="cmbComplaint_rate_id" cssClass="idselect" headerKey="" cssStyle="width:120px;" headerValue="%{getText('system.select')}" list="cmbInboundComplaintRateList" /></td>
		<td style="text-align: right;"><s:label key="ComplaintHistory.lblStaticUnit_id"/>:</td>
		<td><s:select accesskey="search1" name="cmbUnit_id" cssClass="idselect" headerKey="" cssStyle="width:120px;" headerValue="%{getText('system.select')}" list="cmbUnitList" /></td>

	</tr>
	<tr>
		<td style="text-align: right;"><s:label key="ComplaintHistory.lblStaticAgent_id"/>:</td>
		<td><s:select accesskey="search1" name="cmbAgent_id" cssClass="idselect" headerKey="" cssStyle="width:120px;" headerValue="%{getText('system.select')}" list="cmbAgent_idList" /></td>

		<td style="text-align: right;"><s:label key="ComplaintHistory.lblStaticComplaint_type_id"/>:</td>
		<td><s:select accesskey="search1" name="cmbComplaint_type_id" cssClass="idselect" headerKey="" cssStyle="width:120px;" headerValue="%{getText('system.select')}" list="%{#{}}" /></td>

		<td style="text-align: right;"><s:label key="ComplaintHistory.lblStaticSolved_date"/>:</td>
		<td><s:textfield accesskey="search1" name="txtSolved_dateStart" cssStyle="width:100px;" cssClass="idfield date" maxLength="10" /> &nbsp;~&nbsp;
			<s:textfield accesskey="search1" name="txtSolved_dateOver" cssStyle="width:100px;" cssClass="idfield date" maxLength="10" />
		</td>	
		<td style="text-align: right;"><s:label key="ComplaintHistory.lblStaticComplaint_result_id"/>:</td>
		<td><s:select accesskey="search1" name="cmbComplaint_result_id_search" cssClass="idselect" cssStyle="width:120px;" headerKey="" headerValue="%{getText('system.select')}" list="cmbInboundComplaintResultList" /></td>

	</tr>
	<tr>
		<td colspan="8" align="center">
		<s:submit name="btnSearch" key="ComplaintHistory.btnSearch" cssClass="button"
			onclick="searchGridData('grdComplaint_history', 'ComplaintHistory_getXML.action', 'ComplaintHistory');return false;" />
		<s:submit name="btnClearSearch" id="btnClearSearch" key="ComplaintHistory.btnClear" cssClass="button" onclick="clearSearchData();return false;" /></td>
	</tr>
</table>
</fieldset>

<fieldset>
	<legend class="ComplaintHistory">
		<s:property value="getText('ComplaintHistory.grpComplaint_list')" />
	</legend>
	
	<table width="100%">
		<tr>
			<td align='center'>
			<s:label name="lblResult1" id="lblResult1"  cssClass="error_message"/>
	<s:actionerror name="errorMessage"  cssClass="error_message"/>
			</td>
		</tr>
	</table>
	<table id="grdComplaint_history"></table>
	<div id="pager_grdComplaint_history"></div>
	<script type="text/javascript">
                    jQuery("#grdComplaint_history").jqGrid({
                    url:'ComplaintHistory_getXML.action?searchClick=true',
                    datatype: "xml",
                    height: 200,
                    width: 1090,
                    colNames:['<s:text name="ComplaintHistory.grdPHONE_NUMBER_ONE"/>',
                    '<s:text name="ComplaintHistory.grdAGENT_ID"/>',
                    '<s:text name="ComplaintHistory.grdCOMPLAINT_ID"/>',
                    '<s:text name="ComplaintHistory.grdPARENT_COMPLAINT_ID"/>',
                    '<s:text name="ComplaintHistory.grdCOMPLAINT_RATE_ID"/>',
                    '<s:text name="ComplaintHistory.grdSOLVED_DATE"/>',
                    '<s:text name="ComplaintHistory.grdUNIT_ID"/>',
                    '<s:text name="ComplaintHistory.grdCOMPLAINT_RESULT_ID"/>',
                    '','','','','','','','',
                    '<s:text name="ComplaintHistory.grdSOURCE_NAME"/>',
                    ],
                    colModel:[
                             {name :'PHONE_NUMBER', index : 'PHONE_NUMBER', width:75, hidden: false},
                             {name :'AGENT_NAME', index : 'AGENT_ID', width:75, hidden: false},
                             {name :'SERVICE_NAME', index : 'SERVICE_NAME', width:150, hidden: false},
                             {name :'DETAILED_SERVICE_NAME', index : 'DETAILED_SERVICE_NAME', width:150, hidden: false},
                             {name :'CALL_RATE', index : 'CALL_RATE', width:125, hidden: false},
                             {name :'SOLVED_DATE', index : 'SOLVED_DATE', width:100, hidden: false},
                             {name :'UNIT_NAME', index : 'UNIT_NAME',width:75, hidden: false},
                             {name :'CALL_RESULT', index : 'CALL_RESULT', width:100, hidden: false},
                             {name :'COMPLAINT_LIST_ID', index : 'COMPLAINT_LIST_ID', hidden: true},
                             {name :'CALLER_NAME', index : 'CALLER_NAME', hidden: true},
                             {name :'CALLER_EMAIL', index : 'CALLER_EMAIL', hidden: true},
                             {name :'SOLVED_COMMENT', index : 'SOLVED_COMMENT', hidden: true},
                             {name :'COMPLAINT_COMMENT', index : 'COMPLAINT_COMMENT', hidden: true},
                             {name :'EXPENSE', index : 'EXPENSE', hidden: true},
                             {name :'CALL_SORT_ID', index : 'CALL_SORT_ID', hidden: true},
                             {name :'CALL_RESULT_ID', index : 'CALL_RESULT_ID', hidden: true},
                             {name :'SOURCE_NAME', index : 'SOURCE_NAME', width:70, hidden: false} ],
                    rowNum:100,
                    rowTotal: 2000,
                    mtype: "GET",
                    rownumbers: true,
                    rownumWidth: 30,
                    gridview: true,
                    pager:  '#pager_grdComplaint_history',
                    viewrecords: true,
                    sortorder: "asc",
                    loadComplete: function(){
                 
			                    	 $("tr.jqgrow:odd").css("background", "#EAF6F1");
			                    
                    				 document.getElementById("lblResult1").innerHTML = "" ;

                          		     if (jQuery("#grdComplaint_history").getGridParam("records") == 0)
									 {
										document.getElementById("lblResult1").innerHTML = "<s:text name="system.WRN007"/>";
									 }
                    },
                    onSelectRow: function(id){
                    		$("[name=cmbComplaint_sort_id]").attr("disabled","disabled");
                    		$("[name=cmbComplaint_result_id]").attr("disabled","disabled");
                    		$("[name=txtEmail1]").attr("disabled","disabled");
                    		$("[name=txtSolved_date1]").attr("disabled","disabled");
                    		$("[name=txtComplaint_expense1]").attr("disabled","disabled");
                    		$("[name=txtSolved_comment]").attr("disabled","disabled");
                    		$("[name=btnEdit]").attr("disabled","disabled");
                    		$("[name=btnDelte]").attr("disabled","disabled");
                    		var rowData = $("#grdComplaint_history").jqGrid('getRowData', id);
	                    	  $("label[id=lblPhone_number]").html(rowData['PHONE_NUMBER']);
							  $("label[id=lblAgent_id]").html(rowData['AGENT_NAME']);
							  $("label[id=lblComplaint_id]").html(rowData['SERVICE_NAME']);
							  $("label[id=lblComplaint_type_id]").html(rowData['DETAILED_SERVICE_NAME']);
							  $("label[id=lblComplaint_rate_id]").html(rowData['CALL_RATE']);
							  $("label[id=lblUnit_id]").html(rowData['UNIT_NAME']);
							  $("select[name=cmbComplaint_result_id]").val(rowData['CALL_RESULT']);
							  $("input[name=txtSolved_date1]").val(rowData['SOLVED_DATE']);
	                    	  $("input[name=hddComplaint_id1]").val(rowData['COMPLAINT_LIST_ID']);
	                    	  $("label[id=lblCaller_name]").html(rowData['CALLER_NAME']);
	                    	  $("input[name=txtEmail1]").val(rowData['CALLER_EMAIL']);
	                    	  $("textarea[name=txtSolved_comment]").html(rowData['SOLVED_COMMENT']);
	                    	  $("label[id=lblComplaint_comment]").html(rowData['COMPLAINT_COMMENT']);
	                    	  $("input[name=txtComplaint_expense1]").val(rowData['EXPENSE']);
	                    	  $("select[name=cmbComplaint_sort_id]").val(rowData['CALL_SORT_ID']);
	                    	  $("select[name=cmbComplaint_result_id]").val(rowData['CALL_RESULT_ID']);
	                    	  
	                    }
                    });
     </script>
</fieldset>
<fieldset style="height:100px;word-wrap: break-word;">
	<legend class="ComplaintHistory">
		<s:property value="getText('ComplaintHistory.grpComplaint_comment')" />
	</legend>
	<s:label accesskey="edit" name="lblComplaint_comment" id="lblComplaint_comment" value="%{getText('ComplaintHistory.lblComplaint_comment')}" />
</fieldset>

		<fieldset>
			<legend class="ComplaintHistory">
				<s:property value="getText('ComplaintHistory.grpEdit_Complaint')" />
			</legend>
		<table width="100%" cellpadding="2">
			<tr>
				<td colspan="6" align="center">
					<s:label id="system.success" name="system.success"/><label id="message_label1"></label>
				</td>
			</tr>
			<tr>
				<td width="120px"  style="background-color:#EFEFEF;"><s:label key="ComplaintHistory.lblStaticPhone_number"/>:</td>
				<td width="240px"><b><s:label accesskey="edit" name="lblPhone_number" id="lblPhone_number"  /></b></td>
				<td width="140px"  style="background-color:#EFEFEF;"><s:label key="ComplaintHistory.lblStaticCaller_name"/>:</td>
				<td width="240px"><b><s:label accesskey="edit" name="lblCaller_name" id="lblCaller_name"  /></b></td>
				<td width="210px"  style="background-color:#EFEFEF;"><s:label key="ComplaintHistory.lblStaticComplaint_id"/>:</td>
				<td width="240px"><b><s:label accesskey="edit" name="lblComplaint_id" id="lblComplaint_id"  /></b></td>
			</tr>
			<tr>
				<td  style="background-color:#EFEFEF;"><s:label key="ComplaintHistory.lblStaticAgent_id"/>:</td>
				<td><b><s:label accesskey="edit" name="lblAgent_id" id="lblAgent_id" /></b></td>
				<td  style="background-color:#EFEFEF;"><s:label key="ComplaintHistory.lblStaticUnit_id"/>:</td>
				<td><b><s:label accesskey="edit" name="lblUnit_id" id="lblUnit_id" /></b></td>
				<td  style="background-color:#EFEFEF;"><s:label key="ComplaintHistory.lblStaticComplaint_type_id"/>:</td>
				<td><b><s:label accesskey="edit" name="lblComplaint_type_id" id="lblComplaint_type_id" /></b></td>
			</tr>
			<tr>
				<td  style="background-color:#EFEFEF;"><s:label key="ComplaintHistory.lblStaticComplaint_rate_id"/>:</td>
				<td><b><s:label accesskey="edit" name="lblComplaint_rate_id" id="lblComplaint_rate_id" /></b></td>
				<td width="200px"  style="background-color:#EFEFEF;"><s:label key="ComplaintHistory.lblStaticComplaint_sort_id"/>:</td>
				<td><s:select accesskey="edit" name="cmbComplaint_sort_id" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbInboundComplaintSortList" /></td>
				<td  style="background-color:#EFEFEF;"><s:label key="ComplaintHistory.lblStaticComplaint_result_id"/>:</td>
				<td><s:select accesskey="edit" name="cmbComplaint_result_id" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbInboundComplaintResultList" /></td>
			</tr>
			<tr>
				<td style="background-color:#EFEFEF;"><s:label name="lblStaticEmail" value="%{getText('ComplaintHistory.lblStaticEmail')}" />:</td>
				<td><s:textfield accesskey="edit" name="txtEmail1" maxlength = "100" cssClass="idfield" /></td>
				<td style="background-color:#EFEFEF;"><s:label name="lblStaticSolved_date" value="%{getText('ComplaintHistory.lblStaticSolved_date')}" />:</td>
				<td><s:textfield accesskey="edit" name="txtSolved_date1" cssClass="idfield date" maxLength="10" /></td>
				<td style="background-color:#EFEFEF;"><s:label name="lblStaticComplaint_expense" value="%{getText('ComplaintHistory.lblStaticComplaint_expense')}" />:</td>
				<td><s:textfield accesskey="edit" name="txtComplaint_expense1" cssClass="idfield digits" />
				<s:hidden name="hddComplaint_id1"/>
				</td>
			</tr>
			<tr>
				<td colspan="6"><s:label name="lblStaticSolved_comment" value="%{getText('ComplaintHistory.lblStaticSolved_comment')}" /></td>
			</tr>
			<tr>
				<td colspan="6" class="no-wrap"><s:textarea cols="500" accesskey="edit" id="txtArea" cssStyle="width: 100%; height:50px;" name="txtSolved_comment" cssClass="idfield" maxlength="500"/>
				</td>
				
			</tr>
			<tr>
				<td colspan="6" align="center" class="no-wrap">
					<s:submit name="btnEdit" key="ComplaintHistory.btnEdit" cssClass="button" onclick="saveComplaint(); return false;"/>
					<s:submit name="btnDelte" key="ComplaintHistory.btnDelete" cssClass="button" onclick="deleteComplaint(); return false;"/>
					<s:submit name="btnClear" key="ComplaintHistory.btnClear" cssClass="button" onclick="clearComplaint(); return false;" />
				</td>
			</tr>
		</table>
		</fieldset>
		<div id="delete_confirm_dialog1" style="background:#E6E6E6;display:none;position:absolute;border: 1px solid;">
			<table cellpadding=10>
				<tr>
					<td colspan="2" align="center">
						<s:property value="getText('ComplaintHistory.delete_confirm')"/>
					</td>
				</tr>
				<tr>
					<td>
						<button name="delete_confirm" class="button" onclick="deleteData();return false;"><s:property value="getText('ComplaintHistory.btnDelete_confirm')"/></button>
					</td>
					<td>
						<button name="delete_cancel" class="button" onclick="dialogCancel();return false;"><s:property value="getText('ComplaintHistory.btnDelete_cancel')"/></button>
					</td>
				</tr>
			</table>
		</div>
</s:form>

