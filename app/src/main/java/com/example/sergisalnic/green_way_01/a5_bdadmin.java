package com.example.sergisalnic.green_way_01;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class a5_bdadmin extends SQLiteOpenHelper {
    String tablename="Usuarios2";
    String SQLiteCreate = "CREATE TABLE "+tablename+" (Token PRIMARY KEY)";
    public a5_bdadmin(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLiteCreate);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public int insert(String token){
        SQLiteDatabase _descp_ins;
        _descp_ins = this.getWritableDatabase();
        String _cad2 = "INSERT INTO "+tablename+" VALUES ('" +token + "')";
        boolean _unico = true;
        try{
            _descp_ins.execSQL(_cad2);
        }catch(Exception _e){
            _e.getStackTrace();
            _unico = false;
        }
        if(_unico){
            return 1;
        }else{
            return 0;
        }
    }
    public String buscar(){
        SQLiteDatabase _descp_busc;
        _descp_busc = this.getReadableDatabase();
        String[] campos = new String[]{"Token"};
        Cursor _res = _descp_busc.query(tablename, campos,"", null, null, null, null);
        if(_res.getCount()>=1){
            _res.moveToNext();
            String _sol = _res.getString(0);
            return _sol;
        }else{
            return "no encontrado";
        }
    }
}
