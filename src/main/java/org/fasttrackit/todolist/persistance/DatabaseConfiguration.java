package org.fasttrackit.todolist.persistance;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfiguration {

    //We need a MySQL connector JAVA, the most recent version of this connector
    //Google search: mysql-connector-java maven
    //then we go in pom.xml -> dependencies section

    public static Connection getConnection() throws IOException, SQLException, ClassNotFoundException {

        Properties properties = new Properties();

        //below option works only if the folder resources is being set as resources
        InputStream inputStream = DatabaseConfiguration.class.getClassLoader()
                .getResourceAsStream("db.properties");

        //selecting for load the input stream option
        //Select all below and press Ctrl+Alt+T and execute try/finally to surround the code
        try {
            properties.load(inputStream);

            //announcing driver manager about below
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(properties.getProperty("DB_URL"),
                    properties.getProperty("DB_USERNAME"),
                    properties.getProperty("DB_PASSWORD")
            );
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
}
