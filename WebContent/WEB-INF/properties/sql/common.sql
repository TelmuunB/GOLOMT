------------------------------------------------------------------------------------
BEGIN(SELECT_AGENT)
	SELECT AGENT_ID, AGENT_ID, FIRSTNAME, LASTNAME, PASSWORD, AGENT_PERMISSION, EMAIL
	FROM MST_AGENT
	WHERE AGENT_ID = :AGENT_ID AND PASSWORD = :PASSWORD AND DELETE_USER IS NULL
END
------------------------------------------------------------------------------------
BEGIN(SLCT_AGENT_BY_LAST_SESSION)
	SELECT
		MA.AGENT_ID as AGENT_ID, AGENT_REGISTER, FIRSTNAME, LASTNAME, PASSWORD,
		AGENT_PERMISSION, EMAIL
	FROM
		MST_AGENT MA
	LEFT JOIN MST_AGENT_LOG MAL ON MAL.AGENT_ID = MA.AGENT_ID
	WHERE LAST_SESSION = :LAST_SESSION AND (SELECT MAX(UPDATE_DATE) FROM MST_AGENT_LOG WHERE AGENT_ID = MA.AGENT_ID) = MAL.UPDATE_DATE
END
------------------------------------------------------------------------------------
BEGIN(UPDATE_AGENT_LAST_SESSION)
	UPDATE MST_AGENT_LOG SET LAST_SESSION = :CURRENT_SESSION
	WHERE LAST_SESSION = :LAST_SESSION
END
------------------------------------------------------------------------------------
BEGIN(SELECT_AGENT_COMPLAINT)
	SELECT EMAIL
	FROM MST_AGENT
	WHERE AGENT_PERMISSION = '2' AND DELETE_USER IS NULL
END

------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
BEGIN(SLCT_AGENT_LIST)
	SELECT AGENT_ID, AGENT_ID || '-' || FIRSTNAME ||  ' ' || LASTNAME as AGENT_NAME
	FROM MST_AGENT
	WHERE (AGENT_PERMISSION = '0' OR AGENT_PERMISSION = '1') AND DELETE_USER IS NULL
END
------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
BEGIN(SLCT_CALL_TYPE_LIST)
	SELECT CALL_REQ_TYPE_ID, CALL_REQ_TYPE_NAME
	FROM MST_CALL_REQ_TYPE
END
------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
BEGIN(SLCT_CALL_HISTORY_PAGING)
	SELECT * FROM (
		SELECT
			row_number() over (:ORDER) as RN, CALL_HISTORY_ID, PHONENUMBER, CALLERNAME, PHONENUMBER2, CALLTYPE, CALLTYPE AS CALL_TYPE, START_TIME, FINISHED_TIME, AGENTNAME, AGENT_ID,
			VIEW_STATUS, DURATION, HAS_REF, HAS_INFO, HAS_APP, HAS_COMP, HAS_TRA, HAS_OTHER
		FROM
			VIEW_CALL_HISTORY VCH
		WHERE :WHERE AND FINISHED_TIME IS NOT NULL
	)
	WHERE :PAGING
END
------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
BEGIN(SLCT_CALL_HISTORY)
		SELECT
			CALL_HISTORY_ID, PHONENUMBER, CALLERNAME, PHONENUMBER2, CALLTYPE, START_TIME, FINISHED_TIME, AGENTNAME, AGENT_ID,
			VIEW_STATUS, DURATION, HAS_REF, HAS_INFO, HAS_APP, HAS_COMP, HAS_TRA, HAS_OTHER
		FROM
			VIEW_CALL_HISTORY VCH
		WHERE :WHERE 
END
------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
BEGIN(SLCT_OUT_CALL_HISTORY)
		SELECT
			 HIS.CALL_REQ_TYPE AS CALL_REQ_TYPE
			,TYP.CALL_REQ_TYPE_NAME AS CALL_REQ_TYPE_NAME
			,HIS.CALL_NAME AS CALL_NAME
			,HIS.CALL_RESULT_ID AS CALL_RESULT_ID
			,HIS.CALL_RATE_ID AS CALL_RATE_ID
			,HIS.CALL_STAT_ID AS CALL_STAT_ID
			,HIS.DEADLINE AS DEADLINE
			,HIS.CALL_SORT_ID AS CALL_SORT_ID
			,HIS.CALL_COMMENT AS CALL_COMMENT
			,HIS.TASK_COMMENT AS TASK_COMMENT
			,HIS.CALL_HISTORY_ID AS CALL_HISTORY_ID
		FROM
			CALL_HISTORY_SERVICE HIS
		LEFT JOIN MST_CALL_REQ_TYPE TYP ON TYP.CALL_REQ_TYPE_ID=HIS.CALL_REQ_TYPE
		WHERE :WHERE
END
------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
BEGIN(SLCT_CALL_HISTORY_CNT)
	SELECT
		count(*) as CNT
	FROM
		VIEW_CALL_HISTORY VCH
	WHERE :WHERE AND FINISHED_TIME IS NOT NULL
END

------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
BEGIN(SLCT_CALL_HISTORY_SERVICE)
SELECT * FROM
	(
		SELECT
			CALL_HISTORY_SERVICE_ID, CS.CALL_REQ_TYPE as CALL_REQ_TYPE,  CR.CALL_REQ_TYPE_NAME, CALL_NAME,
			CS.CALL_RESULT_ID, CALL_RESULT_NAME, CS.CALL_RATE_ID,  
			CASE 
			WHEN CALL_RATE_NAME IS NULL THEN :SYSTEMRATE
      		ELSE CALL_RATE_NAME
      		END AS CALL_RATE_NAME, 
      		CS.CALL_STAT_ID, CALL_STAT_NAME, to_char(DEADLINE, 'YYYY-MM-DD') as DEADLINE, CS.CALL_SORT_ID, CALL_SORT_NAME,
			CS.UNIT_ID, UNIT_NAME, CALL_COMMENT, TRANSFER_PHONENUMBER,TRANSFER_CHECK,
			(SELECT SERVICE_NAME FROM MST_SERVICE WHERE SERVICE_ID = SERVICE_TYPE1 AND CS.CALL_REQ_TYPE = CALL_REQ_TYPE_ID) as SERVICE_NAME1, SERVICE_TYPE1,
			(SELECT SERVICE_NAME FROM MST_SERVICE WHERE SERVICE_ID = DETAILED_SERVICE_TYPE1 AND CS.CALL_REQ_TYPE = CALL_REQ_TYPE_ID) as DETAILED_SERVICE_NAME1, DETAILED_SERVICE_TYPE1,

			(SELECT SERVICE_NAME FROM MST_SERVICE WHERE SERVICE_ID = SERVICE_TYPE2 AND CS.CALL_REQ_TYPE = CALL_REQ_TYPE_ID) as SERVICE_NAME2, SERVICE_TYPE2,
			(SELECT SERVICE_NAME FROM MST_SERVICE WHERE SERVICE_ID = DETAILED_SERVICE_TYPE2 AND CS.CALL_REQ_TYPE = CALL_REQ_TYPE_ID) as DETAILED_SERVICE_NAME2, DETAILED_SERVICE_TYPE2,

			(SELECT SERVICE_NAME FROM MST_SERVICE WHERE SERVICE_ID = SERVICE_TYPE3 AND CS.CALL_REQ_TYPE = CALL_REQ_TYPE_ID) as SERVICE_NAME3, SERVICE_TYPE3,
			(SELECT SERVICE_NAME FROM MST_SERVICE WHERE SERVICE_ID = DETAILED_SERVICE_TYPE3 AND CS.CALL_REQ_TYPE = CALL_REQ_TYPE_ID) as DETAILED_SERVICE_NAME3, DETAILED_SERVICE_TYPE3,

			(SELECT SERVICE_NAME FROM MST_SERVICE WHERE SERVICE_ID = SERVICE_TYPE4 AND CS.CALL_REQ_TYPE = CALL_REQ_TYPE_ID) as SERVICE_NAME4, SERVICE_TYPE4,
			(SELECT SERVICE_NAME FROM MST_SERVICE WHERE SERVICE_ID = DETAILED_SERVICE_TYPE4 AND CS.CALL_REQ_TYPE = CALL_REQ_TYPE_ID) as DETAILED_SERVICE_NAME4, DETAILED_SERVICE_TYPE4,

			(SELECT SERVICE_NAME FROM MST_SERVICE WHERE SERVICE_ID = SERVICE_TYPE5 AND CS.CALL_REQ_TYPE = CALL_REQ_TYPE_ID) as SERVICE_NAME5, SERVICE_TYPE5,
			(SELECT SERVICE_NAME FROM MST_SERVICE WHERE SERVICE_ID = DETAILED_SERVICE_TYPE5 AND CS.CALL_REQ_TYPE = CALL_REQ_TYPE_ID) as DETAILED_SERVICE_NAME5, DETAILED_SERVICE_TYPE5,

			SEND_STATUS,
			CS.CALL_HISTORY_ID, VIEW_STATUS
		FROM
			CALL_HISTORY_SERVICE CS
		LEFT JOIN MST_CALL_REQ_TYPE CR ON CR.CALL_REQ_TYPE_ID =  CS.CALL_REQ_TYPE
		LEFT JOIN VIEW_CALL_HISTORY CH ON CH.CALL_HISTORY_ID =  CS.CALL_HISTORY_ID
		LEFT JOIN MST_CALL_RATE MCR ON MCR.CALL_RATE_ID =  CS.CALL_RATE_ID
		LEFT JOIN MST_CALL_RESULT MCRS ON MCRS.CALL_RESULT_ID =  CS.CALL_RESULT_ID
		LEFT JOIN MST_CALL_SORT MCS ON MCS.CALL_SORT_ID =  CS.CALL_SORT_ID
		LEFT JOIN MST_CALL_STAT MCST ON MCST.CALL_STAT_ID =  CS.CALL_STAT_ID
		LEFT JOIN MST_UNIT MU ON MU.UNIT_ID =  CS.UNIT_ID
	)
	WHERE :WHERE
	:ORDER
END
------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
BEGIN(SLCT_CALL_HISTORY_SERVICE_CNT)
	SELECT
		count(*) as CNT
	FROM
		CALL_HISTORY_SERVICE
	WHERE :WHERE
END
------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
BEGIN(SLCT_CALLER_DETAIL)
	SELECT
		PHONENUMBER, FIRSTNAME, LASTNAME, PHONENUMBER2, STATUS
	FROM
		MST_USERS
	WHERE DELETE_DATE IS NULL AND DELETE_USER IS NULL AND :WHERE
END
------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
BEGIN(SLCT_CALLER_DETAIL_INSERT)
	INSERT INTO
		MST_USERS (PHONENUMBER, INSERT_DATE, INSERT_USER)
	VALUES
		(:PHONENUMBER, sysdate, :AGENT_ID)
END
------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
BEGIN(SLCT_CALLER_DETAIL_INSERT2)
	INSERT INTO
		MST_USERS (PHONENUMBER, PHONENUMBER2, INSERT_DATE, INSERT_USER)
	VALUES
		(:PHONENUMBER, :PHONENUMBER2, sysdate, :AGENT_ID)
END
------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
BEGIN(SLCT_CALLER_DETAIL_INSERT3)
	INSERT INTO
		MST_USERS (PHONENUMBER, FIRSTNAME, INSERT_DATE, INSERT_USER)
	VALUES
		(:PHONENUMBER, :FIRSTNAME, sysdate, :AGENT_ID)
END
------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
BEGIN(SLCT_CALLER_DETAIL_UPDATE)
	UPDATE MST_USERS SET :SET
	WHERE PHONENUMBER = :PHONENUMBER OR PHONENUMBER2 IS NOT NULL AND PHONENUMBER = PHONENUMBER2
END
------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
BEGIN(UPDATE_CALL_HISTORY_PHONE2)
	UPDATE CALL_HISTORY SET PHONENUMBER2=:PHONENUMBER2 WHERE CALL_HISTORY_ID=:CALL_HISTORY_ID
END
------------------------------------------------------------------------------------
BEGIN(SLCT_CALL_HISTORY_INSERT)
	INSERT INTO
		CALL_HISTORY (PHONENUMBER, CALLTYPE, START_TIME, AGENT_ID, INSERT_DATE, INSERT_USER)
	values
	    (:PHONENUMBER, '0', sysdate, :AGENT_ID, sysdate, :AGENT_ID)
END
------------------------------------------------------------------------------------
BEGIN(SLCT_CALL_HISTORY_INSERT_MISS)
	INSERT INTO
		CALL_HISTORY (PHONENUMBER, CALLTYPE, START_TIME, FINISHED_TIME ,AGENT_ID, INSERT_DATE, INSERT_USER)
	values
	    (:PHONENUMBER, '3', sysdate, sysdate, :AGENT_ID, sysdate, :AGENT_ID)
END
------------------------------------------------------------------------------------
BEGIN(SLCT_CALL_HISTORY_INSERT_COMPLAINT)
	INSERT INTO
		CALL_HISTORY (PHONENUMBER, CALLTYPE, START_TIME, FINISHED_TIME, AGENT_ID, INSERT_DATE, INSERT_USER)
	values
	    (:PHONENUMBER, '5', systimestamp, systimestamp, :AGENT_ID, sysdate, :AGENT_ID)
END
------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
BEGIN(OUT_CALL_HISTORY_INSERT)
	INSERT INTO
		CALL_HISTORY (PHONENUMBER, CALLTYPE, START_TIME, AGENT_ID, INSERT_DATE, INSERT_USER)
	values
	    (:PHONENUMBER, '1', sysdate, :AGENT_ID, sysdate, :AGENT_ID)
END
------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
BEGIN(SLCT_CALL_HISTORY_UPDATE)
	UPDATE CALL_HISTORY SET FINISHED_TIME = sysdate
	WHERE CALL_HISTORY_ID = :CALL_HISTORY_ID
END
------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
BEGIN(SLCT_CALL_HISTORY_MAX)
	SELECT
		MAX(CALL_HISTORY_ID) as CALL_HISTORY_ID
	FROM
	    CALL_HISTORY
	WHERE
	   PHONENUMBER = :PHONENUMBER AND AGENT_ID = :AGENT_ID
END
------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
BEGIN(SLCT_CALL_SERVICE_INSERT)
	INSERT INTO
		CALL_HISTORY_SERVICE (CALL_REQ_TYPE, INSERT_DATE, INSERT_USER, CALL_HISTORY_ID)
	values
	    (:CALL_REQ_TYPE, sysdate, :AGENT_ID, :CALL_HISTORY_ID)
END
------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
BEGIN(SLCT_CALL_SERVICE_UPDATE)
	UPDATE CALL_HISTORY_SERVICE SET :SET
	WHERE CALL_HISTORY_ID = :CALL_HISTORY_ID AND CALL_REQ_TYPE = :CALL_REQ_TYPE
END

------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
BEGIN(SLCT_OUT_CALL_SERVICE_UPDATE)
	UPDATE CALL_HISTORY_SERVICE SET :SET
	WHERE CALL_HISTORY_ID = :CALL_HISTORY_ID AND :CALL_REQ_TYPE = :CALL_REQ_TYPE_ID
END

------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
BEGIN(SLCT_MST_SERVICE)
	SELECT
		SERVICE_ID,
		SERVICE_NAME
	FROM
		MST_SERVICE
	WHERE DELETE_DATE IS NULL
	AND (PERMITTED_AGENT = :AGENT_ID OR PERMITTED_AGENT IS NULL)
	AND CALL_REQ_TYPE_ID = :CALL_REQ_TYPE_ID
	AND DISPLAY_ID = '1'
	ORDER BY ORDER_NUM
END
------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
BEGIN(SLCT_MST_SERVICE_DETAIL)
	SELECT SERVICE_ID,
		SERVICE_NAME
	FROM
		MST_SERVICE
	WHERE DELETE_DATE IS NULL
	AND (PERMITTED_AGENT = :AGENT_ID OR PERMITTED_AGENT IS NULL)
	AND CALL_REQ_TYPE_ID = :CALL_REQ_TYPE_ID
	AND PARENT_SERVICE_ID = :PARENT_SERVICE_ID
	ORDER BY ORDER_NUM
END
------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
BEGIN(INSERT_LOG)
	INSERT INTO
		MST_AGENT_LOG (AGENT_ID, UPDATE_DATE, STATUS, LAST_SESSION)
	values
	    (:AGENT_ID, sysdate, :STATUS, :LAST_SESSION)
END
------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
BEGIN(SLCT_CALL_RESULT_INFO)
	SELECT
		MIGRATION_NUM
	FROM
		MST_CALL_RESULT
	WHERE
		CALL_RESULT_ID = :CALL_RESULT_ID
END
------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
BEGIN(INS_COMPLAINT_FROM_CALL)
	INSERT INTO
		 COMPLAINT
			(CALL_HISTORY_ID, PHONE_NUMBER_ONE, CALLER_NAME, SERVICE_ID, DETAILED_SERVICE_ID, UNIT_ID, CALL_SORT_ID,
		     CALL_RATE_ID, CALL_RESULT_ID, COMPLAINT_COMMENT, DEADLINE, INSERT_DATE, INSERT_USER, AGENT_ID,SOLVED_STATUS)

	SELECT
		CH.CALL_HISTORY_ID,
		CH.PHONENUMBER,
		MU.FIRSTNAME,
		CHS.SERVICE_TYPE:ID,
		CHS.DETAILED_SERVICE_TYPE:ID,
		CHS.UNIT_ID,
		CHS.CALL_SORT_ID,
		CHS.CALL_RATE_ID,
		CHS.CALL_RESULT_ID,
		CHS.CALL_COMMENT,
		CHS.DEADLINE,
		sysdate,
		CH.AGENT_ID,
		CH.AGENT_ID,
		'0' AS SOLVED_STATUS
	FROM CALL_HISTORY CH
	LEFT JOIN MST_USERS MU ON MU.PHONENUMBER = CH.PHONENUMBER
	LEFT JOIN CALL_HISTORY_SERVICE CHS ON CHS.CALL_HISTORY_ID = CH.CALL_HISTORY_ID AND CHS.CALL_REQ_TYPE = 4 AND CHS.TRANSFER_CHECK = 1

	WHERE CHS.SERVICE_TYPE:ID IS NOT NULL AND CH.CALL_HISTORY_ID = :CALL_HISTORY_ID AND CH.FINISHED_TIME IS NOT NULL
END
------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
BEGIN(INS1_COMPLAINT_FROM_CALL)
	INSERT INTO
		 COMPLAINT
			(CALL_HISTORY_ID, PHONE_NUMBER_ONE, CALLER_NAME, SERVICE_ID, DETAILED_SERVICE_ID, UNIT_ID, CALL_SORT_ID,
		     CALL_RATE_ID, CALL_RESULT_ID, COMPLAINT_COMMENT, DEADLINE, INSERT_DATE, INSERT_USER, AGENT_ID,SOLVED_STATUS)

		SELECT
		CH.CALL_HISTORY_ID,
		CH.PHONENUMBER,
		MU.FIRSTNAME,
		CHS.SERVICE_TYPE:ID,
		CHS.DETAILED_SERVICE_TYPE:ID,
		CHS.UNIT_ID,
		CHS.CALL_SORT_ID,
		CHS.CALL_RATE_ID,
		CHS.CALL_RESULT_ID,
		CHS.CALL_COMMENT,
		CHS.DEADLINE,
		sysdate,
		CH.AGENT_ID,
		CH.AGENT_ID,
		'1' AS SOLVED_STATUS
	FROM CALL_HISTORY CH
	LEFT JOIN MST_USERS MU ON MU.PHONENUMBER = CH.PHONENUMBER
	LEFT JOIN CALL_HISTORY_SERVICE CHS ON CHS.CALL_HISTORY_ID = CH.CALL_HISTORY_ID AND CHS.CALL_REQ_TYPE = 4
    LEFT JOIN MST_CALL_RESULT MCR ON MCR.CALL_RESULT_ID = CHS.CALL_RESULT_ID
	WHERE CHS.SERVICE_TYPE:ID IS NOT NULL AND CH.CALL_HISTORY_ID = :CALL_HISTORY_ID AND CH.FINISHED_TIME IS NOT NULL AND MCR.MIGRATION_NUM='2'
END
------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
BEGIN(INS2_COMPLAINT_FROM_CALL)
	INSERT INTO
		 COMPLAINT
			(CALL_HISTORY_ID, PHONE_NUMBER_ONE, CALLER_NAME, SERVICE_ID, DETAILED_SERVICE_ID, UNIT_ID, CALL_SORT_ID,
		     CALL_RATE_ID, CALL_RESULT_ID, COMPLAINT_COMMENT, DEADLINE, INSERT_DATE, INSERT_USER, AGENT_ID,SOLVED_STATUS)

	SELECT
		CH.CALL_HISTORY_ID,
		CH.PHONENUMBER,
		MU.FIRSTNAME,
		CHS.SERVICE_TYPE:ID,
		CHS.DETAILED_SERVICE_TYPE:ID,
		CHS.UNIT_ID,
		CHS.CALL_SORT_ID,
		CHS.CALL_RATE_ID,
		CHS.CALL_RESULT_ID,
		CHS.CALL_COMMENT,
		CHS.DEADLINE,
		sysdate,
		CH.AGENT_ID,
		CH.AGENT_ID,
		CASE
      		WHEN MCR.MIGRATION_NUM='1' THEN '2'
     	 	WHEN MCR.MIGRATION_NUM='2' THEN '1'
      		WHEN MCR.MIGRATION_NUM='3' THEN '0'
      		ELSE '1'
    	END AS SOLVED_STATUS
	FROM CALL_HISTORY CH
	LEFT JOIN MST_USERS MU ON MU.PHONENUMBER = CH.PHONENUMBER
	LEFT JOIN CALL_HISTORY_SERVICE CHS ON CHS.CALL_HISTORY_ID = CH.CALL_HISTORY_ID AND CHS.CALL_REQ_TYPE = 4
	LEFT JOIN MST_CALL_RESULT MCR ON MCR.CALL_RESULT_ID=CHS.CALL_RESULT_ID
	WHERE CHS.SERVICE_TYPE:ID IS NOT NULL AND CH.CALL_HISTORY_ID = :CALL_HISTORY_ID AND CH.FINISHED_TIME IS NOT NULL
END
------------------------------------------------------------------------------------
BEGIN(INS3_COMPLAINT_FROM_CALL)
	INSERT INTO
		 COMPLAINT
			(CALL_HISTORY_ID, PHONE_NUMBER_ONE, CALLER_NAME, SERVICE_ID, DETAILED_SERVICE_ID, UNIT_ID, CALL_SORT_ID,
		     CALL_RATE_ID, CALL_RESULT_ID, COMPLAINT_COMMENT, DEADLINE, INSERT_DATE, INSERT_USER, AGENT_ID,SOLVED_STATUS)

	SELECT
		CH.CALL_HISTORY_ID,
		CH.PHONENUMBER,
		MU.FIRSTNAME,
		CHS.SERVICE_TYPE:ID,
		CHS.DETAILED_SERVICE_TYPE:ID,
		CHS.UNIT_ID,
		CHS.CALL_SORT_ID,
		CHS.CALL_RATE_ID,
		CHS.CALL_RESULT_ID,
		CHS.CALL_COMMENT,
		CHS.DEADLINE,
		sysdate,
		CH.AGENT_ID,
		CH.AGENT_ID,
		'0' AS SOLVED_STATUS
	FROM CALL_HISTORY CH
	LEFT JOIN MST_USERS MU ON MU.PHONENUMBER = CH.PHONENUMBER
	LEFT JOIN CALL_HISTORY_SERVICE CHS ON CHS.CALL_HISTORY_ID = CH.CALL_HISTORY_ID AND CHS.CALL_REQ_TYPE = 4
	LEFT JOIN MST_CALL_RESULT MCR ON MCR.CALL_RESULT_ID=CHS.CALL_RESULT_ID
	WHERE CHS.SERVICE_TYPE:ID IS NOT NULL AND CH.CALL_HISTORY_ID = :CALL_HISTORY_ID AND MCR.MIGRATION_NUM='3'
END

------------------------------------------------------------------------------------
BEGIN(DELETE_FROM_COMPLAINT)
	DELETE FROM COMPLAINT
	WHERE CALL_HISTORY_ID = :CALL_HISTORY_ID
END
------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
BEGIN(DELETE_FROM_TASK)
	DELETE FROM TASK
	WHERE CALL_HISTORY_ID = :CALL_HISTORY_ID
END
------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
BEGIN(INS_TASK_FROM_CALL)
	INSERT INTO
		 TASK
			(CALL_HISTORY_ID, CALLTYPE, PHONE_NUMBER_ONE, PHONE_NUMBER_TWO, CALLER_NAME, AGENT_ID, INSERT_AGENT, TASK_NAME,
			 TASK_COMMENT, DEADLINE, ACTIVE_TASK, INSERT_DATE, INSERT_USER, CALL_REQ_TYPE_ID, CALL_HISTORY_SERVICE_ID)

		SELECT
			CH.CALL_HISTORY_ID,
			0,
			CH.PHONENUMBER,
			MU.PHONENUMBER2,
			MU.FIRSTNAME,
			CH.AGENT_ID,
			CH.AGENT_ID,
			CHS.CALL_NAME,
			CHS.CALL_COMMENT,
			CHS.DEADLINE,
			1,
			sysdate,
			CH.AGENT_ID,
			CHS.CALL_REQ_TYPE,
			CHS.CALL_HISTORY_SERVICE_ID
		FROM CALL_HISTORY CH
		LEFT JOIN MST_USERS MU ON MU.PHONENUMBER = CH.PHONENUMBER
		LEFT JOIN CALL_HISTORY_SERVICE CHS ON CHS.CALL_HISTORY_ID = CH.CALL_HISTORY_ID AND (CHS.TRANSFER_CHECK IS NULL OR CHS.TRANSFER_CHECK <> 1)
		LEFT JOIN MST_CALL_RESULT MCR ON MCR.CALL_RESULT_ID = CHS.CALL_RESULT_ID

	WHERE CH.CALL_HISTORY_ID = :CALL_HISTORY_ID AND MCR.MIGRATION_NUM = 1 AND CH.FINISHED_TIME IS NOT NULL
END
------------------------------------------------------------------------------------
BEGIN(INS1_TASK_FROM_CALL)
	INSERT INTO
		 TASK
			(CALL_HISTORY_ID, CALLTYPE, PHONE_NUMBER_ONE, PHONE_NUMBER_TWO, CALLER_NAME, AGENT_ID, INSERT_AGENT, TASK_NAME,
			 TASK_COMMENT, DEADLINE, ACTIVE_TASK, INSERT_DATE, INSERT_USER, CALL_REQ_TYPE_ID, CALL_HISTORY_SERVICE_ID)

	SELECT
			CH.CALL_HISTORY_ID,
			0,
			CH.PHONENUMBER,
			MU.PHONENUMBER2,
			MU.FIRSTNAME,
			CH.AGENT_ID,
			CH.AGENT_ID,
			CHS.CALL_NAME,
			CHS.CALL_COMMENT,
			CHS.DEADLINE,
			1,
			sysdate,
			CH.AGENT_ID,
			CHS.CALL_REQ_TYPE,
			CHS.CALL_HISTORY_SERVICE_ID
		FROM CALL_HISTORY CH
		LEFT JOIN MST_USERS MU ON MU.PHONENUMBER = CH.PHONENUMBER
		LEFT JOIN CALL_HISTORY_SERVICE CHS ON CHS.CALL_HISTORY_ID = CH.CALL_HISTORY_ID AND (CHS.TRANSFER_CHECK IS NULL OR CHS.TRANSFER_CHECK <> 1)
		LEFT JOIN MST_CALL_RESULT MCR ON MCR.CALL_RESULT_ID = CHS.CALL_RESULT_ID
    WHERE CH.CALL_HISTORY_ID = :CALL_HISTORY_ID AND MCR.MIGRATION_NUM = '1' AND CH.FINISHED_TIME IS NOT NULL
END
------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
BEGIN(SLCT_UNIT_LIST)
	SELECT
		UNIT_ID, UNIT_NAME
	FROM MST_UNIT

	WHERE DELETE_DATE IS NULL AND UPPER(UNIT_NAME) LIKE UPPER(:UNIT_NAME)
	ORDER BY ORDER_NUM
END
------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
BEGIN(INSRT_TASK_HIS_SERVICE)
	INSERT INTO
	 CALL_HISTORY_SERVICE(CALL_REQ_TYPE, CALL_NAME, DEADLINE, TASK_COMMENT, CALL_HISTORY_ID)
	 VALUES(:CALL_REQ_TYPE,:CALL_NAME,TO_DATE(:DEADLINE,'YYYY-MM-DD HH24:MI:SS. SSSSS'),:TASK_COMMENT,:CALL_HISTORY_ID )
END
------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
BEGIN(SLCT_TASK_FOR_ID)
	SELECT
		 CALL_REQ_TYPE_ID
		,TASK_NAME
		,DEADLINE
		,TASK_COMMENT
	FROM TASK
	WHERE
		:WHERE
END
------------------------------------------------------------------------------------
BEGIN(SLCT_CALLER_DETAILS_ON_CHANGE)
	SELECT FIRSTNAME || ' ' ||PHONENUMBER AS FIRSTNAME FROM MST_USERS WHERE :WHERE
END