package com.example.luisamaury.operativos_pia.horario;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luisamaury.operativos_pia.MyDBHandler;
import com.example.luisamaury.operativos_pia.R;

public class AddScheduleActivity extends AppCompatActivity {
    MyDBHandler myDb;                           // Base de datos
    EditText editDias,editInicio, editFin;            // campos de texto
    Button btnAddData;                         // Botones
    Button btnviewAll;
    Button btnDelete;

    Button btnviewUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myDb = new MyDBHandler(this);

        editDias = (EditText)findViewById(R.id.editText_diasHorario);
        editInicio = (EditText)findViewById(R.id.editText_horaInicioHorario);
        editFin = (EditText) findViewById(R.id.editText_horaFinHorario);

        btnAddData = (Button)findViewById(R.id.btnAddSchedule);
        AddData();
    }
    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        boolean isInserted = myDb.insertHorario(editDias.getText().toString(), editInicio.getText().toString(), editFin.getText().toString());
                        if(isInserted == true)
                            Toast.makeText(AddScheduleActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AddScheduleActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
