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
import com.ovs.model.Candidate;
import com.ovs.model.Voter;


 
public class VoterDAO {
    private String jdbcURL="jdbc:mysql://localhost:3306/mini";
    private String jdbcUsername="root";
    private String jdbcPassword="Balaji@123";
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
         int vsno=0;
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
        	vsno=vsno+1;
            int voter_id = resultSet.getInt("voter_id");
            String v_first_name = resultSet.getString("v_first_name");
            String v_last_name = resultSet.getString("v_last_name");
            String v_email = resultSet.getString("v_email");
            int v_age = resultSet.getInt("v_age");
			String v_pwd=resultSet.getString("v_pwd");
			boolean v_cast=resultSet.getBoolean("v_cast");
			Voter voter = new Voter(vsno,voter_id,v_first_name,v_last_name,v_email,v_pwd,v_age,v_cast);
            listVoters.add(voter);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listVoters;
    }

	public boolean insertVoter(Voter voter) throws Exception {
		String sql = "INSERT INTO voters (v_first_name,v_last_name,v_email,v_age,v_pwd) VALUES (?, ?, ?, ?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, voter.getV_first_name());
        statement.setString(2, voter.getV_last_name());
        statement.setString(3, voter.getV_email());
        statement.setInt(4, voter.getV_age());
        statement.setString(5, voter.getV_pwd());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
		
	}

	public Voter getVoter(int voter_id) throws SQLException {
		// TODO Auto-generated method stub
		Voter voter=null;
		String sql="Select * from voters where voter_id=?";
		connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1,voter_id);
		ResultSet resultSet= statement.executeQuery();
		if(resultSet.next())
		{
			
            String v_first_name = resultSet.getString("v_first_name");
            String v_last_name = resultSet.getString("v_last_name");
            int v_age=resultSet.getInt("v_age");
            boolean v_cast=resultSet.getBoolean("v_cast");
            String v_email = resultSet.getString("v_email");
			String v_pwd = resultSet.getString("v_pwd");
			voter=new Voter(voter_id, v_first_name, v_last_name, v_email,v_age, v_pwd,v_cast);
            return voter;
		}
		resultSet.close();
        statement.close();
        disconnect();
		return null;
	}
	
	public Voter getVoter(String v_email, String v_pwd) throws Exception {
		// TODO Auto-generated method stub
		Voter voter=null;
		String sql="Select * from voters where v_email=? and v_pwd=?";
		connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1,v_email);
		statement.setString(2,v_pwd);
		ResultSet resultSet= statement.executeQuery();
		if(resultSet.next())
		{
			int voter_id=resultSet.getInt("voter_id");
            String v_first_name = resultSet.getString("v_first_name");
            String v_last_name = resultSet.getString("v_last_name");
            int v_age=resultSet.getInt("v_age");
            boolean v_cast=resultSet.getBoolean("v_cast");
            voter=new Voter(voter_id, v_first_name, v_last_name, v_email,v_age, v_pwd,v_cast);
            return voter;
		}
		resultSet.close();
        statement.close();
        disconnect();
		return null;
	}

	public void changeVCast(Voter voter) throws SQLException {
		// TODO Auto-generated method stub
		String sql="UPDATE voters SET v_cast=false where v_email=?";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, voter.getV_email());
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
	}

	public boolean deleteVoter(Voter voter) throws Exception {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM voters where voter_id = ?";
        
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, voter.getVoter_id());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
	}

	public boolean resetPolling() throws Exception {
		// TODO Auto-generated method stub
		String sql="UPDATE voters SET v_cast=true where v_cast=false";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		boolean rowReset = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowReset;
	}

	public List<Voter> listAllVoted() throws Exception {
		// TODO Auto-generated method stub
		List<Voter> listVoted = new ArrayList<>();
        
        String sql = "SELECT * FROM voters where v_cast=false";
         
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
			Voter voter = new Voter(voter_id,v_first_name,v_last_name,v_email,v_age,v_pwd,v_cast);
            listVoted.add(voter);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listVoted;
	}

	
    
}