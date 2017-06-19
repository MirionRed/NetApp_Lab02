/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Red King
 */
public class Client {
    private ObjectOutputStream toServer;
    private ObjectInputStream fromServer;
    private Scanner scanner = new Scanner(System.in);
    public Client(int port){
        try{
            Socket socket = new Socket("localhost", port);
            toServer = new ObjectOutputStream(socket.getOutputStream());
            fromServer = new ObjectInputStream(socket.getInputStream());
            
            Line[] lines = new Line[5];
            Point point;
            double p1, p2;
            for(int i = 0; i < 10; i++){
                System.out.print("Enter point " + i + " x : ");
                p1 = scanner.nextDouble();
                System.out.print("Enter point " + i + " y : ");
                p2 = scanner.nextDouble();
                point = new Point(p1,p2);
                toServer.writeObject(point);
                toServer.flush();
            }
            
            for(int i = 0; i < lines.length; i++){
                lines[i] = (Line)fromServer.readObject();
                System.out.println(lines[i].toString());
            }
        }catch(IOException ex){
            System.err.println(ex);
        }catch(ClassNotFoundException ex){
            System.err.println(ex);
        }
    }
    
    public static void main(String[]args){
        new Client(8000);
    }
}
