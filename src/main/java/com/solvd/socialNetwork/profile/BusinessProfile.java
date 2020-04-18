package com.solvd.socialNetwork.profile;


import java.time.LocalDate;

import com.solvd.socialNetwork.enums.Profiles;
import com.solvd.socialNetwork.exception.NotAdvertisingPostFoundException;
import com.solvd.socialNetwork.post.AdvertisingPost;
import com.solvd.socialNetwork.post.Post;

public class BusinessProfile extends Profile {
	public static final Double ADVERTISING_POST_RATE = 0.6;
	public static final Double CONVERSATION_RATE = 0.2; 
	public static final Double LIKES_RATE = 0.2; 
	private String businessName;
	private String category;
	private LocalDate creationDate;
	private String contactMail;
	private String direction;
	
	public BusinessProfile() {
		
	}
	
	public BusinessProfile(String username, String password, String mail, String phoneNumber, String businessName, String category,	LocalDate creationDate, String contactMail, String direction) {
		super(Profiles.BUSINESS,username, password, mail, phoneNumber);
		this.businessName = businessName;
		this.category = category;
		this.creationDate = creationDate;
		this.contactMail = contactMail;
		this.direction = direction;
	}

	@Override
	public Double getRating() {
		return ADVERTISING_POST_RATE * getNumberOfPosts() + CONVERSATION_RATE * getNumberOfConversations() + LIKES_RATE * getLikesReceived();
	}

	@Override
	public Boolean addPost(Post p) throws NotAdvertisingPostFoundException {
		if(!(p instanceof AdvertisingPost))
			throw new NotAdvertisingPostFoundException();
		return addPostToList(p);
	}

	@Override
	public String getProfileName() {
		return businessName;
	}
	
	@Override
	public LocalDate getProfileDate() {
		return creationDate;
	}
	
	@Override
	public String toString() {
		return super.toString() + " | business name: " + this.businessName + " | category: " + this.category + " | contact mail: " + this.contactMail;
	}
	
	public String getBusinessName() {
		return businessName;
	}
	
	public String getCategory() {
		return category;
	}
	
	public LocalDate getCreationDate() {
		return creationDate;
	}
	
	public String getContactMail() {
		return contactMail;
	}
	
	public String getDirection() {
		return direction;
	}
	
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}
	
	public void setContactMail(String contactMail) {
		this.contactMail = contactMail;
	}
	
	public void setDirection(String direction) {
		this.direction = direction;
	}
}
