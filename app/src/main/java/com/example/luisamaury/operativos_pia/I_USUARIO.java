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


public class I_USUARIO extends Fragment {
    View viewer;
    Button btnAddUser;
    Button btnModifyUser, btnViewUsers, btnDeleteUser;

    public I_USUARIO() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static I_USUARIO newInstance(String param1, String param2) {
        I_USUARIO fragment = new I_USUARIO();
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

        viewer = inflater.inflate(R.layout.fragment_i__usuario, container, false);
        btnAddUser = (Button) viewer.findViewById(R.id.btnAddUser);
        btnModifyUser = (Button) viewer.findViewById(R.id.btnModifyUser);
        btnViewUsers = (Button) viewer.findViewById(R.id.btnViewUsers);
        btnDeleteUser = (Button) viewer.findViewById(R.id.btnDeleteUser);
        openAddUser();
        openModifyUser();
        openViewUsers();
        openDeleteUser();
        return viewer;
    }

    public void openAddUser(){
        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), AddUserActivity.class);
                in.putExtra("Some","Some Data");
                startActivity(in);

            }
        });
    }
    public void openModifyUser(){
        btnModifyUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), ModifyUserActivity.class);
                in.putExtra("Some","Some Data");
                startActivity(in);

            }
        });
    }
    public void openViewUsers(){
        btnViewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), ViewUsersActivity.class);
                in.putExtra("Some","Some Data");
                startActivity(in);

            }
        });
    }
    public void openDeleteUser(){
        btnDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), DeleteUserActivity.class);
                in.putExtra("Some","Some Data");
                startActivity(in);

            }
        });
    }

}
