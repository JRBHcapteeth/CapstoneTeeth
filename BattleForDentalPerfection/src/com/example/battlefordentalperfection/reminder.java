package com.example.battlefordentalperfection;


import java.util.Calendar;
import android.app.Activity;
import android.app.AlarmManager;
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


public class reminder extends Activity {

    private TimePicker tpFirst = null; 
    private TimePicker tpSecond = null;
    private AlarmManager alarmMgr;
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
        alarmMgr = (AlarmManager)getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, myAlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        btnNext.setOnClickListener(buttonBackListener);
        btnSet.setOnClickListener(buttonSetListener);
        
        }



        public OnClickListener buttonSetListener = new OnClickListener() 
    	{
    		public void onClick(View v)
    		{
    			Calendar calendar = Calendar.getInstance();
    			//calendar.setTimeInMillis(System.currentTimeMillis());
    			

    			
    			
    			tpFirst = (TimePicker) findViewById(R.id.tpFirst);
    			int minute;
    			int hour;
    			long timeMilli;
    			minute = tpFirst.getCurrentMinute();
    			hour = tpFirst.getCurrentHour();
    			calendar.set(Calendar.HOUR_OF_DAY, hour);
    			calendar.set(Calendar.MINUTE, minute);
    			
    			// With setInexactRepeating(), you have to use one of the AlarmManager interval
    			// constants--in this case, AlarmManager.INTERVAL_DAY.
    			alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
    			        AlarmManager.INTERVAL_DAY, alarmIntent);
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

