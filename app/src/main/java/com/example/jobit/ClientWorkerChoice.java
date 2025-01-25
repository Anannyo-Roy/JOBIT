package com.example.jobit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ClientWorkerChoice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_worker_choice);
    }

    public void clientlogintrans(View view){
        Intent i = new Intent(this,LoginClient.class);
        startActivity(i);

    }
    public void workerlogintrans(View view){
        Intent i = new Intent(this,LoginWorker.class);
        startActivity(i);

    }
}