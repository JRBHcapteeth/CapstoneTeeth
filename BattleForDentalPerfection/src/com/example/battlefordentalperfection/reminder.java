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

    private TimePicker tpFirst = null; 
    private TimePicker tpSecond = null;
    //private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    int hour;
    int minute;
    
    
    @Override 
public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.reminder_home);
        setTitle("Reminder");
        
        Button btnNext = (Button) findViewById(R.id.btnBack);
        Button btnSet = (Button) findViewById(R.id.btnSubmit);
        
        Button btnToggle = (Button) findViewById(R.id.turnOff);
        
        btnNext.setOnClickListener(buttonBackListener);
        
        btnSet.setOnClickListener(buttonSetListener);
        btnToggle.setOnClickListener(toggleButtonListener);
        
        }



        public OnClickListener buttonSetListener = new OnClickListener() 
    	{
    		public void onClick(View v)
    		{
    			Calendar calendar1 = Calendar.getInstance();
    			Calendar calendar2 = Calendar.getInstance();
    			tpFirst = (TimePicker) findViewById(R.id.tpFirst);
    			//tpSecond = (TimePicker) findViewById(R.id.tpSecond);
    			
    			int minute1;
    			int minute2;
    			int hour1;
    			int hour2;
    			
    			minute1 = tpFirst.getCurrentMinute();
    			hour1 = tpFirst.getCurrentHour();
    			
    			//minute2 = tpSecond.getCurrentMinute();
    			//hour2 = tpSecond.getCurrentHour();
    			
    			calendar1.set(Calendar.HOUR_OF_DAY, hour1);
    			calendar1.set(Calendar.MINUTE, minute1);
    			calendar1.set(Calendar.SECOND, 0);
    			calendar1.set(Calendar.MILLISECOND, 0);
    			
    			//calendar2.set(Calendar.HOUR_OF_DAY, hour2);
    			//calendar2.set(Calendar.MINUTE, minute2);
    			//calendar2.set(Calendar.SECOND, 0);
    			//calendar2.set(Calendar.MILLISECOND, 0);
    			
    			
    			alarmIntent = PendingIntent.getBroadcast(reminder.this, 1234567, new Intent(reminder.this, myAlarmReceiver.class), 0);
    			AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);                  
    			long updateFreq = 86400000/2;//24*60*60*1000;
    			alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar1.getTimeInMillis(), updateFreq, alarmIntent);
    			
    			//alarmIntent = PendingIntent.getBroadcast(reminder.this, 1234567, new Intent(reminder.this, myAlarmReceiver.class), 0);
    			//AlarmManager alarmManager2 = (AlarmManager)getSystemService(ALARM_SERVICE);                  
    		    //updateFreq = 86400000;//24*60*60*1000;
    			//alarmManager2.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(), updateFreq, alarmIntent);
    			
    		}
    			
            
    	}; 
    	
    	public OnClickListener toggleButtonListener = new OnClickListener()
    	{
    		public void onClick(View v)
    		{
    		Intent intent = new Intent(reminder.this, myAlarmReceiver.class);
    		  PendingIntent sender = PendingIntent.getBroadcast(reminder.this, 0, intent, 0);
    		        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    		        alarmManager.cancel(sender);
    		}
    	};
    	
    	public OnClickListener buttonBackListener = new OnClickListener() 
    	{
    		public void onClick(View v)
    		{
    			Intent startBios = 
    					new Intent(reminder.this, MainActivity.class);
    			startActivity(startBios);
    		}
    	};

    }       

//pendingIntent = PendingIntent.getBroadcast(SettingsActivity.this, 1234567, new Intent(SettingsActivity.this, WeeklyReminder.class), 0);
//AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);                  
//Calendar calendar = Calendar.getInstance();       
//calendar.setTimeInMillis(System.currentTimeMillis());
//calendar.add(Calendar.SECOND, 30);
//long updateFreq = 30*1000;//24*60*60*1000;
//alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), updateFreq, pendingIntent);