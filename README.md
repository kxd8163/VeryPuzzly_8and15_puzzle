VeryPuzzly_8and15_puzzle
========================

Solver for 8 and 15 Puzzles

This is 8/15 Puzzle solver.
The solution of the Puzzle is based on А* algorithm.

The special case of the algorithm for the puzzle solver consists of the following steps:

1. Push initial position into a priority queue
2. Pop the position with the lowest priority
3. Push all neighboring positions to the queue. Here, all the paths that leaded to the current position are pushed, not just positions itself. Thus, the queue always contains the paths
4. Repeat 2-4 until the last position will get popped in 2.

"""

A* uses a best-first search and finds a least-cost path from a given initial node to one goal node (out of one or more possible goals). As A* traverses the graph, it follows a path of the lowest expected total cost or distance, keeping a sorted priority queue of alternate path segments along the way.
It uses a knowledge-plus-heuristic cost function of node x (f(x)) to determine the order in which the search visits nodes in the tree. The cost function is a sum of two functions: the past path-cost function, which is the known distance from the starting node to the current node x (g(x))a future path-cost function, which is an admissible "heuristic estimate" of the distance from x to the goal (h(x)).

"""

In this case g(x) equals to number of moves that leaded to the position x. When h(x) equals to the number of cells located at proper positions.
For example the following combination has h(x) 1, as 8 is located at the wrong place:

1 2 3

4 5 6

7   8

Thus, the minimum number of moves is h(x).
Good thing about А* is that it finds the shortest path in P time, but space complexity of the algorithm is exponential.
------------------------------------------------------------------------------------
Usage: [java] VeryPuzzly [8|15]

Example:

$java VeryPuzzly 8
Input the combination you want to solve.
Example:

1 2 3

4 0 5

7 8 6

where 0 defines an empty cell

 1 2 3 
 
 4 0 5
 
 7 8 6
 
Minimum number of moves = 2

 1  2  3 
 
 4  0  5 
 
 7  8  6 

-------------------------------

 1  2  3 
 
 4  5  0 
 
 7  8  6 
 
------------------------------

 1  2  3 
 
 4  5  6 
 
 7  8  0 
