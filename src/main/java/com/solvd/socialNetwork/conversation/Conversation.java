package com.solvd.socialNetwork.conversation;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Conversation {
	private static Integer ID_COUNTER = 1;
	private Integer chatId;
	private LocalDate creationDate;
	private List<String> messages;
	private List<File> files;
	
	public Conversation() {
		chatId = ID_COUNTER++;
		creationDate = LocalDate.now();
		messages = new ArrayList<>();
		files = new ArrayList<>();
	}
	
	@Override
	public int hashCode() {
		int hash = this.chatId;
        hash = hash * 15 + this.creationDate.hashCode();
        return hash;
	}
	
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(!(obj instanceof Conversation)) return false;
		Conversation c = (Conversation) obj;
		if(this.hashCode() != c.hashCode()) return false;
		return(this.chatId == c.getChatId());
	}
	
	public Boolean addMessage(String message) {
		return messages.add(message);
	}
	
	public Boolean sendFile(File f) {
		return files.add(f);
	}
	
	public Boolean removeMessage(String message) {
		return messages.remove(message);
	}
	
	public Boolean removeFile(File f) {
		return files.remove(f);
	}
	
	public Integer getIdCounter() {
		return ID_COUNTER;
	}
	
	public Integer getChatId() {
		return chatId;
	}
	
	public LocalDate getCreationDate() {
		return creationDate;
	}
	
	public List<String> getMessages() {
		return messages;
	}
	
	public List<File> getFiles() {
		return files;
	}
}
