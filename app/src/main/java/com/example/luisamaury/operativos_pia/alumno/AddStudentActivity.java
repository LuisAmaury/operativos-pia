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

public class AddStudentActivity extends AppCompatActivity {


    String tryout;
    MyDBHandler myDb;                           // Base de datos
    EditText editName, editPhone;            // campos de texto
    Button btnAddData;                         // Botones
    Button btnviewAll;


    Button btnviewUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        myDb = new MyDBHandler(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editName = (EditText)findViewById(R.id.editText_AddStudentName);
        editPhone = (EditText)findViewById(R.id.editText_AddStudentPhone);
        btnAddData = (Button)findViewById(R.id.btnAddNewStudent);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        AddData();

    }


    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String username = editName.getText().toString();
                        username = username.replace(' ', '-');
                        boolean isInserted = myDb.insertDataAlumno(editName.getText().toString(), editPhone.getText().toString(), username);
                        if(isInserted == true)
                            Toast.makeText(AddStudentActivity.this,"Informacion Ingresada",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AddStudentActivity.this,"Informacion no Ingresada",Toast.LENGTH_LONG).show();
                        editName.setText("");
                        editPhone.setText("");
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
