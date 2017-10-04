package net.djkaytech.demo;

import org.apache.derby.jdbc.ClientDataSource;
import org.apache.derby.jdbc.EmbeddedDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloJavaDb {
    //Connection conn;

    public static void main(String[] args) throws SQLException {


        //app.connectionToDerby();
        //app.normalDbUsage();
        testConnectionWithDataSource();

    }

    private static void testConnectionWithDataSource() throws SQLException {
        HelloJavaDb app = new HelloJavaDb();
        DataSource dataSource = app.getEmbeddedDS("c:\\MyDB\\demo");
        Connection connection = dataSource.getConnection();
        app.normalDbUsage(connection);

    }

    public void connectionToDerby() throws SQLException {
        // -------------------------------------------
        // URL format is
        // jdbc:derby:<local directory to save data>
        // -------------------------------------------
        String dbUrl = "jdbc:derby:c:\\MyDB\\demo;create=true";
        //conn = DriverManager.getConnection(dbUrl);
    }

    public void normalDbUsage(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();

        // drop table
        //stmt.executeUpdate("Drop Table users");

        // create table
        //stmt.executeUpdate("Create table users (id int primary key, name varchar(30))");

        // insert 2 rows
        //stmt.executeUpdate("insert into users values (1,'tom')");
        //stmt.executeUpdate("insert into users values (2,'peter')");
        //stmt.executeUpdate("insert into users values (3,'wladimir')");

        // query
        ResultSet rs = stmt.executeQuery("SELECT * FROM users");

        // print out query result
        while (rs.next()) {
            System.out.printf("%d\t%s\n", rs.getInt("id"), rs.getString("name"));
        }
    }

    public DataSource getEmbeddedDS(String databaseName) {

        EmbeddedDataSource embeddedDataSource = new EmbeddedDataSource();
        embeddedDataSource.setDatabaseName(databaseName);
        embeddedDataSource.setCreateDatabase(databaseName);
        return embeddedDataSource;

    }

    public DataSource getDS(String database, String user, String
            password) throws SQLException
    {
        ClientDataSource ds = new org.apache.derby.jdbc.ClientDataSource();

        // DatabaseName can include Derby URL Attributes
        ds.setDatabaseName(database);

        if (user != null)
            ds.setUser(user);
        if (password != null)
            ds.setPassword(password);


        // The host on which Network Server is running

        ds.setServerName("localhost");

        // port on which Network Server is listening
        ds.setPortNumber(1527);

        return ds;
    }
}