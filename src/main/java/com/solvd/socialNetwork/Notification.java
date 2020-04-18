package com.solvd.socialNetwork;

import com.solvd.socialNetwork.profile.Profile;

public class Notification {
	private Profile userTo;
	private Profile userFrom;
	private String message;
	private String flag;
	
	public Notification() {
		
	}
	
	public Notification (Profile userTo, Profile userFrom, String message) {
		this.userTo = userTo;
		this.userFrom = userFrom;
		this.message = message;
		this.flag = "Not read";
	}
	
	public void markAasRead() {
		this.flag = "Read"; 
	}
	
	public void markAsImportant() {
		this.flag = "Important";
	}
	
	public void markAsNotRead() {
		this.flag = "Not read";
	}
	
	public Profile getUserTo() {
		return userTo;
	}
	
	public Profile getUserFrom() {
		return userFrom;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getFlag() {
		return flag;
	}
}
