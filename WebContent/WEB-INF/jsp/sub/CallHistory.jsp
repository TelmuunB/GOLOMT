<!--
* Моделийн нэр             CallHistory.jsp
*
* Функцын нэр          【Үйлчлүүлэгчийн түүх】
* Тvvх
* Хувилбарын №  Огноо    Хариуцагч    Агуулга
* 01.00.00                 Огноо    Хариуцагч    Шинээр хийсэн/Зассан
* 01.00.01				2013.03.01	Б.Тэлмүүн		Шинээр үүсгэх
* 02.00.01				2013.03.01	Э.Бат-Эрдэнэ 		Зассан
*
*--><%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	$(document).ready(function(){
	$("input[name=txtPhone_number]").focus();
		digits();
		texts();
	if($("#permission").val()=="0"){
					$("[name=cmbAgent_id]").attr("disabled","disabled");
				}
	});
</script>
	<table>
		<tr>
			<td>
			   <form action="CallHistory" id="callhistory">
		           <fieldset><legend><s:property value="getText('CallHistory.grpSearch')"/></legend>
		           <table cellpadding="2" width="100%">
		           		<tr>
		           			<td style="text-align: right;">
		           				<s:label key="CallHistory.lblStaticPhone_number"/>:
		           			</td>
		           			<td>
		           				<s:textfield name="txtPhone_number" maxlength = "15" cssClass="idfield digits"/>
		           			</td>
		           			<td style="text-align: right;">
		           			 	<s:label key="CallHistory.lblStaticCaller_name"/>:
		           			</td>
		           			<td>
		           				<s:textfield name="txtCaller_name" maxlength = "100" cssClass="idfield texts"/>
		           			</td>
		           			<td style="text-align: right;">
		           				<s:label key="CallHistory.lblStaticCall_name"/>:
		           			</td>
		           			<td>
		           				<s:textfield name="txtCall_name" maxlength = "100" cssClass="idfield"/>
		           			</td>
		           		</tr>
		           		<tr>
		           			<td style="text-align: right;">
		           				<s:label key="CallHistory.lblStaticAgent_id"/>:
		           			</td>
		           			<td>
		           				<s:hidden name="permission" id="permission" />
		           				<s:select accesskey="A" name="cmbAgent_id" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="%{cmbAgent_idList}"/>
		           			</td>
		           			<td style="text-align: right;">
		           				<s:label key="CallHistory.lblStaticCall_req_type_id"/>:
		           			</td>
		           			<td>
		           				<s:select name="cmbCall_req_type_id" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbCallTypeList"/>
		           			</td>
		           			<td style="text-align: center;" colspan="2">
		           				<s:checkbox key="CallHistory.lblStaticInbound" name="chkInbound"/>
		           				<s:checkbox key="CallHistory.lblStaticOutbound" name="chkOutbound"/>
		           			</td>
		           		</tr>
		           		<tr>
		           			<td colspan="6" style="text-align: center;">
		           				<br><s:submit name="btnSearch" key="CallHistory.btnSearch" cssClass="button" onclick="searchGridData('grdCall_history', 'CallHistory_getXML.action', 'callhistory');return false"/>
		               			<s:reset key="CallHistory.btnClear" cssClass="button"/>
		           			</td>
		           		</tr>
		           </table>
		           </fieldset>
		         </form>
	          </td>
         </tr>
         <tr>
         	<td>
	           <fieldset style="text-align:center;"><legend><s:property value="getText('CallHistory.grpCall_history')"/></legend>
	           <table>
				<tr>
						<td align="center" colspan="2">
						   <div style="text-align: center;height: 15px;vertical-align: middle;; ">
						        <s:label name="lblResult" id="lblResult"  cssClass="error_message"/>
						        <s:actionerror name="errorMessage"  cssClass="error_message"/>
						   </div>
						</td>
				</tr>
	           	<tr>
	           		<td>
				           <table id="grdCall_history"></table><div id="pager_grdCall_history" ></div>
			               <script type="text/javascript">
			                    jQuery("#grdCall_history").jqGrid({
			                    url:'CallHistory_getXML.action',
			                    datatype: "xml",
			                    height: 275,
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
			                    '',
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
			                             {name:"VIEW_STATUS", hidden:true},
			                             {name:"CALL_TYPE", hidden:true}],
			                    rowNum:12,
			                    rowTotal: 2000,
			                    mtype: "GET",
			                    rownumbers: true,
			                    rownumWidth: 40,
			                    gridview: true,
			                    pager:  '#pager_grdCall_history',
			                    viewrecords: true,
			                    sortorder: "desc",
			                    sortname: 'START_TIME',
			                    shrinkToFit:false,

			                    loadComplete: function(){

			                    	 $("tr.jqgrow:odd").css("background", "#EAF6F1");

		                    		 if ($("#grdCall_history").getGridParam("datatype") == "local")
		                    		 {
		                    		 	return ;
		                    		 }
                          		     document.getElementById("lblResult").innerHTML = "" ;

                          		     if (jQuery("#grdCall_history").getGridParam("records") == 0)
									 {
										document.getElementById("lblResult").innerHTML = "<s:text name="system.WRN007"/>";
									 }

							         var ids = jQuery("#grdCall_history").getDataIDs();
							         var data;
							         for(var i=0;i<ids.length;i++)
							         {
							        	data = jQuery("#grdCall_history").getRowData(ids[i]);

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
							        	else if(data['CALLTYPE'] == 5){
							        		$('#grdCall_history').jqGrid('delRowData',ids[i]);
							        		continue;
							        	}
							        	else
							        	{
							        		co = "<img src='Images/sms-ico.png'  title='<s:text name="CallHistory.ico_message" />' />";
							        	}
						        		jQuery("#grdCall_history").setRowData(ids[i],{CALLTYPE:co});
							        }

							         $("#grdCall_history_detail").jqGrid("clearGridData");

							         $("#callloader input[name=btnDetail]").hide() ;
							         $("#callloader input[name=btnEdit]").hide() ;
							  },

							  onSelectRow: function(selectedRowId) {
							 		
							  		var data = $(this).jqGrid('getRowData', selectedRowId);
										
							  		searchGridData('grdCall_history_detail', 'CallHistory_getXMLSub.action', 'callhistory', {txtCallHistoryId : data['CALL_HISTORY_ID']});
                   					document.getElementById("lblResult").innerHTML = "" ;

									$("#callloader input[name=calltype]").val(data['CALL_TYPE']);
                   					$("#callloader input[name=callID]").val(data['CALL_HISTORY_ID']) ;
									if(data['CALL_TYPE'] == "1")
										$("#callloader input[name=type]").val("outview");
									else if(data['CALL_TYPE'] == "0")
										$("#callloader input[name=type]").val("inbound");
									else if(data['CALL_TYPE'] == "2") $("#callloader input[name=type]").val("sms");
									
                   					if (data['VIEW_STATUS'] == "1")
                   					{
										$("#callloader input[name=btnEdit]").show() ;
										$("#callloader input[name=btnDetail]").hide() ;
                   					}
                   					else
                   					{
                   						$("#callloader input[name=btnEdit]").hide() ;
                   						$("#callloader input[name=btnDetail]").show() ;
                   					}
							  }
		                    });
		                    </script>
			           </td>
			           <td>
				           <table id="grdCall_history_detail"></table>
			               <script type="text/javascript">
			                    jQuery("#grdCall_history_detail").jqGrid({
			                    url:'CallHistory_getXMLSub.action',
			                    datatype: "xml",
			                    height: 300,
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
			  </td>
			</tr>
			<tr>
				<td align="center">
					<form action="mainMenu" id="callloader" method="get">
           			    <s:hidden name="callID"/><s:hidden name="calltype"/><s:hidden name="type"/>
           				<s:submit name="btnEdit" key="CallHistory.btnEdit" cssClass="button" cssStyle="display:none"/>
           				<s:submit name="btnDetail" key="CallHistory.btnDetail" cssClass="button" cssStyle="display:none"/>
					</form>
				</td>
			</tr>
		</table>
