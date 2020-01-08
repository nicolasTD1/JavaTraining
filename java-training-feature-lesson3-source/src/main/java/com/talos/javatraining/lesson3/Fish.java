package com.talos.javatraining.lesson3;

import static java.util.Arrays.asList;

import java.util.List;

public interface Fish extends Animal{
	default List<String> getCharacteristics()
	{
		return asList("They breathe using gills", "They have dominated the world's oceans, lakes and rivers");
	}
}
