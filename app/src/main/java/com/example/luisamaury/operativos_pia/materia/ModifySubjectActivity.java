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

public class ModifySubjectActivity extends AppCompatActivity {

    MyDBHandler myDb;                           // Base de datos
    EditText editName,editTextId, editRequisito, editSemestre;            // campos de texto


    Button btnviewUpdate;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_subject);
        myDb = new MyDBHandler(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editName = (EditText)findViewById(R.id.editText_nameMateria);
        editTextId = (EditText)findViewById(R.id.editText_idMateria);
        editRequisito = (EditText) findViewById(R.id.editText_requisitoMateria);
        editSemestre = (EditText) findViewById(R.id.editText_semestreMateria);

        btnviewUpdate= (Button)findViewById(R.id.btnModifyNewSubject);


        UpdateData();

    }



    public void UpdateData() {
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateDataMateria(editTextId.getText().toString(),
                                editName.getText().toString(), Integer.parseInt(editRequisito.getText().toString())
                                ,Integer.parseInt(editSemestre.getText().toString()));
                        if(isUpdate == true)
                            Toast.makeText(ModifySubjectActivity.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(ModifySubjectActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();
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
