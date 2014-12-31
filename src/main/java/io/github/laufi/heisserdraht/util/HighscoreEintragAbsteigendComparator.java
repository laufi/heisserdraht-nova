package io.github.laufi.heisserdraht.util;

import java.util.Comparator;

public class HighscoreEintragAbsteigendComparator implements Comparator <HighscoreEintrag> {
    @Override
    public int compare(HighscoreEintrag eintrag1, HighscoreEintrag eintrag2) {
        if (eintrag2.getScore() - eintrag1.getScore() > 0) {
            return -1;
        } else if (eintrag1 == eintrag2) {
            return 0;
        } else {
            return 1;
        }
    }
}