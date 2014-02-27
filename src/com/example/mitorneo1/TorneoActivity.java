package com.example.mitorneo1;

import java.util.ArrayList;
import java.util.List;

import com.example.bean.Jugador;
import com.example.bean.Partido;
import com.example.bean.Torneo;
import com.example.dao.JugadorDAO;
import com.example.dao.PartidoDAO;
import com.example.util.GeneradorTorneo;
import android.app.AlertDialog;
import android.opengl.Visibility;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class TorneoActivity extends Activity {
	JugadorDAO helper;
	GeneradorTorneo aux;
	PartidoDAO helperpartido;
	long idtorneo = 0;
	long idparacti = 0;
	List<Partido> partidos;
	Button btningreso;
	TextView jugador1;
	TextView jugador2;
	TextView goles1;
	TextView goles2;
	long idfecha = 0;
	List<String> listapartidos;
	ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_torneo);
		listapartidos = new ArrayList<String>();
		btningreso = (Button)findViewById(R.id.btningresarresult);
		jugador1 = (TextView)findViewById(R.id.jugador1);
		jugador2 = (TextView)findViewById(R.id.jugador2);
		goles1 = (TextView)findViewById(R.id.marcador1);
		goles2 = (TextView)findViewById(R.id.marcador2);
		listView = (ListView) findViewById(R.id.listPartido);
		goles1.setText("0");
		goles2.setText("0");
		helper = new JugadorDAO(this);	
		helperpartido = new PartidoDAO(this);
		Bundle bundle = getIntent().getExtras();
		Torneo torneo = (Torneo)bundle.get("torneo");
		idtorneo = torneo.getId();
		helper.abrir();
		List<Jugador> jugadores = helper.leerJugadores(torneo.getId());
		//helper.cerrar();
		aux = new GeneradorTorneo();
		partidos = aux.generarFechas(torneo, jugadores);
		helperpartido.abrir();
		for(int i = 0;i<partidos.size();i++){
			System.out.println("Partido es id: "+partidos.get(i).getId()+" entre: "+partidos.get(i).getId_juga1()+" y "+partidos.get(i).getId_juga2());
			helperpartido.insertarPartido(partidos.get(i));
		}
		String texto1 = "";
		String texto2 = "";
		//helperpartido.cerrar();
		//helper.abrir();
		for(int i = 0; i<partidos.size();i++){
			if(partidos.get(i).getId_juga_gan() == -1){
				texto1 = helper.obtenerxid(partidos.get(i).getId_juga1(),partidos.get(i).getId_torneo());
				texto2 = helper.obtenerxid(partidos.get(i).getId_juga2(),partidos.get(i).getId_torneo());
				idparacti = partidos.get(i).getId();
				idfecha = partidos.get(i).getNum_fecha();
				break;
			}
		}
		//helper.cerrar();
		jugador1.setText(texto1);
		jugador2.setText(texto2);
        
		partidosnojugados(idfecha);
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, listapartidos);
        
        listView.setAdapter(adapter);
        
		btningreso.setOnClickListener(IngresarResultado());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_principal, menu);
		return true;
	}
	public View.OnClickListener IngresarResultado(){
		
		//helperpartido.abrir();
		//final Partido partido = helperpartido.obtenerxid(idtorneo, idpartido);
		//helperpartido.cerrar();
		View.OnClickListener listener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			System.out.println("Antes de cargar el partido: "+idparacti);
			Partido partido = buscarxidenlista(idparacti);
			partido.setGol_juga1(Integer.parseInt(goles1.getText().toString()));
			partido.setGol_juga2(Integer.parseInt(goles2.getText().toString()));
			System.out.println("goles firt: "+partido.getGol_juga1()+" vs goles second: "+partido.getGol_juga2());
			if(partido.getGol_juga1() > partido.getGol_juga2()){
				partido.setId_juga_gan(partido.getId_juga1());
			}else if(partido.getGol_juga1() < partido.getGol_juga2()){
				partido.setId_juga_gan(partido.getId_juga2());
			}else{
				partido.setId_juga_gan(0);
			}
			System.out.println("el jugador ganador: "+partido.getId_juga_gan());
			helperpartido.modificarPartido(partido);
			String texto1 = "";
			String texto2 = "";
			//helper.abrir();
			boolean flag = false;
			for(int i = 0; i<partidos.size();i++){
				if(partidos.get(i).getId_juga_gan() == -1){
					texto1 = helper.obtenerxid(partidos.get(i).getId_juga1(),partidos.get(i).getId_torneo());
					texto2 = helper.obtenerxid(partidos.get(i).getId_juga2(),partidos.get(i).getId_torneo());
					idparacti = partidos.get(i).getId();
					idfecha = partidos.get(i).getNum_fecha();
					flag = true;
					break;
				}
			}
			System.out.println("el partido siguiente es: "+idparacti+ "que es: "+texto1+" vs. "+texto2+"y la fecha es:"+idfecha);
			//helper.cerrar();
			if(flag){
			jugador1.setText(texto1);
			jugador2.setText(texto2);
			goles1.setText("0");
			goles2.setText("0");
			}else{
				mostraralerta("Se realizaron todo los partidos.");
			}
			listapartidos.clear();
			partidosnojugados(idfecha);
		}
	};
		return listener;
	}
	public void mostraralerta(String x){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Mensaje")
		.setMessage(x)
		.setCancelable(false)
		.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				// if this button is clicked, close
				// current activity
				//TorneoActivity.this.finish();
				btningreso.setVisibility(2);
			}
		  });
		builder.setMessage(x);
		AlertDialog alert = builder.create();
		alert.show();
	}
	public Partido buscarxidenlista(long id){
		Partido partido = null;
		for (int i= 0;i<partidos.size();i++){
			if(partidos.get(i).getId() == id){
				return partidos.get(i);
			}
		}
		return partido;
	}
	
	public void partidosnojugados(long idfecha){
		for(int i = 0; i<partidos.size();i++){
			if(partidos.get(i).getNum_fecha() == idfecha && partidos.get(i).getId_juga_gan() == -1){
				listapartidos.add(helper.obtenerxid(partidos.get(i).getId_juga1(),partidos.get(i).getId_torneo())+" vs. "+helper.obtenerxid(partidos.get(i).getId_juga2(),partidos.get(i).getId_torneo()));
			}
		}
	}
	public void irasalirOnClickHandler(View v) {
		Intent i = new Intent(this, MainActivity.class);
		finish();
		startActivity(i);
		}
}
