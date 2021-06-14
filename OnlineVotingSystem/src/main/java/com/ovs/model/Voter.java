package com.ovs.model;

public class Voter {
	protected int voter_id;
    protected String v_first_name;
    protected String v_last_name;
    protected String v_email;
    protected String v_pwd;
    protected int v_age;
    protected boolean v_cast;
    
	public Voter() {
		
	}

	public Voter(int voter_id, String v_first_name, String v_last_name, String v_email, String v_pwd, int v_age,
			Boolean v_cast) {
		super();
		this.voter_id = voter_id;
		this.v_first_name = v_first_name;
		this.v_last_name = v_last_name;
		this.v_email = v_email;
		this.v_pwd = v_pwd;
		this.v_age = v_age;
		this.v_cast = v_cast;
	}

	public int getVoter_id() {
		return voter_id;
	}

	public void setVoter_id(int voter_id) {
		this.voter_id = voter_id;
	}

	public String getV_first_name() {
		return v_first_name;
	}

	public void setV_first_name(String v_first_name) {
		this.v_first_name = v_first_name;
	}

	public String getV_last_name() {
		return v_last_name;
	}

	public void setV_last_name(String v_last_name) {
		this.v_last_name = v_last_name;
	}

	public String getV_email() {
		return v_email;
	}

	public void setV_email(String v_email) {
		this.v_email = v_email;
	}

	public String getV_pwd() {
		return v_pwd;
	}

	public void setV_pwd(String v_pwd) {
		this.v_pwd = v_pwd;
	}

	public int getV_age() {
		return v_age;
	}

	public void setV_age(int v_age) {
		this.v_age = v_age;
	}

	public boolean getV_cast() {
		return v_cast;
	}

	public void setV_cast(boolean v_cast) {
		this.v_cast = v_cast;
	}

	
    
}
