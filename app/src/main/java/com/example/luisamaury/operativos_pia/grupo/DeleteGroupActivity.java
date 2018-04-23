package com.example.luisamaury.operativos_pia.grupo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luisamaury.operativos_pia.MyDBHandler;
import com.example.luisamaury.operativos_pia.R;
import com.example.luisamaury.operativos_pia.horario.DeleteScheduleActivity;

public class DeleteGroupActivity extends AppCompatActivity {



    MyDBHandler myDb;
    Button btnDelete;
    EditText editDelete;
    String check;
    boolean valido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_group);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myDb = new MyDBHandler(this);
        editDelete = (EditText) findViewById(R.id.editText_Delete);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(!validate()){
                            Toast.makeText(DeleteGroupActivity.this," Error! ",Toast.LENGTH_SHORT).show();
                        }else{
                            confirmDelete();
                        }

                    }
                }
        );
    }

    public boolean validate(){
        boolean valid = true;
        check =editDelete.getText().toString().trim();

        //Toast.makeText(DeleteGroupActivity.this,"Fit: "+check,Toast.LENGTH_SHORT).show();

        if (check.isEmpty() )
        {
            editDelete.setError("Porfavor llena el ID  del Grupo");
            valid = false;
        }


        return valid;
    }
    public void confirmDelete(){
        valido = myDb.checkGrupoInscripcion(editDelete.getText().toString());
        if (valido) {
            Integer deletedRows = myDb.deleteGroup(editDelete.getText().toString());
            if (deletedRows > 0)
                Toast.makeText(DeleteGroupActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(DeleteGroupActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();
            editDelete.setText("");
        }else{
            Toast.makeText(DeleteGroupActivity.this, "Cannot Delete, it is assigned to an inscription", Toast.LENGTH_LONG).show();
        }
    }


}
