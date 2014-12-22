package io.github.laufi.heisserdraht.util;

import static org.junit.Assert.*;
import org.junit.Test;

public class HighscoreTest {
    @Test
    public void testElementOutAndIn(){
        Highscore highscore = new Highscore();
        highscore.addScore(new HighscoreEintrag("test", 101));
        HighscoreEintrag eintrag = highscore.getScore(0);
        assertEquals(eintrag.getScore(), 101);
    }
}
