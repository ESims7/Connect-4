public class Board {

    private Piece[] gameBoard = new Piece[42];

    private String currentPlayer = "White";
    private String pieceOne;
    private String pieceTwo;
    private String pieceThree;
    private String pieceFour;

    void playPiece(int column) {

        for (int i = column - 1; i < 42; i += 7) {
            if (gameBoard[i] == null) {
                gameBoard[i] = new Piece(currentPlayer);
                break;
            }
        }
    }


    private String getCurrentPlayer() {
        return currentPlayer;
    }

    boolean columnIsFull(int column) {
        return (gameBoard[column + 34] != null);
    }

    boolean gameContinuing() {
        for (int i = 0; i < gameBoard.length; i++) {
            if (checkForHorizontalWin(i)) {
                return false;
            }
            if (checkForVerticalWin(i)) {
                return false;
            }
            if (checkForDiagonalWin(i)) {
                return false;
            }

        }
        return true;
    }

    private boolean checkForHorizontalWin(int i) {

        if (i > 38) {
            return false;
        }

        boolean onSameRow = (i / 7 == (i + 1) / 7) && ((i + 1) / 7 == (i + 2) / 7) && ((i + 2) / 7 == (i + 3) / 7);

        try {
            pieceOne = gameBoard[i].getColour();
            pieceTwo = gameBoard[i + 1].getColour();
            pieceThree = gameBoard[i + 2].getColour();
            pieceFour = gameBoard[i + 3].getColour();
        } catch (NullPointerException e) {
            pieceOne = null;
        }

        boolean fourOfSameColour = pieceOne != null && pieceOne.equals(pieceTwo) && pieceTwo.equals(pieceThree) && pieceThree.equals(pieceFour);

        return fourOfSameColour && onSameRow;
    }

    private boolean checkForVerticalWin(int i) {
        if (i > 20) {
            return false;
        }

        try {
            pieceOne = gameBoard[i].getColour();
            pieceTwo = gameBoard[i + 7].getColour();
            pieceThree = gameBoard[i + 14].getColour();
            pieceFour = gameBoard[i + 21].getColour();
        } catch (NullPointerException e) {
            pieceOne = null;
        }

        return pieceOne != null && pieceOne.equals(pieceTwo) && pieceTwo.equals(pieceThree) && pieceThree.equals(pieceFour);
    }

    private boolean checkForDiagonalWin(int i) {
        boolean bottomHalfOfBoard = i <= 20;
        boolean topHalfOfBoard = !bottomHalfOfBoard;

        if (bottomHalfOfBoard) {

            if (i + 24 > 41) {
                return false;
            }

            try {
                pieceOne = gameBoard[i].getColour();
                pieceTwo = gameBoard[i + 8].getColour();
                pieceThree = gameBoard[i + 16].getColour();
                pieceFour = gameBoard[i + 24].getColour();
            } catch (NullPointerException e) {
                pieceOne = null;
            }

            boolean fourOfSameColour = pieceOne != null && pieceOne.equals(pieceTwo) && pieceTwo.equals(pieceThree) && pieceThree.equals(pieceFour);
            boolean inDiagonalLine = (i / 7 == ((i + 8) / 7) - 1) && ((i + 8) / 7 == ((i + 16) / 7) - 1) && ((i + 16) / 7 == ((i + 24) / 7) - 1);

            return pieceOne != null && fourOfSameColour && inDiagonalLine;
        }

        if (topHalfOfBoard) {


            try {
                pieceOne = gameBoard[i].getColour();
                pieceTwo = gameBoard[i - 6].getColour();
                pieceThree = gameBoard[i - 12].getColour();
                pieceFour = gameBoard[i - 18].getColour();
            } catch (NullPointerException e) {
                pieceOne = null;
            }

            boolean fourOfSameColour = pieceOne != null && pieceOne.equals(pieceTwo) && pieceTwo.equals(pieceThree) && pieceThree.equals(pieceFour);
            boolean inDiagonalLine = (i / 7 == ((i - 6) / 7) + 1) && ((i - 6) / 7 == ((i - 12) / 7) + 1) && ((i - 12) / 7 == ((i - 18) / 7) + 1);

            return fourOfSameColour && inDiagonalLine;
        }

        return false;

    }

    void changePlayer() {
        if (currentPlayer.equals("White")) {
            currentPlayer = "Black";
        } else {
            currentPlayer = "White";
        }
    }

    public void printBoard() {
        System.out.println(" 1 2 3 4 5 6 7");
        for (int i = 5; i >= 0; i--) {
            printRow(i);
        }
    }

    private void printRow(int row) {
        StringBuilder rowToPrint = new StringBuilder("|");
        int something = row * 7;
        for (int i = something; i <= something + 6; i++) {
            if (gameBoard[i] != null) {
                rowToPrint.append(gameBoard[i].getSymbol()).append("|");
            } else {
                rowToPrint.append(" |");
            }
        }
        System.out.println(rowToPrint);
        System.out.println("---------------");
    }

    public void printWinner() {
        System.out.println(getCurrentPlayer() + " wins!");
    }
}
