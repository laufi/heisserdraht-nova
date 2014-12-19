package io.github.laufi.heisserdraht.util;

public class StoppuhrNichtGestopptException extends Exception {
	public StoppuhrNichtGestopptException () {
		super("Stoppuhr nicht gestartet!");
	}
}
