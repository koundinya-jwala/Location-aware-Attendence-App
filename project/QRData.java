/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;
import java.util.*;
/**
 *
 * @author User
 */
public class QRData {
    long  seconds;
    String random;
    
    public QRData(long seconds, String random)
    {
        this.seconds=seconds;
        this.random=random;
    }
    
    public static void main(String[] args) {
        System.out.println(new Date().getTime());
    }
    
}
