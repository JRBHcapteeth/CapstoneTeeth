package com.example.battlefordentalperfection;

public class friendObj {

	private static int posX, posY, radius;
	private static String pathname;
	private boolean isActive;
	
	friendObj(int px, int py, int rad, String str, boolean isUp)
	{
		posX = px;
		posY = py;
		radius = rad;
		pathname = str;
		isActive = isUp;
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

	public int getRad()
	{
		return radius;
	}
	
	public void setRad(int num)
	{
		radius = num;
	}
	
	public boolean getIsActive()
	{
		return isActive;
	}
	
	public void setIsActive(boolean bool)
	{
		isActive = bool;
	}
	
	public String getPathname()
	{
		return pathname;
	}
	
	public void setPathname(String string)
	{
		pathname = string;
	}
	
}
