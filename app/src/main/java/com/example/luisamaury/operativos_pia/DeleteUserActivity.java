package com.example.luisamaury.operativos_pia;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luisamaury.operativos_pia.R;
import com.example.luisamaury.operativos_pia.materia.DeleteSubjectActivity;

public class DeleteUserActivity extends AppCompatActivity {
    MyDBHandler myDb;
    Button btnDelete;
    EditText editDelete;
    String check;
    boolean valido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);
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
                            Toast.makeText(DeleteUserActivity.this," Error! ",Toast.LENGTH_SHORT).show();
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
            editDelete.setError("Porfavor llena el Id del Usuario");
            valid = false;
        }


        return valid;
    }
    public void confirmDelete(){
        valido = myDb.checkUserStudent(editDelete.getText().toString());
        if(valido) {
            Integer deletedRows = myDb.deleteUser(editDelete.getText().toString());
            if (deletedRows > 0)
                Toast.makeText(DeleteUserActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(DeleteUserActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();
            editDelete.setText("");
        }else{
            Toast.makeText(DeleteUserActivity.this, "Cannot Delete, it is assigned to a student", Toast.LENGTH_LONG).show();
        }
    }

}
