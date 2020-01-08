package com.talos.javatraining.lesson3.impl.animals;


import java.util.ArrayList;
import java.util.List;

import com.talos.javatraining.lesson3.Invertebrate;
import com.talos.javatraining.lesson3.impl.AbstractAnimal;


public class Octopus extends AbstractAnimal implements Invertebrate
{

	@Override
	public List<String> getParentCharacteristics() {
		List<String> characteristics = new ArrayList<>(Invertebrate.super.getCharacteristics());;
		return characteristics;
	}

	@Override
	public void populateCharacteristics(List<String> characteristics) {
		characteristics.add("They have eight legs");
	}
}