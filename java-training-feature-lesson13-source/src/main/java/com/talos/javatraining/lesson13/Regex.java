package com.talos.javatraining.lesson13;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.PatternSyntaxException;

public class Regex implements RegexInterface{
	
	static final Regex reg = new Regex();
	
	/* String matches with: "true" or “True”. */
	public void containsTrue(String text) {
		String regex = "(.*[T|t]rue.*)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		
		boolean result = matcher.matches();
		
		System.out.println(result);
	}
	
	/* String matches with: "ok" or "oK" or "Ok" or “OK".  */
	public void containsOKVariants(String text) {
		String regex = ".*(?i)ok.*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		
		boolean result = matcher.matches();
		
		System.out.println(result);
	}
	
	/* String contains three consecutive letters one after another. Ex: "1a2bc3def4". */
	public void containsThreeConsecutivesLetters(String text) {
		String regex = ".*[a-zA-Z]{3}.*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		
		boolean result = matcher.matches();
		
		System.out.println(result);
	}
	
	/* String starts with some character other than number. Ex: “a12334bcde". */
	public void notStartWithNumbers(String text) {
		String regex = "^\\D.*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		
		boolean result = matcher.matches();
		
		System.out.println(result);
	}
	
	/*  String has three consecutive number at beginning. Ex: “123abcde”. */
	public void startWithThreeConsecutivesNumbers(String text) {
		String regex = "^\\d{3,}.*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		
		boolean result = matcher.matches();
		
		System.out.println(result);
	}
	
	/* String has a single number between 0 and 2 or a number between 7 and 9. Ex: “8". */
	public void unionNumbersZeroTwoAndSevenNine(String text) {
		String regex = "[0-2[7-9]]?";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		
		boolean result = matcher.matches();
		
		System.out.println(result);
	}
	
	/* String contains a number less than 300. Ex: "Last year I worked 299”. */
	public void containNumbersLessThan300(String text) {
		String regex = "\\D*([0-9]|[1-9][0-9]|[1-2][0-9][0-9])\\D*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		
		boolean result = matcher.matches();
		
		System.out.println(result);
	}

	/* Must replace all instances (not case sensitive) of: “you”, “u” or “youuuu” (with any number of u characters tacked onto the end) 
	 * to "my nightmare” (always lower case). */
	public void autocorrect(String text) {
		StringBuilder stringBuilder = new StringBuilder();
		String[] arr = text.split("\\s");
		for(String s: arr) {
			if(s.endsWith("u")) {
				s = s.replace(s, "my nightmare");
			}
			stringBuilder.append(s + " ");
		}
		System.out.println(stringBuilder.toString());
	}
	
	public void test() {
    	System.out.println("containsTrue");
    		reg.containsTrue("this is a true statement, which returns True");
    	System.out.println("-------------------");
    	System.out.println("containsOKVariants");
	    	reg.containsOKVariants("ewewok holasdf");
	    	reg.containsOKVariants("erwerwoKf");
	    	reg.containsOKVariants("O");
	    	reg.containsOKVariants("OK");
    	System.out.println("-------------------");
    	System.out.println("containsThreeConsecutivesLetters");
	    	reg.containsThreeConsecutivesLetters("1a2bc3def4");
	    	reg.containsThreeConsecutivesLetters("1a2bc3df3434344");
    	System.out.println("-------------------");
    	System.out.println("notStartWithNumbers");
	    	reg.notStartWithNumbers("a12334bcde");
	    	reg.notStartWithNumbers("12334bcde");
	    	reg.notStartWithNumbers("?12334bcde");
    	System.out.println("-------------------");
    	System.out.println("startWithThreeConsecutivesNumbers");
	    	reg.startWithThreeConsecutivesNumbers("123abcde");
    	System.out.println("-------------------");
    	System.out.println("unionNumbersZeroTwoAndSevenNine");
	    	reg.unionNumbersZeroTwoAndSevenNine("0");
    	System.out.println("-------------------");
    	System.out.println("containNumbersLessThan300");
	    	reg.containNumbersLessThan300("Last year I worked 100 and ");
	    	reg.containNumbersLessThan300("sQWEQWCwe 300 aeW Wewe");
    	System.out.println("-------------------");
    	System.out.println("autocorrect");
	    	reg.autocorrect("Hellou u user");
	}

    public static void main( String[] args )
    {
        //System.out.println( "Hello World!" );    	  
    	reg.test();
    }
}

