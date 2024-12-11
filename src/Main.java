import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Window.createBoard(); // Singleton



//        try {
//            ServerSocket serverSocket = new ServerSocket(5010);
//            System.out.println("start listening");
//            Socket socket = serverSocket.accept();
//            System.out.println("There is socket");
//            InputStream is = socket.getInputStream();
//
//            Scanner scanner = new Scanner(is);
//            while (scanner.hasNextLine()){
//                System.out.println(scanner.nextLine());
//            }
//
//        }catch (IOException e){
//            throw new RuntimeException(e);
//        }








    }
}
