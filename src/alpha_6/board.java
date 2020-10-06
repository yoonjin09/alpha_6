package alpha_6;

import java.util.Scanner;

public class board {
	int [][]board;
	int userTag;
	Scanner n;
	public board() {
		this.board=initializeBoard();
		userTag=1;
		n=new Scanner(System.in);
	}
	
	private int[][] initializeBoard(){ //initialize the board and input 0 to all place
		int[][] tempBoard= new int[19][19];
		for(int i =0; i< 19; i++) {
			for(int j=0; j<19; j++) {
				tempBoard[i][j]=0;
			}
		}
		return tempBoard;
	}

	public boolean isValidInput(cor move1, cor move2) { //return false for invalid, return true for valid input
		int x1=move1.getX();
		int x2=move2.getX();
		int y1=move1.getY();
		int y2=move2.getY();
		if(x1<0||y1<0||x2<0||y2<0) return false;
		else if(x1>18||y1>18||x2>18||y2>18) return false;
		else if(this.board[x1][y1]!=0) return false;
		else if(this.board[x2][y2]!=0) return false;
		else return true;
	}
	
	public boolean isFull() { // return false for not full, true for full board
		for(int i =0; i< 19; i++) {
			for(int j=0; j<19; j++) {
				if(this.board[i][j]==0) return false;
			}
		}
		return true;
	}
	
	public void printBoard() {
		System.out.println("x\\y\t1 2 3 4 5 6 7 8 9 a 1 2 3 4 5 6 7 8 9\n");
		for(int i =0; i< 19; i++) {
			System.out.printf("%d\t", i+1);
			for(int j=0; j<19; j++) {
				System.out.printf("%d ", this.board[i][j]);
			}
			System.out.println();
		}
	}
	public void enterInput(cor move1, cor move2) {
		this.board[move1.getX()][move1.getY()]=this.userTag;
		this.board[move2.getX()][move2.getY()]=this.userTag;
		toggleUserTag();		
	}
	
	public void toggleUserTag() {
		if(this.userTag==1) this.userTag=2;
		else if(this.userTag==2) this.userTag=1;
	}
	
	public void getInput() {
		if(isFull()) {
			System.out.println("The board is full");
			return;
		}
		
		int x1=0, x2=0, y1=0, y2=0;
		cor move1, move2;
		boolean checker=false;
				
		do { //checker for invalid input
			if(checker) System.out.println("wrong input! plz retry!");
			System.out.println("input syntax: x1 y1 x2 y2!!");
			x1=this.n.nextInt()-1;
			y1=this.n.nextInt()-1;
			x2=this.n.nextInt()-1;
			y2=this.n.nextInt()-1;
			this.n.nextLine();
			move1=new cor(x1, y1);
			move2=new cor(x2, y2);
		}while(checker=(!isValidInput(move1, move2)));		
			
		enterInput(move1, move2);
	}
	
	public boolean checkWinCondition() {
		return true;
	}
	
	public void runGame() {
		do {
			getInput();
			printBoard();
		}while(checkWinCondition());
		n.close();
	}
	
}//end of class

