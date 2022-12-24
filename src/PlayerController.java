import java.awt.event.KeyEvent;

public class PlayerController implements Runnable{
    public Rect rect;
    public ControlsListener controlsListener;
    public PlayerController(Rect rect, ControlsListener controlsListener) {
        this.rect = rect;
        this.controlsListener = controlsListener;
    }
    public PlayerController(Rect rect) {
        this.rect = rect;
    }
    public void update(double delta) {
        if (controlsListener != null){
            if (controlsListener.isKeyPressed(KeyEvent.VK_UP)) {
                moveUp(Constants.DELTA);
            } else if (controlsListener.isKeyPressed(KeyEvent.VK_DOWN)) {
                movDown(Constants.DELTA);
            }
        }
    }

    public void moveUp(double delta) {
        if (this.rect.y - Constants.PADDLE_SPEED * delta > Constants.PADDLE_HEIGHT / 2.0 - 30) {
            this.rect.y -= Constants.PADDLE_SPEED  * delta;
        }
    }

    public void movDown(double delta) {
        if (this.rect.y + Constants.PADDLE_SPEED * delta < Constants.SCREEN_HEIGHT - 55 - Constants.PADDLE_HEIGHT / 2.0) {
            this.rect.y += (Constants.PADDLE_SPEED * delta);
        }
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread());
        update(Constants.DELTA);
    }
}
