# StackerAI
An AI that teaches itself to play the popular arcade game Stacker. 
The AI utilizes NeuroEvolution of Augmenting Topologies also know as NEAT.

The network is given 6 inputs (see NEATAI.java) and contains 2 outputs {1,0} or {0,1}.
The outputs are to either stop at the current position or dont stop respectively.

Population size = 500;

***WARNING***
As of 10/25/19 the program has a major memory issue that will cause it to crash after 
a certain period of time whilst training. Im currently working on a solution to this issue.
