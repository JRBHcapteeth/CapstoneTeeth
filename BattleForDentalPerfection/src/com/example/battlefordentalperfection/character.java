package com.example.battlefordentalperfection;

import java.io.IOException;
import java.io.InputStream;
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

	//private static final String TAG = "characters";
	//private List<String> charFileNames;
	
	//calls asset functionality
	//AssetManager assets = getAssets();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.character);
		/*
				try
				{//grabs filenames from assets folder
					String[] saFileNames = assets.list("");
					for (String sNames : saFileNames) 
						charFileNames.add(sNames);
				}
				catch(IOException e)
				{//error catching if a file does not open properly
					Log.e(TAG, "Error loading image file names", e);
				}
				*/
		translate(sharedVars.bIsEng);
		
		Button buttonCharReturn = (Button) findViewById(R.id.characterReturn);
		
		buttonCharReturn.setOnClickListener(buttonCharReturnListener);
		
	}
	
	public void translate(boolean isEng)
	{
		
		Button buttonCharReturn = (Button) findViewById(R.id.characterReturn);
		
		ImageView charPic = (ImageView) findViewById(R.id.characterPicture);
		
		TextView charName = (TextView) findViewById(R.id.characterName);
		TextView charBioTitle = (TextView) findViewById(R.id.characterBioTitle);
		TextView charBio = (TextView) findViewById(R.id.characterBio);
		TextView charRepTitle = (TextView) findViewById(R.id.characterRepTitle);
		TextView charRep = (TextView) findViewById(R.id.characterRep);
		
		if (isEng)
		{
			buttonCharReturn.setText(getString(R.string.buttonReturnEng));
			switch(sharedVars.charChoice){
			case 1:
				/*
				String stream = "assets/hero1";
				Drawable portrait = Drawable.createFromPath(stream);
				charPic.setImageDrawable(portrait);*/
					
				charName.setText(getString(R.string.charHero1NameEng));
				charBioTitle.setText(getString(R.string.buttonBiosSingEng));
				charBio.setText(getString(R.string.charHero1BioEng));
				charRepTitle.setText(getString(R.string.buttonRepresentEng));
				charRep.setText(getString(R.string.charHero1RepEng));
				break;
			case 2:
				/*
				String stream = "assets/hero1";
				Drawable portrait = Drawable.createFromPath(stream);
				charPic.setImageDrawable(portrait);*/
					
				charName.setText(getString(R.string.charHero2NameEng));
				charBioTitle.setText(getString(R.string.buttonBiosSingEng));
				charBio.setText(getString(R.string.charHero2BioEng));
				charRepTitle.setText(getString(R.string.buttonRepresentEng));
				charRep.setText(getString(R.string.charHero2RepEng));
				break;
			}
		}
		else
		{
			buttonCharReturn.setText(getString(R.string.buttonReturnEng));
			switch(sharedVars.charChoice){
			case 1:
				charName.setText(getString(R.string.charHero1NameSpn));
				charBioTitle.setText(getString(R.string.buttonBiosSpn));
				charBio.setText(getString(R.string.charHero1BioSpn));
				charRepTitle.setText(getString(R.string.buttonRepresentSpn));
				charRep.setText(getString(R.string.charHero1RepSpn));
				break;
			case 2:
				charName.setText(getString(R.string.charHero2NameSpn));
				charBioTitle.setText(getString(R.string.buttonBiosSpn));
				charBio.setText(getString(R.string.charHero2BioSpn));
				charRepTitle.setText(getString(R.string.buttonRepresentSpn));
				charRep.setText(getString(R.string.charHero2RepSpn));
				break;
			}	
		}
	};
	
	public OnClickListener buttonCharReturnListener = new OnClickListener() 
	{
		public void onClick(View v)
		{
			Intent returnBios = 
					new Intent(character.this, bios.class);
			startActivity(returnBios);
		}
	};
	
}
