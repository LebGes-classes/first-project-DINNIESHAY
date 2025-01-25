import java.io.File;

public class Menu {

    public void printWelcome() {
        Services.clearConsole();
        Services.printFile(new File("Project/welcome.txt"));
    }

    public void printMenu() {
        Services.clearConsole();
        Services.printFile(new File("Project/menu.txt"));
    }

    public void printGameInfo() {
        Services.clearConsole();
        Services.printFile(new File("Project/gameInfo.txt"));
    }
}