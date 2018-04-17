package com.example.luisamaury.operativos_pia;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// Esta es como si fuera su clase, por ejemplo esta en vez de ser MainActivity deberia ser Alumno
public class Materia extends AppCompatActivity {

    MyDBHandler myDb;                           // Base de datos
    EditText editName,editTextId, editRequisito;            // campos de texto
    Button btnAddData;                         // Botones
    Button btnviewAll;
    Button btnDelete;

    Button btnviewUpdate;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.materia);
        myDb = new MyDBHandler(this);

        editName = (EditText)findViewById(R.id.editText_nameMateria);
        editTextId = (EditText)findViewById(R.id.editText_idMateria);
        editRequisito = (EditText) findViewById(R.id.editText_requisitoMateria);

        btnAddData = (Button)findViewById(R.id.button_add);
        btnviewAll = (Button)findViewById(R.id.button_viewAll);
        btnviewUpdate= (Button)findViewById(R.id.button_update);
        btnDelete= (Button)findViewById(R.id.button_delete);
        AddData();
        viewAll();
        UpdateData();
        DeleteData();
    }

    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteDataMateria(editTextId.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(Materia.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Materia.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void UpdateData() {
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateDataMateria(editTextId.getText().toString(),
                                editName.getText().toString(), Integer.parseInt(editRequisito.getText().toString()));
                        if(isUpdate == true)
                            Toast.makeText(Materia.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Materia.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertDataMateria(editName.getText().toString(),Integer.parseInt(editRequisito.getText().toString()));
                        if(isInserted == true)
                            Toast.makeText(Materia.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Materia.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllDataMateria();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Name :"+ res.getString(1)+"\n");
                            buffer.append("Requisito :"+ res.getString(2)+"\n");
                        }

                        // Show all data
                        showMessage("Data",buffer.toString());
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


}



