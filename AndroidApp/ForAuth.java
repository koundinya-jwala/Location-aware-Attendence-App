package com.um56.jefhunt.professorapp;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ForAuth  extends AsyncTask<Void, Void, Void> {
    Scanner inputStream  ;//new socket.getInputStream();
    String dstAddress="192.168.43.244";
    String dstPort="4000";
    String resultDa;

    ForAuth (String result){
        resultDa= result;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        Socket socket = null;
        try {
            socket = new Socket(dstAddress, Integer.parseInt(dstPort));

            PrintStream byteArrayOutputStream = new PrintStream(socket.getOutputStream());
            inputStream = new Scanner(socket.getInputStream());
            byteArrayOutputStream.println(resultDa);
            byteArrayOutputStream.flush();
            //   Toast.makeText(getApplicationContext(),resultDa.toString(),Toast.LENGTH_SHORT).show();

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
