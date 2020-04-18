package com.solvd.socialNetwork.filter;


import java.time.LocalDate;

import com.solvd.socialNetwork.profile.Profile;

public class FilterProfileDate implements IFilter{
	private LocalDate dateToCompare;
	
	public FilterProfileDate(LocalDate date) {
		dateToCompare = date;
	}
	
	public Boolean satisfy(Profile profile) {
		return profile.getProfileDate().equals(dateToCompare);
	}

}
