package com.example.jobit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class workermainpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workermainpage);
    }

    public void toprofilepage(View v){
        Intent intent = new Intent(this, WorkerProfilePage.class);
        startActivity(intent);

    }

    public void torequests(View v){
        Intent intent = new Intent(this, RequestForWorker.class);
        startActivity(intent);
    }

    public void tohired(View v){
        Intent intent = new Intent(this, JobAcceptedForWorker.class);
        startActivity(intent);
    }

    public void note2(View v){
        Intent intent = new Intent(this, notecommon.class);
        startActivity(intent);
    }



}