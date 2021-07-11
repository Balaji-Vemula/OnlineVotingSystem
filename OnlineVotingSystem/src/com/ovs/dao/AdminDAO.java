package com.ovs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ovs.model.Admin;
 
public class AdminDAO {
    private String jdbcURL="jdbc:mysql://localhost:3306/mini";
    private String jdbcUsername="root";
    private String jdbcPassword="Balaji@123";
    private Connection jdbcConnection;
     
    public AdminDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }
     
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException x) {
                throw new SQLException(x);
            }
            jdbcConnection = DriverManager.getConnection(
            		jdbcURL,jdbcUsername,jdbcPassword);
        }
    }
     
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
     
    public boolean check(String email, String pass) throws SQLException {
		String sql="Select * from admin where email=? and pwd=?";
		connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1,email);
		statement.setString(2,pass);
		ResultSet resultSet= statement.executeQuery();
		
		if(resultSet.next())
		{
			return true;
		}
		resultSet.close();
        statement.close();
        disconnect();
		return false;
	}
    
    public Admin getAdmin(String email, String pass) throws SQLException {
    	Admin admin=null;
		String sql="Select * from admin where email=? and pwd=?";
		connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1,email);
		statement.setString(2,pass);
		ResultSet resultSet= statement.executeQuery();
		if(resultSet.next())
		{
			int id=resultSet.getInt("user_id");
            String first_name = resultSet.getString("first_name");
            String last_name = resultSet.getString("last_name");
            admin=new Admin(id,first_name,last_name,email);
            return admin;
		}
		resultSet.close();
        statement.close();
        disconnect();
		return null;
	}
    
}