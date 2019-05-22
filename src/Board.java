public class Board {
    int size;
    String[][] board;
    int[][] position;

    Board(int num){
        size = num;
        board = new String[size + 1][size + 1];
        board[0][0] = " ";
        for (int count = 0; count < size; count++) {
            int tempInt = 'a' + count;
            char tempChar = (char) tempInt;
            board[0][count + 1] = ""+tempChar;
            board[count + 1][0] = tempChar+"";
        }

        for (int i = 1; i < size + 1; i++) {
            for (int j = 1; j < size + 1; j++) {
                board[i][j] = "." ;
            }
        }
        board[size/2][size/2] = "O";
        board[size/2][size/2 + 1] = "X";
        board[size/2 + 1][size/2] = "X";
        board[size/2 + 1][size/2 + 1] = "O";
        position = new int[size+1][size+1];
        for (int i=1;i<size+1;i++){
            for (int j= 1;j<size+1;j++){
                position[i][j]= 0;
            }
        }

    }

    Boolean isFull() {
        Boolean s=true;
        for (int i=1;i< size+1;i++){
            for (int j=1;j< size+1;j++){
                if(board[i][j].equals(".")){
                    s = false;
                    break;
                }
            }
            if(s==false)
            break;
        }
        return s;
    }

    Boolean noSoldier(String ox){
        Boolean s= true;
        for (int i=1;i< size+1;i++){
            for (int j=1;j< size+1;j++){
                if(board[i][j].equalsIgnoreCase(ox)){
                    s = false;
                    break;
                }
            }
            if(s==false)
                break;
        }
        return s;
    }

}
