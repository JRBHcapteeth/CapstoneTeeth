package com.example.battlefordentalperfection;

import java.util.Calendar;

import android.content.SharedPreferences;

public class sharedVars {

	//highscore from game
	private static int highScoreEasy = 0;
	private static int highScoreMedium = 0;
	private static int highScoreHard = 0;
	//difficulty settings for game
	private static int difficulty = 0; //0 = easy, 1= med, 2 = hard;
	//toggles language between English and Spanish
	private static boolean bIsEng = true; 
	//character chosen to be viewed
	private static int charChoice = 1;
	//start with main hero and a lower level villain unlocked
	private static boolean charUnlocked[] = {true, false, false, false,
					true, false, false, false, false, false, false, false};
	//tracks credits earned from timer and spent by unlocking characters
	private static int credits = 10;
	//vars used to store save data
	private static final String HIGH_SCORE_EASY = "HIGH_SCORE_EASY";
	private static final String HIGH_SCORE_MEDIUM = "HIGH_SCORE_MEDIUM";
	private static final String HIGH_SCORE_HARD = "HIGH_SCORE_HARD";
	private static final String DIFFICULTY = "DIFFICULTY";
	private static final String CURRENT_LANG = "CURRENT_LANG";
	private static final String CREDITS = "CREDITS";
	private static final String UNLOCKED_ONE = "UNLOCKED_ONE";
	private static final String UNLOCKED_TWO = "UNLOCKED_TWO";
	private static final String UNLOCKED_THREE = "UNLOCKED_THREE";
	private static final String UNLOCKED_FOUR = "UNLOCKED_FOUR";
	private static final String UNLOCKED_FIVE = "UNLOCKED_FIVE";
	private static final String UNLOCKED_SIX = "UNLOCKED_SIX";
	private static final String UNLOCKED_SEVEN = "UNLOCKED_SEVEN";
	private static final String UNLOCKED_EIGHT = "UNLOCKED_EIGHT";
	private static final String UNLOCKED_NINE = "UNLOCKED_NINE";
	private static final String UNLOCKED_TEN = "UNLOCKED_TEN";
	private static final String UNLOCKED_ELEVEN = "UNLOCKED_ELEVEN";
	private static final String UNLOCKED_TWELVE = "UNLOCKED_TWELVE";
	private static long calendar;
	
	static boolean getbIsEng()
	{
		return bIsEng;
	}
	
	static void flipbIsEng(SharedPreferences saveFile)
	{
		bIsEng = !bIsEng;//flips boolean value
		//adds value to preferences file
		SharedPreferences.Editor editor = saveFile.edit();
		   editor.putBoolean(CURRENT_LANG, bIsEng);
		   editor.commit();
	}
	
	static void loadbIsEng(SharedPreferences saveFile)
	{
		bIsEng = saveFile.getBoolean(CURRENT_LANG, true);
	}
	
	static int getCharChoice()
	{
		return charChoice;
	}
	
	static void setCharChoice(int x)
	{
		charChoice = x;
	}
	
	static boolean getIfCharUnlocked(int pos)
	{
		return charUnlocked[pos-1];
	}
	
	static void unlockChar(int pos, SharedPreferences saveFile)
	{
		charUnlocked[pos-1] = true;
		//adds value to preferences file
		SharedPreferences.Editor editor = saveFile.edit();
		   editor.putBoolean(UNLOCKED_ONE, charUnlocked[0]);
		   editor.putBoolean(UNLOCKED_TWO, charUnlocked[1]);
		   editor.putBoolean(UNLOCKED_THREE, charUnlocked[2]);
		   editor.putBoolean(UNLOCKED_FOUR, charUnlocked[3]);
		   editor.putBoolean(UNLOCKED_FIVE, charUnlocked[4]);
		   editor.putBoolean(UNLOCKED_SIX, charUnlocked[5]);
		   editor.putBoolean(UNLOCKED_SEVEN, charUnlocked[6]);
		   editor.putBoolean(UNLOCKED_EIGHT, charUnlocked[7]);
		   editor.putBoolean(UNLOCKED_NINE, charUnlocked[8]);
		   editor.putBoolean(UNLOCKED_TEN, charUnlocked[9]);
		   editor.putBoolean(UNLOCKED_ELEVEN, charUnlocked[10]);
		   editor.putBoolean(UNLOCKED_TWELVE, charUnlocked[11]);
		   editor.commit();
	}
	
	static void loadUnlocked(SharedPreferences saveFile)
	{
		charUnlocked[0] = saveFile.getBoolean(UNLOCKED_ONE, true);//main hero
		charUnlocked[1] = saveFile.getBoolean(UNLOCKED_TWO, false);
		charUnlocked[2] = saveFile.getBoolean(UNLOCKED_THREE, false);
		charUnlocked[3] = saveFile.getBoolean(UNLOCKED_FOUR, false);
		charUnlocked[4] = saveFile.getBoolean(UNLOCKED_FIVE, true);//lowbie villain
		charUnlocked[5] = saveFile.getBoolean(UNLOCKED_SIX, false);
		charUnlocked[6] = saveFile.getBoolean(UNLOCKED_SEVEN, false);
		charUnlocked[7] = saveFile.getBoolean(UNLOCKED_EIGHT, false);
		charUnlocked[8] = saveFile.getBoolean(UNLOCKED_NINE, false);
		charUnlocked[9] = saveFile.getBoolean(UNLOCKED_TEN, false);
		charUnlocked[10] = saveFile.getBoolean(UNLOCKED_ELEVEN, false);
		charUnlocked[11] = saveFile.getBoolean(UNLOCKED_TWELVE, false);
	}
	
	static int getCredits()
	{
		return credits;
	}
	
	static void setCredits(int pos, SharedPreferences saveFile)
	{
		credits = credits+pos;
		//adds value to preferences file
		SharedPreferences.Editor editor = saveFile.edit();
		   editor.putInt(CREDITS, credits);
		   editor.commit();
	}
	
	static void loadCredits(SharedPreferences saveFile)
	{
		credits = saveFile.getInt(CREDITS, 1000);
	}
	
	static int getHighScore(int diff)
	{
		switch(diff){
		case 0: return highScoreEasy;
		case 1: return highScoreMedium;
		case 2: return highScoreHard;
		}
		return 0;
	}
	
	static void setHighScore(int diff, int num, SharedPreferences saveFile)
	{
		SharedPreferences.Editor editor = saveFile.edit();
		switch(diff){
		case 0: 
			highScoreEasy = num;
			//adds value to preferences file
			editor.putInt(HIGH_SCORE_EASY, num);
			editor.commit();
			break;
		case 1: highScoreMedium = num;
			//adds value to preferences file
			editor.putInt(HIGH_SCORE_MEDIUM, num);
			editor.commit();
			break;
		case 2: highScoreHard = num;
			//adds value to preferences file
			editor.putInt(HIGH_SCORE_HARD, num);
			editor.commit();
			break;
		}
		
	}
	
	static void loadHighScore(SharedPreferences saveFile)
	{
		highScoreEasy = saveFile.getInt(HIGH_SCORE_EASY, 0);
		highScoreMedium = saveFile.getInt(HIGH_SCORE_MEDIUM, 0);
		highScoreHard = saveFile.getInt(HIGH_SCORE_HARD, 0);
	}
	
	static int getDifficulty()
	{
		return difficulty;
	}
	
	static void setDifficulty(int num, SharedPreferences saveFile)
	{
		difficulty = num;
		//adds value to preferences file
		SharedPreferences.Editor editor = saveFile.edit();
		   editor.putInt(DIFFICULTY, num);
		   editor.commit();
	}
	
	static void loadDifficulty(SharedPreferences saveFile)
	{
		difficulty = saveFile.getInt(DIFFICULTY, 0);
	}
	
	static void setTimer(Calendar cal, SharedPreferences saveFile)
	{
		calendar = cal.getTimeInMillis();
		SharedPreferences.Editor editor = saveFile.edit();
		editor.putLong("TIMER", calendar);
		editor.commit();
		
	}
	
	static void loadTimer(SharedPreferences saveFile)
	{
		calendar = saveFile.getLong("TIMER", 0);
	}
	
	
	static boolean checkTimerUse(Calendar cal)
	{
		Calendar calen = Calendar.getInstance();
		calen.setTimeInMillis(calendar);
		calen.add(Calendar.HOUR, 8);
		long time1 = calen.getTimeInMillis();
		long time2 = cal.getTimeInMillis();
		
		if(time2 >= time1)
		{
			return true;
		}
		else
		{
			return false;
		}
		
		
		
		
	}
	
	
}
