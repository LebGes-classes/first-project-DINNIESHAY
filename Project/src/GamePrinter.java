public class GamePrinter {

    private int rows;
    private int columns;
    private int playerX;
    private int playerY;
    private char[][] grid;

    public GamePrinter(Labyrinth labyrinth, Player player) {
        rows = labyrinth.getRows();
        columns = labyrinth.getColumns();
        grid = labyrinth.getGrid();
        playerX = player.x;
        playerY = player.y;
    }

    public void printGameField() {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                if (y == playerY && x == playerX) {
                    System.out.print('o');
                } else {
                    System.out.print(grid[y][x]);
                }
            }
            System.out.println();
        }
    }
}
