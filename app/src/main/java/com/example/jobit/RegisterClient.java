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

public class RegisterClient extends AppCompatActivity {

    Connection con;
    EditText e1,e2,e3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_client);
    }

    public void tocompletesetuppage(View v){
        e1=findViewById(R.id.edittext1);
        e2=findViewById(R.id.edittext2);
        e3=findViewById(R.id.edittext3);

        try{
            ConnectionClass connectionClass = new ConnectionClass();
            con = connectionClass.connectionclass();

            if(con!=null){
                Statement st = con.createStatement();
                String a1 = e1.getText()+"";
                String a2 = a1.toLowerCase();
                ResultSet rs = st.executeQuery("select * from ClientTable where email='"+a2+"'");
                if(rs.next()){
                    Toast.makeText(this, "Email already Registered!", Toast.LENGTH_SHORT).show();
                    e1.setText("");
                    e2.setText("");
                    e3.setText("");
                }else{
                    String email1= e1.getText()+"";
                    String[] arr= email1.split("");
                    int check1 = 0;
                    int check2 =0;
                    int check3= 0;
                    for(String i : arr){
                        if (i.equals("@")){
                            check1 = 1;
                        }
                        if(i.equals(" ")){
                            check3=1;
                        }
                    }

                    if(arr[arr.length-1].equals("m") && arr[arr.length-2].equals("o") && arr[arr.length-3].equals("c") && arr[arr.length-4].equals(".") && arr[arr.length-5].equals("l") && arr[arr.length-6].equals("i") && arr[arr.length-7].equals("a") && arr[arr.length-8].equals("m") && arr[arr.length-9].equals("g") ){
                        check2=1;
                    }
                    if(check1==1 && check2==1 && check3==0){
                        String pass1 = e2.getText()+"";
                        String pass2 = e3.getText()+"";
                        if(pass1.equals(pass2)){
                            st.executeUpdate("insert into ClientTable(email,password) values('"+a2+"','"+e2.getText()+"')");
                            Toast.makeText(this, "User Registered!", Toast.LENGTH_SHORT).show();

                            String a = e1.getText()+"";
                            String[] a3 = a.split("@");
                            st.executeUpdate("delete from CurrentUser");
                            st.executeUpdate("insert into CurrentUser values('"+e1.getText()+"','"+a3[0]+"')");
                            Toast.makeText(this, "Do not press back before setup!!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(this,AfterRegisterClient.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                            e2.setText("");
                            e3.setText("");
                        }
                    }else{
                        Toast.makeText(this, "Provide a valid EMAIL", Toast.LENGTH_SHORT).show();
                        e1.setText("");
                    }
                }

            }else{
                Toast.makeText(this, "Connection Failed", Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void backtologin(View view){
        Intent i = new Intent(this,LoginClient.class);
        startActivity(i);
        finish();
    }
}