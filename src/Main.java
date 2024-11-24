import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        new Thread(() -> playMusic("src/sounds/playing-pac-man-6783.wav")).start();

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("PacMan - GAME");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();

        gamePanel.startGameThread();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }

    // מתודה להפעלת מוזיקה
    private static void playMusic(String filePath) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

            // המתנה עד סיום הצליל
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });

            while (clip.isRunning()) {
                Thread.sleep(100);
            }
        } catch (UnsupportedAudioFileException e) {
            System.out.println("הפורמט של קובץ השמע אינו נתמך.");
        } catch (IOException e) {
            System.out.println("אירעה שגיאה בקריאת קובץ השמע.");
        } catch (LineUnavailableException e) {
            System.out.println("שגיאה: לא ניתן לפתוח את הקובץ לניגון.");
        } catch (InterruptedException e) {
            System.out.println("ניגון השמע הופסק.");
        }
    }
}
