package alpha_6;

public class evaluate { //
		board scoreForBlack, scoreForwhite;
		static cor move= new cor();
	/*
	 * read board left to right, top to bottom, left top to right bottom, 
	 * right top to left bottom, if there is a stone, hand read direction, cor, board, usertag to evaGIveScore()
	*/
	public static void readBoWDir(int[][] rawData, int userTag) {
		int k=0;
		int[] oneRow=new int[19];
		for(int i =0; i<19; i++) { //top down 
			for(int j=0; j<19; j++) {
				if(rawData[i][j]!=0&&rawData[i][j]!=3) {
					move.x=i;
					move.y=0;
					for(j=0; j<19; j++) {
						oneRow[j]=rawData[i][j];
					}
					evaGiveScoreC1(oneRow, 19, userTag, move, 0);
					break;
				}
			}
		}
		
		for(int i=0; i<19; i++) { //left right
			for(int j=0; j<19; j++) {
				if(rawData[j][i]!=0&&rawData[j][i]!=3) {
					move.x=0;
					move.y=i;
					for(j=0; j<19; j++) {
						oneRow[j]=rawData[j][i];
					}
					evaGiveScoreC1(oneRow, 19, userTag, move, 1);
					break;
				}
			}
		}
		
		for(int i=0; i<14; i++) {//top left to right bottom, top left to top right
			k=i;
			for(int j=0; j<19-i; j++) {
				if(rawData[k][j]!=0&&rawData[k][j]!=3) {
					move.x=i;
					move.y=0;
					k=i;
					for(j=0; j<19-i; j++) {
						oneRow[j]=rawData[k][j];
						k++;
					}
					evaGiveScoreC1(oneRow, 19-i, userTag, move, 2);
					break;
				}
				k++;
			}
		}
		for(int i=1; i<14; i++) {//top left to right bottom, left top to left down
			k=0;
			for(int j=i; j<19; j++) {
				if(rawData[k][j]!=0&&rawData[k][j]!=3) {
					move.x=0;
					move.y=i;
					k=0;
					for(j=i; j<19; j++) {
						oneRow[k]=rawData[k][j];
						k++;
					}
					evaGiveScoreC1(oneRow, k, userTag, move, 2);
					break;
				}
				k++;
			}
		}
		
		for(int i=18; i>4; i--) {//top right to left bottom, top right to top left
			k=i;
			for(int j=0; j<i; j++) {
				if(rawData[k][j]!=0&&rawData[k][j]!=3) {
					move.x=i;
					move.y=0;
					k=i;
					for(j=0; j<i; j++) {
						oneRow[j]=rawData[k][j];
						k--;
					}
					evaGiveScoreC1(oneRow, i+1, userTag, move, 3);
					break;
				}
				k--;	
			}
		}
		for(int i=1; i<14; i++) {//top right to left bottom, right top to right bottom
			k=18;
			for(int j=i; j<18; j++) {
				if(rawData[k][j]!=0&&rawData[k][j]!=3) {
					move.x=18;
					move.y=i;
					k=18;
					int pos=0;
					for(j=i; j<18; j++) {
						oneRow[pos]=rawData[k][j];
						k--;
						pos++;
					}
					evaGiveScoreC1(oneRow, 18-i, userTag, move, 3);
					break;
				}
				k--;
			}
		}
		
		return;
	}
	/*
	 * evaluate and give score to new score board according to input
	 * C 1is for first move, C2 is for second move, the score for both cases are different. 
	 * 
	 */
	public static void evaGiveScoreC1(int[] oneRow, int rowLength, int userTag, cor move, int dir) { 
		System.out.printf("x:%2d y:%2d length:%2d dir:%d  ", move.x, move.y, rowLength, dir);
		for(int i=0; i<rowLength; i++) {
			System.out.printf("%d ", oneRow[i]);
		}
		System.out.println();
		return;
	}
	
	public void evaGiveScoreC2() {
		return;
	}
	
}

/*
 * 토큰을 만들자. 바운더리 사이의 거리(칸수)로 돌이 죽었는지를 확인가능. 아군돌의 갯수로 종류분류. 
 */