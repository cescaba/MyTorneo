package com.example.mitorneo1;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bean.Partido;
import com.example.dao.JugadorDAO;
import com.example.dao.PartidoDAO;
import com.example.dao.TorneoDAO;
import com.example.util.Ipsum;

public class PartidoFragment extends Fragment{
	final static String ARG_POSITION = "position";
	final static String TORNEO = "torneo";
    int mCurrentPosition = -1;
    long midtorneo = 0;
    PartidoDAO helperpartido;
	JugadorDAO helperjugador;
	TorneoDAO helpertorneo;
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.editor_partido, container, false);
	}
	 @Override
	    public void onStart() {
	        super.onStart();

	        // During startup, check if there are arguments passed to the fragment.
	        // onStart is a good place to do this because the layout has already been
	        // applied to the fragment at this point so we can safely call the method
	        // below that sets the article text.
	        Bundle args = getArguments();
	        if (args != null) {
	            // Set article based on argument passed in
	        	System.out.println("1. Dentro de PartidoFragment idTorneo: "+args.getLong(TORNEO));
	            updateArticleView(args.getInt(ARG_POSITION),args.getLong(TORNEO));
	        } else if (mCurrentPosition != -1) {
	            // Set article based on saved instance state defined during onCreateView
	            updateArticleView(mCurrentPosition,midtorneo);
	        }
	    }

	    public void updateArticleView(int position,long idtorneo) {
	    	helperpartido = new PartidoDAO(getActivity().getApplicationContext());
	    	helperjugador = new JugadorDAO(getActivity().getApplicationContext());
	    	helpertorneo = new TorneoDAO(getActivity().getApplicationContext());
	        helperpartido.abrir();
	        helperjugador.abrir();
	        helpertorneo.abrir();
	        System.out.println("1. Dentro de PartidoFragment idTorneo: "+idtorneo); 
	        
	        List<Partido> partidos = helperpartido.obtenerlistadepartidos(idtorneo);
	        
	        System.out.println("2. Dentro de PartidoFragment idTorneo: "+partidos.size()); 
	        
	        TextView jug1 = (TextView) getActivity().findViewById(R.id.editjugador1);
	        TextView jug2 = (TextView) getActivity().findViewById(R.id.editjugador2);
	        
	        TextView mar1 = (TextView) getActivity().findViewById(R.id.editmarcador1);
	        TextView mar2 = (TextView) getActivity().findViewById(R.id.editmarcador2);
	        
	        Button btngrabar = (Button)getActivity().findViewById(R.id.btngrabaredit);
	     
	        jug1.setText(helperjugador.obtenerxid(partidos.get(position).getId_juga1(), idtorneo));
	        jug2.setText(helperjugador.obtenerxid(partidos.get(position).getId_juga2(), idtorneo));
	        
	        System.out.println("3. Dentro de PartidoFragment idTorneo: "+partidos.get(position).getGol_juga1()); 
	        
	        mar1.setText(""+partidos.get(position).getGol_juga1());
	        mar2.setText(""+partidos.get(position).getGol_juga2());
	        
	        System.out.println("4. Dentro de PartidoFragment idTorneo: "+partidos.get(position).getGol_juga2()); 
	        
	        btngrabar.setOnClickListener(grabarmodificacion(partidos.get(position).getId(),mar1,mar2));
	        //TextView article = (TextView) getActivity().findViewById(R.id.partido);
	        //article.setText(Ipsum.Articles[position]);
	        mCurrentPosition = position;
	        midtorneo = idtorneo;
	        
	    }

	    @Override
	    public void onSaveInstanceState(Bundle outState) {
	        super.onSaveInstanceState(outState);

	        // Save the current article selection in case we need to recreate the fragment
	        outState.putInt(ARG_POSITION, mCurrentPosition);
	        outState.putLong(TORNEO, midtorneo);
	    }
	    
	    public View.OnClickListener grabarmodificacion(final long id, final TextView mar1, final TextView mar2){
			View.OnClickListener listener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println("Antes de cargar el partido: "+id);
				Partido partido = helperpartido.obtenerxid(id,midtorneo);
				partido.setGol_juga1(Integer.parseInt(mar1.getText().toString()));
				partido.setGol_juga2(Integer.parseInt(mar2.getText().toString()));
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
				
				Intent i = new Intent(getActivity().getApplicationContext(), TorneoActivity.class);
				i.putExtra("torneo", helpertorneo.obtenerxid(partido.getId_torneo()));
				getActivity().finish();
				startActivity(i);
				 
				
			}
		};
			return listener;
		}
}
