package com.ovs.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ovs.model.Candidate;
import com.ovs.model.Voter;


 
public class CandidateDAO {
    private String jdbcURL="jdbc:mysql://localhost:3306/mini";
    private String jdbcUsername="root";
    private String jdbcPassword="root";
    private Connection jdbcConnection;
     
    public CandidateDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
     
    
    
    public List<Candidate> listAllCadidates() throws SQLException {
        List<Candidate> listCandidates = new ArrayList<>();
         
        String sql = "SELECT * FROM candidates";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int c_id = resultSet.getInt("c_id");
            String c_first_name = resultSet.getString("c_first_name");
            String c_last_name = resultSet.getString("c_last_name");
            int c_age = resultSet.getInt("c_age");
            int c_votes=resultSet.getInt("c_votes");
            String party_name=resultSet.getString("party_name");
			Candidate candidate = new Candidate(c_id, c_first_name, c_last_name, c_age, c_votes, party_name);
            listCandidates.add(candidate);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listCandidates;
    }
    
}