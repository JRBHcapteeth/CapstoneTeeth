package com.example.battlefordentalperfection;


import java.util.Calendar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TimePicker;


public class reminder extends Activity {

    private TimePicker tpFirst = null; //set variables
    //private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    int hour;
    int minute;
    Button btnNext;
    Button btnSet;
    
    
    @Override 
public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.reminder_home);
        setTitle("Reminder");
        //set the buttons
         btnNext = (Button) findViewById(R.id.btnBack);
         btnSet = (Button) findViewById(R.id.btnSubmit);
        
        Button btnToggle = (Button) findViewById(R.id.turnOff);
        
        //set the listeners
        btnNext.setOnClickListener(buttonBackListener);
         btnSet.setOnClickListener(buttonSetListener);
        btnToggle.setOnClickListener(toggleButtonListener);
        
        }



        public OnClickListener buttonSetListener = new OnClickListener() 
    	{
    		public void onClick(View v)
    		{  //get the current time the timepicker has
    			Calendar calendar1 = Calendar.getInstance();
    			tpFirst = (TimePicker) findViewById(R.id.tpFirst);
    			btnSet.setClickable(false);
    			int minute1;		
    			int hour1;
    			
    			minute1 = tpFirst.getCurrentMinute();
    			hour1 = tpFirst.getCurrentHour();
    			//set the calander variable to the time picker time
    			calendar1.set(Calendar.HOUR_OF_DAY, hour1);
    			calendar1.set(Calendar.MINUTE, minute1);
    			calendar1.set(Calendar.SECOND, 0);
    			calendar1.set(Calendar.MILLISECOND, 0);
    			
    			//set the alarm for the time chosen
    			alarmIntent = PendingIntent.getBroadcast(reminder.this, 1234567, new Intent(reminder.this, myAlarmReceiver.class), 0);
    			AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);                  
    			long updateFreq = 86400000/2;//24*60*60*1000;
    			alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar1.getTimeInMillis(), updateFreq, alarmIntent);
    			
    			
    		}
    			
            
    	}; 
    	
    	public OnClickListener toggleButtonListener = new OnClickListener()
    	{
    		public void onClick(View v)
    		{
    			//cancel the alarm that has been set
    		Intent intent = new Intent(reminder.this, myAlarmReceiver.class);
    		  PendingIntent sender = PendingIntent.getBroadcast(reminder.this, 0, intent, 0);
    		        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    		        alarmManager.cancel(sender);
    		}
    	};
    	
    	public OnClickListener buttonBackListener = new OnClickListener() 
    	{
    		public void onClick(View v)
    		{//go back to the main menu
    			Intent startBios = 
    					new Intent(reminder.this, MainActivity.class);
    			startActivity(startBios);
    		}
    	};

    }       