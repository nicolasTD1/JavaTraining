package com.talos.javatraining.lesson3.impl.animals;

import java.util.ArrayList;
import java.util.List;

import com.talos.javatraining.lesson3.BonyFish;
import com.talos.javatraining.lesson3.impl.AbstractAnimal;

public class Salmon extends AbstractAnimal implements BonyFish
{
	/*@Override
	public List<String> getCharacteristics()
	{
		List<String> characteristics = new ArrayList<>(AnimalSupport.getFishCharacteristics());
		characteristics.addAll(AnimalSupport.getBonyFishCharacteristics());
		characteristics.add("They are anadromous fish");
		return characteristics;
	}*/

	@Override
	public List<String> getParentCharacteristics() {
		List<String> characteristics = new ArrayList<>(BonyFish.super.getParent());
		return characteristics;
	}

	@Override
	public void populateCharacteristics(List<String> characteristics) {
		characteristics.addAll(BonyFish.super.getCharacteristics());
		characteristics.add("They are anadromous fish");

	}
}
