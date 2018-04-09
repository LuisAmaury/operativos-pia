package com.example.luisamaury.operativos_pia;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteGroupActivity extends AppCompatActivity {


    MyDBHandler myDb;
    Button btnDelete;
    EditText editDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_group);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myDb = new MyDBHandler(this);
        editDelete = (EditText)findViewById(R.id.editText_Delete);
        btnDelete = (Button)findViewById(R.id.btnDelete);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Integer deletedRows = myDb.deleteGroup(editDelete.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(DeleteGroupActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(DeleteGroupActivity.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                        editDelete.setText("");
                    }
                }
        );
    }

}
