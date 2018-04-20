package com.example.luisamaury.operativos_pia;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;
import android.database.Cursor;

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
        final SharedPreferences appData = getApplicationContext().getSharedPreferences("appData", MODE_PRIVATE);
        final SharedPreferences.Editor editor = appData.edit();

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String user = userEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                Cursor cursor = localDatabase.loginCheck(user, password);
                if(cursor.getCount() == 1){
                    String c = "";
                    cursor.moveToFirst();
                    editor.putString("username", cursor.getString(cursor.getColumnIndex("username")));
                    editor.putString("isAdmin", cursor.getString(cursor.getColumnIndex("isAdmin")));
                    editor.putString("idUsuario", cursor.getString(cursor.getColumnIndex("idUsuario")));

                    editor.commit();
                    cursor.close();
                    startActivity(new Intent(MainActivity.this,MENU.class));
                }
            }
        });

    }
}
