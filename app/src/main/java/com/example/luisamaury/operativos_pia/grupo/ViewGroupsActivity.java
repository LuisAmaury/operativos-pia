package com.example.luisamaury.operativos_pia.grupo;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.luisamaury.operativos_pia.MyDBHandler;
import com.example.luisamaury.operativos_pia.R;

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
                unity = unity +"ID Grupo: "+ data.getString(0)+"\nID Materia: "+data.getString(1)+" Materia: "+data.getString(4)+
                        "\nID Horario: "+data.getString(2)+" Dia(s): "+data.getString(5)+"\nHora Inicio: "+data.getString(6)+" Hora Fin: "+data.getString(7)+
                        "\nCupo: "+data.getString(3);
                theList.add(unity);
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, theList);
                listView.setAdapter(listAdapter);
            }
        }
    }

}
