package com.example.luisamaury.operativos_pia;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;

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
                    startActivity(new Intent(MainActivity.this,MENU.class));
                 }
            }
        });

    }
}
