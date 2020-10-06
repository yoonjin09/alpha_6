# alpha_6
실행순서:
  인풋을 받는다
  올바르게 입력 되었는지 확인하고 보드에 착수한다.
  승리자가 있는지 확인한다.
  그리곤 보드 상황을 판단하고
  그중에서 최상의 수를 착수한다.
보드를 판단하는 프로세스다
  보드에서 돌들을 읽어오고 그 돌들을 기반하여 5 가지의 상황으로 나눈다.
    1. offImm
      이는 아군이 4목이 있고 양쪽 혹은 한쪽이 막히지 않은 상태를 일컫는다.
      착수 가능한 2돌을 이어 붙이고 게임을 승리한다.
    2. defImm
      이는 상대방이 4목이 있고 양쪽이 다 막히지 않을 상태를 일컫는다.
      착수 가능한 돌 2개를 다 사용하여 방어를 해야한다.
      단 최대한의 이익을 위해서 한칸을 띄고 착수해도 된다.
      예시로 1일 상대방 돌이고 0이 아군돌일 때 0_11110 이런 모습이 될 것이다.
    3. defFir
      이는 상대방이 4목이 있지만 한쪽은 막혀있는 상태를 일컫는다.
      defImm처럼 한칸을 띄고 착수가 가능하며 나머지 돌 하나로 off나 def를 진행한다.
    4. off
      이는 상대방의 돌이 위협적이지 않고(4목이 없고) 아군의 돌로 공격(4목)이 가능한 상태를 일컫는다.
    5. def
      이는 아군도 상대편도 4목을 완성시킬 수 없는 상태를 일컫는다.
      최대한의 이익을 위해 상대편의 돌들을 재지하고 아군의 돌들이 이어지는 부분에 착수한다.

해당 프로그램에 x1 y1 x2 y2의 방식으로 입력을 한다.

operation order:
  get user's input
  check for validation and enter the value
  check for winner
  evaluate the board
  place the stone that has the best odds
evaluation process:
  read stones from the board and determine a situation out of 5 options
    1. offImm
      it is a situation which ally's stone has completed 4 in a row
      and both or one side is not blocked.
      place 2 stones next to the 4 and win the game
    2. defImm
      it is a situation which opponent has completed 4 in a row and non of the sides are blocked
      use both stones to stop 6 in a row
      but for a better board, it is fine to leave a gap and place the stone
      for example: 1 for opponent's stone and 0 for ally's 0_11110
    3. defFir
      it is a situation which opponent has completed 4 in a row and one of the side is blocked
      block the side and use the one last stone to perform off or def
      of course leaving a gap and place the stone is also possible
    4. off
      it is a situation which opponent has no 4 in a row and ally and make 4 in a row with the stones
    5. def
      it is a situation which neither opponent and ally can make 4 in a row
      place the stone where can stop opponent's build and link ally's stones

the input syntax for the program is x1 y1 x2 y2.
