package io.github.laufi.heisserdraht.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class StoppUhrTest {

	@Test
	public void testZeitmessung() throws InterruptedException, StoppuhrNichtGestopptException {
		StoppUhr uhr = new StoppUhr();
		uhr.starteStoppuhr();
		Thread.sleep(7);
		uhr.stoppeStoppuhr();
		assertEquals(7, uhr.getVerbrauchteZeit());
	}
	@Test(expected=StoppuhrNichtGestopptException.class)
	public void testStoppuhrNichtGestoppt() throws StoppuhrNichtGestopptException{
		StoppUhr uhr = new StoppUhr();
		uhr.starteStoppuhr();
		uhr.getVerbrauchteZeit();
	}
	@Test(expected=StoppuhrNichtGestopptException.class)
	public void testStoppuhrSofortGestoppt() throws StoppuhrNichtGestopptException{
		StoppUhr uhr = new StoppUhr();
		uhr.starteStoppuhr();
		uhr.getVerbrauchteZeit();
	}
	@Test(expected=StoppuhrNichtGestopptException.class)
	public void testStoppuhrNichtGestartet() throws StoppuhrNichtGestopptException{
		StoppUhr uhr = new StoppUhr();
		uhr.stoppeStoppuhr();
		uhr.getVerbrauchteZeit();
	}
}
