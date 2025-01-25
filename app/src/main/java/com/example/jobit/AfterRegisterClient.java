package com.example.jobit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Locale;

public class AfterRegisterClient extends AppCompatActivity {

    Connection con;
    EditText e1,e2,e3,e4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_register_client);
    }

    public void tologinfromsetup(View v){
        e1 = findViewById(R.id.editTextTextPersonName);
        e2 = findViewById(R.id.editTextTextPersonName3);
        e3 = findViewById(R.id.editTextTextPersonName4);
        e4 = findViewById(R.id.editTextTextPersonName5);

        try{
            ConnectionClass connectionClass = new ConnectionClass();
            con = connectionClass.connectionclass();
            String a = e1.getText()+"";
            String b1 = e2.getText()+"";
            String c = e3.getText()+"";
            String d = e4.getText()+"";

            Statement st = con.createStatement();
            ResultSet rs= st.executeQuery("select * from CurrentUser");
            String b= e4.getText()+"";

            if(con!=null){
                if(rs.next()){
                if(!a.equals("") && !b1.equals("") && !c.equals("") && !d.equals("")){
                    if(b.length()==10){
                    String gen = e2.getText()+"";
                    String genlow = gen.toLowerCase();
                    st.executeUpdate("update ClientTable set name='"+e1.getText()+"',gender='"+genlow+"',address='"+e3.getText()+"',phone='"+e4.getText()+"' where email='"+rs.getString(1)+"'");
                    Toast.makeText(this, "Profile Updated!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this,LoginClient.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);


                    }else{
                    Toast.makeText(this, "Enter Valid Phone no.", Toast.LENGTH_SHORT).show();
                }}else {
                    Toast.makeText(this, "All Fields are Mandatory", Toast.LENGTH_SHORT).show();
                }}else{
                    Toast.makeText(this, "Not Connected", Toast.LENGTH_SHORT).show();

            }}

        }catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}