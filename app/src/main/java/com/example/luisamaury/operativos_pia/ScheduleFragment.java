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


public class ScheduleFragment extends Fragment {
    View viewer;
    Button btnAddSchedule;
    Button btnModifySchedule, btnViewSchedule, btnDeleteSchedule;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        viewer = inflater.inflate(R.layout.fragment_student, container, false);
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
                Intent in = new Intent(getActivity(), AddSubjectActivity.class);
                in.putExtra("Some","Some Data");
                startActivity(in);

            }
        });
    }
    public void openModifySchedule(){
        btnModifySchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), ModifySubjectActivity.class);
                in.putExtra("Some","Some Data");
                startActivity(in);

            }
        });
    }
    public void openViewSchedule(){
        btnViewSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), ViewSubjectActivity.class);
                in.putExtra("Some","Some Data");
                startActivity(in);

            }
        });
    }
    public void openDeleteSchedule(){
        btnDeleteSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), DeleteSubjectActivity.class);
                in.putExtra("Some","Some Data");
                startActivity(in);

            }
        });
    }
}
