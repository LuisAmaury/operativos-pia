package com.example.luisamaury.operativos_pia.inscripcion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.luisamaury.operativos_pia.R;


public class I_INSCRIPCION extends Fragment {

    View viewer;
    Button btnAddInscription;
    Button btnModifyInscription, btnViewInscription, btnDeleteInscription;


    public I_INSCRIPCION() {
        // Required empty public constructor
    }


    public static I_INSCRIPCION newInstance(String param1, String param2) {
        I_INSCRIPCION fragment = new I_INSCRIPCION();
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
        viewer = inflater.inflate(R.layout.fragment_i__inscripcion, container, false);
        btnAddInscription = (Button) viewer.findViewById(R.id.btnAddInscription);
        btnModifyInscription = (Button) viewer.findViewById(R.id.btnModifyInscription);
        btnViewInscription = (Button) viewer.findViewById(R.id.btnViewInscription);
        btnDeleteInscription = (Button) viewer.findViewById(R.id.btnDeleteInscription);
        openAddInscription();
        openModifyInscription();
        openViewInscription();
        openDeleteInscription();
        return viewer;
    }

    public void openAddInscription(){
        btnAddInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), AddInscriptionActivity.class);
                in.putExtra("Some","Some Data");
                startActivity(in);

            }
        });
    }
    public void openModifyInscription(){
        btnModifyInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), ModifyInscriptionActivity.class);
                in.putExtra("Some","Some Data");
                startActivity(in);

            }
        });
    }
    public void openViewInscription(){
        btnViewInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), ViewInscriptionActivity.class);
                in.putExtra("Some","Some Data");
                startActivity(in);

            }
        });
    }
    public void openDeleteInscription(){
        btnDeleteInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), DeleteInscriptionActivity.class);
                in.putExtra("Some","Some Data");
                startActivity(in);

            }
        });
    }
}
