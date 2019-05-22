import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Init {
    static String string[] = new String[6];
    public static void main(String []args)
    {
        int demension;
        String ox;
        System.out.println("Welcome to Othello game !");
        System.out.println("Now please enter the board dimension(4~10,even):");
        Scanner scanner = new Scanner(System.in);
//            String s= "";
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a number");
            scanner.next();
        }
        demension = scanner.nextInt();
        while (demension < 4 || demension > 10 || !(demension % 2 == 0)) {
            System.out.println("Please enter right dimension");
            demension = Integer.parseInt(scanner.nextLine());
        }
        System.out.println("Now please choose your side:O or X,player X moves first");
        String s1 = scanner.next();
        ox = s1;
        while (!(s1.equalsIgnoreCase("O") || s1.equalsIgnoreCase("X"))) {
            System.out.println("please enter right side:O or X,player X moves first");
            ox = scanner.next();
        }
        String opponent = ox.equalsIgnoreCase("O") ? "X" : "O";
        Board chessBoard = new Board(demension);
        PrintUtil.printBoard(chessBoard);
        long start = System.currentTimeMillis();
        if (ox.equalsIgnoreCase("O")) {
            Computer computer = new Computer();
            while (true) {
                int num1 = Logic.sniff(ox,chessBoard);
                int num2 = Logic.sniff(opponent,chessBoard);
                if(chessBoard.isFull() || chessBoard.noSoldier(ox) || (num1==0&&num2==0)){
                    int score= Logic.computeTotalScore(chessBoard,ox);
                    if(score>0) {
                        System.out.println("Game over，player win");
                        System.out.println("Your score:"+score);
                        scanner.close();
                        System.exit(0);
                    }
                    else {
                        System.out.println("Computer win");
                        System.out.println("Your score:"+score);
                        scanner.close();
                        System.exit(0);
                    }
                    long end = System.currentTimeMillis();
                    long time = (end-start)/1000;
                    string[1] = "time:"+time;
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
                    string[0] =simpleDateFormat.format(new Date());
                    string[2] ="size"+chessBoard.size +"*"+chessBoard.size;
                    string[3] =ox.equalsIgnoreCase("X")?"X":"O";
                    string[4] =ox.equalsIgnoreCase("X")?"O":"X";
                    string[5] ="Human score:"+score;
                    writeFile(string);
                }
                String xy = computer.comPlay(opponent, chessBoard);
                if (xy.equals("00")) {
                    System.out.println("computer win");
                }
                System.out.println("computer places X at " + xy);
                PrintUtil.printBoard(chessBoard);
                System.out.println("Enter move for " + ox + " (RowCol): ");
                String s = scanner.next();
                char a = s.charAt(0);
                char b = s.charAt(1);
                int x = a - 'a' + 1;
                int y = b - 'a' + 1;
                while (x<1 || x>chessBoard.size||y<1||y>chessBoard.size){
                    System.out.println("Please enter right coordinate");
                     s = scanner.next();
                     a = s.charAt(0);
                     b = s.charAt(1);
                     x = a - 'a' + 1;
                     y = b - 'a' + 1;
                }
                Logic.sniff(ox,chessBoard);
                if(chessBoard.position[x][y]!=1){
                    System.out.println("human gave up");
                    long end = System.currentTimeMillis();
                    long time = (end-start)/1000;
                    string[1] = "time:"+time;
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
                    string[0] =simpleDateFormat.format(new Date());
                    string[2] ="size"+chessBoard.size +"*"+chessBoard.size;
                    string[3] =ox.equalsIgnoreCase("X")?"X":"O";
                    string[4] =ox.equalsIgnoreCase("X")?"O":"X";
                    string[5] ="Human give up";
                    writeFile(string);
                    scanner.close();
                    System.exit(0);
                }
                Logic.move(ox, chessBoard, x, y);
                PrintUtil.printBoard(chessBoard);
            }
        }
        if (ox.equalsIgnoreCase("X")) {
            while (true) {
                int num3 = Logic.sniff(ox,chessBoard);
                int num4 = Logic.sniff(opponent,chessBoard);
                if(chessBoard.isFull() || chessBoard.noSoldier(ox) ||  (num3==0&&num4==0)){
                    int score= Logic.computeTotalScore(chessBoard,ox);
                    if(score>0) {
                        System.out.println("Game over，player win");
                        System.out.println("Your score:"+score);
                        scanner.close();
                        System.exit(0);
                    }
                    else {
                        System.out.println("Computer win");
                        System.out.println("Your score:"+score);
                        scanner.close();
                        System.exit(0);
                    }
                    long end = System.currentTimeMillis();
                    long time = (end-start)/1000;
                    string[1] = "time:"+time;
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
                    string[0] =simpleDateFormat.format(new Date());
                    string[2] ="size"+chessBoard.size +"*"+chessBoard.size;
                    string[3] =ox.equalsIgnoreCase("X")?"X":"O";
                    string[4] =ox.equalsIgnoreCase("X")?"O":"X";
                    string[5] ="Human score:"+score;
                    writeFile(string);
                }
                System.out.println("Enter move for " + ox + " (RowCol): ");
                String s = scanner.next();
                char a = s.charAt(0);
                char b = s.charAt(1);
                int x = a - 'a' + 1;
                int y = b - 'a' + 1;
                while (x<1 || x>chessBoard.size||y<1||y>chessBoard.size){
                    System.out.println("Please enter right coordinate");
                    s = scanner.next();
                    a = s.charAt(0);
                    b = s.charAt(1);
                    x = a - 'a' + 1;
                    y = b - 'a' + 1;
                }
                Logic.sniff(ox,chessBoard);
                if(chessBoard.position[x][y]!=1){
                    System.out.println("human gave up");
                    long end = System.currentTimeMillis();
                    long time = (end-start)/1000;
                    string[1] = "time:"+time;
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
                    string[0] =simpleDateFormat.format(new Date());
                    string[2] ="size"+chessBoard.size +"*"+chessBoard.size;
                    string[3] =ox.equalsIgnoreCase("X")?"X":"O";
                    string[4] =ox.equalsIgnoreCase("X")?"O":"X";
                    string[5] ="Human give up";
                    writeFile(string);
                    scanner.close();
                    System.exit(0);
                }
                Logic.move(ox, chessBoard, x, y);
                PrintUtil.printBoard(chessBoard);
                Computer computer = new Computer();
                String xy = computer.comPlay(opponent, chessBoard);
                if (xy.equals("00")) {
                    System.out.println("computer win");
                }
                System.out.println("computer places X at " + xy);
                PrintUtil.printBoard(chessBoard);
            }
        }
    }

    static void writeFile(String[] s){
        try {
            File file = new File("src//info.csv");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,true));
            bufferedWriter.write(s[0]+","+s[1]+","+s[2]+","+s[3]+","+s[4]+","+s[5]);
            bufferedWriter.newLine();
            bufferedWriter.close();
        }
       catch (IOException e){
           e.printStackTrace();
       }
    }
}
