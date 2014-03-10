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

public class PartidoDAO {
	private SQLiteDatabase database;
	private BaseDeDatos dbHelper;
	
	public PartidoDAO(Context ctx) {
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
	
	 private Partido cursorToPartido(Cursor cursor) {
		    Partido partido = new Partido();
		 	partido.setId(cursor.getLong(0));
		 	partido.setId_torneo(cursor.getLong(1));
		 	partido.setId_juga1(cursor.getLong(2));
		 	partido.setId_juga2(cursor.getLong(3));
		 	partido.setGol_juga1(cursor.getInt(4));
		 	partido.setGol_juga2(cursor.getInt(5));
		 	partido.setId_juga_gan(cursor.getInt(6));
		 	partido.setNum_fecha(cursor.getInt(7));
		    return partido;
	}

	public List<Partido> leerPartidos(long id_torneo){
		List<Partido> partidos = new ArrayList<Partido>();
		//Cursor c = database.query(BaseDeDatos.C_TABLA_JUGADOR, colum_jugador,null,null,null,null,null);
		Cursor c = database.rawQuery("SELECT * FROM partido where id_torneo='"+id_torneo+"' order by id asc", new String [] {});
		
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			Partido partido = cursorToPartido(c);
			partidos.add(partido);
		}
		c.close();
		return partidos;
	}
	public long insertarPartido(Partido partido){
		ContentValues valores = new ContentValues();
		System.out.println("Entro al insertarPartido");
		//valores.put(BaseDeDatos.C_COLUMNA_ID_PAR, partido.getId());
		valores.put(BaseDeDatos.C_COLUMNA_ID_TOR_PAR,partido.getId_torneo());
		valores.put(BaseDeDatos.C_COLUMNA_ID_JUG1, partido.getId_juga1());
		valores.put(BaseDeDatos.C_COLUMNA_ID_JUG2, partido.getId_juga2());
		valores.put(BaseDeDatos.C_COLUMNA_GOL_JUG1, partido.getGol_juga1());
		valores.put(BaseDeDatos.C_COLUMNA_GOL_JUG2, partido.getGol_juga2());
		valores.put(BaseDeDatos.C_COLUMNA_ID_JUG_GAN, partido.getId_juga_gan());
		valores.put(BaseDeDatos.C_COLUMNA_NUM_FECHA, partido.getNum_fecha());
		partido.setId(database.insert(BaseDeDatos.C_TABLA_PARTIDO, null, valores));
		System.out.println("el partido tiene id: "+partido.getId());
		return partido.getId();
		
	}
	public long modificarPartido(Partido partido){
		ContentValues valores = new ContentValues();
		valores.put(BaseDeDatos.C_COLUMNA_ID_PAR, partido.getId());
		valores.put(BaseDeDatos.C_COLUMNA_ID_TOR_PAR,partido.getId_torneo());
		valores.put(BaseDeDatos.C_COLUMNA_ID_JUG1, partido.getId_juga1());
		valores.put(BaseDeDatos.C_COLUMNA_ID_JUG2, partido.getId_juga2());
		valores.put(BaseDeDatos.C_COLUMNA_GOL_JUG1, partido.getGol_juga1());
		valores.put(BaseDeDatos.C_COLUMNA_GOL_JUG2, partido.getGol_juga2());
		valores.put(BaseDeDatos.C_COLUMNA_ID_JUG_GAN, partido.getId_juga_gan());
		valores.put(BaseDeDatos.C_COLUMNA_NUM_FECHA, partido.getNum_fecha());
		String where = BaseDeDatos.C_COLUMNA_ID_PAR+" = '"+partido.getId()+"'";
		where = where + " AND "+BaseDeDatos.C_COLUMNA_ID_TOR_PAR+" = '"+partido.getId_torneo()+"'";
		long x = database.update(BaseDeDatos.C_TABLA_PARTIDO, valores,where, null);
		System.out.println("DEVOLVIO ESTO: "+x+"y el where: "+where+" y el juga_gan "+partido.getId_juga_gan());
		return x;
	}
	
	public Partido obtenerxid(long idpartido,long idtorneo){
		Partido partido = null;
		System.out.println("Entro a la sentencia");
		Cursor c = database.rawQuery("SELECT * FROM partido where _ID='"+idpartido+"' and id_torneo ='"+idtorneo+"'", new String [] {});
		if(c.getCount()>0){
		System.out.println("encontre");
		c.moveToFirst();
		partido = cursorToPartido(c);
		c.close();
		}	
		return partido;
	}
	public List<Partido> obtenerlistadepartidos(long id_torneo){
		List<Partido> partidos = new ArrayList<Partido>();
		
		//Cursor c = database.query(BaseDeDatos.C_TABLA_JUGADOR, colum_jugador,null,null,null,null,null);
		Cursor c = database.rawQuery("SELECT * FROM partido where id_torneo='"+id_torneo+"' and id_juga_gan >= 0", new String [] {});
		
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			Partido partido = cursorToPartido(c);
			partidos.add(partido);
		}
		c.close();
		return partidos;
	}
	}
