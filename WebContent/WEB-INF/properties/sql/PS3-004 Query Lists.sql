
-----------------------------------------------------------
-----------------------COMPLAINT---------------------------
-----------------------------------------------------------
BEGIN(SLCT001_PS_201)
	SELECT
		SERVICE_ID,
		PARENT_SERVICE_ID,
		SERVICE_NAME 
	FROM
		MST_SERVICE 
	WHERE 
		PARENT_SERVICE_ID='8'
	AND
		CALL_REQ_TYPE_ID='4'
	AND
		DELETE_USER IS NULL
END
-----------------------------------------------------------
BEGIN(SLCT002_PS_201)
	SELECT
		SERVICE_ID,
		PARENT_SERVICE_ID,
		SERVICE_NAME 
	FROM
		MST_SERVICE
	WHERE 
		PARENT_SERVICE_ID='9'
	AND
		CALL_REQ_TYPE_ID='4'
	AND
		DELETE_USER IS NULL
END
-----------------------------------------------------------
BEGIN(SLCT003_PS_201)
	SELECT 
		CALL_SORT_ID,
		PARENT_CALL_SORT_ID,
		CALL_SORT_NAME 
	FROM
		MST_CALL_SORT 
	WHERE
		PARENT_CALL_SORT_ID='2'
	AND
		DELETE_USER IS NULL
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(SLCT003_PS_OUTBOUND)
	SELECT 
		CALL_SORT_ID,
		PARENT_CALL_SORT_ID,
		CALL_SORT_NAME 
	FROM
		MST_CALL_SORT 
	WHERE
		PARENT_CALL_SORT_ID='3'
	AND
		DELETE_USER IS NULL
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(SLCT003_PS_OUTBOUND2)
	SELECT 
		CALL_SORT_ID,
		PARENT_CALL_SORT_ID,
		CALL_SORT_NAME 
	FROM
		MST_CALL_SORT 
	WHERE
		PARENT_CALL_SORT_ID='2'
	AND
		DELETE_USER IS NULL
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(SLCT005_PS_201)
	SELECT 
		CALL_RESULT_ID,
		PARENT_CALL_RESULT_ID,
		CALL_RESULT_NAME 
	FROM
		MST_CALL_RESULT 
	WHERE
		PARENT_CALL_RESULT_ID='3'
	AND
		DELETE_USER IS NULL
END
-----------------------------------------------------------
BEGIN(SLCT006_PS_201)
	SELECT 
		CALL_RATE_ID,
		PARENT_CALL_RATE_ID,
		CALL_RATE_NAME 
	FROM
		MST_CALL_RATE 
	WHERE
		PARENT_CALL_RATE_ID='3'
	AND
		DELETE_USER IS NULL
END
-----------------------------------------------------------
BEGIN(SLCT007_PS_201)
	SELECT
		SERVICE_ID,
		PARENT_SERVICE_ID,
		SERVICE_NAME 
	FROM
		MST_SERVICE
	WHERE 
		PARENT_SERVICE_ID='9'
	AND
		DELETE_USER IS NULL
END
----------------------------------------------------------
BEGIN(SLCT008_PS_201)
	SELECT
		SOURCE_ID,
		PARENT_SOURCE_ID,
		SOURCE_NAME
	FROM
		MST_SOURCE
	WHERE
		PARENT_SOURCE_ID='1'
	AND
		DELETE_USER IS NULL
END
-----------------------------------------------------------
-----------------------------------------------------------
---------------------REFERENCE-----------------------------
-----------------------------------------------------------
BEGIN(SLCT001_PS_202)
	SELECT 
		SERVICE_ID,
		PARENT_SERVICE_ID,
		SERVICE_NAME
	FROM 
		MST_SERVICE
	WHERE 
		PARENT_SERVICE_ID='6'
	AND
		CALL_REQ_TYPE_ID='1'
	AND
		DELETE_USER IS NULL
END
-----------------------------------------------------------
BEGIN(SLCT002_PS_202)
	SELECT 
		SERVICE_ID,
		PARENT_SERVICE_ID,
		SERVICE_NAME
	FROM 
		MST_SERVICE
	WHERE 
		PARENT_SERVICE_ID='12'
	AND
		CALL_REQ_TYPE_ID='1'
	AND
		DELETE_USER IS NULL
END
-----------------------------------------------------------
BEGIN(SLCT003_PS_202)
	SELECT
		CALL_RESULT_ID,
		PARENT_CALL_RESULT_ID,
		CALL_RESULT_NAME
	FROM 
		MST_CALL_RESULT 
	WHERE 
		PARENT_CALL_RESULT_ID='2'
	AND
		DELETE_USER IS NULL
END
-----------------------------------------------------------
BEGIN(SLCT004_PS_202)
	SELECT
		CALL_RATE_ID,
		PARENT_CALL_RATE_ID,
		CALL_RATE_NAME
	FROM 
		MST_CALL_RATE 
	WHERE 
		PARENT_CALL_RATE_ID='1'
	AND
		DELETE_USER IS NULL
END
----------------------------------------------------------
BEGIN(CHECK_RESULT_STATUS)
	SELECT CALL_RESULT_ID,MIGRATION_NUM FROM MST_CALL_RESULT
END
-----------------------------------------------------------
BEGIN(INSRT_TASK_TEMP)
	INSERT INTO task_temp(
      PHONE_NUMBER
      ,CALLER_NAME
      ,TASK_NAME
      ,AGENT_ID
      ,DEADLINE
      ,RESULT
      )
      VALUES(
       :PHONE_NUMBER
      ,:CALLER_NAME
      ,:TASK_NAME
      ,:AGENT_ID
      ,:DEADLINE
      ,:RESULT
      )
END
-----------------------INFOREQ-----------------------------
-----------------------------------------------------------
BEGIN(SLCT001_PS_203)
	SELECT
       SERVICE_ID
     , PARENT_SERVICE_ID
     , SERVICE_NAME
 	FROM 
 		MST_SERVICE
 	WHERE
      PARENT_SERVICE_ID='7'
  	AND
      CALL_REQ_TYPE_ID='2'
  	AND
	  DELETE_USER IS NULL
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(SLCT002_PS_203)
	SELECT
       	SERVICE_ID,	
       	PARENT_SERVICE_ID, 	
       	SERVICE_NAME
 	FROM 
        MST_SERVICE
 	WHERE
      PARENT_SERVICE_ID='10'
  	AND
      CALL_REQ_TYPE_ID='2'
  	AND
	  DELETE_USER IS NULL
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(SLCT003_PS_203)
	SELECT 
  		CALL_RESULT_ID,
  		PARENT_CALL_RESULT_ID,
  		CALL_RESULT_NAME 
 	FROM
  		MST_CALL_RESULT 
 	WHERE
  		PARENT_CALL_RESULT_ID='2'
  	AND
		DELETE_USER IS NULL
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(SLCT004_PS_203)
	SELECT 
  		CALL_RATE_ID,
  		PARENT_CALL_RATE_ID,
  		CALL_RATE_NAME 
 	FROM
  		MST_CALL_RATE
 	WHERE
  		PARENT_CALL_RATE_ID='1'
  	AND
		DELETE_USER IS NULL
END
-----------------------------------------------------------
-----------------------APPEAL------------------------------
-----------------------------------------------------------
BEGIN(SLCT001_PS_204)
	SELECT 
		  SERVICE_ID
		, PARENT_SERVICE_ID
		, SERVICE_NAME
	FROM 
	 	MST_SERVICE
	WHERE
		PARENT_SERVICE_ID='5'
	AND
		CALL_REQ_TYPE_ID='3'
	AND
		DELETE_USER IS NULL
END
-----------------------------------------------------------
BEGIN(SLCT003_PS_204)
	SELECT
		  UNIT_ID
		, PARENT_UNIT_ID
		, UNIT_NAME
	FROM 
		MST_UNIT	
  	WHERE 
       	PARENT_UNIT_ID = '1'
    AND
		DELETE_USER IS NULL
END
-----------------------------------------------------------
BEGIN(SLCT002_PS_204)
	SELECT 
		  SERVICE_ID
		, PARENT_SERVICE_ID
		, SERVICE_NAME 
	FROM MST_SERVICE 
	WHERE
		PARENT_SERVICE_ID='11'
  	AND
    CALL_REQ_TYPE_ID='3'
	AND
		DELETE_USER IS NULL
END
-----------------------------------------------------------
BEGIN(SLCT004_PS_204)
	SELECT
		  CALL_SORT_ID
		, PARENT_CALL_SORT_ID
		, CALL_SORT_NAME
	FROM 
      	MST_CALL_SORT	
	WHERE
      	PARENT_CALL_SORT_ID = '3'
      AND
		DELETE_USER IS NULL
END
-----------------------------------------------------------
BEGIN(SLCT005_PS_204)
	SELECT
		  CALL_RESULT_ID
		, PARENT_CALL_RESULT_ID
		, CALL_RESULT_NAME
	FROM 
		MST_CALL_RESULT	
    WHERE 
       	PARENT_CALL_RESULT_ID = '161'
       AND
		DELETE_USER IS NULL
END
-----------------------------------------------------------
BEGIN(SLCT007_PS_204)
	SELECT
		  CALL_RESULT_ID
		, PARENT_CALL_RESULT_ID
		, CALL_RESULT_NAME
	FROM 
		MST_CALL_RESULT	
    WHERE 
       	PARENT_CALL_RESULT_ID = '4'
       AND
		DELETE_USER IS NULL
END
-----------------------------------------------------------
BEGIN(SLCT006_PS_204)
	SELECT 
		  CALL_RATE_ID,
		  PARENT_CALL_RATE_ID,
		  CALL_RATE_NAME 
	FROM
		  MST_CALL_RATE
	WHERE
		  PARENT_CALL_RATE_ID='2'
		 AND
		DELETE_USER IS NULL
END
-----------------------------------------------------------
-----------------------TRANSFER----------------------------
-----------------------------------------------------------
BEGIN(SLCT001_PS_205)
	SELECT 
		UNIT_ID,
		PARENT_UNIT_ID,
		UNIT_NAME 
	FROM
		MST_UNIT 
	WHERE
		PARENT_UNIT_ID='1'
	AND
		DELETE_USER IS NULL
END
-----------------------------------------------------------
BEGIN(SLCT002_PS_205)
	SELECT 
		 CALL_RESULT_ID,
		 PARENT_CALL_RESULT_ID,
		 CALL_RESULT_NAME
	FROM
		 MST_CALL_RESULT 
	WHERE
		 PARENT_CALL_RESULT_ID='2'
	AND
		DELETE_USER IS NULL
END
-----------------------------------------------------------
BEGIN(SLCT003_PS_205)
	SELECT 
		 CALL_RATE_ID,
		 PARENT_CALL_RATE_ID,
		 CALL_RATE_NAME 
	FROM
		 MST_CALL_RATE
	WHERE
		 PARENT_CALL_RATE_ID='1'
	AND
		DELETE_USER IS NULL
END
-----------------------------------------------------------
--------------------------OTHER----------------------------
-----------------------------------------------------------
BEGIN(SLCT001_PS_206)
	SELECT 
		UNIT_ID,
		PARENT_UNIT_ID,
		UNIT_NAME 
	FROM
		MST_UNIT 
	WHERE
		PARENT_UNIT_ID='1'
	AND
		DELETE_USER IS NULL
END
-----------------------------------------------------------
BEGIN(SLCT002_PS_206)
	SELECT 
		CALL_RESULT_ID,
		PARENT_CALL_RESULT_ID,
		CALL_RESULT_NAME 
	FROM
		MST_CALL_RESULT 
	WHERE
		PARENT_CALL_RESULT_ID='1'
	AND
		DELETE_USER IS NULL
END
-----------------------------------------------------------
BEGIN(SLCT003_PS_206)
	SELECT 
		CALL_RATE_ID,
		PARENT_CALL_RATE_ID,
		CALL_RATE_NAME 
	FROM
		MST_CALL_RATE
	WHERE
		PARENT_CALL_RATE_ID='1'
	AND
		DELETE_USER IS NULL
END
-----------------------------------------------------------
-----------------------------------------------------------
--------------------------AGENT----------------------------
BEGIN(INSRT_AGENT)
	INSERT INTO 
	MST_AGENT(
	AGENT_ID,
	FIRSTNAME,
	LASTNAME,
	AGENT_REGISTER,
	PASSWORD,
	EMAIL,
	AGENT_PERMISSION,
	INSERT_USER,
	INSERT_DATE) 
	VALUES(
	:AGENT_ID,
	:FIRSTNAME,
	:LASTNAME,
	:AGENT_REGISTER,
	:PASSWORD,
	:EMAIL,
	:AGENT_PERMISSION,
	:INSERT_USER,
	TO_TIMESTAMP(:INSERT_DATE,'YYYY-MM-DD HH24:MI:SS')) 
END
-----------------------------------------------------------
BEGIN(DELETE_AGENT)
	DELETE FROM MST_AGENT 
	WHERE AGENT_ID=:AGENT_ID
END
-----------------------------------------------------------
BEGIN(UPDT_AGENT)
	UPDATE MST_AGENT
	SET LASTNAME=:LASTNAME,
	FIRSTNAME=:FIRSTNAME,
	AGENT_REGISTER=:AGENT_REGISTER,
	AGENT_ID=:AGENT_ID,
	PASSWORD=:PASSWORD,
	AGENT_PERMISSION=:AGENT_PERMISSION,
	EMAIL=:EMAIL,
	UPDATE_USER=:UPDATE_USER,
	UPDATE_DATE=TO_TIMESTAMP(:UPDATE_DATE,'YYYY-MM-DD HH24:MI:SS')
	WHERE AGENT_ID=:ORIGINAL_AGENT_ID
END	
--------------------------------------------------------------------
----------------------SECONDCOMBOCHANGE-----------------------------
--------------------------------------------------------------------
BEGIN(SECOND_COMBO_CHANGE)
	SELECT 
	:PRIMARY_KEY,
	:PRIMARY_NAME
	FROM :PRIMARY_TABLE
	WHERE
	DELETE_DATE IS NULL
	AND
	:PARENT_PRIMARY_KEY=:PARENT_PRIMARY_ID
END
--------------------------------------------------------------------	
	 


--------------------------------------------------------------------
---------------------------BALUU TEMP-------------------------------
--------------------------------------------------------------------
BEGIN(CHK_CALL_HISTORY_SERVICE)
	SELECT CALL_HISTORY_ID
	FROM CALL_HISTORY_SERVICE
	WHERE 
	CALL_HISTORY_ID=:CALL_HISTORY_ID
	AND
	CALL_REQ_TYPE=:CALL_REQ_TYPE
	AND
	DELETE_USER IS NULL
END
--------------------------------------------------------------------
BEGIN(UPDT_CALL_HISTORY_SERVICE)
	UPDATE CALL_HISTORY_SERVICE
	SET CALL_NAME=:CALL_NAME
	,CALL_RESULT_ID=:CALL_RESULT_ID
	,CALL_RATE_ID=:CALL_RATE_ID
	,CALL_STAT_ID=:CALL_STAT_ID
	,DEADLINE=TO_DATE(:DEADLINE,'yyyy-mm-dd')
	,CALL_SORT_ID=:CALL_SORT_ID
	,UNIT_ID=:UNIT_ID
	,CALL_COMMENT=:CALL_COMMENT
	,SERVICE_TYPE1=:SERVICE_TYPE1
	,DETAILED_SERVICE_TYPE1=:DETAILED_SERVICE_TYPE1
	,SERVICE_TYPE2=:SERVICE_TYPE2
	,DETAILED_SERVICE_TYPE2=:DETAILED_SERVICE_TYPE2
	,SERVICE_TYPE3=:SERVICE_TYPE3
	,DETAILED_SERVICE_TYPE3=:DETAILED_SERVICE_TYPE3
	,SERVICE_TYPE4=:SERVICE_TYPE4
	,DETAILED_SERVICE_TYPE4=:DETAILED_SERVICE_TYPE4
	,SERVICE_TYPE5=:SERVICE_TYPE5
	,DETAILED_SERVICE_TYPE5=:DETAILED_SERVICE_TYPE5
	,UPDATE_USER=:UPDATE_USER
	,UPDATE_DATE=TO_TIMESTAMP(:UPDATE_DATE,'yyyy-mm-dd HH24:mi:ss')
	WHERE CALL_HISTORY_ID=:CALL_HISTORY_ID 
	AND CALL_REQ_TYPE=:CALL_REQ_TYPE
END
--------------------------------------------------------------------
BEGIN(INSRT_CALL_HISTORY_SERVICE)
	INSERT INTO CALL_HISTORY_SERVICE
	(CALL_HISTORY_ID,
	CALL_REQ_TYPE,
	CALL_NAME,
	CALL_RESULT_ID,
	CALL_RATE_ID,
	CALL_STAT_ID,
	DEADLINE,
	CALL_SORT_ID,
	UNIT_ID,
	CALL_COMMENT,
	SERVICE_TYPE1,
	DETAILED_SERVICE_TYPE1,
	SERVICE_TYPE2,
	DETAILED_SERVICE_TYPE2,
	SERVICE_TYPE3,
	DETAILED_SERVICE_TYPE3,
	SERVICE_TYPE4,
	DETAILED_SERVICE_TYPE4,
	SERVICE_TYPE5,
	DETAILED_SERVICE_TYPE5,
	INSERT_DATE,
	INSERT_USER)
	VALUES(:CALL_HISTORY_ID,
	:CALL_REQ_TYPE,
	:CALL_NAME,
	:CALL_RESULT_ID,
	:CALL_RATE_ID,
	:CALL_STAT_ID,
	TO_DATE(:DEADLINE,'yyyy-mm-dd'),
	:CALL_SORT_ID,
	:UNIT_ID,
	:CALL_COMMENT,
	:SERVICE_TYPE1,
	:DETAILED_SERVICE_TYPE1,
	:SERVICE_TYPE2,
	:DETAILED_SERVICE_TYPE2,
	:SERVICE_TYPE3,
	:DETAILED_SERVICE_TYPE3,
	:SERVICE_TYPE4,
	:DETAILED_SERVICE_TYPE4,
	:SERVICE_TYPE5,
	:DETAILED_SERVICE_TYPE5,
	TO_TIMESTAMP(:INSERT_DATE,'yyyy-mm-dd HH24:mi:ss'),
	:INSERT_USER)
END
--------------------------------------------------------------------
BEGIN(SLCT_CALL_STAT)
	SELECT
	    CALL_STAT_ID
	   ,PARENT_CALL_STAT_ID
	   ,CALL_STAT_NAME
	FROM MST_CALL_STAT
	WHERE PARENT_CALL_STAT_ID='1' AND DELETE_USER IS NULL
END
--------------------------------------------------------------------
BEGIN(SLCT_CALL_TYPE_ID)
	SELECT 
		 SERVICE_ID
		,SERVICE_NAME
	FROM MST_SERVICE
	WHERE (PARENT_SERVICE_ID=6 OR PARENT_SERVICE_ID=7 OR PARENT_SERVICE_ID=5 OR PARENT_SERVICE_ID=8) AND DELETE_USER IS NULL
END
--------------------------------------------------------------------
BEGIN(SLCT_INSERT_AGENT)
	SELECT 
		 AGENT_REGISTER
		,FIRSTNAME
	FROM MST_AGENT
	WHERE 1=1 AND DELETE_USER IS NULL
END
--------------------------------------------------------------------
BEGIN(SLCT_CALL_RES_ID)
	SELECT 
		 CALL_RESULT_ID
		,CALL_RESULT_NAME
	FROM MST_CALL_RESULT
	WHERE PARENT_CALL_RESULT_ID>1 AND DELETE_USER IS NULL
END
--------------------------------------------------------------------
BEGIN(SLCT_AGENT_ID)
	SELECT 
		AGENT_ID,
		AGENT_ID || '-' || FIRSTNAME AS AGENT_NAME 
	FROM MST_AGENT
	WHERE 1=1 AND DELETE_USER IS NULL
END
------------------------------------------------------------------
BEGIN(SLCT_TASK_TYPE)
	SELECT
	    CALL_HISTORY_ID
	   ,CALLTYPE
	FROM CALL_HISTORY
	WHERE CALLTYPE='2' AND DELETE_USER IS NULL
END

