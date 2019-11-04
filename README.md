# StackerAI
An AI that teaches itself to play the popular arcade game Stacker. 
The AI utilizes NeuroEvolution of Augmenting Topologies also know as NEAT.

The network is given 6 inputs (see NEATAI.java) and contains 2 outputs {1,0} or {0,1}.
The outputs are to either stop at the current position or dont stop respectively.

Population size = 500;

Note: The file "network.model" is the trained AI model.

10/30/19:
- Memory issue fixed
- Added load-from-file support

