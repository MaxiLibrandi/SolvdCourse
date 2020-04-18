package com.solvd.socialNetwork.filter;

import com.solvd.socialNetwork.profile.Profile;

public interface IFilter {
	public abstract Boolean satisfy(Profile profile);
}
