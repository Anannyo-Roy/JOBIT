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

public class LoginWorker extends AppCompatActivity {

    Connection con;
    EditText e1,e2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_worker);
    }

    public void toRegister(View view){
        Intent intent = new Intent(this, RegisterWorker.class);
        startActivity(intent);

    }

    public void workerlogintrans(View view){
        e1 = findViewById(R.id.edittext1);
        e2 = findViewById(R.id.edittext2);

        try{
            ConnectionClass connectionClass = new ConnectionClass();
            con = connectionClass.connectionclass();
            if(con!=null){
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("Select * from WorkerTable where phone='"+e1.getText()+"' and password='"+e2.getText()+"'");
                if(rs.next()){
                    st.executeUpdate("delete from CurrentUser");
                    st.executeUpdate("insert into CurrentUser(email,id) values('"+e1.getText()+"','"+e1.getText()+"')");
                    Intent intent = new Intent(this,workermainpage.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);


                }else{
                    Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    e1.setText("");
                    e2.setText("");
                }

            }else{
                Toast.makeText(this, "Connection failing", Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}