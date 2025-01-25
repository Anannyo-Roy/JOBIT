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

public class LoginClient extends AppCompatActivity {

    Connection con;
    EditText e1,e2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_client);
    }

    public void clientlogintrans(View view){
        e1 = findViewById(R.id.edittext1);
        e2 = findViewById(R.id.edittext2);

        try{
            ConnectionClass connectionClass = new ConnectionClass();
            con = connectionClass.connectionclass();
            if(con!=null){
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("Select * from ClientTable where email='"+e1.getText()+"' and password='"+e2.getText()+"'");
                if(rs.next()){
                    String a = e1.getText()+"";
                    String[] a1 = a.split("@");
                    st.executeUpdate("delete from CurrentUser");
                    st.executeUpdate("insert into CurrentUser values('"+e1.getText()+"','"+a1[0]+"')");
                    Intent intent = new Intent(this,clientmainpage.class);
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

    public void toRegister(View view){
        Intent x = new Intent(this,RegisterClient.class);
        startActivity(x);

    }
}