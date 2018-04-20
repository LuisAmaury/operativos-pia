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


public class I_HORARIO extends Fragment {
    View viewer;
    Button btnAddSchedule;
    Button btnModifySchedule, btnViewSchedule, btnDeleteSchedule;


    public I_HORARIO() {
        // Required empty public constructor
    }


    public static I_HORARIO newInstance(String param1, String param2) {
        I_HORARIO fragment = new I_HORARIO();
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
        viewer = inflater.inflate(R.layout.fragment_i__horario, container, false);
        btnAddSchedule = (Button) viewer.findViewById(R.id.btnAddSchedule);
        btnModifySchedule = (Button) viewer.findViewById(R.id.btnModifySchedule);
        btnViewSchedule = (Button) viewer.findViewById(R.id.btnViewSchedule);
        btnDeleteSchedule = (Button) viewer.findViewById(R.id.btnDeleteSchedule);
        openAddSchedule();
        openModifySchedule();
        openViewSchedule();
        openDeleteSchedule();
        return viewer;
    }

    public void openAddSchedule(){
        btnAddSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), AddScheduleActivity.class);
                in.putExtra("Some","Some Data");
                startActivity(in);

            }
        });
    }
    public void openModifySchedule(){
        btnModifySchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), ModifyScheduleActivity.class);
                in.putExtra("Some","Some Data");
                startActivity(in);

            }
        });
    }
    public void openViewSchedule(){
        btnViewSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), ViewScheduleActivity.class);
                in.putExtra("Some","Some Data");
                startActivity(in);

            }
        });
    }
    public void openDeleteSchedule(){
        btnDeleteSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), DeleteScheduleActivity.class);
                in.putExtra("Some","Some Data");
                startActivity(in);

            }
        });
    }
}
