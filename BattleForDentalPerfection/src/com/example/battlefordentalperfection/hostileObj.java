package com.example.battlefordentalperfection;

public class hostileObj {
	
	private int posYMin, posYMax, posXMinTop, posXMaxTop, posXMinBot, posXMaxBot, imgref;
	private boolean isActive, isNear, hasGivenPoint;
	
	hostileObj(int pym, int pyM, int pxmT, int pxMT, int pxmB, int pxMB, int pth, boolean isUp, boolean ibp, boolean hgp)
	{
		posYMin = pym;
		posYMax = pyM;
		posXMinTop = pxmT;
		posXMaxTop = pxMT;
		posXMinBot = pxmB;
		posXMaxBot = pxMB;
		imgref = pth;
		isActive = isUp;
		isNear = ibp;
		hasGivenPoint = hgp;
	}
	
	public int getPosYMin()
	{
		return posYMin;
	}
	
	public void setPosYMin(int num)
	{
		posYMin = num;
	}
	
	public int getPosYMax()
	{
		return posYMax;
	}
	
	public void setPosYMax(int num)
	{
		posYMax = num;
	}

	public int getPosXMinTop()
	{
		return posXMinTop;
	}
	
	public void setPosXMinTop(int num)
	{
		posXMinTop = num;
	}
	
	public int getPosXMaxTop()
	{
		return posXMaxTop;
	}
	
	public void setPosXMaxTop(int num)
	{
		posXMaxTop = num;
	}
	
	public int getPosXMinBot()
	{
		return posXMinBot;
	}
	
	public void setPosXMinBot(int num)
	{
		posXMinBot = num;
	}
	
	public int getPosXMaxBot()
	{
		return posXMaxBot;
	}
	
	public void setPosXMaxBot(int num)
	{
		posXMaxBot = num;
	}
	
	public boolean getIsActive()
	{
		return isActive;
	}
	
	public void setIsActive(boolean bool)
	{
		isActive = bool;
	}
	
	public boolean getIsNear()
	{
		return isNear;
	}
	
	public void setIsNear(boolean bool)
	{
		isNear = bool;
	}

	public boolean getHasGivenPoint()
	{
		return hasGivenPoint;
	}
	
	public void setHasGivenPoint(boolean bool)
	{
		hasGivenPoint = bool;
	}
	
	public int getImageRef()
	{
		return imgref;
	}
	
	public void setImageRef(int num)
	{
		imgref = num;
	}

}
