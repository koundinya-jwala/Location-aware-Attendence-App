/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;
import java.util.concurrent.*;
import java.util.*;
/**
 *
 * @author User
 */
public class Connected {
    ConcurrentHashMap<String,Integer> map=new ConcurrentHashMap<>();
    ConcurrentHashMap<String,QRData> timeOut=new ConcurrentHashMap<>();
    
    public void add(String rollno)
    {
        if(!map.containsKey(rollno))
        {
            map.put(rollno,1);
        }
    }
    
    public int isPresent(String rollno)
    {
        if(map.containsKey(rollno))
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }
    
    public void set(String roll, String random)
    {
       QRData qr=new QRData(new Date().getTime(),random);
       timeOut.put(roll, qr);
    }
    
    public void  requestAttendence(String roll_scan,String roll_gen, String random)
    {
        long seconds=new Date().getTime();
        
        QRData value=timeOut.get(roll_gen);
        System.out.print("request--");
        if(seconds - value.seconds <200_000)
        {
            if(random.equals(value.random))
            {
                System.out.println("generator   :"+value.random+"   scanner   :"+random );
                add(roll_scan);
                System.out.println("Attendence given   : "+roll_scan);
            }

        }
    }
    
    
    
}
