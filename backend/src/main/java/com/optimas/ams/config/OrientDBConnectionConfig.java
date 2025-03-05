package com.optimas.ams.config;

import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import com.orientechnologies.orient.core.record.OVertex;

import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrientDBConnectionConfig {

    @Bean
    public ODatabaseSession databaseSession() {
        System.out.println("Starting OrientDB connection...");

        OrientDB orient = new OrientDB("remote:localhost", OrientDBConfig.defaultConfig());
        ODatabaseSession db = orient.open("ams", "root", "charanba");

        System.out.println("OrientDB setup completed!");
        return db;
    }
}
