<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<script language="javascript">
		 /* subtab2 */
				$(function() {
				$( "#subtab2" ).tabs({
				load: function()
				{
					$(".button").button() ;
				},
				beforeLoad: function( event, ui ) {
				ui.jqXHR.error(function() {
				ui.panel.html(
				"Couldn't load this tab. We'll try to fix this as soon as possible. " +
				"If this wouldn't be a demo." );
				});
				}
				});
				});
	</script>

      <div id="subtab2">
		<ul>
			<li><a href="CreateAgent.action"><s:text name="CreateAgent.form_name"/></a></li>
			<li><a href="InsertData.action"><s:text name="InsertData.form_name"/></a></li>			
		</ul>
   	  </div>           
