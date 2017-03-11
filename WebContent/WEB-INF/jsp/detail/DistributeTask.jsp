<!--
* Моделийн нэр            DistributeTask.jsp
*
* Функцын нэр          【Даалгавар хувиарилах
* Тvvх
* Хувилбарын №  Огноо    Хариуцагч    Агуулга
* 01.00.00                 Огноо    Хариуцагч    Шинээр хийсэн/Зассан
* 01.00.01 2013.02.28 Б.Тэlмүүн Шинээр үүсгэх
* 02.00.01 2013.03.04 Б.Оргил Зассан
* 03.00.01 2013.03.07 Б.Номин-эрдэнэ Зассан
*-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	$(document).ready(function(){
	$("input[name=txtPhone_number]").focus();			
							
			digits();
			texts();
		
		$("#demoDialog").hide();
		
	});
	
	
	function openDistributeTaskAgent(){	
		$("#demoDialog").html("") ;		
		$("#demoDialog").dialog({
			height:450,
			width:475,
			overflow:'none',
			modal:true	
		});
		$("#demoDialog").load("DistributeTaskAgent.action", function() {
       			var d = $("#demoDialog") ;
       			formatter(d);
    	});
	}
		function openInsertTaskDialog(){
			$("#demoDialog").html("") ;
			$("#demoDialog").dialog({
				height:320,
				width:700,
				modal: true				
			});
			var url="Task_id="+$("input[name=temp_task_id]").val();
			$("#demoDialog").load("InsertTask?"+url, function() {	
					var dialog = $("#demoDialog") ;
        			formatter(dialog);
       		});			
		}
		
		$("iframe").load(function(){
			var file = document.getElementById("ExcelFile");
			  $.ajax(
	          { 
		       		type: "POST",
					url: "DistributeTask_getXML.action?searchClick=true",					
					success: function(result)
					    {   
						    jQuery("#grdTask_list2").trigger("reloadGrid");
						    file.value="";
						}
	           });
		});
		
		function checkNull(){
			if($("#ExcelFile").val()!=""){
				var tester=$("#ExcelFile").val().split(".");
				if(tester[1]=="xls"){
					return true;
				}else{
					alert('<s:text name="DistributeTask.errBad_xls"/>');
					return false;
				}
			}else{
				alert('<s:text name="DistributeTask.errFile_null"/>');
				return false;
			}
		}
</script>
<form id="distributetask_form">
	<s:hidden name="temp_task_id"/>
<fieldset><legend><s:property value="getText('DistributeTask.grpSearch')" /></legend> <s:hidden id="qString" name="qString" />
<table cellpadding="4" id=InboundTransfer>
	<tr>
		<td style="text-align: right; width: 110;"><s:label key="DistributeTask.lblStaticPhone_number" />:</td>
		<td><s:textfield id="txtPhone_number" name="txtPhone_number" accesskey="search2" cssStyle="width:120px" cssClass="idfield digits" maxlength="15" /></td>
		<td style="text-align: right; width: 110;"><s:label key="DistributeTask.lblStaticCaller_name" />:</td>
		<td><s:textfield id="txtCaller_name" name="txtCaller_name" accesskey="search2" cssStyle="width:120px" cssClass="idfield texts" maxlength="100" /></td>
		<td style="text-align: right; width: 100;"><s:label key="DistributeTask.lblStaticDeadline" />:</td>
		<td colspan="2"><s:textfield name="txtDeadlineStart" accesskey="search2" cssStyle="width:100px" cssClass="idfield date" maxlength="10" />&nbsp;~&nbsp; <s:textfield name="txtDeadlineOver"
			id="txtDeadlineOver" accesskey="search2" cssStyle="width:100px" cssClass="idfield date" maxlength="10" /></td>
		<td style="text-align: right; width:70;"><s:label key="DistributeTask.lblStaticInsert_agent" />:</td>
		<td><s:select name="cmbInsert_agent" accesskey="search2" cssStyle="width:120px" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbAgent_idList" /></td>
	</tr>
	<tr>
		<td style="text-align: right; width: 110;"><s:label key="DistributeTask.lblStaticAgent_id" />:</td>
		<td><s:select name="cmbAgent_id" accesskey="search2" cssStyle="width:120px" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbAgent_idList" /></td>
		<td style="text-align: right; width: 110;"><s:label key="DistributeTask.lblStaticTask_name" />:</td>
		<td><s:textfield id="txtTask_name" name="txtTask_name" accesskey="search2" cssStyle="width:120px" maxlength = "100" cssClass="idfield" /></td>
		<td style="text-align: right; width: 100;"><s:label key="DistributeTask.lblStaticCall_result_id" />:</td>
		<td><s:select name="cmbCall_result_id" accesskey="search2" cssStyle="width:140px" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbCallResult" />
		<s:checkbox accesskey="search" value="false" key="DistributeTask.chkExcel" name="chkExcel" cssClass="idcheck" />
		</td>
		<td colspan="2" style="text-align: right; width: 70;"><s:label key="DistributeTask.lblStaticCall_type_id" />:</td>
		<td><s:select name="cmbCall_type_id" accesskey="search2" cssStyle="width:120px" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbCallReqType" /></td>
	</tr>
	<tr>
		<td colspan="10" align="middle"><s:submit name="btnSearch" key="DistributeTask.btnSearch" cssClass="button"
			onclick="searchGridData('grdTask_list', 'DistributeTask_getGridField.action', 'distributetask_form');return false" /> <s:reset cssClass="button" key="DistributeTask.btnClear" /></td>
	</tr>
</table>
</fieldset>
</form>
<s:form action="DistributeTask" cssClass="form" enctype="multipart/form-data" target="uploadTrg">
	<fieldset><legend><s:property value="getText('DistributeTask.grpTask_list')" /></legend>
	<div style="text-align: center;height: 20px;">
			        <s:label name="lblResult" id="lblResult"  cssClass="error_message"/>
	</div>
	<table id="grdTask_list"></table>
	<div id="pager_grdTask_list"></div>
	<script type="text/javascript">
                    jQuery("#grdTask_list").jqGrid({
                    		url:'DistributeTask_getGridField.action?searchClick=true',
                    		datatype: "xml",
		                    height: 200,
                    		width: 1065,
                    colNames:
                    ['<s:text name="DistributeTask.grdPHONE_NUMBER_ONE"/>',
                    '<s:text name="DistributeTask.grdPHONE_NUMBER_ONE"/>',
                    '<s:text name="DistributeTask.grdCALLER_NAME"/>',
                    '<s:text name="DistributeTask.grdTASK_NAME"/>',
                    '<s:text name="DistributeTask.grdAGENT_ID"/>',
                    '<s:text name="DistributeTask.grdTASK_COMMENT"/>',
                    '<s:text name="DistributeTask.grdDEADLINE"/>',
                    '<s:text name="DistributeTask.grdINSERT_AGENT"/>',
                    '<s:text name="DistributeTask.grdCALL_RESULT_ID"/>',
                    '',
                    '<s:text name="DistributeTask.grdCALL_TYPE"/>',
                    ''],
                    colModel:[
                   			 { name : 'TASK_ID'		 	, index : 'TASK_ID' , width:100, hidden:true},	
                             { name : 'PHONE_NUMBER_ONE', index : 'PHONE_NUMBER_ONE' , width:100},
                             { name : 'CALLER_NAME'  	, index : 'CALLER_NAME' , width:150},
                             { name : 'TASK_NAME'    	, index : 'TASK_NAME' , width:150},
                             { name : 'AGENT_ID'     	, index : 'AGENT_ID' , width:100},
                             { name : 'TASK_COMMENT' 	, index : 'TASK_COMMENT' , width:150},
                             { name : 'DEADLINE' 	 	, index : 'DEADLINE' , width:100},
                             { name : 'INSERT_USER'     , index : 'INSERT_USER' , width:100},         
                             { name : 'CALL_RESULT_NAME', index : 'CALL_RESULT_NAME' , width:150},
                             { name : 'CALL_RESULT_ID', index : 'CALL_RESULT_ID' , hidden:true},               
                             { name : 'CALL_REQ_TYPE_NAME', index : 'CALL_REQ_TYPE_NAME' , width:150},
                             { name : 'CALL_REQ_TYPE_ID', index : 'CALL_REQ_TYPE_ID' , hidden:true} ],
                    rowNum:100,
                    rowTotal: 2000,
                    mtype: "GET",
                    multiselect: true,                    
                    rownumbers: true,
                    rownumWidth: 40,
                    gridview: true,
                    multiselect: true,   
                    pager:  '#pager_grdTask_list',
                    viewrecords: true,
                    sortorder: "asc",
                    sortname: "DEADLINE",
                    loadComplete: function(){
            
			                    	 $("tr.jqgrow:odd").css("background", "#EAF6F1");
			                    
                    				 document.getElementById("lblResult").innerHTML = "" ;
									 if (jQuery("#grdTask_list").getGridParam("records") == 0)
									 {
									 	document.getElementById("lblResult").innerHTML = "<s:text name="system.WRN007"/>";
									 }
                    },
             		onSelectRow: function(id){          
				      var rowData = $('#grdTask_list').jqGrid('getRowData', id);
				      $("input[name=temp_task_id]").val(rowData['TASK_ID']);
					  
						 }
		             							    
                    });
                    
                    
                    </script></fieldset>
	<table cellpadding="4">
		<tr>
			<td><s:file name="ExcelFile" id="ExcelFile" onchange="javascript:sendPath(this);" value="Хайх" /></td>
			<td><s:submit name="btnExcel_file_copy" key="DistributeTask.btnExcel_file_copy" cssClass="button" onclick="checkNull();" method="onBtnExcel_file_copyClick" cssStyle="width:150;" /> 
            </td>
			<td>
			<button name="btnAgent_prorate" class="button" onclick="openDistributeTaskAgent(); return false;" style="width:150;"><s:label key="DistributeTask.btnAgent_prorate"></s:label></button>
			</td>
			<td>
			<button id="dedata" class="button" onclick="return false;" style="width:150;"><s:label key="DistributeTask.btnAdd"></s:label></button>
			</td>
			<td>
			<button id="editdata" class="button" onclick="openInsertTaskDialog(); return false;"><s:label key='DistributeTask.btnInsert_task' /></button>
			</td>
			<td>
			<button class="button" id="deletedata" onclick="return false;"><s:label key="DistributeTask.btnDelete"></s:label></button>
			</td>
		</tr>
	</table>
	<fieldset><legend><s:property value="getText('DistributeTask.grpExcel_import')" /></legend>
	<table id="grdTask_list2"></table>
	<div id="pager_grdTask_list2"></div>
	<script type="text/javascript">
                    jQuery("#grdTask_list2").jqGrid({
                    url:      'DistributeTask_getXML.action?searchClick=true',
                    datatype: "xml",
                    navigator:true,                   
                    height: 150,
                    width: 1065,
                    colNames:[
                    '',
                    '<s:text name="DistributeTask.grdPHONE_NUMBER_ONE"/>',
                    '<s:text name="DistributeTask.grdCALLER_NAME"/>',
                    '<s:text name="DistributeTask.grdTASK_NAME"/>',
                    '<s:text name="DistributeTask.grdAGENT_ID"/>',                  
                    '<s:text name="DistributeTask.grdDEADLINE"/>',
                    '<s:text name="DistributeTask.grdTASK_COMMENT"/>'],
                    colModel:[
                    		 { name : 'TASK_ID'		 , index : 'TASK_ID' , width:100, hidden:true},	
                             { name : 'PHONE_NUMBER' , index : 'PHONE_NUMBER' , width:100},
                             { name : 'CALLER_NAME'  , index : 'CALLER_NAME' , width:150},
                             { name : 'TASK_NAME'    , index : 'TASK_NAME' , width:150},
                             { name : 'AGENT_ID'     , index : 'AGENT_ID' , width:100},                             
                             { name : 'DEADLINE' 	 , index : 'DEADLINE' , width:100},
                             { name : 'TASK_COMMENT' , index : 'TASK_COMMENT' , width:200}
                             ],
                    rowNum:100,
                    rowTotal: 2000,                    
                    mtype: "GET",
                    rownumbers: true,
                    rownumWidth: 40,                    
                    gridview: true,
                    pager:  '#pager_grdTask_list2',
                    viewrecords: true,
                    sortorder: "asc",
                    multiselect: true,
                    loadComplete: function(){

			                    	 $("tr.jqgrow:odd").css("background", "#EAF6F1");
			                    }                                        
                    });
                    jQuery("#grdTask_list2").jqGrid('navGrid','#pager_grdTask_list2',{add:false,del:true,edit:false,position:'right'});
                    jQuery("#dedata").click( function() {
                    	var gr = jQuery("#grdTask_list2").jqGrid('getGridParam','selarrrow');                    	
                    	if( gr != null ) {                    	                     		
  							var id = gr.toString();   							
  							var ids = id.split(/,/);   														 
  							var utga = new Array();  												    
  							for(var i=0;i<ids.length;i++)
  							{	
  								utga[i]=(jQuery("#grdTask_list2").jqGrid('getCell', ids[i], 'TASK_ID'));  															
  							}
  							var didConfirm = confirm("<s:text name= 'DistributeTask.Confirm' />");
							  if (didConfirm == true) {
								    $.ajax({ 
		                    			type: "POST",
							      		url: "DistributeTask_excelToData.action",
							         	data: {"test":utga.toString()},
							        	dataType: 'text',
							         	success: function()
							           	    {	
							            	    jQuery("#grdTask_list").trigger("reloadGrid");	
							            	    jQuery("#grdTask_list2").trigger("reloadGrid");	          
							        		}
	                    			});
							}
                    	} 
                    	else alert("Check hiinvv");                    	 
                    });
                   jQuery("#deletedata").click( function() {
                   		
                    	var gr = jQuery("#grdTask_list").jqGrid('getGridParam','selarrrow');                    	
                    	var gtr= jQuery("#grdTask_list2").jqGrid('getGridParam','selarrrow');
                    	var flag1=1;
  						var flag2=1;
  						
                    	if( gr != null || gtr != null) {
                    	
                    		var id = gr.toString();   							
  							var ids = id.split(/,/);
  							var utga = new Array();
  							var id1 = gtr.toString();   							
  							var ids1 = id1.split(/,/);
  							var utga1 = new Array();
  							for(var i=0;i<ids.length;i++)
  							{
  								if(ids[i]==false){
									flag1=0;
									break;
	  							}
  								utga[i]=(jQuery("#grdTask_list").jqGrid('getCell', ids[i], 'TASK_ID'));  															
  							}
  							for(var k=0;k<ids1.length;k++)
  							{
  								if(ids1[k]==false){
  									flag2=0;
  									break;	
  								}
  								utga1[k]=(jQuery("#grdTask_list2").jqGrid('getCell', ids1[k], 'TASK_ID'));  															
  							}
  							if(flag1==0 && flag2==0){
									alert("<s:text name='DistributeTask.Delete'/>");
  									return false;  								
  							}
  							var didConfirm = confirm("<s:text name='DistributeTask.Delete'/>");
							  if (didConfirm == true) {
								   if(flag1==1){
								   $.ajax(
		                    		{ 
		                    			type: "POST",
							      		url: "DistributeTask_deteleFromGrid.action",
							         	data: {"test":utga.toString()},
							        	dataType: 'text',
							         	success: function(result)
							           	    {	
							            	    jQuery("#grdTask_list").trigger("reloadGrid");	          
							        		}
		                    		});
		                    	  }
		                    	  if(flag2==1){
		                    	  	$.ajax(
		                    		{ 
		                    			type: "POST",
							      		url: "DistributeTask_deleteFromGridTemp.action",
							         	data: {"test1":utga1.toString()},
							        	dataType: 'text',
							         	success: function(result)
							           	    {	
							            	    jQuery("#grdTask_list2").trigger("reloadGrid");	          
							        		}
		                    		});
		                    	  }
							} 
                    		
                    	} 
                    });
                    </script></fieldset>
</s:form>
<iframe frameborder="0" id="uploadTrg" name="uploadTrg" style="display:none"> </iframe>
<div id="demoDialog"></div>

