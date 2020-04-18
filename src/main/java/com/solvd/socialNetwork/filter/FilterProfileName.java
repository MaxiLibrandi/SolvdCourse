package com.solvd.socialNetwork.filter;

import com.solvd.socialNetwork.profile.Profile;

public class FilterProfileName implements IFilter{
	private String nameToSearch;
	
	public FilterProfileName(String name) {
		nameToSearch = name;
	}
	
	public Boolean satisfy(Profile profile) {
		return profile.getProfileName().contains(nameToSearch);
	}
}
