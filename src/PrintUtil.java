import java.util.Scanner;

public class PrintUtil {
    //

     static void printBoard(Board boardTemp) {
        if (boardTemp.size < 0) {
            throw new RuntimeException("Please enter the right board demnesion(positive interger)");
        } else {

            for (String[] row : boardTemp.board) {
                for (String col : row) {
                    System.out.print(" " +col+" ");
                }
                System.out.println();//换行

            }
            //System.out.println(Arrays.toString(board));
        }
    }

    static void updateBoard(String input,String ox,Board boardTemp){
        char rowTemp= input.charAt(0);
        char colTemp= input.charAt(1);
        int row = rowTemp - 'a' + 1;
        int col = colTemp - 'a' + 1;
        boardTemp.board[row][col] = ox;
    }


}
