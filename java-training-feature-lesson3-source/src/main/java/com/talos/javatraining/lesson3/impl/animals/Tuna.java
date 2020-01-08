package com.talos.javatraining.lesson3.impl.animals;

import java.util.ArrayList;
import java.util.List;

import com.talos.javatraining.lesson3.BonyFish;
import com.talos.javatraining.lesson3.impl.AbstractAnimal;

public class Tuna extends AbstractAnimal implements BonyFish
{
	/*@Override
	public List<String> getCharacteristics()
	{
		List<String> characteristics = new ArrayList<>(AnimalSupport.getFishCharacteristics());
		characteristics.addAll(AnimalSupport.getBonyFishCharacteristics());
		characteristics.add("Excellent swimmers");
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
		characteristics.add("Excellent swimmers");
	}
}
