package com.um56.jefhunt.professorapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import java.io.IOException;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import static android.content.ContentValues.TAG;


public class Client extends AsyncTask<Void, Void, String> {
    Scanner inputStream  ;//new socket.getInputStream();
    String dstAddress="192.168.43.244";
    String dstPort="4000";
    String response = "";
    String user;
    String pasS;
    TextView textResponse;
    String auth;
    String stRoll;
    Context context;
    String data;

    Client(String addr, String pass, TextView textResponse,Context context) {
        user = addr;
        pasS = pass;
        this.context=context;
        this.textResponse = textResponse;
    }

    @Override
    protected String doInBackground(Void... arg0) {

        Socket socket = null;


        try {
            socket = new Socket(dstAddress, Integer.parseInt(dstPort));

            PrintStream byteArrayOutputStream = new PrintStream(socket.getOutputStream());

            int bytesRead;
            inputStream  =new Scanner(socket.getInputStream());
            byteArrayOutputStream.println("5@"+user+"@"+pasS);
            byteArrayOutputStream.flush();
            /*
             * notice: inputStream.read() will block if no data return
             */
            if (inputStream.hasNext()) {
                data = inputStream.next();
                Log.e(TAG,data);

            }

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "UnknownHostException: " + e.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "IOException: " + e.toString();
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
        return response;
    }

    @Override
    protected void onPostExecute(String result) {
        textResponse.setText(response);
        super.onPostExecute(result);

        String getData[] = data.split("@");

        Log.e("PRO",getData[0]);
            if (getData[0].equals("0")) {
                //Data = getData;
                Log.e("PRO",getData[0]+"Prof");
                Intent intent =new Intent(context,ProfessorActivity.class);
                intent.putExtra("DATA",data);
                context.startActivity(intent);
            }
            if (getData[0].equals("1")) {
                //Data = getData;
                stRoll=data.split("@")[1];
                auth=data.split("@")[2];
                Log.e("PRO",auth+" Stdent");

                if (auth.equals("yes")){
                    Log.e("YES",auth);
                    Intent intent = new Intent(context,QRGenerate.class);
                    intent.putExtra("Roll",stRoll);
                    context.startActivity(intent);
                }
                if(auth.equals("no")){
                    Log.e("YES",auth);
                    Intent intent = new Intent(context,QRScanner.class);
                    intent.putExtra("Roll",stRoll);
                    context.startActivity(intent);

                }
            }


        }
}
