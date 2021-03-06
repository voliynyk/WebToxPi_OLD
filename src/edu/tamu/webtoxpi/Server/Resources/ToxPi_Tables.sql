CREATE TABLE USERS 
    (ID SERIAL PRIMARY KEY,
     LOGIN           TEXT    	UNIQUE NOT NULL,
     PASSWORD        TEXT    	NOT NULL,
     FIRSTNAME       TEXT    	NOT NULL,
     LASTNAME        TEXT    	NOT NULL,
     EMAIL           TEXT    	NOT NULL,
     REGISTEREDDT    TIMESTAMP	NOT NULL DEFAULT CURRENT_DATE,
     LASTVISITDT     TIMESTAMP	NOT NULL DEFAULT CURRENT_DATE);
INSERT INTO USERS (LOGIN, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, REGISTEREDDT, LASTVISITDT) VALUES ('SYSTEM', '', 'SYSTEM', 'SYSTEM', 'VOLIYNYK@CVM.TAMU.EDU', CURRENT_DATE, CURRENT_DATE);


CREATE TABLE IMPORTINFO 
    (ID SERIAL PRIMARY KEY,
     USER_ID        INTEGER    	NOT NULL REFERENCES USERS,
     TRANSACTIONDATE TIMESTAMP  NOT NULL DEFAULT CURRENT_DATE,
     NOTES          TEXT    	NOT NULL,
     FILENAME		TEXT        NOT NULL);

     
CREATE TABLE SEARCHTEMPLATES 
    (ID SERIAL PRIMARY KEY,
     USER_ID        INTEGER    	NOT NULL REFERENCES USERS,
     CODE           TEXT    	UNIQUE NOT NULL,
     OBJECTXML      XML         NULL,
     UPDATEDBY      INTEGER     NOT NULL REFERENCES USERS,
     UPDATEDDT      TIMESTAMP	NOT NULL DEFAULT CURRENT_DATE);


CREATE TABLE VIEWTEMPLATES 
    (ID SERIAL PRIMARY KEY,
     USER_ID        INTEGER     NOT NULL REFERENCES USERS,
     CODE           TEXT    	UNIQUE NOT NULL,
    OBJECTXML       XML         NULL,
     UPDATEDBY      INTEGER     NOT NULL REFERENCES USERS,
     UPDATEDDT      TIMESTAMP	NOT NULL DEFAULT CURRENT_DATE);


CREATE TABLE NOTES 
    (ID SERIAL PRIMARY KEY,
     CODE           TEXT        NOT NULL,
     NOTE           TEXT        NULL,
     OWNER_ID       INTEGER     NOT NULL,
     OWNERTYPE      CHAR[1]     NOT NULL,
     VISIBLE        BOOLEAN     NOT NULL DEFAULT TRUE,
     UPDATEDBY      INTEGER     NOT NULL REFERENCES USERS,
     UPDATEDDT      TIMESTAMP	NOT NULL DEFAULT CURRENT_DATE);


CREATE TABLE SOURCES
    (ID SERIAL PRIMARY KEY,
     CODE           TEXT    	UNIQUE NOT NULL,
     NAME           TEXT    	NULL,
     UPDATEDBY      INTEGER     NOT NULL REFERENCES USERS,
     UPDATEDDT      TIMESTAMP	NOT NULL DEFAULT CURRENT_DATE);


CREATE TABLE CASREGISTRYNUMBERS
    (ID SERIAL PRIMARY KEY,
     CODE           TEXT    	UNIQUE NOT NULL,
     NAME           TEXT    	NULL,
     UPDATEDBY      INTEGER     NOT NULL REFERENCES USERS,
     UPDATEDDT      TIMESTAMP	NOT NULL DEFAULT CURRENT_DATE);


CREATE TABLE CHEMICALS
    (ID SERIAL PRIMARY KEY,
     CODE           TEXT    	UNIQUE NOT NULL,
     NAME           TEXT    	NULL,
     UPDATEDBY      INTEGER     NOT NULL REFERENCES USERS,
     UPDATEDDT      TIMESTAMP	NOT NULL DEFAULT CURRENT_DATE);


CREATE TABLE WEIGHTS
    (ID SERIAL PRIMARY KEY,
     CODE           TEXT    	NOT NULL,
     NAME           TEXT    	NULL,
     WEIGHT         INTEGER    	NOT NULL,
     UPDATEDBY      INTEGER     NOT NULL REFERENCES USERS,
     UPDATEDDT      TIMESTAMP	NOT NULL DEFAULT CURRENT_DATE);


CREATE TABLE GROUPS
    (ID SERIAL PRIMARY KEY,
     CODE           TEXT    	NOT NULL,
     NAME           TEXT    	NULL,
     WEIGHT_ID      INTEGER     NOT NULL REFERENCES WEIGHTS,
     COLOR          INTEGER     NULL,
     UPDATEDBY      INTEGER     NOT NULL REFERENCES USERS,
     UPDATEDDT      TIMESTAMP	NOT NULL DEFAULT CURRENT_DATE);


CREATE TABLE TYPES
    (ID SERIAL PRIMARY KEY,
     CODE           TEXT    	NOT NULL,
     NAME           TEXT    	NULL,
     GROUP_ID       INTEGER     NOT NULL REFERENCES GROUPS,
     UPDATEDBY      INTEGER     NOT NULL REFERENCES USERS,
     UPDATEDDT      TIMESTAMP	NOT NULL DEFAULT CURRENT_DATE);


CREATE TABLE COMPONENTSOURCES 
    (ID SERIAL PRIMARY KEY,
     CODE           TEXT    	NOT NULL,
     NAME           TEXT    	NULL,
     UPDATEDBY      INTEGER     NOT NULL REFERENCES USERS,
     UPDATEDDT      TIMESTAMP	NOT NULL DEFAULT CURRENT_DATE);

CREATE TABLE UNITS 
    (ID SERIAL PRIMARY KEY,
     CODE           TEXT    	NOT NULL,
     NAME           TEXT    	NULL,
     RELATEDUNIT	INTEGER     NULL REFERENCES UNITS,
     VOLUME			DECIMAL     NULL,
     UPDATEDBY      INTEGER     NOT NULL REFERENCES USERS,
     UPDATEDDT      TIMESTAMP	NOT NULL DEFAULT CURRENT_DATE);
INSERT INTO UNITS (CODE, NAME, RELATEDUNIT, VOLUME, UPDATEDBY, UPDATEDDT) VALUES ('DEFAULT', '', NULL, NULL, 1, CURRENT_DATE);

CREATE TABLE COMPONENTS
    (ID SERIAL PRIMARY KEY,
     CODE           TEXT    	NOT NULL,
     NAME           TEXT    	NULL,
     TYPE_ID        INTEGER     NOT NULL REFERENCES TYPES,
     COMPSOURCE_ID  INTEGER     NULL REFERENCES COMPONENTSOURCES,
     UNIT			INTEGER     NOT NULL REFERENCES UNITS,
     UPDATEDBY      INTEGER     NOT NULL REFERENCES USERS,
     UPDATEDDT      TIMESTAMP	NOT NULL DEFAULT CURRENT_DATE);


CREATE TABLE ORDERS 
    (ID SERIAL PRIMARY KEY,
     SOURCE_ID      INTEGER		NOT NULL REFERENCES SOURCES,
     CASR_ID        INTEGER     NOT NULL REFERENCES CASREGISTRYNUMBERS,
     CHEMICAL_ID    INTEGER     NOT NULL REFERENCES CHEMICALS,
     IMPORT_ID		INTEGER		NULL REFERENCES IMPORTINFO,
     ORDNUMBER      INTEGER     NOT NULL,
     UPDATEDBY      INTEGER     NOT NULL REFERENCES USERS,
     UPDATEDDT      TIMESTAMP	NOT NULL DEFAULT CURRENT_DATE,
     UNIQUE (SOURCE_ID, CASR_ID, CHEMICAL_ID));

CREATE TABLE RESULTS 
    (ID SERIAL PRIMARY KEY,
     ORDER_ID       INTEGER     NOT NULL REFERENCES ORDERS,
     COMPONENT_ID   INTEGER     NOT NULL REFERENCES COMPONENTS,
     NUMRESULT      DECIMAL     NULL,
     STRRESULT      TEXT     	NULL,
     BOOLRESULT     BOOLEAN    	NULL,
     UPDATEDBY      INTEGER     NOT NULL REFERENCES USERS,
     UPDATEDDT      TIMESTAMP	NOT NULL DEFAULT CURRENT_DATE);
     
CREATE TABLE RESULTSHISTORY 
    (ID SERIAL PRIMARY KEY,
     RESULTS        INTEGER     NOT NULL REFERENCES RESULTS,
     OLDNUMRESULT   DECIMAL     NULL,
     OLDSTRRESULT   TEXT     	NULL,
     OLDBOOLRESULT  BOOLEAN    	NULL,
     NEWNUMRESULT   DECIMAL     NULL,
     NEWSTRRESULT   TEXT     	NULL,
     NEWBOOLRESULT  BOOLEAN    	NULL,
     UPDATEDBY      INTEGER     NOT NULL REFERENCES USERS,
     UPDATEDDT      TIMESTAMP	NOT NULL DEFAULT CURRENT_DATE);
     
CREATE OR REPLACE FUNCTION RESULT_CHANGES_HISTORY() RETURNS TRIGGER AS
$BODY$
BEGIN
    IF  TG_OP = 'INSERT' THEN
        INSERT INTO RESULTSHISTORY(RESULTS, OLDNUMRESULT, OLDSTRRESULT, OLDBOOLRESULT, NEWNUMRESULT, NEWSTRRESULT, NEWBOOLRESULT, UPDATEDBY)
        values (NEW.ID, null, null, null, NEW.NUMRESULT, NEW.STRRESULT, NEW.BOOLRESULT, 1);
        RETURN NEW;
    ELSIF TG_OP = 'UPDATE' THEN
        INSERT INTO RESULTSHISTORY(RESULTS, OLDNUMRESULT, OLDSTRRESULT, OLDBOOLRESULT, NEWNUMRESULT, NEWSTRRESULT, NEWBOOLRESULT, UPDATEDBY)
        values (NEW.ID, OLD.NUMRESULT, OLD.STRRESULT, OLD.BOOLRESULT, NEW.NUMRESULT, NEW.STRRESULT, NEW.BOOLRESULT, 1);
        RETURN NEW;
    ELSIF TG_OP = 'DELETE' THEN
        RETURN OLD;
    END IF;
END;
$BODY$ LANGUAGE plpgsql;

CREATE TRIGGER T_RESULTS AFTER INSERT OR UPDATE OR DELETE ON RESULTS FOR EACH ROW EXECUTE PROCEDURE RESULT_CHANGES_HISTORY();