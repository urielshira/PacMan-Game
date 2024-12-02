import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyHandler implements KeyListener {

    public boolean up, down, left, right;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode){
            case KeyEvent.VK_UP : up = true; down = left = right = false ; break;
            case KeyEvent.VK_DOWN: down = true; up = left = right = false; break;
            case KeyEvent.VK_LEFT: left = true; up = down = right = false; break;
            case KeyEvent.VK_RIGHT: right = true; up = left = down = false ; break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void resetKeys() {
        up = false;
        down = false;
        left = false;
        right = false;
    }
}
