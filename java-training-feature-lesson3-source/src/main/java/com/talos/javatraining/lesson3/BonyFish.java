package com.talos.javatraining.lesson3;

import static java.util.Arrays.asList;

import java.util.List;

public interface BonyFish extends Fish {
	default List<String> getCharacteristics()
	{
		return asList("They have skeletons primarily composed of bone tissue");
	}
	
	default List<String> getParent()
	{
		return Fish.super.getCharacteristics();
	}
}
