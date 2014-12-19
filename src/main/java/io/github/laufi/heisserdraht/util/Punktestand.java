package io.github.laufi.heisserdraht.util;

public class Punktestand <T> {
	private String name;
	private T punktestand;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public T getPunktestand() {
		return punktestand;
	}
	public void setPunktestand(T punktestand) {
		this.punktestand = punktestand;
	}
}
