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
            case KeyEvent.VK_UP -> up = true;
            case KeyEvent.VK_DOWN -> up = true;
            case KeyEvent.VK_LEFT -> up = true;
            case KeyEvent.VK_RIGHT -> up = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode){
            case KeyEvent.VK_UP -> up = false;
            case KeyEvent.VK_DOWN -> up = false;
            case KeyEvent.VK_LEFT -> up = false;
            case KeyEvent.VK_RIGHT -> up = false;
        }
    }
}
