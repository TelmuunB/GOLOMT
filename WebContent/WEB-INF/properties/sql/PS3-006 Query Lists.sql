BEGIN(SAVE_SMS_SEND)
	INSERT INTO MESSAGE
	(MESSAGE_SENDER,
	MESSAGE_RECEIVER,
	TEXT_MESSAGE,
	AGENT_ID,
	INSERT_USER,
	INSERT_DATE)
	VALUES
	(:MESSAGE_SENDER,
	:MESSAGE_RECEIVER,
	:TEXT_MESSAGE,
	:AGENT_ID,
	:INSERT_USER,
	TO_TIMESTAMP(:INSERT_DATE,'yyyy-mm-dd HH24:MI:SS')
	)
END
--------------------------------------------------------------------
--------------------------------------------------------------------
--------------------------------------------------------------------
BEGIN(UPDT_TASK_COMPLAINT_SOLVED)
	UPDATE COMPLAINT C
	SET C.SOLVED_STATUS=:SOLVED_STATUS
	WHERE C.CALL_HISTORY_ID=(SELECT T.CALL_HISTORY_ID FROM TASK T WHERE T.TASK_ID = :TASK_ID)
END
--------------------------------------------------------------------
--------------------------------------------------------------------
--------------------------------------------------------------------
BEGIN(COMPLAINT_TO_CALL_HISTORY_SERVICE)
	INSERT INTO CALL_HISTORY_SERVICE(
	CALL_REQ_TYPE,
	CALL_RESULT_ID,
	CALL_RATE_ID,
	CALL_SORT_ID,
	DEADLINE,
	UNIT_ID,
	CALL_COMMENT,
	SERVICE_TYPE1,
	DETAILED_SERVICE_TYPE1,
	INSERT_DATE,
	INSERT_USER,
	CALL_HISTORY_ID,
	TASK_COMMENT
	)SELECT
		'4',
		CALL_RESULT_ID,
		CALL_RATE_ID,
		CALL_SORT_ID,
		DEADLINE,
		UNIT_ID,
		COMPLAINT_COMMENT,
		SERVICE_ID,
		DETAILED_SERVICE_ID,
		INSERT_DATE,
		INSERT_USER,
		CALL_HISTORY_ID,
		COMPLAINT_COMMENT
	FROM
		COMPLAINT
	WHERE
		CALL_HISTORY_ID=:CALL_HISTORY_ID
END
-----------------------------------------------------------------------------
-----------------------------------------------------------------------------
BEGIN(ASSIGN_CALL_HISTORY_ID_TO_COMPLAINT)
	UPDATE COMPLAINT SET CALL_HISTORY_ID=:CALL_HISTORY_ID WHERE COMPLAINT_LIST_ID=:COMPLAINT_ID
END
-----------------------------------------------------------------------------
-----------------------------------------------------------------------------
BEGIN(SLCT_COMPLAINT_MAX)
	SELECT
		MAX(COMPLAINT_LIST_ID) as COMPLAINT_ID
	FROM
	    COMPLAINT
	WHERE
	   INSERT_USER = :INSERT_USER
END