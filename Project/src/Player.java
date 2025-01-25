public class Player {

    public int x;
    public int y;
    private char playerSymbol = 'o';

    public Player() {
        placePlayerToStart();
    }

    public void placePlayerToStart() {
        x = 1;
        y = 1;
    }

    public char getPlayerSymbol() {
        return playerSymbol;
    }
}
