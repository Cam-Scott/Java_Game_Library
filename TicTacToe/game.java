import java.util.Scanner;

public class game {	
	public static Scanner scan = new Scanner(System.in);
	public static int row, col;
	public static char[][] board = new char[3][3];
	public static int turns;
	
	public static void main(String[] args) {
		System.out.print("Type play: ");
		if (scan.next().toLowerCase().equals("play")) {
			System.out.println();
			play();
		}
	}

	public static void play() {
		initializeBoard();
		while (true) {
			xTurn();
			oTurn();
		}
	}
	
	public static void initializeBoard() {
		turns = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = '*';
			}
		}
		printBoard();
	}
	
	public static void printBoard() {
		for (int i = 0; i < 3; i++) {
			System.out.println("-------------");
			for (int j = 0; j < 3; j++) {
				if (j==0) {
					System.out.print("| ");
				}
				System.out.print(board[i][j] + " | ");
			}
			System.out.println();
		}
		System.out.println("-------------");
	}
	
	public static void xTurn() {
		turns += 1;
		placeMove('X');
		printBoard();
	}
	
	public static void oTurn() {
		turns += 1;
		placeMove('O');
		printBoard();
	}
	
	public static void placeMove(char player) {
		if (turns > 9) {
			gameOver('*');
		}
		System.out.println(player + "'s turn.");
		System.out.print("Enter row: ");
		row = scan.nextInt() - 1;
		System.out.print("Enter column: ");
		col = scan.nextInt() - 1;
		if (row > 2 || row < 0 || col > 2 || col < 0) {
			System.out.println("\nInvalid, try again.\n");
			placeMove(player);
		} else {
			System.out.println();
			if (board[row][col] == '*') {
				board[row][col] = player;
				checkWinner(player, row, col);
			} else {
				System.out.println("Invalid, try again.\n");
				placeMove(player);
			}
		}
	}
	
	public static void checkWinner(char player, int row, int col) {
		//check vertical
		if (board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
			gameOver(player);
		}
		//check horizontal
		if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
			gameOver(player);
		}
		//check diagonals
		if (board[1][1] != '*') {
			if ((board[0][0] == board[1][1] && board[1][1] == board[2][2]) || (board[0][2] == board[1][1] && board[1][1] == board[2][0])) {
				gameOver(player);
			}
		}
	}
	
	public static void gameOver(char winner) {
		printBoard();
		if (winner != '*') {
			System.out.println("Player " + winner + " has won!\n");
		} else {
			System.out.println("Players tied.");
		}
		System.out.println("To play again type \"reset\".");
		if (scan.next().toLowerCase().equals("reset")) {
			play();
		}
	}
}