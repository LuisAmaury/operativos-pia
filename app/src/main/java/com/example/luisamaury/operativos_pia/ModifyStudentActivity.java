package com.example.luisamaury.operativos_pia;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModifyStudentActivity extends AppCompatActivity {

    MyDBHandler myDb;                           // Base de datos
    EditText editName,editTextId, editPhone, editIdUsuario;            // campos de texto


    Button btnviewUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_student);
        myDb = new MyDBHandler(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editName = (EditText)findViewById(R.id.editText_ModifyStudentName);
        editTextId = (EditText)findViewById(R.id.editText_ModifyStudentID);
        editPhone = (EditText)findViewById(R.id.editText_ModifyStudentPhone);
        editIdUsuario = (EditText)findViewById(R.id.editText_ModifyStudentIDUser);

        btnviewUpdate= (Button)findViewById(R.id.btnModifyStudent);


        UpdateData();
    }

    public void UpdateData() {
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateDataAlumno(editTextId.getText().toString(),
                                editName.getText().toString(), editPhone.getText().toString(), Integer.parseInt(editIdUsuario.getText().toString()));
                        if(isUpdate == true)
                            Toast.makeText(ModifyStudentActivity.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(ModifyStudentActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();
                        editName.setText("");
                        editTextId.setText("");
                        editPhone.setText("");
                        editIdUsuario.setText("");
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
