package com.example.bean;

import java.io.Serializable;

public class Jugador implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String name = "";
private int id;
private int id_torneo;
private int par_gan;
private int par_emp;
private int par_per;
private int gol_afa;
private int gol_enc;
private int numero;


public Jugador(String name, int value){
	this.name = name; //MAH
	this.id = value; //MAH
}

public int getId_torneo() {
	return id_torneo;
}

public void setId_torneo(int id_torneo) {
	this.id_torneo = id_torneo;
}

public int getPar_gan() {
	return par_gan;
}

public void setPar_gan(int par_gan) {
	this.par_gan = par_gan;
}

public int getPar_emp() {
	return par_emp;
}

public void setPar_emp(int par_emp) {
	this.par_emp = par_emp;
}

public int getPar_per() {
	return par_per;
}

public void setPar_per(int par_per) {
	this.par_per = par_per;
}

public int getGol_afa() {
	return gol_afa;
}

public void setGol_afa(int gol_afa) {
	this.gol_afa = gol_afa;
}

public int getGol_enc() {
	return gol_enc;
}

public void setGol_enc(int gol_enc) {
	this.gol_enc = gol_enc;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}

public Jugador(){
	
}
public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public int getId() {
	return id;
}


public void setId(int id) {
	this.id = id;
}

public int getNumero() {
	return numero;
}

public void setNumero(int numero) {
	this.numero = numero;
}

}
