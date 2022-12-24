import javax.swing.*;
import java.awt.*;


public class MainMenu extends JFrame implements Runnable {
    public Graphics2D graphics2D;
    public ControlsListener controlsListener = new ControlsListener();
    public MListener mouseListener = new MListener();
    public MyText startGame, exitGame, pong;
    public boolean isRunning = true;

    public MainMenu(){
        Constants.BAR_HEIGHT = this.getInsets().top;
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.SCREEN_TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.addKeyListener(controlsListener);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.graphics2D = (Graphics2D)this.getGraphics();
        this.pong = new MyText("Ping Pong",  new Font("Calibre", Font.PLAIN, Constants.TEXT_SIZE + 50),
                Constants.SCREEN_WIDTH / 2.0 - 170, Constants.SCREEN_HEIGHT / 2.0 - 200);
        this.startGame = new MyText("Start Game",  new Font("Calibre", Font.PLAIN, Constants.TEXT_SIZE),
                Constants.SCREEN_WIDTH / 2.0 - 70, Constants.SCREEN_HEIGHT / 2.0 - 50);
        this.exitGame = new MyText("Exit Game",  new Font("Calibre", Font.PLAIN, Constants.TEXT_SIZE),
                Constants.SCREEN_WIDTH / 2.0 - 60, Constants.SCREEN_HEIGHT / 2.0 + 50);
    }
    public void update(double delta) {
        Image image = createImage(getWidth(), getHeight());
        Graphics graphic = image.getGraphics();
        this.draw(graphic);
        graphics2D.drawImage(image, 0, 0, this);

        if (mouseListener.x > startGame.x && mouseListener.x < startGame.x + startGame.width &&
            mouseListener.y > startGame.y - startGame.height && mouseListener.y < startGame.y + startGame.height / 2.0) {
            startGame.color = Color.GRAY;
            if (mouseListener.isPressed) { App.changeState(1); }

        } else { startGame.color = Color.WHITE; }

        if (mouseListener.x > exitGame.x && mouseListener.x < exitGame.x + exitGame.width &&
                mouseListener.y > exitGame.y - exitGame.height && mouseListener.y < exitGame.y + exitGame.height / 2.0) {
            exitGame.color = Color.GRAY;
            if (mouseListener.isPressed) { App.changeState(2); }
        } else { exitGame.color = Color.WHITE; }
    }
    public void stop() { isRunning = false; }
    public void draw(Graphics graphic) {
        Graphics2D tmp_graphics2D = (Graphics2D) graphic;
        tmp_graphics2D.setColor(Color.BLACK);
        tmp_graphics2D.fillRect(0,0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        pong.draw(tmp_graphics2D);
        startGame.draw(tmp_graphics2D);
        exitGame.draw(tmp_graphics2D);
    }
    @Override
    public void run() {
        double lastFrameTime = 0.0;
        while (isRunning) {
            double time = TimeControl.getPeriod();
            double delta = time - lastFrameTime;
            lastFrameTime = time;
            update(delta);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.dispose();
    }
}
