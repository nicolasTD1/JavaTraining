package com.talos.javatraining.lesson3;

import static java.util.Arrays.asList;

import java.util.List;

public interface JawlessFish extends Fish {
	default List<String> getCharacteristics()
	{
		return asList("They don't have jaw");
	}
	
	default List<String> getParent()
	{
		return Fish.super.getCharacteristics();
	}
}
