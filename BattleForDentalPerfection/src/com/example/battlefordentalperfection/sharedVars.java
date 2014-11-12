package com.example.battlefordentalperfection;

public class sharedVars {
	
	//toggles language between English and Spanish
	private static boolean bIsEng = true; 
	private static int charChoice = 1;
	
	static boolean getbIsEng()
	{
		return bIsEng;
	}
	
	static boolean flipbIsEng()
	{
		bIsEng = !bIsEng;
		return bIsEng;
	}
	static int getCharChoice()
	{
		return charChoice;
	}
	
	static int setCharChoice(int x)
	{
		charChoice = x;
		return charChoice;
	}
	
}
