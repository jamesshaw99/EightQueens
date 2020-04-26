import java.util.Scanner;

 class Main {
	
	static String userQueen;																		//This is a string containing the coordinate that the user selected

	public static void main(String[] args) {
		
		Chessboard board = new Chessboard();														//Creates a new Chessboard object
		
		board.printSolution();																		//Prints the board at the beginning to the user can see which coordinates they can choose

		Scanner scan = new Scanner(System.in);														//Starts the scanner
		System.out.println("Where would you like to place the first queen?");						//Prompts the user to enter the coordinate for the first queen
		userQueen = scan.next();																	//Uses scanner to get a string from the user and put it into the userQueen variable
		userQueen = userQueen.toUpperCase();														//Ensures that the user input is in uppercase so I don't have to deal with upper and lower case separately
		while (userQueen.charAt(0) > 'H' || userQueen.charAt(1) > '8' || userQueen.length() > 2){	//If statements to check that the coordinate given by the user is a valid coordinate on the board
			System.out.println("This coordinate is not on the board. Please choose another:");		//If not a valid coordinate, give an error and prompt the user to give another coordinate
			userQueen = scan.next();																//Puts the new user coordinate in the userQueen variable
			userQueen = userQueen.toUpperCase();													//Ensures the input is uppercase
		}
		scan.close();																				//Closes the scanner
		board.solveNQ(userQueen);																	//Runs the solve N Queens algorithm
		
	}
	

}
