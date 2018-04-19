package com.um56.jefhunt.professorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class StudentActivity extends AppCompatActivity {

    TextView textView;
    String auth;
    String stRoll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        textView=findViewById(R.id.tv);
        Intent getIntent=getIntent();
        Bundle b=getIntent.getExtras();
        if(b!=null)
        {
            String j =(String) b.get("DATA");
             stRoll=j.split("@")[1];
             auth=j.split("@")[2];
        }
        if (auth=="yes"){
         Intent intent = new Intent(this,QRGenerate.class);
         startActivity(intent);
        }
        if(auth=="no"){
            Intent intent = new Intent(this,QRScanner.class);
            startActivity(intent);

        }
    }
}
