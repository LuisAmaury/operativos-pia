package com.example.luisamaury.operativos_pia;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// Esta es como si fuera su clase, por ejemplo esta en vez de ser MainActivity deberia ser Alumno
public class Inscripcion extends AppCompatActivity {

    MyDBHandler myDb;                           // Base de datos
    EditText id, idAlumno, idGrupo, calificacion;// campos de texto
    Button btnAddData;                         // Botones
    Button btnviewAll;
    Button btnDelete;

    Button btnviewUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscripcion);
        myDb = new MyDBHandler(this);

        id = (EditText)findViewById(R.id.editText_id);
        idAlumno = (EditText)findViewById(R.id.editText_idAlumno);
        idGrupo = (EditText)findViewById(R.id.editText_idGrupo);
        calificacion = (EditText)findViewById(R.id.editText_calificacion);

        btnAddData = (Button)findViewById(R.id.button_add);
        btnviewAll = (Button)findViewById(R.id.button_viewAll);
        btnviewUpdate= (Button)findViewById(R.id.button_update);
        btnDelete= (Button)findViewById(R.id.button_delete);

        AddData();
        viewAll();
        UpdateData();
        DeleteData();
    }

    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteDataInscripcion(id.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(Inscripcion.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Inscripcion.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void UpdateData() {
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateDataInscripcion(id.getText().toString(),
                                idAlumno.getText().toString(), idGrupo.getText().toString(), calificacion.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(Inscripcion.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Inscripcion.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertInscripcion(idAlumno.getText().toString(), idGrupo.getText().toString(), calificacion.getText().toString());
                        if(isInserted == true)
                            Toast.makeText(Inscripcion.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Inscripcion.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllDataInscripcion();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("idAlumno :"+ res.getString(1)+"\n");
                            buffer.append("idGrupo :"+ res.getString(2)+"\n");
                            buffer.append("Calificacion :"+ res.getString(3)+"\n");
                        }

                        // Show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title,String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}

