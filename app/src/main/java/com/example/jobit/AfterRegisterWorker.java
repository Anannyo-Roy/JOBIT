package com.example.jobit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AfterRegisterWorker extends AppCompatActivity {
    Connection con;
    EditText e1,e2,e3,e4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_register_worker);
    }

    public void setuptoworkermain(View v){
        e1 = findViewById(R.id.editTextTextPersonName);
        e2 = findViewById(R.id.editTextTextPersonName3);
        e3 = findViewById(R.id.editTextTextPersonName4);
        e4 = findViewById(R.id.editTextTextPersonName5);

        try{

            String a = e1.getText()+"";
            String b1 = e2.getText()+"";
            String c = e3.getText()+"";
            String d = e4.getText()+"";

            ConnectionClass connectionClass = new ConnectionClass();
            con = connectionClass.connectionclass();

            Statement st = con.createStatement();
            ResultSet rs= st.executeQuery("select * from CurrentUser");

            if(con!=null){
                if(rs.next()){
                    if(!a.equals("") && !b1.equals("") && !c.equals("") && !d.equals("")){
                            String gen = e2.getText()+"";
                            String genlow = gen.toLowerCase();
                            st.executeUpdate("update WorkerTable set name='"+e1.getText()+"',gender='"+genlow+"',address='"+e3.getText()+"',occupation='"+e4.getText()+"' where phone='"+rs.getString(1)+"'");
                            Toast.makeText(this, "Profile Updated!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(this,LoginWorker.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);


                    }else {
                        Toast.makeText(this, "All Fields are Mandatory", Toast.LENGTH_SHORT).show();
                    }}else{
                    Toast.makeText(this, "Not Connected", Toast.LENGTH_SHORT).show();
                }}

        }catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    }
