public class Computer {
    String comPlay(String ox,Board board1){
        Logic.sniff(ox,board1);
       String xy= Logic.bestPlay(board1,ox);
       return xy;
    }
}
