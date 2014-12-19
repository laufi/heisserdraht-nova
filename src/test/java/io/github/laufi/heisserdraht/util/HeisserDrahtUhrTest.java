package io.github.laufi.heisserdraht.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class HeisserDrahtUhrTest {

	@Test
	public void testZeitmessung() throws InterruptedException, StoppuhrNichtGestopptException {
		HeisserDrahtUhr uhr= new HeisserDrahtUhr();
		uhr.starteSpiel();
		Thread.sleep(7);
		uhr.stoppeSpiel();
		assertEquals(7, uhr.getVerbrauchteZeit());
	}
	@Test
	public void testFehlerfreiesSpiel() throws InterruptedException, StoppuhrNichtGestopptException{
		HeisserDrahtUhr uhr= new HeisserDrahtUhr();
		uhr.starteSpiel();
		Thread.sleep(7);
		uhr.stoppeSpiel();
		assertEquals(uhr.getPunktestand(), uhr.getVerbrauchteZeit());
	}
	@Test
	public void testSpielMitFehlern() throws InterruptedException, StoppuhrNichtGestopptException{
		HeisserDrahtUhr uhr= new HeisserDrahtUhr();
		uhr.starteSpiel();
		Thread.sleep(7);
		uhr.neuerFehler();
		uhr.neuerFehler();
		Thread.sleep(3);
		uhr.stoppeSpiel();
		assertEquals(20, uhr.getPunktestand());
		assertEquals(2, uhr.getFehler());
	}
	@Test(expected=StoppuhrNichtGestopptException.class)
	public void testStoppuhrNichtGestoppt() throws StoppuhrNichtGestopptException{
		HeisserDrahtUhr uhr = new HeisserDrahtUhr();
		uhr.starteStoppuhr();
		uhr.getPunktestand();
	}
}
