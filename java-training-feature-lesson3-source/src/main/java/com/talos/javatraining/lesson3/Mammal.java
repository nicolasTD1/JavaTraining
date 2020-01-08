package com.talos.javatraining.lesson3;

import static java.util.Arrays.asList;

import java.util.List;

public interface Mammal extends Animal{

	/*default List<String> getCharacteristics()
	{
		List<String> characteristics = new ArrayList<>(AnimalSupport.getMammalCharacteristics());
		characteristics.add("They bark");
		return characteristics;
	}*/
	
	default List<String> getCharacteristics() {
		return asList("They have hair or fur", "They suckle milk when they are young");
	}
	
}
