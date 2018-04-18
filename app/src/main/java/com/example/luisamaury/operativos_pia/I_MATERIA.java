package com.example.luisamaury.operativos_pia;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class I_MATERIA extends Fragment {
    MyDBHandler myDb;                           // Base de datos
    EditText editName,editTextId, editRequisito;            // campos de texto
    Button btnAddData, btnviewAll, btnDelete, btnviewUpdate;                         // Botones

    public static I_MATERIA newInstance(String param1, String param2) {
        I_MATERIA fragment = new I_MATERIA();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_i__materi, container, false);
        myDb = new MyDBHandler(getContext());

        editName = view.findViewById(R.id.editText_nameMateria);
        editTextId = view.findViewById(R.id.editText_idMateria);
        editRequisito = view.findViewById(R.id.editText_requisitoMateria);

        btnAddData = view.findViewById(R.id.button_add);
        btnviewAll = view.findViewById(R.id.button_viewAll);
        btnviewUpdate= view.findViewById(R.id.button_update);
        btnDelete= view.findViewById(R.id.button_delete);
        AddData();
        viewAll();
        UpdateData();
        DeleteData();
        return view;
    }
    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteDataMateria(editTextId.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(getContext(),"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getContext(),"Data not Deleted",Toast.LENGTH_LONG).show();
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
                            Toast.makeText(getContext(),"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getContext(),"Data not Updated",Toast.LENGTH_LONG).show();
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
                            Toast.makeText(getContext(),"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getContext(),"Data not Inserted",Toast.LENGTH_LONG).show();
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
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}