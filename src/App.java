public class App {
    public static int state = 0;
    public static Thread mainThread;
    public static MainMenu menu;
    public static Frame gameFrame;

    public static void main(String[] args) {


        menu = new MainMenu();
        mainThread = new Thread(menu);
        mainThread.start();
    }
    public static void changeState(int newState) {
        if (newState == 1 && state == 0) {
            menu.stop();
            gameFrame = new Frame();
            mainThread = new Thread(gameFrame);
            mainThread.start();
        } else if (newState == 0 && state == 1) {
            gameFrame.stop();
            menu = new MainMenu();
            mainThread = new Thread(menu);
            mainThread.start();
        } else if (newState == 2) {
            if (gameFrame != null) gameFrame.stop();
            if (menu != null) menu.stop();
        }
        state = newState;
    }
}
