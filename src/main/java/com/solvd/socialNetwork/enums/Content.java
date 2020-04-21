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
	
	public Boolean getCanBeLiked() {
		return canBeLiked;
	}
	
	public Boolean getCanBeCommented() {
		return canBeCommented;
	}
	
	public Boolean getCanBeShared() {
		return canBeShared;
	}
	
	public Boolean getCanBeRemoved() {
		return canBeRemoved;
	}
	
	public Boolean getCanBeEdited() {
		return canBeEdited;
	}
}
