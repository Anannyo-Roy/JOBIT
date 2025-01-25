package com.example.jobit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.Statement;

public class addnote extends AppCompatActivity {
        String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnote);

        Intent intent = getIntent();
        name= intent.getStringExtra("namee");


    }

    public void onsave(View v){
        TextView t1 = findViewById(R.id.titleinput);
        String text = t1.getText().toString().trim();
        if(text.isEmpty()){
            Toast.makeText(this, "Title cannot be empty", Toast.LENGTH_SHORT).show();
        }
        else{
            TextView t2 = findViewById(R.id.editTextTextMultiLine);
            String descrip = t2.getText().toString().trim();

            try{
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connect = connectionClass.connectionclass();
                if(connect!=null){
                    Statement st = connect.createStatement();

                    st.executeUpdate("insert into note"+name.trim()+" values('"+text+"','"+descrip+"')");

                }
                else{
                    Toast.makeText(this, "connection check", Toast.LENGTH_SHORT).show();
                }

            }catch(Exception e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent(this , notecommon.class);
            startActivity(intent1);
            finish();

        }
    }
}