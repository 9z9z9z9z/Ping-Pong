import java.awt.*;
import java.awt.event.MouseListener;

public class MyText {
    public String score;
    public Font font;
    public Color color = Color.WHITE;
    public double x, y;
    public double width, height;
    public MListener mouseListener;
    public MyText(String text, Font font, double x, double y) {
        this.score = text;
        this.font = font;
        this.x = x; this.y = y;
        this.width = font.getSize() * text.length();
        this.height = font.getSize();
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(color);
        graphics2D.setFont(font);
        graphics2D.drawString(score, (float) x, (float) y);
    }
}
