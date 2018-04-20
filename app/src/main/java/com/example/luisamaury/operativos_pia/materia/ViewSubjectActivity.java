package com.example.luisamaury.operativos_pia.materia;

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

public class ViewSubjectActivity extends AppCompatActivity {

    MyDBHandler myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_subject);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView listView = (ListView) findViewById(R.id.listViewer);
        ArrayList<String> theList = new ArrayList <>();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String unity ;
        myDb = new MyDBHandler(this);

        Cursor data = myDb.getAllDataMateria();

        if(data.getCount() == 0){
            Toast.makeText(ViewSubjectActivity.this,"No Existen Datos :(",Toast.LENGTH_SHORT).show();
        }else{
            while(data.moveToNext()){
                unity = "";
                unity = unity + data.getString(0)+".- "+data.getString(1)+"\n\tRequisito: "+data.getString(2);
                theList.add(unity);
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, theList);
                listView.setAdapter(listAdapter);
            }
        }
    }

}
