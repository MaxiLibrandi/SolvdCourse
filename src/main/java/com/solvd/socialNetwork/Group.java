package com.solvd.socialNetwork;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.solvd.socialNetwork.post.Post;
import com.solvd.socialNetwork.profile.Profile;

public class Group {
	private static Integer ID_COUNTER = 1;
	private Integer groupId;
	private String groupName;
	private List<Profile> participants; 
	private Profile groupAdmin;
	private LocalDate creationDate;
	private List<Post> posts; 
	
	public Group() {
		
	}
	
	public Group(String groupName, Profile admin) {
		this.groupId = ID_COUNTER++;
		this.groupName = groupName;
		this.participants = new ArrayList<>();
		this.groupAdmin = admin;
		this.creationDate = LocalDate.now();
		this.posts = new ArrayList<>();
	}
	
	public Boolean addPost(Post p) {
		return posts.add(p);
	}
	
	public Boolean removePost(Post p) {
		return posts.remove(p);
	}
	
	public Boolean addParticipant(Profile p) {
		if(participants.contains(p))
			return false;
		return participants.add(p);
	}
	
	public Boolean removeParticipant(Profile p) {
		return participants.remove(p);
	}
	
	public static Integer getIdCounter() {
		return ID_COUNTER;
	}
	
	public Integer getGroupId() {
		return groupId;
	}
	
	public String getGroupName() {
		return groupName;
	}
	
	public List<Profile> getParticipants() {
		return participants;
	}
	
	public List<Post> getPosts() {
		return posts;
	}
	
	public Profile getGroupAdmin() {
		return groupAdmin;
	}
	
	public LocalDate getCreationDate() {
		return creationDate;
	}
	
	public void setGroupAdmin(Profile groupAdmin) {
		this.groupAdmin = groupAdmin;
	}
	
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
