package com.example.jobit;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class JobAcceptedForWorker extends AppCompatActivity {


    // so our images and other things are set in array
    Connection connect;
    // now paste some images in drawable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_accepted_for_worker);



        ListView listView;
        ArrayList<String> mTitle = new ArrayList<>();
        ArrayList<String> mDescription = new ArrayList<>();


        listView = findViewById(R.id.listView);
        ArrayList<Integer> arr1 = new ArrayList<>();
        ArrayList<String> arr = new ArrayList<>();

        try{
            ConnectionClass connectionClass = new ConnectionClass();
            connect = connectionClass.connectionclass();
            int ii = 0;
            if(connect!=null){
                Statement st = connect.createStatement();
                ResultSet cu = st.executeQuery("select * from CurrentUser");
                String cu1 = "";
                if(cu.next()){
                    cu1 = cu.getString(1);
                }
                Statement st1 = connect.createStatement();
                ResultSet rs = st1.executeQuery("Select * from request"+cu1);
                while(rs.next()){

                    String email = rs.getString("email")+"@gmail.com";
                    String email1 = rs.getString("email");
//                    String note = rs.getString("note")+"";
                    int reqacc = rs.getInt("reqacc");
                    int reqrej = rs.getInt("reqrej");

                    if(reqacc==1 && reqrej==0){
                        Statement st2 = connect.createStatement();
                        ResultSet info = st2.executeQuery("Select name,gender from ClientTable where email='"+email+"'");
                        while(info.next()){
                            mTitle.add(info.getString("name"));
                            mDescription.add(email);
                            if(info.getString("gender").equals("female")){
                                arr1.add(ii);
                            }
                            ii++;
                            arr.add(email1);
                        }
                    }




//
//                    mTitle.add(rs.getString("name"));
//                    arr.add(rs.getString("phone"));
//                    mDescription.add(rs.getString("occupation"));
//                    ii++;
                }

            }
            else{
                Toast.makeText(this, "connection dekh", Toast.LENGTH_SHORT).show();
            }

        }catch(Exception e){
//            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        int images[] = new int[mDescription.size()];
        for(int i = 0 ; i<images.length ; i++){
            boolean b = true;
            for (int j : arr1 ){
                if(i==j){
                    b = false;
                }
            }
            if(b){
                images[i] = R.drawable.baseline_person_4_24;
            }
            else{
                images[i] = R.drawable.baseline_person_3_24;
            }

        }
        String[] mTitle1 = new String[images.length];
        String[] mDescription1 = new String[images.length];

        for(int i = 0 ; i< images.length ; i++){
            mTitle1[i] = mTitle.get(i);
            mDescription1[i] = mDescription.get(i);

        }
        // now create an adapter class

        MyAdapter adapter = new MyAdapter(this, mTitle1, mDescription1, images);
        listView.setAdapter(adapter);
        // there is my mistake...
        // now again check this..

        // now set item click on list view

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onclick(arr.get(position));

            }
        });
        // so item click is done now check list view
    }
    public void onclick(String a){
        Intent intent = new Intent(this , JobAcceptedForWorkerNext.class);
        intent.putExtra("emaill" , a);
        startActivity(intent);
    }



    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String rTitle[];
        String rDescription[];
        int rImgs[];

        MyAdapter (Context c, String title[], String description[], int imgs[]) {
            super(c, R.layout.row, R.id.textView1, title);
            this.context = c;
            this.rTitle = title;
            this.rDescription = description;
            this.rImgs = imgs;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView images = row.findViewById(R.id.image);
            TextView myTitle = row.findViewById(R.id.textView1);
            TextView myDescription = row.findViewById(R.id.textView2);

            // now set our resources on views
            images.setImageResource(rImgs[position]);
            myTitle.setText(rTitle[position]);
            myDescription.setText(rDescription[position]);


            return row;
        }


    }
}