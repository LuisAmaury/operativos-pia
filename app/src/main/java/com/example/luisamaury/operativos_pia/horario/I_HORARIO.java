package com.example.luisamaury.operativos_pia.horario;

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

        SharedPreferences appData = this.getActivity().getSharedPreferences("appData", Context.MODE_PRIVATE);
        if(appData.getString("isAdmin", "").equals("false")){
            viewer.findViewById(R.id.btnAddSchedule).setVisibility(View.GONE);
            viewer.findViewById(R.id.btnModifySchedule).setVisibility(View.GONE);
            viewer.findViewById(R.id.btnViewSchedule).setVisibility(View.GONE);
            viewer.findViewById(R.id.btnDeleteSchedule).setVisibility(View.GONE);
        } else {
            viewer.findViewById(R.id.horarioTable).setVisibility(View.GONE);
        }

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
