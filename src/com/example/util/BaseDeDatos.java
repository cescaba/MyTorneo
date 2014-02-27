package com.example.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static android.provider.BaseColumns._ID;

@SuppressLint("NewApi")
public class BaseDeDatos extends SQLiteOpenHelper {
	public static final String C_TABLA_TORNEO = "torneo";
	public static final String C_COLUMNA_ID = "_ID";
	public static final String C_COLUMNA_NOMBRE = "nom_torneo";
	public static final String C_COLUMNA_NUM_JUGA = "num_juga";
	//MAH INI
	public static final String C_TABLA_JUGADOR = "jugador";
	public static final String C_COLUMNA_ID_JUG = "_ID";
	public static final String C_COLUMNA_ID_TOR = "id_torneo";
	public static final String C_COLUMNA_NOM_JUG = "nom_jugador";
	public static final String C_COLUMNA_PAR_GAN = "par_gan";
	public static final String C_COLUMNA_PAR_EMP = "par_emp";
	public static final String C_COLUMNA_PAR_PER = "par_per";
	public static final String C_COLUMNA_GOL_AFA = "gol_afa";
	public static final String C_COLUMNA_GOL_ENC = "gol_enc";
	public static final String C_COLUMNA_NUMERO = "numero";
	
	public static final String C_TABLA_PARTIDO = "partido";
	public static final String C_COLUMNA_ID_PAR = "_ID";
	public static final String C_COLUMNA_ID_TOR_PAR = "id_torneo";
	public static final String C_COLUMNA_ID_JUG1 = "id_juga1";
	public static final String C_COLUMNA_ID_JUG2 = "id_juga2";
	public static final String C_COLUMNA_GOL_JUG1 = "gol_juga1";
	public static final String C_COLUMNA_GOL_JUG2 = "gol_juga2";
	public static final String C_COLUMNA_ID_JUG_GAN = "id_juga_gan";
	public static final String C_COLUMNA_NUM_FECHA = "num_fecha";
	
	//MAH FIN
	public BaseDeDatos(Context ctx){
		super(ctx,"Mibase",null,3);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "CREATE TABLE torneo("+_ID+" INTEGER PRIMARY KEY";
		sql = sql + " AUTOINCREMENT, nom_torneo TEXT, num_juga INTEGER,fechas INTEGER);";
		db.execSQL(sql);
		
		sql = "CREATE TABLE jugador("+_ID+" INTEGER PRIMARY KEY";
		sql = sql + " AUTOINCREMENT, id_torneo INTEGER, nom_jugador TEXT";
		sql = sql + " ,par_gan INTEGER, par_emp INTEGER, par_per INTEGER,";
		sql = sql + " gol_afa INTEGER, gol_enc INTEGER, numero INTEGER);";
		db.execSQL(sql);
		
		sql = "CREATE TABLE partido("+_ID+" INTEGER PRIMARY KEY";
		sql = sql + " AUTOINCREMENT, id_torneo INTEGER, id_juga1 INTEGER";
		sql = sql + " ,id_juga2 INTEGER, gol_juga1 INTEGER, gol_juga2 INTEGER,";
		sql = sql + " id_juga_gan INTEGER, num_fecha INTEGER);";
		db.execSQL(sql);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS torneo");
		//MAH INI
		db.execSQL("DROP TABLE IF EXISTS jugador");
		db.execSQL("DROP TABLE IF EXISTS partido");
		//MAH FIN
		onCreate(db);
	}
	
	public void abrir(){
		this.getWritableDatabase();
	}
	public void cerrar(){
		this.close();
	}
	
}