package alpha_6;

import java.util.Scanner;

public class board {
	int [][]board;
	int userTag, aiTag;
	Scanner n;
	
	public board() {
		this.board=new int[19][19];
		initializeBoard();
		userTag=1;
		n=new Scanner(System.in);
	}
	
	private void initializeBoard(){ //initialize the board and input 0 to all place
		System.out.println("initializing...");
		for(int i =0; i< 19; i++) {
			for(int j=0; j<19; j++) {
				this.board[i][j]=0;
			}
		}
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
	
	public boolean isValidInput(cor move1) { //return false for invalid, return true for valid input
		int x1=move1.getX();
		int y1=move1.getY();
		if(x1<0||y1<0) return false;
		else if(x1>18||y1>18) return false;
		else if(this.board[x1][y1]!=0) return false;
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
		System.out.println("x\\y\t0 1 2 3 4 5 6 7 8 9 a 1 2 3 4 5 6 7 8");
		for(int i =0; i< 19; i++) {
			System.out.printf("%2d\t", i);
			for(int j=0; j<19; j++) {
				System.out.printf("%d ", this.board[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public void enterInput(cor move1, cor move2) {
		this.board[move1.getX()][move1.getY()]=this.userTag;
		this.board[move2.getX()][move2.getY()]=this.userTag;
		toggleUserTag();		
	}
	
	public void enterInput(cor move1, int inputUserTag) {
		this.board[move1.getX()][move1.getY()]=inputUserTag;
	}
	
	public void toggleUserTag() { //userTag only switches between 1 and 2
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
			System.out.println("input syntax: x1 y1 x2 y2!! 0~18");
			x1=this.n.nextInt();
			y1=this.n.nextInt();
			x2=this.n.nextInt();
			y2=this.n.nextInt();
			this.n.nextLine();
			move1=new cor(x1, y1);
			move2=new cor(x2, y2);
		}while(checker=(!isValidInput(move1, move2)));		
			
		enterInput(move1, move2);
	}
	
	public boolean checkWinCondition() {//return true for no winner, false for a winner
		
		return true;
	}
	
	public void setGame() {
		int numOfRed;
		
		do {
			System.out.print("plz input the color of ai's turn(1 for black, 2for white): ");
			aiTag=this.n.nextInt();
			this.n.nextLine();
		}while(aiTag!=1&&aiTag!=2);
			
			
		boolean invalidInput;
		do {
			System.out.print("plz input the number of the red stone: ");
			numOfRed=this.n.nextInt();
			this.n.nextLine();
			if(numOfRed==0) return;
			
			System.out.print("plz input the cordinates (syntax: x1 y1 x2 y2...): ");
			invalidInput=false;
			cor []moves=new cor[numOfRed];
			int x=0, y=0;
			for(int i=0; i<numOfRed; i++) {
				x=this.n.nextInt();
				y=this.n.nextInt();
				moves[i]=new cor(x, y);
				if(!isValidInput(moves[i])) {
					System.out.println("error!");
					initializeBoard();
					invalidInput=true;
					break;
				}
				enterInput(moves[i], 3);
			}
			this.n.nextLine();
		}while(invalidInput);
		
	}
	
	public void runGame() {
		setGame();
		printBoard();
		do {
			if(userTag==aiTag) {
				evaluate.aiTurn(this.board, this.aiTag);
				printBoard();
			}
			getInput();
			printBoard();
		}while(checkWinCondition());
		n.close();
	}
	
}//end of class

