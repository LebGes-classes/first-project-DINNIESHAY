public class GameControl {

    private final Menu menu = new Menu();
    private final LevelGenerator levelGenerator = new LevelGenerator();
    private final int numberOfLevels = levelGenerator.numberOfLevels;
    private Labyrinth labyrinth;
    private Player player;

    public void openGame() {
        menu.printWelcome();
        Services.getInput();
        menu.printMenu();
        processMenuChoice();
    }

    private void processMenuChoice() {
        String input;
        boolean gameStarted = false;

        while (!gameStarted) {
            input = Services.getInput();
            if (input.equals("1")) {
                gameStarted = true;
            }
            else if (input.equals("2")) {
                menu.printGameInfo();
                Services.getInput();
                menu.printMenu();
            }
            else if (input.equals("3")) {
                exitGame();
                return;
            } else {
                System.out.println("Wrong button. Try again.");
            }
        }
        startGame();
    }

    private void startGame() {
        for (int level = 1; level <= numberOfLevels; level++) {
            char[][] grid = levelGenerator.generateLevel(level);
            labyrinth = new Labyrinth(grid);
            player = new Player();

            runGameLoop();

            if (level == numberOfLevels) {
                System.out.println("CONGRATULATIONS! YOU'VE PASSED THE GAME!\n");
            } else {
                System.out.println("YOU PASSED LEVEL " + level +"!\n\nPress -E- to exit the game\nPress any other key to continue...");
                if (Services.getInput().equals("E")) {
                    exitGame();
                    return;
                }
            }
        }
    }

    private void exitGame() {
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
        player.placePlayerToStart();
    }
}
