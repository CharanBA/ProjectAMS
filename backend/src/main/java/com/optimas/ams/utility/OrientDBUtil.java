package com.optimas.ams.utility;

import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public class OrientDBUtil {
    private static final String DB_URL = "remote:localhost";  
    private static final String DB_NAME = "ams";  
    private static final String USERNAME = "root";
    private static final String PASSWORD = "charanba";

    private static OrientDB orientDB = new OrientDB(DB_URL, OrientDBConfig.defaultConfig());

    public static ODatabaseSession getSession() {
        return orientDB.open(DB_NAME, USERNAME, PASSWORD);
    }
}
