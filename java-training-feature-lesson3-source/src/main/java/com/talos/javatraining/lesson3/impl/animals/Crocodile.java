package com.talos.javatraining.lesson3.impl.animals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.talos.javatraining.lesson3.Reptile;
import com.talos.javatraining.lesson3.impl.AbstractAnimal;

public class Crocodile extends AbstractAnimal implements Reptile
{
	/*@Override
	public List<String> getCharacteristics()
	{
		List<String> characteristics = new ArrayList<>(AnimalSupport.getReptileCharacteristics());
		characteristics.addAll(Arrays.asList("They have V-shaped snouts", "Toothy grin"));
		return characteristics;
	}*/

	@Override
	public List<String> getParentCharacteristics() {
		List<String> characteristics = new ArrayList<>(Reptile.super.getCharacteristics());
		return characteristics;
	}

	@Override
	public void populateCharacteristics(List<String> characteristics) {
		characteristics.addAll(Arrays.asList("They have V-shaped snouts", "Toothy grin"));

	}
}
