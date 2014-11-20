package com.example.battlefordentalperfection;

<<<<<<< HEAD
<<<<<<< HEAD
import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
=======
>>>>>>> origin/master
=======
import android.app.Activity;
>>>>>>> parent of f0fb393... update to timer and reminder
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
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
<<<<<<< HEAD
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
=======

>>>>>>> parent of f0fb393... update to timer and reminder
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.reminder_home);
        setTitle("Reminder");

        Button btnNext = (Button) findViewById(R.id.btnBack);
        Button btnSet = (Button) findViewById(R.id.btnSubmit);
        btnNext.setOnClickListener(buttonBackListener);
        btnSet.setOnClickListener(buttonSetListener);
        
        }

<<<<<<< HEAD
        
<<<<<<< HEAD
=======
       
       	//TimePicker tpFirst = (TimePicker) findViewById(R.id.tpFirst);
        //TimePicker tpSecond = (TimePicker) findViewById(R.id.tpSecond);
>>>>>>> origin/master
=======
        final   TimePicker tpFirst = (TimePicker) findViewById(R.id.tpFirst);
        final   TimePicker tpSecond = (TimePicker) findViewById(R.id.tpSecond);
>>>>>>> parent of f0fb393... update to timer and reminder

        public OnClickListener buttonSetListener = new OnClickListener() 
    	{
    		public void onClick(View v)
<<<<<<< HEAD
    		{
<<<<<<< HEAD
    			
    			
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
=======
    			String strDateTime =" "+ tpFirst.getCurrentHour() + ":" + tpFirst.getCurrentMinute();
>>>>>>> parent of f0fb393... update to timer and reminder

                Toast.makeText(reminder.this, "User has selected " + strDateTime, Toast.LENGTH_LONG).show();

<<<<<<< HEAD
<<<<<<< HEAD
                // With setInexactRepeating(), you have to use one of the AlarmManager interval
                // constants--in this case, AlarmManager.INTERVAL_DAY.
                alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, timeMilli,
                		                     AlarmManager.INTERVAL_DAY, alarmIntent);
                
=======
                finish();*/
>>>>>>> origin/master
=======
                finish();
>>>>>>> parent of f0fb393... update to timer and reminder
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

