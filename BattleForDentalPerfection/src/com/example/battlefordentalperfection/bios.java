package com.example.battlefordentalperfection;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class bios extends ActionBarActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bios);
		
		translate(sharedVars.getbIsEng());
		
		Button buttonBiosReturn = (Button) findViewById(R.id.biosReturn);
		ImageButton buttonHeroOne = (ImageButton) findViewById(R.id.heroButton1);
		ImageButton buttonHeroTwo = (ImageButton) findViewById(R.id.heroButton2);
		
		buttonBiosReturn.setOnClickListener(buttonBiosReturnListener);
		buttonHeroOne.setOnClickListener(buttonHeroOneListener);
		buttonHeroTwo.setOnClickListener(buttonHeroTwoListener);
	}
	
	public void translate(boolean isEng)
	{
		Button buttonBiosReturn = (Button) findViewById(R.id.biosReturn);
		
		if (isEng)
		{
			buttonBiosReturn.setText(getString(R.string.buttonReturnEng));
		}
		else
		{
			buttonBiosReturn.setText(getString(R.string.buttonReturnSpn));	
		}
	};
	
	public OnClickListener buttonBiosReturnListener = new OnClickListener() 
	{
		public void onClick(View v)
		{
			Intent returnMain = 
					new Intent(bios.this, MainActivity.class);
			startActivity(returnMain);
		}
	};

	public OnClickListener buttonHeroOneListener = new OnClickListener() 
	{
		public void onClick(View v)
		{
			sharedVars.setCharChoice(1);
			Intent viewChar = 
					new Intent(bios.this, character.class);
			startActivity(viewChar);
		}
	};
	
	public OnClickListener buttonHeroTwoListener = new OnClickListener() 
	{
		public void onClick(View v)
		{
			sharedVars.setCharChoice(2);
			Intent viewChar = 
					new Intent(bios.this, character.class);
			startActivity(viewChar);
		}
	};
}
