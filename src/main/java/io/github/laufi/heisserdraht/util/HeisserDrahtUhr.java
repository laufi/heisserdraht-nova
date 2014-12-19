package io.github.laufi.heisserdraht.util;

public class HeisserDrahtUhr extends StoppUhr {
	private long punktestand, fehler;
	public void starteSpiel(){
		starteStoppuhr();
	}
	public void neuerFehler(){
		fehler++;
	}
	public long getFehler(){
		return fehler;
	}
	public long getPunktestand() throws StoppuhrNichtGestopptException{
		if (punktestand == 0){
			throw new StoppuhrNichtGestopptException();
		}
		return punktestand;
	}
	public void stoppeSpiel() throws StoppuhrNichtGestopptException{
		stoppeStoppuhr();
		punktestand=getVerbrauchteZeit()+fehler*5;
	}
}
