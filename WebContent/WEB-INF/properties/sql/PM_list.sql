-----------------------------------------------------------
BEGIN(PM_SELECT_EXCEL_TEMPLATE)
SELECT 
  	FILE_ID, NAME, FILE, FILE_TYPE
FROM MST_FILES
WHERE NAME = :NAME
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(PM_UPDATE_EXCEL_TEMPLATE)
UPDATE MST_FILES
SET
  	FILE = ?, 
  	FILE_TYPE = ?
WHERE NAME = ?
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(PM_INSERT_EXCEL_TEMPLATE)
INSERT INTO MST_FILES
SET
	FILE = ?, 
  	FILE_TYPE = ?,
  	NAME = ?
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(PM_SELECT_STATE_BUDGET)
SELECT 
  COUNT(*) as COUNT
FROM MST_AIMAG
WHERE AIMAG_NUM NOT IN (
		SELECT
		  AIMAG_NUM
		FROM
		  MST_STATE_BUDGET
		WHERE
		  STATE_BUDGET_YEAR = :NOW_YEAR)
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(PM_SELECT_STATE_SAVINGS)
SELECT 
  COUNT(*) as COUNT
FROM MST_STATE_SAVING
WHERE STATE_SAVING_YEAR = :NOW_YEAR
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(PM_SELECT_CURRENCY)
SELECT
  COUNT(*) as COUNT
FROM
  MST_VOCURRENCY
WHERE
  VOCURRENCY_YEAR = :NOW_YEAR
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(SEL_OPEN)
      SELECT
  AID_STATUS
FROM
  MST_AID_OPEN
WHERE
  MST_YEAR=:NOW_YEAR
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(PM_SELECT_AIMAG)
      SELECT AIMAG_NUM, AIMAG_NAME FROM MST_AIMAG ORDER BY AIMAG_NAME ASC
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(PM_SELECT_MASTER)
      SELECT MASTERS_ITEMS_NUM, MASTERS_ITEMS_NAME 
      FROM MST_MASTERS_ITEMS
      WHERE MASTERS_NUM = :MASTERS_NUM
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(PM_SELECT_POSITION)
      SELECT POSITION_NUM, POSITION_NAME FROM MST_POSITION WHERE CHECKED=0
      ORDER BY POSITION_NAME
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(PM_SELECT_ORGANIZATION)
      SELECT 
     ORGANIZATION_REG_NUM, ORGANIZATION_NAME 
FROM 
     MST_ORGANIZATION
WHERE 
     DELETE_USER IS NULL
ORDER BY ORGANIZATION_NAME ASC
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(PM_SELECT_SUM)
SELECT 
     SUM_NUM, AIMAG_NUM, SUM_NAME
FROM 
     MST_SUM 
WHERE 
     DELETE_USER IS NULL AND AIMAG_NUM = :AIMAG_NUM
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(PM_SELECT_BAG)
SELECT 
     BAG_NUM, BAG_NAME
FROM 
     MST_BAG 
WHERE 
     DELETE_USER IS NULL AND SUM_NUM = :SUM_NUM
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(PM_SELECT_TRANS_TYPE)
      SELECT
  TRANSPORT_TYPE_NUM,TRANSPORT_TYPE_NAME
FROM
  MST_TRANSPORT_TYPE

END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(PM_SEL_CUR_NAME_OF_BAG)
      SELECT
  MA.AIMAG_NUM,MS.SUM_NUM,MB.BAG_NUM,MB.BAG_NAME,MB.UPDATE_DATE
FROM MST_BAG MB
JOIN MST_SUM MS
ON MB.SUM_NUM=MS.SUM_NUM
JOIN MST_AIMAG MA
ON MS.AIMAG_NUM=MA.AIMAG_NUM
WHERE
  MB.BAG_NUM=:BAG_NUM

END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(PM_SEL_TOCURRENCY_FOR_VALUE_OF_CURRENCY)
      SELECT
  TOCURRENCY_NUM,TOCURRENCY_NAME
FROM
  MST_TOCURRENCY
ORDER BY TOCURRENCY_NUM ASC
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(PM_SEL_YEAR_FOR_VALUE_OF_CURRENCY)
      SELECT
  VOCURRENCY_YEAR
FROM
  MST_VOCURRENCY
WHERE
 GROUP BY VOCURRENCY_YEAR
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(PM_SEL_RATING_OF_CIVIL_SERVICE)
      SELECT
  COCIVILSERVICE_NUM,COCIVILSERVICE
FROM
  MST_COCIVILSERVICE

END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(PM_SEL_CUR_ORG_FOR_COFFICIAL_OF_DEPARTMENT)
      SELECT
MO.ORGANIZATION_REG_NUM,V.AIMAG_NAME,V.SUM_NAME,V.BAG_NAME
FROM
  MST_ORGANIZATION MO
JOIN VW_ADDRESS V
ON MO.BAG_NUM=V.BAG_NUM
WHERE
  MO.ORGANIZATION_NAME LIKE :txtIDepart 
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(PM_SEL_AID_USER_COFFICIAL_OF_AID_USER)
      SELECT
VR.AID_NUM,
VR.REGISTRATION_NUM,
VR.LAST_NAME,
VR.FIRST_NAME,
MP.POSITION_NAME,
VA.SUM_NAME,
VA.AIMAG_NAME,
VA.BAG_NAME,
MO.ORGANIZATION_NAME
FROM
VW_AID_USER VR
Inner Join MST_ORGANIZATION MO ON VR.ORGANIZATION_ID = MO.ORGANIZATION_REG_NUM
Inner Join MST_POSITION MP ON VR.POSITION = MP.POSITION_NUM
Inner Join VW_ADDRESS VA ON VR.BAG_NUM = VA.BAG_NUM
WHERE 
VR.YEAR=:YEAR AND
VR.FIRST_NAME LIKE :txtSAid AND
VR.DELETE_USER is null

END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(SELCET_TRANSPORT)
      SELECT
    TRANSPORT_TYPE_NUM,
    TRANSPORT_TYPE_NAME
FROM MST_TRANSPORT_TYPE
WHERE
    DELETE_USER IS NULL
ORDER BY TRANSPORT_TYPE_NAME ASC
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(SELECT_CONSTRUCTION)
      SELECT
      CONSTRUCTION_TYPE_NUM
    , CONSTRUCTION_NAME
    , UPDATE_DATE
    , UPDATE_USER
    , DELETE_DATE
    , DELETE_USER
FROM MST_CONSTRUCTION
WHERE
   	DELETE_USER IS NULL
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(SELECT_FAMILY_MEMBER)
      SELECT
      FAMILY_MEMBER_NUM
    , FAMILY_MEMBER_NAME
    , UPDATE_DATE
    , UPDATE_USER
    , DELETE_DATE
    , DELETE_USER
FROM MST_FAMILY_MEMBER
WHERE
    DELETE_USER IS NULL
ORDER BY FAMILY_MEMBER_NAME ASC
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(SELECT_ANIMAL)
SELECT 
	ANIMAL_TYPE, ANIMAL_TYPE_NAME, UPDATE_DATE, 
	UPDATE_USER, INSERT_DATE, INSERT_USER, DELETE_DATE, 
	DELETE_USER 
FROM MST_ANIMAL M
ORDER BY ANIMAL_TYPE_NAME ASC
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(SELECT_BANK)
SELECT 
	BANK_NUM, BANK_NAME, UPDATE_DATE, UPDATE_USER, INSERT_DATE, 
	INSERT_USER, DELETE_DATE, DELETE_USER 
FROM MST_BANK M
ORDER BY BANK_NAME ASC
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(PM_SELECT_SEARCHCON)
SELECT
  ID, FORMID, SEARCHCON, USER_NUM FROM SEARCH_CON S
WHERE USER_NUM = :USER_NUM AND FORMID = :FORMID
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(PM_INSERT_SEARCHCON)
INSERT INTO SEARCH_CON (FORMID, SEARCHCON, USER_NUM)
VALUES (:FORMID, :SEARCHCON,:USER_NUM)
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(PM_DELETE_SEARCHCON)
	DELETE FROM SEARCH_CON WHERE USER_NUM = :USER_NUM
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(PM_UPDATE_SEARCHCON)
	UPDATE SEARCH_CON SET SEARCHCON = :SEARCHCON, FORMID = :FORMID WHERE USER_NUM = :USER_NUM AND FORMID = :FORMID
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(PM_SELECT_MENU)
	SELECT 
		ID, URL, TARGET, TITLE, PERMISSION, PARENT_ID, PROGID 
	FROM 
		MST_MAINMENU
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(PM_SELECT_MAIN_MENU)
	SELECT 
		ID, URL, TARGET, TITLE, PERMISSION, PARENT_ID, PROGID, HINT, HEIGHT, WIDTH, WINDOW_TYPE 
	FROM 
		MST_MAINMENU
	WHERE MENU_TYPE = 'M'
	ORDER BY PARENT_ID, DISPLAY_ORDER
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(PM_SELECT_LEFT_MENU)
	SELECT 
		ID, URL, TARGET, TITLE, PERMISSION, PARENT_ID, PROGID,  HINT, HEIGHT, WIDTH, WINDOW_TYPE 
	FROM 
		MST_MAINMENU
	WHERE MENU_TYPE = 'L'
	ORDER BY PARENT_ID, DISPLAY_ORDER
END
------------------------------------------------------------
------------------------------------------------------------
BEGIN(PM_SELECT_ASB)
--	SELECT 1 as CODE, 0 as PCODE, AIMAG_NUM AS NUM, AIMAG_NAME as NAME FROM MST_AIMAG
--	UNION ALL
--	SELECT 2, AIMAG_NUM, SUM_NUM, SUM_NAME FROM MST_SUM
--	UNION ALL
--	SELECT 3, SUM_NUM, BAG_NUM, BAG_NAME FROM MST_BAG
--	UNION ALL
 --   SELECT 4, BAG_NUM, HOROOLOL_NUM, HOROOLOL_NAME FROM MST_HOROOLOL
--	ORDER BY CODE, PCODE, NAME
END
------------------------------------------------------------
------------------------------------------------------------
BEGIN(PM_SELECT_TMN)
	SELECT 1 as CODE, 0 as TYPECODE, 0 as MAKERCODE , TRANSPORT_TYPE_NUM  AS NUM, TRANSPORT_TYPE_NAME  as NAME FROM MST_TRANSPORT_TYPE
	UNION ALL
	SELECT 2, TRANSPORT_TYPE_NUM ,0, M.TRANSPORT_MAKER_NUM , TRANSPORT_MAKER_NAME  FROM MST_TRANSPORT_MAKER M
      LEFT JOIN MST_TRANSPORT_MAKER_TYPES S
      ON S.TRANSPORT_MAKER_NUM = M.TRANSPORT_MAKER_NUM
	UNION ALL
	SELECT 3, TRANSPORT_TYPE_NUM,TRANSPORT_MAKER_NUM  , TRANSPORT_NAME_NUM , TRANSPORT_NAME  FROM MST_TRANSPORT_NAME

	ORDER BY CODE, TYPECODE,MAKERCODE,NAME
END
------------------------------------------------------------
-----------------------------------------------------------
BEGIN(PM_SELECT_AID_YEAR)
	SELECT 
		DISTINCT(YEAR) 
	FROM 
		VW_AID_USER 
	ORDER BY 
		YEAR
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(PM_SEARCH_ORG_TREE_SAS)
	SELECT  ORGANIZATION_PNUM, level, ORGANIZATION_NAME, hi.ORGANIZATION_REG_NUM as ORGANIZATION_REG_NUM
	FROM    (
	        SELECT  FN_CONNECT_BY(ORGANIZATION_REG_NUM) AS ORGANIZATION_REG_NUM, @level AS level
	        FROM    (
	                SELECT  @start_with := 0,
	                        @parent_limit := 100,
	                        @id := @start_with,
	                        @level := 0
	                ) vars, MST_ORGANIZATION
	        WHERE   @id IS NOT NULL
	        ) ho
	JOIN    MST_ORGANIZATION hi
	ON      hi.ORGANIZATION_REG_NUM = ho.ORGANIZATION_REG_NUM
	:WHERE

--WHERE
  --MO.AIMAG_NUM=:cmbAimag AND
  --MO.SUM_NUM=:cmbSum AND
  --MO.BAG_NUM=:cmbKhoroo AND
  --MO.AFFILIATE_NUM=:cmbAffiliate AND
  --MO.STATUS=:cmbStatus AND
  --MO.DELETE_USER is null OR
  --MO.ORGANIZATION_NAME=:txtPoOrgS OR
  --MO.ORGANIZATION_PNUM=:ORGANIZATION_PNUM OR
  --MO.WORKERS_NUMBER=:txtEstablishment
--LIMIT :begin,:end
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(PM_SLCT006_PS_200)
SELECT
      M.NEWS_TITLE
    , M.TEXT
    , M.FILE_URL
    , M.READ_COUNT
    , M.INSERT_DATE
    , M.FILE_ID
FROM
      MST_NEWS M
WHERE
      M.NEWS_NUM = :NEWS_NUM
     AND M.DELETE_USER IS NULL
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(PM_SLCT007_PS_200)
SELECT
      NEWS_TITLE
    , NEWS_NUM
    , SUBSTR(M.TEXT,1,300) AS BRIEF
    , FILE_URL
    , FILE_ID
    , TEXT
    , M.UPDATE_DATE
    , READ_COUNT
    , SUBSTR(M.INSERT_DATE,1,10) AS ADD_DATE
    , M.INSERT_DATE
    , UNAME
FROM
      MST_NEWS M
LEFT JOIN
    ( SELECT
         O.USER_NUM
      ,  CONCAT(SUBSTR(O.LAST_NAME,1,1),'.',O.FIRST_NAME) AS UNAME
      FROM
         MST_USER O
      WHERE
         O.DELETE_USER IS NULL
      GROUP BY O.USER_NUM
    ) MU
    ON
      MU.USER_NUM = M.UPDATE_USER

WHERE
		:WHERE
END
-----------------------------------------------------------
BEGIN(PM_SELECT_FILE)
SELECT
     FILE_ID,
     NAME,
     FILE,
     FILE_TYPE
FROM
      MST_FILES F
WHERE
	FILE_ID=:FILE_ID
END
---------------------------------------------------------
---------------------------------------------------------
BEGIN(PM_SELECT_RULE)
SELECT 
	 FILE_URL
   , RULE
   , UPDATE_DATE
   , FILE_ID
   , ACCESS_PERMISSION
FROM 
    MST_RULE
:WHERE 
END
-----------------------------------------------------------
-----------------------------------------------------------
BEGIN(PM_ADD_READ_COUNT_NEWS)
UPDATE
      MST_NEWS M
SET
      M.READ_COUNT=M.READ_COUNT+1
WHERE
      M.NEWS_NUM= :NEWS_NUM 
END
---------------------------------------------------------
---------------------------------------------------------
BEGIN(PM_SELECT_ORGANIZATION_COMPETENT)
SELECT 
	 C.ORGANIZATION_REG_NUM
   , C.ORGAN_USER_REG_NUM
   , O.ORGANIZATION_NAME
FROM 
    MST_ORGANIZATION_COMPETENT C
LEFT JOIN 
    MST_ORGANIZATION O
ON C.ORGANIZATION_REG_NUM = O.ORGANIZATION_REG_NUM
WHERE C.DELETE_USER IS NULL AND C.ORGAN_USER_REG_NUM = :ORGAN_USER_REG_NUM
END
-----------------------------------------------------------
BEGIN(PM_SELECT_AID_COMPETENT)
SELECT 
	 B.AID_USER_NUM
   , B.COMP_USER_NUM
   , A.AID_NUM
FROM 
    MST_AID_COMPETENT B
INNER JOIN VW_AID_USER A
ON A.REGISTRATION_NUM = B.AID_USER_NUM
WHERE B.DELETE_USER IS NULL AND B.COMP_USER_NUM = :COMP_USER_NUM
END
-----------------------------------------------------------