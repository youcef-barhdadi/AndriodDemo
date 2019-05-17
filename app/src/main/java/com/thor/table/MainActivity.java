package com.thor.table;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






     final    DBConnection db = DBConnection.getDBConnection(this);


       final   MyAdpter adpter = new MyAdpter(db.GetAllAdmin());

        final ListView list =(ListView) findViewById(R.id.list);

        list.setAdapter(adpter);



        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setMessage("Are you sure About it");
                builder.setTitle("Confirmation");
                builder.setCancelable(true);
                final long  ID= id;

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.Delete(ID);


                        adpter.setAdmins(db.GetAllAdmin());
                        adpter.notifyDataSetChanged();

                        Toast.makeText(getApplicationContext(),"Delete",Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });


                AlertDialog alert11 = builder.create();
                alert11.show();






            }
        });



    }


    public  void btnAddClick(View v){

        EditText edit = (EditText)findViewById(R.id.edit);

        String value = edit.getText().toString();

       final DBConnection db = DBConnection.getDBConnection(this);

        db.InsertRowAdmin(value);


        MyAdpter adpter = new MyAdpter(db.GetAllAdmin());

        ListView list =(ListView) findViewById(R.id.list);

        list.setAdapter(adpter);







    }

    public class MyAdpter extends BaseAdapter{

        ArrayList<Admin> admins;


        public  MyAdpter(ArrayList<Admin> admins){
            this.admins = admins;
        }


        public  void  setAdmins(ArrayList<Admin> admins){
            this.admins = admins;
        }



        @Override
        public int getCount() {
            return admins.size();
        }

        @Override
        public Object getItem(int position) {
            return  (Object)admins.get(position);
        }

        @Override
        public long getItemId(int position) {
            return (long)admins.get(position).getID();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater =getLayoutInflater();
            View v = inflater.inflate(R.layout.adminlist,null);


            TextView txtViewid = (TextView)v.findViewById(R.id.MyId);
            TextView txtViewName = (TextView)v.findViewById(R.id.name);


            Admin admin =(Admin) getItem(position);

            txtViewid.setText(admin.getID()+"");
            txtViewName.setText(admin.getName());



            return v;
        }
    }
}
