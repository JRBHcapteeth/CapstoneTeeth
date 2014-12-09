package com.example.battlefordentalperfection;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class character extends ActionBarActivity{

	private static final String TAG = "character";
	
	private List<String> charPicList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.character);

		charPicList = new ArrayList<String>();
		makeList();
		translate();
		
		Button buttonCharReturn = (Button) findViewById(R.id.characterReturn);
		buttonCharReturn.setOnClickListener(buttonCharReturnListener);
		
	}
	
	public void makeList()
	{//calls asset functionality
		AssetManager assets = getAssets();
		charPicList.clear();//clears list
		
		try
		{//grabs filenames from assets folder
			String[] saFileNames = assets.list("body");
			for (String sNames : saFileNames) 
					charPicList.add(sNames);
		}
		catch(IOException e)
		{//error catching if a file does not open properly
			Log.e(TAG, "Error loading image file names", e);
		}
	}
	
	public void translate()
	{
		//changes the return to bios button
		Button buttonCharReturn = (Button) findViewById(R.id.characterReturn);
		if (sharedVars.getbIsEng())
			buttonCharReturn.setText(getString(R.string.buttonReturnEng));
		else
			buttonCharReturn.setText(getString(R.string.buttonReturnSpn));
		
		//profiles contain the references to the full name, bio, and representation of each character for eng and spn.
		Profile heroOne = new Profile("hero1body.png",(getString(R.string.charHero1NameEng)),(getString(R.string.charHero1BioEng)), 
				(getString(R.string.charHero1RepEng)),(getString(R.string.charHero1NameSpn)), 
				(getString(R.string.charHero1BioSpn)), (getString(R.string.charHero1RepSpn)));
		Profile heroTwo = new Profile("hero2body.png",(getString(R.string.charHero2NameEng)),(getString(R.string.charHero2BioEng)), 
				(getString(R.string.charHero2RepEng)),(getString(R.string.charHero2NameSpn)), 
				(getString(R.string.charHero2BioSpn)), (getString(R.string.charHero2RepSpn)));
		Profile heroThree = new Profile("hero3body.png",(getString(R.string.charHero3NameEng)),(getString(R.string.charHero3BioEng)), 
				(getString(R.string.charHero3RepEng)),(getString(R.string.charHero3NameSpn)), 
				(getString(R.string.charHero3BioSpn)), (getString(R.string.charHero3RepSpn)));
		Profile heroFour = new Profile("hero4body.png",(getString(R.string.charHero4NameEng)),(getString(R.string.charHero4BioEng)), 
				(getString(R.string.charHero4RepEng)),(getString(R.string.charHero4NameSpn)), 
				(getString(R.string.charHero4BioSpn)), (getString(R.string.charHero4RepSpn)));
		
		Profile villainOne = new Profile("vill1body.png",(getString(R.string.charVillain1NameEng)),(getString(R.string.charVillain1BioEng)), 
				(getString(R.string.charVillain1RepEng)),(getString(R.string.charVillain1NameSpn)), 
				(getString(R.string.charVillain1BioSpn)), (getString(R.string.charVillain1RepSpn)));
		Profile villainTwo = new Profile("vill2body.png",(getString(R.string.charVillain2NameEng)),(getString(R.string.charVillain2BioEng)), 
				(getString(R.string.charVillain2RepEng)),(getString(R.string.charVillain2NameSpn)), 
				(getString(R.string.charVillain2BioSpn)), (getString(R.string.charVillain2RepSpn)));
		Profile villainThree = new Profile("vill3body.png",(getString(R.string.charVillain3NameEng)),(getString(R.string.charVillain3BioEng)), 
				(getString(R.string.charVillain3RepEng)),(getString(R.string.charVillain3NameSpn)), 
				(getString(R.string.charVillain3BioSpn)), (getString(R.string.charVillain3RepSpn)));
		Profile villainFour = new Profile("vill4body.png",(getString(R.string.charVillain4NameEng)),(getString(R.string.charVillain4BioEng)), 
				(getString(R.string.charVillain4RepEng)),(getString(R.string.charVillain4NameSpn)), 
				(getString(R.string.charVillain4BioSpn)), (getString(R.string.charVillain4RepSpn)));
		Profile villainFive = new Profile("vill5body.png",(getString(R.string.charVillain5NameEng)),(getString(R.string.charVillain5BioEng)), 
				(getString(R.string.charVillain5RepEng)),(getString(R.string.charVillain5NameSpn)), 
				(getString(R.string.charVillain5BioSpn)), (getString(R.string.charVillain5RepSpn)));
		Profile villainSix = new Profile("vill6body.png",(getString(R.string.charVillain6NameEng)),(getString(R.string.charVillain6BioEng)), 
				(getString(R.string.charVillain6RepEng)),(getString(R.string.charVillain6NameSpn)), 
				(getString(R.string.charVillain6BioSpn)), (getString(R.string.charVillain6RepSpn)));
		Profile villainSeven = new Profile("vill7body.png",(getString(R.string.charVillain7NameEng)),(getString(R.string.charVillain7BioEng)), 
				(getString(R.string.charVillain7RepEng)),(getString(R.string.charVillain7NameSpn)), 
				(getString(R.string.charVillain7BioSpn)), (getString(R.string.charVillain7RepSpn)));
		Profile villainEight = new Profile("vill8body.png",(getString(R.string.charVillain8NameEng)),(getString(R.string.charVillain8BioEng)), 
				(getString(R.string.charVillain8RepEng)),(getString(R.string.charVillain8NameSpn)), 
				(getString(R.string.charVillain8BioSpn)), (getString(R.string.charVillain8RepSpn)));
		
		//depending on the profile selected, different information is brought up
		switch(sharedVars.getCharChoice()){
		case 1: setInfo(heroOne); break;
		case 2: setInfo(heroTwo); break;
		case 3: setInfo(heroThree); break;
		case 4: setInfo(heroFour); break;
		case 5: setInfo(villainOne); break;
		case 6: setInfo(villainTwo); break;
		case 7: setInfo(villainThree); break;
		case 8: setInfo(villainFour); break;
		case 9: setInfo(villainFive); break;
		case 10: setInfo(villainSix); break;
		case 11: setInfo(villainSeven); break;
		case 12: setInfo(villainEight); break;
		}
	};
	
	private void setInfo(Profile profile)
	{
		//text boxes that will hold information
		ImageView charPic = (ImageView) findViewById(R.id.characterPicture);
		TextView charName = (TextView) findViewById(R.id.characterName);
		TextView charBioTitle = (TextView) findViewById(R.id.characterBioTitle);
		TextView charBio = (TextView) findViewById(R.id.characterBio);
		TextView charRepTitle = (TextView) findViewById(R.id.characterRepTitle);
		TextView charRep = (TextView) findViewById(R.id.characterRep);
				
		AssetManager assets = getAssets();
		InputStream stream; 
		
		try
		{
			stream = assets.open("body/"+profile.getPath());
			Drawable card = Drawable.createFromStream(stream, profile.getPath());
			charPic.setImageDrawable(card);
		}
		catch (IOException e)  
	    {
	        Log.e(TAG, "Error loading " + profile.getPath(), e);
	    }
		
		//populates text boxes depending on language selected
		if (sharedVars.getbIsEng())
		{
			charName.setText(profile.getNameEng());
			charBioTitle.setText(getString(R.string.buttonBiosSingEng));
			charBio.setText(profile.getBioEng());
			charRepTitle.setText(getString(R.string.buttonRepresentEng));
			charRep.setText(profile.getRepEng());
		}
		else
		{
			charName.setText(profile.getNameSpn());
			charBioTitle.setText(getString(R.string.buttonBiosSpn));
			charBio.setText(profile.getBioSpn());
			charRepTitle.setText(getString(R.string.buttonRepresentSpn));
			charRep.setText(profile.getRepSpn());
		}
			
	}
	
	public OnClickListener buttonCharReturnListener = new OnClickListener() 
	{
		public void onClick(View v)
		{
			System.gc();
			Intent returnBios = 
					new Intent(character.this, bios.class);
			startActivity(returnBios);
		}
	};
	
}
