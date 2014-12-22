package io.github.laufi.heisserdraht.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Highscore{
    private List<HighscoreEintrag> score = new ArrayList<HighscoreEintrag>();
    public void addScore(HighscoreEintrag score){
        this.score.add(score);
    }
    public HighscoreEintrag getScore (int nummer) {
        return this.score.get(nummer);
    }
    public void sortScore(Comparator comparator){
        Collections.sort(this.score, comparator);
    }
    public HighscoreEintrag[] top (int top){
        sortScore(new HighscoreEintragAbsteigendComparator());
        HighscoreEintrag[] ergebnis = new HighscoreEintrag[20];
        for (int i=0; i < 20; i++){
            ergebnis[i] = getScore(i);
        }
        return  ergebnis;
    }
}