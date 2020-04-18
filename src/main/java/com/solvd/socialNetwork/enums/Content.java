package com.solvd.socialNetwork.enums;

public enum Content {
	ADVERTISING_POST(true, false, true, true, true),
	PERSONAL_POST(true, true, true, true, true),
	STORY(false, true, true, true, false),
	COMMENT(true, true, false, true, true);
	
	private Boolean canBeLiked;
	private Boolean canBeCommented;
	private Boolean canBeShared;
	private Boolean canBeRemoved;
	private Boolean canBeEdited;
	
	private Content(Boolean canBeLiked, Boolean canBeCommented, Boolean canBeShared, Boolean canBeRemoved, Boolean canBeEdited) {
		this.canBeLiked = canBeLiked;
		this.canBeCommented = canBeCommented;
		this.canBeShared = canBeShared;
		this.canBeRemoved = canBeRemoved;
		this.canBeEdited = canBeEdited;
	}
	
	public Boolean canBeLiked() {
		return canBeLiked;
	}
	
	public Boolean canBeCommented() {
		return canBeCommented;
	}
	
	public Boolean canBeShared() {
		return canBeShared;
	}
	
	public Boolean canBeRemoved() {
		return canBeRemoved;
	}
	
	public Boolean canBeEdited() {
		return canBeEdited;
	}
}
