package model;

import java.lang.reflect.Type;

public class Variablen {
	
	// public, private, protected
	private int modifier;
	
	// String, Integer, int...
	private String datatype;
	
	// vorname, nachname, cooleVariable ...
	private String name;
	
	private Type type;

	public Variablen() {
		// TODO Auto-generated constructor stub
	}

	public int getModifier() {
		return modifier;
	}

	public void setModifier(int modifier) {
		this.modifier = modifier;
	}

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}
