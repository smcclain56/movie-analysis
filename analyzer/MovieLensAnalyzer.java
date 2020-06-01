package analyzer;
import java.util.Scanner;
import java.util.Map;
import data.*;
import graph.*;
import util.*;
import java.util.InputMismatchException;

/**
 * Please include in this comment you and your partner's name and describe any extra credit that you implement 
 */
public class MovieLensAnalyzer{
	public static void main(String[] args){
		// Your program should take two command-line arguments: 
		// 1. A ratings file
		// 2. A movies file with information on each movie e.g. the title and genres		
		if(args.length != 2){
			System.err.println("Usage: java MovieLensAnalyzer [ratings_file] [movie_title_file]");
			System.exit(-1);
		}		
		System.out.println("========================= Welcome to MoveLens Analyzer ==================");
		System.out.println("The files being analyzed are: ");
		System.out.println(args[0]);
		System.out.println(args[1]);
		DataLoader loader = new DataLoader();
		loadingData(args[0], args[1], loader);
		
		//shows the user options for establishing adjacency
		System.out.println("There are 3 choices for defining adjacency:");
		System.out.println("[Option 1] u and v are adjacent if the same 12 users gave the same rating to both movies.");
		System.out.println("[Option 2] u and v are adjacent if the same 12 users watch both movies (regardless of rating)");
		System.out.println("Choose an option to build the graph. (1-2)");
		
		//takes in the option for establishing adjacency
		Scanner scan = new Scanner(System.in);
		Graph<Integer> g = new Graph<Integer>();
		try {
			String option = scan.nextLine();
			if(option.equals("1")) {
				g = buildGraph1(loader);
			}
			else if(option.equals("2")) {
				g = buildGraph2(loader);
			}else {
				System.out.println("Please enter a number 1-2.");
				scan.close();
			}
		}catch(InputMismatchException e) {
			throw new InputMismatchException();
		}

		String option2;
		Scanner scan2 = new Scanner(System.in);
		try {
			do {
				//prints out the analyzing options for the user
				System.out.println();
				System.out.println("[Option 1] Print out statistics about the graph.");
				System.out.println("[Option 2] Print node information.");
				System.out.println("[Option 3] Display shortest path between two nodes.");
				System.out.println("[Option 4] Quit.");

				//prompts the user to choose an option when analyzing the graph
				System.out.println("Choose an option. (1-4)");
				option2 = scan.nextLine();
				//printing out option 1 information
				if(option2.equals("1")) {
					graphStats(g);
				//printing out option 2 information
				}else if(option2.equals("2")) {
					System.out.println("Enter movie id (1-1000): ");
					int movieID = scan2.nextInt();
					nodeStats(loader, g, movieID);
					//TODO: Find the movie in the graph
					//call node stats on the movie
				//printing out option 3 information	
				}else if(option2.equals("3")) {
					System.out.println("Enter starting node (1-1000): ");
					int startNode = scan2.nextInt();
					System.out.println("Enter ending node: ");
					int endNode = scan2.nextInt();
					
					//TODO: Call the algorithm for the shortest path between the two nodes
				}else if(!option2.equals("4")) {
					System.out.println("Please enter a number 1-4.");
				}
			}while(!option2.equals("4"));
			System.out.println("Exiting... bye.");
			System.exit(0);
		}catch(InputMismatchException e) {
			throw new InputMismatchException();
		}
		
		
		


	}
	
/**
 * Static method to build the graph. 
 */
public static Graph<Integer> buildGraph1(DataLoader loader){
	int sharedUsers = 0;
	Graph<Integer> graph = new Graph<Integer>();
	Map<Integer, Movie> movies = loader.getMovies();
	for(Movie m : movies.values()) {
		graph.addVertex(m.getMovieId());
		for(Movie k : movies.values()) {
			graph.addVertex(k.getMovieId());
			sharedUsers = 0;
			if(m.equals(k)==false) {
				for(Integer u : m.getRatings().keySet()) {
					if(k.getRatings().containsKey(u)) {
						//System.out.println(m.getRatings().get(u) + "\t"+ k.getRatings().get(u));						System.out.println();
						if(m.getRatings().get(u).equals(k.getRatings().get(u))) {		
							sharedUsers++;
						}
					}
					if(sharedUsers>=12) {
						graph.addEdge(m.getMovieId(), k.getMovieId());
						graph.addEdge(k.getMovieId(), m.getMovieId());
						break;
					}
				}
			}
		}
		
	}
	return graph;
}

/**
 * Static method to build the graph.
 */
public static Graph<Integer> buildGraph2(DataLoader loader) {
	int sharedUsers = 0;
	Graph<Integer> graph = new Graph<Integer>();
	Map<Integer, Movie> movies = loader.getMovies();
	for(Movie m : movies.values()) {
		graph.addVertex(m.getMovieId());
		for(Movie k : movies.values()) {
			graph.addVertex(k.getMovieId());
			sharedUsers = 0;
			if(m.equals(k)==false) {
				for(Integer u : m.getRatings().keySet()) {
					if(k.getRatings().containsKey(u) == true) {
						sharedUsers++;
					}
					if(sharedUsers>=12) {
						//System.out.println("Adding Edge: " + m.getMovieId() + " to " + k.getMovieId());
						graph.addEdge(m.getMovieId(), k.getMovieId());
						graph.addEdge(k.getMovieId(), m.getMovieId());
						break;
					}
				}
			}
		}
		
	}
	return graph;
}

/**
 * Static method that uses Data Loader to read in the data.
 */
public static void loadingData(String movieFile, String reviewFile, DataLoader loader) {
	loader.loadData(movieFile, reviewFile);
	//loader.printMovieList();
	//loader.printReviewerList();
}

/**
 * Static method that print out Option 1 statistics.
 * @param <V>
 * 
 * @param Graph g, graph to be analyzed
 */
public static void graphStats(Graph<Integer> g) {
	int vertices = g.numVertices();
	int edges = g.numEdges();
	System.out.println("Number of nodes: " + vertices);
	System.out.println("Number of edges: " + edges);
	double density = (edges / (double) (vertices*(vertices-1)));
	System.out.println("Density of the graph: " + density);
	System.out.println("Maximum degree: " + g.maxDegree());
	int[][] shortestPath = g.floydWarshall(g);
	longPath(shortestPath);
	//TODO: average length of shortest paths
	double avg = avgShortestPath(shortestPath);
	System.out.println("Average length of the shortest paths: " + avg );
}

/**
 * Static method that print out Option 2 statistics.
 * 
 * @param Graph g, graph to be analyzed
 */
public static void nodeStats(DataLoader loader, Graph<Integer> g, Integer movieID) {
	Map<Integer, Movie> movies = loader.getMovies();
	Movie movie = movies.get(movieID);
	System.out.println(movie.toString());
	System.out.println("Neighbors: ");
	for (Integer n : g.getNeighbors(movieID)) {
		System.out.println(movies.get(n).getTitle());
	}
}




/**
 * Averaging the shortest Paths. 
 */
public static double avgShortestPath(int[][]shortestPath) {
	double totalLength = 0.0;
	for(int i=1; i<shortestPath.length; i++) {
		for(int j=1; j<shortestPath.length; j++) {
			totalLength += shortestPath[i][j];
		}
	}
	double avgPath = (double)(totalLength)/(shortestPath.length*shortestPath.length);
	return avgPath;
}


/**
 * Finding the Longest Shortest Path.
 */
public static int longPath(int[][] shortestPath) {
	int longPath = 0;
	int start = 0;
	int end = 0;
	for(int i=1; i<shortestPath.length; i++) {
		for(int j=1; j<shortestPath.length; j++) {
			if(shortestPath[i][j]>longPath && shortestPath[i][j] != Integer.MAX_VALUE) {
				System.out.println("Here");
				longPath = shortestPath[i][j];
				start = i;
				end = j;
			}
		}
	}
	System.out.println("Diameter: " + longPath + " (from " + start + " to " + end + ")");
	return longPath;
	
}
}

