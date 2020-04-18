package com.solvd.socialNetwork.profile;

import java.time.LocalDate;

import com.solvd.socialNetwork.enums.Profiles;
import com.solvd.socialNetwork.exception.NotPersonalPostFoundException;
import com.solvd.socialNetwork.post.PersonalPost;
import com.solvd.socialNetwork.post.Post;

public class PersonalProfile extends Profile{
	public static final Double PERSONAL_POST_RATE = 0.5;
	public static final Double CONVERSATION_RATE = 0.3; 
	public static final Double LIKES_RATE = 0.2;  
	private String name;
	private String lastName;
	private LocalDate birthday;
	
	public PersonalProfile() {
		
	}
	
	public PersonalProfile(String username, String password, String mail, String phoneNumber, String name, String lastName, LocalDate birthday) {
		super(Profiles.PERSONAL,username, password, mail, phoneNumber);
		this.name = name;
		this.lastName = lastName;
		this.birthday = birthday;
	}

	@Override
	public Double getRating() {
		return PERSONAL_POST_RATE * getNumberOfPosts() + CONVERSATION_RATE * getNumberOfConversations() + LIKES_RATE * getLikesReceived();
	}

	@Override
	public Boolean addPost(Post p) throws NotPersonalPostFoundException {
		if(!(p instanceof PersonalPost))
			throw new NotPersonalPostFoundException();
		return addPostToList(p);
	}
	
	@Override
	public String getProfileName() {
		return name + " " + lastName;
	}
	
	@Override
	public LocalDate getProfileDate() {
		return birthday;
	}
	
	@Override
	public String toString() {
		return super.toString() + " | name: " + this.name + " | last name: " + this.lastName;
	}
	
	public String getName() {
		return name;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public LocalDate getBirthday() {
		return birthday;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
}
