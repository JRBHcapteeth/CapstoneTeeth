package com.example.battlefordentalperfection;

public class hostileObj {
	
	private static int posXMin, posXMax, posYMinTop, posYMaxTop, posYMinBot, posYMaxBot;
	private static String pathname;
	private boolean isActive;
	
	hostileObj(int pxM, int pxm, int pymT, int pyMT, int pymB, int pyMB, String str, boolean isUp)
	{
		posXMin = pxM;
		posXMax = pxm;
		posYMinTop = pymT;
		posYMaxTop = pyMT;
		posYMinBot = pymB;
		posYMaxBot = pyMB;
		pathname = str;
		isActive = isUp;
	}
	
	public int getPosXMin()
	{
		return posXMin;
	}
	
	public void setPosXMin(int num)
	{
		posXMin = num;
	}
	
	public int getPosXMax()
	{
		return posXMax;
	}
	
	public void setPosXMax(int num)
	{
		posXMax = num;
	}

	public int getPosYMinTop()
	{
		return posYMinTop;
	}
	
	public void setPosYMinTop(int num)
	{
		posYMinTop = num;
	}
	
	public int getPosYMaxTop()
	{
		return posYMaxTop;
	}
	
	public void setPosYMaxTop(int num)
	{
		posYMaxTop = num;
	}
	
	public int getPosYMinBot()
	{
		return posYMinBot;
	}
	
	public void setPosYMinBot(int num)
	{
		posYMinBot = num;
	}
	
	public int getPosYMaxBot()
	{
		return posYMaxBot;
	}
	
	public void setPosYMaxBot(int num)
	{
		posYMaxBot = num;
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
