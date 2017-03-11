<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<html>
	<head>
		<title><s:property value="getText(Monitor.form_name)"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="Expires" content="0">
		<meta http-equiv="Cache-Control" content="no-cache">
		<meta http-equiv="Pragma" content="no-cache">
		<base target="_self" >
		
		<link rel="stylesheet" href="css/monitor.css" type="text/css"/>
		<script type="text/javascript" src="struts/js/combined20130203.js"></script>

	</head>
	<script type="text/javascript">
		var retriever;
		var retriever2;
		var timer;
		var d;
		preload([
		    'Images/inactive.png',
		    'Images/logout.png',
		    'Images/active.png',
		    "Images/login.png"
		]);
		$(document).ready(function(){
			retrieveData();
			retriever=self.setInterval(function(){
			retrieveData();
			},5000);
			timer=self.setInterval(function(){
					d = new Date();
					$("#Monitor_time").html(d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate()+" "+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds());
			},1000);
		});
		function restarter(){
			retriever2=self.setInterval(function(){
			retrieveData();
			},5000);
		}
		function retrieveData(){
			  
			  $.ajax({
	        	type: "POST",
	        	data:{},
	      		url: "Monitor_getTableData.action",
	        	dataType: 'json',
	           	success: function(result){
	           			
	           			var agents=result.agentList;
	           			var notready=result.notReadyList;
	           			var talking=result.talkingList;
	           			var inbound=result.inboundList;
	           			var outbound=result.outboundList;
	           			var missed=result.missedList;
	           			var html="";
	           			for (id in agents){
	           				html+="<tr class='grdb'>";
							html+="<td>"+agents[id]+"</td>";
							if(notready[id]=='2'){
								html+="<td>"+"<img src='Images/inactive.png'/>"+"</td>";
							}
							else if(notready[id]=='4'){
								html+="<td>"+"<img src='Images/logout.png'/>"+"</td>";
							}
							else if(notready[id]=='3'){
								html+="<td>"+"<img src='Images/active.png'/>"+"</td>";
							}else if(notready[id]=='1'){
								html+="<td>"+"<img src='Images/login.png'/>"+"</td>";
							}
							else{
								html+="<td></td>";
							}
							if(talking[id]=='1'){
								html+="<td>"+"<img src='Images/login.png'/>"+"</td>";
							}else if(talking[id]=='2'){
								html+="<td class='bgY'>"+"<img src='Images/login.png'/>"+"</td>";
							}else{
								html+="<td></td>";
							}
	           				html+="<td>"+inbound[id]+"</td>";
	           				html+="<td>"+outbound[id]+"</td>";
	           				html+="<td>"+missed[id]+"</td>";
	           				html+="</tr>";
	           			}
	           			
	           			$("#grdLiveMonitor").find("tr.grdb").remove();
	           			$("#grdLiveMonitor").append(html);
	           			return false;
	           	}
			  });
		}
		function stopper(){
			clearInterval(retriever);
			clearInterval(retriever2);
		}
		function preload(arrayOfImages) {
		    $(arrayOfImages).each(function(){
		        $('<img/>')[0].src = this;
		        // Alternatively you could use:
		        // (new Image()).src = this;
		    });
		}
	</script>
	<body>
		
		<div id="Monitor_wrapper">
			<div id="Monitor_header">
			<p class="header_text" style="text-align:center;">
				<s:label name="lblStaticMonitorTitle" value="%{getText('Monitor.monitorTitle')}"/>
			</p>
			<div id="Monitor_time"></div>
			</div>
			<div style="text-align:center;width:100%;margin:0 auto;">
				<table id="grdLiveMonitor" border="1" style="border-collapse:collapse;width:100%;border-color:#0b2a61">
					<tr>
						<td rowspan="2">
						<b><s:label name="lblStaticAgent" value="%{getText('Monitor.grdAgent')}"/></b>
						</td>
						<td colspan="2">
						<b><s:label name="lblStaticStatus" value="%{getText('Monitor.grdStatus')}"/></b>
						</td>
						<td colspan="3">
						<b><s:label name="lblStaticCalls" value="%{getText('Monitor.grdCalls')}"/></b>
						</td>
					</tr>
					<tr style="border-bottom:3px solid #1f4392;">
						<td>
						<b><s:label name="lblStaticNotReady" value="%{getText('Monitor.grdNotReady')}"/></b>
						</td>
						<td>
						<b><s:label name="lblStaticTalking" value="%{getText('Monitor.grdTalking')}"/></b>
						</td>
						<td>
						<b><s:label name="lblStaticInbound" value="%{getText('Monitor.grdInbound')}"/></b>
						</td>
						<td>
						<b><s:label name="lblStaticOutbound" value="%{getText('Monitor.grdOutbound')}"/></b>
						</td>
						<td>
						<b><s:label name="lblStaticMissed" value="%{getText('Monitor.grdMissed')}"/></b>
						</td>
					</tr>
					
				</table>
			</div>
			<!--<div style="text-align:center;">
			<table id="grdInfo" border="1">
				<tr>
					<td>
					</td>
					<td>
					<s:label name="lblInCall" value="%{getText('Monitor.agentInCall')}"/>
					</td>
				</tr>
				<tr>
					<td>
					</td>
					<td>
					<s:label name="lblCallOver3Min" value="%{getText('Monitor.agentCallOver3Min')}"/>
					</td>
				</tr>
				<tr>
					<td>
					</td>
					<td>
					<s:label name="lblReady" value="%{getText('Monitor.agentReady')}"/>
					</td>
				</tr>
			</table>
			</div>
			-->
			
		</div>
		<a href="" onclick="restarter();return false;">Start</a>
			<a href="" onclick="stopper();return false;">Stop</a>
	</body>
</html>