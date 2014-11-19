package com.example.battlefordentalperfection;

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

public class reminder extends ActionBarActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.reminder_home);
        setTitle("Reminder");

        Button btnNext = (Button) findViewById(R.id.btnBack);
        Button btnSet = (Button) findViewById(R.id.btnSubmit);
        btnNext.setOnClickListener(buttonBackListener);
        btnSet.setOnClickListener(buttonSetListener);
        
        }

       
       	//TimePicker tpFirst = (TimePicker) findViewById(R.id.tpFirst);
        //TimePicker tpSecond = (TimePicker) findViewById(R.id.tpSecond);

        public OnClickListener buttonSetListener = new OnClickListener() 
    	{
    		public void onClick(View v)
    		{/*
    			String strDateTime =" "+ tpFirst.getCurrentHour() + ":" + tpFirst.getCurrentMinute();

                Toast.makeText(reminder.this, "User has selected " + strDateTime, Toast.LENGTH_LONG).show();

                finish();*/
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

