public class AIController implements Runnable{
    PlayerController playerController;
    public Rect ball;

    public AIController(PlayerController playerController, Rect ball) {
        this.playerController = playerController;
        this.ball = ball;
    }

    public void update(double delta) {
        playerController.update(delta);

        if (ball.y < playerController.rect.y) {
            playerController.moveUp(delta);
        } else if (ball.y + ball.height > playerController.rect.y + playerController.rect.height) {
            playerController.movDown(delta);
        }
    }

    @Override
    public void run() {

    }
}
