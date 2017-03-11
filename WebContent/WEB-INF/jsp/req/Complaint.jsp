<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

	<script language="javascript">
		 /* complainttab */
		$(function() {
			$( "#complainttab" ).tabs({
				load: function(){
					formatter($("#complainttab div.ui-tabs-panel:not(.ui-tabs-hide)")) ;
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

      <div id="complainttab">
		<ul>
			<li><a href="RegisterComplaint.action"><s:text name="RegisterComplaint.form_name"/></a></li>
			<li><a href="ComplaintHistory.action"><s:text name="ComplaintHistory.form_name"/></a></li>
			<li><a href="RegisterOtherComplaint"><s:text name="RegisterOtherComplaint.form_name"/></a></li>
		</ul>
   	  </div>
