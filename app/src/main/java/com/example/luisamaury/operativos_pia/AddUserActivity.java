package com.example.luisamaury.operativos_pia;

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

import com.example.luisamaury.operativos_pia.R;

public class AddUserActivity extends AppCompatActivity {
    String nameSubject, subID, idSubject, nameHour, nomUser, fit;
    Button btnAddUser;
    EditText editPass,editUserName;
    View viewer;
    Integer namePosition;
    MyDBHandler db = new MyDBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editUserName = (EditText) findViewById(R.id.editText_UserName);

        editPass = (EditText) findViewById(R.id.editText_PassWord);
        btnAddUser = (Button)findViewById(R.id.btnAddNewUser);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if(bundle.getString("some") != null){
                Toast.makeText(getApplicationContext(),
                        "data: "+ bundle.getString("some"),
                        Toast.LENGTH_SHORT).show();
            }
        }

        addUser();
    }
    public void addUser(){
        //MyDBHandler MYdb = new MyDBHandler(getApplicationContext());


        //idSubject = aid.getString(0);
        btnAddUser.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!validate()) {
                            Toast.makeText(AddUserActivity.this, " Error! ", Toast.LENGTH_SHORT).show();
                        } else {
                            saveNewGroup();
                        }
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

    public boolean validate(){
        boolean valid = true;
        fit =editPass.getText().toString().trim();
        nomUser = editUserName.getText().toString().trim();

        // Toast.makeText(AddGroupActivity.this,"Fit: "+fit,Toast.LENGTH_SHORT).show();

        if (fit.isEmpty() )
        {
            editPass.setError("Porfavor llena la Contraseña");
            valid = false;
        }
        if (nomUser.isEmpty() )
        {
            editUserName.setError("Porfavor llena el Nombre de Usuario");
            valid = false;
        }

        return valid;
    }
    public void saveNewGroup() {
        //Toast.makeText(AddUserActivity.this," userName: "+editUserName.getText().toString(),Toast.LENGTH_SHORT).show();


        boolean isInserted = db.addUser(editPass.getText().toString(),editUserName.getText().toString());
        if (isInserted == true)
            Toast.makeText(AddUserActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(AddUserActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
        editUserName.setText("");
        editPass.setText("");
    }



}
