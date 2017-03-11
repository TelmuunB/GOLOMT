<!--
* Моделийн нэр             DistributeTaskAgent.jsp
*
* Функцын нэр          【Даалгавар хувиарлах】
* Тvvх
* Хувилбарын №  Огноо    Хариуцагч    Агуулга
* 01.00.00                 Огноо    Хариуцагч    Шинээр хийсэн/Зассан
* 01.00.01				2013.02.28	Б.Тэмүүлэн		Шинээр үүсгэх
* 02.00.01				2013.02.28	Э.Бат-Эрдэнэ 		Зассан
*
*-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
			<script type="text/javascript">
				function distributeTasks(){
					var agent_Grid=$("#grdProrate_agent");
					var selRowIds = agent_Grid.jqGrid ('getGridParam', 'selarrrow');
					var agentList="&selected_Agent_Id=";
					for(var i=0;i<selRowIds.length;i++){
						celValue = agent_Grid.jqGrid ('getCell', selRowIds[i], 'AGENT_ID');
						if(celValue==false){
							continue;
						}
						agentList+=celValue+",";
					}
					if(agentList.split(",").length<2){
						$("#DistributeTaskAgent_message").html("<span style='color:red;'><s:text name='DistributeTaskAgent.No_agent'/></span>");
						setTimeout(function(){$('#DistributeTaskAgent_message').html('');},2000);
						return false;
					}
					var task_Grid=$("#grdTask_list2");
					var task_Count=task_Grid.getGridParam("reccount");
					var task_List="";
					if(task_Count==0){
						$("#DistributeTaskAgent_message").html("<span style='color:red;'><s:text name='DistributeTaskAgent.No_task'/></span>");
						setTimeout(function(){$('#DistributeTaskAgent_message').html('');},2000);
						return false;
					}
					
					for(var i=1;i<=task_Count;i++){
						celValue = task_Grid.jqGrid ('getCell',i,'TASK_ID');
						task_List+=celValue+",";
					}
					$.ajax({
				      type: "POST",
					  url: "DistributeTaskAgent_onBtnProrateClick.action",
				      data: "distrubute_Task_id="+task_List+agentList,
				      async: false,
					  cache: false,
				      success: function(result){
				      	$("#DistributeTaskAgent_message").html("<span style='color:green;'><s:text name='DistributeTaskAgent.success'/></span>");
				      	setTimeout(function(){$('#demoDialog').dialog('close');},2000);
				      	$("#grdTask_list2").trigger("reloadGrid");
				      }
				    });
				    return false;
				}
			</script>
           	   <table>
           	   	<tr>
           	   		<td>
           	   			<table id="grdProrate_agent"></table><div id="pager_grdProrate_agent" ></div>
				               <script type="text/javascript">
				                    jQuery("#grdProrate_agent").jqGrid({
				                    url: 'DistributeTaskAgent_getXML.action?searchClick=false',
				                    datatype: "xml",
				                    height: 255,
				                    width: 400,
				                    colNames:[ 
				                    '<s:text name="DistributeTaskAgent.AgentID"/>',
				                 	'<s:text name="DistributeTaskAgent.FirstName"/>',
				                 	],
				                    colModel:[
				                    {name : 'AGENT_ID', index : 'AGENT_ID' , width:50 },
				                    {name : 'FIRST_NAME', index : 'FIRST_NAME' , width:100 },
				                    ],
				                    rowNum:100,
				                    rowTotal: 2000,
				                    mtype: "GET",
				                    multiselect: true,
				                    rownumbers: true,
				                    rownumWidth: 40,
				                    gridview: true,
				                    pager:  '#pager_grdProrate_agent',
				                    viewrecords: true,
				                    sortorder: "asc"
				                    });
				                    </script>
           	   		</td>
           	   	</tr>
           	   	<tr>
		           	<td style="text-align: center;">
		           	<s:submit name="btnProrate" key="DistributeTaskAgent.btnProrate" cssClass="button" onclick="distributeTasks();"/>
               		<s:submit name="btnReturn" key="DistributeTaskAgent.btnReturn" cssClass="button" onclick="$('#demoDialog').dialog('close');"/>
		           	</td>
           	   	</tr>
           	   	<tr>
           	   		<td>
           	   			<label id="DistributeTaskAgent_message"></label>
           	   		</td>
           	   	</tr>
           	   </table>               

