package com.example.battlefordentalperfection;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;

public class gameBase extends ActionBarActivity{
	
	private game gameView;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState); // call super's onCreate method
		setContentView(R.layout.game); // inflate the layout

		// get the View
		gameView = (game) findViewById(R.id.ViewGame);
	} // end method onCreate
	
	@Override
	public void onPause()
	{
		super.onPause(); // call the super method
		gameView.stopGame(); // terminates the game
	} // end method onPause
	   
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		// get int representing the type of action which caused this event
		int action = event.getAction();

		// the user user touched the screen or dragged along the screen
		if (action == MotionEvent.ACTION_DOWN)
		{
			gameView.resetPlayerMath(event);
		} // end if
		
		return true;
	} // end method onTouchEvent
	
}
