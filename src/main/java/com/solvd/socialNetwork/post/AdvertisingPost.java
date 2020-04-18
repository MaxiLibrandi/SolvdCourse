package com.solvd.socialNetwork.post;

import com.solvd.socialNetwork.enums.Content;

public class AdvertisingPost extends Post {
	private String url;

	public AdvertisingPost() {
		
	}
	
	public AdvertisingPost(String username, String content, String url) {
		super(Content.ADVERTISING_POST,username,content);
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
}
