package com.example.luisamaury.operativos_pia;

import android.database.Cursor;
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

public class AddSubjectActivity extends AppCompatActivity {


    MyDBHandler myDb;                           // Base de datos
    EditText editName,editTextId, editRequisito;            // campos de texto
    Button btnAddData;                         // Botones
    Button btnviewAll;
    Button btnDelete;

    Button btnviewUpdate;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);
        myDb = new MyDBHandler(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editName = (EditText)findViewById(R.id.editText_nameMateria);

        editRequisito = (EditText) findViewById(R.id.editText_requisitoMateria);

        btnAddData = (Button)findViewById(R.id.btnAddNewSubject);

        AddData();

    }



    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertDataMateria(editName.getText().toString(),Integer.parseInt(editRequisito.getText().toString()));
                        if(isInserted == true)
                            Toast.makeText(AddSubjectActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AddSubjectActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
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
