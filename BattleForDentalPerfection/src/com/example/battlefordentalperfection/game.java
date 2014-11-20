package com.example.battlefordentalperfection;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.RelativeLayout;

public class game extends SurfaceView implements SurfaceHolder.Callback{
		
	private GameThread gameThread; // controls the game loop
	   
	private int score; //obstacles cleared
	private int hp; //amount of hits player can take
	private int overdose; //progress of overdose meter
	private boolean gameover = true; //true if game done, false if running
	private double timePassed; //time elapsed since last graphic update
	private int playerMathXVal; //current position along the curve y = -x^2 from -2 to 2.
	private double delta;
	private boolean isFalling = false;
	
	private int screenWidth;
	private int screenHeight;
	
	private ArrayList<friendObj> friendObjArr; //pos 0 = player, others are dental/food products
	private ArrayList<hostileObj> hostileObjArr; //contains the positions for hostile entities
	
	private Paint backgroundPaint; // Paint used to clear the drawing area
	private Paint testPaint;
	private Paint textPaint;

	
	public game(Context context, AttributeSet atrbs)
	{
		super(context, atrbs);
		
		getHolder().addCallback(this); 
		
		score = 0;
		overdose = 0;
		gameover = false;
		timePassed = 0.0;
		
		switch(sharedVars.getDifficulty()){
		case 0: hp = 3; break;
		case 1: hp = 2; break;
		case 2: hp = 1; break;	
		default: hp = 3; break;
		}
		
		friendObjArr = new ArrayList<friendObj>();
		hostileObjArr = new ArrayList<hostileObj>();
		
		friendObjArr.add(0, new friendObj(screenWidth / 2, screenHeight / 4, 15, "hero1.png", true));
		friendObjArr.add(1, new friendObj(0, 0, 15, "hero2.png", false));
		friendObjArr.add(2, new friendObj(0, 0, 15, "icon.png", false));
		
		hostileObjArr.add(0, new hostileObj(0, 0, 0, 0, 0, 0, "vill1.png", false));
		hostileObjArr.add(1, new hostileObj(0, 0, 0, 0, 0, 0, "vill1.png", false));
		hostileObjArr.add(2, new hostileObj(0, 0, 0, 0, 0, 0, "vill1.png", false));
		hostileObjArr.add(3, new hostileObj(0, 0, 0, 0, 0, 0, "vill1.png", false));
		hostileObjArr.add(4, new hostileObj(0, 0, 0, 0, 0, 0, "vill1.png", false));

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

	      playerMathXVal = screenWidth/2;
	      delta = -(screenWidth/100);
	      friendObjArr.get(0).setPosX(screenWidth / 2);
	      friendObjArr.get(0).setPosY(screenHeight / 4);
	      
	      textPaint.setTextSize(w / 20); // text size 1/20 of screen width
	      textPaint.setAntiAlias(true); // smoothes the text
	      
	      backgroundPaint.setColor(Color.WHITE); // set background color
	      if (gameover)
	      {
	    	  gameover = false; // the game is not over
	    	  //friendObjArr.get(0).setPosX(screenWidth / 2);
	    	  //friendObjArr.get(0).setPosY(screenHeight / 4);
	    	  gameThread = new GameThread(getHolder());
	    	  gameThread.start();
	      } // end if
	      
	} // end method onSizeChanged
	
	// stops the game
	public void stopGame()
	{
		if (gameThread != null)
			gameThread.setRunning(false);
	} // end method stopGame
	
	public void resetPlayerMath(MotionEvent event)
	{
		//playerMathXVal = friendObjArr.get(0).getPosX();
		isFalling = false;
	    playerMathXVal = screenWidth/2;
		delta = -(screenWidth/100);
		friendObjArr.get(0).setPosX(screenWidth / 2);
		friendObjArr.get(0).setPosY(screenHeight / 4);
	}
	
    public void updateScreen(double timePassedMS)
    {
    	//int interval = (int) timePassedMS / 1000; //milisec to sec.
    	
    	
    	//playerMathXVal = (int) Math.pow(-(playerMathXVal + interval), 2);

		playerMathXVal += delta;
		if(delta > -5)
			isFalling = true;
		if(!isFalling)
			delta *= 1.01;
		else
			delta /= 1.01;
		
		friendObjArr.get(0).setPosX(playerMathXVal);
    }
    
    public void drawEntities(Canvas canvas)
    {
    	// clear the background
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), 
           backgroundPaint);
        
        String widthMsg = "Width: " + Integer.toString(screenWidth);
        String heightMsg = "Height: " + Integer.toString(screenHeight);
        String sPosX = "pos x: " + Integer.toString(friendObjArr.get(0).getPosX());
        String sPosY = "pos y: " + Integer.toString(friendObjArr.get(0).getPosY());
        String sDelta = "delta: " + Double.toString(delta);
        String sMaths = "playerMath: " + Integer.toString(playerMathXVal);
        
        canvas.drawText(widthMsg, 5, 50, textPaint);
        canvas.drawText(heightMsg, 5, 80, textPaint);
        canvas.drawText(sPosX, 5, 110, textPaint);
        canvas.drawText(sPosY, 5, 140, textPaint);
        canvas.drawText(sDelta, 5, 170, textPaint);
        canvas.drawText(sMaths, 5, 200, textPaint);
        
        canvas.drawCircle(friendObjArr.get(0).getPosX(), friendObjArr.get(0).getPosY(), friendObjArr.get(0).getRad(),
                testPaint);
    }

	@Override
	public void surfaceCreated(SurfaceHolder holder) 
	{
		if (true)
		{
			playerMathXVal = screenWidth/2;
			delta = -(screenWidth/10);
			friendObjArr.get(0).setPosX(screenWidth / 2);
			friendObjArr.get(0).setPosY(screenHeight / 4);
			
			gameThread = new GameThread(holder);
			gameThread.setRunning(true);
			gameThread.start(); // start the game loop thread
		} // end if
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
	        long previousFrameTime = System.currentTimeMillis(); 
	        
	        while (threadIsRunning)
	        {
	        	try
	            {
	        		canvas = surfaceHolder.lockCanvas(null);
	        		// lock the surfaceHolder for drawing
	        		synchronized(surfaceHolder)
	        		{
	        			long currentTime = System.currentTimeMillis();
	                    double elapsedTimeMS = currentTime - previousFrameTime;
	                    timePassed += elapsedTimeMS / 1000.00; 
	                    updateScreen(elapsedTimeMS); // update game state
	                    drawEntities(canvas); // draw 
	                    previousFrameTime = currentTime; // update previous time
	                } // end synchronized block
	            } // end try
	        	finally
	            {
	        		if (canvas != null)
	        			surfaceHolder.unlockCanvasAndPost(canvas);
	            } // end finally
	        } // end while
	    } // end method run
	} // end nested class CannonThread
	
}
