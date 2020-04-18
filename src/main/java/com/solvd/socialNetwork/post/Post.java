package com.solvd.socialNetwork.post;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.solvd.socialNetwork.enums.Content;
import com.solvd.socialNetwork.profile.Profile;

public abstract class Post {
	private Content contentType;
	private static Integer ID_COUNTER =1;
	private Integer postId;
	private String username;
	private LocalDate publishDate;
	private String content;
	private List<Profile> likes;
	private List<Profile> shares;
	
	public Post() {
		
	}
	
	public Post(Content contentType, String username, String content) {
		this.contentType = contentType;
		this.postId = ID_COUNTER++;
		this.username = username;
		this.publishDate = LocalDate.now();
		this.content = content;
		this.likes = new ArrayList<>();
		this.shares = new ArrayList<>();
	}
	
	public Content getContentType() {
		return contentType;
	}
	
	@Override
	public int hashCode() {
		int hash = this.postId;
        hash = hash * 17 + this.username.hashCode();
        hash = hash * 13 + this.publishDate.hashCode();
        return hash;
	}
	
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(!(obj instanceof Post)) return false;
		Post p = (Post) obj;
		if(this.hashCode() != p.hashCode()) return false;
		return(this.postId == p.getPostId());
	}
	
	public Boolean addLike(Profile p) {
		return likes.add(p);
	}
	
	public Boolean removeLike(Profile p) {
		return likes.remove(p);
	}
	
	public List<Profile> getLikes() {
		return likes;
	}
	
	public Integer getLikesCount() {
		return likes.size();
	}
	
	public Boolean addShare(Profile p) {
		return shares.add(p);
	}
	
	public Boolean removeShare(Profile p) {
		return shares.remove(p);
	}
	
	public List<Profile> getShares() {
		return shares;
	}
	
	public Integer getSharesCount() {
		return shares.size();
	}
	
	public Integer getIdCounter() {
		return ID_COUNTER;
	}
	
	public Integer getPostId() {
		return postId;
	}
	
	public LocalDate getPublishDate() {
		return publishDate;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getContent() {
		return content;
	} 
	
	public void editContent(String newContent) {
		this.content = newContent;
	}
}
