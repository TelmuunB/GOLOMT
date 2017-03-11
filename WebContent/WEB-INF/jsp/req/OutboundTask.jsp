<!--
* Моделийн нэр             CallerHistory.jsp
*
* Функцын нэр          【Үйлчлүүлэгчийн түүх】
* Тvvх
* Хувилбарын №  Огноо    Хариуцагч    Агуулга
* 01.00.00                 Огноо    Хариуцагч    Шинээр хийсэн/Зассан
* 01.00.01				2013.03.01	Б.Тэлмүүн		Шинээр үүсгэх
* 02.00.01				2013.03.01	Э.Бат-Эрдэнэ 		Зассан
*
*-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	$(document).ready(function(){
	
		$("#OutboundTask").bind("keyup", function(){
					$("#OutboundTask").css({"border-color":""});
					maxlength = $(this).attr("cols");
					if($(this).val().length 	> maxlength){
						value = $(this).val().slice(0,maxlength); 
						$(this).val(value);
						
						$("#OutboundTask").css({"border-color":"red"});
					}
			});
									
		digits();
		texts();
		if($("#cmbTask_list").val()!=null){
			$("#cmbTask_list").trigger("change");
		}
		
	});
	
		function openInsertTaskDialog(){
			var url='SendSMS.action?phone_number='+$("[name=phone_number]").val();
			$("#smsDemoDialog").html("") ;
			$("#smsDemoDialog").dialog({
				height:305,
				width:400,
				overflow:'none',
				modal: true				
			});
			$("#smsDemoDialog").load(url, function() {	
					var dialog = $("#smsDemoDialog") ;
        			formatter(dialog);
       		});			
		}
		
	<s:if test="%{getCall_req_type_id() == 3}">
		$("div#Call_req_type_id4").hide();
		$("div#Call_req_type_id0").hide();
	    $("div#Call_req_type_id3").show();
	    <s:if test="type != 'outview'">
	    	$("select[name=cmbCall_sort_id]").removeAttr('disabled');
	    </s:if>
	</s:if>
	<s:elseif test="%{getCall_req_type_id() == 4}">
		$("div#Call_req_type_id3").hide();
		$("div#Call_req_type_id0").hide();
		$("div#Call_req_type_id4").show();
		<s:if test="type != 'outview'">
			$("select[name=cmbCall_sort_id]").removeAttr('disabled');
		</s:if>
	</s:elseif>
	<s:else>
		$("div#Call_req_type_id4").hide();
		$("div#Call_req_type_id3").hide();
		$("div#Call_req_type_id0").show();
		$("select[name=cmbCall_sort_id]").attr('disabled','disabled');
	</s:else>
	
	<s:if test="type == 'outview'">
		$("select[name=cmbCall_sort_id]").attr('disabled','disabled');
		$("[name=phone_number2]").attr('disabled','disabled');
		$("[name=caller_name]").attr('disabled','disabled');
		$("[name=cmbCall_status_id]").attr('disabled','disabled');
		$("[name=cmbCall_result_id]").attr('disabled','disabled');
		$("[name=cmbCall_rate_id]").attr('disabled','disabled');
		$("[name=call_comment]").attr('disabled','disabled');
	</s:if>

	
	if($("[name=ag_agent_permission]").val()=="1"){
		$("select[name=cmbCall_sort_id]").attr('disabled','');
		$("[name=phone_number2]").attr('disabled','');
		$("[name=caller_name]").attr('disabled','');
		$("[name=cmbCall_status_id]").attr('disabled','');
		$("[name=cmbCall_result_id]").attr('disabled','');
		$("[name=cmbCall_rate_id]").attr('disabled','');
		$("[name=call_comment]").attr('disabled','');
	}
	
	
	
	function selected(){
		var value=$("[name=cmbTask_list]").val();
		$.ajax(
	    {
	    	type: "POST",
	    	async: false,
	  		url: "OutboundTask_OutboundData",
	  		cache: false,
	     	data: {"taskID":value},
	     	dataType: 'json',
	       	success: function(result)
	        	     {
	        	     	var area=result.outboundResultList;
	        	     	for(key in area){
	        	     		switch(key){
	        	     			case "CALL_REQ_TYPE_ID":
		        	     				$("[id=Call_req_type_id]").val(area[key]);
		        	     				
		        	     				if(area[key]==3) {
		        	     					$("div#Call_req_type_id4").hide();
		        	     					$("div#Call_req_type_id0").hide();
	        	     						$("div#Call_req_type_id3").show();
	        	     						 <s:if test="type != 'outview'">
	        	     							$("select[name=cmbCall_sort_id]").removeAttr('disabled');
	          	     						 </s:if>
		        	     				} else if(area[key]==4)
		        	     					{
		        	     						$("div#Call_req_type_id3").hide();
		        	     						$("div#Call_req_type_id0").hide();
		        	     						$("div#Call_req_type_id4").show();
		        	     						<s:if test="type != 'outview'">
			        	     						$("select[name=cmbCall_sort_id]").removeAttr('disabled');
	        	     							</s:if>
		        	     					}else{
		        	     						$("div#Call_req_type_id4").hide();
		        	     						$("div#Call_req_type_id3").hide();
		        	     						$("div#Call_req_type_id0").show();
		        	     						<s:if test="type != 'outview'">
			        	     						$("select[name=cmbCall_sort_id]").attr('disabled','disabled');
	        	     							</s:if>
		        	     					} 
		        	     				
		        	     				break;
	        	     				
	        	     			case "CALL_REQ_TYPE_NAME":
	        	     				$("[id=Call_req_type_name]").html(area[key]);
	        	     				break;
	        	     			case "CALL_HISTORY_ID":
	        	     				$("[id=outbound_id]").val(area[key]);
	        	     				break;
	        	     			case "PHONE_NUMBER_ONE":
	        	     				$("[id=phone_number]").html(area[key]);
	        	     				break;
	        	     			case "PHONE_NUMBER_TWO":
        	     					$("#phone_number2").val(area[key]);
	        	     				break;
	        	     			case "CALLER_NAME":
	        	     				$("#caller_name").val(area[key]);
		        	     			break;
	        	     			case "TASK_NAME":
	        	     				$("[id=Call_name]").html(area[key]);
	        	     				break;
	        	     			case "DEADLINE":
	        	     				$("[id=Deadline]").val(area[key]);
	        	     				break;
	        	     			case "TASK_COMMENT":
	        	     				$("[id=Task_comment]").html(area[key]);
	        	     				break;
	        	     		}
	        	     	}
	        	   	 },
	        error: function (xhr, ajaxOptions, thrownError) {

		    }
	        	   	 
	    });
	}

	<s:if test="type == 'out' && view_status !=1 && getStatus() != 'false'" >	
		$(function() {
			
			var call_start_time = new Date() ;

			setInterval(function() {
				
				var cur = new Date() ;
				var sec_numb = Math.floor((cur.getTime() - call_start_time.getTime()) / 1000) ;

			    var hours   = Math.floor(sec_numb / 3600);
			    var minutes = Math.floor((sec_numb - (hours * 3600)) / 60);
			    var seconds = sec_numb - (hours * 3600) - (minutes * 60);

			    if (hours   < 10) {hours   = "0"+hours;}
			    if (minutes < 10) {minutes = "0"+minutes;}
			    if (seconds < 10) {seconds = "0"+seconds;}

			    var time    = hours+':'+minutes+':'+seconds;
				$("#duration_time").html(time) ;
			}, 100) ;
		});
	</s:if>
	
	<s:if test="calltype == 1">
		$("#cmbTask_list").attr('disabled','');
		$("#btnCall").hide();
		$("#btnMessage").hide();
	</s:if>
	
	<s:if test="getCallEnd() =='true' || type == 'out'">
		$("#btnCall").hide();
		$("#btnMessage").hide();
	</s:if>
	
	function call(){
		$("#outtaskloader input[name=type]").val('out');
		$("#outtaskloader input[name=phone_number]").val($("[name=phone_number]").val());
		
	}
	
</script>
	<s:hidden name="Call_req_type_id" id="Call_req_type_id"/>
	<s:hidden name="call_history_id" id="call_history_id"/>
	<s:hidden name="task_ID" id="task_ID"/>
<table>
	<tr>
		<td width="490px">
		<fieldset><legend><s:property value="getText('OutboundTask.grpCall_information')" /></legend>
			<table cellpadding="2" border="0">
				<tr>
					<td>
					</td>
					<td>
						<s:select id="cmbTask_list" name="cmbTask_list" onchange="selected()" cssClass="idselect" list="%{cmbTask_listList}" cssStyle="width:210;" /> 
						<s:hidden name="ag_agent_permission" val=""/>
					</td>
					<td width="50px">
						<!--<form action="mainMenu" id="outtaskloader" method="get">
							<s:hidden name="type"/> <s:hidden name="phone_number"/>
							<s:submit type="image" src='Images/phone.png' name="btnCall" id="btnCall" key="OutboundTask.btnCall" onclick="call()"  cssStyle="width:30px;height:30px;margin:0 15px 0 15px;" /> 
						</form>
					--></td>
					<td>
						<s:submit type="image" src='Images/sms.png' name="btnMessage" id="btnMessage" key="OutboundTask.btnMessage" cssStyle="width:30;height:30px;" onclick="openInsertTaskDialog(); return false;"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" width="50%"><s:label value="Дуудлагын ID" />: <b><s:label  name="callID" id="callID"/></b></td>
					<td colspan="3" width="50%"><s:label value="%{getText('OutboundTask.lblStaticAgent_id')}" />: 
						<b><s:label name="Agent_id" id="Agent_id" value="%{getAgentID()}"/></b>
					</td>
				</tr>
				<tr>
					<td><s:label value="%{getText('OutboundTask.lblStaticPhone_number')}" />:</td>
					<td><s:label value="%{getText('OutboundTask.lblStaticSecond_phone')}" />:</td>
					<td colspan="2"><s:label value="%{getText('OutboundTask.lblStaticCaller_name')}" />:</td>
				</tr>
				<tr>
					<td><b><s:label name="phone_number" id="phone_number"/></b></td>
					<td><s:textfield name="phone_number2" id="phone_number2" maxlength = "15" onchange="saveOnChange('OutboundTask_saveUserOnChange', {phone_number: '%{phone_number}', phone_number2:this.value})" cssClass="idfield digits" cssStyle="width:100;" /></td>
					<td colspan="2"><s:textfield name="caller_name" id="caller_name" maxlength = "100" onchange="saveOnChange('OutboundTask_saveUserOnChange', {phone_number: '%{phone_number}', caller_name:this.value})" cssClass="idfield texts" cssStyle="width:150;" /></td>
				</tr>
				<tr>
					<td><s:label value="%{getText('OutboundTask.lblStaticCall_start_time')}" />:</td>
					<td>
						<b>
							<s:label name="call_start_time" cssClass="ui-field-label"/>&nbsp;~&nbsp;<s:label name="call_finished_time" cssClass="ui-field-label"/>
						</b>
					</td>
					<td colspan="2">
						<s:label value="%{getText('OutboundTask.lblStaticDuration_time')}" />: <b><s:label name="duration_time" id="duration_time" cssClass="ui-field-label"/></b>
					</td>
				</tr>
			</table>
		</fieldset>
		<fieldset><legend><s:property value="getText('OutboundTask.grpCall_registration')" /></legend>
			<table width="100%" cellpadding="2">
				<tr>
					<td style="text-align: right;" width="50%">
						<s:label value="%{getText('OutboundTask.lblStaticCall_req_type_id')}" />:</td>
					<td><b><s:label name="Call_req_type_name" id="Call_req_type_name" /></b></td>
				</tr>
				<tr>
					<td style="text-align: right;"><s:label value="%{getText('OutboundTask.lblStaticCall_status_id')}" />:</td>
					<td>
						<s:select name="cmbCall_status_id" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbCallStatList" >
							<s:param name="onchange">saveOnChange('OutboundTask_saveOnChange', {callID: '<s:property value="callID"/>', cmbCall_status_id:this.value, Call_req_type_id:document.getElementById("Call_req_type_id").value, taskID:document.getElementById("cmbTask_list").value})</s:param>
						</s:select>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;"><s:label value="%{getText('OutboundTask.lblStaticCall_sort_id')}" />:</td>
					<td>
						<div id="Call_req_type_id3" hide="false">
							<s:select name="cmbCall_sort_id" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="%{cmbInboundAppealSortList}" >
								<s:param name="onchange">saveOnChange('OutboundTask_saveOnChange', {callID: '<s:property value="callID"/>', cmbCall_sort_id:this.value, Call_req_type_id:document.getElementById("Call_req_type_id").value, taskID:document.getElementById("cmbTask_list").value})</s:param>
							</s:select>
						</div>
						<div id="Call_req_type_id4" hide="false">
							<s:select name="cmbCall_sort_id" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="%{cmbInboundComplaintSortList}" >
								<s:param name="onchange">saveOnChange('OutboundTask_saveOnChange', {callID: '<s:property value="callID"/>', cmbCall_sort_id:this.value, Call_req_type_id:document.getElementById("Call_req_type_id").value, taskID:document.getElementById("cmbTask_list").value})</s:param>
							</s:select>
						</div>
						<div id="Call_req_type_id0" hide="false">
							<s:select name="cmbCall_sort_id" cssClass="idselect" headerKey="" disabled="true" headerValue="%{getText('system.select')}" list="%{#{}}" />
						</div>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;"><s:label value="%{getText('InboundInforeq.lblStaticCall_name')}" />:</td>
					<td><b><s:label name="Call_name" id="Call_name"  /></b></td>
				</tr>
				<tr>
					<td style="text-align: right;"><s:label value="%{getText('OutboundTask.lblStaticCall_result_id')}" />: </td>
					<td>
						<s:select name="cmbCall_result_id" id="cmbCall_result_id" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbCall_Out_result_idList" >
							<s:param name="onchange">saveOnChange('OutboundTask_saveOnChange', {callID: '<s:property value="callID"/>', cmbCall_result_id:this.value, Call_req_type_id:document.getElementById("Call_req_type_id").value, taskID:document.getElementById("cmbTask_list").value})</s:param>
						</s:select>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;"><s:label value="%{getText('OutboundTask.lblStaticCall_rate_id')}" />: </td>
					<td>
						<s:if test="%{getCall_req_type_id() == 4}">
							<s:select name="cmbCall_rate_id" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbInboundComplaintRateList" >
								<s:param name="onchange">saveOnChange('OutboundTask_saveOnChange', {callID: '<s:property value="callID"/>', cmbCall_rate_id:this.value, Call_req_type_id:document.getElementById("Call_req_type_id").value, taskID:document.getElementById("cmbTask_list").value})</s:param>
							</s:select>
						</s:if>
						<s:else>
							<s:select name="cmbCall_rate_id" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbRateList" >
								<s:param name="onchange">saveOnChange('OutboundTask_saveOnChange', {callID: '<s:property value="callID"/>', cmbCall_rate_id:this.value,Call_req_type_id:document.getElementById("Call_req_type_id").value, taskID:document.getElementById("cmbTask_list").value})</s:param>
							</s:select>
						</s:else>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;"><s:label value="%{getText('OutboundTask.lblStaticDeadline')}" />:</td>
					<td><b><s:label name="Deadline" id="Deadline" /></b></td>
				</tr>
				<tr>
					<td style="text-align: right;"><s:label value="%{getText('OutboundTask.lblStaticTask_comment')}" />:</td>
					<td><b><s:label name="Task_comment" id="Task_comment" /></b></td>
				</tr>
				<tr>
					<td width="50%"><s:label value="%{getText('OutboundTask.lblStaticOutbound_comment')}" />:</td>
				</tr>
				<tr>
					<td colspan="2">
							<s:textarea cols="500" id="OutboundTask" accesskey="register" cssStyle="resize: none; overflow: auto; width: 100%; height:100px;" name="call_comment" cssClass="idfield OutboundTask">
								<s:param name="disabled" value="'true'.equals(status)"/>
								<s:param name="onchange">saveOnChange('OutboundTask_saveOnChange', {callID: '<s:property value="callID"/>', call_comment:this.value, Call_req_type_id:document.getElementById("Call_req_type_id").value, taskID:document.getElementById("cmbTask_list").value})</s:param>
							</s:textarea>
	             	</td>
				</tr>

			</table>
		</fieldset>
		</td>
		<td style="width:600px; margin-right:-5px;">
			<iframe src="HelpTree.action" frameborder="0" width="600px" height="588px" scrolling="no">
			</iframe>
		</td>
	</tr>
</table>
 		<table>
        		<tr>
	           			<td>
				           <table id="grdInCall_history"></table><div id="pager_grdInCall_history" ></div>
			               <script type="text/javascript">
			                    jQuery("#grdInCall_history").jqGrid({
			                    url:'OutboundTask_getXML.action',
			                    datatype: "xml",
			                    postData: {callID:"<s:property value='callID'/>"},
			                    height: 155,
			                    width:700,
			                    colNames:[
			                    '',
			                    '<s:text name="CallHistory.grdPHONENUMBER"/>',
			                    '<s:text name="CallHistory.grdCALLER_NAME"/>',
			                    '<s:text name="CallHistory.grdSTART_TIME"/>',
			                    '<s:text name="CallHistory.grdFINISH_TIME"/>',
			                    '<s:text name="CallHistory.grdDURATION"/>',
			                    '<s:text name="CallHistory.grdCALLTYPE"/>',
			                    '<s:text name="CallHistory.grdAGENT_NAME"/>',
			                    '<s:text name="CallHistory.grdPHONENUMBER2"/>',
			                    ''],
			                    colModel:[
										 {name:"CALL_HISTORY_ID",index : "CALL_HISTORY_ID", align:"center", hidden:true},
			                             {name:"PHONENUMBER",index:"PHONENUMBER" , width:80, align:"center"},
			                             {name:"CALLERNAME",index:"CALLERNAME", width:120, align:"center"},
			                             {name:"START_TIME",index:"START_TIME", width:140, align:"center"},
			                             {name:"FINISHED_TIME",index:"FINISHED_TIME", width:140, align:"center"},
			                             {name:"DURATION",index:"DURATION", width:80, align:"center"},
			                             {name:"CALLTYPE",index:"CALLTYPE", width:80, align:"center"},
			                             {name:"AGENT_NAME",index:"AGENT_NAME", width:130, align:"center"},
			                             {name:"PHONENUMBER2",index:"PHONENUMBER2", width:80, align:"center"},
			                             {name:"VIEW_STATUS",index:"VIEW_STATUS", hidden:true}],
			                    rowNum:12,
			                    rowTotal: 2000,
			                    mtype: "GET",
			                    rownumbers: true,
			                    rownumWidth: 40,
			                    gridview: true,
			                    pager:  '#pager_grdInCall_history',
			                    viewrecords: true,
			                    sortorder: "desc",
			                    sortname: 'FINISHED_TIME',
			                    shrinkToFit:false,

			                    loadComplete: function(){

			                    	 $("tr.jqgrow:odd").css("background", "#EAF6F1");

							         var ids = jQuery("#grdInCall_history").getDataIDs();
							         var data;
							         for(var i=0;i<ids.length;i++)
							         {
							        	data = jQuery("#grdInCall_history").getRowData(ids[i]);

							        	if(data['CALLTYPE'] == 0)
							        	{
							        		co = "<img src='Images/inbound.png'  title='<s:text name="CallHistory.ico_inbound" />' />";
							        	}
							        	else if(data['CALLTYPE'] == 1)
							        	{
							        		co = "<img src='Images/outbound.png'  title='<s:text name="CallHistory.ico_outbound" />' />";
							        	}
							        	else if(data['CALLTYPE'] == 3)
							        	{
							        		co = "<img src='Images/MissedCall.png'  title='<s:text name="CallHistory.ico_missed" />' />";
							        	}
							        	else
							        	{
							        		co = "<img src='Images/sms-ico.png'  title='<s:text name="CallHistory.ico_message" />' />";
							        	}
										jQuery("#grdInCall_history").setRowData(ids[i],{CALLTYPE:co});
							         }

							        $("#grdInCall_history_detail").jqGrid("clearGridData");
							  },

							  onSelectRow: function(selectedRowId) {
							  		var data = $(this).jqGrid('getRowData', selectedRowId);

							  		searchGridData('grdInCall_history_detail', 'OutboundTask_getXMLSub.action', null, {callID : data['CALL_HISTORY_ID']});
							  }
		                    });
		                    </script>
			           </td>
			           <td>
				           <table id="grdInCall_history_detail"></table>
			               <script type="text/javascript">
			                    jQuery("#grdInCall_history_detail").jqGrid({
			                    url:'OutboundTask_getXMLSub.action',
			                    datatype: "xml",
			                    height: 180,
			                    width: 350,
			                    colNames:[
			                    '<s:text name="CallHistory.grdCALL_REQ_TYPE"/>',
			                    '<s:text name="CallHistory.grdSERVICE_TYPE1"/>',
			                    '<s:text name="CallHistory.grdDETAILED_SERVICE_TYPE1"/>',
			                    '<s:text name="CallHistory.grdCALL_COMMENT"/>',
			                    '<s:text name="CallHistory.grdCALL_NAME"/>',
			                    '<s:text name="CallHistory.grdCALL_RESULT_ID"/>',
			                    '<s:text name="CallHistory.grdCALL_RATE_ID"/>',
			                    '<s:text name="CallHistory.grdCALL_STAT_ID"/>',
			                    '<s:text name="CallHistory.grdCALL_SORT_ID"/>',
			                    '<s:text name="CallHistory.grdDEADLINE"/>',
			                    '<s:text name="CallHistory.grdUNIT_ID"/>',
			                    '<s:text name="CallHistory.grdSERVICE_TYPE2"/>',
			                    '<s:text name="CallHistory.grdDETAILED_SERVICE_TYPE2"/>',
			                    '<s:text name="CallHistory.grdSERVICE_TYPE3"/>',
			                    '<s:text name="CallHistory.grdDETAILED_SERVICE_TYPE3"/>',
			                    '<s:text name="CallHistory.grdSERVICE_TYPE4"/>',
			                    '<s:text name="CallHistory.grdDETAILED_SERVICE_TYPE4"/>',
			                    '<s:text name="CallHistory.grdSERVICE_TYPE5"/>',
			                    '<s:text name="CallHistory.grdDETAILED_SERVICE_TYPE5"/>',
			                    '<s:text name="CallHistory.grdTRANSFER_PHONENUMBER"/>'
			                    ],
			                    colModel:[
				                             {name:"CALL_REQ_TYPE_NAME", width:120, align:"center"},
				                             {name:"SERVICE_NAME1", width:120, align:"center"},
				                             {name:"DETAILED_SERVICE_NAME1", width:120, align:"center"},
				                             {name:"CALL_COMMENT", width:120, align:"center"},
				                             {name:"CALL_NAME", width:120, align:"center"},
				                             {name:"CALL_RESULT_NAME", width:120, align:"center"},
				                             {name:"CALL_RATE_NAME", width:120, align:"center"},
				                             {name:"CALL_STAT_NAME", width:120, align:"center"},
				                             {name:"CALL_SORT_NAME", width:120, align:"center"},
				                             {name:"DEADLINE", width:120, align:"center"},
				                             {name:"UNIT_NAME", width:120, align:"center"},
				                             {name:"SERVICE_NAME2", width:120, align:"center"},
				                             {name:"DETAILED_SERVICE_NAME2", width:120, align:"center"},
				                             {name:"SERVICE_NAME3", width:120, align:"center"},
				                             {name:"DETAILED_SERVICE_NAME3", width:120, align:"center"},
				                             {name:"SERVICE_NAME4", width:120, align:"center"},
				                             {name:"DETAILED_SERVICE_NAME4", width:120, align:"center"},
				                             {name:"SERVICE_NAME5", width:120, align:"center"},
				                             {name:"DETAILED_SERVICE_NAME5", width:120, align:"center"},
				                             {name:"TRANSFER_PHONENUMBER", width:120, align:"center"}
				                             ],
			                    rowTotal: 2000,
			                    mtype: "GET",
			                    gridview: true,
			                    viewrecords: true,
			                    sortorder: "asc",
			                    sortname:"CALL_REQ_TYPE",
			                    shrinkToFit:false,
			                    loadComplete: function(){

			                    	 $("tr.jqgrow:odd").css("background", "#EAF6F1");
			                    }
			                    });
			                    </script>

			           </td>
        		</tr>
        </table>
        
 <div id="smsDemoDialog"></div>     