package com.talos.javatraining.lesson3.impl.animals;

import com.talos.javatraining.lesson3.Invertebrate;
import com.talos.javatraining.lesson3.impl.AbstractAnimal;

import java.util.ArrayList;
import java.util.List;


public class Worm extends AbstractAnimal implements Invertebrate
{
	/*@Override
	public List<String> getCharacteristics()
	{
		List<String> characteristics = new ArrayList<>(AnimalSupport.getInvertebrateCharacteristics());
		characteristics.add("They are bilaterally symmetrical");
		return characteristics;
	}*/

	@Override
	public List<String> getParentCharacteristics() {
		List<String> characteristics = new ArrayList<>(Invertebrate.super.getCharacteristics());
		return characteristics;
	}

	@Override
	public void populateCharacteristics(List<String> characteristics) {
		characteristics.add("They are bilaterally symmetrical");

	}
}
