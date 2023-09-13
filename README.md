# topologicalSort
Certainly! Below is a README.md content you can use for this TopologicalSort program:

Topological Sort
This Java program performs a topological sort on a directed graph to linearly order its vertices. The program reads an input file to construct the graph and then computes the topological ordering of its vertices.

Classes & Features
VertexNode Class - Represents a vertex in the graph, holding its name, a reference to the next vertex, and a list of edges connected to it.
EdgeNode Class - Represents a directed edge between two vertices.
TopologicalSort Class - Holds the main logic for the graph, including methods to add vertices, add edges, print the graph, and compute its topological order.
How to Run
To execute the program:

Compile the program:

javac TopologicalSort.java


Run the program with the input file path as an argument:

java TopologicalSort <path_to_input_file>

The input file should contain a list of vertices on the first line and directed edges on the subsequent lines.

Example Input File:
A B C D E

A B

A C

B D

C D

D E

Output
The program will print the graph and then display the topological ordering of its vertices. If the graph contains a cycle (is not a DAG), it will indicate so.

Note
Make sure the input file is formatted correctly, i.e., vertices are listed on the first line separated by spaces, and edges are listed on the subsequent lines with a space between the two vertices representing the start and end of the edge
