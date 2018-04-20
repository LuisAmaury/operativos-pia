package com.example.luisamaury.operativos_pia.materia;

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

public class DeleteSubjectActivity extends AppCompatActivity {
    MyDBHandler myDb;                           // Base de datos
    EditText editName,editTextId, editRequisito;            // campos de texto
    Button btnAddData;                         // Botones
    Button btnviewAll;
    Button btnDelete;

    Button btnviewUpdate;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_subject);
        myDb = new MyDBHandler(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editName = (EditText)findViewById(R.id.editText_nameMateria);
        editTextId = (EditText)findViewById(R.id.editText_idMateria);


        btnDelete= (Button)findViewById(R.id.btnDeleteNewSubject);

        DeleteData();
    }

    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteDataMateria(editTextId.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(DeleteSubjectActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(DeleteSubjectActivity.this,"Data not Deleted",Toast.LENGTH_LONG).show();
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