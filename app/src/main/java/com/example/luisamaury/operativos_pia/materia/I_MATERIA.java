package com.example.luisamaury.operativos_pia.materia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.luisamaury.operativos_pia.R;


public class I_MATERIA extends Fragment {
    View viewer;
    Button btnAddSubject;
    Button btnModifySubject, btnViewSubject, btnDeleteSubject;

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
        viewer  = inflater.inflate(R.layout.fragment_i__materi, container, false);
        btnAddSubject = (Button) viewer.findViewById(R.id.btnAddSubject);
        btnModifySubject = (Button) viewer.findViewById(R.id.btnModifySubject);
        btnViewSubject = (Button) viewer.findViewById(R.id.btnViewSubject);
        btnDeleteSubject = (Button) viewer.findViewById(R.id.btnDeleteSubject);

        SharedPreferences appData = getActivity().getApplicationContext().getSharedPreferences("appData", Context.MODE_PRIVATE);
        if(appData.getString("isAdmin", "").equals("false")){
            viewer.findViewById(R.id.btnAddSubject).setVisibility(View.GONE);
            viewer.findViewById(R.id.btnModifySubject).setVisibility(View.GONE);
            viewer.findViewById(R.id.btnDeleteSubject).setVisibility(View.GONE);
        }

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
