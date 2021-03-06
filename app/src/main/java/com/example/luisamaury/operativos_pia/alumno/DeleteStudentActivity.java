package com.example.luisamaury.operativos_pia.alumno;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luisamaury.operativos_pia.MyDBHandler;
import com.example.luisamaury.operativos_pia.R;
import com.example.luisamaury.operativos_pia.grupo.DeleteGroupActivity;

public class DeleteStudentActivity extends AppCompatActivity {

    MyDBHandler myDb;                           // Base de datos
    EditText editName,editTextId, editPhone, editIdUsuario;            // campos de texto
    Button btnAddData;                         // Botones
    Button btnviewAll;
    Button btnDelete;

    Button btnviewUpdate;
    boolean valido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_delete_student);
        myDb = new MyDBHandler(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editName = (EditText)findViewById(R.id.editText_nameAlumno);
        editTextId = (EditText)findViewById(R.id.editText_idAlumno);
        // editPhone = (EditText)findViewById(R.id.editText_telefonoAlumno);
        // editIdUsuario = (EditText)findViewById(R.id.editText_idUsuarioAlumno);

        //btnAddData = (Button)findViewById(R.id.button_add);
        //btnviewAll = (Button)findViewById(R.id.button_viewAll);
        //btnviewUpdate= (Button)findViewById(R.id.button_update);
        btnDelete= (Button)findViewById(R.id.btnDeleteNewStudent);

        DeleteData();
    }

    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        valido = myDb.checkAlumnoInscripcion(editTextId.getText().toString());
                        if(valido) {
                            Integer deletedRows = myDb.deleteDataAlumno(editTextId.getText().toString());
                            if (deletedRows > 0)
                                Toast.makeText(DeleteStudentActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(DeleteStudentActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();
                            editName.setText("");
                            editTextId.setText("");
                        }else{
                            Toast.makeText(DeleteStudentActivity.this, "Cannot Delete, it is assigned to an inscription", Toast.LENGTH_LONG).show();
                        }
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
