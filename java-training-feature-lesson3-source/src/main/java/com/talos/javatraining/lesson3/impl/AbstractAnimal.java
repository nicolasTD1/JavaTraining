package com.talos.javatraining.lesson3.impl;

import com.talos.javatraining.lesson3.Animal;

import java.util.List;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;


public abstract class AbstractAnimal implements Animal
{
	List<String> characteristics;
	
	@Override
    public List<String> getCharacteristics()
    {
        List<String> characteristics = new ArrayList<>(getParentCharacteristics());
        populateCharacteristics(characteristics);
        return characteristics;
    }
 
    public abstract List<String> getParentCharacteristics();
 
    public abstract void populateCharacteristics(List<String> characteristics);
	

	@Override
	public String getName()
	{
		return getClass().getSimpleName();
	}

	@Override
	public String getFullDescription(){
		StringBuilder builder = new StringBuilder();
		String name = getName();
		String lastChr = name.substring(name.length()-1);
		builder.append(name).append(lastChr.equals("s") ? "es" : "s").append(" have these characteristics :");
		for (String characteristic : getCharacteristics())
		{
			builder.append(StringUtils.LF).append(StringUtils.CR).append("- ").append(characteristic);
		}
		return builder.toString();
	}
}
