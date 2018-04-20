package com.example.luisamaury.operativos_pia;

import android.content.Context;
import android.content.Intent;
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
    View viewer;
    Button btnAddStudent;
    Button btnModifyStudent, btnViewStudent, btnDeleteStudent;

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
        viewer = inflater.inflate(R.layout.fragment_i__alumno, container, false);
        btnAddStudent = (Button) viewer.findViewById(R.id.btnAddStudent);
        btnModifyStudent = (Button) viewer.findViewById(R.id.btnModifyStudent);
        btnViewStudent = (Button) viewer.findViewById(R.id.btnViewStudent);
        btnDeleteStudent = (Button) viewer.findViewById(R.id.btnDeleteStudent);
        openAddStudent();
        openModifyStudent();
        openViewStudent();
        openDeleteStudent();
        return viewer;
    }

    public void openAddStudent(){
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), AddStudentActivity.class);
                in.putExtra("Some","Some Data");
                startActivity(in);

            }
        });
    }
    public void openModifyStudent(){
        btnModifyStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), ModifyStudentActivity.class);
                in.putExtra("Some","Some Data");
                startActivity(in);

            }
        });
    }
    public void openViewStudent(){
        btnViewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), ViewStudentActivity.class);
                in.putExtra("Some","Some Data");
                startActivity(in);

            }
        });
    }
    public void openDeleteStudent(){
        btnDeleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), DeleteStudentActivity.class);
                in.putExtra("Some","Some Data");
                startActivity(in);

            }
        });
    }


}
