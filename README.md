# Movie Analyzer
This project analyzes the MovieLens data set which contains movie ratings from 671 users collected over a 20-year period (from 1995 to 2016). I created a graph using the MovieLens Data (where the nodes are movies) and a text-based interface that allows the user to explore the graph. 

# Features
The program gives the user 2 choices for defining adjacency in the graph. Once the graph is created the user is given options to print out statistics about the graph, print out node information, or display the shortest path between two nodes.

The files are divided into the following packages:
- analyzer - The analyzer package contains the main controller class for this assignment. This class contains a main method that builds the graph and allows the user to explore the graph
- data - The data package contains two classes for storing the MovieLens data: Movie.java and Reviewer.java. 
- graph - The graph package contains all classes that relate to graphs. This includes a graph class that implements a Graph interface and a Graph algorithms class which implements Dijkstra's algorithm and the Floyd-Warshall algorithm
- util - The util package contains all other classes that are necessary including a data loader file that reads in and parses the MovieLens data. This package also includes a priority queue file. 

# Credits
My professor, America Chambers, supplied the data and util packages (minus the priority queue implementation) as starter code for this assignment. This was also a pair programming assignment in which I worked with my classmate, Gennie Cheatham.

