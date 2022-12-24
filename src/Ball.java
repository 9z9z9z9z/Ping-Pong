public class Ball{
    public Rect rect;
    public Rect leftPaddle, rightPaddle;
    MyText rightScore, leftScore;
    private double vy = 100.0;
    private double vx = -250.0;

    public Ball(Rect rect, Rect leftPaddle, Rect rightPaddle, MyText leftScore, MyText rightScore) {
        this.rect = rect;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
        this.leftScore = leftScore;
        this.rightScore = rightScore;
    }

    public double calculateNewAngle(Rect paddle) {
        double relativeIntersectY = (paddle.y + (paddle.height / 2.0)) - (this.rect.y + (this.rect.height / 2.0));
        double normalIntersectY = relativeIntersectY / (paddle.height / 2.0);
        return Math.toRadians(normalIntersectY * Constants.MAX_ANGLE);
    }
    public void update(double delta) {
        if (this.vy > 0) {
            if (this.rect.y + (vy * delta) + this.rect.height > Constants.SCREEN_HEIGHT) {
                this.vy *= -1.0;
            }
        } else if (this.vy < 0) {
            if (this.rect.y + (vy * delta) < Constants.BAR_HEIGHT + 35) {
                this.vy *= -1.0;
            }
        }
        if (vx < 0) {
            if (this.rect.x + (vx * delta) < this.leftPaddle.x + this.leftPaddle.width &&
                    this.rect.x + (vx * delta) > this.leftPaddle.x &&
                    this.rect.y + (vy * delta) > this.leftPaddle.y &&
                    this.rect.y + (vy * delta) < this.leftPaddle.y + this.leftPaddle.height) {

                double theta = calculateNewAngle(this.leftPaddle);
                double newVx = Math.abs(Math.cos(theta) * Constants.BALL_SPEED);
                double newVy = (-Math.sin(theta) * Constants.BALL_SPEED);
                double oldSign = Math.signum(this.vx);

                this.vx = newVx * (-1.0 * oldSign);
                this.vy = newVy;

            }
        } else if (vx > 0) {
            if (this.rect.x + (vx * delta) + this.rect.width > this.rightPaddle.x &&
                    this.rect.x + (vx * delta) < this.rightPaddle.x + this.rightPaddle.width &&
                    this.rect.y + (vy * delta) > this.rightPaddle.y &&
                    this.rect.y + (vy * delta) < this.rightPaddle.y + this.rightPaddle.height) {

                double theta = calculateNewAngle(this.rightPaddle);
                double newVx = Math.abs(Math.cos(theta) * Constants.BALL_SPEED);
                double newVy = (-Math.sin(theta) * Constants.BALL_SPEED);
                double oldSign = Math.signum(this.vx);

                this.vx = newVx * (-1.0 * oldSign);
                this.vy = newVy;

            }
        }

        // Movements
        this.rect.x += vx * delta;
        this.rect.y += vy * delta;

        if (this.rect.x + this.rect.width < leftPaddle.x) {
            int right_score = Integer.parseInt(rightScore.score);
            right_score++;
            if (right_score == Constants.WIN_SCORE) {
                System.out.println("AI is a winner!");
                App.changeState(2);
            }
            rightScore.score = "" + right_score;
            this.rect.x = Constants.SCREEN_WIDTH / 2;
            this.rect.y = Constants.SCREEN_HEIGHT / 2;
            this.vx = -250;
            this.vy = 100;
            this.leftPaddle.y = Constants.SCREEN_HEIGHT / 2 - Constants.PADDLE_HEIGHT / 2;
            this.rightPaddle.y = Constants.SCREEN_HEIGHT / 2 - Constants.PADDLE_HEIGHT / 2;
        } else if (this.rect.x + this.rect.width > rightPaddle.x) {
            int left_score = Integer.parseInt(leftScore.score);
            left_score++;
            if (left_score == Constants.WIN_SCORE) {
                System.out.println("Congratulations!\nYou are winner!");
                App.changeState(2);
            }
            leftScore.score = "" + left_score;
            this.rect.x = Constants.SCREEN_WIDTH / 2;
            this.rect.y = Constants.SCREEN_HEIGHT / 2;
            this.vx = -250;
            this.vy = 100;
            this.leftPaddle.y = Constants.SCREEN_HEIGHT / 2 - Constants.PADDLE_HEIGHT / 2;
            this.rightPaddle.y = Constants.SCREEN_HEIGHT / 2 - Constants.PADDLE_HEIGHT / 2;
        }

    }
}
