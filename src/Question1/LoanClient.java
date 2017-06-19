/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Red King
 */
public class LoanClient {
    private DataOutputStream toServer;
    private DataInputStream fromServer;
    private Scanner scanner = new Scanner(System.in);
    public LoanClient(int port){
        try{
            Socket socket = new Socket("localhost", 8000);
            
            fromServer = new DataInputStream(socket.getInputStream());
            toServer = new DataOutputStream(socket.getOutputStream());
            
            System.out.println("Enter Annual Interest Rate: ");
            toServer.writeDouble(scanner.nextDouble());
            toServer.flush();
            System.out.println("Enter Number Of Years: ");
            toServer.writeInt(scanner.nextInt());
            toServer.flush();
            System.out.println("Enter Loan Amount: ");
            toServer.writeDouble(scanner.nextDouble());
            toServer.flush();
            
            double monthlyPayment = fromServer.readDouble();
            double totalPayment = fromServer.readDouble();
            
            System.out.println("Monthly Payment = " + monthlyPayment);
            System.out.println("Total Payment = " + totalPayment);
        }catch(IOException ex){
            System.err.println(ex);
        }
    }
    
    public static void main(String[]args){
        new LoanClient(8000);
    }
}
