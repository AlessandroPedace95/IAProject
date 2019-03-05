package com.sfo.games.puzzlebobble.stages;

//Java program to find path between two 
//cell in matrix 
class Path { 

	// method for finding and printing 
	// whether the path exists or not 
	public boolean isPath(int matrix[][], int y, int x) 
	{ 
		// defining visited array to keep 
		// track of already visited indexes 
		boolean visited[][] = new boolean[y][x]; 

		// flag to indicate whether the path exists or not 
		boolean flag=false; 

		for(int i=0;i<y;i++) 
		{ 
			for(int j=0;j<x;j++) 
			{ 
				// if matrix[i][j] is source 
				// and it is not visited 
				if(matrix[i][j]==1 && !visited[i][j]) 

					// starting from i, j and then finding the path 
					if(isPath(matrix, i, j, visited)) 
					{ 
						flag=true; // if path exists 
						break; 
					} 
			} 
		} 
		if(flag) { 
			System.out.println("YES"); 
			return true;
		}
		else {
			System.out.println("NO"); 
			return false;
		}
	} 


	// method for checking boundries 
	public static boolean isSafe(int i, int j, int matrix[][]) 
	{ 

		if(i>=0 && i<matrix.length && j>=0
				&& j<matrix[0].length) 
			return true; 
		return false; 
	} 

	// Returns true if there is a path from a source (a 
	// cell with value 1) to a destination (a cell with 
	// value 2) 
	public static boolean isPath(int matrix[][], 
			int i, int j, boolean visited[][]){ 

		// checking the boundries, walls and 
		// whether the cell is unvisited 
		if(isSafe(i, j, matrix) && matrix[i][j]!=0
				&& !visited[i][j]) 
		{ 
			// make the cell visited 
			visited[i][j]=true; 

			// if the cell is the required 
			// destination then return true 
			if(matrix[i][j]==2) 
				return true; 

			// traverse up 
			boolean up = isPath(matrix, i-1, j, visited); 

			// if path is found in up direction return true 
			if(up) 
				return true; 

			// traverse left 
			boolean left = isPath(matrix, i, j-1, visited); 

			// if path is found in left direction return true 
			if(left) 
				return true; 

			//traverse down 
			boolean down = isPath(matrix, i+1, j, visited); 

			// if path is found in down direction return true 
			if(down) 
				return true; 

			// traverse right 
			boolean right = isPath(matrix, i, j+1, visited); 

			// if path is found in right direction return true 
			if(right) 
				return true; 
		} 
		return false; // no path has been found 
	} 

	// driver program to check above function 

	//public static void main (String[] args) { 

		/*
		 * A value of cell 1 means Source.
	   A value of cell 2 means Destination.
	   A value of cell 3 means Blank cell.
       A value of cell 0 means Blank Wall.	
		 */

	/*	int matrix[][] = {{3, 0, 3, 0, 1, 0, 3, 0}, 
				{3, 0, 3, 0, 3, 0, 3, 0}, 
				{0, 3, 0, 3, 0, 3, 0, 3}, 
				{3, 3, 0, 3, 0, 3, 0, 3},
				{3, 0, 3, 0, 3, 0, 3, 3},
				{3, 3, 3, 3, 3, 3, 3, 3},
				{3, 3, 3, 3, 3, 3, 3, 3},
				{3, 3, 3, 3, 3, 3, 3, 3},
				{3, 3, 3, 3, 3, 3, 3, 3},
				{0, 0, 0, 0, 2, 0, 0, 0},
	*/

	//	}; 

		//isPath(matrix, 10, 8);			 // calling isPath method 
	//} 
} 

/* This code is contributed by Madhu Priya */

