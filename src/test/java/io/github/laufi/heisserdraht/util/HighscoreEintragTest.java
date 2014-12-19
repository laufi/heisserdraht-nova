package io.github.laufi.heisserdraht.util;

import static org.junit.Assert.*;
import org.junit.Test;

public class HighscoreEintragTest{
	@Test
	public void testPOJOConstructor (){
		HighscoreEintrag eintrag = new HighscoreEintrag("test",101);
		assertTrue(eintrag.getName().equals("test"));
		assertEquals(eintrag.getScore(), 101);
	}
	@Test
	public void testPOJOSets (){
		HighscoreEintrag eintrag = new HighscoreEintrag();
		eintrag.setName("test");
		eintrag.setScore(101);
		assertTrue(eintrag.getName().equals("test"));
		assertEquals(eintrag.getScore(), 101);
	}
}