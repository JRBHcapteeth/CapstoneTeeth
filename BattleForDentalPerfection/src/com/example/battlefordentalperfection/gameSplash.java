package com.example.battlefordentalperfection;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class gameSplash extends ActionBarActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		//opens the content view
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gamesplash);
		
		translate();

		Button buttonDiff = (Button) findViewById(R.id.buttonDifficulty);
		Button buttonStart = (Button) findViewById(R.id.buttonStartGame);
		Button buttonGameReturn = (Button) findViewById(R.id.buttonGameReturn);
		
		//creates listeners for each button
		buttonDiff.setOnClickListener(buttonDiffListener);
		buttonStart.setOnClickListener(buttonStartListener);
		buttonGameReturn.setOnClickListener(buttonGameReturnListener);
	};
	
	public void translate()
	{
		String message;
		Button buttonDiff = (Button) findViewById(R.id.buttonDifficulty);
		Button buttonStart = (Button) findViewById(R.id.buttonStartGame);
		Button buttonGameReturn = (Button) findViewById(R.id.buttonGameReturn);
		TextView textHighScoreEasy = (TextView) findViewById(R.id.textViewHighScoreEasy);
		TextView textHighScoreMedium = (TextView) findViewById(R.id.textViewHighScoreMedium);
		TextView textHighScoreHard = (TextView) findViewById(R.id.textViewHighScoreHard);
		
		if (sharedVars.getbIsEng())
		{
			buttonStart.setText(getString(R.string.buttonStartGameEng));
			buttonGameReturn.setText(getString(R.string.buttonReturnEng));
			switch(sharedVars.getDifficulty()){
			case 0:
				buttonDiff.setText(getString(R.string.buttonDifficultyEasyEng));
				break;
			case 1:
				buttonDiff.setText(getString(R.string.buttonDifficultyMedEng));
				break;
			case 2:
				buttonDiff.setText(getString(R.string.buttonDifficultyHardEng));
				break;
			}
			message = getString(R.string.textHighScoreEasyEng)+" "+String.valueOf(sharedVars.getHighScore(0));
			textHighScoreEasy.setText(message);
			message = getString(R.string.textHighScoreMediumEng)+" "+String.valueOf(sharedVars.getHighScore(1));
			textHighScoreMedium.setText(message);
			message = getString(R.string.textHighScoreHardEng)+" "+String.valueOf(sharedVars.getHighScore(2));
			textHighScoreHard.setText(message);
		}
		else
		{
			buttonStart.setText(getString(R.string.buttonStartGameSpn));
			buttonGameReturn.setText(getString(R.string.buttonReturnSpn));
			switch(sharedVars.getDifficulty()){
			case 0:
				buttonDiff.setText(getString(R.string.buttonDifficultyEasySpn));
				break;
			case 1:
				buttonDiff.setText(getString(R.string.buttonDifficultyMedSpn));
				break;
			case 2:
				buttonDiff.setText(getString(R.string.buttonDifficultyHardSpn));
				break;
			}
			message = getString(R.string.textHighScoreEasySpn)+" "+String.valueOf(sharedVars.getHighScore(0));
			textHighScoreEasy.setText(message);
			message = getString(R.string.textHighScoreMediumSpn)+" "+String.valueOf(sharedVars.getHighScore(1));
			textHighScoreMedium.setText(message);
			message = getString(R.string.textHighScoreHardSpn)+" "+String.valueOf(sharedVars.getHighScore(2));
			textHighScoreHard.setText(message);	
		}
	};
	

	public OnClickListener buttonDiffListener = new OnClickListener() 
	{
		public void onClick(View v)
		{
			changeDiff();
		}
	};
	
	public void changeDiff()
	{
		SharedPreferences saveFile = this.getSharedPreferences("com.example.battlefordentalperfection", Context.MODE_PRIVATE);
		switch(sharedVars.getDifficulty()){
		case 0: //Easy to Medium
			sharedVars.setDifficulty(1, saveFile);
			break;
		case 1: //Medium to Hard
			sharedVars.setDifficulty(2, saveFile);
			break;
		case 2: //Hard to Easy
			sharedVars.setDifficulty(0, saveFile);
			break;
		}
		translate();
	}

	public OnClickListener buttonStartListener = new OnClickListener() 
	{
		public void onClick(View v)
		{
			Button buttonStart = (Button) findViewById(R.id.buttonStartGame);
			if (sharedVars.getbIsEng())
				buttonStart.setText(getString(R.string.buttonLoadingEng));
			else
				buttonStart.setText(getString(R.string.buttonLoadingSpn));
			
			Intent startGame = 
					new Intent(gameSplash.this, gameBase.class);
			startActivity(startGame);
			
			if (sharedVars.getbIsEng())
				buttonStart.setText(getString(R.string.buttonStartGameEng));
			else
				buttonStart.setText(getString(R.string.buttonStartGameSpn));
		}
	};
	
	public OnClickListener buttonGameReturnListener = new OnClickListener() 
	{
		public void onClick(View v)
		{
			Intent startGame = 
					new Intent(gameSplash.this, MainActivity.class);
			startActivity(startGame);
		}
	};  
	   
}
