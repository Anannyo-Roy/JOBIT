package com.example.jobit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class WorkerProfileUpdate extends AppCompatActivity {

    Connection con;
    TextView t1;
    EditText e1,e2,e3,e4,e5;
    String cu1;
    ResultSet rs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_profile_update);
    }


    public void updatebutton1(View view){

        e1=findViewById(R.id.editTextTextPersonName);
        e2=findViewById(R.id.editTextTextPersonName2);
        e3=findViewById(R.id.editTextTextPersonName3);
        e4=findViewById(R.id.editTextTextPersonName4);
        e5=findViewById(R.id.editTextTextPersonName5);



        try{
            ConnectionClass connectionClass = new ConnectionClass();
            con = connectionClass.connectionclass();

            if(con!=null){
                Statement st = con.createStatement();
                Statement st1 = con.createStatement();
                ResultSet cu = st.executeQuery("select * from CurrentUser");

                while(cu.next()){
                    cu1 = cu.getString(1)+"";}
                rs = st1.executeQuery("select password from WorkerTable where phone='"+cu1+"'");
                while(rs.next()){
                    String a = rs.getString(1)+"";
                    String b = e1.getText()+"";
                    if(b.equals(a)){
                        String c = e2.getText()+"";
                        String d = e3.getText()+"";
                        String d1 = d.toLowerCase();
                        String e = e2.getText()+"";
                        String f = e2.getText()+"";
                        int check1 = 0;
                        int check2 = 0;
                        int check3 = 0;
                        int check4 = 0;
                        if (!d.equals("")){
                            st.executeUpdate("update WorkerTable set gender='"+d1+"' where phone = '"+cu1+"'");
                            check2 = 1;
                        }
                        if (!e.equals("")){
                            st.executeUpdate("update WorkerTable set address='"+e4.getText()+"' where phone = '"+cu1+"'");
                            check3=1;
                        }
                        if (!f.equals("")){
                                st.executeUpdate("update WorkerTable set occupation='"+e5.getText()+"' where phone = '"+cu1+"'");
                            check1= 1;
                        }
                        if(check2==1 && check3==1 && check1==1){
                            if (!c.equals("")){
                                st.executeUpdate("update WorkerTable set password='"+e2.getText()+"' where phone = '"+cu1+"'");
//                                check4 =1;

                            }
                            Intent inten = new Intent(this, workermainpage.class);
                            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
                            inten.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(inten);
                    }}else{
                        Toast.makeText(this, "Old Password doesnt match", Toast.LENGTH_SHORT).show();
                    }
                }
            }else{
                Toast.makeText(this, "No current User", Toast.LENGTH_SHORT).show();
            }

        }catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}