package com.example.battlefordentalperfection;

public class friendObj {

	private int posX, posY, imgref;
	private double radius;
	private boolean isActive, isDental;
	
	//variables for player collision detection
	//in landscape orientation, playerCol12 is the top point of the players circle
	//rest follow in a clockwise cycle
	//in portrait mode, playerCol12 is the right-most point, going clockwise for the
	//rest of the points
	private Point playerCol12, playerCol130, playerCol3, playerCol430, playerCol6, playerCol730, playerCol9, playerCol1030;
	
	friendObj(int px, int py, double rad, int imgr, boolean isUp, boolean isDent)
	{
		posX = px;
		posY = py;
		radius = rad;
		imgref = imgr;
		isActive = isUp;
		isDental = isDent;
		playerCol12 = new Point(0,0);
		playerCol130 = new Point(0,0);
		playerCol3 = new Point(0,0);
		playerCol430 = new Point(0,0);
		playerCol6 = new Point(0,0);
		playerCol730 = new Point(0,0);
		playerCol9 = new Point(0,0);
		playerCol1030 = new Point(0,0);
	}
	
	public int getPosX()
	{
		return posX;
	}
	
	public void setPosX(int num)
	{
		posX = num;
	}
	
	public int getPosY()
	{
		return posY;
	}
	
	public void setPosY(int num)
	{
		posY = num;
	}

	public double getRad()
	{
		return radius;
	}
	
	public void setRad(double rad)
	{
		radius = rad;
	}
	
	public boolean getIsActive()
	{
		return isActive;
	}
	
	public void setIsActive(boolean bool)
	{
		isActive = bool;
	}
	
	public boolean getIsDental()
	{
		return isDental;
	}
	
	public void setIsDental(boolean bool)
	{
		isDental = bool;
	}
	
	public int getImageRef()
	{
		return imgref;
	}
	
	public void setImageRef(int num)
	{
		imgref = num;
	}

	public Point getPlayerCol12()
	{
		return playerCol12;
	}
	
	public void setPlayerCol12(double x, double y)
	{
		playerCol12.setPoint(x, y);
	}
	
	public Point getPlayerCol130()
	{
		return playerCol130;
	}
	
	public void setPlayerCol130(double x, double y)
	{
		playerCol130.setPoint(x, y);
	}
	
	public Point getPlayerCol3()
	{
		return playerCol3;
	}
	
	public void setPlayerCol3(double x, double y)
	{
		playerCol3.setPoint(x, y);
	}
	
	public Point getPlayerCol430()
	{
		return playerCol430;
	}
	
	public void setPlayerCol430(double x, double y)
	{
		playerCol430.setPoint(x, y);
	}
	
	public Point getPlayerCol6()
	{
		return playerCol6;
	}
	
	public void setPlayerCol6(double x, double y)
	{
		playerCol6.setPoint(x, y);
	}
	
	public Point getPlayerCol730()
	{
		return playerCol730;
	}
	
	public void setPlayerCol730(double x, double y)
	{
		playerCol730.setPoint(x, y);
	}
	
	public Point getPlayerCol9()
	{
		return playerCol9;
	}
	
	public void setPlayerCol9(double x, double y)
	{
		playerCol9.setPoint(x, y);
	}
	
	public Point getPlayerCol1030()
	{
		return playerCol1030;
	}
	
	public void setPlayerCol1030(double x, double y)
	{
		playerCol1030.setPoint(x, y);
	}
	
}
