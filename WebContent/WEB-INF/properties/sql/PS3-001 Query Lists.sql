-------------------------------------------CALLREQTYPE--------------------------------------
BEGIN(SLCT_CALLREQTYPE)
	SELECT 
		CALL_REQ_TYPE_ID,
		CALL_REQ_TYPE_NAME
	FROM 
		MST_CALL_REQ_TYPE
END

---------------------------------------------------------------------------------------------
BEGIN(INSERT_TASK)
	INSERT INTO 
	TASK (
	PHONE_NUMBER_ONE,
	CALLER_NAME,
	TASK_NAME,
	AGENT_ID,
	DEADLINE,
	TASK_COMMENT )
	VALUES (
	:PHONE_NUMBER_ONE,
	:CALLER_NAME,
	:TASK_NAME,
	:AGENT_ID,
	TO_TIMESTAMP(:DEADLINE,'YYYY-MM-DD HH24:MI:SS'),
	:TASK_COMMENT
	)
END
-----------------------------------------------------------
BEGIN(SLCT127_PS_000)
	SELECT
	      M.ORGANIZATION_REG_NUM
	    , M.ORGANIZATION_NAME
	FROM MST_ORGANIZATION M
	WHERE
	    M.DELETE_USER IS NULL
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(SLCT_Task_List)
SELECT
		 T.TASK_ID AS TASK_ID
		,T.CALLTYPE AS CALLTYPE
		,NVL(PHONE_NUMBER_ONE, 0) ||' , '|| NVL(PHONE_NUMBER_TWO, 0) AS PHONE_NUMBER
		,T.PHONE_NUMBER_ONE AS PHONE_NUMBER_ONE
		,T.PHONE_NUMBER_TWO AS PHONE_NUMBER_TWO
		,T.CALLER_NAME AS CALLER_NAME
		,T.AGENT_ID AS AGENT_ID
		,T.INSERT_AGENT AS INSERT_AGENT
		,T.TASK_NAME AS TASK_NAME
		,T.TASK_COMMENT AS TASK_COMMENT
		,TO_CHAR(T.DEADLINE,'YYYY-MM-DD') AS DEADLINE
		,T.ACTIVE_TASK AS ACTIVE_TASK
		,T.CALL_REQ_TYPE_ID AS CALL_REQ_TYPE_ID
		,MS.CALL_RESULT_NAME AS CALL_RESULT_NAME
		,TO_CHAR(T.INSERT_DATE,'YYYY-MM-DD') AS INSERT_DATE
		,MCRT.CALL_REQ_TYPE_NAME AS CALL_REQ_TYPE_NAME
    ,cll.service_type1 AS SERVICE_TYPE1
    ,SER1.SERVICE_NAME AS SERVICE_NAME1
    ,cll.detailed_service_type1 AS DETAILED_SERVICE_TYPE1
    ,SER6.SERVICE_NAME AS DETAILED_SERVICE_NAME1
    ,cll.service_type2 AS SERVICE_TYPE2
    ,SER2.SERVICE_NAME AS SERVICE_NAME2
    ,cll.detailed_service_type2 AS DETAILED_SERVICE_TYPE2
    ,SER7.SERVICE_NAME AS DETAILED_SERVICE_NAME2
    ,cll.service_type3 AS SERVICE_TYPE3
    ,SER3.SERVICE_NAME AS SERVICE_NAME3
    ,cll.detailed_service_type3 AS DETAILED_SERVICE_TYPE3
    ,SER8.SERVICE_NAME AS DETAILED_SERVICE_NAME3
    ,cll.service_type4 AS SERVICE_TYPE4
    ,SER4.SERVICE_NAME AS SERVICE_NAME4
    ,cll.detailed_service_type4 AS DETAILED_SERVICE_TYPE4
    ,SER9.SERVICE_NAME AS DETAILED_SERVICE_NAME4
    ,cll.service_type5 AS SERVICE_TYPE5
    ,SER5.SERVICE_NAME AS SERVICE_NAME5
    ,cll.detailed_service_type5 AS DETAILED_SERVICE_TYPE5
    ,SER10.SERVICE_NAME AS DETAILED_SERVICE_NAME5
    ,TO_CHAR(T.INSERT_DATE,'YYYY-MM-DD') AS T_INSERT_DATE
    ,MSR.CALL_RATE_NAME AS CALL_RATE_NAME
    ,MSS.CALL_SORT_NAME AS CALL_SORT_NAME
    ,MSU.UNIT_NAME AS UNIT_NAME
    ,TO_CHAR(CH.START_TIME,'YYYY-MM-DD HH24:MI:SS') AS START_TIME
    ,TIMESTAMP_DIFF_IN_TIME(CH.FINISHED_TIME, CH.START_TIME) AS DURAT
    
	FROM TASK T 
	 LEFT JOIN CALL_HISTORY CH ON CH.CALL_HISTORY_ID = T.CALL_HISTORY_ID
    LEFT JOIN CALL_HISTORY_SERVICE CLL ON CLL.CALL_HISTORY_SERVICE_ID=T.CALL_HISTORY_SERVICE_ID
	LEFT JOIN MST_CALL_RESULT MS ON MS.CALL_RESULT_ID=CLL.CALL_RESULT_ID
	 LEFT JOIN MST_CALL_RATE MSR ON MSR.CALL_RATE_ID=CLL.CALL_RATE_ID
	 LEFT JOIN MST_CALL_SORT MSS ON MSS.CALL_SORT_ID=CLL.CALL_SORT_ID
 	 LEFT JOIN MST_UNIT MSU ON MSU.UNIT_ID = CLL.UNIT_ID
	LEFT JOIN MST_CALL_REQ_TYPE MCRT ON MCRT.CALL_REQ_TYPE_ID=T.CALL_REQ_TYPE_ID
	LEFT JOIN MST_SERVICE SER1 ON ser1.service_id = cll.service_type1
		LEFT JOIN MST_SERVICE SER2 ON ser2.service_id = cll.service_type2
    	LEFT JOIN MST_SERVICE SER3 ON ser3.service_id = cll.service_type3
      	LEFT JOIN MST_SERVICE SER4 ON ser4.service_id = cll.service_type4
        LEFT JOIN MST_SERVICE SER5 ON ser5.service_id = cll.service_type5
  LEFT JOIN MST_SERVICE SER6 ON ser6.service_id = cll.detailed_service_type1
	LEFT JOIN MST_SERVICE SER7 ON ser7.service_id = cll.detailed_service_type2
    LEFT JOIN MST_SERVICE SER8 ON ser8.service_id = cll.detailed_service_type3
    LEFT JOIN MST_SERVICE SER9 ON ser9.service_id = cll.detailed_service_type4
    LEFT JOIN MST_SERVICE SER10 ON ser10.service_id = cll.detailed_service_type5
  WHERE (T.ACTIVE_TASK!=0 OR T.ACTIVE_TASK IS NULL) AND
		:WHERE	
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(SLCT_TASK)
	SELECT
		 TA.TASK_ID AS TASK_ID
        ,TA.AGENT_ID AS AGENT_ID
        ,TA.CALLER_NAME AS CALLER_NAME
        ,TA.CALL_HISTORY_ID AS CALL_HISTORY_ID
		,TA.PHONE_NUMBER_ONE AS PHONE_NUMBER_ONE
		,TA.PHONE_NUMBER_TWO AS PHONE_NUMBER_TWO
		,TA.CALLTYPE AS CALLTYPE
		,TA.TASK_NAME AS TASK_NAME
		,TA.DEADLINE AS DEADLINE
		,TA.TASK_COMMENT AS TASK_COMMENT
		,TA.ACTIVE_TASK AS ACTIVE_TASK
		,TA.CALL_REQ_TYPE_ID AS CALL_REQ_TYPE_ID
		,RT.CALL_REQ_TYPE_NAME AS CALL_REQ_TYPE_NAME
	FROM TASK TA
	LEFT JOIN MST_CALL_REQ_TYPE  RT ON RT.CALL_REQ_TYPE_ID=TA.CALL_REQ_TYPE_ID
	WHERE
		:WHERE	
		
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(SLCT_OUTBOUND_TASK_LIST)
	SELECT
		 TASK_ID 
		,PHONE_NUMBER_ONE ||'  '|| PHONE_NUMBER_TWO ||'   '|| TASK_NAME AS TASKS
	FROM TASK
	WHERE
		(PHONE_NUMBER_ONE = :PHONE_NUMBER_ONE
		OR
		PHONE_NUMBER_TWO = :PHONE_NUMBER_ONE)
		AND DELETE_USER IS NULL
		AND DELETE_DATE IS NULL
		
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(SLCT_OUTBOUND_CALL_RESULT_LIST)
	SELECT
		 CALL_RESULT_ID
		,CALL_RESULT_NAME
	FROM MST_CALL_RESULT
	WHERE
		PARENT_CALL_RESULT_ID = '161'
		
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(SLCT_TASK_LIST_ACT)
	SELECT
		 MIGRATION_NUM
		
	FROM MST_CALL_RESULT
	WHERE
		:WHERE
		
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(UPDT_OUT_TASK_LIST_ACT)
	UPDATE TASK
	SET ACTIVE_TASK = '0'
	WHERE
		:WHERE

END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(UPDT_OUT_TASK_LIST_REV)
	UPDATE TASK
	SET ACTIVE_TASK = '1'
	WHERE
		:WHERE
		
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(UPDT_COMPLAINT_SOL)
	UPDATE COMPLAINT
	SET SOLVED_STATUS='1'
	WHERE
		:WHERE
END
-----------------------------------------------------------
BEGIN(UPDT_COMPLAINT_REV)
	UPDATE COMPLAINT
	SET SOLVED_STATUS='0'
	WHERE
		:WHERE
END
-----------------------------------------------------------
-----------------------------------------------------------
