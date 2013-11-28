//
// Laura Richards
//
// CIS*6420 - Soft Computing
// Assignment #1
//
// GA.java
// Does the reproduction, crossover and mutation of the population during each generation
//
//

import java.util.Random;
import java.lang.Object;

public class GA {
	
	private static Entity[] population;
	private static Entity[] populationOld;
	
	private static int fcnNum = 0;
	private static int popNum = 0;
	private static int gens = 0;
	private static double xover = 0;
	private static double mutation = 0;
	
	private static double fit_Total = 0;
	private static double fit_Avg = 0;
	private static double maxFitness = 0;
	
	// Keeps track of the best fit member, plus does total fitness and Avg
	public static void MaxIndvl()
	{
		int index =0;
		
		int i = 0;
		double max = 0;
		for ( i = 0; i < popNum; i ++)
		{
			fit_Total += population[i].getFit();
			
			if(population[i].getFit() > max)
			{
				max = population[i].getFit();
				maxFitness = max;
				index = i;
			}
		}	
		
		fit_Avg = (double)fit_Total/(double)popNum;
		
		// update the fitness probabalitily 
		FitProb();
		
		System.out.println("Tot-Avg-Max "+ fit_Total+" "+fit_Avg+" "+maxFitness);
	}
	
	
	// Gives total fitness to each member so they can compute their fitness prob's
	public static void FitProb()
	{
		int i = 0;
		for ( i = 0; i < popNum; i ++)
		{
			population[i].setFitProb(fit_Total);
		}
	}
	
	
	// Move current pop to old pop statis
	public static void ClonePop()
	{
		int i = 0;
		for( i = 0; i < popNum; i++)
		{
			populationOld[i] = new Entity(population[i]);
		}
	}
	
	
	public static void Reproduction()
	{
		// Current population now becomes old population
		ClonePop();
		
		// Using roulette wheel for randomly picking out members from the old population to put into the new population
		Random generator = new Random();
		int i = 0;
		for(i = 0; i < popNum; i++)
		{
			double wheelNum = generator.nextDouble();
			
			double count = 0;
			
			int j = 0;
			
			for(j = 0; j < popNum; j ++)
			{
				count += population[j].getFitProb();
				
				if(count >= wheelNum)
				{
					population[i] = new Entity(populationOld[j]);
					break;
				}
			}
		}
	}
	
	
	// Performs crossover on the parents that we just moved into the new generation
	public static void CrossOver()
	{
		Random generator = new Random();
		
		int i = 0;
		for(i = 1; i < popNum; i = i+2)
		{
			double xoverPercent = generator.nextInt(100) + 1;
			xoverPercent = xoverPercent/(double)100;
			
			if(xoverPercent <= xover) // Does crossover happen between this pair?
			{
				int xoverPoint = generator.nextInt(99); // crossover starts at the index after the one given
				
				String parent1 = new String(population[i-1].getEnt());
				String parent2 = new String(population[i].getEnt());
				
				population[i-1].setEnt(parent1.substring(0,xoverPoint+1) + parent2.substring(xoverPoint+1, 100));
				population[i].setEnt(parent2.substring(0,xoverPoint+1) + parent1.substring(xoverPoint+1, 100));
				
			}
		}
	}
	
	
	// Performs mutation on the selected few in the population
	public static void Mutation()
	{
		Random generator = new Random();
		
		int i = 0;
		for(i = 0; i < popNum; i ++)
		{
			double mutPercent = generator.nextInt(100) + 1;
			mutPercent = mutPercent/(double)100;
			
			
			if( mutPercent <= mutation) // Does mutation happen between to this member?
			{
				int mutPoint = generator.nextInt(100);
				String parent = new String(population[i].getEnt());
				
				char flip = '1';
				
				if(parent.charAt(mutPoint) == '1')
					flip = '0';
				else
					flip = '1';
				
				if(mutPoint == 0)
					population[i].setEnt(flip+parent.substring(mutPoint+1,100));
				else if (mutPoint == 99)
					population[i].setEnt(parent.substring(0,mutPoint)+flip);
				else
					population[i].setEnt(parent.substring(0,mutPoint) + flip + parent.substring(mutPoint+1,100));	
				
			}				
		}
	}
	
	
	// Update the fitness of everyone in the population
	public static void ReCalFitness()
	{
		int i = 0;
		for(i = 0; i < popNum; i ++)
		{
			if(fcnNum == 1)
				population[i].Fitness1();
			else
				population[i].Fitness2();
		}
	}
	
	
	public static void main(String argv[]) {
		
		// Vars in GA program
		fcnNum = Integer.parseInt(argv[0]);
		popNum = Integer.parseInt(argv[1]);
		gens = Integer.parseInt(argv[2]);
		xover = Double.parseDouble(argv[3]);
		mutation = Double.parseDouble(argv[4]);
		
		// Population holders
		population = new Entity[popNum];
		populationOld = new Entity[popNum];
		
		double solution = 0;
		
		if(fcnNum == 1)
			solution = 100;
		else if(fcnNum == 2)
			solution = 1.2676506002282294E30;
		else
		{
			System.out.println("Wrong Function Number, should be 1 or 2");
			System.exit(0);
		}
	
		MakeRandomPop();
		
		int currentGen = 0;
		
		while(true)
		{
			/*Check population for fitness levels*/
			fit_Total = 0;
			fit_Avg = 0;
			
			MaxIndvl();
			
			
			if(currentGen >= gens)
			{
				break;
			}
			else if(maxFitness == solution)
			{
				System.out.println("Found Solution in Gen "+(currentGen)+" :: Tot-Avg-Max-Fit1-B "+ fit_Total+" "+fit_Avg+" "+maxFitness);
				break;
			}
			
			currentGen++;
			
			
			// Create new population
			
			// Reproduction
			Reproduction();
			
			// Crossover
			CrossOver();
			
			// Mutation
			Mutation();

			
			// re calculate the fitness
			ReCalFitness();
		}
		
		System.out.println("");
		
	}
	
	
	
	// Sets up the starting population = strings of 100 bits
	public static void MakeRandomPop()
	{
		Random generator = new Random();
		
		int i = 0;
		for(i =0; i < popNum; i++)
		{
			String member = new String();
			
			int j = 0;
			for(j = 0; j < 100; j++)
			{
				boolean bit = generator.nextBoolean();
				
				if(bit)
					member = member+"1";
				else
					member = member+"0";
			}
			
			population[i] = new Entity(member, fcnNum);
			
		}
	}

}
