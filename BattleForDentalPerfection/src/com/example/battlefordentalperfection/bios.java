package com.example.battlefordentalperfection;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
		translate();
		
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
	public void translate()
	{
		Button buttonBiosReturn = (Button) findViewById(R.id.biosReturn);
		
		if (sharedVars.getbIsEng())
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
		int buttonId = iBut.getId();
		switch(buttonId){
		case 1://displays hero 1
			goToCharacter(1);
			break;
		case 2://displays hero 2
			goToCharacter(2);
			break;
		case 3://displays hero 3
			goToCharacter(3);
			break;
		case 4://displays hero 4
			goToCharacter(4);
			break;
		case 5://displays villain 1
			goToCharacter(5);
			break;
		case 6://displays villain 2
			goToCharacter(6);
			break;
		case 7://displays villain 3
			goToCharacter(7);
			break;
		case 8://displays villain 4
			goToCharacter(8);
			break;
		case 9://displays villain 5
			goToCharacter(9);
			break;
		case 10://displays villain 6
			goToCharacter(10);
			break;
		case 11://displays villain 7
			goToCharacter(11);
			break;
		case 12://displays villain 8
			goToCharacter(12);
			break;
		}
	}
	
	public void goToCharacter(int num)
	{
		isCharUnlocked(num);
		
		if(sharedVars.getIfCharUnlocked(num))
		{
			Intent viewChar;
		
			sharedVars.setCharChoice(num);
			viewChar = new Intent(bios.this, character.class);
			startActivity(viewChar);
		}
	}

	private void isCharUnlocked(final int num)
	{
		if(sharedVars.getIfCharUnlocked(num))//if chars already unlocked, end here
			return;
		else{//if char isnt unlocked
			if(sharedVars.getCredits() >= 10)//if the user has enough credits to use
			{
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				if(sharedVars.getbIsEng())
				{
					builder.setTitle(R.string.buttonCharLockedEng); 
					String message = getString(R.string.buttonCharBuyMsgOneEng)+" "+
							String.valueOf(sharedVars.getCredits())+" "+
							getString(R.string.buttonCharBuyMsgTwoEng);
					builder.setMessage(message);
				}
				else
				{
					builder.setTitle(R.string.buttonCharLockedSpn); 
					String message = getString(R.string.buttonCharBuyMsgOneSpn)+" "+
							String.valueOf(sharedVars.getCredits())+" "+
							getString(R.string.buttonCharBuyMsgTwoSpn);
					builder.setMessage(message);			
				}
				builder.setCancelable(true); 
		
				if(sharedVars.getbIsEng())
					builder.setPositiveButton(R.string.buttonCharBuyConfirmEng,
							new DialogInterface.OnClickListener()
							{ 
								public void onClick(DialogInterface dialog, int id) 
								{
									unlockCharacter(num);
								}
							}
							);
				else
					builder.setPositiveButton(R.string.buttonCharBuyConfirmSpn,
							new DialogInterface.OnClickListener()
							{     
								public void onClick(DialogInterface dialog, int id) 
								{
									unlockCharacter(num);    
								}
							}
							);
		
				AlertDialog unlockedCheck = builder.create();
				unlockedCheck.show();
			}// end of if(sharedVars.getCredits() >= 10)
			else//user does not have enough credits to make a purchase
				{
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				if(sharedVars.getbIsEng())
				{
					builder.setTitle(R.string.buttonCharLockedEng); 
					builder.setMessage(R.string.buttonCharNoCredEng);
				}
				else
				{
					builder.setTitle(R.string.buttonCharLockedSpn); 
					builder.setMessage(R.string.buttonCharNoCredSpn);			
				}
				builder.setCancelable(true); 
		
				AlertDialog lockedCheck = builder.create();
				lockedCheck.show();
				}//end of inside else
			}//end of outside else
	}
	
	public void unlockCharacter(int num)
	{
		SharedPreferences saveFile = this.getSharedPreferences("com.example.battlefordentalperfection", Context.MODE_PRIVATE);
		sharedVars.setCredits(-10, saveFile);
		sharedVars.unlockChar(num, saveFile);
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
