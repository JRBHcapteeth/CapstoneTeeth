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
	
	public boolean pointCompare(hostileObj hostile)
	{
		return  //if the y value of the point is in between the y values of the hostile object
				(this.y < hostile.getPosYMax() && this.y > hostile.getPosYMin() && //AND
				//if the x value is NOT between the max bottom and min top (the gap between hostile objects)
				(!(this.x > hostile.getPosXMaxBot() && this.x < hostile.getPosXMinTop())));
	}
}
