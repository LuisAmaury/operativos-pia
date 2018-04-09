package com.example.luisamaury.operativos_pia;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class GroupFragment extends Fragment {

    View viewer;
    Button btnOpen;
    Button btnModifyGroup, btnViewGroups, btnDelete;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Group Fragment");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        viewer = inflater.inflate(R.layout.fragment_group, container, false);
        btnOpen = (Button) viewer.findViewById(R.id.btnOpen);
        btnModifyGroup = (Button) viewer.findViewById(R.id.btnModifyGroup);
        btnViewGroups = (Button) viewer.findViewById(R.id.btnViewGroups);
        btnDelete = (Button) viewer.findViewById(R.id.btnDeleteGroup);
        Open();
        openModifyGroup();
        openViewGroups();
        openDeleteGroup();
        return viewer;
    }

    public void Open(){
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), AddGroupActivity.class);
                in.putExtra("Some","Some Data");
                startActivity(in);

            }
        });
    }
    public void openModifyGroup(){
        btnModifyGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), ModifyGroupActivity.class);
                in.putExtra("Some","Some Data");
                startActivity(in);

            }
        });
    }
    public void openViewGroups(){
        btnViewGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), ViewGroupsActivity.class);
                in.putExtra("Some","Some Data");
                startActivity(in);

            }
        });
    }
    public void openDeleteGroup(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), DeleteGroupActivity.class);
                in.putExtra("Some","Some Data");
                startActivity(in);

            }
        });
    }
}
