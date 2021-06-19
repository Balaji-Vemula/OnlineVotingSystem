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
    
    public List<Candidate> listAll() throws SQLException {
        List<Candidate> list = new ArrayList<>();
         
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
			Candidate candi = new Candidate(c_id, c_first_name, c_last_name, c_age, c_votes, party_name);
            list.add(candi);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return list;
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

	public Boolean insertCandidate(Candidate candidate) throws Exception {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO candidates (c_first_name,c_last_name,c_age,party_name) VALUES (?, ?, ?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, candidate.getC_first_name());
        statement.setString(2, candidate.getC_last_name());
        statement.setInt(3,candidate.getC_age());
        statement.setString(4, candidate.getParty_name());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
		
	}

	public boolean incrementVote(Candidate candidate) throws Exception {
		// TODO Auto-generated method stub
		String sql="UPDATE candidates SET c_votes=c_votes+1 where party_name=?";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, candidate.getParty_name());
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
	}

	public Candidate getCandidate(int c_id) throws Exception {
		// TODO Auto-generated method stub
		Candidate candidate=null;
		String sql="Select * from candidates where c_id=?";
		connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1,c_id);
		ResultSet resultSet= statement.executeQuery();
		if(resultSet.next())
		{
			
            String c_first_name = resultSet.getString("c_first_name");
            String c_last_name = resultSet.getString("c_last_name");
            int c_age=resultSet.getInt("c_age");
            int c_votes=resultSet.getInt("c_votes");
            String party_name=resultSet.getString("party_name");
			candidate=new Candidate(c_id, c_first_name, c_last_name,c_age,c_votes,party_name);
            return candidate;
		}
		resultSet.close();
        statement.close();
        disconnect();
		return null;
	}

	public boolean deleteCandidate(Candidate dc) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM candidates where c_id = ?";
        
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, dc.getC_id());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
	}

	public boolean resetPolling() throws Exception {
		// TODO Auto-generated method stub
		String sql="UPDATE candidates SET c_votes=0 where c_votes!=0";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		boolean rowReset = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowReset;
		
	}
    
}