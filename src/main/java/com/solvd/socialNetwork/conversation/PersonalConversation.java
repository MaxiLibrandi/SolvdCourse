package com.solvd.socialNetwork.conversation;

import com.solvd.socialNetwork.profile.Profile;

public class PersonalConversation extends Conversation{
	private Profile userTo;
	private Profile userFrom;

	public PersonalConversation() {
		
	}
	
	public PersonalConversation(Profile userTo, Profile userFrom) {
		this.userFrom = userFrom;
		this.userTo = userTo;
	}
	
	public Profile getUserTo() {
		return userTo;
	}
	
	public Profile getUserFrom() {
		return userFrom;
	}
}
