package com.mahdi.model.common;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;

public class JDBC {
    private static BasicDataSource basicDataSource = new BasicDataSource();
    static  {
        basicDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        basicDataSource.setUsername("mahdi");
        basicDataSource.setPassword("myjava123");
        basicDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        basicDataSource.setMaxTotal(5);
    }
    public static Connection getConnection() throws Exception {
        return basicDataSource.getConnection();
    }
}
