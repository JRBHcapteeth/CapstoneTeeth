package com.example.battlefordentalperfection;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class myAlarmReceiver extends BroadcastReceiver { 
     @Override
     public void onReceive(Context context, Intent intent) {
         Toast.makeText(context, "Alarm went off", Toast.LENGTH_SHORT).show();
     }
}