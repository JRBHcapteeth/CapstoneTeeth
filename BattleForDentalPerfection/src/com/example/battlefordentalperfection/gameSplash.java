package com.example.battlefordentalperfection;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class gameSplash extends ActionBarActivity{
	
	private game gameView;
	
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
		Button buttonDiff = (Button) findViewById(R.id.buttonDifficulty);
		Button buttonStart = (Button) findViewById(R.id.buttonStartGame);
		Button buttonGameReturn = (Button) findViewById(R.id.buttonGameReturn);
		TextView textHighScore = (TextView) findViewById(R.id.textViewHighScore);
		
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
			String message = getString(R.string.textHighScoreEng)+" "+String.valueOf(sharedVars.getHighScore());
			textHighScore.setText(message);
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
			String message = getString(R.string.textHighScoreSpn)+" "+String.valueOf(sharedVars.getHighScore());
			textHighScore.setText(message);		
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
			Intent startGame = 
					new Intent(gameSplash.this, gameBase.class);
			startActivity(startGame);
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
