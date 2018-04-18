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



public class I_ALUMNO extends Fragment {
    MyDBHandler myDb;                           // Base de datos
    EditText editName,editTextId, editPhone, editIdUsuario;            // campos de texto
    Button btnAddData, btnviewAll, btnDelete, btnviewUpdate;// Botones

    public I_ALUMNO() {
        // Required empty public constructor
    }

    public static I_ALUMNO newInstance(String param1, String param2) {
        I_ALUMNO fragment = new I_ALUMNO();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_i__alumno, container, false);
        myDb = new MyDBHandler(getContext());
        editName =  view.findViewById(R.id.editText_nameAlumno);
        editTextId = view.findViewById(R.id.editText_idAlumno);
        editPhone = view.findViewById(R.id.editText_telefonoAlumno);
        editIdUsuario = view.findViewById(R.id.editText_idUsuarioAlumno);

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
                        Integer deletedRows = myDb.deleteDataAlumno(editTextId.getText().toString());
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
                        boolean isUpdate = myDb.updateDataAlumno(editTextId.getText().toString(),
                                editName.getText().toString(), editPhone.getText().toString(), Integer.parseInt(editIdUsuario.getText().toString()));
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
                        boolean isInserted = myDb.insertDataAlumno(editName.getText().toString(), editPhone.getText().toString(), Integer.parseInt(editIdUsuario.getText().toString()));
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
                        Cursor res = myDb.getAllDataAlumno();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Name :"+ res.getString(1)+"\n");
                            buffer.append("Phone :"+ res.getString(2)+"\n");
                            buffer.append("IdUsuario :"+ res.getString(3)+"\n");
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
