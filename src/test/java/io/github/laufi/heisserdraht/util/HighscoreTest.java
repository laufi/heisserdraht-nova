package io.github.laufi.heisserdraht.util;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

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
        assertEquals(eintrag.getScore(), 3000);
    }
    @Test
    public void testTop20(){
        Highscore highscore = new Highscore();
        for(int i = 0; i <= 50; i = i + 2){
            highscore.addScore(new HighscoreEintrag(i));
        }
        HighscoreEintrag[] top20 = highscore.top(20);
        assertEquals(top20[0].getScore(), 50);
        assertEquals(top20[4].getScore(), 42);
    }
    @Test
    public void testTop20ZuWenig(){
        Highscore highscore = new Highscore();
        for(int i = 0; i <= 20; i = i + 2){
            highscore.addScore(new HighscoreEintrag(i));
        }
        HighscoreEintrag[] top20 = highscore.top(20);
        assertEquals(top20[0].getScore(), 20);
        assertEquals(top20[4].getScore(), 12);
        assertEquals(top20[19].getScore(), 0);
    }
    @Test
    public void testSaveAndLoad() throws IOException {
        Highscore highscore = new Highscore();
        for(int i = 0; i <= 50; i = i + 2){
            highscore.addScore(new HighscoreEintrag(i));
        }
        highscore.save();
        Highscore anotherScore = new Highscore();
        anotherScore.load();
        assertEquals(50, anotherScore.getScore(25).getScore());
    }
    @AfterClass
    public static void tearDown() {
        try{
            File file = new File("highscore.dat");
            if(file.delete()){
                System.out.println(file.getName() + " is deleted!");
            }else{
                System.out.println("Delete operation is failed.");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}