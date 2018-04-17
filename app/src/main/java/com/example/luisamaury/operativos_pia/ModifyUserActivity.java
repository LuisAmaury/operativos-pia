package com.example.luisamaury.operativos_pia;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ModifyUserActivity extends AppCompatActivity {


    Spinner spinner, spinner2;
    String idUser, idPassword, idSubject, nameHour, idHour,fit,  idGroup;
    Button btnModifyUser;
    EditText editPass, editIdUser;
    View viewer;
    Integer namePosition;

    MyDBHandler db = new MyDBHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editPass = (EditText) findViewById(R.id.editText_PassWord);
        editIdUser = (EditText)findViewById(R.id.editText_GroupID);
        btnModifyUser = (Button)findViewById(R.id.btnModifyUser);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if(bundle.getString("some") != null){
                Toast.makeText(getApplicationContext(),
                        "data: "+ bundle.getString("some"),
                        Toast.LENGTH_SHORT).show();
            }
        }
        clickBtn();
    }

    public void clickBtn(){
        //MyDBHandler MYdb = new MyDBHandler(getApplicationContext());


        //idSubject = aid.getString(0);
        btnModifyUser.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        //VALIDATE
                        if(!validate()){
                            Toast.makeText(ModifyUserActivity.this," Error! ",Toast.LENGTH_SHORT).show();
                        }else{
                            modifyUser();
                        }

                        // Show all data
                        //showMessage("Data",buffer.toString());

                    }
                });
    }

    public boolean validate(){
        boolean valid = true;
        idUser =editIdUser.getText().toString().trim();
        idPassword =editPass.getText().toString().trim();
        // Toast.makeText(ModifyGroupActivity.this,"Fit: "+fit,Toast.LENGTH_SHORT).show();

        if (idUser.isEmpty() )
        {
            editIdUser.setError("Porfavor llena el ID del Usuario");
            valid = false;
        }
        if (idPassword.isEmpty() )
        {
            editPass.setError("Porfavor llena la Contraseña");
            valid = false;
        }

        return valid;
    }

    public void modifyUser(){
        //Toast.makeText(AddGroupActivity.this," WORK WORK! ",Toast.LENGTH_SHORT).show();

        boolean isInserted = db.modifyUser(idUser,idPassword);
        if(isInserted == true)
            Toast.makeText(ModifyUserActivity.this,"Data Modificada",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(ModifyUserActivity.this,"Data no Modificada",Toast.LENGTH_SHORT).show();
        editPass.setText("");
        editIdUser.setText("");

    }

}
