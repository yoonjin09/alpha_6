package alpha_6;

public class evaluate { //
	public evaluate() {
		board scoreForBlack, scoreForwhite;
		
		return;
	}
	/*
	 * read board left to right, top to bottom, left top to right bottom, 
	 * right top to left bottom, if there is a stone, hand read direction, cor, board, usertag to evaGIveScore()
	*/
	public void readBoWDir(board rawData) {
		
		return;
	}
	/*
	 * evaluate and give score to new score board according to input
	 * C 1is for first move, C2 is for second move, the score for both cases are different. 
	 * 
	 */
	/*
	 * start_index_x,y 시작하는 좌표
	 * direction 방
	 * userTag_confirm 흑인지 백인지 확인
	 * arraylength 는 넘어오는 array의 길이 (대각선 때문에)
	 * one_rawData[] 는 array 값
	 */
	 
	public void evaGiveScoreC1(int start_index_x, int start_index_y, int direction, int arraylength, int usertag, int one_rawData[]) { 
		int[][] totalScore = new int[19][19]; // 이거는 일단 없는거라고 생각하자
		
		int[] dummycell= new int[19]; //만약 상대편 돌에 의해 쓰레기 cell 이 나올 때를 대비.
		int notusertag=0; // usertag 의 반대. 1이면 2, 2이면 1.
		int first_notusertag = -1; //dummycell을 살펴볼 때 만약 notusertag가 있다면 위치를 기억하기 위한 수
		int gab_notusertag = 0; 
		
		if(usertag == 1)
			notusertag = 2;
		else if(usertag == 2)
			notusertag = 1;
		else
			System.out.println("Usertag is something wrong in evaGiveScoreC1");
		
		for(int i=0; i<arraylength ; i++) { //받아온 데이터를 dummycell 로 옮긴다.
			dummycell[i] = one_rawData[i];
		}
		
		for(int i=0; i<arraylength; i++) {
			if(dummycell[i] == notusertag) { //만약 각 cell 이 notusertag 라면.
				if(first_notusertag == -1) { // 처음 notusertag가 나왔다면.
					gab_notusertag= i; 		// gab은 처음 나온 수 index 만큼이다.
					first_notusertag = 0;	//이제 notusertag가 한 번 나왔기 때문에 0으로 바꿔준다.
				}
				else{
					gab_notusertag = i - first_notusertag-1; //두번째 notusertag가 나왔다면 두 index의 차이에 -1 이다.
				}
				
				if(gab_notusertag < 6) { //만약 차이가 5이하이면 어차피 죽은 공간, 따라서 검은 돌만 있다고 생각한다.
					for(int j = first_notusertag; j<i;j++) {
						dummycell[j] = notusertag;
					}
				}
				first_notusertag = i;
			}
			
			if(i == arraylength-1) {
				if(i-first_notusertag < 6) {
					for(int j=first_notusertag+1; j<arraylength; j++) {
						dummycell[j] = notusertag;
					}
				}
			}
			
		}
		//여기까지가 죽은 공간은 모두 notusertag 값으로 채워버리는 것.
		
		int[] one_rawScore = new int[19]; // 점수 저장할 array
		int count_usertag=0;
		for(int i=0; i< arraylength-5; i++) {
			//6개의 범위 안에 usertag가 얼마나 있는지 확인
			for(int j=i; j<i+6; j++) {			
				if(dummycell[j] == usertag)
					count_usertag++;
			}
			//만약 그 범위에 0, 즉 빈 칸이 있다면 count_usertag 만큼 더해준다. 그게 점수다
			for(int j=i; j<i+6; j++) {			
				if(dummycell[j] == 0)
					one_rawScore[j]+=count_usertag;
			}
			count_usertag=0; 
		}
		
		//여기 까지가 점수 매기기
		
		//방향 순서 ->, 아래 , 오른쪽 아래, 왼쪽 아래
		int x = start_index_x;
		int y = start_index_y;
		switch(direction) {
			case 1: 
				for(int i=0; i<19; i++) {
					totalScore[x][i] += one_rawScore[i];
				}
				break;
				
			case 2:
				for(int i=0; i< 19; i++) {
					totalScore[i][y] += one_rawScore[i];
				}
				break;
				
			case 3:
				for(int i=0; i< arraylength; i++) {
					totalScore[x][y] +=one_rawScore[i];
					x++; y++;
				}
				break;
				
			case 4:
				for(int i=0; i< arraylength; i++) {
					totalScore[x][y]+=one_rawScore[i];
					x--; y--;
				}
				break;
				
			default :
				break;
		}
		
		return;
	}
	
	public void evaGiveScoreC2() {
		return;
	}
	
	
	
}
