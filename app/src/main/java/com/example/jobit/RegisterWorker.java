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

public class RegisterWorker extends AppCompatActivity {
    Connection con;
    EditText e1,e2,e3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_worker);
    }
    public void backtologin(View view){
        Intent i = new Intent(this,LoginWorker.class);
        startActivity(i);
    }

    public void tocompletesetuppage(View v){
        e1=findViewById(R.id.edittextone);
        e2=findViewById(R.id.edittextpass1);
        e3=findViewById(R.id.editpassone);

        try{
            ConnectionClass connectionClass = new ConnectionClass();
            con = connectionClass.connectionclass();

            if(con!=null){
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from WorkerTable where phone='"+e1.getText()+"'");
                if(rs.next()){
                    Toast.makeText(this, "Phone already Registered!", Toast.LENGTH_SHORT).show();
                    e1.setText("");
                    e2.setText("");
                    e3.setText("");
                }else{
                    String email1= e1.getText()+"";;
                    int check = 0;
                    if(email1.length()==10){
                        check=1;
                    }

                    if(check==1){
                        String pass1 = e2.getText()+"";
                        String pass2 = e3.getText()+"";
                        if(pass1.equals(pass2)){
                            st.executeUpdate("insert into WorkerTable(phone,password) values('"+e1.getText()+"','"+e2.getText()+"')");
                            Toast.makeText(this, "User Registered!", Toast.LENGTH_SHORT).show();
                            st.executeUpdate("delete from CurrentUser");
                            st.executeUpdate("insert into CurrentUser(email,id) values('"+e1.getText()+"','"+e1.getText()+"')");
                            Toast.makeText(this, "Do not press back before setup!!", Toast.LENGTH_SHORT).show();
//                            st.executeUpdate("insert into CurrentUser(email) values('"+e1.getText()+"')");
                            Intent intent = new Intent(this,AfterRegisterWorker.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                            e2.setText("");
                            e3.setText("");
                        }
                    }else{
                        Toast.makeText(this, "Provide a valid Phone No.", Toast.LENGTH_SHORT).show();
                    }
                }

            }else{
                Toast.makeText(this, "Connection Failed", Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}