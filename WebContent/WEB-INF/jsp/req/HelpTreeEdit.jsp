<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="sj" uri="/struts-jquery-tags"%>


<link rel="stylesheet" href="css/jquery.treeview.css" />
<link rel="stylesheet" href="css/screen.css" />
<link rel="stylesheet" href="css/jquery.contextmenu.css" />
<link rel="stylesheet" href="css/HelpTreeEdit.css" />


<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/jquery.contextmenu.r2.js" type="text/javascript"></script>
<script src="js/jquery.cookie.js" type="text/javascript"></script>
<script src="js/jquery.treeview.js" type="text/javascript"></script>
<script src="js/jquery.treeview.edit.js" type="text/javascript"></script>
<script src="js/splitter.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){		
	var tree_id;	
	var parent_id;
	var tree_parent;												
		  	loadTree();
			$("li.link").live("mousedown",function(e){
				e.stopPropagation();
				clearOnlyFields();
				tree_id=$(this).attr("name");
				$("li.link").find("span").css({"border":""});
				if(e.button==2){
				$(this).find("span:first").css({"border":"1px solid"});
				} 
			});
			
			
			$("span.folder").live("click",function(){
				$("span.folder").css({"color":"black"});
				$(this).css({"color":"lightgreen"});
				$.ajax({
						type: "POST",
						data:{"tree_content_id":tree_id},
						url: "HelpTreeEdit_ContentRead",
						dataType: 'json',
						success: function(result){
							var array2=result.hsts2;
							for(key in array2){
							$("#treecontent").html("<h3>"+$("[name="+tree_id+"]").find("span:first").html()+"</h3><hr/>"+array2[key]);
							resetMessages();
							}
							$("li.link").css({"color":"black"});
							$("[name="+tree_id+"]").css({"color":"red"});							
						}
				});
			});
			
			$("span.folder").live("hover",function(){
				$(this).css({"color":"lightblue"});
			});
										
			$("span.folder").live("mouseout",function(){
				$(this).css({"color":"black"});
			});
				
			$(".filetree").contextMenu('myMenu',{				
				bindings:{			
					'AddFile': function(t){									
									parent_id=$("li[name="+tree_id+"]").attr("name");						
								 	$("input[name=hddParent_id]").val(parent_id);
								 	$("li[name="+tree_id+"]").append("<ul><li class='link' name='unsaved_child'><img src=css/images/golomt.png><s:text name='HelpTree.newDoc'/></li></ul>");
									$("input[name=txtData_name]").val("<s:text name='HelpTree.newDoc'/>");
									$("input[name=txtData_name]").focus();
									$("input[name=txtData_name]").select();
									resetMessages();
					 	
					},
					'Delete': function(t){			
					if($("li[name="+tree_id+"]").find("UL").attr("tagName")==null){
						 		if($("li[name="+tree_id+"]").attr("name")=="unsaved_child"){
						 			$("li[name="+tree_id+"]").parent().remove();
						 		}else if($("li[name="+tree_id+"]").attr("name")=="unsaved"){
						 			$("li[name="+tree_id+"]").remove();
						 		}else{
						 		deleteConfirm();
						 		}
						 	}else{
						 	$("#insertdata_messageDelete").html("");	 	
						 	$("#insertdata_messageSave").html("");	
							$("#insertdata_NotDelete").html("<s:text name='HelpTree.NotDeleteSpan'/>"); 
						 	}
						 		
							},
							'Edit': function(t){
							$("input[name=txtId]").val(tree_id);
							resetMessages();
							$.ajax({
							  		type: "POST",
						        	async: false,
						      		url: "HelpTreeEdit!Edit",
						      		cache:false,
						         	data: {"tree_id":tree_id},
						        	dataType: 'json',
						           	success: function(result)
						            	    {					            	   	    
						            	     array=result.editData;							       	            	    
						            	     	for(id in array){						            	     						            	     						            	
						            	     		if(id=="ID"){	
						            	     		$("input[name=txtId]").val(array[id]);
						            	     		}
						            	     		else if(id=="NAME"){
						            	     		$("input[name=txtData_name]").val(array[id]);
						            	     		}
						            	     		else if(id=="CONTENT"){
						            	     		$("input[name=txtData_content]").val(array[id]);
						            	     		}						            	     		
						            	     	}
						            	}
							  	});					  		  
							}	
						}			
				});
				$("button[name=btnReset]").click(function() {
							  $("input[name=txtId]").val("");
							  $("input[name=hddParent_id]").val("");
							  $("input[name=txtData_name]").val("");
							  $("input[name=txtData_content]").val("");
							  $("li[name=unsaved]").remove();
							  $("li[name=unsaved_child]").parent().remove();
							  $("#insertdata_messageSave").html("");
							  $("#insertdata_messageDelete").html("");
							  $("#insertdata_message").html("");
							  $("#insertdata_NotDelete").html("");
				});
				
				$("button[name=btnSave]").click(function(){		
							 
							 var col_id=$("input[name=txtId]").val();
						     var parent_id=$("input[name=hddParent_id]").val();						     						 	 
						 	 var name=$("input[name=txtData_name]").val();
						 	 var content=$("input[name=txtData_content]").val();
						   	 if($("input[name=txtData_name]").attr("value")!=""){		
						 	 	if($("input[name=txtId]").attr("value")==""){						 								
						 	 		if(!parentValidate()){
									 	parent_id="0";
									}	
						 	 		$.ajax({
									  		type: "POST",
								        	async: false,
								      		url: "HelpTreeEdit_Save.action",
								      		cache:false,
								         	data: {"parent_id1":parent_id,
								         	"content_num":content,								 
								         	"tree_name":name},
								        	dataType: 'json',
								           	success: function(result)
										            	    {
										            	    $("#insertdata_messageSave").html("<s:text name='HelpTree.SaveSpan'/>");
										            	    clearHelpFields();
															loadTree();
							      							}
					      					});
					      		}
			      			    else{
			      			    	if(!txtValidate()){
									 	return false;
									}
							 	 	$.ajax({
								  		type: "POST",
							        	async: false,
							      		url: "HelpTreeEdit_EditRow",
							      		cache:false,
							         	data: {"tree_id":col_id,	
							         	"name":name,								         	
							         	"Content":content},
							        	dataType: 'json',
							           	success: function(result)
							            	    {
							            	     $("#insertdata_messageSave").html("<s:text name='HelpTree.SaveSpan'/>");									            	    								            	     
							            	     $("li[name="+col_id+"]").html($("input[name=txtData_name]").val());
							            	     clearHelpFields();
							            	     loadTree();
							            	    }									            	    
								  	});
								  	tree_id="";	
				 	 		  }
						 }else{
					 			 $("#insertdata_message").html('<span style="border-color:red"><s:text name="HelpTree.searchAdd"/></span>');
					 			 $("#mainTree").html("");
			            	     $("#treecontent").html("");
				            	 loadTree();
				         }						 					 	 					 	 
				});					
				function deleteConfirm()
							{
								var x;
								var r=confirm("<s:text name='HelpTree.confirmDelete'/>");
								if (r==true)
								  {								  
								  		$.ajax({
										  	type: "POST",
									        async: false,   									        
									      	url: "HelpTreeEdit_DeleteRow",
									      	data: {"tree_id":tree_id},
									        dataType: 'json',
									        success: function(result)
									            	{
									            		$("#insertdata_messageDelete").html("<s:text name='HelpTree.DeleteSpan'/>");
									            	    clearHelpFields();
									            	    loadTree();
									            	}
									  	});
									  	
								  }
															 
								    
								    
							}														
				function Content(name){
					$(".treecontent").load(name);
				}
				function Clear(){
					$("#txtDataContent").val("");
				}	
				
				$("#MySplitter").splitter({
				splitVertical: true,
				sizeLeft: true,
				accessKey: 'I'
				});
			
			});	
		
	</script>

	<script type="text/javascript">
			function loadTree(){
				$.ajax({
		        	type: "POST",
		        	data:{"":""},
		      		url: "HelpTreeEdit_Select.action",
		        	dataType: 'json',
		           	success: function(result)
		            	    {
		            	      var array=result.hsts;
		            	      var array1=result.hsts1;
		            	      for(id in array){
		            	      	if(array[id]=="0"){
						      		var str = "" ;
				      				$("#mainTree").append("<li id='main' class='link' id='tree' name="+id+"><span class='folder' title='"+array1[id]+"'>"+array1[id]+"</span></li>") ;						      			
						      	}else {
						      		var str = "" ;
									var li = $("li[name="+array[id]+"]");
									str = "<li id='tree' class='link' name="+id+"><span class='folder' title='"+array1[id]+"'>"+array1[id]+"</span></li>" ;	
			      					if (li.children(":last")[0].nodeName != "UL"){									      						
			      						li.append("<ul>" + str + "</ul>");
			      					}
			      					else{														      					
			      						$(li.children(":last")[0]).append(str);
			      					}			
						      	}																      	
						     }
		            	  },
		        	async:false
		      	});
		      	$("#mainTree").treeview({});
		    }
			function clearHelpFields(){
				 $("input[name=txtId]").val("");	            	     
				 $("input[name=hddParent_id]").val("");
	      	     $("input[name=txtData_name]").val("");
	      	     $("input[name=txtData_content]").val("");
	      	     $("#mainTree").html("");
	      	     $("#treecontent").html("");
	      	     $("#insertdata_message").html("");
	      	     $("#insertdata_messageDelete").html("");
	      	     $("#insertdata_messageSave").html("");
	      	     $("#insertdata_NotDelete").html("");
			}
			function clearOnlyFields(){
   				 $("input[name=txtId]").val("");	            	     
				 $("input[name=hddParent_id]").val("");
	      	     $("input[name=txtData_name]").val("");
	      	     $("input[name=txtData_content]").val("");
				 $("#insertdata_message").html("");
	      	     $("#insertdata_messageSave").html("");
	      	     $("#insertdata_messageDelete").html("");
	      	     $("#insertdata_NotDelete").html("");
			}
			function resetMessages(){
				 $("#insertdata_message").html("");
				 $("#insertdata_messageSave").html("");
	      	     $("#insertdata_messageDelete").html("");
	      	     $("#insertdata_NotDelete").html("");
			}
			function txtValidate(){
				if($("[name=txtId]").val()!=""){
				return true;
				}else{
				$("#insertdata_message").html("<s:text name='HelpTree.pleaseChoose'/>");
				return false;
				}
			}
			function parentValidate(){
				if($("[name=hddParent_id]").val()!=""){
				return true;
				}else{
				$("#insertdata_message").html("<s:text name='HelpTree.pleaseChoose'/>");
				return false;
				}
			}
	</script>
<div class="div">		
				<s:hidden name="txtId" />
				<s:hidden name="hddParent_id" />
			<div class="contextMenu" id="myMenu">
			<ul>
				<li id="AddFile"><img src="Images/add.gif" height="15px"
					width="15px"> Нэмэх</li>
				<li id="Edit"><img src="Images/edit.gif" height="15px" width="15px"> Засварлах</li>
				<li id="Delete"><img src="Images/delete.gif" height="15px"
					width="15px"> Устгах</li>
				
			</ul>
			</div>			
			<div class="divCss" id="MySplitter">  
							    <div class="tree" id="tree">
							    	<div id=minDiv>		
							    	<s:property value="getText('HelpTreeEdit.Name')"/>			    
								    <s:textfield name="txtData_name" cssClass="txtFieldCss1" title="Нэр" id="txtData_name"/>
								    <s:property value="getText('HelpTreeEdit.Content')"/>
								    <s:textfield name="txtData_content" id="txtDataContent" title="Контэнт" cssClass="txtFieldCss" width="100px" onclick="window.open('editor.jsp','popUpWindow','height=600,width=800, resizable=yes,scrollbars=yes,toolbar=yes,menubar=no,location=no,directories=no, status=yes');" />
						    		<button name="btnSave" class="button" id="btnSave">
			              			 		<s:property value="getText('InsertData.btnSave')"/>
			              			</button>
									<button name="btnReset" class="button">
			               					<s:property value="getText('HelpTreeEdit.btnClear')"/>
			               			</button>
			               			<br/>
			               			<b><span id="insertdata_message" style="color:red"></span></b>
			               			<b><span id="insertdata_NotDelete" style="color:red"></span></b>
			               			<b><span id="insertdata_messageSave" style="color:green"></span></b>
			               			<b><span id="insertdata_messageDelete" style="color:green"></span></b>	
			               			<br/>
			               			</div>		
			  	   					<ul id="mainTree" class="filetree" name="orderForm"></ul>
								</div>
						<div class="treeContent" id="treecontent" name="treecontent">			
							
						</div>
			</div>	
</div>