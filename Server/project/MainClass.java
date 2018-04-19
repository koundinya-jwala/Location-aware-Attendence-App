/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;
import java.net.*;
import java.io.*;
import java.util.*;
/**
 *
 * @author User
 */
public class MainClass {
    
    Connected dataObject=new Connected();
    ServerSocket server;
    PrintWriter write;
    Scanner read;
    Database database=new Database();
    
    public static void main(String[] args) throws Exception {
        new MainClass().startServer();
        
    }
    
    public void startServer() throws Exception
    {
        Socket socket;
        InetAddress addr=InetAddress.getByName("192.168.43.244");
        server=new ServerSocket(4000,2000,addr);
        Thread thread[]=new Thread[2000];
        for(int i=0;true;i++)
        {
            socket=server.accept();
            thread[i]=new Thread(new ExecuteTask(new PrintWriter(socket.getOutputStream()),new Scanner(socket.getInputStream()), dataObject,database));
            thread[i].start();
        }
    }
    
    
    
}
