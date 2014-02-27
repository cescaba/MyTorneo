package com.example.bean;
import java.io.Serializable;

public class Partido implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	private long id_torneo;
	private long id_juga1;
	private long id_juga2;
	private int gol_juga1;
	private int gol_juga2;
	private long id_juga_gan;
	private int num_fecha;
	
	public Partido(){
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getId_torneo() {
		return id_torneo;
	}
	public void setId_torneo(long id_torneo) {
		this.id_torneo = id_torneo;
	}
	public long getId_juga1() {
		return id_juga1;
	}
	public void setId_juga1(long id_juga1) {
		this.id_juga1 = id_juga1;
	}
	public long getId_juga2() {
		return id_juga2;
	}
	public void setId_juga2(long id_juga2) {
		this.id_juga2 = id_juga2;
	}
	public int getGol_juga1() {
		return gol_juga1;
	}
	public void setGol_juga1(int gol_juga1) {
		this.gol_juga1 = gol_juga1;
	}
	public int getGol_juga2() {
		return gol_juga2;
	}
	public void setGol_juga2(int gol_juga2) {
		this.gol_juga2 = gol_juga2;
	}
	public long getId_juga_gan() {
		return id_juga_gan;
	}
	public void setId_juga_gan(long id_juga_gan) {
		this.id_juga_gan = id_juga_gan;
	}
	public int getNum_fecha() {
		return num_fecha;
	}
	public void setNum_fecha(int num_fecha) {
		this.num_fecha = num_fecha;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
