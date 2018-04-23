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

public class DeleteScheduleActivity extends AppCompatActivity {
    MyDBHandler myDb;                           // Base de datos
    EditText editDias,editInicio, editFin,editTextId;
    Button btnAddData;                         // Botones
    Button btnviewAll;
    Button btnDelete;
    boolean valido;

    Button btnviewUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_schedule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myDb = new MyDBHandler(this);

        editTextId = (EditText)findViewById(R.id.editText_id);
        editDias = (EditText)findViewById(R.id.editText_diasHorario);
        editInicio = (EditText)findViewById(R.id.editText_horaInicioHorario);
        editFin = (EditText) findViewById(R.id.editText_horaFinHorario);

        btnDelete = (Button)findViewById(R.id.btnDeleteSchedule);
        DeleteData();
    }

    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        valido = myDb.checkHorarioGrupo(editTextId.getText().toString());
                        if (valido) {
                            Integer deletedRows = myDb.deleteDataHorario(editTextId.getText().toString());
                            if (deletedRows > 0) {
                                Toast.makeText(DeleteScheduleActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                            }else
                                Toast.makeText(DeleteScheduleActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(DeleteScheduleActivity.this, "Cannot Delete, it is assigned to a group", Toast.LENGTH_LONG).show();
                        }

                    }
                }
        );
    }

}
