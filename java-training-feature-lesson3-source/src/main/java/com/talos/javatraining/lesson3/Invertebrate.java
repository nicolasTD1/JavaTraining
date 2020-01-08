package com.talos.javatraining.lesson3;

import static java.util.Arrays.asList;

import java.util.List;

public interface Invertebrate extends Animal {
	default List<String> getCharacteristics()
	{
		return asList("Lack of backbones and internal skeletons", "Simple anatomy");
	}
}
