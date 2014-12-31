package io.github.laufi.heisserdraht;

import com.github.lalyos.jfiglet.FigletFont;
import dnl.utils.text.table.TextTable;
import io.github.laufi.heisserdraht.util.Highscore;
import io.github.laufi.heisserdraht.util.HighscoreEintrag;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException{
        printWelcomeScreen();
        printTop20();
    }
    private static void printWelcomeScreen() throws IOException {
        System.out.println(FigletFont.convertOneLine("Heißer Draht am OHG"));
        System.out.println(FigletFont.convertOneLine(FigletFont.class.getClassLoader().getResourceAsStream("slant.flf"), "Wer ist am schnellsten ?"));
    }
    private static void printTop20() {
        Highscore highscore = new Highscore();
        try {
            highscore.load();
        } catch (IOException e) {
        }
        String[] columnNames = {"Name", "Score"};
        Object[][] data = new Object[20][2];
        HighscoreEintrag[] top20 = highscore.top(20);
        for(int i = 0; i < top20.length; i++) {
            data[i][0] = top20[i].getName();
            data[i][1] = top20[i].getScore();
        }
        TextTable tt = new TextTable(columnNames, data);
        // this adds the numbering on the left
        tt.setAddRowNumbering(true);
        // sort by the first column
        tt.setSort(0);
        tt.printTable();
    }
}