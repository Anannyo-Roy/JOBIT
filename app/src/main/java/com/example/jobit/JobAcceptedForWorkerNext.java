package com.example.jobit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class JobAcceptedForWorkerNext extends AppCompatActivity {

    Connection connect;
    TextView t1,t2,t3,t4,t5,t6;
    static String email;
    static String a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_accepted_for_worker_next);

        Intent intent = getIntent();
        a = intent.getStringExtra("emaill");
        email=a+"@gmail.com";

        t1 = findViewById(R.id.textView12); //name
        t2 = findViewById(R.id.textView7);  //email
        t3 = findViewById(R.id.textView8);  //gender
        t4 = findViewById(R.id.textView9);  //phone
        t5 = findViewById(R.id.textView10); //address
        t6 = findViewById(R.id.textView14); //note

        t2.setText(a);

        try{
            ConnectionClass connectionClass = new ConnectionClass();
            connect= connectionClass.connectionclass();
            if(connect!=null){
                Statement st = connect.createStatement();
                ResultSet rs= st.executeQuery("select * from ClientTable where email='"+email+"'");
                while(rs.next()){
                    t1.setText(rs.getString("name"));
                    t3.setText(rs.getString("gender"));
                    t4.setText(rs.getString("phone"));
                    t5.setText(rs.getString("address"));
                }
                Statement st1 = connect.createStatement();
                ResultSet cu = st1.executeQuery("select * from CurrentUser");
                String cu1 = "";
                if(cu.next()){
                    cu1 = cu.getString(1);
                }
                Statement st2 = connect.createStatement();
                ResultSet rs1 = st2.executeQuery("Select note from request"+cu1);
                while(rs1.next()){
                    t6.setText(rs1.getString("note"));
                }
            }else{
                Toast.makeText(this, "Check Connection", Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void declinerequest1(View v){
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            connect = connectionClass.connectionclass();
            if(connect!=null){
                Statement st = connect.createStatement();
                ResultSet cu = st.executeQuery("select * from CurrentUser");
                Toast.makeText(this, "Job Ended", Toast.LENGTH_SHORT).show();

                String cu1 = "";
                while(cu.next()){
                    cu1 = cu.getString(1);
                }
                st.executeUpdate("delete from request"+cu1+" where email='"+a+"'");
                st.executeUpdate("delete from request"+a+" where phone='"+cu1+"'");
                Intent intent = new Intent(this, workermainpage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        }catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}