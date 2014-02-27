package com.example.util;

import java.util.ArrayList;
import java.util.List;

import com.example.bean.*;

public class GeneradorTorneo {

	public int fechas;
	public int partidosxfechas;
	public int qjugadores;
	public int totalpartidos;
	
	public GeneradorTorneo(){
		
	}
	public List<Partido> generarFechas(Torneo torneo, List<Jugador> jugadores){
		List<Partido> partidos = new ArrayList<Partido>();
		qjugadores = jugadores.size();
		if(espar(qjugadores)){
			fechas = qjugadores -1;
			partidosxfechas = qjugadores/2;
		}else{
			fechas = qjugadores;
			partidosxfechas = (qjugadores -1)/2;
		 		Jugador aux = new Jugador();
		 		aux.setId(-1);
		 		aux.setName("aux");
		 		jugadores.add(aux);
		}
		totalpartidos = (qjugadores*(qjugadores-1))/2;
		for(int i = 1; i<=fechas;i++){
			
			mostrar(jugadores,partidos,i,torneo);
			combinar(jugadores);
		}
		if(!espar(qjugadores)){
			jugadores.remove(jugadores.size()-1);
		}
		
		return partidos;
	}
	public Boolean espar(int num){
		if(num%2==0){
			return true;
		}
		return false;
	}
	 public void combinar(List<Jugador> equipos){  
		 	
	        Jugador buffer = new Jugador();
	        buffer.setId(equipos.get(equipos.size()-1).getId());
	        buffer.setId_torneo(equipos.get(equipos.size()-1).getId_torneo());
	        buffer.setName(equipos.get(equipos.size()-1).getName());
	        buffer.setNumero(equipos.get(equipos.size()-1).getNumero());
        	
	        for (int i = equipos.size()-1; i > 1; i--) {
	        	equipos.get(i).setId(equipos.get(i-1).getId());
	        	equipos.get(i).setId_torneo(equipos.get(i-1).getId_torneo());
	        	equipos.get(i).setName(equipos.get(i-1).getName());
	        	equipos.get(i).setNumero(equipos.get(i-1).getNumero());
	        }
	        
	        equipos.get(1).setId(buffer.getId());
        	equipos.get(1).setId_torneo(buffer.getId_torneo());
        	equipos.get(1).setName(buffer.getName());
        	equipos.get(1).setNumero(buffer.getNumero());
    
	    }

	    public void mostrar(List<Jugador> equipos, List<Partido> partidos, int fecha, Torneo torneo){
	        int j = equipos.size() -1;
	    	for (int i = 0; i<j; i++) {
	    		if(equipos.get(i).getId()!= -1 && equipos.get(j).getId() != -1){
	        	Partido partido  = new Partido();
	        	partido.setId_torneo(torneo.getId());
	        	partido.setNum_fecha(fecha);
	        	partido.setId_juga_gan(-1);
	        	partido.setId_juga1(equipos.get(i).getId());
	        	partido.setId_juga2(equipos.get(j).getId());
	        	System.out.println(" fecha: "+fecha+" jugador1: "+equipos.get(i).getName()+" vs. "+equipos.get(j).getName());
	        	partidos.add(partido);
	    		}
	        	j--;
	        }
	    }
}
