package alpha_6;

public class evaluate { //
	// board scoreForBlack, scoreForwhite;
	// static int [][]totalScore= new int[19][19];
	static cor move = new cor();
	final static int LEFTRIGHT = 0, TOPDOWN = 1, TOPLBOTR = 2, BOTLTOPR = 3;
	
	
	/*
	 * 
	 */

	public static void aiTurn(int[][] board, int aiTag) {
		int oppoTag;
		if (aiTag == 1)
			oppoTag = 2;
		else
			oppoTag = 1;
		int[][] scoreBoard = new int[19][19];
		readBoWDir(board, scoreBoard, aiTag);
		readBoWDir(board, scoreBoard, oppoTag);

	}
	/*
	 * read board left to right, top to bottom, left top to right bottom, left
	 * bottom to right top if there is a stone, hand direction, cor of the beginning
	 * of the row, copied data of the row, usertag to evaGIveScore() all direction
	 * start from (0,0) except left bottom to right top which starts from (18,0)
	 * (0,0) being left top corner; (18,0) being left bottom
	 */

	public static void readBoWDir(int[][] rawData, int[][] scoreBoard, int userTag) {
		int k = 0;
		int[] oneRow = new int[19];

		for (int i = 0; i < 19; i++) { // left right
			for (int j = 0; j < 19; j++) {
				// if(rawData[j][i]!=0&&rawData[j][i]!=3) {
				if (rawData[j][i] == userTag) {

					move.x = 0;
					move.y = i;
					for (j = 0; j < 19; j++) {
						oneRow[j] = rawData[j][i];
					}
					evaGiveScoreC1(scoreBoard, oneRow, 19, userTag, move, LEFTRIGHT);
					break;
				}
			}
		}

		for (int i = 0; i < 19; i++) { // top down
			for (int j = 0; j < 19; j++) {
				// if(rawData[i][j]!=0&&rawData[i][j]!=3) {
				if (rawData[i][j] == userTag) {
					move.x = i;
					move.y = 0;
					for (j = 0; j < 19; j++) {
						oneRow[j] = rawData[i][j];
					}
					evaGiveScoreC1(scoreBoard, oneRow, 19, userTag, move, TOPDOWN);
					break;
				}
			}
		}

		for (int i = 0; i < 14; i++) {// top left to right bottom, top left to top right
			k = i;
			for (int j = 0; j < 19 - i; j++) {
				// if(rawData[k][j]!=0&&rawData[k][j]!=3) {
				if (rawData[k][j] == userTag) {
					move.x = i;
					move.y = 0;
					k = i;
					for (j = 0; j < 19 - i; j++) {
						oneRow[j] = rawData[k][j];
						k++;
					}
					evaGiveScoreC1(scoreBoard, oneRow, 19 - i, userTag, move, TOPLBOTR);
					break;
				}
				k++;
			}
		}
		for (int i = 1; i < 14; i++) {// top left to right bottom, left top to left down
			k = 0;
			for (int j = i; j < 19; j++) {
				// if(rawData[k][j]!=0&&rawData[k][j]!=3) {
				if (rawData[k][j] == userTag) {
					move.x = 0;
					move.y = i;
					k = 0;
					for (j = i; j < 19; j++) {
						oneRow[k] = rawData[k][j];
						k++;
					}
					evaGiveScoreC1(scoreBoard, oneRow, k, userTag, move, TOPLBOTR);
					break;
				}
				k++;
			}
		}

		for (int i = 18; i > 4; i--) {// top right to left bottom, top right to top left
			k = i;
			for (int j = 0; j < i; j++) {
				// if(rawData[k][j]!=0&&rawData[k][j]!=3) {
				if (rawData[k][j] == userTag) {
					move.x = i;
					move.y = 0;
					k = i;
					for (j = 0; j < i; j++) {
						oneRow[j] = rawData[k][j];
						k--;
					}
					evaGiveScoreC1(scoreBoard, oneRow, i + 1, userTag, move, BOTLTOPR);
					break;
				}
				k--;
			}
		}
		for (int i = 1; i < 14; i++) {// top right to left bottom, right top to right bottom
			k = 18;
			for (int j = i; j < 18; j++) {
				// if(rawData[k][j]!=0&&rawData[k][j]!=3) {
				if (rawData[k][j] == userTag) {
					move.x = 18;
					move.y = i;
					k = 18;
					int pos = 0;
					for (j = i; j < 18; j++) {
						oneRow[pos] = rawData[k][j];
						k--;
						pos++;
					}
					evaGiveScoreC1(scoreBoard, oneRow, 18 - i, userTag, move, BOTLTOPR);
					break;
				}
				k--;
			}
		}

		return;
	}
	/*
	 * evaluate and give score to new score board according to input C 1is for first
	 * move, C2 is for second move, the score for both cases are different.
	 * 
	 */
	/*
	 * start_index_x,y 시작하는 좌표 direction 방 userTag_confirm 흑인지 백인지 확인 arraylength 는
	 * 넘어오는 array의 길이 (대각선 때문에) one_rawData[] 는 array 값
	 */

	public static void evaGiveScoreC1(int[][] scoreBoard, int one_rawData[], int arraylength, int usertag, cor move,
			int direction) {
		// int[][] totalScore = new int[19][19]; // 이거는 일단 없는거라고 생각하자

		int[] dummycell = new int[19]; // 만약 상대편 돌에 의해 쓰레기 cell 이 나올 때를 대비.
		int notusertag = 0; // usertag 의 반대. 1이면 2, 2이면 1.
		int first_notusertag = -1; // dummycell을 살펴볼 때 만약 notusertag가 있다면 위치를 기억하기 위한 수
		int gab_notusertag = 0;

		System.out.println("debug-------");
		System.out.println("dir: " + direction + " | pos x: " + move.x + " | pos y: " + move.y);
		for (int i = 0; i < arraylength; i++) {
			System.out.printf("%d ", one_rawData[i]);
		}
		System.out.println();

		if (usertag == 1)
			notusertag = 2;
		else if (usertag == 2)
			notusertag = 1;
		else
			System.out.println("Usertag bug in evaGiveScoreC1");

		for (int i = 0; i < arraylength; i++) { // 받아온 데이터를 dummycell 로 옮긴다.
			dummycell[i] = one_rawData[i];
		}

		for (int i = 0; i < arraylength; i++) {
			if (dummycell[i] == notusertag || dummycell[i] == 3) { // 만약 각 cell 이 notusertag 라면.
				if (first_notusertag == -1) { // 처음 notusertag가 나왔다면.
					gab_notusertag = i; // gab은 처음 나온 수 index 만큼이다.
					first_notusertag = 0; // 이제 notusertag가 한 번 나왔기 때문에 0으로 바꿔준다.
				} else {
					gab_notusertag = i - first_notusertag - 1; // 두번째 notusertag가 나왔다면 두 index의 차이에 -1 이다.
				}

				if (gab_notusertag < 6) { // 만약 차이가 5이하이면 어차피 죽은 공간, 따라서 검은 돌만 있다고 생각한다.
					for (int j = first_notusertag; j < i; j++) {
						dummycell[j] = notusertag;
					}
				}
				first_notusertag = i;
			}

			if (i == arraylength - 1) {
				if (i - first_notusertag < 6) {
					for (int j = first_notusertag + 1; j < arraylength; j++) {
						dummycell[j] = notusertag;
					}
				}
			}

		}
		// 여기까지가 죽은 공간은 모두 notusertag 값으로 채워버리는 것.

		int[] one_rawScore = new int[19]; // 점수 저장할 array
		int count_usertag = 0;
		for (int i = 0; i < arraylength - 5; i++) {
			// 6개의 범위 안에 usertag가 얼마나 있는지 확인
			for (int j = i; j < i + 6; j++) {
				if (dummycell[j] == usertag)
					count_usertag++;
			}
			// 만약 그 범위에 0, 즉 빈 칸이 있다면 count_usertag 만큼 더해준다. 그게 점수다
			for (int j = i; j < i + 6; j++) {
				if (dummycell[j] == 0)
					one_rawScore[j] += count_usertag;
			}
			count_usertag = 0;
		}

		// 여기 까지가 점수 매기기

		// 방향 순서 ->, 아래 , 오른쪽 아래, 왼쪽 아래
		// int x = start_index_x;
		// int y = start_index_y;
		switch (direction) {
		case LEFTRIGHT: // left to right
			for (int i = 0; i < 19; i++) {
				scoreBoard[i][move.y] += one_rawData[i];// one_rawScore[i];
			}
			break;

		case TOPDOWN: // top down
			for (int i = 0; i < 19; i++) {
				scoreBoard[move.x][i] += one_rawData[i];// one_rawScore[i];
			}
			break;

		case TOPLBOTR: // TOP L TO BOT R
			for (int i = 0; i < arraylength; i++) {
				scoreBoard[move.x][move.y] += one_rawData[i];// one_rawScore[i];
				move.x++;
				move.y++;
			}
			break;

		case BOTLTOPR: // BOT L TO TOP R
			for (int i = 0; i < arraylength; i++) {
				scoreBoard[move.x][move.y] += one_rawData[i];// one_rawScore[i];
				move.x--;
				move.y++;
			}
			break;

		default:
			break;
		}

		System.out.println("x\\y\t0 1 2 3 4 5 6 7 8 9 a 1 2 3 4 5 6 7 8\n");
		for (int i = 0; i < 19; i++) {
			System.out.printf("%d\t", i);
			for (int j = 0; j < 19; j++) {
				System.out.printf("%d ", scoreBoard[i][j]);
			}
			System.out.println();
		}
	}

	public void evaGiveScoreC2() {
		return;
	}

}

/*
 * 토큰을 만들자. 바운더리 사이의 거리(칸수)로 돌이 죽었는지를 확인가능. 아군돌의 갯수로 종류분류.
 */