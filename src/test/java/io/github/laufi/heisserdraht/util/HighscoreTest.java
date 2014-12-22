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
    @Test
    public void testSortDesc(){
        Highscore highscore = new Highscore();
        highscore.addScore(new HighscoreEintrag(3000));
        highscore.addScore(new HighscoreEintrag(30));
        highscore.sortScore(new HighscoreEintragAbsteigendComparator());
        HighscoreEintrag eintrag = highscore.getScore(0);
        assertEquals(eintrag.getScore(), 30);
    }
    @Test
    public void testTop20(){
        Highscore highscore = new Highscore();
        for(int i = 0; i <= 50; i = i + 2){
            highscore.addScore(new HighscoreEintrag(i));
        }
        HighscoreEintrag[] top20 = highscore.top(20);
        assertEquals(top20[0].getScore(), 50);
        assertEquals(top20[3].getScore(), 42);
    }
}