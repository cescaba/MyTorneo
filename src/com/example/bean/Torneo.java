package com.example.bean;
import java.io.Serializable;
public class Torneo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name = "";
	private long id;
	private int num_juga;


	public Torneo(String name, int value){
		this.name = name;
		this.id = value;
	}

	public Torneo(){
		
	}
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public long getId() {
		return id;
	}


	public void setId(long l) {
		this.id = l;
	}

	public int getNum_juga() {
		return num_juga;
	}

	public void setNum_juga(int num_juga) {
		this.num_juga = num_juga;
	}

	}