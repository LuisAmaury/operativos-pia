package com.example.luisamaury.operativos_pia;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class InscriptionFragment extends Fragment {
    View viewer;
    Button btnAddStudent;
    Button btnModifyStudent, btnViewStudent, btnDeleteStudent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        viewer = inflater.inflate(R.layout.fragment_student, container, false);
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
