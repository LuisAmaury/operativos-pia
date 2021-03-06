package com.example.luisamaury.operativos_pia.grupo;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.luisamaury.operativos_pia.MyDBHandler;
import com.example.luisamaury.operativos_pia.R;

import java.util.List;

public class AddGroupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    Spinner spinner, spinner2;
    String nameSubject, subID, idSubject, nameHour, idHour, fit;
    Button btnViewSubjectID;
    EditText editFit;
    View viewer;
    Integer namePosition;

    MyDBHandler db = new MyDBHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner)findViewById(R.id.spinner2);
        editFit = (EditText) findViewById(R.id.editText_Fit);
        btnViewSubjectID = (Button)findViewById(R.id.btnViewID);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if(bundle.getString("some") != null){
                Toast.makeText(getApplicationContext(),
                        "data: "+ bundle.getString("some"),
                        Toast.LENGTH_SHORT).show();
            }
        }
        loadSpinnerData(); //for Subjects
        loadSpinnerData2(); //for Schedule

        spinner.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);


        clickBtn();

    }
    private void loadSpinnerData() {
        // database handler
        MyDBHandler db = new MyDBHandler(getApplicationContext());

        // Spinner Drop down elements
        List<String> lables = db.getAllSubjects();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner.setAdapter(dataAdapter);
    }

    private void loadSpinnerData2(){
        // database handler
        MyDBHandler db = new MyDBHandler(getApplicationContext());

        // Spinner Drop down elements
        List<String> schedules = db.getAllSchedules();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, schedules);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner2.setAdapter(dataAdapter);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        Spinner spinnerCheck = (Spinner) parent;

        if(spinnerCheck.getId() == R.id.spinner)
        {
            String label = parent.getItemAtPosition(position).toString();
            namePosition = position;
            nameSubject = label;

            // Showing selected spinner item
            //Toast.makeText(parent.getContext(), "You selected : " + label,
            //      Toast.LENGTH_SHORT).show();
            Spinner spinner = (Spinner) parent;

        }
        else if(spinnerCheck.getId() == R.id.spinner2)
        {
            String label = parent.getItemAtPosition(position).toString();

            nameHour = label;

            // Showing selected spinner item
            //Toast.makeText(parent.getContext(), "You selected : " + label,
            //Toast.LENGTH_SHORT).show();
            Spinner spinner = (Spinner) parent;
        }

    }


    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
    public void clickBtn(){
        //MyDBHandler MYdb = new MyDBHandler(getApplicationContext());


        //idSubject = aid.getString(0);
        btnViewSubjectID.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor aid = db.getSubjectID(nameSubject);
                        StringBuffer buffer = new StringBuffer();
                        Cursor spin2 = db.getScheduleId(nameHour);

                        try {

                            while (aid.moveToNext()) { // puesto a que comienza en -1, debemos de pasarlo al siguiente, y se supone que solo debe tener 1 valor
                                idSubject = aid.getString(0);
                                buffer.append("ID Subject :"+ idSubject+"\n");
                            }
                            while (spin2.moveToNext()) { // puesto a que comienza en -1, debemos de pasarlo al siguiente, y se supone que solo debe tener 1 valor
                                idHour = spin2.getString(0);
                                buffer.append("ID Hour :"+ idHour+"\n");
                            }
                        }catch(Exception e){
                            e.getCause();
                            Toast.makeText(AddGroupActivity.this," Error! ",Toast.LENGTH_SHORT).show();

                        }
                        //VALIDATE
                        if(!validate()){
                            Toast.makeText(AddGroupActivity.this," Error! ",Toast.LENGTH_SHORT).show();
                        }else{
                            try {
                                saveNewGroup();
                            }catch(Exception e){
                                e.getCause();
                                Toast.makeText(AddGroupActivity.this," Error al insertar: "+e.getCause(),Toast.LENGTH_SHORT).show();
                            }
                        }

                        // Show all data
                        //showMessage("Data",buffer.toString());

                    }
                });
    }

    public void showMessage(String title,String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public boolean validate(){
        boolean valid = true;
        fit =editFit.getText().toString().trim();

        // Toast.makeText(AddGroupActivity.this,"Fit: "+fit,Toast.LENGTH_SHORT).show();

        if (fit.isEmpty() )
        {
            editFit.setError("Porfavor llena el Cupo");
            valid = false;
        }

        return valid;
    }
    public void saveNewGroup(){
        /*Toast.makeText(AddGroupActivity.this," idSubject: "+idSubject,Toast.LENGTH_SHORT).show();
        Toast.makeText(AddGroupActivity.this," idHour: "+idHour,Toast.LENGTH_SHORT).show();
        Toast.makeText(AddGroupActivity.this," fit: "+fit,Toast.LENGTH_SHORT).show();
*/
        boolean isInserted = db.saveNewGroup(idSubject,idHour,fit);
        if(isInserted == true)
            Toast.makeText(AddGroupActivity.this,"Informacion Ingresada",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(AddGroupActivity.this,"Informacion no Ingresada",Toast.LENGTH_SHORT).show();
        editFit.setText("");


    }

}
