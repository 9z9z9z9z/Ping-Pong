public class AIController implements Runnable{
    PlayerController playerController;
    public Rect ball;

    public AIController(PlayerController playerController, Rect ball) {
        this.playerController = playerController;
        this.ball = ball;
    }

    public void update(double delta) {
        playerController.update(Constants.DELTA);

        if (ball.y < playerController.rect.y) {
            playerController.moveUp(Constants.DELTA);
        } else if (ball.y + ball.height > playerController.rect.y + playerController.rect.height) {
            playerController.movDown(Constants.DELTA);
        }
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread());
        update(Constants.DELTA);
    }
}
