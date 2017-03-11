<!--
* Моделийн нэр             InsertData.jsp
*
* Функцын нэр          【Өгөгдөл оруулах】
* Тvvх
* Хувилбарын №  Огноо    Хариуцагч    Агуулга
* 01.00.00                 Огноо    Хариуцагч    Шинээр хийсэн/Зассан
* 01.00.01				2013.03.01	Б.Тэмүүлэн		Шинээр үүсгэх
* 02.00.01				2013.03.04	А.Инкар 		Зассан
*
*-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
           <script>
					$(document).ready(function(){
						 var clickCounter=0;
						 var mClick=0;
					 	 var tree_id;
					 	 var tree_parent;
					 	 var parent_id;
					 	 var parent_display_id;
					 	 
					 	 $("select[name=cmbMigration_attrib]")
					 	 	.html("<option value='0'><s:text name='system.select'/></option>"+
					 	 	"<option value='1'><s:text name='InsertData.to_Task'/></option>"+
					 	 	"<option value='2'><s:text name='InsertData.to_History'/></option>"+
					 	 	"<option value='3'><s:text name='InsertData.to_Complaint'/></option>");
					 	 
						 $("select[name=cmbInsert_data_type]").change(function (){
						 	$("#mainTree").html("");
						 	if($(this).val()!=""){
						 		var value=$(this).val();
						 		
								if(value=='5'){
									$("select[name=cmbMigration_attrib]").attr("disabled","");
								}else{
									$("select[name=cmbMigration_attrib]").attr("disabled","disabled");
								}
								 	$.ajax(
					          	{
					        	type: "POST",
					        	url: "InsertDataTree",
					      		data: {"parent":value},
					        	dataType: 'json',
					           	success: function(result)
					            	      {
					            	      array1 = result.fatherList;
									      array = result.parentList;
									      for(id in array){
									      	if(array[id]=="1"){
										      		$("#mainTree").append("<li id='main' class='link' accesskey='"+array[id]+"' name="+id+"><span><b>"+array1[id]+"</b></span></li>");
									      	}else{
									      				$("li[name="+array[id]+"]").append("<ul><li class='link' name="+id+"><span>"+array1[id]+"</span></li></ul>");
									      	}
									      
					            	  	 }
					            	  	 if($("#mainTree").find("LI").attr("tagName")!="LI"){
					            	  	 		$("#mainTree").html("<button name='btnAddNew' class=\"button\"><s:text name='InsertData.btnAdd_New'/></button>");
					            	  	 }
					            	   
					            	   }
					           });
					         }
						 });
						 
						 $("li.link").live('click',function(){
						 	var position=$(this).position();
						 	$("#menu").css({"display":"none","height":"0px"});
						 	$("li").find("span").css({"border":"","background":""});
							  if($(this).attr("name")!=null){
							  	tree_parent=$("select[name=cmbInsert_data_type]").val();
							  	tree_id=$(this).attr("name");
							  	parent_display_id=$(this).parent().parent().attr("accesskey");
							  	$("li[name="+tree_id+"]").find("span").first().css({"border":"1px solid","background":"lightgreen"});
							  	
							  	$("#menu")
							      .css({"display":"block","left":position.left,"top":(position.top+15),"height":"65px"});
							  }
							});
							
						 $("li").live('hover',function(){
							  $(this).css({"color":"orange","cursor":"pointer"});
							});
							
						 $("li").live('mouseout',function(){
						      $(this).css({"color":"blue"});
							});
							
						 $("li.add").live('click',function(){
						 	$("[name=txtData_id]").val("");
						 	if($("li[name="+tree_id+"]").parent().parent().attr("tagName")=="LI"&&
						 	$("li[name=unsaved]").attr("name")!="unsaved"&&
						 	$("li[name=unsaved_child]").attr("name")!="unsaved_child"){
											 	parent_id=$("li[name="+tree_id+"]").parent().parent().attr("name");
											 	$("li[name="+tree_id+"]").parent().append("<li class='link' name='unsaved'><s:text name='InsertData.new_doc'/></li>");
											 	$("input[name=hddParent_id]").val(parent_id);
											 	$("input[name=txtData_name]").val("<s:text name='InsertData.new_doc'/>");
											 	$("input[name=txtData_name]").focus();
											 	$("input[name=txtData_name]").select();
						 	}else if($("li[name="+tree_id+"]").find("ul").attr("tagName")!="UL"&&
						 	$("li[name=unsaved]").attr("name")!="unsaved"&&
						 	$("li[name=unsaved_child]").attr("name")!="unsaved_child"&&
						 	$("li[name="+tree_id+"]").attr("id")=="main"){
										 	    parent_id=$("li[name="+tree_id+"]").parent().attr("name");
										 		$("li[name="+tree_id+"]").parent().append("<li class='link' name='unsaved'><s:text name='InsertData.new_doc'/></li>");
											 	$("input[name=hddParent_id]").val(parent_id);
											 	$("input[name=txtData_name]").val("<s:text name='InsertData.new_doc'/>");
											 	$("input[name=txtData_name]").focus();
											 	$("input[name=txtData_name]").select();
						 	}else{
						 		$("#insertdata_message").html('<s:text name="InsertData.add_Error"/>');
						 		clickCounter=0;
						 	}
						 	$(this).parent().parent().css({"display":"none","height":"0px"});
						 	$("li[name="+tree_id+"]").find("span").first().css({"border":"","background":""});
							});
							
						 $("li.add_child").live('click',function(){
						 	$("[name=txtData_id]").val("");
						 	if($("li[name=unsaved]").attr("name")!="unsaved"&&
						 	$("li[name=unsaved_child]").attr("name")!="unsaved_child"
						 	){
						 			if($("li[name="+tree_id+"]").attr("id")=="main"){
						 				parent_display_id=$(this).parent().parent().attr("accesskey");
						 			}else{
						 				parent_display_id="";
						 			}
						 	parent_id=$("li[name="+tree_id+"]").attr("name");
						 	$("input[name=hddParent_id]").val(parent_id);
						 	$("li[name="+tree_id+"]").append("<ul><li class='link' name='unsaved_child'><s:text name='InsertData.new_doc'/></li></ul>");
							$("input[name=txtData_name]").val("<s:text name='InsertData.new_doc'/>");
							$("input[name=txtData_name]").focus();
							$("input[name=txtData_name]").select();
						 	$(this).parent().parent().css({"display":"none","height":"0px"});
						 	$("li[name="+tree_id+"]").find("span").first().css({"border":"","background":""});
						 	}
						 	else{
						 	$("#insertdata_message").html('<s:text name="InsertData.add_Error"/>');
						 	$(this).parent().parent().css({"display":"none","height":"0px"});
						 	$("li[name="+tree_id+"]").find("span").first().css({"border":"","background":""});
						 	}
						 });
							
						 $("li.edit").live('click',function(){
						 	if($("#mainTree").find("li[name=unsaved]").attr("name")!="unsaved"&&$("#mainTree").find("li[name=unsaved_child]").attr("name")!="unsaved_child"){
						      $.ajax({
							  		type: "POST",
						        	async: false,
						      		url: "InsertData_Edit",
						      		cache:false,
						         	data: {"id":tree_id,"parent_id":tree_parent},
						        	dataType: 'json',
						           	success: function(result)
						            	    {
						            	     array=result.editData;
						            	     	for(id in array){
						            	     		if(id=="id"){
						            	     		$("input[name=txtData_id]").val(array[id]);
						            	     		}
						            	     		else if(id=="name"){
						            	     		$("input[name=txtData_name]").val(array[id]);
						            	     		}
						            	     		else if(id=="order"){
						            	     			if(array[id]!=null&&array[id]!="null"&&array[id]!=""){
						            	     			$("input[name=txtData_order]").val(array[id]);
						            	     			}
						            	     		
						            	     		}
						            	     		else if(id=="migration"){
						            	     		$("select[name=cmbMigration_attrib]").val(array[id]);
						            	     		}
						            	     	}
						            	    }
							  	}); 
							  	$(this).parent().parent().css({"display":"none","height":"0px"});
							  	$("li[name="+tree_id+"]").find("span").first().css({"border":"","background":""});
							}else{
							$(this).parent().parent().css({"display":"none","height":"0px"});
							  	$("li[name="+tree_id+"]").find("span").first().css({"border":"","background":""});
							$("#insertdata_message").html("<s:text name='InsertData.edit_error'/>");
							clickCounter=0;
							}
							});
						 $("li.delete").live('click',function(){
						 	if($("li[name="+tree_id+"]").parent().find("ul").attr("tagName")==null){
						 		if($("li[name="+tree_id+"]").attr("name")=="unsaved_child"){
						 			$("li[name="+tree_id+"]").parent().remove();
						 		}else if($("li[name="+tree_id+"]").attr("name")=="unsaved"){
						 			$("li[name="+tree_id+"]").remove();
						 		}else{
						 		deleteConfirm();
						 		}
						 	}else{
						 	$("#insertdata_message").html("<s:text name='InsertData.delete_error'/>");
						 	clickCounter=0;
						 	}
						 	$(this).parent().parent().css({"display":"none","height":"0px"}); 
						 	$("li[name="+tree_id+"]").find("span").first().css({"border":"","background":""});
						 });
								
						 $("li.cancel").live('click',function(){
						      $(this).parent().parent().css({"display":"none","height":"0px"});
						      $("li[name="+tree_id+"]").find("span").first().css({"border":"","background":""});
							});
							
						 $("button[name=btnReset]").click(function() {
							  $("input[name=txtData_id]").val("");
							  $("input[name=hddParent_id]").val("");
							  $("input[name=txtData_name]").val("");
							  $("input[name=txtData_order]").val("");
							  $("select[name=cmbMigration_attrib]").val("0");
							  $("li[name=unsaved]").remove();
							  $("li[name=unsaved_child]").parent().remove();
							});
							
						 $("button[name=btnSave]").click(function(){
						     var col_id=$("input[name=txtData_id]").val();
						     var hidden_parent_id=$("input[name=hddParent_id]").val();
						 	 var order_num=$("input[name=txtData_order]").val();
						 	 var migration_num=$("select[name=cmbMigration_attrib]").val();
						 	 if($("input[name=txtData_name]").val()!=""){
						 	 var name=$("input[name=txtData_name]").val();
						 	 }else{
						 	 var name="<s:text name='InsertData.new_doc'/>";
						 	 }
						 	 if($("input[name=txtData_name]"))
						 	 if($("input[name=txtData_id]").attr("value")==""){
						 	 	if($("input[name=hddParent_id]").attr("value")!=""){
						 	 	$.ajax({
							  		type: "POST",
						        	async: false,
						      		url: "InsertData!Save",
						      		cache:false,
						         	data: {"id":hidden_parent_id,
						         	"parent_id":tree_parent,
						         	"order_num_id":order_num,
						         	"migration_num_id":migration_num,
						         	"tree_name":name,
						         	"disp_id":parent_display_id},
						        	dataType: 'json',
						           	success: function(result)
						            	    {
						            	    $("#insertdata_message").html("<s:text name='InsertData.created'/>");
						            	    clickCounter=0;
						            	    $("input[name=txtData_id]").val("");
						            	    $("input[name=hddParent_id]").val("");
						            	    $("input[name=txtData_order]").val("");
						            	    $("select[name=cmbMigration_attrib]").val("0");
						            	    $("input[name=txtData_name]").val("");
						            	    $("#mainTree").html("");
											      $.ajax(
										          {
										        	type: "POST",
										        	async: false,
										      		url: "InsertDataTree",
										      		cache:false,
										         	data: {"parent":tree_parent},
										        	dataType: 'json',
										           	success: function(result)
										            	    {
										            	      array1 = result.fatherList;
														      array = result.parentList;
														      for(id in array){
														      	if(array[id]=="1"){
															      	for(id1 in array1){
															      		if(id1==id){
															      		$("#mainTree").append("<li id='main' class='link' accesskey='"+array[id]+"' name="+id1+"><span><b>"+array1[id1]+"</b></span></li>");
															      		}
															      	}
														      	}else{
														      		for(id1 in array1){
														      			if(id1==id){
														      				$("li[name="+array[id]+"]").append("<ul><li class='link' name="+id1+"><span>"+array1[id1]+"</span></li></ul>");
														      			}
														      		}
														      	}
														      
										            	  	 }
										            	   
										            	   }
										           });
						            	    }
							  	});
							  	tree_id="";
							  	tree_parent="";
							  	parent_display_id="";
							  	
						 	 	}else{
						 	 	$("#insertdata_message").html("<s:text name='InsertData.insert_error'/>");
						 	 	clickCounter=0;
						 	 	}
						 	 }else{
						 	 	if(migration_num==""||migration_num==null){
						 	 		migration_num="0";
						 	 	}
						 	 	$.ajax({
							  		type: "POST",
						        	async: false,
						      		url: "InsertData!EditRow",
						      		cache:false,
						         	data: {"id":col_id,
						         	"parent_id":tree_parent,
						         	"order_num_id":order_num,
						         	"migration_num_id":migration_num,
						         	"tree_name":name},
						        	dataType: 'json',
						           	success: function(result)
						            	    {
						            	     $("#insertdata_message").html("<s:text name='InsertData.created'/>");
						            	     clickCounter=0;
						            	     $("input[name=txtData_id]").val("");
						            	     $("input[name=hddParent_id]").val("");
						            	     $("input[name=txtData_order]").val("");
						            	     $("select[name=cmbMigration_attrib]").val("0");
						            	     $("input[name=txtData_name]").val("");
						            	     $("#mainTree").html("");
						            	     		$.ajax(
										          {
										        	type: "POST",
										        	async: false,
										      		url: "InsertDataTree",
										      		cache:false,
										         	data: {"parent":tree_parent},
										        	dataType: 'json',
										           	success: function(result)
										            	    {
										            	      array1 = result.fatherList;
														      array = result.parentList;
														      for(id in array){
														      	if(array[id]=="1"){
															      	for(id1 in array1){
															      		if(id1==id){
															      		$("#mainTree").append("<li id='main' class='link' accesskey='"+array[id]+"' name="+id1+"><span><b>"+array1[id1]+"</b></span></li>");
															      		}
															      	}
														      	}else{
														      		for(id1 in array1){
														      			if(id1==id){
														      				$("li[name="+array[id]+"]").append("<ul><li class='link' name="+id1+"><span>"+array1[id1]+"</span></li></ul>");
														      			}
														      		}
														      	}
														      
										            	  	 }
										            	   
										            	   }
										           });
						            	    }
							  	});
							  	tree_id="";
							  	tree_parent="";
							  	parent_display_id="";
						 	 }
						 });
						 $("button[name=btnAddNew]").live('click',function(){
								parent_id='1';
								tree_parent=$("select[name=cmbInsert_data_type]").val();
								$("#mainTree").append("<li class='link' name='unsaved'><s:text name='InsertData.new_doc'/></li>");
								$("input[name=hddParent_id]").val(parent_id);
								$("input[name=txtData_name]").val("<s:text name='InsertData.new_doc'/>");
								$("input[name=txtData_name]").focus();
								$("input[name=txtData_name]").select();
						 });
						 function deleteConfirm()
							{
								$("#delete_confirm_dialog1").css({"left":"40%","top":"40%","display":"block"});
							}
						$("button[name=delete_confirm]").click(function(){
								$.ajax({
										  	type: "POST",
									        async: false,
									      	url: "InsertData!DeleteRow",
									      	cache:false,
									        data: {"id":tree_id,"parent_id":tree_parent},
									        dataType: 'json',
									        success: function(result)
									            	{
									            		$("#insertdata_message").html("<s:text name='InsertData.deleted'/>");
									            		clickCounter=0;
									            	    $("li[name="+tree_id+"]").remove();
									            	    $("#delete_confirm_dialog1").css({"display":"none"});
									            	}
									  	});
						});
						$("button[name=delete_cancel]").click(function(){
								tree_id="";
								parent_id="";
								$("#delete_confirm_dialog1").css({"display":"none"});
						});
					$(document).click(function(event){
						
						if(event.target.nodeName!="LI"){
								if(event.target.nodeName!="B"){
									if(event.target.nodeName!="SPAN"){
									$("#menu").css({"display":"none","height":"0px"});
									$("li[name="+tree_id+"]").find("span").first().css({"border":"","background":""});
									}
								}
						}
						
						if(clickCounter==1){
							clickCounter=0;
							$("#insertdata_message").html("");
						}else{
							clickCounter+=1;
						}
					});
					var keyList=new Array();
					var key_Counter=0;
					$(document).keyup(function(e){
						keyList.push(e.keyCode);
						key_Counter+=1;
						if(key_Counter==8){
							if(keyList.compare([38,38,40,40,37,39,37,39])){
								$("#easter").css({"display":"block"});
								setTimeout(function(){$("#easter").css({"display":"none"});},5000);
							}else{
								keyList=new Array();
							}
							key_Counter=0;
						}
					});
					});
					Array.prototype.compare = function (array) {
					    if (!array)
					        return false;
					
					    if (this.length != array.length)
					        return false;
					
					    for (var i = 0; i < this.length; i++) {
					        if (this[i] instanceof Array && array[i] instanceof Array) {
					            if (!this[i].compare(array[i]))
					                return false;
					        }
					        else if (this[i] != array[i]) {
					            return false;
					        }
					    }
					    return true;
					}
			</script>
			<link rel="stylesheet" type="text/css" href="css/box.css"/>
			<table>
				<tr>
					<td>
					<fieldset>
	               		<legend>
	               			<s:property value="getText('InsertData.cmbInsert_data_type')"/>
	               		</legend>
	               			<s:select name="cmbInsert_data_type" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="cmbInsertDataCallReqTypeList"/>
	        		</fieldset>
					</td>
					<td rowspan="2">
					<fieldset>
               		<legend>
               			<s:property value="getText('InsertData.grpInsert_data')"/>
               		</legend>
               		<table>
               			<tr>
                			<td colspan="2">
               					 <s:hidden name="txtData_id"/>
               					 <s:hidden name="hddParent_id"/>
               				</td>
               			</tr>
               			<tr>
               				<td>
                         		<s:property value="getText('InsertData.txtData_order')"/>:</td>
              				<td>
              			 		<s:textfield name="txtData_order" cssClass="idfield"/></td>
              			</tr>
               			<tr>
               				<td>
              			 		<s:property value="getText('InsertData.txtData_name')"/>:</td>
              				<td>
              	 				<s:textfield name="txtData_name" cssClass="idfield"/></td>
              			</tr>
              			<tr>
              				<td>
              					<s:property value="getText('InsertData.cmbMigration_attrib')"/>:</td>
              				<td>
              					<s:select id="cmbMigration_attrib" name="cmbMigration_attrib" cssClass="idselect" headerKey="" headerValue="%{getText('system.select')}" list="%{#{}}" disabled='true' /></td>
              			</tr>
              			<tr>
              				<td>
              			 		<button name="btnSave" class="button">
              			 		<s:property value="getText('InsertData.btnSave')"/>
              			 		</button>
              			 	</td>
              				<td>
               					<button name="btnReset" class="button">
               					<s:property value="getText('InsertData.btnDelete')"/>
               					</button>
               				</td>
               			</tr>
               			<tr>
               				<td colspan="2" align="middle">
               					<b><span id="insertdata_message" style="color:red"></span></b>
               				</td>
               			</tr>
	           		</table>
	               </fieldset>  
					</td>
				</tr>
				<tr>
					<td width="350px">
					<div id="tree_container" style="overflow:scroll;height:500px;border:1px solid;background:white;padding:5px;">
					<ul id="mainTree" name="1" style="list-style-type:none;margin-left:5px">
						
					</ul>
					</div>
					</td>
				</tr>
			</table>
		<div id="menu" style="background:#E6E6E6;display:none;position:absolute;height:0px;width:100px;border: 1px solid;">
			<ul style="margin-left:2px">
				<li class="add" style="list-style-type: none;color:blue;">
				<s:property value="getText('InsertData.btnAdd')"/>
				</li>
				<li class="add_child" style="list-style-type: none;color:blue;">
				<s:property value="getText('InsertData.btnAdd_child')"/>
				</li>
				<li class="edit" style="list-style-type: none;color:blue;">
				<s:property value="getText('InsertData.btnEdit')"/>
				</li>
				<li class="delete" style="list-style-type: none;color:blue;">
				<s:property value="getText('InsertData.btnDelete')"/>
				</li>
				<li class="cancel" style="list-style-type: none;color:blue;">
				<s:property value="getText('InsertData.btnCancel')"/>
				</li>
			</ul>
		</div>
		<div id="delete_confirm_dialog1" style="background:#E6E6E6;display:none;position:absolute;border: 1px solid;">
			<table cellpadding=10>
				<tr>
					<td colspan="2" align="center">
						<s:property value="getText('InsertData.delete_confirm')"/>
					</td>
				</tr>
				<tr>
					<td>
						<button name="delete_confirm" class="button"><s:property value="getText('InsertData.btnDelete_confirm')"/></button>
					</td>
					<td>
						<button name="delete_cancel" class="button"><s:property value="getText('InsertData.btnDelete_cancel')"/></button>
					</td>
				</tr>
			</table>
		</div>
		<div id="easter" style="position:absolute;top:30%;left:50%;display:none;width:100px;height:65px;border:1px solid;border-radius:5px;padding:5px;">
			You got an achievement
			<hr/>
			This is easter egg 9001 points. Baluu
		</div>
			
         
