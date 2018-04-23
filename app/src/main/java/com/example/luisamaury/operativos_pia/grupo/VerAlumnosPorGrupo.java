package com.example.luisamaury.operativos_pia.grupo;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.luisamaury.operativos_pia.MyDBHandler;
import com.example.luisamaury.operativos_pia.R;

import java.util.ArrayList;

public class VerAlumnosPorGrupo  extends AppCompatActivity {

    MyDBHandler myDb;
    Button btnVerAlumno;
    EditText grupoID;
    String grupoIDString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_alumnos_grupo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnVerAlumno = (Button)findViewById(R.id.btnViewID);

        Open();
    }

    public void Open(){
        btnVerAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                grupoID = (EditText) findViewById(R.id.editText_GroupID);
                grupoIDString = grupoID.getText().toString();

                ListView listView = (ListView) findViewById(R.id.listViewer);
                ArrayList<String> theList = new ArrayList <>();
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                String unity ;
                myDb = new MyDBHandler(VerAlumnosPorGrupo.this);

                Cursor data = myDb.alumnosDeUnGrupo(grupoIDString);

                if(data.getCount() == 0){
                    Toast.makeText(VerAlumnosPorGrupo.this,"No Existen Datos :(",Toast.LENGTH_SHORT).show();
                }else{
                    while(data.moveToNext()){
                        unity = "";
                        //unity = unity +"ID Grupo: "+ data.getString(0)+"\nID Materia: "+data.getString(1)+"\nID Horario: "+data.getString(2)+"\nCupo: "+data.getString(3);
                        unity = unity + "ID Alumno: " + data.getString(0)+"\nNombre: " + data.getString(1);
                        theList.add(unity);
                        ListAdapter listAdapter = new ArrayAdapter<>(VerAlumnosPorGrupo.this,android.R.layout.simple_list_item_1, theList);
                        listView.setAdapter(listAdapter);
                    }
                }

            }
        });
    }

}
