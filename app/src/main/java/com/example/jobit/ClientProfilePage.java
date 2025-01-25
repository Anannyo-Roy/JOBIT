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

public class ClientProfilePage extends AppCompatActivity {

    Connection con;
    TextView t1,t2,t3,t4,t5,t6,t7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_profile_page);

        try {
            ConnectionClass connectionClass = new ConnectionClass();
            con = connectionClass.connectionclass();

            t1=findViewById(R.id.textViewsetup); //name
            t2=findViewById(R.id.textView2);  //email
            t3=findViewById(R.id.textView7); //pass
            t4=findViewById(R.id.textView8);    //phone
            t5=findViewById(R.id.textView9);    //gen
            t6=findViewById(R.id.textView10);   //address
            t7=findViewById(R.id.textView11);   //hired

            if(con!=null) {
                Statement st = con.createStatement();
                ResultSet cu = st.executeQuery("select * from CurrentUser");
                if (cu.next()) {
                    ResultSet rs = st.executeQuery("select name,email,password,phone,gender,address from ClientTable where email='"+cu.getString(1)+"'");
                    if(rs.next()){
                        t1.setText(rs.getString(1));
                        t2.setText(rs.getString(2));
                        String pass = rs.getString(3)+"";
                        String pass1 = "";
                        for(int i = 0; i<pass.length(); i++){
                            pass1+="*";
                        }
                        t3.setText(pass1);
                        t4.setText(rs.getString(4));
                        t5.setText(rs.getString(5));
                        t6.setText(rs.getString(6));
                    }
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void toupdateprofile(View v){
        Intent intent = new Intent(this, ClientProfileUpdate.class);
        startActivity(intent);
    }

    public void signout(View v){
        Intent intent = new Intent(this, ClientWorkerChoice.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}