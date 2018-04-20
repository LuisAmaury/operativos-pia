package com.example.luisamaury.operativos_pia;


import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class MainActivity extends AppCompatActivity {
    final Context context = this;
    private Button btnEntrar;
    private EditText userEditText, passwordEditText;
    MyDBHandler localDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        localDatabase = new MyDBHandler(this);
        btnEntrar = findViewById(R.id.main_btnEntrar);
        userEditText = findViewById(R.id.main_user);
        passwordEditText = findViewById(R.id.main_password);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String user = userEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if(localDatabase.loginCheck(user, password)){
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            context);

                    // set title
                    alertDialogBuilder.setTitle("Your Title");

                    // set dialog message
                    alertDialogBuilder
                            .setMessage(user + "" + password)
                            .setCancelable(false)
                            .setNegativeButton("No",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // if this button is clicked, just close
                                    // the dialog box and do nothing
                                    dialog.cancel();
                                }
                            });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();
                 }
            }
        });

    }
}
