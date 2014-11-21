package com.example.battlefordentalperfection;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class timer extends Activity {

	private Button startButton;
	private Button pauseButton;
	
	private TextView timerValue;
	
	private long startTime = 0L;
	
	private Handler customHandler = new Handler();
	
	long timeInMilliseconds = 0L;
	long timeSwapBuff = 0L;
	long updatedTime = 0L;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timer);

		timerValue = (TextView) findViewById(R.id.timerValue);

		startButton = (Button) findViewById(R.id.startButton);
		
		startButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View view) {
				startTime = SystemClock.uptimeMillis();
				startButton.setClickable(false);
				pauseButton.setClickable(true);
				customHandler.postDelayed(updateTimerThread, 0);

			}
		});

		pauseButton = (Button) findViewById(R.id.pauseButton);
		
		pauseButton.setOnClickListener(new View.OnClickListener() {
		
			public void onClick(View view) {
			
				timeSwapBuff += timeInMilliseconds;
				startButton.setClickable(true);
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
			if(mins == 2 && secs == 0)
			{
				Thread.currentThread().interrupt();
				return;
			}
			secs = secs % 60;
			int milliseconds = (int) (updatedTime % 1000);
			timerValue.setText("" + mins + ":"
					+ String.format("%02d", secs) + ":"
					+ String.format("%03d", milliseconds));
			customHandler.postDelayed(this, 0);
		}

	};

}