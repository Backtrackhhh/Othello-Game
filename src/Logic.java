public class Logic {
    static int sniff(String ox, Board boardTemp) {
        int count= 0;
        int x,y;
        String opponent = ox.equalsIgnoreCase("X")?"O":"X";
        //归零
        for (int row = 1; row < boardTemp.size+1; row++)
        {
            for (int col = 1; col < boardTemp.size+1; col++)
            {
                boardTemp.position[row][col] = 0;
            }
        }
        for (int i = 1; i < boardTemp.size + 1; i++) {
            for (int j = 1; j < boardTemp.size + 1; j++) {
                if (!boardTemp.board[i][j].equals(".")) {
                    continue;
                }
                for (int tempI = -1; tempI <= 1; tempI++) {
                    for (int tempJ = -1; tempJ <= 1; tempJ++) {
                        //检查若坐标超过棋盘 或为当前单元格
                        if (i + tempI < 1 || i + tempI > boardTemp.size
                                || j + tempJ < 0 || j + tempJ > boardTemp.size
                                || (tempI == 0 && tempJ == 0)) {
                            continue;   //继续循环
                        }
                        if (boardTemp.board[i + tempI][j + tempJ].equals(opponent))
                        {
                        //以对手下子位置为坐标
                        x = i + tempI;
                        y = j + tempJ;
                        //对对手下子为起始点，向四周查找自己方的棋子，以攻击对方棋子
                        while (true) {
                            //对手下子的四周坐标
                            x += tempI;
                            y += tempJ;
                            //超过棋盘
                            if (x < 1 || x > boardTemp.size || y < 1 || y > boardTemp.size) {
                                break;  //退出循环
                            }
                            //若对应位置为空
                            if (boardTemp.board[x][y].equals(".")) {
                                break;
                            }
                            //若对应位置下的子是当前棋手的
                            if (boardTemp.board[x][y].equalsIgnoreCase(ox)) {
                                //设置移动数组中对应位置为1
                                boardTemp.position[i][j] = 1;
                                count++;
                                break;
                            }
                        }
                        }
                    }
                }
            }
        }
        return count;
    }

    static void move(String ox, Board boardTemp, int row, int col) {
        int x = 0;
        int y = 0;
        boardTemp.board[row][col] = ox;
        //int score = 0;
        String opponent = ox.equalsIgnoreCase("X")?"O":"X";
        for (int tempI = -1; tempI <= 1; tempI++) {
            for (int tempJ = -1; tempJ <= 1; tempJ++) {
                //检查若坐标超过棋盘 或为当前单元格
                if (row + tempI < 1 || row + tempI > boardTemp.size
                        || col + tempJ < 1 || col + tempJ > boardTemp.size
                        || (tempI == 0 && tempJ == 0)) {
                    continue;   //继续循环
                }
                if (boardTemp.board[row + tempI][col + tempJ].equals(opponent)) {
                    x = row + tempI;
                    y = col + tempJ;
                    while (true) {
                        //对手下子的四周坐标
                        x += tempI;
                        y += tempJ;
                        //超过棋盘
                        if (x < 1 || x > boardTemp.size || y < 1 || y > boardTemp.size) {
                            break;  //退出循环
                        }
                        //若对应位置为空
                        if (boardTemp.board[x][y].equals(".")) {
                            break;
                        }
                        //若对应位置下的子是当前棋手的
                        if (boardTemp.board[x][y].equalsIgnoreCase(ox)) {
                            while (boardTemp.board[x -= tempI][y -= tempJ].equals(opponent)) {
                                //将中间的棋子都变成我方棋子
                                boardTemp.board[x][y] = ox;
                                boardTemp.board[row][col] = ox;
                            }
                            break;
                        }
                    }
                }
            }
        }

    }

    static int computeTotalScore(Board boardTemp,String ox){
        int totalScore= 0;
        String opponent = (ox.equalsIgnoreCase("X")) ? "O" : "X"; //对手下的棋子
        for (int i= 0;i < boardTemp.size;i++){
            for (int j= 0;j < boardTemp.size;j++){
                if(boardTemp.board[i][j].equals(ox)){
                    totalScore += 1;
                }
                if (boardTemp.board[i][j].equals(opponent)){
                    totalScore -= 1;
                }
            }
        }
        return totalScore;
    }

    static String bestPlay(Board boardTemp, String ox)
    {
        int row, col, i, j;
        //定义一个临时数组
        Board chessboard1 = new Board(boardTemp.size);
        int MaxScore = 0;   //保存最高分
        int Score = 0;
        int x = 0;
        int y = 0;
        String opponent = (ox.equalsIgnoreCase("X")) ? "O" : "X"; //对手下的棋子
        //循环检查每个单元格
        for (i = 1; i < boardTemp.size+1; i++)
        {
            for (j = 1; j < boardTemp.size+1; j++)
            {
                chessboard1.board[i][j] = boardTemp.board[i][j];
            }
        }
        sniff(ox,chessboard1);
        for (row = 1; row < chessboard1.size+1; row++)
        {
            for (col = 1; col < chessboard1.size+1; col++)
            {
                //若该位置不可下子
                if (chessboard1.position[row][col]!=1)
                {
                    continue;   //继续
                }
                //在临时数组中的指定行列下子
                move(ox,chessboard1,row,col);
                //获取下子后可得到的分数
                Score = computeTotalScore(chessboard1,ox);
                //若原方案得到的分数小于本次下子的分数
                if (MaxScore < Score)
                {
                    MaxScore = Score;   //保存最高分
                    x = row;
                    y = col;
                }
            }
        }
        move(ox,boardTemp,x,y);
        int a='a'+x-1;
        int b= 'a'+y-1;
        String x1 =(char)a+"";
        String x2 = (char)b+"";
        return ""+x1+x2;//返回坐标
    }

}

