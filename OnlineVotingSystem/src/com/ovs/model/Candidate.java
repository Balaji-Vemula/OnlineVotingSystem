package com.ovs.model;

public class Candidate {
	protected int csno;
	protected int c_id;
    protected String c_first_name;
    protected String c_last_name;
    protected int c_age;
    protected int c_votes;
    protected String party_name;
	public Candidate() {
	
	}
	
	public Candidate(int c_id) {
		this.c_id = c_id;
	}

	
	
	public Candidate(int csno, int c_id, String c_first_name, String c_last_name, int c_age, int c_votes,
			String party_name) {
		this.csno = csno;
		this.c_id = c_id;
		this.c_first_name = c_first_name;
		this.c_last_name = c_last_name;
		this.c_age = c_age;
		this.c_votes = c_votes;
		this.party_name = party_name;
	}

	public Candidate(String c_first_name, String c_last_name, int c_age, String party_name) {
		
		this.c_first_name = c_first_name;
		this.c_last_name = c_last_name;
		this.c_age = c_age;
		this.party_name = party_name;
	}
	
	public Candidate(String party_name) {
		this.party_name = party_name;
	}

	public Candidate(int c_id, String c_first_name, String c_last_name, int c_age, int c_votes, String party_name) {
		this.c_id = c_id;
		this.c_first_name = c_first_name;
		this.c_last_name = c_last_name;
		this.c_age = c_age;
		this.c_votes = c_votes;
		this.party_name = party_name;
	}
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public String getC_first_name() {
		return c_first_name;
	}
	public void setC_first_name(String c_first_name) {
		this.c_first_name = c_first_name;
	}
	public String getC_last_name() {
		return c_last_name;
	}
	public void setC_last_name(String c_last_name) {
		this.c_last_name = c_last_name;
	}
	public int getC_age() {
		return c_age;
	}
	public void setC_age(int c_age) {
		this.c_age = c_age;
	}
	public int getC_votes() {
		return c_votes;
	}
	public void setC_votes(int c_votes) {
		this.c_votes = c_votes;
	}
	public String getParty_name() {
		return party_name;
	}
	public void setParty_name(String party_name) {
		this.party_name = party_name;
	}

	public int getCsno() {
		return csno;
	}

	public void setCsno(int csno) {
		this.csno = csno;
	}
    
    
}