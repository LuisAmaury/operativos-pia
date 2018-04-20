package com.example.luisamaury.operativos_pia;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewGroupsActivity extends AppCompatActivity {

    MyDBHandler myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_groups);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView listView = (ListView) findViewById(R.id.listViewer);
        ArrayList<String> theList = new ArrayList <>();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String unity ;
        myDb = new MyDBHandler(this);

        Cursor data = myDb.viewAllGroups();

        if(data.getCount() == 0){
            Toast.makeText(ViewGroupsActivity.this,"No Existen Datos :(",Toast.LENGTH_SHORT).show();
        }else{
            while(data.moveToNext()){
                unity = "";
                unity = unity +"ID Grupo: "+ data.getString(0)+"\nID Materia: "+data.getString(1)+"\nID Horario: "+data.getString(2)+"\nCupo: "+data.getString(3);
                theList.add(unity);
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, theList);
                listView.setAdapter(listAdapter);
            }
        }
    }

}
