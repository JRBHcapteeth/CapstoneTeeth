package com.example.battlefordentalperfection;

<<<<<<< HEAD
import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
=======
>>>>>>> origin/master
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

<<<<<<< HEAD
public class reminder extends Activity {
    private TimePicker tpFirst = null; 
    private TimePicker tpSecond = null;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    int hour;
    int minute;
    
    
    @Override 
=======
public class reminder extends ActionBarActivity {

>>>>>>> origin/master
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.reminder_home);
      
        Button btnNext = (Button)findViewById(R.id.btnBack);
        Button btnTog = (Button) findViewById(R.id.tglRem);
        Button btnSet = (Button) findViewById(R.id.btnSubmit);
        alarmMgr = (AlarmManager)getSystemService(ALARM_SERVICE);
    	Intent intent = new Intent(this, myAlarmReceiver.class);
    	alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
         
        //setCurrentTimeOnView();
        btnNext.setOnClickListener(buttonBackListener);
        btnSet.setOnClickListener(buttonSetListener);
        
        
           
            
    
    }
       

<<<<<<< HEAD
        
=======
       
       	//TimePicker tpFirst = (TimePicker) findViewById(R.id.tpFirst);
        //TimePicker tpSecond = (TimePicker) findViewById(R.id.tpSecond);
>>>>>>> origin/master

        public OnClickListener buttonSetListener = new OnClickListener() 
    	{
    		public void onClick(View v)
<<<<<<< HEAD
    		{
    			
    			
    			tpFirst = (TimePicker) findViewById(R.id.tpFirst);
    			int minute;
    			int hour;
    			long timeMilli;
    			minute = tpFirst.getCurrentMinute();
    			hour = tpFirst.getCurrentHour();
    			
    			
               // Toast.makeText(reminder.this, "User has selected " + hour + " " + minute, Toast.LENGTH_LONG).show();
=======
    		{/*
    			String strDateTime =" "+ tpFirst.getCurrentHour() + ":" + tpFirst.getCurrentMinute();
>>>>>>> origin/master

             // Set the alarm to start at the users choice
                Calendar calendar = Calendar.getInstance();
                //timeMilli = calendar.getTimeInMillis();
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                timeMilli = calendar.getTimeInMillis();

<<<<<<< HEAD
                // With setInexactRepeating(), you have to use one of the AlarmManager interval
                // constants--in this case, AlarmManager.INTERVAL_DAY.
                alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, timeMilli,
                		                     AlarmManager.INTERVAL_DAY, alarmIntent);
                
=======
                finish();*/
>>>>>>> origin/master
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

