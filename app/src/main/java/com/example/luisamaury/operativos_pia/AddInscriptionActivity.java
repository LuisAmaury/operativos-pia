package com.example.luisamaury.operativos_pia;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddInscriptionActivity extends AppCompatActivity {
    MyDBHandler myDb;                           // Base de datos
    EditText id, idAlumno, idGrupo, calificacion;// campos de texto
    Button btnAddData;                         // Botones
    Button btnviewAll;
    Button btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inscription);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myDb = new MyDBHandler(this);

        id = (EditText)findViewById(R.id.editText_id);
        idAlumno = (EditText)findViewById(R.id.editText_idAlumno);
        idGrupo = (EditText)findViewById(R.id.editText_idGrupo);
        calificacion = (EditText)findViewById(R.id.editText_calificacion);

        btnAddData = (Button)findViewById(R.id.btnAddInscription);

        AddData();
    }
    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertInscripcion(idAlumno.getText().toString(), idGrupo.getText().toString(), calificacion.getText().toString());
                        if(isInserted == true)
                            Toast.makeText(AddInscriptionActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AddInscriptionActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

}
