package com.example.luisamaury.operativos_pia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper {
    //information of database
    public static final int DATABASE_VERSION = 3;
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
    public static final String materia_col_4 = "semestre";

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
    public static final String usuario_col_3 = "username";
    public static final String usuario_col_4 = "isAdmin";

    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + usuario_TABLE_NAME +" (idUsuario INTEGER PRIMARY KEY AUTOINCREMENT,contrasena TEXT, username TEXT, isAdmin TEXT)");
        db.execSQL("create table " + alumno_TABLE_NAME +" (idAlumno INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT, telefono TEXT, idUsuario INTEGER, FOREIGN KEY(idUsuario) REFERENCES " + usuario_TABLE_NAME + "(idUsuario))");
        db.execSQL("create table " + horario_TABLE_NAME +" (idHorario INTEGER PRIMARY KEY AUTOINCREMENT,dias TEXT, horaInicio TEXT, horaFin TEXT)");
        db.execSQL("create table " + materia_TABLE_NAME +" (idMateria INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT, requisito INTEGER, semestre INTEGER)");
        db.execSQL("create table " + grupo_TABLE_NAME +" (idGrupo INTEGER PRIMARY KEY AUTOINCREMENT, idMateria INTEGER, idHorario INTEGER, cupo INTEGER, FOREIGN KEY(idMateria) REFERENCES " + materia_TABLE_NAME + "(idMateria), FOREIGN KEY(idHorario) REFERENCES " + horario_TABLE_NAME + "(idHorario))");
        db.execSQL("create table " + inscripcionAlumno_TABLE_NAME +" (idInscripcionAlumno INTEGER PRIMARY KEY AUTOINCREMENT,idAlumno INTEGER, idGrupo INTEGER, calificacion INTEGER, FOREIGN KEY(idAlumno) REFERENCES " + alumno_TABLE_NAME + "(idAlumno), FOREIGN KEY(idGrupo) REFERENCES " + grupo_TABLE_NAME + "(idGrupo))");
        prechargingData(db);
    }

    public void prechargingData(SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(usuario_col_3, "root");
        contentValues.put(usuario_col_2, "root");
        contentValues.put(usuario_col_4, "true");
        db.insert(usuario_TABLE_NAME, null, contentValues);
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
    public long[] insertDataAlumno(String name, String telefono, String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        long[] sessionIds = new long[2];

        contentValues.put(usuario_col_3, username);
        contentValues.put(usuario_col_2, "12");
        contentValues.put(usuario_col_4, "false");
        sessionIds[0] = db.insert(usuario_TABLE_NAME, null, contentValues);

        contentValues.clear();

        contentValues.put(alumno_col_2, name);
        contentValues.put(alumno_col_3, telefono);
        contentValues.put(alumno_col_4, sessionIds[0]);
        sessionIds[1] = db.insert(alumno_TABLE_NAME,null ,contentValues);

        db.close();

        return sessionIds;
    }

    public Cursor getAllDataAlumno() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+alumno_TABLE_NAME,null);
        return res;
    }

    public boolean updateDataAlumno(String id,String name, String telefono, int idUsuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(alumno_col_1,id);
        contentValues.put(alumno_col_2,name);
        contentValues.put(alumno_col_3, telefono);
        contentValues.put(alumno_col_4, idUsuario);
        db.update(alumno_TABLE_NAME, contentValues, "idAlumno = ?",new String[] { id });
        return true;
    }



    public Integer deleteDataAlumno(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(alumno_TABLE_NAME, "idAlumno = ?",new String[] {id});
    }
    public Cursor getStudentID(String username){
        String id = new String();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT idAlumno FROM "+alumno_TABLE_NAME+" WHERE nombre = ?; ", new String[] {username});

        return res;
    }

    // MATERIA

    public boolean insertDataMateria(String name, int requisito, int semestre) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(materia_col_2, name);
        contentValues.put(materia_col_3, requisito);
        contentValues.put(materia_col_4, semestre);
        long result = db.insert(materia_TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllDataMateria() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+materia_TABLE_NAME,null);
        return res;
    }

    public boolean updateDataMateria(String id,String name, int requisito, int semestre) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(materia_col_1,id);
        contentValues.put(materia_col_2,name);
        contentValues.put(materia_col_3, requisito);
        contentValues.put(materia_col_4, semestre);
        db.update(materia_TABLE_NAME, contentValues, "idMateria = ?",new String[] { id });
        return true;
    }

    public Integer deleteDataMateria(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(materia_TABLE_NAME, "idMateria = ?",new String[] {id});
    }
    public boolean checkMateriaGrupo(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean valido = true;
        Cursor res = db.rawQuery("SELECT idMateria FROM "+grupo_TABLE_NAME+" WHERE idMateria = ?; ", new String[] {id});
        if(res!=null && res.getCount()>0){
            valido = false;
        }

        return valido;
    }
    //HORARIO
    public boolean insertHorario(String dias, String horaInicio, String horaFin){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(horario_col_2, dias);
        contentValues.put(horario_col_3, horaInicio);
        contentValues.put(horario_col_4, horaFin);
        long result = db.insert(horario_TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllDataHorario(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + horario_TABLE_NAME, null);
        return res;
    }

    public boolean updateDataHorario(String id, String dias, String horaInicio, String horaFin){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(horario_col_2, dias);
        contentValues.put(horario_col_3, horaInicio);
        contentValues.put(horario_col_4, horaFin);
        db.update(horario_TABLE_NAME, contentValues, "idHorario = ?",  new String[] {id});
        return true;
    }

    public Integer deleteDataHorario(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(horario_TABLE_NAME, "idHorario = ?", new String[] {id});
    }
    public boolean checkHorarioGrupo(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean valido = true;
        Cursor res = db.rawQuery("SELECT idHorario FROM "+grupo_TABLE_NAME+" WHERE idHorario = ?; ", new String[] {id});
        if(res!=null && res.getCount()>0){
            valido = false;
        }

        return valido;
    }


    //INSCRIPCION
    public boolean insertInscripcion(String idAlumno, String idGrupo, String calificacion){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(inscripcionAlumno_col_2, idAlumno);
        contentValues.put(inscripcionAlumno_col_3, idGrupo);
        contentValues.put(inscripcionAlumno_col_4, calificacion);
        long result = db.insert(inscripcionAlumno_TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllDataInscripcion(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + inscripcionAlumno_TABLE_NAME, null);
        return res;
    }

    public Cursor getAllDataInscripcionNamed(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT i.idInscripcionAlumno, i.idAlumno, i.idGrupo, i.calificacion, a.nombre, m.nombre FROM InscripcionAlumno i LEFT JOIN Alumno a ON i.idAlumno = a.idAlumno LEFT JOIN grupo g ON i.idGrupo = g.idGrupo LEFT JOIN Materia m ON g.idMateria = m.idMateria", null);
        return cursor;
    }

    public Cursor getStudentDataInscripcion(String idStudent){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("SELECT * FROM " + inscripcionAlumno_TABLE_NAME+" WHERE idAlumno = ?; ", new String[] {idStudent});
        return res;
    }

    public boolean updateDataInscripcion(String idInscripcionAlumno, String idAlumno, String idGrupo, String calificacion){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(inscripcionAlumno_col_2, idAlumno);
        contentValues.put(inscripcionAlumno_col_3, idGrupo);
        contentValues.put(inscripcionAlumno_col_4, calificacion);
        db.update(inscripcionAlumno_TABLE_NAME, contentValues, "idInscripcionAlumno = ?",  new String[] {idInscripcionAlumno});
        return true;
    }

    public Integer deleteDataInscripcion(String idInscripcionAlumno){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(inscripcionAlumno_TABLE_NAME, "idInscripcionAlumno = ?", new String[] {idInscripcionAlumno});
    }
    public boolean checkGrupoInscripcion(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean valido = true;
        Cursor res = db.rawQuery("SELECT idGrupo FROM "+inscripcionAlumno_TABLE_NAME+" WHERE idGrupo = ?; ", new String[] {id});
        if(res!=null && res.getCount()>0){
            valido = false;
        }

        return valido;
    }
    public boolean checkAlumnoInscripcion(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean valido = true;

        Cursor res = db.rawQuery("SELECT idAlumno FROM "+inscripcionAlumno_TABLE_NAME+" WHERE idAlumno = ?; ", new String[] {id});
        if(res!=null && res.getCount()>0){
            valido = false;
        }

        return valido;
    }



    public List<String> getAllSubjects(){
        List<String> labels = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM "+  materia_TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }

    public Cursor getSubjectID(String subject){
        String id = new String();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT idMateria FROM "+materia_TABLE_NAME+" WHERE nombre = ?; ", new String[] {subject});
        //Cursor c = db.rawQuery("SELECT * FROM "+alumno_TABLE_NAME+" WHERE TRIM(nombre) = '"+subject.trim()+"'", null);
        //Cursor res = db.rawQuery("select * from "+alumno_TABLE_NAME,null);


        return res;
    }

    /////////////////////////////
    //Requieres Implementation //
    //////(//////////////////////
    public List<String> getAllSchedules(){
        List<String> hours = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + horario_TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                hours.add(cursor.getString(1));

                //all.add(cursor.getString(0));
                //all.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return hours;
    }
    public Cursor getScheduleId(String hour){
        String id = new String();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT idHorario FROM "+horario_TABLE_NAME+" WHERE dias = ?; ", new String[] {hour});
        //Cursor c = db.rawQuery("SELECT * FROM "+alumno_TABLE_NAME+" WHERE TRIM(nombre) = '"+subject.trim()+"'", null);
        //Cursor res = db.rawQuery("select * from "+alumno_TABLE_NAME,null);


        return res;
    }

    public boolean saveNewGroup(String Subject, String Hour, String Cupo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(grupo_col_2, Subject);
        contentValues.put(grupo_col_3, Hour);
        contentValues.put(grupo_col_4, Cupo);
        long result = db.insert(grupo_TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }




    public boolean modifyGroup (String idGrupo, String Subject, String Hour, String Cupo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(grupo_col_2,Subject);
        contentValues.put(grupo_col_3,Hour);
        contentValues.put(grupo_col_4,Cupo);
        long result =db.update(grupo_TABLE_NAME, contentValues, "idGrupo = ?",new String[] { idGrupo});
        if(result == -1)
            return false;
        else
            return true;

    }
    /* ***************************************************** */
    /* **   AGREGAR A DELETE/VIEW TABLA CORRESPONDIENTE   ** */
    /* ***************************************************** */
    public Cursor viewAllGroups(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+grupo_TABLE_NAME,null);
        return res;
    }
    public Integer deleteGroup(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(grupo_TABLE_NAME, "idGrupo = ?",new String[] {id});
    }

    /////   USERS   //////
    public Cursor viewAllUsers(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+usuario_TABLE_NAME,null);
        return res;
    }

    /* ***************************************************** */
    /* **  AGREGAR A DELETE/MODIFY CHEQUEO DE CONTRASEÑA  ** */
    /* ***************************************************** */
    public Integer deleteUser(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(usuario_TABLE_NAME, "idUsuario = ?",new String[] {id});
    }

    public boolean checkUserStudent(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean valido = true;
        Cursor res = db.rawQuery("SELECT idUsuario FROM "+alumno_TABLE_NAME+" WHERE idUsuario = ?; ", new String[] {id});
        if(res!=null && res.getCount()>0){
            valido = false;
        }

        return valido;
    }
    public boolean modifyUser (String idUsuario, String password, String UserName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(usuario_col_2,password);
        contentValues.put(usuario_col_3,UserName);
        long result =db.update(usuario_TABLE_NAME, contentValues, "idUsuario = ?",new String[] { idUsuario});
        if(result == -1)
            return false;
        else
            return true;

    }
    public boolean addUser(String Password, String UserName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(usuario_col_2, Password);
        contentValues.put(usuario_col_3, UserName);
        long result = db.insert(usuario_TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

///// END USERS /////
    //LOGIN
    public Cursor loginCheck(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        String [] whereArgs = new String[2];
        whereArgs[0] = username;
        whereArgs[1] = password;
        Cursor cursor = db.rawQuery("SELECT u.idUsuario, u.username, u.isAdmin, a.idAlumno FROM usuarios u LEFT JOIN Alumno a ON u.idUsuario = a.idUsuario WHERE username = ? AND contrasena = ?", whereArgs);
        return cursor;
    }



    ////// Ver los alumnos de un grupo //////
    public Cursor alumnosDeUnGrupo(String idGrupo){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT Alumno.idAlumno, Alumno.nombre FROM Alumno INNER JOIN InscripcionAlumno on Alumno.idAlumno = InscripcionAlumno.idAlumno WHERE InscripcionAlumno.calificacion = 0 and idGrupo = " + idGrupo, null);
        return res;
    }

}
