package io.github.laufi.heisserdraht.util;

import java.util.Comparator;

public class HighscoreEintragAbsteigendComparator implements Comparator <HighscoreEintrag> {
    @Override
    public int compare(HighscoreEintrag eintrag1, HighscoreEintrag eintrag2) {
        return eintrag1.getScore() - eintrag2.getScore();
    }
}