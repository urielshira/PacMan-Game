import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {

    String path;

    public Sound(String path) {
        this.path = path;
        new Thread(() -> Sound.playMusic(path)).start();
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
