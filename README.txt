CS 6385.501 - Algorithmic Aspects of Telecommunication Networks - F16
Assignment 2: FordFulkerson Algorithm

Contents of the folder
=================
nxr153230.zip contains

	-FordFulkersonRefinement1
	-FordFulkerson Documentation.pdf
	-README.txt

FordFulkerson Documentation.pdf file contains a brief explanation about the algorithm and some sample input and output for different graphs.

FordFulkersonRefinement1/src contains
	
	-Graph.java
	-Edge.java
	-Vertex.java
	-FordFulkerson.java
	-Driver.java

Driver.java is the driver program that takes the graph, source and sink as input and calculates the maximum flow possible in the graph from the source to the sink.

Edge.java- Class that represents an edge in the graph
Vertex,java-Class that represents a Vertex in the graph
Graph.java- Class that represents the graph
FordFulkerson.java-Class that implements Ford-Fulkerson Algorithm


Compilation and running instructions
 =============================
To compile the program, copy all the files to the same folder
	compilation command:   javac Driver.java
To run the program
	run command	: java Driver

Input format:
==========
<number of vertices>  <number of edges>
<from vertex> <to vertex> <capacity of the edge>
.
.
.
.
Source:
<source_vertex>
Sink:
<sink_vertex>
Sample Input:
=============
7 13
1 2 15
2 4 8
1 4 12
1 5 5
2 5 10
7 6 10
5 6 10
6 4 5
4 7 5
7 5 8
6 3 12
4 3 5
3 7 15
//<Prints the input graph>
Source:
1
Sink:
7