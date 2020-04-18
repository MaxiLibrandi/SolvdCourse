package com.solvd.socialNetwork.post;

import java.util.HashMap;

import com.solvd.socialNetwork.enums.Content;
import com.solvd.socialNetwork.profile.Profile;

public class PersonalPost extends Post{
	private HashMap<Profile, String> comments;
	
	public PersonalPost() {
		
	}

	public PersonalPost(String username, String content) {
		super(Content.PERSONAL_POST,username,content);
		comments = new HashMap<Profile, String>();
	}
	
	public void addComment(Profile profile, String comment) {
		if(comments.containsKey(profile)) {
			comments.put(profile, comments.get(profile) + " | " + comment);
		}else {
			comments.put(profile, comment);
		}
	}
	
	public int getCommentsAmount() {
		return comments.keySet().size();
	}
	
	public String getComments(){
		return comments.toString();
	}
}
