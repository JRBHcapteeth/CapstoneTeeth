package com.example.battlefordentalperfection;

public class Profile {
	
	private String pathname, nameEng, bioEng, repEng, nameSpn, bioSpn, repSpn;
	
	Profile(String pn, String nE, String bE, String rE, String nS, String bS, String rS)
	{
		pathname = pn;
		nameEng = nE;
		bioEng = bE;
		repEng = rE;
		nameSpn = nS;
		bioSpn = bS;
		repSpn = rS;
	}
	public String getPath()
	{
		return pathname;
	}
	
	public String getNameEng()
	{
		return nameEng;
	}
	
	public String getBioEng()
	{
		return bioEng;
	}
	
	public String getRepEng()
	{
		return repEng;
	}
	
	public String getNameSpn()
	{
		return nameSpn;
	}
	
	public String getBioSpn()
	{
		return bioSpn;
	}
	
	public String getRepSpn()
	{
		return repSpn;
	}
}
