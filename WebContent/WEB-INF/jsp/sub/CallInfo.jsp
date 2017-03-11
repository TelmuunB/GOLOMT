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
				<s:set name="status" value="status" />

				<s:if test="status != 'true' && view_status != 1">
					$(function() {
						var start_time = new Date() ;
						

						setInterval(function() {
							
							var cur = new Date() ;
							var sec_numb = Math.floor((cur.getTime() - start_time.getTime()) / 1000) ;

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
				
				$(document).ready(function(){		
								var click_flag=false;
								digits();
								texts();
								$("[name=phone_number2]").keyup(function(){
									if($(this).val().length>=6){
										$.ajax({
							        	type: "POST",
							        	data:{"phone_number2":$(this).val()},
							      		url: "CallReqType_getCallerDetails.action",
							        	dataType: 'json',
							           	success: function(result){
								           			var array=result.callInfoList;
								           			var html="";
								           			var count=0;
								           			var pos=$("[name=caller_name]").position();
													$("#phb_List").css({"width":$("[name=caller_name]").width(),"top":(pos.top+$("[name=caller_name]").height()+6),"left":(pos.left)});
												    var lenght=0;
												    for(row in array){
												    	lenght+=1;
												    }
												    
								           			for(row in array){
								           				if(array[row].split(' ')[0]==""||array[row].split(' ')[0]==" "){
								           					continue;
								           				}
								           				html+="<span style='margin:5px;' class='phb_Phone' id='phb_"+array[row].split(' ')[1]+"'>"+array[row].split(' ')[0]+"</span>";
								          				if(row!=lenght){
								          				html+="<br/>";
								          				}
								          				count+=1;
								          			}
								           			if(count==0){
								           				$("#phb_List").css({"display":"none"});
							           				return false;
								           			}
							         				$("#phb_List").html(html);
							           				$("#phb_List").css({"display":"block"});
							           				click_flag=true;
							          			}
							           	});
									}							
								});
								
								
								$("span.phb_Phone").live("click",function(){
									$("[name=phone_number2]").val($(this).attr("id").split('_')[1]);
									$("[name=caller_name]").val($(this).html());
									$("[name=phone_number2]").trigger("onchange");
									$("[name=caller_name]").trigger("onchange");
									$("#phb_List").hide();
								});
								$("span.phb_Phone").live("mouseover",function(){
									$(this).css({"background":"lightblue","cursor":"pointer"});
								});
								$("span.phb_Phone").live("mouseout",function(){
									$(this).css({"background":""});
								});
								
								$(document).click(function(e){
									if(click_flag==true){
										if(e.target.className!="phb_Phone"){
											$("#phb_List").hide();
										}						
									}
								});
				});
				
				
</script>

<fieldset style="margin-bottom: 10px; border-radius: 4px;">
	<legend style="background-color: #7BBABF; border: 1px solid #7BBABF; border-radius: 4px;">
		<s:property value="getText('CallInfo.grpCall_information')" />
	</legend>
	<table width="100%"  cellpadding="4">
		<tr>
			<td colspan="2" width=50%>
				<s:label key="CallInfo.lblStaticInbound_id" />: <s:label name="callID" id="callID" cssClass="ui-field-label"/></td>
			<td colspan="2" width=50%>
				<s:label key="CallInfo.lblStaticAgent_id" />: <s:label name="agentID" cssClass="ui-field-label"/>
			</td>
		</tr>
		<tr>
			<td width="100px" align="left"><s:label key="CallInfo.lblStaticPhone_number" />:</td>
			<td width="150px" align="center"><s:label key="CallInfo.lblStaticSecond_phone" />:</td>
			<td width="150px" align="center"><s:label key="CallInfo.lblStaticCaller_name" />:</td>
		</tr>
		<tr>
			<td align="center">
				<s:label name="phone_number" cssStyle="font-weight:bold;"/>
			</td>
			<td align="center">
				<s:if test="#status == 'true'">
					<s:label name="phone_number2" cssClass="ui-field-label" />
				</s:if>
				<s:else>
					<s:textfield name="phone_number2" cssClass="idfield digits" maxlength="15" cssStyle="width: 100px;" onchange="saveOnChange('CallReqType_saveUserOnChange', {phone_number: '%{phone_number}', phone_number2:this.value, callID:document.getElementById('callID').innerText})" />
				</s:else>
			</td>
			<td align="center">
				<s:if test="#status == 'true'">
					<s:label name="caller_name" cssClass="ui-field-label" />
				</s:if>
				<s:else>
					<s:textfield name="caller_name" cssClass="idfield texts" cssStyle="width:150;"  maxlength="30"  onchange="saveOnChange('CallReqType_saveUserOnChange', {phone_number: '%{phone_number}', caller_name:this.value, callID:document.getElementById('callID').innerText})" />
				</s:else>

			</td>
		</tr>
		<tr>
			<td><s:label key="CallInfo.lblStaticDate" />:</td>
			<td colspan="2">
				<s:label name="start_time" cssClass="ui-field-label"/>&nbsp;~&nbsp;<s:label name="finished_time" cssClass="ui-field-label"/>
			</td>
			<td align="center">
				<s:label key="CallInfo.lblStaticDuration__time" />: <s:label name="duration_time" id="duration_time" cssClass="ui-field-label"/>
	 	    </td>
		</tr>
	</table>
</fieldset>
<div id="phb_List" style="position:absolute;display:none;background:white;border:1px solid #A4A4A4;z-index:99">
	
</div>

