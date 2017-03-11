<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="sj" uri="/struts-jquery-tags"%>

		<link rel="stylesheet" href="css/jquery.treeview.css" />
		<link rel="stylesheet" href="css/screen.css" />
		<link rel="stylesheet" href="css/jquery.contextmenu.css" />
		<link rel="stylesheet" href="css/HelpTree.css" />
		
		
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
					  $.ajax(
					          {
					        	type: "POST",
					        	data:{"fsafs":"fasfs"},
					      		url: "HelpTree_Select.action",
					        	dataType: 'json',
					           	success: function(result)
					            	    {
					            	    //return ;
					            	      var array=result.hsts;
					            	      var array1=result.hsts1;
					            	      for(id in array){
									      	if(array[id]=="0"){
									      		var str = "" ;
									      		$("#mainTree").append("<li id='main' class='link' id='tree' name="+id+"><span class='folder' title='"+array1[id]+"'>"+array1[id]+"</span></li>") ;
									      	}else{
									      		var str = "" ;
									      		
												var li = $("li[name="+array[id]+"]");
												var str = "<li id='tree' class='link' name="+id+"><span class='folder' title='"+array1[id]+"'>"+array1[id]+"</span></li>" ;
						      					if (li.children(":last-child")[0].nodeName != "UL")
						      					{									      						
						      						li.append("<ul>" + str + "</ul>");
						      					}
						      					else
						      					{														      					
						      						$(li.children(":last-child")[0]).append(str);
						      					}			
									      	}
									     
									      }
					            	  	$("#mainTree").treeview({
						            	  	animated: "fast",
								            collapsed: true,
								            persist: "location",
								            prerendered: false
					            	  	});
					            	  	$("#loadingTree").hide();
					            	 	}
					      });		      	 
			$("li.link").live("click",function(t){
			$(this).find("span").css({"color":"black"});
			t.stopPropagation(); 
			tree_id=$(this).attr("name");			
			$.ajax({
						type: "POST",
						data:{"tree_content_id":tree_id},
						url: "HelpTree_ContentRead",
						dataType: 'json',
						success: function(result){
							var array2=result.hsts2;
							for(key in array2){
							$("#treecontent").html(array2[key]);
							$("#insertdata_messageSave").html("");	
							$("#insertdata_messageDelete").html("");
							$("#insertdata_NotDelete").html("");
							$("#insertdata_message").html("");
							}							
						}
				});		
				$("#buttonZoom").removeAttr("disabled") ;
			});				
			$("button[name=btnSearch]").click(function(){			
			var test=$("input[name=searchHelpTree]").val();
			if($("input[name=searchHelpTree]").attr("value")!=""){	
			$.ajax({
						type: "POST",
						data:{"search":test},
						url: "HelpTree_getSearch.action",
						dataType: 'json',
						success: function(result){
							var array=result.searchResult;
							var con="";
							var name="";
							var count=0;										
								for(key in array){
										
									if(key=="NAME"+count){
										name+="<li class='searchClass' accesskey='search"+array["ID"+count]+"'><span class='folder' title='"+array[key]+"'>" +array[key]+"</span></li>";
									}else if(key=="CONTENT"+count){
										var wp=array[key].split(" ");
										var word="";
										for(var i=0;i<wp.length;i++){
											if(test.toLowerCase()==wp[i].toLowerCase()){
												word+='<span style="background:yellow">'+wp[i]+'</span> ';
											}else{
												word+=wp[i]+" ";
											}
										}
										con+="<div id='searchContent' accesskey='search"+array["ID"+count]+"'>"+word+"</div><hr/>";
										count=count+1;
										
									}
								}
								
								count="0";
								$("#mainTree").html(name);	
								$("#treecontent").html(con);
								
								$("#insertdata_message").html("");
								
								
								$("li.searchClass").live("click",function(r){
								$(this).find("span").css({"color":"black"});
								var ownId=$(this).attr("accesskey");
								$("#treecontent").find("div").hide();
								$("#treecontent").find("div[accesskey="+ownId+"]").show();
								$("#mainTree").find("li").css({"background":""});
								$("li[accesskey="+ownId+"]").css({"background":"gray"});
								r.stopPropagation(); 									
								});	
								$("button[name=btnSearch]").click(function(){
								$("#mainTree").html("");
								$("#treecontent").html("");								
								  $.ajax(
									          {
									        	type: "POST",
									        	data:{"fsafs":"fasfs"},
									      		url: "HelpTree_Select.action",
									        	dataType: 'json',
									           	success: function(result)
									            	    {
									            	      var array=result.hsts;
									            	      var array1=result.hsts1;
									            	      for(id in array){
													      	if(array[id]=="0"){
													      		var str = "" ;
													      		$("#mainTree").append("<li id='main' class='link' id='tree' name="+id+"><span class='folder' title='"+array1[id]+"'>"+array1[id]+"</span></li>") ;
													      	}else{
													      		var str = "" ;
													      		
																var li = $("li[name="+array[id]+"]");
																var str = "<li id='tree' class='link' name="+id+"><span class='folder' title='"+array1[id]+"'>"+array1[id]+"</span></li>" ;
										      					if (li.children(":last")[0].nodeName != "UL")
										      					{									      						
										      						li.append("<ul>" + str + "</ul>");
										      					}
										      					else
										      					{														      					
										      						$(li.children(":last")[0]).append(str);
										      					}			
													      	}
													     
													      }
									            	  	$("#mainTree").treeview({});
									            	  	
									            	 	}
									      });
								});	   														
							}
				});	
			}else
			$("#insertdata_message").html("<s:text name='HelpTree.searchSpan'/>");					   
		});
				
		   	var height = screen.availHeight;
 			var width = screen.availWidth;
			function zoomContent() {
			  var w = window.open('about:blank','helpdetail','scrollbars=yes,location=no,menubar=no,status=no,titlebar=no,toolbar=no,width='+screen.width+',height='+screen.height+',left=0,top=0');
			  var html = $("#treecontent").html();			
			    $(w.document.body).html(html);
			     w.focus();
			}			
			$(function() {
			    $("#buttonZoom").click(zoomContent);
			});
		
			$("#MySplitter").splitter({
			splitVertical: true,
			sizeLeft: true,
			accessKey: 'I'
			});			
	});						
  
	</script>
<s:hidden name="txtId" />
<s:hidden name="txtType" />
<fieldset class="fieldset"><legend style="color:#234a8f; margin-left: 10px; font-weight: bold; background: transparent;"><s:property value="getText('HelpTree.grpCall_appeal')" /></legend>
<div class="div">
			<div class="treesearch">
					<s:textfield name="searchHelpTree" cssClass="searchinput"></s:textfield>
					<button name="btnSearch" class="button"></button>	
					<button name="btnZoom" class="buttonZoom" id="buttonZoom" disabled="true"></button>
					<b><span id="insertdata_message" style="color:red"></span></b>			
			</div>				
			  	<div>
				  <div class ="titles">							
					<s:property value="getText('HelpTree.lblTitle')" />
				  </div>
				  <div  class="titles2">
					<s:property value="getText('HelpTree.lblData')" />
				  </div>
				</div>  
			  
			  <div id="MySplitter">				   
	     		  <div class="tree" id="tree">	     	
	  
	     			<ul id="mainTree" class="filetree" name="orderForm"> <img id="loadingTree" src='Images/ajax-loader.gif' style="position:absolute; margin-top:150px;margin-left:55px;"/> </ul>	     		
	     		  </div>		    	   						  
			      <div class="treecontent" id="treecontent" name="treecontent">
			      </div>
	  		   </div>
			 
</div>
</fieldset>
