package io.github.laufi.heisserdraht.util;

public class Debounce{

    private volatile long lastCalled = 0;
    private int interval = 500;
    public boolean debounce() {
        if( lastCalled + interval < System.currentTimeMillis() ) {
            lastCalled = System.currentTimeMillis();
            return true;
        } else {
            return false;
        }
    }
}