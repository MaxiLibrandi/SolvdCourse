package com.solvd.socialNetwork.enums;

public enum Profiles {
	BUSINESS(false, true, true, false, true),
	PERSONAL(true, false, true, true, true);
	
	private Boolean canPostPersonalPosts;
	private Boolean canPostAdvertisingPosts;
	private Boolean canLikePosts;
	private Boolean canCommentPosts;
	private Boolean canSharePosts;
	
	private Profiles(Boolean canPostPersonalPost, Boolean canPostAdvertisingPosts, Boolean canLikePosts, Boolean canCommentPosts, Boolean canSharePosts) {
		this.canPostPersonalPosts = canPostPersonalPost;
		this.canPostAdvertisingPosts = canPostAdvertisingPosts;
		this.canLikePosts = canLikePosts;
		this.canCommentPosts = canCommentPosts;
		this.canSharePosts = canSharePosts;
	}
	
	public Boolean getCanPostPersonalPosts() {
		return canPostPersonalPosts;
	}
	
	public Boolean getCanPostAdvertisingPosts() {
		return canPostAdvertisingPosts;
	}
	
	public Boolean getCanLikePosts() {
		return canLikePosts;
	}
	
	public Boolean getCanCommentPosts() {
		return canCommentPosts;
	}
	
	public Boolean getCanSharePosts() {
		return canSharePosts;
	}
}
