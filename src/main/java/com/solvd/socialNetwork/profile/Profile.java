package com.solvd.socialNetwork.profile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.solvd.socialNetwork.conversation.*;
import com.solvd.socialNetwork.enums.Profiles;
import com.solvd.socialNetwork.exception.ConversationNotFoundException;
import com.solvd.socialNetwork.exception.GroupConversationCreationException;
import com.solvd.socialNetwork.exception.PersonalConversationAlreadyExistsException;
import com.solvd.socialNetwork.post.Post;

public abstract class Profile {
	private Profiles profileType;
	private String username;
	private String password; 
	private String mail;
	private String phoneNumber;
	private List<Post> postsDone;
	private List<Conversation> conversationHistory;
	
	public Profile() {
		
	}
	
	public Profile(Profiles p, String username, String password, String mail, String phoneNumber) {
		this.profileType = p;
		this.username = username;
		this.password = password;
		this.mail = mail;
		this.phoneNumber = phoneNumber;
		postsDone = new ArrayList<>();
		conversationHistory = new ArrayList<>();
	}

	public abstract Double getRating();
	public abstract String getProfileName();
	public abstract LocalDate getProfileDate();
	
	public abstract Boolean addPost(Post p) throws Exception;
	
	protected Boolean addPostToList(Post p) {
		return postsDone.add(p);
	}
	
	public Boolean removePost(Post p){
		return postsDone.remove(p);
	}
	
	public Integer getNumberOfPosts() {
		return postsDone.size();
	}
	
	public Integer getLikesReceived() {
		return postsDone.stream().mapToInt(p -> p.getLikesCount()).sum();
	}
	
	private Boolean addConversationToHistory(Conversation c) {
		return conversationHistory.add(c);
	}
	
	public Boolean createPersonalConversation(Profile userTo, String message) throws PersonalConversationAlreadyExistsException{
		//LOOK FOR THE CONVERSATION, IF EXISTS, THROW EXCEPTION
		if (conversationHistory.stream().anyMatch(c -> c instanceof PersonalConversation && (((PersonalConversation)c).getUserTo().equals(userTo) || ((PersonalConversation)c).getUserFrom().equals(userTo)))){
			throw new PersonalConversationAlreadyExistsException();
		}
		//IF CONVERSATION DOESN'T EXIST, CREATE IT AND SEND THE MESSAGE
		PersonalConversation c = new PersonalConversation(userTo, this);
		c.addMessage(message);
		userTo.addConversationToHistory(c);
		return conversationHistory.add(c);
	}
	
	public Boolean createGroupConversation(String name, String message, List<Profile> participants) throws GroupConversationCreationException{
		if (participants.size() == 0) {
			throw new GroupConversationCreationException();
		}
		GroupConversation c = new GroupConversation(name, this);
		c.addMessage(message);
		participants.stream().forEach(p -> {
			c.addParticipant(p);
			p.addConversationToHistory(c);
		});
		return conversationHistory.add(c);
	}
	
	public Boolean sendMessageToPersonalConversation(Profile userTo, String message) throws ConversationNotFoundException {
		if(conversationHistory.stream().filter(c -> c instanceof PersonalConversation && (((PersonalConversation)c).getUserTo().equals(userTo) || ((PersonalConversation)c).getUserFrom().equals(userTo))).map(c -> c.addMessage(message)).count() > 0)
			return true;
		throw new ConversationNotFoundException();
	}
	
	public Boolean sendMessageToGroupConversation(String name, String message) throws ConversationNotFoundException {
		for(Conversation c : conversationHistory) {
			if(c instanceof GroupConversation && ((GroupConversation)c).getName().equals(name)) {
				return c.addMessage(message);
			}
		}
		throw new ConversationNotFoundException();
	}

	public Boolean addParticipantToGroupConversation(String name, Profile p) throws ConversationNotFoundException{
		for(Conversation c : conversationHistory) {
			if(c instanceof GroupConversation && ((GroupConversation)c).getName().equals(name)) {
				if(!((GroupConversation)c).containParticipant(p)) {
					p.addConversationToHistory(c);
					return ((GroupConversation)c).addParticipant(p);
				}
				return false;
			}
		}
		throw new ConversationNotFoundException();
	}
	
	public Integer getNumberOfConversations() {
		return conversationHistory.size();
	}
	
	public Profiles getProfileType() {
		return profileType;
	}
	
	public Boolean login() {
		//TRY TO LOGIN
		return false;
	}
	
	public Boolean logout() {
		//TRY TO LOGOUT
		return false;
	}
	
	@Override
	public int hashCode() {
		int hash = this.username.hashCode();
        hash = hash * 17 + this.mail.hashCode();
        hash = hash * 31 + this.phoneNumber.hashCode();
        return hash;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(!(obj instanceof Profile)) return false;
		Profile p = (Profile) obj;
		if(this.hashCode() != p.hashCode()) return false;
		return(this.username.equals(p.getUsername()) && this.password.equals(p.getPassword()) && this.mail.equals(p.getMail()) && this.phoneNumber.equals(p.getPhoneNumber()));
	}
	
	@Override
	public String toString() {
		return "Profile type: " + profileType + " | Username: " + this.username + " | mail: " + this.mail + " | phone number" + phoneNumber;
	
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getMail() {
		return mail;
	}
		
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
