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
<fieldset style="width:100%"><legend><s:property value="getText('CallerHistory.grpCaller_history')"/></legend>
               <table id="grdCaller_history"></table><div id="pager_grdCaller_history" ></div>
           
               <script type="text/javascript">
                    jQuery("#grdCaller_history").jqGrid({
                    url:            'CallerHistory_getXML.action',
                    datatype: "xml",
                    autowidth: true,
                    height: 120,
                    width: 1000,
                    colNames:['<s:text name="CallerHistory.grdPHONE_NUMBER_ONE"/>',
                    '<s:text name="CallerHistory.grdPHONE_NUMBER_TWO"/>',
                    '<s:text name="CallerHistory.grdCALL_REQ_TYPE_ID"/>',
                    '<s:text name="CallerHistory.grdCALL_RESULT_ID"/>',
                    '<s:text name="CallerHistory.grdSTART_TIME"/>',
                    '<s:text name="CallerHistory.grdAGENT_ID"/>',
                    '<s:text name="CallerHistory.grdCALLTYPE"/>',
                    '<s:text name="CallerHistory.grdTRANSFER_NUMBER"/>',
                    '<s:text name="CallerHistory.grdMORE"/>'],
					colModel:[
                             {width:100},
                             {width:100},
                             {width:200},
                             {width:200},
                             {width:150},
                             {width:100},
                             {width:100},
                             {width:100},
                             {width:200} ],
                    rowNum:100,
                    rowTotal: 2000,
                    mtype: "GET",
                    rownumbers: true,
                    rownumWidth: 40,
                    gridview: true,
                    pager:  '#pager_grdCaller_history',
                    viewrecords: true,
                    sortorder: "asc",
                    loadComplete: function(){

			                    	 $("tr.jqgrow:odd").css("background", "#EAF6F1");
			                    }                    
                    });
               </script>
</fieldset>


