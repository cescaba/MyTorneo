package com.example.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.util.BaseDeDatos;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static android.provider.BaseColumns._ID;

import com.example.bean.*;

public class TorneoDAO{
	
	private SQLiteDatabase database;
	private BaseDeDatos dbHelper;
	

	private String[] colum_torneo = new String[]{BaseDeDatos.C_COLUMNA_ID, BaseDeDatos.C_COLUMNA_NOMBRE};
	
	public TorneoDAO(Context ctx) {
		dbHelper = new BaseDeDatos(ctx);
		// TODO Auto-generated constructor stub
	}

	public void abrir() {
		// TODO Auto-generated method stub
		database = dbHelper.getWritableDatabase();
	}

	public void cerrar() {
		// TODO Auto-generated method stub
		dbHelper.close();
	}
	
	public List<Torneo> leerTorneos(){
		List<Torneo> torneos = new ArrayList<Torneo>();
		Cursor c = database.query(BaseDeDatos.C_TABLA_TORNEO, colum_torneo,null,null,null,null,null);
		System.out.println("realizo la query");
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			Torneo torneo = cursorToTorneo(c);
			System.out.println("torneo: "+ c.getInt(0) +" "+torneo.getName());
			torneos.add(torneo);
		}
		c.close();
		return torneos;
	}
	 private Torneo cursorToTorneo(Cursor cursor) {
		    Torneo torneo = new Torneo();
		    torneo.setId(cursor.getInt(0));
		    torneo.setName(cursor.getString(1));
		    //torneo.setNum_juga(cursor.getInt(2));
		    return torneo;
	}
	//public void insertarTorneo(String nombre){ 'MAH
	 public Torneo insertarTorneo(Torneo torneo){
	 ContentValues valores = new ContentValues();
		valores.put(BaseDeDatos.C_COLUMNA_NOMBRE, torneo.getName());
		valores.put(BaseDeDatos.C_COLUMNA_NUM_JUGA, torneo.getNum_juga());
		torneo.setId(database.insert(BaseDeDatos.C_TABLA_TORNEO, null, valores));
		return torneo;
		//valores.put("nom_torneo", nombre);
		//database.insert("torneo",null, valores);
		//System.out.println("insertarTorneo("+nombre+")");
		//database.execSQL("INSERT INTO torneo(nom_torneo) values ('"+nombre+"')");
	}
	public void deleteTorneo(Torneo torneo){
		long id = torneo.getId();
		database.delete(BaseDeDatos.C_TABLA_TORNEO, BaseDeDatos.C_COLUMNA_ID + " = "+ id, null);
	}
	
	public Torneo obtenerxid(long id){
		Torneo torneo = null;
		Cursor c = database.rawQuery("SELECT * FROM torneo where _ID='"+id+"'", new String [] {});
		if(c.getCount()>0){
			System.out.println("encontre");
			c.moveToFirst();
			torneo = cursorToTorneo(c);
			c.close();
		}	
		return torneo;
	}
}
