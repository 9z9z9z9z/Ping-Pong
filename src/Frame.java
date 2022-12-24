import javax.swing.*;
import java.awt.*;


public class Frame extends JFrame implements Runnable {
    boolean flag = true;
    public Graphics2D graphics2D;
    public Rect firstPlayer, secondPlayer, ball, line;
    public ControlsListener controlsListener = new ControlsListener();
    public boolean isRunning = true;
    public PlayerController playerController1;
    public AIController aiController;
    public Ball ballObj;
    public MyText LeftScore, RightScore;
    public Frame(){
        Constants.BAR_HEIGHT = this.getInsets().top;
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.SCREEN_TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.addKeyListener(controlsListener);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.graphics2D = (Graphics2D)this.getGraphics();


        LeftScore = new MyText("0", new Font("Calibre", Font.PLAIN, Constants.TEXT_SIZE),
                Constants.SCREEN_WIDTH / 2.0 - 30, Constants.TEXT_POSITION_Y);
        RightScore = new MyText("0", new Font("Calibre", Font.PLAIN, Constants.TEXT_SIZE),
                Constants.SCREEN_WIDTH / 2.0 + 20, Constants.TEXT_POSITION_Y);

        this.line = new Rect(Constants.SCREEN_WIDTH / 2 + 5, 0,
                2, Constants.SCREEN_HEIGHT, Color.WHITE);

        this.firstPlayer = new Rect(30, Constants.SCREEN_HEIGHT / 2 - Constants.PADDLE_HEIGHT / 2,
                Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT, Constants.MAIN_COLOR);

        this.secondPlayer = new Rect(Constants.SCREEN_WIDTH - 40, Constants.SCREEN_HEIGHT / 2 - Constants.PADDLE_HEIGHT / 2,
                Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT, Constants.MAIN_COLOR);

        this.ball = new Rect(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2,
                Constants.BALL_SIZE, Constants.BALL_SIZE, Color.RED);
        this.ballObj = new Ball(ball, firstPlayer, secondPlayer, LeftScore, RightScore);

        this.playerController1 = new PlayerController(firstPlayer, controlsListener);
        this.aiController = new AIController(new PlayerController(secondPlayer), ball);
    }
    public void update(double delta) {
        Image image = createImage(getWidth(), getHeight());
        Graphics graphic = image.getGraphics();
        this.draw(graphic);
        if (flag) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        //Thread ball_thread = new Thread(ballObj);
        Thread player_thread = new Thread(playerController1);
        Thread ai_thread = new Thread(aiController);
        graphics2D.drawImage(image, 0, 0, this);
        //ball_thread.start();
        player_thread.start();
        ai_thread.start();
        ballObj.update(delta);
        playerController1.update(delta);
        aiController.update(delta);
    }
    public void stop() { isRunning = false; }
    public void draw(Graphics graphic) {
        Graphics2D tmp_graphics2D = (Graphics2D) graphic;
        tmp_graphics2D.setColor(Color.BLACK);
        tmp_graphics2D.fillRect(0,0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        line.draw(tmp_graphics2D);

        LeftScore.draw(tmp_graphics2D);
        RightScore.draw(tmp_graphics2D);

        firstPlayer.draw(tmp_graphics2D);
        secondPlayer.draw(tmp_graphics2D);
        ball.draw(tmp_graphics2D);
    }
    @Override
    public void run() {
        double lastFrameTime = 0.0;
        while (isRunning) {
            double time = TimeControl.getPeriod();
            double delta = time - lastFrameTime;
            System.out.println(delta);
            lastFrameTime = time;
            flag = false;
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
