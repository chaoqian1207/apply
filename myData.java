package com_mec_bm;

public class myData extends BaseData 
{
	protected String id;
	protected String name;
	
	public myData(String id, String name)
	{
		super();
		this.id = id;
		this.name = name;
	}
	
	public String getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}

	public String toString() 
	{ 
		return id + "(" + name + ")";
	} 
}
