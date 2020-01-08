package com.talos.javatraining.lesson3;

import java.util.List;
import org.apache.commons.lang3.StringUtils;


public interface Animal
{

	default String getName(){
		return getClass().getSimpleName();
	}
	
	List<String> getCharacteristics();

	
	default String getFullDescription(){
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
	
	static Animal create(String str) {
		
		String[] nombres = {
				"Worm", "Tuna", "Sparrow", "Shark", "Salmon", "Salamander", "Ray", "Octopus", 
				"Lamprey", "Human", "Hen", "Hagfish", "Frog", "Dog", "Crocodile", "Alligator"
		};
		
		for(int i=0; i<nombres.length; i++) {
			if(str==null) return null;
			
			if(str.equals(nombres[i])) {
				try {
					Class<?> c = Class.forName("com.talos.javatraining.lesson3.impl.animals."+nombres[i]);
					Animal myAnimal = (Animal) c.newInstance();
					return myAnimal;
				} catch (Exception e){ 	}
			} 
		}
		return null;
	}

}