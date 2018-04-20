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

public class DeleteInscriptionActivity extends AppCompatActivity {
    MyDBHandler myDb;                           // Base de datos
    EditText id, idAlumno, idGrupo, calificacion;// campos de texto
    Button btnAddData;                         // Botones
    Button btnviewAll;
    Button btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_inscription);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myDb = new MyDBHandler(this);

        id = (EditText)findViewById(R.id.editText_id);
        idAlumno = (EditText)findViewById(R.id.editText_idAlumno);
        idGrupo = (EditText)findViewById(R.id.editText_idGrupo);
        calificacion = (EditText)findViewById(R.id.editText_calificacion);

        btnDelete = (Button)findViewById(R.id.btnDeleteInscription);
        DeleteData();

    }
    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteDataInscripcion(id.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(DeleteInscriptionActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(DeleteInscriptionActivity.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

}
