package com.talos.javatraining.lesson3;

import java.util.Arrays;
import java.util.List;

public interface Reptile extends Animal {
	default List<String> getCharacteristics()
	{
		return Arrays.asList("They ruled the earth for over 150 million years", "They can lay some distance away from bodies of water");
	}
}
