package com.example.battlefordentalperfection;

import java.util.Calendar;

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
		

		timerValue = (TextView) findViewById(R.id.timerValue);
		
		yellowTeeth = (ImageView) findViewById(R.id.imageView1);
		whiteTeeth = (ImageView)findViewById(R.id.imageView2);
		
		whiteTeeth.setAlpha((float)(0));

		startButton = (Button) findViewById(R.id.startButton);
		
		startButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View view) {
				SharedPreferences saveFile = timer.this.getSharedPreferences("com.example.battlefordentalperfection", Context.MODE_PRIVATE);
				startTime = SystemClock.uptimeMillis();
				Calendar cal = Calendar.getInstance();
		        cal.setTimeInMillis(System.currentTimeMillis());
		        sharedVars.setTimer(cal, saveFile);
				startButton.setClickable(false);
				pauseButton.setClickable(true);
				customHandler.postDelayed(updateTimerThread, 0);

			}
		});

		pauseButton = (Button) findViewById(R.id.pauseButton);
		pauseButton.setText("Stop");
		pauseButton.setOnClickListener(new View.OnClickListener() {
		
			public void onClick(View view) {
			
				timeSwapBuff += timeInMilliseconds;
				startButton.setClickable(false);
				pauseButton.setClickable(false);
				customHandler.removeCallbacks(updateTimerThread);

			}
		});

	}

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
			if(mins == 2 && secs == 0)
			{
				Thread.currentThread().interrupt();
				return;
			}
			yellowTeeth.setAlpha((float) (whiteVis + .00000000000001));
			whiteTeeth.setAlpha((float)(yellowVis-.0000000000001));
			
			
			customHandler.postDelayed(this, 0);
		}

	};

}