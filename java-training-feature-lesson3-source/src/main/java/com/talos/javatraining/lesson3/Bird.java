package com.talos.javatraining.lesson3;

import static java.util.Arrays.asList;

import java.util.List;

public interface Bird extends Animal {
	default List<String> getCharacteristics()
	{
		return asList("Coat of feathers", "Warm-blooded metabolisms");
	}
	
}
