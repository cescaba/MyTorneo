package com.example.mitorneo1;


import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.example.bean.*;
import com.example.dao.*;
import com.example.util.GeneradorTorneo;
import com.example.adapter.*;
public class NuevoActivity extends Activity {
	JugadorListAdapter adapter;
	JugadorDAO helper;
	TorneoDAO helper2;
	PartidoDAO helper3; //V1.4 CJCA
	GeneradorTorneo aux; //V1.4 CJCA
	List<Partido> partidos; //V1.4 CJCA
	int q = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nuevo);
		
		helper = new JugadorDAO(this);
		helper2 = new TorneoDAO(this);
		helper3 = new PartidoDAO(this);
		
		//controles
		final Button btnadd = (Button)findViewById(R.id.btnagregar);
		final Button btnsiguiente = (Button)findViewById(R.id.btnsiguiente);
		
		final ListView lista = (ListView)findViewById(R.id.listJugadores);
		
		//ListView
		final ArrayList<Jugador> list = new ArrayList<Jugador>();
		adapter = new JugadorListAdapter(this, R.layout.jugador_list_item, list);	
		lista.setAdapter(adapter);
		
		//Acciones con botones
		btnadd.setOnClickListener(ListenerAgregarJugador(list));
		btnsiguiente.setOnClickListener(ListenerGrabarTorneo(list,this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nuevo, menu);
		return true;
	}
	public View.OnClickListener ListenerNuevo(Intent i){
		final Intent a = i;
		View.OnClickListener listener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startActivity(a);
		}
	};
		return listener;
	}
	
	
	public void removeJugadorOnClickHandler(View v) {
		Jugador itemToRemove = (Jugador)v.getTag();
		adapter.remove(itemToRemove);
	}
	public View.OnClickListener ListenerAgregarJugador(final ArrayList<Jugador> jugadores){
		
		View.OnClickListener listener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Jugador jug = new Jugador(); //MAH
			jugadores.add(jug);
			adapter.notifyDataSetChanged();
		}
	};
		return listener;
	}
	
public View.OnClickListener ListenerGrabarTorneo(final ArrayList<Jugador> list, final Context ctx){ //MAH
		
		View.OnClickListener listener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			helper2.abrir();
			helper.abrir();
			helper3.abrir();
			EditText nomtorneo = (EditText)findViewById(R.id.txttitulo);
			String nombre = nomtorneo.getText().toString();
			Torneo torneo = new Torneo();
			
			if(nombre != ""){
				torneo.setName(nombre);
			}
			torneo.setNum_juga(list.size());
			
			torneo = helper2.insertarTorneo(torneo);
			
			for(int i = 0; i < list.size();i++){
				helper.insertarJugador(list.get(i).getName(), torneo.getId(),torneo.getNum_juga());	
			}
			//V1.4 CJCA INI
			List<Jugador> jugadores = helper.leerJugadores(torneo.getId());
			aux = new GeneradorTorneo();
			partidos = aux.generarFechas(torneo, jugadores); 
			
			for(int i = 0;i<partidos.size();i++){
				System.out.println("Partido es id: "+partidos.get(i).getId()+" entre: "+partidos.get(i).getId_juga1()+" y "+partidos.get(i).getId_juga2());
				helper3.insertarPartido(partidos.get(i));
			}
			
			helper.cerrar();
			helper2.cerrar();
			helper3.cerrar();
			//V1.4 CJCA FIN
			
			Intent i = new Intent(ctx, TorneoActivity.class);
			i.putExtra("torneo", torneo);
			finish();
			startActivity(i);
		}
	};
		return listener;
	}
}
