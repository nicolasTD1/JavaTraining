package com.talos.javatraining.lesson13;

public interface RegexInterface {

	public void containsTrue(String text);
	
	public void containsOKVariants(String text);
	
	public void containsThreeConsecutivesLetters(String text);
	
	public void notStartWithNumbers(String text);
	
	public void startWithThreeConsecutivesNumbers(String text);
	
	public void unionNumbersZeroTwoAndSevenNine(String text);
	
	public void containNumbersLessThan300(String text);
	
	public void autocorrect(String text);
	
}
