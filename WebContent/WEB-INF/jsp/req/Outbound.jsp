<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

	<script language="javascript">
		 /* subtab */
				$(function() {
					$( "#subtab1" ).tabs({
								selected:<s:property value="defaultTab"/>,
								disabled:[<s:property value="disabledTabs"/>],
								load: function()
									{
										formatter($("#subtab1 div.ui-tabs-panel:not(.ui-tabs-hide)")) ;
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

				function changeTab(){
					$("#btnChoose_task").click();
				}
				
			
	</script>

      <div id="subtab1">
		<ul>
			<li><a href="TaskList.action"><s:text name="TaskList.form_name"/></a></li>
			<li><a onclick="changeTab()" href="OutboundTask.action?type=<s:property value="%{type}"/>&phone_number=<s:property value="%{phone_number}"/>&dnis=<s:property value="%{dnis}"/>&callID=<s:property value="%{callID}"/>&callEnd=<s:property value="%{callEnd}"/>&calltype=<s:property value="%{calltype}"/>"><s:text name="OutboundTask.form_name"/></a></li>
		</ul>
   	  </div>

