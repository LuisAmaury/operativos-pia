package com.example.luisamaury.operativos_pia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper {
    //information of database
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SO.db";

    public static final String alumno_TABLE_NAME = "Alumno";
    public static final String alumno_col_1 = "idAlumno";
    public static final String alumno_col_2 = "nombre";
    public static final String alumno_col_3 = "telefono";
    public static final String alumno_col_4 = "idUsuario";

    public static final String inscripcionAlumno_TABLE_NAME = "InscripcionAlumno";
    public static final String inscripcionAlumno_col_1 = "idInscripcionAlumno";
    public static final String inscripcionAlumno_col_2 = "idAlumno";
    public static final String inscripcionAlumno_col_3 = "idGrupo";
    public static final String inscripcionAlumno_col_4 = "calificacion";

    public static final String materia_TABLE_NAME = "Materia";
    public static final String materia_col_1 = "idMateria";
    public static final String materia_col_2 = "nombre";
    public static final String materia_col_3 = "requisito";

    public static final String grupo_TABLE_NAME = "grupo";
    public static final String grupo_col_1 = "idGrupo";
    public static final String grupo_col_2 = "idMateria";
    public static final String grupo_col_3 = "idHorario";
    public static final String grupo_col_4 = "cupo";

    public static final String horario_TABLE_NAME = "horario";
    public static final String horario_col_1 = "idHorario";
    public static final String horario_col_2 = "dias";
    public static final String horario_col_3 = "horaInicio";
    public static final String horario_col_4 = "horaFin";

    public static final String usuario_TABLE_NAME = "usuarios";
    public static final String usuario_col_1 = "idUsuario";
    public static final String usuario_col_2 = "contrasena";

    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + usuario_TABLE_NAME +" (idUsuario INTEGER PRIMARY KEY AUTOINCREMENT,contrasena TEXT)");
        db.execSQL("create table " + alumno_TABLE_NAME +" (idAlumno INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT, telefono TEXT, idUsuario INTEGER, FOREIGN KEY(idUsuario) REFERENCES " + usuario_TABLE_NAME + "(idUsuario))");
        db.execSQL("create table " + horario_TABLE_NAME +" (idHorario INTEGER PRIMARY KEY AUTOINCREMENT,dias TEXT, horaInicio TEXT, horaFin TEXT)");
        db.execSQL("create table " + materia_TABLE_NAME +" (idMateria INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT, requisito INTEGER)");
        db.execSQL("create table " + grupo_TABLE_NAME +" (idGrupo INTEGER PRIMARY KEY AUTOINCREMENT, idMateria INTEGER, idHorario INTEGER, cupo INTEGER, FOREIGN KEY(idMateria) REFERENCES " + materia_TABLE_NAME + "(idMateria), FOREIGN KEY(idHorario) REFERENCES " + horario_TABLE_NAME + "(idHorario))");
        db.execSQL("create table " + inscripcionAlumno_TABLE_NAME +" (idInscripcionAlumno INTEGER PRIMARY KEY AUTOINCREMENT,idAlumno INTEGER, idGrupo INTEGER, calificacion INTEGER, FOREIGN KEY(idAlumno) REFERENCES " + alumno_TABLE_NAME + "(idAlumno), FOREIGN KEY(idGrupo) REFERENCES " + grupo_TABLE_NAME + "(idGrupo))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+usuario_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+alumno_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+horario_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+materia_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+grupo_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+inscripcionAlumno_TABLE_NAME);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    // ALUMNO
    public boolean insertDataAlumno(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(alumno_col_2,name);
        long result = db.insert(alumno_TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllDataAlumno() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+alumno_TABLE_NAME,null);
        return res;
    }

    public boolean updateDataAlumno(String id,String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(alumno_col_1,id);
        contentValues.put(alumno_col_2,name);
        db.update(alumno_TABLE_NAME, contentValues, "idAlumno = ?",new String[] { id });
        return true;
    }

    public Integer deleteDataAlumno(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(alumno_TABLE_NAME, "idAlumno = ?",new String[] {id});
    }
}
