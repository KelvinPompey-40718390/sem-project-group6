package com.napier.seprojectgroup6.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static ConnectionManager instance = null;
    private Connection con = null;
    public boolean connected = false;
    private ConnectionManager() {

    }

    public static synchronized ConnectionManager getInstance() {
        if(instance == null) {
            instance = new ConnectionManager();
        }

        return instance;
    }

    /**
     * Returns the database connection
     * @return Connection
     */
    public Connection getConnection() {
        return this.con;
    }
    public void connect(ConnectionComplete connectionResult)
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "semgroup6");
                System.out.println("Successfully connected");
                this.connected = true;
                connectionResult.onComplete();
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
                this.connected = false;
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }





}
