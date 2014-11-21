package com.example.battlefordentalperfection;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class game extends SurfaceView implements SurfaceHolder.Callback{
		
	private GameThread gameThread; // controls the game loop
	private Activity activity;
	private Random rand;
	   
	private int score; //obstacles cleared
	private int hp; //amount of hits player can take
	private int overdose; //progress of overdose meter
	private int timeBuffer;
	private int playerMathXVal; //current position along the curve y = -x^2 from -2 to 2.
	private int screenWidth;
	private int screenHeight;
	private int hostileWidth;
	private int hostileHeightLowest;
	private int hostileHeightMidLow;
	private int hostileHeightMiddle;
	private int hostileHeightMidHigh;
	private int hostileHeightHighest;
	private int currentCheck = 999;
	
	private boolean gameover = true; //true if game done, false if running
	private boolean playerHasTapped = false;
	private boolean isHit = false;
	
	private final double gravity = 10; //constant downwards vector
	private final double piDivFour = Math.PI/4; //constant downwards vector
	private final double gracePeriod = 1.5;
	private double gotHitTime;
	private double delta;//variable upwards vector
	private double totalPassedTimeMS;//for threaded game loop
	private double elapsedTimeInSec;//for use in making events
	private double radius;//for use in making events
	private double timeInterval; //used in gamethread
	
	private Bitmap junkOne, junkTwo, junkThree, junkFour, junkFive, junkSix;
	private Bitmap foodOne, foodTwo, foodThree, foodFour;
	private Bitmap dentalOne, dentalTwo;
	
	private ArrayList<friendObj> friendObjArr = new ArrayList<friendObj>(); //pos 0 = player, others are dental/food products
	private ArrayList<hostileObj> hostileObjArr = new ArrayList<hostileObj>(); //contains the positions for hostile entities
	
	private Paint backgroundPaint; // Paint used to clear the drawing area
	private Paint testPaint;
	private Paint textPaint;

	
	public game(Context context, AttributeSet atrbs)
	{
		super(context, atrbs);
		activity = (Activity) context; 
		
		getHolder().addCallback(this); 
		
		score = 0;
		overdose = 0;
		gameover = false;
		timeBuffer = 0;
		
		totalPassedTimeMS = 0.0;
		elapsedTimeInSec = 0.0;
		
		switch(sharedVars.getDifficulty()){
		case 0: hp = 3; break;
		case 1: hp = 2; break;
		case 2: hp = 1; break;	
		default: hp = 3; break;
		}
		
		rand = new Random();
		friendObjArr.clear();
		hostileObjArr.clear();
		
		friendObjArr.add(0, new friendObj(screenWidth / 2, screenHeight / 4, 15, 0, true, false));
		friendObjArr.add(1, new friendObj(0, 0, 15, 0, false, false));
		friendObjArr.add(2, new friendObj(0, 0, 15, 0, false, false));

		hostileObjArr.add(0, new hostileObj(0, 0, 0, 0, 0, 0, 0, false, false, false));
		hostileObjArr.add(1, new hostileObj(0, 0, 0, 0, 0, 0, 0, false, false, false));
		hostileObjArr.add(2, new hostileObj(0, 0, 0, 0, 0, 0, 0, false, false, false));
		hostileObjArr.add(3, new hostileObj(0, 0, 0, 0, 0, 0, 0, false, false, false));
		hostileObjArr.add(4, new hostileObj(0, 0, 0, 0, 0, 0, 0, false, false, false));
		
        junkOne = BitmapFactory.decodeResource(getResources(), R.drawable.vill1);
        junkTwo = BitmapFactory.decodeResource(getResources(), R.drawable.vill2);
        junkThree = BitmapFactory.decodeResource(getResources(), R.drawable.vill3);
        junkFour = BitmapFactory.decodeResource(getResources(), R.drawable.vill4);
        junkFive = BitmapFactory.decodeResource(getResources(), R.drawable.vill5);
        junkSix = BitmapFactory.decodeResource(getResources(), R.drawable.vill6);
        
        foodOne = BitmapFactory.decodeResource(getResources(), R.drawable.hero1);
        foodTwo = BitmapFactory.decodeResource(getResources(), R.drawable.hero1);
        foodThree = BitmapFactory.decodeResource(getResources(), R.drawable.hero1);
        foodFour = BitmapFactory.decodeResource(getResources(), R.drawable.hero1);

        dentalOne = BitmapFactory.decodeResource(getResources(), R.drawable.hero2);
        dentalTwo = BitmapFactory.decodeResource(getResources(), R.drawable.hero2);

		backgroundPaint = new Paint(); // Paint for drawing the target
	    testPaint = new Paint(); // Paint for drawing
	    textPaint = new Paint();
		
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{
		super.onSizeChanged(w, h, oldw, oldh);

		screenWidth = w;
		screenHeight = h;

		hostileWidth = screenHeight/8;
		hostileHeightLowest = screenWidth/6;
		hostileHeightMidLow = screenWidth*2/6;
		hostileHeightMiddle = screenWidth/2;
		hostileHeightMidHigh = screenWidth*4/6;
		hostileHeightHighest = screenWidth*5/6;
	      
		radius = ((screenHeight+screenWidth)/2)/50;

		playerMathXVal = screenWidth/2;
		
		textPaint.setTextSize(w / 20); // text size 1/20 of screen width
		textPaint.setAntiAlias(true); // smoothes the text
		textPaint.setColor(Color.CYAN);
	      
		backgroundPaint.setColor(Color.WHITE); // set background color
		
		hostileObjArr.get(0).setPosYMin(screenHeight-hostileWidth);
		hostileObjArr.get(0).setPosYMax(screenHeight);
		hostileObjArr.get(0).setPosXMaxTop(screenWidth);
		hostileObjArr.get(0).setPosXMinBot(0);
		hostileObjArr.get(0).setPosXMinTop(hostileHeightHighest);
		hostileObjArr.get(0).setPosXMaxBot(hostileHeightLowest);
		hostileObjArr.get(0).setImageRef(1);
		hostileObjArr.get(0).setIsActive(true);
		
	} // end method onSizeChanged
	
	// stops the game
	public void stopGame()
	{
		if (gameThread != null)
			gameThread.setRunning(false);
		activity.finish();
	} // end method stopGame
	
	public void resetPlayerMath(MotionEvent event)
	{
		//playerMathXVal = friendObjArr.get(0).getPosX();
		delta = screenWidth / 300;
		friendObjArr.get(0).setPosX(playerMathXVal);
		playerHasTapped = true;
	}
	
    public void updateScreen(double timeInMS)
    {
    	isHit = false;
        elapsedTimeInSec += (timeInMS / 1000.0); // convert to seconds
        
        if(hp <=0)
    		gameOverDialog();        	

        /** --------------- **/
        /** PLAYER MOVEMENT **/
        /** --------------- **/
        
    	if(playerMathXVal >= 0 && playerMathXVal <= screenWidth)
    	{
    		playerMathXVal += (delta+gravity);//piDivFour
    		delta -= 0.5;
    		friendObjArr.get(0).setPlayerCol12(playerMathXVal+radius, friendObjArr.get(0).getPosY());
    		friendObjArr.get(0).setPlayerCol130(playerMathXVal+piDivFour, friendObjArr.get(0).getPosY()+piDivFour);
    		friendObjArr.get(0).setPlayerCol3(playerMathXVal, friendObjArr.get(0).getPosY()+radius);
    		friendObjArr.get(0).setPlayerCol430(playerMathXVal-piDivFour, friendObjArr.get(0).getPosY()+piDivFour);
    		friendObjArr.get(0).setPlayerCol6(playerMathXVal-radius, friendObjArr.get(0).getPosY());
    		friendObjArr.get(0).setPlayerCol730(playerMathXVal-piDivFour, friendObjArr.get(0).getPosY()-piDivFour);
    		friendObjArr.get(0).setPlayerCol9(playerMathXVal, friendObjArr.get(0).getPosY()-radius);
    		friendObjArr.get(0).setPlayerCol1030(playerMathXVal+piDivFour, friendObjArr.get(0).getPosY()-piDivFour);
    	}
    	else
    		gameOverDialog();
        
		friendObjArr.get(0).setPosX(playerMathXVal);

        /** ----------------------- **/
        /** HOSTILE OBJECT CREATION **/
        /** ----------------------- **/
        
    	if(elapsedTimeInSec >= (4+timeBuffer)) //start a hostile object
    	{
    		for(int x = 0; x < hostileObjArr.size(); x++) //goes through the 5 elements of hostileObjArr 
    		{
    			if(!hostileObjArr.get(x).getIsActive())//find the first that is not active
    			{
    				hostileObjArr.get(x).setPosYMin(screenHeight-hostileWidth);
    				hostileObjArr.get(x).setPosYMax(screenHeight);
    				hostileObjArr.get(x).setPosXMaxTop(screenWidth);
    				hostileObjArr.get(x).setPosXMinBot(0);
    				switch(rand.nextInt(6))//6 possible gaps in obstacle 
    				{
    				case 0: //largest gap
        				hostileObjArr.get(x).setPosXMinTop(hostileHeightHighest);
        				hostileObjArr.get(x).setPosXMaxBot(hostileHeightLowest);
        				hostileObjArr.get(x).setImageRef(1);
        				break;
    				case 1: //medium gap one
        				hostileObjArr.get(x).setPosXMinTop(hostileHeightHighest);
        				hostileObjArr.get(x).setPosXMaxBot(hostileHeightMidLow);
        				hostileObjArr.get(x).setImageRef(2);
        				break;
    				case 2: //medium gap two
        				hostileObjArr.get(x).setPosXMinTop(hostileHeightMidHigh);
        				hostileObjArr.get(x).setPosXMaxBot(hostileHeightLowest);
        				hostileObjArr.get(x).setImageRef(3);
        				break;
    				case 3: //small gap 1
        				hostileObjArr.get(x).setPosXMinTop(hostileHeightHighest);
        				hostileObjArr.get(x).setPosXMaxBot(hostileHeightMiddle);
        				hostileObjArr.get(x).setImageRef(4);
        				break;
    				case 4: //small gap 2
        				hostileObjArr.get(x).setPosXMinTop(hostileHeightMidHigh);
        				hostileObjArr.get(x).setPosXMaxBot(hostileHeightMidLow);
        				hostileObjArr.get(x).setImageRef(5);
        				break;
    				case 5: //small gap 3
        				hostileObjArr.get(x).setPosXMinTop(hostileHeightMiddle);
        				hostileObjArr.get(x).setPosXMaxBot(hostileHeightLowest);
        				hostileObjArr.get(x).setImageRef(6);
        				break;
    				}//end of switch
    				hostileObjArr.get(x).setIsActive(true);
					elapsedTimeInSec = 0; //resets the spawn timer
			    	timeBuffer++;
			    	if(timeBuffer>4)
			    		timeBuffer=0;
					break;
    			}//end of if isActive
    		}//end of for
    	}//end of if time passed

        /** ------------------------ **/
        /** FRIENDLY OBJECT CREATION **/
        /** ------------------------ **/
        
    	/*if(elapsedTimeInSec >=5.0) //randoms a possible friendly object
    	{
    		for(int x = 1; x < 3; x++) //goes through the 2 remaining elements of friendObjArr 
    		{
    			if(!friendObjArr.get(x).getIsActive())//find the first that is not active
    			{
    				friendObjArr.get(x).setPosY(screenHeight);
    				friendObjArr.get(x).setRad(radius);
    				switch(rand.nextInt(5))//5 possible placements between obstacles
    				{
    				case 0: //highest point
    					friendObjArr.get(x).setPosX(hostileHeightHighest);
        				friendObjArr.get(x).setIsDental(true);
        				friendObjArr.get(x).setImageRef(1);
            			break;
    				case 1: //mid=high point
    					friendObjArr.get(x).setPosX(hostileHeightMidHigh);
        				friendObjArr.get(x).setIsDental(false);
        				friendObjArr.get(x).setImageRef(2);
            			break;
    				case 2: //middle point
    					friendObjArr.get(x).setPosX(hostileHeightMiddle);
        				friendObjArr.get(x).setIsDental(false);
        				friendObjArr.get(x).setImageRef(3);
            			break;
    				case 3: //mid-low point
    					friendObjArr.get(x).setPosX(hostileHeightMidLow);
        				friendObjArr.get(x).setIsDental(false);
        				friendObjArr.get(x).setImageRef(4);
            			break;
    				case 4: //lowest point
    					friendObjArr.get(x).setPosX(hostileHeightLowest);
        				friendObjArr.get(x).setIsDental(true);
        				friendObjArr.get(x).setImageRef(5);
            			break;
    				}
    				elapsedTimeInSec = 0; //resets the spawn timer
    				friendObjArr.get(x).setIsActive(true);
    				break;
    			}
    		}
    	}
    	*/

        /** ---------------- **/
        /** HOSTILE MOVEMENT **/
        /** ---------------- **/
        
    	for(int x = 0; x < hostileObjArr.size(); x++) //updates positions of active hostileObjArr objects
		{
			if(hostileObjArr.get(x).getIsActive())//find active objects
			{
				hostileObjArr.get(x).setPosYMin(hostileObjArr.get(x).getPosYMin()-2);
				hostileObjArr.get(x).setPosYMax(hostileObjArr.get(x).getPosYMax()-2);
				
				if((hostileObjArr.get(x).getPosYMin() <= friendObjArr.get(0).getPosY()+(1.5*radius)) && hostileObjArr.get(x).getIsNear() == false) //when object comes close enough to possibly be hit
					hostileObjArr.get(x).setIsNear(true);
				
				if((hostileObjArr.get(x).getPosYMax() <= friendObjArr.get(0).getPosY()-(1.5*radius)) && hostileObjArr.get(x).getIsNear() == true) //when object goes behind the player enough
				{
					hostileObjArr.get(x).setIsNear(false);
					if(!hostileObjArr.get(x).getHasGivenPoint())//checks if the player has not been given a point for clearing the obstacle yet
					{
						score++;
						hostileObjArr.get(x).setHasGivenPoint(true);
					}
				}
				
				if(hostileObjArr.get(x).getPosYMax() <= 0) //when object goes off edge of screen
				{
					hostileObjArr.get(x).setIsActive(false);
					hostileObjArr.get(x).setHasGivenPoint(false);
				}
			}
		}

        /** ----------------- **/
        /** FRIENDLY MOVEMENT **/
        /** ----------------- **/
        
    	/*
    	for(int x = 1; x < 3; x++) //updates positions of active friendObjArr objects
		{
			if(friendObjArr.get(x).getIsActive())//find active objects
			{
				friendObjArr.get(x).setPosY(friendObjArr.get(x).getPosY()-1);
				if(friendObjArr.get(x).getPosY() <= 0) //when object goes off edge of screen
					friendObjArr.get(x).setIsActive(true);
			}
		}
    	*/
    	
        /** --------------------------- **/
        /** HOSTILE COLLISION DETECTION **/
        /** --------------------------- **/
        
    	for(int x = 0; x < hostileObjArr.size(); x++) //updates positions of active hostileObjArr objects
		{
    		//check if object is near player, no need to collision detect if they can't be hit 
    		//AND if the player is able to be hit again
			if(hostileObjArr.get(x).getIsNear() && (gracePeriod < (System.currentTimeMillis() / 1000) - gotHitTime))
			{
				currentCheck = x;
				isHit = friendObjArr.get(0).getPlayerCol12().pointCompare(hostileObjArr.get(x));
				isHit = friendObjArr.get(0).getPlayerCol130().pointCompare(hostileObjArr.get(x));
				isHit = friendObjArr.get(0).getPlayerCol3().pointCompare(hostileObjArr.get(x));
				isHit = friendObjArr.get(0).getPlayerCol430().pointCompare(hostileObjArr.get(x));
				isHit = friendObjArr.get(0).getPlayerCol6().pointCompare(hostileObjArr.get(x));
				isHit = friendObjArr.get(0).getPlayerCol730().pointCompare(hostileObjArr.get(x));
				isHit = friendObjArr.get(0).getPlayerCol9().pointCompare(hostileObjArr.get(x));
				isHit = friendObjArr.get(0).getPlayerCol1030().pointCompare(hostileObjArr.get(x));
			}
		}
    	
    	if(isHit)
    	{
    		hp--;
    		gotHitTime = System.currentTimeMillis() / 1000;
    	}
    	
    }
    
    public void drawEntities(Canvas canvas)
    {
    	// clear the background
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), 
           backgroundPaint);
        
        for(int x = 0; x < hostileObjArr.size(); x++) //checks what hostile objects are active to draw
		{
        	if(hostileObjArr.get(x).getIsActive())//find active objects
        	{
        		switch(hostileObjArr.get(x).getImageRef())
        		{
        		case 1:
        			drawBitmap(canvas, junkOne, true, x);
            		break;
        		case 2:
        			drawBitmap(canvas, junkTwo, true, x);
            		break;
        		case 3:
        			drawBitmap(canvas, junkThree, true, x);
            		break;
        		case 4:
        			drawBitmap(canvas, junkFour, true, x);
            		break;
        		case 5:
        			drawBitmap(canvas, junkFive, true, x);
            		break;
        		case 6:
        			drawBitmap(canvas, junkSix, true, x);
            		break;
        		}
        	}
		}
        /*
        for(int x = 1; x < 3; x++) //checks what friendly objects are active to draw
		{
        	if(friendObjArr.get(x).getIsActive())//find active objects
        	{
        		switch(friendObjArr.get(x).getImageRef())
        		{
        		case 1:
        			drawBitmap(canvas, dentalOne, true, x);
            		break;
        		case 2:
        			drawBitmap(canvas, foodOne, true, x);
            		break;
        		case 3:
        			drawBitmap(canvas, foodTwo, true, x);
            		break;
        		case 4:
        			drawBitmap(canvas, foodThree, true, x);
            		break;
        		case 5:
        			drawBitmap(canvas, dentalTwo, true, x);
            		break;
        		}
        	}
		}
        */String sHp = "HP: " + Integer.valueOf(hp);
        String sScore = "Score: " + Integer.valueOf(score);/*
        String sHit = "isHit: " + isHit;
        String sCheck = "currentCheck: " + Integer.valueOf(currentCheck);
        String hostile1near = "hostile1near: " + Integer.valueOf(hostileObjArr.get(0).getImageRef())+" "+hostileObjArr.get(0).getIsNear();
        String hostile2near = "hostile2near: " + Integer.valueOf(hostileObjArr.get(1).getImageRef())+" "+hostileObjArr.get(1).getIsNear();
        String hostile3near = "hostile3near: " + Integer.valueOf(hostileObjArr.get(2).getImageRef())+" "+hostileObjArr.get(2).getIsNear();
        String hostile4near = "hostile4near: " + Integer.valueOf(hostileObjArr.get(3).getImageRef())+" "+hostileObjArr.get(3).getIsNear();
        String hostile5near = "hostile5near: " + Integer.valueOf(hostileObjArr.get(4).getImageRef())+" "+hostileObjArr.get(4).getIsNear();
        String sHitTime = "gotHitTime: " + Double.valueOf(gotHitTime);
        String sHitTimeDiff = "TimeDiff: " + Double.valueOf((System.currentTimeMillis() / 1000) - gotHitTime);
        String sTimes = "elapsedTimeInSec: " + Double.toString(elapsedTimeInSec);
        String sTimeInterval = "timeInterval: " + Double.toString(timeInterval);
        String sTimeBuff = "timeBuffer: " + Double.toString(timeBuffer);
        String hostileOne = "hostile1: " + String.valueOf(hostileObjArr.get(0).getIsActive()) +" "+Integer.valueOf(hostileObjArr.get(0).getImageRef());
        String hostileTwo = "hostile2: " + String.valueOf(hostileObjArr.get(1).getIsActive()) +" "+Integer.valueOf(hostileObjArr.get(1).getImageRef());
        String hostileThree = "hostile3: " + String.valueOf(hostileObjArr.get(2).getIsActive()) +" "+Integer.valueOf(hostileObjArr.get(2).getImageRef());
        String hostileFour = "hostile4: " + String.valueOf(hostileObjArr.get(3).getIsActive()) +" "+Integer.valueOf(hostileObjArr.get(3).getImageRef());
        String hostileFive = "hostile5: " + String.valueOf(hostileObjArr.get(4).getIsActive()) +" "+Integer.valueOf(hostileObjArr.get(4).getImageRef());
        String hostileOnePos = "hostile1POS: " + Integer.valueOf(hostileObjArr.get(0).getPosYMin());
        String hostileTwoPos = "hostile2POS: " + Integer.valueOf(hostileObjArr.get(1).getPosYMin());
        String hostileThreePos = "hostile3POS: " + Integer.valueOf(hostileObjArr.get(2).getPosYMin());
        String hostileFourPos = "hostile4POS: " + Integer.valueOf(hostileObjArr.get(3).getPosYMin());
        String hostileFivePos = "hostile5POS: " + Integer.valueOf(hostileObjArr.get(4).getPosYMin());
        
        */canvas.drawText(sHp, 5, 30, textPaint);
        canvas.drawText(sScore, 5, 60, textPaint);/*
        canvas.drawText(sHit, 5, 50, textPaint);
        canvas.drawText(sCheck, 5, 80, textPaint);
        canvas.drawText(hostile1near, 5, 110, textPaint);
        canvas.drawText(hostile2near, 5, 140, textPaint);
        canvas.drawText(hostile3near, 5, 170, textPaint);
        canvas.drawText(hostile4near, 5, 200, textPaint);
        canvas.drawText(hostile5near, 5, 230, textPaint);
        canvas.drawText(sHitTime, 5, 260, textPaint);
        canvas.drawText(sHitTimeDiff, 5, 290, textPaint);
        canvas.drawText(sTimes, 5, 200, textPaint);
        canvas.drawText(sTimeInterval, 5, 230, textPaint);
        canvas.drawText(sTimeBuff, 5, 260, textPaint);
        canvas.drawText(hostileOne, 5, 300, textPaint);
        canvas.drawText(hostileTwo, 5, 330, textPaint);
        canvas.drawText(hostileThree, 5, 360, textPaint);
        canvas.drawText(hostileFour, 5, 390, textPaint);
        canvas.drawText(hostileFive, 5, 420, textPaint);
        canvas.drawText(hostileOnePos, 5, 500, textPaint);
        canvas.drawText(hostileTwoPos, 5, 530, textPaint);
        canvas.drawText(hostileThreePos, 5, 560, textPaint);
        canvas.drawText(hostileFourPos, 5, 590, textPaint);
        canvas.drawText(hostileFivePos, 5, 620, textPaint);*/
        
        canvas.drawCircle(friendObjArr.get(0).getPosX(), friendObjArr.get(0).getPosY(), (float) friendObjArr.get(0).getRad(),
                testPaint);
    }

    public void drawBitmap(Canvas canvas, Bitmap bmp, boolean isHostile, int x) //x is array position
    {
    	if(isHostile)
    	{
    		canvas.drawBitmap(bmp, null, new Rect(hostileObjArr.get(x).getPosXMinBot(),
    				hostileObjArr.get(x).getPosYMin(),hostileObjArr.get(x).getPosXMaxBot(), hostileObjArr.get(x).getPosYMax()), null);
    		canvas.drawBitmap(bmp, null, new Rect(hostileObjArr.get(x).getPosXMinTop(),
    				hostileObjArr.get(x).getPosYMin(),hostileObjArr.get(x).getPosXMaxTop(), hostileObjArr.get(x).getPosYMax()), null);
    	}
    	else
    		canvas.drawBitmap(bmp, null, new Rect((int)(friendObjArr.get(x).getPosX() - radius),
    				(int)(friendObjArr.get(x).getPosY() - radius),(int)(2*radius), (int)(2*radius)), null);
    }
    
	@Override
	public void surfaceCreated(SurfaceHolder holder) 
	{
		playerMathXVal = screenWidth/2;
		friendObjArr.get(0).setPosX(playerMathXVal);
		friendObjArr.get(0).setPosY(screenHeight / 4);
		
		gameThread = new GameThread(holder);
		gameThread.setRunning(true);
		gameThread.start(); // start the game loop thread
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {/*Unused*/}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// ensure that thread terminates properly
		boolean retry = true;
		gameThread.setRunning(false);
	      
		while (retry)
		{
			try
			{
				gameThread.join();
				retry = false;
			} // end try
			catch (InterruptedException e)
			{
			} // end catch
		} // end while   
	}
	
	private void drawGameStart(Canvas canvas)
	{
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.startgamebg);
        canvas.drawBitmap(bmp, null, new Rect(0,0,screenWidth, screenHeight), null);
	}
	
	private void gameOverDialog()
	{
		gameover = true;
		
		SharedPreferences saveFile = activity.getSharedPreferences("com.example.battlefordentalperfection", Context.MODE_PRIVATE);
		if (score > sharedVars.getHighScore())
			sharedVars.setHighScore(score, saveFile);
		
		final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
		if(sharedVars.getbIsEng())
		{
			String scoreMsg = activity.getString(R.string.gameOverScoreEng) +" "+ Integer.valueOf(score);
			builder.setTitle(R.string.gameOverTitleEng); 
			builder.setMessage(scoreMsg);
		
			builder.setPositiveButton(R.string.gameOverOKEng,
				new DialogInterface.OnClickListener()
				{     
					public void onClick(DialogInterface dialog, int id)
					{
						activity.finish();
					}
				}
			);
		}
		else
		{
			String scoreMsg = activity.getString(R.string.gameOverScoreSpn) +" "+ Integer.valueOf(score);
			builder.setTitle(R.string.gameOverTitleSpn); 
			builder.setMessage(scoreMsg);
		
			builder.setPositiveButton(R.string.gameOverOKSpn,
				new DialogInterface.OnClickListener()
				{     
					public void onClick(DialogInterface dialog, int id)
					{
						activity.finish();
					}
				}
			);
		}
			builder.setCancelable(false);
			
			activity.runOnUiThread(
			         new Runnable() {
			            public void run()
			            {
			               builder.show(); // display the dialog
			            } // end method run
			         } // end Runnable
			      ); // end call to runOnUiThread
	}
	
	private class GameThread extends Thread
	{
		private SurfaceHolder surfaceHolder; // for manipulating canvas
	    private boolean threadIsRunning = true; // running by default
	      
	    // initializes the surface holder
	    public GameThread(SurfaceHolder holder)
	    {
	    	surfaceHolder = holder;
	        setName("GameThread");
	    } // end constructor
	      
	    // changes running state
	    public void setRunning(boolean running)
	    {
	    	threadIsRunning = running;
	    } // end method setRunning
	      
	    // controls the game loop
	    @Override
	    public void run()
	    {
	    	Canvas canvas = null; // used for drawing
	    	long previousTime = System.currentTimeMillis(); 
			long currentTime;
			double elapsedTimeMS;
	        
	        while (threadIsRunning)
	        {
	        	try
	            {
	        		canvas = surfaceHolder.lockCanvas(null);
	        		// lock the surfaceHolder for drawing
	        		synchronized(surfaceHolder)
	        		{
	        			if(playerHasTapped && !gameover)
	        			{
	        				currentTime = System.currentTimeMillis();
	        				timeInterval = elapsedTimeMS = currentTime - previousTime;
	        				totalPassedTimeMS += elapsedTimeMS / 1000.00;
	        				updateScreen(totalPassedTimeMS); // update game state
	        				drawEntities(canvas); // draw 
	                        previousTime = currentTime; // update previous time
	        			}
	        			else
	        				drawGameStart(canvas);
	                } // end synchronized block
	            } // end try
	        	finally
	            {
	        		if (canvas != null)
	        			surfaceHolder.unlockCanvasAndPost(canvas);
	            } // end finally
	        } // end while
	    } // end method run
	} // end nested class
	
}
