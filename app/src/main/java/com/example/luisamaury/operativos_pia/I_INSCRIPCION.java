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


public class I_INSCRIPCION extends Fragment {

    MyDBHandler myDb;                           // Base de datos
    EditText id, idAlumno, idGrupo, calificacion;// campos de texto
    Button btnAddData, btnviewAll, btnDelete, btnviewUpdate;                        // Botones


    public I_INSCRIPCION() {
        // Required empty public constructor
    }


    public static I_INSCRIPCION newInstance(String param1, String param2) {
        I_INSCRIPCION fragment = new I_INSCRIPCION();
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
        View view = inflater.inflate(R.layout.fragment_i__inscripcion, container, false);
        myDb = new MyDBHandler(getContext());

        id = view.findViewById(R.id.editText_id);
        idAlumno = view.findViewById(R.id.editText_idAlumno);
        idGrupo = view.findViewById(R.id.editText_idGrupo);
        calificacion = view.findViewById(R.id.editText_calificacion);

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
                        Integer deletedRows = myDb.deleteDataInscripcion(id.getText().toString());
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
                        boolean isUpdate = myDb.updateDataInscripcion(id.getText().toString(),
                                idAlumno.getText().toString(), idGrupo.getText().toString(), calificacion.getText().toString());
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
                        boolean isInserted = myDb.insertInscripcion(idAlumno.getText().toString(), idGrupo.getText().toString(), calificacion.getText().toString());
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
                        Cursor res = myDb.getAllDataInscripcion();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("idAlumno :"+ res.getString(1)+"\n");
                            buffer.append("idGrupo :"+ res.getString(2)+"\n");
                            buffer.append("Calificacion :"+ res.getString(3)+"\n");
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