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

<script language="javascript">
		 /* calltab */
				$(function() {
				$( "#calltab" ).tabs({
				load: function()
				{
					formatter($("#calltab div.ui-tabs-panel:not(.ui-tabs-hide)")) ;
				},
				beforeLoad: function( event, ui ) {
				ui.jqXHR.error(function() {
				ui.panel.html(
				"Couldn't load this tab. We'll try to fix this as soon as possible. " +
				"If this wouldn't be a demo." );
				});
				},cache:true
				});
				});

		in_callID = '<s:property value="callID"/>' ;
		in_message = '<s:text name="CallReqType.confirmMessage"/>' ;
		mailsent = '<s:text name="system.MAILSENT"/>' ;
</script>
	<table>
		<tr>
			<td width="490px">
				<%@include file="/WEB-INF/jsp/sub/CallInfo.jsp"%>
				<div id="calltab">
				<ul>
					<s:if test="has_ref == 1">
						<li><a href="InboundReference.action?callID=<s:property value='callID'/>"><s:text name="InboundReference" /></a></li>
					</s:if>
					<s:if test="has_info == 1">
						<li><a href="InboundInforeq.action?callID=<s:property value='callID'/>"><s:text name="InboundInforeq" /></a></li>
					</s:if>
					<s:if test="has_app == 1">
						<li><a href="InboundAppeal.action?callID=<s:property value='callID'/>"><s:text name="InboundAppeal" /></a></li>
					</s:if>
					<s:if test="has_comp == 1">
						<li><a href="InboundComplaint.action?callID=<s:property value='callID'/>"><s:text name="InboundComplaint" /></a></li>
					</s:if>
					<s:if test="has_tra == 1">
						<li><a href="InboundTransfer.action?callID=<s:property value='callID'/>"><s:text name="InboundTransfer" /></a></li>
					</s:if>
					<s:if test="has_other == 1">
						<li><a href="InboundOther.action?callID=<s:property value='callID'/>"><s:text name="InboundOther" /></a></li>
					</s:if>
				</ul>
				</div>
			</td>
			<td style="width:600px; margin-right:-5px;" rowspan="2">
				<iframe src="HelpTree.action" frameborder="0" width="600px" height="570px" scrolling="no">
			   </iframe>

			</td>
		</tr>
	</table>

<fieldset style="width:100%"><legend><s:property value="getText('CallerHistory.grpCaller_history')"/></legend>

        <table>
        		<tr>
	           		<td>
				           <table id="grdInCall_history"></table><div id="pager_grdInCall_history" ></div>
			               <script type="text/javascript">
			                    jQuery("#grdInCall_history").jqGrid({
			                    url:'CallReqType_getXML.action',
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
										 {name:"CALL_HISTORY_ID", align:"center", hidden:true},
			                             {name:"PHONENUMBER", width:80, align:"center"},
			                             {name:"CALLERNAME", width:120, align:"center"},
			                             {name:"START_TIME", width:140, align:"center"},
			                             {name:"FINISHED_TIME", width:140, align:"center"},
			                             {name:"DURATION", width:80, align:"center"},
			                             {name:"CALLTYPE", width:80, align:"center"},
			                             {name:"AGENT_NAME", width:130, align:"center"},
			                             {name:"PHONENUMBER2", width:80, align:"center"},
			                             {name:"VIEW_STATUS", hidden:true}],
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

							  		searchGridData('grdInCall_history_detail', 'CallReqType_getXMLSub.action', null, {callID : data['CALL_HISTORY_ID']});
							  }
		                    });
		                    </script>
			           </td>
			           <td>
				           <table id="grdInCall_history_detail"></table>
			               <script type="text/javascript">
			                    jQuery("#grdInCall_history_detail").jqGrid({
			                    url:'CallReqType_getXMLSub.action',
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

</fieldset>


