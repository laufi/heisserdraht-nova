package io.github.laufi.heisserdraht;

import com.github.lalyos.jfiglet.FigletFont;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException{
        printWelcomeScreen();
    }
    private static void printWelcomeScreen() throws IOException {
        System.out.println(FigletFont.convertOneLine("Heißer Draht am OHG"));
        System.out.println(FigletFont.convertOneLine(FigletFont.class.getClassLoader().getResourceAsStream("slant.flf"), "Wer ist am schnellsten ?"));
    }
}