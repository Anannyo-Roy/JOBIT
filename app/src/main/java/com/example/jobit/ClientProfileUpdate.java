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

public class ClientProfileUpdate extends AppCompatActivity {

    Connection con;
    TextView t1;
    EditText e1,e2,e3,e4,e5;
     String cu1;
    ResultSet rs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_profile_update);
        t1= findViewById(R.id.textView);


    }

    public void updatebutton(View v){

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
                rs = st1.executeQuery("select password from ClientTable where email='"+cu1+"'");
                while(rs.next()){
                    String a = rs.getString(1)+"";
                    String b = e1.getText()+"";
                    if(b.equals(a)){
                        String c = e2.getText()+"";
                        String d = e3.getText()+"";
                        String d1 = d.toLowerCase();
                        String e = e4.getText()+"";
                        String f = e5.getText()+"";
                        int check1 = 0;
                        int check2 = 0;
                        int check3 = 0;
                        int check4 = 0;

                        if (!d.equals("")){
                            st.executeUpdate("update ClientTable set gender='"+d1+"' where email = '"+cu1+"'");
                            check2 = 1;
                        }
                        if (!e.equals("")){
                            st.executeUpdate("update ClientTable set address='"+e4.getText()+"' where email = '"+cu1+"'");
                            check3=1;
                        }
                        if (!f.equals("")){
                            String x = e5.getText()+"";
                            if(x.length()==10){
                            st.executeUpdate("update ClientTable set phone='"+e5.getText()+"' where email = '"+cu1+"'");
                            check4 =1;
                            }
                            else{
                                Toast.makeText(this, "Enter valid phone number!", Toast.LENGTH_SHORT).show();
                                e5.setText("");
                            }
                        }
                        if(check2==1 && check3==1 && check4==1){
                            if (!c.equals("")){
                                st.executeUpdate("update ClientTable set password='"+e2.getText()+"' where email = '"+cu1+"'");

                            }
                            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
                            Intent inten = new Intent(this, clientmainpage.class);
                            inten.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(inten);

//                            finish();
                        }else{
                            Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
                        }

                    }else{
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