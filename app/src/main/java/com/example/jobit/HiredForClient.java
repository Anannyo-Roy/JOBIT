package com.example.jobit;

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

public class HiredForClient extends AppCompatActivity {

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hired_for_client);

        listView = findViewById(R.id.listView);

        String currentid ="";
        ArrayList<String> workerphone = new ArrayList<>();
        ArrayList<String> phone = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> occupation = new ArrayList<>();
        ArrayList<String> gender = new ArrayList<>();



        try{
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connect = connectionClass.connectionclass();
            int ii = 0;
            if(connect!=null){
                Statement st = connect.createStatement();

                ResultSet r1 = st.executeQuery("select id from CurrentUser");
                while(r1.next()){
                    currentid = r1.getString("id").trim();
                }
                ResultSet r2 = st.executeQuery("select * from request"+currentid);
                while(r2.next()){
                    workerphone.add(r2.getString("phone").trim());
                }
                for(String s : workerphone){
                    ResultSet r3 = st.executeQuery("Select * from request"+s);
                    while(r3.next()){
                        int a = r3.getInt("reqacc");
                        int b = r3.getInt("reqrej");
                        if((a==1)){
                            phone.add(s);
                        }

                    }
                }
                for(String s : phone) {
                    ResultSet r4 = st.executeQuery("Select * from WorkerTable");
                    while (r4.next()) {
                        if (r4.getString("phone").trim().equals(s)) {
                            names.add(r4.getString("name").trim());
                            occupation.add(r4.getString("occupation"));
                            gender.add(r4.getString("gender").trim());
                        }
                    }
                }




            }
            else{
                Toast.makeText(this, "connection dekh", Toast.LENGTH_SHORT).show();
            }

        }catch(Exception e){
//            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();;
        }




        String mTitle[] = new String[names.size()];
        String mDescription[] = new String[names.size()];
        int images[] = new int[names.size()];


        for(int i = 0 ;i<images.length ; i++){
            mTitle[i] = names.get(i);
            mDescription[i] = occupation.get(i);
            if(gender.get(i).equals("female")){
                images[i] = R.drawable.baseline_person_3_24;
            }
            else{
                images[i] = R.drawable.baseline_person_4_24;
            }
        }




        MyAdapter adapter = new MyAdapter(this, mTitle, mDescription, images);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onclick1(phone.get(position));
            }
        });
        // so item click is done now check list view
    }

    public void onclick1(String a){
        Intent intent = new Intent(this , HiredForClientNext.class);
        intent.putExtra("phone" , a.trim());
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