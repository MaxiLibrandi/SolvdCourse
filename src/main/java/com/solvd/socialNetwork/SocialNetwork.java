package com.solvd.socialNetwork;

import java.util.ArrayList;
import java.util.List;
import com.solvd.socialNetwork.profile.Profile;

public class SocialNetwork {
	private String companyName;
	private String hostname;
	private List<Profile> users;
	private List<Group> groups;
	
	public SocialNetwork() {
		
	}
	
	public SocialNetwork(String companyName, String hostname) {
		this.companyName = companyName;
		this.hostname = hostname;
		users = new ArrayList<>();
		groups = new ArrayList<>();
	}
	
	public Boolean addUser(Profile p) {
		return users.add(p);
	}
	
	public Boolean removeUser(Profile p) {
		return users.remove(p);
	}
	
	public Boolean addGroup(Group g) {
		return groups.add(g);
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public String getHostname() {
		return hostname;
	}
	
	public List<Profile> getUsers() {
		return users;
	}
	
	public List<Group> getGroups() {
		return groups;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
}
