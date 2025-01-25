package com.example.jobit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class HiredForClientNext extends AppCompatActivity {

    static String phon;
    Connection connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hired_for_client_next);

        Intent intent = getIntent();
        phon = intent.getStringExtra("phone");
        TextView ph = findViewById(R.id.textView943);
        ph.setText(phon.trim());
        TextView add = findViewById(R.id.textView10);
        TextView name = findViewById(R.id.textView);
        TextView occ = findViewById(R.id.textView34);
        ImageView i = findViewById(R.id.imageView3);
        TextView gend = findViewById(R.id.textView88);

        try{
            ConnectionClass connectionClass = new ConnectionClass();
            connect = connectionClass.connectionclass();
            if(connect!=null){
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery("Select * from WorkerTable where phone = '"+phon+"'");

                if(rs.next()){
                    if(rs.getString("gender").equals("female")){
                        i.setImageResource(R.drawable.baseline_person_3_24);
                    }
                    else{
                        i.setImageResource(R.drawable.baseline_person_4_24);
                    }
                    gend.setText(rs.getString("gender").trim());
                    name.setText(rs.getString("name").trim());
//                    ph.setText(rs.getString("phonenumber").trim());
                    add.setText(rs.getString("address").trim());
                    occ.setText(rs.getString("occupation").trim());


                }

            }
            else{
                Toast.makeText(this, "connection dekh", Toast.LENGTH_SHORT).show();
            }

        }catch(Exception e){
            System.out.println(e.getMessage());

        }

    }

    public void onclick2(View v){
        TextView t = findViewById(R.id.textView3);
        if(t.getText().toString().equals("Complete Job")){
            Toast.makeText(this, "Job Ended", Toast.LENGTH_SHORT).show();
            t.setText("Completed");


            try{
                ConnectionClass connectionClass = new ConnectionClass();
                connect = connectionClass.connectionclass();
                if(connect!=null){
                    Statement st = connect.createStatement();

                    ResultSet r1 = st.executeQuery("Select * from CurrentUser");
                    String a = "";
                    while(r1.next()){
                        a = r1.getString("id");
                    }
                    st.executeUpdate("delete from request"+a.trim()+" where phone ='"+phon+"'");
                    st.executeUpdate("delete from request"+phon+" where email ='"+a.trim()+"'");

                }
                else{
                    Toast.makeText(this, "connection dekh", Toast.LENGTH_SHORT).show();
                }

            }catch(Exception e){
                System.out.println(e.getMessage());

            }
            Intent intent = new Intent(this, clientmainpage.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        }
        else {
            Toast.makeText(this, "Completed", Toast.LENGTH_SHORT).show();
        }
    }
}