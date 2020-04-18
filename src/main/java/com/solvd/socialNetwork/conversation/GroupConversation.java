package com.solvd.socialNetwork.conversation;

import java.util.ArrayList;
import java.util.List;

import com.solvd.socialNetwork.profile.Profile;

public class GroupConversation extends Conversation{
	private String name;
	private Profile admin;
	private List<Profile> participants;

	public GroupConversation() {
		
	}
	
	public GroupConversation(String name, Profile admin){
		super();
		this.name = name;
		this.admin = admin;
		this.participants = new ArrayList<>();
		this.participants.add(admin);
	}
	
	public Boolean addParticipant(Profile p) {
		return participants.add(p);
	}
	
	public Boolean removeParticipant(Profile p) {
		return participants.remove(p);
	}
	
	public Boolean containParticipant(Profile p) {
		return participants.contains(p);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Profile getAdmin() {
		return admin;
	}
	
	public List<Profile> getParticipants() {
		return participants;
	}
}
