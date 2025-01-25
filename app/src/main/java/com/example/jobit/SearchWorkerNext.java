package com.example.jobit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class SearchWorkerNext extends AppCompatActivity {

    Connection connect;
    TextView t;
    static String phn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_worker_next);



        Intent intent = getIntent();
        String a = intent.getStringExtra("emaill");
//        System.out.println(a);
        phn = a;
        TextView ph = findViewById(R.id.textView7);
        ph.setText(a.trim());
        TextView add = findViewById(R.id.textView8);
        TextView name = findViewById(R.id.textView);
        TextView occ = findViewById(R.id.textView9);
        ImageView i = findViewById(R.id.imageView3);
        TextView gend = findViewById(R.id.textViewg);

        try{
            ConnectionClass connectionClass = new ConnectionClass();
            connect = connectionClass.connectionclass();
            if(connect!=null){
                Statement st = connect.createStatement();



                ResultSet rs = st.executeQuery("Select * from WorkerTable where phone = '"+a+"'");

                if(rs.next()){
                    if(rs.getString("gender").equals("female")){
                        i.setImageResource(R.drawable.baseline_person_3_24);
                    }
                    else{
                        i.setImageResource(R.drawable.baseline_person_4_24);
                    }
                    gend.setText(rs.getString("gender").trim());
                    name.setText(rs.getString("name").trim());
//                    ph.setText(rs.getString("phonenumber").trim());
                    add.setText(rs.getString("address").trim());
                    occ.setText(rs.getString("occupation").trim());


                }

            }
            else{
                Toast.makeText(this, "connection check", Toast.LENGTH_SHORT).show();
            }

        }catch(Exception e){
//            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }



    }
    public void onclick1(View view){
        t = findViewById(R.id.textView6);
        TextView t1 = findViewById(R.id.write1);
        String textt = t1.getText().toString().trim();
        if(!textt.isEmpty()) {
            if (t.getText().equals("Send Request")) {
                t.setText("Sent!");


                try{
                    ConnectionClass connectionClass = new ConnectionClass();
                    Connection connect1 = connectionClass.connectionclass();

                    if(connect1!=null){
                        Statement st = connect1.createStatement();
                        ResultSet rs = st.executeQuery("SELECT Distinct TABLE_NAME FROM information_schema.TABLES");
                        boolean bb = false;
                        while(rs.next()){
                            String a = rs.getString(1).trim();
                            if(a.equals("request"+phn.trim())){
                                bb = true;
                                break;
                            }
                        }
                        ArrayList<String> aa= new ArrayList<>();
                        TextView t2 = findViewById(R.id.write1);
                        ResultSet r = st.executeQuery("Select * from CurrentUser");

                        while (r.next()) {
                            aa.add(r.getString(2));
                        }
                        if(bb){

//                            Toast.makeText(this, "bani hui ", Toast.LENGTH_SHORT).show();

                            st.executeUpdate("insert into " + "request" + phn.trim() + " values('" +aa.get(0) + "' , '" + t2.getText().toString() + "' , "+0+" , "+0+")");
                        }
                        else {
//                            Toast.makeText(this, "nhi bani hu", Toast.LENGTH_SHORT).show();

                            st.executeUpdate("create table " + "request" + phn.trim() + "(email varchar(30) , note varchar(200) , reqacc int , reqrej int )");
                            st.executeUpdate("insert into " + "request" + phn.trim() + " values('" + aa.get(0) + "' , '" + t2.getText().toString() + "' , "+0+" , "+0+")");

                        }
                        ResultSet r1 = st.executeQuery("SELECT Distinct TABLE_NAME FROM information_schema.TABLES");

                        bb= false;

                        while(r1.next()){
                            String a = r1.getString(1);
                            if(a.equals("request"+aa.get(0).trim())){
                                bb = true;
                                break;
                            }

                        }
//                        Toast.makeText(this, "hiii", Toast.LENGTH_SHORT).show();
                        if(bb){
                            st.executeUpdate("insert into " + "request" + aa.get(0).trim() + " values('" + phn.trim() + "' , "+0+" , "+0+")");
                        }
                        else{
                            st.executeUpdate("create table " + "request" +  aa.get(0).trim()+ "(phone varchar(30) , reqacc int , reqrej int )");
                            st.executeUpdate("insert into " + "request" + aa.get(0).trim() + " values('" + phn.trim() + "' , "+0+" , "+0+")");
                        }

                        Toast.makeText(this, "sent!", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(this, "connection check", Toast.LENGTH_SHORT).show();
                    }

                }catch(Exception e){
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();;
                }
                Intent intent = new Intent(this, clientmainpage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);


            } else {
                Toast.makeText(this, "already been sent", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Request Description cannot be empty", Toast.LENGTH_SHORT).show();
        }



    }



}