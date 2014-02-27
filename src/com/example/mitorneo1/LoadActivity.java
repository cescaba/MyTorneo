package com.example.mitorneo1;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
//MAH INI
import com.example.adapter.*;
import com.example.bean.*;
import com.example.dao.*;
//MAH FIN

public class LoadActivity extends Activity {
	TorneoListAdapter adapter;
	TorneoDAO helper;
	JugadorListAdapter adapter1;
	JugadorDAO helper1; //MAH
	Cursor cursor;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_load);
		ListView lista = (ListView)findViewById(R.id.listtorneo);
		
		helper = new TorneoDAO(this);
		helper.abrir();	
		List<Torneo> values = helper.leerTorneos();	
		helper.cerrar(); 
	

		
		System.out.println("antes del cursor: "+values.get(0).getName());
		adapter = new TorneoListAdapter(this, R.layout.torneo_list_item, values);
		
		lista.setAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.load, menu);
		return true;
	}
	public void removeTorneoOnClickHandler(View v) {
	Torneo itemToRemove = (Torneo)v.getTag();
		adapter.remove(itemToRemove);
		helper.abrir();
		helper.deleteTorneo(itemToRemove);
		helper.cerrar();
	}
	public void iraTorneoOnClickHandler(View v) {
		Torneo item = (Torneo)v.getTag();
		//Intent i  = new Intent(this,TorneoActivity.class);
		//i.putExtra("nombre", item.getName());
		//System.out.println("pasooo 1: "+item.getName());
		//startActivity(i);
		
	}
	//MAH FIN
	
	public void mostraralerta(String x){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(x);
		AlertDialog alert = builder.create();
		alert.show();
	}
}
