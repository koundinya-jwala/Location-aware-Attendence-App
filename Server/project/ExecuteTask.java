/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;
import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
/**
 *
 * @author User
 */
public class ExecuteTask implements Runnable {
    PrintWriter write;
    Scanner read;
    Connected dataObject;
    Database database;
    
    public ExecuteTask(PrintWriter write, Scanner read,Connected dataObject,Database database)
    {
        this.dataObject=dataObject;
        this.read=read;
        this.write=write;
        this.database=database;
    }
    
    public void run()
    {
        String request=read.next();
        String div[]=request.split("@");
        if(div[0].equals("5"))
        {
            String username=div[1];
            String password=div[2];
            String result=database.checkUsername(username, password);
            
            
            if(result.charAt(0)=='1')
            {
                if(dataObject.isPresent(result.split("@")[1])==-1 )
                    result=result+"@no";
                else
                    result=result+"@yes";
                System.out.println("Student : "+username +"    "+password);
                
            }
            else
                System.out.println("Professor :"+username +"     "+password);
            write.print(result);
            write.flush();
        }
        else if(div[0].equals("40"))
        {
            String coursenum=div[1];
            String roll=div[2];
            System.out.println("Request for Student list for course");
            System.out.println("Course num :"+coursenum+"   roll:"+roll);
            addToConnected(roll);
            printConnected();
            System.out.println("");
        }
        else if(div[0].equals("50"))
        {
            String roll=div[1];
            String random=div[2];
            System.out.println("Generator : random :"+random+"   roll:"+roll);
            if(dataObject.isPresent(roll)==1)
                  dataObject.set(roll, random);
            
            printqrGenerate();
            System.out.println("");
        }
        else if(div[0].equals("60"))
        {
            String roll_scan=div[1];
            String roll_gen=div[2];
            String random=div[3];
            System.out.println("Scanner :"+roll_scan+ "random :"+random+"   roll:"+roll_gen);
            dataObject.requestAttendence(roll_scan,roll_gen, random);
            printqrGenerate();
            printConnected();
            System.out.println("");
        }
        
        close();
    }
    
    public void addToConnected(String rolllist)
    {
        for(String roll:rolllist.split(":"))
        {
            if(dataObject.isPresent(roll)==-1  )
            {
                dataObject.map.put(roll,1);
            }
        }
    }
    
    public void printConnected()
    {
        System.out.println("Students who have attendence : "); 
        for(String roll:dataObject.map.keySet())
        {
            System.out.println(roll);
        }
    }
    
    public void printqrGenerate()
    {
         System.out.println("Students whose session still exists  : "); 
        for(String roll:dataObject.timeOut.keySet())
        {
            System.out.println(roll);
        }
    }
    
    public void clear()
    {
        
    }
    
    public void close()
    {
        write.close();
        read.close();
    }
    
    
}
