import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControlsListener implements KeyListener {
    private boolean keysPressed[] = new boolean[128];
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        keysPressed[keyEvent.getKeyCode()] = true;

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        keysPressed[keyEvent.getKeyCode()] = false;
    }

    public boolean isKeyPressed(int keyCode) { return keysPressed[keyCode]; }
}
