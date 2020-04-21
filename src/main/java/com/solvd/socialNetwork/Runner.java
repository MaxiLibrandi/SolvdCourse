package com.solvd.socialNetwork;

import java.util.ArrayList;
import java.time.*;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.socialNetwork.enums.Profiles;
import com.solvd.socialNetwork.exception.ConversationNotFoundException;
import com.solvd.socialNetwork.exception.GroupConversationCreationException;
import com.solvd.socialNetwork.exception.NotAdvertisingPostFoundException;
import com.solvd.socialNetwork.exception.NotPersonalPostFoundException;
import com.solvd.socialNetwork.exception.PersonalConversationAlreadyExistsException;
import com.solvd.socialNetwork.filter.IGenericFilter;
import com.solvd.socialNetwork.post.*;
import com.solvd.socialNetwork.profile.*;

public class Runner {
	private final static Logger LOGGER = LogManager.getLogger(Runner.class);

	public static void main(String[] args) {
		SocialNetwork facebook = new SocialNetwork("Facebook", "www.facebook.com");
		
		PersonalProfile maximo = new PersonalProfile("maxilibrandi","1234","maximolibrandi@hotmail.com","+5492281585831", "Maximo", "Librandi", LocalDate.of(1997,8,3));
		facebook.addUser(maximo);
		LOGGER.info(maximo.toString());
		
		PersonalProfile gonzalo = new PersonalProfile("gonza_librandi","123456789","gonzalibrandi@gmail.com","+5492281580621", "Gonzalo", "Librandi", LocalDate.of(2002,8,30));
		facebook.addUser(gonzalo);
		LOGGER.info(gonzalo.toString());
		
		BusinessProfile solvd = new BusinessProfile("solvdINC","solvd123","solvd@gmail.com","+542494505050","Solvd INC.", "Software", LocalDate.of(2012,8,17), "contact@solvd.com", "San Martin 1234, Tandil, Argentina"); 
		facebook.addUser(solvd);
		LOGGER.info(solvd.toString());
	
		PersonalPost maximoFirstPersonalPost = new PersonalPost("maxilibrandi","Hello Facebook i'm new using it!");
		
		if (maximo.getProfileType().getCanPostPersonalPosts()) {
			try {
				maximo.addPost(maximoFirstPersonalPost);
			} catch (NotPersonalPostFoundException e) {
				LOGGER.error(e);
			}
		}
		
		if(gonzalo.getProfileType().getCanLikePosts() && maximoFirstPersonalPost.getContentType().getCanBeLiked()) {
			maximoFirstPersonalPost.addLike(gonzalo);
		}else {
			LOGGER.error(gonzalo.getProfileType() + " PROFILE CAN'T LIKE " + maximoFirstPersonalPost.getContentType());
		}
		
		if(gonzalo.getProfileType().getCanSharePosts() && maximoFirstPersonalPost.getContentType().getCanBeShared()) {
			maximoFirstPersonalPost.addShare(gonzalo);
		}else {
			LOGGER.error(gonzalo.getProfileType() + " PROFILE CAN'T SHARE " + maximoFirstPersonalPost.getContentType());
		}
		
		if(solvd.getProfileType().getCanLikePosts() && maximoFirstPersonalPost.getContentType().getCanBeLiked()) {
			maximoFirstPersonalPost.addLike(solvd);
		}else {
			LOGGER.error(solvd.getProfileType() + " PROFILE CAN'T LIKE " + maximoFirstPersonalPost.getContentType());
		}
		
		if(solvd.getProfileType().getCanCommentPosts() && maximoFirstPersonalPost.getContentType().getCanBeCommented()) {
			maximoFirstPersonalPost.addComment(solvd, "Nice!");
		}else {
			LOGGER.error(solvd.getProfileType() + " PROFILE CAN'T COMMENT " + maximoFirstPersonalPost.getContentType());
		}
		
		//	I USED ENUMS UP TO HERE
		
		PersonalPost maximoSecondPersonalPost = new PersonalPost("maxilibrandi","I can't stop using Facebook!");
		try {
			maximo.addPost(maximoSecondPersonalPost);
		} catch (NotPersonalPostFoundException e) {
			LOGGER.error(e);
		}
		maximoSecondPersonalPost.addLike(gonzalo);
		maximoSecondPersonalPost.addLike(solvd);
		
		PersonalPost gonzaloFirstPersonalPost = new PersonalPost("gonza_librandi", "Hi Facebook! I'm new to it");
		try {
			gonzalo.addPost(gonzaloFirstPersonalPost);
		} catch (NotPersonalPostFoundException e) {
			LOGGER.error(e);
		}
		gonzaloFirstPersonalPost.addLike(maximo);
		
		AdvertisingPost solvdFirstAdvertisingPost = new AdvertisingPost("solvdINC", "New testing automation course in Argentina", "www.solvdinc.com/testing/apply");
		try {
			solvd.addPost(solvdFirstAdvertisingPost);
		} catch (NotAdvertisingPostFoundException e) {
			LOGGER.error(e);
		}
		solvdFirstAdvertisingPost.addLike(maximo);
		solvdFirstAdvertisingPost.addLike(gonzalo);
		
		LOGGER.info("THROW NOT PERSONAL POST FOUND EXCEPTION - POST IS ADVERTISINGPOST");
		try {
			maximo.addPost(solvdFirstAdvertisingPost);
		} catch (NotPersonalPostFoundException e) {
			LOGGER.error(e);
		}
		
		LOGGER.info("THROW NOT ADVERTISING POST FOUND EXCEPTION - POST IS PERSONALPOST");
		try {
			solvd.addPost(maximoSecondPersonalPost);
		} catch (NotAdvertisingPostFoundException e) {
			LOGGER.error(e);
		}
		
		LOGGER.info("CREATE CONVERSARTION BETWEEN MAXIMO AND GONZALO");
		try {
			maximo.createPersonalConversation(gonzalo, "Hi bro!");
		} catch (PersonalConversationAlreadyExistsException e) {
			LOGGER.error(e);
		}
		
		LOGGER.info("CREATE CONVERSATION BETWEEN MAXIMO AND SOLVD");
		try {
			maximo.createPersonalConversation(solvd, "I'm interested in Testing automation course!");
		} catch (PersonalConversationAlreadyExistsException e) {
			LOGGER.error(e);
		}
		
		LOGGER.info("THROW PERSONAL CONVERSATION ALREADY EXISTS EXCEPTION - MAXIMO AND SOLVD ALREADY HAVE CONVERSATION");
		try {
			maximo.createPersonalConversation(solvd, "What should I do?");
		} catch (PersonalConversationAlreadyExistsException e) {
			LOGGER.error(e);
		}
		
		LOGGER.info("SEND MESSAGE FROM MAXIMO TO SOLVD");
		try {
			maximo.sendMessageToPersonalConversation(solvd, "What should I do?");
		} catch (ConversationNotFoundException e) {
			LOGGER.error(e);
		}
		
		LOGGER.info("SEND MESSAGE FROM SOLVD TO MAXIMO");
		try {
			solvd.sendMessageToPersonalConversation(maximo, "Send us a mail to course@solvd.com");
		} catch (ConversationNotFoundException e) {
			LOGGER.error(e);
		}
		
		LOGGER.info("THROW GROUP CONVERSATION CREATION EXCEPTION - PARTICIPANTS IS EMPTY");
		List<Profile> participants = new ArrayList<>();
		try {
			solvd.createGroupConversation("Testing Automation Course", "Welcome!", participants);
		} catch (GroupConversationCreationException e) {
			LOGGER.error(e);
		}
		
		LOGGER.info("CREATE GROUP CONVERSATION WITH MAXIMO AS PARTICIPANT");
		participants.add(maximo);
		try {
			solvd.createGroupConversation("Testing Automation Course", "Welcome!", participants);
		} catch (GroupConversationCreationException e) {
			LOGGER.error(e);
		}
		
		LOGGER.info("THROW CONVERSATION NOT FOUND EXCEPTION - SOLVD AND GONZALO HAVEN'T GOT A CONVERSATION");
		try {
			solvd.sendMessageToPersonalConversation(gonzalo, "Are you interested on the course?");
		} catch (ConversationNotFoundException e) {
			LOGGER.error(e);
		}
				
		LOGGER.info("ADD PARTICIPANT TO GROUP CONVERSATION");
		try {
			solvd.addParticipantToGroupConversation("Testing Automation Course", gonzalo);
		} catch (ConversationNotFoundException e) {
			LOGGER.error(e);
		}
		
		LOGGER.info("THROW CONVERSATION NOT FOUND EXCEPTION - THERE'S NOT A GROUP CONVERSATION NAMED 'COURSE'");
		try {
			solvd.sendMessageToGroupConversation("Course", "You were chose for the course!");
		} catch (ConversationNotFoundException e) {
			LOGGER.error(e);
		}
		
		LOGGER.info("SEND MESSAGE TO GROUP CONVERSATION");
		try {
			solvd.sendMessageToGroupConversation("Testing Automation Course", "You were chose for the course!");
		} catch (ConversationNotFoundException e) {
			LOGGER.error(e);
		}

		LOGGER.info("GET RATINGS OF USERS");
		LOGGER.info("Rating maximo:" + maximo.getRating());
		LOGGER.info("Rating gonzalo:" + gonzalo.getRating());
		LOGGER.info("Rating solvd:" + solvd.getRating());
		
		// LAMBDA FUNCTIONS
		
		IGenericFilter<Profile,LocalDate> filterProfileByEqualDate = (profile, objectiveDate) -> {
			return profile.getProfileDate().equals(objectiveDate);
		};
		
		IGenericFilter<Profile,String> filterProfileByEqualName = (profile, objectiveName) -> {
			return profile.getProfileName().equals(objectiveName);
		};
		
		IGenericFilter<Post,LocalDate> filterPostByEqualDate = (post, objectiveDate) -> {
			return post.getPublishDate().equals(objectiveDate);
		};
		
		IGenericFilter<Post,Integer> filterPostByLikesAmount = (post, likesAmount) -> {
			return post.getLikesCount() >= likesAmount;
		};
		
		IGenericFilter<Profile,Profiles> filter = (profile, type) -> {
			return profile.getProfileType().equals(type);
		};
		
		LOGGER.info("Testing lambda functions..");
		LOGGER.info("Gonzalo profile date is 2002/08/30: " + filterProfileByEqualDate.satisfy(gonzalo, LocalDate.of(2002,8,30)));
		LOGGER.info("Maximo profile name is Maxi Librandi: " + filterProfileByEqualName.satisfy(maximo, "Maxi Librandi"));
		LOGGER.info("Maximo first post date is 2020/04/17: " + filterPostByEqualDate.satisfy(maximoFirstPersonalPost, LocalDate.of(2020,4,17)));
		LOGGER.info("Maximo first post has >= 2 likes: " + filterPostByLikesAmount.satisfy(maximoFirstPersonalPost, 2));
		LOGGER.info("Solvd profile is of type PERSONAL: " + filter.satisfy(solvd, Profiles.PERSONAL));		
		
		
		System.out.println("Test: "+ maximo.getLikesReceived());
	}
}