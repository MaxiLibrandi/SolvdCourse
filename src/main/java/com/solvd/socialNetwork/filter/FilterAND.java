package com.solvd.socialNetwork.filter;

import com.solvd.socialNetwork.profile.Profile;

public class FilterAND implements IFilter{
	private IFilter filterOne;
	private IFilter filterTwo;
	
	public FilterAND(IFilter filterOne, IFilter filterTwo) {
		this.filterOne = filterOne;
		this.filterTwo = filterTwo;
	}
	
	public Boolean satisfy(Profile profile) {
		return filterOne.satisfy(profile) && filterTwo.satisfy(profile);
	}
}
