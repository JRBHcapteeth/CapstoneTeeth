package com.example.battlefordentalperfection;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {
	
	//toggles language between English and Spanish
	boolean bIsEng = true; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		translate(bIsEng);
		
		//button objects
		Button buttonBios = (Button) findViewById(R.id.buttonBios);
		Button buttonReminder = (Button) findViewById(R.id.buttonReminder);
		Button buttonTimer = (Button) findViewById(R.id.buttonTimer);
		Button buttonStory = (Button) findViewById(R.id.buttonStory);
		Button buttonGame = (Button) findViewById(R.id.buttonGame);
		Button buttonLang = (Button) findViewById(R.id.buttonLang);
		
		//creates listeners for each button
		buttonBios.setOnClickListener(buttonBiosListener);
		buttonReminder.setOnClickListener(buttonReminderListener);		
		buttonTimer.setOnClickListener(buttonTimerListener);		
		buttonStory.setOnClickListener(buttonStoryListener);		
		buttonGame.setOnClickListener(buttonGameListener);		
		buttonLang.setOnClickListener(buttonLangListener);
		
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void translate(boolean isEng)
	{
		Button buttonBios = (Button) findViewById(R.id.buttonBios);
		Button buttonReminder = (Button) findViewById(R.id.buttonReminder);
		Button buttonTimer = (Button) findViewById(R.id.buttonTimer);
		Button buttonStory = (Button) findViewById(R.id.buttonStory);
		Button buttonGame = (Button) findViewById(R.id.buttonGame);
		Button buttonLang = (Button) findViewById(R.id.buttonLang);
		
		if (isEng)
		{
			buttonBios.setText(getString(R.string.buttonBiosEng));
			buttonTimer.setText(getString(R.string.buttonTimerEng));
			buttonReminder.setText(getString(R.string.buttonReminderEng));
			buttonStory.setText(getString(R.string.buttonStoryEng));
			buttonGame.setText(getString(R.string.buttonGameEng));
			buttonLang.setText(getString(R.string.buttonLangEng));
		}
		else
		{
			buttonBios.setText(getString(R.string.buttonBiosSpn));
			buttonTimer.setText(getString(R.string.buttonTimerSpn));
			buttonReminder.setText(getString(R.string.buttonReminderSpn));
			buttonStory.setText(getString(R.string.buttonStorySpn));
			buttonGame.setText(getString(R.string.buttonGameSpn));
			buttonLang.setText(getString(R.string.buttonLangSpn));		
		}
	};
	/********************************************************************
	 * Button listeners. When the corresponding button is clicked, the 
	 * action within is performed. All but languages navigate to another
	 * part of the app.
	 ********************************************************************/
	public OnClickListener buttonBiosListener = new OnClickListener() 
	{
		public void onClick(View v)
		{
			setContentView(R.layout.bios);
		}
	};
	public OnClickListener buttonReminderListener = new OnClickListener() 
	{
		public void onClick(View v)
		{
			setContentView(R.layout.reminder_home);
		}
	};
	public OnClickListener buttonTimerListener = new OnClickListener() 
	{
		public void onClick(View v)
		{
			setContentView(R.layout.timer);
		}
	};
	public OnClickListener buttonStoryListener = new OnClickListener() 
	{
		public void onClick(View v)
		{
			setContentView(R.layout.story);
		}
	};
	public OnClickListener buttonGameListener = new OnClickListener() 
	{
		public void onClick(View v)
		{
			setContentView(R.layout.game);
		}
	};
	public OnClickListener buttonLangListener = new OnClickListener() 
	{
		public void onClick(View v)
		{
			bIsEng = !bIsEng;
			translate(bIsEng);
		}
	};
}
