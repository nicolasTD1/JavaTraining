package com.talos.javatraining.lesson3;

import static java.util.Arrays.asList;

import java.util.List;

public interface Amphibian extends Animal {
	default List<String> getCharacteristics()
	{
		return asList("They have a semi-aquatic lifestyle",
				"They have to stay near bodies of water, both to maintain the moisture of their skin and to lay their eggs");
	}
}
