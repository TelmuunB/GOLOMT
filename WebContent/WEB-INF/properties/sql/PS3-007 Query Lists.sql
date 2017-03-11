-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(SLCT_CREATEAGENT)
  SELECT FIRSTNAME as FIRSTNAME,
   LASTNAME as LASTNAME,
   AGENT_REGISTER,
   AGENT_ID,
   AGENT_PERMISSION, 
   MAP.AGENT_PERMISSION_NAME AS AGENT_PERMISSION_NAME, 
   EMAIL, 
   PASSWORD AS A_PASSWORD,
   DELETE_USER
   FROM MST_AGENT MA
  LEFT JOIN
  MST_AGENT_PERMISSION MAP
  ON MAP.AGENT_PERMISSION_ID=MA.AGENT_PERMISSION WHERE :WHERE
  :ORDER
END
-----------------------------------------------------------
BEGIN(SLCT_TASKEXCEL)
	SELECT TASK_ID,PHONE_NUMBER ,CALLER_NAME ,TASK_NAME ,AGENT_ID ,TO_CHAR(DEADLINE,'YYYY-MM-DD') AS DEADLINE,RESULT
    FROM TASK_TEMP :WHERE :ORDER
END
-----------------------------------------------------------
--------------------TASK TEMP------------------------------
BEGIN(SLCT_TASK_TEMP)
	SELECT  TASK_ID , PHONE_NUMBER ,CALLER_NAME ,TASK_NAME ,AGENT_ID ,TASK_TYPE , TO_CHAR(DEADLINE,'YYYY-MM-DD') as DEADLINE ,INSERT_USER , RESULT
	FROM TASK_TEMP  
END
-----------------------------------------------------------	
BEGIN(SLCT_TASK_TEMP_PS_211)
	SELECT TASK_ID, PHONE_NUMBER, CALLER_NAME, TASK_NAME, AGENT_ID, DEADLINE,RESULT    
	FROM TASK_TEMP WHERE TASK_ID=:TASK_ID 
END
-----------------------------------------------------------	
BEGIN(SLCT002_PS_211)
	SELECT 
	T.TASK_ID AS TASK_ID,
	T.PHONE_NUMBER_ONE AS PHONE_NUMBER_ONE,
	T.CALLER_NAME AS CALLER_NAME,
	T.TASK_NAME AS TASK_NAME,
    T.AGENT_ID AS AGENT_ID,
    T.TASK_COMMENT AS TASK_COMMENT, 
    TO_CHAR(T.DEADLINE,'YYYY-MM-DD') AS DEADLINE, 
    T.INSERT_USER AS INSERT_USER, 
    CR.CALL_RESULT_NAME,
    CHS.CALL_RESULT_ID,
    MCRT.CALL_REQ_TYPE_NAME AS CALL_REQ_TYPE_NAME,
    T.CALL_REQ_TYPE_ID AS CALL_REQ_TYPE_ID
	FROM TASK T
	LEFT JOIN MST_CALL_REQ_TYPE MCRT ON MCRT.CALL_REQ_TYPE_ID=T.CALL_REQ_TYPE_ID
  	LEFT JOIN CALL_HISTORY_SERVICE CHS ON CHS.CALL_HISTORY_SERVICE_ID=T.CALL_HISTORY_SERVICE_ID
  	LEFT JOIN MST_CALL_RESULT CR ON CR.CALL_RESULT_ID=CHS.CALL_RESULT_ID
  WHERE T.DELETE_DATE IS NULL AND (T.ACTIVE_TASK!=0 OR T.ACTIVE_TASK IS NULL)
  :WHERE :ORDER 
END
-----------------------------------------------------------	
BEGIN(INS_TASK)
	INSERT INTO 
  TASK(
     PHONE_NUMBER_ONE
    ,CALLER_NAME
    ,AGENT_ID
    ,TASK_NAME
    ,TASK_COMMENT
    ,DEADLINE
    ,INSERT_USER
    ,EXCEL_TASK
  )
  VALUES(
    :PHONE_NUMBER_ONE
    ,:CALLER_NAME
    ,:AGENT_ID
    ,:TASK_NAME
    ,:TASK_COMMENT
    ,TO_TIMESTAMP(:DEADLINE,'YYYY-MM-DD:HH24:MI:SS')
    ,:INSERT_USER
    ,1
  )
END
-----------------------------------------------------------	
----------------------------------------------------------------
BEGIN(DLT_TASK_TEMP)
	DELETE
	FROM
	  TASK_TEMP
	WHERE
	  TASK_ID=:TASK_ID
END
----------------------------------------------------------------
BEGIN(DLT_TASK)
	UPDATE TASK
	SET 
	DELETE_DATE=TO_TIMESTAMP(:DELETE_DATE,'YYYY-MM-DD:HH24:MI:SS'),
	DELETE_USER=:DELETE_USER
	WHERE
	  TASK_ID=:TASK_ID
END
----------------------------------------------------------------
BEGIN(SLCT001_PS_211)
	SELECT
      CALL_RESULT_ID
     ,CALL_RESULT_NAME
 	FROM MST_CALL_RESULT 
 	WHERE PARENT_CALL_RESULT_ID != '1' AND PARENT_CALL_RESULT_ID != '0' 
 	 AND DELETE_DATE IS NULL AND DELETE_USER IS NULL 
END
----------------------------------------------------------------
----------------------------------------------------------------
------------------------DistributeTask--------------------------
----------------------------------------------------------------
BEGIN (SLCT_DISTRIBUTE_AGENT)
	SELECT
		AGENT_ID,
		FIRSTNAME
		FROM MST_AGENT
		WHERE DELETE_DATE IS NULL
END
----------------------GETTASK-------------------------------------
BEGIN(GET_TASK)
	SELECT
		PHONE_NUMBER_ONE,
		CALLER_NAME,
		TASK_NAME,
		AGENT_ID,
		TASK_COMMENT,
    CASE WHEN TO_CHAR(DEADLINE, 'YYYY-MM-DD') IS NULL THEN ' '
    ELSE
    TO_CHAR(DEADLINE, 'YYYY-MM-DD')
    END AS DEADLINE
	FROM TASK
	WHERE TASK_ID=:TASK_ID
END
------------------------------------------------------------------
-----------------------------UPDATE_TASK-------------------------
BEGIN(UPDATE_TASK)
	UPDATE TASK
	SET
		PHONE_NUMBER_ONE=:PHONE_NUMBER_ONE,
		CALLER_NAME=:CALLER_NAME,
		TASK_NAME=:TASK_NAME,
		AGENT_ID=:AGENT_ID,
		TASK_COMMENT=:TASK_COMMENT,
		DEADLINE=TO_TIMESTAMP(:DEADLINE,'YYYY-MM-DD:HH24:MI:SS')
	WHERE TASK_ID=:TASK_ID
END
----------------------------------------------------------------
----------------------DistributeTaskAgent-----------------------
----------------------------------------------------------------
BEGIN(DIST_TASK_AGENT)
	UPDATE TASK_TEMP
	SET AGENT_ID=:AGENT_ID
	WHERE TASK_ID=:TASK_ID
END
----------------------------------------------------------------
--------------------------MONITOR-------------------------------
----------------------------------------------------------------
BEGIN(MONITOR_AGENT_LOG)
SELECT 
  MAX(MAL.AGENT_ID) AS AGENT_ID,
  MAX(MAL.AGENT_ID || ' ' || MA.FIRSTNAME) AS AGENT,
  (SELECT MAS.STATUS FROM (SELECT * FROM MST_AGENT_LOG MAS1 ORDER BY MAS1.UPDATE_DATE DESC) MAS WHERE MAS.AGENT_ID=MAL.AGENT_ID AND ROWNUM=1) AS STATUS,
  (SELECT (CASE WHEN CHT.FINISHED_TIME IS NULL AND CHT.START_TIME IS NOT NULL THEN (CASE WHEN ROUND(TIMESTAMP_DIFF_IN_SECONDS(SYSTIMESTAMP,START_TIME)/60)>3 THEN '2' ELSE '1' END) ELSE '0' END) FROM (SELECT * FROM CALL_HISTORY CHT1 ORDER BY CHT1.START_TIME DESC) CHT WHERE CHT.AGENT_ID=MAL.AGENT_ID AND CHT.START_TIME IS NOT NULL AND ROWNUM=1) AS TALKING,
  (SELECT COUNT(CHI.CALL_HISTORY_ID) FROM CALL_HISTORY CHI WHERE CHI.AGENT_ID=MAL.AGENT_ID AND CALLTYPE='0' AND CHI.START_TIME IS NOT NULL AND CHI.FINISHED_TIME IS NOT NULL AND TO_CHAR(CHI.START_TIME,'YYYY-MM-DD')=TO_CHAR(SYSDATE,'YYYY-MM-DD')) AS INBOUND_CALL,
  (SELECT COUNT(CHO.CALL_HISTORY_ID) FROM CALL_HISTORY CHO WHERE CHO.AGENT_ID=MAL.AGENT_ID AND CALLTYPE='1' AND CHO.START_TIME IS NOT NULL AND CHO.FINISHED_TIME IS NOT NULL AND TO_CHAR(CHO.START_TIME,'YYYY-MM-DD')=TO_CHAR(SYSDATE,'YYYY-MM-DD')) AS OUTBOUND_CALL,
  (SELECT COUNT(CHM.CALL_HISTORY_ID) FROM CALL_HISTORY CHM WHERE CHM.AGENT_ID=MAL.AGENT_ID AND CALLTYPE='3' AND CHM.START_TIME IS NOT NULL AND TO_CHAR(CHM.START_TIME,'YYYY-MM-DD')=TO_CHAR(SYSDATE,'YYYY-MM-DD')) AS MISSED_CALL
FROM MST_AGENT_LOG MAL
LEFT JOIN MST_AGENT MA ON MA.AGENT_ID=MAL.AGENT_ID
GROUP BY MAL.AGENT_ID
END
----------------------------------------------------------------