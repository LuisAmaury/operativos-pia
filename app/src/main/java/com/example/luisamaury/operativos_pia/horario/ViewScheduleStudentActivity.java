package com.example.luisamaury.operativos_pia.horario;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.luisamaury.operativos_pia.MyDBHandler;
import com.example.luisamaury.operativos_pia.R;

import java.util.ArrayList;

public class ViewScheduleStudentActivity extends AppCompatActivity {
    MyDBHandler myDb;
    String idAlumno, UserName, nomSubject, horaInicio, horaFin;
    String idGrupo;
    Cursor nomMateria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule_student);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView listView = (ListView) findViewById(R.id.listViewer);
        ArrayList<String> theList = new ArrayList <>();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String unity;

        Integer counter=0;
        myDb = new MyDBHandler(this);

        SharedPreferences appData = getApplicationContext().getSharedPreferences("appData", Context.MODE_PRIVATE);
        if(appData.getString("isAdmin", "").equals("false")){

            UserName = appData.getString("username","");
            
        }
        Cursor aid = myDb.getStudentID(UserName);

        while (aid.moveToNext()) { // puesto a que comienza en -1, debemos de pasarlo al siguiente, y se supone que solo debe tener 1 valor
            idAlumno = aid.getString(0);
        }

        Cursor data = myDb.getStudentDataInscripcion(idAlumno);


        if(data.getCount() == 0){
            Toast.makeText(ViewScheduleStudentActivity.this,"No Existen Datos :(",Toast.LENGTH_SHORT).show();
        }else{
            while(data.moveToNext()){
                unity = "";
                counter++;
                idGrupo =data.getString(2);
                Cursor idMateria = myDb.getIDMateriaGrupo(idGrupo);
                while(idMateria.moveToNext()){
                    nomMateria = myDb.getNameMateria( idMateria.getString(0));
                };
                while(nomMateria.moveToNext()){
                    nomSubject = nomMateria.getString(0);
                };

                unity = unity + counter+"\nID Grupo: "+data.getString(2)+"\nMateria: "+nomSubject+"\nCalificacion: "+data.getString(3);
                theList.add(unity);
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, theList);
                listView.setAdapter(listAdapter);
            }
        }
    }

}
