package com.example.battlefordentalperfection;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class bios extends ActionBarActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		//opens the content view
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bios);
		
		//translates text
		translate(sharedVars.getbIsEng());
		
		//creates button to return to main menu and sets ID
		Button buttonBiosReturn = (Button) findViewById(R.id.biosReturn);
		buttonBiosReturn.setId(0);

		//creates buttons to view heros and sets IDs
		ImageButton buttonHeroOne = (ImageButton) findViewById(R.id.heroButton1);
		buttonHeroOne.setId(1);
		ImageButton buttonHeroTwo = (ImageButton) findViewById(R.id.heroButton2);
		buttonHeroTwo.setId(2);
		ImageButton buttonHeroThree = (ImageButton) findViewById(R.id.heroButton3);
		buttonHeroThree.setId(3);
		ImageButton buttonHeroFour = (ImageButton) findViewById(R.id.heroButton4);
		buttonHeroFour.setId(4);

		//creates buttons to view villains and sets IDs
		ImageButton buttonVillainOne = (ImageButton) findViewById(R.id.villainButton1);
		buttonVillainOne.setId(5);
		ImageButton buttonVillainTwo = (ImageButton) findViewById(R.id.villainButton2);
		buttonVillainTwo.setId(6);
		ImageButton buttonVillainThree = (ImageButton) findViewById(R.id.villainButton3);
		buttonVillainThree.setId(7);
		ImageButton buttonVillainFour = (ImageButton) findViewById(R.id.villainButton4);
		buttonVillainFour.setId(8);
		ImageButton buttonVillainFive = (ImageButton) findViewById(R.id.villainButton5);
		buttonVillainFive.setId(9);
		ImageButton buttonVillainSix = (ImageButton) findViewById(R.id.villainButton6);
		buttonVillainSix.setId(10);
		ImageButton buttonVillainSeven = (ImageButton) findViewById(R.id.villainButton7);
		buttonVillainSeven.setId(11);
		ImageButton buttonVillainEight = (ImageButton) findViewById(R.id.villainButton8);
		buttonVillainEight.setId(12);
		
		//ties created buttons to the listener
		buttonBiosReturn.setOnClickListener(buttonBiosReturnListener);
		
		buttonHeroOne.setOnClickListener(buttonBiosListener);
		buttonHeroTwo.setOnClickListener(buttonBiosListener);
		buttonHeroThree.setOnClickListener(buttonBiosListener);
		buttonHeroFour.setOnClickListener(buttonBiosListener);
		
		buttonVillainOne.setOnClickListener(buttonBiosListener);
		buttonVillainTwo.setOnClickListener(buttonBiosListener);
		buttonVillainThree.setOnClickListener(buttonBiosListener);
		buttonVillainFour.setOnClickListener(buttonBiosListener);
		buttonVillainFive.setOnClickListener(buttonBiosListener);
		buttonVillainSix.setOnClickListener(buttonBiosListener);
		buttonVillainSeven.setOnClickListener(buttonBiosListener);
		buttonVillainEight.setOnClickListener(buttonBiosListener);
	}
	
	//if bIsEng is true, text is English. Otherwise, it's Spanish
	public void translate(boolean isEng)
	{
		Button buttonBiosReturn = (Button) findViewById(R.id.biosReturn);
		
		if (isEng)
			buttonBiosReturn.setText(getString(R.string.buttonReturnEng));
		else
			buttonBiosReturn.setText(getString(R.string.buttonReturnSpn));	
	};
	
	//listener for all buttons on this view.
	public OnClickListener buttonBiosListener = new OnClickListener() 
	{
		public void onClick(View v)
		{
			processClick((ImageButton) v);
		}
	};

	//When a click event occurs, the button's id is used to determine which was clicked
	//Operation that follows is based off the ID.
	private void processClick(ImageButton iBut)
	{	
		Intent viewChar;
		int buttonId = iBut.getId();
		switch(buttonId){
		case 1://displays hero 1
			sharedVars.setCharChoice(1);
			viewChar = new Intent(bios.this, character.class);
			startActivity(viewChar);
			break;
		case 2://displays hero 2
			sharedVars.setCharChoice(2);
			viewChar = new Intent(bios.this, character.class);
			startActivity(viewChar);
			break;
		case 3://displays hero 3
			sharedVars.setCharChoice(3);
			viewChar = new Intent(bios.this, character.class);
			startActivity(viewChar);
			break;
		case 4://displays hero 4
			sharedVars.setCharChoice(4);
			viewChar = new Intent(bios.this, character.class);
			startActivity(viewChar);
			break;
		case 5://displays villain 1
			sharedVars.setCharChoice(5);
			viewChar = new Intent(bios.this, character.class);
			startActivity(viewChar);
			break;
		case 6://displays villain 2
			sharedVars.setCharChoice(6);
			viewChar = new Intent(bios.this, character.class);
			startActivity(viewChar);
			break;
		case 7://displays villain 3
			sharedVars.setCharChoice(7);
			viewChar = new Intent(bios.this, character.class);
			startActivity(viewChar);
			break;
		case 8://displays villain 4
			sharedVars.setCharChoice(8);
			viewChar = new Intent(bios.this, character.class);
			startActivity(viewChar);
			break;
		case 9://displays villain 5
			sharedVars.setCharChoice(9);
			viewChar = new Intent(bios.this, character.class);
			startActivity(viewChar);
			break;
		case 10://displays villain 6
			sharedVars.setCharChoice(10);
			viewChar = new Intent(bios.this, character.class);
			startActivity(viewChar);
			break;
		case 11://displays villain 7
			sharedVars.setCharChoice(11);
			viewChar = new Intent(bios.this, character.class);
			startActivity(viewChar);
			break;
		case 12://displays villain 8
			sharedVars.setCharChoice(12);
			viewChar = new Intent(bios.this, character.class);
			startActivity(viewChar);
			break;
		}
	}

	public OnClickListener buttonBiosReturnListener = new OnClickListener() 
	{
		public void onClick(View v)
		{
			Intent returnBios = 
					new Intent(bios.this, MainActivity.class);
			startActivity(returnBios);
		}
	};
	
}
