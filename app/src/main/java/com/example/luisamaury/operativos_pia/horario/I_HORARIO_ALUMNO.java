package com.example.luisamaury.operativos_pia.horario;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.luisamaury.operativos_pia.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link I_HORARIO_ALUMNO.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link I_HORARIO_ALUMNO#newInstance} factory method to
 * create an instance of this fragment.
 */
public class I_HORARIO_ALUMNO extends Fragment {

    View viewer;
    Button btnAddSchedule;
    Button btnModifySchedule, btnViewSchedule, btnDeleteSchedule;

    public I_HORARIO_ALUMNO() {
        // Required empty public constructor
    }


    public static I_HORARIO_ALUMNO newInstance(String param1, String param2) {
        I_HORARIO_ALUMNO fragment = new I_HORARIO_ALUMNO();
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
        Activity activity = getActivity();
        viewer = inflater.inflate(R.layout.fragment_i__horario__alumno, container, false);
        btnAddSchedule = (Button) viewer.findViewById(R.id.btnAddSchedule);
        btnModifySchedule = (Button) viewer.findViewById(R.id.btnModifySchedule);
        btnViewSchedule = (Button) viewer.findViewById(R.id.btnViewSchedule);
        btnDeleteSchedule = (Button) viewer.findViewById(R.id.btnDeleteSchedule);
        NavigationView nvDrawer = (NavigationView) viewer.findViewById(R.id.nv);
        SharedPreferences appData = getActivity().getApplicationContext().getSharedPreferences("appData", Context.MODE_PRIVATE);
        if(appData.getString("isAdmin", "").equals("false")){
            //Toast.makeText(activity,"NO ADMIN ",Toast.LENGTH_LONG).show();

        }

        openViewSchedule();
        return viewer;
    }
    public void openViewSchedule(){
        btnViewSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), ViewScheduleStudentActivity.class);
                in.putExtra("Some","Some Data");
                startActivity(in);

            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event

}
