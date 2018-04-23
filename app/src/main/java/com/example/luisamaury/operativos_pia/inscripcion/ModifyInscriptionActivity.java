package com.example.luisamaury.operativos_pia.inscripcion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luisamaury.operativos_pia.MyDBHandler;
import com.example.luisamaury.operativos_pia.R;

public class ModifyInscriptionActivity extends AppCompatActivity {
    MyDBHandler myDb;                           // Base de datos
    EditText id, idAlumno, idGrupo, calificacion;// campos de texto
    Button btnAddData;                         // Botones
    Button btnviewAll;
    Button btnDelete;

    Button btnviewUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_inscription);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        id = (EditText)findViewById(R.id.editText_id);
        idAlumno = (EditText)findViewById(R.id.editText_idAlumno);
        idGrupo = (EditText)findViewById(R.id.editText_idGrupo);
        calificacion = (EditText)findViewById(R.id.editText_calificacion);
        myDb = new MyDBHandler(this);
        btnviewUpdate= (Button)findViewById(R.id.btnModifyInscription);

        UpdateData();
    }
    public void UpdateData() {
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String idInscripcion = id.getText().toString();
                        String idAlumnoString = idAlumno.getText().toString();
                        String idGrupoString = idGrupo.getText().toString();
                        String calificacionString = calificacion.getText().toString();
                        boolean isUpdate = myDb.updateDataInscripcion(idInscripcion, idAlumnoString, idGrupoString, calificacionString);

                        if(isUpdate == true)
                            Toast.makeText(ModifyInscriptionActivity.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(ModifyInscriptionActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

}
