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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class notecommon extends AppCompatActivity {

    static String curname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notecommon);

        ListView listView;


        listView = findViewById(R.id.listView);
        ArrayList<String> heads= new ArrayList<>();
        ArrayList<String> desc = new ArrayList<>();
        try{
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connect = connectionClass.connectionclass();
            if(connect!=null){
                Statement st = connect.createStatement();

                ResultSet r1 = st.executeQuery("Select * from CurrentUser");
                String currentname = "";
                while(r1.next()){
                    currentname =r1.getString("id").trim();
                }
                curname = currentname;
                ResultSet r2 = st.executeQuery("SELECT Distinct TABLE_NAME FROM information_schema.TABLES");
                boolean b = false;
                while(r2.next()){
                    if(r2.getString(1).equals("note"+currentname)){
                        b= true;
                    }
                }

                if(b){
                    ResultSet r3 = st.executeQuery("Select * from note"+currentname);
                    while(r3.next()){
                        heads.add(r3.getString(1).trim());
                        desc.add(r3.getString(2));
                    }
                }
                else{
                    st.executeUpdate("Create table note"+currentname+"(head varchar(100) , descrip varchar(300))");
                }

            }
            else{
                Toast.makeText(this, "Not Connectedf ", Toast.LENGTH_SHORT).show();
            }

        }catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        String[] mTitle1 = new String[heads.size()];
        for(int i = 0 ; i<mTitle1.length ;i++ ){
            mTitle1[i] = heads.get(i);
        }



        MyAdapter adapter = new MyAdapter(this, mTitle1);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onclick(heads.get(position) , desc.get(position));

            }
        });
    }
    public void onclick(String a , String b){
        Intent intent = new Intent(this , viewnote.class);
        intent.putExtra("namee" , a);
        intent.putExtra("desc" , b);
        intent.putExtra("curname" ,curname);
        startActivity(intent);
        finish();

    }

    public void onaddclick(View v){
        Intent intent = new Intent(this , addnote.class);
        intent.putExtra("namee" , curname);
        startActivity(intent);
        finish();
    }



    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String rTitle[];


        MyAdapter (Context c, String title[]) {
            super(c, R.layout.row1, R.id.textView1, title);
            this.context = c;
            this.rTitle = title;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row1, parent, false);

            TextView myTitle = row.findViewById(R.id.textView1);

            // now set our resources on views

            myTitle.setText(rTitle[position]);



            return row;
}


}
}
