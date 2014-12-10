package com.example.battlefordentalperfection;

import java.util.Calendar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class timer extends ActionBarActivity {

 private Button startButton;
 private Button pauseButton;
 
 private TextView timerValue;
 
 private long startTime = 0L;
 
 private Handler customHandler = new Handler();
 
 long timeInMilliseconds = 0L;
 long timeSwapBuff = 0L;
 long updatedTime = 0L;
 
 private ImageView yellowTeeth;
    private ImageView whiteTeeth;
    private int mShortAnimationDuration;
    int whiteVis = 0;
 int yellowVis = 1;

 @Override
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.timer);
  
        //set all of the values to their respective object
  timerValue = (TextView) findViewById(R.id.timerValue);
  
  whiteTeeth = (ImageView) findViewById(R.id.imageView2);
  yellowTeeth = (ImageView)findViewById(R.id.imageView1);

  //make sure only the yellow teeth are showing
  yellowTeeth.setAlpha(1f);

  startButton = (Button) findViewById(R.id.startButton);
   translate();
  //set the listener for the start button
  startButton.setOnClickListener(new View.OnClickListener() {
   
	 
   public void onClick(View view) {
          mShortAnimationDuration = getResources().getInteger(android.R.integer.config_longAnimTime);

    SharedPreferences saveFile = timer.this.getSharedPreferences("com.example.battlefordentalperfection", Context.MODE_PRIVATE);
    startTime = SystemClock.uptimeMillis();
    Calendar cal = Calendar.getInstance();
          cal.setTimeInMillis(System.currentTimeMillis());
          sharedVars.setTimer(cal, saveFile);
    startButton.setClickable(false);//if the start button is pressed
                                    //make sure it cant be pressed again
    pauseButton.setClickable(true);
    //whiteTeeth.animate()
    //.alpha(1f)
    //.setDuration(120000)
    //.setListener(null);
     
    yellowTeeth.animate()//animation for the yellow teeth
    .alpha(0f)
    .setDuration(120000)
    .setListener(new AnimatorListenerAdapter(){
    @Override
                  public void onAnimationEnd(Animator animation) {
                 yellowTeeth.setVisibility(View.GONE);//when the animation is done
                                                      //make sure the yellow teeth are gone
    }
             });
    customHandler.postDelayed(updateTimerThread, 0);

   }
  });

  pauseButton = (Button) findViewById(R.id.pauseButton);
  pauseButton.setText("Stop");
  //set the listener for the stop button
  pauseButton.setOnClickListener(new View.OnClickListener() {
  
   public void onClick(View view) {
   
    timeSwapBuff += timeInMilliseconds;
    startButton.setClickable(false);
    pauseButton.setClickable(false); // make sure you cant spam the timer
    //whiteTeeth.animate().cancel();
    yellowTeeth.animate().cancel();
    customHandler.removeCallbacks(updateTimerThread);

   }
  });

 }
//if bIsEng is true, text is English. Otherwise, it's Spanish
		public void translate()
		{
			Button startButton = (Button) findViewById(R.id.startButton);
			Button pauseButton = (Button) findViewById(R.id.pauseButton);
			
			if (sharedVars.getbIsEng())
			{
				startButton.setText(getString(R.string.startButtonLabelEng));
				pauseButton.setText(getString(R.string.pauseButtonLabelEng));
			}
			else
			{
				startButton.setText(getString(R.string.startButtonLabelSpn));
				pauseButton.setText(getString(R.string.pauseButtonLabelSpn));
			}
		};
 private Runnable updateTimerThread = new Runnable() {

  public void run() {
   
   timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
   
   updatedTime = timeSwapBuff + timeInMilliseconds;

   int secs = (int) (updatedTime / 1000);
   int mins = secs / 60;
   
   secs = secs % 60;
   
   int milliseconds = (int) (updatedTime % 1000);
   timerValue.setText("" + mins + ":"
     + String.format("%02d", secs) + ":"
     + String.format("%03d", milliseconds));
   if(mins == 2 && secs == 0 && milliseconds == 0) //when two minutes has been reached
   {
    Thread.currentThread().interrupt();
    //whiteTeeth.animate().cancel();
    yellowTeeth.animate().cancel();
    return;
   }
   if(whiteTeeth.getAlpha() == .9 )//make sure the white teeth dont dissapear
   {
    whiteTeeth.animate().cancel();
   }
   
   
   
   customHandler.postDelayed(this, 0);//go through the thread again
  }

 };

}