package com.example.jobit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.Statement;

public class viewnote extends AppCompatActivity {

    String name;
    String desc;
    String curname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewnote);

        Intent intent = getIntent();
        name = intent.getStringExtra("namee");
        desc = intent.getStringExtra("desc");
        curname = intent.getStringExtra("curname");

        TextView t1 = findViewById(R.id.textView2);
        t1.setText(name);
        TextView t2 = findViewById(R.id.editTextTextMultiLine);
        t2.setText(desc);




    }

    public void ondelete(View v){
        try{
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connect = connectionClass.connectionclass();
            if(connect!=null){
                Statement st = connect.createStatement();

                st.executeUpdate("delete from note"+curname+" where head = '"+name.trim()+"'");
                Toast.makeText(this, "Note Deleted", Toast.LENGTH_SHORT).show();

            }
            else{
                Toast.makeText(this, "connection dekh", Toast.LENGTH_SHORT).show();
            }

        }catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        Intent intent1 = new Intent(this , notecommon.class);
        startActivity(intent1);
        finish();
    }

    public void onsaveclick(View v){

        TextView t1 = findViewById(R.id.editTextTextMultiLine);
        String ab = t1.getText().toString().trim();

        try{
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connect = connectionClass.connectionclass();
            if(connect!=null){
                Statement st = connect.createStatement();

                st.executeUpdate("update note"+curname+" set descrip = '"+ab+"' where head = '"+name+"'");
                Toast.makeText(this, "Saved Changes", Toast.LENGTH_SHORT).show();

            }
            else{
                Toast.makeText(this, "connection dekh", Toast.LENGTH_SHORT).show();
            }

        }catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        Intent intent1 = new Intent(this , notecommon.class);
        startActivity(intent1);
        finish();

    }
}