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

public class ModifyScheduleActivity extends AppCompatActivity {
    MyDBHandler myDb;                           // Base de datos
    EditText editDias,editInicio, editFin,editTextId;
    Button btnAddData;                         // Botones
    Button btnviewAll;
    Button btnDelete;

    Button btnviewUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_schedule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myDb = new MyDBHandler(this);

        editTextId = (EditText)findViewById(R.id.editText_id);
        editDias = (EditText)findViewById(R.id.editText_diasHorario);
        editInicio = (EditText)findViewById(R.id.editText_horaInicioHorario);
        editFin = (EditText) findViewById(R.id.editText_horaFinHorario);
        btnviewUpdate= (Button)findViewById(R.id.btnModifySchedule);

        UpdateData();
    }

    public void UpdateData() {
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateDataHorario(editTextId.getText().toString(),
                                editDias.getText().toString(), editInicio.getText().toString(), editFin.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(ModifyScheduleActivity.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(ModifyScheduleActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

}
