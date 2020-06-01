package graph;
import graph.Graph;
import analyzer.MovieLensAnalyzer;
//import java.util.Arrays;


public class GraphAlgorithms {
	/**
	 * Algorithm for Floyd-Warshall
	 */
	public static int[][] floydWarshall(Graph<Integer>g) {
		int n = g.numVertices();
		//System.out.println(g.numEdges());
		int[][][] results = new int[n+1][n+1][n+1];
		
		//initializing the array
		for(int i=1; i<=n; i++) {
			//System.out.print("\ni: " + i + " -->"); 
			for(int j=1; j<=n; j++) {
				if(g.edgeExists(i, j)) {
					results[0][i][j] = 1;
					//System.out.print(results[0][i][j]+  " ");
				}else if(i==j) {
					results[0][i][j] = 0;
				}
				else {
					results[0][i][j] = Integer.MAX_VALUE;
					//System.out.print(results[0][i][j] +  " ");
				}
			}
		}
		//System.out.println("\n");
		//finish filling out the array 
		for(int k=1; k<=n; k++) {
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=n; j++) {
					int min1;
					if(results[k-1][i][k] == Integer.MAX_VALUE || results[k-1][k][j] == Integer.MAX_VALUE) {
						min1 = Integer.MAX_VALUE;
					}else {
						min1 = results[k-1][i][k] + results[k-1][k][j];
						
					}
					
					int min2 = results[k-1][i][j];
					if(min1<=min2) {
						results[k][i][j] = min1;
						//System.out.println("MIN VALUE: " + results[k][i][j]);
					}else {
						//System.out.println("K: " + k + "I: " + i + "J: " + j);
						results[k][i][j] = min2;
						//System.out.println("MIN VALUE: " + results[k][i][j]);
					}
				}
			}
		}
		//System.out.println("find: "+ results[n][0][0]);
//		int[][] resultss = results[n];
//		for(int i=1; i<resultss.length; i++) {
//			//System.out.println(resultss);
//			for(int j=1; j<resultss.length;j++) {
//				System.out.print(resultss[i][j] + "\t");
//			}
//			System.out.println();
//		}
		
		
		return results[n];//the 2D Array that holds the shortest path;	
	}
}
