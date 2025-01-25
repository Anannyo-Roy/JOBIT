package com.example.jobit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class clientmainpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientmainpage);
    }

    public void toprofile(View v){
        Intent intent = new Intent(this, ClientProfilePage.class);
        startActivity(intent);
    }

    public void toWorkerSearchPage(View v){
        Intent intent = new Intent(this, SearchWorker.class);
        startActivity(intent);
    }

    public void topendingreq(View v){
        Intent intent = new Intent(this, RequestsForClient.class);
        startActivity(intent);
    }

    public void notee(View v){
        Intent intent = new Intent(this, notecommon.class);
        startActivity(intent);
    }

    public void tophiredclients(View v){
        Intent intent = new Intent(this, HiredForClient.class);
        startActivity(intent);
    }


}