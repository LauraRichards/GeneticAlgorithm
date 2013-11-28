//
// Laura Richards
//
// CIS*6420 - Soft Computing
// Assignment #1
//
// Entity.java
// Object for holding everything about a member of the population
//
//

import java.util.Random;
import java.lang.Object;
import java.lang.Math;


public class Entity {
	
	private String ent = new String();
	
	private double fitnessNum = 0;
	private double fitnessProb = 0;

	
	
	//----------Constructors----------------//
	
	
	// Starting off
	public Entity(String ent, int fcnNum)
	{
		this.ent = ent;
		if(fcnNum == 1)
			Fitness1();
		else
			Fitness2();
	}
	
	// Cloning
	public Entity(Entity another)
	{
		this.ent = another.ent;
		this.fitnessNum = another.fitnessNum;
		this.fitnessProb = another.fitnessProb;
	}
	
	
	//----------Setters----------------//
	
	public void setEnt(String ent)
	{
		this.ent = ent;
	}
	
	
	public void setFitProb(double fitTotal)
	{
		this.fitnessProb = fitnessNum / fitTotal;
	}
	
	
	//----------Getters----------------//
	
	public String getEnt()
	{
		return ent;
	}
	
	public double getFit()
	{
		return fitnessNum;
	}
	
	public double getFitProb()
	{
		return fitnessProb;	
	}
	
		
	
	//----------Fitness Functions----------------//
	
	// Counts number of 1's in the member
	public void Fitness1()
	{
		fitnessNum = 0;
		
		int i = 0;
		for(i = 0; i < 100 ; i ++)
		{
			if( ent.charAt(i)  == '1')
				fitnessNum += 1;
		}
	}
	
	
	// Changes the binary string into the int representation
	public void Fitness2()
	{
		fitnessNum = 0;
		
		int i = 0;
		for(i = 0; i < 100 ; i ++)
		{
			if( ent.charAt(i)  == '1')
			{
				fitnessNum += Math.pow(2, (double)i);
			}
		}
	}

	
	
	public static void main(String argv[]) {
	}
	
}
