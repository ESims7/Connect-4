import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Board board = new Board();
        board.printBoard();
        int columnChoice;

        while (board.gameContinuing()) {

            System.out.println("Select column you'd like to play in:");
            Scanner input = new Scanner(System.in);

            try {
                columnChoice = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid selection");
                continue;
            }

            if (columnChoice > 7 || columnChoice < 0) {
                System.out.println("Invalid selection");
                continue;
            }

            if (board.columnIsFull(columnChoice)) {
                System.out.println("That column is full");
                continue;
            }

            board.playPiece(columnChoice);
            board.printBoard();

            if (board.gameContinuing()) {
                board.changePlayer();
            }

        }

        board.printWinner();

    }
}
