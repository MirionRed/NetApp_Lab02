/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
public class LoanServer {
    public LoanServer(int port){
        try{
            ServerSocket serverSocket = new ServerSocket(port);
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
        }catch(IOException e){
            System.err.println(e);
        }
    }
    
    public static void main(String[]args){
        int port;
        if(args.length == 0){
            port = 8000;
            new LoanServer(port);
        } else if (args.length == 1){
            port = Integer.parseInt(args[0]);
            new LoanServer(port);
        } else {
            System.out.println("Invalid number of arguments!");
        }
    }
}

class HandleClient extends Thread{
    private Socket socket;
    public HandleClient(Socket socket){
        this.socket = socket;
    }
    
    public void run(){
        try{
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            
            while(true){
               Loan loan = new Loan(in.readDouble(), in.readInt(), in.readDouble());
               
               out.writeDouble(loan.getMonthlyPayment());
               out.writeDouble(loan.getTotalPayment());
               
               System.out.println("Loan data received");
            }
        }catch(SocketException ex){
            System.out.println("Client is disconnected.\n");
        }catch(IOException ex){
            System.err.println(ex);
        }
    }
}
