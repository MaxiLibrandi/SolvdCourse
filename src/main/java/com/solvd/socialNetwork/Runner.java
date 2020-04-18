package com.solvd.socialNetwork;

import java.util.ArrayList;
import java.time.*;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.socialNetwork.exception.ConversationNotFoundException;
import com.solvd.socialNetwork.exception.GroupConversationCreationException;
import com.solvd.socialNetwork.exception.NotAdvertisingPostFoundException;
import com.solvd.socialNetwork.exception.NotPersonalPostFoundException;
import com.solvd.socialNetwork.exception.PersonalConversationAlreadyExistsException;
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
		
		if (maximo.getProfileType().canPostPersonalPosts()) {
			try {
				maximo.addPost(maximoFirstPersonalPost);
			} catch (NotPersonalPostFoundException e) {
				LOGGER.error(e);
			}
		}
		
		if(gonzalo.getProfileType().canLikePosts() && maximoFirstPersonalPost.getContentType().canBeLiked()) {
			maximoFirstPersonalPost.addLike(gonzalo);
		}else {
			LOGGER.error(gonzalo.getProfileType() + " PROFILE CAN'T LIKE " + maximoFirstPersonalPost.getContentType());
		}
		
		if(gonzalo.getProfileType().canSharePosts() && maximoFirstPersonalPost.getContentType().canBeShared()) {
			maximoFirstPersonalPost.addShare(gonzalo);
		}else {
			LOGGER.error(gonzalo.getProfileType() + " PROFILE CAN'T SHARE " + maximoFirstPersonalPost.getContentType());
		}
		
		if(solvd.getProfileType().canLikePosts() && maximoFirstPersonalPost.getContentType().canBeLiked()) {
			maximoFirstPersonalPost.addLike(solvd);
		}else {
			LOGGER.error(solvd.getProfileType() + " PROFILE CAN'T LIKE " + maximoFirstPersonalPost.getContentType());
		}
		
		if(solvd.getProfileType().canCommentPosts() && maximoFirstPersonalPost.getContentType().canBeCommented()) {
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
	}
}