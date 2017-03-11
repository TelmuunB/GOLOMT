<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--
Copyright (c) 2003-2012, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http
://ckeditor.com/license
-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	
	<meta content="text/html; charset=utf-8" http-equiv="content-type" />
	<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
		CKEDITOR.replace( 'Content001',
	    {
	        height: 350,
	        width: 790
	    });
			CKEDITOR.instances.Content001.setData(window.opener.document.getElementById("txtDataContent").value);
		});	
		function demoValue(){
			window.opener.document.getElementById("txtDataContent").value = CKEDITOR.instances.Content001.getData();
			window.close();
		}
		function Cancel(){
		window.close();
		}
	</script>
</head>
<body>
	<div>
			<textarea class="ckeditor" cols="80" id="Content001" name="ckeditor" rows="10" height="100">&lt;p&gt;This is some &lt;strong&gt;sample text&lt;/strong&gt;. You are using &lt;a href="http://ckeditor.com/"&gt;CKEditor&lt;/a&gt;.&lt;/p&gt;
			
			</textarea>
			<table>
						<tr>
		              		<td>
		              			<button name="btnSave" class="button" onclick="demoValue()">Save</button>
		              		</td>
		              		<td>
		               			<button name="btnC" class="button" onclick="Cancel()">Cancel</button>
		               		</td>
		               	</tr>
			</table>
			
	</div>			 
</body>
</html>
