package com.talos.javatraining.lesson3.impl.animals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.talos.javatraining.lesson3.CartilaginousFish;
import com.talos.javatraining.lesson3.impl.AbstractAnimal;

public class Ray extends AbstractAnimal implements CartilaginousFish
{
	/*@Override
	public List<String> getCharacteristics()
	{
		List<String> characteristics = new ArrayList<>(AnimalSupport.getFishCharacteristics());
		characteristics.addAll(AnimalSupport.getCartilaginousFishCharacteristics());
		characteristics.addAll(Arrays.asList("They are flattened", "They have five gill openings"));
		return characteristics;
	}*/

	@Override
	public List<String> getParentCharacteristics() {
		List<String> characteristics = new ArrayList<>(CartilaginousFish.super.getParent());
		return characteristics;
	}

	@Override
	public void populateCharacteristics(List<String> characteristics) {
		characteristics.addAll(CartilaginousFish.super.getCharacteristics());
		characteristics.addAll(Arrays.asList("They are flattened", "They have five gill openings"));

	}
}
