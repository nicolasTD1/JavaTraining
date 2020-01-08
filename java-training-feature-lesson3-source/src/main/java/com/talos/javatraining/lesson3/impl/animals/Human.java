package com.talos.javatraining.lesson3.impl.animals;

import java.util.ArrayList;
import java.util.List;

import com.talos.javatraining.lesson3.Mammal;
import com.talos.javatraining.lesson3.impl.AbstractAnimal;

public class Human extends AbstractAnimal implements Mammal
{
	@Override
	public List<String> getParentCharacteristics() {
		List<String> characteristics = new ArrayList<>(Mammal.super.getCharacteristics());
		return characteristics;
	}

	@Override
	public void populateCharacteristics(List<String> characteristics) {
		characteristics.add("They rule the word!");
		
	}
}
