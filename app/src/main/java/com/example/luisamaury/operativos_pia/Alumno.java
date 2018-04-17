package com.example.luisamaury.operativos_pia;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Alumno extends AppCompatActivity {

    MyDBHandler myDb;                           // Base de datos
    EditText editName,editTextId, editPhone, editIdUsuario;            // campos de texto
    Button btnAddData;                         // Botones
    Button btnviewAll;
    Button btnDelete;

    Button btnviewUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alumno);
        myDb = new MyDBHandler(this);

        editName = (EditText)findViewById(R.id.editText_nameAlumno);
        editTextId = (EditText)findViewById(R.id.editText_idAlumno);
        editPhone = (EditText)findViewById(R.id.editText_telefonoAlumno);
        editIdUsuario = (EditText)findViewById(R.id.editText_idUsuarioAlumno);

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
                        Integer deletedRows = myDb.deleteDataAlumno(editTextId.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(Alumno.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Alumno.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void UpdateData() {
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateDataAlumno(editTextId.getText().toString(),
                                editName.getText().toString(), editPhone.getText().toString(), Integer.parseInt(editIdUsuario.getText().toString()));
                        if(isUpdate == true)
                            Toast.makeText(Alumno.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Alumno.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertDataAlumno(editName.getText().toString(), editPhone.getText().toString(), Integer.parseInt(editIdUsuario.getText().toString()));
                        if(isInserted == true)
                            Toast.makeText(Alumno.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Alumno.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllDataAlumno();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Name :"+ res.getString(1)+"\n");
                            buffer.append("Phone :"+ res.getString(2)+"\n");
                            buffer.append("IdUsuario :"+ res.getString(3)+"\n");
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
