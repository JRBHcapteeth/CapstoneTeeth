package com.example.battlefordentalperfection;

public class sharedVars {
	
	//toggles language between English and Spanish
	private static boolean bIsEng = true; 
	private static int charChoice = 1;
	
	static boolean getbIsEng()
	{
		return bIsEng;
	}
	
	static void flipbIsEng()
	{
		bIsEng = !bIsEng;
	}
	static int getCharChoice()
	{
		return charChoice;
	}
	
	static void setCharChoice(int x)
	{
		charChoice = x;
	}
	
}
