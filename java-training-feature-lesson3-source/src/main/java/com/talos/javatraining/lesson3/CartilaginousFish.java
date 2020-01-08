package com.talos.javatraining.lesson3;

import static java.util.Arrays.asList;

import java.util.List;

public interface CartilaginousFish extends Fish {
	default List<String> getCharacteristics()
	{
		return asList("They have skeleton made of cartilage rather than bone");
	}
	default List<String> getParent()
	{
		return Fish.super.getCharacteristics();
	}
}
