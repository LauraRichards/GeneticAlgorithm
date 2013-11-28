Laura Richards
CIS*6420 - Assign#1

README
-------  genetic algorithm

Building the GA
- type "make" in the current directory

Running the GA
- the command line variables for running the GA program are as follows:

java GA f p g x m

f = fitness function you want, 1 or 2
p = population size
g = the number of generations you want to run for
x = the crossover rate (0 - 1.0)
m = the mutation rate (0 - 1.0)

- ex. "java GA 1 10 100 0.5 0.01" 

The total fitness, average fitness and maximum fitness of each generation will be printed to screen.


To rebuild the GA, type "make clean" and type "make" again.