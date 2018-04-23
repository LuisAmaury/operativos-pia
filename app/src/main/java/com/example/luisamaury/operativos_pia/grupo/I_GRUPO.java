package com.example.luisamaury.operativos_pia.grupo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

import com.example.luisamaury.operativos_pia.MyDBHandler;
import com.example.luisamaury.operativos_pia.R;


public class I_GRUPO extends Fragment {
    View viewer;
    Button btnOpen;
    Button btnModifyGroup, btnViewGroups, btnDelete;
    Button btnVerAlumno;
    MyDBHandler myDb;                           // Base de datos
    EditText idHorario, editDias,editHoraInicio, editHoraFin;// campos de texto
    Button btnAddData, btnviewAll, btnviewUpdate;                       // Botones

    public I_GRUPO() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static I_GRUPO newInstance(String param1, String param2) {
        I_GRUPO fragment = new I_GRUPO();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        viewer = inflater.inflate(R.layout.fragment_group, container, false);
        btnOpen = (Button) viewer.findViewById(R.id.btnOpen);
        btnModifyGroup = (Button) viewer.findViewById(R.id.btnModifyGroup);
        btnViewGroups = (Button) viewer.findViewById(R.id.btnViewGroups);
        btnDelete = (Button) viewer.findViewById(R.id.btnDeleteGroup);
        btnVerAlumno = (Button) viewer.findViewById(R.id.btnVerAlumnosGrupo);
      
        SharedPreferences appData = getActivity().getApplicationContext().getSharedPreferences("appData", Context.MODE_PRIVATE);
        if(appData.getString("isAdmin", "").equals("false")){
            viewer.findViewById(R.id.btnOpen).setVisibility(View.GONE);
            viewer.findViewById(R.id.btnModifyGroup).setVisibility(View.GONE);
            viewer.findViewById(R.id.btnDeleteGroup).setVisibility(View.GONE);
        }

        Open();
        openModifyGroup();
        openViewGroups();
        openDeleteGroup();
        openVerAlumnosPorGrupo();
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

    public void openVerAlumnosPorGrupo(){
        btnVerAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), VerAlumnosPorGrupo.class);
                in.putExtra("Some","Some Data");
                startActivity(in);

            }
        });
    }

}
