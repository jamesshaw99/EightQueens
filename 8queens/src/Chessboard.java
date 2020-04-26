
public class Chessboard {
	
	static int[][] board;																			//This is a 2D array which acts as the board, storing all of the positions on the board
	static String userQueen;																		//This is a string containing the coordinate that the user selected
	int solutionNo = 0;																				//This is an integer value that tracks the number of solutions for the given queen
	int x, y;																						//These integers are the x and y coordinates of the given queen
	
	public Chessboard() {
		board = new int[8][8];																		//Creates a new 2D array which is 8 columns by 8 rows
		for (int i = 0; i < 8; i++) {																//loops through each of the rows
			for (int j = 0; j < 8; j++) {															//loops through each of the columns
				board[i][j] = 0;																	//Sets all values to 0 to start - 0 means no queen at this position
			}
		}
	}
	
	/* This method is used to print out the board.
	 * INPUTS:
	 * 		int board[][] --> takes a 2D array of integers called board as an input
	 * OUTPUTS:
	 * 		N/A	
	 */
	void printSolution() {
		
		if (solutionNo > 0) {																		//This ensures that the solutionNo does not print the first time that this method is run. This is because i print the blank board at the beginning which is not showing a solution.
			System.out.println("Solution Number " + solutionNo);									//Prints out the solution number to make it easy to see how many solutions there are for this queen.
		}
		for (int i = 0; i < 9; i++) {																//Loops through all of the rows of the board - loops 9 times instead of 8 to give the boarder
			for (int j = 0; j < 9; j++) {															//Loops through all of the columns of the board
				if (i == 0 && j == 0) {																//If looking at the top left corner
					System.out.print("\\|");														//Print \| to start the boarder -> has 2 \s as this is an escape character
				} else if (i == 0) {																//If looking at the first row
					System.out.print("_" + (char)(j+64) + "_");										//Print the correct letter of the alphabet based on the column number
				} else if (j == 0) {																//If looking at the first column
					System.out.print(i + "|");														//Print the row number
				} else {																			//If not the first column or row
					if (board[i-1][j-1] == 1) {														//If the value at this position is 1 -> is a queen
						System.out.print(" Q ");													//Print Q to represent a queen at this position
					} else {																		//If not a queen
						System.out.print(" . ");													//Print . to represent no queen at this position
					}
				}
			}
			System.out.println();																	//Move to the next line
		}
		
		solutionNo++;																				//Increments solutionNo by one 
		
	}
	
	/* This method is used to check if the current position is a valid place to put a new queen.
	 * INPUTS:
	 * 		int board[][] --> takes a 2D array of integers called board as an input
	 * 		int row --> an integer value holding the row that we want to check -> the x coordinate
	 * 		int col --> an integer value holding the column that we want to check -> the y coordinate
	 * OUTPUTS:
	 * 		true --> This means that this coordinate is safe -> not within attacking range of another queen
	 * 		false --> This means that this coordinate is not safe -> is within attacking range of another queen
	 */
	boolean isSafe(int board[][], int row, int col) {
		
		int i, j;																					//Create variables i and j for the loops so I don't have to do this within the loops
		
		for (i = 0; i < 8; i++) {																	//Loops through the columns
			if (board[row][i] == 1) {																//If there is a queen in this row - only need to check queens to the left as i'm inserting queens from the left column to the right column
				return false;																		//Return false to let the program know this is not a safe position
			}
		}
		
		for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {										//Loops through all of the upper diagonals on the left of the given coordinate
			if (board[i][j] == 1) {																	//If there is a queen on this upper diagonal
				return false;																		//Return false to let the program know this is not a safe position
			}
		}
		
		for (i = row, j = col; j >= 0 && i < 8; i++, j--) {											//Loops through all of the lower diagonals on the left of the given coordinate
			if (board[i][j] == 1) {																	//If there is a queen on this lower diagonal
				return false;																		//Return false to let the program know this is not a safe position
			}
		}
		
		for (i = row, j = col; i >= 0 && j < 8; i--, j++) {										//Loops through all of the upper diagonals on the right of the given coordinate
			if (board[i][j] == 1) {																	//If there is a queen on this upper diagonal
				return false;																		//Return false to let the program know this is not a safe position
			}
		}
		
		for (i = row, j = col; j < 8 && i < 8; i++, j++) {											//Loops through all of the lower diagonals on the right of the given coordinate
			if (board[i][j] == 1) {																	//If there is a queen on this lower diagonal
				return false;																		//Return false to let the program know this is not a safe position
			}
		}
		
		return true;																				//If this method hasn't found any queens in the same row or diagonals then return true to let the program know this coordinate is safe
		
	}
	
	/* This method is used as the main control method of my solution. This iterates through each column from left to right and feeds all the correct solutions to the print function
	 * INPUTS:
	 * 		int board[][] --> takes a 2D array of integers called board as an input
	 * 		int col --> an integer value holding the column that we want to check -> the y coordinate
	 * OUTPUTS:
	 * 		true --> This means that there is a valid solution to this problem
	 * 		false --> This means that there is not a valid solution to this problem
	 */
	boolean solveNQUtil (int board[][], int col) {
		boolean res = false;																		//A boolean variable that holds the result of if a solution is found or not
		if (col == 8) {																				//If all the columns have been checked -> columns 0-7
			printSolution();																		//Print this solution
			System.out.println();																	//Adds a blank line under to separate the different solutions
			return true;																			//Returns true to let the program know that a solution has been found
		}
		if (col == x) {																				//If this column is the one where the users queen is placed
			res = solveNQUtil(board, col+1);														//skip to the next column
		} else {
			for (int i = 0; i < 8; i++) {															//loops through the rows on the board
				if (isSafe(board, i, col)) {														//checks to see if the current position being checked is safe -> if yes, run the code in the if statement, if not then move on to the next iteration of the loop
					board[i][col] = 1;																//Sets this coordinate to 1 indicating a queen has been placed here
					res = solveNQUtil(board, col+1) || res;											//I have used short circuit evaluation to say if the next column has a valid position then set res to true, else keep res as false and set the coordinate back to 0
					board[i][col] = 0;																//Sets the coordinate back to 0 of there is no valid queen position in the next column -> backtracking
				}
			}
		}
		return res;																					//Returns the value of the res variable - true if a solution is found or false if a solution is not found
	}
	
	/* This method is used to run the program multiple times to find more than one solution
	 * INPUTS:
	 * 		String user --> This is the users inputted string
	 * OUTPUTS:
	 * 		N/a
	 */
	void solveNQ(String user) {
		
		placeQueen(user);																			//This runs the placeQueen method which places the users queen on the board
		
		System.out.println("All possible solutions for this queen are: ");							//Tells the user that the program is going to display all possible solutions
		if (solveNQUtil(board,0) == false) {														//Keeps running the solveNQUtil method, starting from the 0th column, and checks to see if this returns false - indicating no solution found
			System.out.print("Solution does not exist");											//Lets the user know that there is no solution for this coordinate
		}	
		
	}
	
	/* This method is used to run the program multiple times to find more than one solution
	 * INPUTS:
	 * 		String userQueen --> This is the users inputted string
	 * OUTPUTS:
	 * 		N/a
	 */
	void placeQueen(String userQueen){
		for (int i = 0; i < 8; i++) {																//Loops through all the columns of the board
			for (int j = 0; j < 8; j++) {															//Loops through all the rows of the board
				char character = (char)(i+65);														//Creates a character based on the current column being looked at
				if (character == userQueen.charAt(0) && (j + 1 == (userQueen.charAt(1) - '0'))) {	//Checks to see if the current position being looked at matched with the coordinate given by the user
					board[j][i] = 1;																//Place the users queen at this position
					x = i;																			//set the x coordinate of the queen
					y = j;																			//set the y coordinate of the queen
				}
			}
		}		
	}

}
