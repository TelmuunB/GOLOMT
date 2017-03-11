﻿﻿<!--
* Моделийн нэр             TaskList.jsp
*
* Функцын нэр          【Даалгаврын жагсаалт】
* Тvvх
* Хувилбарын №  Огноо    Хариуцагч    Агуулга
* 01.00.00                 Огноо    Хариуцагч    Шинээр хийсэн/Зассан
* 01.00.01				2013.03.01	Б.Тэлмүүн		Шинээр үүсгэх
* 02.00.01				2013.03.04	Э.Бат-Эрдэнэ 		Зассан
*
*-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

	<script type="text/javascript">
		 $(document).ready(function(){
		 	$("input[name=txtPhone_number]").focus();
		 		digits();
				texts();
   			$.ajax({
				url: "TaskList",
				dataType: "xml",
				success: function(xmlResponse) {
					var data = $("rows", xmlResponse).map(function() {
						return {
							value: $("value", this).text(),
							id: $("id", this).text()
						};
					}).get();		
				}
			});
			$('.dropshadow').css('height', $('.infox-panelwindow').height());

			$('[name=btnChoose_task]').attr('disabled','disabled');

			for(var i=25; i<58; i++)
			{
				if(i%2==0){
					$("td:eq("+i+")").css("background-color", "#EFEFEF");
				}
			}
	
		});

		function Act(){
			$("[name=chkMine_task]").removeAttr('disabled');
           	$("[name=chkAverage_task]").removeAttr('disabled');
		}
		
		
		if($("select[name=cmbAgent_id]").val()!=''){
			$("[name=chkMine_task]").removeAttr('checked');
           	$("[name=chkAverage_task]").removeAttr('checked');
			$("[name=chkMine_task]").attr('disabled','disabled');
			$("[name=chkAverage_task]").attr('disabled','disabled');
		}
		
	</script>
			<form action="TaskList" id="tasklist">
           	   <fieldset><legend><s:property value="getText('TaskList.grpSearch')"/></legend>
           	   		<table cellpadding="2" class="" width="100%" border="0">
           	   			<tr>
           	   				<td style="text-align: right;">
           	   				    <s:label value="%{getText('TaskList.lblStaticPhone_number')}"/>:
           	   				</td>
           	   				<td>
           	   				    <s:textfield accesskey="search" name="txtPhone_number" maxlength = "15" cssClass="idfield digits"/>
           	   				</td>
           	   				<td style="text-align: right;">
           	   				    <s:label value="%{getText('TaskList.lblStaticAgent_id')}"/>:
           	   				</td>
           	   				<td>
           	   					<s:if test="%{getPermission()==0}">
           	   						<s:select accesskey="search" name="cmbAgent_id" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" disabled="true" list="cmbAgent_idList"/>
           	   					</s:if>
           	   					<s:elseif test="%{getPermission()==1}">
           	   						<s:select accesskey="search" name="cmbAgent_id" cssClass="idselect" onchange="Sel(this);" headerKey="" headerValue="%{getText('system.select')}" list="%{cmbAgent_idList}"/>
           	   						<script type="text/javascript">
           	   							function Sel(obj){
          	   								if($("select[name=cmbAgent_id]").val()!=''){
          	   									$("[name=chkMine_task]").removeAttr('checked');
          	   									$("[name=chkAverage_task]").removeAttr('checked');
												$("[name=chkMine_task]").attr('disabled','disabled');
												$("[name=chkAverage_task]").attr('disabled','disabled');
											}
											else{
												$("[name=chkMine_task]").removeAttr('disabled');
												$("[name=chkAverage_task]").removeAttr('disabled');
											}
           	   							}
           	   						</script>
           	   					</s:elseif>
           	   				</td>
           	   				<td style="text-align: right;">
               					<s:label value="%{getText('DistributeTask.lblStaticDeadline')}"/>:
               				</td>
               				<td>
               					<s:textfield accesskey="search" name="txtDeadlineStart" cssStyle="width:90px" cssClass="idfield date" maxLength="10"/>
               					&nbsp; ~ &nbsp;
               					<s:textfield accesskey="search" name="txtDeadlineEnd" cssStyle="width:90px" cssClass="idfield date" maxLength="10"/>
               				</td>
           	   			</tr>
           	   			<tr>
           	   				<td style="text-align: right;">
           	   				    <s:label value="%{getText('TaskList.lblStaticCaller_name')}"/>:
           	   				</td>
           	   				<td>
           	   				    <s:textfield accesskey="search" name="txtCaller_name" maxlength = "50" cssClass="idfield texts"/>
           	   				</td>
           	   				<td style="text-align: right;">
           	   				    <s:label value="%{getText('TaskList.lblStaticInsert_agent')}"/>:
           	   				</td>
           	   				<td>
           	   				    <s:select accesskey="search" name="cmbInsert_agent" cssStyle="width: 160px;" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="%{cmbInsert_agentList}"/>
           	   				</td>
           	   				<td style="text-align: right;">
           	   				    <s:label value="%{getText('TaskList.lblStaticCall_req_type_id_for_task')}"/>:
           	   				</td>
           	   				<td>
           	   				    <s:select accesskey="search" name="cmbCall_req_type_id" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbCallReqType"/>
           	   				</td>

           	   			</tr>
           	   			<tr>
           	   				<td style="text-align: right;">
           	   				    <s:label value="%{getText('TaskList.lblStaticTask_name')}"/>:
           	   				</td>
           	   				<td>
           	   				    <s:textfield accesskey="search" name="txtTask_name" maxlength = "100" cssClass="idfield"/>
           	   				</td>
           	   				<td style="text-align: right;">
           	   				    <s:checkbox accesskey="search" value="false" key="TaskList.chkMine_task" name="chkMine_task" cssClass="idcheck" />
           	   				</td>
           	   				<td style="text-align: left;">
           	   				    <s:checkbox accesskey="search" value="false" key="TaskList.chkAverage_task" name="chkAverage_task" id="chkAverage_task" cssClass="idcheck"/>
           	   				</td>
           	   				<td style="text-align: right;">
           	   				    <s:label value="%{getText('TaskList.lblStaticCall_result_id')}"/>:
           	   				</td>
           	   				<td>
           	   				    <s:select accesskey="search" name="cmbCall_result_id" cssStyle="width: 160px;" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="%{cmbCall_result_idList}"/>
           	   				</td>
           	   			</tr>
           	   			<tr>
           	   				<td colspan="6" align="center">
           	   					<s:submit name="btnSearch" key="TaskList.btnSearch" cssClass="button" onclick="searchGridData('grdResultTask', 'TaskList_getXML.action', 'tasklist'); return false;"/>
               					<s:reset key="TaskList.btnClear" cssClass="button" onclick="Act()"/>
           	   				</td>
           	   			</tr>
           	   		</table>
   				</fieldset>
   			</form>
   	
               <s:hidden id="qString" name="qString"/>
               <div style="text-align: center;height: 30px;">
			        <s:label name="lblResult" id="lblResult"  cssClass="error_message"/>
			   </div>
               <fieldset><legend><s:property value="getText('TaskList.grpTask_list')"/></legend>

               <div align="center" style="padding-bottom: 5px;">

	                <div align="center" class="multititle"><table id="grdResultTask"></table></div>
	                <div id="pager_grdResult"></div>
		               <script type="text/javascript">
		                    jQuery("#grdResultTask").jqGrid({
		                    url:'TaskList_getXML.action?searchClick=true',
		                    postData: {qString : $('#qString').val()},
		                    datatype: "xml",
		                    height: 250,
		                    width: 1020,
		                    colNames:[
				                     '1'
				                     ,'<s:text name="TaskList.grdPHONE_NUMBER_ONE" />'
				                     ,'<s:text name="TaskList.grdCALLER_NAME" />'
				                     ,'<s:text name="TaskList.grdTASK_NAME" />'
				                     ,'<s:text name="TaskList.grdAGENT_ID" />'
				                     ,'<s:text name="TaskList.grdAPPEAL_ID" />'
				                     ,'<s:text name="TaskList.grdDEADLINE" />'
				                     ,'<s:text name="TaskList.grdINSERT_AGENT" />'
				                     ,'<s:text name="TaskList.grdCALL_RESULT_ID" />'
				                     ,'<s:text name="TaskList.grdINSERT_DATE" />'
				                     ,'10'
				                     ,'11'
				                     ,'none'
				                     ,'none'
				                     ,'none'
				                     ,'none'
				                     ,'none'
				                     ,'none'
				                     ,'none'
				                     ,'none'
				                     ,'none'
				                     ,'none'
				                     ,'none'
				                     ,'none'
				                     ,'none'
				                     ,'none'
				                     ,'none'
				                     ,'none'
				                     ,'none'
				                     ],
		                    colModel:[
		                             {name : 'TASK_ID', index : 'TASK_ID', hidden: true },
		                             {name : 'PHONE_NUMBER_ONE', index : 'PHONE_NUMBER_ONE' , width:80 },
		                             {name : 'CALLER_NAME', index : 'CALLER_NAME' , width: 80 },
		                             {name : 'TASK_NAME', index : 'TASK_NAME' , width:90 },
		                             {name : 'AGENT_ID', index : 'AGENT_ID' , width:100 },
		                             {name : 'CALL_REQ_TYPE_NAME', index : 'CALL_REQ_TYPE_NAME' , width:100 },
		                             {name : 'DEADLINE', index : 'DEADLINE' , width:100 },
		                             {name : 'INSERT_AGENT', index : 'INSERT_AGENT' , width:100 },
		                             {name : 'CALL_RESULT_NAME', index : 'CALL_RESULT_NAME' , width:100 },
		                             {name : 'T_INSERT_DATE', index: 'T_INSERT_DATE', width:100 },
		                             {name : 'CALL_REQ_TYPE_ID', index : 'CALL_REQ_TYPE_ID' , hidden:true },
		                             {name : 'CALLTYPE', index : 'CALLTYPE' , hidden:true },
		                             {name : 'TASK_COMMENT', index : 'TASK_COMMENT' , hidden:true },
		                             {name : 'SERVICE_NAME1', index : 'SERVICE_NAME1' , hidden:true },
		                             {name : 'DETAILED_SERVICE_NAME1', index : 'DETAILED_SERVICE_NAME1' , hidden:true },
		                             {name : 'SERVICE_NAME2', index : 'SERVICE_NAME2' , hidden:true },
		                             {name : 'DETAILED_SERVICE_NAME2', index : 'DETAILED_SERVICE_NAME2' , hidden:true },
		                             {name : 'SERVICE_NAME3', index : 'SERVICE_NAME3' , hidden:true },
		                             {name : 'DETAILED_SERVICE_NAME3', index : 'DETAILED_SERVICE_NAME3' , hidden:true },
		                             {name : 'SERVICE_NAME4', index : 'SERVICE_NAME4' , hidden:true },
		                             {name : 'DETAILED_SERVICE_NAME4', index : 'DETAILED_SERVICE_NAME4' , hidden:true },
		                             {name : 'SERVICE_NAME5', index : 'SERVICE_NAME5' , hidden:true },
		                             {name : 'DETAILED_SERVICE_NAME5', index : 'DETAILED_SERVICE_NAME5' , hidden:true },
		                             {name : 'INSERT_DATE', index : 'INSERT_DATE' , hidden:true },
		                             {name : 'CALL_RATE_NAME', index : 'CALL_RATE_NAME' , hidden:true },
		                             {name : 'CALL_SORT_NAME', index : 'CALL_SORT_NAME' , hidden:true },
		                             {name : 'UNIT_NAME', index : 'UNIT_NAME' , hidden:true },
		                             {name : 'START_TIME', index : 'START_TIME' , hidden:true },
		                             {name : 'DURAT', index : 'DURAT' , hidden:true }
		                             ],
		                    rowNum:<s:text name="limitGrid" />,
		                    rowTotal: 2000,
		                    mtype: "GET",
		                    rownumbers: true,
		                    rownumWidth: 20,
		                    gridview: true,
		                    pager:  '#pager_grdResult',
		                    viewrecords: true,
		                    sortorder: "ASC",
		                    sortname: 'DEADLINE',
		                    loadComplete: function(){
		               
		               	    	 $("tr.jqgrow:odd").css("background", "#EAF6F1");
			                    
		                    },
		                    onSelectRow: function(selectedRowId) {
					                    $('[name=btnChoose_task]').removeAttr('disabled');
					                    $('[name=btnChoose_task]').attr("class", "button ui-button ui-widget ui-state-default ui-corner-all");
										document.getElementById("lblResult").innerHTML = "" ;
                    					document.getElementById("reg_error").innerHTML = "" ;
				                    	var data = $(this).jqGrid('getRowData', selectedRowId);
				                    	$("#taskloader input[name=type]").val('outview');
				                    	$("#taskloader input[name=phone_number]").val('<s:text name="agentID"/>');
				                    	$("#taskloader input[name=dnis]").val(data['PHONE_NUMBER_ONE']);
				                    	$('[id=lblPhone_number]').html(data['PHONE_NUMBER_ONE']);
				                    	$('[id=lblCaller_name]').html(data['CALLER_NAME']);
				                    	$('[id=lblTask_name]').html(data['TASK_NAME']);
				                    	$('[id=lblAgent_id]').html(data['AGENT_ID']);
				                    	$('[id=lblDeadline]').html(data['DEADLINE']);
				                    	$('[id=lblSecond_phone]').html(data['PHONE_NUMBER_TWO']);
				                    	$('[id=lblTask_comment]').html(data['TASK_COMMENT']);
				                    	$('[id=lblCall_req_type_id]').html(data['CALL_REQ_TYPE_NAME']);
				                    	$('[id=lblCall_type]').html(data['CALLTYPE']);
				                    	$('[id=lblInbound_service_id1]').html(data['SERVICE_NAME1']);
				                    	$('[id=lblDetailed_service_id1]').html(data['DETAILED_SERVICE_NAME1']);
				                    	$('[id=lblInbound_service_id2]').html(data['SERVICE_NAME2']);
				                    	$('[id=lblDetailed_service_id2]').html(data['DETAILED_SERVICE_NAME2']);
				                    	$('[id=lblInbound_service_id3]').html(data['SERVICE_NAME3']);
				                    	$('[id=lblDetailed_service_id3]').html(data['DETAILED_SERVICE_NAME3']);
				                    	$('[id=lblInbound_service_id4]').html(data['SERVICE_NAME4']);
				                    	$('[id=lblDetailed_service_id4]').html(data['DETAILED_SERVICE_NAME4']);
				                    	$('[id=lblInbound_service_id5]').html(data['SERVICE_NAME5']);
				                    	$('[id=lblDetailed_service_id5]').html(data['DETAILED_SERVICE_NAME5']);
				                    	$('[id=lblCall_result_id]').html(data['INSERT_DATE']);
				                    	$('[id=lblCall_start_time]').html(data['START_TIME']);
				                    	$('[id=lblCall_rate_id]').html(data['CALL_RATE_NAME']);
				                    	$('[id=lblCall_sort_id]').html(data['CALL_SORT_NAME']);
				                    	$('[id=lblUnit_id]').html(data['UNIT_NAME']);
				                    	$('[id=lblDuration_time]').html(data['DURAT']);
				                    },
				            ondblClickRow: function(selectedRowId) {
				            			document.getElementById("lblResult").innerHTML = "" ;
				            			document.getElementById("reg_error").innerHTML = "" ;
		                    			var data = $(this).jqGrid('getRowData', selectedRowId);
		                    			$("#taskloader input[name=phone_number]").val('<s:text name="agentID"/>');
				                    	$("#taskloader input[name=dnis]").val(data['PHONE_NUMBER_ONE']);
				                    	$("#taskloader input[name=type]").val('outview');
				                    	if(data['TASK_ID']!=null){
				                    		$("input[name=btnChoose_task]").trigger('click');
				                    	}else{ alert("Task id is not!!");	}
		                    },
			              	loadComplete: function(){
			              	 		$("tr.jqgrow:odd").css("background", "#EAF6F1");
			                    	document.getElementById("lblResult").innerHTML = "" ;
				                    if (jQuery("#grdResultTask").getGridParam("records") == 0)
										{
											document.getElementById("lblResult").innerHTML = "<s:text name="CompetentOfficial.MSG004"/>";
											$("#lblResult").html("<s:text name='system.WRN007'/>");
										}
									
								}
		                    });

		               </script>
	           </div>
			   </fieldset>
               <!--<s:hidden name="TaskId" id="TaskId" />-->
               <s:hidden name="pageLoaded" id="pageLoaded" />
               <table>
               		<tr>
               			<td>
               				<div id="reg_error" >
						        <s:actionerror name="error" cssClass="error_message" />
						        <s:actionmessage name="msg" cssClass ="error_message"  id="error_message"/>
				           	</div>
				        </td>
               		</tr>
               </table>
               <fieldset><legend><s:property value="getText('TaskList.grpTask_detail')"/></legend>
               <table class="" width="100%" cellpadding="4" border="0">
               		<tr>
               			<td style="text-align: right; ">
               			    <s:label value="%{getText('TaskList.lblStaticPhone_number')}"/>:
               			</td>
               			<td width="190px">
               			    <b><s:label name="lblPhone_number" id="lblPhone_number" /></b>
               			</td>
               			<td style="text-align: right;">
               			    <s:label value="%{getText('TaskList.lblStaticCall_req_type_id')}"/>:
               			</td>
               			<td width="190px">
               			    <b><s:label name="lblCall_req_type_id" id="lblCall_req_type_id" /></b>
               			</td>
               			<td style="text-align: right;">
               			    <s:label value="%{getText('TaskList.lblStaticUnit_id')}"/>:
               			</td>
               			<td width="190px">
               			    <b><s:label name="lblUnit_id" id="lblUnit_id" /></b>
               			</td>
               		</tr>
               		<tr>
               			<td style="text-align: right;">
               			    <s:label value="%{getText('TaskList.lblStaticSecond_phone')}"/>:
               			</td>
               			<td>
               			    <b><s:label name="lblSecond_phone" id="lblSecond_phone" /></b>
               			</td>
               			<td style="text-align: right;">
               			    <s:label value="%{getText('TaskList.lblStaticInbound_service_id')}"/>:
               			</td>
               			<td>
               			    <b><s:label name="lblInbound_service_id1" id="lblInbound_service_id1" /></b>,
               				<b><s:label name="lblInbound_service_id2" id="lblInbound_service_id2" /></b>,
               				<b><s:label name="lblInbound_service_id3" id="lblInbound_service_id3" /></b>,
               				<b><s:label name="lblInbound_service_id4" id="lblInbound_service_id4" /></b>,
               				<b><s:label name="lblInbound_service_id5" id="lblInbound_service_id5" /></b>
               				
               			</td>
               			<td style="text-align: right;">
               			    <s:label value="%{getText('TaskList.lblStaticCall_sort_id')}"/>:
               			</td>
               			<td>
               			    <b><s:label name="lblCall_sort_id" id="lblCall_sort_id" /></b>
               			</td>
               		</tr>
               		<tr>
               			<td style="text-align: right;">
               			    <s:label value="%{getText('TaskList.lblStaticCaller_name')}"/>:
               			</td>
               			<td>
               			    <b><s:label name="lblCaller_name" id="lblCaller_name" /></b>
               			</td>
               			<td style="text-align: right; ">
               			    <s:label value="%{getText('TaskList.lblStaticDetailed_service_id')}"/>:
               			</td>
               			<td>
               			    <b><s:label name="lblDetailed_service_id1" id="lblDetailed_service_id1" /></b>,
               			    <b><s:label name="lblDetailed_service_id2" id="lblDetailed_service_id2" /></b>,
               			    <b><s:label name="lblDetailed_service_id3" id="lblDetailed_service_id3" /></b>,
               			    <b><s:label name="lblDetailed_service_id4" id="lblDetailed_service_id4" /></b>,
               			    <b><s:label name="lblDetailed_service_id5" id="lblDetailed_service_id5" /></b>
               			</td>
               			<td style="text-align: right;">
               			    <s:label value="%{getText('TaskList.lblInsert_date')}"/>:
               			</td>
               			<td>
               			    <b><s:label name="lblCall_result_id" id="lblCall_result_id" /></b>
               			</td>
               		</tr>
               		<tr>
               			<td style="text-align: right;">
               			    <s:label value="%{getText('TaskList.lblStaticAgent_id')}"/>:
               			</td>
               			<td>
               			    <b><s:label name="lblAgent_id" id="lblAgent_id" /></b>
               			</td>
               			<td style="text-align: right;">
               			    <s:label value="%{getText('TaskList.lblStaticTask_name')}"/>:
               			</td>
               			<td>
               			    <b><s:label name="lblTask_name" id="lblTask_name" /></b>
               			</td>
               			<td style="text-align: right; ">
               			    <s:label value="%{getText('TaskList.lblStaticCall_rate_id')}"/>:
               			</td>
               			<td>
               			    <b><s:label name="lblCall_rate_id" id="lblCall_rate_id" /></b>
               			</td>
               		</tr>
               		<tr>
               			<td style="text-align: right;">
               			    <s:label value="%{getText('TaskList.lblStaticCall_start_time')}"/>:
               			</td>
               			<td>
               			    <b><s:label name="lblCall_start_time" id="lblCall_start_time" /></b>
               			</td>
               			<td style="text-align: right;">
               			    <s:label value="%{getText('TaskList.lblStaticDuration_time')}"/>:
               			</td>
               			<td>
               			    <b><s:label name="lblDuration_time" id="lblDuration_time" /></b>
               			</td>
               			<td style="text-align: right;">
               			     <s:label value="%{getText('TaskList.lblStaticDeadline')}"/>:
               			</td>
               			<td>
               			    <b><s:label name="lblDeadline" id="lblDeadline" /></b>
               			</td>
               		</tr>
               		<tr>
               			<td style="text-align: right;" class="last">
               			    <s:label value="%{getText('TaskList.lblStaticTask_comment')}"/>:
               			</td>
               			<td class="last" colspan="6">
               			     <b><s:label name="lblTask_comment" id="lblTask_comment" /></b>
         				</td>
               		</tr>
               		<tr>
               			<td colspan="6" style="text-align: center;" class="last">
               				<form action="mainMenu" id="taskloader" method="get">
           			    	<s:hidden name="type"/><s:hidden name="dnis"></s:hidden><s:hidden name="phone_number"/>
               					<s:submit name="btnChoose_task" key="TaskList.btnChoose_task" cssClass="button"  cssStyle="width:150px"/>
               				</form>
               			</td>
               		</tr>

               </table>
</fieldset>


