package com.ovs.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ovs.model.Voter;


 
public class VoterDAO {
    private String jdbcURL="jdbc:mysql://localhost:3306/mini";
    private String jdbcUsername="root";
    private String jdbcPassword="root";
    private Connection jdbcConnection;
     
    public VoterDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
		String sql="Select * from voter where email=? and pwd=?";
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
    
    public List<Voter> listAllVoters() throws SQLException {
        List<Voter> listVoters = new ArrayList<>();
         
        String sql = "SELECT * FROM voters";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int voter_id = resultSet.getInt("voter_id");
            String v_first_name = resultSet.getString("v_first_name");
            String v_last_name = resultSet.getString("v_last_name");
            String v_email = resultSet.getString("v_email");
            int v_age = resultSet.getInt("v_age");
			String v_pwd=resultSet.getString("v_pwd");
			boolean v_cast=resultSet.getBoolean("v_cast");
			Voter voter = new Voter(voter_id,v_first_name,v_last_name,v_email,v_pwd,v_age,v_cast);
            listVoters.add(voter);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listVoters;
    }
    
}