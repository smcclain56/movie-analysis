# Movie Analyzer
This project analyzes the MovieLens data set which contains movie ratings from 671 users collected over a 20-year period (from 1995 to 2016). I created a graph using the MovieLens Data (where the nodes are movies) and a text-based interface that allows the user to explore the graph. 

# Features
The program gives the user 2 choices for defining adjacency in the graph. Once the graph is created the user is given options to print out statistics about the graph, print out node information, or display the shortest path between two nodes.

The files are divided into the following packages:
- analyzer - The analyzer package contains the main controller class for this assignment. This class contains a main method that builds the graph and allows the user to explore the graph
- data - The data package contains two classes for storing the MovieLens data: Movie.java and Reviewer.java. 
- graph - The graph package contains all classes that relate to graphs. This includes a graph class that implements a Graph interface and a Graph algorithms class which implements Dijkstra's algorithm and the Floyd-Warshall algorithm
- util - The util package contains all other classes that are necessary including a data loader file that reads in and parses the MovieLens data. This package also includes a priority queue file. 

# Example Session
========================= Welcome to MoveLens Analyzer ==================
The files being analyzed are: 
./src/ml-latest-small/movies.csv
./src/ml-latest-small/ratings.csv

There are 2 choices for defining adjacency:
[Option 1] u and v are adjacent if the same 12 users gave the same rating to both movies.
[Option 2] u and v are adjacent if the same 12 users watch both movies (regardless of rating)
Choose an option to build the graph. (1-2)
1
Building graph...

[Option 1] Print out statistics about the graph.
[Option 2] Print node information.
[Option 3] Display shortest path between two nodes.
[Option 4] Quit.
Choose an option. (1-4)
1
Number of nodes: 1000
Number of edges: 31552
Density of the graph: 0.03158358358358358
Maximum degree: 303(node 176)
Diameter: 6 (from 69 to 981)
Average length of the shortest paths: 2.3549340638338534

[Option 1] Print out statistics about the graph.
[Option 2] Print node information.
[Option 3] Display shortest path between two nodes.
[Option 4] Quit.
Choose an option. (1-4)
2
Enter movie id (1-1000): 
500
(500) Lady and the Tramp
Num Ratings: 51
Genres: Animation Romance Comedy Children 

Neighbors: 
	Lion King, The
	Jurassic Park
	Pinocchio
	Cinderella
	Mary Poppins
	E.T. the Extra-Terrestrial
	Raiders of the Lost Ark
	Groundhog Day
	Bambi
	Saving Private Ryan
	Jungle Book, The
	Peter Pan
	Sleeping Beauty
	Bug's Life, A
	Ghostbusters
	Big
	Who Framed Roger Rabbit?

[Option 1] Print out statistics about the graph.
[Option 2] Print node information.
[Option 3] Display shortest path between two nodes.
[Option 4] Quit.
Choose an option. (1-4)
3
Enter starting node (1-1000): 
69
Enter ending node: 
981
Harry Potter and the Deathly Hallows: Part 2==>Harry Potter and the Deathly Hallows: Part 1
Harry Potter and the Deathly Hallows: Part 1==>Harry Potter and the Goblet of Fire
Harry Potter and the Goblet of Fire==>Incredibles, The
Incredibles, The==>Lion King, The
Lion King, The==>Happy Gilmore
Happy Gilmore==>Billy Madison

[Option 1] Print out statistics about the graph.
[Option 2] Print node information.
[Option 3] Display shortest path between two nodes.
[Option 4] Quit.
Choose an option. (1-4)
4
Exiting... bye.

# Credits
My professor, America Chambers, supplied the data and util packages (minus the priority queue implementation) as starter code for this assignment. This was also a pair programming assignment in which I worked with my classmate, Gennie Cheatham.

