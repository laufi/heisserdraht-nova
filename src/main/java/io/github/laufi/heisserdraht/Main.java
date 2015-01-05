package io.github.laufi.heisserdraht;

import com.github.lalyos.jfiglet.FigletFont;
import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.io.gpio.trigger.GpioPulseStateTrigger;
import dnl.utils.text.table.TextTable;
import io.github.laufi.heisserdraht.util.*;

import java.io.IOException;
import java.util.Scanner;


public class Main {
    static Scanner scanner = new Scanner(System.in);
    static final GpioController gpio = GpioFactory.getInstance();
    static GpioPinDigitalInput draht = gpio.provisionDigitalInputPin(RaspiPin.GPIO_05);
    static GpioPinDigitalOutput led = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04);
    public static void main(String[] args) throws IOException, StoppuhrNichtGestopptException {
        Highscore highscore = new Highscore();
        try {
            highscore.load();
        } catch (IOException e) {
        }
        draht.addTrigger(new GpioPulseStateTrigger(PinState.LOW, led, 550));
        home(highscore);
    }
    public static void home(Highscore highscore) throws IOException, StoppuhrNichtGestopptException {
        ClearConsole.clearConsole();
        printWelcomeScreen();
        printTop20(highscore);
        highscore.save();
        System.in.read();
        startGame(highscore);
    }
    private static void printWelcomeScreen() throws IOException {
        System.out.println(FigletFont.convertOneLine("Heißer Draht am OHG"));
        System.out.println(FigletFont.convertOneLine(FigletFont.class.getClassLoader().getResourceAsStream("slant.flf"), "Wer ist am schnellsten ?"));
    }
    private static void printTop20(Highscore highscore) {
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
        tt.printTable();

    }
    private static void printError(long count) throws IOException {
        ClearConsole.clearConsole();
        System.out.println(FigletFont.convertOneLine(String.valueOf(count) + " Fehler"));
    }
    private static void startGame(Highscore highscore) throws StoppuhrNichtGestopptException, IOException {
        Debounce debouncer = new Debounce();
        HeisserDrahtUhr spiel = new HeisserDrahtUhr();
        spiel.starteSpiel();
        printError(0);
        draht.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                if (debouncer.debounce()) {
                    spiel.neuerFehler();
                    try {
                        printError(spiel.getFehler());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        System.in.read();
        // Exception kann nicht geworfen werden / wäre fatal da Stoppuhr vorher gestartet wurde ==> Daher Abbruch bei Exception
        spiel.stoppeSpiel();
        draht.removeAllListeners();
        float zeit = (float) spiel.getVerbrauchteZeit() / 1000;
        float fehler = (float) spiel.getFehler();
        float punktestand = (float) spiel.getPunktestand() / 1000;
        ClearConsole.clearConsole();
        System.out.println(FigletFont.convertOneLine("Gut gemacht!"));
        String[] columnNames = {"Zeit", "Fehler", "Punkte"};
        Float[][] data = {{zeit,fehler, punktestand}};
        TextTable tt = new TextTable(columnNames, data);
        tt.printTable();
        System.out.print("Gib deinen Namen für den Highscore an:");
        String name = scanner.nextLine();
        if (name.equals("")){
            highscore.addScore(new HighscoreEintrag(punktestand));
        } else {
            highscore.addScore(new HighscoreEintrag(name, punktestand));
        }
        home(highscore);
    }
}