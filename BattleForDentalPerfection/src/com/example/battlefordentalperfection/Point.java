package com.example.battlefordentalperfection;

public class Point {
	
	private double x, y;
	
	Point(double xIn, double yIn)
	{
		x = xIn;
		y = yIn;
	}
	
	public void setPoint(double xIn, double yIn)
	{
		x = xIn;
		y = yIn;
	}
	
	public boolean pointCompareHostile(hostileObj hostile)
	{
		return  //if the y value of the point is in between the y values of the hostile object
				(this.y < hostile.getPosYMax() && this.y > hostile.getPosYMin() && //AND
				//if the x value is NOT between the max bottom and min top (the gap between hostile objects)
				(!(this.x > hostile.getPosXMaxBot() && this.x < hostile.getPosXMinTop())));
	}
	/*
	 * (int)(friendObjArr.get(x).getPosX() - 2*radius),
    											(int)(friendObjArr.get(x).getPosY() - 2*radius),
    											(int)(friendObjArr.get(x).getPosX() + 2*radius), 
    											(int)(friendObjArr.get(x).getPosY() + 2*radius)
	 */
	public boolean pointCompareFriendly(friendObj friend, double value) //value is 2*radius
	{
		return  ((this.x > (friend.getPosX() - value)) &&
				 (this.y > (friend.getPosY() - value)) &&
				 (this.x < (friend.getPosX() + value)) &&
				 (this.y < (friend.getPosY() + value)));
	}
}
