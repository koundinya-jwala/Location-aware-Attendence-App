package com.um56.jefhunt.professorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class ProfessorActivity extends AppCompatActivity {

    TextView textView;
    CheckBox st1,st2,st3;
    Button submit;
    String data[];
    String stRoll[]=new String[10];
    String coursenum;
    String result=new String();

    ///
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);
        textView=findViewById(R.id.tv);
        Intent getIntent=getIntent();
        Bundle b=getIntent.getExtras();
        if(b!=null)
        {
            String j =(String) b.get("DATA");
            data=j.split("@");
            coursenum=data[2].split(":")[0];
            String stulist=data[2].split(":")[1];
            int i=0;
            for (String temp:stulist.split(";")){
               stRoll[i]=temp.split("#")[0];
               i++;
            }

        }
        addListnerButtonClick();
    }

    public void addListnerButtonClick(){
        st1=findViewById(R.id.checkBox1);
        st2=findViewById(R.id.checkBox2);
        submit=findViewById(R.id.button1);

        st1.setText(stRoll[0]);
        st2.setText(stRoll[1]);
        //st3.setText(stRoll[2]);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             int total = 0;

             result=result+"40@"+coursenum+"@";
             if (st1.isChecked()){
                 result=result+stRoll[0]+":";
                 total+=1;
             }
             if (st2.isChecked()){
                 result=result+stRoll[1];
             }
               Log.e("TAGSS",result);
               DataProvider dataProvider=new DataProvider(result+"@");
               dataProvider.execute();
        }
       });
    }
}