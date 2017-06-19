/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;

/**
 *
 * @author Red King
 */
public class Server {
    public Server(){
        try{
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("Server started at " + new Date() + "\n");
            
            int clientNo = 1;
            while(true){
                Socket socket = serverSocket.accept();
                InetAddress inetAddress = socket.getInetAddress();
                System.out.println("Client No. " + clientNo + " [" +
                        inetAddress.getHostName() + "] [" + 
                        inetAddress.getHostAddress() + "] connected at " +
                        new Date() + "\n");
                HandleClient clientThread = new HandleClient(socket);
                clientThread.start();
                clientNo++;
                
            }
        }catch(SocketException ex){
            System.err.println(ex);
        }catch(IOException ex){
            System.err.println(ex);
        }
    }
    
    public static void main(String[]args){
        new Server();
    }
}

class HandleClient extends Thread{
    private Socket socket;
    public HandleClient(Socket socket){
        this.socket = socket;
    }
    
    public void run(){
        try{ 
            ObjectInputStream inputFromClient = 
                    new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputToClient = 
                    new ObjectOutputStream(socket.getOutputStream());
            
            while(true){
                Line[]lines = new Line[5];
                
                for(int i = 0; i < lines.length; i++){
                    Point p1 = (Point)inputFromClient.readObject();
                    Point p2 = (Point)inputFromClient.readObject();
                    lines[i] = new Line(p1,p2);
                }
                
                for(int i = 0; i < lines.length; i++){
                    outputToClient.writeObject(lines[i]);
                    outputToClient.flush();
                }
                
                System.out.println("Point object obtained");
                System.out.println("Line object instantiated");
                System.out.println("Mid-point sent");
                System.out.println();
            }
        }catch(SocketException ex){
            System.out.println("Client is disconnected.\n");
        }catch(IOException ex){
            System.err.println(ex);
        }catch(ClassNotFoundException ex){
            System.err.println(ex);
        }
    }
}
