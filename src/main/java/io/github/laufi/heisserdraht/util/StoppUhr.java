package io.github.laufi.heisserdraht.util;

public class StoppUhr {
	private long verbrauchteZeit, startZeit, endZeit;
	protected void starteStoppuhr(){
		startZeit = System.currentTimeMillis();
	}
	protected void stoppeStoppuhr() throws StoppuhrNichtGestopptException{
		if (startZeit == 0){
			throw new StoppuhrNichtGestopptException();
		}
		endZeit = System.currentTimeMillis();
		verbrauchteZeit = endZeit - startZeit;
	}
	public long getVerbrauchteZeit () throws StoppuhrNichtGestopptException{
		if (verbrauchteZeit == 0){
			throw new StoppuhrNichtGestopptException();
		}
		return verbrauchteZeit;
	}
}
