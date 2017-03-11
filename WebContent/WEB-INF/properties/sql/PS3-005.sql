------------------------------------------------------------
BEGIN(HELP_TREE_PS_214)
	SELECT 
	ID,
	PARENT_ID,	
	NAME
	FROM
	HELP_TREE
	WHERE DELETE_DATE IS NULL
  	ORDER BY ID
	END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(HELP_TREE_CONTENT_PS_214)
	SELECT 
	ID,	
	CONTENT	
	FROM
	HELP_TREE
	WHERE DELETE_DATE IS NULL
	AND ID=:ID
	ORDER BY ID ASC	
	END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(DELETEROW_HELP_TREE_SORT_PS_214)
	UPDATE HELP_TREE SET DELETE_DATE=TO_TIMESTAMP(:DELETE_DATE,'YYYY-MM-DD:HH24:MI:SS')
	WHERE ID=:ID
END

-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(EDIT_HELP_TREE_214)
	SELECT
	ID,
	NAME,
	CONTENT
	FROM HELP_TREE
	WHERE
	ID=:ID
END
------------------------------------------------------------
------------------------------------------------------------
BEGIN(SAVEROW_HELP_TREE_PS_214)
	INSERT INTO HELP_TREE
	(PARENT_ID,
	NAME,
	CONTENT)
	VALUES
	(:PARENT_ID,
	:NAME,
	:CONTENT
	)
END
------------------------------------------------------------
-----------------------------------------------------
-----------------------------------------------------------
BEGIN(EDITROW_HELP_TREE_PS_214)
	UPDATE HELP_TREE
	SET ID=:ID 
	,NAME=:NAME
	,CONTENT=:CONTENT
	WHERE ID=:ID
END
-----------------------------------------------------------
BEGIN(SEARCH_HELP_TREE_PS_214)
	SELECT 
	ID,		
	NAME,
	CONTENT
	FROM
	HELP_TREE
	WHERE DELETE_DATE IS NULL
	:WHERE	
	END
-----------------------------------------------------------

	