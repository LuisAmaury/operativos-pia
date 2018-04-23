package com.example.luisamaury.operativos_pia.inscripcion.inscripcion_materia;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luisamaury.operativos_pia.MyDBHandler;
import com.example.luisamaury.operativos_pia.R;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;

public class Materia extends Fragment {
    MyDBHandler myDb;
    ArrayList<String> theList = new ArrayList <>();
    String _idAlumno;
    String [] parts;
    String _idGrupo;
    String req;
    String materia;
    String calif = "0";
    public Materia() {
        // Required empty public constructor
    }

    public static Materia newInstance(String param1, String param2) {
        Materia fragment = new Materia();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String unity ;
        myDb = new MyDBHandler(getContext());
        SharedPreferences appData = this.getActivity().getSharedPreferences("appData", Context.MODE_PRIVATE);
        _idAlumno = appData.getString("idAlumno", "");

        Cursor data = myDb.viewGrupos();

        if(data.getCount() == 0){
            Toast.makeText(getContext(),"No hay grupos disponibles ",Toast.LENGTH_SHORT).show();
        }else{
            while(data.moveToNext()){
                unity = "";
                unity = unity +"Grupo: "+ data.getString(0)+"\nMateria: " + data.getString(1)+"\nHorario: "
                        +data.getString(2)+" "+data.getString(3)+" - "+data.getString(4);
                theList.add(unity);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_materia, container, false);
        final ListView listView = view.findViewById(R.id.listGrupos);

        final ListAdapter listAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1, theList);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String aux = theList.get(position);
                parts = aux.split("\\s+");
                _idGrupo = parts[1];

                if(ValidaMateriaRequisito()){

                    if(ValidaCalificacion()){
                        AgregaMateria();
                    }else {
                        Toast.makeText(getActivity(), "No puede llevar la materia", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    AgregaMateria();
                }
            }
        });
        return view;
    }

   private void AgregaMateria() {
        if (ValidarHorarios()){
            Toast.makeText(getActivity(), "Horarios iguales", Toast.LENGTH_SHORT).show();
        }else {
            String calf = "0";
            boolean isInserted = myDb.insertInscripcion(_idAlumno,String.valueOf(_idGrupo), calf);
            if(isInserted == true)
                Toast.makeText(getActivity(),"Data Inserted",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getActivity(),"Data not Inserted",Toast.LENGTH_LONG).show();
        }

    }

    private boolean ValidarHorarios() {
        return false;
    }

    private boolean ValidaCalificacion() {
        Cursor calificacion = myDb.obtenerCalificacion(_idGrupo,_idAlumno, req);
        //int count = calificacion.getCount();
        if (calificacion.getCount() == 1){
            calificacion.moveToFirst();
            calif = calificacion.getString(calificacion.getColumnIndex("calificacion"));
        }
        int c = Integer.valueOf(calif);
        if(c >= 70){
            return true;
        }else{
            return false;
        }
    }

    private boolean ValidaMateriaRequisito(){

        Cursor requisito = myDb.obtenerRequisito(_idGrupo);
        if (requisito.getCount()==1){
            requisito.moveToFirst();
            materia = requisito.getString(requisito.getColumnIndex("idMateria"));
            req = requisito.getString(requisito.getColumnIndex("requisito"));
        }
        if(req.equals("0")){
            return false;
        }else{
            return true;
        }
    }


}
