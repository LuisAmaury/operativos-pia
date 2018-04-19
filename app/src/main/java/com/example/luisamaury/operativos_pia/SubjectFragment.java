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

public class SubjectFragment extends Fragment {
    View viewer;
    Button btnAddSubject;
    Button btnModifySubject, btnViewSubject, btnDeleteSubject;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        viewer = inflater.inflate(R.layout.fragment_subject, container, false);
        btnAddSubject = (Button) viewer.findViewById(R.id.btnAddSubject);
        btnModifySubject = (Button) viewer.findViewById(R.id.btnModifySubject);
        btnViewSubject = (Button) viewer.findViewById(R.id.btnViewSubject);
        btnDeleteSubject = (Button) viewer.findViewById(R.id.btnDeleteSubject);
        openAddSubject();
        openModifySubject();
        openViewSubject();
        openDeleteSubject();
        return viewer;
    }

    public void openAddSubject(){
        btnAddSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), AddSubjectActivity.class);
                in.putExtra("Some","Some Data");
                startActivity(in);

            }
        });
    }
    public void openModifySubject(){
        btnModifySubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), ModifySubjectActivity.class);
                in.putExtra("Some","Some Data");
                startActivity(in);

            }
        });
    }
    public void openViewSubject(){
        btnViewSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), ViewSubjectActivity.class);
                in.putExtra("Some","Some Data");
                startActivity(in);

            }
        });
    }
    public void openDeleteSubject(){
        btnDeleteSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), DeleteSubjectActivity.class);
                in.putExtra("Some","Some Data");
                startActivity(in);

            }
        });
    }
}
