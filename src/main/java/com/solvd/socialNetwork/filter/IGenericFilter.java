package com.solvd.socialNetwork.filter;

@FunctionalInterface
public interface IGenericFilter<T,U> {
	public Boolean satisfy (T toCheck, U condition);
}
