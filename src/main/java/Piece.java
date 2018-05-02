public class Piece extends Board {

    private String colour;

    Piece(String colour) {
        this.colour = colour;
    }

    public String getColour() {
        return this.colour;
    }

    String getSymbol() {
        if (this.getColour().equals("Black")) {
            return "\uD83D\uDD34";
        } else {
            return "\u25CB";
        }
    }


}
