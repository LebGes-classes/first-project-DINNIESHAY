public class Labyrinth {

    private char[][] grid;
    private int rows;
    private int columns;

    public Labyrinth(char[][] grid) {
        this.grid = grid;
        rows = grid.length;
        columns = grid[0].length;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public char[][] getGrid() {
        return grid;
    }

    public char getElementAt(int x, int y) {
        return grid[y][x];
    }

    public boolean isClearPosition(int x, int y) {
        return grid[y][x] == ' ' || grid[y][x] == '✻';
    }
}
