import java.util.Scanner;

public class GameControl {

    private final Menu menu = new Menu();
    private final LevelGenerator levelGenerator = new LevelGenerator();
    private final int numberOfLevels = levelGenerator.numberOfLevels;
    private Labyrinth labyrinth;
    private Player player;

    public void open() {
        menu.printWelcome();
        Services.getInput();
        menu.printMenu();

        Scanner in = new Scanner(System.in);
        String input;
        boolean gameStarted = false;

        while (!gameStarted) {
            input = in.nextLine();
            if (input.equals("1")) {
                gameStarted = true;
            }
            else if (input.equals("2")) {
                menu.printGameInfo();
                Services.getInput();
                menu.printMenu();
            }
            else if (input.equals("3")) {
                exit();
                return;
            } else {
                System.out.println("Wrong button. Try again.");
            }
        }
        start();
    }

    private void start() {
        for (int level = 1; level <= numberOfLevels; level++) {
            char[][] grid = levelGenerator.generateLevel(level);
            labyrinth = new Labyrinth(grid);
            player = new Player();

            runGameLoop();

            if (level == numberOfLevels) {
                System.out.println("CONGRATULATIONS! YOU'VE PASSED THE GAME!\n");
            } else {
                System.out.println("YOU PASSED LEVEL " + level +"!\nPress any key to continue...");
                Services.getInput();
            }
        }
    }

    private void exit() {
        Services.clearConsole();
        System.out.println("Exiting the game...");
    }

    private void printFrame(){
        Services.clearConsole();
        GamePrinter gamePrinter = new GamePrinter(labyrinth, player);
        gamePrinter.printGameField();
    }

    private void processPlayerInput() {
        String move = Services.getInput();
        switch(move) {
            case "W":
                if (labyrinth.isClearPosition(player.x, player.y - 1)){
                    player.y -= 1;
                } else {
                    restartLevel();
                }
                break;
            case "S":
                if (labyrinth.isClearPosition(player.x, player.y + 1)){
                    player.y += 1;
                } else {
                    restartLevel();
                }
                break;
            case "A":
                if (labyrinth.isClearPosition(player.x - 1, player.y)){
                    player.x -= 1;
                } else {
                    restartLevel();
                }
                break;
            case "D":
                if (labyrinth.isClearPosition(player.x + 1, player.y)){
                    player.x += 1;
                } else {
                    restartLevel();
                }
                break;
            default:
                break;
        }
    }

    private void runGameLoop() {
        while(true) {
            printFrame();
            processPlayerInput();
            char currElementAtPos = labyrinth.getElementAt(player.x, player.y);
            if (currElementAtPos == '✻') {
                printFrame();
                break;
            }
        }
    }

    private void restartLevel() {
        System.out.println("Oops! You hit a wall!\nPress any key to start again...");
        Services.getInput();
        placePlayerToStart();
    }

    private void placePlayerToStart() {
        player.x = 1;
        player.y = 1;
    }
}
